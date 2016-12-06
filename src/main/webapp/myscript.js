/**
 * 
 */
var xmlhttp;
function getCounts(url,cfunc) {
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = cfunc;
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}
function myFunction() {
	getCounts("/meetings/solum_team_meeting", function()
		{
			if(xmlhttp.readyState == 4 && xmlhttp.status==200) {
				var table = document.getElementById("meetings");
				var counts = xmlhttp.response.split(" ");
				
				table.innerHTML = 
				"<table style='border:1px solid; width:40%'>" +
					"<tr style='border:1px solid'>" +
						"<td style='border:1px solid'>Year</td>" +
						"<td style='border:1px solid'>Number of Meetings</td>" +
					"</tr>" +
					"<tr style='border:1px solid'>" +
						"<td style='border:1px solid'>" + 2013 + "</td>" +
						"<td style='border:1px solid'>" + counts[0] + "</td>" +
					"</tr>" +
					"<tr style='border:1px solid'>" +
						"<td style='border:1px solid'>" + 2014 + "</td>" +
						"<td style='border:1px solid'>" + counts[1] + "</td>" +
					"</tr>" +
					"<tr style='border:1px solid'>" +
						"<td style='border:1px solid'>" + 2015 + "</td>" +
						"<td style='border:1px solid'>" + counts[2] + "</td>" +
					"</tr>" +
					"<tr style='border:1px solid'>" +
						"<td style='border:1px solid'>" + 2016 + "</td>" +
						"<td style='border:1px solid'>" + counts[3] + "</td>" +
					"</tr>" +
				"</table>";
			}
		});
}