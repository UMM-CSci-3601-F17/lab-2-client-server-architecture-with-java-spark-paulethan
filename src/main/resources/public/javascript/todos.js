// Syntax highlighting for JSON
// from https://stackoverflow.com/questions/4810841/how-can-i-pretty-print-json-using-javascript
function syntaxHighlight(json) {
  json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
  return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
    var cls = 'number';
    if (/^"/.test(match)) {
      if (/:$/.test(match)) {
        cls = 'key';
      } else {
        cls = 'string';
      }
    } else if (/true|false/.test(match)) {
      cls = 'boolean';
    } else if (/null/.test(match)) {
      cls = 'null';
    }
    return '<span class="' + cls + '">' + match + '</span>';
  });
}

// gets todos from the api.
// It adds the values of the various inputs to the requested URl to filter and order the returned todos.
function getFilteredTodos() {
  console.log("Getting all the todos.");

  var HttpThingy = new HttpClient();

  var url = "/api/todos?";
  if(document.getElementById("owner").value != "") {
    url = url + "&owner=" + document.getElementById("owner").value;
  }
  if(document.getElementById("category").value != "") {
    url = url + "&category=" + document.getElementById("category").value;
  }
  if(document.getElementById("status").value != "") {
    url = url + "&status=" + document.getElementById("status").value;
  }
  if(document.getElementById("contains").value != "") {
    url = url + "&contains=" + document.getElementById("contains").value;
  }
  if(document.getElementById("orderBy").value != "") {
    url = url + "&orderBy=" + document.getElementById("orderBy").value;
  }
  if(document.getElementById("limit").value != "") {
    url = url + "&limit=" + document.getElementById("limit").value;
  }

  HttpThingy.get(url, function(returned_json){
    document.getElementById('jsonDump').innerHTML = syntaxHighlight(JSON.stringify(JSON.parse(returned_json), null, 2));
  });
}

/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
  // We'll take a URL string, and a callback function.
  this.get = function(aUrl, aCallback){
    var anHttpRequest = new XMLHttpRequest();

    // Set a callback to be called when the ready state of our request changes.
    anHttpRequest.onreadystatechange = function(){

      /**
       * Only call our 'aCallback' function if the ready state is 'DONE' and
       * the request status is 200 ('OK')
       *
       * See https://httpstatuses.com/ for HTTP status codes
       * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
       *  for XMLHttpRequest ready state documentation.
       *
       */
      if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
        aCallback(anHttpRequest.responseText);
    };

    anHttpRequest.open("GET", aUrl, true);
    anHttpRequest.send(null);
  }
}
