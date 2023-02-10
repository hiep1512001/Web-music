<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CSS only -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/css/style.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <script src="https://kit.fontawesome.com/8e44eaf2c8.js" crossorigin="anonymous"></script>

    <title>Music</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
if((request.getSession(false).getAttribute("User")== null) )
{
%>
<jsp:forward page="/pages/login.jsp"></jsp:forward>
<%} %>
<body>
    <div class="header">
        <div class="col-12">
            <nav class="navbar navbar-expand-lg float-right">
                <div class="folder-list_header collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Login, register -->
                    <!-- thêm class none btn để ẩn btn login vs register khi đã login -->
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle border-radius" type="button"
                            data-bs-toggle="dropdown" aria-expanded="false" name="show_name">
                            ${name}
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="HomeMember">home</a></li>
                            <li><a class="dropdown-item" href="LogoutServlet">Log out</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="container-fluid">
        <div class="main">
            <div class="row">
                <div class="menu-bar col-2">
                    <div class="navbar-nav me-auto mb-2 mb-lg-0">
                        <div class="title-app">
                            <p>Music</p>
                        </div>
                    </div>
                    <!-- nemu bar -->
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="HomeMember">
                                <i class="fa-solid fa-house"></i>
                                Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="SearchMember">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                Search
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="MyLirary">
                                <i class="bi bi-music-note-list margin-top"></i>
                                My library
                            </a>
                        </li>
                                                <li class="nav-item">
                            <a class="nav-link" href="LogoutServlet">
                                <i class="bi bi-box-arrow-left"></i>    
                                Log Out 
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="folder-list col-10">
                    <form class="user-info">
                        <div class="form-row display-flex">
                            <div class="col-sm-6">
                            
                            <p style="color: red;">${errorString}</p>
                                <div class="form-group col-sm-12">
                                    <label for="myName">Fullname</label>
                                    <input type="text" name='name'value ='${name}' class="form-control" id="myName" placeholder="Fullname" disabled>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label for="myEmail">Email</label>
                                    <input type="email"value ='${email}' class="form-control" id="myEmail" placeholder="Email" disabled>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label for="myAccount">Name Account</label>
                                    <input type="text"value ='${account}' class="form-control" disabled>
                                </div>
                                <div class="form-group col-sm-12">
                                    <label for="myPassword">Password</label>
                                    <input type="password" name='password' value ='${password}'class="form-control" id="myPassword" placeholder="Password"
                                        disabled>
                                </div>      
                                
            						 <a href="UpdateUser?id=${id}"
		                                    class="btn btn-primary btn-lg active btnUpdate" role="button" aria-pressed="true">Edit</a>
         
                                <div class="none-btn" id="save">
                     
        
                                </div>
                                
                            </div>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <script src="./js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>

</body>

</html>