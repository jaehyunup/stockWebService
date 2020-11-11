/*
 GET 요청을 통해 REST API에 최대이윤 최소/최대 거래일자 요청 및 view에 반환하는 메서드
 */

function serachMaxProfit(){
	var formDate = $("#searchForm").serialize();
	var symbol=$("#symbol").val().toUpperCase();
	console.log(symbol);
	var newURI="http://localhost:8888/stocks/maximumprofit/"+symbol;
    $.ajax({
        url: newURI,
        type: 'GET',
        dataType:"json"
    }).done(function(json) { 
        console.log(json)
        $("#serachText").html("거래종목 ["+symbol+"] - 최대 이윤 매수/매도일 검색결과");
        $("#mindate").html("매수 날짜 :"+json.minDate).css("color","white")
        $("#maxdate").html("매도 날짜 :"+json.maxDate)
        $("#profit").html("예상 수익금 : "+json.profit.toFixed(2)+" 달러")
	  		
        
    }).fail(function(xhr,status,errorThrown){
        console.log(status)
        $("#mindate").html("정확한 주식시장 Symbol을 입력해주세요").css("color","red")
        $("#maxdate").html("")
        $("#profit").html("")
        console.log(errorThrown)
   })
}