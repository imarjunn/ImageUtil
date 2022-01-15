<%@page import="com.arjun.imageManagementUtility.dao.ImageDao"%>
<%@page import="com.arjun.imageManagementUtility.entity.ImageEntity"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<style>
body
{
  background:#e6e6ff !important;
  font-family: 'Encode Sans', sans-serif;
}

#imgUtil
{
  border-radius:0px;
  background:#fff;
  padding:10px;
  font-size:17px;
}
#logo
{
  font-size:20px;
  font-weight:bolder;
  color:#00004d;
  letter-spacing:2px;
}

#userNam{
	margin-left: 20px !important;
	margin-top: 20px !important;
	font-size:20px;
  	font-weight:bolder;
  	color:#00004d;
  	letter-spacing:2px;
}

#headerTop{
	margin-top: 40px !important;
}

#cont{
	margin-left: 14% !important;
	margin-top: 2% !important;
	margin-down: 2% !important;
}

input[type='file'] {
  color: rgba(0, 0, 0, 0)
}
<style>
    <%@include file="bootstrap/css/bootstrap.min.css" %>
</style>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</style>
<meta charset="ISO-8859-1">
<title>Image Management Utility</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
	ImageDao imgdao = new ImageDao();
		double totalsize=0.0D;
	%>
	<nav id="imgUtil" class="navbar">
		<div class="container-fluid">
    		<div class="navbar-header">
				<a id="logo" class="navbar-brand" href="#">Image Management Utility</a>
			</div>
		</div>
	</nav>
	<div id="userNam">
		<h5 class="page-header" ><i>Hi, <%=session.getAttribute("username")%>!!!</i></h5>
	</div>
	<div id="headerTop" class="container">
		<h3 class="page-header">Please select an image file to upload (Max Size 1MB)</h5>
	</div>
	<div id="cont">
	<form action="<%=request.getContextPath()%>/imageUtility" method="post"
		enctype="multipart/form-data">
		<input  type="hidden" name=userid
			value="<%=session.getAttribute("username")%>"> 
			<input type="text" name="filename" required> 
			 <input type="file" accept="image/*" name="photo" size="1" class="filebutton" required>  
			<input class="btn btn-dark"  type="submit" name="submit" value="Submit">
			<a class="btn btn-dark" href="index.jsp" class="showasbutton"> Cancel</a>
	</form>
	</div>
	<div class="container mt-3">
		<h3 class="page-header">Uploaded Photos</h5>
	</div>

	<!-- <table  border="1" align="center" style="width: 100%"> -->
	<div class="container">
	<table class="table table-hover">
		<thead class="thead-dark">
		<tr>
			<th scope="col">S.No</th>
			<th scope="col">Name</th>
			<th scope="col">Size(in MB)</th>
			<th scope="col">Preview</th>
			<th scope="col">Actions</th>
			</tr>
		</thead>
		<%
		String username = (String) session.getAttribute("username");
				int identity = imgdao.getUserId(username);
				List<ImageEntity> imageList = imgdao.getListData(identity);
				if (!imageList.isEmpty()) {

			Iterator<ImageEntity> iterator = imageList.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				i++;
				ImageEntity image = iterator.next();
				totalsize = totalsize + image.getImagesize();
		%>
		<tbody>
		<tr>

			<th scope="row"><%=i%></th>
			<td><%=image.getImageName()%></td>
			<td><%=image.getImagesize()%></td>
			<td><img id="imageFit" src="data:image/png;base64,<%=image.getBase64Image()%>"
				height="55" width="55"></td>
			<td><a class="btn btn-dark" href="<%=request.getContextPath()%>/editImg?idEdit=<%=image.getImageId()%>">Edit</a>
			<a class="btn btn-dark" href="<%=request.getContextPath()%>/imgDelete?imgId=<%=image.getImageId()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	</div>
	<%
	}
	%>
	<div id="cont">
	<br>
<h5 class="page-header">Total Size : <%=totalsize%></h5> <br>
</div>
</body>
</html>