$(function(){
	$i=0;
	$('#sitesearch').bind('keyup', 'keypress', function(e){
		var inputString=$(this).val();
		var minlength = 3;
		var code = (e.keyCode ? e.keyCode : e.which);
		if(code=='38' || code=='40' || code=='13'){ 
			$li=$('#autoSuggestionsList').find('li');
			$last=$li.size();
			if(code=='13') { 
				if($('#autoSuggestionsList li.current').size()>0) {
					window.location.href=($('.current a').attr('href'));}
				else return false;

			}
			if(code=='38'){
				$i--;
				$c=$('#autoSuggestionsList li.current');
				$li.removeClass('current');
				if($i<0) {$i=$last-1;}
				$li.eq($i).addClass('current');
				
			}
			else if(code=='40'){
				$i++;
				$c=$('#autoSuggestionsList li.current')
				$li.removeClass('current');
				if($i>=$last) {$i=0;}
				$li.eq($i).addClass('current');
				
			}
		}
		else if (inputString.length >= minlength ){
			//dnt use static urls
		$('#suggestions').show();
		$('#autoSuggestionsList').empty().html('<li><a>Loading...</a></li>');
		$("input[name*='keyword']" ).val($('#sitesearch').val());
		$.post(baseurl+"sitesearch/ajax/",$("#frm_sitesearch").serialize() , function(data){
			$i=0;
			if(data.length >0) {
				$('#suggestions').show();
				$('#autoSuggestionsList').html(data);
				$('.searchhrbar:last').hide();
				$('#autoSuggestionsList li').removeClass('current');
				Cufon.refresh();
			}
		});
		}
		else{
			$('#suggestions').hide();
		}	
	});
	
	$("#frm_sitesearch").submit(function(){
			return false;
	});
});

