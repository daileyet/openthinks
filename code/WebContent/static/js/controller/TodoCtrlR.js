/**
 * Todo editor controller
 * 
 * @author open-thinks@outlook.com
 * @see open-todo.js
 */
(function(ctx) {
	var plugin = ctx.plugin = (ctx.plugin == undefined ? new TODO_PLUGIN()
			: ctx.plugin);
	plugin.editor.defaults = {
		$toolbar : $('#editControls'),
		$editor : $('#editor'),
		$source : $('#editor-source'),
		toolbarSelector : '#editControls',
		switchSelector : "ul[class~='nav'] a",
		internalCommand : {
			role : 'role',
			enable : true
		},
		externalCommand : {
			role : 'role-customer',
			enable : true
		},
		dataModel : ctx.task,
		dataView : $("#editContent"),
		action : function(key) {
			return ctx.CONTEXT_PATH + '' + {
				save : '/task/ajax_save.htm',
				'delete' : '',
				assignGroup : '',
				unlock : '',
				lock : '',
			}[key];
		}
	}// initial context defaults options
	, plugin.editor.externalhandlers = [ {//
		key : 'save',
		handler : function() {
			// console.debug($(this).data(plugin.editor.defaults.externalCommandRole));
			var processResp = function(data) {
				if (data.type == "ERROR") {
//					$.sticky('[' + data.type + ']' + data.msg);
				} else if (data.type == "SUCESS") {
//					$.sticky('[' + data.type + ']');
					plugin.updateEditorModel(data.other);
				} else {
				}
			};
			$.post(defs.action('save'), ctx.task, processResp, "json");

		}
	}, {//
		key : 'delete',
		handler : function() {

		}
	}, {
		key : 'assignGroup',
		handler : function() {

		}
	}, {
		key : 'unlock',
		handler : function() {

		}
	}, {
		key : 'lock',
		handler : function() {

		}
	} ], defs = plugin.editor.defaults, plugin.setUp();
})(openContext);
