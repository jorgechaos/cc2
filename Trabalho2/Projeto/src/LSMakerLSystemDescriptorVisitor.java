import org.antlr.v4.runtime.tree.TerminalNode;

import LSystemDescriptorParser.AlphabetContext;
import LSystemDescriptorParser.AngleContext;
import LSystemDescriptorParser.AxiomContext;
import LSystemDescriptorParser.CommandContext;
import LSystemDescriptorParser.DescriptionContext;
import LSystemDescriptorParser.RulesContext;
import LSystemDescriptorParser.SettingsContext;
import LSystemDescriptorParser.SingleRuleContext;
import LSystemDescriptorParser.SizeContext;
import LSystemDescriptorParser.SymbolWithCommandContext;

public class LSMakerLSystemDescriptorVisitor extends LSystemDescriptorBaseVisitor<Object> {
	LSystem ls;
	AlphabetTable at;
	
	public LSystem visitDescription(DescriptionContext ctx) {
		this.ls = new LSystem();
		
		visit(ctx.alphabet());
		
		visit(ctx.axiom());
		
		visit(ctx.rules());
		
		visit(ctx.settings());
		
		return ls;
	}
	
	public Void visitAlphabet(AlphabetContext ctx) {
		at = new AlphabetTable();

		if (ctx.symbolWithCommand() != null && !ctx.symbolWithCommand().isEmpty()) {
			for(SymbolWithCommandContext swcctx : ctx.symbolWithCommand()) {
				visit(swcctx);
			}
		}
		
		ls.setAlpTab(at);
		
		return null;
	}
	
	public Void visitSymbolWithCommand(SymbolWithCommandContext ctx) {
		
		if (ctx.ALPHABET_SYMBOL() != null && !ctx.ALPHABET_SYMBOL().getText().isEmpty()) {
			if(ctx.command() != null && !ctx.command().isEmpty()) {
				at.add(ctx.ALPHABET_SYMBOL().getText().charAt(1), (TurtleCommands) visit(ctx.command()));
			}
			else {
				at.add(ctx.ALPHABET_SYMBOL().getText().charAt(1), TurtleCommands.NONE);
			}			
		}
		
		return null;
	}
	
	public TurtleCommands visitCommand(CommandContext ctx) {
		if(ctx.OP_FORWARD() != null && !ctx.OP_FORWARD().getText().isEmpty()) {
			return TurtleCommands.FORWARD;
		}
		
		if(ctx.OP_FORWARD_NODRAW() != null && !ctx.OP_FORWARD_NODRAW().getText().isEmpty()) {
			return TurtleCommands.FORWARD_NODRAW;
		}
		
		if(ctx.OP_ROTATE_CCW() != null && !ctx.OP_ROTATE_CCW().getText().isEmpty()) {
			return TurtleCommands.ROTATE_CCW;
		}
		
		if(ctx.OP_ROTATE_CW() != null && !ctx.OP_ROTATE_CW().getText().isEmpty()) {
			return TurtleCommands.ROTATE_CW;
		}
		if(ctx.OP_RESET() != null && !ctx.OP_RESET().getText().isEmpty()) {
			return TurtleCommands.RESET;
		}
		if(ctx.OP_PUSH() != null && !ctx.OP_PUSH().getText().isEmpty()) {
			return TurtleCommands.PUSH;
		}
		
		if(ctx.OP_POP() != null && !ctx.OP_POP().getText().isEmpty()) {
			return TurtleCommands.POP;
		}
		
		if(ctx.OP_FORGET() != null && !ctx.OP_FORGET().getText().isEmpty()) {
			return TurtleCommands.FORGET;
		}
		
		return TurtleCommands.NONE;
	}
	
	public Void visitAxiom(AxiomContext ctx) {
		String axiom = "";
		
		if(ctx.ALPHABET_SYMBOL() != null && !ctx.ALPHABET_SYMBOL().isEmpty())
			for(TerminalNode as : ctx.ALPHABET_SYMBOL()) {
				axiom += as.getText().charAt(1);
			}

		ls.setAxiom(axiom);
		return null;
	}
	
	public Void visitRules(RulesContext ctx) {		
		if(ctx.singleRule() != null && !ctx.singleRule().isEmpty())
		for (SingleRuleContext srCtx : ctx.singleRule()) {
			String result =	(String)visit(srCtx);
			ls.addRule(result.charAt(0), result.substring(1));
		}
		
		return null;
	}
	
	public String visitSingleRule(LSystemDescriptorParser.SingleRuleContext ctx) {
		String ret = "";
		
		if (ctx.ALPHABET_SYMBOL() != null && !ctx.ALPHABET_SYMBOL().isEmpty()) {
			ret += ctx.ALPHABET_SYMBOL(0).getText().charAt(1);
			
			for (int i = 1; i<ctx.ALPHABET_SYMBOL().size(); i++) {
				ret += ctx.ALPHABET_SYMBOL(i).getText().charAt(1);
			}
		}
			
		return ret;
	}
	
	public Void visitSettings(SettingsContext ctx) {
		if(ctx.size() != null && !ctx.size().isEmpty())
			visit(ctx.size());
		if(ctx.angle() != null && !ctx.angle().isEmpty())
			visit(ctx.angle());
		ls.setLen(10);
		
		return null;
	}
	
	public Void visitAngle(AngleContext ctx) {
		if(ctx.REAL() != null && !ctx.REAL().getText().isEmpty())
			ls.setAng(Float.parseFloat(ctx.REAL().getText()));
		if(ctx.INTEGER() != null && !ctx.INTEGER().getText().isEmpty()) {
			ls.setAng(Integer.parseInt(ctx.INTEGER().getText()));
		}
		return null;
	}
	
	public Void visitSize(SizeContext ctx) {
		if(ctx.REAL() != null && !ctx.REAL().getText().isEmpty())
			ls.setLen(Float.parseFloat(ctx.REAL().getText()));
		if(ctx.INTEGER() != null && !ctx.INTEGER().getText().isEmpty()) {
			ls.setLen(Integer.parseInt(ctx.INTEGER().getText()));
		}
		return null;
	}
}
