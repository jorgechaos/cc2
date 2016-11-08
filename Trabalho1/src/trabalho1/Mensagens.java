package trabalho1;

import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class Mensagens implements ANTLRErrorListener {
    
    public static Mensagens MENSAGENS = new Mensagens();
    
    
    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionLine, String msg, RecognitionException e){
        Token token = (Token)offendingSymbol;
        String texto;
        
        if (token != null){
            texto = token.getText(); 
            if(texto.equals("<EOF>")) Saida.println("Linha "+ line +": erro sintatico proximo a EOF");
            else Saida.println("Linha "+ line +": erro sintatico proximo a "+ texto);
        }
        
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
        
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
        
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
        
    }
 
}
