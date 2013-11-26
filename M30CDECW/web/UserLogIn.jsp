<%-- 
    Document   : UserLogIn
    Created on : 13-Mar-2013, 18:53:13
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
        <title>Computer Shop - User Login</title>
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
                <p>
                <form name="userLogIn" action="UserManagement?action=authenticateUser" method="POST">
                    <p style="color: red">
                        <c:choose>
                            <c:when test="${message != null}">
                                ${message}
                            </c:when>
                        </c:choose>
                    </p>
                    <table>
                        <tr><td>User Name </td><td style="width: 200px;"><input type="text" name="userName" style="width: 150px;" /></td></tr>
                        <tr><td>User Password &nbsp;&nbsp;</td><td style="width: 200px;"><input type="password" name="userPassword" /></td></tr>
                        <td></td><td><input type="submit" value="Log in" style="float: right; width: 120px;height: 25px;"></td>
                    </table>
                </form>
                </p>





            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>