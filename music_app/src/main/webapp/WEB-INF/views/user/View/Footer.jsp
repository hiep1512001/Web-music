<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Footer</title>
</head>
<body>

	<div class="footer">
		<!-- info song -->
		<div class="info-music">
			<a href="./music-info.html" class="info-form"> <img
				src="https://t2.genius.com/unsafe/440x440/https:%2F%2Fimages.genius.com%2Fe5c77d88b77995a9aabd03caec55940c.500x500x1.jpg"
				alt="">
				<div class=" info-form__name">
					<h5>cry with you</h5>
					<p>Mr Siro</p>
				</div>
			</a>
		</div>
		<div class="play-music">
			<div class="play-lists">
				<div class="shuffle-music">
					<i class="fa-solid fa-shuffle shuffle-music-icon"></i>
				</div>
				<div class="back-music">
					<i class="fa-solid fa-backward-step back-music-icon"></i>
				</div>
				<div class="control-music">
					<i class="fa-solid fa-play pause-music none-btn"></i> <i
						class="fa-solid fa-pause"></i>
				</div>
				<div class="next-music">
					<i class="fa-solid fa-forward-step next-music-icon"></i>
				</div>
				<div class="repeat-music">
					<i class="fa-solid fa-repeat repeat-music-icon"></i>
				</div>
			</div>
			<div class="folder-music">
				<div class="now-time">
					<span>2:10</span>
				</div>
				<div class="play-input">
					<input type="range" value="300" step="1" class="play-change">
				</div>
				<div class="final-time">
					<span>4:10</span>
				</div>
			</div>

		</div>
		<!-- change volume -->
		<div class="volume-music">
			<i class="bi bi-volume-down-fill volume-music-icon"></i> <i
				class="fa-solid fa-volume-xmark none-btn"></i> <input type="range"
				value="100" step="5" class="volume-change"> <span></span> <i
				class="fa-solid fa-download icon-download"></i>
		</div>
	</div>
</body>
</html>