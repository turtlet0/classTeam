package classes.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.db.ClassDTO;
import classes.db.TutorDTO;
// 클래스 등록 정보(tutor, class) 세션 객체에 저장
import classes.db.classDAO;

// 동작 구현을 위한 클래스는 Action 인터페이스 상속받아 사용 - 추상메서드 execute 사용
public class ClassAddAction implements Action {
	/*클래스 등록 1 페이지 처리 class*/
	
	// 추상 메서드 execute 구현
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("CA. ClassAddAction_execute() 호출!");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		/*session 제어*/
		HttpSession session = request.getSession(); 
		// tip) getSession() | (true) : HttpSession 객체 기존재 시 해당 객체 가져옴. 없으면 새로 생성  
		// System.out.println("session is new?: "+session.isNew()); // 새로 생성된 객체인지 여부 출력
		
		// 로그인 여부 확인
		int member_cd = Integer.parseInt(String.valueOf(session.getAttribute("member_cd"))); // (String) 이용 형변환 시 에러 발생
		
		
		ActionForward forward;
		if(member_cd == 0) {
			forward = new ActionForward();
			// 로그인 X
			forward.setPath("./Main.cl"); // 임시) 로그인 페이지로 이동해야함
			forward.setRedirect(true);
			return forward;
		}
		
		
		
		
		/*튜터 정보 저장할 TutorDTO 객체 생성 -> session에 저장*/
		// session에 TutorDTO 객체 존재하면 해당 객체 가져오기. 없으면 새로 생성
		TutorDTO tdto = null;
		if(session.getAttribute("tdto") == null) {
			System.out.println("tdto 새로 생성");
			tdto = new TutorDTO();
		} else {
			System.out.println("tdto 기존");
			tdto = (TutorDTO) session.getAttribute("tdto");
			System.out.println(tdto);
		}	
		
		/*클래스 등록 정보 저장할 ClassDTO 객체 생성 -> session에 저장*/
		// session에 ClassDTO 객체 존재하면 해당 객체 가져오기. 없으면 새로 생성
		ClassDTO cdto = null;
		if(session.getAttribute("cdto") == null) {
			System.out.println("cdto 새로 생성");
			cdto = new ClassDTO();
		} else {
			System.out.println("cdto 기존");
			cdto = (ClassDTO) session.getAttribute("cdto");
			System.out.println(cdto);
		}
		
		/*// null 체크 형식 참고
		if(request.getParameter("content") == null || request.getParameter("content").equals("") 
				|| request.getParameter("content").equals("null")) {
			System.out.println("content null");
		} else {
			System.out.println("content null x");
			cdto.setContent(request.getParameter("content"));
		}*/
		
		// class_cd : DB에서 AI 이용해 추가
		// currentIdx 값(input hidden) 이용해 각 페이지에서 전달받은 파라메터만 저장
		if(request.getParameter("currentIdx") != null) {
			String currentIdx = (String) request.getParameter("currentIdx");
			System.out.println("currentIdx: "+currentIdx);
			switch (currentIdx) {
			case "1":
				System.out.println("1 정보 저장");
				// tutor 정보
				tdto.setCompany_name(request.getParameter("company_name"));
				tdto.setInstagram(request.getParameter("instagram"));
				tdto.setBlog(request.getParameter("blog"));
				tdto.setYoutube(request.getParameter("youtube"));
				
				// class 정보
				cdto.setTitle(request.getParameter("title"));
				cdto.setIntroduce(request.getParameter("introduce"));
				cdto.setCategory(request.getParameter("category"));
				
				// sub_category
				if(request.getParameterValues("sub_category") != null) { // null 체크
					String[] subCategoryArr = request.getParameterValues("sub_category");
					// 배열의 각 인덱스에 저장된 서브카테고리 데이터를 하나의 String 값으로 합치기 위한 변수 선언
					String sub_category = "";
					// 반복문 이용
					for (int i=0; i<subCategoryArr.length; i++) {
						if(i == subCategoryArr.length -1) {
							sub_category += subCategoryArr[i];
						} else {
							sub_category += subCategoryArr[i] + ",";
							
						}
					}
					cdto.setSub_category(sub_category);
					
				}
				
				
				cdto.setTutor_introduce(request.getParameter("tutor_introduce"));
				break;
			case "2":
				System.out.println("2 정보 저장");
				
				cdto.setContent(request.getParameter("content"));
				cdto.setFree_curriculum(request.getParameter("free_curriculum"));
//				cdto.setDuration_time(request.getParameter("duration_time"));
//				cdto.setStep1(request.getParameter("step1"));
//				cdto.setStep2(request.getParameter("step2"));
//				cdto.setStep3(request.getParameter("step3"));
//				cdto.setStep4(request.getParameter("step4"));
//				cdto.setStep5(request.getParameter("step5"));
				cdto.setRecommend_people(request.getParameter("recommend_people"));
//				cdto.setTag(request.getParameter("tag"));
				break;
				
			default: // idx = 3
				System.out.println("3 정보 저장");
				
				cdto.setOpening_area(request.getParameter("opening_area"));
				cdto.setPostcode(request.getParameter("postcode"));
				cdto.setRoad_address(request.getParameter("road_address"));
				cdto.setJibun_address(request.getParameter("jibun_address"));
				cdto.setDetail_address(request.getParameter("detail_address"));
				cdto.setSido(request.getParameter("sido"));
				cdto.setSigungu(request.getParameter("sigungu"));
				cdto.setBname(request.getParameter("bname"));
				cdto.setDirections(request.getParameter("directions"));
				
				// convenience_option
				if(request.getParameterValues("convenience_option") != null) {
					String[] convenienceOptionArr = request.getParameterValues("convenience_option");
					//System.out.println("request.getParameterValues(\"convenience_option\"): " + request.getParameterValues("convenience_option"));
					String convenience_option = "";
					for (int i=0; i<convenienceOptionArr.length; i++) {
						if(i == convenienceOptionArr.length -1) {
							convenience_option += convenienceOptionArr[i];
						} else {
							convenience_option += convenienceOptionArr[i] + ",";
							
						}
					}
					cdto.setSub_category(convenience_option);
				}
				
				// health_safety_option
				if(request.getParameterValues("health_safety_option") != null) {
					String[] health_safetyOptionArr = request.getParameterValues("health_safety_option");
					String health_safety_option = "";
					for (int i=0; i<health_safetyOptionArr.length; i++) {
						if(i == health_safetyOptionArr.length -1) {
							health_safety_option += health_safetyOptionArr[i];
						} else {
							health_safety_option += health_safetyOptionArr[i] + ",";
							
						}
					}
					cdto.setSub_category(health_safety_option);
				}
				
				// view 페이지에서 null 미채크 시, 형 변환 시 에러 방지 위해 null 체크 별도로 수행
				if(request.getParameter("approval_condition") == null || request.getParameter("approval_condition").equals("") 
						|| request.getParameter("approval_condition").equals("null")) {
					System.out.println("approval_condition null");
				} else {
					System.out.println("approval_condition null x");
					cdto.setApproval_condition(Integer.parseInt(request.getParameter("approval_condition")));
				}
				if(request.getParameter("reg_date") == null || request.getParameter("reg_date").equals("") 
						|| request.getParameter("reg_date").equals("null")) {
					System.out.println("reg_date null");
				} else {
					System.out.println("reg_date null x");
					cdto.setReg_date(Timestamp.valueOf(request.getParameter("reg_date")));
				}
				break;
			}
		
		
			
			// session에 DTO 객체 저장
			session.setAttribute("tdto", tdto);
			session.setAttribute("cdto", cdto);

			System.out.println(tdto);
			System.out.println(cdto);
			System.out.println();
		}
		
		
		
		/*get방식으로 받은 idx의 값에 따라 이동 페이지 결정*/
		int idx = 1;
		System.out.println(request.getParameter("idx"));
		if(request.getParameter("idx") != null) {
			idx = Integer.parseInt(request.getParameter("idx"));
		}
		
		if(idx == 1) {
			// 글쓰기 등록 1페이지로 이동
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd1Form.jsp");
			forward.setRedirect(false);
			
			System.out.println("CA. ./class/ClassAdd1Form.jsp 이동");
			return forward;
		}else if(idx == 2) {
			// 글쓰기 등록 2페이지로 이동
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd2Form.jsp");
			forward.setRedirect(false);
			System.out.println("CA. ./class/ClassAdd2Form.jsp 이동");
			
			return forward;
		} else if(idx == 3) {
			// 클래스 등록 3 페이지로 이동
			forward = new ActionForward();
			forward.setPath("./class/ClassAdd3Form.jsp");
			forward.setRedirect(false);
			
			System.out.println("CA. ./class/ClassAdd3Form.jsp 이동");
			return forward;
		}
		// idx == 4 : 최종 등록
		
		/*DB 저장*/
			// 두 메서드 연달아 수행되어야 함
		classDAO cdao = new classDAO();
		cdao.insertTutor(tdto);
		System.out.println("CA. TutorDTO DB 저장완료");
		cdao.insertClass(cdto);
		System.out.println("CA. ClassDTO DB 저장완료");
		
		/*세션 DTO 속성 제거*/
		session.removeAttribute("tdto");
		session.removeAttribute("cdto");
		
		
		/*페이지 이동*/
		forward = new ActionForward();
		forward.setPath("./Main.cl");
		forward.setRedirect(true);
		
		System.out.println("CA. ./Main.cl");
		return forward;
		
		
		
		
		
		
	}

}
