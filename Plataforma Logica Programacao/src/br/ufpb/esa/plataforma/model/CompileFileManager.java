package br.ufpb.esa.plataforma.model;

import java.io.IOException;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

public class CompileFileManager extends ForwardingJavaFileManager <JavaFileManager> {

	private CompileClassLoader classLoader;
	private CompileByte codigoFonte;
	private CompileByte codigoCompilado;
	
	public CompileFileManager(JavaFileManager fileManager, CompileClassLoader classLoader) {
		super(fileManager);
		this.classLoader= classLoader;
	}
	
	public void setSources(CompileByte sourceObj, CompileByte compiledObj){
		this.codigoFonte = sourceObj;
		this.codigoCompilado = compiledObj;
		this.classLoader.addClass(compiledObj);
	}
	
	 @Override
	  public FileObject getFileForInput(Location location, String packageName,String relativeName) throws IOException{
	    return codigoFonte;
	  }

	  @Override
	  public JavaFileObject getJavaFileForOutput(Location location, String qualifiedName, Kind kind, FileObject outputFile) throws IOException{
	    return codigoCompilado;
	  }

	  @Override
	  public ClassLoader getClassLoader(Location location) {
	    return classLoader;
	  }

}
