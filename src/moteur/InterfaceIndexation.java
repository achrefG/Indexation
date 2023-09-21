package moteur;

import multimedia.Multimedia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterfaceIndexation {
    ConnexionDB connexionDB ;
    ArrayList<Multimedia> all_images;
    Traitement traitement ;
    private JLabel imageLabel1; // Ã‰tiquette pour afficher l'image 1
    private JLabel imageLabel2; // Ã‰tiquette pour afficher l'image 2
 
    private JLabel caracteristiquesHisto; 
    private JComboBox<String> imageComboBox1; // Liste dÃ©roulante pour les noms de fichiers de l'image 1
    private JComboBox<String> imageComboBox2; // Liste dÃ©roulante pour les noms de fichiers de l'image 2
    private JComboBox<String> choiceComboBox; // Liste dÃ©roulante pour les choix
    private JPanel imageDisplayPanel; // Panel pour afficher les images sÃ©lectionnÃ©es
    private JScrollPane imageScrollPane; // JScrollPane pour la plateforme d'affichage d'images
    private int imageIndex;
    private int caracteristiqueHisto1; 
    private int caracteristiqueHisto2; 
    private int caracteristiqueHisto3; 
    private int caracteristiqueHisto4; 
    JTextField zoneTexte1 ;
    JTextField zoneTexte2 ;
    JTextField zoneTexte3 ;
    JTextField zoneTexte4 ;

    public InterfaceIndexation() throws SQLException {
        connexionDB = new ConnexionDB();
        all_images=  connexionDB.createMultimedia();
        traitement = new Traitement(all_images);


        // CrÃ©er une nouvelle fenÃªtre Swing
        JFrame frame = new JFrame("Ma Superbe Application Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);

        // CrÃ©er un conteneur pour organiser les composants avec un gestionnaire de mise en page BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

        // Panel pour les choix et les labels d'image
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.lightGray);

        // CrÃ©er un label pour le choix
        JLabel choiceLabel = new JLabel("Choisissez un element:");
        topPanel.add(choiceLabel);

        // CrÃ©er une liste dÃ©roulante pour les choix
        String[] choix = {"Vider la table des images",
        		"Afficher toute les images",
        		"Comparer deux images selon leurs signatures",
                "Comparer une image selon les signatures ",
                "Comparer deux images selon leurs caracteristiques",
                "Comparer une image selon les caracteristiques",
                "Afficher les images contenant peu de vert et beaucoup de rouge",
                "Afficher les images noir et blanc",
                "Afficher les images textures"};
        choiceComboBox = new JComboBox<>(choix);
        topPanel.add(choiceComboBox);

        // Ajouter le panel des choix en haut de la fenÃªtre
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel pour les images et les listes dÃ©roulantes
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(Color.white);

        // CrÃ©er un label pour l'image 1
        JLabel label1 = new JLabel("Image 1:");
        imagePanel.add(label1);

        // CrÃ©er une liste dÃ©roulante pour les noms de fichiers (de 1.jpg Ã  500.jpg) pour l'image 1
        List<String> imageNames = new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            imageNames.add(i + ".jpg");
        }
        imageComboBox1 = new JComboBox<>(imageNames.toArray(new String[0]));
        imagePanel.add(imageComboBox1);

        // CrÃ©er un label pour l'image 2
        JLabel label2 = new JLabel("Image 2:");
        imagePanel.add(label2);

        // CrÃ©er une liste dÃ©roulante pour les noms de fichiers (de 1.jpg Ã  500.jpg) pour l'image 2
        imageComboBox2 = new JComboBox<>(imageNames.toArray(new String[0]));
        imagePanel.add(imageComboBox2);
        
        
        
        JLabel caracteristiqueHisto = new JLabel("Choisissez des caracteristiques de l image:");
        imagePanel.add(caracteristiqueHisto);
        
        zoneTexte1 = new JTextField("Entrez le degree de texture de l'image");
        zoneTexte1.setForeground(Color.GRAY); // Couleur du texte en gris
        imagePanel.add(zoneTexte1);

        zoneTexte2 = new JTextField("Entrez le degree de couleur de l'image");
        zoneTexte2.setForeground(Color.GRAY); // Couleur du texte en gris
        imagePanel.add(zoneTexte2);
        
        
        
        JLabel caracteristiqueImage = new JLabel("Choisissez des caracteristiques de l image:");
        imagePanel.add(caracteristiqueImage);
        
        zoneTexte3 = new JTextField("Entrez le degree de couleur");
        zoneTexte3.setForeground(Color.GRAY); // Couleur du texte en gris
        imagePanel.add(zoneTexte3);

        zoneTexte4 = new JTextField("Entrez le degree de texture ");
        zoneTexte4.setForeground(Color.GRAY); // Couleur du texte en gris
        imagePanel.add(zoneTexte4);

        
        // Ajouter le panel des images et des listes dÃ©roulantes en bas de la fenÃªtre
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Panel pour afficher les images
        imageDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageDisplayPanel.setBackground(Color.white);

        // Ajouter le panel d'affichage des images Ã  un JScrollPane pour permettre le dÃ©filement
        imageScrollPane = new JScrollPane(imageDisplayPanel);
        imageScrollPane.setPreferredSize(new Dimension(750, 200)); // Taille du JScrollPane

        // Ajouter le JScrollPane en bas de la fenÃªtre
        mainPanel.add(imageScrollPane, BorderLayout.SOUTH);

        // Ajouter un Ã©couteur de sÃ©lection Ã  la liste dÃ©roulante de choix
        choiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // RÃ©cupÃ©rer le choix sÃ©lectionnÃ©
                String selectedChoice = (String) choiceComboBox.getSelectedItem();
                // Mettez en place la logique pour afficher les images en fonction du choix
                afficherImagesEnFonctionDuChoix(selectedChoice);
            }
        });
        
        // Ajouter un ActionListener Ã  imageComboBox1
        imageComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // RÃ©cupÃ©rer le nom du fichier sÃ©lectionnÃ© dans imageComboBox1
                String selectedImage1 = (String) imageComboBox1.getSelectedItem();
                String imageNumber = selectedImage1.replace(".jpg", "");
                imageIndex = Integer.parseInt(imageNumber);

            }
        });

// Ajouter un ActionListener Ã  imageComboBox2
        imageComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // RÃ©cupÃ©rer le nom du fichier sÃ©lectionnÃ© dans imageComboBox2
                String selectedImage2 = (String) imageComboBox2.getSelectedItem();
                // Afficher l'image sÃ©lectionnÃ©e
                afficherImage(selectedImage2, imageDisplayPanel);
            }
        });
        
       
        // Ajouter le conteneur principal Ã  la fenÃªtre
        frame.add(mainPanel);

        // Rendre la fenÃªtre visible
        frame.setVisible(true);
    }

    // MÃ©thode pour charger et afficher les images en fonction du choix
    private void afficherImagesEnFonctionDuChoix(String selectedChoice) {
        // Effacer les images prÃ©cÃ©dentes
        imageDisplayPanel.removeAll();
        imageDisplayPanel.revalidate();
        imageDisplayPanel.repaint();
// AFFICHER TOUTE LES IMAGES 
        if (selectedChoice.equals("Afficher toute les images")) {
            for (int i=0; i<500; i++) {
                String fileName = i + ".jpg";
                afficherImage(fileName, imageDisplayPanel);
            }
        }
        
    // AFFICHER LA SIMILITUDE DE  IMAGES SELON LEUR SIGNATURES 
        if (selectedChoice.equals("Afficher toute les images")) {
        	String carac3 = "";
            String carac4 = "";
            // Récupérez le texte des zones de texte
            String texteZone3 = zoneTexte3.getText();
            String texteZone4 = zoneTexte4.getText();

            // Stockez le contenu dans les variables
            carac3= texteZone3;
            carac4 = texteZone4;
            caracteristiqueHisto3 = Integer.parseInt(carac3);
            caracteristiqueHisto4 = Integer.parseInt(carac4);
     	   
     	   ArrayList<Multimedia> similarImages = traitement.getSimilarImagesByCaracterstics(all_images.get(imageIndex)
     			   ,caracteristiqueHisto1,caracteristiqueHisto2);
             for (Multimedia image : similarImages) {
                 String fileName = image.getID()+ ".jpg";
                 afficherImage(fileName, imageDisplayPanel);
             }
        }



       if (selectedChoice.equals("Comparer une image selon les caracteristiques")) {
    	   
    	   String carac1 = "";
           String carac2 = "";
           // Récupérez le texte des zones de texte
           String texteZone1 = zoneTexte1.getText();
           String texteZone2 = zoneTexte2.getText();

           // Stockez le contenu dans les variables
           carac1 = texteZone1;
           carac2 = texteZone2;
           caracteristiqueHisto1 = Integer.parseInt(carac1);
           caracteristiqueHisto2 = Integer.parseInt(carac2);
    	   
    	   ArrayList<Multimedia> similarImages = traitement.getSimilarImagesByCaracterstics(all_images.get(imageIndex)
    			   ,caracteristiqueHisto1,caracteristiqueHisto2);
            for (Multimedia image : similarImages) {
                String fileName = image.getID()+ ".jpg";
                afficherImage(fileName, imageDisplayPanel);
            }
        }


        // Par exemple, vous pouvez charger et afficher toutes les images noir et blanc ici
        if (selectedChoice.equals("Afficher les images noir et blanc")) {
            ArrayList<Multimedia> nbImages =  traitement.getNbImages();
            for (Multimedia image : nbImages) {
                // liste des noir et blanc
                String fileName = image.getID()+ ".jpg";
                // Chargez l'image et ajoutez-la au panel
                afficherImage(fileName, imageDisplayPanel);
            }
        }
    }

    // MÃ©thode pour charger et afficher l'image dans un panel donnÃ©
    private void afficherImage(String fileName, JPanel panel) {
        // Le chemin du dossier oÃ¹ se trouvent les images
        String dossierImages = "D:\\demo\\archive500";

        // Chemin complet du fichier image
        String cheminImage = dossierImages + File.separator + fileName;

        // VÃ©rifier si le fichier image existe
        File imageFile = new File(cheminImage);
        if (imageFile.exists()) {
            // Charger l'image et l'afficher dans un JLabel
            ImageIcon imageIcon = new ImageIcon(cheminImage);
            JLabel imageLabel = new JLabel(imageIcon);
            panel.add(imageLabel);
        }
    }

}
