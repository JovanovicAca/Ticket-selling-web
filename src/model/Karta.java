package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Karta {
	private String id;
	private String manifestacija;
	private Timestamp datumManifestacije;
	private double cena;
	private String imeKupca;
	private StatusKarte statusKarte;
	private TipKarte tipKarte;
	private int obrisan;//0-nije,1-jeste
	
	public Karta() {}
	
	public Karta(String id, String manifestacija, Timestamp datumManifestacije, double cena, String imeKupca,
			StatusKarte statusKarte, TipKarte tipKarte,int obrisan) {
		super();
		this.id = id;
		this.manifestacija = manifestacija;
		this.datumManifestacije = datumManifestacije;
		this.cena = cena;
		this.imeKupca = imeKupca;
		this.statusKarte = statusKarte;
		this.tipKarte = tipKarte;
		this.obrisan = obrisan;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManifestacija() {
		return manifestacija;
	}
	public void setManifestacija(String manifestacija) {
		this.manifestacija = manifestacija;
	}
	public Timestamp getDatumManifestacije() {
		return datumManifestacije;
	}
	public void setDatumManifestacije(Timestamp datumManifestacije) {
		this.datumManifestacije = datumManifestacije;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getImeKupca() {
		return imeKupca;
	}
	public void setImeKupca(String imeKupca) {
		this.imeKupca = imeKupca;
	}
	public StatusKarte getStatusKarte() {
		return statusKarte;
	}
	public void setStatusKarte(StatusKarte statusKarte) {
		this.statusKarte = statusKarte;
	}
	public TipKarte getTipKarte() {
		return tipKarte;
	}
	public void setTipKarte(TipKarte tipKarte) {
		this.tipKarte = tipKarte;
	}
	
	public int getObrisan() {
		return obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}

	//citanje iz fajlai pravljenje objekta
	public static Karta parseString(String line) throws ParseException {
		String[] tokeni = line.split(";");
		String id = tokeni[0];
		String manifestacija = tokeni[1];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date parsedDate = dateFormat.parse(tokeni[2]);
		Timestamp datumManifestacije = new Timestamp(parsedDate.getTime());
		double cena = Double.parseDouble(tokeni[3]);
		String imeKupca = tokeni[4];
		StatusKarte statusKarte = StatusKarte.valueOf(tokeni[5]);
		TipKarte tipKarte = TipKarte.valueOf(tokeni[6]);
		int obrisan = Integer.parseInt(tokeni[7]);
		
		return new Karta(id, manifestacija, datumManifestacije, cena, imeKupca, statusKarte, tipKarte,obrisan);
	}
	
	//string za fajl
	public static String toFileString(Karta k) {
		
		return k.getId() + ";" + k.getManifestacija() + ";" + k.getDatumManifestacije() + ";" + k.getCena() + ";" + k.getImeKupca() + ";" + k.getStatusKarte() + ";" + k.getTipKarte() + ";" + k.getObrisan();
	}
}
