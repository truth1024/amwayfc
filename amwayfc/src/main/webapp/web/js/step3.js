var 
people_num = {
	'21' : {adult : 0,children : 0},
	'22' : {adult : 0,children : 0}
}
;
$(function(){
	render();

	//除自行安排外的成人复选框点击事件
	$('body').delegate('input[name*="_u_"]','click',function(){
		var 
		$this = $(this),	
		index = $this.attr('index'),							//行程ID
		val = $this.val(),										//复选框值
		checked = !!$this.attr('checked'),						//复选框是否被选中标识
		selector_j_a = 'input[name*="'+index+'_u_'+val+'"]'		//行程成人复选框选择
		selector_j_c = 'input[name*="'+index+'_cu_"]'			//行程小孩复选框选择
		;
		if(checked){	//选中
			$(selector_j_a).attr('disabled',true);
			$this.attr('disabled',false);
			$this.siblings('.children').addClass('show');
		}else{			//未选中
			$(selector_j_a).attr('disabled',false);
			if(!!$this.siblings('div').children(selector_j_c).attr('checked')){
				$(selector_j_c).attr('disabled',false);
			}
			$this.siblings('div').children(selector_j_c).attr('checked',false);
			$this.siblings('.children').removeClass('show');
		}
	});

	$('body').delegate('input[name*="_cu_"]','click',function(){
		var 
		$this = $(this),	
		index = $this.attr('index'),							//行程ID
		val = $this.val(),										//复选框值
		checked = !!$this.attr('checked'),						//复选框是否被选中标识
		selector_j_c = 'input[name*="'+index+'_cu_'+val+'"]'	//行程小孩复选框选择
		;
		if(checked){	//选中
			$(selector_j_c).attr('disabled',true);
			$this.attr('disabled',false);
		}else{			//未选中
			$(selector_j_c).attr('disabled',false);
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
		people_num['21'].children = people_num['22'].children = data.cus.length;
		people_num['21'].adult = people_num['22'].adult = data.us.length-people_num['21'].children;
		// console.log(people_num);
		// console.log(data.js);
		$(template.render('journeys21',data)).insertAfter('#journey_21');
		$(template.render('journeys22',data)).insertAfter('#journey_22');
	}
};

function change_people_num(date,symbol){

};