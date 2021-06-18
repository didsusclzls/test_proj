<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- 버튼을 위해서 작성 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<style>
/* div{
border:1px solid black;
} */
.btn_margin {
	left: 50px;
	position: relative;
	margin-left: 20px;
}

#img_st {
	margin-top: 50px;
	margin-bottom: 0px;
}

#basic_sty {
	border: 1px solid black;
	width: 100px;
}

#box {
	border: 1px solid black;
}

.search {
	left: 200px;
}

.head {
	margin-top: 25px;
}

.table_title {
	width: 100px;
}

#center {
	text-align: center;
}
</style>


<header class="pt-5">

	<%-- <jsp:include page="../include/header.jsp"></jsp:include>
  --%>

	<jsp:include page="../include2/topLayout.jsp"></jsp:include>
</header>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var result = '<c:out value="${result}"/>';
						checkModal(result);
						history.replaceState({}, null, null)
						function checkModal(result) {
							if (result === "" || history.state) {
								return;
							}
							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글" + parseInt(result) + " 번이 등록됨")
							}
							$("#myModal").modal("show")

						}

						$("#regBtn").on("click", function() {
							self.location = "/board/register"
						})

						//내꺼 오타천국 
						var actionForm = $("#actionForm");
						$(".paginate_button a").on(
								"click",
								function(e) {
									e.preventDefault()
									console.log('click')
									actionForm.find('input[name="pageNum"]')
											.val($(this).attr("href"))
									actionForm.submit()
								})

						//지아꺼
						/*    var actionForm = $("#actionForm");
						   $(".paginate_button a").on("click",function(e){
						      e.preventDefault();
						      console.log("click");
						      actionForm.find("input[name='pageNum']").val($(this).attr("href"));
						      //actionForm.submit();
						   }); */

						$(".move")
								.on(
										"click",
										function(e) {
											e.preventDefault()
											actionForm
													.append("<input type='hidden' name='bno' value='"
															+ $(this).attr(
																	"href")
															+ "'/>")
											actionForm.attr("action",
													"/board/get")
											actionForm.submit()
										})

						var searchForm = $("#searchForm")
						$("#searchForm button").on(
								"click",
								function(e) {
									if (!searchForm.find("option:selected")
											.val()) {
										alert("검색종류를 선택하세요")
										return false;
									}
									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요")
										return false;
									}
									searchForm.find("input[name='pageNum']")
											.val("1")
									e.preventDefault();
									searchForm.submit();
								})

					})
</script>

<!-- 		------------------------------------------------------------------------------- -->
<div class="container">
	<div class="row">
		<div class="col-lg-12 ">
			<h1 class="page-header">게시판</h1>
		</div>
		<!-- col 12 -->
	</div>
	<!-- row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="row justify-content-between">
				<div class="col-4 head">
					Total
					<c:out value='${pageMaker.total }' />
					건
				</div>


				<div class="col-6 search">
					<form id='searchForm' action='/board/list' method='get'>
						<select name='type'>
							<option value=""
								<c:out value="${pageMaker.cri.type==null?'selected': ''}"/>>----</option>
							<option value="T"
								<c:out value="${pageMaker.cri.type eq'T'?'selected':''}"/>>제목</option>
							<option value="C"
								<c:out value="${pageMaker.cri.type eq'C'?'selected': ''}"/>>내용</option>
							<option value="W"
								<c:out value="${pageMaker.cri.type eq'W'?'selected': ''}"/>>작성자</option>

						</select> <input type='text' name='keyword'
							value='<c:out value="${pageMaker.cri.keyword }"/>' /> <input
							type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }' />
						<input type='hidden' name='amount'
							value='${pageMaker.cri.amount }' />
						<button class='btn btn-info'>Search</button>
					</form>


				</div>
			</div>
		</div>
	</div>
	<!-- row -->











	<div class="row">
		<div class="col-lg-12">

			<table class="table">
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th colspan="4" id="center">제목</th>
						<th scope="col">파일</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${list }">
						<tr>
							<td>${board.bno }</td>
							<td colspan="4" id="center"><a class='move'
								href='<c:out value="${board.bno }"/>'>${board.title } <b>[<c:out
											value="${board.replyCnt }" /> ]
								</b>
							</a></td>
							<td></td>
							<td>${board.writer }</td>
							<td>${board.regdate }</td>
						</tr>
					</c:forEach>



				</tbody>

			</table>
		</div>
		<!-- col 12 -->
	</div>
	<!-- row -->


<div class='pull-right'>
	<ul class='pagination'>
		<c:if test="${pageMaker.prev }">
			<li class='paginate_button previous'><a
				href="${pageMaker.startPage-1 }">Previous</a></li>
		</c:if>
		<c:forEach var='num' begin='${pageMaker.startPage }'
			end='${pageMaker.endPage }'>
			<li class="paginate_button ${pageMaker.cri.pageNum==num?"active":""}">
				<a href="${num }">${num }</a>
			</li>
		</c:forEach>
		<c:if test="${pageMaker.next }">
			<li class='paginate_button next'><a
				href="${pageMaker.endPage+1 }">Next</a></li>
		</c:if>
	</ul>
</div>
<form id='actionForm' action='/board/list' method='get'>
	<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum }'>
	<input type='hidden' name='amount' value='${pageMaker.cri.amount }'>
	<input type='hidden' name='type' value="${pageMaker.cri.type }">
	<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">

</form>
<div class='modal fade' id='myModal' tabindex='-1' role='dialog'
	aria-labelledby='myModallabel' aria-hidden='true'>
	<div class='modal-dialog'>
		<div class='modal-content'>
			<div class='modal-header'>
				<button type='button' class='close' data-dismiss='modal'
					aria-hidden='true'>&times;</button>
				<h4 class='modal-title' id='myModalLable'>Modal title</h4>
			</div>
			<div class='modal-body'>처리가 완료되었습니다.</div>
			<div class='modal-footer'>
				<button type='button' class='btn btn-info' data-dismiss='modal'>Close</button>
				<button type='button' class='btn btn-primary'>Save Changes</button>
			</div>
		</div>
	</div>
</div>
<!-- /.table-responsive -->




</div>
<!-- container -->
<!-- 	footer 시작		-------------------------------------------------------------- -->
<%-- <jsp:include page="../include/footer.jsp"></jsp:include>
 --%>
<jsp:include page="../include2/bottomLayout.jsp"></jsp:include>
