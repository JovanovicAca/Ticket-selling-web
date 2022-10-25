package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import files.Citanje;
import files.Pisanje;
import model.Karta;

public class KarteDAO {

	public static ArrayList<Karta> ucitajKarte() throws ParseException {
		Citanje c = new Citanje();
		ArrayList<Karta> karte = c.ucitajKarte();
		return karte;
	}
	
	public static Karta pronadjiPoId(String id) throws ParseException {
		ArrayList<Karta> karte = ucitajKarte();
		for (Karta karta : karte) {
			if(karta.getId().equals(id)) {
				return karta;
			}
		}
		return null;
	}
	
	public static Karta pronadjiPoManifestaciji(String manifestacija) throws ParseException {
		ArrayList<Karta> karte = ucitajKarte();
		for (Karta karta : karte) {
			if(karta.getManifestacija().equals(manifestacija)) {
				return karta;
			}
		}
		return null;
	}

	public static ArrayList<Karta> pronadjiPoKorisniku(String username) throws ParseException {
		ArrayList<Karta> karte = ucitajKarte();
		ArrayList<Karta> sveKarte = new ArrayList<Karta>();
		for (Karta karta : karte) {
			if(karta.getImeKupca().equals(username)) {
				sveKarte.add(karta);
			}
		}
		return sveKarte;
	}

	public static int getId() throws ParseException {
		ArrayList<Karta> karte = ucitajKarte();
		String id = null;
		for (Karta karta : karte) {
			id = karta.getId();
		}
		return Integer.parseInt(id);
	}

	public static void upisiNovuListu(ArrayList<Karta> sveKarte) throws IOException {
		PrintWriter writter = new PrintWriter("data/karte.txt");
		writter.close();
		Pisanje p = new Pisanje();
		p.pisiKarte(sveKarte, "data/karte.txt");
		
	}

}
