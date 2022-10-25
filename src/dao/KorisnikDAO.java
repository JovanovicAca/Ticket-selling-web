package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import files.Citanje;
import files.Pisanje;
import model.Korisnik;

public class KorisnikDAO {

	public static ArrayList<Korisnik> ucitajKorisnike() throws ParseException {
		Citanje c = new Citanje();
		ArrayList<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
		sviKorisnici = c.ucitajKorisnike();
		return sviKorisnici;
	}

	public static Korisnik pronadjiPoUsername(String username) throws ParseException {
		ArrayList<Korisnik> korisnici = ucitajKorisnike();
		for (Korisnik korisnik : korisnici) {
			if(korisnik.getKorisnickoIme().equals(username)) {
				return korisnik;
			}
		}
		return null;
	}
	
	public static Korisnik pronadjiLogin(String username,String lozinka) throws ParseException {
		ArrayList<Korisnik> korisnici = ucitajKorisnike();
		for (Korisnik korisnik : korisnici) {
			if(korisnik.getKorisnickoIme().equals(username)) {
				if(korisnik.getLozinka().equals(lozinka)) {
					return korisnik;
				}
				
			}
		}
		return null;
	}

	public static void upisiNovuListu(ArrayList<Korisnik> sviKorisnici) throws ParseException, IOException {
		PrintWriter writter = new PrintWriter("data/korisnici.txt");
		writter.close();
		Pisanje p = new Pisanje();
		p.pisiKorisnike(sviKorisnici, "data/korisnici.txt");
	}
	
}
