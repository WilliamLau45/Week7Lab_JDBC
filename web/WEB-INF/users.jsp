<%-- 
    Document   : users
    Created on : Oct 26, 2022, 7:56:04 PM
    Author     : ohiow
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        table, th, td {border:1px solid black;}
    </style>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    
    <body>

        <h1>Manage Users</h1>

        <c:choose>
            <c:when test="${users eq null}">
                ${message}
            </c:when>
            <c:when test="${users ne null}">
                <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th> </th>
                        <th> </th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role.roleTitle}</td>
                            <td><a href="<c:url value="/" >
                                       <c:param name="action" value="edit"/>
                                       <c:param name="userEmail" value="${user.email}"/>
                                   </c:url>">Edit</a></td>
                            <td><a href="<c:url value="/" >
                                       <c:param name="action" value="delete"/>
                                       <c:param name="userEmail" value="${user.email}"/>
                                   </c:url>">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${page eq 'edit'}">
                <h2>Edit User</h2>

                <form action="" method="post">
                    Email: ${updateUser.email}<br>
                    <input type="hidden" value="${updateUser.email}" name="updateUserEmail">
                    First Name:<input type="text" value="${updateUser.firstName}" name="editFirstName"><br>
                    Last Name:<input type="text" value="${updateUser.lastName}" name="editLastName"><br>
                    Password:<input type="password" value="${updateUser.password}" name="editPassword"><br>
                    Role:<select name="editRole">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                    </select>
                    <br>
                    <input type="hidden" value="update" name="action">
                    <input type="submit" value="Update" name="action">
                    <button><a href="User">Cancel</a></button>
                </form>
            </c:when>
                    
            <c:otherwise>
                <h2>Add User</h2>
                <form action="" method="post">
                    Email:<input type="text" value="" name="email"><br>
                    First Name:<input type="text" value="" name="firstName"><br>
                    Last Name:<input type="text" value="" name="lastName"><br>
                    Password:<input type="text" value="" name="password"><br>
                    Role:<select name="role">
                        <option value="system admin">system admin</option>
                        <option value="regular user">regular user</option>
                    </select>
                    <br>
                    <input type="hidden" value="add" name="action">
                    <input type="submit" value="Add User">
                </form>
            </c:otherwise>
        </c:choose>

        <c:if test="${task eq 'error'}">Error! Please try again. </c:if>
        ${message}      
    </body>
</html>
