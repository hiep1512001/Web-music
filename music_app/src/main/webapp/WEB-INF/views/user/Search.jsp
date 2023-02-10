<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
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
				<div class="folder-list col-10">
					<div class="list-items">
						<div class="title-items">
							<p>Browser all</p>
						</div>
						<div class="row">
							<!-- music item hiển thị 5 mục trên 1 hàng-->
							<div class="col-10 col-sm-5 col-md-2-5 item__margin-top">
								<a href="./search-in-category.html"
									class="categoty-content-items">
									<h5>Khóc cùng em</h5>
									<div class="img-size-category">
										<img
											src="https://t2.genius.com/unsafe/440x440/https:%2F%2Fimages.genius.com%2Fe5c77d88b77995a9aabd03caec55940c.500x500x1.jpg"
											alt="">
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
	</div>

</body>
</html>