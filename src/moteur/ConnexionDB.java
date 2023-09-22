package moteur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public boolean isSimilarBySignature(int id_image1, int id_image2,float color,float texture, float shape, float location) throws SQLException {
    	boolean isSimilar = false ;
    	String request = "declare\n"
    			+ "  i ordsys.ordimage;\n"
    			+ "  ctx RAW(400) := NULL;\n"
    			+ "  ligne multimedia%ROWTYPE;\n"
    			+ "  cursor mm is\n"
    			+ "    select * from multimedia\n"
    			+ "    for update;\n"
    			+ "  sig1 ordsys.ordimageSignature;\n"
    			+ "  sig2 ordsys.ordimageSignature;\n"
    			+ "  sim integer;\n"
    			+ "  dist float;\n"
    			+ "  i NUMBER;\n"
    			+ "  image_name VARCHAR2(100);\n"
    			+ "	 message VARCHAR2(100);\n"
    			+ "begin \n"
    			
    			+ "    select signature into sig1\n"
    			+ "    from multimedia\n"
    			+ "    where ID = '"+id_image1+"' ;\n"
    		
    			+ "    select signature into sig2\n"
    			+ "    from multimedia\n"
    			+ "    where ID = '"+id_image2+"' ;\n"
    			+ "    \n"
    		
    			+ "    sim := ordsys.ordimageSignature.isSimilar(sig1, sig2, 'color = "+color+", texture = "+texture+", shape ="+shape+", location = "+location+"', 10); \n"
    			+ "    -- Afficher les resultats : \n"
    			+ "    if \n"
    			+ "        -- Si similaires  : \n"
    			+ "\n"
    			+ "        sim = 1 then message:='true';\n"
    			+ "    else \n"
    			+ "       -- Si non similaires  : \n"
    			+ "        message:='false';\n"
    			+ "   end if;\n"
    			+ "		?:=message;\n"
    			+ "end;";

  
            CallableStatement callableStatement = connexion.prepareCall(request);
               
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

                // Exécution de la requête
            callableStatement.execute();

                // Récupération du résultat de la requête
            String message = callableStatement.getString(1);
            if(message.equals("true")) {
            	isSimilar = true ;
            };
            callableStatement.close();
            return isSimilar ;
     
    }
    public ArrayList<Multimedia> getSimilarBySignature10(int id_image,float color,float texture, float shape, float location)  throws SQLException{
    	
    	
    	String query = "declare\n"
    			+ "  i ordsys.ordimage;\n"
    			+ "  ctx RAW(400) := NULL;\n"
    			+ "  ligne multimedia%ROWTYPE;\n"
    			+ "  cursor mm is\n"
    			+ "    select * from multimedia\n"
    			+ "    for update;\n"
    			+ "  sig1 ordsys.ordimageSignature;\n"
    			+ "  sig2 ordsys.ordimageSignature;\n"
    			+ "  sim integer;\n"
    			+ "  dist float;\n"
    			+ "  i NUMBER;\n"
    			+ "	 message VARCHAR2(10000);\n"
    			+ "  image_name VARCHAR2(100);\n"
    			+ "begin \n"
    			+ "	 message := '';\n"
    			+ "    -- marquer le numero de l'ID correspondant a l'image qu'on veut tester sur la console dans cette exemple ID=1 \n"
    			+ "    select signature into sig1\n"
    			+ "    from multimedia\n"
    			+ "    where ID = '"+id_image+"' ;\n"
    			+ "\n"
    			+ "  FOR image_number IN 1..10 LOOP\n"
    			+ "        -- va parcourir tout les autres ID pour récupérer leur signature et comparé avec celle donnée en entrée \n"
    			+ "           select signature into sig2\n"
    			+ "            from multimedia\n"
    			+ "            where ID = image_number ;\n"
    			+ "             -- Fait la similarité entre deux signatures de deux images différentes \n"
    			+ "             -- rajouter la possibilité de faire les critéres sur la console ! \n"
    			+ "          -- Afficher les resultats : \n"
    			+ "              dist := ordsys.ordimageSignature.evaluateScore(sig1, sig2, 'color = "+color+", texture = "+texture+", shape ="+shape+", location = "+location+"');\n"
    			+ "				message := message || TO_CHAR(image_number) || ',' || TO_CHAR(dist) || ';' ; \n"
    			+ "  END LOOP;\n"
    			+ "		?:=message;\n"
    			+ "end;";
    	  
        CallableStatement callableStatement = connexion.prepareCall(query);
           
        callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

            // Exécution de la requête
        callableStatement.execute();

            // Récupération du résultat de la requête
        String m = callableStatement.getString(1);
        System.out.println(m);
        String[] message = m.split(";");
        ArrayList<Multimedia> images_similaire = new ArrayList<Multimedia>();
        for(int i = 0 ; i<message.length ; i++) {
        	String[] StringIdDistance= message[i].split(",") ; 
        	
        	int id = Integer.parseInt(StringIdDistance[0]);
        	int distance ;
        	if(StringIdDistance[1].equals("")) {
        		distance = 0;
        	}else {
            	distance = Integer.parseInt(StringIdDistance[1]);
            	Multimedia multimedia = new Multimedia(id, distance);
            	images_similaire.add(multimedia);
        	}

        }
        
        Collections.sort(images_similaire, new Comparator<Multimedia>() {
            @Override
            public int compare(Multimedia m1, Multimedia m2) {
                // Triez par ordre croissant de distance.
                return Integer.compare(m1.getDistance(), m2.getDistance());
            }
        });
        callableStatement.close();
    	return images_similaire;
    }
    
    public ArrayList<Multimedia> getSimilarBySignature(int id_image,float color,float texture, float shape, float location)  throws SQLException{
    	
    	
    	String query = "declare\n"
    			+ "  i ordsys.ordimage;\n"
    			+ "  ctx RAW(400) := NULL;\n"
    			+ "  ligne multimedia%ROWTYPE;\n"
    			+ "  cursor mm is\n"
    			+ "    select * from multimedia\n"
    			+ "    for update;\n"
    			+ "  sig1 ordsys.ordimageSignature;\n"
    			+ "  sig2 ordsys.ordimageSignature;\n"
    			+ "  sim integer;\n"
    			+ "  dist float;\n"
    			+ "  i NUMBER;\n"
    			+ "	 message VARCHAR2(10000);\n"
    			+ "  image_name VARCHAR2(100);\n"
    			+ "begin \n"
    			+ "	 message := '';\n"
    			+ "    -- marquer le numero de l'ID correspondant a l'image qu'on veut tester sur la console dans cette exemple ID=1 \n"
    			+ "    select signature into sig1\n"
    			+ "    from multimedia\n"
    			+ "    where ID = '"+id_image+"' ;\n"
    			+ "\n"
    			+ "  FOR image_number IN 1..500 LOOP\n"
    			+ "        -- va parcourir tout les autres ID pour récupérer leur signature et comparé avec celle donnée en entrée \n"
    			+ "           select signature into sig2\n"
    			+ "            from multimedia\n"
    			+ "            where ID = image_number ;\n"
    			+ "             -- Fait la similarité entre deux signatures de deux images différentes \n"
    			+ "             -- rajouter la possibilité de faire les critéres sur la console ! \n"
    			+ "          -- Afficher les resultats : \n"
    			+ "              dist := ordsys.ordimageSignature.evaluateScore(sig1, sig2, 'color = "+color+", texture = "+texture+", shape ="+shape+", location = "+location+"');\n"
    			+ "				message := message || TO_CHAR(image_number) || ',' || TO_CHAR(dist) || ';' ; \n"
    			+ "  END LOOP;\n"
    			+ "		?:=message;\n"
    			+ "end;";
    	  
        CallableStatement callableStatement = connexion.prepareCall(query);
           
        callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);

            // Exécution de la requête
        callableStatement.execute();

            // Récupération du résultat de la requête
        String m = callableStatement.getString(1);
        System.out.println(m);
        String[] message = m.split(";");
        ArrayList<Multimedia> images_similaire = new ArrayList<Multimedia>();
        for(int i = 0 ; i<message.length ; i++) {
        	String[] StringIdDistance= message[i].split(",") ; 
        	
        	int id = Integer.parseInt(StringIdDistance[0]);
        	int distance ;
        	if(StringIdDistance[1].equals("")) {
        		distance = 0;
        	}else {
            	distance = Integer.parseInt(StringIdDistance[1]);
            	Multimedia multimedia = new Multimedia(id, distance);
            	images_similaire.add(multimedia);
        	}

        }
        
        Collections.sort(images_similaire, new Comparator<Multimedia>() {
            @Override
            public int compare(Multimedia m1, Multimedia m2) {
                // Triez par ordre croissant de distance.
                return Integer.compare(m1.getDistance(), m2.getDistance());
            }
        });
        callableStatement.close();
    	return images_similaire;
    }
	public ArrayList<Multimedia> createMultimedia10() throws SQLException {
		String request = "select ID,MOYNORMEGRADIENT,HR,HB,HG,ISNB from multimedia where ID<'11' Order by ID ";
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
