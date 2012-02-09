<script type="text/javascript">
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
		$(document).on('click', "button:enabled.dialog", function() {
			var that = $(this);
			var href = that.attr("href");
			var title = that.attr("title"); 
			var id = that.attr("item_id") || "";
			// show a spinner or something via css
            var dialog = $('<div id="dialog" class="loading"></div>').appendTo('body');
            // open the dialog 
            dialog.dialog({
            	create: function(event, ui) {
            		that.trigger("createActionEvent", dialog);
            	},
                // add a close listener to prevent adding multiple divs to the document
                close: function(event, ui) {
                    // remove div with all data and events
                    dialog.remove();
                },
                modal: true,
                title: title
            });
            // load remote content
            dialog.load(
            	href, 
                {id: id}, // omit this param object to issue a GET request instead a POST request, otherwise you may provide post parameters within the object
                function (responseText, textStatus, XMLHttpRequest) {
                    // remove the loading class
                    dialog.removeClass('loading');
                }
            );
		});
	});
	
</script>