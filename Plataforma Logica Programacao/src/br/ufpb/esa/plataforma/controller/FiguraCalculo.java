package br.ufpb.esa.plataforma.controller;

import javafx.scene.image.Image;

public class FiguraCalculo implements FiguraAlgoritmo {

	private Image imagem;
	
	private Integer id;
	
	public FiguraCalculo(){
		
		
	}

	@Override
	public Image getFigura(){
		return this.imagem;
	}

	@Override
	public Integer getId(){
		return this.id;
	}

}
