Vue.component("Home", {
    data: function() {
        return {
            korisnik: {
                korisnickoIme: null,
                lozinka: null
            },
            noviKorisnik: {
                korisnickoIme: null,
                lozinka: null,
                ime: null,
                prezime: null,
                datumRodjenja: null,
                pol: null,
            },
            sortirajOpadajuce: true,
            listaFestivala: [],

            naziv: null,
            lokacija: null,
            cenaOd: null,
            cenaDo: null,
            datumOd: null,
            datumDo: null,
            rasprodanost: null,
            tip: null,
            sortBy: null,
        }
    },
    template: ` 
    <div id="appHome">
        <link rel="stylesheet" href="CSS/home.css" type="text/css">
        <div id="main-img" class="mainWindow">
            <img id ="main-tiketImg" src="RES/tiket.png">
        </div>

        <div id="main-window" class="mainWindow">
            <div id="main-navBar">
                <button class="main-navBtn" v-on:click="manifestacijaForm()" type="button">Prikaz Manifestacija</button>
                <button class="main-navBtn" v-on:click="loginForm()" type="button">Prijava na Sistem</button>
                <button class="main-navBtn" v-on:click="registerForm()" type="button">Registracija Naloga</button>
            </div>

            <div id="main-manifestacijeDiv">
                <div id="main-filter">

                    <input v-model="naziv" id="velikiInput" type="text" placeholder="Naziv Manifestacije">
                    <input v-model="lokacija" id="velikiInput" type="text" placeholder="Grad">
                    <input v-model="cenaOd" id="maliInput" type="text" placeholder="Cena Od">
                    <input v-model="cenaDo" id="maliInput" type="text" placeholder="Cena Do">

                    <label class="labelSelect" id="main-datum">Datum Od </label>
                    <input v-model="datumOd" id="datumInput" type="date" placeholder="Datum Od">
                    <label class="labelSelect" id="main-datum">Datum Do </label>
                    <input v-model="datumDo" id="datumInput" type="date" placeholder="Datum Do">
                
                    <form id="velikiSelect">
                        <label class="labelSelect" for="selectRasprodate">Rasprodanost Manifestacije: </label>
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
                <div id="main-manifestacijeBorder">
                    <div id="main-manifestacije">
                        <div id="unutrasnjost-kupac" v-for="mani in listaFestivala" v-on:click="prikaziManifestaciju(mani)">
                            <div id="manifestacijica-main">
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

            <div id="main-login">
                <div id="main-login-text">
                    <h1 id="main-pwd-h2"> Korisnicko Ime</h1>
                    <input v-model="korisnik.korisnickoIme" type="text" class="input-login">
                    <h1 id="main-pwd-h1"> Loznika </h1>
                    <input v-model="korisnik.lozinka" type="password" class="input-login">
                    </br>
                    <button class="main-login-btn" v-on:click="login()" type="button">Prijavi Se</button>
                </div>
            </div>

            <div id="main-register">
                <div id="main-reg-text1">
                    <h1> Ime </h1>
                    <input v-model="noviKorisnik.ime" type="text" class="input-login">
                    <h1 id="main-dole"> Korisnicko Ime </h1>
                    <input v-model="noviKorisnik.korisnickoIme" type="text" class="input-login">
                    <h1 id="main-dole"> Datum Rodjenja </h1>
                    <input v-model="noviKorisnik.datumRodjenja" name="datum-rodjenja-reg" type="date" id="main-datum" class="input-login">
                </div>
                <div id="main-reg-text2">
                    <h1> Prezime </h1>
                    <input v-model="noviKorisnik.prezime" type="text" class="input-login">
                    <h1 id="main-dole"> Loznika </h1>
                    <input v-model="noviKorisnik.lozinka" type="password" class="input-login">
                    <h1 id="main-dole"> Pol </h1>
                    </br>
                    <input v-model="noviKorisnik.pol" type="radio" id="html" value="Musko">
                    <label for="musko">Musko</label>
                    <input v-model="noviKorisnik.pol" type="radio" id="css" value="Zensko">
                    <label for="zensko">Zensko</label>
                </div>
                <button class="main-reg-btn" v-on:click="register()" type="button">Registruj Se</button>
            </div>
        </div>
    </div>		  
`,

    methods: {
        prikaziManifestaciju: function(manifestacija) {
            localStorage.setItem('manifestacija', JSON.stringify(manifestacija))
            this.$router.push('/manifestacija')
        },
        hideAll: function() {
            document.getElementById('main-register').style.display = 'none';
            document.getElementById('main-login').style.display = 'none';
            document.getElementById('main-manifestacijeDiv').style.display = 'none';
        },
        loginForm: function() {
            this.hideAll();
            document.getElementById('main-login').style.display = 'block';
        },
        registerForm: function() {
            this.hideAll();
            document.getElementById('main-register').style.display = 'block';
        },
        manifestacijaForm: function() {
            this.hideAll();
            document.getElementById('main-manifestacijeDiv').style.display = 'block';
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
        podesavanjeDatuma: function() {
            var today = new Date().toISOString().split('T')[0];
            document.getElementsByName('datum-rodjenja-reg')[0].setAttribute('max', today);
        },
        login: function() {
            axios
                .post('/login', {
                    korisnickoIme: this.korisnik.korisnickoIme,
                    lozinka: this.korisnik.lozinka
                })
                .then(response => {
                    localStorage.setItem('user', JSON.stringify(response.data))
                    if (response.data.uloga == "KUPAC") {
                        this.$router.push("/kupac");
                    } else if (response.data.uloga == "PRODAVAC") {
                        this.$router.push("/prodavac")
                    } else if (response.data.uloga == "ADMINISTRATOR") {
                        this.$router.push("/admin")
                    }
                })
                .catch(error => {
                    swal("Error", "Pogresni podaci!", "error")
                })
        },
        register: function() {
            axios
                .post('/register', {
                    korisnickoIme: this.noviKorisnik.korisnickoIme,
                    lozinka: this.noviKorisnik.lozinka,
                    ime: this.noviKorisnik.ime,
                    prezime: this.noviKorisnik.prezime,
                    datumRodjenja: this.noviKorisnik.datumRodjenja,
                    pol: this.noviKorisnik.pol,
                })
                .then(response => {
                    if (response.data === "Zauzeto") {
                        swal("Error", "Username vec postoji!", "error")
                        return
                    }
                    localStorage.setItem('user', JSON.stringify(response.data))
                        //console.log(response.data)
                    this.$router.push("/kupac");
                })
                .catch(error => {
                    swal("Error", "Proverite da li ste uneli sve podatke!", "error")
                })

        },
    },
    mounted() {
        this.manifestacijaForm();
        this.podesavanjeDatuma();
        axios
            .get('/getSveManifestacije')
            .then(response => {
                this.listaFestivala = response.data
            })

        this.sortBy = "Datum Uzlazno"
        this.pretraga()
    }
});