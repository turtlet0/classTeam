<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
    System.out.println(ctx);
%>
<!-- SmartEditor JS -->
<!-- 스마트에디터2 적용 참고 사이트 : https://m.blog.naver.com/javaking75/220249200436 -->
<!-- 스마트에디터2 이미지 조절 플러그인 적용 참고 사이트 : https://github.com/naver/smarteditor2/issues/36-->
<script type="text/javascript" src="/LatteClass/se2/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- 스마트에디터2 JS -->
<script type="text/javascript">
	var oEditors = [];
	$(function(){
		// alert("JQuery 정상 동작");
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
			//SmartEditor2Skin.html 파일이 존재하는 경로
			sSkinURI: "/LatteClass/se2/SmartEditor2Skin.html",  
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,             
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,     
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,         
				fOnBeforeUnload : function(){
				     
				}
			}, 
			fCreator: "createSEditor2",
			fOnAppLoad : function(){
				//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
				oEditors.getById["ir1"].exec("PASTE_HTML", ['${sessionScope.cdto.content}']);
			} 
		});
		      
		//submit 클릭시 form 전송
		$(":submit").click(function(){
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
		});
	});
</script>
</head>
<body>
	<!-- WebContent/class/ClassAdd2Form.jsp -->
	<!-- 클래스 등록2 페이지 -->
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
		<hr>
		<form name="classAddForm" method="post">
			<input type="text" name="currentIdx" value="2" hidden="hidden"><!-- Action으로 넘어오기 전 현재 페이지 Idx 확인 위해 전달 -->
		
			<div>
				<p><b>*</b>6. 클래스 상세 내용<span>(필수)</span></p>
				<div>
					<label><b>*</b>1) 클래스 소개<span>(필수)</span></label><br>
					<!-- 에디터 적용 예정 -->
					<textarea rows="10" cols="30" id="ir1" name="content" style="width:650px; height:350px;"></textarea>
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
				<br>
				<div>
					<p><b>*</b>2) 클래스 커리큘럼<span>(필수)</span></p>
					<button type="button">자유롭게 작성</button>
					<button type="button">단계별 작성</button>
					
					<div name="freeCurriculum">
						<textarea rows="10" id="free_curriculum" placeholder="커리큘럼(클래스 진행 순서)를 적어주세요." value="${sessionScope.cdto.free_curriculum }"></textarea>
					</div>
					<div name="stepCurriculum" ><!-- hidden="hidden" -->
						<div>
							<p><b>*</b>소요 시간</p>
							<textarea rows="1" id="duration_time" name="duration_time" 
									placeholder="소요시간/기간을 입력해주세요." style="height:18px" value="${sessionScope.cdto.duration_time }"></textarea>
						</div>
						<div>
							<p><b>*</b>STEP 1</p>
							<textarea rows="1" id="step1" name="step1" 
									placeholder="1단계를 작성해주세요" style="height:18px" value="${sessionScope.cdto.step1 }"></textarea>
							<!-- Ajax 적용 예정 -->	
							<input type="file" name="step1_img">
							<div>
								<img>
							</div>
						</div>
						<div>
							<p>STEP 2</p>
							<textarea rows="1" id="step2" name="step2" 
									placeholder="2단계를 작성해주세요" style="height:18px" value="${sessionScope.cdto.step2 }"></textarea>
							<!-- Ajax 적용 예정 -->	
							<input type="file" name="step2_img">
							<div>
								<img>
							</div>
						</div>
						<div>
							<p>STEP 3</p>
							<textarea rows="1" id="step3" name="step3" 
									placeholder="3단계를 작성해주세요" style="height:18px" value="${sessionScope.cdto.step3 }"></textarea>
							<!-- Ajax 적용 예정 -->	
							<input type="file" name="step3_img">
							<div>
								<img>
							</div>
						</div>
						<div>
							<!-- Ajax 추가 예정 -->
							<button type="button">단계 추가</button>
						</div>
					</div>
				</div>	
				<br>
				<div>
					<p>3) 클래스 결과물 (최대 8개 등록 가능)</p>
					<!-- Ajax 적용 예정 -->
					<!-- DB 업데이트 시각으로 출력 순서 지정 예정 -->
					<input type="file" name="result_img">
				</div>	
				<br>
				<div>
					<p>4) 이런분들이 들으면 좋아요</p>
					<textarea rows="1" id="recommend_people" name="recommend_people" 
						placeholder="셀프인테리어를 위한 소품이 필요하신 분, 연인 선물이 필요하신 분" style="height: 18px;" value="${sessionScope.cdto.recommend_people }"></textarea>
				</div>
				<br>
				<div>
					<p>태그 - 클래스 검색할 때 사용됩니다. (최대 10개 가능)</p>
					<!-- Ajax 적용 예정 -->
					<input id="tag" name="tag" 
						placeholder="지역, 카테고리 위주로 작성해주세요" value="${sessionScope.cdto.tag }">
					<button type="button">추가</button>
					<div></div>
				</div>
				<br>
				<div>
					<input type="submit" value="이전" onclick='classAddSubmit(1)'>
					<input type="submit" value="다음" onclick='classAddSubmit(3)'>
				</div>
			</div>
		</form>
	</div>
	

	
	

	
	<!-- 스크립트 영역 -->
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