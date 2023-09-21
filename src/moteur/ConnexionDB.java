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

public class ConnexionDB {
	private String url ;
    private String utilisateur ;
    private String motDePasse;
    private Connection connexion ;
    private CallableStatement callableStatement ;
	public static int[] stringTabToInt(String[] stringTab) 
	{
		
		int[] intArray = new int[stringTab.length];

		for (int i = 0; i < stringTab.length; i++) {
		    try {
		        intArray[i] = Integer.parseInt(stringTab[i]);
		    } catch (NumberFormatException e) {
		        // Gérez les cas où la conversion échoue (par exemple, si la chaîne n'est pas un entier valide).
		        // Vous pouvez choisir de l'ignorer ou de prendre d'autres mesures appropriées.
		        System.err.println("Erreur de conversion pour l'élément à l'indice " + i);
		        intArray[i] = 0; // Valeur par défaut en cas d'erreur.
		    }
		}
		return intArray;
	}
	

	
    public ConnexionDB(){
    	this.url = "jdbc:oracle:thin:@10.40.128.30:1521:orcl";
    	this.utilisateur = "Y2023M2_SDJAKER";
    	this.motDePasse = "imad2023";
    	this.connexion = null;
    	this.callableStatement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            if (connexion != null) {            	
                System.out.println("Connexion à la base de données Oracle réussie !");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void closeConnexion() {
        // Fermeture des ressources
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Connexion à la base de données Oracle fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
	public ArrayList<Multimedia> createMultimedia() throws SQLException {
		String request = "select ID,MOYNORMEGRADIENT,HR,HB,HG,ISNB from multimedia Order by ID";
        Statement statement =  connexion.createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        ArrayList<Multimedia> images_all = new ArrayList<Multimedia>();
        
        // Traiter les résultats (dans cet exemple, nous affichons simplement les résultats)
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            double moynormegradiant = resultSet.getDouble("MOYNORMEGRADIENT");
            String hrString = resultSet.getString("HR");
            String hbString = resultSet.getString("HB");
            String hgString = resultSet.getString("HG");
            boolean isNb = resultSet.getBoolean("ISNB");
            int[] hr = stringTabToInt(hrString.split(","));
            int[] hb = stringTabToInt(hbString.split(","));
            int[] hg = stringTabToInt(hgString.split(","));            
            Histogramme histogramme = new Histogramme(hr, hb, hg);
            Multimedia multimedia = new Multimedia(id, moynormegradiant, histogramme, isNb);
            images_all.add(multimedia);
        }
        
        resultSet.close();
        statement.close();
        return images_all;
        
	}
}
