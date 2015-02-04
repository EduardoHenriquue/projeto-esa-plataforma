package br.ufpb.esa.plataforma.model;

public class Test {

	public static void main(String [] args){
		Compiler <Runnable> compiler = new Compiler <Runnable>();
	
		String source = "public class HelloWorld extends Runnable{" + 
		"public static void main (String[]args){"+ 
		"system.out.println(\" Hello World, Funcionou :D)}" + "}";
		
		Class<Runnable> clazz = compiler.compile(null, "HelloWorld", source);
		
	}
}
