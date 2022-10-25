Vue.component("Manifestacija", {
    data: function() {
        return {
            listaFestivala: [],
            prikazanPassword: false,
            proslava: "",
            slobodanBrojMesta: "",
            korisnik: "",

            tipKarte: "",
            kolicina: "",
            ukupnaCena: "",
            idManifestacije: "",
            korisnickoIme: "",

        }
    },
    template: ` 
    <div id="homeKupac">
        <link rel="stylesheet" href="CSS/manifestacija.css" type="text/css">

        <div id="kupac-menu" class="kupacWindow">
            <div id="img-kupacMenuDiv">
                <img id="img-kupacMenu" src="RES/tiket.png">
            </div>
        </div>

        <div id="kupac-display" class="kupacWindow">
            <div id="manifestacija-display">

                <div id="manifestacija-slika">
                    <h1> Poster Manifestacije </h1>
                </div>

                <div id="manifestacija-mapa">
                    <h1> Mapa Manifestacije </h1>
                </div>

                <div id="manifestacija-inputi">
                    <h3 class="manif-naslov"> Naziv: {{proslava.naziv}} </h3>
                    <h3 class="manif-naslov"> Tip manifestacije: {{proslava.tipManifestacije}} </h3>
                    <h3 class="manif-naslov"> Ukupan broj Mesta: {{proslava.brojMesta}} </h3>
                    <h3 class="manif-naslov"> Popunjen broj Mesta: {{slobodanBrojMesta}} </h3>
                    <h3 class="manif-naslov"> Datum i Vreme: {{formatDate(proslava.datumOdrzavanja)}}</h3>
                </div>

                <div id="manifestacija-desno">
                    <h1> Komentari </h1>
                    <div id="manifestacija-komentari-border">
                        <div id="manifestacija-komentari-intro">
                            <div v-for="nesto in listaFestivala">
                                <div id="komentar-box">
                                    <div id="komentar-profile">
                                        <img id="img-komentar" src="RES/korisnik.png">
                                        </br></br>
                                        <h6> blagojevic.uros99 </h6>
                                    </div>
                                    <div id="komentar-text">
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="manifestacija-mojKom">
                        <h2> Ostavi Komentar </h2>
                        <div id="mojKom-border">
                            <textarea type="text" id="mojKom-input" placeholder="Unesite Vas Komentar"/>
                            </br>
                            <button type="submit" id="komentarBtn"> Dodaj Komentar </button>
                        </div>
                    </div>

                </div>
            </div>

            <div id="manifestacija-reg-aktivna">
                <div id="manifestacija-mapa2">
                    <h1> Mapa Manifestacije </h1>
                </div>

                <div id="manifestacija-slika2">
                    <h1> Poster Manifestacije </h1>
                </div>

                <div id="manifestacija-inputi2">
                    <h3 class="manif-naslov"> Naziv: {{proslava.naziv}} </h3>
                    <h3 class="manif-naslov"> Tip manifestacije: {{proslava.tipManifestacije}} </h3>
                    <h3 class="manif-naslov"> Ukupan broj Mesta: {{proslava.brojMesta}} </h3>
                    <h3 class="manif-naslov"> Popunjen broj Mesta: {{slobodanBrojMesta}} </h3>
                    <h3 class="manif-naslov"> Datum i Vreme: {{formatDate(proslava.datumOdrzavanja)}}</h3>
                </div>

                <div id="manifestacija-rezKartu">
                    <h2> Odaberite Tip Karte </h2>
                    </br>
                    <button type="submit" v-on:click="odaberiKartu(0)" id="regKarta" class="tip-karte-rez" v-on:change="cenaHandler()"> Regular </button>
                    <button type="submit" v-on:click="odaberiKartu(1)" id="fanKarta" class="tip-karte-rez" v-on:change="cenaHandler()"> Fan Pit </button>
                    <button type="submit" v-on:click="odaberiKartu(2)" id="vipKarta" class="tip-karte-rez" v-on:change="cenaHandler()"> Vip </button>
                </div>

                <div id="manif-kolicina">
                    <h3> Odaberite Kolicinu :</h3>
                    <input v-model="kolicina" id="karta-kolicina2" type="number" min="0" @change="cenaHandler"/>
                    </br>
                    <h3> Status Korisnika : {{korisnik.tipKupca.imeTipa}}</h3>
                    </br>
                    <h3> Popust : {{korisnik.tipKupca.popust}} %</h3>
                    </br>
                    <h3> Ukupna Cena :</h3>
                    <input v-model="ukupnaCena" id="karta-kolicina3" type="text" readonly/>
                    </br>
                    <button id="kupiKartuBtn" v-on:click="kupiKartu()">Kupi Kartu </button>
                </div>
            </div>

            <div id="manifestacija-nereg-gotova">

                <div id="manifestacija-slika3">
                    <h1> Poster Manifestacije </h1>
                </div>

                <div id="manifestacija-inputi3">
                    <h3 class="manif-naslov"> Naziv: {{proslava.naziv}} </h3>
                    <h3 class="manif-naslov"> Tip manifestacije: {{proslava.tipManifestacije}} </h3>
                    <h3 class="manif-naslov"> Ukupan broj Mesta: {{proslava.brojMesta}} </h3>
                    <h3 class="manif-naslov"> Popunjen broj Mesta: {{slobodanBrojMesta}} </h3>
                    <h3 class="manif-naslov"> Datum i Vreme: {{proslava.datumOdrzavanja}}</h3>
                </div>

                <div id="manifestacija-mapa3">
                </div>
            </div>

            <div id="manifestacija-admin">
                <div id="manifestacija-slika">
                    <h1> Poster Manifestacije </h1>
                </div>

                <div id="manifestacija-mapa">
                    <h1> Mapa Manifestacije </h1>
                </div>

                <div id="manifestacija-inputi">
                    <h3 class="manif-naslov"> Naziv: {{proslava.naziv}} </h3>
                    <h3 class="manif-naslov"> Tip manifestacije: {{proslava.tipManifestacije}} </h3>
                    <h3 class="manif-naslov"> Ukupan broj Mesta: {{proslava.brojMesta}} </h3>
                    <h3 class="manif-naslov"> Popunjen broj Mesta: {{slobodanBrojMesta}} </h3>
                    <h3 class="manif-naslov"> Datum i Vreme: {{proslava.datumOdrzavanja}}</h3>
                </div>

                <div id="manifestacija-desno">
                    <h1 id="komNaslov"> Komentari </h1>
                    <div id="manifestacija-komentari-border2">
                        <div id="manifestacija-komentari-intro">
                            <div v-for="nesto in listaFestivala">
                                <div id="komentar-box">
                                    <div id="komentar-profile">
                                        <img id="img-komentar" src="RES/korisnik.png">
                                        </br></br>
                                        <h6> blagojevic.uros99 </h6>
                                    </div>
                                    <div id="komentar-text">
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="admin-odobriManif">
                    <h2> Odobravanje Manifestacije </h2>
                    <h2> --------------------------------- </h2>
                    </br>
                    <h2> Trenutnu Manifestaciju mogu videti samo Admini i Organizator Manifestacije. </h2>
                    </br>
                    <h2> Da li kao Admin odobravate odrzavanje ove Manifestacije ?  </h2>
                    </br> </br> </br> </br> </br>
                    <button id="yesManif"> Odobravam </button>
                    </br>
                    <button id="noManif"> Ne Odobravam </button>
                </div>
            </div>

            <div id="manifestacija-prod">
                <div id="manifestacija-mapa2">
                    <h1> Mapa Manifestacije </h1>
                </div>

                <div id="manifestacija-slika2">
                    <h1> Poster Manifestacije </h1>
                </div>

                <div id="manifestacija-inputi2">
                    <h3 class="manif-naslov"> Naziv :</h3>
                    <input id="nazivM" type="text"/>
                    <h3 class="manif-naslov"> Tip: </h3>
                    <input id="tipM" type="text"/>
                    <h3 class="manif-naslov"> Vreme :</h3>
                    <input id="vremeM" type="text"/>
                    <h3 class="manif-naslov"> Datum :</h3>
                    <input id="datumM" type="text"/>
                    <h3 class="manif-naslov"> Ukupan broj Mesta:</h3>
                    <input id="brojM" type="text"/>
                    <h3 class="manif-naslov"> Preostao broj Mesta:</h3>
                    <input id="ostatakM" type="text" readonly/>
                    <button id="izmeneProdavacBtn" type="submit"> Sacuvaj Izmene </button>
                </div>

                <div id="manifestacija-desno">
                    <h1 id="komNaslov"> Komentari </h1>
                    <div id="manifestacija-komentari-border2">
                        <div id="manifestacija-komentari-intro">
                            <div v-for="nesto in listaFestivala">
                                <div id="komentar-box">
                                    <div id="komentar-profile">
                                        <img id="img-komentar" src="RES/korisnik.png">
                                        </br></br>
                                        <h6> blagojevic.uros99 </h6>
                                    </div>
                                    <div id="komentar-text">
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                        <p> Ukoliko ste zeljni i voljni da se prijavite za konkurenciju na nasem fakultetu to bi bilo jako lepo sa vase strane </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>		  
`,
    methods: {
        kupiKartu: function() {
            const { tipKarte, kolicina, ukupnaCena, idManifestacije, korisnickoIme } = this;

            axios
                .get('/kupiKartu', {
                    params: {
                        tipKarte,
                        kolicina,
                        ukupnaCena,
                        idManifestacije,
                        korisnickoIme
                    }
                }).then(response => {


                })
            swal({
                title: "Success!",
                text: "Uspesna kupovina!",
                type: "success"
            }).then(function() {
                location.reload()
            });
        },
        cenaHandler: function() {
            var cena = 0
            if (this.tipKarte === "REGULAR") {
                cena = this.kolicina * this.proslava.cenaKarte
            } else if (this.tipKarte === "FAN_PIT") {
                cena = this.kolicina * this.proslava.cenaKarte * 2
            } else if (this.tipKarte === "VIP") {
                cena = this.kolicina * this.proslava.cenaKarte * 4
            }
            cena = cena - (cena * (this.korisnik.tipKupca.popust) / 100)
            this.ukupnaCena = cena
        },

        formatDate(d) {
            return moment(d).format('lll');
        },
        izracunaj: function(id) {
            axios
                .get('/slobodanBrojMesta', {
                    params: {
                        id
                    }
                }).then((response) => {
                    this.slobodanBrojMesta = response.data
                }).catch(error => {
                    console.log(error.response.data);
                })
        },
        hideAll: function() {
            document.getElementById('manifestacija-display').style.display = 'none';
            document.getElementById('manifestacija-reg-aktivna').style.display = 'none';
            document.getElementById('manifestacija-nereg-gotova').style.display = 'none';
            document.getElementById('manifestacija-admin').style.display = 'none';
            document.getElementById('manifestacija-prod').style.display = 'none';
        },
        odaberiKartu: function(choice) {
            document.getElementById('vipKarta').style.backgroundColor = "#000";
            document.getElementById('fanKarta').style.backgroundColor = "#000";
            document.getElementById('regKarta').style.backgroundColor = "#000";
            if (choice == 0) {
                document.getElementById('regKarta').style.backgroundColor = "rgb(59, 59, 59)";
                this.tipKarte = "REGULAR"
                this.cenaHandler()
            }
            if (choice == 1) {
                document.getElementById('fanKarta').style.backgroundColor = "rgb(59, 59, 59)";
                this.tipKarte = "FAN_PIT"
                this.cenaHandler()
            }
            if (choice == 2) {
                document.getElementById('vipKarta').style.backgroundColor = "rgb(59, 59, 59)";
                this.tipKarte = "VIP"
                this.cenaHandler()
            }
        },
        prikazMapa: async function() {
            let self = this;
            var vectorSource = new ol.source.Vector({});
            var vectorLayer = new ol.layer.Vector({ source: vectorSource });

            var map = new ol.Map({
                target: 'manifestacija-mapa',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    }), vectorLayer
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([19.8335, 45.2481]),
                    zoom: 13
                })
            });

            var map1 = new ol.Map({
                target: 'manifestacija-mapa2',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    }), vectorLayer
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([19.8335, 45.2481]),
                    zoom: 13
                })
            });

            var map2 = new ol.Map({
                target: 'manifestacija-mapa3',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    }), vectorLayer
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([19.8335, 45.2481]),
                    zoom: 13
                })
            });
        }
    },
    beforeMount() {
        var user = JSON.parse(localStorage.getItem('user'));
        this.korisnik = user
        this.korisnickoIme = user.korisnickoIme
        var manifestacija = JSON.parse(localStorage.getItem('manifestacija'));
        this.proslava = manifestacija;
        this.idManifestacije = this.proslava.id
    },
    mounted() {
        this.hideAll();
        this.prikazMapa();
        var manifestacija = JSON.parse(localStorage.getItem('manifestacija'));
        this.proslava = manifestacija;
        this.izracunaj(manifestacija.id);
        var user = JSON.parse(localStorage.getItem('user'));
        this.korisnik = user
        if (user == null) {
            document.getElementById('manifestacija-nereg-gotova').style.display = 'block';
        } else {
            if (user.uloga == "KUPAC" && manifestacija.rasprodata) {
                document.getElementById('manifestacija-display').style.display = 'block';
            } else if (user.uloga == "KUPAC" && !manifestacija.rasprodata) {
                document.getElementById('manifestacija-reg-aktivna').style.display = 'block';
            } else if (user.uloga == "ADMINISTRATOR") {
                if (manifestacija.statusManifestacije == "NEAKTIVNA") {
                    document.getElementById('manifestacija-admin').style.display = 'block';
                    document.getElementById('admin-odobriManif').style.display = 'block';
                    document.getElementById('komNaslov').style.display = 'none';
                    document.getElementById('manifestacija-komentari-border2').style.display = 'none';
                } else {
                    document.getElementById('manifestacija-admin').style.display = 'block';
                    document.getElementById('admin-odobriManif').style.display = 'none';
                    document.getElementById('manifestacija-desno').style.display = 'block';
                }
            } else if (user.uloga == "PRODAVAC") {
                document.getElementById('manifestacija-prod').style.display = 'block';
            }
        }
    },
});