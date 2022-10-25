package model;

public class TipKupca {
	private String imeTipa;
	private double popust;
	private double brBodova;//trazeni broj bodova da bi korisnik postao npr zlatni kupac
	
	public TipKupca(String imeTipa, double popust, double brBodova) {
		super();
		this.imeTipa = imeTipa;
		this.popust = popust;
		this.brBodova = brBodova;
	}
	public String getImeTipa() {
		return imeTipa;
	}
	public void setImeTipa(String imeTipa) {
		this.imeTipa = imeTipa;
	}
	public double getPopust() {
		return popust;
	}
	public void setPopust(double popust) {
		this.popust = popust;
	}
	public double getBrBodova() {
		return brBodova;
	}
	public void setBrBodova(double brBodova) {
		this.brBodova = brBodova;
	}
	
	@Override
	public String toString() {
		return  imeTipa + "," + popust + "," + brBodova;
	}
	
	
}
