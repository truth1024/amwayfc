(function(){
	
	//日期格式化
	template.helper('dateFormat',function(date){
		if(date != null){
			date = date.replace('T' ,' ').replace(/-/g,"/");
			return new Date(Date.parse(date)).format('yyyy/MM/dd');
		}else{
			return '';
		}
	});
	
	//日期格式化
	template.helper('dateFormat2',function(date){
		if(date != null){			
			date = date.replace('T' ,' ').replace(/-/g,"/");
			return new Date(Date.parse(date)).format('yyyy-MM-dd');
		}else{
			return '';
		}
	});
	
	template.helper('timeFormat',function(date){
		if(date != null){			
			date = date.replace('T' ,' ').replace(/-/g,"/");
			return new Date(Date.parse(date)).format('yyyy/MM/dd hh:mm');
		}else{
			return '';
		}
	});
	
	template

	template.compile('users',
			'{each us as u index}\
				<div class="{if index%2 == 0}left{else}right{/if}">\
				{if u.isjoin == 1}\
	                <table width="430" border="0" cellspacing="0" cellpadding="0" class="table">\
	              		<tr><th scope="col" colspan="2">{u.name}</th></tr>\
	              		<tr><td width="100">性别</td><td>{u.sex}</td></tr>\
	              		<tr><td>户籍编号</td><td>{u.logincode}</td></tr>\
	              		<tr><td>店铺</td><td>{u.store}</td></tr>\
	              		<tr><td>年龄</td><td>{u.age}</td></tr>\
	              		<tr><td>出席情况</td><td>出席</td></tr>\
	              		<tr><td>签证</td><td>{u.visa}</td></tr>\
	              		<tr><td>机票</td><td>{u.airticket}</td></tr>\
	              		<tr><td>饮食禁忌</td><td>{u.diet}</td></tr>\
	              		<tr><td>纽约项目电话</td><td>{u.phone}</td></tr>\
	            	</table>\
            	{else}\
            		<table width="430" border="0" cellspacing="0" cellpadding="0" class="table grey">\
                  		<tr><th scope="col" colspan="2">{u.name}</th></tr>\
                  		<tr><td>出席情况</td><td>不出席</td></tr>\
                	</table>\
                	<span>尊敬的安利贵宾，您已选择不出席此次纽约研讨会，如有任何问题，请联系属地业务部</span>\
            	{/if}\
            	</div>\
			{/each}'
	);
	
})();
