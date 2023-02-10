
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Singer</title>
</head>
<body>
    <table border="1"  >
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Picture</th>
       </tr>
       <c:forEach items="${singerList}" var="singer">
            <tr>
             <td>${singer.id}</td>
             <td>${singer.name}</td>
             <td><img src="getImageSinger.jsp?id=${singer.id}" width="100px" height="60px"/></td>
          </tr>
       </c:forEach>
    </table>
</body>
</html>