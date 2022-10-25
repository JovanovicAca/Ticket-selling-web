package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manifestacija {
	private String id;
	private String naziv;
	private TipManifestacije tipManifestacije;
	private int brojMesta;
	private Timestamp datumOdrzavanja;
	private double cenaKarte;
	private StatusManifestacije statusManifestacije;
	private Lokacija lokacija;
	private String slika;
	private boolean rasprodata;
	private List<String> komentari = new ArrayList<String>();
	private int obrisana;//0 = nije, 1 = jeste
	
	public Manifestacija() {}

	public Manifestacija(String id,String naziv, TipManifestacije tipManifestacije, int brojMesta, Timestamp datumOdrzavanja,
			double cenaKarte, StatusManifestacije statusManifestacije, Lokacija lokacija, String slika, boolean rasprodata,
			List<String> komentari,int obrisana) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.tipManifestacije = tipManifestacije;
		this.brojMesta = brojMesta;
		this.datumOdrzavanja = datumOdrzavanja;
		this.cenaKarte = cenaKarte;
		this.statusManifestacije = statusManifestacije;
		this.lokacija = lokacija;
		this.slika = slika;
		this.rasprodata = rasprodata;
		this.komentari = komentari;
		this.obrisana = obrisana;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public TipManifestacije getTipManifestacije() {
		return tipManifestacije;
	}

	public void setTipManifestacije(TipManifestacije tipManifestacije) {
		this.tipManifestacije = tipManifestacije;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public Timestamp getDatumOdrzavanja() {
		return datumOdrzavanja;
	}

	public void setDatumOdrzavanja(Timestamp datumOdrzavanja) {
		this.datumOdrzavanja = datumOdrzavanja;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public StatusManifestacije getStatusKarte() {
		return statusManifestacije;
	}

	public void setStatusKarte(StatusManifestacije statusKarte) {
		this.statusManifestacije = statusKarte;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public boolean isRasprodata() {
		return rasprodata;
	}

	public void setRasprodata(boolean rasprodata) {
		this.rasprodata = rasprodata;
	}

	public StatusManifestacije getStatusManifestacije() {
		return statusManifestacije;
	}

	public void setStatusManifestacije(StatusManifestacije statusManifestacije) {
		this.statusManifestacije = statusManifestacije;
	}

	public List<String> getKomentari() {
		return komentari;
	}

	public void setKomentari(List<String> komentari) {
		this.komentari = komentari;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getObrisana() {
		return obrisana;
	}

	public void setObrisana(int obrisana) {
		this.obrisana = obrisana;
	}
	
	
	
	//za citanje iz fajla i kreiranje objekta
	public static Manifestacija parseString(String line) throws ParseException {
		String[] tokeni = line.split(";");
		String id = tokeni[0];
		String naziv = tokeni[1];
		TipManifestacije tipManifestacije = TipManifestacije.valueOf(tokeni[2]);
		int brojMesta = Integer.parseInt(tokeni[3]);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date parsedDate = dateFormat.parse(tokeni[4]);
		Timestamp datumOdrzavanja = new Timestamp(parsedDate.getTime());
		double cenaKarte = Double.parseDouble(tokeni[5]);
		StatusManifestacije statusManifestacije = StatusManifestacije.valueOf(tokeni[6]);
		
		String[] lokacije = tokeni[7].split(",");
		Lokacija lokacija = new Lokacija(Double.parseDouble(lokacije[0]),Double.parseDouble(lokacije[1]), lokacije[2], Integer.parseInt(lokacije[3]), lokacije[4], lokacije[5], Integer.parseInt(lokacije[6])); 
		
		String slika = tokeni[8];
		boolean raspodat = Boolean.valueOf(tokeni[9]);
		
		List<String> komentari = new ArrayList<String>();
		String[] komentar = tokeni[10].split(",");
		for (String string : komentar) {
			//sveKarte.add(string + ","); mozda ovako
			komentari.add(string);
		}
		
		int obrisana = Integer.parseInt(tokeni[11]);
		
		return new Manifestacija(id, naziv, tipManifestacije, brojMesta, datumOdrzavanja, cenaKarte, statusManifestacije, lokacija, slika, raspodat, komentari, obrisana);
		
	}
	//za pisanje u fajl
	public static String stringToFile(Manifestacija m) {
		Lokacija l = m.getLokacija();
		String lokacija = l.getGeoDuzina() + "," + l.getGeoSirina() + "," + l.getUlica() + "," + l.getBroj() + "," + l.getMesto() + "," + l.getGrad() + "," + l.getPostanskiBroj();
		return m.getId() + ";" + m.naziv + ";" + m.tipManifestacije + ";" + m.brojMesta + ";"
				+ m.datumOdrzavanja.toString() + ";" + m.cenaKarte + ";" + m.statusManifestacije + ";"
				+ lokacija + ";" + m.slika + ";" + m.rasprodata + ";" + m.komentari + ";" + m.obrisana;
	}
	
}
