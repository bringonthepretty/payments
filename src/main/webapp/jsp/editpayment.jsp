<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
    <head>
        <title>Payments</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta charset="utf-8">
        <style><%@include file="/css/bootstrap.min.css"%></style>

        <style><%@include file="/css/signin.css"%></style>
    </head>

    <body>
        <main class="form-signin" align="center">
            <form action="/payments?command=commit_payment_changes" method="post" autocomplete="off">
                <h1 class="h3 mb-3">Please edit your payment</h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Password" name="name" value="${requestScope.payment.getName()}">
                    <label for="floatingInput">name</label>
                </div>
                <div class="form-floating">
                    <input type="number" class="form-control" id="floatingInput" placeholder="Password" name="price" value="${requestScope.payment.getPrice()}">
                    <label for="floatingInput">price</label>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Password" name="destination" value="${requestScope.payment.getDestinationAddress()}">
                    <label for="floatingPassword">destination</label>
                </div>
                <input type="hidden" name="paymentId" value="${requestScope.payment.getId()}">
                <div class="btn-group" role="group" aria-label="Basic example">
                  <button type="submit" class="btn btn-primary" name="action" value="saveAndPay">Save changes and send transaction</button>
                  <button type="submit" class="btn btn-primary" name="action" value="save">Save changes</button>
                </div>
            </form>
        </main>
    </body>
<html>