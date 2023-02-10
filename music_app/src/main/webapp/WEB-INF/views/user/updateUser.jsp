<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <script src="https://kit.fontawesome.com/8e44eaf2c8.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./assets/css/style.css">
   

    <title>Music</title>
    
</head>
<% //In case, if User session is not set, redirect to Login page.
if((request.getSession(false).getAttribute("User")== null) )
{
%>
<jsp:forward page="/pages/login.jsp"></jsp:forward>
<%} %>
<form action='/music_app/UpdateUser' method='post' >
<body>
    <div class="header">
        <div class="col-2"></div>
        <div class="col-10">
            <nav class="navbar navbar-expand-lg">
                <div class="folder-list_header collapse navbar-collapse" id="navbarSupportedContent">

                  
                    
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

                    <div class="list-items">
                        <!-- Name category vs see all music in category-->
                        <div class="title-items">
                            <p>Update User ${name}</p>
                          
                            
                        </div>

                        </div>
                        <!-- name Category -->
                        <div class="row">
                            <div class="addmusic">
                                <p style="color: white;" >Full Name
                                    <div class="input-group mb-3">
                                        <input type="text" value="${name}"  name ='name'class="form-control" placeholder="Input full name user!"  >
                                       
                                      </div>
                                </p>
                                                            <div class="addmusic">
                                <p style="color: white;" >Email
                                    <div class="input-group mb-3">
                                        <input type="email" value="${email}" disabled name ='GenresName'class="form-control" placeholder=""  >
                                       
                                      </div>
                                </p>
                                                            <div class="addmusic">
                                <p style="color: white;" >Name Accout
                                    <div class="input-group mb-3">
                                        <input type="text" value="${account}" disabled name ='GenresName'class="form-control" placeholder=""  >
                                       
                                      </div>
                                </p>
                                                            <div class="addmusic">
                                <p style="color: white;" >Password
                                    <div class="input-group mb-3">
                                        <input type="text" value="${password}"  name ='password'class="form-control" placeholder="Input password!"  >
                                       
                                      </div>
                                </p>
                            </div>
                          
                            <!--add Category  -->
                            <div class="addmusic">
                                <button type="submit" class="btn btn-success">Update User</button>
                            </div>
                            
                        </div>
                    </div>
                   
                </div>
            </div>

        </div>
    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>

</body>
</form>
</html>