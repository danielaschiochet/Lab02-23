/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.alien.model.Dizionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Dizionario d = new Dizionario();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML // fx:id="btnTranslate"
    private Button btnTranslate; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtText"
    private TextField txtText; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {

    	this.txtResult.clear();
    	this.txtText.clear();
    	d.elimina();
    	
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	String stringa = this.txtText.getText().toLowerCase(); //rendo tutti i caratteri minuscoli
    	
    	if(stringa==null) { //se l'utenete non ha scritto nulla lo invito a riprovare
    		this.txtResult.setText("Inserisci una o due parole.");
    		return;
    	}
    	
    	StringTokenizer st = new StringTokenizer(stringa, " "); //taglio la stringa dove ci sono gli spazi
    	
    	if(!st.hasMoreTokens()) { //se non c'è scritto nulla invito l'utente a riprovare
    		this.txtResult.setText("Inserisci una o due parole.");
    		return;
    	}
    	
    	String alieno = st.nextToken(); //altrimenti prendo la prima parola [PAROLA ALIENA]
    	
    	if(st.hasMoreTokens()) { //se ho + di una parola
    		String tradotto = st.nextToken(); //la seconda sarà la [TRADUZIONE]
    		
    		if(!alieno.matches("[a-zA-Z]*") || !tradotto.matches("[a-zA-Z]*")) { //controllo che siano solo caratteri alfabetici altrimenti invito a riprovare
    			txtResult.setText("Inserire solo caratteri alfabetici.");
    			return;
    		}
    		
    		this.d.aggiungi(alieno, tradotto); //se tutto ok aggiugo la traduzione al dizionario e lo riferisco all'utente
    		txtResult.setText("La parola <"+alieno+"> è stata aggiunta con traduzione <"+tradotto+">.");
    		txtText.clear();
    		
    	}else { //se mi viene data solo la parola aliena
    		if(!alieno.matches("[a-zA-Z?]*")){ //controllo che siano caratteri accettati
    			txtResult.setText("Inserire solo caratteri alfabetici o ?.");
    			return;
    		}
    		
    		String tradotto; //mi creo la stringa x stampare la traduzione
    		
    		if(alieno.matches("[a-zA-Z?]*") && !alieno.matches("[[a-zA-Z]*]")) { //controllo se wildcard	
    			
    			tradotto = d.traduciWordWildCard(alieno);
    			
    		} else { //se non lo è mi salvo la traduzione
    			
    			tradotto = d.traduci(alieno);
    			
    		}
    		
    		if(tradotto!=null) { //se esiste una traduzione gliela stampo all'utente
    			txtResult.setText(tradotto);
    			txtText.clear();
    		}else { //altrimenti gli dico che non c'è
    			txtResult.setText("La parola cercata non è presente.");
    			txtText.clear();
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtText != null : "fx:id=\"txtText\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
