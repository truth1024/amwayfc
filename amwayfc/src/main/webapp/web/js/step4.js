$(function(){
	render();
	
	$('#confirm').on('click',function(){
		confirm_journey();
	});
	
	$('#delete').on('click',function(){
		delete_journey();
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