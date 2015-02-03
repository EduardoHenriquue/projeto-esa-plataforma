package br.ufpb.esa.plataforma.controller;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.InvalidDnDOperationException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ImageDrag extends JLabel{
	
	private ImageIcon imagem;
	private DragSource dragSource;
	private DragGestureListener dgListener;
	private DragSourceListener dsListener;
	private int dragAction = DnDConstants.ACTION_COPY_OR_MOVE;
	
	public ImageDrag(JPanel panel){
		this.imagem = new ImageIcon(getClass().getResource("imagens/mario.png"));
		this.setIcon(imagem);
		
		this.dragSource = DragSource.getDefaultDragSource();
		this.dgListener = new DGListener();
		// component, action, listener
		this.dragSource.createDefaultDragGestureRecognizer(this, this.dragAction, this.dgListener);
		
	}

	
	class DGListener implements DragGestureListener {
		/**
		 * Start the drag if the operation is ok.
		 * uses java.awt.datatransfer.StringSelection to transfer
		 * the label's data
		 * @param e the event object
		 */
		  
		 public void dragGestureRecognized(DragGestureEvent evento) 
		 {
			 //
			 // if the action is ok we go ahead
			 // otherwise we punt
			 // 
				 System.out.println("DragActioon-> "+evento.getDragAction()+" "
						+ ImageDrag.this.dragAction);

			 if((evento.getDragAction() & ImageDrag.this.dragAction) == 0)
				 return;
			 //
			 // get the label's text and put it inside a Transferable
			 // Transferable transferable = 
			 // 		new StringSelection( DragLabel.this.getText() );
			 // 		

			 
			 Transferable transferable = new TransferableImagem(ImageDrag.this.imagem);    
			 
			 //
			 // now kick off the drag
			 // 
			 try 
			 {
				 System.out.println("Drag Started");
				 // initial cursor, transferrable, dsource listener      
				 evento.startDrag(DragSource.DefaultCopyNoDrop, transferable, ImageDrag.this.dsListener);
			}
			catch( InvalidDnDOperationException idoe ) 
			{
				System.err.println( idoe );
			}
		 }
	}

	
		  
	class DSListener implements DragSourceListener {
		  /**
		   * @param e the event
		   */    
		  public void dragDropEnd(DragSourceDropEvent e) 
		  {
			  if( e.getDropSuccess() == false ) 
			  {		  
				  return;
			  }
			  /*
			   * the dropAction should be what the drop target specified
			   * in acceptDrop
			   */
			  
			  
			  // this is the action selected by the drop target
			  if((e.getDropAction() & ImageDrag.this.dragAction )==0)
				  return;
		  }
		  
		  /**
		   * @param e the event
		   */	  
		  public void dragEnter(DragSourceDragEvent evento) {      
			  DragSourceContext context = evento.getDragSourceContext();
			  //intersection of the users selected action, and the source and target actions
			  int myaction = evento.getDropAction();
			  if( (myaction & ImageDrag.this.dragAction) != 0) {
				  context.setCursor(DragSource.DefaultCopyDrop);	  
	      		  } 
			  else {
				  context.setCursor(DragSource.DefaultCopyNoDrop);	  	
			  }
		  }
		  
		  /**
		   * @param e the event
		   */  
		  public void dragOver(DragSourceDragEvent e) {
			  DragSourceContext context = e.getDragSourceContext();
			  int sa = context.getSourceActions();
			  int ua = e.getUserAction();
			  int da = e.getDropAction();
			  int ta = e.getTargetActions();
			  
		  }
		  
		  /**
		   * @param e the event
		   */
		  
		  public void dragExit(DragSourceEvent evento) 
		  {
			  DragSourceContext context = evento.getDragSourceContext();
		  }
		  
		  /**
		   * for example, press shift during drag to change to
		   * a link action
		   * @param e the event     
		   */
		  public void dropActionChanged (DragSourceDragEvent e) 
		  {
			  DragSourceContext context = e.getDragSourceContext();      
			  context.setCursor(DragSource.DefaultCopyNoDrop);	  	
		  }
	  }
	
	
}


class TransferableImagem implements Transferable {
	  protected static DataFlavor imagemFlavor = new DataFlavor(ImageIcon.class, "Objeto Imagem");
	  protected static DataFlavor[] supportedFlavors = { imagemFlavor };
	  ImageIcon icon;
	  public TransferableImagem(ImageIcon icon){
	    this.icon = icon;
	  }

	  public DataFlavor[] getTransferDataFlavors() {
	    return supportedFlavors;
	  }

	  public boolean isDataFlavorSupported(DataFlavor flavor) {
	    if (flavor.equals(imagemFlavor) || flavor.equals(DataFlavor.stringFlavor))
	      return true;
	    return false;
	  }

	  public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
	    if (flavor.equals(imagemFlavor))
	      return icon;
	    else if (flavor.equals(DataFlavor.stringFlavor))
	      return icon.toString();
	    else
	      throw new UnsupportedFlavorException(flavor);
	  }
	}