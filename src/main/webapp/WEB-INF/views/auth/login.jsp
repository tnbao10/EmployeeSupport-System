<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
</head>
<body>
	<section class="vh-100 d-flex flex-column">
		<div class="container-fluid h-custom flex-grow-1">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-md-9 col-lg-6 col-xl-5">
					<img
						src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
						class="img-fluid" alt="Sample image">
				</div>
				<div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
					<form method="post" action="${pageContext.request.contextPath}/auth?action=login">
						<p style="color: red">${messageFail}</p>
						<h1>SIGN IN</h1>
						<br>
						<!-- Email input -->
						<div class="form-outline mb-4">
							Username <input type="text" name="username"
								class="form-control form-control-lg" style="font-size: 20px;" />
						</div>
						<!-- Password input -->
						<div class="form-outline mb-4">
							Password <input type="password" name="password"
								class="form-control form-control-lg" style="font-size: 20px;" />
						</div>
						<button type="submit" class="btn btn-primary btn-lg" style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
					</form>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<div
			class="d-flex flex-column text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary mt-auto">
			<!-- Copyright -->
			<div class="text-white mb-3 mb-md-0">Copyright © 2020. All
				rights reserved.</div>
			<!-- Right -->
			<div>
				<a href="#!" class="text-white me-4"> <i
					class="fab fa-facebook-f"></i>
				</a> <a href="#!" class="text-white me-4"> <i class="fab fa-twitter"></i>
				</a> <a href="#!" class="text-white me-4"> <i class="fab fa-google"></i>
				</a> <a href="#!" class="text-white"> <i class="fab fa-linkedin-in"></i>
				</a>
			</div>
		</div>
	</section>


	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
