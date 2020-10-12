/*
.html
.text
.value
.append
.hide
.show
.toggle
.ready
.remove
.removeClass
.addClass
.on
.hover
*/
console.log("fdsfsf");
var lightTheme = false;

$("#toggleTheme").on('click', function () {
    console.log("Gfdg");
    if (lightTheme == false) {
        console.log(lightTheme);
        console.log("light");
        lightMode();
    } else if (lightTheme == true) {
        console.log(lightTheme);
        console.log("dark");
        darkMode();
    }
})


function darkMode() {
    $("body, .container-fluid, .bottom").css({'background-color': 'black'});
    $(".overlay").css({'background-color': 'rgba(0, 0, 0, 0.5)'});
    $(".choose ul").css({'background-color': 'rgba(0, 0, 0, 0.75)', 'color': 'white'});
    $(".alert").addClass("bg-danger");

    $(".hero-form h3").addClass("white-text").removeClass("black-text");
    document.querySelector(".themeStatus").innerHTML = 'Dark';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('assets/cover-4.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(0, 0, 0, 0.75)";
    })

    document.querySelector(".bottom p").style.color = 'white';
    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'white';
    })

    var texts = document.querySelectorAll(".white-text");
    texts.forEach((e) => {
        e.style.color = "white";
    })

    document.getElementById("send").className = 'btn btn-outline-warning btn-lg';

    document.getElementById("toggleTheme").className = 'btn btn-outline-info btn-lg';

    $(".navBar .btn, .news, .other").addClass("btn-outline-primary").removeClass("btn-primary");
    $(".registerLogin .register").addClass("btn-outline-success").removeClass("btn-success");
    $(".registerLogin .login").addClass("btn-outline-danger").removeClass("btn-danger");

    $(".swiper-container img").css({'background-color': 'rgba(0, 0, 0, 0.5)'});
    $(".swiper-pagination-bullets").css({'background-color': 'rgba(0, 0, 0, 0.15)'});
    $(".swiper-container").css({'background-color': 'rgba(0, 0, 0, 0.5)'});
    $(".table").addClass("table-dark").removeClass("table-light");
    $(".results .border").addClass("border-dark").removeClass("border-light");
    $(".revealMap h3").css({'background-color': 'rgb(0, 0, 0)', 'color': 'rgb(255, 255, 255)'});
    lightTheme = false;
}

function lightMode() {
    $("body, .container-fluid, .bottom, .choose ul").css({'background-color': 'white'});
    $(".overlay").css({'background-color': 'rgba(255, 255, 255, 0.25)'});
    $(".choose ul").css({'background-color': 'rgba(255, 255, 255, 0.75)', 'color': 'black'});
    $(".alert").removeClass("bg-danger");

    $(".hero-form h3").addClass("black-text").removeClass("white-text");

    document.querySelector(".themeStatus").innerHTML = 'Light';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('https://cdn.wallpapersafari.com/77/60/Qmy5KZ.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(255, 255, 255, 0.75)";
    })
    var texts = document.querySelectorAll(".white-text");
    texts.forEach((e) => {
        e.style.color = "black";
    })

    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'black';
    })
    document.getElementById("send").className = 'btn btn-warning btn-lg';

    // REDUCE REDUNDANCY
    document.getElementById("toggleTheme").className = 'btn btn-info btn-lg';

    $(".navBar .btn, .news, .other").addClass("btn-primary").removeClass("btn-outline-primary");
    $(".registerLogin .register").addClass("btn-success").removeClass("btn-outline-success");
    $(".registerLogin .login").addClass("btn-danger").removeClass("btn-outline-danger");

    $(".swiper-container img").css({'background-color': 'rgba(255, 255, 255, 0.25)'});
    $(".swiper-pagination-bullets").css({'background-color': 'rgba(255, 255, 255, 0.05)'});
    $(".swiper-container").css({'background-color': 'rgba(255, 255, 255, 0.15)'});
    $(".table").addClass("table-light").removeClass("table-dark");
    $(".results .border").addClass("border-light").removeClass("border-dark");
    $(".revealMap h3").css({'background-color': 'rgb(255, 255, 255)', 'color': 'rgb(0, 0, 0)'});

    lightTheme = true;
}


$("#nav-about").on('click', function () {
    var element = document.getElementById('about');
    var position = element.getBoundingClientRect();
    var x = position.left;
    var y = position.top;

    window.scroll(x, y);
})

// TOGGLES THE SIGHTINGS GALLERY
var isHidden = false;

/*
$("#sightingID").on('click', function (event) { // INTENDED TO NOT BE HARD-CODED
    let lat = $(this).data("lat");
    let long = $(this).data("long");

    if (isHidden == true) {
        revealLocation();
    } else if (isHidden == false) {
        hideLocation(lat, long);
    }
})

 */

/*
function hideLocation(latitude, longitude) {
    var sightingID = document.getElementById("sightingID"); // INTENDED TO NOT BE HARD-CODED
    // sightingID.style.backgroundImage = "url('https://miro.medium.com/max/4064/1*qYUvh-EtES8dtgKiBRiLsA.png')"; // INTENDED TO NOT BE HARD-CODED

    const apiKey = "AIzaSyBpD442VLkwZ5_Kp795_zE-UWHy8-6EBQc";
    var mapsrc = "https://www.google.com/maps/embed/v1/place?key=" + apiKey + "&q=" + latitude + ","
        + longitude + "&center=" + latitude + "," + longitude + "&zoom=18";

    sightingID.style.backgroundImage = "url('" + mapsrc + "')";
    isHidden = true;
}

function revealLocation(id) {
    var sightingID = document.getElementById("sightingID"); // INTENDED TO NOT BE HARD-CODED
    // sightingID.style.backgroundImage = "url('https://www.bergmannpc.com/imager/contentimages/project/unc-charlotte-center-city/6658/UNC-Center-City-Campus-Exterior_20d4ef1e03a0bb7e3cb004631c67ab23.jpg')"; // INTENDED TO NOT BE HARD-CODED
    sightingID.style.backgroundImage = "url('https://specials-images.forbesimg.com/imageserve/5d35eacaf1176b0008974b54/960x0.jpg?cropX1=790&cropX2=5350&cropY1=784&cropY2=3349')"; // INTENDED TO NOT BE HARD-CODED
    isHidden = false;
}

 */