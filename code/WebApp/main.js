var searchphrase = "";

//$(document).ready(function() {dbtest();});
$(document).ready(function() {getArticleImage()});
function search() {
    $('#results_header').empty();
    $('#results').empty();
    
    var terms = $('#terms').val();
    searchphrase = terms;
    if(terms) {
        terms = terms.trim();
        var words = terms.replace(" ", "+");
        var url='http://localhost:8983/solr/atracoes/select?q=title:"'+words+'"&version=2.2&start=0&rows=1000&indent=on&wt=json&callback=?&json.wrf=on_data';
        $.getJSON(url).fail(function(jqXHR, textStatus, errorThrown) {
          if(textStatus != "parsererror") {
           
    var header = [
        '<h1><strong class="text-danger">Solr Server not Available</strong></h1>',
		'<h2 class="lead"> Request failed: net::ERR_CONNECTION_REFUSED</h2>'
     ].join('\n');
     
        $('#results_header').append(header);
          }   
    });
    }
}

function getArticleImage() {
    var url = 'http://pt.wikipedia.org/w/api.php?action=query&titles='+'Casa_de_Serralves'+'&prop=pageimages&format=json&pithumbsize=100';
    $.getJSON(url).done(function(data){alert(JSON.stringify(data));}) ;
    alert("aquiiiiiii");
}

function on_data2(data) {
    var docs = data.response.docs;
    console.log(JSON.stringify(docs));
}

/*
function str2ab(str) {
  var buf = new ArrayBuffer(str.length*2); // 2 bytes for each char
  var bufView = new Uint16Array(buf);
  for (var i=0, strLen=str.length; i<strLen; i++) {
    bufView[i] = str.charCodeAt(i);
  }
  return buf;
}

function dbtest() {
    var Uints = new Uint8Array(r.result);
    db = new SQL.Database(Uints);
    
    var uInt8 = new Uint8Array(str2ab("atracoes.db"));
    console.log(uInt8);
    //Create the database
    var db = new SQL.Database(uInt8);
    // alert("QUI");
    // Run a query without reading the results
    //db.run("CREATE TABLE test (col1, col2);");
    // Insert two rows: (1,111) and (2,222)
    //db.run("INSERT INTO test VALUES (?,?), (?,?)", [1,111,2,222]);
   
    // Prepare a statement
    var stmt = db.prepare("SELECT * FROM category");
    alert("QUI");
    stmt.getAsObject(); // {col1:1, col2:111}

    // Bind new values
  //  stmt.bind();
    while(stmt.step()) { //
        var row = stmt.getAsObject();
        alert(JSON.stringify(row));
    }
}*/

/*
$.ajaxSetup({
      error : function(jqXHR, textStatus, errorThrown) {
          if(textStatus != "parsererror") {
           
    var header = [
        '<h1><strong class="text-danger">Solr Server not Available</strong></h1>',
		'<h2 class="lead"> Request failed: net::ERR_CONNECTION_REFUSED</h2>'
     ].join('\n');
     
        $('#results_header').append(header);
          }   
    }
});*/

 function on_data(data) {
      $('#results').empty();
        var docs = data.response.docs;
       // console.log(JSON.stringify(docs));
        $.each(docs, function(i, item) {
            printResult(item);
        });

        var total = 'Found ' + docs.length + ' results';
     
     var header = [
        '<h1>Search Results</h1>',
		'<h2 class="lead"><strong class="text-danger">'+docs.length+'</strong> results were found for the search for <strong class="text-danger">'+searchphrase+'</strong></h2>'
     ].join('\n');
     
        $('#results_header').append(header);
}

function printResult(item) {
    
    var result_row = [
        '<article class="search-result row">',
			'<div class="col-xs-12 col-sm-12 col-md-3">',
            '<a href="'+ item.url +'" title="'+item.title+'" class="thumbnail">',
                '<img src="'+"http://lorempixel.com/250/140/people"+'" alt="'+item.title+'" /></a>',
			'</div>',
			'<div class="col-xs-12 col-sm-12 col-md-2">',
				'<ul class="meta-search">',
					'<li><i class="glyphicon glyphicon-calendar"></i> <span>'+item.pageid+'</span></li>',
					'<li><i class="glyphicon glyphicon-time"></i> <span>'+item.categoryid+'</span></li>',
					'<li><i class="glyphicon glyphicon-tags"></i> <span>'+item.lastchange.replace(
    /^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/,
    "$1/$2/$3 $4:$5:$6")+'</span></li>',
				'</ul>',
			'</div>',
			'<div class="col-xs-12 col-sm-12 col-md-7 excerpet">',
				'<h3><a href="'+item.url+'" title="">'+item.title+'</a></h3>',
				'<p>'+item.first_paragraph+'</p>',
			'</div>',
			'<span class="clearfix borda"></span>',
		'</article>"'
                     ].join('\n');
    
    $('#results').append($(result_row));
}