var 
people_num = {
	'21' : {adult : 0,children : 0},
	'22' : {adult : 0,children : 0}
}
;
$(function(){
	var page = location.href.split('?')[1];
	if(!!page){
		$('.reelect:eq(1)').attr('href',page+'.html');
	}
	//渲染页面
	render();

	//成人复选框点击事件
	$('body').delegate('input[name*="_u_"]','click',function(){
		var 
		$this = $(this),	
		index = $this.attr('index'),							//行程ID
		name = $this.attr('name'),
		val = $this.val(),										//复选框值
		checked = !!$this.attr('checked'),						//复选框是否被选中标识
		selector_j_a = 'input[name*="'+index+'_u_'+val+'"]',	//行程成人复选框选择
		selector_j_c = 'input[name*="'+index+'_cu_"]',			//行程小孩复选框选择
		flag = true												//确认自行标识
		;
		if(checked){	//选中
			//判断点击的是否为自行成人复选框
			if(name.indexOf('self') > -1){		//是
				var tip_info = $('.self_tip:first').text();
				flag = confirm(tip_info);
			}
			if(flag){
				$(selector_j_a).attr('disabled',true);
				$this.attr('disabled',false);
				$this.siblings('.children').addClass('show');
				change_people_num(index,'adult',-1);
			}else{
				return flag;
			}
		}else{			//未选中
			$(selector_j_a).attr('disabled',false);
			
			var 
			$cinput = $this.parent().children('div').children(selector_j_c+':checked'),
			len = $cinput.length 		//该成人下选择的小孩数
			;
			if(len > 0){
				change_people_num(index,'children',len);
				$cinput.each(function(){
					$(selector_j_c+'[value="'+$(this).val()+'"]').attr('disabled',false);
				});
			}
			$this.siblings('div').children(selector_j_c).attr('checked',false);
			$this.siblings('.children').removeClass('show');
			change_people_num(index,'adult',1);
		}
	});

	//小孩复选框点击事件
	$('body').delegate('input[name*="_cu_"]','click',function(){
		var 
		$this = $(this),	
		index = $this.attr('index'),							//行程ID
		val = $this.val(),										//复选框值
		checked = !!$this.attr('checked'),						//复选框是否被选中标识
		selector_j_c = 'input[name*="'+index+'_cu_'+val+'"]',	//行程小孩复选框选择
		flag = true												//小孩是否可以参加行程
		;
		flag = $this.parent().parent().parent().
			parent().parent().parent().parent().
			parent().parent().attr('index') == 1 ? true : false;
		if(flag){
			if(checked){	//选中
				//其他小孩复选框置灰
				$(selector_j_c).attr('disabled',true);
				$this.attr('disabled',false);
				change_people_num(index,'children',-1);
			}else{			//未选中
				$(selector_j_c).attr('disabled',false);
				change_people_num(index,'children',1);
			}
		}else{
			alert($('#j_remind').text());
			return flag;
		}
	});

	$('body').delegate('#submit','click',function(){
		submit();
	});

	$('.cancel_button').on('click',function(){
		$(this).parent().parent().hide();
	});

});

//渲染页面函数
function render(){
	var url = url_basic+'journey_step3journeys';
	$.post(url,render_callback);
};

//渲染页面回调函数
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
		$(template.render('journeys21',data)).insertAfter('#journey_21');
		$(template.render('journeys22',data)).insertAfter('#journey_22');
		get_user_journey();
	}
};

//获取用户行程关系函数
function get_user_journey(){
	var
	url = url_basic+'user_step3userJourney'
	;
	$.post(url,get_user_journey_callback);
};

//获取用户行程关系回调函数
function get_user_journey_callback(data){
	var 
	status = data.status,
	ujs = data.ujs
	;
	if(status != 200){

	}else{
		if(ujs != null && ujs.length > 0){
			//将选择项加载到页面上
			$.each(ujs,function(i,v){
				var 
				jid = v.jid,
				date = get_date_by_jid(jid),
				uid = v.uid,
				auid = v.auid
				;
				//auid为0表示为大人
				if(auid === 0){
					$('input[name*="'+date+'_u_'+uid+'"]').attr('disabled',true);
					$('div[index="'+jid+'"]').find('input[name*="'+date+'_u_'+uid+'"]')
					.attr({'disabled':false,'checked':true});
					$('div[index="'+jid+'"]').find('input[name*="'+date+'_u_'+uid+'"]')
					.nextAll('.children').addClass('show');
				}else{
					$('input[name*="'+date+'_cu_'+uid+'"]').attr('disabled',true);
					$('div[index="'+jid+'"]').find('input[name*="'+date+'_u_'+auid+'"]')
					.parent().find('input[name*="'+date+'_cu_'+uid+'"]')
					.attr({'disabled':false,'checked':true});
				}
			});
			people_num['21'].adult = people_num['21'].children =
			people_num['22'].adult = people_num['22'].children = 0;
		}
	}
};

//提交
function submit(){
	var 
	url = url_basic+'journey_step3insert',	//请求路径
	par = {ujstr:undefined},				//请求参数
	flag = true,							//是否可以提交标识
	tip = undefined							//不可提交提示
	;
	//校验是否可以提交
	for(var key in people_num){
		//当people_num的人数值都为0时，才可提交
		if(people_num[key].adult != 0){
			flag = false;
			tip = '选择不完全,请仔细检查';
			break;
		}
		if(people_num[key].children != 0){
			flag = false;
			tip = '选择不完全,请仔细检查';
			break;
		}
	}

	if(flag){//校验通过
		par.ujstr = splice_jid_uid_cid();
		$.post(url,par,submit_callback);
	}else{
		$('.boxTips span').text(tip);
		$('.boxTips').css('top',(getScrollTop()+240)+'px').show();
		return flag;
	}
};

//提交回调函数
function submit_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		alert(data.tip);
	}else{
		lf('step04.html');
	}
};

/**
 * [change_people_num 更改people_num内的人数值]
 * @param  {[string]} date   [行程日 可填写值：'21' '22']
 * @param  {[string]} aorc   [更改成人数还是小孩数 可填写值： 'adult' 'children']
 * @param  {[int]} symbol ['增加人数还是减少人数' 可填写值： -1 1]
 * @return {[]}        [无返回值]
 */
function change_people_num(date,aorc,symbol){
	people_num[date][aorc] = people_num[date][aorc]+symbol;
};

//拼接用户行程字符串
function splice_jid_uid_cid(){
	var 
	ujstr = ''
	;
	$('.chooseJourney .names').each(function(){
		var 
		$this = $(this),
		$input = $this.find('input[name*="_u_"]:checked'),	//成人选中的复选框
		jid = $this.attr('index'),							//行程ID
		uidstr = '',										//成人ID字符串
		cuidstr = '0&'										//小孩ID字符串
		;
		
		$input.each(function(){
			uidstr = jid+'_'+$(this).val()+'_';
			var $cinput = $(this).parent().children('div').children('input:checked');
			if($cinput.length == 0){
				cuidstr = '0&';
				ujstr += uidstr+cuidstr;
			}else{
				cuidstr = '';
				$cinput.each(function(){
					cuidstr += $(this).val()+'&';
				});
				ujstr += uidstr+cuidstr;
			}
			ujstr = ujstr.substring(0,ujstr.length-1)+'#';
		});
	});
	return ujstr.substring(0,ujstr.length-1);
};

//根据jid获取日期
function get_date_by_jid(jid){
	if(jid === 6 || jid === 7 || jid === 8 || jid === 11){
		return '21';
	}else{
		return '22';
	}
}