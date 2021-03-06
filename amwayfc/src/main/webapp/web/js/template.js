(function(){
	
	var 
	userFC_num = 0,
	user_num = 0
	;

	//日期格式化
	template.helper('dateFormat',function(date){
		if(date != null){
			date = date.replace('T' ,' ').replace(/-/g,"/");
			return new Date(Date.parse(date)).format('M月dd日');
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

	//是否可以参加危险活动标识
	template.helper('is_can',function(age){
		return age > 60 ? 2 : 1;
	});
	
	template.compile('usersFC',
		'{each usFC as u index}\
			<div class="{if index%2 == 0}left{else}right{/if}">\
			{if u.isjoin == 1}\
                <table width="430" border="0" cellspacing="0" cellpadding="0" class="table">\
              		<tr><th scope="col" colspan="2">{u.name}</th></tr>\
              		<tr><td width="100">性别</td><td>{u.sex}</td></tr>\
              		<tr><td>户籍编号</td><td>{u.logincode}</td></tr>\
              		<tr><td>店铺</td><td>{u.store}</td></tr>\
              		<tr><td>年龄</td><td>{u.age}</td></tr>\
              		<tr><td>家属关系</td><td>{u.relation}</td></tr>\
              		<tr><td>出席情况</td><td>出席</td></tr>\
              		<tr><td>签证</td><td>{u.visa}</td></tr>\
              		<tr><td>机票</td><td>{u.airticket}</td></tr>\
              		<tr><td>饮食禁忌</td><td>{u.diet}</td></tr>\
              		<tr><td>电话</td><td>{u.phone}</td></tr>\
            	</table>\
        	{else}\
        		<table width="430" border="0" cellspacing="0" cellpadding="0" class="table grey">\
              		<tr><th scope="col" colspan="2">{u.name}</th></tr>\
              		<tr><td>出席情况</td><td>不出席</td></tr>\
            	</table>\
            	<span class="tips">尊敬的安利贵宾，您已选择不出席此次纽约研讨会，如有任何问题，请联系属地业务部</span>\
        	{/if}\
        	</div>\
		{/each}'
	);

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
              		<tr><td>家属关系</td><td>{u.relation}</td></tr>\
              		<tr><td>出席情况</td><td>出席</td></tr>\
              		<tr><td>签证</td><td>{u.visa}</td></tr>\
              		<tr><td>机票</td><td>{u.airticket}</td></tr>\
              		<tr><td>饮食禁忌</td><td>{u.diet}</td></tr>\
              		<tr><td>电话</td><td>{u.phone}</td></tr>\
            	</table>\
        	{else}\
        		<table width="430" border="0" cellspacing="0" cellpadding="0" class="table grey">\
              		<tr><th scope="col" colspan="2">{u.name}</th></tr>\
              		<tr><td>出席情况</td><td>不出席</td></tr>\
            	</table>\
            	<span class="tips">尊敬的安利贵宾，您已选择不出席此次纽约研讨会，如有任何问题，请联系属地业务部</span>\
        	{/if}\
        	</div>\
		{/each}'
	);

	template.compile('step4users',
		'{each us as u index}\
		<div class="{if index%2 == 0}left{else}right{/if}" style="height:360px;">\
        	<table width="430" border="0" cellspacing="0" cellpadding="0" class="table">\
              	<tr><th scope="col" colspan="2">{u.name}</th></tr>\
              	<tr><td width="100" bgcolor="#efefef">日期</td><td bgcolor="#efefef">行程</td></tr>\
              	<tr><td>7月18日</td><td>法国尼斯</td></tr>\
              	<tr><td>7月19日</td><td>法国漫步戛纳</td></tr>\
              	<tr><td>7月20日</td><td>法国恋恋薰衣草</td></tr>\
          	{each u.js as j}\
              	<tr><td>{dateFormat j.start}</td><td>{j.title}</td></tr>\
        	{/each}\
              	<tr><td>7月23日</td><td>西班牙巴塞罗那</td></tr>\
            </table>\
        </div>\
		{/each}'
	);
	
	template.compile('journeys21',
		'{each js21 as j }\
		<div class="chooseJourney" index="{j.istake}">\
		{if j.istake == 2}\
			<div id="j_remind" class="remind">“激情.马赛—航海魅力”线路消耗体力较大，出于健康考量，只面向18岁以上，60岁以下的家属开放。</div>\
		{/if}\
            <table width="924" border="0" cellspacing="0" cellpadding="0" class="tableTravel table01">\
	          	<tr>\
		            <th scope="col" width="160">行程名称</th>\
		            <th scope="col" width="300">行程景点</th>\
		            <th scope="col" width="80">时长</th>\
		            <th scope="col" width="80">适合人群</th>\
		            <th scope="col" width="80">体力指数</th>\
		            <th scope="col" width="80">剩余名额</th>\
		            <th scope="col">详情</th>\
	          	</tr>\
	          	<tr>\
		            <td>{j.title}</td>\
		            <td>{j.briefinfo}</td>\
		            <td>{j.duration}小时</td>\
		            <td>{j.suit}</td>\
		            <td>{j.power}</td>\
		            <td id="remainnum_{j.id}">{j.remainnum == null ? "无限制" : j.remainnum}</td>\
		            <td><a href="{j.infopath}" target="_blank">查看详情</a></td>\
		    	</tr>\
	          	<tr>\
	            	<td colspan="7">\
		                <div class="names" index="{j.id}">\
			                <font>请选择出行人员：</font>\
			                <ul>\
			                {each us as u}\
                    		{if u.isadult != 2}\
	                        	<li>\
	                        		<input index="21" name="21_u_{u.id}" age="{is_can u.age}" type="checkbox" value="{u.id}" />{u.name}\
	                        	{if u.istake == 1 && cus.length > 0}\
	                        		<i class="children"><img src="/web/images/picCorTop.png" /></i>\
	                        		<div class="children childName">\
	                        			随行子女<br />\
	                        		{each cus as cu }\
	                        			<div><input index="21" name="21_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}</div>\
	                        		{/each}\
	                        		</div>\
	                        	{/if}\
	                        	</li>\
                        	{/if}\
	                    	{/each}\
			                </ul>\
			                <div style="clear:both"></div>\
		            	</div>\
	        		</td>\
	          	</tr>\
        	</table>\
            <div style="clear:both;"></div>\
        </div>\
		{/each}'
	);

	template.compile('journeys22',
		'{each js as j index}\
		{if index > 2}\
		<div class="chooseJourney" index="{j.istake}">\
            <table width="924" border="0" cellspacing="0" cellpadding="0" class="tableTravel table02">\
	          	<tr>\
		            <th scope="col" width="160">行程名称</th>\
		            <th scope="col" width="300">行程景点</th>\
		            <th scope="col" width="80">时长</th>\
		            <th scope="col" width="80">适合人群</th>\
		            <th scope="col" width="80">体力指数</th>\
		            <th scope="col" width="80">剩余名额</th>\
		            <th scope="col">详情</th>\
	          	</tr>\
	          	<tr>\
		            <td>{j.title}</td>\
		            <td>{j.briefinfo}</td>\
		            <td>{j.duration}小时</td>\
		            <td>{j.suit}</td>\
		            <td>{j.power}</td>\
		            <td>{j.remainnum == null ? "无限制" : j.remainnum}</td>\
		            <td><a href="{j.infopath}" target="_blank">查看详情</a></td>\
		    	</tr>\
	          	<tr>\
	            	<td colspan="7">\
		                <div class="names" index="{j.id}">\
			                <font>请选择出行人员：</font>\
			                <ul>\
			                {each us as u}\
                    		{if u.isadult != 2}\
	                        	<li>\
	                        		<input index="22" name="22_u_{u.id}" age="{is_can u.age}" type="checkbox" value="{u.id}" />{u.name}\
	                        	{if u.istake == 1 && cus.length > 0}\
	                        		<i class="children"><img src="/web/images/picCorTop.png" /></i>\
	                        		<div class="children childName">\
	                        			随行子女<br />\
	                        		{each cus as cu }\
	                        			<div><input index="22" name="22_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}</div>\
	                        		{/each}\
	                        		</div>\
	                        	{/if}\
	                        	</li>\
                        	{/if}\
	                    	{/each}\
			                </ul>\
			                <div style="clear:both"></div>\
		            	</div>\
	        		</td>\
	          	</tr>\
        	</table>\
            <div style="clear:both;"></div>\
        </div>\
        {/if}\
		{/each}'
	);

})();
