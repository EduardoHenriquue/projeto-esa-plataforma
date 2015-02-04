package br.ufpb.esa.plataforma.model;

import java.util.HashMap;
import java.util.Map;

import javax.tools.SimpleJavaFileObject;

public class CompileClassLoader extends ClassLoader {

	private Map <String, SimpleJavaFileObject> classes = new HashMap<String, SimpleJavaFileObject>();
	
	public CompileClassLoader(ClassLoader classLoader){
		super(classLoader);
	}
	
	public void addClass(CompileByte compiledObj){
		classes.put(compiledObj.getName(), compiledObj);
	}
	
	@Override
	  public Class<?> findClass(String qualifiedClassName)throws ClassNotFoundException{
		CompileByte byt = (CompileByte) classes.get(qualifiedClassName);
		if (byt == null) {
			return super.findClass(qualifiedClassName);
		}
	    byte[] bytes = byt.getBytes();
	    return defineClass(qualifiedClassName, bytes, 0, bytes.length);
	  }
}
