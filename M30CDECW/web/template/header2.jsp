<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="headerWidget" id="viewCart">
    <a href="ShoppingCartController">
        <img src="img/cart.gif" alt="shopping cart icon" id="cart">
        <span class="horizontalMargin">
           
            <c:choose>
            <c:when test="${cartItemNo != null}">
                ${cartItemNo}
            </c:when>
                <c:otherwise>
                    0
                </c:otherwise>
            </c:choose>
            items
            
        </span>
    </a>
    <div class="connUser">

        <c:choose>
            <c:when test="${activeUser.userName != null}">
                
                Welcome<a href="UserManagement?action=myPage" style="text-decoration: none !important;"> <span style='color: green;'>( ${activeUser.userName} )</span></a>
                <a href='UserManagement?action=logOut' > Log Out</a>
            </c:when>
            <c:otherwise>
                Guest( <a href='UserManagement?action=signIn' > sign in</a> / <a href='UserManagement?action=signUp' >sign up</a>)

            </c:otherwise>
        </c:choose>
                <c:choose>
            <c:when test="${activeUser.userType == 'admin'}">
                <a href="AdminAction?action=goToAdminPanel" >   /   Admin panel</a>
            </c:when>
        </c:choose>


    </div>
</div>

