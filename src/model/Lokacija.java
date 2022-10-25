package model;

public class Lokacija {
	private double geoDuzina;
	private double geoSirina;
	private String ulica;
	private int broj;
	private String mesto;
	private String grad;
	private int postanskiBroj;
	
	public Lokacija() {}
	
	public Lokacija(double geoDuzina, double geoSirina, String ulica, int broj, String mesto, String grad,
			int postanskiBroj) {
		super();
		this.geoDuzina = geoDuzina;
		this.geoSirina = geoSirina;
		this.ulica = ulica;
		this.broj = broj;
		this.mesto = mesto;
		this.grad = grad;
		this.postanskiBroj = postanskiBroj;
	}
	
	public double getGeoDuzina() {
		return geoDuzina;
	}
	public void setGeoDuzina(double geoDuzina) {
		this.geoDuzina = geoDuzina;
	}
	public double getGeoSirina() {
		return geoSirina;
	}
	public void setGeoSirina(double geoSirina) {
		this.geoSirina = geoSirina;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public int getPostanskiBroj() {
		return postanskiBroj;
	}
	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
}
