$(function(){
	var page = location.href.split('?')[1];
	if(!!page){
//		$('.reelect').attr('href',page+'.html');
//		$('.reelect').prev().remove();
		$('.operate a').attr('href',page+'.html');
		$('.operate a').text('返回');
	}
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
	var 
	status = data.status,
	us = data.us,
	arr = [],
	j = 0
	;
	if(status !== 200){
		alert(data.tip);
	}else{
		data.usFC = [];
		for(var i = 0,len = us.length;i < len;i++){
			if(us[i].nature === 'FC'){
				arr[j] = i;
				data.usFC[j] = us[i];
				j++;
			}
		}
		arr = arr.reverse();
		for(var k in arr){
			data.us.splice(arr[k],1);
		}
		$('.contentMember:eq(0)').append($(template.render('usersFC',data)));
		if(data.us.length === 0){
			$('.family').hide();
		}else{			
			$('.contentMember:eq(1)').append($(template.render('users',data)));
		}
	}
};