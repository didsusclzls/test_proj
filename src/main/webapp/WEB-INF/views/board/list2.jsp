<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<!-- 버튼을 위해서 작성 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 추가 -->
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
</style>



</head>
<body>

	<header class="pt-5">

		<jsp:include page="../include2/topLayout.jsp" flush="false" />

	</header>
	<%--  <jsp:include page = "slider_from_YT.jsp" flush = "false"/> --%>
	<main>

		<div class="container" style="border: 1px solid black;">


			<div class="row" style="border: 1px solid black;">

				<div class="container" style="border: 1px solid black;">
					<div class="row" style="border: 1px solid black;">

						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
						<button type="button" class="btn_margin btn btn-outline-success">Success</button>
					</div>
					<!-- 버튼 btn row -->
				</div>
				<!-- 버튼 btn container -->

				<div class="container" style="margin-top:30px;">
				<c:forEach var="list2" items="${list2 }">
				
					<div class="row">

						
						<div class="col-md-4" style="border: 1px solid black;">

							<img id="img_st" src="http://placehold.it/200x200" alt="">
							
							
							<table>
								<tr style="border: 1px solid black;">
									<td id="basic_sty">좋아요</td>
									<td id="basic_sty">싫어요</td>
								</tr>
							</table>

						</div>
						<!-- col end -->
						<!-- 2번째 테이블 -->
						<div class="col-md-4" style="border: 1px solid black;">
						${list2.sub_title } 
						</div>
						<!-- 3번째 테이블 -->
						<div class="col-md-4" style="border: 1px solid black;">
						${list2.content }
						</div>
					</div>
					<!-- row end -->
					</c:forEach>
				
				
				<!-- 페이지 번호 출력 -->
				<div class='float-right'>
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class=" btn btn-default paginate_button previous"><a
								href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>
						<c:forEach var="num" begin="${pageMaker.startPage }"
							end="${pageMaker.endPage}">
							<li class=" btn btn-default paginate_button ${pageMaker.cri.pageNum==num?"active":""}">
								<a href="${num}">${num }</a>
							</li>
						</c:forEach>
						<c:if test="${pageMaker.next}">
							<li class="btn btn-default paginate_button next"><a
								href="${pageMaker.endPage+1}">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!-- Pagination 끝 -->
	
					
								<!-- 페이지 번호 이벤트 처리 -->
				<form id='actionForm' action="/board/list2" method='GET'>
				               <input type="hidden" name="pageNum"
                  value="${pageMaker.cri.pageNum }"> 
                  <input type="hidden"
                  name="amount" value="${pageMaker.cri.amount }">
                     <input type="hidden"
                  name="type" value="${pageMaker.cri.type }">
                     <input type="hidden"
                  name="keyword" value="${pageMaker.cri.keyword }">


				</form>					
					
					
					
				</div>
				<!-- container end-->


			</div>
			<!-- ALL row  end-->
		</div>
		<!-- ALL contatiner end -->



	</main>


	<footer>
		<jsp:include page="../include2/bottomLayout.jsp" flush="false" />
	</footer>
	
	
<script type="text/javascript">
$(document).ready(function(){
	var result='<c:out value="${result}"/>';
checkModal(result);
history.replaceState({},null,null);


function checkModal(result){
	if(result === ''|| history.state){
		return;
	}
	if(parseInt(result)>0){
		$(".modal-body").html(
				"게시글"+ parseInt(result)+"번이 등록되었습니다.");
	}
	$("#myModal").modal("show");
}
});
<!-- 글쓰기 버튼 클릭시 register.jsp로 이동 -->

$("#regBtn").on("click",function(){
	self.location ="/board/register";
}); 
	
	//페이지번호 처리
	var actionForm = $("#actionForm");
	
	$(".paginate_button a").on("click",function(e){
		e.preventDefault();
		console.log('click');
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	}); 
	
	//get으로 넘기기
	   $(".move").on("click",function(e) {
           e.preventDefault();
           actionForm.append("<input type='hidden' name='bno' value='"
                    + $(this).attr("href")
                    + "'>");
           actionForm.attr("action","/board/get");
           actionForm.submit();

        });
	
/* 검색 기능 */
var searchForm = $("#searchForm");
$("#searchForm button").on("click", function(e){
	if(!searchForm.find("option:selected").val()){
		alert("검색종류를 선택하세요");
		return false;
	}
	if(!searchForm.find("input[name='keyword']").val()){
		alert("키워드를 입력하세요");
		return false;
	}
	searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();
		searchForm.submit();
	
	});

 
	   
</script>
	
	
</body>
</html>