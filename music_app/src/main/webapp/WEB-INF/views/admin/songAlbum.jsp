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

    <script src="https://kit.fontawesome.com/8e44eaf2c8.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./assets/css/style.css">
   

    <title>Music</title>
    
</head>
<% //In case, if Admin session is not set, redirect to Login page
if((request.getSession(false).getAttribute("Admin")== null) )
{
%>
<jsp:forward page="/pages/login.jsp"></jsp:forward>
<%} %>
<body>
    <div class="header">
        <div class="col-2"></div>
        <div class="col-10">
            <nav class="navbar navbar-expand-lg">
                <div class="folder-list_header collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- back next button -->

                  
                    
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
                            <a class="nav-link" href="HomeAdmin">
                                <i class="fa-solid fa-house"></i>
                                Home
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Search">
                                <i class="fa-solid fa-magnifying-glass"></i>
                                Search
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="AddSong">
                                <i class="bi bi-plus-circle-fill"></i>    
                                Add Music
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="AddAlbum">
                                <i class="bi bi-journal-plus"></i>    
                                Add Album
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="AddSinger">
                                <i class="bi bi-person-plus-fill"></i>    
                                Add Singer
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="GetSong">
                                <i class="bi bi-file-earmark-music-fill"></i>    
                                Song Manager
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="GetAlbum">
                                <i class="bi bi-journal-album"></i>    
                                Album Manager
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
                        <!-- Name category vs see alll music in category-->
                        <div class="title-items">
                            <p>Album ${album.name} </p>
                          
                            
                        </div>

                        </div>
                        <table class="table table-dark">
                            <thead>
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Music Name</th>
                                <th scope="col">Singer</th>
                                <th scope="col">Category</th>
                                <th scope="col">Act</th>

                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                               <c:forEach items="${songList}" var="song" >
		                              <tr>
		                                <th scope="row">${song.id}</th>
		                                <td >${song.name} </td>   
		                           		<td >${song.idSinger}</td> 
		                           		<td >${song.idGenre} </td> 
		                                <td>
		                                    <a href="UpdateSongAlbum?id=${song.id}"
		                                    class="btn btn-primary btn-lg active btnUpdate" role="button" aria-pressed="true">Update</a>
		                                    <a href="DeleteSongAlbum?id=${song.id}"  class="btn btn-danger" role="button" aria-pressed="true">Danger</a>  </td>
       								</c:forEach> 
                            </tbody>
                          </table>
                            
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

</html>