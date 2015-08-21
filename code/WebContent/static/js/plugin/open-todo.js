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

TODO_PLUGIN.prototype.updateEditorUI=function(){
	//TODO
};
TODO_PLUGIN.prototype.updateEditorModel=function(data){
	var dfs = this.editor.defaults,model=dfs.dataModel;
	for(var property in data){
		console.log(property);
		if(model.hasOwnProperty(property)){
			model[property]=data[property];
		}
	}
};

TODO_PLUGIN.prototype.setUp=function(){
	var plugin=this,dfs=plugin.editor.defaults,
	bindToolBar= function() {//bind plug-in editor tool bar ;
		//require bootstrap-wysiwyg plug-in
		var dfs = plugin.editor.defaults, toolbarBtnSelector = 'a[data-'
				+ dfs.internalCommand.role
				+ '],button[data-'
				+ dfs.internalCommand.role
				+ '],input[type=button][data-' + dfs.internalCommand.role + ']', toolbarExternalBtnSelector = 'a[data-'
				+ dfs.externalCommand.role
				+ '],button[data-'
				+ dfs.externalCommand.role
				+ '],input[type=button][data-'
				+ dfs.externalCommand.role + ']';
		if (dfs.internalCommand.enable) {//directly use HTM5 document.execCommand(command, 0, args);
			dfs.$toolbar.find(toolbarBtnSelector).click(function() {
				plugin.editor.handler($(this).data(dfs.internalCommand.role));
			});
		} else {//initial bootstrap-wysiwyg plug-in. @see http://www.bootcss.com/p/bootstrap-wysiwyg/
			plugin.editor.defaults.$editor.wysiwyg({
				toolbarSelector : plugin.editor.defaults.toolbarSelector,
				commandRole : plugin.editor.defaults.internalCommand.role
			});
		}
		if(dfs.externalCommand.enable){
			dfs.$toolbar.find(toolbarExternalBtnSelector).click(function() {
				var cmdTrigger=$(this),commandWithArgs=cmdTrigger.data(dfs.externalCommand.role),
				commandArr = commandWithArgs.split(' '), 
				command = commandArr.shift(); 
				//args = commandArr.join(' ') ;
				plugin.externalCommand(command).handler.apply(cmdTrigger,commandArr);
			});
		}
	},switchSourceOrEditModel=function() {//switch source or edit model
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
	},bindModelViev=function(){
		watchable = DataBind.bind(dfs.dataView, dfs.dataModel);
		watchable.watch(function(ev) {
			console.log('#' + this.id + ' ev:' + ev.type + ' old val:'
					+ ev.data.oldValue + ' new val:' + ev.data.newValue
					+ ' key:' + ev.data.key);
		});
	};
	bindToolBar(), switchSourceOrEditModel(),bindModelViev();
};
