package br.ufpb.esa.plataforma.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;

public class Algoritmo {
	
	
	Map<Integer, Image> algoritmo = new HashMap<Integer, Image>();

	public Algoritmo() {
		
	}

	// retornando uma lista apenas para teste
	public Map<Integer, Image> montarAlgoritmo(FiguraAlgoritmo figura) {
		this.algoritmo.put(figura.getId(), figura.getFigura());
		return this.algoritmo;
	}
	
	public Boolean compararAlgoritmo(Map<Integer, Image> algoritmoCorreto, Map<Integer, Image> algoritmoDoAluno){
		return null;
	}

}
