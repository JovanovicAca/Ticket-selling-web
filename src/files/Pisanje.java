package files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Admin;
import model.Karta;
import model.Komentar;
import model.Korisnik;
import model.Kupac;
import model.Manifestacija;
import model.ManifestacijaKarta;
import model.Prodavac;
import model.Uloga;

public class Pisanje {

	////////////////////////////////
	public void pisiKorisnike(ArrayList<Korisnik> lista,String path) throws IOException {
		FileWriter file = new FileWriter(path);
			String line = "";
			
			for (Korisnik k : lista) {
				
				if(k.getUloga().equals(Uloga.KUPAC)) {
					line = Kupac.stringToFile((Kupac)k);
				}else if(k.getUloga().equals(Uloga.PRODAVAC)) {
					line = Prodavac.stringToFile((Prodavac) k);
				}else if(k.getUloga().equals(Uloga.ADMINISTRATOR)) {
					line = Admin.stringToFile((Admin) k);
				}else {
					
				}
				
				try {
					file.write(line + "\n");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			file.close();
					
		}
	////////////////////////////////
	public void pisiKarte(ArrayList<Karta> karte,String path) throws IOException {
		String line = "";
		FileWriter file = new FileWriter(path);
		for (Karta karta : karte) {
			line = karta.toFileString(karta);
			try {
				
				file.write(line + "\n");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		file.close();
	}
	
	////////////////////////////////
	public void pisiKomentare(ArrayList<Komentar> komentari,String path) throws IOException {
		String line = "";
		FileWriter file = new FileWriter(path);
		for (Komentar k : komentari) {
			line = k.toFileString(k);
			try {
				
				
				file.write(line + "\n");
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		file.close();
	}
	////////////////////////////////
	public void pisiManifestacije(ArrayList<Manifestacija> manifestacije,String path) throws IOException {
		String line = "";
		FileWriter file = new FileWriter(path);
		for (Manifestacija m : manifestacije) {
			line = m.stringToFile(m);
			try {
				
				
				file.write(line + "\n");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		file.close();
	}
	////////////////////////////////
	public void pisiManifestacijeKarte(ArrayList<ManifestacijaKarta> manifestacije,String path) throws IOException {
		String line = "";
		FileWriter file = new FileWriter(path);
		for (ManifestacijaKarta m : manifestacije) {
			line = m.stringToFile(m);
			try {
				
				
				file.write(line + "\n");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		file.close();
	}
}
