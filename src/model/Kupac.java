package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kupac extends Korisnik{


	private List<String> sveKarte;
	private double brBodova;
	private TipKupca tipKupca;
	private List<String> sveManifestacije;
	
	public Kupac() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol,
			LocalDate datumRodjenja, Uloga uloga, int obrisan, int blokiran,String statusKorisnika,List<String> sveKarte, double brBodova,TipKupca tipKupca,List<String> sveManifestacije) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga, obrisan, blokiran,statusKorisnika);
		// TODO Auto-generated constructor stub
		this.sveKarte = sveKarte;
		this.brBodova = brBodova;
		this.tipKupca = tipKupca;
		this.sveManifestacije = sveManifestacije;
	}

	
	public List<String> getSveKarte() {
		return sveKarte;
	}
	public void setSveKarte(List<String> sveKarte) {
		this.sveKarte = sveKarte;
	}
	public double getBrBodova() {
		return brBodova;
	}
	public void setBrBodova(double brBodova) {
		this.brBodova = brBodova;
	}

	public TipKupca getTipKupca() {
		return tipKupca;
	}

	public void setTipKupca(TipKupca tipKupca) {
		this.tipKupca = tipKupca;
	}
	
	public List<String> getSveManifestacije() {
		return sveManifestacije;
	}


	public void setSveManifestacije(List<String> sveManifestacije) {
		this.sveManifestacije = sveManifestacije;
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
			//sve karte
			List<String> sveKarte = new ArrayList<String>();
			String[] karte = tokeni[10].split(",");
			for (String string : karte) {
				//sveKarte.add(string + ","); mozda ovako
				sveKarte.add(string);
			}
			
			double brBodova = Double.parseDouble(tokeni[11]);
			//tip kupca
			String[] tips = tokeni[12].split(",");
			TipKupca tipKupca = new TipKupca(tips[0],Double.parseDouble(tips[1]),Double.parseDouble(tips[2]));
			
			//sve manifestacije
			List<String> sveManifestacije = new ArrayList<String>();
			String[] manifestacije = tokeni[13].split(",");
			for (String string : manifestacije) {
				//sveKarte.add(string + ","); mozda ovako
				sveManifestacije.add(string);
			}
			Kupac k = new Kupac(korisnickoIme, lozinka, ime, prezime, pol, date, uloga,obrisan,blokiran,statusKorisnika,sveKarte,brBodova,tipKupca,sveManifestacije);
			
			return k;
		}
		
		//za pisanje u fajl
		public static String stringToFile(Kupac k) {
//			TipKupca tip = k.getTipKupca();
//			String tipKupca = tip.getImeTipa() + "," + tip.getBrBodova() + "," + tip.getPopust();
			String karte = "";
			for (String s : k.getSveKarte()) {
				karte += "," + s;
			}
			karte.substring(1);
			String mani = "";
			for (String s : k.getSveManifestacije()) {
				mani += "," + s;
			}

			mani.substring(1);
			return k.getKorisnickoIme() + ";" + k.getLozinka() + ";" + k.getIme() + ";" + k.getPrezime() + ";" + k.getPol() + ";" + k.getDatumRodjenja() + ";" + k.getUloga() + ";" + k.getObrisan() + ";" + k.getBlokiran()
			+ ";" + k.getStatusKorisnika() + ";" + karte + ";" + k.getBrBodova()+ ";" + k.getTipKupca().toString() + ";" + mani;
		}
		
		
}