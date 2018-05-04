<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listPurchase.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:choose>
					<c:when test="${cencel != 'cencel'}">
						<tr>
							<td width="93%" class="ct_ttl01">구매 목록조회</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td width="93%" class="ct_ttl01">구매 취소내역</td>
						</tr>
					</c:otherwise>
				</c:choose>
				
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		전체  ${resultPage.totalCount} 건수,	현재 ${resultPage.currentPage} 페이지
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품번호</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">주문날짜</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<c:choose>
			<c:when test="${cencel != 'cencel'}">
				<td class="ct_list_b">정보수정</td>
			</c:when>
			<c:otherwise>
				<td class="ct_list_b">취소날짜</td>
			</c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:forEach var="purchase" items="${list}">
		<c:set var="i" value="${i+1}" />
			<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=${purchase.tranNo}">${i}</a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo}&menu=${menu}">${purchase.purchaseProd.prodNo}</a>
		</td>
		<td></td>
		<td align="left">${purchase.purchaseProd.prodName}</td>
		<td></td>
		<td align="left">${purchase.orderDate}</td>
		<td></td>
		<c:choose>
			<c:when test="${cencel == 'cencel'}">
				<td align="left">현재
				
					구매취소
				상태 입니다.</td>
			</c:when>
			<c:when test="${purchase.tranCode.trim()==3}">
			<td align="left">현재
				
					배송완료
				상태 입니다.</td>
			</c:when>
			<c:when test="${purchase.tranCode.trim()==2}">
			<td align="left">현재
				
					배송중 상태
				입니다.
			</td>	
			</c:when>
			<c:otherwise>
			<td align="left">현재
				
					구매완료 상태
				입니다.
			</td>
			</c:otherwise>
		</c:choose>
		<td></td>
		
		<c:choose>
			<c:when test="${cencel != 'cencel'}">
				<c:if test="${purchase.tranCode.trim()==2}">
					<td align="left">
					<a href="/updateTranCode.do?prodNo=${purchase.purchaseProd.prodNo}&tranCode=3&currentPage=${i}">물건도착</a>
					</td>
				</c:if>
			</c:when>
			<c:otherwise>
				<td align="left">${purchase.cencelDate}</td>
			</c:otherwise>
		</c:choose>
		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			 <input type="hidden" id="currentPage" name="currentPage" value=""/>
			 <input type="hidden" id="cencel" name="cencel" value="${cencel}"/>
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>