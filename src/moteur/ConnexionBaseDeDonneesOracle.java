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

public class ConnexionBaseDeDonneesOracle {
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
    public static void main(String[] args) {
        // Informations de connexion à la base de données Oracle
        String url = "jdbc:oracle:thin:@10.40.128.30:1521:orcl";
        String utilisateur = "Y2023M2_SDJAKER";
        String motDePasse = "imad2023";

        // Déclaration de l'objet de connexion
        Connection connexion = null;
        CallableStatement callableStatement = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            if (connexion != null) {
            	
            	
            	
            	
                System.out.println("Connexion à la base de données Oracle réussie !");
                String request = "select ID,MOYNORMEGRADIENT,HR,HB,HG,ISNB from multimedia Order by ID";
                Statement statement =  connexion.createStatement();
                ResultSet resultSet = statement.executeQuery(request);
                ArrayList<Multimedia> images_all = new ArrayList();
                
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
                    
                    System.out.println("ID: " + id + ", Moynormegradiant: " + moynormegradiant + ", HR: " + hr + ", HB: " + hb+ ", HG: " + hg+", isNb:"+isNb);
                    
                    Histogramme histogramme = new Histogramme(hr, hb, hg);
                    Multimedia multimedia = new Multimedia(id, moynormegradiant, histogramme, isNb);
                    images_all.add(multimedia);
                }
                Traitement traitement = new Traitement(images_all);
                traitement.getNbImages();
                ArrayList<Multimedia> similarImages= traitement.getSimilarImagesByCaracterstics(images_all.get(10));
                
                for (int i = 0; i < 500; i++) {
					System.out.println(similarImages.get(i).getID()+":"+similarImages.get(i).getDistance());
					
				}
                
                // Fermer les ressources
                resultSet.close();
                statement.close();
             
                
                /*
                // Script PL/SQL que vous souhaitez exécuter
                String plsqlScript = "BEGIN " +
                        "FOR row IN multimedia LOOP " +
                        "DBMS_OUTPUT.PUT_LINE('nom: ' || row.nom || ', hb: ' || row.hb || ', hg: ' || row.hg); " +
                        "END LOOP; " +
                        "END;";

                // Appeler le script PL/SQL
                callableStatement = connexion.prepareCall("{call ? := ?}");
                callableStatement.registerOutParameter(1, Types.VARCHAR);
                callableStatement.setString(2, plsqlScript);
                callableStatement.execute();

                // Récupérer le résultat
                String resultat = callableStatement.getString(1);

                // Afficher le résultat
                System.out.println("Résultat du script PL/SQL :");
                System.out.println(resultat);

                System.out.println("Script PL/SQL exécuté avec succès !");
                */
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }
}
