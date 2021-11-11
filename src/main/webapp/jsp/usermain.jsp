<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page import="com.epam.jwd.service.impl.UserService" %>
<%@ page import="com.epam.jwd.service.dto.paymentdto.PaymentDto" %>

<html>

    <head>
        <title>Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta charset="utf-8">
        <style><%@include file="/css/bootstrap.min.css"%></style>
        <style><%@include file="/css/navbar.css"%></style>


      </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item disabled">
                  <a class="nav-link" href="#">Account</a>
                </li>
                <li class="nav-item ">
                  <a class="nav-link" href="#">Credit Cards</a>
                </li>
                <li class="nav-item ">
                  <a class="nav-link" href="/payments?command=payments&currentPage=1">Payments</a>
                </li>
                <li class="nav-item ">
                  <a class="nav-link" href="/payments?command=signout">Log Out</a>
                </li>
              </ul>

              <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                <label class="btn btn-outline-primary" for="btnradio1">English</label>

                <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                <label class="btn btn-outline-primary" for="btnradio2">Russian</label>
              </div>
            </div>
          </div>
        </nav>
        <%= java.util.Calendar.getInstance().getTime() %>
        <%= session.getAttribute("id") %>
        <%= session.getAttribute("role") %>
        <c:set var="user" scope="page" value="${UserService().getById(sessionScope.id)}"/>
        <c:if test="${user.accountId == null}">
           <p>My income is: <c:out value="${id}"/><p>
           <p>User: <c:out value="${user}"/><p>
        </c:if>

    </body>
</html>