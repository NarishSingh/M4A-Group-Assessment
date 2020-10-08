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

// DARK MODE \\
// var toggle;

// $("#toggleTheme").on("click", toggleTheme());

// function toggleTheme () {
//     if (toggle == true) {
//         console.log(toggle);
//         $(".container-fluid").css({ 'background-color': 'white' });
//         $(".bottom").css({ 'background-color': 'white' });
//         $("p").css({ 'color': 'black' });
//         $(".bottom a").css({ 'color': 'black' });
//         $(".section-2").css({ 'background-color': 'white' });
//         $(".nav ul li").css({ 'background-color': 'rgba(255, 255, 255, 0.534)' });
//         $(".nav a").css({ 'color': 'white' });
//         $(".nav a:hover").css({ 'color': 'grey' });

//         $(".swiper-container img").css({ 'background-color': 'rgba(255, 255, 255, 0.1)' });
//         $(".swiper-pagination-bullets").css({ 'background-color': 'rgba(255, 255, 255, 0.15)' });
//         toggle = false;
//         console.log(toggle);
//     } else if (toggle == false) {
//         console.log(toggle);
//         $(".container-fluid").css({ 'background-color': 'black' });
//         $(".nav ul li").css({ 'background-color': 'rgba(0, 0, 0, 0.534)' });  
//         console.log(toggle);
//         toggle = true;
//     }
// }

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
    document.querySelector("body").style.backgroundColor = 'black';
    document.querySelector(".container-fluid").style.backgroundColor = 'black';
    document.querySelector(".themeStatus").innerHTML = 'Dark';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('assets/cover-4.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(0, 0, 0, 0.75)";
    }) 
    document.querySelector(".bottom").style.backgroundColor = 'black';
    document.querySelector(".bottom p").style.color = 'white';
    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'white';
    })
    document.getElementById("send").className = 'btn btn-outline-warning btn-lg';

    document.getElementById("toggleTheme").className = 'btn btn-outline-info btn-lg';
    document.getElementById("landingPage").className = 'btn btn-outline-primary btn-lg';
    document.getElementById("heroes").className = 'btn btn-outline-primary btn-lg';
    document.getElementById("sightings").className = 'btn btn-outline-primary btn-lg';
    document.getElementById("nav-about").className = 'btn btn-outline-primary btn-lg';
    // document.querySelector().style.color = 'white';
    lightTheme = false;
}

function lightMode() {
    document.querySelector("body").style.backgroundColor = 'white';
    document.querySelector(".container-fluid").style.backgroundColor = 'white';
    document.querySelector(".themeStatus").innerHTML = 'Light';
    var bgimgs = document.querySelectorAll(".bg-img");
    bgimgs.forEach((e) => {
        e.style.backgroundImage = "url('https://cdn.wallpapersafari.com/77/60/Qmy5KZ.jpg')";
    })
    var bgcolors = document.querySelectorAll(".bg-color");
    bgcolors.forEach((e) => {
        e.style.backgroundColor = "rgba(255, 255, 255, 0.75)";
    }) 
    document.querySelector(".bottom").style.backgroundColor = 'white';
    var paragraphs = document.querySelectorAll("p, label, .bottom a, .h3");
    paragraphs.forEach((e) => {
        e.style.color = 'black';
    })
    document.getElementById("send").className = 'btn btn-warning btn-lg';

    // REDUCE REDUNDANCY
    document.getElementById("toggleTheme").className = 'btn btn-info btn-lg';
    document.getElementById("landingPage").className = 'btn btn-primary btn-lg';
    document.getElementById("heroes").className = 'btn btn-primary btn-lg';
    document.getElementById("sightings").className = 'btn btn-primary btn-lg';
    document.getElementById("nav-about").className = 'btn btn-primary btn-lg';
    // document.querySelector("label").style.color = 'black';
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

$("#sightingID").on('click', function () { // INTENDED TO NOT BE HARD-CODED
    if (isHidden == true) {
        revealLocation();
    } else if (isHidden == false) {
        hideLocation();
    }
})

function hideLocation() {
    var sightingID = document.getElementById("sightingID"); // INTENDED TO NOT BE HARD-CODED
    sightingID.style.backgroundImage = "url('https://miro.medium.com/max/4064/1*qYUvh-EtES8dtgKiBRiLsA.png')"; // INTENDED TO NOT BE HARD-CODED
    isHidden = true;
}

function revealLocation() {
    var sightingID = document.getElementById("sightingID"); // INTENDED TO NOT BE HARD-CODED
    sightingID.style.backgroundImage = "url('https://www.bergmannpc.com/imager/contentimages/project/unc-charlotte-center-city/6658/UNC-Center-City-Campus-Exterior_20d4ef1e03a0bb7e3cb004631c67ab23.jpg')"; // INTENDED TO NOT BE HARD-CODED
    isHidden = false;
}