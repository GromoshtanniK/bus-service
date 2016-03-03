<%@ page import="bus.service.web.constants.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jQuery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/scripts.js"></script>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <meta charset="UTF-8">
    <title>Routes</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>
    <div class="content">
        <div class="content-title">
            Регистрация
        </div>
        <form class="form-horizontal registration-form" method="post" action="<%=Path.REGISTRATION_SERVLET%>">
            <fieldset>
                <div class="control-group ${param.username_error}">
                    <!-- Username -->
                    <label class="control-label" for="username">Имя пользователя</label>

                    <div class="controls">
                        <input type="text" id="username" name="username" placeholder="" class="input-xlarge" value="${param.username}">

                        <p class="help-block">Имя пользователя может состоять из любых чисел и букв без пробелов</p>
                    </div>
                </div>

                <div class="control-group ${param.email_error}">
                    <!-- E-mail -->
                    <label class="control-label" for="email">E-mail</label>

                    <div class="controls">
                        <input type="text" id="email" name="email" placeholder="" class="input-xlarge" value="${param.email}">

                        <p class="help-block">Пожалуйста, предоставьте свой E-mail</p>
                    </div>
                </div>

                <div class="control-group ${param.password_error}">
                    <!-- Password-->
                    <label class="control-label" for="password">Пароль</label>

                    <div class="controls">
                        <input type="password" id="password" name="password" placeholder="" class="input-xlarge">

                        <p class="help-block">Пароль должен состоять как минимум из 6 символов</p>
                    </div>
                </div>

                <div class="control-group ${param.password_error}">
                    <!-- Password -->
                    <label class="control-label" for="password_confirm">Пароль (Подтверждение)</label>

                    <div class="controls">
                        <input type="password" id="password_confirm" name="password_confirm" placeholder=""
                               class="input-xlarge">

                        <p class="help-block">Подтвердите пароль</p>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Button -->
                    <div class="controls registration-btn">
                        <button class="btn">Регистрация</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>