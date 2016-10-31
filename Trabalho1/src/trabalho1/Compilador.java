package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 *
 * @author Guilherme Perego
 */
public class Compilador {
    public static void main(String[] args){
        String entrada, saida;
        
        if (args.length!=2){
        System.out.println("Uso correto: <Entrada> <Saida>");
        System.out.println("<Entrada>: path para casos de teste");
        System.out.println("<Saida>: path para a saida dos casos de teste"); 
        return;
        }
        
        entrada = args[0];
        saida = args[1];
       
        InputStream casosDeTeste;
        FileOutputStream saidaCasosDeTeste;
        try {
            casosDeTeste = new FileInputStream(entrada);
            saidaCasosDeTeste = new FileOutputStream(saida);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File not found exception");
            return;
        }
        
        ANTLRInputStream input;
        try {
            input = new ANTLRInputStream(casosDeTeste);
        } catch (IOException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IO exception");
            return;
        }
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        
        lexer.removeErrorListeners();
        lexer.addErrorListener(Mensagens.MENSAGENS);
        
        parser.removeErrorListeners();
        parser.addErrorListener(Mensagens.MENSAGENS);
        
        parser.programa();
        Saida.println("Fim da compilacao");
    }
}
