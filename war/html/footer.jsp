<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">

	function bindButtons(element) {
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
	}
	
	$(function (){
		bindButtons();
		
		$(document).on('mouseenter mouseleave', ".clickable", function(event) {
			$(this).toggleClass("ui-state-hover");
		});
		
        $(document).on('click', "button:enabled.dialog", function() {
			var that = $(this);
			var href = that.attr("href");
			var title = that.attr("title"); 
			var id = that.attr("item_id") || "";
			var buttons = eval(that.attr("buttons") || "");
			
			//var buttonsNames = eval(<s:text name='buttons' />);
			var buttonsNames = eval({
				add: '<s:text name='button.add' />', 
				edit: '<s:text name='button.edit' />',  
				delete: '<s:text name='button.delete' />',
				cancel: '<s:text name='button.cancel' />',
				select: '<s:text name='button.select' />'
			});
			var dialog_buttons = {};
			for(i in buttons) {
				if (buttons[i] == 'cancel') {
					dialog_buttons[buttonsNames[buttons[i]]] = function() {
						dialog.dialog("close");
					}
				} else {
					var action = buttons[i];
					dialog_buttons[buttonsNames[buttons[i]]] = function() { that.trigger("buttonClickActionEvent", [action, dialog]); };
				}
			}
			
			// show a spinner or something via css
            var dialog = $('<div id="dialog" class="loading"></div>').appendTo('body');
            // open the dialog 
            dialog.dialog({
            	create: function(event, ui) {
            		that.trigger("createActionEvent", dialog);
            	},
            	buttons: dialog_buttons,
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