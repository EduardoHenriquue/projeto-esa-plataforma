package br.ufpb.esa.plataforma.model;

import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.DiagnosticCollector;
import javax.tools.ToolProvider;

public class Compiler <T> {

	private JavaCompiler compiler;
	private CompileFileManager  manager;
	private CompileClassLoader classLoader;
	private DiagnosticCollector <JavaFileObject> diagnostics;
	
	public Compiler() throws CompilerException{
		compiler = ToolProvider.getSystemJavaCompiler();
		if(compiler == null){
			throw new CompilerException("Compilador não encontrado");
		}
		
		diagnostics = new DiagnosticCollector <JavaFileObject>();
		
		StandardJavaFileManager standardManager = compiler.getStandardFileManager(diagnostics, null, null);
		manager = new CompileFileManager (standardManager, classLoader);
	}
   @SuppressWarnings("unchecked")
   public synchronized Class<T> compile(String packageName, String className, String javaSource) throws CompilerException{
		    try {
		    	String qualifiedClassName = CompileUtils.INSTANCE.getQualifiedClassName(packageName, className);
		    	CompileByte sourceObj = new CompileByte(className, javaSource);
		    	CompileByte compiledObj = new CompileByte (qualifiedClassName);
		        manager.setSources(sourceObj, compiledObj);
		        
		        CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, Arrays.asList(sourceObj));
		    	boolean result = task.call();
		        if(!result){
		        	throw new CompilerException("A compilação falhou", diagnostics);
		        }
		        Class<T> newClass = (Class<T>) classLoader.loadClass(qualifiedClassName);
		    	return newClass;
		    }
		    catch (Exception exception) {
		        throw new CompilerException(exception, diagnostics);
		 }
		 
		 
	}
}
