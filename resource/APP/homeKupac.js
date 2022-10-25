Vue.component("Kupac", {
    data: function() {
        return {
            listaFestivala: [],
            listaKarata: [],

            naziv: null,
            lokacija: null,
            cenaOd: null,
            cenaDo: null,
            datumOd: null,
            datumDo: null,
            rasprodanost: null,
            tip: null,
            sortBy: null,

            naziv1: null,
            lokacija1: null,
            cenaOd1: null,
            cenaDo1: null,
            datumOd1: null,
            datumDo1: null,
            rasprodanost1: null,
            tip1: null,

            korisnik: {
                ime: null,
                korisnickoIme: null,
                prezime: null,
                lozinka: null,
                stariUsername: null,

            },
            datumRodjenja: null,
            stariUsername: null,
            prikazanPassword: false,

            karta: "",
        }
    },
    template: ` 
    <div id="homeKupac">
        <link rel="stylesheet" href="CSS/homeKupac.css" type="text/css">
        <div id="kupac-menu" class="kupacWindow">
            <button type="submit" class="kupacMenuBtn" id="rezKartuKupac" v-on:click="rezervisiKartu()"> Pregled Manifestacija </button>
            <button type="submit" class="kupacMenuBtn" id="pregledKarteKupac" v-on:click="pregledKarata()"> Pregled Mojih Karata </button>
            <button type="submit" class="kupacMenuBtn" id="izmeniProfilKupac" v-on:click="izmeniProfil()"> Izmena Profil </button>
            <div id="img-kupacMenuDiv">
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
                        <label class="labelSelect" for="selectTip">Sortiraj Po: </label>
                        <select v-model="sortBy" id="selectRasprodate">
                            <option value="Datum Uzlazno">Datum Uzlazno</option>
                            <option value="Datum Silazno">Datum Silazno</option>
                            <option value="Naziv Uzlazno">Naziv Uzlazno</option>
                            <option value="Naziv Silazno">Naziv Silazno</option>
                            <option value="Cena Uzlazno">Cena Uzlazno</option>
                            <option value="Cena Silazno">Cena Silazno</option>
                        </select>
                        <button id="searchBtn" v-on:click="pretraga()" type="button"> Pretraga </button>
                    </form>
                </div>
                <div id="kupac-manifestacije-border">
                    <div id="kupac-manifestacije-unutra">
                        <div id="unutrasnjost-kupac" v-for="mani in listaFestivala" v-on:click="prikaziManifestaciju(mani)">
                            <div id="manifestacijicaKupac">
                                <div id="manifestacijicaMain-naslov">
                                    <h3>{{mani.tipManifestacije}}</h3>
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
            <div id="kupac-karte">
                <div id="kupac-filterKarti">
                    <input v-model="naziv1" id="velikiInput1" type="text" placeholder="Naziv Manifestacije">
                    <input v-model="lokacija1" id="velikiInput1" type="text" placeholder="Grad">
                    <input v-model="cenaOd1" id="maliInput1" type="text" placeholder="Cena Od">
                    <input v-model="cenaDo1" id="maliInput1" type="text" placeholder="Cena Do">
                    </br>
                    </br>
                    <label class="labelSelect">Datum Od </label>
                    <input v-model="datumOd1" id="datumInput1" type="date" placeholder="Datum Od">
                    <label class="labelSelect">Datum Do </label>
                    <input v-model="datumDo1" id="datumInput1" type="date" placeholder="Datum Do">
                  
                    <form id="velikiSelect">
                        <label class="labelSelect" for="selectRasprodate">Rasprodanost Karata: </label>
                        <select v-model="rasprodanost1" id="selectRasprodate1">
                            <option value="sveProdate">Sve</option>
                            <option value="Nerasprodate">Nerasprodate</option>  
                            <option value="Rasprodate">Rasprodate</option>
                        </select>
                        <label class="labelSelect" for="selectTip">Tip Manifestacije: </label>
                        <select v-model="tip1" id="selectTip1">
                            <option value="sviTipovi">Sve</option>
                            <option value="koncert">Koncert</option>  
                            <option value="festival">Festival</option>
                            <option value="pozoriste">Pozoriste</option>
                            <option value="rejv">Rejv</option>
                            <option value="rodjendan">Rodjendan</option>
                            <option value="svadba">Svadba</option>
                        </select>

                        <button id="searchBtnKarta" v-on:click="pretragaKarte()" type="button"> Pretraga </button>

                    </form>
                </div>
                <div id="kupac-karte-border">
                    <div id="kupac-karte-unutra">
                        <div v-for="nesto in listaKarata" v-on:click="prikaziKartu(nesto)">
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
                                        <h1>{{nesto.tipKarte.slice(0, 3)}}</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="prodavac-karta-info1">
                    <h1> Lokacija Manifestacije </h1>
                    <div id="kupac-karta-mapa">
                    </div>
                </div>
                <button id="cancelKartaBtn" v-on:click="otkaziKartu()"> Otkazi Kartu </button>
            </div>
 
            <div id="kupac-profil">
                <div id="kupac-profil-levo">
                    <h1> Ime </h1>
                    <input v-model="korisnik.ime" type="text" class="input-login">
                    </br>
                    </br>
                    </br>
                    <h1> Korisnicko Ime </h1>
                    <input readonly v-model="korisnik.korisnickoIme" type="text" class="input-login">
                    </br>
                    </br>
                    </br>
                    <h1> Datum Rodjenja </h1>
                    <input readonly v-model="datumRodjenja" type="text" id="main-datum" class="input-login">
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
        prikazMapa: async function(geoSirina, geoDuzina) {
            let self = this;
            var vectorSource = new ol.source.Vector({});
            var vectorLayer = new ol.layer.Vector({ source: vectorSource });

            var map = new ol.Map({
                target: 'kupac-karta-mapa',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    }), vectorLayer
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([19.2521, 42.241212]),
                    zoom: 13
                })
            });
            var marker;

            setMarker = function(position) {
                marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat(position)));
                vectorSource.addFeature(marker);
            }
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
            document.getElementById('kupac-karte').style.display = 'none';
            document.getElementById('kupac-profil').style.display = 'none';
            document.getElementById('prodavac-karta-info1').style.display = 'none';
            document.getElementById('cancelKartaBtn').style.display = 'none';
        },
        rezervisiKartu: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('rezKartuKupac').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-manifestacije').style.display = 'block';
        },
        pregledKarata: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('pregledKarteKupac').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-karte').style.display = 'block';
        },
        izmeniProfil: function() {
            this.unselectSve();
            this.hideAll();
            document.getElementById('izmeniProfilKupac').style.backgroundColor = "#4d4d4d";
            document.getElementById('kupac-profil').style.display = 'block';
        },
        mainMenu: function() {
            this.$router.push("/");
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
        otkaziKartu: function() {
            const { karta } = this;
            axios
                .get('/otkaziKartu', {
                    params: {
                        karta
                    }
                })

            swal({
                title: "Success!",
                text: "Uspesno otkazivanje!",
                type: "success"
            }).then(function() {
                location.reload()
            });
        },
        prikaziKartu: function(karta) {
            document.getElementById('prodavac-karta-info1').style.display = 'block';
            document.getElementById('cancelKartaBtn').style.display = 'block';
            this.karta = karta.idKarte
            var geosirina = karta.lokacija.geoSirina
            var geodzuzina = karta.lokacija.geoDuzina
                //this.prikazMapa(geosirina, geodzuzina)
        },
        pretragaKarte: function() {
            document.getElementById('prodavac-karta-info1').style.display = 'none';
            document.getElementById('cancelKartaBtn').style.display = 'none';

            const { naziv1, lokacija1, cenaOd1, cenaDo1, datumOd1, datumDo1, rasprodanost1, tip1 } = this;
            var korisnickoIme = JSON.parse(localStorage.getItem('user')).korisnickoIme
            console.log(korisnickoIme)
            axios
                .get('/pretragaManifestacijaKarta', {
                    params: {
                        naziv1,
                        lokacija1,
                        cenaOd1,
                        cenaDo1,
                        datumOd1,
                        datumDo1,
                        rasprodanost1,
                        tip1,
                        korisnickoIme
                    }
                }).then((response) => {
                    console.log(response.data)
                    this.listaKarata = response.data
                }).catch(error => {
                    console.log(error.response.data)
                })
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
    },

    mounted() {
        this.prikazMapa();
        this.rezervisiKartu();
        this.korisnik = JSON.parse(localStorage.getItem('user'))
        this.datumRodjenja = this.korisnik.datumRodjenja.day + '-' + this.korisnik.datumRodjenja.month + '-' + this.korisnik.datumRodjenja.year
        var username = JSON.parse(localStorage.getItem('user')).korisnickoIme
        this.stariUsername = username
        this.korisnik.stariUsername = this.stariUsername

        axios
            .post('/getSveKarteKorisnik', {
                korisnickoIme: username
            })
            .then(response => {
                this.listaKarata = response.data
            })
        axios
            .get('/getSveManifestacije')
            .then(response => {
                this.listaFestivala = response.data
            })

        this.sortBy = "Datum Uzlazno"
        this.pretraga()
    }
});