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
    private JLabel imageLabel1; // Étiquette pour afficher l'image 1
    private JLabel imageLabel2; // Étiquette pour afficher l'image 2
    private JComboBox<String> imageComboBox1; // Liste déroulante pour les noms de fichiers de l'image 1
    private JComboBox<String> imageComboBox2; // Liste déroulante pour les noms de fichiers de l'image 2
    private JComboBox<String> choiceComboBox; // Liste déroulante pour les choix
    private JPanel imageDisplayPanel; // Panel pour afficher les images sélectionnées
    private JScrollPane imageScrollPane; // JScrollPane pour la plateforme d'affichage d'images

    public InterfaceIndexation() throws SQLException {
        connexionDB = new ConnexionDB();
        all_images=  connexionDB.createMultimedia();
        traitement = new Traitement(all_images);


        // Créer une nouvelle fenêtre Swing
        JFrame frame = new JFrame("Ma Superbe Application Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Créer un conteneur pour organiser les composants avec un gestionnaire de mise en page BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel pour les choix et les labels d'image
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(Color.lightGray);

        // Créer un label pour le choix
        JLabel choiceLabel = new JLabel("Choisissez un élément:");
        topPanel.add(choiceLabel);

        // Créer une liste déroulante pour les choix
        String[] choix = {"Afficher toute les images",
                "Comparer les deux images selon leurs signatures ",
                "Comparer les deux images selon leurs caractéristiques",
                "Afficher les images contenant peu de vert et beaucoup de rouge",
                "Afficher les images noir et blanc",
                "Afficher les images texturées"};
        choiceComboBox = new JComboBox<>(choix);
        topPanel.add(choiceComboBox);

        // Ajouter le panel des choix en haut de la fenêtre
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel pour les images et les listes déroulantes
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setBackground(Color.white);

        // Créer un label pour l'image 1
        JLabel label1 = new JLabel("Image 1:");
        imagePanel.add(label1);

        // Créer une liste déroulante pour les noms de fichiers (de 1.jpg à 500.jpg) pour l'image 1
        List<String> imageNames = new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            imageNames.add(i + ".jpg");
        }
        imageComboBox1 = new JComboBox<>(imageNames.toArray(new String[0]));
        imagePanel.add(imageComboBox1);

        // Créer un label pour l'image 2
        JLabel label2 = new JLabel("Image 2:");
        imagePanel.add(label2);

        // Créer une liste déroulante pour les noms de fichiers (de 1.jpg à 500.jpg) pour l'image 2
        imageComboBox2 = new JComboBox<>(imageNames.toArray(new String[0]));
        imagePanel.add(imageComboBox2);

        // Ajouter le panel des images et des listes déroulantes en bas de la fenêtre
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Panel pour afficher les images
        imageDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imageDisplayPanel.setBackground(Color.white);

        // Ajouter le panel d'affichage des images à un JScrollPane pour permettre le défilement
        imageScrollPane = new JScrollPane(imageDisplayPanel);
        imageScrollPane.setPreferredSize(new Dimension(750, 200)); // Taille du JScrollPane

        // Ajouter le JScrollPane en bas de la fenêtre
        mainPanel.add(imageScrollPane, BorderLayout.SOUTH);

        // Ajouter un écouteur de sélection à la liste déroulante de choix
        choiceComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le choix sélectionné
                String selectedChoice = (String) choiceComboBox.getSelectedItem();
                // Mettez en place la logique pour afficher les images en fonction du choix
                afficherImagesEnFonctionDuChoix(selectedChoice);
            }
        });
        // Ajouter un ActionListener à imageComboBox1
        imageComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le nom du fichier sélectionné dans imageComboBox1
                String selectedImage1 = (String) imageComboBox1.getSelectedItem();
                // Afficher l'image sélectionnée
                afficherImage(selectedImage1, imageDisplayPanel);
            }
        });

// Ajouter un ActionListener à imageComboBox2
        imageComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le nom du fichier sélectionné dans imageComboBox2
                String selectedImage2 = (String) imageComboBox2.getSelectedItem();
                // Afficher l'image sélectionnée
                afficherImage(selectedImage2, imageDisplayPanel);
            }
        });

        // Ajouter le conteneur principal à la fenêtre
        frame.add(mainPanel);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    // Méthode pour charger et afficher les images en fonction du choix
    private void afficherImagesEnFonctionDuChoix(String selectedChoice) {
        // Effacer les images précédentes
        imageDisplayPanel.removeAll();
        imageDisplayPanel.revalidate();
        imageDisplayPanel.repaint();

        if (selectedChoice.equals("Afficher toute les images")) {
            for (int i=0; i<500; i++) {
                // liste des noir et blanc
                String fileName = i + ".jpg";
                // Chargez l'image et ajoutez-la au panel
                afficherImage(fileName, imageDisplayPanel);
            }
        }


       if (selectedChoice.equals("Comparer les deux images selon leurs caractéristiques")) {
            ArrayList<Multimedia> similarImages= traitement.getSimilarImagesByCaracterstics(all_images.get(10));
            for (Multimedia image : similarImages) {
                // liste des noir et blanc
                String fileName = image.getID()+ ".jpg";
                // Chargez l'image et ajoutez-la au panel
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

    // Méthode pour charger et afficher l'image dans un panel donné
    private void afficherImage(String fileName, JPanel panel) {
        // Le chemin du dossier où se trouvent les images
        String dossierImages = "D:\\demo\\archive500";

        // Chemin complet du fichier image
        String cheminImage = dossierImages + File.separator + fileName;

        // Vérifier si le fichier image existe
        File imageFile = new File(cheminImage);
        if (imageFile.exists()) {
            // Charger l'image et l'afficher dans un JLabel
            ImageIcon imageIcon = new ImageIcon(cheminImage);
            JLabel imageLabel = new JLabel(imageIcon);
            panel.add(imageLabel);
        }
    }

}
