<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %> 
<!-- JSP include -->
<%-- <%@ include file="../includes/header.jsp" %> --%>
<!-- JSP 액션태그 include -->
<jsp:include page="../includes/header.jsp"></jsp:include>

			<div class="col-md-12">
				<h4 class="m-b-lg">Member Register</h4>
			</div><!-- END column -->


			<div class="col-md-12">
				<div class="widget p-lg">
					<h4 class="m-b-lg">Member Register</h4>
					<p class="m-b-lg docs">
						<!-- Add <code>.table-hover</code> to enable a hover state on table rows within a <code>&lt;tbody&gt;</code>. -->
					</p>

					<div class="panel-body">
						<form method="post" action="">
							<div class="form-group">
								<label>회원명</label><input type="text" name="uname" id="uname" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>학교명</label><input type="text" name="schoolname" id="schoolname" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>학년/반</label><input type="text" name="gradeclass" id="gradeclass" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>아이디</label><input type="text" name="uid" id="uid" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>비밀번호</label><input type="text" name="upw" id="upw" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>노선</label><input type="text" name="route" id="route" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<label>탑승장소</label><input type="text" name="boardingplace" id="boardingplace" class="form-control" value="" required="required">
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-default">회원가입</button>
								<button type="button" class="btn btn-default">취소</button>
							</div>
						</form>
					</div>
				</div><!-- .widget -->
			</div><!-- END column -->

<!-- JSP 액션태그 include -->
<jsp:include page="../includes/footer.jsp"></jsp:include>
<!-- JSP include -->
<%-- <%@ include file="../includes/footer.jsp" %> --%>



