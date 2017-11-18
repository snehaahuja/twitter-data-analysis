
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page2</title>
</head>

<body>
<center><h2><font color="blue">${msg}</font></h2></center><br/>\


<br/><br/><br/>
<h1>${msg1}</h1>
<h1>${msg2}</h1>
<c:forEach items="${msg3}" var="tweets">
<center><table><tr>
<td>${tweets.getUser().getName()}</td><td></td>
<td>${tweets.getText()}</td><td></td>
</tr> </table></center>
</c:forEach>

</body>
</html>