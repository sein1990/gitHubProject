<!doctype html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/handsontable.full.min.css">

<script src="js/handsontable.full.min.js"></script>
<script src="js/buildexcelsheet.js"></script>
<script src="js/excelbuilder.js"></script>
<script>
 function pr(){
	 
	var x = document.querySelector('#hot');
	var y = x.parentNode;
	var e = new excelbuilder(x,y);
	
	var w = e.getT();
	var hot = new Handsontable(x, w);
	alert("fff");
 }

</script>


</head>
<body onload="pr();">
<div id="hot"></div>
<div id="hh"></div>
</body>
</html>