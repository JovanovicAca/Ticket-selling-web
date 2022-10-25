Vue.component("Admin", {
    data: function() {
        return {
            listaFestivala: [],
            listaKorisnika: [],
            listaKarata: [],


            djokica: [],

            prikazanPassword: false,

            naziv: null,
            lokacija: null,
            cenaOd: null,
            cenaDo: null,
            datumOd: null,
            datumDo: null,
            rasprodanost: null,
            tip: null,
            sortBy: null,

            korisnik: {
                ime: null,
                korisnickoIme: null,
                prezime: null,
                lozinka: null,
                stariUsername: null,

            },
            datumRodjenja: null,
            stariUsername: null,
        }
    },
    template: ` 
    <div id="homeKupac">
        <link rel="stylesheet" href="CSS/homeAdmin.css" type="text/css">

        <div id="kupac-menu" class="kupacWindow">
            <button type="submit" class="kupacMenuBtn" id="sveManifAdmin" v-on:click="prikaziManifestacije()"> Pregled Svih Manifestacija </button>
            <button type="submit" class="kupacMenuBtn" id="sviKupciAdmin" v-on:click="prikaziKorisnike()"> Pregled Svih Korisnika </button>
            <button type="submit" class="kupacMenuBtn" id="sveKarteAdmin" v-on:click="prikaziKarte()"> Pregled Svih Karata </button>
            <button type="submit" class="kupacMenuBtn" id="izmeniProfilAdmin" v-on:click="izmeniProfil()"> Izmena Profila </button>
            <div id="img-adminMenuDiv">
                <img id="img-kupacMenu" src="RES/tiket.png">
            </div>
            <button type="submit" class="kupacMenuBtn" v-on:click="mainMenu()"> Izloguj Se </button>
        </div>

        <div id="kupac-display" class="kupacWindow">
            <div id="kupac-manifestacije">
                <div id="kupac-filter">

                     <input v-model="naziv" id="velikiInput" type="text" placeholder="Naziv Manifestacije">
                    <input v-model="lokacija" id="velikiInput" type="text" placeholder="Grad">
                    <input v-model="cenaOd" id="maliInput" type="text" placeholder="Cena Od">
                    <input v-model="cenaDo" id="maliInput" type="text" placeholder="Cena Do">

                    <label class="labelSelect" id="main-datum">Datum Od </label>
                    <input v-model="datumOd" id="datumInput" type="date" placeholder="Datum Od">
                    <label class="labelSelect" id="main-datum">Datum Do </label>
                    <input v-model="datumDo" id="datumInput" type="date" placeholder="Datum Do">

                    <form id="velikiSelect">
                        <label class="labelSelect" for="selectRasprodate">Rasprodanost Karata: </label>
                        <select v-model="rasprodanost" id="selectRasprodate">
                            <option value="sveProdate">Sve</option>  
                            <option value="Nerasprodate">Nerasprodate</option>
                            <option value="Rasprodate">Rasprodate</option>
                        </select>

                        <label class="labelSelect" for="selectTip">Tip Manifestacije: </label>
                        <select v-model="tip" id="selectTip">
                            <option value="sviTipovi">Sve</option>
                            <option value="koncert">Koncert</option>  
                            <option value="festival">Festival</option>
                            <option value="pozoriste">Pozoriste</option>
                            <option value="rejv">Rejv</option>
                            <option value="rodjendan">Rodjendan</option>
                            <option value="svadba">Svadba</option>
                        </select>

                        <label class="labelSelect" for="selectOrganizator">Status Manifestacije: </label>
                        <select id="selectOrganizator">
                            <option value="sve">Sve Manifestacije</option>
                            <option value="neaktivna">Cekanje Odobrenja</option>
                            <option value="aktivna">Odobrene</option>
                        </select>
                        <label class="labelSelect" for="selectTip">Sortiraj Po: </label>
                        <select v-model="sortBy" id="selectRasprodate">
                            <option value="Datum Uzlazno">Datum Uzlazno</option>
                            <option value="Datum Silazno">Datum Silazno</option>
                            <option value="Naziv Uzlazno">Naziv Uzlazno</option>
                            <option value="Naziv Silazno">Naziv Silazno</option>
                            <option value="Cena Uzlazno">Cena Uzlazno</option>
                            <option value="Cena Silazno">Cena Silazno</option>
                        </select>
                        <button id="pretragaBtnProdavac" v-on:click="pretraga()" type="button"> Pretraga </button>
                    </form>
                </div>
                <div id="kupac-manifestacije-border">
                    <div id="kupac-manifestacije-unutra">
                        <div id="unutrasnjost-kupac" v-for="mani in listaFestivala" v-on:click="prikaziManifestaciju(mani)">
                            <div id="manifestacijicaKupac">
                                <div id="manifestacijicaMain-naslov">
                                    <h3> Koncert </h3>
                                </div>
                                <div id="manifestacijicaMain-content">
                                    <h6>{{mani.naziv}}</h6>
                                    <h6>{{mani.tipManifestacije}}</h6>
                                    <h6>{{mani.datumOdrzavanja}}</h6>
                                    <h6>{{mani.lokacija.grad}} {{mani.lokacija.ulica}} </h6>
                                    <h6>{{mani.cenaKarte}}</h6>
                                </div>
                                <div id="manifestacijicaMain-slika">
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="admin-korisnici">
                <div id="admin-filterKorisnika">
                    <input id="velikiInput1" type="text" placeholder="Korisnicko Ime">
                    <input id="maliInput1" type="text" placeholder="Ime Korisnika">
                    <input id="maliInput2" type="text" placeholder="Prezime Korisnika">
                    </br>
                    </br>
                    <label class="labelSelect" for="selectRasprodate">Uloga Korisnika: </label>
                    <select id="selectRasprodate3">
                        <option value="sviKorisnici">Svi Korisnici</option> 
                        <option value="kupac">Kupac</option>  
                        <option value="prodavac">Prodavac</option> 
                        <option value="admin">Admin</option>
                    </select>
                    <label class="labelSelect" id="admin-label" for="selectRasprodate">Tip Korisnika: </label>
                    <select id="selectRasprodate3">
                        <option value="sviTipovi">Svi Tipovi</option> 
                        <option value="regularan">Regularan</option>  
                        <option value="srebrni">Srebrni</option> 
                        <option value="zlatni">Zlatni</option>
                    </select>
                    </br>
                    </br>
                    <label class="labelSelect" for="selectRasprodate">Status Korisnika: </label>
                    <select id="selectRasprodate3">
                        <option value="sviKorisnici">Svi Korisnici</option>
                        <option value="redovniKorisnici">Redovni Korisnici</option>
                        <option value="sumnjiviKorisnici">Sumnjivi Korisnici</option>  
                        <option value="blokiraniKorisnici">Blokirani Korisnici</option>  
                    </select>
                    <button id="pretragaKorisnikaAdmin" v-on:click="pretraga()" type="button"> Pretraga </button>
                </div>
                <div id="admin-korisnici-border">
                    <div id="admin-korisnici-unutra">
                        <div v-for="korisnik in djokica" v-on:click="prikaziKorisnika()">
                            <div id="admin-korisnik-item">
                                <div id="admin-korisnik-intro">
                                    <img id="korisnikAdmin-slika" src="RES/korisnik.png">
                                    <div id="admin-korisnik-info">
                                        <h5> Korisnicko Ime: blagojevicuros99 </h5>
                                        <h5> Uloga Korisnika: Kupac </h5>
                                        <h5> Status Korisnika: Sumnjiv </h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="admin-korisnici-inputi">
                    <h2> Ime i Prezime Korisnika </h2>
                    <input id="korisniciAdminTxt" type="text" readonly/>
                    </br></br></br>
                    <h2> Email Adresa Korisnika</h2>
                    <input id="korisniciAdminTxt" type="text" readonly/>
                    </br></br></br>
                    <h2> Tip Korisnika </h2>
                    <input id="korisniciAdminTxt" type="text" readonly/>
                    </br></br></br>
                    <h2> Status Korisnika </h2>
                    <input id="korisniciAdminTxt" type="text" readonly/>
                </div>
                <button type="submit" id="blokBtn"> Blokiraj Korisnika </button>
                <button type="submit" id="odblokBtn"> Odblokiraj Korisnika </button>
            </div>

            <div id="kupac-karte">
                <div id="kupac-filterKarti">
                    <input id="velikiInput1" type="text" placeholder="Naziv Manifestacije">
                    <input id="velikiInput1" type="text" placeholder="Lokacija">
                    <input id="maliInput1" type="number" min=0 placeholder="Cena Od">
                    <input id="maliInput1" type="number" min=0 placeholder="Cena Do">
                    </br>
                    </br>
                    <input id="velikiInput1" type="text" placeholder="Ime i Prezime Kupca">
                    <label class="labelSelect">Datum Od </label>
                    <input id="datumInput1" type="date" placeholder="Datum Od">
                    <label class="labelSelect">Datum Do </label>
                    <input id="datumInput1" type="date" placeholder="Datum Do">
                    </br>
                    </br>
                    <label class="labelSelect" for="selectRasprodate">Tip Karte: </label>
                    <select id="selectRasprodate1">
                        <option value="saab">Regular</option>  
                        <option value="volvo">Fan Pit</option> 
                        <option value="volvo">VIP</option>
                    </select>
                    <label class="labelSelect" for="selectTip">Tip Manifestacije: </label>
                    <select id="selectTip1">
                        <option value="sviTipovi">Sve</option>
                        <option value="koncert">Koncert</option>  
                        <option value="festival">Festival</option>
                        <option value="pozoriste">Pozoriste</option>
                        <option value="rejv">Rejv</option>
                        <option value="rodjendan">Rodjendan</option>
                        <option value="svadba">Svadba</option>
                    </select>
                    
                    <button id="searchBtnKarta12" v-on:click="pretragaKarte()" type="button"> Pretraga </button>
                    <form id="velikiSelect">

                        <label class="labelSelect" for="selectRasprodate">Rasprodanost Karata: </label>
                        <select id="selectRasprodate1">
                            <option value="sveProdate">Sve</option>
                            <option value="Nerasprodate">Nerasprodate</option>  
                            <option value="Rasprodate">Rasprodate</option>

                        </select>
                    </form>
                </div>
                <div id="kupac-karte-border1">
                    <div id="kupac-karte-unutra">
                        <div v-for="nesto in listaKarata" v-on:click="pregledKarte()">
                            <div id="karta-okvir">
                                <div id="karta-unutrasnjost">
                                    <div id="karta-krugLeviCrni"/>
                                    <div id="karta-krugDesniCrni"/>
                                    <div id="karta-krugLeviBeli"/>
                                    <div id="karta-krugDesniBeli"/>
                                    <div id="linijaKarta"/>
                                    <div id="kartaSlova">
                                        <h5 class="kartaNaslov"> Naziv : {{nesto.naziv}} </h5>
                                        <h5 class="kartaNaslov"> Lokacija : {{nesto.lokacija.grad}}  {{nesto.lokacija.ulica}} {{nesto.lokacija.broj}}</h5>
                                        <h5 class="kartaNaslov"> Tip Manifestacije: {{nesto.tipManifestacije}} </h5>
                                        <h5 class="kartaNaslov"> Cena : {{nesto.cenaKarte}} </h5>
                                        <h5 class="kartaNaslov"> Datum Izvodjenja: {{nesto.datumOdrzavanja}} </h5>
                                    </div>
                                    <div id="tipKarteNaKarti">
                                        <h1>{{nesto.tipKarte}}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <h2 id="lokacijaID"> Lokacija Manifestacije </h2>
                <div id="prodavac-karta-mapa">

                </div>
            </div>

            <div id="kupac-profil">
                <div id="kupac-profil-levo">
                    <h1> Ime </h1>
                    <input v-model="korisnik.ime" type="text" class="input-login">
                    </br>
                    </br>
                    </br>
                    <h1> Korisnicko Ime </h1>
                    <input v-model="korisnik.korisnickoIme" type="text" class="input-login">
                    </br>
                    </br>
                    </br>
                    <h1> Datum Rodjenja </h1>
                    <input readonly v-model="datumRodjenja" type="text" name="datum-rodjenja" id="main-datum" class="input-login">
                </div>
                <div id="kupac-profil-desno">
                    <h1> Prezime </h1>
                    <input v-model="korisnik.prezime" type="text" class="input-login">
                    </br>
                    </br>
                    </br>
                    <h1> Lozinka </h1>
                    <input v-model="korisnik.lozinka" type="password" id="passwordBox" class="input-login">
                </div>
                <button type="submit" class="izmeniInfoBtn" v-on:click="izmeniInfo()"> Izmeni Informacije </button>
                <button type="submit" v-on:click="prikaziPassword()" class="okoBtnProfil"><img id="slika" src="RES/eye.png"></button>
            </div>
        </div>
</div>			  
`,
    methods: {
        prikaziManifestaciju: function(manifestacija) {
            localStorage.setItem('manifestacija', JSON.stringify(manifestacija))
            this.$router.push('/manifestacija')
        },
        pretraga: function() {
            const { naziv, lokacija, cenaOd, cenaDo, datumOd, datumDo, rasprodanost, tip, sortBy } = this;

            axios
                .get('/pretragaManifestacija', {
                    params: {
                        naziv,
                        lokacija,
                        cenaOd,
                        cenaDo,
                        datumOd,
                        datumDo,
                        rasprodanost,
                        tip,
                        sortBy
                    }
                }).then((response) => {
                    this.listaFestivala = response.data
                }).catch(error => {
                    console.log(error.response.data)
                })
        },
        unselectSve: function() {
            let sviDugmici = document.getElementsByClassName("kupacMenuBtn");
            for (let i = 0; i < sviDugmici.length; i++) {
                sviDugmici[i].style.backgroundColor = "#2e2e2e";
            }
            return;
        },
        hideAll: function() {
            document.getElementById('kupac-manifestacije').style.display = 'none';
            document.getElementById('kupac-profil').style.display = 'none';
            document.getElementById('kupac-karte').style.display = 'none';
            document.getElementById('admin-korisnici').style.display = 'none';
        },
        prikaziManifestacije: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('sveManifAdmin').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-manifestacije').style.display = 'block';
        },
        prikaziKorisnike: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('sviKupciAdmin').style.backgroundColor = "#4d4d4d";
            document.getElementById('admin-korisnici').style.display = 'block';
        },
        prikaziKarte: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('sveKarteAdmin').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-karte').style.display = 'block';
        },
        izmeniProfil: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('izmeniProfilAdmin').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-profil').style.display = 'block';
        },
        mainMenu: function() {
            this.$router.push("/");
        },
        podesavanjeDatuma: function() {
            var today = new Date().toISOString().split('T')[0];
            document.getElementsByName('datum-rodjenja')[0].setAttribute('max', today);
        },
        prikaziPassword: function() {
            if (this.prikazanPassword) {
                document.getElementById('passwordBox').type = "password";
                this.prikazanPassword = false;
            } else {
                document.getElementById('passwordBox').type = "text";
                this.prikazanPassword = true;
            }
        },
        prikaziKorisnika: function() {

        },
        izmeniInfo: function() {
            if (!this.korisnik.ime || !this.korisnik.prezime || !this.korisnik.korisnickoIme || !this.korisnik.lozinka) {
                swal("Error", "Polja ne smeju ostati prazna!", "error")
                return
            }
            swal({
                    title: "Jeste li sigurni?",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                })
                .then((willDelete) => {
                    if (willDelete) {
                        axios
                            .post('/izmeniProfil', {
                                ime: this.korisnik.ime,
                                korisnickoIme: this.korisnik.korisnickoIme,
                                prezime: this.korisnik.prezime,
                                lozinka: this.korisnik.lozinka,
                                stariUsername: this.korisnik.stariUsername,
                            })
                            .then(response => {
                                if (response.data === "Zauzeto") {
                                    swal("Error", "Username vec postoji!", "error")
                                    return
                                } else {
                                    swal("Success", "Uspesno promenjene informacije!", "success")
                                }
                            })
                    } else {
                        swal("Infomacije nisu promenjene!")
                    }
                })

        },
        prikazMapa: function() {
            let self = this;
            var vectorSource = new ol.source.Vector({});
            var vectorLayer = new ol.layer.Vector({ source: vectorSource });

            var map = new ol.Map({
                target: 'prodavac-karta-mapa',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    }), vectorLayer
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([19.8335, 45.2671]),
                    zoom: 12
                })
            });

            var marker;

            setMarker = function(position) {
                marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat(position)));
                vectorSource.addFeature(marker);
            }

            map.on("click", function(event) {
                let position = ol.proj.toLonLat(event.coordinate);
                self.currentPosition.lat = parseFloat(position.toString().split(",")[1]).toFixed(6);
                self.currentPosition.lon = parseFloat(position.toString().split(",")[0]).toFixed(6);
                vectorSource.clear();
                setMarker(position);
                self.reverseGeolocation(position);
            });
        },
        reverseGeolocation: function(coords) {
            let self = this;
            fetch('http://nominatim.openstreetmap.org/reverse?format=json&lon=' + coords[0] + '&lat=' + coords[1])
                .then(function(response) {
                    return response.json();
                }).then(function(json) {
                    console.log(json);
                    if (json.address.house_number == undefined) {} else {
                        self.locationString = json.address.road + " " + self.new_manifestation.locationDTO.number + ", " + json.address.city;
                        self.new_manifestation.locationDTO.longitude = coords[1]
                        self.new_manifestation.locationDTO.latitude = coords[0]
                        self.new_manifestation.locationDTO.street = json.address.road
                        self.new_manifestation.locationDTO.number = json.address.house_number
                        self.new_manifestation.locationDTO.postNumber = json.address.postcode
                        self.new_manifestation.locationDTO.state = json.address.country
                        self.new_manifestation.locationDTO.city = json.address.city

                    }

                });
        },
    },
    mounted() {
        this.prikaziManifestacije();
        this.podesavanjeDatuma();
        this.prikazMapa();
        this.korisnik = JSON.parse(localStorage.getItem('user'))
        this.datumRodjenja = this.korisnik.datumRodjenja.day + '-' + this.korisnik.datumRodjenja.month + '-' + this.korisnik.datumRodjenja.year
        var username = JSON.parse(localStorage.getItem('user')).korisnickoIme
        this.stariUsername = username
        this.korisnik.stariUsername = this.stariUsername

        axios
            .get('/getSveManifestacije')
            .then(response => {
                this.listaFestivala = response.data
            })

        axios
            .get('/getSveKarte')
            .then(response => {
                this.listaKarata = response.data
            })
            // for (let i = 0; i < 15; i++) {
            //     this.listaFestivala.push(i);
            // }

        // axios
        //     .get('/getSveManifestacije')
        //     .then(response => {
        //         this.listaFestivala = response.data
        //     })
        // for (let i = 0; i < 15; i++) {
        //     this.djokica.push(i);
        // }
        this.sortBy = "Datum Uzlazno"
        this.pretraga()
    }
});