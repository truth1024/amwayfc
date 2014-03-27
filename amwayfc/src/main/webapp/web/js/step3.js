$(function(){
	render();

	$('body').delegate('input[name*="_u"]','click',function(){
		var checked = !!$(this).attr('checked');
		var name = $(this).attr('name');
		console.log(checked);
		if(checked){
			if(){

			}
			$(this).siblings('.children').addClass('show');
		}else{
			$(this).siblings('.children').removeClass('show');
		}
	});
});

function render(){
	var url = url_basic+'journey_step3journeys';
	$.post(url,render_callback);
};

function render_callback(data){
	var status = data.status;
	if(status !== 200){
		alert(data.tip);
	}else{
		var index = 0;
		data.cus = [];
		data.js21 = [];
		if(data.us != null){
			for(var i = 0,len = data.us.length;i<len;i++){
				if(data.us[i].isadult === 2){
					data.cus[index] = data.us[i];
					index++;
				}
			}
		}
		index = 0;
		for(var i = 0,len = data.js.length;i<len;i++){
			if(i < 3){
				data.js21[index] = data.js[i];
				index++;
			}else{
				break;
			}
		}
		console.log(data.js);
		$(template.render('journeys21',data)).insertAfter('#journey_21');
		$(template.render('journeys22',data)).insertAfter('#journey_22');
	}
};