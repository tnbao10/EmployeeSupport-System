<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="mt" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<mt:EmployeeLayout title="Employee">
	<jsp:attribute name="content">
		<div class="page-inner">
			<div class="page-header">
				<h3 class="fw-bold mb-3">Request</h3>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<div class="card-title">Create Request</div>
							<p class="text-success">${msgCreateSuccess}</p>
							<p class="text-danger">${msgCreateFailed}</p>
						</div>
						<div class="card-body">
							<div class="row">
								<form  method="POST" action="${pageContext.request.contextPath}/employee?action=create-request">
																
									<div class="form-group">
										<label  style="font-weight: bold">Header</label>
										<p class="text-danger">${msgNullHeader}</p>
										<div class="col-md-9 ">
											<input type="text" class="form-control" name="header"/>
										</div>
									</div>

									<div class="form-group">
										<label style="font-weight: bold">Content</label>
										<p class="text-danger">${msgNullContent}</p>
										<textarea class="form-control"  rows="5" name="content">
                          				</textarea>
									</div>

									<!-- Combobox -->
									<div class="form-group">
										<label  style="font-weight: bold">Priority</label>
									
										 <select class="form-select" name="priority">
											<option value="1">High</option>
											<option value="2">Medium</option>
											<option value="3">Low</option>
										</select>
									</div>

									<div class="form-group">
										<label style="font-weight: bold">Date</label>
										<input type="text" class="form-control" name="date" value="<fmt:formatDate value="${date}" pattern="dd/MM/yyyy"/>" readonly />
									</div>

									<div class="card-action">
										<button class="btn btn-success">Create Request</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</mt:EmployeeLayout>
