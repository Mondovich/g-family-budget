<script type="text/javascript">
	function resizeDivs() {
		var h = $(window).height() - $(".shadow").position().top - 55;
		$(".shadow").height(h);
		$(".main_content").height(h-8);
		if ($(".main_content .body").position() != null) {
			var h1 = $(".main_content .body").position().top;
			$(".main_content .body").height(h-h1-51);
		}
		var h2 = $(".left_menu .last_item.body").position().top;
		$(".left_menu .last_item.body").height(h-h2-40);
		$(".left_menu").height(h-10);
		
		var w = $(window).width()-$(".shadow.main").position().left - 30;
		$(".shadow.main").width(w);
		$(".main_content").width(w-8);
		$(".main_content .body").width(w-10);
		$(".main_content .body .item").width(w-25);
	}
	
	$(function (){
		$("button.plus").button({
			icons: {
				primary: 'ui-icon-circle-plus'
			},
	        text: false
		});
		$("button.edit").button({
			create: function(event, ui) {
				$(this).button( "option", "disabled", true );
			},
			icons: {
				primary: 'ui-icon-circle-check'
			},
	        text: false
		});
		$("button.minus").button({
			create: function(event, ui) {
				$(this).button( "option", "disabled", true );
			},
			icons: {
				primary: "ui-icon-circle-minus"
			},
	        text: false
		});
		$("button").button();
		
		$(window).resize(function() {
			//resizeDivs();
		});
		$(window).trigger( 'resize' );
		
		$('.money').maskMoney({thousands:',', decimal:'.', allowZero: true, allowNegative: true, showSymbol: true, symbolStay: true});
        $('.money').mask();
        $("#currency").maskMoney({thousands:',', decimal:'.', allowZero: true, allowNegative: true, showSymbol: true, symbolStay: true});
		$(".clickable").hover(
			function (event) {
				//$(this).removeClass("ui-state-active");
			  	$(this).addClass("ui-state-hover");
			},
			function () {
				$(this).removeClass("ui-state-hover");
				//$(this).addClass("ui-state-active");
			}
		);
	});
	
</script>