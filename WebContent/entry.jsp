<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBSサンプル</title>
</head>
<body>
<h1>登録画面</h1>
<form action="/bbsPostSampleDao/NameRegister" method="post">
名前：<input type="text" name="name"><br>
<input type="submit" value="登録">
</form>
</body>
</html>