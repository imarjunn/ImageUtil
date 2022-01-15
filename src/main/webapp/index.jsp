<html>
<head>
<style>
    <%@include file="bootstrap/css/bootstrap.min.css" %>

#center{
	margin-top: 10% !important;
}
</style>
<!-- <link href="style.css" rel="stylesheet" type="text/css" />	 -->
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div  class="container">
	<div id="center" class="row justify-content-center">
		<div class="col-md-5">
			<div class="card">
				<h2 class="card-title text-center">Login</h2>
				<div class="card-body py-md-4">
					<form action="<%=request.getContextPath()%>/login" method = "post">
						<div class="form-group">
							<label for="username">Username</label>
							<input type="text" name="username" class="form-control" placeholder="username">
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<input type="password" name="password" class="form-control" placeholder="Password">
						</div>
						<div>
						<p>Forgot your password?</p>
						</div>
						<div class="d-flex flex-row align-items-center justify-content-between">
						<a href="register.jsp">Register</a>
						<button class="btn btn-primary" type="submit" >Login</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
