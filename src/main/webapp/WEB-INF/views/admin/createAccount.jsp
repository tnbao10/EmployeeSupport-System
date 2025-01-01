<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>

<mt:AdminLayout title="Admin">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Account</h3>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">

						<div class="card-header">
							<div class="card-title">Create Account</div>
							<p style="color: green">${msgSuccess}</p>
							<p style="color: red">${msgFailed}</p>
						</div>
						<div class="card-body">
							<form method="POST"
								action="${pageContext.request.contextPath}/admin?action=create-account">
								<div class="row">
									<div class="col-md-6 col-lg-4">
										<div class="form-group">
											<label for="username">Username</label> <input type="text"
												name="username" class="form-control" id="username" required />
										</div>
										<div class="form-group">
											<label for="password">Password</label> <input type="password"
												name="password" class="form-control" id="password" required />
										</div>

										<!-- Combobox -->
										<div class="form-group">
											<label for="authority">Authority</label> <select
												class="form-select" name="quyen" id="authority" required>
												<option value="1">Employee</option>
												<option value="2">Support Employee</option>
												<option value="3">Admin</option>
											</select>
										</div>

										<div class="form-check">
											<input class="form-check-input" type="checkbox" value="agree"
												name="terms" id="flexCheckDefault" required /> <label
												class="form-check-label" for="flexCheckDefault">
												Agree with terms and conditions </label>
										</div>

										<div class="card-action">
											<button type="submit" class="btn btn-success">Create</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</mt:AdminLayout>
