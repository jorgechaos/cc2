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
    HashMap<Character, TurtleCommands> hm = new HashMap<>();
    AlphabetTable tabelaDeSimbolos = new AlphabetTable(hm);
    
    public void enterAlphabet(AlphabetContext ctx){
        
    }
    
    public void exitAlphabet(AlphabetContext ctx){
        Character alphabetSymbol;
        String command;
        
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
                            if(swcCtx.command() != null){
                                command = swcCtx.getText().substring(4);
                                //System.out.println(alphabetSymbol + " " + command);
                                switch(command){
                                    case "FORWARD":
                                    hm.put(alphabetSymbol, TurtleCommands.FORWARD);
                                    break;
                                case "FORWARD_NODRAW":
                                    hm.put(alphabetSymbol, TurtleCommands.FORWARD_NODRAW);
                                    break;
                                case "ROTATE":
                                    hm.put(alphabetSymbol, TurtleCommands.ROTATE);
                                    break;
                                case "SET_DIR":
                                    hm.put(alphabetSymbol, TurtleCommands.SET_DIR);
                                    break;
                                case "RESET":
                                    hm.put(alphabetSymbol, TurtleCommands.RESET);
                                    break;
                                case "PUSH":
                                    hm.put(alphabetSymbol, TurtleCommands.PUSH);
                                    break;
                                case "POP":
                                    hm.put(alphabetSymbol, TurtleCommands.POP);
                                    break;
                                case "FORGET":
                                    hm.put(alphabetSymbol, TurtleCommands.FORGET);
                                    break;    
                                default:
                                    hm.put(alphabetSymbol, TurtleCommands.NONE);
                                    break;
                                }
                            }
                            //Adiciona simbolo e comando na tabela
                            tabelaDeSimbolos.setAlphabetSymbols(hm);
                        }
                    }
                }
            }    
        }
        //System.out.println(tabelaDeSimbolos.getAlphabetSymbols());
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
                if(rule.ALPHABET_SYMBOL() != null && !rule.ALPHABET_SYMBOL().getText().isEmpty()){
                    alphabetSymbol = rule.ALPHABET_SYMBOL().getText().charAt(1);
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
