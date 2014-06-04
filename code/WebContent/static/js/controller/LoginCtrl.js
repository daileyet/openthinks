/**
 * controller for login page
 */

(function() {
	// define variable
	var app_path = $("#APP_BASE_PATH").val();

	var $login_box = $("#loginbox");
	var $login_form = $("#loginform");
	$login_form.$login_btn = $("#btn-login", $login_form);

	var $login_alert = $("#login-alert");
	$login_alert.$text = $("div", $login_alert);
	$login_alert.$action_btn = $("button", $login_alert);

	var $switchers = $("#login_switcher,#signup_switcher");

	var $signup_box = $("#signupbox");
	var $signup_form = $("#signupform");
	$signup_form.$signup_btn = $("#btn-signup", $signup_form);

	var $signup_alert = $("#signupalert");
	$signup_alert.$text = $("div", $signup_alert);
	$signup_alert.$action_btn = $("button", $signup_alert);

	var $signup_success = $("#signupsuccess");

	// define method
	var $fn = {
		closeAlert : function() {
			$login_alert.hide();
			$signup_alert.hide();
		},
		showAlert : function(type, msg, isSuccess) {
			switch (type) {
			case 'login':
				$login_alert.$text.html(msg);
				$login_alert.show();
				break;
			case 'signup':
				if (isSuccess && isSuccess == true) {
					$signup_alert.hide();
					$signup_success.html(msg);
					$signup_success.show().fadeOut(3000, function() {
						$login_box.show();
						$signup_box.hide();
					});
				} else {
					$signup_alert.$text.html(msg);
					$signup_alert.show();
				}
				break;
			}
		},
		submitLogin : function() {
			var processResp = function(data) {
				if (data.type == "ERROR") {
					$fn.showAlert('login', data.msg);
				} else if (data.type == "SUCESS") {
					window.location = app_path + "/index.htm";
				} else {
					console.log('unsupport type');
				}
			};
			$.post(app_path + "/login.htm", $login_form.serialize(),
					processResp, "json");
		},
		submitSignup : function() {
			var processResp = function(data) {
				if (data.type == "ERROR") {
					$fn.showAlert('signup', data.msg, false);
				} else if (data.type == "SUCESS") {
					$fn.showAlert('signup', data.msg, true);
				} else {
				}
			};
			$.post(app_path+"/register.htm", $signup_form
					.serialize(), processResp, "json");
		}
	};
	// define bind
	$login_alert.$action_btn.click($fn.closeAlert);
	$login_form.$login_btn.click($fn.submitLogin);
	
	$signup_alert.$action_btn.click($fn.closeAlert);
	$signup_form.$signup_btn.click($fn.submitSignup);
	
	$switchers.click(function() {
		if ($(this).attr("id") == "login_switcher") {
			$login_box.hide();
			$signup_box.show();
		} else if ($(this).attr("id") == "signup_switcher") {
			$login_box.show();
			$signup_box.hide();
		}
	});

})();
