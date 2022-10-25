package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManifestacijaKarta {
	private String idManifestacije;
	private String naziv;
	private Lokacija lokacija;
	private TipManifestacije tipManifestacije;
	private double cenaKarte;
	private Timestamp datumOdrzavanja;
	private TipKarte tipKarte;
	private String idKarte;
	
	public ManifestacijaKarta() {
		
	}
	
	public ManifestacijaKarta(String idManifestacije, String naziv, Lokacija lokacija,
			TipManifestacije tipManifestacije, double cenaKarte, Timestamp datumOdrzavanja,TipKarte tipKarte,String idKarte) {
		super();
		this.idManifestacije = idManifestacije;
		this.naziv = naziv;
		this.lokacija = lokacija;
		this.tipManifestacije = tipManifestacije;
		this.cenaKarte = cenaKarte;
		this.datumOdrzavanja = datumOdrzavanja;
		this.tipKarte = tipKarte;
		this.idKarte = idKarte;
	}

	public String getIdManifestacije() {
		return idManifestacije;
	}

	public void setIdManifestacije(String idManifestacije) {
		this.idManifestacije = idManifestacije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public TipManifestacije getTipManifestacije() {
		return tipManifestacije;
	}

	public void setTipManifestacije(TipManifestacije tipManifestacije) {
		this.tipManifestacije = tipManifestacije;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public Timestamp getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Timestamp datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}
	
	public TipKarte getTipKarte() {
		return tipKarte;
	}

	public void setTipKarte(TipKarte tipKarte) {
		this.tipKarte = tipKarte;
	}

	public String getIdKarte() {
		return idKarte;
	}

	public void setIdKarte(String idKarte) {
		this.idKarte = idKarte;
	}

	public static ManifestacijaKarta parseString(String line) throws ParseException {
		String[] tokeni = line.split(";");
		String idManifestacije = tokeni[0];
		String naziv = tokeni[1];
		String[] lokacije = tokeni[2].split(",");
		Lokacija lokacija = new Lokacija(Double.parseDouble(lokacije[0]),Double.parseDouble(lokacije[1]), lokacije[2], Integer.parseInt(lokacije[3]), lokacije[4], lokacije[5], Integer.parseInt(lokacije[6])); 
		TipManifestacije tipManifestacije = TipManifestacije.valueOf(tokeni[3]);
		double cenaKarte = Double.parseDouble(tokeni[4]);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date parsedDate = dateFormat.parse(tokeni[5]);
		Timestamp datumOdrzavanja = new Timestamp(parsedDate.getTime());
		TipKarte tipKarte = TipKarte.valueOf(tokeni[6]);
		String idKarte = tokeni[7];
		return new ManifestacijaKarta(idManifestacije,naziv,lokacija,tipManifestacije,cenaKarte,datumOdrzavanja,tipKarte,idKarte);
	}
	
	public static String stringToFile(ManifestacijaKarta mk) {
		Lokacija l = mk.getLokacija();
		String lokacija = l.getGeoDuzina() + "," + l.getGeoSirina() + "," + l.getUlica() + "," + l.getBroj() + "," + l.getMesto() + "," + l.getGrad() + "," + l.getPostanskiBroj();
		return mk.getIdManifestacije() + ";" + mk.getNaziv() + ";" + lokacija + ";" + mk.getTipManifestacije()
		+ ";" + mk.getCenaKarte() + ";" + mk.getDatumOdrzavanja() + ";" + mk.getTipKarte() + ";" + mk.getIdKarte();
		
	}
}
