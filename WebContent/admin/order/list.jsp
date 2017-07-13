<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
	//异步加载获取订单详情
	function showDetail(oid) {
		//获得按钮
		var currenBut=$("#but"+oid);
		//获得div
		var currenDiv=$("#div"+oid);
		//显示隐藏div
		if(currenBut.attr("value")=="订单详情"){
			//JQuery的方式校验
			$.ajax({
						url : "${pageContext.request.contextPath}/adminOrder_findOrderItem.action?time="
								+ new Date().getTime() + "&oid=" + oid,
						async : true,
						type : 'GET',
						dataType : "html",
						success : function(e) { //成功后回调
							currenDiv.html(e);
						},
						error : function(a, b, c) { //失败后回调
							alert("失败:" + a.status + ";" + b.statusText + ";" + c);
						}
					}); 
			currenBut.attr({"value":"关闭"});
		}else {
			currenBut.attr({"value":"订单详情"});
			currenDiv.html("");
		}
		
	}
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>订单列表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="7%">序号</td>
								<td align="center" width="10%">订单编号</td>
								<td align="center" width="10%">总金额</td>
								<td align="center" width="10%">收货人</td>
								<td align="center" width="10%">订单状态</td>
								<td width="*" align="center">订单详情</td>
							</tr>
							<s:iterator var="order" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = '#facd89'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="#status.count" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.oid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.total" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.name" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:if test="#order.state==1">
										未付款
										</s:if> <s:if test="#order.state==4">
										交易完成
										</s:if> <s:if test="#order.state==2">
											<a href="${pageContext.request.contextPath}/adminOrder_updateState.action?oid=<s:property value="#order.oid"/>"><font color="red"> 发货</font></a>
										</s:if> <s:if test="#order.state==3">
										未确认收货
										</s:if></td>
									<td align="center" style="HEIGHT: 22px"><input
										type="button" value="订单详情"
										id="but<s:property value='#order.oid'/>" onclick="showDetail(<s:property value='#order.oid'/>)" />
										<div id="div<s:property value="#order.oid"/>"></div></td>

								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#afd1f3">第<s:property
							value="pageBean.page" />/<s:property value="pageBean.totalPage" />页
						&nbsp;&nbsp;&nbsp; <s:if test="pageBean.page !=1">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=1">首页</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if> <s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a
									href="${pageContext.request.contextPath }/adminOrder_findAll.action?page=<s:property value="#i"/>"><font
									color="red"><s:property value="#i" /></font></a>
							</s:if>
							<s:else>
								<span><s:property value="#i" /></span>
							</s:else>
						</s:iterator> <s:if test="pageBean.page != pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

