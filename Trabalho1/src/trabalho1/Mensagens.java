package trabalho1;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class Mensagens extends BaseErrorListener {
    public static Mensagens MENSAGENS = new Mensagens();
    
    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionLine, String msg, RecognitionException e){
        Token token = (Token) offendingSymbol;
        String texto;
        if (token != null){
            texto = token.getText();
            if(texto == "<EOF>") 
                texto = "EOF";
            
            Saida.println("Linha "+ line +": erro sintatico proximo a"+ texto);
        }
    }
   
}
