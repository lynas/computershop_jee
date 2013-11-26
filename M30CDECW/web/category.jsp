<%-- 
    Document   : category
    Created on : 07-Mar-2013, 09:49:51
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
        <title>Computer Shop - Category</title>
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
                    <c:forEach var="product" items="${productList}">
                        <form action="CartOperation" method="POST">
                            <input type="hidden" name="productID" value="${product.getItemId()}">
                            <input type="hidden" name="page" value="${product.getItemType()}">
                            <input type="hidden" name="price" value="${product.getItemPrice()}">
                            <input type="hidden" name="iname" value="${product.getItemName()}">
                            <tr class="productDetails">
                                <td><span><c:out value="${product.getItemName()}"/></span></td>
                                <td><span><c:out value="${product.getItemPrice()}"/></span></td>
                                <td><span><input type="text" value="1" name="itemQuantity" style="width: 20px;"/> </span></td>
                                <td><span><input type="submit" value="Add To Cart" style="width: 120px; height: 40px; font-weight: bold; font-size: 15px; color: green" /></span></td>
                            </tr>
                        </form>
                    </c:forEach>


                </table>
            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>
