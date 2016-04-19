<%--
  Created by IntelliJ IDEA.
  User: chenpengjiang
  Date: 2016/3/22
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="../../resources/jsp/common_css.jsp"%>
    <%@ include file="../../resources/jsp/commom_js.jsp"%>
</head>
<body>
<section id="container" class="">
    <!--header start-->
    <header class="header white-bg">
        <div class="sidebar-toggle-box">
            <div data-original-title="Toggle Navigation" data-placement="right" class="icon-reorder tooltips"></div>
        </div>
        <div id="headtemplate" style="display:inline">
            <%@ include file="../general/top.jsp"%>
        </div>
    </header>
    <!--header end-->
    <!--sidebar start-->
    <aside>
        <div id="sidebar"  class="nav-collapse">
            <%@ include file="../general/leftmenu.jsp"%>
        </div>
    </aside>

    <!--main content start-->
    <div id="maincontent">
        <iframe id="content" name="content"  frameborder="0" height="800px"
                scrolling="no" width="100%"></iframe>
    </div>
    <!--main content end-->


</section>
<script>

    //owl carousel

    $(document).ready(function() {
        $("#owl-demo").owlCarousel({
            navigation : true,
            slideSpeed : 300,
            paginationSpeed : 400,
            singleItem : true

        });
    });

    //custom select box

    $(function(){
        $('select.styled').customSelect();
    });

</script>
</body>
</html>