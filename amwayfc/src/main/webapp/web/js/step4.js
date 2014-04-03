$(function(){
	render();
	
	$('#confirm').on('click',function(){
		confirm_journey();
	});
	
	$('#delete').on('click',function(){
		delete_journey();
	});
	
	$('#email').on('click',function(){
		$('#send_email').show();
	});
	
	$('.cancel_button').on('click',function(){
		$('#send_email').hide();
	});
	
	//发送按钮
	$('#send_email .confirm').bind('click',function(){
		$(this).text('发送中');
		send_email();
	});
});

function render(){
	var
	url = url_basic+'user_step4users'
	;
	$.post(url,render_callback);
};

function render_callback(data){
	var 
	status = data.status,
	isfirst = data.isfirst
	;
	if(status !== 200){

	}else{
		if(data.us[0].js.length === 0){
			lf('step02.html');
		}else{
			//已确认
			if(isfirst === 4){
				$('#print').show();
				$('#email').show();
			}
			$('.operate').eq(isfirst-3).show();
			$('.contentMember').append($(template.render('step4users',data)));
		}
	}
};

//确认行程函数
function confirm_journey(){
	var 
	url = url_basic+'journey_step4confirm'
	;
	$.post(url,confirm_journey_callback);
};

//确认行程回调函数
function confirm_journey_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		
	}else{
		lf('step04.html');
	}
};

//删除行程函数
function delete_journey(){
	var
	url = url_basic+'journey_step4delete'
	;
	$.post(url,delete_journey_callback);
};

function delete_journey_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		
	}else{
		lf('step03.html');
	}
};

//发送邮件函数
function send_email(){
	var 
	url = url_basic+'user_step4email',
	par = {
		email : $('input[name="email"]').val()
	}
	;
	if(par.email === ''){
		alert('邮箱不能为空');
	}else if(par.email.indexOf('@') < 0){
		alert('邮箱格式不正确');
	}else{	
		$('#send_email .confirm').unbind();
		$.post(url,par,send_email_callback);
	}
};

function send_email_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		
	}else{
		$('#send_email').hide();
		$('#send_email input[name="email"]').val('');
		$('#send_email .confirm').text('发送');
		$('#send_email .confirm').bind('click',function(){
			$(this).text('发送中');
			send_email();
		});
		alert('恭喜您和您的家人已经完成行程选择');
	}
};