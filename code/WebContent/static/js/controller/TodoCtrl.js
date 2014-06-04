/**
 * Todo editor controller
 * @author open-thinks@outlook.com
 * @see open-todo.js
 */
(function() {
	var plugin = new TODO_PLUGIN();
	plugin.editor.defaults = {
		$toolbar : $('#editControls'),
		$editor : $('#editor'),
		$source : $('#editor-source'),
		switchSelector : "ul[class~='nav'] a",
		internalCommand : {
			role : 'role',
			enable : false
		},
		externalCommand : {
			role : 'role-customer',
			enable : true
		}
	}//initial context defaults options
	, plugin.editor.defaults.$editor.wysiwyg({
		toolbarSelector : '#editControls',
		commandRole : plugin.editor.defaults.internalCommand.role
	})//initial bootstrap-wysiwyg plug-in. @see http://www.bootcss.com/p/bootstrap-wysiwyg/
	, plugin.editor.externalhandlers = [ {//
		key : 'save',
		handler : function(a, b) {
			alert($(this).data(plugin.editor.defaults.externalCommandRole));
		}
	}, {//

	}

	],

	plugin.bindToolBar(), plugin.switchSourceOrEditModel();

})();
