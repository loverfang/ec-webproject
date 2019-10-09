function del(delUrl){

	var selectIDs = document.getElementsByName("selectIDs");
	
	var flag=false;
	
		for(var i=0;i<selectIDs.length;i++){
			if(selectIDs[i].checked==true){
				flag=true;
				break;
			}
		}
		if(!flag){
			alert("请选择要删除的条目!");
			return;
		}
		//删除提示
		if(window.confirm("确实要删除吗？")){
			with(document.getElementById('listForm')){
				method="post";
				action=delUrl;
				submit();
			}
		}
}

function sel(obj){
	if(obj.checked == true){
		selAll();
	}else if(obj.checked == false){
		noSelAll();
	}
}

function selAll(){
	var selectID = document.getElementsByName('selectIDs');
	 
	
	for(i=0;i<selectID.length;i++){
		if(!selectID[i].checked){
			selectID[i].checked=true;
		}
	}
}
function noSelAll(){
	var selectID = document.getElementsByName('selectIDs');
	for(i=0;i<selectID.length;i++){
		if(selectID[i].checked){
			selectID[i].checked=false;
		}
	}
}
