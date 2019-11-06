$(function() {
	var a = {
		labels : [ "Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm",
				"Thứ sáu", "Thứ bảy" ],
		datasets : [ {
			label : "Data 1",
			borderColor : 'rgba(52,152,219,1)',
			backgroundColor : 'rgba(52,152,219,1)',
			pointBackgroundColor : 'rgba(52,152,219,1)',
			data : [ 29, 48, 40, 19, 78, 31, 85 ]
		}, {
			label : "Data 2",
			backgroundColor : "#DADDE0",
			borderColor : "#DADDE0",
			data : [ 45, 80, 58, 74, 54, 59, 40 ]
		} ]
	}, t = {
		responsive : !0,
		maintainAspectRatio : !1
	}, e = document.getElementById("bar_chart").getContext("2d");
	new Chart(e, {
		type : "line",
		data : a,
		options : t
	});

	// World Map
	var mapData = {
		"US" : 7402,
		'RU' : 5105,
		"AU" : 4700,
		"CN" : 4650,
		"FR" : 3800,
		"DE" : 3780,
		"GB" : 2400,
		"SA" : 2350,
		"BR" : 2270,
		"IN" : 1870,
	}
	$('#world-map').vectorMap({
		map : 'world_mill_en',
		backgroundColor : 'transparent',
		regionStyle : {
			initial : {
				fill : '#DADDE0',
			}
		},
		showTooltip : true,
		onRegionTipShowx : function(e, el, code) {
			el.html(el.html() + ' (Visits - ' + mapData[code] + ')');
		},
		markerStyle : {
			initial : {
				fill : '#3498db',
				stroke : '#333'
			}
		},
		markers : [ {
			latLng : [ 1.3, 103.8 ],
			name : 'Singapore : 203'
		}, {
			latLng : [ 38, -105 ],
			name : 'USA : 755',
		}, {
			latLng : [ 58, -115 ],
			name : 'Canada : 700',
		}, {
			latLng : [ -25, 140 ],
			name : 'Australia : 304',
		}, {
			latLng : [ 55.00, -3.50 ],
			name : 'UK : 202',
		}, {
			latLng : [ 21, 78 ],
			name : 'India : 410',
		}, {
			latLng : [ 25.00, 54.00 ],
			name : 'UAE : 180',
		} ]
	});

	var doughnutData = {
		labels : [ "Desktop", "Tablet", "Mobile" ],
		datasets : [ {
			data : [ 47, 30, 23 ],
			backgroundColor : [ "rgb(255, 99, 132)", "rgb(54, 162, 235)",
					"rgb(255, 205, 86)" ]
		} ]
	};

	var doughnutOptions = {
		responsive : true,
		legend : {
			display : false
		},
	};

	var ctx4 = document.getElementById("doughnut_chart").getContext("2d");
	new Chart(ctx4, {
		type : 'doughnut',
		data : doughnutData,
		options : doughnutOptions
	});

});
var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document
		.querySelector('#singleFileUploadSuccess');

var multipleUploadForm = document.querySelector('#multipleUploadForm');
var multipleFileUploadInput = document
		.querySelector('#multipleFileUploadInput');
var multipleFileUploadError = document
		.querySelector('#multipleFileUploadError');
var multipleFileUploadSuccess = document
		.querySelector('#multipleFileUploadSuccess');

function uploadSingleFile(file) {
	var formData = new FormData();
	formData.append("file", file);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadFile");

	xhr.onload = function() {
		console.log(xhr.responseText);
		var response = JSON.parse(xhr.responseText);
		if (xhr.status == 200) {
			singleFileUploadError.style.display = "none";
			singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='"
					+ response.fileDownloadUri
					+ "' target='_blank'>"
					+ response.fileDownloadUri + "</a></p>";
			singleFileUploadSuccess.style.display = "block";
		} else {
			singleFileUploadSuccess.style.display = "none";
			singleFileUploadError.innerHTML = (response && response.message)
					|| "Some Error Occurred";
		}
	}

	xhr.send(formData);
}

function uploadMultipleFiles(files) {
	var formData = new FormData();
	for (var index = 0; index < files.length; index++) {
		formData.append("files", files[index]);
	}

	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/uploadMultipleFiles");

	xhr.onload = function() {
		console.log(xhr.responseText);
		var response = JSON.parse(xhr.responseText);
		if (xhr.status == 200) {
			multipleFileUploadError.style.display = "none";
			var content = "<p>All Files Uploaded Successfully</p>";
			for (var i = 0; i < response.length; i++) {
				content += "<p>DownloadUrl : <a href='"
						+ response[i].fileDownloadUri + "' target='_blank'>"
						+ response[i].fileDownloadUri + "</a></p>";
			}
			multipleFileUploadSuccess.innerHTML = content;
			multipleFileUploadSuccess.style.display = "block";
		} else {
			multipleFileUploadSuccess.style.display = "none";
			multipleFileUploadError.innerHTML = (response && response.message)
					|| "Some Error Occurred";
		}
	}

	xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event) {
	var files = singleFileUploadInput.files;
	if (files.length === 0) {
		singleFileUploadError.innerHTML = "Please select a file";
		singleFileUploadError.style.display = "block";
	}
	uploadSingleFile(files[0]);
	event.preventDefault();
}, true);

multipleUploadForm.addEventListener('submit', function(event) {
	var files = multipleFileUploadInput.files;
	if (files.length === 0) {
		multipleFileUploadError.innerHTML = "Please select at least one file";
		multipleFileUploadError.style.display = "block";
	}
	uploadMultipleFiles(files);
	event.preventDefault();
}, true);