<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path%>/css/pignose.calendar.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script src="<%=path%>/js/pignose.calendar.full.min.js"></script>
<script type="text/javascript">

$(function() {
    $('.calendar').pignoseCalendar({
    	lang: 'ko',
		multiple: false,
		select: onSelectHandler,
		format: 'YYYY.MM.DD',
		minDate: moment().format("YYYY-MM-DD") //추가
	
	});
    
    
    function onSelectHandler(date, context) {
        var $element = context.element;
        var $calendar = context.calendar;
        var text1 = '';
        

        if (date[0] !== null) {
            text1 += date[0].format('YYYY-MM-DD');
        }



		$('input[name=setDate]').attr('value',text1);

        
    }
    
});

</script>
</head>
<body>

  <div class="calendar"></div>
  <form class="fr1" method="post">

  	<input name="setDate"> <br>
  	<input type="hidden" name="class_cd" value=${param.class_cd }>
  	<select name="startTime">
  		<option></option>
  		<option value="am 6:00">am 6:00</option>
  		<option value="am 7:00">am 7:00</option>
  		<option value="am 8:00">am 8:00</option>
  		<option value="am 9:00">am 9:00</option>
  		<option value="am 10:00">am 10:00</option>
  		<option value="am 11:00">am 11:00</option>
  		<option value="pm 12:00">pm 12:00</option>
  		<option value="pm 1:00">pm 1:00</option>
  		<option value="pm 2:00">pm 2:00</option>
  		<option value="pm 3:00">pm 3:00</option>
  		<option value="pm 4:00">pm 4:00</option>
  		<option value="pm 5:00">pm 5:00</option>
  		<option value="pm 6:00">pm 6:00</option>
  		<option value="pm 7:00">pm 7:00</option>
  		<option value="pm 8:00">pm 8:00</option>
  		<option value="pm 9:00">pm 9:00</option>
  		<option value="pm 10:00">pm 10:00</option>
  		<option value="pm 11:00">pm 11:00</option>
  		<option value="am 12:00">am 12:00</option>
  		<option value="am 1:00">am 1:00</option>
  		<option value="am 2:00">am 2:00</option>
  		<option value="am 3:00">am 3:00</option>
  		<option value="am 4:00">am 4:00</option>
  		<option value="am 5:00">am 5:00</option>
  	</select>
  	<select name="endTime">
  		<option></option>
  		<option value="am 6:00">am 6:00</option>
  		<option value="am 7:00">am 7:00</option>
  		<option value="am 8:00">am 8:00</option>
  		<option value="am 9:00">am 9:00</option>
  		<option value="am 10:00">am 10:00</option>
  		<option value="am 11:00">am 11:00</option>
  		<option value="pm 12:00">pm 12:00</option>
  		<option value="pm 1:00">pm 1:00</option>
  		<option value="pm 2:00">pm 2:00</option>
  		<option value="pm 3:00">pm 3:00</option>
  		<option value="pm 4:00">pm 4:00</option>
  		<option value="pm 5:00">pm 5:00</option>
  		<option value="pm 6:00">pm 6:00</option>
  		<option value="pm 7:00">pm 7:00</option>
  		<option value="pm 8:00">pm 8:00</option>
  		<option value="pm 9:00">pm 9:00</option>
  		<option value="pm 10:00">pm 10:00</option>
  		<option value="pm 11:00">pm 11:00</option>
  		<option value="am 12:00">am 12:00</option>
  		<option value="am 1:00">am 1:00</option>
  		<option value="am 2:00">am 2:00</option>
  		<option value="am 3:00">am 3:00</option>
  		<option value="am 4:00">am 4:00</option>
  		<option value="am 5:00">am 5:00</option>
  	</select>
  	<button class="subm">ㅈ송</button>
  </form>

</body>
</html>