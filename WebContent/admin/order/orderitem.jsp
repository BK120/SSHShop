<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<table border="1" width="100%">
	<tr>
		<td align="center">图片</td>
		<td align="center">数量</td>
		<td align="center">小计</td>
	</tr>
	<s:iterator value="list" var="orderItem">
	<tr>
		<td width="50%" align="center"><img width="100%" height="40" src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image" />"/></td>
		<td width="25%" align="center"><s:property value="#orderItem.count" /></td>
		<td width="*" align="center"><s:property value="#orderItem.subtotal" /></td>
	</tr>
	</s:iterator>

</table>