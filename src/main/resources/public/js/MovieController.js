function createMovie() {
    var movie = {
        movie: {id: $("#addMovieList").val()},
    };

    $.ajax({
            type: "POST",
            url: "/api/addMovie",
            data: JSON.stringify(booking),
            contentType: "application/json; charset=utf-8",
            success: function(result){
            $.alert({
                                 title: "Movie added!",
                                 content: "",
                           });
            }
        });

$(document).ready(function () {
    initializeTable();
    $("#submitButton").click(function () {
        addMovie();
    });