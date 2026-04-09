<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/8/2026
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Danh sách món ăn</title>
  <style>
    body { font-family: sans-serif; margin: 50px; line-height: 1.6; }
    .highlight { color: #e67e22; font-weight: bold; }
  </style>
</head>
<body>
<h1>Kết quả tìm kiếm</h1>
<p>Thông báo: <span class="highlight">${msg}</span></p>

<ul>
  <li>Loại thực phẩm: <b>${cat}</b></li>
  <li>Số lượng hiển thị tối đa: <b>${lim}</b> món</li>
</ul>
</body>
</html>