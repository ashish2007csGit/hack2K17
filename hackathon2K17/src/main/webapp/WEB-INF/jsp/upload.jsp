<!doctype html>

<html lang="en">
<head>
<link rel="icon" href="images/favicon.png" type="image/png">
<meta charset="utf-8">
<title>Video Player</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
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

.mdl-layout__header .mdl-layout__drawer-button{
	color:black;
}

</style>

</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--white">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<img src="images/vuclip-logo.png" alt="vuclip Smart Alert" />
				<h4 class="mdl-color-text--orange" style="margin-left: 30%;">Upload Video</h4>
			</div>
		</header>

		<div class="mdl-layout__drawer">
			<div class="avatar">
				<span class="mdl-layout-title mdl-color--black"><img style="margin-left: 20%;"
					src="images/vuclip-logo.png" alt="vuclip Smart Alert" /></span>
			</div>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link" href="/upload">Upload</a> <a
					class="mdl-navigation__link" href="/home">Video library</a>
			</nav>
		</div>

		<main class="mdl-layout__content">
		<div class="page-content">
		<div class="mdl-grid">
			<div class="mdl-cell mdl-cell--4-col"></div>
			<div class="mdl-cell mdl-cell--4-col">

				<form class="mdl-color--white" action="/uploadVideo" method="post"
					enctype="multipart/form-data">
					<div style="margin-top: 16px">
						<input type="file" name="file" id="uploadBtn" accept="video/*">

						<button id="show-snackbar"
							class="mdl-button mdl-button--primary mdl-button--icon mdl-button--file">
							<i class="material-icons">file_upload</i>
						</button>
					</div>

					<div style="margin-top: 16px">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="cb_english">
							<input type="checkbox" id="cb_english"  name="checkboxName" value="EN" class="mdl-checkbox__input">
							<span class="mdl-checkbox__label">Generate english subtitles</span>
						</label>
					</div>
					
					<div style="margin-top: 16px">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="cb_hindi">
							<input type="checkbox" id="cb_hindi" name="checkboxName" value="HI" class="mdl-checkbox__input">
							<span class="mdl-checkbox__label">Generate hindi subtitles</span>
						</label>
					</div>
					
					<div style="margin-top: 16px">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="cb_french">
							<input type="checkbox" id="cb_french" name="checkboxName" value="FR" class="mdl-checkbox__input">
							<span class="mdl-checkbox__label">Generate french subtitles</span>
						</label>
					</div>
					<div style="margin-top: 16px">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="cb_german">
							<input type="checkbox" id="cb_german" name="checkboxName" value="GE" class="mdl-checkbox__input">
							<span class="mdl-checkbox__label">Generate german subtitles</span>
						</label>
					</div>
					<div style="margin-top: 16px">
						<label class="mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect" for="cb_italian">
							<input type="checkbox" id="cb_italian" name="checkboxName" value="IT" class="mdl-checkbox__input">
							<span class="mdl-checkbox__label">Generate italian subtitles</span>
						</label>
					</div>
					
					
				</form>


			</div>
			<div class="mdl-cell mdl-cell--4-col"></div>
		</div>

		<div id="snackbar-example" class="mdl-js-snackbar mdl-snackbar">
			<div class="mdl-snackbar__text"></div>
			<button class="mdl-snackbar__action" type="button"></button>
		</div>
		</div>
		</main>


		<footer class="mdl-mega-footer">
			<p>All rights reserved by @SaaStars team</p>
		</footer>
		
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>

	<script>
		(function() {
			'use strict';
			var snackbarContainer = document.querySelector('#snackbar-example');
			var showSnackbarButton = document.querySelector('#show-snackbar');

			showSnackbarButton.addEventListener('click', function() {
				'use strict';
				var data = {
					message : 'Video uploaded successfully'
				};
				snackbarContainer.MaterialSnackbar.showSnackbar(data);
			});
		}());
	</script>

</body>
</html>
