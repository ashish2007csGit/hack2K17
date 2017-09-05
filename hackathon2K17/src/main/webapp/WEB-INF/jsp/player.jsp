<!DOCTYPE html>
<html lang="en-IE">
<head>
	<meta charset="utf-8" />
	<title>Video Player</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="css/styles.css" />
	<!--[if lt IE 9]><script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.2/html5shiv-printshiv.min.js" type="text/javascript"></script><![endif]-->
</head>
<body>
	<h1>Vuclip Hackathon 2K17</h1>
	<figure id="videoContainer" data-fullscreen="false">
		<!-- <video id="video" controls preload="metadata">
			<source src="video/sintel-short.mp4" type="video/mp4">
			<source src="video/sintel-short.webm" type="video/webm">
			<track label="English" kind="subtitles" srclang="en" src="subtitles/srt/iran-speech.vtt" default>
			<track label="Deutsch" kind="subtitles" srclang="de" src="subtitles/vtt/sintel-de.vtt">
			<track label="Español" kind="subtitles" srclang="es" src="subtitles/vtt/sintel-es.vtt">
		</video>-->
		
		<video id="video" controls preload="metadata">
			<source src="${video }" type="video/mp4">
			<track label="English" kind="subtitles" srclang="en" src="${vtt }" default>
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
			&copy; copyright Vuclip Pvt. Limited  | <a href="https://www.viu.com">https://www.viu.com</a>
		</figcaption>
	</figure>
	<script src="js/video-player.js"></script>
</body>
</html>