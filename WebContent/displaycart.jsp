<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
        <script type="text/javascript">
        function displayResult()
        {
            document.getElementById("myTable").insertRow(-1).innerHTML = '<td>1</td><td>2</td>';
        }
        </script>
    </head>

    <body>       
        <table id="myTable" border="1">
            <tr>
                <td>cell 1</td>
                <td>cell 2</td>
            </tr>
            <tr>
                <td>cell 3</td>
                <td>cell 4</td>
            </tr>
        </table>
        <br />
        <button type="button" onclick="displayResult()">Insert new row</button>            
    </body>
</html>