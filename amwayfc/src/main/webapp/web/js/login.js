/**
 * 	登录操作
 */
$(function(){
	$('#login').on('click',function(){
		login();
	});
});

//登录函数操作
function login(){
	var url = url_basic+'login_login';
	var par = {};
	var flag = true;
	//检查是否为空
	$('.inputLogin').each(function(){
		var val = $(this).val();
		var name = $(this).attr('name');
		if(val === ''){
			alert(login_blank_tip);
			flag = false;
			return flag;
		}else{
			par[name] = val;
		}
	});
	if(flag){
		$.post(url,par,login_callback);
	}
};

//登录回调函数
function login_callback(data){
	var statsu = data.status;
	if(statsu !== 200){
		alert(data.tip);
	}else{
		lf('step01.html');
	}
};


