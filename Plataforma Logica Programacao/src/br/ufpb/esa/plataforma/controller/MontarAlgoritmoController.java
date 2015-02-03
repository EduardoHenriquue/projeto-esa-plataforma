package br.ufpb.esa.plataforma.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class MontarAlgoritmoController implements Initializable {

	@FXML
	private Rectangle comando;
	@FXML
	private AnchorPane pane;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void dragAndDropComandos(DragEvent evento) {
		this.comando.getScaleX();
		this.comando.getScaleY();

		this.pane.getScaleX();
		this.pane.getScaleY();

		comando.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evento) {
				Dragboard drag = comando.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString(comando.getId());
				drag.setContent(content);
				evento.consume();

			}
		});

		this.pane.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent evento) {
				if (evento.getGestureSource() != pane
						&& evento.getDragboard().hasString()) {
					evento.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				evento.consume();
			}
		});

		this.pane.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent evento) {
				Dragboard drag = evento.getDragboard();
				Boolean concluido = false;
				if (drag.hasString()) {
					pane.setClip(comando);
					concluido = true;
				}

				evento.setDropCompleted(concluido);

				evento.consume();
			}
		});

		this.comando.setOnDragDone(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent evento) {
				if (evento.getTransferMode() == TransferMode.MOVE) {
					comando.setClip(comando);
				}

				evento.consume();
			}
		});
	}

}
