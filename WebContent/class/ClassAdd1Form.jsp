<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
클래스 등록 페이지 동작 방식
- 총 3단계로 구성
- 각 단계간 이동 (이전, 다음 버튼 클릭 시) 시 idx 파라메터를 url에 붙여 ClassAddAction.cl로 넘김
- ClassAddAction.java 페이지에서 Session 객체 생성 및 ClassDTO 객체 생성 후 정보 cdto에 저장 -> session 객체에 저장
- idx 파라메터 값에 따라 해당 페이지로 redirect 방식으로 페이지 이동
- 예정) 최종 등록 시 DB 저장되고 session의 cdto 객체는 무효화(제거)
 -->

<%
	request.setCharacterEncoding("UTF-8");
%>
	<!-- WebContent/class/ClassAdd1Form.jsp -->
	<!-- 클래스 등록1 페이지 -->
	
	<p>클래스 등록</p>
	<div>
		<div>
			<span>1 기본 정보 입력</span>
			<span>--</span>
			<span>2 클래스 상세 소개</span>
			<span>--</span>
			<span>3 클래스 위치 등록</span>
		</div>
		
		<div>
			<ul>
				<b>작성 유의 사항</b>
				<li>스텝 3까지 작성 후 최종 등록해 주셔야 내용이 소실 되지 않습니다</li>
				<li>등록 이후에도 자유롭게 수정 하실 수 있으니, 간단히 먼저 작성 후 등록 신청 하셔도 좋습니다</li>
				<li>일정 및 금액 설정은 등록 신청 이후에 '나의 클래스' 페이지에서 가능합니다</li>
				<li>필수 작성 사항들을 기입하신 후 다음 스텝으로 이동이 가능합니다</li>
			</ul>
		</div>
		
		<div>
			<div>
				<!-- Ajax 적용 예정 -->
				<form name="teachertForm" method="post">
					<div>
						<p><b>*</b>강사님 기본 정보 입력<span>(필수)</span></p>
					</div>
					<div>
						<p>계정 ID<span>(알림 메일이 발송됩니다)</span></p>
						<p>이메일(출력)</p>
					</div>
					<div>
						<p><b>*</b>강사명(닉네임)</p>
						<input name="userName" value="닉네임(출력)" readonly="readonly">
					</div>
					<div>
						<p><b>*</b>전화 번호<span>(알림 메일이 발송됩니다)</span></p>
						<input name="phoneNumber" value="전화 번호(출력)" readonly="readonly">
					</div>
					<div>
						<p>상호명/브랜드명</p>
						<input name="companyName" placeholder="공방, 스튜디오, 강습소 이름">
					</div>
					<div>
						<p>SNS</p>
						<input name="instagram" placeholder="인스타"><br>
						<input name="blog" placeholder="블로그"><br>
						<input name="youtube" placeholder="유튜브">
					</div>
					<div>
						<button type="button" >수정하기</button>
						<button type="button"  hidden="hidden">취소</button>
						<button type="button"  hidden="hidden">저장</button>
					</div>
				</form>
			</div>
			
			<form  method="post">
				<div>
					<label><b>*</b>1) 클래스명<span>(필수)</span><span>글자수((출력)/ 50)</span></label><br>
					<input id="title" name="title" type="text" maxlength="50">
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
				<div>
					<label><b>*</b>2) 클래스 간단 소개<span>(필수)</span><span>글자수((출력)/ 150)</span></label><br>
					<textarea rows="2" id="introduce" name="introduce" placeholder="클래스를 2~3줄 사이로 간단히 설명해 주세요"
						maxlength="150" style="height: 36px"></textarea>
					<div>
						<ul>
							<b>작성 TIP</b>
							<li>클래스 요약 설명 작성란 입니다</li>
							<li>제목을 보완해 줄 수 있는 설명을 적어주세요</li>
							<li>다음 스텝에서 자세한 클래스 소개란이 있으므로 간단히 작성해 주셔도 좋습니다</li>
						</ul>
					</div>
				</div>
				<div>
					<label><b>*</b>3) 클래스 카테고리<span>(필수)</span></label><br>
					<div>
						<label><input name="category" type="radio" value="핸드메이드"><span>핸드메이드</span></label>
						<label><input name="category" type="radio" value="쿠킹"><span>쿠킹</span></label>
						<label><input name="category" type="radio" value="플라워·가드닝"><span>플라워·가드닝</span></label>
						<label><input name="category" type="radio" value="드로잉"><span>드로잉</span></label>
						<label><input name="category" type="radio" value="음악"><span>음악</span></label>
						<label><input name="category" type="radio" value="요가·필라테스"><span>요가·필라테스</span></label>
						<label><input name="category" type="radio" value="레져·스포츠"><span>레져·스포츠</span></label>
						<label><input name="category" type="radio" value="뷰티"><span>뷰티</span></label>
						<label><input name="category" type="radio" value="반려동물"><span>반려동물</span></label>
						<label><input name="category" type="radio" value="체험"><span>체험</span></label>
						<label><input name="category" type="radio" value="자기계발"><span>자기계발</span></label>
						<label><input name="category" type="radio" value="기타"><input type="text" placeholder="기타 분류 입력">  </label>
					</div>
				</div>
	
				<div>
					<label><b>*</b>4) 클래스 대표 이미지<span>(필수)</span></label><br>
					<!-- Ajax 적용 예정 -->
					<input type="file" name="classRepImg">
					<div>
						<ul>
							<b>작성 TIP</b>
							<li>클래스 대표 이미지 한 장을 업로드 해주세요</li>
							<li>너무 큰 용량은 업로드 되지 않을 수 있습니다</li>
							<li>다음 스텝에서 여러장의 이미지들을 추가 할 수 있습니다 :)</li>
						</ul>
					</div>
				</div>
				
				<div>
					<label><b>*</b>5) 강사 소개<span>(필수)</span><span>글자수((출력)/ 600)</span></label><br>
					<!-- Ajax 적용 예정 -->
					<input type="file" name="teacherRepImg"><br>
					<textarea rows="6" id="teacher" name="teacher" placeholder="강사 소개를 적어주세요"
						maxlength="600" style="height: 108px"></textarea>
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
				
				<div>
					<input type="submit" value="이전" disabled="disabled">
					<input type="submit" value="다음" onclick='classAddSubmit(2)'>
				</div>
			</form>
		</div>
		
	</div>




<!-- /////////////////////////////////////////// -->
<!-- 이하 작성 코드는 연습용입니다. -->
	<h2> session ${sessionScope.cdto }</h2> 
	<h2> session address ${sessionScope.cdto.address }</h2> 
	<h2> session content ${sessionScope.cdto.content }</h2> 
	<h2> session ${sessionScope.fdto }</h2> 
	<h2> session uploadFileName ${sessionScope.fdto.uploadFileName }</h2>
		
	<form name="classAddForm" method="post">
		<div>
			<input type="text" name="content" value="${sessionScope.cdto.content }" placeholder="내용 작성">
			
		</div>
		
		
		<!-- 이미지 -->
		<div>
			<img src="D:\workspace_mocaclass\mocaclass\WebContent\partUpload\${sessionScope.fdto.uploadFileName }" style="width:200">
			<a href="javascript:imgUploadPopup()">
				이미지 등록 및 수정
			</a>
		</div>
	
		<div>
			<input type="submit" value="이전(클릭X)" >
			<input type="submit" value="다음" onclick='classAddSubmit(2)'>
			
		</div>
	</form>

	<!-- 스크립트 영역 -->
	<script type="text/javascript">
		function imgUploadPopup(){
			var url = "../partFileUpload/partUploadFormPopup.jsp";
			var title = "img upload popup";
			var option = "width = 500, height = 500, top = 100, left = 200, location = no"
			    window.open(url, name, option);
		}
	
	</script>
	
	<script>
		function classAddSubmit(idx){
			if(idx == 1) {
				classAddForm.action = './ClassAddAction.cl?idx=1';
				classAddForm.submit();
			}
			else if(idx == 2) {
				classAddForm.action = './ClassAddAction.cl?idx=2';
				classAddForm.submit();
			}
			else if(idx == 3) {
				classAddForm.action = './ClassAddAction.cl?idx=3';
				classAddForm.submit();
			}
		}
	</script>
</body>
</html>