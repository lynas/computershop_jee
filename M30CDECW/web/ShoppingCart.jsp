<%-- 
    Document   : ShoppingCart
    Created on : 09-Mar-2013, 20:49:12
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
        <title>Computer Shop - My Cart</title>
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


                <table>
                    <c:choose>
                        <c:when test="${cartSize > 0}">
                            <form action="TransactionOperation?action=clearCart" method="POST">
                                <input style="width: 200px; height: 50px; font-weight: bold; font-size: 15px; color: red;" type="submit" value="Clear Cart" />
                            </form>
                            
                            <tr class="productDetails the">
                                <td>Item Name</td>
                                <td style="text-align: center">Quantity</td>
                                <td>Price</td>
                                <td></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <p style="padding-top: 70px; color: red; font-weight: bold; font-size: 30px;">Cart Is Empty</p>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach var="product" items="${productList}">
                        <form action="TransactionOperation?pid=${product.getItemId()}&remove=1&action=summary" method="POST">
                            <input type="hidden" name="productID" value="${product.getItemId()}">
                            <input type="hidden" name="page" value="${product.getItemType()}">
                            <input type="hidden" name="price" value="${product.getItemPrice()}">
                            <input type="hidden" name="iname" value="${product.getItemName()}">

                            <tr class="productDetails">
                                <td><span><c:out value="${product.getItemName()}"/></span></td>
                                <td style="text-align: center"><span><c:out value="${product.getItemQuantity()}"/></span></td>
                                <td><span><c:out value="${product.getItemQuantity() * product.getItemPrice()}"/></span></td>
                                <td><span><input type="submit" value="Remove" style="width: 100px; height: 40px; color: red;"/></span></td>
                            </tr>
                        </form>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${cartSize > 0}">
                            <tr class="productDetails the">
                                <td></td>
                                <td style="text-align: center">Total Price = </td>
                                <td>${totalPrice} </td>
                                <td>
                                    <form action="UserManagement?action=chekout" method="POST">
                                        <input type="submit" value="Check Out"  style="width: 120px; height: 40px; color: green; font-size: 20px; font-weight: bold;"/>
                                    </form>

                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </table>
            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>