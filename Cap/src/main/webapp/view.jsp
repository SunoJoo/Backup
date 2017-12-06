<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ocr.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>웹툰</title>
</head>
<body>
<%  if(request.getAttribute("data")!= null){
			ResultData data = (ResultData)request.getAttribute("data");					
				%>
				<%= data.getText() %>
				<%=data.getPosition() %>	  
				<%} %>
</body>
=======
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="ocr.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>웹툰</title>
</head>
<body>
<%  if(request.getAttribute("data")!= null){
			ResultData data = (ResultData)request.getAttribute("data");					
				%>
				<%= data.getText() %>
				<%=data.getPosition() %>	  
				<%} %>
</body>
>>>>>>> parent of 4507a42... rollback
</html>