		
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<!doctype html>


<div class="container">
	<hr>
	<h1>Your ToDo's</h1>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>TargetDate</th>
				<th>IsDone</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.description}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.done}</td>
					<td><a href="delete-todo?id=${todo.id}" class="btn btn-danger">DELETE</a></td>
					<td><a href="update-todo?id=${todo.id}" class="btn btn-primary">UPDATE</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href ="add-todo" class="btn btn-success">Add Task</a>
</div>

<%@ include file="common/footer.jspf" %>
			
		<!-- Bootstrap button colours
			<button type="button" class="btn btn-primary">Primary</button>
			<button type="button" class="btn btn-secondary">Secondary</button>
			<button type="button" class="btn btn-success">Success</button>
			<button type="button" class="btn btn-danger">Danger</button>
			<button type="button" class="btn btn-warning">Warning</button>
			<button type="button" class="btn btn-info">Info</button>
			<button type="button" class="btn btn-light">Light</button>
			<button type="button" class="btn btn-dark">Dark</button>
			<button type="button" class="btn btn-link">Link</button>-->