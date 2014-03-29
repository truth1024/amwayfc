$(function(){
	render();
});

function render(){
	var
	url = url_basic+'user_step4users'
	;
	$.post(url,render_callback);
};

function render_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){

	}else{
		if(data.us[0].js.length === 0){
			lf('step01.html');
		}else{
			$('.contentMember').append($(template.render('step4users',data)));
		}
	}
};