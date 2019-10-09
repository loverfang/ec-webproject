//弹出第一步框框
var step1Layer;
var mailConfirm;

function openStep1Layer(){
    
    layer.closeAll(); 
    
    //页面层-自定义
	step1Layer = layer.open({
		type: 1,
		title: false,
		fix:false,
		closeBtn: 0,
		area: ['820px', '585px'],
		shadeClose: true,
		content: $("#forget_window")
	});
}
    
function closeStep1Layer(){
    $("#registForm")[0].reset();
    layer.close(step1Layer);
}

function closeAllLayer(){
	$("#email").val("");
	layer.closeAll(); 
}

function validateEmail(){

    $("#Forget_Send").html("Waitting...");
    $("#Forget_Send").removeAttr("onclick");
    
    //提交表单
	$.ajax({
        url:'/forgetpwd/',
       type:"POST",
       data:{"email":$("#email").val()},
    success:function(data){
              var results = eval("("+data+")");
              if(results.code == 1){
                  //发送成功后弹出提示框
                  mailConfirm = layer.open({
						type: 1,
						title: false,
						fix:false,
						closeBtn: 0,
						area: ['660px', '227px'],
						shadeClose: true,
						content: $("#ForgetEmailConfirm")
				  });
				  
				  $("#Forget_Send").html("SEND");
        		  $("#Forget_Send").attr("onclick","validateEmail()");
			  }else{
				  layer.alert(results.message);
				  return false;
			  }
			  
          },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
	      
	  }
  	});
  	
}
