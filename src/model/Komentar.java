package model;

public class Komentar {

	private String id;
	private String korisnik;
	private String manifestacija;
	private String tekst;
	private int ocena;
	private int obrisan;//0-nije,1-jeste
	
	public Komentar() {}
	
	public Komentar(String id,String korisnik, String manifestacija, String tekst, int ocena,int obrisan) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.manifestacija = manifestacija;
		this.tekst = tekst;
		this.ocena = ocena;
		this.obrisan = obrisan;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}
	public String getManifestacija() {
		return manifestacija;
	}
	public void setManifestacija(String manifestacija) {
		this.manifestacija = manifestacija;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		if(ocena>5 || ocena <1){
			return;
		}
		else 
			this.ocena = ocena;
	}

	public int getObrisan() {
		return obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}
	
	//za citanje iz fajla i kreiranje objekta
	public static Komentar parseString(String line) {
		String[] tokeni = line.split(";");
		String id = tokeni[0];
		String korisnik = tokeni[1];
		String manifestacija = tokeni[2];
		String tekst = tokeni[3];
		int ocena = Integer.parseInt(tokeni[4]);
		int obrisan = Integer.parseInt(tokeni[5]);
		return new Komentar(id,korisnik, manifestacija, tekst, ocena,obrisan);
	}
	
	//za fajl
	public static String toFileString(Komentar k) {
		return k.getId() + ";" + k.getKorisnik() + ";" + k.getManifestacija() + ";" + k.getTekst() + ";" + k.getOcena() + ";" + k.getObrisan();
	}
	
}
