<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<mt:SupportEmployeeLayout title="Employee">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Edit</h3>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">Edit Profile</div>
							<p style="color: red">${msgFailed}</p>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-6 col-lg-4">
									<div class="avatar avatar-xxl">
										<img
											src="${pageContext.request.contextPath}/assets/img/${nhanvien.hinhAnh}"
											alt="..." class="avatar-img rounded-circle">
									</div>

									<!-- Form bắt đầu -->
									<form method="POST"
										action="${pageContext.request.contextPath}/support-employee?action=update-profile"
										enctype="multipart/form-data"
										>
										
										<div class="form-group">
											<label>Upload File</label>
											<input type="file" name="file">
										</div>
										
										<div class="form-group">
											<label for="username">Username</label> <input type="text"
												class="form-control" id="username" name="username"
												value="${nhanvien.username}" disabled />
										</div>

										<div class="form-group">
											<label for="fullname">FullName</label> <input type="text"
												class="form-control" id="fullname" name="fullname"
												value="${nhanvien.hoTen }" />
										</div>

										<div class="form-group">
											<label for="authority">Authority</label> <input type="text"
												class="form-control" id="authority" name="authority"
												value="Support-Employee" disabled />
										</div>

										<div class="form-group">
											<label for="dob">Date of Birth</label>
											<fmt:formatDate value="${nhanvien.ngaySinh}"
												pattern="dd/MM/yyyy" var="_dob" />
											<input type="text" name="dob" id="dob" class="form-control"
												value="${_dob}">
										</div>

										<div class="card-action">
											<button type="submit" class="btn btn-success">Save
												Changes</button>
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
