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
<form action='/music_app/Search' method='post' >
<body>
    <div class="header">
        <div class="col-2"></div>
        <div class="col-10">
            <nav class="navbar navbar-expand-lg">
                <div class="folder-list_header collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- back next button -->




                    <!-- thanh tìm kiếm -->
                    <div class="search">
                        <form action="" class="search_bar" style="width: 100%;
                                    max-width: 600px;
                                background: rgba(255, 255, 255, 0.2);
                                display: flex;
                                align-items: center;
                                border-radius: 60px;
                           
                              
                        
                        
                        ">

                         <input type="text" name='NameSearch' value='${name}' placeholder="Hôm nay bạn muốn nghe gì?" style = "    padding-bottom: 1px;
                         padding-top: 2px;
                         margin-left: 100px;
                         width: 828px;
                         height: 32px;
                         border-top-width: 3px;
                         margin-top: 1px;
                         border-bottom-width: 3px;
                         display: flex;
                         align-items: center;
                         justify-content: center;
                         background: transparent;
                         flex: 1;
                         border: 0;
                         outline: none;
                         padding: 24px 20px;
                         font-size: 20px;
                         color: #cac7ff;

                         
                         "
                     
                         >
                       
                        
                        </form>
                       </div>
                
                <!-- <div class="search">
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"   integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
                    <link rel="stylesheet" href="./assets/css/style.css">
                     <script src="./javascrip/js.js" crossorigin="anonymous"></script>
                    <form action="">
                        <input type="search" required placeholder="Input name Category here!">
                        <i class="fa fa-search"></i>




                </div> -->
           
            
                
        <!-- Dropdown -->


          
                   <div class="dropdown">
                       
                          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                         
                            <li> <a class="dropdown-item" href="#">Log Out</a></li>
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

                        <div class="row">
                            <!-- music item -->
                            <c:forEach items="${listSong}" var="song">
                            <div class="col-12 col-sm-6 col-md-2 image">
                                <a href="PlaySong?id=${song.id}" class="content-items">
                                    <div class="img-size">
                                        <img src="getImageSong.jsp?id=${song.id}">
                                    </div>
                                    <h5>${song.name}</h5>
                                <p class="name-singer">${song.idSinger}</p>
                                </a>
                            </div>
                            </c:forEach> 
                         </div>
                          <!-- row 2 -->                         
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