package br.ufpb.esa.plataforma.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Bloco extends JPanel{
	
	private Graphics retangulo;
	
	public Bloco(int coordenadaX, int coordenadaY){
		super.paintComponents(this.retangulo);
		this.retangulo.drawRect(coordenadaX, coordenadaY, 40, 15);
		
	}
	
	public void setTitleBloco(){
		
	}
	

}
