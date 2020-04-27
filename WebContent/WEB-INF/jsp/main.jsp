<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Post, java.util.List"%>
<%
	//リクエストスコープに保存された投稿リストを取得する
	List<Post> postList = (List<Post>) request.getAttribute("postList");
	//リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBSサンプル</title>
</head>
<body>
	<h1>メイン画面</h1>
	<p><a href="/bbsPostSampleDao/Main">更新</a></p>
	<form action="/bbsPostSampleDao/Main" method="post">
		<input type="text" name="text"> <input type="submit" value="投稿">
	</form>
	<% if (errorMsg != null) { %>
	<p><%= errorMsg %></p>
	<% } %>
	<% for(Post post : postList) {%>
	<p><%= post.getId() %>　<b><font color = "#009900"><%= post.getName() %></font></b>：<%= post.getCreated_at() %></p>
	<p>　　　 <%= post.getText() %></p>
	<% } %>
</body>
</html> 