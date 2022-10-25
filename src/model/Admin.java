package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Admin extends Korisnik{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String korisnickoIme, String lozinka, String ime, String prezime, String pol,
			LocalDate datumRodjenja, Uloga uloga, int obrisan, int blokiran,String statusKorisnika) {
		super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga, obrisan, blokiran,statusKorisnika);
		// TODO Auto-generated constructor stub
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
		Admin a = new Admin(korisnickoIme, lozinka, ime, prezime, pol, date, uloga,obrisan,blokiran,statusKorisnika);
		
		return a;
	}
	
	//za pisanje u fajl
	public static String stringToFile(Admin a) {
		return a.getKorisnickoIme() + ";" + a.getLozinka() + ";" + a.getIme() + ";" + a.getPrezime() + ";" + a.getPol() + ";" + a.getDatumRodjenja() + ";" + a.getUloga() + ";" + a.getObrisan() + ";" + a.getBlokiran() + ";" + a.getStatusKorisnika();
	}
	
}
