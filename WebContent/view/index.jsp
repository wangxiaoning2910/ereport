<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
		<title>易通-经营分析报表</title>
		<link rel="Shortcut Icon" href="<c:url value='resources/img/favicon.ico' />" />
		<!-- Bootstrap Core CSS -->
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css' />">
	    <!-- MetisMenu CSS -->
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/metisMenu.min.css' />">
	    <!-- Timeline CSS -->
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/timeline.css' />">
	    <!-- Custom CSS -->
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/sb-admin-2.css' />">
	    <!-- Custom Fonts -->
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css' />">
	    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-addtabs.css' />">
	    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value='/resources/css/bootstrap-treeview.min.css' />">
	    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/custom.css' />">
	</head>
	<body>
		<div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div id="tree"></div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper-index">
            <div class="col-md-12">
	            <div id="tabs">
	                <!-- Nav tabs -->
	                <ul class="nav nav-tabs" role="tablist">
	                    <li role="presentation" class="active">
	                    	<a href="#home" aria-controls="home" role="tab" data-toggle="tab">首页</a>
	                    </li>                    
	                </ul>
	                <!-- Tab panes -->
	                <div class="tab-content">
	                    <div role="tabpanel" class="tab-pane active" id="home">
							
	                    </div>                    
	                </div>
	            </div>
        	</div>
           
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script type="text/javascript" src="<c:url value='/resources/script/jquery-1.11.1.min.js' />"></script>
    <!-- Bootstrap Core JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/script/bootstrap.min.js' />"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/script/metisMenu.min.js' />"></script>
    <!-- Morris Charts JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/script/raphael-min.js' />"></script>
    <!-- Custom Theme JavaScript -->
    <script type="text/javascript" src="<c:url value='/resources/script/sb-admin-2.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/script/bootstrap-addtabs.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/script/bootstrap-treeview.min.js' />"></script>
	<script type="text/javascript">
		var PREVIOUS_NODEID = "";
		var ONSELECTED_NODEID = "";
	
		$('#tree').treeview({
			data: getTree(),
			highlightSelected: false,
			multiSelect: false,
			onNodeSelected: function(event, data) {
				addatab(data);
			},
			onNodeUnselected: function(event,data){
			}
		});
		function getTree(){
			var result;
			var parentmids = [];
			$.ajax({
				url:'getMenu.do',
				type:'post',
				async:false,
				dataType: 'json',
				success:function(data){
					var resultlength = data.length;
					result = data;
					for(var i = 0;i<resultlength;i++){
						parentmids[i] = result[i].parentmid;
					}
					parentmids = uniQueue(parentmids);
					for (var j = 0;j<parentmids.length;j++) {
						var parentmid = parentmids[j];
						for(var i = 0;i<resultlength;i++){
							if(parentmid == result[i].mid){
								result[i].selectable = false;
								delete(result[i]["href"]);
								result[i].state = {expanded:false}
								result[i].nodes = [];
							}
						}
						for(var i = 0;i<resultlength;i++){
							if(parentmid == result[i].parentmid){
								parentobj = getparentobj(parentmid,result);
								if(typeof(parentobj) != "undefined"){
									parentobj.nodes.unshift(result[i]);
								}
							}
						}
					}
					var loopcount = resultlength;
					for(var k = 0;k<loopcount;k++){
						var mid = result[k].mid.substring(0,1);
						if(parseInt(mid) != 1){
							var a = result.splice(k,1);
							loopcount = result.length;
							k = 0;
						}
					}
				}
			});
			return result
		}
	    $(function(){
	        $('#tabs').addtabs({monitor:'.topbar'});
	    })
	    function addatab(data){
	    	Addtabs.add({
	               id: data.nodeId,
	               title: data.text,
	               url: data.href,
	            })
	    }
	    function uniQueue(array){
	    	var arr=[]; 
	    	var m; 
	    	while(array.length>0){ 
	    		m=array[0]; 
	    		arr.push(m); 
	    		array=$.grep(array,function(n,i){ 
	    			return n==m; 
	    		},true); 
	    	} 
	    	return arr; 
	    }
	    function getparentobj(haveparentmid,result){
	    	var obj;
	    	for(var i = 0;i<result.length;i++){
	    		if(haveparentmid == result[i].mid){
	    			obj = result[i];
	    		}
	    	}
	    	return obj;
	    }
     </script>
	</body>
</html>