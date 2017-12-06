<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ocr.*" %>
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title> 야! 너도봐</title>
		<STYLE>
			#dvt{margin-top:70px; text-align:center; margin-bottom: 5px; width:100%; height:70px;padding:13px;font-size:70px;}
			#dvc{width:100%; text-align:center;font-size:30px;font-style: italic;}
			#dvci{width:100%; height:20px; text-align:center;font-size:30px;font-style: italic;}
			#dvb{margin-top:20px; margin-bottom: 5px; width:100%; height:20px; text-align:center;padding:13px;font-size:70px;font-style: boid; font-size: 20px; font-style: italic; background-color: #EAEAEA}
		</STYLE>		
		<script>
		
		</script>
	</head>
	<body>
	
		<script type="text/javascript">
		function check() {
			if(f.search_con.value==""){
				alert("검색할 내용을 입력하세요");
				f.search_con.focus();
				return false;
			}
			if(f.search_con.value==" "){
				alert("공백으로 시작할 수 없습니다");
				f.search_con.focus();
				return false;
			}
			return true;
		}
		
		</script>
		<div id = "dvt">
				<img src="web_resources/title_ver_3.png"/>
		</div>						
				
		<div id = "dvci"> 
			<form name="choices" action="main.page" method="post" onsubmit="return check()">
							<!--  <input type="text" name="search_con" value="" size="15%">		-->
			<!--  카테고리				
			  <select id="day" name="day">
				<option value="" selected>-- 요일 --</option>
				<option value="sunday">Sun.</option>
				<option value="monday">Mon.</option>
				<option value="tuesday">Tue.</option>		
				<option value="wednesday">Wed.</option>
				<option value="thursday">Thu.</option>
				<option value="friday">Fri.</option>			
				<option value="saturday">Sat.</option>																								
			</select>		 
			<select id="cat" name="cat">
				<option value="" selected>-- 웹툰 --</option>
				<option class="tuesday" value="25455">노블레스</option>
				<option class="monday" value="183559">신의탑</option>
				<option class="thursday" value="675331">마술사</option>	
				<option class="sunday" value="557676">다이스</option>																							
			</select> 
			
			<script src="//code.jquery.com/jquery.min.js"></script>
			<script src='//rawgit.com/tuupola/jquery_chained/master/jquery.chained.min.js'></script>
			<script>
				$("#cat").chained("#day");
			</script>
			<input type="text" name="toon_number" size="3" >	-->
			
			<input type="text" name="text" value="주소를 입력해주세요." onfocus="this.value=''">
			<input type="submit" name="push_button" value="찾기">			
			
			</form>			
		</div>
		
		<div id = "dvc">				
				
			
		</div>
				
		
		<div id = "dvb"> Created by ViewToonPool </div>
	</body>
</html>
