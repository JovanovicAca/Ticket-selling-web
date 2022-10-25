package dao;

import java.text.ParseException;
import java.util.ArrayList;

import files.Citanje;
import model.Manifestacija;

public class ManifestacijaDAO {

	public static ArrayList<Manifestacija> ucitajManifestacije() throws ParseException {
		Citanje c = new Citanje();
		ArrayList<Manifestacija> manifestacije = c.ucitajManifestacije();
		return manifestacije;
	}
	
	public static Manifestacija pronadjiPoId(String id) throws ParseException {
		ArrayList<Manifestacija> manifestacije = ucitajManifestacije();
		for (Manifestacija manifestacija : manifestacije) {
			if(manifestacija.getId().equals(id)) {
				return manifestacija;
			}
		}
		return null;
	}

	

}
