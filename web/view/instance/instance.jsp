<%--
  Created by IntelliJ IDEA.
  User: chenpengjiang
  Date: 2016/3/22
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <%@ include file="/resources/jsp/common_css.jsp"%>
    <%@ include file="/resources/jsp/commom_js.jsp"%>
    <%@ include file="../../resources/jsp/bootstraptables_res.jsp"%>
</head>
<body>
<!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <section class="panel">
                    <div class="col-lg-12">
                    <header class="panel-heading">
                        虚拟机列表
                    </header>
                    </div>


                    <div id="toolbar">
                        <button id="boot" class="btn btn-info">
                            <i class="glyphicon glyphicon-info"></i> 新增
                        </button>
                    </div>

                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">功能</a></li>
                        <li><a href="#">另一个功能</a></li>
                        <li><a href="#">其他</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                    </ul>

                  <div class="col-lg-12">
                     <table  id="table_comment"
                   data-height="700"
                   data-query-params="queryParams"
                   data-pagination="true"
                             data-toolbar="#toolbar"

                   >
                <thead>
                <tr>
                    <th data-field="servername">云主机名称</th>
                    <th data-field="imagename">镜像名称</th>
                    <th data-field="flavorname">配置</th>
                    <th data-field="keyname">安全值对</th>
                    <th data-field="state">状态</th>
                    <th data-field="ipaddress" data-formatter="ipFormatter">ip地址</th>
                    <th data-field="powerstate" data-formatter="dianyuanFormatter">电源状态</th>
                    <th data-field="createtime" data-formatter="timeFormatter">创建时间</th>
                    <th data-field="" data-formatter="optionFormatter">操作</th>
                </tr>
                </thead>
            </table>
                  </div>
                </section>
            </div>
        </section>
    </section>
    <!--main content end-->

<%--模态框--%>
<div class="modal fade" id="bootModal" style="margin-top: 200px" role="dialog" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="exampleModalLabel">启动新虚拟机</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">虚拟机名称</label>
                        <input type="text" name="name" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="peizhi-name" class="control-label">配置</label>
                        <select  class="form-control"><option>最低配置</option></select>
                    </div>
                    <div class="form-group">
                        <label for="net-name" class="control-label">网络选择</label>
                        <select  class="form-control"><option>网络1</option></select>
                    </div>
                    <div class="form-group">
                        <label for="image-name" class="control-label">镜像选择</label>
                        <select  class="form-control"><option>Linux(10M)</option></select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" onclick="bootserver()" class="btn btn-primary">启动</button>
            </div>
        </div>
    </div>
</div>
<script>

    //owl carousel

    $(document).ready(function() {

        $.getJSON('<%=path%>/Instance/getcurInstance.do',
                function(data) {
                    $('#table_comment').bootstrapTable({
                        data: data.data
                    });
                    $('#table_comment').bootstrapTable('load', data.data);
                    $('#table_comment').bootstrapTable('refresh', {silent: true});
                });

        $('#boot').on('click',function(){
            $('#bootModal').modal('show');
        });

    });
    function timeFormatter(value) {
        return new Date(parseInt(value)).toLocaleString().replace(/:\d{1,2}$/,' ');
    }
    function ipFormatter(value){
         var arr=value.split("{");
        var ip = arr[2].split(",");
        var ipadd = ip[0].split("=");
        return ipadd[1];
    }

    function dianyuanFormatter(value){
        if(value==1){
            return "运行中";
        }else if(value==4){
            return "已关机";
        }else{
            return "其他状态";
        }
    }

    function optionFormatter(value,row){
        var method = "vnc('"+row.serverid+"')"

      return  '<div class="btn-group"><button type="button" class="btn btn-primary dropdown-toggle"data-toggle="dropdown"> 操作 <span class="caret"></span> </button><ul class="dropdown-menu" role="menu"><li><a onclick="'+ method + '">控制台</a></li><li><a href="#">终止实例</a></li><li><a href="#">重启实例</a></li><li><a href="#">绑定浮动ip</a></li><li><a href="#">解除浮动ip绑定</a></li><li><a href="#">编辑安全组</a></li><li><a href="#">查看日志</a></li><li><a href="#">调整配置</a></li><li><a href="#">查看日志</a></li><li class="divider"></li><li><a href="#">删除此主机</a></li></ul></div>';
    }

    function vnc(serverid){

        $.ajax({
            url: "<%=request.getContextPath()%>/Instance/getconsole", //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性\
            type: "GET",   //请求方式
            data: {serverid:serverid},
            success: function(data) {
                window.open(data.url);



            },
            error: function() {
                //请求出错处理
                alert("网络异常");
            }
        });

    }

    function bootserver(){
        var name = $('#recipient-name').val();
        $.ajax({
            url: "<%=request.getContextPath()%>/Instance/bootnewserver", //请求的url地址
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性\
            type: "POST",   //请求方式
            data: {name:name},
            success: function(data) {

                if(data.flag=='success'){

                    $('#bootModal').modal('hide');
                    $.getJSON('<%=path%>/Instance/getcurInstance.do',
                            function(data) {
                                $('#table_comment').bootstrapTable({
                                    data: data.data
                                });
                                $('#table_comment').bootstrapTable('load', data.data);
                                $('#table_comment').bootstrapTable('refresh', {silent: true});
                            });


                }else{
                    alert("网络异常");
                }


            },
            error: function() {
                //请求出错处理
                alert("网络异常");
            }
        });

    }


    //custom select box

    $(function(){
        $('select.styled').customSelect();
    });

</script>
</body>
</html>