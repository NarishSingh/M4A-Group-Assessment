$(document).ready(function () {
    function displayWindowSize() {
        var w = document.documentElement.clientWidth;
        var h = document.documentElement.clientHeight;
        if (w < 1105) {
            var p = " ";

            $("#toggleTheme h5").html(p);
            $("#toggleTheme h5").addClass("fas fa-adjust");

            $("#home h5").html(p);
            $("#home h5").addClass("fas fa-home");

            $("#hero h5").html(p);
            $("#hero h5").addClass("fas fa-address-book");

            $("#sightings h5").html(p);
            $("#sightings h5").addClass("fas fa-eye");

            $("#about h5").html(p);
            $("#about h5").addClass("fas fa-align-right");

        } else if (w > 1105) {
            var toggle = "Theme";
            var home = "Home";
            var hero = "Heroes/Villains";
            var sightings = "Sightings";
            var about = "About";

            $("#toggleTheme h5").html(toggle);
            $("#toggleTheme h5").removeClass("fas fa-adjust");

            $("#home h5").html(home);
            $("#home h5").removeClass("fas fa-home");

            $("#hero h5").html(hero);
            $("#hero h5").removeClass("fas fa-address-book");

            $("#sightings h5").html(sightings);
            $("#sightings h5").removeClass("fas fa-eye");

            $("#about h5").html(about);
            $("#about h5").removeClass("fas fa-align-right");
        }
    }

    window.addEventListener("resize", displayWindowSize);
    displayWindowSize();

//     var tog = $(
//     "<div class='col text-left col-lg-4 col-md-4 col-sm-2 col-xs-12'>" +
//     "<a id='toggleTheme' class='btn btn-outline-info btn-lg'><h5 class='themeStatus' style='display:inline;color:white'>Dark</h5></a>" +
//     "</div>");
    
//     var header = $(
//         "<div class='row m-4 navBar'>" +
//         tog +
//         "<div class='col text-center col-lg-2 col-md-2 col-sm-2 col-xs-12'>" +
//         "<a href='index.html' class='btn btn-outline-primary btn-lg' id='home'><h5>Home</h5></a>" +
//         "</div>" +
//         "<div class='col text-center col-lg-2 col-md-2 col-sm-2 col-xs-12'>" +
//         "<a href='hero' class='btn btn-outline-primary btn-lg' id='hero'><h5>Heroes/Villians</h5></a>" +
//         "</div>" +
//         // "<div class='col text-center col-lg-2 col-md-3 col-sm-3 col-xs-12'>" +
//         //     "<a href='superpower' class='btn btn-outline-primary btn-lg' id='hero'>Powers</a>" +
//         // "</div>" +
//         "<div class='col text-center col-lg-2 col-md-2 col-sm-2 col-xs-12'>" +
//         "<a href='sighting' class='btn btn-outline-primary btn-lg' id='sightings'><h5>Sightings</h5></a>" +
//         "</div>" +
//         // "<div class='col text-center col-lg-2 col-md-3 col-sm-3 col-xs-12'>" +
//         //     "<a href='organization' class='btn btn-outline-primary btn-lg' id='sightings'>Organization</a>" +
//         // "</div>" +
//         // "<div class='col text-center col-lg-2 col-md-3 col-sm-3 col-xs-12'>" +
//         //     "<a href='location' class='btn btn-outline-primary btn-lg' id='sightings'>Location</a>" +
//         // "</div>" +
//         "<div class='col text-center col-lg-2 col-md-2 col-sm-2 col-xs-12'>" +
//         "<a id='about' class='btn btn-outline-primary btn-lg'><h5>About</h5></a>" +
//         "</div>" +
//         "</div>"
//     );
//     $("#toggleTheme").append(navbar);

//     // console.log(header);
//     // tog.on('click', function () {alert(tog) });
// })


})
