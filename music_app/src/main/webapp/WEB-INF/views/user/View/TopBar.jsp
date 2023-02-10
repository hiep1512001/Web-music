<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TopBar</title>
</head>
<body>
<div class="header ">
		<div class="col-10">
			<nav class=" navbar navbar-expand-lg">
				<div class="folder-list_header collapse navbar-collapse"
					id="navbarSupportedContent">
					<!-- next, back button -->
					<div class="navbar-nav me-auto mb-2 mb-lg-0 control-b-n">
						<a class="back-next--button" href="#"><i
							class="fa-solid fa-chevron-left"></i></a> <a
							class="back-next--button" href="#"><i
							class="fa-solid fa-chevron-right"></i></a>
					</div>
					<!-- Login, register -->
					<form class="d-flex login-btn">
						<a href="./login.html" class="btn" type="submit">Login</a> <a
							href="./register.html" class="btn" type="submit">Register</a>
					</form>
				</div>
			</nav>
		</div>
	</div>
</body>
</html>