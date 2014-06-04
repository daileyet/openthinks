
/**
 * @Deprecated
 * @See file{task_editor.js}
 */

function newTask(url) {
		window.location = url;
	}

function delTask(url,id){
	var answer=window.confirm("Do you want to remove this taks?");
	if(answer){
		window.location = url+id;
	}
}

function lock_task(url,idstr) {
		//alert(idstr);
		jQuery.get(url, {
			id : idstr
		}, function(data) {
			if (data.sucess == 'Y') {
				$("input[name='task.lock']").attr("value", "Unlock");
				$("input[name='task.lock']").attr("onClick",
						"unlock_task('" + idstr + "')");
				$("span[name='lock_by']").css("display", "inline");
				$("#tip_info").text(data.info);
			} else {
				$("#error_info").text(data.info);
			}
			console.log(data.info);
		}, "json");
	}

	function unlock_task(url,idstr) {
		//alert(idstr);
		jQuery.get(url, {
			id : idstr
		}, function(data) {
			if (data.sucess == 'Y') {
				$("input[name='task.lock']").attr("value", "Lock");
				$("input[name='task.lock']").attr("onClick",
						"lock_task('" + idstr + "')");
				$("span[name='lock_by']").css("display", "none");
				$("#tip_info").text(data.info);
			} else {
				$("#error_info").text(data.info);
			}
			console.log(data.info);
		}, "json");
	}