$(function(){
	//获取登录编码下的用户信息列表
	get_users();
});

//获取用户信息函数
function get_users(){
	var url = url_basic+'user_step2users';
	$.post(url,get_users_callback);
};

//获取用户信息回调函数
function get_users_callback(data){
	var status = data.status;
	if(status !== 200){
		alert(data.tip);
	}else{
		if(data.us != undefined && data.us.length !== 0){
			$('.contentMember').append($(template.render('users',data)));
		}
	}
};