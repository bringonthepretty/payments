<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta charset="utf-8">
        <style><%@include file="/css/signin.css"%></style>
        <style><%@include file="/css/bootstrap.min.css"%></style>
    </head>
    <body class="text-center">
        <main class="form-signin" align="center">
            <form action="/payments?command=signin" method="post">
                <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
                <div class="form-floating">
                    <input type="text" class="form-control" id="floatingInput" placeholder="Login" name="login">
                    <label for="floatingInput">Login</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password">
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
            </form>
        </main>
    </body>
</html>