<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<mt:SupportEmployeeLayout title="Support-Employee">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Change Password</h3>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">Change Password</div>
							<p class="text-danger">${error}</p>
							<p class="text-success">${msgChangePass}</p>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-6 col-lg-4">

									<!-- Form bắt đầu -->
									<form method="POST"
										action="${pageContext.request.contextPath}/support-employee?action=change-password">
										
										<div class="form-group">
											<label for="currentPass" class="form-label">Current Passowrd</label>
											<input type="password" class="form-control" id="currentPass" name="currentPassword">
										</div>
										
										<div class="form-group">
											<label for="newPass" class="form-label">New Passowrd</label>
											<input type="password" class="form-control" id="newPass" name="newPassword">
										</div>
										
										<div class="form-group">
											<label for="confirmPass" class="form-label">Confirm Passowrd</label>
											<input type="password" class="form-control" id="confirmPass" name="confirmPassword">
										</div>


										<div class="card-action">
											<button type="submit" class="btn btn-success">Change Password</button>
										</div>
									</form>
									<!-- Form kết thúc -->
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</mt:SupportEmployeeLayout>
