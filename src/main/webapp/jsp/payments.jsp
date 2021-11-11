<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.service.impl.PaymentService" %>
<%@ taglib uri = "tags" prefix = "m" %>

<html>
    <head>
        <title>Payments</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta charset="utf-8">
        <style><%@include file="/css/bootstrap.min.css"%></style>
        <style><%@include file="/css/navbar.css"%></style>
        <style><%@include file="/css/core.css"%></style>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="/jsp/usermain.jsp">Account</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#">Credit Cards</a>
                        </li>
                        <li class="nav-item disabled">
                            <a class="nav-link">Payments</a>
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

        <a href="/jsp/createpayment.jsp" class="btn btn-primary">Create Payment</a>

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Destination</th>
                    <th scope="col">Transaction Time</th>
                    <th scope="col">Committed</th>
                    <th scope="col">Edit</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="i" begin="0" end="${requestScope.payments.size()-1}">
                    <c:choose>
                        <c:when test="${requestScope.payments.get(i).getCommitted()}">
                            <tr>
                                <td>${(requestScope.currentPage - 1) * 5 + i + 1}</td>
                                <m:paymentoutput paymentDto="${requestScope.payments.get(i)}"/>
                                <td></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>${(requestScope.currentPage - 1) * 5 + i + 1}</td>
                                <m:paymentoutput paymentDto="${requestScope.payments.get(i)}"/>
                                <td>
                                    <form class="inline" method="post" action="/payments?command=edit_payment" >
                                        <button class="btn btn-exsm btn-primary" type="submit">edit</button>
                                        <input type="hidden" name="paymentId" value="${requestScope.payments.get(i).getId()}">
                                    </form>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${requestScope.currentPage == 1 && requestScope.maxPage != 1}">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link">First</a>
                    </li>
                    <li class="page-item active" aria-current="page">
                        <a class="page-link">1</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=2">2</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.maxPage}">Last</a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <c:if test="${requestScope.currentPage == requestScope.maxPage && requestScope.maxPage != 1}">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=1">First</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.maxPage-1}">${requestScope.maxPage-1}</a>
                    </li>
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.maxPage}">${requestScope.maxPage}</a>
                    </li>
                    <li class="page-item disabled">
                        <a class="page-link">Last</a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <c:if test="${requestScope.maxPage == 1}">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link">First</a>
                    </li>
                    <li class="page-item active">
                        <a class="page-link">1</a>
                    </li>
                    <li class="page-item disabled">
                        <a class="page-link">Last</a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <c:if test="${requestScope.currentPage > 1 && requestScope.currentPage < requestScope.maxPage}">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=1">First</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.currentPage-1}">${requestScope.currentPage-1}</a>

                    </li>
                    <li class="page-item active" aria-current="page">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.currentPage}">${requestScope.currentPage}</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.currentPage+1}">${requestScope.currentPage+1}</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="/payments?command=payments&currentPage=${requestScope.maxPage}">Last</a>
                    </li>
                </ul>
            </nav>
        </c:if>
    </body>
</html>