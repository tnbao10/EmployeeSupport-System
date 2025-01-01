<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<mt:EmployeeLayout title="Employee">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Profile</h3>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">Your Profile</div>
							<p class="text-success">${msgSuccess}</p>
							<p class="text-success">${msgChangePass}</p>							
						</div>
						
						<div class="card-body">
							<div class="row">
								<div class="col-md-6 col-lg-4">
									<div class="avatar avatar-xxl">
										<img
											src="${pageContext.request.contextPath}/assets/img/${nhanvien.hinhAnh}"
											alt="..." class="avatar-img rounded-circle">
									</div>
									<div class="form-group">
										<label for="username">Username</label> <input type="text"
											class="form-control" id="username" value="${nhanvien.username}" disabled />
									</div>

									<div class="form-group">
										<label for="fullname">FullName</label> <input type="text"
											class="form-control" id="fullname" value="${nhanvien.hoTen }" disabled />
									</div>

									<div class="form-group">
										<label for="authority">Authority</label> <input type="text"
											class="form-control" id="authority" value="Employee" disabled />
									</div>

									<div class="form-group">
										<label for="dob">Date of Birth</label>
										<fmt:formatDate value="${nhanvien.ngaySinh}"
											pattern="dd/MM/yyyy" var="_dob" />
										<input type="text" name="dob" id="dob" class="form-control"
											value="${nhanvien.ngaySinh} " disabled>
									</div>




									<div class="card-action">
										<button class="btn btn-success">
											<a style="color: white" href="${pageContext.request.contextPath}/employee?action=edit-profile">Edit Profile</a>
										</button>
										<button class="btn btn-danger">
											<a style="color: white" href="${pageContext.request.contextPath}/employee?action=change-password">Change Password</a>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</mt:EmployeeLayout>
