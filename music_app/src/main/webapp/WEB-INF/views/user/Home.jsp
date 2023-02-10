
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Home</title>
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


				<div class="folder-list col-10">
					<div class="list-items">
						<!-- Name category vs see all music in category-->
						<div class="title-items">
							<p>Mr Siro</p>
							<a href="./show-music-in-category.html">see all</a>
						</div>
						<div class="row">
							<!-- music item hiển thị 6 mục trên 1 hàng-->
							<div class="col-12 col-sm-6 col-md-2 image">
								<a href="./MusicInfoUser" class="content-items">
									<div class="img-size">
										<img
											src="https://t2.genius.com/unsafe/440x440/https:%2F%2Fimages.genius.com%2Fe5c77d88b77995a9aabd03caec55940c.500x500x1.jpg"
											alt="">
									</div>
									<h5>Khóc cùng em</h5>
									<p class="name-singer">Mr Siro</p>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
		crossorigin="anonymous"></script>


</body>
</html>