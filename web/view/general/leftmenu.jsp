<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- sidebar menu start-->
<ul class="sidebar-menu">
    <li class="sub-menu active">
        <a href="javascript:;" class="">
            <i class="icon-book"></i>
            <span>计算资源</span>
            <span class="arrow"></span>
        </a>
        <ul class="sub">
            <li><a class="" href="/general/menu.do?module=index" target="content">使用概况</a></li>
            <li class="active"><a class="" href="/general/menu.do?module=instance" target="content">实例</a></li>
            <li><a class="" href="#">云硬盘</a></li>
            <li><a class="" href="#">镜像</a></li>
            <li><a class="" href="#">访问与安全</a></li>
        </ul>
    </li>
    <li class="sub-menu">
        <a href="javascript:;" class="">
            <i class="icon-cogs"></i>
            <span>网络</span>
            <span class="arrow"></span>
        </a>
        <ul class="sub">
            <li><a class="" href="#">网络拓扑</a></li>
            <li><a class="" href="#">网络</a></li>
            <li><a class="" href="#">路由</a></li>
        </ul>
    </li>
    <li class="sub-menu">
        <a href="javascript:;" class="">
            <i class="icon-tasks"></i>
            <span>对象存储</span>
            <span class="arrow"></span>
        </a>
        <ul class="sub">
            <li><a class="" href="#">容器</a></li>
        </ul>
    </li>
    <li class="sub-menu">
        <a href="javascript:;" class="">
            <i class="icon-th"></i>
            <span>身份</span>
            <span class="arrow"></span>
        </a>
        <ul class="sub">
            <li><a class="" href="#">项目</a></li>
        </ul>
    </li>
    <li>
        <a class="" href="/logout.do">
            <i class="icon-user"></i>
            <span>退出</span>
        </a>
    </li>
</ul>
<!-- sidebar menu end-->
