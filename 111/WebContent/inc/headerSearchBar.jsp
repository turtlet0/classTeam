<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 검색창 css  -->
	<div class="header">
		<!-- 검색조건 -->
		<!-- 아직 미완성  -->
		<form name="form1" id="form1"
			action="./classlist.do?pageNum=${pageNum }">
			<input onkeyup="filter()" type="text" id="value" name="keyWord" class="collapsible" placeholder="클래스 검색" value="${keyWord }">
			<div class="content">
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
	
				<div>
					카테고리 : <select id="category" onchange="categoryChange(this)" name="category">
						<option value="all" selected="selected">전체</option>
						<option value="핸드메이드">핸드메이드</option>
						<option value="쿠킹">쿠킹</option>
						<option value="플라워·가드닝">플라워·가드닝</option>
						<option value="드로잉">드로잉</option>
						<option value="음악">음악</option>
						<option value="요가·필라테스">요가·필라테스</option>
						<option value="레저·스포츠">레저·스포츠</option>
						<option value="뷰티">뷰티</option>
						<option value="반려동물">반려동물</option>
						<option value="체험">체험</option>
						<option value="자기계발">자기계발</option>
					</select> <select id="subcategory" name="subcategory">
						<option value="all" selected="selected">전체</option>
					</select>
				</div>
				<input type="submit" value="검색">
			</div>
		</form>
		<!-- 검색조건 -->
	</div>

	<!-- 검색창 javascript -->
	<script type="text/javascript">
      /* 검색창 누르면 검색조건창이 열림 */
		var coll = document.getElementsByClassName("collapsible");
		var i;
		for (i = 0; i < coll.length; i++) {
		  coll[i].addEventListener("click", function() {
		    this.classList.toggle("active");
		    var content = this.nextElementSibling;
		    if (content.style.display === "block") {
		      content.style.display = "none";
		    } else {
		      content.style.display = "block";
		    }
		  });
		}
      /* 검색창 누르면 검색조건창이 열림 */
     
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
	</script>

