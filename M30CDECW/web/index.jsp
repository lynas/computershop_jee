<%-- 
    Document   : index
    Created on : 06-Mar-2013, 20:20:24
    Author     : Md Sazzad Islam
    ID         : 4628965   
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computer Shop</title>
        <link href="style/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="main">
            <div id="header">
                <jsp:include page="template/header.jsp" flush="true" />
            </div>
            <div id="header2">
                <jsp:include page="template/header2.jsp" flush="true" />
            </div>

            <div id="indexLeftColumn">
                <div id="welcomeText">
                    <p>[ welcome ]</p>
                    <p> To the Computer Shop </p>
                    <p style="padding-bottom: 260px;"> All Kinds of Computer Product is available here </p>
                </div>
            </div>

            <div id="indexRightColumn">

                <div class="categoryBox">
                    <a href="PageOperation?page=computer">
                        <span class="categoryLabelText">Computer</span>
                    </a>
                </div>
                <div class="categoryBox">
                    <a href="PageOperation?page=camera">
                        <span class="categoryLabelText">Camera</span>
                    </a>
                </div>
                <div class="categoryBox">
                    <a href="PageOperation?page=soundSystem">
                        <span class="categoryLabelText">Sound System</span>
                    </a>
                </div>
                <div class="categoryBox">
                    <a href="PageOperation?page=battery">
                        <span class="categoryLabelText">Batteries</span>
                    </a>
                </div>
            </div>

            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>

    </body>
</html>
