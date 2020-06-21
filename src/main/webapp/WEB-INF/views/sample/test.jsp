<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/CommonTaglibrary.tld" prefix="cmm"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="false" %>
<html>
<head>
	<title>Test</title>
	<link rel="stylesheet" href="${CONTEXT_PATH}/resources/css/common.css?ver=${nowDate}" media="screen, print">
	<script src="${CONTEXT_PATH}/resources/js/common.js"></script>
	<script src="${CONTEXT_PATH}/resources/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<img src="${CONTEXT_PATH}/resources/img/111.jpg">
<cmm:limitStr value="말줄임테스트12341234123" length="6"/>


<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
