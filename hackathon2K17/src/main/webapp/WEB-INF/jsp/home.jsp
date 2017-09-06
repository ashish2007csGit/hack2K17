<!doctype html>

<html lang="en">
<head>
<link rel="icon" href="images/favicon.png" type="image/png">
<meta charset="utf-8">
<title>Video Player</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<link rel="stylesheet" href="css/styles.css">
<style>
#view-source {
	position: fixed;
	display: block;
	right: 0;
	bottom: 0;
	margin-right: 40px;
	margin-bottom: 40px;
	z-index: 900;
}

.hide {
	display: none;
}

.mdl-layout__header .mdl-layout__drawer-button{
	color:black;
}

</style>

<script type="text/javascript">
	function submitClick(id){
		
		$.ajax({
						url : '/play?id=' + id,
						type: 'get',
						dataType: "json",
						success: function(result) {
							var index = result.length - 1;
							console.log('Index is: ' + index);
							
							 	/* $("#menu").addClass("hide");
								$("#player").removeClass("hide"); */
								var video = document.getElementById('video');
								var videoSrc = 'video\\' + result[0];
								   video.src = videoSrc + '.mp4';
								
								var subButtons = [];
								//var list = $(".subtitles-menu");
								   
								for(i=1;i<=index;i++){   
									/* var trackSrc = 'subtitles\\vtt\\' + result
								   	track.src = trackSrc + '.vtt'; */
								   	if(result[i].indexOf("EN") != -1){
								   		console.log('English loaded');
								   		//subButtons.push(i.toString());
										track = document.createElement("track");
										track.label = "English";
										track.kind = "subtitles";
									   	track.srclang = "en";
									   	track.src = "subtitles\\vtt\\" + result[i] + ".vtt";
									   	video.appendChild(track);
								   	} else if(result[i].indexOf("HI") != -1){
								   		console.log('Hindi loaded');
								   		//subButtons.push(i.toString());
								   		var list = $(".subtitles-menu");
								   		track = document.createElement("track");
									   	track.kind = "subtitles";
									   	track.label = "Hindi";
									   	track.srclang = "hn";
									   	track.src = "subtitles\\vtt\\" + result[i] + ".vtt";
									   	video.appendChild(track);
								   	} else if(result[i].indexOf("FR") != -1){
								   		console.log('French loaded');
								   		//subButtons.push(i.toString());
										track = document.createElement("track");
									   	track.kind = "subtitles";
									   	track.label = "French";
									   	track.srclang = "fr";
									   	track.src = "subtitles\\vtt\\" + result[i] + ".vtt";
									   	video.appendChild(track);
								   	} else if(result[i].indexOf("DE") != -1){
								   		console.log('German loaded');
								   		//subButtons.push(i.toString());
										track = document.createElement("track");
									   	track.kind = "subtitles";
									   	track.label = "Ferman";
									   	track.srclang = "de";
									   	track.src = "subtitles\\vtt\\" + result[i] + ".vtt";
									   	video.appendChild(track);
								   	} else if(result[i].indexOf("IT") != -1){
								   		console.log('Italian loaded');
								   		//subButtons.push(i.toString());
										track = document.createElement("track");
									   	track.kind = "subtitles";
									   	track.label = "Italian";
									   	track.srclang = "it";
									   	track.src = "subtitles\\vtt\\" + result[i] + ".vtt";
									   	video.appendChild(track);
								   	} else{
								   		console.log('Else condi');
								   		//console.log(list.children().eq(i));
								   		//list.children().eq(i).remove();
								   		subButtons.push(i.toString());
								   	}
								   	
								}
								   //video.play();
								 var list = $(".subtitles-menu");
								 var len = list.length;
								 for(i=0;i<len;i++){
									 
								 }
								 var length = subButtons.length;
								 for(i=0;i<length;i++){
									 list.children().eq(subButtons[i]).remove();
									 var newArr = [];
									 for(i=0;i<subButtons.length;i++){
										 newArr.push(subButtons[i+1] -1);
									 }
									 subButtons = newArr;
								 }
								 
								console.log(subButtons);
								$("#menu").addClass("hide");
								$("#player").removeClass("hide");
					    }

					});
	} 
	
</script>

</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--white">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<img src="images/vuclip-logo.png" alt="vuclip Smart Alert" />
				<h4 class="mdl-color-text--orange" style="margin-left: 30%;">Video Library</h4>
			</div>
		</header>

		<div class="mdl-layout__drawer">
			<div class="avatar mdl-color--black">
				<span class="mdl-layout-title"><img style="margin-left: 20%;" src="images/vuclip-logo.png" alt="vuclip Smart Alert" /></span>
			</div>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="/upload">Upload</a> <a
					class="mdl-navigation__link" href="/home">Video library</a>
			</nav>
		</div>


		<main class="mdl-layout__content">

		<div id="menu" class="page-content">
			<div class="mdl-grid">
				<div class="mdl-cell mdl-cell--3-col"></div>
				<div class="mdl-cell mdl-cell--7-col">

					<!-- <form action="/play" method="post"> -->
					<table style="width:60%" class="mdl-data-table fullwidth mdl-js-data-table mdl-shadow--2dp">
						<thead>
							<tr>
								<th style="width:20%">Sr. No.</th>
								<th style="text-align:center; width:60%">Video</th>
								<th style="width:20%">Play</th>
							</tr>
						</thead>
						<tbody>
							<%
								int i = 1;
							%>
							<c:forEach items="${videos}" var="videoName">

								<tr>
									<td><%=i%></td>
									<td style="text-align:center">${videoName}</td>
									<td><button onclick="submitClick(<%=i%>)"
											class="mdl-button mdl-button--fab mdl-js-button mdl-js-ripple-effect">
											<i class="material-icons">video_library</i>
										</button></td>
									<%
										i++;
									%>
								</tr>


							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="mdl-cell mdl-cell--2-col"></div>
			</div>
		</div>

		<div id="player" class="hide">
			<figure style="height:400px;width: 600px;" id="videoContainer" data-fullscreen="false">
				<video id="video" controls preload="metadata">
					<!-- <source src="" type="video/mp4"> -->
					<track label="English" kind="subtitles" srclang="en" id="enTrack" src="">
					<track label="Hindi" kind="subtitles" srclang="hn" id="hnTrack" src="">
					<track label="French" kind="subtitles" srclang="fr" id="frTrack" src="">
					<track label="German" kind="subtitles" srclang="de" id="deTrack" src="">
					<track label="Italian" kind="subtitles" srclang="it" id="itTrack" src="">
				</video>


				<div id="video-controls" class="controls" data-state="hidden">
					<button id="playpause" type="button" data-state="play">Play/Pause</button>
					<button id="stop" type="button" data-state="stop">Stop</button>
					<div class="progress">
						<progress id="progress" value="0" min="0">
							<span id="progress-bar"></span>
						</progress>
					</div>
					<button id="mute" type="button" data-state="mute">Mute/Unmute</button>
					<button id="volinc" type="button" data-state="volup">Vol+</button>
					<button id="voldec" type="button" data-state="voldown">Vol-</button>
					<button id="fs" type="button" data-state="go-fullscreen">Fullscreen</button>
					<button id="subtitles" type="button" data-state="subtitles">CC</button>
				</div>
				<figcaption>
					&copy; copyright Vuclip Pvt. Limited | <a
						href="https://www.viu.com">https://www.viu.com</a>
				</figcaption>
			</figure>
			<script src="js/video-player.js"></script>

		</div>
		</main>
		<footer class="mdl-mega-footer">
			<p>All rights reserved by @SaaStars team</p>
		</footer>
		
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
