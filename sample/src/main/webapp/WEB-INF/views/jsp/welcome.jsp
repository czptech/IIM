<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Syllabus Tracker</title>
</head>
<body>
	<!-- At runtime, the included file will be ‘executed’ and the result content will be included with the soure JSP page. -->
	<jsp:include page="header.jsp" />
	<!-- At JSP page translation time, the content of the file given in the include directive is ‘pasted’ as it is -->
	<%-- <%@ include file=”header.jsp” %> --%>
	

	<table>
		<tr>
			<td><jsp:include page="menu.jsp" /></td>
			<%-- <td><%@ include file=”menu.jsp” %></td> --%>
		</tr>
		<tr>
			<td><jsp:include page="body.jsp" /></td>
			<%-- <td><%@ include file=”body.jsp” %></td> --%>
		</tr>
	</table>
	<jsp:include page="footer.jsp" />
	<%-- <%@ include file=”footer.jsp” %> --%>
</body>
</html>