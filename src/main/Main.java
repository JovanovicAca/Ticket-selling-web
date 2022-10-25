package main;


//import static spark.Spark.port;
//import static spark.Spark.post;
//import static spark.Spark.staticFiles;
import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import dao.KarteDAO;
import dao.KorisnikDAO;
import dao.ManifestacijaDAO;
import files.Citanje;
import files.Pisanje;
import model.Karta;
import model.Korisnik;
import model.Kupac;
import model.Manifestacija;
import model.ManifestacijaKarta;
import model.Prodavac;
import model.StatusKarte;
import model.TipKarte;
import model.TipKupca;
import model.Uloga;

public class Main {
	private static Gson gson = new Gson();
	private static String korisnickoime;
	public static void main(String[] args) throws IOException, ParseException {
		port(9090);
		
		ArrayList<Korisnik> sviKorisnici = KorisnikDAO.ucitajKorisnike();
		ArrayList<Manifestacija> sveManifestacije = ManifestacijaDAO.ucitajManifestacije();
		ArrayList<Karta> sveKarte = KarteDAO.ucitajKarte();
		Citanje c = new Citanje();
		Pisanje p = new Pisanje();
		
		staticFiles.externalLocation(new File("./resource").getCanonicalPath());
		after((req,res) -> res.type("application/json"));
		
		post("/login", (req, res) -> {
			try {
				Korisnik k = gson.fromJson(req.body(), Korisnik.class);
				
				if ((k = KorisnikDAO.pronadjiLogin(k.getKorisnickoIme(), k.getLozinka())) != null) {
					res.status(200);
					return gson.toJson(k);
				} else {
					res.status(404);
					return "Greska";
				}

			} catch (Exception e) {
				return "Greska";
			}
			
		});
		
		post("/register",(req,res) -> {
			
			try {
				HashMap<String, String> mapa = gson.fromJson(req.body(), HashMap.class);
				String datum = mapa.get("datumRodjenja");
				LocalDate datumRodjenja = LocalDate.parse(datum);
				
				ArrayList<String> lista = new ArrayList<String>();
				ArrayList<String> manifestacije = new ArrayList<String>();
				TipKupca tip = new TipKupca(null, 0, 0);
				if(KorisnikDAO.pronadjiPoUsername(mapa.get("korisnickoIme")) != null) {
					res.status(200);
					return "Zauzeto";
				}
				
				Kupac kupac = new Kupac(mapa.get("korisnickoIme"), mapa.get("lozinka"), mapa.get("ime"), mapa.get("prezime"), mapa.get("pol"), datumRodjenja, Uloga.KUPAC, 0, 0, null,lista, 0, tip,manifestacije);
				
				sviKorisnici.add(kupac);
				p.pisiKorisnike(sviKorisnici, "data/korisnici.txt");
				res.status(200);
				return gson.toJson(kupac);	
			}catch(Exception e) {
				res.status(404);
				return "Greska";
			}
			
		});
		
		get("/getSveManifestacije",(req,res) ->{
			return gson.toJson(sveManifestacije);
		});
		
		post("/getSveKarteKorisnik",(req,res) ->{
			HashMap<String, String> mapa = gson.fromJson(req.body(), HashMap.class);
			ArrayList<Karta> karte = KarteDAO.pronadjiPoKorisniku(mapa.get("korisnickoIme"));
			ArrayList<ManifestacijaKarta> manifestacije = new ArrayList<ManifestacijaKarta>();
			for (Karta karta : karte) {
				for(Manifestacija m : sveManifestacije) {
					if(karta.getManifestacija().equals(m.getId()) && karta.getStatusKarte().equals(StatusKarte.REZERVISANA)) {
						ManifestacijaKarta mk = new ManifestacijaKarta(m.getId(), m.getNaziv(), m.getLokacija(), m.getTipManifestacije(), m.getCenaKarte(),m.getDatumOdrzavanja(), karta.getTipKarte(),karta.getId());
						manifestacije.add(mk);
					}
				}	
			}	
			return gson.toJson(manifestacije);
		});
		
		get("/getSveKarte",(req,res) -> {
			ArrayList<ManifestacijaKarta> mks = new ArrayList<ManifestacijaKarta>();
			for (Karta karta : sveKarte) {
				for (Manifestacija mani : sveManifestacije) {
					if(karta.getManifestacija().equals(mani.getId())) {
						ManifestacijaKarta mk = new ManifestacijaKarta(mani.getId(), mani.getNaziv(), mani.getLokacija(), mani.getTipManifestacije(), mani.getCenaKarte(), mani.getDatumOdrzavanja(), karta.getTipKarte(),karta.getId());
						mks.add(mk);
					}
				}
			}
			return gson.toJson(mks);
		});
		
		post("/getSveManifestacijeKorisnik",(req,res) ->{
			HashMap<String, String> mapa = gson.fromJson(req.body(), HashMap.class);
			ArrayList<Manifestacija> manifestacije = new ArrayList<Manifestacija>();
			Kupac k = (Kupac) KorisnikDAO.pronadjiPoUsername(mapa.get("korisnickoIme"));
			for (String s : k.getSveManifestacije()) {
				for (Manifestacija m : sveManifestacije) {
					if(s.equals(m.getId())) {
						manifestacije.add(m);
					}
				}
			}
			return gson.toJson(manifestacije);
		});
		

		post("/izmeniProfil",(req,res) -> {
			HashMap<String, String> mapa = gson.fromJson(req.body(), HashMap.class);
			
			if(!mapa.get("korisnickoIme").equals(mapa.get("stariUsername"))) {
				if(KorisnikDAO.pronadjiPoUsername(mapa.get("korisnickoIme")) != null) {
					res.status(200);
					return "Zauzeto";
				}
			}
			
			for (Korisnik k : sviKorisnici) {
				if(k.getKorisnickoIme().equals(mapa.get("stariUsername"))) {
					Korisnik korisnik = k;
					korisnik.setIme(mapa.get("ime"));
					korisnik.setPrezime(mapa.get("prezime"));
					korisnik.setKorisnickoIme(mapa.get("korisnickoIme"));
					korisnik.setLozinka(mapa.get("lozinka"));
					
				}
			}
			KorisnikDAO.upisiNovuListu(sviKorisnici);
			res.status(200);
			
			return "Uspesno";
		});
		
		post("/sacuvajKorisnicko",(req,res) -> {
			HashMap<String, String> mapa = gson.fromJson(req.body(), HashMap.class);
			korisnickoime = mapa.get("korisnickoIme");
			return null;
		});
		
		get("/pretragaManifestacija","application/json",(req,res) -> {
			
			String naziv = req.queryParams("naziv");
			String grad = req.queryParams("lokacija");
			double cenaOd;
			double cenaDo;
		
			if(req.queryParams("cenaOd") != null) 
			{
				if(req.queryParams("cenaOd").equals(""))
				{
					cenaOd = 0;
				}
				else
				{
					cenaOd = Double.parseDouble(req.queryParams("cenaOd"));			
				}
			}
			else 
			{
		   		cenaOd = 0;
			}
			//ako je null ili " "
			if(req.queryParams("cenaDo") != null) {
				if(req.queryParams("cenaDo").equals(""))
				{
					cenaDo = 0;
				}
				else
				{
					cenaDo = Double.parseDouble(req.queryParams("cenaDo"));			
				}
			}
			else {
				cenaDo = 0;
			}
			
			Timestamp datumOd;
			Timestamp datumDo; 
			if(req.queryParams("datumOd") != null) 
			{
				if(req.queryParams("datumOd").equals(""))
				{
					datumOd = null;
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(req.queryParams("datumOd"));
					datumOd = new Timestamp(parsedDate.getTime());
				}
			}
			else 
			{
				datumOd = null;
			}
			if(req.queryParams("datumDo") != null) 
			{
				if(req.queryParams("datumDo").equals(""))
				{
					datumDo = null;
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(req.queryParams("datumDo"));
					datumDo = new Timestamp(parsedDate.getTime());
				}
			}
			else 
			{
				datumDo = null;
			}
			String rasprodanost = req.queryParams("rasprodanost");
			Boolean rasp;
			Boolean rasp1;
			if(req.queryParams("rasprodanost") == null) {
				rasp = false;
				rasp1 = false;
			}
			else if(req.queryParams("rasprodanost").isEmpty() || req.queryParams("rasprodanost").equals("Nerasprodate")) {
				rasp1 = false;
				rasp = false;
			}
			else if(req.queryParams("rasprodanost").equals("sveProdate")) {
				rasp1 = false;
				rasp = true;
			}
				
			else {
				rasp1 = true;
				rasp = true;
			}
			
			
			String tip = req.queryParams("tip");
			List<Manifestacija> manifestacije = new ArrayList<Manifestacija>();

			if(req.queryParams("sortBy").equals("Datum Uzlazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
					return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
							&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
							&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
							&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
							&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
							&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
							&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
							&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
				}).sorted(Comparator.comparing(Manifestacija::getDatumOdrzavanja)).collect(Collectors.toList());
			
			}
			else if(req.queryParams("sortBy").equals("Datum Silazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
						return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
								&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
								&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
								&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
								&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
								&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
								&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
								&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
					}).sorted(Comparator.comparing(Manifestacija::getDatumOdrzavanja)).collect(Collectors.toList());
				 Collections.reverse(manifestacije);
			}
			else if(req.queryParams("sortBy").equals("Naziv Uzlazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
						return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
								&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
								&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
								&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
								&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
								&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
								&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
								&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
					}).sorted(Comparator.comparing(Manifestacija::getNaziv)).collect(Collectors.toList());
			}
			else if(req.queryParams("sortBy").equals("Naziv Silazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
						return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
								&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
								&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
								&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
								&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
								&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
								&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
								&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
					}).sorted(Comparator.comparing(Manifestacija::getNaziv)).collect(Collectors.toList());
				 Collections.reverse(manifestacije);
			}
			
			else if(req.queryParams("sortBy").equals("Cena Uzlazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
						return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
								&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
								&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
								&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
								&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
								&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
								&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
								&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
					}).sorted(Comparator.comparing(Manifestacija::getCenaKarte)).collect(Collectors.toList());
			}
			else if(req.queryParams("sortBy").equals("Cena Silazno")) {
				 manifestacije = sveManifestacije.stream().filter(manifestacija ->{
						return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
								&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
								&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
								&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
								&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
								&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
								&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
								&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
					}).sorted(Comparator.comparing(Manifestacija::getCenaKarte)).collect(Collectors.toList());
				 Collections.reverse(manifestacije);
			}
			
			List<Manifestacija> manis = new ArrayList<Manifestacija>();
			if(req.queryParams("prodavac") != null) {
				if(req.queryParams("prodavac").equals("sviOrganizatori")) {
					return gson.toJson(manifestacije);
				}else {
					Prodavac k = (Prodavac) KorisnikDAO.pronadjiPoUsername(req.queryParams("korisnickoIme"));
					for (String idMani : k.getManifestacije()) {
						System.out.println(idMani);
						for (Manifestacija m : manifestacije) {
							if(idMani.equals(m.getId())) {
								manis.add(m);
							}
						}
					}
					
				}
				return gson.toJson(manis);
			}
			return gson.toJson(manifestacije);
		
			
		});
		
		get("/slobodanBrojMesta","application/json",(req,res) -> {
			int cnt = 0;
			for (Karta karta : sveKarte) {
				if(karta.getManifestacija().equals(req.queryParams("id")))
				{
					if(karta.getStatusKarte().equals(StatusKarte.REZERVISANA))
					{
						cnt++;
					}
				}
			}
			return gson.toJson(cnt);
		});
		
		get("/pretragaManifestacijaKarta","application/json",(req,res) -> {
			
			String naziv = req.queryParams("naziv1");
			String grad = req.queryParams("lokacija1");
			double cenaOd;
			double cenaDo;
		
			if(req.queryParams("cenaOd1") != null) 
			{
				if(req.queryParams("cenaOd1").equals(""))
				{
					cenaOd = 0;
				}
				else
				{
					cenaOd = Double.parseDouble(req.queryParams("cenaOd1"));			
				}
			}
			else 
			{
		   		cenaOd = 0;
			}
			if(req.queryParams("cenaDo1") != null) {
				if(req.queryParams("cenaDo1").equals(""))
				{
					cenaDo = 0;
				}
				else
				{
					cenaDo = Double.parseDouble(req.queryParams("cenaDo1"));			
				}
			}
			else {
				cenaDo = 0;
			}
			
			Timestamp datumOd;
			Timestamp datumDo; 
			if(req.queryParams("datumOd1") != null) 
			{
				if(req.queryParams("datumOd1").equals(""))
				{
					datumOd = null;
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(req.queryParams("datumOd1"));
					datumOd = new Timestamp(parsedDate.getTime());
				}
			}
			else 
			{
				datumOd = null;
			}
			if(req.queryParams("datumDo1") != null) 
			{
				if(req.queryParams("datumDo1").equals(""))
				{
					datumDo = null;
				}
				else
				{
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsedDate = dateFormat.parse(req.queryParams("datumDo1"));
					datumDo = new Timestamp(parsedDate.getTime());
				}
			}
			else 
			{
				datumDo = null;
			}
			String rasprodanost = req.queryParams("rasprodanost1");
			Boolean rasp;
			Boolean rasp1;
			if(req.queryParams("rasprodanost1") == null) {
				rasp = false;
				rasp1 = false;
			}
			else if(req.queryParams("rasprodanost1").isEmpty() || req.queryParams("rasprodanost1").equals("Nerasprodate")) {
				rasp1 = false;
				rasp = false;
			}
			else if(req.queryParams("rasprodanost1").equals("sveProdate")) {
				rasp1 = false;
				rasp = true;
			}
				
			else {
				rasp1 = true;
				rasp = true;
			}
			
			
			String tip = req.queryParams("tip1");
	
			
			List<Manifestacija> manifestacije = sveManifestacije.stream().filter(manifestacija ->{
				return (naziv == null || naziv.equals("") || manifestacija.getNaziv().equalsIgnoreCase(naziv))
						&& (grad == null || grad.equals("") || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
						&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
						&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
						&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
						&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
						&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp || manifestacija.isRasprodata() == rasp1)
						&& (tip == null || tip.isEmpty() || tip.equals("sviTipovi") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
			}).collect(Collectors.toList());
			
				ArrayList<Karta> karte = KarteDAO.pronadjiPoKorisniku(req.queryParams("korisnickoIme"));
				ArrayList<ManifestacijaKarta> mani = new ArrayList<ManifestacijaKarta>();
				for (Karta karta : karte) {
					for(Manifestacija m : manifestacije) {
						if(karta.getManifestacija().equals(m.getId())) {
							ManifestacijaKarta mk = new ManifestacijaKarta(m.getId(), m.getNaziv(), m.getLokacija(), m.getTipManifestacije(), m.getCenaKarte(),m.getDatumOdrzavanja(), karta.getTipKarte(),karta.getId());
							mani.add(mk);
						}
					}	
				}	
			return gson.toJson(mani);
			
			
		});
		
		get("/kupiKartu","application/json",(req,res) ->{
			String tipKarte = req.queryParams("tipKarte");
			Integer kolicina = Integer.parseInt(req.queryParams("kolicina"));
			Double ukupnaCena = Double.parseDouble(req.queryParams("ukupnaCena"));
			String idManifestacije = req.queryParams("idManifestacije");
			String korisnickoIme = req.queryParams("korisnickoIme");


			for (int i = 0;i<kolicina;i++) {
				int id = KarteDAO.getId() + i + 1;
				Manifestacija mani = ManifestacijaDAO.pronadjiPoId(idManifestacije);
				Timestamp datumManifestacije = mani.getDatumOdrzavanja();
				double cena = ukupnaCena/kolicina;
				String imeKupca = korisnickoIme;
				StatusKarte statusKarte = StatusKarte.REZERVISANA;
				TipKarte tip1 = TipKarte.valueOf(tipKarte);
				int obrisan = 0;
				Karta karta = new Karta(String.valueOf(id), idManifestacije, datumManifestacije, cena, imeKupca, statusKarte, tip1, obrisan);
				sveKarte.add(karta);
			}
			KarteDAO.upisiNovuListu(sveKarte);
			
			return null;
		});
		
		get("/otkaziKartu","application/json",(req,res)->{
			
			String idKarte = req.queryParams("karta");
			for (Karta karta : sveKarte) {
				if(karta.getId().equals(idKarte)) {
					karta.setStatusKarte(StatusKarte.ODUSTANAK);
				}
			}
			
			KarteDAO.upisiNovuListu(sveKarte);
			return null;
		});
		
//		private static ArrayList<Manifestacija> pronadjiPretraga(){
//			
//			return sveManifestacije.stream().filter(manifestacija ->{
//				return (naziv == null || manifestacija.getNaziv().equalsIgnoreCase(naziv))
//						&& (grad == null || manifestacija.getLokacija().getGrad().equalsIgnoreCase(grad))
//						&& (manifestacija.getCenaKarte() >= cenaOd || cenaOd == 0)
//						&& (manifestacija.getCenaKarte() <= cenaDo || cenaDo == 0)
//						&& (datumOd == null || manifestacija.getDatumOdrzavanja().after(datumOd))
//						&& (datumDo == null || manifestacija.getDatumOdrzavanja().before(datumDo))
//						&& (rasprodanost == null || rasprodanost.isEmpty() || manifestacija.isRasprodata() == rasp)
//						&& (tip == null || tip.isEmpty() || tip.equals("Sve") || manifestacija.getTipManifestacije().toString().equalsIgnoreCase(tip));
//			}).collect(Collectors.toList());
//		}
		
	}

	

	



	private static Object renderIndex() {
		try {
		      URL url = Main.class.getResource("resource/public");
		      return new String(Files.readAllBytes(Paths.get(url.toURI())), Charset.defaultCharset());
		    } catch (Exception e) {
		      System.out.println(e.getMessage() + "AA");
		    }
		    return null;
	}

}
