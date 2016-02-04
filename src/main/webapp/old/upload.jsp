<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<html>  
  <head>  
    <title>文件上传</title>  
  
 <meta http-equiv="pragma" content="no-cache">  
 <meta http-equiv="cache-control" content="no-cache">  
 <meta http-equiv="expires" content="0">  
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
 <meta http-equiv="description" content="This is my page">  
  </head>  
 <body>  
  <form action="${pageContext.request.contextPath}/upload/img.htm" enctype="multipart/form-data" method="post">  
   <table align="center">  
    <tr><td>文件1</td><td><input type="file" name="file" id="file_upload" value=""/></td></tr>  
    <tr><td>文件2</td><td><input type="file" name="file" value=""/></td></tr>  
    <tr><td>文件3</td><td><input type="file" name="file" value=""/></td></tr>  
    <tr><td>文件4</td><td><input type="file" name="file" value=""/></td></tr>  
    <tr><td colspan="2"><input type="submit" value ="提交"/></td></tr>  
   </table>  
  </form>  
  
  <script src="https://www.java.com/js/deployJava.js"></script>
  
  <script> 
  var attributes = {id:'imageUploaderApplet',code:'com.openthinks.tasks.web.utils.applet.UploadFileApplet', codebase:'static/applet/',archive:'upload.jar,apache-mime4j-0.6.jar,commons-logging-1.1.3.jar,httpclient-4.0.3.jar,httpcore-4.0.1.jar,httpmime-4.0.3.jar',
      width:750, height:600} ; 
  var parameters = {postUrl:'http://localhost:8080/openthinks/upload/img.htm'};
  deployJava.runApplet(attributes, parameters, '1.6'); 
</script>
  
  </body>  
</html>  