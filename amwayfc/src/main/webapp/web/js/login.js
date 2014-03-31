/**
 * 	登录操作
 */	
$(function(){
	$('#login').on('click',function(){
		login();
	});

	$('.cancel_button').on('click',function(){
		$(this).parent().parent().hide();
	});

	$('#update_password .confirm').on('click',function(){
		update_password();
	});

	//忘记按钮点击事件
	$('#forget_password').on('click',function(){
		$('#find_password').show();
	});

	//发送按钮绑定事件
	$('#find_password .confirm').bind('click',function(){
		$(this).text('发送中');
		find_password();
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
		if(data.isFirst === 1){
			alert('尊敬的安利贵宾，您已登录成功！首次登录的贵宾请修改密码');
			$('#update_password').show();
		}else{
			lf('step04.html');
		}
	}
};

//更改密码函数
function update_password(){
	var 
	url = url_basic+'login_updatePassword',
	par = {},
	flag = true
	;
	$('#update_password input').each(function(){
		var 
		$this = $(this),
		val = $this.val(),
		name = $(this).attr('name')
		;
		if(val === ''){
			alert('新密码和确认密码不能为空');
			flag = false;
			return flag;
		}else{
			par[name] = val;
		}
	});

	if(flag){
		if(par.password !== par.confirmPassword){
			alert('密码不一致');
		}else{
			$.post(url,par,update_password_callback);
		}
	}
};

//更改密码回调函数
function update_password_callback(data){
	var 
	status = data.status
	;
	if(status != 200){
		alert(data.tip);
	}else{
		alert('您的密码已修改成功！点击“确定”，进入在线注册流程。');
		lf('step01.html');
	}
};

//找回密码函数
function find_password(){
	var
	url = url_basic+'login_findPassword',
	par = {},
	flag = true
	;
	$('#find_password input').each(function(){
		var 
		$this = $(this),
		val = $this.val(),
		name = $this.attr('name');
		if(val === ''){
			alert('用户编码和用户邮箱不能为空');
			flag = false;
			return flag;
		}else{
			par[name] = val;	
		}
	});
	if(flag){
		$('#find_password .confirm').unbind();
		$.post(url,par,find_password_callback);
	}
};

//找回密码回调函数
function find_password_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		alert(data.tip);
	}else{
		alert(data.tip);
		$('#find_password').hide().find('input').val('');
	}
	$('#find_password .confirm').text('发送');
	$('#find_password .confirm').bind('click',function(){
		$(this).text('发送中');
		find_password();
	})
};