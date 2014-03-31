// JavaScript Document
$(document).ready(function(){
	$('h4').on('click',function(){
		var $content = $(this).next();
		if($content.css('display') === 'block'){
			$content.slideUp();
		}else{
			$content.slideDown();
		}
	});

	$('.close').on('click',function(){
		$(this).parent().slideUp();
	});

	var num = location.href.split('#')[1];
    if(!!num){
    	$('h4').eq(num).next().show();
    }
});

