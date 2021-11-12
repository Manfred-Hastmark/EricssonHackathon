

function scrape(){
    document.getElementById("display").innerHTML = "Tjeeeeeeena";
    var url_string = "https://www.chalmersstore.se/sv/sok/linj%C3%A4r%20algebra";
    var url = new URL(url_string);
    
    download("hello.txt","Helloww");


}

var url = 'https://www.chalmersstore.se/sv/sok/linj%C3%A4r%20algebra'; // set your page url here
with (new ActiveXObject("Microsoft.XmlHttp")) {
    open('GET', url, false);
    send('');
    var data = responseText;
    with (new ActiveXObject("ADODB.Stream")) {
        Open();
        Type = 2; // adTypeText
        Charset = 'utf-8'; // specify correct encoding
        WriteText(data);
        SaveToFile("page.html", 2);
        Close();
    }
}