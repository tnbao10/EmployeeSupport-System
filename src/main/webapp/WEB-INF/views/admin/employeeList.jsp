<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<!-- Import Thư viện JSTL (Tag List) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Import Thư viện Format thời gian (Tag List) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<mt:AdminLayout title="Admin-Empolyee-List">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Employee Management</h3>
				<ul class="breadcrumbs mb-3">
					<li class="nav-home"><a
						href="${pageContext.request.contextPath}/admin"> <i
							class="icon-home"></i>
					</a></li>
				</ul>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h4 class="card-title">Employee List</h4>
						</div>
						<div class="card-body">
							<div class="mb-3">
								<form method="get"
									action="${pageContext.request.contextPath}/admin">
									<div class="input-group">
										<input type="text" name="username" class="form-control"
											placeholder="Search Employee" />
										<button type="submit" class="btn btn-primary">Search</button>
										<input type="hidden" name="action" value="search-by-username" />
									</div>
								</form>
							</div>
							<div class="table-responsive">
								<table id="basic-datatables"
									class="display table table-striped table-hover">
									<thead>
										<tr>
											<th>Username</th>
											<th>FullName</th>
											<th>Photo</th>
											<th>Authority</th>
											<th>Date Of Birth</th>
											<th>Action</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Username</th>
											<th>FullName</th>
											<th>Photo</th>
											<th>Authority</th>
											<th>Date Of Birth</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="nhanvien" items="${nhanviens}">
											<c:if test="${nhanvien.quyen != 3}">
												<tr>
													<td>${nhanvien.username}</td>
													<td>${nhanvien.hoTen}</td>
													<td><img
														src="${pageContext.request.contextPath}/assets/img/${nhanvien.hinhAnh}"
														width="100" height="100"></td>
													<td>${nhanvien.quyen == 1 ? "Employee" : nhanvien.quyen == 2 ? "SupportEmployee" : nhanvien.quyen == 3 ? "Admin" : "Unknown"}
													</td>
													<td><fmt:formatDate value="${nhanvien.ngaySinh}"
															pattern="dd/MM/yyyy" /></td>
													<td><a
														href="${pageContext.request.contextPath}/admin?action=employee-request-list&quyen=${nhanvien.quyen}&username=${nhanvien.username}"
														class="btn btn-primary">Details</a></td>
												</tr>
											</c:if>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</jsp:attribute>
</mt:AdminLayout>