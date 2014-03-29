var 
people_num = {
	'21' : {adult : 0,children : 0},
	'22' : {adult : 0,children : 0}
}
;
$(function(){
	render();

	//成人复选框点击事件
	$('body').delegate('input[name*="_u_"]','click',function(){
		var 
		$this = $(this),	
		index = $this.attr('index'),							//行程ID
		name = $this.attr('name'),
		val = $this.val(),										//复选框值
		checked = !!$this.attr('checked'),						//复选框是否被选中标识
		selector_j_a = 'input[name*="'+index+'_u_'+val+'"]'		//行程成人复选框选择
		selector_j_c = 'input[name*="'+index+'_cu_"]'			//行程小孩复选框选择
		flag = true												//确认自行标识
		;
		if(checked){	//选中
			//判断点击的是否为自行成人复选框
			if(name.indexOf('self') > -1){		//是
				var tip_info = $this.parent().parent().parent().prev().children('em').text();
				console.log(tip_info);
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
		selector_j_c = 'input[name*="'+index+'_cu_'+val+'"]'	//行程小孩复选框选择
		flag = true												//小孩是否可以参加行程
		;
		var flag = $this.parent().parent().parent().parent().parent().attr('index') == 1 ? true : false;
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
			alert('该线路只提供18岁以上成员参与');
			return flag;
		}
	});

	$('body').delegate('#submit','click',function(){
		submit();
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
		console.log(people_num);
		// console.log(data.js21);
		$(template.render('journeys21',data)).insertAfter('#journey_21');
		$(template.render('journeys22',data)).insertAfter('#journey_22');
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
			break;
		}
		if(people_num[key].children != 0){
			flag = false;
			break;
		}
	}

	if(flag){//校验通过
		console.log(splice_jid_uid_cid());
		par.ujstr = splice_jid_uid_cid();
		$.post(url,par,submit_callback);
	}else{
		alert(tip);
		return flag;
	}
};

function submit_callback(data){
	var 
	status = data.status
	;
	if(status !== 200){
		alert(data.tip);
	}else{

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
	console.log(people_num);
};

//拼接用户行程字符串
function splice_jid_uid_cid(){
	var 
	i = 0,
	ujstr = '',
	tempstr = ''							//临时字符串
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
			console.log(uidstr,$cinput.length);
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
function exportPDF(){
var filePath = getExportPath();
	if(filePath != null) {
		try {
			var word = new ActiveXObject("Word.Application");
			var doc = word.Documents.Add("", 0, 1);
			var range = doc.Range(0, 1);
			var sel = document.body.createTextRange();
			try {
				sel.moveToElementText(content);
			} catch (notE) {
				alert("导出数据失败，没有数据可以导出。");
				window.close();
				return;
			}
			sel.select();
			sel.execCommand("Copy");
			range.Paste();
			//word.Application.Visible = true;// 控制word窗口是否显示
			doc.saveAs(filePath + "/导出测试.pdf", 17);// 保存为pdf格式
			alert("导出成功");
		} catch (e) {
			alert("导出数据失败，需要在客户机器安装Microsoft Office Word 2007以上版本，将当前站点加入信任站点，允许在IE中运行ActiveX控件。");
		} finally {
			try {
				word.quit();// 关闭word窗口
			} catch (ex) {}
		}
	}
};

function getExportPath() {
	var shell = new ActiveXObject("Shell.Application");
	var folder = shell.BrowseForFolder(0, '请选择存储目录', 0x0040, 0x11); 
	var filePath;
	if(folder != null) {
		 filePath = folder.Items().Item().Path;
	}
	return filePath;
};