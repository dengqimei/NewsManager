/**
 * 登陆下拉
 */
$(function() { 
		$(window).scroll(function() {
			var top = document.body.scrollTop;
			$("#mask").css({
			top: top + "px"
		}); 
			$("#login").css({
				top: top + 130 + "px"
			}); 
	}); 
}); 
