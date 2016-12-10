// Generated from LA.g4 by ANTLR 4.5.3
package trabalho1;

	import java.util.ArrayList;
	import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LAParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LAVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LAParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(LAParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(LAParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(LAParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(LAParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(LAParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(LAParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#ponteiros_opcionais}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPonteiros_opcionais(LAParser.Ponteiros_opcionaisContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(LAParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#outros_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutros_ident(LAParser.Outros_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(LAParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(LAParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(LAParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(LAParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(LAParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(LAParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(LAParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#parametros_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros_opcional(LAParser.Parametros_opcionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(LAParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#mais_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_ident(LAParser.Mais_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#declaracoes_locais}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes_locais(LAParser.Declaracoes_locaisContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(LAParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#comandos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandos(LAParser.ComandosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdLeia}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(LAParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdEscreva}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(LAParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdSe}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(LAParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdCaso}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(LAParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdPara}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(LAParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdEnquanto}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(LAParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdFaca}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(LAParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdPontIdentExp}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPontIdentExp(LAParser.CmdPontIdentExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdIdentExp}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIdentExp(LAParser.CmdIdentExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdIdent}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdIdent(LAParser.CmdIdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmdRet}
	 * labeled alternative in {@link LAParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRet(LAParser.CmdRetContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#mais_expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMais_expressao(LAParser.Mais_expressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#senao_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSenao_opcional(LAParser.Senao_opcionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#argumentos_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentos_opcional(LAParser.Argumentos_opcionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(LAParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(LAParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(LAParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#intervalo_opcional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalo_opcional(LAParser.Intervalo_opcionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(LAParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(LAParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(LAParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(LAParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(LAParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioPont}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioPont(LAParser.Parcela_unarioPontContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioNormal}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioNormal(LAParser.Parcela_unarioNormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioExpMaisExp}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioExpMaisExp(LAParser.Parcela_unarioExpMaisExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioInt}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioInt(LAParser.Parcela_unarioIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioReal}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioReal(LAParser.Parcela_unarioRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parcela_unarioExp}
	 * labeled alternative in {@link LAParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unarioExp(LAParser.Parcela_unarioExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(LAParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(LAParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(LAParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(LAParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_nao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_nao(LAParser.Op_naoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(LAParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(LAParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_or(LAParser.Op_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_and(LAParser.Op_andContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_mod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_mod(LAParser.Op_modContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_multiplicacao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_multiplicacao(LAParser.Op_multiplicacaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LAParser#op_adicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_adicao(LAParser.Op_adicaoContext ctx);
}