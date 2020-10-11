$(document).ready(function() {
    var bottom = $(
    "<div class='row'>" +
        "<div class='column col-xl-6 col-lg-12 col-sm-12' id='our-mission'>" +
            "<h3>Our Mission</h3>" +
            "<p style='padding:50px'>" +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
            "</p>" +
            "</div>" +
        "<div class='column col-xl-6 col-lg-12 col-sm-12' id='our-mission'>" +
            "<h3>About</h3>" +
            "<p style='padding:50px'>" +
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." +
                "</p>" +
            "</div>" +
        "</div>" +
    "<div class='row'>" +
        "<div class='column col-xl-6 col-md-12 col-sm-12 col-xs-12'>" +
            "<div class='form-group row justify-content-center align-items-center'>" +
                "<label form='description-input' class='col-form-label'></label>" +
                "<div class='col-xl-7 col-lg-12 col-sm-12'>" +
                    "<input type='text' name='description-input' class='form-control' id='description-input'>" +
                    "</div>" +
                "<button type='button' class='btn btn-outline-warning btn-lg' id='send'>Send Feedback</button>" +
                "</div>" +
            "</div>" +
        "<div class='column col-xl-6 col-md-12 col-sm-12 col-xs-12' style='padding-left: 100px'>" +
            "<ul class='row' style='list-style:none;text-align:center;color:white'>" +
                "<a class='col-3' href='#'>" +
                    "<li>Home</li>" +
                    "</a> | " +
                "<a class='col-3' href='#'>" +
                    "<li>Heroes</li>" +
                    "</a> | " +
                "<a class='col-3' href='#'>" +
                    "<li>Sightings</li>" +
                    "</a>" +
                "</ul>" +
            "</div>" +
        "</div>" +
    "</div>" +
    "<hr style='background-color:rgba(255,255,255,0.1)'>" +
    "<div class='footer'>" +
        "<a href='#' class='fa fa-facebook'></a>" +
        "<a href='#' class='fa fa-linkedin'></a>" +
        "<a href='#' class='fa fa-youtube'></a>" +
        "<a href='#' class='fa fa-instagram'></a>" +
        "<a href='#' class='fa fa-reddit'></a>" +
        "<br><br>" +
        "@Copyright Bob, Narish, Timmy" +
    "</div>"
    );
    $(".bottom").append(bottom);
})