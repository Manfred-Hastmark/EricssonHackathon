
var userSearch;



const puppet = require('puppeteer');

async function scrapeProduct(url){
    const browser = await puppet.launch();
    const page = await browser.newPage();
    await  page.goto(url);

    const[el] = await page.$x('/html/body/div[4]/div/div[1]/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/tws-util-heading/div/h2/a');
    const src = await el.getProperty('src');
    const srcText = await src.jsonValue();

    const[el2] = await page.$x('/html/body/div[4]/div/div[1]/div/div/div[2]/div/div/div/div[3]/div[1]/div/div/tws-util-heading/div/h2/a');
    const txt = await el2.getProperty('textContent');
    const rawText = await txt.jsonValue();
    console.log(srcText, rawText);

}

scrapeProduct('https://www.chalmersstore.se/sok/python%22');

function showInput() {
	document.getElementById('display').innerHTML = 
	document.getElementById("query").value;
	userSearch = document.getElementById("query").value;
}

function newBook() {
	document.getElementById('formDiv').style.display = "block";
	document.getElementById('searchDiv').style.display = "none";
}




	
