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
	
	template.helper('kickbackTransform',function(kickback){
		if(kickback == null){
			return '';
		}else{
			return (kickback.indexOf('%') > 0 ? kickback : kickback+'元');
		}
	});
	
	template.helper('integralTypeTransfrom',function(typeId){
		return integral_type[typeId-1];
	});
	
	template.helper('adminTypeTransfrom',function(typeId){
		return admin_type[typeId-1];
	});
	
	template.helper('getIntegral',function(t){
		var kickback;
		if(t.cuser.kickback.indexOf('%') > -1){
			kickback = parseInt(t.cuser.kickback.substring(0,t.cuser.kickback.length-1));
			return t.acceptMoney*kickback/100;
		}else{
			return t.acceptMoney;
		}
	});
	
	//用户管理-待审核
	template.compile('users',
			'{each data as u}\
				<tr id="user_{u.id}">\
					<td>{u.id}</td>\
					<td>{u.name}</td>\
					<td>{u.phone}</td>\
					<td>{u.email}</td>\
					<td>{u.typeName}</td>\
					<td>{u.city}</td>\
					<td style="line-height: 6px;">\
					{if isSuper > 1}\
						<img alt="" onclick="passDialog({u.id})" src="image/pass.png" style="margin-right:10px;">\
						<img alt="" onclick="refuseDialog({u.id})" src="image/refuse.png">\
					{else}\
						----\
					{/if}\
					</td>\
					<td>{timeFormat u.createDate}</td>\
				</tr>\
			{/each}'
	);
	
	//用户管理-合同信息添加
	template.compile('user_contract',
			'<div class="edit_form">\
				<div><input name="contract.uid" type="hidden" value="{uid}"/></div>\
				<div><input name="user.id" type="hidden" value="{uid}"/></div>\
				<div><input name="contract.contractid" type="text" value="{input.contractid}"/></div>\
				<div><input name="contract.firstParty" type="text" value="{input.firstParty}"/></div>\
				<div><input name="contract.certificate" type="text" value="{input.certificate}"/></div>\
				<div><input name="contract.contacts" type="text" value="{input.contacts}"/></div>\
				<div><input name="contract.telephone" type="text" value="{input.telephone}"/></div>\
				<div><input name="contract.address" type="text" value="{input.address}"/></div>\
				<div><input name="contract.email" type="text" value="{input.email}"/></div>\
				<div><input id="sign_date" name="contract.signDate" type="text" value="{input.signDate}"/></div>\
				<div style="position:relative;"><input type="text" name="user.kickback" value="{input.kickback}"><span id="edit_symbol" title="点击切换返点类型">元</span></div>\
				<div><input name="user.basicIntegral" type="text" value="{input.basicIntegral}"/></div>\
			</div>'
	);
	
	//用户管理-已审核
	template.compile('all_user',
			'{each data as u}\
					<tr id="all_{u.id}">\
					<td>{u.id}</td>\
					<td>{u.credit}点</td>\
					<td>{u.name}</td>\
					<td>{u.phone}</td>\
					<td>{u.typeName}</td>\
					<td>{u.city}</td>\
					<td>{kickbackTransform u.kickback}</td>\
					<td>{u.releaseNum}/{u.releaseSNum}</td>\
					<td>{u.parNum}/{u.parSNum}</td>\
					<td>{u.basicIntegral}</td>\
					<td>{u.integral}</td>\
					<td>\
					{if isSuper > 1}\
						<a href="javascript:editDialog({u.id});">编辑</a>\
						<a href="javascript:integralDialog({u.id},1,{u.basicIntegral},{u.integral});">充值</a>\
						<a href="javascript:integralDialog({u.id},3,{u.basicIntegral},{u.integral});">提现</a>\
					{else}\
						<a href="javascript:editBasicDialog({u.id});">编辑</a>\
					{/if}\
					{if u.status == 3}\
						<a href="javascript:userStatus({u.id},4);">锁定</a>\
					{else if u.status == 4}\
						<a href="javascript:userStatus({u.id},3);">解锁</a>\
					{/if}\
					</td>\
				</tr>\
			{/each}'
	);
	
	//编辑选择
	template.compile('edit',
			'<div style="text-align: center;">\
				<a style="margin-right:40px;" href="javascript:editBasicDialog({uid});">基本信息编辑</a>\
				<a href="javascript:editContractDialog({uid});">合同信息编辑</a>\
			</div>'
	);
	
	//用户管理-基本信息编辑
	template.compile('user',
			'<div class="edit_form">\
				<div><input type="hidden" name="user.id" value="{user.id}"></div>\
				<div><input type="text" title="{input.name}" name="user.name" value="{user.name}"></div>\
				<div>\
					<select name="user.type" title="{input.type}">\
					{each types as t}\
						<option value="{t.id}"\
						{if t.id == user.type}\
							 selected=selected\
						{/if}\
						>{t.name}</option>\
					{/each}\
					</select>\
				</div>\
				{if isSuper > 1}\
				<div><input type="text" title="{input.email}" name="user.email"  value="{user.email}"></div>\
				{/if}\
				<div><input type="text" title="{input.phone}" name="user.phone"  value="{user.phone}"></div>\
				<div><input type="text" title="{input.city}" name="user.city"  value="{user.city}"></div>\
			</div>'
	);
	
	//用户管理-合同信息编辑
	template.compile('edit_contract',
			'<div class="edit_form">\
				<div><input name="user.id" type="hidden" value="{uid}"/></div>\
				<div><input name="contract.uid" type="hidden" value="{uid}"/></div>\
				<div><input name="contract.contractid" type="text" title="{input.contractid}" value="{contractid}"/></div>\
				<div><input name="contract.firstParty" type="text" title="{input.firstParty}" value="{firstParty}"/></div>\
				<div><input name="contract.certificate" type="text" title="{input.certificate}" value="{certificate}"/></div>\
				<div><input name="contract.contacts" type="text" title="{input.contacts}" value="{contacts}"/></div>\
				<div><input name="contract.telephone" type="text" title="{input.telephone}" value="{telephone}"/></div>\
				<div><input name="contract.address" type="text" title="{input.address}" value="{address}"/></div>\
				<div><input name="contract.email" type="text" title="{input.email}" value="{email}"/></div>\
				<div><input id="sign_date" name="contract.signDate" type="text" title="{input.signDate}" value="{dateFormat2 signDate}"/></div>\
				<div style="position:relative;"><input type="text" name="user.kickback" title="{input.kickback}" value="{if user.kickback.indexOf("%") > -1}{user.kickback.split("%")[0]}{else}{user.kickback}{/if}"><span id="edit_symbol" title="点击切换返点类型">{if user.kickback.indexOf("%") > -1}%{else}元{/if}</span></div>\
				<div><input name="basicIntegral" disabled type="text" title="{input.basicIntegral}" value="{user.basicIntegral}"/></div>\
			</div>'
	);
	
	template.compile('appeals',
			'{each data as m}\
				<tr id="appeal_{m.tid}">\
					<td><a href="javascript:taskDetail({m.tid});">{m.task.orderid}</a></td>\
					<td>{m.fromUser.name}/{m.fromUser.phone}</td>\
					<td>{m.user.name}/{m.user.phone}</td>\
					{if m.type != 4}\
						<td>{m.info}</td>\
						<td style="line-height: 6px;">\
							{if isSuper > 1}\
								<img alt="" onclick="appealDialog({m.tid})" src="image/pass.png">\
							{else}\
								----\
							{/if}\
						</td>\
						<td>{timeFormat m.sendDate}</td>\
					{else}\
						<td>{timeFormat m.sendDate}</td>\
						<td>{m.operatorName}</td>\
						<td>{m.operateinfo}</td>\
						<td>{timeFormat m.operatedate}</td>\
					{/if}\
				</tr>\
			{/each}'
	);
	
	template.compile('process_task',
			'{each data as t}\
				<tr id="process_task_{t.id}">\
					<td>{t.orderid}<a class="task_detail_a" href="javascript:taskDetail({t.id});">详情</a></td>\
					<td>{t.address}</td>\
					<td>{t.typeName}</td>\
					<td>{t.budget}</td>\
					<td><a href="javascript:userInfo({t.user.id});">{t.user.name}</a></td>\
					{if t.client != null}\
					<td><a href="javascript:clientInfo({t.client.id});">{t.client.name}</a></td>\
					{else}\
					<td>&nbsp;</td>\
					{/if}\
					<td>{dateFormat t.weddingDay}</td>\
					<td>{t.participation}</td>\
					<td><a class="close_task_a" href="javascript:closeDialog({t.id});">强制关闭</a></td>\
					<td>{timeFormat t.createDate}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('complete_task',
			'{each data as t}\
				<tr id="complete_task_{t.id}">\
					<td>{t.orderid}<a class="task_detail_a" href="javascript:taskDetail({t.id});">详情</a></td>\
					<td>{t.address}</td>\
					<td>{t.typeName}</td>\
					<td>{t.budget}</td>\
					<td><a href="javascript:userInfo({t.user.id});">{t.user.name}</a></td>\
					{if t.client != null}\
					<td><a href="javascript:clientInfo({t.client.id});">{t.client.name}</a></td>\
					{else}\
					<td>&nbsp;</td>\
					{/if}\
					<td>{dateFormat t.weddingDay}</td>\
					<td><a href="javascript:userInfo({t.cuser.id});">{t.cuser.name}</a></td>\
					<td>{t.acceptMoney}</td>\
					<td>{getIntegral t}</td>\
					<td>{timeFormat t.successDate}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('close_task',
			'{each data as t}\
				<tr id="close_task_{t.id}">\
					<td>{t.orderid}<a class="task_detail_a" href="javascript:taskDetail({t.id});">详情</a></td>\
					<td>{t.address}</td>\
					<td>{t.typeName}</td>\
					<td>{t.budget}</td>\
					<td><a href="javascript:userInfo({t.user.id});">{t.user.name}</a></td>\
					{if t.client != null}\
					<td><a href="javascript:clientInfo({t.client.id});">{t.client.name}</a></td>\
					{else}\
					<td>&nbsp;</td>\
					{/if}\
					<td>{dateFormat t.weddingDay}</td>\
					<td>{t.participation}</td>\
					<td>\
					{if t.closeUser == null || t.closeUser.name == ""}\
						系统\
					{else if t.closeUser.id == 1}\
						{t.closeUser.name}\
					{else}\
						{t.closeUser.name}/{t.closeUser.phone}</td>\
					{/if}\
					<td>\
					{if t.closeInfo == "" || t.closeInfo == null}\
						----\
					{else}\
						{t.closeInfo}</td>\
					{/if}\
					<td>{timeFormat t.closeDate}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('task_detail',
			'<div>\
				<div>{orderid}</div>\
				<div>{title}</div>\
				<div>{address}</div>\
				<div>婚期：{dateFormat weddingDay}</div>\
				<div>预算：{budget}</div>\
				<div>客户姓名：{client.name}</div>\
				<div>联系方式：{client.phone}</div>\
				<div>备注：{note}</div>\
			</div>'
	);
	
	template.compile('user_detail',
			'<div>\
				<div>姓名：{name}</div>\
				<div>手机：{phone}</div>\
				<div>城市：{city}</div>\
				<div>类型：{typeName}</div>\
				<div>信誉：{credit}</div>\
				<div>返点值：{kickbackTransform kickback}</div>\
			</div>'
	);
	
	template.compile('client_detail',
			'<div>\
				<div>姓名：{name}</div>\
				<div>手机：{phone}</div>\
				<div>QQ：{qq}</div>\
				<div>邮箱：{weChat}</div>\
			</div>'
	);
	
	template.compile('integral_record',
			'{each data as i}\
				<tr id="integral_record_{i.id}">\
					<td>{i.username}</td>\
					<td>{timeFormat i.operatedate}</td>\
					<td>{i.integral}</td>\
					<td>{i.currentintegral}</td>\
					<td>{integralTypeTransfrom i.type}</td>\
					<td>{i.orderid}</td>\
					<td>{if i.operatorName == "" || i.operatorName == null}系统{else}{i.operatorName}{/if}</td>\
					<td>{i.note}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('permissions',
			'{each data as m}\
				<tr id="permission_{m.id}">\
					<td>{m.id}</td>\
					<td>{m.name}</td>\
					<td>{adminTypeTransfrom m.type}</td>\
					<td>{timeFormat m.createDate}</td>\
					<td>{m.createName}</td>\
					<td>\
					{if isSuper == 3 || (isSuper == 2 && m.id != 1)}\
						<a href="javascript:editAdminDialog({m.id});">编辑信息</a>\
						<a href="javascript:resetPassword(\'{m.email}\',{m.id});">重置密码</a>\
						{if m.type != 3}\
						<a href="javascript:removeAdminDialog(\'{m.name}\',{m.id});">消除权限</a>\
						{/if}\
					{/if}\
						<a href="javascript:record(\'#login_record_sheet\',{m.id});">登录记录</a>\
						<a href="javascript:record(\'#operate_record_sheet\',{m.id});">操作记录</a>\
					</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('add_admin',
			'<div id="add_admin_form" class="edit_form">\
				<div><input type="text" name="manager.email" value="{input.email}"/></div>\
				<div><input type="text" name="manager.name" value="{input.name}"/></div>\
				<div>\
					<input style="margin-left:45px;" type="radio" name="manager.type" value="1" checked/>普管\
					<input type="radio" name="manager.type" value="2"/>超管\
				</div>\
			</div>'
	);
	
	template.compile('edit_admin',
			'<div id="edit_admin_form" class="edit_form">\
				<div><input type="hidden" name="manager.id" value="{id}"/></div>\
				<div><input type="text" title="{input.name}" name="manager.name" value="{name}"/></div>\
				<div>\
					<input style="margin-left:45px;" type="radio" name="manager.type" value="1" {if type == 1} checked{/if}/>普管\
					<input type="radio" name="manager.type" value="{if type != 3}2{else}3{/if}" {if type != 1}checked{/if}/>超管\
				</div>\
			</div>'
	);
	
	template.compile('update_password',
			'<div id="update_password_form" class="edit_form">\
				<div>{input.oldPassword}：<input type="password" title="{input.oldPassword}" name="password" value=""/></div>\
				<div>&nbsp;&nbsp;&nbsp;{input.newPassword}：<input type="password" title="{input.newPassword}" name="manager.password" value=""/></div>\
				<div>{input.confirmPassword}：<input type="password" title="{input.confirmPassword}" name="confirmPassword" value=""/></div>\
			</div>'
	);
	
	template.compile('login_record',
			'{each data as l}\
				<tr id="login_record_{l.id}">\
					<td>{l.lid}</td>\
					<td>{lname}</td>\
					<td>{timeFormat l.logindate}</td>\
					<td>{l.ip}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('operate_record',
			'{each data as l}\
				<tr id="operate_record_{l.id}">\
					<td>{l.lid}</td>\
					<td>{m.name}</td>\
					<td>{adminTypeTransfrom m.type}</td>\
					<td>{timeFormat l.operatedate}</td>\
					<td>{l.ip}</td>\
					<td>{l.info}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('history',
			'{each data as m}\
				<tr id="message_{m.id}">\
					<td class="td_text_left">{m.info}</td>\
					<td>{timeFormat m.sendDate}</td>\
				</tr>\
			{/each}'
	);
	
	template.compile('type',
			'{each types as t}\
				<option value="{t.id}">{t.name}</option>\
			{/each}'
	);
	
})();
