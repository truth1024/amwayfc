
//获取当前使用语音路径
function get_lang_path(){
	var path = location.href.split('/web')[1];
	return (path !== undefined && path.indexOf('/s/') < 0) ? 'c/' : 's/';
};

//跳转页面
function lf(page_name){
	var lang_path = get_lang_path();
	location.href = url_basic+lang_path+page_name;
};