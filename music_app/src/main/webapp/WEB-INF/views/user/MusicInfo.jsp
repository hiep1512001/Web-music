
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Music info</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS only -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="assets/css/base.css">
<script src="https://kit.fontawesome.com/8e44eaf2c8.js"
	crossorigin="anonymous"></script>

<link rel="icon" href="https://www.invert.vn/media/ar/2-chi-pu.jpg"
	type="image/x-icon" />

</head>
<body>

	<jsp:include page="View/TopBar.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="main">
			<div class="row">
				<jsp:include page="View/LeftBar.jsp"></jsp:include>
				<!-- container info -->

				<div class="container__info col-10">

					<div class="header-info ">
						<img src="../image/den2.jpg" class="header-info__img">
						<p class="header-info__name">Đen</p>
						<p class="header-info__amount-listener">569,225 monthly
							listeners</p>
					</div>

					<div class="container__info-list">
						<div class="list">
							<!-- note -->
							<div class="note-item">
								<div class="list__id">
									<p class="text-note-list">#</p>
								</div>
								<div class="list__title">
									<p class="text-note-list">TITLE</p>
								</div>
								<div class="list__album">
									<p class="text-note-list">ALBUM</p>
								</div>
								<div class="list__dateAdd">
									<p class="text-note-list">DATE ADDED</p>
								</div>
								<div class="list__time">
									<i class="fa-regular fa-clock text-note-list"></i>
								</div>
							</div>
						</div>

						<!-- list song -->
						<div id="song-list"></div>

						<div class="see-more">
							<button class="btn-see-more">SEE MORE</button>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="View/Footer.jsp"></jsp:include>

	<script src="js/main.js"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>


</body>
</html>