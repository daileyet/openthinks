
(function() {
	var plugin = new TODO_PLUGIN();
	plugin.editor.defaults = {
		$toolbar : $('#editControls'),
		$editor : $('#editor'),
		$source : $('#editor-source'),
		switchSelector : "ul[class~='nav'] a",
		commandRole : 'role',
		externalCommandRole : 'role-customer'
	}, 
	plugin.editor.externalhandlers = [{
		key:'save',
		handler:function(a,b){
			alert($(this).data(plugin.editor.defaults.externalCommandRole));
		}
	}],
	
	plugin.bindToolBar(), plugin
			.switchSourceOrEditModel();

})();
