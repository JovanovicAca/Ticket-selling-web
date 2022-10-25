const Home = { template: '<Home></Home>' }
const Admin = { template: '<Admin></Admin>' }
const Kupac = { template: '<Kupac></Kupac>' }
const Prodavac = { template: '<Prodavac></Prodavac>' }
const Manifestacija = { template: '<Manifestacija></Manifestacija>' }

const router = new VueRouter({
    mode: 'hash',
    routes: [
        { path: '/', component: Home },
        { path: '/admin', component: Admin },
        { path: '/kupac', component: Kupac },
        { path: '/prodavac', component: Prodavac },
        { path: '/manifestacija', component: Manifestacija },
    ]
});
var app = new Vue({
    router,
    el: '#main-div',

})