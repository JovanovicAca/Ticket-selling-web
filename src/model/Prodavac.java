package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Prodavac extends Korisnik {
	private List<String> manifestacije;

	

	public Prodavac() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Prodavac(String korisnickoIme, String lozinka, String ime, String prezime, String pol,
			LocalDate datumRodjenja, Uloga uloga, int obrisan, int blokiran,String statusKorisnika,List<String> manifestacije) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga, obrisan, blokiran,statusKorisnika);
		this.manifestacije = manifestacije;
	}

	public List<String> getManifestacije() {
		return manifestacije;
	}

	public void setManifestacije(List<String> manifestacije) {
		this.manifestacije = manifestacije;
	}
	
	
	//za citanje iz fajla i kreiranje objekta
		public static Korisnik parseString(String line) throws ParseException {
			String tokeni[] = line.split(";");
			String korisnickoIme = tokeni[0];
			String lozinka = tokeni[1];
			String ime = tokeni[2];
			String prezime = tokeni[3];
			String pol = tokeni[4];
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(tokeni[5]);
			Uloga uloga = Uloga.valueOf(tokeni[6]);
			String ul = uloga.toString();
			int obrisan = Integer.parseInt(tokeni[7]);
			int blokiran = Integer.parseInt(tokeni[8]);
			String statusKorisnika = tokeni[9];
			List<String> manifestacije = new ArrayList<String>();
			String[] man = tokeni[10].split(",");
			for (String string : man) {
				//sveKarte.add(string + ","); mozda ovako
				manifestacije.add(string);
			}
			
			Prodavac p = new Prodavac(korisnickoIme, lozinka, ime, prezime, pol, date, uloga,obrisan,blokiran,statusKorisnika,manifestacije);
			
			return p;
		}
		
		//za pisanje u fajl
		public static String stringToFile(Prodavac p) {
			String mani="";
			for (String id : p.getManifestacije()) {
				mani += "," + id;
			}
			mani = mani.substring(1);
			
			return p.getKorisnickoIme() + ";" + p.getLozinka() + ";" + p.getIme() + ";"
				+ p.getPrezime() + ";" + p.getPol() + ";" + p.getDatumRodjenja() + ";" + p.getUloga() + ";" + 
				p.getObrisan() + ";" + p.getBlokiran() + ";" + p.getStatusKorisnika() + ";" + mani;
		}
	
}
