<%-- 
    Document   : UserRegistration
    Created on : Mar 13, 2013, 2:58:37 PM
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
        <title>Computer Shop - User Registration</title>
        <link href="style/style.css" rel="stylesheet" type="text/css">
        <script src="scripts/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="main">
            <div id="header">
                <jsp:include page="template/header.jsp" flush="true" />
            </div>
            <div id="header2">
                <jsp:include page="template/header2.jsp" flush="true" />
            </div>
            <%--
            <div id="indexLeftColumn">
                <jsp:include page="template/leftColumn.jsp" flush="true" />
            </div>
            --%>
            <div id="indexRightColumn">
                <h1 style="text-align: center; width: 900px; color: red;">You Need to sign in to checkout </h1>
                <form action="UserManagement?action=createUser" method="POST">

                    <h3>Already an user? <a href="UserLogIn.jsp">Sign In</a></h3>
                    <h3 style="color: green;">New User Register</h3>
                    <p style="font-size: 25px; color: brown; text-align: left;"><b>[[ Personal Information  ]]</b></p>
                    <div style="float: right; color: red; font-size: 25px;">${errorMsg}</div>
                    <table>
                        <tr>
                            <td class="fTitle">User Name</td>
                            <td class="regForm"><input  type="text" name="field1" ></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Password</td>
                            <td><input type="password" name="field2" ></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">E-Mail</td>
                            <td><input type="text" name="field3" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Full Name</td>
                            <td><input type="text" name="field4" value=""></td> 
                            <td></td>
                        </tr>
                    </table>
                    <p style="font-size: 25px; color: green; text-align: left;"><b>[[ Address ]]</b></p>
                    <table>
                        <tr>
                            <td class="fTitle">House</td>
                            <td><input type="text" name="field5" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Street</td>
                            <td><input type="text" name="field6" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">City</td>
                            <td><input type="text" name="field7" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Country </td>
                            <td><input type="text" name="field8" value=""></td> 
                            <td></td>
                        </tr>
                    </table>
                    <p style="font-size: 25px; color: brown; text-align: left;"><b>[[ Payment Information  ]]</b></p>
                    <table>
                        <tr>
                            <td class="fTitle">Credit Type</td>
                            <td><input type="text" name="field9" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Credit Card No</td>
                            <td><input type="text" name="field10" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Security Code</td>
                            <td><input type="text" name="field11" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Name on Card </td>
                            <td><input type="text" name="field12" value=""></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle">Expire Date </td>
                            <td><input type="text" name="field13" value="2013-12"></td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td class="fTitle"></td>
                            <td style=""><input type="submit"  value="Create Account"></td> 
                            <td></td>
                        </tr>
                    </table>

                </form>
            </div>
            <div id="footer">
                <jsp:include page="template/footer.jsp" flush="true" />
            </div>
        </div>
    </body>
</html>
