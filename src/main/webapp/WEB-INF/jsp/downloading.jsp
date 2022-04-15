<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<%--<html xmlns:th="http://www.thymeleaf.org">--%>
<head>
    <meta charset="UTF-8">
    <title>Downloading</title>
    <link href="/css/bootstrap.css" type="text/css" rel="stylesheet" />
    <script src="/js/jquery-3.1.0.min.js" ></script>
    <script src="/js/bootstrap.min.js" ></script>
</head>
<body>
<div class="container">
    <h1>All files</h1>
    <table class="table table-striped table-fixed">
        <thead>
        <th>Links</th>
        <th class="text-center">Names</th>
        </thead>
        <tbody>

        <%-- Code for using thymeleaf instead of jsp responsible for file download and delete function --%>

<%--        <tr th:each="path : ${pathList}">--%>
<%--            <td type="button" class="btn btn-success"><a th:href="@{/download(file_token=${path.get('file_token')--%>
<%--                                                                                             })--%>
<%--                                                                     }" class="text-center">Download</a></td>--%>
<%--            <td th:text="${path.get('path')}" class="text-center"></td>--%>
<%--            <td type="button" class="btn btn-danger"><a th:href="@{/delete(id=${path.get('file_id')--%>
<%--                                                                                },--%>
<%--                                                                           file_token=${path.get('file_token')--%>
<%--                                                                                        })--%>
<%--                                                                  }"  style="color:#008000" class="text-center">Delete</a></td>--%>
<%--        </tr>--%>

<c:forEach items="${pathList}" var="path">
    <tr>
        <td type="button" class="btn btn-success"><a href = "/download?file_token=${path.file_token}" class="text-center">Download</a></td>
        <td class="text-center">${path.path}</td>
        <td type="button" class="btn btn-danger"><a href="/delete?id=${path.file_id}&file_token=${path.file_token}" style="color:#008000" class="text-center">Delete</a></td>
    </tr>
</c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>