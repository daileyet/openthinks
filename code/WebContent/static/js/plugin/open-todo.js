/**
 * instance of whole task lister and editor
 * @since 5/29/2014
 */
function TODO_PLUGIN(){
	this.lister = {};
	this.editor = {
		defaults : {},
		handler : function(commandWithArgs, valueArg) {
			var commandArr = commandWithArgs.split(' '), command = commandArr
					.shift(), args = commandArr.join(' ') + (valueArg || '');
			document.execCommand(command, 0, args);
		},
		externalhandlers : [],
	};
};
/**
 * @param cmdhandler
 *            object {key:'Command key',handler:function(){}}
 */
TODO_PLUGIN.prototype.addExternalCommand = function(cmdhandler) {
	this.editor.externalhandlers.push(cmdhandler);
};
TODO_PLUGIN.prototype.externalCommand = function(externalkey) {
	return $.grep(this.editor.externalhandlers,function(cmd){
		if(cmd.key && cmd.key==externalkey){
			return true;
		}
		return false;
	})[0] || {key:externalkey,handler:function(){}};
};
TODO_PLUGIN.prototype.switchSourceOrEditModel=function() {//switch source or edit model
	var dfs=this.editor.defaults;
	$(dfs.switchSelector).each(function(){			
		$(this).click(function(){	
			$(this).parent().parent().find("li.active").removeClass("active");
			$(this).parent().addClass("active");
			var $control=$("#"+$(this).data('role')).css("display","block");
			var $toggle=$("#"+$(this).data('toggle')).css("display","none");
			if($(this).data('role')==dfs.$source.attr('id')){
				$control.text($toggle.html());
			}else if($(this).data('role')==dfs.$editor.attr('id')){
				$control.html($toggle.text());
			}
		});
	});
};
TODO_PLUGIN.prototype.bindToolBar = function() {
	var plugin = this, dfs = this.editor.defaults, toolbarBtnSelector = 'a[data-'
			+ dfs.commandRole
			+ '],button[data-'
			+ dfs.commandRole
			+ '],input[type=button][data-' + dfs.commandRole + ']', toolbarExternalBtnSelector = 'a[data-'
			+ dfs.externalCommandRole
			+ '],button[data-'
			+ dfs.externalCommandRole
			+ '],input[type=button][data-'
			+ dfs.externalCommandRole + ']';
	dfs.$toolbar.find(toolbarBtnSelector).click(function() {
		// $(this).data(dfs.commandRole)
		plugin.editor.handler($(this).data(dfs.commandRole));
	});

	dfs.$toolbar.find(toolbarExternalBtnSelector).click(function() {
		// $(this).data(dfs.externalCommandRole)
		var cmdTrigger=$(this),commandWithArgs=cmdTrigger.data(dfs.externalCommandRole),
		commandArr = commandWithArgs.split(' '), 
		command = commandArr.shift(); 
		//args = commandArr.join(' ') ;
		plugin.externalCommand(command).handler.apply(cmdTrigger,commandArr);
	});

};