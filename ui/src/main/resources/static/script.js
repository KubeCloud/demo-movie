/**
 * Created by kaspernissen on 10/06/2016.
 */

function getMovies(id){
    $('#movie-container').removeClass('hide');

    $.get("http://192.168.1.51:8001/movies/"+id, function( data ) {
        $("#movies").empty();
        data.forEach(function(element){
            $("#movies").append('<div class="col s3"><div class="card"><div class="card-image"><img src="'+element.pictureUrl+'"><span class="card-title">'+element.title+'</span></div></div></div>');
        })
    });
}

$('#card-mje').click(function(){
    getMovies(1);
});

$('#card-kni').click(function () {
    getMovies(2);
});

