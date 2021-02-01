<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>UPDATE</title>
	</head>

	<body>
		<h1>Login!</h1>
		<form action='' method='POST'>
			<p>update USERNAME, current username is "${name}"</p>
			<input name='name' type='text' />
			<br />
			
			<label>update Password, current password is "${password}"</label>
			<input name='password' type='text' />
			<br />
			
			<input type='submit' />
		</form>
	</body>

</html>