package trabalho1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
       
        InputStream inputs;
        FileOutputStream outputs;
        try {
            inputs = new FileInputStream(entrada);
            outputs = new FileOutputStream(saida);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File not found exception");
            return;
        }
        
        ANTLRInputStream input;
        try {
            input = new ANTLRInputStream(inputs);
        } catch (IOException ex) {
            Logger.getLogger(Compilador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IO exception");
            try {
				outputs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return;
        }
        
        LALexer lexer = new LALexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        
        parser.addErrorListener(MensagensErro.MENSAGENSERRO);
        lexer.addErrorListener(MensagensErro.MENSAGENSERRO);
        
        parser.programa();
        Saida.println("Fim da compilacao");
        
        PrintWriter printer = new PrintWriter(outputs);
        printer.print(Saida.getTexto());
        printer.close();
        
        try {
			outputs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
