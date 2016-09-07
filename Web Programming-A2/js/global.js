// JavaScript Document//clear text box on click
if (baseurl=='' || baseurl== null || baseurl== 'undefined') {
	var baseurl = window.location.protocol + '//' + window.location.host +'/2/reading';
}

function clearText(field){

	if (field.defaultValue == field.value) field.value = '';
	else if (field.value == '') field.value = field.defaultValue;

}


 $(function(){
	$('.tab').click(function(e) {
		var $tabs = $(".tab").parent("li");
		var $curr_tab = $(this).parent("li");
		if($(this).attr("href")=="member_register")
			location.href = "member_register";
		if($(this).attr("href")=="./member_homepage")
			location.href = "/member_home";
		$('.member_cont').empty();
		$('.member_cont').html("<div align='center' style='height:500px; width:600px;padding-top:250px;'><img src='"+cloudurl+"images/spinner.gif'></div>");
		$.ajax({
		  type: 'GET',
		  url: $(this).attr("href"),
		  data: '',
		  success: function(html) {
			if(html==""){
				location.href = "./home";
			}
			else{
			$tabs.attr("class","");
			$curr_tab.attr("class","active");
			$('.member_cont').empty();
			$('.member_cont').html(html);
			}
		  }
		}); 
		e.preventDefault();
		return false;
	  });
	  // CUFON
	Cufon.replace('.fut-font li a', {  hover: true });
	Cufon.replace('h1')('h2')('h3')('h4')('.etix-movie-info');
	Cufon.replace('a.fut-nav-shadow', { hover: true, textShadow: '#312f2f 1px 1px'});
	$('#refresh').live('click',function(){
		$(".refresh img").attr("src",baseurl+"vcode?"+((new Date()).getTime()));
		$("#captcha").val('');
	});
});	
	
$(document).ready(function(){
	
	$('ul.nav').superfish({delay: 400}); 
	$('.styled').selectbox();

	// Carousel
	
	var selectors = [
		'.movieWrap .carouselWrap',
		'.movieWrap .carouselWrap2'
	];

	var selectorCount = selectors.length;

	
	el = $('.movieWrap .carouselWrap');
	if(el.size() > 0 && $('ul li', el).size() > 0) {
		el.jCarouselLite({
			btnNext: ".next",
			btnPrev: ".prev",
			circular: true,
			visible:1
		});
	}
	el = $('.movieWrap .carouselWrap2');
	if(el.size() > 0 && $('ul li', el).size() > 0) {
		el.jCarouselLite({
			btnNext: ".next2",
			btnPrev: ".prev2",
			circular: true,
			visible:1
		});
	}
	
	
	if($('.trailer-thumb .carouselWrap').size() && $('ul li', el).size() > 3) {
		$('.trailer-thumb .carouselWrap').jCarouselLite({
			btnNext: ".next",
			btnPrev: ".prev",
			circular: true,
			visible: 3
		})
	}

	if($('.movieWrap5 .carouselWrap').size()) {
		$('.movieWrap5 .carouselWrap').jCarouselLite({
			btnNext: ".next",
			btnPrev: ".prev",
			circular: true,

			visible: 5
		});
	}

	// Features Tabs
	// Removed, as the "Features" are now on separate pages.
	var tab;
	   $(".theatre-details .feat-tab_content").hide(); //Hide all content
	   $(".theatre-details ul.features-tab li:first").addClass("active").show(); //Activate first tab
	   $(".theatre-details .cinema-features-cont > div:first").show(); //Show first tab content
	   $(".theatre-details ul.features-tab li").click(function() {
		   var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
		   if(!$(activeTab).is(':visible')) {
			   $(".theatre-details ul.features-tab li").removeClass("active"); //Remove any "active" class
			   $(".theatre-details .cinema-features-cont > div").hide(); //Hide all tab content
			   $(activeTab).fadeIn(); //Fade in the active content
		   }
			$(this).addClass("active"); //Add "active" class to selected tab
		   return false;
	   });

	//toggle payment tag
	$("#ccard_select_btn").click(function() {
		$('#ccard_select_btn').addClass("active");
		$('#paypal_select_btn, #givex_select_btn, #radiantgift_select_btn, #vista_select_btn').removeClass("active");
		$("#input_ticket_surcharge").val($("#ticket_surcharge").val());
		var ticket_tot_amt = parseFloat($("#giftticketamt").val())+parseFloat($("#ticket_surcharge").val());
		$('#div_ticket_surcharge').html("$"+$("#ticket_surcharge").val());
		$('#div_ticket_total').html("$"+ticket_tot_amt.toFixed(2));
		$('#paypal_pay_block').hide();
		$('#credit_pay_block').show();
		$('#givex_pay_block').hide();
		$('#radiantgift_pay_block').hide();
	});
	$("#paypal_select_btn").click(function() {
		$('#paypal_select_btn').addClass("active");
		$('#ccard_select_btn, #givex_select_btn').removeClass("active");
		$('#credit_pay_block').hide(); 
		$('#paypal_pay_block').show();
		$('#givex_pay_block').hide();
	});
	$("#givex_select_btn").click(function() {
		$('#givex_select_btn').addClass("active");
		$('#ccard_select_btn, #paypal_select_btn').removeClass("active");
		var ticket_tot_amt = parseFloat($("#giftticketamt").val());
		$('#div_ticket_surcharge').html("$0.00");
		$('#div_ticket_total').html("$"+ticket_tot_amt.toFixed(2));
		$('#credit_pay_block').hide(); 
		$('#paypal_pay_block').hide();
		$('#givex_pay_block').show();
	});
	$("#vista_select_btn").click(function() {
		$('#vista_select_btn').addClass("active");
		$('#ccard_select_btn, #paypal_select_btn').removeClass("active");
		$('#credit_pay_block').hide(); 
		$('#paypal_pay_block').hide();
		$('#givex_pay_block').show();
	});
	$("#radiantgift_select_btn").click(function() {
		$('#radiantgift_select_btn').addClass("active");
		$('#ccard_select_btn, #paypal_select_btn').removeClass("active");
		var ticket_tot_amt = parseFloat($("#giftticketamt").val());
		$('#div_ticket_surcharge').html("$0.00");
		$("#div_ticket_total").html("$"+ticket_tot_amt.toFixed(2));
		$("#input_ticket_surcharge").val(0);
		$('#credit_pay_block').hide(); 
		$('#radiantgift_pay_block').show();
		var contentHeight = $('#contents').find("fieldset:visible").height()+110;
		$("#contents").css("height",contentHeight)
	});
	// Cinema Location Session Times Tabs
	$(".sessiontimes-tab_content").hide(); //Hide all content
	$("ul.session-times-tab li:first").addClass("active").show(); //Activate first tab
	$(".sessiontimes-tab_content:first").show(); //Show first tab content
	$("ul.session-times-tab li").click(function() {
		$("ul.session-times-tab li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".sessiontimes-tab_content").hide(); //Hide all tab content
		var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active content
		$('.print_btn').show();
		if($(activeTab).hasClass('no_session')) $('.print_btn').hide();
		return false;
	});

	$('#cinemasection_container').live('change',function(){});

	if($('#slider').size() > 0) {
		$('#slider').nivoSlider({
			effect:'random', //Specify sets like: 'fold,fade,sliceDown'
			slices:10,
			animSpeed:500, //Slide transition speed
			pauseTime:5000,
			startSlide:0, //Set starting Slide (0 index)
			directionNav:true, //Next & Prev
			directionNavHide:true, //Only show on hover
			controlNav:true, //1,2,3...
			afterLoad: function(){ Cufon.refresh(); $('.nivo-caption h2').show(); }, 
			afterChange: function(){ Cufon.refresh(); $('.nivo-caption h2').show(); }
		});
	}
	
	

});

$(function(){

	if( $('#my-dropdown,#my-dropdown2, #my-dropdown3, #my-dropdown4').size() > 0) {
		$('#my-dropdown, #my-dropdown2, #my-dropdown3, #my-dropdown4').sSelect({'ddMaxHeight':'300px'});
		$('#my-dropdown, #my-dropdown2, #my-dropdown3, #my-dropdown4').siblings('.newListSelected').find('.SSContainerDivWrapper').css('width', '187px');
	}

	$('#my-dropdown').getSetSSValue('Please select').resetSS();
	$('#my-dropdown2').getSetSSValue('Please select').resetSS();
	$('#my-dropdown3').getSetSSValue('Please select').resetSS();
	$('#my-dropdown4').getSetSSValue('Please select').resetSS();
	
	$('#my-dropdown').change(function(){	
		if($('#my-dropdown').val()!="" && $('#my-dropdown').val()!="Please select"){
			showallmovies();
		}
		else
		{
		   $('#my-dropdown2').val("");
		  $('#my-dropdown2').getSetSSValue('Please select').resetSS();
		  $("#my-dropdown2").next("div").find(".SSContainerDivWrapper").remove();
		   $('#my-dropdown3').val("");
		 $('#my-dropdown3').getSetSSValue('Please select').resetSS();
		  $("#my-dropdown3").next("div").find(".SSContainerDivWrapper").remove();
		   $('#my-dropdown4').val("");
		 $('#my-dropdown4').getSetSSValue('Please select').resetSS();
		  $("#my-dropdown4").next("div").find(".SSContainerDivWrapper").remove();
		}
	});

	$('#my-dropdown2').change(function(){
		if(($('#my-dropdown').val()!="" && $('#my-dropdown').val()!="Please select") && ($('#my-dropdown2').val()!="" && $('#my-dropdown2').val()!="Please select")){
			showSessionTime();
		}
			else
		{
		   $('#my-dropdown3').val("");
		    $('#my-dropdown3').getSetSSValue('Please select').resetSS();
		   $("#my-dropdown3").next("div").find(".SSContainerDivWrapper").remove();
		   $('#my-dropdown4').val("");
		    $('#my-dropdown4').getSetSSValue('Please select').resetSS();
		   $("#my-dropdown4").next("div").find(".SSContainerDivWrapper").remove();
		}
	});

	$('#my-dropdown3').change(function(){
		if(($('#my-dropdown').val()!="" && $('#my-dropdown').val()!="Please select") && ($('#my-dropdown2').val()!="" && $('#my-dropdown2').val()!="Please select") && ($('#my-dropdown3').val()!="" && $('#my-dropdown3').val()!="Please select")){
			showTime();
		}
		else
		{
		   $('#my-dropdown4').val("");
		    $('#my-dropdown4').getSetSSValue('Please select').resetSS();
		   $("#my-dropdown4").next("div").find(".SSContainerDivWrapper").remove();
		}
	});


	//glod lounge private booking form validation
	$('input#xxb').click(function(){
		var flag = true;
		var fields = '';
		/////////////////
		if($('select#my-dropdown1').val() == '' || $('div.selectedTxt').html() == 'Select a Cinema Location')
	{
		$('div#mydrp1').addClass('error-sel');
		flag = false;
		fields = '<li>Cinema Location</li>';
	}
		else
	{
		$('div#mydrp1').removeClass('error-sel');
	}
	////////////////////
	if($('input[name="fn"]').val() == '')
	{
		$('input[name="fn"]').addClass('textbox-error');
		if($('input[name="fn"]').parent().find('div.error-icon').length == 0)
	{
		$('input[name="fn"]').parent().append('<div class="error-icon"></div>');
	}
	flag = false;
	fields += '<li>First name</li>';
	}
	else
	{
		$('input[name="fn"]').removeClass('textbox-error');
		$('input[name="fn"]').parent().find('div.error-icon').remove();
	}
	///////////////////////
	if($('input[name="ln"]').val() == '')
	{
		$('input[name="ln"]').addClass('textbox-error');
		if($('input[name="ln"]').parent().find('div.error-icon').length == 0)
		{
			$('input[name="ln"]').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Last name</li>';
	}
	else
	{
		$('input[name="ln"]').removeClass('textbox-error');
		$('input[name="ln"]').parent().find('div.error-icon').remove();
	}
	////////////////////////
	if($('input[name="pc"]').val() == '')
	{
		$('input[name="pc"]').addClass('textbox-error');
		if($('input[name="pc"]').parent().find('div.error-icon').length == 0)
		{
			$('input[name="pc"]').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Postcode</li>';
	}
	else
	{
		$('input[name="pc"]').removeClass('textbox-error');
		$('input[name="pc"]').parent().find('div.error-icon').remove();
	}
	///////////////////////
	if($('input[name="cn"]').val() == '')
	{
		$('input[name="cn"]').addClass('textbox-error');
		if($('input[name="cn"]').parent().find('div.error-icon').length == 0)
		{
			$('input[name="cn"]').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Contact number</li>';
	}
	else
	{
		$('input[name="cn"]').removeClass('textbox-error');
		$('input[name="cn"]').parent().find('div.error-icon').remove();
	}

	//////////////////////
	if($('input[name="em"]').val() == '')
	{
		$('input[name="em"]').addClass('textbox-error');
		if($('input[name="em"]').parent().find('div.error-icon').length == 0)
		{	
			$('input[name="em"]').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Email address</li>';
	}
	else
	{
		$('input[name="em"]').removeClass('error-sel');
		$('input[name="em"]').parent().find('div.error-icon').remove();
		$('input[name="em"]').removeClass('textbox-error');
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(regex.test($('input[name="em"]').val()))
		{
			$('input[name="em"]').removeClass('error-sel');
			$('input[name="em"]').parent().find('div.error-icon').remove();
		}
		else
		{
			$('input[name="em"]').addClass('textbox-error');
			$('input[name="em"]').parent().append('<div class="error-icon"></div>');
			fields += '<li>Valid Email address</li>';
			flag = false;
		}
	}

	//////////////////////
	if($('input[name="og"]').val() == '')
	{
		$('input[name="og"]').addClass('textbox-error');
		if($('input[name="og"]').parent().find('div.error-icon').length == 0)
		{
			$('input[name="og"]').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Organization/Group</li>';
	}
	else
	{
		$('input[name="og"]').removeClass('textbox-error');
		$('input[name="og"]').parent().find('div.error-icon').remove();
	}
	//////////////////////
	if($('#captcha').val() == '')
	{
		$('#captcha').addClass('textbox-error');
		if($('#captcha').parent().find('div.error-icon').length == 0)
		{
			$('#captcha').parent().append('<div class="error-icon"></div>');
		}
		flag = false;
		fields += '<li>Verification Code</li>';
	}
	else
	{
		$('#captcha').removeClass('textbox-error');
		$('#captcha').parent().find('div.error-icon').remove();
	}

	if(flag == false)
	{
		showPopup('<span style="color:red">Required Field(s)</span>','<p>Please fill all the required fields and click submit.</p><br><div id="req-fields"><ul>'+fields+'</ul></div>');
		return false;
	}
	else
	{
		$('form#cpb').submit();
	}
	});

});

function showallmovies(){
	var theatreid = $('#my-dropdown').val();

	$.getJSON(baseurl +'expressticket/showMovie/'+theatreid, 
			function(data) {
				var moviesublist = "";
				moviesublist = moviesublist + '<option selected="selected" disabled="disabled">Please select</option>';
				$.each(data.movies, function(i,movie){
					moviesublist = moviesublist + '<option value="'+movie.Id+'">' + movie.Title + '</option>';
				});
				$('#my-dropdown2').html(moviesublist).resetSS();
				$('#my-dropdown2').siblings('.newListSelected').find('.SSContainerDivWrapper').css('width', '187px');
			});
}


//e-ticket javascript

function showSessionTime(){
	var threatreid = $('#my-dropdown').val();
	var movieid = $('#my-dropdown2').val();
	var dateurl = baseurl + "expressticket/showSessionTime/";    
	
	

	$.getJSON(dateurl, {'theatreid':threatreid, 'movieid':movieid},
			function(data) {
				var datesublist = "";
				console.log(data);
				datesublist = datesublist + '<option selected="selected" disabled="disabled">Please select</option>';
				$.each(data.date, function(i,mydate){
					datesublist = datesublist + '<option value="' + mydate.mydate + '">' + mydate.mydatevalue + '</option>';
				});

				$('#my-dropdown3').html(datesublist).resetSS();
				$('#my-dropdown3').siblings('.newListSelected').find('.SSContainerDivWrapper').css('width', '187px');
			});
}

function showTime(datevalue){
	var threatreid = $('#my-dropdown').val();
	var movieid = $('#my-dropdown2').val();

	var datevalue = $('#my-dropdown3').val();

	var timeurl = baseurl + "expressticket/showBySessionTimeByDate/"+ threatreid + "/"+movieid+"/"+ datevalue;

	$.getJSON(timeurl, 
			function(data) {
				var timesublist = "";
				timesublist = timesublist + '<option selected="selected" disabled="disabled">Please select</option>';
				$.each(data.time, function(i,mytime){
					//timesublist = timesublist + '<option value="' + mytime.mytime +':'+mytime.screen+ '">' + mytime.mytimevalue + '</option>';
					timesublist = timesublist + '<option value="' + mytime.mytime +'" title="' +mytime.flg+ '">' + mytime.mytimevalue + '</option>';
				});
				$('#my-dropdown4').html(timesublist).resetSS();
				$('#my-dropdown4').siblings('.newListSelected').find('.SSContainerDivWrapper').css('width', '187px');
			});
}

function expressSubmit(){
	var theatre = $('#my-dropdown').val();
	var movieselect = $('#my-dropdown2').val();
	var sessiondate = $('#my-dropdown3').val();
	var sessiontime = $('#my-dropdown4').val();
	
	gaTrackEvent ('E-Tix', 'Home', 'Home - Buy Tickets - Left Nav Button');
	
	setTimeout(function() {
		if(theatre != "" && movieselect != "" && sessiondate != "" && sessiontime != ""){
	
			$('#expressform').submit();
		}
		else if(theatre == ""){
	
			window.location.replace(baseurl + "session_times/locations/");
	
		}else if(movieselect == ""){
			
			window.location.replace(baseurl + "session_times/movies/");
		}
		else if(sessiondate == "" || sessiontime == ""){
			
			window.location.replace(baseurl + "session_times/time");
		}
	}, 1000);	
}

//eticket step 3 payment arrangement.
function showSection(id){

	var numberid = id;

	if(id == "paysection1"){
		$('#payformsection1').show();
		$('#payformsection2').hide();
		$('#payformsection3').hide();
	}
	if(id == "paysection2"){
		$('#payformsection2').show();
		$('#payformsection1').hide();
		$('#payformsection3').hide();
	}
	if(id == "paysection3"){
		$('#payformsection3').show();
		$('#payformsection2').hide();
		$('#payformsection1').hide();
	}

}

//session time javascript for submiting to E-tix



//search movie
var timeoutId;
function searchMovie(){
	//$(this).delay(200);
	
	var location = $('#selectmovie').val();
	var title = $('#searchtitle').val();

	if(title == "Movie title..."){
		title = "";
	}
	$('#movielist').hide().html('<h2>Searching...</h2>').show();
	if (timeoutId) { clearTimeout(timeoutId); }
		timeoutId=setTimeout(function(){
	$.get("./movies/searchBylocationAndTitle/"+location+"/",{"title":encodeURI(title)},function (repsonText) {
			$('#movielist').fadeOut('fast', function() {;
			$('#movielist').html(repsonText);
			$('#movielist').fadeIn()});
	});
	},2000);
}

function showFeatureSessions(theatre, feature, date) {
	
	$('#sessiontime').show();
	$('#stab').show().addClass('load-spinner').html('');

	$.ajax({
		url: './cinemafeature/get_theatre_sessions/' + theatre  + '/' + feature + '/' +  date,
		type: 'POST',
		dataType: 'html',
		success: function (data) {
			$('#stab').removeClass('load-spinner').html(data);
		}
	});
	return false;	

}

// TIPSY TOOLTIP RED
$(function() {
    $('.tooltip-sw').tipsy({gravity: 'sw'});
	$("a.inline_lightbox").fancybox({"showCloseButton":true});
});

function shareURL(url) {
	var page = $(location).attr('pathname');
	var pathname = page.split("/");
	var pagename = pathname[1];
	if(pagename=="" || pagename=="home"){
		var pg_action = "Home";
		var pg_label = "Share Banner";
	}
	else if(pagename=="movie"){
		var pg_action = "Movie";
		var pg_label = "Share Movie";
	}
	else if(pagename=="gold_lounge"){
		var pg_action = "Gold Lounge "+pathname[3];
		var pg_label = "Share Movie";
	}
	else if(pagename=="gold_lounge_menu"){
		var pg_action = pagename;
		var pg_label = "Share Menu";
	}
	else{
		var pg_action = pagename;
		var pg_label = "Share Movie";
	}
	$('#fb_share_link').bind('click', function() {
		_gaq.push(['_trackEvent', 'Facebook', pg_action, pg_action+' - Facebook - '+pg_label]);
	});
	$('#twitter_share_link').bind('click', function() {
		_gaq.push(['_trackEvent', 'Twitter', pg_action, pg_action+' - Twitter - '+pg_label]);
	});
	$('#fb_share_link').attr('href', 'http://www.facebook.com/sharer.php?s=100'+(url));	
	$('#twitter_share_link').attr('href', 'https://twitter.com/share?source=tweetbutton&text=Reading%20Cinema&url='+encodeURIComponent(url));
	return false;
}

$(document).ready(function(){
    $('a.inline_lightbox').live('mouseenter', function(){
        $(this).fancybox({'type':'inline'});
    });
	$('a.share_litbox').live('mouseenter', function(){
        $(this).fancybox({'type':'inline',"showCloseButton":true,"onClosed": function(){
				$('.nivo-imageLink,.nivo-caption').mouseover(function(){
					$('.nivo-caption').show();
					$('.nivo-caption').css('opacity','1')
				}).mouseout(function(){
					$('.nivo-caption').hide();
				});
			}
		});
    });
});

function showPopup(title, content) {
	$('#general_popup .overlay-title').html(title);
	$('#general_popup .overlay-content').html(content);
	$('#popup_link').click();
}


function gaTrackEvent (category, action, label, url) {
	
	_gaq.push(['_trackEvent', category, action, label]);
	
	if (url != null) {
	 	setTimeout(function() {
			window.location.href = url;
		}, 1000);
	}
}