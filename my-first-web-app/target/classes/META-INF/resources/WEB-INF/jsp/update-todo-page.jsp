<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
		<title>
			Update Todo
		</title>
	</head>
	<body>
		<div class="container">
			<form:form method="post" modelAttribute="todo">
				<hr>
				<h1>Update Todo${id}</h1>
				Description:<form:input type="txt" path="description" required="required"/>
				IsDone:<form:radiobutton path="done" value="true" />
						<form:radiobutton path="done" value="false" />
				<form:input type="hidden" path="id" />
				
				<input type="submit" class="btn btn-success">
				<form:errors path="description" cssClass="text-warning"/>
			</form:form>
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	</body>
</html>