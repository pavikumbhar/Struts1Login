

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/pwdhash.js" type="text/javascript"></script>
        <script type="text/javascript">
            function submitform()
            {

                if ($("#userName").val() != null && $("#userName").val().trim().length != 0 &&
                        $("#password").val() != null && $("#password").val().trim().length != 0) {
                    var token;

                    $.ajax({
                        type: 'GET',
                        url: 'getTokenAction.do',
                        //dataType: 'json',
                        success: function (response) {
                            token = response;
                            alert(response);
                         
                        },
                        data: {},
                        async: false
                    });
                 
                
                 alert($("#password").val());
                 alert("token Length"+token.length);
                 token=token.trim();
                  alert("token Length"+token.length);
                var hashValue=Data2ASCIIhex(RC4(token,$("#password").val() ));
                
                $("#password").val(hashValue);
               
                return true;
                } else {

                    if ($("#userName").val() != null && $("#userName").val().trim().length != 0) {
                        $("#errorbx").html("Please provide password.");
                    } else
                    if ($("#password").val() != null && $("#password").val().trim().length != 0) {
                        $("#errorbx").html("Please provide username.");
                    } else {
                        $("#errorbx").html("Please provide username and password.");
                    }

                    return false;
                }


            }

             /**
              * @pavi Kumbhar
              * trim is not avialabe in IE 
              * So we write Custom Trim function.
              */
            String.prototype.trim = function () {
                return this.replace(/^\s+|\s+$/g, "");
            }
            String.prototype.ltrim = function () {
                return this.replace(/^\s+/, "");
            }
            String.prototype.rtrim = function () {
                return this.replace(/\s+$/, "");
            }
            
        </script>

    </head>

    <body>
        <div id="errorbx" style="color:red">

            <html:errors />
        </div>
        <html:form action="/Login"  onsubmit="return submitform()" >
            User Name : <html:text value="" name="LoginForm" property="userName" styleId="userName" /> <br>
            Password  : <html:password value="" name="LoginForm" property="password" styleId="password" /> <br>
            <html:submit value="Login" />
        </html:form>
    </body>
</html>
