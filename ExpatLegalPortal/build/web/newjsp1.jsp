<%-- 
    Document   : newjsp1
    Created on : Sep 4, 2017, 8:14:15 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <!--ADD INPUT OF TYPE FILE WITH THE MULTIPLE OPTION-->
    <p>
        <input type="file" id="file" onchange="FileDetails()" multiple />
    </p>
    
    <!--SHOW RESULT HERE-->
    <p id="fp"></p>

 
    <script>
    function FileDetails()
    {

        // GET THE FILE INPUT.
        var fi = document.getElementById('file');
        // VALIDATE OR CHECK IF ANY FILE IS SELECTED.
        if (fi.files.length > 0) {

            // THE TOTAL FILE COUNT.
            document.getElementById('fp').innerHTML =
                'Total Files: <b>' + fi.files.length + '</b></br >';

            // RUN A LOOP TO CHECK EACH SELECTED FILE.
            for (var i = 0; i <= fi.files.length - 1; i++) {

                var fname = fi.files.item(i).name;      // THE NAME OF THE FILE.
                var fsize = fi.files.item(i).size;      // THE SIZE OF THE FILE.

                // SHOW THE EXTRACTED DETAILS OF THE FILE.
                document.getElementById('fp').innerHTML =
                    document.getElementById('fp').innerHTML + '<br /> ' +
                        fname + '<b>' + '<input type="text" name="remarks">' + '</b>';
            }
        }
        else { 
            alert('Please select a file.') 
        }
    }
</script>
    </body>
</html>
