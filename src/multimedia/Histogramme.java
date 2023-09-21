package multimedia;

import java.util.ArrayList;

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
public class Histogramme {
	private int[] red_histogramme ;
	private int[] green_histogramme ;
	private int[] blue_histogramme ;
	/**
	 * @param hr
	 * @param hb
	 * @param hg
	 */
	public Histogramme(int[] hr, int[] hb, int[] hg) {
		super();
		this.red_histogramme = hr;
		this.blue_histogramme = hb;
		this.green_histogramme = hg;
	}
	
	/**
	 * @return the red_histogramme
	 */
	public int[] getRed_histogramme() {
		return red_histogramme;
	}
	/**
	 * @param red_histogramme the red_histogramme to set
	 */
	public void setRed_histogramme(int[] red_histogramme) {
		this.red_histogramme = red_histogramme;
	}
	/**
	 * @return the green_histogramme
	 */
	public int[] getGreen_histogramme() {
		return green_histogramme;
	}
	/**
	 * @param green_histogramme the green_histogramme to set
	 */
	public void setGreen_histogramme(int[] green_histogramme) {
		this.green_histogramme = green_histogramme;
	}
	/**
	 * @return the blue_histogramme
	 */
	public int[] getBlue_histogramme() {
		return blue_histogramme;
	}
	/**
	 * @param blue_histogramme the blue_histogramme to set
	 */
	public void setBlue_histogramme(int[] blue_histogramme) {
		this.blue_histogramme = blue_histogramme;
	}

}
