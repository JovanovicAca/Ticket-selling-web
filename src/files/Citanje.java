package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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

public class Citanje {

	///////////////////////////
	public ArrayList<Korisnik> ucitajKorisnike() throws ParseException {
		ArrayList<Korisnik> lista = new ArrayList<Korisnik>();
			try {
				
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/korisnici.txt"), "UTF-8"));
				
				String line = "";
				
				try {
					while((line = br.readLine()) != null) {
						
						String tokeni[] = line.split(";");
						Uloga uloga = Uloga.valueOf(tokeni[6]); // Uzimamo ulogu trenutne linije
						if(uloga.equals(Uloga.KUPAC)) {
							lista.add(Kupac.parseString(line));
						}else if(uloga.equals(Uloga.PRODAVAC)) {
							lista.add(Prodavac.parseString(line));
						}else if(uloga.equals(Uloga.ADMINISTRATOR)) {
							lista.add(Admin.parseString(line));
						}else {
							System.out.println("Error.\n");
						}
						
						
						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			return lista;
		}
	
	///////////////////////////
	public ArrayList<Manifestacija> ucitajManifestacije() throws ParseException{
		ArrayList<Manifestacija> lista = new ArrayList<Manifestacija>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/manifestacije.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(Manifestacija.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return lista;
	}
	
	///////////////////////////
	public ArrayList<Karta> ucitajKarte() throws ParseException{
		ArrayList<Karta> lista = new ArrayList<Karta>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/karte.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(Karta.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return lista;
	}
	
	///////////////////////////
	public ArrayList<Komentar> ucitajKomentar() throws ParseException{
		ArrayList<Komentar> lista = new ArrayList<Komentar>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/manifestacije.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(Komentar.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return lista;
	}
	///////////////////////////
	public ArrayList<ManifestacijaKarta> ucitajManifestacijaKarte() throws ParseException{
		ArrayList<ManifestacijaKarta> lista = new ArrayList<ManifestacijaKarta>();
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/manifestacijaKarte.txt"), "UTF-8"));
			
			String line = "";
			
			try {
				while((line = br.readLine()) != null) {
					lista.add(ManifestacijaKarta.parseString(line));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return lista;
	}
	
}
