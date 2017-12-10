var lineDiv = document.getElementById('line-chart');
function grabData(destination, source, limit, callback){
	var xmlhttp = new XMLHttpRequest();
//	var URL = "http://localhost:8080/api/depth/" + destination + "/" + source + "/" + limit;
	var URL = "http://45.55.162.134:8222/LearnCI-CD/api/depth/" + destination + "/" + source + "/" + limit;
    xmlhttp.onreadystatechange = function(){
    	if (xmlhttp.readyState == XMLHttpRequest.DONE) {
           if (xmlhttp.status == 200) {
        	   var depth = JSON.parse(xmlhttp.response);
        	   callback(depth);
           }
        }
    }
    xmlhttp.open("GET", URL, true);
    xmlhttp.send();
}
function drawFirstChart(depth){
	var traceA = {
		x: depth.bidX,
		y: depth.bidY,
		type: 'scatter',
		fill: 'tozeroy'
	};
	var traceB = {
		x: depth.askX,
		y: depth.askY,
		type: 'scatter',
		fill: 'tozeroy'
	};
	var data = [traceA, traceB];
	var layout = {
		title:'Depth Chart'
		};
   	Plotly.plot(this.lineDiv, data, layout);
    setTimeout(grabData(destination, source, limit, drawUpdatedChart), 1000);
}
function drawUpdatedChart(depth){
	var data = {
		x: [depth.bidX, depth.askX],
		y: [depth.bidY, depth.askY]
	}
	Plotly.update(this.lineDiv, data);
	setTimeout(grabData(destination, source, limit, drawUpdatedChart), 1000);
}