package br.ufpb.esa.plataforma.controller;

import javafx.scene.image.Image;

public class FiguraInicio implements FiguraAlgoritmo {
	
	// Esta variável nunca mudará
	private Image imagem;
	
	private Integer id;
	
	public FiguraInicio(){
		
	}

	
	@Override
	public Image getFigura() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getId() {
		
		return this.id;
	}
	
		
}
