TASK_EDITOR = function(formData, uiDefintions, errorData,successData) {
    //formData.parent_node		COMPONENT ID
    //formData.task_id 			VALUE
    //formData.task_subject 	VALUE
    //formData.task_content		VALUE
	//formData.task_lock		VALUE   Y,N,
	//formData.task_group_id	VALUE
	//formData.is_group_owner	VALUE
	
	//formData.actions         
	//formData.actions.save     URL
	//formData.actions.list		URL
	//formData.actions.remove  	URL
	//formData.actions.lock		URL
	//formData.actions.unlock	URL
	//formData.actions.assign
	
    //uiDefintions.content_id   COMPONENT ID
    //uiDefintions.subject_id	COMPONENT ID
	//uiDefintions.source_id	COMPONENT ID
	//uiDefintions.control_id	COMPONENT ID
    //errorData.id				COMPONENT ID
    //errorData.message			VALUE
	
	//successData.id				COMPONENT ID
    //successData.message			VALUE
    this.formData=formData;
	this.uiDefintions=uiDefintions;
	this.errorData=errorData;
    var self=this;
    
    
    //expose function
    this.initial = initial;
    this.assignGroup=assignGroup;
    //self function
   
    

////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    function createSubmitForm() {

        $("#" + formData.parent_node).append("<form id='v_task-form' action='" + v(self.formData.actions.save) + "' method='post' ><input type='hidden' name='id' id='v_task-id' value='" 
        + v(self.formData.task_id) + "'/><input type='hidden' id='v_task-subject' name='subject' value='" 
        + v(self.formData.task_subject) + "'/><textarea name='content.content' id='v_task-content'>" 
        + v(self.formData.task_content) + "</textarea></form>");
    };

    function loadUIData() {

        if (self.uiDefintions.content_id && self.formData.task_content) $("#" + self.uiDefintions.content_id).html(unescape(self.formData.task_content));
        if (self.uiDefintions.source_id && self.formData.task_content) $("#" + self.uiDefintions.source_id).text(unescape(self.formData.task_content));
        if (self.uiDefintions.subject_id && self.formData.task_subject) $("#" + self.uiDefintions.subject_id).html(unescape(self.formData.task_subject));
    };

	/*Deprecated*/
    //synchronizing the subject on left task list and right task editor
    function syncSubject() {
        var v_subject = v(self.formData.task_subject);
        var v_task_id = v(self.formData.task_id);
        var $list = $('#task-list-container', window.parent.document);
        var $subject = $list.contents().find("div[class~='panel-primary'] span[name='task-subject']");
        if ($subject.length > 0 && $subject.html() != unescape(v_subject) && v_task_id != "") {
            $subject.html(unescape(v_subject));
        }      
    };
	
	function callTaskListRefresh(focusData){
		var $list = $('#task-list-container', window.parent.document);
		var win=$list[0].contentWindow;
		win.tasks.refresh(focusData);
		//refresh_();
	};

    function updateSubmitForm(_formData) {
    	
        var $form = $("#v_task-form");
       
        if ($form.length <= 0) return;
        //if(_formData.task_subject)$("#v_task-subject").attr("value", escape(_formData.task_subject));
        //if(_formData.task_subject)
        	$("#v_task-subject").val( escape(v(_formData.task_subject)));
        //if(_formData.task_content)
        	$("#v_task-content").text(escape(v(_formData.task_content)));
        //if(_formData.actions && _formData.actions.save)$("#v_task-form").attr("action", _formData.actions.save);
        //if(_formData.task_id)$("#v_task-id").attr("value", _formData.task_id);
        //if(_formData.task_id)
        	$("#v_task-id").val( v(_formData.task_id));
    };

    function initial() {
		
        createSubmitForm();
        configureToolBar();
        loadUIData();
        checkOperationError(self.errorData);				
		setUpEditorCommand();
		switchSourceOrEditModel();
    };
    
    function configureToolBar(){
    	if(self.formData.task_lock=='Y'){
    		$("a[data-role='lock']").hide();
    		$("a[data-role='unlock']").show();
    	}else if(self.formData.task_lock=='N'){
    		$("a[data-role='lock']").show();
			$("a[data-role='unlock']").hide();
    	}else{
    		$("a[data-role='lock']").hide();
			$("a[data-role='unlock']").hide();
    	}
    	
    	if(self.formData.task_id==''){
    		$("a[data-role='assignGroup']").hide();
    	}else{
    		
    		if(self.formData.is_group_owner=='true'){
    			$("a[data-role='assignGroup']").show();
    		}else{
    			$("a[data-role='assignGroup']").hide();
    		}
    	}
    	
    };
	
	function setUpEditorCommand(){
		$("#"+self.uiDefintions.control_id+" a").click(function(e) {
					switch($(this).data('role')) {
						case 'h1':
						case 'h2':
						case 'p':
							document.execCommand('formatBlock', false, '<' + $(this).data('role') + '>');
							break;
						case 'save':													
							saveTask();
							break;
						case 'delete':	
							deleteTask();
							break;
						case 'lock':
							lockTask();
							break;
						case 'unlock':
							unlockTask();
							break;
						case 'test':
							callTaskListRefresh();
							break;
						default:
							document.execCommand($(this).data('role'), false, null);
							break;
					}
					
				});
	};
	
	function lockTask(){
		var processResp=function(data) {
			if (data.type == 'SUCESS') {
				$("a[data-role='lock']").hide();
				$("a[data-role='unlock']").show();
				callTaskListRefresh();
			} else if(data.type=="ERROR"){
				checkOperationError({
					id: errorData.id,
					message: data.msg
				});
			}else{}
			
		};
						
		jQuery.get(self.formData.actions.lock, {
			id : self.formData.task_id
		},processResp, "json");
	};
	
	function unlockTask(){
		var processResp=function(data) {
			if (data.type == 'SUCESS') {
				$("a[data-role='lock']").show();
				$("a[data-role='unlock']").hide();
				callTaskListRefresh();
			} else if(data.type=="ERROR"){
				checkOperationError({
					id: errorData.id,
					message: data.msg
				});
			}else{}
		};					
		jQuery.get(self.formData.actions.unlock, {
			id : self.formData.task_id
		},processResp, "json");
	};
	
	function switchSourceOrEditModel(){
		$("ul[class~='nav'] a").each(function(){			
			$(this).click(function(){	
				$(this).parent().parent().find("li.active").removeClass("active");
				$(this).parent().addClass("active");
				var $control=$("#"+$(this).data('role')).css("display","block");
				var $toggle=$("#"+$(this).data('toggle')).css("display","none");
				if($(this).data('role')==self.uiDefintions.source_id){
					$control.text($toggle.html());
				}else if($(this).data('role')==self.uiDefintions.content_id){
					$control.html($toggle.text());
				}
			});
		});
					
	};
	
    function saveTask() {

        updateSubmitForm({
            actions: {save:self.formData.actions.save},
            task_subject: $("#" + self.uiDefintions.subject_id).html(),
            task_content: $("#" + self.uiDefintions.content_id).html(),
            task_id:self.formData.task_id
        });
        
        var v_subject = $("#v_task-subject").attr("value");
        if (v_subject == "") {
            checkOperationError({
                id: errorData.id,
                message: 'Subject can not be empty.'
            });
            return;
        }

		var processResp=function(data){
			if(data.type=="ERROR"){
				checkOperationError({
					id: errorData.id,
					message: data.msg
				});
			}else if(data.type=="SUCESS"){				
				updateSubmitForm({task_id:data.other.id});
				self.formData.task_id=data.other.id;
				self.formData.task_lock=data.other.lock;
				configureToolBar();
				callTaskListRefresh(data.other.id);
			}else{}
		};
		
		$.post(		
			v(formData.actions.save)
			,$("#v_task-form").serialize()
			,processResp
			,"json"
		);
		
		
   };

    function deleteTask() {
    	if(self.formData.task_id=='')return;

		var processResp=function(data){
			if(data.type=="ERROR"){
				checkOperationError({
					id: errorData.id,
					message: data.msg
				});
			}else if(data.type=="SUCESS"){
				$("#" + self.uiDefintions.content_id).empty();
				$("#" + self.uiDefintions.source_id).empty();
				$("#" + self.uiDefintions.subject_id).empty();
				updateSubmitForm({task_id:"",task_subject:"",task_content:""});
				self.formData.task_id='';
				$("#v_task-id").val("");
				callTaskListRefresh();
			}else{}
		};
		
       $.get(
		v(self.formData.actions.remove)
		,{"id":v(self.formData.task_id)}
		,processResp
		,"json"
	   );
   };

    function checkOperationError(_errorData) {

        if (_errorData.message && _errorData.message != "") {
            if (_errorData.id) {
                var $error = $("#" + _errorData.id);
                $error.html(_errorData.message);
                $error.parent().slideDown("slow",
                function() {
                    setTimeout(function() {
                        $error.parent().slideUp("slow",
                        function() {});
                    },
                    "3000");
                });
            }
        } else {
            if (_errorData.id) {
                var $error = $("#" + _errorData.id);
                if ($error.html() != "") {
                    $error.parent().slideDown("slow",
                    function() {
                        setTimeout(function() {
                            $error.parent().slideUp("slow",
                            function() {});
                        },
                        "3000");
                    });
                }
            }
        }
    };

   
    function assignGroup(groupId){
    	if(self.formData.task_id==''  || groupId=="")return;
    	
    	var processResp=function(data){
			if(data.type=="ERROR"){
				checkOperationError({
					id: errorData.id,
					message: data.msg
				});
			}else if(data.type=="SUCESS"){
				self.formData.task_group_id=groupId;
				checkOperationError({
					id: successData.id,
					message: data.msg
				});
			}else{}
		};
    	
		$.post(
				v(self.formData.actions.assign)
				,{"taskId":v(self.formData.task_id),"groupId":groupId}
				,processResp
				,"json"
		);
		
    }
    
};