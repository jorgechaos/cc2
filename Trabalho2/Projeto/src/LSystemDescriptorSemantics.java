package trabalho2;

import java.util.HashMap;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import trabalho2.LSystemDescriptorParser.AlphabetContext;
import trabalho2.LSystemDescriptorParser.DescriptionContext;
import trabalho2.LSystemDescriptorParser.SingleRuleContext;
import trabalho2.LSystemDescriptorParser.SymbolWithCommandContext;

public class LSystemDescriptorSemantics extends LSystemDescriptorBaseListener {
	HashMap<Character, TurtleCommands> hm = new HashMap<>();
	AlphabetTable tabelaDeSimbolos = new AlphabetTable(hm);
        public static boolean  erroSemantico = false;
        public static String message;
        
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
                                                        //erroSemantico = true;
                                                        FileChecker.ok = false;
							//System.out.println("Simbolo já Declarado!");
                                                        message = "Erro Semantico no Alfabeto! " + "\n";
                                                        message += "Simbolo: " + alphabetSymbol + " já declarado.";
							//System.out.println(tabelaDeSimbolos.getAlphabetSymbols());
						} else {
							//Verifica se o simbolo possui um comando
							if(swcCtx.command() != null && !swcCtx.command().isEmpty()){
								
								if (swcCtx.command().OP_FORWARD() != null && !swcCtx.command().OP_FORWARD().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORWARD);
									
								}
								
                                                                else if (swcCtx.command().OP_FORWARD_NODRAW() != null && !swcCtx.command().OP_FORWARD_NODRAW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORWARD_NODRAW);
									
								}
								
                                                                else if (swcCtx.command().OP_ROTATE_CCW() != null && !swcCtx.command().OP_ROTATE_CCW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.ROTATE_CCW);
									
								}
								
                                                                else if (swcCtx.command().OP_ROTATE_CW() != null && !swcCtx.command().OP_ROTATE_CW().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.ROTATE_CW);
									
								}
								
                                                                else if (swcCtx.command().OP_RESET() != null && !swcCtx.command().OP_RESET().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.RESET);
									
								}
								
                                                                else if (swcCtx.command().OP_PUSH() != null && !swcCtx.command().OP_PUSH().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.PUSH);
									
								}
								
                                                                else if (swcCtx.command().OP_POP() != null && !swcCtx.command().OP_POP().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.POP);
									
								}
								
                                                                else if (swcCtx.command().OP_FORGET() != null && !swcCtx.command().OP_FORGET().getText().isEmpty()) {
									hm.put(alphabetSymbol, TurtleCommands.FORGET);
									
								}
							}
							else
								hm.put(alphabetSymbol, TurtleCommands.NONE);
						}
						//Adiciona simbolo e comando na tabela
						tabelaDeSimbolos.setAlphabetSymbols(hm);
                                                //System.out.println(tabelaDeSimbolos.getAlphabetSymbols());
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
                                        //erroSemantico = true;
                                        FileChecker.ok = false;
					//System.out.println(alphabetSymbol + " Axioma Não Declarado");
                                        message = "Erro Semantico no Axioma! " + "\n";
                                        message += "Simbolo: " + alphabetSymbol + " não faz parte do  alfabeto ";
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
					//System.out.println(tabelaDeSimbolos.getAlphabetSymbols());
					//Verificar se os simbolos foram declarados no alfabeto
					if(!tabelaDeSimbolos.hasKey(alphabetSymbol)){
						//Erro semantico de simbolo nao declarado
						//System.out.println(alphabetSymbol + " Regra Não Declarado");
                                                //erroSemantico = true;
                                                FileChecker.ok = false;
                                                message = "Erro Semantico nas Regras! " + "\n";
                                                message += "Simbolo: " + alphabetSymbol + " não faz parte do alfabeto ";
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
