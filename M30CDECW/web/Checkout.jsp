<%-- 
    Document   : Checkout
    Created on : 18-Mar-2013, 23:07:18
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
        <title>Computer Shop - Checkout</title>
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
                <jsp:include page="template/leftColumn.jsp" flush="true" />
            </div>

            <div id="indexRightColumn">
                <div style="color: green; font-size: 20px; text-align: left; margin: 100px;">Checkout successful</div>
                <p style="text-align: left; margin: 100px;">
                    <a href="index.jsp">Continue Shopping</a><br />
                    <a href='UserManagement?action=logOut' > Log Out</a>
                </p>
            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>