<%-- 
    Document   : UserPanel
    Created on : 29-Mar-2013, 02:02:33
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
        <title>Computer Shop - My Page</title>
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
                <p>[ <a href="index.jsp">Home</a> ]</p>
            </div>

            <div id="indexRightColumn">

                <c:choose>
                    <c:when test="${activeUser.userID != 0}">
                        <br />
                        <a href="UserManagement?action=myorder">My Orders</a>
                        <table border=" 1px solid gray">
                            <c:choose>

                                <c:when test="${action == 'myorder' && listSize!=0}">

                                    <tr><td>Date</td><td>Product Name</td><td>Product Quantity</td><td>Product Price</td></tr>
                                    <c:forEach var="product" items="${productList}">
                                        <tr><td>${product.getOrderDate()}</td><td>${product.getProductName()}</td><td>${product.getPdoductQuantity()}</td><td>${product.getProductPrice()}</td></tr>

                                    </c:forEach>

                                </c:when>
                                <c:when test="${action == 'myorder' && listSize==0}">

                                    <br /><br /> <span style="color: red; font-size: 30px;">[[ You have no purchase record..]]</span>

                                </c:when>

                            </c:choose>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div style="line-height: 1400%; color: red; font-size: 30px;">[[ You Are not Authorised to view this page ]]</div>

                    </c:otherwise>
                </c:choose>


            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>

