package br.ufpb.esa.plataforma.model;

import java.net.URI;
import java.net.URISyntaxException;

import javax.tools.JavaFileObject.Kind;


public enum CompileUtils {

	INSTANCE;
	
	public  URI createURI(String str) {
	    try {
	      return new URI(str);
	    }
	    catch (URISyntaxException e) {
	      throw new RuntimeException(e);
	    }
	  }
	
	public String getQualifiedClassName(String packageName, String className){
		if(packageName.isEmpty()){
			return className;
		}
		else{
			return packageName + "." + className;
		}
	}
	public String getClassNameWithExt (String className){
		return className + Kind.SOURCE.extension;
	}
}
