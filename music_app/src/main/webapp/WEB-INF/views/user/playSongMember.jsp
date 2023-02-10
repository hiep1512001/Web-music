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

    <link rel="icon" href="https://www.invert.vn/media/ar/2-chi-pu.jpg" type="image/x-icon" />

    <title>Music</title>
</head>
<% //In case, if User session is not set, redirect to Login page.
if((request.getSession(false).getAttribute("User")== null) )
{
%>
<jsp:forward page="/pages/login.jsp"></jsp:forward>
<%} %>
   <div class="header ">
    <div class="col-10">
       <nav class=" navbar navbar-expand-lg">
           <div class="folder-list_header collapse navbar-collapse" id="navbarSupportedContent">
               <!-- next, back button -->

               <!-- Login, register -->

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
                            <a class="nav-link" href="MyLibrary">
                                <i class="bi bi-music-note-list margin-top"></i>
                                My library
                            </a>
                        </li>
                                                <li class="nav-item">
                            <a class="nav-link" href="LogoutServlet">
                                <i class="bi bi-box-arrow-left"></i>    
                                Log Out 
                            </a>
                    </ul>
                    
                </div>
         
            <!-- container info -->
            <div class="container__info col-10">
             
                <div class="header-info ">
                    <img src="getImageSinger.jsp?id=${singer.id}" class="header-info__img">
                     <p class="header-info__name">${singer.name}</p>

                </div>            
                
                <div class="container__info-list">
                <p style="color:green;">${errorString}</p>
                    <div class="list">
                         <!-- note -->
                         <div class="note-item">
                            <div class="list__id">
                                <p class="text-note-list">#</p> 
                            </div>
                            <div class="list__album">
                                <p class="text-note-list">TITLE</p> 
                            </div>
                            <div class="list__album">
                                <p class="text-note-list">SINGER</p>
                            </div>
   							<div class="list__album">
                                <p class="text-note-list">ALBUM</p>
                            </div>
                               							<div class="list__album">
                                <p class="text-note-list"></p>
                            </div>
                       </div>
                    </div>           
                        <!-- list song -->
                         <div class="note-item">
                         
                            <div class="list__id">
       
                            </div>
                            <div class="list__album">
                                <p class="text-note-list">${song.name}</p> 
                            </div>
                               <div class="list__album">
                                <p class="text-note-list">${singer.name}</p>
                            </div>
                            <div class="list__album">
                                <p class="text-note-list">${song.idAlbum}</p>
                            </div>
                            <form action='/music_app/AddMyLibrary?id=${song.id}' method='post'>
                            <div class="list__album">
							<button type="submit" class="btn btn-success">Add MyLibrary</button>
                            </div>
                            </form>
                       </div>
                </div>
                 </div>

            </div>
        </div>
    </div>
   </div>

   <div class="footer">
    <!-- info song -->
<audio src="getFileSong.jsp?id=${song.id}" type="audio/mp3" controls autoplay="autoplay" style="margin-left: 25%;
    width: 50%;" ></audio>
    </div>
    <!-- change volume -->
    <div class="volume-music">
        <i class="bi bi-volume-down-fill volume-music-icon"></i>
        <i class="fa-solid fa-volume-xmark none-btn"></i>
        <input type="range" value="100" step="5" class="volume-change">
        <span></span>
        <i class="fa-solid fa-download icon-download"></i>
    </div>
</div>

    <script src="../js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>


</body>

</html>