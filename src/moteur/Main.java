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
    	
    	ArrayList<Multimedia> peuVertBcpRouge= traitement.getByColeur(45,25 );
        for (Multimedia multimedia : peuVertBcpRouge) {
 			System.out.println("Image peuVertBcpRouge "+multimedia.getID()+" distance:"+multimedia.getDistance());
 		}
    	/*
        for (Multimedia multimedia : peuVertBcpRouge) {
 			System.out.println("Image peuVertBcpRouge "+multimedia.getID()+" distance:"+multimedia.getDistance());
 		}
    	
    	ArrayList<Multimedia> nbImages =  traitement.getNbImages();
        
    	ArrayList<Multimedia> similarImagesCaracterstics= traitement.getSimilarImagesByCaracterstics(all_images.get(10));
        
    	
    	
    	ArrayList<Multimedia> similarImagesBySignature= connexionDB.getSimilarBySignature(11,1,0,0,0);
        boolean isSimilar  = connexionDB.isSimilarBySignature(1,60,1,0,0,0);
        
        
        System.out.println("############ Similaire By Signature ############\n\n");
        
        
        
        
        for (Multimedia multimedia : similarImagesCaracterstics) {
			System.out.println("Image similaire a l'image 11 :"+multimedia.getID()+" distance:"+multimedia.getDistance());
		}
        System.out.println("############ Similaire By Caractéristique ############\n\n");
        for (Multimedia multimedia : similarImagesBySignature) {
        	System.out.println("Image similaire a l'image 11 :"+multimedia.getID()+" distance:"+multimedia.getDistance());
		}
        
        
        
        
        System.out.println("l'image 10 est 20 sont similaire ? : "+isSimilar);
       
        */

    }
}
