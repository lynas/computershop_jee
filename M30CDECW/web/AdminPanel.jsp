<%-- 
    Document   : AdminPanel
    Created on : 20-Mar-2013, 01:28:48
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
        <title>Computer Shop - Administration Panel</title>
        <link href="style/style.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

    </head>
    <body>
        <div id="main">
            <div id="header">
                <jsp:include page="template/header.jsp" flush="true" />
            </div>
            <div id="header2">
                <jsp:include page="template/header2.jsp" flush="true" />
            </div>

            <div id="indexLeftColumn" style="height: 300px;">

                <p>[ Admin Pannel ]</p>
                <p>[ <a href="AdminAction?action=goToIndexPage">Home</a> ]</p>

            </div>

            <div id="indexRightColumn">
                <c:choose>
                    <c:when test="${activeUser.userType == 'admin'}">
                        <table>
                            <tr>
                                <td class="adminOption"><span class="actionCat"><a href="AdminAction?action=add">Add new Product</a></span></td>

                            </tr>
                            <c:choose>
                                <c:when test="${action == 'add'}">
                                    <tr>
                                        <td class="subAdminOption" style="background: azure; text-align: left;">
                                            <span class="" style="">
                                                <form action="AdminAction?action=addNewProduct" method="POST" name="productType" id="pt">
                                                    <table>
                                                        <tr><td>Product Type:</td>
                                                            <td>
                                                                <select name="productType">
                                                                    <option >computer</option>
                                                                    <option >camera</option>
                                                                    <option >soundSystem</option>
                                                                    <option >battery</option>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr><td>Product Name:</td><td><input type="text" name="productName" /></td></tr>
                                                        <tr><td>Product Quantity:</td><td><input type="text" name="productQuantity" /></td></tr>
                                                        <tr><td>Unite Price:</td><td><input type="text" name="productPrice" /></td></tr>
                                                        <tr><td></td><td><input type="submit" value="Add" /></td></tr>
                                                    </table>
                                                </form>
                                            </span>
                                        </td>

                                    </tr>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${action == 'addSuccess'}">
                                    <td class="subAdminOption" style="background: azure; text-align: left;">
                                        <span class="" style="color: green; font-size: 25px;">
                                            Add Success !!
                                        </span>
                                    </td>
                                </c:when>
                            </c:choose>
                            <tr>
                                <td class="adminOption"><span class="actionCat"><a href="AdminAction?action=update">Update Existing Product</a></span></td>

                            </tr>
                            <c:choose>
                                <c:when test="${action == 'update'}">
                                    <tr>
                                        <td class="subAdminOption" style="background: azure; text-align: left;">
                                            <span class="" style="">
                                                <form action="AdminAction?action=updateProduct" method="POST">
                                                    <table>
                                                        <tr>
                                                            <td>Product Type:</td>
                                                            <td>
                                                                <select name="productType" class="upItemType">
                                                                    <option> Select</option>
                                                                    <c:forEach var="product" items="${productList}">
                                                                        <option> <c:out value="${product.getItemType()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Product Name:</td>
                                                            <td>
                                                                <select class="upItemName" name="productName">

                                                                </select>
                                                            </td>
                                                        </tr>


                                                    </table>
                                                    <table>
                                                        <span id="details"></span>

                                                    </table>
                                                </form>
                                            </span>
                                        </td>

                                    </tr>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${action == 'updateSuccess'}">
                                    <td class="subAdminOption" style="background: azure; text-align: left;">
                                        <span class="" style="color: green; font-size: 25px;">
                                            Update Success !!
                                        </span>
                                    </td>
                                </c:when>
                            </c:choose>        
                            <tr>
                                <td class="adminOption"><span class="actionCat"><a href="AdminAction?action=delete">Delete a Product</a></span></td>

                            </tr>
                            <c:choose>
                                <c:when test="${action == 'delete'}">
                                    <tr>
                                        <td class="subAdminOption" style="background: azure; text-align: left;">
                                            <span class="" style="">
                                                <form action="AdminAction?action=deleteProduct" method="POST">
                                                    <table>
                                                        <tr><td>Product Type: </td>
                                                            <td>
                                                                <select name="productType" class="delProductType">
                                                                    <option> Select</option>
                                                                    <c:forEach var="product" items="${productList}">
                                                                        <option> <c:out value="${product.getItemType()}"/></option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr><td>Product Name: </td>
                                                            <td>
                                                                <select name="productName" class="delProductName">

                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr><td></td><td><input type="submit" value="Delete" /></td></tr>
                                                    </table>
                                                </form>
                                            </span>
                                        </td>

                                    </tr>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${action == 'deleteSuccess'}">
                                    <td class="subAdminOption" style="background: azure; text-align: left;">
                                        <span class="" style="color: green; font-size: 25px;">
                                            Delete Success !!
                                        </span>
                                    </td>
                                </c:when>
                            </c:choose>
                            <tr>
                                <td class="adminOption"><span class="actionCat"><a href="AdminAction?action=userOrder">View User Order</a></span></td>

                            </tr>
                            <c:choose>
                                <c:when test="${action == 'userOrder'}">
                                    <tr>
                                        <td class="subAdminOption" style="background: azure; text-align: left;">
                                            <span class="" style="">
                                                <form action="AdminAction?action=showUserOrder" method="POST">
                                                    Select User: &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <select style="width: 170px;" name="selectedUserName">
                                                        <c:forEach var="user" items="${userList}">

                                                            <option> <c:out value="${user.getUserName()}"/></option>

                                                            <%-- <input type="hidden" name="userId" value="${user.getUserID()}" /> --%>
                                                        </c:forEach>
                                                    </select>
                                                    <input type="submit" value="Show Order" />

                                                </form>
                                            </span>
                                        </td>

                                    </tr>
                                </c:when>
                            </c:choose>

                            <c:choose>
                                <c:when test="${action == 'showUserOrder' && listSize!=0}">

                                    <table border=" 1px solid gray">


                                        <tr><td>Date</td><td>Product Name</td><td>Product Quantity</td><td>Product Price</td></tr>
                                        <c:forEach var="product" items="${productList}">
                                            <tr><td>${product.getOrderDate()}</td><td>${product.getProductName()}</td><td>${product.getPdoductQuantity()}</td><td>${product.getProductPrice()}</td></tr>

                                        </c:forEach>

                                    </table>

                                </c:when>
                                <c:when test="${action == 'showUserOrder' && listSize==0}">

                                    <span style="color: red; font-size: 30px;">[[ This user has no purchase record..]]</span>

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
        <script type="text/javascript" src="js/jsfunc.js"></script>
    </body>
</html>
