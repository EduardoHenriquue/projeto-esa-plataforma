package br.ufpb.esa.plataforma.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TelaMontarAlgoritmo extends Application{
	
	private Group root;
	private double largura = 900;
    private double altura = 500;
    private HBox hBoxFigura;
    private HBox hBoxDrop;
    private HBox vBox;
    private ScrollPane scroll;
    //private Text labelHBFigura;
    //private Text labelHBDrop;
    private ImageView imagem;
    private int cursor = 0;
    private int esquerda = 10;
    private int direita = 0;
    
    
    
    public static void main(String[] args) {
        launch(args);
    }

    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*
		 * Definição da tela e nome
		 */
		primaryStage.setTitle("Bem vindo ao Prog!");
		this.root = new Group();
		Scene cena = new Scene(root, this.largura, this.altura, Color.WHITE);
		
		/*
		 * Definição da área e onde ficarão as figuras
		 */
		this.hBoxFigura = new HBox();
        this.hBoxFigura.setPrefSize(400, 200);
        this.hBoxFigura.setSpacing(10);
        this.hBoxFigura.setStyle("-fx-border-width: 1;" + "-fx-border-style: solid;");
        
        /*
         * Definição da área para onde as figuras serão arrastadas
         */
		this.hBoxDrop = new HBox();
		this.hBoxDrop.setPrefSize(400, 400);
		this.hBoxFigura.setSpacing(10);
		this.hBoxDrop.setStyle("-fx-border-width: 1;" + "-fx-border-style: solid;");
		
		//this.labelHBFigura = new  Text("Arraste... ");
        //this.labelHBFigura.setTranslateX(20);
        //this.labelHBFigura.setTranslateY(20);
        
        //this.labelHBDrop = new  Text("... E Solte Aqui! ");
        //this.labelHBDrop.setTranslateX(420);
        //this.labelHBDrop.setTranslateY(20);
        
        
        /*
         * Carrega as imagens
         */
        //this.carregarImagem(new Image("imagens/mario.png"), this.hBoxFigura);
        this.carregarImagem(new Image("imagens/inicio.png"), this.hBoxFigura);
        this.carregarImagem(new Image("imagens/fim.png"), this.hBoxFigura);
        
        
        /*
         * 
         */
        this.setupGestureTarget(hBoxFigura);
        this.setupGestureTarget(hBoxDrop);
        
        /*
         * 
         */
        this.vBox = new HBox();
        vBox.getChildren().addAll(this.hBoxFigura, this.hBoxDrop);
        root.getChildren().addAll(vBox);
        //root.getChildren().add(this.labelHBFigura);
        //root.getChildren().add(this.labelHBDrop);

        primaryStage.setScene(cena);
        primaryStage.show();
		
	}

	public void carregarImagem(Image image, HBox hBox){
		imagem = new ImageView();
		imagem.setImage(image);
		this.setupGestureSource(imagem);
		hBox.getChildren().add(imagem);
		
	}
	
	
//	public void relocateToPoint (Point2D p) {
//	    Point2D p2 = this.hBoxFigura.sceneToLocal(p);
//
//	    this.hBoxDrop.relocate (
//	            (int) (p2.getX()),
//	            (int) (p2.getY())
//	        );
//	}
	
	
	public void setupGestureSource(final ImageView source){
        
        source.setOnDragDetected(new EventHandler <MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {

               /* allow any transfer mode */
               Dragboard dragboard = source.startDragAndDrop(TransferMode.ANY);
                
               /* put a image on dragboard */
               ClipboardContent content = new ClipboardContent();
                
               Image sourceImage = source.getImage();
               content.putImage(sourceImage);
               dragboard.setContent(content);


               imagem = source ;
               //relocateToPoint(new Point2D( event.getSceneX(), event.getSceneY()));
               event.consume();
           }
       });
        
            source.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    source.setCursor(Cursor.CLOSED_HAND);
//                    System.out.println("e...: "+e.getSceneX());
                    cursor = (int) e.getSceneX();
                }
            });
    }
    
   public void setupGestureTarget(final HBox targetBox){
         
        targetBox.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
 
                Dragboard db = event.getDragboard();
                 
                if(db.hasImage()){
                    event.acceptTransferModes(TransferMode.ANY);
                    //relocateToPoint(new Point2D( event.getSceneX(), event.getSceneY()));
                }
                 
                event.consume();
            }
        });
 
        targetBox.setOnDragDropped(new EventHandler <DragEvent>(){
            @Override
            public void handle(DragEvent event) {
  
                Dragboard db = event.getDragboard();
 
                if(db.hasImage()){
 
                    imagem.setImage(db.getImage());
                    
                    
                    Point2D localPoint = targetBox.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));

//                    System.out.println("event.getSceneX : "+event.getSceneX());
//                    System.out.println("localPoint.getX : "+localPoint.getX());
//                    System.out.println("********");
 
                    targetBox.getChildren().remove(imagem);

                    imagem.setX((int)(localPoint.getX() - imagem.getBoundsInLocal().getWidth())  );
                    imagem.setY((int)(localPoint.getY() - imagem.getBoundsInLocal().getHeight()) );

                    targetBox.getChildren().add(imagem);
                   
                    
                    
                    event.setDropCompleted(true);
                }else{
                    event.setDropCompleted(false);
                }
 
                event.consume();
            }
        });
    }
	
}
