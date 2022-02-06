<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>
  <link href="payment.css" rel="stylesheet">
  <!-- iamport.payment.js -->
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
  <script type="text/javascript">
    	
     	function doDisplay1(){
     		var con1 = document.getElementById("showbtn1");
     		
     		if(con1.style.display=='none'){
     			con1.style.display = 'block';
     		}else{
     			con1.style.display = 'none';
     		}
     	}
     	
     	function doDisplay2() {
     		var con2 = document.getElementById("showbtn2");
     		
     		if(con2.style.display=='none'){
     			con2.style.display = 'block';
     		}else{
     			con2.style.display = 'none';
     		}
			
		}
     </script>
  
</head>
<body>
	<!-- 결제 기능 조문주 -->

    <section id="section-payment"> 
      <h2>클래스 결제 페이지</h2>
      
      <div class="div-payment-left">
        <div class="div-payment">
          <div>클래스 티켓 정보</div>
          <div>
            <div>이미지</div>
            <div>클래스 명</div>
            <div>일시</div>
            <div>장소</div>
          </div>
        </div>
        <div class="div-payment">
          <div>연락처 입력</div>
          <div>
            <div>계정</div>
            <div>이름</div>
            <div>전화번호</div>
          </div>
          <button>연락처 수정</button>
        </div>
      </div>
			
	  <div class="div-payment-right">
	    <div class="div-payment">
          <div>결제 정보</div>
            <div>
              <div>티켓 갯수</div>
              <div>가격</div>
              <div>결제 금액</div>
            </div>
        </div>
        
        <div class="div-payment">
          <div>취소 및 환불 안내 <button onclick="doDisplay1()">>></button></div>
            <div style="display: none;" id="showbtn1">
              <p>dlrj</p>
            </div>
        </div>
        
        <div class="div-payment">
          <div> 결제 안내 <button onclick="doDisplay2()">>></button> </div>
            <div style="display: none;" id="showbtn2">
              <p>결제 안ㄴ내어쩌구ㅇ</p>
            </div>
        </div>
            
        <div class="div-payment">
          <div>결제</div>
            <div>
              <div>신용카드</div>
              <div>네이버페이</div>
              <div>카카오페이</div>
            </div>
            <button id="btn-pay" class="btn_payment">결제하기</button>
        </div>
      </div>
     </section>
     
     
     <!-- 결제 api -->
     <script type="text/javascript">
     
     $(".btn_payment").click(function(){

     	IMP.init('imp31095046');

     	IMP.request_pay({
        	pg : 'inicis',
        	pay_method : 'card',
        	merchant_uid : 'merchant_' + new Date().getTime(),
        	name : '주문명:결제테스트',
        	amount : 100, 
        	buyer_email : 'iamport@siot.do',
        	buyer_name : '구매자이름',
        	buyer_tel : '010-1234-5678',
        	buyer_addr : '서울특별시 강남구 삼성동',
        	buyer_postcode : '123-456'
		}, function(rsp) {
        	if ( rsp.success ) {
        		var msg = '결제가 완료되었습니다.';
            	msg += '고유ID : ' + rsp.imp_uid;
            	msg += '상점 거래ID : ' + rsp.merchant_uid;
            	msg += '결제 금액 : ' + rsp.paid_amount;
            	msg += '카드 승인번호 : ' + rsp.apply_num;
            
            	
         	} else {
         		var msg = '결제에 실패하였습니다.';
             	msg += '에러내용 : ' + rsp.error_msg;
         	}
         		alert(msg);
     		});
     });
     
    /*  function db(pg : 'inicis',
        	pay_method : 'card',
        	merchant_uid : 'merchant_' + new Date().getTime(),
        	name : '주문명:결제테스트',
        	amount : 100, 
        	buyer_email : 'iamport@siot.do',
        	buyer_name : '구매자이름',
        	buyer_tel : '010-1234-5678',
        	buyer_addr : '서울특별시 강남구 삼성동',
        	buyer_postcode : '123-456'){
    	 
     } */
	</script>
     
    
    
    
    
</body>
</html>