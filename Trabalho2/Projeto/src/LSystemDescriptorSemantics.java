import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class LSystemDescriptorSemantics extends LSystemDescriptorBaseListener {
	HashMap<Character, TurtleCommands> hm = new HashMap<>();
	AlphabetTable tabelaDeSimbolos = new AlphabetTable(hm);

	public void enterAlphabet(AlphabetContext ctx){

	}

	public void exitAlphabet(AlphabetContext ctx){
		Character alphabetSymbol;

		if (ctx.symbolWithCommand() != null && !ctx.symbolWithCommand().isEmpty()) {
			for (SymbolWithCommandContext swcCtx : ctx.symbolWithCommand()) {
				if (swcCtx != null && !swcCtx.isEmpty()) {
					if(swcCtx.ALPHABET_SYMBOL() != null && !swcCtx.ALPHABET_SYMBOL().getText().isEmpty()) {
						alphabetSymbol = swcCtx.ALPHABET_SYMBOL().getText().charAt(1);
						//System.out.println(alphabetSymbol);
						if(tabelaDeSimbolos.hasKey(alphabetSymbol)){
							//Erro Semantico De Simbolo ja declarado
							System.out.println("Simbolo já Declarado!");
							//System.out.println(tabelaDeSimbolos.getAlphabetSymbols());
						} else {
							//Verifica se o simbolo possui um comando
							if(swcCtx.command() != null && !swcCtx.command().isEmpty()){
								
								if (swcCtx.command().OP_FORWARD() != null && !swcCtx.command().OP_FORWARD().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORWARD);
									break;
								}
								
								if (swcCtx.command().OP_FORWARD_NODRAW() != null && !swcCtx.command().OP_FORWARD_NODRAW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORWARD_NODRAW);
									break;
								}
								
								if (swcCtx.command().OP_ROTATE_CCW() != null && !swcCtx.command().OP_ROTATE_CCW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.ROTATE_CCW);
									break;
								}
								
								if (swcCtx.command().OP_ROTATE_CW() != null && !swcCtx.command().OP_ROTATE_CW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.ROTATE_CW);
									break;
								}
								
								if (swcCtx.command().OP_RESET() != null && !swcCtx.command().OP_RESET().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.RESET);
									break;
								}
								
								if (swcCtx.command().OP_PUSH() != null && !swcCtx.command().OP_PUSH().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.PUSH);
									break;
								}
								
								if (swcCtx.command().OP_POP() != null && !swcCtx.command().OP_POP().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.POP);
									break;
								}
								
								if (swcCtx.command().OP_FORGET() != null && !swcCtx.command().OP_FORGET().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORGET);
									break;
								}
							}
							else
								hm.put(alphabetSymbol, TurtleCommands.NONE);
						}
						//Adiciona simbolo e comando na tabela
						tabelaDeSimbolos.setAlphabetSymbols(hm);
					}
				}
			}
		}    
	}


	public void enterDescription(DescriptionContext ctx) {

	}

	public void exitDescription(DescriptionContext ctx) { 
	}

	public void enterSymbolWithCommand(LSystemDescriptorParser.SymbolWithCommandContext ctx) { 
	}

	public void exitSymbolWithCommand(LSystemDescriptorParser.SymbolWithCommandContext ctx) {
	}

	public void enterCommand(LSystemDescriptorParser.CommandContext ctx) { 
	}

	public void exitCommand(LSystemDescriptorParser.CommandContext ctx) { 
	}

	public void enterAxiom(LSystemDescriptorParser.AxiomContext ctx) { 
	}

	public void exitAxiom(LSystemDescriptorParser.AxiomContext ctx) { 
		Character alphabetSymbol;

		if(ctx.ALPHABET_SYMBOL() != null && !ctx.ALPHABET_SYMBOL().isEmpty() ){
			for(TerminalNode tn : ctx.ALPHABET_SYMBOL()){
				alphabetSymbol = tn.getText().charAt(1);
				//System.out.println(alphabetSymbol);
				//Verifica se o simbolo foi declarado
				if(!tabelaDeSimbolos.hasKey(alphabetSymbol)){
					//Erro Semantico de simbolo nao declado
					System.out.println(alphabetSymbol + " Axioma Não Declarado");
				}
			}
		}
	}

	public void enterRules(LSystemDescriptorParser.RulesContext ctx) { 
	}

	public void exitRules(LSystemDescriptorParser.RulesContext ctx) {
		Character alphabetSymbol;

		if(ctx.singleRule() != null && !ctx.singleRule().isEmpty()){
			for(SingleRuleContext rule : ctx.singleRule()){
				if(rule.ALPHABET_SYMBOL() != null && !rule.ALPHABET_SYMBOL().isEmpty()){
					alphabetSymbol = rule.ALPHABET_SYMBOL().get(0).getText().charAt(1);
					//System.out.println(alphabetSymbol);
					//Verificar se os simbolos foram declarados no alfabeto
					if(!tabelaDeSimbolos.hasKey(alphabetSymbol)){
						//Erro semantico de simbolo nao declarado
						//System.out.println(alphabetSymbol + " Regra Não Declarado");
					}
				}
			}
		}

	}

	public void enterSingleRule(LSystemDescriptorParser.SingleRuleContext ctx) { 
	}

	public void exitSingleRule(LSystemDescriptorParser.SingleRuleContext ctx) { 
	}

	public void enterEveryRule(ParserRuleContext ctx) { 
	}

	public void exitEveryRule(ParserRuleContext ctx) { 
	}

	public void visitTerminal(TerminalNode node) { 
	}

	public void visitErrorNode(ErrorNode node) { 
	}

}
