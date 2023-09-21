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
    	ArrayList<Multimedia> nbImages =  traitement.getNbImages();
        ArrayList<Multimedia> similarImages= traitement.getSimilarImagesByCaracterstics(all_images.get(10), 2, 8);
        
        for (int i = 0; i < 10; i++) {
			System.out.println("Image similaire a l'image 11 :"+similarImages.get(i).getID()+" | distance :"+similarImages.get(i).getDistance());
		}
        
        
        for (int i = 0; i < nbImages.size(); i++) {
			System.out.println("Image noir est blancs"+similarImages.get(i).getID()+" | distance :"+similarImages.get(i).getDistance());
		}
    }
}
