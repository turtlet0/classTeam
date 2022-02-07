<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- WebContent/class/ClassAdd2Form.jsp -->
	<!-- 클래스 등록2 페이지 -->
	
	<p>클래스 등록</p>
	<div>
		<div>
			<span>1 기본 정보 입력</span>
			<span>--</span>
			<span>2 클래스 상세 소개</span>
			<span>--</span>
			<span>3 클래스 위치 등록</span>
		</div>
		
		<form  method="post">
			<div>
				<p><b>*</b>6. 클래스 상세 내용<span>(필수)</span></p>
				<div>
					<label><b>*</b>1) 클래스 소개<span>(필수)</span></label><br>
					<!-- 에디터 적용 예정 -->
					<textarea name="content"></textarea>
				</div>
				<div>
					<ul>
						<b>작성 TIP</b>
						<li>강사님의 클래스를 더 자세히 설명해주세요 (최소 2줄 이상)</li>
						<li>내용 복사 & 붙여 넣기의 경우 일부 화면이 깨질 수 있어요</li>
						<li>원하시면 영상 링크를 추가 할 수도 있습니다</li>
						<li>미리보기를 원하시면 버튼 리스트 맨 아래줄 우측의 돋보기 버튼을 눌러주세요</li>
						
						<b>다음과 같은 항목 위주로 작성해 주시면 좋아요</b>
						<div>
							<li>인사말</li>
							예시) 아직도 아이패드로 넷플릭스만 보고 계시나요? 애플펜슬은 샀는데 도대체 어떻게 써야할지 모르시겠다구요? 오늘부터 저와 함께 아이패드 드로잉 기초를 완전히 정복해 보아요!
						</div>
						<div>
							<li>클래스 특징 (차별성 & 강점)</li>
							예시) 프로크리에이트의 기초부터 사용팁은 물론 프린트에 대한 기본적인 지식을 쌓을 수 있습니다. 프로크리에이트 사용부터 굿즈 제작, 템플릿 넣는 법까지, 모든 과정을 꼼꼼하게 가르쳐드립니다.
						</div>
						<div>
							<li>클래스 결과물</li>
							예시) 클래스를 통해 직접 그린 그림으로 스티커, 명함엽서를 만들어 가실 수 있어요
						</div>
						<div>
							<li>클래스 난이도 및 대상</li>
							예시) 취미로 드로잉을 배워보고 싶으신 분, 나만의 굿즈를 제작해보고 싶으신 분
						</div>
						<li>준비물 및 도구 대여 여부</li>
						<li>이런 분들께 추천 해요</li>
						<li>기타 안내 사항 (주차 여부 등)</li>
					</ul>
				</div>
				
				<div>
					<p><b>*</b>2) 클래스 커리큘럼<span>(필수)</span></p>
					<button type="button">자유롭게 작성</button>
					<button type="button">단계별 작성</button>
					
					<div name="freeCuurriculum">
						<textarea rows="10" id="curriculum" placeholder="커리큘럼(클래스 진행 순서)를 적어주세요."></textarea>
					</div>
					<div name="stepCurriculum" ><!-- hidden="hidden" -->
						<div>
							<p><b>*</b>소요 시간</p>
							<textarea rows="1" id="durationTime" name="durationTime" 
									placeholder="1단계를 작성해주세요" style="height:18px"></textarea>
						</div>
						<div>
							<p><b>*</b>STEP 1</p>
							<textarea rows="1" id="step2" name="step2" 
									placeholder="2단계를 작성해주세요" style="height:18px"></textarea>
							<!-- Ajax 적용 예정 -->	
							<input type="file" name="step2mg">
							<div>
								<img>
							</div>
						</div>
						<div>
							<p><b>*</b>STEP 1</p>
							<textarea rows="1" id="step3" name="step3" 
									placeholder="3단계를 작성해주세요" style="height:18px"></textarea>
							<!-- Ajax 적용 예정 -->	
							<input type="file" name="step3Img">
							<div>
								<img>
							</div>
						</div>
						<div>
							<button type="button">단계 추가</button>
						</div>
					</div>
				</div>	
				
				<div>
					<p><b>*</b>3) 클래스 결과물 (최대 8개 등록 가능)</p>
					<!-- Ajax 적용 예정 -->
					<!-- DB 업데이트 시각으로 출력 순서 지정 예정 -->
					<input type="file" name="resultImg">
				</div>	
				
				<div>
					<p><b>*</b>3) 클래스 결과물 (최대 8개 등록 가능)</p>
					<textarea rows="1" id="recommendPeople" name="recommendPeople" 
						placeholder="셀프인테리어를 위한 소품이 필요하신 분, 연인 선물이 필요하신 분" style="height: 18px;"></textarea>
				</div>
				
				<div>
					<p>태그 - 클래스 검색할 때 사용됩니다. (최대 10개 가능)</p>
					<!-- Ajax 적용 예정 -->
					<input id="tag" name="tag" 
						placeholder="지역, 카테고리 위주로 작성해주세요">
					<button type="button">추가</button>
					<div></div>
				</div>
			
				<div>
					<input type="submit" value="이전" onclick='classAddSubmit(1)'>
					<input type="submit" value="다음" onclick='classAddSubmit(3)'>
				</div>
			</div>
		</form>
	</div>
	
<!-- ///////////////////////// -->
<!-- 이하 연습 코드 -->		
	<h1>WebContent/class/ClassAdd2Form.jsp</h1>
	
	<!-- 클래스 등록1 cdto 객체 정보 -->
	<h2> request ${requestScope.cdto }</h2> 
	1. ${cdto }
	2. ${requestScope.cdto.content }
	<h2> session ${sessionScope.cdto }</h2> 
	<h2> session address ${sessionScope.cdto.address }</h2> 
	<h2> session content ${sessionScope.cdto.content }</h2> 
	
	<form name="classAddForm" method="post" enctype="multipart/form-data">
	
		<input type="text" name="address" value="${sessionScope.cdto.address }" placeholder="주소작성하세요"><br>
		
		<!-- tip) file 타입 value값 입력(수정) 불가 -->
		<input type="file" name="filedata" id="imgFile" onchange="imgPreview(this);">
		<img id="img" width="150">
		<div>
			<input type="submit" value="이전" onclick='classAddSubmit(1)'>
			<input type="submit" value="다음" onclick='classAddSubmit(3)'>
		</div>
	</form>
	
	
	
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=af4b76efafbb11499fecf49b4f1f8f9a&libraries=services"></script>
	<script type="text/javascript" src="../js/addressMapAPI.js"></script>
	
	<script type="text/javascript">
		function imgPreview(input) {
			alert("onchange");
			/* alert(input.files);
			alert(e.target.result);
			alert(document.getElementById('img').src); */
			if (input.files && input.files[0]) {
				/* alert("input O" + e.target.result); */
			   var reader = new FileReader();
			   reader.onload = function(e) {
			     document.getElementById('img').src = e.target.result;
			   };
			   reader.readAsDataURL(input.files[0]);
			 } else {
				/* alert("input X" + e.target.result); */
			   document.getElementById('img').src = "";
			 }
		};
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