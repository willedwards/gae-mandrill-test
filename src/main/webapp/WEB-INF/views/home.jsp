<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE HTML>
<html>
  <head>
    <title>GAE TEMPLATE Test Page</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <style>
      body { background-color: #eee; font: helvetica; }
      #container { width: 500px; background-color: #fff; margin: 30px auto; padding: 30px; border-radius: 5px; box-shadow: 5px; }
      .green { font-weight: bold; color: green; }
      .message { margin-bottom: 10px; }
      label { width:70px; display:inline-block;}
      .hide { display: none; }
      .error { color: red; font-size: 0.8em; }
    </style>
  </head>
  <body>
   
  <div id="container">
   
    <h1>GAE template test Page</h1>
    <p>This is a template page.
    </p>
    
    <h2>Simple test</h2>
    <form id="testForm">
      <label for="testField1">Test field 1: </label><input name="testField1" id="testField1" type="text" />
      <input type="submit" value="Send" /> <br /><br/>
	  <p id="testResponse" >Empty</p>	
    </form>
      
    <hr/>

    
  </div>    
   
  <script type="text/javascript">

	$(document).ready(function() {
	
	    $('#testForm').submit(function(e) {
	     // will pass the form data using the jQuery serialize function
	     $.post('${pageContext.request.contextPath}/test', $(this).serialize(), function(response) {
	    		$('#testResponse').text(response.username);
	     });
	      
	     e.preventDefault(); // prevent actual form submit and page reload
	   });
	
	 });

  </script>
   
  </body>
</html>