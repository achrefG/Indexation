package moteur;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import multimedia.Multimedia;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

/**
 * Ajouter en commentaire la description de la classe.
 *
 * @version $Revision: $ - $Date: $
 * @author $LastChangedBy: $
 * <pre>
 * $URL: $
 * </pre>
 *
 */
public class MainWindow {

	private JFrame frame;
    ConnexionDB connexionDB ;
    ArrayList<Multimedia> all_images;
    Traitement traitement ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public MainWindow() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
        List<String> imageNames = new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            imageNames.add(i + ".jpg");
        }
        
         connexionDB = new ConnexionDB();
    	 all_images=  connexionDB.createMultimedia();
    	 traitement = new Traitement(all_images);
        
        
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choisissier une requete");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(460, 36, 186, 71);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Afficher toutes les images", "Comparer deux images selon leur signature", "Afficher les images similaire a une image selon signature", "Afficher les images similaire a une image selon caractéristique", "Afficher les images avec peu de vert et beaucoup de rouge","Afficher les images noir & blanc","Afficher la plus grande & la plus petite image"}));
		comboBox.setBounds(732, 61, 508, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Séléctionner une image");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(460, 125, 155, 48);
		frame.getContentPane().add(lblNewLabel_1);
		String[] images_name1 = imageNames.toArray(new String[0]);
		JComboBox comboBox_images1 = new JComboBox(images_name1);

		comboBox_images1.setBounds(732, 140, 101, 21);
		frame.getContentPane().add(comboBox_images1);
		
		JButton btnNewButton = new JButton("Valider la requette");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));

		btnNewButton.setBounds(685, 321, 155, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Séléctionner la deuxieme image");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(928, 125, 191, 48);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JComboBox comboBox_images2 = new JComboBox(images_name1);
		
		comboBox_images2.setBounds(1139, 140, 101, 21);
		frame.getContentPane().add(comboBox_images2);
		
		JPanel imageDisplayPanel = new JPanel();
		imageDisplayPanel.setBackground(Color.WHITE);
		imageDisplayPanel.setBounds(54, 395, 909, 151);
		
		
		JScrollPane scrollPane = new JScrollPane(imageDisplayPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(500, 400);
		scrollPane.setSize(900, 350);
		imageDisplayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
		//imageDisplayPanel.setLayout(new GridLayout(0,2));
		frame.getContentPane().add(scrollPane);
		
		JPanel ImageSelectionnerPanel = new JPanel();
		ImageSelectionnerPanel.setBackground(Color.WHITE);
		ImageSelectionnerPanel.setBounds(30, 400, 449, 350);
		frame.getContentPane().add(ImageSelectionnerPanel);
		
		JLabel lblNewLabel_3 = new JLabel("Image séléctionner");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(178, 369, 198, 21);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Résultats");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3_1.setBounds(901, 369, 101, 21);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JPanel PanelSignature = new JPanel();
		PanelSignature.setBounds(257, 171, 767, 50);
		frame.getContentPane().add(PanelSignature);
		PanelSignature.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Couleur");
		PanelSignature.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldCouleur = new TextField();
		PanelSignature.add(textFieldCouleur);
		
		JLabel lblNewLabel_2_2 = new JLabel("texture");
		PanelSignature.add(lblNewLabel_2_2);
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldTexture = new TextField();
		PanelSignature.add(textFieldTexture);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Forme");
		PanelSignature.add(lblNewLabel_2_2_1);
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldForme = new TextField();
		PanelSignature.add(textFieldForme);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Emplacement");
		PanelSignature.add(lblNewLabel_2_2_2);
		lblNewLabel_2_2_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldEmplacement = new TextField();
		PanelSignature.add(textFieldEmplacement);
		
		JPanel PanelCaracteristique = new JPanel();
		PanelCaracteristique.setBounds(138, 231, 1025, 33);
		frame.getContentPane().add(PanelCaracteristique);
		PanelCaracteristique.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2_1 = new JLabel("Poid texture");
		PanelCaracteristique.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldPoidsTexture = new TextField();
		PanelCaracteristique.add(textFieldPoidsTexture);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Poid couleur");
		PanelCaracteristique.add(lblNewLabel_2_1_2);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldPoidsCouleur = new TextField();
		PanelCaracteristique.add(textFieldPoidsCouleur);
		
		JLabel lblNewLabel_2_ = new JLabel("Méthode de calcul de distance entre histogrammes");
		PanelCaracteristique.add(lblNewLabel_2_);
		lblNewLabel_2_.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JComboBox comboBox_distanceMethode = new JComboBox();
		PanelCaracteristique.add(comboBox_distanceMethode);
		comboBox_distanceMethode.setModel(new DefaultComboBoxModel(new String[] {"Distance de Bhattacharya", "Distance Euclédienne"}));
		
		JPanel PanelTauxRV = new JPanel();
		PanelTauxRV.setBounds(508, 274, 379, 37);
		frame.getContentPane().add(PanelTauxRV);
		PanelTauxRV.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("taux rouge");
		PanelTauxRV.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldTauxRouge = new TextField();
		PanelTauxRV.add(textFieldTauxRouge);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("taux vert");
		PanelTauxRV.add(lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		TextField textFieldTauxVert = new TextField();
		PanelTauxRV.add(textFieldTauxVert);
		
		
		PanelSignature.setVisible(false);
		PanelCaracteristique.setVisible(false);
		PanelTauxRV.setVisible(false);
		comboBox_images1.setVisible(false);
		comboBox_images2.setVisible(false);
		lblNewLabel_1_1.setVisible(false);
		lblNewLabel_1.setVisible(false);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Afficher toutes les images")) {
					comboBox_images1.setVisible(false);
					lblNewLabel_1.setVisible(false);
					PanelSignature.setVisible(false);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}else if (comboBox.getSelectedItem().equals("Afficher les images similaire a une image selon signature")) {
					comboBox_images1.setVisible(true);
					lblNewLabel_1.setVisible(true);
					PanelSignature.setVisible(true);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}else if (comboBox.getSelectedItem().equals("Comparer deux images selon leur signature")) {
					comboBox_images1.setVisible(true);
					lblNewLabel_1.setVisible(true);
					PanelSignature.setVisible(true);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(true);
					lblNewLabel_1_1.setVisible(true);
				}else if (comboBox.getSelectedItem().equals("Afficher les images similaire a une image selon caractéristique")) {
					comboBox_images1.setVisible(true);
					lblNewLabel_1.setVisible(true);
					PanelSignature.setVisible(false);
					PanelCaracteristique.setVisible(true);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}else if (comboBox.getSelectedItem().equals("Afficher les images avec peu de vert et beaucoup de rouge")) {
					comboBox_images1.setVisible(false);
					lblNewLabel_1.setVisible(false);
					PanelSignature.setVisible(false);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(true);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}else if (comboBox.getSelectedItem().equals("Afficher les images noir & blanc")) {
					comboBox_images1.setVisible(false);
					lblNewLabel_1.setVisible(false);
					PanelSignature.setVisible(false);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}else if (comboBox.getSelectedItem().equals("Afficher la plus grande & la plus petite image")) {
					comboBox_images1.setVisible(false);
					lblNewLabel_1.setVisible(false);
					PanelSignature.setVisible(false);
					PanelCaracteristique.setVisible(false);
					PanelTauxRV.setVisible(false);
					comboBox_images2.setVisible(false);
					lblNewLabel_1_1.setVisible(false);
				}
				
			}
		});
		
		/**TRAITEMENT**/
		comboBox_images1.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				String fileName = (String) comboBox_images1.getSelectedItem();
				ImageSelectionnerPanel.removeAll();
				ImageSelectionnerPanel.revalidate();
				ImageSelectionnerPanel.repaint();
				afficherImage(fileName, ImageSelectionnerPanel);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        imageDisplayPanel.removeAll();
		        imageDisplayPanel.revalidate();
		        imageDisplayPanel.repaint();
		        
                String selectedImage1 = (String) comboBox_images1.getSelectedItem();
                String imageNumber = selectedImage1.replace(".jpg", "");
                int imageIndex1 = Integer.parseInt(imageNumber);
		        
                String selectedImage2 = (String) comboBox_images2.getSelectedItem();
                String imageNumber2 = selectedImage2.replace(".jpg", "");
                int imageIndex2 = Integer.parseInt(imageNumber2);
                
				String requete = (String)comboBox.getSelectedItem() ;
					if (requete.equals("Afficher toutes les images")) {
						System.out.println("Affichage debut");
			            for (int i=1; i<501; i++) {
			                String fileName = i + ".jpg";
			                afficherImage(fileName, imageDisplayPanel);
			            }
			            System.out.println("Affichage fin");

					}
					else if (requete.equals("Afficher les images similaire a une image selon signature")) {
						
				           String stringCouleur = textFieldCouleur.getText();
				           String stringTexture = textFieldTexture.getText();
				           String stringShape = textFieldForme.getText();
				           String stringEmplacement = textFieldEmplacement.getText();
				           
				           float intCouleur = Float.parseFloat(stringCouleur);
				           float intTexture= Float.parseFloat(stringTexture);
				           float intShape = Float.parseFloat(stringShape);
				           float intEmplacement = Float.parseFloat(stringEmplacement);
				    	  ArrayList<Multimedia> similarImages;
						try {
							similarImages = connexionDB.getSimilarBySignature(imageIndex1,intCouleur,intTexture,intShape,intEmplacement);
				            for (Multimedia image : similarImages) {
				                String fileName = image.getID()+ ".jpg";
				                afficherImageWithDistance(fileName, image.getDistance()+"",imageDisplayPanel);
				            }
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			
						
						
					}
					else if (requete.equals("Comparer deux images selon leur signature")) {
						
				           String stringCouleur = textFieldCouleur.getText();
				           String stringTexture = textFieldTexture.getText();
				           String stringShape = textFieldForme.getText();
				           String stringEmplacement = textFieldEmplacement.getText();
				           
				           float intCouleur = Float.parseFloat(stringCouleur);
				           float intTexture= Float.parseFloat(stringTexture);
				           float intShape = Float.parseFloat(stringShape);
				           float intEmplacement = Float.parseFloat(stringEmplacement);
				           
							try {
								afficherImage(selectedImage2, imageDisplayPanel);
								boolean isSimilair = connexionDB.isSimilarBySignature(imageIndex1, imageIndex2, intCouleur, intTexture, intShape, intEmplacement);
								
								if(isSimilair) {
									JOptionPane.showMessageDialog(frame, "les images "+imageIndex1+" et "+imageIndex2 +" sont similaires");
								}else {
									JOptionPane.showMessageDialog(frame, "les images "+imageIndex1+" et "+imageIndex2 +" sont pas similaires");
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				           
						
					}
					else if (requete.equals("Afficher les images similaire a une image selon caractéristique")) {
						
				           String stringCouleur = textFieldPoidsCouleur.getText();
				           String stringTexture = textFieldPoidsTexture.getText();
				           int item =( comboBox_distanceMethode.getSelectedIndex()) ;
				          
				           boolean intEstBat = true ;
				           int intCouleur = Integer.parseInt(stringCouleur);
				           int intTexture= Integer.parseInt(stringTexture);
				           if(item ==0) intEstBat = false;
				           //Boolean.parseBoolean(stringEstBat);
				           
				    	  ArrayList<Multimedia> similarImages;
						
							similarImages = traitement.getSimilarImagesByCaracterstics(all_images.get(imageIndex1+1), intTexture, intCouleur, intEstBat);
				            for (Multimedia image : similarImages) {
				                String fileName = image.getID()+ ".jpg";
				                afficherImageWithDistance(fileName, image.getDistance()+"",imageDisplayPanel);
				            }
							
						
					}
					else if (requete.equals("Afficher les images avec peu de vert et beaucoup de rouge")) {
						
						
				           String stringSeuilMaxVert = textFieldTauxVert.getText();
				           String stringSeuilMinRouge = textFieldTauxRouge.getText();
				          
				          
				           
				           int intSeuilMaxVert = Integer.parseInt(stringSeuilMaxVert);
				           int intSeuilMinRouge= Integer.parseInt(stringSeuilMinRouge);
				         
				           
				    	  ArrayList<Multimedia> similarImages;
						
							similarImages = traitement.getByColeur(intSeuilMinRouge, intSeuilMaxVert);
				            for (Multimedia image : similarImages) {
				                String fileName = image.getID()+ ".jpg";
				                afficherImage(fileName, imageDisplayPanel);
				            }
					}
					else if (requete.equals("Afficher les images noir & blanc")) {

						
				           
				    	  ArrayList<Multimedia> similarImages;
						
							similarImages = traitement.getNbImages();
				            for (Multimedia image : similarImages) {
				                String fileName = image.getID()+ ".jpg";
				                afficherImage(fileName, imageDisplayPanel);
				            }
					}
					else if (requete.equals("Afficher la plus grande & la plus petite image")) {
						
				           
				    	ArrayList<Multimedia> similarImages;
						
						similarImages = traitement.getDimension();
				            for (Multimedia image : similarImages) {
				                String fileName = image.getID()+ ".jpg";
				                afficherImage(fileName,imageDisplayPanel);
				            }
					}

			}
		});
	}
    private void afficherImageWithDistance(String fileName,String distance, JPanel panel) {
        // Le chemin du dossier oÃ¹ se trouvent les images
        String dossierImages = "C:\\Users\\achref.ghezil\\Downloads\\archive500";

        // Chemin complet du fichier image
        String cheminImage = dossierImages + File.separator + fileName;
        
        // VÃ©rifier si le fichier image existe
        File imageFile = new File(cheminImage);
        if (imageFile.exists()) {
            // Charger l'image et l'afficher dans un JLabel
            ImageIcon imageIcon = new ImageIcon(cheminImage);
            JLabel imageLabel = new JLabel(fileName+"\n"+" distance:"+distance);
            JLabel image = new JLabel(imageIcon);
            //imageLabel.setBounds(0,0, 200, 200);
            panel.add(imageLabel);
            panel.add(image);
        }
        
    }
    private void afficherImage(String fileName, JPanel panel) {
        // Le chemin du dossier oÃ¹ se trouvent les images
        String dossierImages = "C:\\Users\\achref.ghezil\\Downloads\\archive500";

        // Chemin complet du fichier image
        String cheminImage = dossierImages + File.separator + fileName;
        
        // VÃ©rifier si le fichier image existe
        File imageFile = new File(cheminImage);
        if (imageFile.exists()) {
            // Charger l'image et l'afficher dans un JLabel
            ImageIcon imageIcon = new ImageIcon(cheminImage);
            JLabel imageLabel = new JLabel(fileName);
            JLabel image = new JLabel(imageIcon);
            //imageLabel.setBounds(0,0, 200, 200);
            panel.add(imageLabel);
            panel.add(image);
        }
        
    }
}
