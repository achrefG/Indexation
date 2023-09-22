package moteur;
import java.sql.SQLException;
import java.util.ArrayList;
import multimedia.Multimedia;

public class Main {
    public static void main(String[] args) throws SQLException {
       
    	/*#############""TESSTTTT SUR L'ENSEMBLE DES DONNE##########*/
        ConnexionDB connexionDB = new ConnexionDB();
        
        long startTimet = System.currentTimeMillis();
        ArrayList<Multimedia> all_images=  connexionDB.createMultimedia();
        long endTimet = System.currentTimeMillis();
        
        Traitement traitement = new Traitement(all_images);
        
        long executionBDD = endTimet - startTimet ;
        System.out.print("Le temps d'execution de la requete select : "+executionBDD+ " ms");

        
        
    	// TESTS SUR CONSOLE DES REQUETES 
      /*  TEST 1 ---------------------------------------------------------   */  
        
        // test similarite par caracteristiques 

        /*parametre*/
        int IdImage = 11;
        Multimedia image = all_images.get(IdImage -1) ;
        int poidTexture = 2 ;
        int poidRGB = 8 ;
        boolean isEuc = false ;
        // recuperation dans la array list les images similaire a l'image donnee 
        // Calcul du temps d'execution
        long startTimeT1 = System.currentTimeMillis();
        ArrayList<Multimedia> similarImagesCaracterstics= traitement.getSimilarImagesByCaracterstics(image,poidTexture, poidRGB, isEuc );
        
        
        // Affichage des images dans la console en parcourant la liste 
        for (Multimedia multimedia : similarImagesCaracterstics) {
             System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
             }
        long endTimeT1 = System.currentTimeMillis();
        
        long executionTimeByCaracteristics = endTimeT1 - startTimeT1;
        // Affichage du temps d'execution
        System.out.println("Le temps d'execution de la requete  : similarite par caracteristiques est "+
        executionTimeByCaracteristics+" ms");
        
        
        
        
        
        
        /*  TEST 2  ---------------------------------------------------------   */ 
        
        
        
        // test similarite par signatures entre une image et le reste 
          long startTimeT2 = System.currentTimeMillis();
          ArrayList<Multimedia> similarImagesBySignature= connexionDB.getSimilarBySignature(11,1,0,0,0);
          
          
          // Affichage des images en question 
          for (Multimedia multimedia : similarImagesBySignature) {
               System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
               }
          long endTimeT2 = System.currentTimeMillis();
            
          long executionTimeBySignatures = endTimeT2 - startTimeT2; 
           // Affichage du temps d'execution 
          System.out.println("Le temps d'execution de la requete  : similarite par signatures est "+
           executionTimeBySignatures+" ms");
     
                  
                  
                  
                  
         /*  TEST 3  ---------------------------------------------------------   */ 
                  
          
         // test similarite par signatures  entre deux images 
          long startTimeT3 = System.currentTimeMillis();
          boolean isSimilar  = connexionDB.isSimilarBySignature(1,60,1,0,0,0);
          
          
          // Affichage des resultats 
          System.out.println("l'image 1 et 60 sont similaires ? : "+isSimilar);
          long endTimeT3 = System.currentTimeMillis();
          long executionTimeDeuxImages = endTimeT3 - startTimeT3; 
          // Affichage du temps d'execution 
          System.out.println("Le temps d'execution de la requete  : similarite entre deux images est  "+
          executionTimeDeuxImages+" ms");
        
          
          
          
          
          /*  TEST 4 ---------------------------------------------------------   */       
    	
          //  TEST peu de vert Beaucoup de rouge 
          	long startTimeT4 = System.currentTimeMillis();
	        ArrayList<Multimedia> peuVertBcpRouge= traitement.getByColeur(45,25);
	       
	        // Affichage des resultats 
	        for (Multimedia multimedia : peuVertBcpRouge) {
	             System.out.println("Image peuVertBcpRouge "+multimedia.getID()+" distance:"+multimedia.getDistance());
	         }
	        long endTimeT4 = System.currentTimeMillis();
	        
	        long executionTimeRV = endTimeT4 - startTimeT4; 
	          // Affichage du temps d'execution 
	        System.out.println("Le temps d'execution de la requete  : peu de vert beaucoup de rouge est "+
	          executionTimeRV+" ms");
         


		/*  TEST 5 ---------------------------------------------------------   */    
          
        // Test avoir images noir et blanc
	        long startTimeT5 = System.currentTimeMillis();
	        ArrayList<Multimedia> nbImages =  traitement.getNbImages();
	        
	        //Affichage des resultats 
            for (Multimedia multimedia : nbImages) {
            	System.out.println("image noir et blanc:" + multimedia.getID());
            	}
            long endTimeT5 = System.currentTimeMillis();
            long executionTimeNB = endTimeT5 - startTimeT5; 
	        // Affichage du temps d'execution 
	        System.out.println("Le temps d'execution de la requete  : Les images noir et blanc est "+ 
	        executionTimeNB+" ms");
	        
	        
	        /*TEST SUR UNIQUEMENT UN echantiant de 10 images*/
	        
	    	/*#############""TESSTTTT SUR L'ENSEMBLE DES DONNE##########*/
	        //ConnexionDB connexionDB = new ConnexionDB();
	        
	        long startTimetT6 = System.currentTimeMillis();
	        ArrayList<Multimedia> images_10=  connexionDB.createMultimedia10();
	        long endTimetT6 = System.currentTimeMillis();
	        
	        traitement = new Traitement(images_10);
	        
	        long executionBDD_10 = endTimetT6 - startTimetT6 ;
	        System.out.print("Le temps d'execution de la requete select : "+executionBDD_10+ " ms");

	        
	        
	    	// TESTS SUR CONSOLE DES REQUETES 
	      /*  TEST 1 ---------------------------------------------------------   */  
	        
	        // test similarite par caracteristiques 
	        /*parametre*/
	      
	        
	        // recuperation dans la array list les images similaire a l'image donnee 
	        // Calcul du temps d'execution
	        long startTimeT1_10 = System.currentTimeMillis();
	        ArrayList<Multimedia> similarImagesCaracterstics_10= traitement.getSimilarImagesByCaracterstics(image,poidTexture, poidRGB, isEuc );
	        
	        
	        // Affichage des images dans la console en parcourant la liste 
	        for (Multimedia multimedia : similarImagesCaracterstics_10) {
	             System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
	             }
	        long endTimeT1_10 = System.currentTimeMillis();
	        
	        long executionTimeByCaracteristics_10 = endTimeT1_10 - startTimeT1_10;
	        // Affichage du temps d'execution
	        System.out.println("Le temps d'execution de la requete  : similarite par caracteristiques est "+
	        		executionTimeByCaracteristics_10+" ms");
	        
	        
	        
	        
	        
	        
	        /*  TEST 2  ---------------------------------------------------------   */ 
	        
	        
	        
	        // test similarite par signatures entre une image et le reste 
	          long startTimeT2_10 = System.currentTimeMillis();
	          ArrayList<Multimedia> similarImagesBySignature_10= connexionDB.getSimilarBySignature10(5,1,0,0,0);
	          
	          
	          // Affichage des images en question 
	          for (Multimedia multimedia : similarImagesBySignature_10) {
	               System.out.println("Image similaire a l'image "+IdImage+": "+multimedia.getID()+" distance:"+multimedia.getDistance());
	               }
	          long endTimeT2_10 = System.currentTimeMillis();
	            
	          long executionTimeBySignatures_10 = endTimeT2_10 - startTimeT2_10; 
	           // Affichage du temps d'execution 
	          System.out.println("Le temps d'execution de la requete  : similarite par signatures est "+
	           executionTimeBySignatures_10+" ms");
	     
	                  
	                  
	                  
	                  
	         /*  TEST 3  ---------------------------------------------------------   */ 
	                  
	          
	         // test similarite par signatures  entre deux images 
	          long startTimeT3_10 = System.currentTimeMillis();
	          isSimilar  = connexionDB.isSimilarBySignature(1,5,1,0,0,0);
	          
	          
	          // Affichage des resultats 
	          System.out.println("l'image 1 et 60 sont similaires ? : "+isSimilar);
	          long endTimeT3_10 = System.currentTimeMillis();
	          long executionTimeDeuxImages_10 = endTimeT3_10 - startTimeT3_10; 
	          // Affichage du temps d'execution 
	          System.out.println("Le temps d'execution de la requete  : similarite entre deux images est  "+
	          executionTimeDeuxImages_10+" ms");
	        
	          
	          
	          
	          
	          /*  TEST 4 ---------------------------------------------------------   */       
	    	
	          //  TEST peu de vert Beaucoup de rouge 
	          	long startTimeT4_10 = System.currentTimeMillis();
		        ArrayList<Multimedia> peuVertBcpRouge_10= traitement.getByColeur(45,25);
		       
		        // Affichage des resultats 
		        for (Multimedia multimedia : peuVertBcpRouge_10) {
		             System.out.println("Image peuVertBcpRouge "+multimedia.getID()+" distance:"+multimedia.getDistance());
		         }
		        long endTimeT4_10 = System.currentTimeMillis();
		        
		        long executionTimeRV_10 = endTimeT4_10 - startTimeT4_10; 
		          // Affichage du temps d'execution 
		        System.out.println("Le temps d'execution de la requete  : peu de vert beaucoup de rouge est "+
		          executionTimeRV_10+" ms");
	         


			/*  TEST 5 ---------------------------------------------------------   */    
	          
	        // Test avoir images noir et blanc
		        long startTimeT5_10 = System.currentTimeMillis();
		        nbImages =  traitement.getNbImages();
		        
		        //Affichage des resultats 
	            for (Multimedia multimedia : nbImages) {
	            	System.out.println("image noir et blanc:" + multimedia.getID());
	            	}
	            long endTimeT5_10 = System.currentTimeMillis();
	            long executionTimeNB_10 = endTimeT5_10 - startTimeT5_10; 
		        // Affichage du temps d'execution 
		        System.out.println("Le temps d'execution de la requete  : Les images noir et blanc est "+ 
		        executionTimeNB_10+" ms");

    }
    
}