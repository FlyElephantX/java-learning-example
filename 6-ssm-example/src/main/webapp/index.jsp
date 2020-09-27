<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ssm</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            type: "get",
            url: "/SSM_Project/student/findAll",
            dataType: "JSON",
            success: function (result) {
                $.each(result, function () {
                    $("#findAll").append("<li>" + this.id + "--" + this.name + "--" + this.age + "--" + this.birthday + "</li>");
                })
            }
        });
    });
</script>
<body>
<ur id="findAll">
</ur>
</body>
</html>