<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="bus.service.web.constants.Path" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jQuery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <meta charset="UTF-8">
    <title>Routes</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <div class="content">
        <div class="content-title">
            Вход
        </div>
        <form class="form-horizontal login-form" method="post" action="<%=Path.LOGIN_SERVLET%>">
            <fieldset>
                <div class="control-group">
                    <!-- Username -->
                    <label class="control-label" for="username">Имя пользователя</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>

                <div class="control-group">
                    <!-- Password-->
                    <label class="control-label" for="password">Пароль</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>

                <div class="control-group align-center">
                    <!-- Button -->
                    <div class="controls login-btn">
                        <button class="btn">Вход</button>
                    </div>
                </div>
            </fieldset>
        </form>

        <div class="login-error ${param.error}">
            Введены неверные данные
        </div>
    </div>
</div>
</body>
</html>