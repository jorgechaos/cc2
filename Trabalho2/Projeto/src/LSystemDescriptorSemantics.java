/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho2;

import java.util.ArrayList;
import java.util.HashMap;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import trabalho2.LSystemDescriptorParser.*;
import trabalho2.TurtleCommands;
/**
 *
 * @author Jorge Bernardo
 */
public class LSystemDescriptorSemantics extends LSystemDescriptorBaseListener {
    AlphabetTable tabelaDeSimbolos = new AlphabetTable();

    
    public void enterAlphabet(AlphabetContext ctx){
        
    }
    
    public void exitAlphabet(AlphabetContext ctx){
        Character alphabetSymbol;
        String command;
        HashMap<Character, TurtleCommands> hm = new HashMap<>();
        
        if (ctx.symbolWithCommand() != null && !ctx.symbolWithCommand().isEmpty()) {
            for (SymbolWithCommandContext swcCtx : ctx.symbolWithCommand()) {
                if (swcCtx != null && !swcCtx.isEmpty()) {
                    if(swcCtx.ALPHABET_SYMBOL() != null && !swcCtx.ALPHABET_SYMBOL().getText().isEmpty()) {
                        System.out.println(swcCtx.ALPHABET_SYMBOL().getText().charAt(1));
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
