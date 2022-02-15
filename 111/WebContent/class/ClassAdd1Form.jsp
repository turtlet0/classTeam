<%@page import="classes.db.ClassDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/js/classRepImgUpload.js"></script> <!-- 클래스 대표 이미지 js -->
<script src="${pageContext.request.contextPath}/js/tutorRepImgUpload.js"></script> <!-- 강사 대표 이미지 업로드 js -->
<!-- ${pageContext.request.contextPath} -->
<script type="text/javascript">
$(document).ready(function(){
	alert('ClassAdd1Form - jquery 정상 동작');
	// 객체, value 사용 시, null 및 undefined 여부 체크 먼저해야 에러 발생 안함
		
	// category 제어
	var cateogry = '${sessionScope.cdto.category}';
	if(cateogry != null && cateogry != ""){
		alert(category);
		$('input:radio[name=category]:radio[value='+category+']').attr('checked', true);
	}
		
	// subCategory 
	var subCategory = '${sessionScope.cdto.sub_category}';
	var subCategoryArr = subCategory.split(",");
	for(var i=0; i<subCategoryArr.length; i++){
		alert(subCategoryArr[i]);
	}
	if(subCategory != null && subCategory != ""){
		for(var i=0; i<subCategoryArr.length; i++){
			$('input:checkbox[name=sub_category]:checkbox[value='+subCategoryArr[i]+']').attr('checked', true);
		}
	}
	$('div[name=subCategory]').hide(); // hide() = display:none;
	var valueCheck = $('input:radio[name=category]:checked').val();
	if(valueCheck != undefined ){ // undefined 
   		$('div[id='+valueCheck+']').show(); // show() = display:block;
	}
	$('input:radio[name=category]').on('click',function() {
		var valueCheck = $('input:radio[name=category]:checked').val();
		//alert("category: " + valueCheck);
		$('div[name=subCategory]').hide();
		$('div[id='+valueCheck+']').show(); 
	}); 
		
	// classRepImg 제어
	$('#classRepImgDiv').hide();
	 //alert('classRepFdto: ${sessionScope.classRepFdto}');
	if('${sessionScope.classRepFdto}' != null && '${sessionScope.classRepFdto}' != ""){
		$('#classRepImgDiv').show();
	}
       
	// tutorRepImg 제어
	$('#tutorRepImgDiv').hide();
	//alert('tutorRepFdto: ${sessionScope.tutorRepFdto}');
	if('${sessionScope.tutorRepFdto}' != null && '${sessionScope.tutorRepFdto}' != ""){
		$('#tutorRepImgDiv').show();
	}
       
	/* 글자수 초과 여부 체크 */
	// 키보드 이벤트 처리 : <textarea> 또는 <input> 키보드 입력 시마다 이벤트 발생
	// keyup 추천 / keydown 과 keypress 구분하기가 어렵기 때문
      
	// title
	$('#title').keyup(function(){
		$('titleSpan').html("글자수 ("+$(this).val().length+" / 50)");
			// val() 이용 lenth 반환
		if($(this).val().length >= 50){
			$(this).val($(this).val().substring(0, 50));
			$('#titleSpan').html("(50 / 50)");
		}
	});

	// introduce
	$('#introduce').keyup(function(){
		$('#introduceSpan').html("글자수 ("+$(this).val().length+" / 150)");
			// val() 이용 length 반환 
		if($(this).val().length >= 150){
			$(this).val($(this).val().substring(0, 150));
			$('#introduceSpan').html("(150 / 150)");
		}
	});
	
	// tutor_introduce
	$('#tutor_introduce').keyup(function(){
		$('#tutor_introduceSpan').html("글자수 ("+$(this).val().length+" / 600)");
		if($(this).val().length >= 600){
			$(this).val($(this).val().substring(0, 600));
			$('#tutor_introduceSpan').html("(600 / 600)");
		}
	});
	
	/* 필수 항목 입력 여부 체크 */
	// 모두 입력 시에 submit 버튼(다음) 활성화
	
	//$('#nextSubmit').attr('disabled', true);
	$('#classAddForm').on('input', function (){ // form input 
		if($('#title').val() == ""){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
		if($('#introduce').val() == ""){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
		if($('input[name=category]:checked').val() == undefined){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
		if($('#classRep_img_file').val() == ""){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
		if($('#tutorRep_img_file').val() == ""){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
		if($('#tutor_introduce').val() == ""){
			$('#nextSubmit').attr('disabled', true);
		} else{
			$('#nextSubmit').attr('disabled', false);
		}
	});
});
</script>
</head>
<body>

<!-- 
클래스 등록 페이지 동작 방식
- 총 3단계로 구성
- 각 단계간 이동 (이전, 다음 버튼 클릭 시) 시 idx 파라메터를 url에 붙여 ClassAddAction.cl로 넘김
- ClassAddAction.java 페이지에서 Session 객체 생성 및 ClassDTO 객체 생성 후 정보 cdto에 저장 -> session 객체에 저장
- idx 파라메터 값에 따라 해당 페이지로 redirect 방식으로 페이지 이동
- 예정) 최종 등록 시 DB 저장되고 session의 dto 객체는 무효화(제거)
 -->

<%
	request.setCharacterEncoding("UTF-8");

	int member_cd = Integer.parseInt(String.valueOf(session.getAttribute("member_cd")));
%>
	<!-- WebContent/class/ClassAdd1Form.jsp -->
	<!-- 클래스 등록1 페이지 -->
	tdto ${sessionScope.tdto } <hr>
	cdto ${sessionScope.cdto } <hr>
	<p>클래스 등록</p>
	<hr>
	<div>
		<div>
			<span>1 기본 정보 입력</span>
			<span>--</span>
			<span>2 클래스 상세 소개</span>
			<span>--</span>
			<span>3 클래스 위치 등록</span>
		</div>
		<br>
		<div>
			<ul>
				<b>작성 유의 사항</b>
				<li>스텝 3까지 작성 후 최종 등록해 주셔야 내용이 소실 되지 않습니다</li>
				<li>등록 이후에도 자유롭게 수정 하실 수 있으니, 간단히 먼저 작성 후 등록 신청 하셔도 좋습니다</li>
				<li>일정 및 금액 설정은 등록 신청 이후에 '나의 클래스' 페이지에서 가능합니다</li>
				<li>필수 작성 사항들을 기입하신 후 다음 스텝으로 이동이 가능합니다</li>
			</ul>
		</div>
		<hr>
		<div>
			<div>
				<!-- Ajax 적용 예정 -->
				<!-- <form name="teachertForm" method="post">
					
				</form> -->
			</div>
			
			<form name="classAddForm" method="post">
				<!--임시) 강사 정보 -->
				<div>
					<div>
						<p><b>*</b>강사님 기본 정보 입력<span>(필수)</span></p>
					</div>
					<div>
						<p>계정 ID<span>(알림 메일이 발송됩니다)</span></p>
						<p>이메일(출력)</p>
					</div>
					<div>
						<p><b>*</b>강사명(닉네임)</p>
						<input name="user_name" value="닉네임(출력)" readonly="readonly">
					</div>
					<div>
						<p><b>*</b>전화 번호<span>(알림 메일이 발송됩니다)</span></p>
						<input name="phone_num" value="전화 번호(출력)" readonly="readonly">
					</div>
					<div>
						<p>상호명/브랜드명</p>
						<input name="company_name" placeholder="공방, 스튜디오, 강습소 이름" value="${sessionScope.tdto.company_name }">
					</div>
					<div>
						<p>SNS</p>
						<input name="instagram" placeholder="인스타" value="${sessionScope.tdto.instagram }"><br>
						<input name="blog" placeholder="블로그" value="${sessionScope.tdto.blog }"><br>
						<input name="youtube" placeholder="유튜브" value="${sessionScope.tdto.youtube }">
					</div>
				<!-- 	<div>
						<button type="button" >수정하기</button>
						<button type="button"  hidden="hidden">취소</button>
						<button type="button"  hidden="hidden">저장</button>
					</div> -->
				</div>
			
				<hr>
				<input type="text" name="currentIdx" value="1" hidden="hidden"><!-- Action으로 넘어오기 전 현재 페이지 Idx 확인 위해 전달 -->
				<div>
					<label><b>*</b>1) 클래스명<span>(필수)</span><span id="titleSpan">글자수 (0 / 50)</span></label><br>
					<input id="title" name="title" type="text" maxlength="50" value="${sessionScope.cdto.title }">
					<div>
						<ul>
							<b>작성 TIP</b>
							<li>어떤 수업인지 한 눈에 알 수 있는 클래스 이름</li>
							<li>스튜디오 또는 공방 및 강습소의 이름이 포함 되어도 좋습니다</li>
							<li>지역 또는 클래스 성격 (원데이 클래스 / 정규 클래스) 등을 뒤에 적어 주셔도 좋습니다</li>
							<li>등록 이후 언제나 자유롭게 수정이 가능하므로 부담없이 작성해 주세요</li>
						</ul>
					</div>
				</div>
				<br>
				<div>
					<label><b>*</b>2) 클래스 간단 소개<span>(필수)</span><span>글자수 (0 / 150)</span></label><br>
					<textarea rows="2" id="introduce" name="introduce" placeholder="클래스를 2~3줄 사이로 간단히 설명해 주세요"
						maxlength="150" style="height: 36px">${sessionScope.cdto.introduce }</textarea>
					<div>
						<ul>
							<b>작성 TIP</b>
							<li>클래스 요약 설명 작성란 입니다</li>
							<li>제목을 보완해 줄 수 있는 설명을 적어주세요</li>
							<li>다음 스텝에서 자세한 클래스 소개란이 있으므로 간단히 작성해 주셔도 좋습니다</li>
						</ul>
					</div>
				</div>
				<br>
				<div>
					<label><b>*</b>3) 클래스 카테고리<span>(필수)</span></label><br>
					<div>
						<!-- 예정) JQuery 이용 cdto에 저장된 value의 radio 선택 -->
						<label><input name="category" type="radio" value="handmade"><span>핸드메이드</span></label>
						<label><input name="category" type="radio" value="cooking"><span>쿠킹</span></label>
						<label><input name="category" type="radio" value="flower_gardening"><span>플라워·가드닝</span></label>
						<label><input name="category" type="radio" value="drawing"><span>드로잉</span></label>
						<label><input name="category" type="radio" value="music"><span>음악</span></label>
						<label><input name="category" type="radio" value="yoga_pilates"><span>요가·필라테스</span></label>
						<label><input name="category" type="radio" value="leisure_sports"><span>레져·스포츠</span></label>
						<label><input name="category" type="radio" value="beauty"><span>뷰티</span></label>
						<label><input name="category" type="radio" value="pet"><span>반려동물</span></label>
						<label><input name="category" type="radio" value="experience"><span>체험</span></label>
						<label><input name="category" type="radio" value="self-improvement"><span>자기계발</span></label>
						<label><input name="category" type="radio" value="etc"><input type="text" placeholder="기타 분류 입력">  </label>
					</div>
				</div>
				<br>
				<!-- 각 카테고리 별 서브 카테고리 (서브 카테고리 없는 카테고리도 존재)/JQuery로 display 제어 -->
				<div name="subCategory" id="handmade">
					<label><b>*</b>3-1) 클래스 서브 카테고리<span>(필수) 중복 선택 가능</span></label><br>
					<div>
						<!-- 예정) JQuery 이용 cdto에 저장된 value의 radio 선택 -->
						<label><input name="sub_category" type="checkbox" value="캔들·디퓨저"><span>캔들·디퓨저</span></label>
						<label><input name="sub_category" type="checkbox" value="향수"><span>향수</span></label>
						<label><input name="sub_category" type="checkbox" value="비누·배쓰밤"><span>비누·배쓰밤</span></label>
						<label><input name="sub_category" type="checkbox" value="위빙·소잉"><span>위빙·소잉</span></label>
						<label><input name="sub_category" type="checkbox" value="라탄·마크라메"><span>라탄·마크라메</span></label>
					</div>
				</div>
				<div name="subCategory" id="cooking" >
					<label><b>*</b>3-1) 클래스 서브 카테고리<span>(필수) 중복 선택 가능</span></label><br>
					<div>
						<!-- 예정) JQuery 이용 cdto에 저장된 value의 radio 선택 -->
						<label><input name="sub_category" type="checkbox" value="베이킹"><span>베이킹</span></label>
						<label><input name="sub_category" type="checkbox" value="요리"><span>요리</span></label>
						<label><input name="sub_category" type="checkbox" value="떡·앙금"><span>떡·앙금</span></label>
						<label><input name="sub_category" type="checkbox" value="디저트·음료"><span>디저트·음료</span></label>
						<label><input name="sub_category" type="checkbox" value="커피·바리스타"><span>커피·바리스타</span></label>
					</div>
				</div>
				<br>
				<div>
					<label><b>*</b>4) 클래스 대표 이미지<span>(필수)</span></label><br>
					<!-- Ajax 적용 예정 -->
					<div>
						<input type="hidden" id="classRep_img_idx" name="img_idx" value="classRep" >
						<button type="button" id="classRepImgUploadBtn">
							<span>이미지 업로드</span><br>
							<span>jpg, jpeg, jfif, png, bmp 확장자만 업로드 가능합니다.</span><br>
							<span>5MB 이하 용량의 이미지만 업로드 가능합니다.</span>
							
							<input type="file" id="classRep_img_file" accept="image/*" hidden="hidden">
						</button>
						
						<div id=classRepImgResult>
							<div id="classRepImgDiv" style="position:relative; width:300px;">
								<img id="classRepReturnImg" src="/LatteClass_merge/upload/${sessionScope.classRepFdto.img_upload_path}/${sessionScope.classRepFdto.img_name}" style="width:300px">
									<!-- 이미지 경로, 상대 경로로 지정하면 에러 발생 -->
							</div>
						</div>
					</div>

					<div>
						<ul>
							<b>작성 TIP</b>
							<li>클래스 대표 이미지 한 장을 업로드 해주세요</li>
							<li>너무 큰 용량은 업로드 되지 않을 수 있습니다</li>
							<li>다음 스텝에서 여러장의 이미지들을 추가 할 수 있습니다 :)</li>
						</ul>
					</div>
				</div>
				<br>
				<div>
					<label><b>*</b>5) 강사 소개<span>(필수)</span><span id="tutor_introduceSpan">글자수 (0 / 600)</span></label><br>
					<!-- Ajax 적용 예정 -->
					<div>
						<input type="hidden" id="tutorRep_img_idx" name="img_idx" value="tutorRep" >
						<button type="button" id="tutorRepImgUploadBtn">
							<span>이미지 업로드</span><br>
							<span>jpg, jpeg, jfif, png, bmp 확장자만 업로드 가능합니다.</span><br>
							<span>5MB 이하 용량의 이미지만 업로드 가능합니다.</span>
							
							<input type="file" id="tutorRep_img_file" accept="image/*" hidden="hidden">
						</button>
						
						<div id=tutorRep_img_file>
							<div id="tutorRepImgDiv" style="position:relative; width:300px;">
								<img id="tutorRepReturnImg" src="/LatteClass_merge/upload/${sessionScope.tutorRepFdto.img_upload_path}/${sessionScope.tutorRepFdto.img_name}" style="width:300px">
									<!-- 이미지 경로, 상대 경로로 지정하면 에러 발생 -->
							</div>
						</div>
					</div>
					<textarea rows="6" id="tutor_introduce" name="tutor_introduce" placeholder="강사 소개를 적어주세요"
						maxlength="600" style="height: 108px">${sessionScope.cdto.tutor_introduce }</textarea>
					<div>
						<ul>
							<b>작성 TIP</b>
							<li>강사 이미지와 소개 내용을 잘 작성해주시면 클래스에 대한 신뢰도가 향상 됩니다</li>
							<li>1. 간단한 인사말과 이름 또는 예명</li>
							<li>2. 관련 이력 또는 자격증</li>
							<li>3. 대화체의 친근한 강사 소개 (스토리텔링 형식)</li>
						</ul>
					</div>
				</div>
				<br>
				<div>
					<input type="submit" value="이전" disabled="disabled">
					<input type="submit" value="다음" id="nextSubmit" onclick='classAddSubmit(2)'>
				</div>
			</form>
		</div>
		
	</div>






	<!-- 스크립트 영역 -->	
	<script>
		function classAddSubmit(idx){
			if(idx == 1) {
				classAddForm.action = './ClassAdd.cl?idx=1';
				classAddForm.submit();
			}
			else if(idx == 2) {
				classAddForm.action = './ClassAdd.cl?idx=2';
				classAddForm.submit();
			}
			else if(idx == 3) {

				classAddForm.action = './ClassAdd.cl?idx=3';
				classAddForm.submit();
			}
		}
	</script>
	
</body>
</html>