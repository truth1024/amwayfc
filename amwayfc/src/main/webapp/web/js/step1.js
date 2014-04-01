$(function(){
	get_remainnum();
});

//获取行程上限和剩余人数函数
function get_remainnum(){
	var 
	url = url_basic+'journey_step3remainnum'
	;
	$.post(url,get_remainnum_callback);
};

//获取行程上限和剩余人数回调函数
function get_remainnum_callback(data){
	var 
	status = data.status
	;
	if(status === 200){
		$.each(data.js,function(i,v){
			$('#journey_'+v.id+' em').text(v.num);
			$('#journey_'+v.id+' td:eq(3)').text(v.remainnum);
		});
	}
};

//显示对应的行程详情
 function dis(num){
    $('.content').hide().eq(num).show();
};