<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="com.sctrcd.payments.validation.iban.IbanValidationResult"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBAN validation</title>
<meta http-equiv="contentType" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/iban.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/vendor/jquery-min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/iban.js"></script>
</head>
<body>

<div id="ibanValidator">
	<form action="<%=request.getContextPath()%>/validate/iban" method="get">
		<div>IBAN Validator</div>
	    <input type="text" name="ibanRequest" id="ibanRequest" /> 
	    <input type="submit" id="validateIbanBtn" value="Validate">
	    <div id="ibanValidationResults">&nbsp;</div>
	</form>
</div>

</body>
</html>