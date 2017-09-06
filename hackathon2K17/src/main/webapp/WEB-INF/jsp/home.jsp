<!doctype html>

<html lang="en">
<head>
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

</style>

<script type="text/javascript">
	function submitClick(id){
		
		$.ajax({
						url : '/play?id=' + id,
						type: 'get',
						success: function(result) {
								console.log(result);
								$("#menu").addClass("hide");
								$("#player").removeClass("hide");
								var video = document.getElementById('video');
								var videoSrc = 'video\\' + result;
								   video.src = videoSrc + '.mp4';
								var trackSrc = 'subtitles\\vtt\\' + result
								   track.src = trackSrc + '.vtt';
								   //video.play();
					    }

					});
	} 
	
</script>

</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<img src="images/footer-logo.png" alt="vuclip Smart Alert" />
				<h4 style="margin-left: 20%;">Video Library</h4>
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
		</header>

		<div class="mdl-layout__drawer">
			<div class="avatar">
				<span class="mdl-layout-title"><img src="images/footer-logo.png" alt="vuclip Smart Alert" /></span>
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
					<source src="" type="video/mp4">
					<track id="track" label="English" kind="subtitles" srclang="en"
						src="" default>
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
		</main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>
</html>
