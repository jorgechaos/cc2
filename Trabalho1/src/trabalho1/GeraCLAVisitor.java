	package trabalho1;

public class GeraCLAVisitor extends LABaseVisitor<String> {
	
	final int literalSize = 80;
	final boolean debugArvore = false;
	int tabCount = 0;
	
	@Override public String visitPrograma(LAParser.ProgramaContext ctx){
		// declaracoes 'algoritmo' corpo 'fim_algoritmo'
		
		if(debugArvore) System.out.println("programa : <declaracoes> 'algoritmo' <corpo> 'fim_algoritmo'");
		
		GeradorC.limparGerador();
		GeradorC.println("#include <stdio.h>");
		GeradorC.println("#include <stdlib.h>");
		GeradorC.println();
		
		if(ctx.declaracoes() != null && !ctx.declaracoes().isEmpty() && !visit(ctx.declaracoes()).equals(""))
			GeradorC.println(visit(ctx.declaracoes()));
		
		GeradorC.println("int main() {");
		tabCount++;
		
		if(ctx.corpo() != null)
			GeradorC.print(visit(ctx.corpo()));
		
		GeradorC.println("\treturn 0;");
		GeradorC.print("}");
		tabCount--;
		
		
		return GeradorC.getCodigo();
	}
	
	@Override public String visitDeclaracoes(LAParser.DeclaracoesContext ctx){
		// (decl_local_global)*
		if(debugArvore) System.out.println("\ndeclaracoes : <decl_local_global>*");
		
		String retorno = "";
		
		if(ctx.decl_local_global() != null){
			for(LAParser.Decl_local_globalContext decctx : ctx.decl_local_global()){
				if (decctx != null){
					retorno += visit(decctx);
				}
			}
		}
		
		return retorno;
	}
	
	@Override public String visitDecl_local_global(LAParser.Decl_local_globalContext ctx){
		// declaracao_local  DONE
		// declaracao_global DONE
		
		if(debugArvore) System.out.println("declaracao_local_global : <declaracao_local> | <declaracao_global>");
		
		String retorno = "";
		
		if (ctx.declaracao_local() != null){
			retorno += visit(ctx.declaracao_local());
		}
		if (ctx.declaracao_global() != null){
			retorno += visit(ctx.declaracao_global());
		}
		
		return retorno;
	}
	
	@Override public String visitDeclaracao_global(LAParser.Declaracao_globalContext ctx){
		// 'procedimento' IDENT '(' parametros_opcional ')' declaracoes_locais comandos 'fim_procedimento' 			DONE
		// 'funcao' IDENT '(' parametros_opcional ')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao'	DONE
		
		if(debugArvore) System.out.print("declaracao_global: ");
		
		String retorno = "";
		
		switch(ctx.escopo) {
			case "procedimento" :
				if(debugArvore) System.out.println("'procedimento' IDENT '(' parametros_opcional ')' declaracoes_locais comandos 'fim_procedimento'");
				if(ctx.IDENT()!=null){
					retorno += "void ";
					retorno += ctx.IDENT().getText();
					retorno += " (";
					if(ctx.parametros_opcional()!=null && !ctx.parametros_opcional().isEmpty()){
						if(ctx.parametros_opcional().parametro()!=null && !ctx.parametros_opcional().parametro().isEmpty()){
							retorno+=visit(ctx.parametros_opcional().parametro());
						}
						
					}
					retorno += "){\n";
					
					tabCount++;
					if(ctx.declaracoes_locais()!=null && !ctx.declaracoes_locais().isEmpty())
						retorno += visit(ctx.declaracoes_locais());
						
					if(ctx.comandos()!=null && !ctx.comandos().isEmpty())
						retorno += visit(ctx.comandos());
					
					tabCount--;
					retorno += "}\n";
				}
				break;
			case "funcao" :
				if(debugArvore) System.out.println("'funcao' IDENT '(' parametros_opcional ')' ':' tipo_estendido declaracoes_locais comandos 'fim_funcao'");
				if(ctx.IDENT()!=null){
					String tipo = "";
					if(ctx.tipo_estendido()!=null && !ctx.tipo_estendido().isEmpty())
						tipo = ctx.tipo_estendido().type;
					
					int numPont = tipo.lastIndexOf("^")+1;
					if(numPont>0) tipo = tipo.substring(numPont, tipo.length());
					
					switch(tipo){
						case "inteiro" :
						case "logico" :
							tipo = "int";
							break;
						case "real" :
							tipo = "float";
							break;
						case "literal" :
							tipo = "char*";
							break;
					}
					
					for(int i=0; i<numPont; i++) tipo+="*";
					
					if(debugArvore) System.out.println("tipo: <tipo_estendido>");
					if(debugArvore) System.out.println(tipo);
					
					retorno += ctx.IDENT().getText();
					retorno += " (";
					if(ctx.parametros_opcional()!=null && !ctx.parametros_opcional().isEmpty()){
						if(ctx.parametros_opcional().parametro()!=null && !ctx.parametros_opcional().parametro().isEmpty()){
							retorno+=visit(ctx.parametros_opcional().parametro());
						}
						
					}
					retorno += "){\n";
					
					tabCount++;
					if(ctx.declaracoes_locais()!=null && !ctx.declaracoes_locais().isEmpty())
						retorno += visit(ctx.declaracoes_locais());
						
					if(ctx.comandos()!=null && !ctx.comandos().isEmpty())
						retorno += visit(ctx.comandos());
					
					tabCount--;
					retorno += "}\n";
				}
				break;
		}
		
		return retorno;
	}
	
	@Override public String visitParametro(LAParser.ParametroContext ctx){
		// 'var'? identificador mais_ident ':' tipo_estendido (',' parametro)?
		if(debugArvore) System.out.println("parametro : 'var'? <identificador> <mais_ident> ':' <tipo_estendido> (',' <parametro>)?");
		String retorno = "";
		
		if(ctx.identificador()!=null && !ctx.identificador().isEmpty()){
			for(int i=0; i<tabCount;i++) retorno+="\t";
			
			String tipo = ctx.contexto.TipoDe(ctx.identificador().nome);
			
			if(tipo!=null && !tipo.equals("")){
				switch(tipo){
					case "literal" :
						retorno += "char* ";
						break;
					case "inteiro" :
					case "logico" :
						retorno += "int ";
						break;
					case "real" :
						retorno += "float ";
						break;
					default :
						retorno += tipo;
				}
			}
				
			retorno += ctx.identificador().nome;
			if(ctx.identificador().dimensao()!= null && !ctx.identificador().dimensao().isEmpty())
				retorno += visit(ctx.identificador().dimensao());
		}
		return retorno;
	}
	
	@Override public String visitDeclaracao_local(LAParser.Declaracao_localContext ctx){
		// 'declare' variavel 										DONE
		// 'constante' IDENT ':' tipo_basico '=' valor_constante	DONE
		// 'tipo' IDENT ':' tipo									DONE
		
		if(debugArvore) System.out.print("\ndeclaracao_local: ");
		
		String retorno = "";
		
		switch(ctx.escopo){
			case "declare" :
				if(debugArvore) System.out.println("'declare' <variavel>");
				retorno += visit(ctx.variavel());
				break;
			case "constante":
				if(debugArvore) System.out.println("'constante' IDENT ':' <tipo_basico> '=' <valor_constante>");
				
				if(ctx.IDENT()!=null && ctx.valor_constante()!=null && !ctx.valor_constante().isEmpty()){
					retorno += "#define ";
					retorno += ctx.IDENT().getText();
					retorno += " ";
					switch(ctx.valor_constante().getText()){
						case "verdadeiro":
							retorno += "1";
							break;
						case "falso":
							retorno += "0";
							break;
						default:
							retorno += ctx.valor_constante().getText();
					}
				}
				
				retorno += "\n";
				break;
			case "tipo":
				if(debugArvore) System.out.println("'tipo' IDENT ':' <tipo>");
				
				for(int i=0; i<tabCount;i++) retorno+="\t";	
				if(ctx.tipo()!=null && !ctx.tipo().isEmpty()) retorno += visit(ctx.tipo());
				if(ctx.IDENT()!=null) retorno += " " + ctx.IDENT().getText();
				retorno += ";\n";
				break;
		}
		
		return retorno;
	}
	
	@Override public String visitVariavel(LAParser.VariavelContext ctx){
		// IDENT dimensao (',' IDENT dimensao)* ':' tipo	DONE
		if(debugArvore) System.out.println("variavel: IDENT <dimensao> (',' IDENT <dimensao>)* ':' <tipo>");
		
		String retorno="";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		String tipo = "";
		if(ctx.tipo()!=null && !ctx.tipo().isEmpty()) tipo+=visit(ctx.tipo());
		
		String variaveis="";
		if(ctx.IDENT()!=null && !ctx.IDENT().isEmpty()){
			for(int i=0;i<ctx.IDENT().size(); i++){
				//Adiciona uma variavel
				variaveis += ctx.IDENT(i).getText();
				
				
				if(tipo.length() >= 4 && tipo.substring(0, 4).equals("char"))
					variaveis+="["+literalSize+"]";

				//Adiciona sua dimensao
				if(ctx.dimensao(i)!=null && !ctx.dimensao(i).isEmpty()){
					if(ctx.dimensao(i).exp_aritmetica()!=null && !ctx.dimensao(i).exp_aritmetica().isEmpty())
						for(LAParser.Exp_aritmeticaContext exparctx : ctx.dimensao(i).exp_aritmetica()){
							variaveis += "[" +visit(exparctx)+ "]";
						}
				}
				
				variaveis += ", ";
			}
			variaveis = variaveis.substring(0, variaveis.lastIndexOf(", "));
		}
		
		retorno += tipo + " " + variaveis;
		retorno += ";\n";
		
		return retorno;
	}
	
	@Override public String visitTipo(LAParser.TipoContext ctx){
		// registro | tipo_estendido
		
		String retorno = "";
		
		String tipo = ctx.type;
		int numPont = tipo.lastIndexOf("^")+1;
		if(numPont>0) tipo = tipo.substring(numPont, tipo.length());
		
		switch(tipo){
			case "registro" :
				if(debugArvore) System.out.println("tipo : <registro>\n\tregistro");
				retorno += "struct {\n";
				
				tabCount++;

				if(ctx.registro()!=null && !ctx.registro().isEmpty())
					retorno+=visit(ctx.registro());
				
				tabCount--;
				
				for(int i=0; i<tabCount;i++) retorno+="\t";
				retorno += "}";
				break;
			case "inteiro" :
			case "logico" :
				tipo = "int";
				for(int i=0; i<numPont; i++) tipo+="*";
				if(debugArvore) System.out.println("tipo: <tipo_estendido>\nint");
				return tipo;
			case "real" :
				tipo = "float";
				for(int i=0; i<numPont; i++) tipo+="*";
				if(debugArvore) System.out.println("tipo: <tipo_estendido>\nfloat");
				return tipo;
			case "literal" :
				tipo = "char";
				for(int i=0; i<numPont; i++) tipo+="*";
				if(debugArvore) System.out.println("tipo: <tipo_estendido>\nliteral");
				return tipo;
			default:
				for(int i=0; i<numPont; i++) tipo+="*";
				if(debugArvore) System.out.println("tipo: <tipo_estendido>");
				if(debugArvore) System.out.println(tipo);
				return tipo;
		}
		
		return retorno;
	}
	
	@Override public String visitRegistro(LAParser.RegistroContext ctx){
		//'registro' variavel+ 'fim_registro'
		
		String retorno = "";
		if(ctx.variavel()!=null && !ctx.variavel().isEmpty()){
			for(LAParser.VariavelContext vctx : ctx.variavel()){
				if(vctx!=null && !vctx.isEmpty()){
					retorno += visit(vctx);
				}
				
			}
		}	
		
		
		return retorno;
	}
	
	@Override public String visitCorpo(LAParser.CorpoContext ctx){
		// declaracoes_locais comandos
		if(debugArvore) System.out.println("\ncorpo : <declaracoes_locais> <comandos>");
		
		String retorno = "";
		if (ctx.declaracoes_locais() != null && ctx.comandos() != null){
			retorno += visit(ctx.declaracoes_locais());
			retorno += visit(ctx.comandos());
		}
		
		return retorno;
	}
	
	@Override public String visitDeclaracoes_locais(LAParser.Declaracoes_locaisContext ctx){
		//(declaracao_local declaracoes_locais)? 
		if(debugArvore) System.out.println("\ndeclaracoes_locais : (<declaracao_local> <declaracoes_locais>)? ");
		
		String retorno = "";

		if(ctx.declaracao_local() != null && ctx.declaracoes_locais() != null){
			retorno += visit(ctx.declaracao_local());
			retorno += visit(ctx.declaracoes_locais());
		}
		
		return retorno;
	}
	
	@Override public String visitComandos(LAParser.ComandosContext ctx){
		// cmd*
		if(debugArvore) System.out.println("\ncomandos : <cmd>*");
		String retorno="";
		
		if(ctx.cmd() != null){
			for (LAParser.CmdContext cmdctx: ctx.cmd()){
				if(cmdctx != null){
					retorno += visit(cmdctx);
				}
			}
		}
		
		return retorno;
	}
	
	@Override public String visitCmdLeia(LAParser.CmdLeiaContext ctx){
		// 'leia' '(' identificador mais_ident ')'	DONE
		if(debugArvore) System.out.println("\n\tCmd_Leia");
		
		String retorno = "", tipo="";
		
		// completa com identificador, se existir:
		if(ctx.identificador() != null && !ctx.identificador().isEmpty()){
			
			for(int i=0; i<tabCount;i++) retorno+="\t";
			
			tipo = ctx.contexto.TipoDe(ctx.identificador().nome);
			
			if(tipo!=null && !tipo.equals("")){
				if(tipo.equals("literal"))
					retorno += "gets(";	
				else{
					retorno += "scanf(\"%";
					
					if(tipo.equals("inteiro") || tipo.equals("logico")){
						retorno+="d";
					}
					if(tipo.equals("real")){
						retorno+="f";
					}
					
					retorno +="\",&";
				}
				
				retorno += ctx.identificador().nome;
				if(ctx.identificador().dimensao()!= null && !ctx.identificador().dimensao().isEmpty())
					retorno += visit(ctx.identificador().dimensao());
				
				retorno += ");\n";
			}
			else
				return "ERRO\n";
		}
		
		// completa com mais_ident, se existir:
				if(ctx.mais_ident() != null && !ctx.mais_ident().isEmpty() && ctx.mais_ident().identificador() != null && !ctx.mais_ident().identificador().isEmpty()){
					for(LAParser.IdentificadorContext identctx : ctx.mais_ident().identificador()){
						tipo = ctx.contexto.TipoDe(identctx.nome);
						
					if(tipo!=null && !tipo.equals("")){
						
						for(int i=0; i<tabCount;i++) retorno+="\t";
						
						if(tipo.equals("literal"))
							retorno += "gets(";	
						else{
							retorno += "scanf(\"%";
							
							if(tipo.equals("inteiro") || tipo.equals("logico")){
								retorno+="d";
							}
							if(tipo.equals("real")){
								retorno+="f";
							}
							
							retorno +="\",&";
						}
						
						retorno += identctx.nome;
						if(identctx.dimensao()!= null && !identctx.dimensao().isEmpty())
							retorno += visit(identctx.dimensao());
						
						retorno += ");\n";
					}
					else
						return "ERRO\n";
						
					}
					
				}	
		
		return retorno;
	}

	@Override public String visitCmdEscreva(LAParser.CmdEscrevaContext ctx){
		// 'escreva' '(' expressao mais_expressao ')' DONE
		if(debugArvore) System.out.println("\n\tCmd_Escreva");
		
		String retorno ="", parte1 = "", parte2 ="", tipo;
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "printf(";
		
		parte1 += "\"";
		
		if(ctx.expressao() != null && !ctx.expressao().isEmpty()){
			tipo = ctx.expressao().type;
			
			if(debugArvore) System.out.println("\n\texpressao");
			
			if(tipo != null){
				if(ctx.contexto.existeSimbolo(visit(ctx.expressao()))){
					switch (tipo){
						case "inteiro":
						case "logico":
							parte1+="%d";
							break;
						case "real":
							parte1+="%f";
							break;
						case "literal":
							parte1+="%s";
							break;
						default:
							parte1 += "%<Nao Sei Qual Formatacao Usar Para "+ tipo + " (Esta na Tabela)>";
					}
					
					parte2 += visit(ctx.expressao());
				}
				else{
					switch (tipo){
						case "inteiro":
						case "logico":
							parte1 += "%d";
							parte2 += visit(ctx.expressao());
							break;
						case "real":
							parte1 += "%f";
							parte2 += visit(ctx.expressao());
							break;
						case "literal":
							parte1 += visit(ctx.expressao()).replaceAll("\"", "");;
							break;
						default:
							parte1 += "%<Nao Sei Qual Formatacao Usar Para "+ tipo +" (Nao Esta na Tabela)>";
							parte2 += visit(ctx.expressao());
					}
				}
			}
			else return "ERRO\n";
		}
		
		parte1 += "\"";
		if(!parte2.equals(""))
			parte1 += ",";
		
		retorno += parte1 + parte2;
		retorno += ");\n";
		
		if(ctx.mais_expressao() != null && !ctx.mais_expressao().isEmpty() && ctx.mais_expressao().expressao()!=null && !ctx.mais_expressao().expressao().isEmpty()){
			for(LAParser.ExpressaoContext expctx : ctx.mais_expressao().expressao()){
				parte1 = ""; 
				parte2 = "";
				
				for(int i=0; i<tabCount;i++) retorno+="\t";
				retorno += "printf(";
				
				parte1 += "\"";
				
				if(expctx != null && !expctx.isEmpty()){
					tipo = expctx.type;
					if(debugArvore) System.out.println("\n\tmais_expressao");
					
					if(tipo != null){
						if(ctx.contexto.existeSimbolo(visit(expctx))){
							switch (tipo){
								case "inteiro":
								case "logico":
									parte1+="%d";
									break;
								case "real":
									parte1+="%f";
									break;
								case "literal":
									parte1+="%s";
									break;
								default:
									parte1 += "%<Nao Sei Qual Formatacao Usar Para "+ tipo +" (Esta na Tabela)>";
							}
							
							parte2 += visit(expctx);
						}
						else{
							switch (tipo){
								case "inteiro":
								case "logico":
									parte1 += "%d";
									parte2 += visit(expctx);
									break;
								case "real":
									parte1 += "%f";
									parte2 += visit(expctx);
									break;
								case "literal":
									parte1 += visit(expctx).replaceAll("\"", "");;
									break;
								default:
									parte1 += "%<Nao Sei Qual Formatacao Usar Para "+ tipo +" (Nao Esta na Tabela)>";
									parte2 += visit(expctx);
							}
						}
					}
					else parte1 += "%<Nao Sei Qual Formatacao Usar (Tipo Nulo)>";
				}
				
				parte1 += "\"";
				if(!parte2.equals(""))
					parte1 += ",";
				
				retorno += parte1 + parte2;
				retorno += ");\n";
				}
			}

		return retorno;
	}

	@Override public String visitCmdSe(LAParser.CmdSeContext ctx){
		// 'se' expressao 'entao' comandos senao_opcional 'fim_se' DONE
		if(debugArvore) System.out.println("\n\tCmd_SeEntao");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "if (";
		
		String expressao = "";
		if(ctx.expressao() != null && !ctx.expressao().isEmpty())
			expressao = visit(ctx.expressao());
		
		retorno += expressao;
		
		retorno += ") {\n";
		
		tabCount++;
		
		retorno += visit(ctx.comandos());
		
		tabCount--;
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "}\n";
		
		if(ctx.senao_opcional()!=null && !ctx.senao_opcional().isEmpty() && ctx.senao_opcional().comandos()!=null && !ctx.senao_opcional().comandos().isEmpty()){
			for(int i=0; i<tabCount;i++) retorno+="\t";
			retorno += "else {\n";
			
			tabCount++;
			
			retorno += visit(ctx.senao_opcional().comandos());
			
			tabCount--;
			for(int i=0; i<tabCount;i++) retorno+="\t";
			retorno += "}\n";
		}
		
		return retorno;
	}

	@Override public String visitCmdCaso(LAParser.CmdCasoContext ctx){
		// 'caso' exp_aritmetica 'seja' selecao senao_opcional 'fim_caso' DONE
		if(debugArvore) System.out.print("Cmd_CasoSeja");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		retorno+="switch (";
		if(ctx.exp_aritmetica()!=null && !ctx.exp_aritmetica().isEmpty())
			retorno+=visit(ctx.exp_aritmetica());
		
		retorno += ") {\n";
		
		tabCount++;
		
		if(ctx.selecao()!=null && !ctx.selecao().isEmpty())
			retorno += visit(ctx.selecao());
		
		if(ctx.senao_opcional()!=null && !ctx.senao_opcional().isEmpty()){
			if(ctx.senao_opcional().comandos()!=null && !ctx.senao_opcional().comandos().isEmpty()){
				for(int i=0; i<tabCount;i++) retorno+="\t";
				retorno += "default:\n";
				tabCount++;
				retorno += visit(ctx.senao_opcional().comandos());
				tabCount--;
			}
			
		}
		
		tabCount--;
		
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno+="}\n";
		return retorno;
	}
	
	@Override public String visitSelecao(LAParser.SelecaoContext ctx){
		//selecao : (constantes ':' comandos)+
		if(debugArvore) System.out.println("\tSelecao");
		
		String retorno = "";
		
		for(int j=0;j<ctx.constantes().size();j++){

			if(ctx.constantes(j)!=null && !ctx.constantes(j).isEmpty()){
				String[] intervalos = visit(ctx.constantes(j)).split(",");
				String[] partes;
				int StartNum,EndNum;
				for (String s: intervalos){
					partes = s.split("ate");
						if(partes.length == 2){
							try{
								StartNum = Integer.parseInt(partes[0]);
								EndNum = Integer.parseInt(partes[1]);
								for(int k=StartNum;k<=EndNum;k++){
									for(int i=0; i<tabCount;i++) retorno+="\t";
									retorno+="case ";
									retorno+=k;
									retorno+=":\n";
								}
							}
							catch(NumberFormatException e){}
						}
						if(partes.length == 1){
							try{
								StartNum = Integer.parseInt(partes[0]);
								for(int i=0; i<tabCount;i++) retorno+="\t";
								retorno+="case ";
								retorno+=StartNum;
								retorno+=":\n";
							}
							catch(NumberFormatException e){}
						}

				}
					
			}
			
			tabCount++;
			
			if(ctx.comandos()!=null && !ctx.comandos().isEmpty()){
				retorno += visit(ctx.comandos(j));
				for(int i=0; i<tabCount;i++) retorno+="\t";
				retorno += "break;\n";
			}
			
			tabCount--;
		}
		
		
		
		return retorno;
	}
	
	@Override public String visitConstantes(LAParser.ConstantesContext ctx){
		if(debugArvore) System.out.println("\tConstantes");
		//constantes : numero_intervalo (',' constantes)?
		String retorno = "";
		String numero = "";
		
		
		// numero_intervalo : op_unario NUM_INT intervalo_opcional
		if(ctx.numero_intervalo()!=null && !ctx.numero_intervalo().isEmpty()){
			if(ctx.numero_intervalo().op_unario()!=null && !ctx.numero_intervalo().op_unario().isEmpty())
				numero+=ctx.numero_intervalo().op_unario().getText();
			if(ctx.numero_intervalo().NUM_INT()!=null)
				numero += ctx.numero_intervalo().NUM_INT().getText();
			
			int StartNum;
			try{
				StartNum = Integer.parseInt(numero);
			}
			catch(NumberFormatException e){
				return "NAN";
			}
			
			retorno += StartNum;
			
			numero = "";
			// intervalo_opcional : ('..' op_unario NUM_INT)?
			if(ctx.numero_intervalo().intervalo_opcional()!=null && !ctx.numero_intervalo().intervalo_opcional().isEmpty()){
				if(ctx.numero_intervalo().intervalo_opcional().op_unario()!=null && !ctx.numero_intervalo().intervalo_opcional().op_unario().isEmpty())
					numero+=ctx.numero_intervalo().intervalo_opcional().op_unario().getText();
				if(ctx.numero_intervalo().intervalo_opcional().NUM_INT()!=null)
					numero+=ctx.numero_intervalo().intervalo_opcional().NUM_INT().getText();
				
				int EndNum;
				try{
					EndNum = Integer.parseInt(numero);
					retorno += "ate" + EndNum;
				}
				catch(NumberFormatException e){}
			}
		}
		else return "NAN";
		
		if(ctx.constantes()!=null && !ctx.constantes().isEmpty()){
			retorno+=",";
			retorno+=visit(ctx.constantes());
		}
		
		return retorno;
	}

	@Override public String visitCmdPara(LAParser.CmdParaContext ctx){
		// 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' comandos 'fim_para' DONE
		if(debugArvore) System.out.println("\n\tCmd_ParaAteFaca");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "for (";
		
		if(ctx.IDENT() != null){
			retorno += ctx.IDENT().getText();
			retorno += " = ";
			if(ctx.exp_aritmetica(0) != null && !ctx.exp_aritmetica(0).isEmpty()){
				retorno += visit(ctx.exp_aritmetica(0));
				retorno+="; ";
			}
			if(ctx.exp_aritmetica(1) != null && !ctx.exp_aritmetica(1).isEmpty()){
				retorno += ctx.IDENT().getText();
				retorno += " <= ";
				retorno += visit(ctx.exp_aritmetica(1));
				retorno+="; ";
			}
			retorno += ctx.IDENT().getText();
			retorno += "++";
		}
		
		retorno += ") {\n";
		tabCount++;
		retorno+=visit(ctx.comandos());
		tabCount--;
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "}\n";
			
		return retorno;
	}

	@Override public String visitCmdEnquanto(LAParser.CmdEnquantoContext ctx){
		// 'enquanto' expressao 'faca' comandos 'fim_enquanto' DONE
		if(debugArvore) System.out.println("\n\tCmd_Enquanto_Faca");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		retorno += "while (";
		
		if(ctx.expressao()!=null && !ctx.expressao().isEmpty())
			retorno += visit(ctx.expressao());
		
		retorno += ") {\n";
		
		tabCount++;
		if(ctx.comandos()!=null && !ctx.comandos().isEmpty())
			retorno+=visit(ctx.comandos());
		tabCount--;
		
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "}\n";
		return retorno;
	}

	@Override public String visitCmdFaca(LAParser.CmdFacaContext ctx){
		// 'faca' comandos 'ate' expressao DONE
		if(debugArvore) System.out.println("\n\tCmd_FacaAte");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		retorno += "do {\n";
		
		tabCount++;
		if(ctx.comandos()!=null && !ctx.comandos().isEmpty())
			retorno += visit(ctx.comandos());
		tabCount--;
		
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "} while (";
		
		if(ctx.expressao()!=null && !ctx.expressao().isEmpty())
		retorno += visit(ctx.expressao());
		
		retorno += ");\n";

		return retorno;
	}
	
	@Override public String visitCmdPontIdentExp(LAParser.CmdPontIdentExpContext ctx){
		// '^' IDENT outros_ident dimensao '<-' expressao DONE
		if(debugArvore) System.out.println("\n\tCmd_AtribPont");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		String esquerda ="";
		if(ctx.IDENT() != null){
			esquerda+="*";
			esquerda += ctx.IDENT().getText();
		}
		if(ctx.dimensao() != null && !ctx.dimensao().isEmpty())
			esquerda += visit(ctx.dimensao());			
		if(ctx.outros_ident() != null && !ctx.outros_ident().isEmpty()){
			esquerda += ctx.outros_ident().nome;
			if(ctx.outros_ident().identificador() != null
					&& !ctx.outros_ident().identificador().isEmpty()
					&& ctx.outros_ident().identificador().dimensao() != null
					&& !ctx.outros_ident().identificador().dimensao().isEmpty())
				esquerda += visit(ctx.outros_ident().identificador().dimensao());
		}
		
		String direita = "";
		if(ctx.expressao() != null && !ctx.expressao().isEmpty()){
			direita += visit(ctx.expressao());
			
			switch(ctx.expressao().type){
				case "literal" :
					retorno += "strcpy(";
					retorno += esquerda;
					retorno += ",";
					retorno += direita;
					retorno += ");\n";
					break;
				default :
					retorno += esquerda;
					retorno += " = ";
					retorno += direita;
					retorno += ";\n";
			}
		}
		return retorno;
	}

	@Override public String visitCmdIdentExp(LAParser.CmdIdentExpContext ctx){
		// IDENT outros_ident dimensao '<-' expressao DONE
		if(debugArvore) System.out.println("\n\tCmd_Atrib");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		String esquerda = "";

		if(ctx.IDENT() != null)
			esquerda += ctx.IDENT().getText();
		if(ctx.dimensao() != null && !ctx.dimensao().isEmpty())
			esquerda += visit(ctx.dimensao());
		if(ctx.outros_ident() != null && !ctx.outros_ident().isEmpty()){
			esquerda += ctx.outros_ident().nome;
			if(ctx.outros_ident().identificador() != null && !ctx.outros_ident().identificador().isEmpty() && ctx.outros_ident().identificador().dimensao() != null && !ctx.outros_ident().identificador().dimensao().isEmpty())
				esquerda += visit(ctx.outros_ident().identificador().dimensao());
		}
		
		String direita = "";
		if(ctx.expressao() != null && !ctx.expressao().isEmpty()){
			direita += visit(ctx.expressao());
			
			switch(ctx.expressao().type){
				case "literal" :
					retorno += "strcpy(";
					retorno += esquerda;
					retorno += ",";
					retorno += direita;
					retorno += ");\n";
					break;
				default :
					retorno += esquerda;
					retorno += " = ";
					retorno += direita;
					retorno += ";\n";
			}
		}
		return retorno;
	}

	@Override public String visitCmdIdent(LAParser.CmdIdentContext ctx){
		// IDENT '(' argumentos_opcional ')' DONE
		if(debugArvore) System.out.println("\n\tCmd_Func");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		
		if(ctx.IDENT()!=null && !(ctx.IDENT().getText().length()==0))
			retorno += ctx.IDENT().getText();
		
		retorno += " (";
		
		if(ctx.argumentos_opcional()!=null && !ctx.argumentos_opcional().isEmpty()){
			if(ctx.argumentos_opcional().expressao()!=null && !ctx.argumentos_opcional().expressao().isEmpty())
				retorno+=visit(ctx.argumentos_opcional().expressao());
			if(ctx.argumentos_opcional().mais_expressao()!=null && !ctx.argumentos_opcional().mais_expressao().isEmpty())
				retorno += visit(ctx.argumentos_opcional().mais_expressao());
		}
			
		retorno += ");\n";
		return retorno;
	}

	@Override public String visitCmdRet(LAParser.CmdRetContext ctx){
		// RETORNAR expressao DONE
		if(debugArvore) System.out.println("\n\tCmd_Return");
		
		String retorno = "";
		for(int i=0; i<tabCount;i++) retorno+="\t";
		retorno += "return ";
		if(ctx.expressao()!=null && !ctx.expressao().isEmpty())
			retorno += visit(ctx.expressao());
		retorno += ";\n";
		
		return retorno;
	}
	
	@Override public String visitExpressao(LAParser.ExpressaoContext ctx){
		//expressao : termo_logico (op_or termo_logico)*
		if(debugArvore) System.out.print("<-Expressao");
		
		String retorno = "";
		
		if(ctx.termo_logico(0) != null && !ctx.termo_logico(0).isEmpty())
			retorno += visit(ctx.termo_logico(0));
		if(ctx.op_or()!=null && !ctx.op_or().isEmpty()){
			for(int i=1;i<ctx.termo_logico().size();i++){
				if(ctx.termo_logico(i)!=null && !ctx.termo_logico(i).isEmpty()){
					retorno += " || "; //Traduzimos 'ou' para '||'
					if(ctx.termo_logico(i)!=null && !ctx.termo_logico(i).isEmpty())
						retorno += visit(ctx.termo_logico(i));
				}	
			}
		}
		
		return retorno;
	}
	
	@Override public String visitTermo_logico(LAParser.Termo_logicoContext ctx){
		//termo_logico : fator_logico (op_and fator_logico)*
		if(debugArvore) System.out.print("<-TermoLogico");
		
		String retorno = "";
		
		if(ctx.fator_logico(0) != null && !ctx.fator_logico(0).isEmpty())
			retorno += visit(ctx.fator_logico(0));
		if(ctx.op_and()!=null && !ctx.op_and().isEmpty())
			for(int i=1;i<ctx.fator_logico().size();i++){
				retorno += " && "; //Traduzimos 'e' para '&&'
				if(ctx.fator_logico(i)!=null && !ctx.fator_logico(i).isEmpty())
					retorno += visit(ctx.fator_logico(i));
			}
		
		return retorno;
	}
	
	@Override public String visitFator_logico(LAParser.Fator_logicoContext ctx){
		//fator_logico : op_nao parcela_logica
		//op_nao : ('nao')?
		if(debugArvore) System.out.print("<-FatorLogico");
		
		String retorno = "";
		
		if(ctx.op_nao()!=null && !ctx.op_nao().isEmpty() && ctx.op_nao().happens)
			retorno += "!"; //Traduzimos 'nao' para '!'
		if(ctx.parcela_logica()!=null && !ctx.parcela_logica().isEmpty())
			retorno += visit(ctx.parcela_logica());
		
		return retorno;
	}
	
	@Override public String visitParcela_logica(LAParser.Parcela_logicaContext ctx){
		//parcela_logica : 'verdadeiro' | 'falso' | exp_relacional
		if(debugArvore) System.out.print("<-ParcelaLogica");
		
		if(ctx.getText().equals("verdadeiro")) return "1";  //Traduzimos 'verdadeiro' para 1
		if(ctx.getText().equals("falso")) return "0"; 		//Traduzimos 'falso' para 0
		
		String retorno = "";
		
		if (ctx.exp_relacional()!=null && !ctx.exp_relacional().isEmpty())
			retorno += visit(ctx.exp_relacional());
		
		return retorno;
	}
	
	@Override public String visitExp_relacional(LAParser.Exp_relacionalContext ctx){
		//exp_relacional : exp_aritmetica (op_relacional exp_aritmetica)?
		//op_relacional : '=' | '<>' | '>=' | '<=' | '>' | '<'
		if(debugArvore) System.out.print("<-Exp_Relacional");
		
		String retorno = "";
		
		if(ctx.exp_aritmetica(0) != null && !ctx.exp_aritmetica(0).isEmpty())
			retorno += visit(ctx.exp_aritmetica(0));
		if(ctx.op_relacional() != null && !ctx.op_relacional().isEmpty()){
			String operador = "";
			operador += ctx.op_relacional().getText();
			
			switch(operador){
				case "=":  operador = " == "; //Traduzimos '=' para '=='
						   break;
				case "<>": operador = " != "; //Traduzimos '<>' para '!='
				  		   break;
				case ">=": operador = " >= "; //Os outros são iguais
				  		   break;
				case "<=": operador = " <= ";
				  		   break;
				case ">":  operador = " > ";
				  		   break;
				case "<":  operador = " < ";
				  		   break;
				default:   operador = " ";
						   break;
			}
			
			retorno += operador;
			if(ctx.exp_aritmetica(1)!=null && !ctx.exp_aritmetica(1).isEmpty())
				retorno += visit(ctx.exp_aritmetica(1));
		}
		
		return retorno;
	}
	
	@Override public String visitExp_aritmetica(LAParser.Exp_aritmeticaContext ctx){
		//exp_aritmetica : termo (op_adicao termo)*
		if(debugArvore) System.out.print("<-Exp_Aritmetica");
		
		String retorno = "";
		
		if(ctx.termo(0)!=null && !ctx.termo(0).isEmpty())
			retorno += visit(ctx.termo(0));
		
		if(ctx.op_adicao()!=null && !ctx.op_adicao().isEmpty())
			for(int i=1;i<ctx.termo().size();i++){
				if(ctx.op_adicao(i-1)!=null && !ctx.op_adicao(i-1).isEmpty())
					retorno += " " + ctx.op_adicao(i-1).getText() + " ";
				if(ctx.termo(i)!=null && !ctx.termo(i).isEmpty())
					retorno += visit(ctx.termo(i));
			}
		
		return retorno;
	}
	
	@Override public String visitTermo(LAParser.TermoContext ctx){
		//termo : fator (op_multiplicacao fator)*
		if(debugArvore) System.out.print("<-Termo");
		
		String retorno = "";
		
		if(ctx.fator(0) != null && !ctx.fator(0).isEmpty())
			retorno += visit(ctx.fator(0));
		
		if(ctx.op_multiplicacao()!=null && !ctx.op_multiplicacao().isEmpty())
			for(int i=1;i<ctx.fator().size();i++){
				if(ctx.op_multiplicacao(i-1)!=null && !ctx.op_multiplicacao(i-1).isEmpty())
					retorno += " " + ctx.op_multiplicacao(i-1).getText() + " ";
				if(ctx.fator(i)!=null && !ctx.fator(i).isEmpty())
					retorno += visit(ctx.fator(i));
			}
		return retorno;
	}
	
	@Override public String visitFator(LAParser.FatorContext ctx){
		//fator : parcela (op_mod parcela)*
		if(debugArvore) System.out.print("<-Fator");
		
		String retorno = "";
		
		if(ctx.parcela(0) != null && !ctx.parcela(0).isEmpty())
			retorno += visit(ctx.parcela(0));
		if(ctx.op_mod()!=null && !ctx.op_mod().isEmpty()){
			for(int i=1;i<ctx.parcela().size();i++){
				if(ctx.parcela(i)!=null && !ctx.parcela(i).isEmpty()){
					retorno += " % ";
					if(ctx.parcela(i)!=null && !ctx.parcela(i).isEmpty())
						retorno += visit(ctx.parcela(i));
				}	
			}
		}
		
		return retorno;
	}
	
	@Override public String visitParcela(LAParser.ParcelaContext ctx){
		//parcela : op_unario parcela_unario | '&' IDENT outros_ident dimensao | CADEIA
		//op_unario : '-'?
		if(debugArvore) System.out.print("<-Parcela");
		
		String retorno = "";
		
		if(ctx.CADEIA()!=null && !(ctx.CADEIA().getText().length()==0)) return ctx.CADEIA().getText();
		if(ctx.IDENT()!=null && !(ctx.IDENT().getText().length()==0)){
			retorno += "&" + ctx.IDENT().getText();
			if(ctx.dimensao()!=null && !ctx.dimensao().isEmpty())
				retorno += visit(ctx.dimensao());
			if(ctx.outros_ident()!=null && !ctx.outros_ident().isEmpty())
				retorno+=ctx.outros_ident().nome;
		}
		if(ctx.parcela_unario()!=null && !ctx.parcela_unario().isEmpty()){
			if(ctx.op_unario()!=null && !ctx.op_unario().isEmpty())
				retorno+=ctx.op_unario().getText();
			retorno += visit(ctx.parcela_unario());
		}
		
		return retorno;
	}
	
	@Override public String visitParcela_unarioPont(LAParser.Parcela_unarioPontContext ctx){
		//'^' IDENT outros_ident dimensao
		if(debugArvore) System.out.print("<-Parcela_Unario_Pont");
		
		String retorno = "";
		if(ctx.IDENT()!=null)
			retorno += ctx.IDENT().getText();
		
		retorno+="*";
		
		if(ctx.dimensao()!=null && !ctx.dimensao().isEmpty())
			retorno += visit(ctx.dimensao());
		if(ctx.outros_ident()!=null && !ctx.outros_ident().isEmpty())
			retorno+=ctx.outros_ident().nome;
		return retorno;
	}
	
	@Override public String visitParcela_unarioNormal(LAParser.Parcela_unarioNormalContext ctx){
		//IDENT outros_ident dimensao
		if(debugArvore) System.out.print("<-ParcelaUnario");
		
		String retorno = "";
		if(ctx.IDENT()!=null)
			retorno += ctx.IDENT().getText();
		if(ctx.dimensao()!=null && !ctx.dimensao().isEmpty())
			retorno += visit(ctx.dimensao());
		if(ctx.outros_ident()!=null && !ctx.outros_ident().isEmpty())
			retorno+=ctx.outros_ident().nome;
		
		return retorno;
	}
	
	@Override public String visitParcela_unarioExpMaisExp(LAParser.Parcela_unarioExpMaisExpContext ctx){
		//IDENT '(' expressao mais_expressao ')'
		if(debugArvore) System.out.print("<-ParcelaUnario(Exp_MaisExp)");
		
		String retorno = "";
		if(ctx.IDENT()!=null)
			retorno += ctx.IDENT().getText();
		
		retorno += "(";
		
		if(ctx.expressao()!=null && !ctx.expressao().isEmpty())
			retorno += visit(ctx.expressao());
		if(ctx.mais_expressao()!=null && !ctx.mais_expressao().isEmpty())
			retorno += visit(ctx.mais_expressao());
		
		retorno += ")";
		return retorno;
	}
	
	@Override public String visitParcela_unarioInt(LAParser.Parcela_unarioIntContext ctx){
		//NUM_INT
		if(debugArvore) System.out.print("<-Parcela_unarioInt");
		
		return ctx.NUM_INT().getText();
	}
	
	@Override public String visitParcela_unarioReal(LAParser.Parcela_unarioRealContext ctx){
		//NUM_REAL
		if(debugArvore) System.out.print("<-Parcela_unarioReal");
		
		return ctx.NUM_REAL().getText();
	}
	
	@Override public String visitParcela_unarioExp(LAParser.Parcela_unarioExpContext ctx){
		//'(' expressao ')'
		if(debugArvore) System.out.print("<-Parcela_unarioExp");
		
		String retorno = "(";
		retorno += visit(ctx.expressao());
		retorno += ")";
		return retorno;
	}
	
	@Override public String visitMais_expressao(LAParser.Mais_expressaoContext ctx){
		//mais_expressao : (',' expressao)*
		if(debugArvore) System.out.print("<-MaisExpressao");
		
		String retorno = "";
		
		if(ctx.expressao()!=null && !ctx.expressao().isEmpty())
		for(LAParser.ExpressaoContext expctx : ctx.expressao()){
			retorno += ", ";
			retorno += visit(expctx);
		}
		
		return retorno;
	}
	
	@Override public String visitDimensao(LAParser.DimensaoContext ctx){
		// ('[' exp_aritmetica ']')*
		
		String retorno = "";
		if(ctx.exp_aritmetica()!=null && !ctx.exp_aritmetica().isEmpty()){
			for(LAParser.Exp_aritmeticaContext expactx : ctx.exp_aritmetica()){
				retorno += "["+visit(expactx)+"]";
			}
		}
		
		return retorno;
	}
}