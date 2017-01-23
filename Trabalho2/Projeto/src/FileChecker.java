package trabalho2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

import javax.swing.JOptionPane;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Interval;

/** Dependencies:
 * antlr
 * antlr LSystemDescriptorLexer (Which requires LSystemDescriptorLexer.tokens)
 * antlr LSystemDescriptorParser (Which requires LSystemDescriptorListener and LSystemDescriptor.tokens)
 * */
public abstract class FileChecker {
	static boolean ok;
	static String message;
	public static boolean check(File f){
		ok = true;
		message = "";
		InputStream is;
		
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FNF exc");
			return false;
		}
		
		ANTLRInputStream antlris;
		
		try {
			antlris = new ANTLRInputStream(is);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO exc");
			try {
				is.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("IO exc in IO exc");
			}
			return false;
		}
		
		LSystemDescriptorLexer lexer = new LSystemDescriptorLexer(antlris);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LSystemDescriptorParser parser = new LSystemDescriptorParser(tokens);
		
		parser.removeErrorListeners();
		
		parser.addErrorListener(new ANTLRErrorListener() {	

			@Override
			public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol, int line, int charPositionLine, String msg, RecognitionException e) {
				
				
				CommonToken os = (CommonToken)offendingSymbol;
				if(e != null)
					message += e + "\n";
				message += "Syntax error:" + "\n";
				
				message += "Lin:Pos    " + line + ":" + charPositionLine + "\n";
				message += "Token:     \"" + os.getText() + "\"" + "\n";
				message += "Type:      " + os.getType() + ":" + recognizer.getVocabulary().getSymbolicName(os.getType()) + "\n";
				
				String expected = "", expectedNmbrs = "";
				if(e != null && e.getExpectedTokens() != null && e.getExpectedTokens().getIntervals() != null)
					for(Interval intv : e.getExpectedTokens().getIntervals()){
						for(int i=intv.a;i<=intv.b;i++){
							expectedNmbrs += i+","; 
							expected += recognizer.getVocabulary().getSymbolicName(i) + ",";
						}
					}
				if(!expectedNmbrs.equals(""))
					expectedNmbrs = expectedNmbrs.substring(0, expectedNmbrs.lastIndexOf(","));
				if(!expected.equals(""))
					expected = expected.substring(0, expected.lastIndexOf(","));
				
				if(e!=null)
					message += "Expected:  " + expectedNmbrs + ":" + expected + "\n";
				else{
					if(os.getType() != recognizer.getTokenTypeMap().get("OTHER_CHAR"))
						message += "Expected:  ?" + "\n";
					else
						message += "Invalid Character" + "\n";
				}
					
				
				ok = false;
				message += "\n";
			}
			
			@Override
			public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
				message += "Context Sensitivity Report" + "\n";
				ok = false;
				message += "\n";
			}
			
			@Override
			public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
				message += "Attempting Full Context Report" + "\n";
				ok = false;
				message += "\n";
			}
			
			@Override
			public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
				message += "Ambiguity Report" + "\n";
				ok = false;
				message += "\n";
			}
		});
		
                parser.addParseListener(new LSystemDescriptorSemantics());
                
		parser.description();
		if(ok){
			message += "Ok" + "\n";
			message += "\n";
		}
		
		JOptionPane.showMessageDialog(MainWindowController.getMainFrame(), message);
		return ok;
	}
}
