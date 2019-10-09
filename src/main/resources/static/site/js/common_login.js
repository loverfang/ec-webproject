//弹出第一步框框
var loginLayer;

var  loginDiv = "<div class=\"window1\" id=\"login_window\" style=\"margin:0px;display:none;\">";
loginDiv += 	"<div class=\"win-kuang\">";
loginDiv +=		"<p><img src=\"/images/images_018.jpg\" width=\"590\" height=\"56\"></p>";
loginDiv +=		"<div class=\"win-list pt45\">";
loginDiv +=	    "<form id=\"commonLoginForm\">"

loginDiv +=			"<dl>";
loginDiv +=				"<dt>Corporate User name</dt>";
loginDiv +=				"<dd>";
loginDiv +=					"<input name=\"username\" type=\"text\" class=\"inp\">";
loginDiv +=				"</dd>";
loginDiv +=			"</dl>";
loginDiv +=			"<dl>";
loginDiv +=				"<dt>Password</dt>";
loginDiv +=				"<dd>";
loginDiv +=					"<input name=\"password\" type=\"password\" class=\"inp\">";
loginDiv +=				"</dd>";
loginDiv +=			"</dl>";
loginDiv +=			"<dl>";
loginDiv +=				"<dt>";
loginDiv +=					"<input type=\"checkbox\" name=\"ifchecked\" id=\"checkbox\" value=\"autologin\">";
loginDiv +=				"</dt>";
loginDiv +=				"<dd> Remember me for 2 weeks </dd>";
loginDiv +=			"</dl>";
loginDiv +=			"<dl>";
loginDiv +=				"<dt>&nbsp;</dt>";
loginDiv +=				"<dd>";
loginDiv +=					"<div class=\"fl login-bnt\"><a href=\"javascript:void(0)\" onclick=\"loginSubmit()\">Login</a> </div>";
loginDiv +=					"<div class=\"underline fl baise\"><a href=\"javascript:void(0)\" onclick=\"openStep1Layer()\">Recover your password</a></div>";
loginDiv +=				"</dd>";
loginDiv +=			"</dl>";
loginDiv +=			"<dl>";
loginDiv +=				"<dt>&nbsp;</dt>";
loginDiv +=				"<dd> <a href=\"/regist/\" class=\"baise underline\">No account? Register now!</a> </dd>";
loginDiv +=			"</dl>";
loginDiv +=			"</form>";

loginDiv +=		"</div>";
loginDiv +=	"</div>";
loginDiv +="</div>";



function openLoginLayer(){

    $("body").append(loginDiv);
    
    layer.closeAll();
    
    //页面层-自定义
	step1Layer = layer.open({
		type: 1,
		title: false,
		fix:false,
		closeBtn: 0,
		area: ['820px', '585px'],
		shadeClose: true,
		content: $("#login_window")
	});
}

function closeLoginLayer(){
    //$("#registForm")[0].reset();
    layer.close(loginLayer);
}

function loginSubmit(){

    var username = $("#commonLoginForm input[name=username]").val();
    if(username=null||username==""){
        layer.msg("please fill your username",{time: 2000,icon: 2});
    	return false;
    }
    
    var password = $("#commonLoginForm input[name=password]").val();
    if(password=null||password==""){
    	layer.msg("please fill your password",{time: 2000,icon: 2});
    	return false;
    }
    
    //提交表单
	$.ajax({
        url:'/login/dologin/',
       type:"POST",
       data:$("#commonLoginForm").serialize(),
    success:function(data){
              var results = eval("("+data+")");
              if(results.code == 1){
                  //发送成功后弹出提示框
                  layer.msg(results.message, {time: 2000,icon: 1}, function(){
	                layer.closeAll();
	                window.location.reload();
	              });
                  
				  //$("#Forget_Send").html("SEND");
        		  //$("#Forget_Send").attr("onclick","validateEmail()");
			  }else{
				  layer.msg(results.message, {time: 2000,icon: 2}, function(){
	                //layer.closeAll();
	                //window.location.reload();
	              });
				  return false;
			  }
			  
			  
          },
      error: function (XMLHttpRequest, textStatus, errorThrown) {
	      
	  }
  	});
}
