<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<mt:AdminLayout title="Admin">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Request Manament</h3>
				<ul class="breadcrumbs mb-3">
					<li class="nav-home"><a
						href="${pageContext.request.contextPath}/admin"> <i
							class="icon-home"></i>
					</a></li>
				</ul>
			</div>

			<!-- Form Search By Dates -->
			<div class="row mb-3">
				<div class="col-md-12">
					<form method="GET"
						action="${pageContext.request.contextPath}/admin">

						<div class="row">
							<div class="col-md-4">
								<label class="form-label">Start Date</label> <input type="date"
									class="form-control" name="startDate" />
							</div>
							<div class="col-md-4">
								<label class="form-label">End Date</label> <input type="date"
									class="form-control" name="endDate" />
							</div>
							<div class="col-md-4 d-flex align-items-end">
								<button type="submit" class="btn btn-primary">Search</button>
							</div>
							<input type="hidden" name="action" value="search-by-dates">
						</div>
					</form>
				</div>
			</div>
			<!-- Kết thúc Form Search By Dates -->

			<!-- Form Search By Priority -->
			<div class="row mb-3">
				<div class="col-md-12">
					<form method="GET"
						action="${pageContext.request.contextPath}/admin">
						<div class="form-group">
							<label>Select a Priority:</label> <select name="priorityId"
								class="form-control">
								<option value="1">High</option>
								<option value="2">Medium</option>
								<option value="3">Low</option>
							</select>
						</div>
						<button type="submit" class="btn btn-primary">Search</button>
						<input type="hidden" name="action" value="search-by-priority">
					</form>
				</div>
			</div>
			<!-- Kết thúc Form Search By Priority -->

			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<h4 class="card-title">Request List</h4>
							<p class="text-success">${msgUpdateSuccess }</p>
							<p class="text-danger">${msgUpdateFailed }</p>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table id="basic-datatables"
									class="display table table-striped table-hover">
									<thead>
										<tr>
											<th>Header</th>
											<th>Content</th>
											<th>Priority</th>
											<th>Date</th>
											<th>Employee</th>
											<th>Support Employee</th>
											
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Header</th>
											<th>Content</th>
											<th>Priority</th>
											<th>Date</th>
											<th>Employee</th>
											<th>Support Employee</th>
											<th>Assign Support Employee</th>
											<th>Action</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="yeucau" items="${yeucaus}">
											<form method="POST" action="${pageContext.request.contextPath}/admin">
												<tr>
													<td>${yeucau.tieuDe}</td>
													<td>${yeucau.noiDung}</td>
													<td>${yeucau.douutien.tenDoUuTien}</td>
													<td>
														<fmt:formatDate value="${yeucau.ngayGui}" pattern="dd/MM/yyyy" />
													</td>
													<td>${yeucau.nhanvienByMaNvGui.username}</td>
													<td>${yeucau.nhanvienByMaNvXuLy.username}</td>
													<td>
														<select name="supportEmployeeUsername">
																<c:forEach var="nhanvienhotro" items="${nhanvienhotros}">
																	<option value="${nhanvienhotro.username}">${nhanvienhotro.username}-(Support Employee)</option>
																</c:forEach>
														</select>
													</td>
													<td>
														<button type="submit">Assign</button>
														<input type="hidden" name="action" value="assignment">
														<input type="hidden" name="yeucauId" value="${yeucau.maYeuCau}">
													</td>
	
												</tr>
											</form>
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
