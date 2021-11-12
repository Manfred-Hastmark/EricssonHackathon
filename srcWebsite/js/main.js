var userSearch;
    function showInput() {
        document.getElementById('display').innerHTML = 
                    document.getElementById("query").value;
		userSearch = document.getElementById("query").value;
    }
	
	function newBook() {
		document.getElementById('formDiv').style.display = "block";
		document.getElementById('searchDiv').style.display = "none";
	}
	
