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
<form action='/music_app/UpdateSongAlbum' method='post' enctype='multipart/form-data'>
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
                        <!-- Name category vs see all music in category-->
                        <div class="title-items">
                            <p>Update Music ${song.name}</p>
                          
                            
                        </div>

                        </div>
                        <!-- name music -->
                        <div class="row">
                            <div class="addmusic">
                                <p style="color: white;" >Name Music
                                    <div class="input-group mb-3">
                                        <input type="text" value='${song.name}' name='SongName'class="form-control" placeholder="Input name music here!"  >
                                       
                                      </div>
                                </p>
                            </div>
                            <!-- Singer -->
                            <div class="Singer">
                                <p style="color: white;" >Singer </p>
                                <div class="input-group mb-3">
                                    <select name='NameSinger' class="custom-select" id="inputGroupSelect02">
                                     <c:forEach items="${singerList}" var="singer">
                                  <option value="${singer.id}">${singer.name}</option>
       							</c:forEach> 
                                    </select>
                                    <div class="input-group-append">
                                        <a class="btn btn-primary" href="AddSinger" role="button">Add Singer</a>
                                        <a class="btn btn-primary" href="GetSinger" role="button">Singer Manager</a>
                                    </div>
                                  </div>
                    
                           
                         </div> 

                         </div>
                         <!-- add album -->
                         <div class="album">
                            <p style="color: white;" >Album </p>
                    
                            <div class="input-group mb-3">
                                <select name='NameAlbum' class="custom-select" id="inputGroupSelect02">
                                  <c:forEach items="${albumList}" var="album">
                                  <option value="${album.id}">${album.name}</option>
       							</c:forEach> 
                                </select>
                                <div class="input-group-append">
                                    <a class="btn btn-primary" href="AddAlbum" role="button">Add Album</a>
                                </div>
                              </div>

                         </div>
                            <!-- add Category -->
                            <p style="color: white;" >Category </p>
                    
                            <div class="input-group mb-3">
                                <select name='NameGenre' class="custom-select" id="inputGroupSelect02">
                                  <c:forEach items="${genreList}" var="genre">
                                  <option value="${genre.id}">${genre.name}</option>
       							</c:forEach> 
                                </select>
                                <div class="input-group-append">
                                    <a class="btn btn-primary" href="AddGenres" role="button">Add Category</a>
                                    <a class="btn btn-primary" href="GetGenres" role="button">Category Manager</a>
                                </div>
                              </div>
                            <!-- choose file  -->
                            <p style="color: white;" >Choose file mp3 
                                <div class="input-group">
                                    <div class="custom-file">
                                      <input type="file" name='DataSong' class="custom-file-input" id="inputGroupFile04">
                                      <label class="custom-file-label" for="inputGroupFile04">Choose file</label>
                                    </div>
                                  
                                  </div>
                                  <p style="color: white;" >Choose image
                                    <div class="input-group">
                                        <div class="custom-file">
                                          <input type="file" name='ImageSong'class="custom-file-input" id="inputGroupFile04">
                                          <label class="custom-file-label" for="inputGroupFile04">Choose image</label>
                                        </div>
                                      
                                      </div>
                                </p>
                            </p>
                            <!--add music  -->
                            <div class="addmusic">

                                <button type="submit" class="btn btn-success">Update Music</button>
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