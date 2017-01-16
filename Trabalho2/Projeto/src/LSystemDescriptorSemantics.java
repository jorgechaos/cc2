/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t2;

import java.util.ArrayList;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import t2.LSystemDescriptorParser.*;

/**
 *
 * @author Jorge Bernardo
 */
public class LSystemDescriptorSemantics extends LSystemDescriptorBaseListener {
    ArrayList<Character> alphabet = new ArrayList();
    
    public void enterAlphabet(AlphabetContext ctx){
        System.out.println("O Listener disse: " + ctx.getText());
    }
    
    public void exitAlphabet(AlphabetContext ctx){
        System.out.println("O Listener disse: " + ctx.getText());
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
    
    public void enterParameter(LSystemDescriptorParser.ParameterContext ctx) { 
    }
    
    public void exitParameter(LSystemDescriptorParser.ParameterContext ctx) { 
    }
    
    public void enterArithmeticExpression(LSystemDescriptorParser.ArithmeticExpressionContext ctx) { 
    }
    
    public void exitArithmeticExpression(LSystemDescriptorParser.ArithmeticExpressionContext ctx) { 
    }
	
    public void enterTerm(LSystemDescriptorParser.TermContext ctx) { 
    }
	
    public void exitTerm(LSystemDescriptorParser.TermContext ctx) { 
    }
	
    public void enterFactor(LSystemDescriptorParser.FactorContext ctx) { 
    }
	
    public void exitFactor(LSystemDescriptorParser.FactorContext ctx) { 
    }
	
    public void enterAxiom(LSystemDescriptorParser.AxiomContext ctx) { 
    }
	
    public void exitAxiom(LSystemDescriptorParser.AxiomContext ctx) { 
    }

    public void enterRules(LSystemDescriptorParser.RulesContext ctx) { 
    }
	
    public void exitRules(LSystemDescriptorParser.RulesContext ctx) { 
    }
	
    public void enterSingleRule(LSystemDescriptorParser.SingleRuleContext ctx) { 
    }

    public void exitSingleRule(LSystemDescriptorParser.SingleRuleContext ctx) { 
    }
	
    public void enterResultExpression(LSystemDescriptorParser.ResultExpressionContext ctx) { 
    }
	
    public void exitResultExpression(LSystemDescriptorParser.ResultExpressionContext ctx) { 
    }
	
    public void enterSymbolParameter(LSystemDescriptorParser.SymbolParameterContext ctx) { 
    }
	
    public void exitSymbolParameter(LSystemDescriptorParser.SymbolParameterContext ctx) { 
    }
	
    public void enterRulesArithmeticExpression(LSystemDescriptorParser.RulesArithmeticExpressionContext ctx) { 
    }
	
    public void exitRulesArithmeticExpression(LSystemDescriptorParser.RulesArithmeticExpressionContext ctx) { 
    }

    public void enterRulesTerm(LSystemDescriptorParser.RulesTermContext ctx) { 
    }
	
    public void exitRulesTerm(LSystemDescriptorParser.RulesTermContext ctx) { 
    }

    public void enterRulesFactor(LSystemDescriptorParser.RulesFactorContext ctx) { 
    }

    public void exitRulesFactor(LSystemDescriptorParser.RulesFactorContext ctx) { 
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
