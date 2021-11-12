

function showInput() {
  document.getElementById('display').innerHTML = document.getElementById("query").value;
  var query = document.getElementById("query").value;
  
  scrape();
}