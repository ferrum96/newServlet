<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.10.2020
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@page import="ru.appline.logic.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <h1>Домашняя страница по работе с пользователем</h1>
  Введите ID пользователя (0 - для вывода списка всех пользователей)
  <br/>
  Доступно: <%
  Model model = Model.getInstance();
  out.print(model.getFromList().size());
  %>
  <form method="get" action="get">
    <label>ID
      <input type="text" name="id">
    </label>
    <button type="submit">Поиск</button>
  </form>

  <form method="get" action="delete">
    <label>ID
      <input type="text" name="id">
    </label>
    <button type="submit">Удалить</button>
  </form>

  <a href="addUser.html">Создать нового пользователя</a>
  <br/>
  <a href="changeUser.html">Изменить существующего пользователя</a>

  </body>
</html>
