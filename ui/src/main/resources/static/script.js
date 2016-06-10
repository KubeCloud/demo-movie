/**
 * Created by kaspernissen on 10/06/2016.
 */

//$('#btn').on('click', function(){alert("Click!")});

$('#btn-martin').on('click', function(){
    $('#movie-container').removeClass('hide');

    $.get("http://localhost:8001/movies/1", function( data ) {
        $("#movies").empty();
        data.forEach(function(element){
            $("#movies").append('<div class="col s3"><div class="card"><div class="card-image"><img src="'+element.pictureUrl+'"><span class="card-title">'+element.title+'</span></div></div></div>');
        })

    });
});

$('#btn-kasper').on('click', function(){
    $('#movie-container').removeClass('hide');

    $.get("http://localhost:8001/movies/2", function( data ) {
        $("#movies").empty();
        data.forEach(function(element){
            $("#movies").append('<div class="col s3"><div class="card"><div class="card-image"><img src="'+element.pictureUrl+'"><span class="card-title">'+element.title+'</span></div></div></div>');
        })

    });
});
