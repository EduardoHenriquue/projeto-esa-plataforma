package br.ufpb.esa.plataforma.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Demonstrates a drag-and-drop feature.
 */
public class MainApplication extends Application {

	Stage stage;
	Parent root;
	
	public void start(Stage stage){
		this.stage = stage;
		
		iniciar();
	}
	
	public void iniciar(){
		FXMLLoader carregador = new FXMLLoader();
		try {
			this.root = carregador.load(MainApplication.class.getResource("/view/SceneMontarAlgoritmo.fxml"));
			
			this.stage.setScene(new Scene(this.root));
			this.stage.setTitle("Montar Algoritmo");
			stage.show();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public Stage getStage(){
		return this.stage;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
