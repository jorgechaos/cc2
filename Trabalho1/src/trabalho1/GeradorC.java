package trabalho1;

public abstract class GeradorC {
	static String codigo;
	
	public static void limparGerador(){
		codigo = "";
	}
	
	public static void println(String s){
		codigo += s;
		codigo += "\n";
	}
	
	public static void println(Integer i){
		codigo += ""+i;
		codigo += "\n";
	}
	
	public static void println(){
		codigo += "\n";
	}
	
	public static void print(String s){
		codigo += s;
	}
	
	public static void print(Integer i){
		codigo += ""+i;
	}
	
	public static String getCodigo(){
		return codigo;
	}
}
