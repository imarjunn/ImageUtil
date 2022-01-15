<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/editImg" method="post" enctype="multipart/form-data">
	<table>
		<tr>
			<td>Name</td>
			<td><input type="text" name="filename"></td>
		</tr>
		<tr>
			<td>Preview</td>
			<td><input type="file" name="photo"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Save"></td>
			<td><a href="imageUtility.jsp">Cancel</a></td>
		</tr>
	</table>
	</form>
</body>
</html>