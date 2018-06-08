<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Insert title here2</title>
</head>
<script !src="">
    function requestJson(){

        var jsonData = {
            "name" : "??",
            "price" : "999"
        };
        $.ajax({
            type:'post',
            url:'${pageContext.request.contextPath }/requestJson',
            contentType:'application/json;charset=utf-8',//???json??
            //?????json??????
            data:JSON.stringify(jsonData),
            success:function(data){//??json??
                alert(data.name);
            }
        });
    }

</script>
<body>
<input value="??" type="button" onclick="requestJson()"/>
<a href="hello?lang=en_US">en</a><br/>
<a href="hello?lang=zh_CN">zh</a><br/>
</body>
</html>