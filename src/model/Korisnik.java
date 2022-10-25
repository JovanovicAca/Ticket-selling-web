package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Korisnik {
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String pol;
	private LocalDate datumRodjenja;
	private Uloga uloga;
	private int obrisan;//0-nije,1-jeste
	private int blokiran;//0-nije,1-jeste
	private String statusKorisnika;
	
	public Korisnik() {
		
	};
	
	public Korisnik(String korisnickoIme, String lozinka) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	public Korisnik(String korisnickoIme,String lozinka,String ime,String prezime,String pol,LocalDate datumRodjenja) throws ParseException {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;;
	}
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String pol, LocalDate datumRodjenja,
			Uloga uloga,int obrisan,int blokiran,String statusKorisnika) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.uloga = uloga;
		this.obrisan = obrisan;
		this.blokiran = blokiran;
		this.statusKorisnika = statusKorisnika;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public Uloga getUloga() {
		return uloga;
	}
	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	} 
	
	public String returnUloga(Uloga uloga) {
		return uloga.toString();
	}

	public int getObrisan() {
		return obrisan;
	}

	public void setObrisan(int obrisan) {
		this.obrisan = obrisan;
	}

	public int getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(int blokiran) {
		this.blokiran = blokiran;
	}

	public String getStatusKorisnika() {
		return statusKorisnika;
	}

	public void setStatusKorisnika(String statusKorisnika) {
		this.statusKorisnika = statusKorisnika;
	}

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime="
				+ prezime + ", pol=" + pol + ", datumRodjenja=" + datumRodjenja + ", uloga=" + uloga + ", obrisan="
				+ obrisan + ", blokiran=" + blokiran + statusKorisnika + "]";
	}
	
}
