<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    function Percentage(number1, number2) {
        return (Math.round(number1 / number2 * 10000) / 100.00 + "%");  // 小数点后两位百分比
    }
    $(function(){
        $.ajax({
            url: "<%=request.getContextPath()%>/general/limitinfo",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性\
            type: "GET",   //请求方式
            success: function(data) {
                var cpus = Percentage(data.vcpuuse,data.vcpu);
                $("#cpupercent_").text(cpus);
                $("#cpupercent").attr("style","width:"+ cpus);

                var rams = Percentage(data.ramuse,data.ram);
                $("#rampercent_").text(rams);
                $("#rampercent").attr("style","width:"+ rams);

                var floatingips = Percentage(data.floatingipuse,data.floatingip);
                $("#floatingippercent_").text(floatingips);
                $("#floatingippercent").attr("style","width:"+ floatingips);

                var volumes = Percentage(data.volumesuse,data.volumes);
                $("#volumespercent_").text(volumes);
                $("#volumespercent").attr("style","width:"+ volumes);

                var instances = Percentage(data.instanceuse,data.instances);
                $("#instancespercent_").text(instances);
                $("#instancespercent").attr("style","width:"+ instances);
            },
            error: function() {
                //请求出错处理
                alert("网络异常");
            }
        });

        $.ajax({
            url: "<%=request.getContextPath()%>/general/newsinfo",    //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性\
            type: "GET",   //请求方式
            success: function(data) {
                $('#newsnum').text(eval(data).length);
                var htm = "";
                htm+= "<li><p class='red'>您有"+ eval(data).length +"条新消息</p></li>";

                $.each(data,function(index,item) {

                    if(item.type==0){
                        var type = "系统消息";
                    }else{
                        var type = "导师消息";
                    }
                    htm+= "<li> <a href='#'><span class='photo'><img alt='avatar'' src='<%=request.getContextPath()%>/resources/img/avatar-mini.jpg'></span> <span class='subject'>";
                    htm+= "<span class='from'>"+type+"</span>"
                    htm+= "<span class='time'>刚刚</span></span>"
                    htm+= "<span class='message'>"+item.content+"</span>"
                    htm+= "</a></li>"

                });
                    htm += "<li><a href='#'>查看全部</a></li>";
                $('#newscontainer').append(htm);




            },
            error: function() {
                //请求出错处理
                alert("网络异常");
            }
        });
    });
</script>
<!--logo start-->
<a href="#" class="logo">教学<span>云平台</span></a>
<!--logo end-->
<div class="nav notify-row" id="top_menu">
    <!--  notification start -->
    <ul class="nav top-menu">
        <!-- settings start -->
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="icon-tasks"></i>
                <span class="badge bg-success">5</span>
            </a>
            <ul class="dropdown-menu extended tasks-bar">
                <div class="notify-arrow notify-arrow-green"></div>
                <li>
                    <p class="green">资源使用情况</p>
                </li>
                <li>
                    <a href="#">
                        <div class="task-info">
                            <div class="desc">处理器资源</div>
                            <div id="cpupercent_" class="percent"></div>
                        </div>
                       <div class="progress progress-striped">
                            <div id="cpupercent" class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="task-info">
                            <div class="desc">内存资源</div>
                            <div id="rampercent_" class="percent"></div>
                        </div>
                        <div class="progress progress-striped">
                            <div id="rampercent" class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="task-info">
                            <div class="desc">浮动IP</div>
                            <div id="floatingippercent_" class="percent">87%</div>
                        </div>
                        <div class="progress progress-striped">
                            <div id="floatingippercent" class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 87%">
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="task-info">
                            <div class="desc">云硬盘资源</div>
                            <div id="volumespercent_" class="percent">33%</div>
                        </div>
                        <div class="progress progress-striped">
                            <div id="volumespercent" class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 33%">
                            </div>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <div class="task-info">
                            <div class="desc">虚拟机数量</div>
                            <div id="instancespercent_" class="percent">45%</div>
                        </div>
                        <div class="progress progress-striped active">
                            <div id="instancespercent" class="progress-bar"  role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">
                            </div>
                        </div>

                    </a>
                </li>
                <li class="external">
                    <a href="#">查看详情</a>
                </li>
            </ul>
        </li>
        <!-- settings end -->
        <!-- inbox dropdown start-->
        <li id="header_inbox_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <i class="icon-envelope-alt"></i>
                <span id="newsnum" class="badge bg-important">2</span>
            </a>
            <ul id="newscontainer" class="dropdown-menu extended inbox">
                <div class="notify-arrow notify-arrow-red"></div>
               <%-- <li>
                    <p class="red">您有2条新消息</p>
                </li>
                <li>
                    <a href="#">
                        <span class="photo"><img alt="avatar" src="<%=request.getContextPath()%>/resources/img/avatar-mini.jpg"></span>
                                    <span class="subject">
                                    <span class="from">系统消息</span>
                                    <span class="time">刚刚</span>
                                    </span>
                                    <span class="message">
                                        系统维护通知。
                                    </span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="photo"><img alt="avatar" src="<%=request.getContextPath()%>/resources/img/avatar-mini2.jpg"></span>
                                    <span class="subject">
                                    <span class="from">导师消息</span>
                                    <span class="time">10分钟前</span>
                                    </span>
                                    <span class="message">
                                        虚拟机实验通知。
                                    </span>
                    </a>
                </li>

                <li>
                    <a href="#">查看全部</a>
                </li>--%>
            </ul>
        </li>
        <!-- inbox dropdown end -->
        <!-- notification dropdown start-->
        <li id="header_notification_bar" class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                <i class="icon-bell-alt"></i>
                <span class="badge bg-warning">6</span>
            </a>
            <ul class="dropdown-menu extended notification">
                <div class="notify-arrow notify-arrow-yellow"></div>
                <li>
                    <p class="yellow">您有6条提示</p>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-danger"><i class="icon-bolt"></i></span>
                        虚拟机demo-unix已成功创建。.
                        <span class="small italic"></span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-warning"><i class="icon-bell"></i></span>
                        网络demo-net已成功创建。.
                        <span class="small italic"></span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-danger"><i class="icon-bolt"></i></span>
                        虚拟机占用CPU持续过高.
                        <span class="small italic"></span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-success"><i class="icon-plus"></i></span>
                        新的云硬盘demo-net已成功创建。.
                        <span class="small italic"></span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="label label-info"><i class="icon-bullhorn"></i></span>
                        虚拟机发生错误，已自动关闭！.
                        <span class="small italic"></span>
                    </a>
                </li>
                <li>
                    <a href="#">查看更多提示</a>
                </li>
            </ul>
        </li>
        <!-- notification dropdown end -->
    </ul>
    <!--  notification end -->
</div>
<div class="top-nav ">
    <!--search & user info start-->
    <ul class="nav pull-right top-menu">
        <li>
            <input type="text" class="form-control search" placeholder="Search">
        </li>
        <!-- user login dropdown start-->
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <img alt="" src="../../resources/img/avatar-mini.jpg">
                <span class="username">demo</span>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu extended logout">
                <div class="log-arrow-up"></div>
                <li><a href="#"><i class=" icon-suitcase"></i>个人信息</a></li>
                <li><a href="#"><i class="icon-cog"></i> 设置</a></li>
                <li><a href="#"><i class="icon-bell-alt"></i> 提示</a></li>
                <li><a href="<%=request.getContextPath()%>/login/logout.do"><i class="icon-key"></i> 注销</a></li>
            </ul>
        </li>
        <!-- user login dropdown end -->
    </ul>
    <!--search & user info end-->
</div>