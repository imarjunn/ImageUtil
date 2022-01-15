<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
    <%@include file="bootstrap/css/bootstrap.min.css" %>
</style>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<title>Sign up</title>
</head>
<body>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-5">
			<div class="card">
				<h2 class="card-title text-center">Registration</h2>
				<div class="card-body py-md-4">
					<form action="<%=request.getContextPath()%>/register" method="post">
						<div class="form-group">
							<label for="name">Name</label>
							<input type="text" name="name" class="form-control" placeholder="Enter name">
						</div>
						<div class="form-group">
							<label for="username">Username</label>
							<input type="text" name="username" class="form-control" placeholder="username">
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" name="password" class="form-control" placeholder="Password">
						</div>
						<div class="form-group">
							<label for="rePassword">Retype Password</label>
							<input type="password" name="rePassword" class="form-control" placeholder="Retype Password">
						</div>
						<div class="d-flex flex-row align-items-center justify-content-between">
							<a href="index.jsp">Already have an account?</a>
							<input class="btn btn-primary" type="submit" name="submit" value="Signup">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>