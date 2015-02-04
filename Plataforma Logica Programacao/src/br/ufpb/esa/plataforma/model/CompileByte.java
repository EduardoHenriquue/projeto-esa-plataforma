package br.ufpb.esa.plataforma.model;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.tools.SimpleJavaFileObject;

public class CompileByte extends SimpleJavaFileObject {
	
	private String source;
	private ByteArrayOutputStream byteCode = new ByteArrayOutputStream();

	public CompileByte(String name, String source) {
	   super(CompileUtils.INSTANCE.createURI(CompileUtils.INSTANCE.getClassNameWithExt(name)), Kind.SOURCE);
	   this.source=source;
	}
	
	public CompileByte (String name){
		super(CompileUtils.INSTANCE.createURI(name), Kind.CLASS);
	}
	
	  @Override
	  public String getCharContent(boolean ignoreEncodingErrors) {
	    return source;
	  }

	  @Override
	  public OutputStream openOutputStream() {
	    return byteCode;
	  }

	  public byte[] getBytes() {
	    return byteCode.toByteArray();
	  }
}
