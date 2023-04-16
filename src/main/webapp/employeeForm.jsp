<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="comman.jsp"%>
<%@ include file="header.jsp"%>
<div class="row" style="padding-top: 60px">
	<div class="card" style="margin-top: 5px">
		<div class="card-body">


			<form action="employee" name=form1 method="post">
				<div class="container">
					<div class="row">
						<div class="col">
							<input
								type="text" class="form-control inpSty" id="firstName" name="firstName" placeholder="First Name"
								aria-label="First Name" required>
						</div>
						<div class="col">
							<input
								type="text" class="form-control inpSty" id="middleName" name="middleName" placeholder="Middle Name"
								aria-label="Middle Name">
						</div>
						<div class="col">
							<input
								type="text" class="form-control inpSty" id="lastName" name="lastName"  placeholder="Last Name"
								aria-label="Last Name" required>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<input
								type="text" class="form-control inpSty" name="dob" placeholder="Date Of Birth"
								id='datepicker' required>
						</div>
						<div class="col">
							<input
								type="text" class="form-control inpSty" id="salary" name="salary"  placeholder="Salary"
								aria-label="Salary" required>
						</div>
						<div class="col"></div>
					</div>
					<div class="row">
						<div class="col">
							<label class="form-label"> Gender </label>
							<div>
								<span class="form-check"> <input class="form-check-input"
									type="radio" name="gender"value="M"  id="flexRadioDefault1" checked>
									<label class="form-check-label" for="flexRadioDefault1">
										Male </label>
								</span> <span class="form-check"> <input
									class="form-check-input" type="radio" name="gender" value="F"
									id="flexRadioDefault2"> <label class="form-check-label"
									for="flexRadioDefault2">Female </label>
								</span>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<button type="submit" class="btn btn-outline-primary" onClick="return checkform()">Submit</button>
							<button type="reset" class="btn btn-outline-secondary">Reset</button>
						</div>
					</div>

				</div>
			</form>

		</div>
	</div>
</div>