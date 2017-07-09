<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<div>
<select id="indexval" onchange="indexSelect(this,document.getElementById('ddl2'))">
    <option>Choose an Index</option>
</select>
</div>

<select id="ddl2" onchange="getStockName()">
</select>
</body>

<script type="text/javascript">
////////////////////////////////////////////////////////////////////////////////////
//Function to populate the Indices dropdown
/////////////////////////////////////////////////////////////////////////////////////
var select = document.getElementById("indexval");

var options = ["Nifty50", "Dow30", "Straits_Times_Index"];
for(var i = 0; i < options.length; i++) {
    var opt = options[i];
    var el = document.createElement("option");
    el.textContent = opt;
    el.value = opt;
    select.appendChild(el);
}
///////////////////////////////////////////////////////////////////////////////////

//Function to populate the Stock list bases on the indice value
/////////////////////////////////////////////////////////////////////////////////////
function indexSelect(ddl1,ddl2){
var stockIndex =document.getElementById('indexval').options[document.getElementById('indexval').selectedIndex].text;
window.alert(stockIndex);

var Nifty50 = ['Black', 'White', 'Blue'];
var Dow30 = ['Square', 'Circle', 'Triangle'];
var Straits_Times_Index = ['John', 'David', 'Sarah'];

switch (stockIndex) {
case 'Nifty50':
    ddl2.options.length = 0;
    for (i = 0; i < Nifty50.length; i++) {
        createOption(ddl2, Nifty50[i], Nifty50[i]);
    }
    break;
case 'Dow30':
    ddl2.options.length = 0; 
for (i = 0; i < Dow30.length; i++) {
    createOption(ddl2, Dow30[i], Dow30[i]);
    }
    break;
case 'Straits_Times_Index':
    ddl2.options.length = 0;
    for (i = 0; i < Straits_Times_Index.length; i++) {
        createOption(ddl2, Straits_Times_Index[i], Straits_Times_Index[i]);
    }
    break;
    default:
        ddl2.options.length = 0;
    break;
}

}
///////////////////////////////////////////////////////////////////////////////////////////////

function createOption(dd2, text, value) {
        var opt = document.createElement('option');
        opt.value = value;
        opt.text = text;
        dd2.options.add(opt);
    }
///////////////////////////////////////////////////////////////////////////////////////////////
function getStockName(){
	var stockName =document.getElementById('ddl2').options[document.getElementById('ddl2').selectedIndex].text;
	window.alert(stockName);
	
}
</script>
</html>