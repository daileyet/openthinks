function WordImageUploader(s_url, app_name)
{
    var _this = this; 
    var appName = "/editor1";

    var init = function()
    {
        sUrl = s_url;
        appName = app_name;
        if (appName == '/') {
            appName = '';
        }

    };
  var printRequiredHtml11 = function() {
        var xx = '<div id=\"word_image_container_temp\" style=\"display:none;\"></div>';
		
		var yy='<script src=\"https://www.java.com/js/deployJava.js\">';
		yy=yy+'</script>';
		
		var zz='<script>var attributes = {id : \"imageUploaderApplet\",code:\"com.openthinks.tasks.web.utils.applet.UploadFileApplet.class\"';
		zz=zz+',codebase:\"'+appName+'/static/applet/\"';
		zz=zz+',archive:\"upload.jar,apache-mime4j-0.6.jar,commons-logging-1.1.3.jar,httpclient-4.0.3.jar,httpcore-4.0.1.jar,httpmime-4.0.3.jar\"';
		zz=zz+',width:500,height:10};';
		zz=zz+'var parameters = {postUrl :\"'+sUrl+'\"};';
		zz=zz+'deployJava.runApplet(attributes, parameters, \"1.6\");';
		
		zz=zz+'$(\"#imageUploaderApplet\").css(\"display\",\"none\"); </script>';
		
        document.write(xx);
		document.write(yy);
		document.write(zz);
   };

    init();
    printRequiredHtml11();
    
    _this.uploadWordImagesFromCKEditor = function(editorInstance, pre_id) {
        var ed = $(editorInstance);
        //var txt = ed.getData();
		var txt = ed.html();
        var txt0 = txt;
        jQuery('#word_image_container_temp').html(txt);
        var i = 0;
        jQuery('#word_image_container_temp img').each(function() {
            var src = $(this).attr('src');
            if (src.indexOf("file:///") != -1) {
                var srct = src.replace('file:///', '');
                //alert(srct);
                var serverPath = _this.uploadLocalFile(srct, pre_id + '_' + newGuid121());
                if (serverPath != 'error') {
                    //alert(serverPath);
                    txt = txt.replace(src, serverPath);
                }
            }
        });
        //jQuery('#container_temp').html(txt);
        if (txt0 != txt) {
            ed.html(txt);
        }
        //alert(ed.getData());
    };

    _this.uploadLocalFile = function(filePath, fileName) {
        var appletObj = document.getElementById("imageUploaderApplet");
        var result = appletObj.sendFile(filePath,fileName);
        return result;
    };

}

function newGuid121()
{
    var guid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        guid += n;
        if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
            guid += "-";
    }
    return guid;
}

