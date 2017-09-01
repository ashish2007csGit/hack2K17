<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Video Player</title>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.deep_purple-pink.min.css">
<link rel="stylesheet" href="styles.css">
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
</style>
</head>
<body
	class="mdl-demo mdl-color--grey-100 mdl-color-text--grey-700 mdl-base">
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<header
			class="mdl-layout__header mdl-layout__header--scroll mdl-color--primary">
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
				<h3>Upload your video</h3>
			</div>
			<div class="mdl-layout--large-screen-only mdl-layout__header-row">
			</div>
		</header>
		<main class="mdl-layout__content"> 
			
			<div class = "mdl-grid">
               <div class = "mdl-cell mdl-cell--4-col"></div>
               <div class = "mdl-cell mdl-cell--4-col">
               
               	<form action="/uploadVideo" method="post" enctype="multipart/form-data">
               		
                     <input type = "file" name ="file" id = "uploadBtn">
                  </div>
                  <button class="mdl-button mdl-js-button mdl-button--fab">
						<i class="material-icons">file_upload</i>
					</button>
               		
               	</form>
               
               
               </div>
               <div class = "mdl-cell mdl-cell--4-col"></div>
            </div>
			
		</main>


		<footer class="mdl-mega-footer">
			<p>All rights reserved by @SaaStars team</p>
		</footer>
		</main>
	</div>
	<script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</body>
</html>
