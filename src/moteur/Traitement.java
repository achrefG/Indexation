package moteur;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import multimedia.Histogramme;
import multimedia.Multimedia;

/**
 * Ajouter en commentaire la description de la classe.
 *
 * @version $Revision: $ - $Date: $
 * @author $LastChangedBy: agh$
 * <pre>
 * $URL: $
 * </pre>
 *
 */
public class Traitement {
	
	/**
	 * @param images_all
	 */
	public Traitement(ArrayList<Multimedia> images_all) {
		super();
		this.images_all = images_all;
	}
	
	private ArrayList<Multimedia> images_all ;
	
	
	
	/**
	 * @return the images_all
	 */
	public ArrayList<Multimedia> getImages_all() {
		return images_all;
	}

	/**
	 * @param images_all the images_all to set
	 */
	public void setImages_all(ArrayList<Multimedia> images_all) {
		this.images_all = images_all;
	}

	public ArrayList<Multimedia> getNbImages() {
		ArrayList<Multimedia> nB_images = new ArrayList<Multimedia>() ;
		for (Multimedia image: images_all) {
			if(image.isNb()) {
				nB_images.add(image);
			}
		}
		return nB_images;
	};
	
	public ArrayList<Multimedia> getSimilarImagesByCaracterstics(Multimedia image_input){
		ArrayList<Multimedia> SimilarImages = new ArrayList<Multimedia>();
		for (Multimedia image: images_all) {
			double battachariaDistanceRed = getBattachariaDistance(image_input.getHistogramme().getRed_histogramme(),image.getHistogramme().getRed_histogramme());
			double battachariaDistanceGreen = getBattachariaDistance(image_input.getHistogramme().getGreen_histogramme(),image.getHistogramme().getGreen_histogramme());
			double battachariaDistanceBlue = getBattachariaDistance(image_input.getHistogramme().getBlue_histogramme(),image.getHistogramme().getBlue_histogramme());
			
			double battachariaDistance = ((battachariaDistanceRed + battachariaDistanceGreen+ battachariaDistanceBlue )/3)*1000;
			int battachariaDistanceInt =(int) Math.round(battachariaDistance);
		
			image.setDistance(battachariaDistanceInt);
			
			SimilarImages.add(image);
		}
        // Triez SimilarImages en fonction de la distance.
        Collections.sort(SimilarImages, new Comparator<Multimedia>() {
            @Override
            public int compare(Multimedia m1, Multimedia m2) {
                // Triez par ordre croissant de distance.
                return Integer.compare(m1.getDistance(), m2.getDistance());
            }
        });

		return SimilarImages ;
	}
	
	public double getBattachariaDistance(int[] histogramme_1, int[] histogramme_2 ) {
//		double BhattacharyyaCoefficient=0.0;
		int size = 256;
		int sum1 = 0;
		int sum2 = 0;
		
		BigDecimal[] hist1_norm = new BigDecimal[size];
		BigDecimal[] hist2_norm = new BigDecimal[size];
		
		
		// Calcul des sommes des valeurs dans hist1 et hist2
		for (int i = 0; i < size; i++) {
			sum1 += histogramme_1[i];
			sum2 += histogramme_2[i];	
		}
		
		// Normalisation des histogrammes
		for (int i = 0; i < size; i++) {
			BigDecimal bigSum1 = new BigDecimal(sum1);
			BigDecimal bigSum2 = new BigDecimal(sum2);
			BigDecimal hist_1_i= new BigDecimal(histogramme_1[i]);
			BigDecimal hist_2_i= new BigDecimal(histogramme_2[i]);
			
			hist1_norm[i] = hist_1_i.divide(bigSum1,MathContext.DECIMAL128);
			hist2_norm[i] = hist_2_i.divide(bigSum2,MathContext.DECIMAL128);

		}
		BigDecimal bcCoeff = calculateBhattacharyyaCoefficient(hist1_norm, hist2_norm);
		
		
		double bcCoeffDouble = bcCoeff.doubleValue() ;
		
		double bcDistance = -(Math.log(bcCoeffDouble));

        //System.out.println("Distance de Bhattacharyya : " + bcDistance);
		
        return bcDistance;		
	};
    public static BigDecimal calculateBhattacharyyaCoefficient(BigDecimal[] p1, BigDecimal[] p2) {
    	BigDecimal bcCoeff = new BigDecimal(0);
        for (int i = 0; i < 256; i++) {
        	BigDecimal bc = new BigDecimal(0);
        	bc = p1[i].multiply(p2[i],MathContext.DECIMAL128);
        	bc = bc.sqrt(MathContext.DECIMAL128);
        	bcCoeff = bcCoeff.add(bc,MathContext.DECIMAL128);
        	
        }
        return bcCoeff;
    }
}


