<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- <script type="text/javascript">
    $(document).ready(function(){
    if (jQuery) {  
     // jQuery is loaded  
    alert("Yeah!");
    } else {
    // jQuery is not loaded
    alert("Doesn't Work");
        }
    });
    </script> -->
</head>
<body>



<input type="button" id="calculate" value="calculate" />
                    
                    <input type="text" value="" id="result">
</body>




<!-- <form name="form1" action="AjaxServlet" method="POST">
<input type="hidden" name="action" value="checking">
<div class="select123">
<select name="select123" onchange="document.form1.submit();">
    <option selected="selected" value=""></option>
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
    <option value="4">4</option>
</select>
<input type= "text" name= "n">
</div>
<br><br>
</form> -->

<div>Check if This is your first buy <input type = "checkbox" name = "firstBuy" id = "firstBuy" ></div>
<script>
/* $(function(){

	   $('#calculate').change(function () {
	     var typeFeed=$(this).val();
	     //do ajax now
	     $.get("@Url.Action("changefeed","Home")?TypeFeed="+typeFeed,function(res){
	      do something with res now
	     });
	   });

	}); */
</script>
<script type="text/javascript">
var n = "Praveen";
 $.post("AjaxServlet",
		  { name: n, time: "2pm" },
		  function(json){
		    //alert("Data Loaded: " + data);
		    $("#regTitle").val(json.txt11);
		  }
		); 
 /* if(document.getElementById('firstBuy').checked) {
		firstBuy ="true";
	}
	else{
		firstBuy = "false";
	}
	window.alert(firstbuy); */
		
		</script>
<input type = "text" id = "regTitle">

</html>