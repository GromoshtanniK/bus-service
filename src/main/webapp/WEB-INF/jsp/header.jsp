<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.web.constants.SessionAttributes" %>
<%@ page import="bus.service.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) session.getAttribute(SessionAttributes.USER);
    boolean isLoggedIn = user != null && user.getUserName() != null;
%>

<div class="header">
    <div class="title">Расписание автобусов</div>
    <div class="menu">
        <div class="menu-links">
            <div class="menu-link"><a href="<%=Path.ROOT%>">Главная</a></div>
            <%if (isLoggedIn && user.getRole() == User.REGISTERED_USER) {%>
                <div class="menu-link"><a href="<%=Path.PROFILE_SERVLET%>">Профиль</a></div>
            <%}%>
            <%if (isLoggedIn && user.getRole() == User.DISPATCHER) {%>
                <div class="menu-link"><a href="<%=Path.ROUTE_EDIT_SERVLET%>">Редактирование маршрутов</a></div>
                <div class="menu-link"><a href="<%=Path.ADD_DELETE_SERVLET%>">Добавление/Удаление маршрутов</a></div>
            <%}%>
        </div>
        <div class="login">
            <%if (isLoggedIn) {%>
                <span class="login-name">Вы вошли как : <%=user.getUserName()%> </span>
                <a href="<%=Path.LOG_OUT%>">Выйти</a>
            <%} else { %>
                <a href="<%=Path.LOGIN_SERVLET%>">Войти</a> | <a href="<%=Path.REGISTRATION_SERVLET%>">Регистрация</a>
            <%} %>
        </div>
    </div>
</div>