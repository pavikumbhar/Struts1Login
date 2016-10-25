<%-- 
    Document   : login
   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       
        <script src="js/pwdhash.js" type="text/javascript"></script>



        <script language="JavaScript" type="text/javascript">

            /**
             * @ pavi Kumbhar
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


            var request;

            function getTokenFromServer() {
                var url = "getTokenAction.do";
                var userName = document.getElementById('userName').value;
                var password = document.getElementById('password').value;

                if (userName != null && userName.trim().length != 0 &&
                        userName != null && password.trim().length != 0) {

                    // Perform the AJAX request using a non-IE browser.
                    if (window.XMLHttpRequest) {
                        request = new XMLHttpRequest();

                        // Register callback function that will be called when
                        // the response is generated from the server.
                        request.onreadystatechange = encryptPassword;


                        alert("non IE");
                        try {
                            request.open("POST", url, false); // pass false for synchronous request 
                        } catch (e) {
                            alert("Unable to connect to server to retrieve count.");
                        }

                        request.send(null);


                        // Perform the AJAX request using an IE browser.
                    } else if (window.ActiveXObject) {
                        request = new ActiveXObject("Microsoft.XMLHTTP");

                        if (request) {
                            // Register callback function that will be called when
                            // the response is generated from the server.
                            request.onreadystatechange = encryptPassword;

                            request.open("GET", url, false); // pass false for synchronous request 
                            request.send();

                        }

                    }

                } else {

                    if (userName != null && userName.trim().length != 0) {

                        document.getElementById('errorbx').innerHTML = "Please provide password.";

                    } else
                    if (password != null && password.trim().length != 0) {

                        document.getElementById('errorbx').innerHTML = "Please provide username.";
                    } else {

                        document.getElementById('errorbx').innerHTML = "Please provide username and password.";
                    }

                    return false;


                }




            }

            // Callback function to encryptPassword with token retrieved from server.
            function encryptPassword() {
                if (request.readyState == 4) {
                    if (request.status == 200) {
                        var token = request.responseText;
                        alert("token Length" + token.length);
                        token = token.trim();
                        alert("token Length" + token.length);
                        var password = document.getElementById('password').value;
                        alert(" ..  . .password" + password);
                        var hashValue = Data2ASCIIhex(RC4(token, password));

                        alert("hashValue" + hashValue);
                        document.getElementById('password').value = hashValue;

                        alert(">>>>passss>>>>" + document.getElementById('password').value);
                        return true;

                    } else {
                        alert("Unable to retrieve count from server.");
                        return false;
                    }
                }
                return false;
            }
        </script>


    </head>

    <body>
        <div id="errorbx" style="color:red">

            <html:errors />
        </div>
        <html:form action="/Login"  onsubmit="return getTokenFromServer()" >
            User Name : <html:text value="" name="LoginForm" property="userName" styleId="userName" /> <br>
            Password  : <html:password value="" name="LoginForm" property="password" styleId="password" /> <br>
            <html:submit value="Login" />
        </html:form>
    </body>
</html>
