$(function(){
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