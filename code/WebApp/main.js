function search() {
    var terms = $('#terms').val();
    if(terms) {
        //alert(terms);
        get();
        //makeCorsRequest();
        //httpGet('http://127.0.0.1:8983/solr/atracoes/select?q=title:castelo&wt=json&indent=true');
    }
}


function get() {
 /*   $.get( "http://127.0.0.1:8983/solr/atracoes/select?q=title:castelo&wt=json&indent=true", function( data ) {
        alert(data);
        //$( ".result" ).html( data );
       // alert( "Load was performed." );
    });*/
  /*  
    $.ajax({
        type:'GET',
        url: "https://www.google.pt/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8",
        data: {},
        success: function(data) {
            //console.log(data);
            alert(data);
  }, error: function(jqXHR, textStatus, errorThrown) {
      alert(errorThrown);
   //console.log(errorThrown);
  }
}); */
    
    $.ajax({
dataType: 'jsonp',
data: 'id=10',
jsonp: 'myCallbackFunction',
url: 'http://127.0.0.1:8983/solr/atracoes/select?q=title:castelo&wt=json&indent=true',
success: function () {
    alert("AA");
// do stuff
},
});
}

function myCallbackFunction(data){
       // $('body').text(data.response);
    alert(data);
    }


// Create the XHR object.
function createCORSRequest(method, url) {
  var xhr = new XMLHttpRequest();
  if ("withCredentials" in xhr) {
    // XHR for Chrome/Firefox/Opera/Safari.
    xhr.open(method, url, true);
  } else if (typeof XDomainRequest != "undefined") {
    // XDomainRequest for IE.
    xhr = new XDomainRequest();
    xhr.open(method, url);
  } else {
    // CORS not supported.
    xhr = null;
  }
  return xhr;
}

// Helper method to parse the title tag from the response.
function getTitle(text) {
  return text.match('<title>(.*)?</title>')[1];
}

// Make the actual CORS request.
function makeCorsRequest() {
  // All HTML5 Rocks properties support CORS.
  var url = 'http://127.0.0.1:8983/solr/atracoes/select?q=title:castelo&wt=json&indent=true';

  var xhr = createCORSRequest('GET', url);
  if (!xhr) {
    alert('CORS not supported');
    return;
  }

  // Response handlers.
  xhr.onload = function() {
    var text = xhr.responseText;
    var title = getTitle(text);
    alert('Response from CORS request to ' + url + ': ' + title);
  };

  xhr.onerror = function() {
    alert('Woops, there was an error making the request.');
  };

  xhr.send();
}



function httpGet(theUrl)
{
    var xmlHttp = null;

    xmlHttp = new XMLH
    ttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
