package br.ufpb.esa.plataforma.model;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class CompilerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O coletor de informações da compilação
	 */
	private DiagnosticCollector<JavaFileObject> collector;

	public CompilerException(String message) {
		super(message);
	}

	public CompilerException(String message, DiagnosticCollector<JavaFileObject> collector) {
		super(message);
		this.collector = collector;
	}
	
	public CompilerException(Throwable e, DiagnosticCollector<JavaFileObject> collector) {
		super(e);
		this.collector = collector;
	}

	public String getCompilationError() {
		StringBuilder sb = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> diagnostic : collector.getDiagnostics()) {
            System.out.println("Tipo de Arquivo: " + diagnostic.getKind());
            System.out.println("Código: " + diagnostic.getCode());
            System.out.println("Linha: " + diagnostic.getLineNumber());
            System.out.println("Coluna: " + diagnostic.getColumnNumber());
            System.out.println("Posição inicial: " + diagnostic.getStartPosition());
            System.out.println("Posição: " + diagnostic.getPosition());
            System.out.println("Posição final: " + diagnostic.getEndPosition());
        }
		return sb.toString();
	}
	
	@Override
	public String toString() {
	  return getCompilationError();
	}
}
