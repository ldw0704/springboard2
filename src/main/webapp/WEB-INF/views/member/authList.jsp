<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 
<!-- JSP include -->
<%-- <%@ include file="../includes/header.jsp" %> --%>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/header.jsp"></jsp:include>

			<div class="col-md-12">
				<h4 class="m-b-lg">Member Authority Page</h4>
			</div><!-- END column -->


			<div class="col-md-12">
				<div class="widget p-lg">
					<h4 style="display:inline;" class="m-b-lg">Member Authority Page</h4>
					<p class="m-b-lg docs"></p>

					<table class="table table-hover">
						<thead>
							<tr><th>#번호</th><th>회원아이디</th><th>권한</th></tr>
						</thead>
						
						<c:forEach items="${list}" var="author" varStatus="num">
						<tr>
							<td>${num.count }</td>
							<td>${author.uid }</td>
							<td>${author.authority }</td>
						</tr>						
						</c:forEach>
						
					</table>
					
					
					<div class="container-fluid">
						<div class="row">
						  <div class="col-xs-7 pagination">
							  <form method="post" action="getMemberAuths">
							  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
							  <input type="hidden" name="uid" value="${uid }"/>
								<select name="authority" class="form-control" style="float:left; width:40%; height:32px;">
									<option value="">권한을 추가하세요</option>
									<option value="ROLE_ADMIN" >관리자</option>
									<option value="ROLE_MEMBER" >운영자</option>
									<option value="ROLE_USER" >사용자</option>
								</select>
								
								<button class="btn btn-default btn-sm" style="float:left;">권한추가</button>
							  </form>
						  </div>
						  <div class="col-xs-5" style="text-align:right;">
							  <ul class="pagination">
							  	<c:if test="${pageMaker.prev}">
							    <li class="paginate_button">
							      <a href="?pageNum=${pageMaker.startPage - 1}" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    </c:if>
							    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
							    <li class="paginate_button">
							    	<a href="?pageNum=${num}">${num}</a>
							    </li>
							    </c:forEach>
							    <c:if test="${pageMaker.next}">
							    <li class="paginate_button">
							      <a href="?pageNum=${pageMaker.endPage + 1}" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							    </c:if>
							  </ul>
						  </div>
						</div>
					</div>
					
				</div><!-- .widget -->
			</div><!-- END column -->

<!-- JSP 액션태그 include -->
<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- JSP include -->
<%-- <%@ include file="../includes/footer.jsp" %> --%>



