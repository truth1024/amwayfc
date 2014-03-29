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

	template.compile('journeys21',
			'{each js21 as j}\
				<div class="chooseJourney" index="{j.istake}">\
	                <span>{j.title}<em>{if j.remainnum != null}剩余数量：{j.remainnum}{/if}{if j.title.indexOf("航海魅力") > -1}（该线路只提供18岁以上成员参与）{/if}</em></span>\
	                <font>行程景点：{j.briefinfo}</font><font>时长：{j.duration}小时</font>\
	                <div class="names" index="{j.id}">\
	                    <ul>\
	                    	{each us as u}\
                    		{if u.isadult != 2}\
                        	<li>\
                        	<input index="21" name="21_u_{u.id}" type="checkbox" value="{u.id}" />{u.name}\
                        	{if u.istake == 1 && cus.length > 0}\
                        		<i class="children"><img src="images/picCorTop.png" /></i>\
                        		<div class="children childName">\
                        		{each cus as cu }\
                        			<input index="21" name="21_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}<br>\
                        		{/each}\
                        		</div>\
                        	{/if}\
                        	</li>\
                        	{/if}\
	                    	{/each}\
	                    </ul>\
	                </div>\
	                <div class="detail"><a href="{j.infopath}">查看详情</a></div>\
	                <div style="clear:both;"></div>\
	            </div>\
			{/each}\
				<div class="chooseJourney" index="1">\
	                <span>【自行安排】<em>如选择自选行程，您将放弃当日其他自选行程</em></span>\
	                <div class="names" index="11">\
	                    <ul>\
	                        {each us as u}\
	                    		{if u.isadult != 2}\
	                        	<li>\
	                        		<input index="21" name="self_21_u_{u.id}" type="checkbox" value="{u.id}" />{u.name}\
	                        	{if u.istake == 1 && cus.length > 0}\
	                        		<i class="children"><img src="images/picCorTop.png" /></i>\
                            		<div class="children childName">\
                            		{each cus as cu }\
                            			<input index="21" name="_21_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}<br>\
                            		{/each}\
                            		</div>\
	                        	{/if}\
	                        	</li>\
	                        	{/if}\
	                    	{/each}\
	                    </ul>\
	                </div>\
	                <div style="clear:both;"></div>\
	            </div>'
	);
	
	template.compile('journeys22',
			'{each js as j index}\
			{if index > 2}\
				<div class="chooseJourney" index="{j.istake}">\
	                <span>{j.title}<em>{if j.remainnum != null}剩余数量：{j.remainnum}{/if}{if j.title.indexOf("航海魅力") > -1}（该线路只提供18岁以上成员参与）{/if}</em></span>\
	                <font>行程景点：{j.briefinfo}</font><font>时长：{j.duration}小时</font>\
	                <div class="names" index="{j.id}">\
	                    <ul>\
	                    	{each us as u}\
	                    		{if u.isadult != 2}\
	                        	<li>\
	                        		<input index="22" name="22_u_{u.id}" type="checkbox" value="{u.id}" />{u.name}\
	                        	{if u.istake == 1 && cus.length > 0}\
	                        		<i class="children"><img src="images/picCorTop.png" /></i>\
                            		<div class="children childName">\
                            		{each cus as cu }\
                            			<input index="22" name="22_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}<br>\
                            		{/each}\
                            		</div>\
	                        	{/if}\
	                        	</li>\
	                        	{/if}\
	                    	{/each}\
	                    </ul>\
	                </div>\
	                <div class="detail"><a href="{j.infopath}">查看详情</a></div>\
	                <div style="clear:both;"></div>\
	            </div>\
	        {/if}\
			{/each}\
				<div class="chooseJourney" index="1">\
	                <span>【自行安排】<em>如选择自选行程，您将放弃当日其他自选行程</em></span>\
	                <div class="names" index="12">\
	                    <ul>\
	                        {each us as u}\
	                    		{if u.isadult != 2}\
	                        	<li>\
	                        		<input index="22" name="self_22_u_{u.id}" type="checkbox" value="{u.id}" />{u.name}\
	                        	{if u.istake == 1 && cus.length > 0}\
	                        		<i class="children"><img src="images/picCorTop.png" /></i>\
                            		<div class="children childName">\
                            		{each cus as cu }\
                            			<input index="22" name="_22_cu_{cu.id}" type="checkbox" value="{cu.id}" />{cu.name}<br>\
                            		{/each}\
                            		</div>\
	                        	{/if}\
	                        	</li>\
	                        	{/if}\
	                    	{/each}\
	                    </ul>\
	                </div>\
	                <div style="clear:both;"></div>\
	            </div>'
	);

})();
