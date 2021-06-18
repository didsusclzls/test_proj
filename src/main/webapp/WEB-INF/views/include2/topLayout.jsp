
<%-- <%@page import="login.SessionCheck"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Welcome to Codevang</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	

	
	<style>
header {
	background-color: #FF4500;
}
</style>
	
	<style>
	
#myInput {
	  background-image: url('/css/searchicon.png'); /* Add a search icon to input */
	  background-position: 10px 12px; /* Position the search icon */
	  background-repeat: no-repeat; /* Do not repeat the icon image */
	  width: 100%; /* Full-width */
	  font-size: 16px; /* Increase font-size */
	  padding: 12px 20px 12px 40px; /* Add some padding */
	  border: 1px solid #ddd; /* Add a grey border */
	  margin-bottom: 12px; /* Add some space below the input */
	}

	#myTable {
	  border-collapse: collapse; /* Collapse borders */
	  width: 100%; /* Full-width */
	  border: 1px solid #ddd; /* Add a grey border */
	  font-size: 18px; /* Increase font-size */
	}

	#myTable th, #myTable td {
	  text-align: left; /* Left-align text */
	  padding: 12px; /* Add padding */
	}

	#myTable tr {
	  /* Add a bottom border to all table rows */
	  border-bottom: 1px solid #ddd;
	}

	#myTable tr.header, #myTable tr:hover {
	  /* Add a grey background color to the table header and on hover */
	  background-color: #f1f1f1;
	}

/* .navbar navbar-expand-lg navbar-dark bg-orangered fixed-top{

 background-color:blue;
} */
</style>
	
	
	
	
	
</head>

<!-- 네비바를 fiexd-top으로 설정했을 때 컨텐츠와 겹치는 문제 방지 -->

 <body> 
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-blue fixed-top">
		<div class="container">
			<a class="navbar-brand" href="../board2/main">Codevang's Page</a>
	<!-- 		<div class="collapse navbar-collapse" id="navbarResponsive"> -->
				
				<div class="col-md-4 col-md-offset-3">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="/etc">마이페이지
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/join/login.jsp">즐겨찾기</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/join/register.jsp">로그아웃</a></li>
				</ul>
			</div>
		<!-- 	</div> -->

		</div>
		<!-- container -->
			</nav>
			<!-- top navigation -->
			
	
	<%-- <div class="container"><div class="pull-right hidden-lg hidden-md hidden-sm">
    <a class="btn btn-default" data-toggle="collapse" data-target=".board-bottom-search-collapse"><i class='fa fa-search'></i></a>
</div>
<div class="pull-right collapse navbar-collapse board-bottom-search-collapse">
    <div class="form-group">
        <label class="sr-only" for="sfl">sfl</label>
        <select name=sfl class="form-control">
        <option value='wr_subject'>제목</option>
        <option value='wr_content'>내용</option>
        <option value='wr_subject||wr_content'>제목+내용</option>
        <option value='mb_id,1'>회원아이디</option>
        <option value='mb_id,0'>회원아이디(코)</option>
        <option value='wr_name,1'>이름</option>
        <option value='wr_name,0'>이름(코)</option>
        </select>
    </div>
    <div class="form-group">
        <label class="sr-only" for="stx">stx</label>
        <input name=stx maxlength=15 size=10 itemname="검색어" required value='<?=stripslashes($stx)?>' class="form-control">
    </div>
    <div class="form-group">
        <label class="sr-only" for="sop">sop</label>
        <select name=sop class="form-control">
            <option value=and>and</option>
            <option value=or>or</option>
        </select>
    </div>
    <div class="form-group">
        <button class="btn btn-primary">검색</button>
    </div>
</div></div>
	 --%>
	
		<div class="container">
		 <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
	
		 
<!-- 
<table id="myTable">
  <tr class="header">
    <th style="width:60%;">Name</th>
    <th style="width:40%;">Country</th>
  </tr>
  <tr>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td> </td>
    <td></td>
  </tr>
  <tr>
    <td> </td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td>
  </tr>
</table>
 -->
<script>
function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
</script> 
		
			 </div>
			<!-- searching -->
		
	

	<!-- Page Content -->
	<div class="container" style=" overflow: hidden">
		<div class="row">


			<div class="col-lg-9 my-4 mb-4">

				<!-- 여기서부터 본문내용, 이후 코드는 bottomLayout에서 마무리 -->
