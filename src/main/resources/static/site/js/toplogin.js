var weixinlayer;

function login(){
    var username = $("input[name='username']").val();
    if(username==''){
       // alert('please input your username!');
       layer.msg('please input your username!', {icon: 2}); 
        return false;
    }
    
    var password = $("input[name='password']").val();
    if(password==''){
        //alert('please input your password!');
        layer.msg('please input your password!', {icon: 2}); 
        return false;
    }
    
    $("#Top_Login_From_Submit").val("WAIT");
    $("#Top_Login_From_Submit").removeAttr("onclick");
    
    //提交表单
	$.ajax({
      	url:'/login/dologin/',
       type:"POST",
       data:$("#Top_Login_From").serialize(),
    success:function(data){
	        var results = eval("("+data+")");
	        if(results.code == 1){
	            layer.msg(results.message,{time: 2000,icon: 1},function(){
	            
	                window.location.reload();
	                
	            });
			}else{
			   
			    layer.msg(results.message, {icon: 2}); 
			    $("#Top_Login_From_Submit").val("LOGIN");
    			$("#Top_Login_From_Submit").attr("onclick","login()");
    			 
			    return false;
			}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
	    
	    }
  	});	
}