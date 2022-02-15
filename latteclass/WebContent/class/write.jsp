<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 헤더를 추가하면 작동안됨 -->
<script type="text/javascript">
/* 서브 카테고리 선택 */
function categoryChange(e) {
  var subcategory_handmade = ["전체", "캔들·디퓨저", "향수", "비누·배쓰밤", "위빙·소잉", "라탄·마크라메", "액세서리", "가죽", "도자기", "목공", "레진", "디자인, 굿즈", "업사이클링", "기타 공예"];
  var subcategory_cooking = ["전체", "베이킹", "요리", "떡·앙금", "디저트·음료", "커피·바리스타", "기타 쿠킹"];
  var subcategory_drawing = ["전체", "소묘", "펜화", "캘리그라피", "수채화", "동양화", "서양화", "민화", "일러스트", "유화", "아크릴", "디지털 드로잉", "기타 드로잉"];
  var subcategory_music = ["전체", "피아노", "기타·우쿠렐라", "보컬", "작사·작곡", "프로듀싱", "기타 악기"];
  var subcategory_yoga_pilates = ["전체", "요가", "필라테스"];
  var subcategory_leisure_sport = ["전체", "피트니스", "실내 운동", "야외 운동", "댄스", "레저", "기타 스포츠"];
  var subcategory_beauty = ["전체", "메이크업", "헤어", "네일아트", "타투", "셀프케어"];
  var subcategory_pet = ["전체", "펫 푸드", "펫 에티켓", "펫 액세사리", "펫 미용", "기타 펫 클래스"];
  var target = document.getElementById("subcategory");
 
  if(e.value === "핸드메이드") var d = subcategory_handmade;
  else if(e.value === "쿠킹") var d = subcategory_cooking;
  else if(e.value === "드로잉") var d = subcategory_drawing;
  else if(e.value === "음악") var d = subcategory_music;
  else if(e.value === "요가·필라테스") var d = subcategory_yoga_pilates;
  else if(e.value === "레저·스포츠") var d = subcategory_leisure_sport;
  else if(e.value === "뷰티") var d = subcategory_beauty;
  else if(e.value === "반려동물") var d = subcategory_pet;
 
  target.options.length = 0;
 
  for (x in d) {
    var opt = document.createElement("option");
    opt.value = d[x];
    opt.innerHTML = d[x];
    target.appendChild(opt);
  } 
}

function pdsCheck(f) {
	var class_rep_img = f.class_rep_img.value;
	class_rep_img=class_rep_img.trim();
	if(class_rep_img.length==0){
		alert("첨부파일을 선택하세요");
		return false;
	}else{
		var dot=class_rep_img.lastIndexOf(".");
		
		var ext=class_rep_img.substr(dot+1);
		
		ext = ext.toLowerCase();
		
		alert(ext);
		if(ext=="png" || ext=="jpg"||ext=="gif"||ext=="jpeg"){
			return true;
		}else{
			alert(ext);
			alert("이미지 파일만 가능합니다.");
			return false;
		}
	}
	return true;
	
}


</script>


</head>
<body>
<!-- 헤더추가하면 write.jsp에 자바스크립트가 작동 안됨 -->
<%-- <%@include file="../inc/top.jsp" %> --%>

	<h2>강의 등록</h2>
	
	<fieldset>
		<legend>강의등록</legend>
		<form action="./classwriteProAction.do?pageNum=${pageNum}" method="post" name="fr" enctype="multipart/form-data" onsubmit="return pdsCheck(this)">
			<input type="hidden" name="class_cd" value="${class_cd}">
			
			강의명 : <input type="text" name="title"><br>
			강의이미지 : <input type="file" name="class_rep_img"><br>
			카테고리: <select onchange="categoryChange(this)" name="category">
			   			<option value="all" selected="selected">전체</option>
			   			<option value="핸드메이드" >핸드메이드</option>
			   			<option value="쿠킹" >쿠킹</option>
			   			<option value="플라워·가드닝" >플라워·가드닝</option>
			   			<option value="드로잉" >드로잉</option>
			   			<option value="음악" >음악</option>
			   			<option value="요가·필라테스" >요가·필라테스</option>
			   			<option value="레저·스포츠" >레저·스포츠</option>
			   			<option value="뷰티" >뷰티</option>
			   			<option value="반려동물" >반려동물</option>
			   			<option value="체험" >체험</option>
			   			<option value="자기계발" >자기계발</option>
			 		  </select>
			   		  <select id="subcategory" name="subcategory">
			    		<option value="all" selected="selected">전체</option>
			 		  </select>
			  <br><br>
			지역 : <select id="sido" name="sido">
			    	<option value="all" selected="selected">전체</option>
			    	<option value="서울">서울</option>
			    	<option value="부산">부산</option>
			    	<option value="인천">인천</option>
			    	<option value="대구">대구</option>
			    	<option value="울산">울산</option>
			    	<option value="광주">광주</option>
			    	<option value="대전">대전</option>
			    	<option value="세종">세종</option>
			    	<option value="경기도">경기도</option>
			    	<option value="경상남도">경상남도</option>
			    	<option value="경상북도">경상북도</option>
			    	<option value="전라남도">전라남도</option>
			    	<option value="전라북도">전라북도</option>
			    	<option value="충청남도">충청남도</option>
			    	<option value="충청북도">충청북도</option>
			    	<option value="강원도">강원도</option>
			    	<option value="제주도">제주도</option>
			    	
			  	   </select>
			  		<br><br>
			가격 : <input type="text" name="price"><br>	
					<b style="color: red;">문의시 문의라고 입력하세요</b>
			<input type="submit" value="강의등록" >
			<input type="reset" value="초기화">
			<input type="button" value="목록" onclick="window.location='./classlist.do'">
			
		</form>
	</fieldset>

</body>
</html>