package moteur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import multimedia.Histogramme;
import multimedia.Multimedia;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnexionDB connexionDB = new ConnexionDB();
    	ArrayList<Multimedia> all_images=  connexionDB.createMultimedia();
    	Traitement traitement = new Traitement(all_images);
    	
    	
    	/*  TEST peu de vert Beaucoup de rouge 
    	ArrayList<Multimedia> peuVertBcpRouge= traitement.getByColeur(45,25 );
        for (Multimedia multimedia : peuVertBcpRouge) {
 			System.out.println("Image peuVertBcpRouge "+multimedia.getID()+" distance:"+multimedia.getDistance());
 		}
 		*/
    	
    	
    	/* Test avoir images noir et blanc
    	ArrayList<Multimedia> nbImages =  traitement.getNbImages();
    	   for (Multimedia multimedia : nbImages) {
        	System.out.println("image noir et blanc:" + multimedia.getID());
		}
    	 * */
    	
    	
    	/* test similarité par caract
    	int IdImage = 11;
    	Multimedia image = all_images.get(IdImage -1) ;
    	int poidTexture = 2 ;
    	int poidRGB = 8 ;
    	boolean isEuc = false ;
    	ArrayList<Multimedia> similarImagesCaracterstics= traitement.getSimilarImagesByCaracterstics(image,poidTexture, poidRGB, isEuc );
        for (Multimedia multimedia : similarImagesCaracterstics) {
 			System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
 		}
 		*/
    	
    	
    	

        
    	
        
    	
    	/*
    	ArrayList<Multimedia> similarImagesBySignature= connexionDB.getSimilarBySignature(11,1,0,0,0);
    	        for (Multimedia multimedia : similarImagesCaracterstics) {
 			System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
 		}
        */
    	/*
        boolean isSimilar  = connexionDB.isSimilarBySignature(1,60,1,0,0,0);
        System.out.println("l'image 1 et 60 sont similaires ? : "+isSimilar);
        
        */
      
        
        
        
        
        
        
        
       

    }
}
