$(function(){
	//ajax请求默认设置
	$.ajaxSetup({
		statusCode:{
			404 :function(){
				failureDialog();
			},
			401: function(){
				lf('index.html');
			}
		},
		dataType : 'json'
	});
	
	$('#logout').on('click',function(){
		logout();
	});
});

function logout(){
	var url = url_basic+'login_logout';
	$.post(url,logout_callback);
};

function logout_callback(data){
	var status = data.status;
	if(status !== 200){
		alert(data.tip);
	}else{
		lf('index.html');
	}
}