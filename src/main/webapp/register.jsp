<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
	<form method="post" action=register>
		<div class="input">
			<label>Username</label>
			<input type='text' name='login'>
		</div>
		<div class="input">
			<label>Password</label>
			<input type='password' name='mdp'><br>
		</div>
	
		<button type="submit">register</button>
		<a href="login">Login</a>
	</form>
</body>
</html>