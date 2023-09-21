package multimedia;

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
public class Multimedia {
	private int id;
	private double moynormegradiant;
	private Histogramme histogramme ;
	private boolean isNb ;
	private int distance ;//taux de resemblance 
	public Multimedia(int id, double moynormegradiant, Histogramme histogramme, boolean isNb) {
		super();
		this.id = id;
		this.moynormegradiant = moynormegradiant;
		this.histogramme = histogramme;
		this.isNb = isNb;
		this.distance = 0;
	}
	public Multimedia(int id, int distance) {
		super();
		this.id = id;
		this.moynormegradiant = 0;
		this.histogramme = null;
		this.isNb = false;
		this.distance = distance;
	}
	/**
	 * @return the id
	 */
	public int getID() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * @return the moynormegradiant
	 */
	public double getMoynormegradiant() {
		return moynormegradiant;
	}
	/**
	 * @param moynormegradiant the moynormegradiant to set
	 */
	public void setMoynormegradiant(double moynormegradiant) {
		this.moynormegradiant = moynormegradiant;
	}
	/**
	 * @return the histogramme
	 */
	public Histogramme getHistogramme() {
		return histogramme;
	}
	/**
	 * @param histogramme the histogramme to set
	 */
	public void setHistogramme(Histogramme histogramme) {
		this.histogramme = histogramme;
	}
	/**
	 * @return the isNb
	 */
	public boolean isNb() {
		return isNb;
	}
	/**
	 * @param isNb the isNb to set
	 */
	public void setNb(boolean isNb) {
		this.isNb = isNb;
	}
	/**
	 * @param id
	 * @param moynormegradiant
	 * @param histogramme
	 * @param isNb
	 */
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	


}
