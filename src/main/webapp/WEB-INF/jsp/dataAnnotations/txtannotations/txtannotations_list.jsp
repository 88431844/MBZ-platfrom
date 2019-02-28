<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							
						<!-- 检索  -->
						<form action="txtannotations/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								 <td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入账户名称" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords}" placeholder="这里输入账户名称"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<%--<td style="padding-left:2px;"><input class="span10 date-picker" name="lastLoginStart" id="lastLoginStart"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始日期" title="开始日期"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastLoginEnd" name="lastLoginEnd"  value="" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束日期" title="结束日期"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="selectIsLock" id="selectIsLock" data-placeholder="请选择题锁状态" style="vertical-align:top;width: 120px;">
									<option value="">全部</option>
									<option value="0" <c:if test="${'0' eq pd.selectIsLock }">selected</c:if>>未标注</option>
									<option value="1" <c:if test="${'1' eq pd.selectIsLock }">selected</c:if>>正在标注</option>
									<option value="2" <c:if test="${'2' eq pd.selectIsLock }">selected</c:if>>已完成</option>
									<option value="3" <c:if test="${'3' eq pd.selectIsLock }">selected</c:if>>正在审核</option>
									<option value="4" <c:if test="${'4' eq pd.selectIsLock }">selected</c:if>>审核完成</option>
								  	</select>
								</td>
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<td ><span>剩余未标注总量：${pd.unmarkedNum},剩余未审核总量：${pd.uncheckedNum},今日团队完成总量：${pd.overNumToday},当前用户今日标注量：${pd.overNumTodayByUserFromTagging},当前用户今日审核量：${pd.overNumTodayByUserFromCheck}</span></td>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<!-- <th class="center" style="width:50px;">序号</th> -->
									<th class="center">文本序号</th>
									<th class="center">文本标题</th>
									<!-- <th class="center">项目类型</th> -->
									<th class="center">状态</th>
									<th class="center">题锁</th>
									<th class="center">操作人</th>
									<th class="center">审核状态</th>
									<th class="center">审核人员</th>
									<th class="center">修改耗时</th>
									<th class="center">修改时间</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.TXTANNOTATIONS_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<%-- <td class='center' style="width: 30px;">${vs.index+1}</td> --%>
											<td class='center' style="color:#FF7F00;cursor:pointer;" 
											     onclick="getCheckTxtContent('${var.TXTANNOTATIONS_ID}')">${var.TXTID}</td>
											<td class='center'>
												<a style="color:#FF7F00;cursor:pointer;" 
											     onclick="getTxtContent('${var.TXTANNOTATIONS_ID}')">${var.TXTTITLE}</a>
											</td>
											<%-- <td class='center'>
												<c:if test="${var.TYPE == 0 }">
													墨迹天气
												</c:if>
												<c:if test="${var.TYPE == 1 }">
													其他
												</c:if>
											</td> --%>
											<td class='center'>
												<c:if test="${var.STATUS == 0 }">
													<span style="color:red;">无效数据</span>
												</c:if>
												<c:if test="${var.STATUS == 1 }">
													有效数据
												</c:if>
											</td>
											<td class='center'>
												<c:if test="${var.isLock == 0}">
													<span style="color:red;">未标注</span>
												</c:if>
												<c:if test="${var.isLock == 1}">
													正在标注
												</c:if>
												<c:if test="${var.isLock == 2}">
													已完成
												</c:if>
												<c:if test="${var.isLock == 3}">
													正在审核
												</c:if>
												<c:if test="${var.isLock == 4}">
													审核完成
												</c:if>
											</td>
											<td class='center'>${var.userName}</td>
											<td class='center'>
												<c:if test="${var.checkStatus == 0 }">
													未通过
												</c:if>
												<c:if test="${var.checkStatus == 1 }">
													已通过
												</c:if>
											</td>
											<td class='center'>${var.checkUserName}</td>
											<td class='center'>${var.editTime}分钟</td>
											<td class='center'>${var.updateTime}</td>
											<%-- <td class='center'>${var.updateTime}分钟</td> --%>
											<%-- <td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<c:if test="${QX.edit == 1 }">
													<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.TXTANNOTATIONS_ID}');">
														<i class="ace-icon fa fa-pencil-square-o bigger-120" title="编辑"></i>
													</a>
													</c:if>
													<c:if test="${QX.del == 1 }">
													<a class="btn btn-xs btn-danger" onclick="del('${var.TXTANNOTATIONS_ID}');">
														<i class="ace-icon fa fa-trash-o bigger-120" title="删除"></i>
													</a>
													</c:if>
												</div>
												<div class="hidden-md hidden-lg">
													<div class="inline pos-rel">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
															<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
														</button>
			
														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
															<c:if test="${QX.edit == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="edit('${var.TXTANNOTATIONS_ID}');" class="tooltip-success" data-rel="tooltip" title="修改">
																	<span class="green">
																		<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
															<c:if test="${QX.del == 1 }">
															<li>
																<a style="cursor:pointer;" onclick="del('${var.TXTANNOTATIONS_ID}');" class="tooltip-error" data-rel="tooltip" title="删除">
																	<span class="red">
																		<i class="ace-icon fa fa-trash-o bigger-120"></i>
																	</span>
																</a>
															</li>
															</c:if>
														</ul>
													</div>
												</div>
											</td> --%>
										</tr>
									
									</c:forEach>
									</c:if>
									<c:if test="${QX.cha == 0 }">
										<tr>
											<td colspan="100" class="center">您无权查看</td>
										</tr>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }">
									<a class="btn btn-sm btn-success" onclick="add();">新增</a>
									</c:if>
									<c:if test="${QX.del == 1 }">
									<a class="btn btn-sm btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div>
						</form>
					
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
			
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>txtannotations/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>txtannotations/delete.do?TXTANNOTATIONS_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>txtannotations/goEdit.do?TXTANNOTATIONS_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>txtannotations/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		};
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>txtannotations/excel.do';
		}
		//根据ID获取文章，仅限于审核使用
		function getCheckTxtContent(id){
			$.ajax({
				type: "POST",
				url: 'txtannotations/getIsLock.do',
		    	data:{
		    		//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		    		getTxtContent : 0,
		    		TXTANNOTATIONS_ID : id
		    	},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					 if(data == 0 || data == 2){
						 var currentPage = "${page.currentPage}";
						 var showCount = "${page.showCount}";
						 var selectIsLock = "${pd.selectIsLock}";
						 var keywords = "${pd.keywords}";
						 window.location.href='<%=basePath%>txtannotations/showContentById.do?getTxtContent=0&TXTANNOTATIONS_ID=' + id + '+&currentPage=' + currentPage + '&showCount=' + showCount+'&selectIsLock='+selectIsLock+'&keywords='+keywords; 
					 }else if(data == 1 ){
						alert("此题已经有人在标注了，请标注其他题目。"); 
					 }else if(data == 3 ){
						alert("此题正在审核，请标注或审核其他题目。"); 
					 }else if(data == 4){
						//利用对话框返回的值 （true 或者 false）  
					        if (confirm("此题已经审核完成，确定要进行修改么？")) {  
					        	var currentPage = "${page.currentPage}";
								var showCount = "${page.showCount}";
								var selectIsLock = "${pd.selectIsLock}";
								var keywords = "${pd.keywords}";
								window.location.href='<%=basePath%>txtannotations/showContentById.do?getTxtContent=0&TXTANNOTATIONS_ID=' + id + '+&currentPage=' + currentPage + '&showCount=' + showCount+'&selectIsLock='+selectIsLock+'&keywords='+keywords; 
					        }else {  
					            //点击取消调用此步骤
					        }   
					 }else{
					
					 }
				}
			});
		}
		//根据ID获取文章内容
		function getTxtContent(id){
			
			$.ajax({
				type: "POST",
				url: 'txtannotations/getIsLock.do',
		    	data:{
		    		//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		    		getTxtContent : 0,
		    		TXTANNOTATIONS_ID : id
		    	},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){
					 if(data == 0){
						 var currentPage = "${page.currentPage}";
						 var showCount = "${page.showCount}";
						 var selectIsLock = "${pd.selectIsLock}";
						 var keywords = "${pd.keywords}";
						 window.location.href='<%=basePath%>txtannotations/showContentById.do?getTxtContent=1&TXTANNOTATIONS_ID=' + id + '+&currentPage=' + currentPage + '&showCount=' + showCount+'&selectIsLock='+selectIsLock+'&keywords='+keywords; 
					 }else if(data == 1){
						alert("此题已经有人在标注了，请标注其他题目。"); 
					 }else if(data == 3 ){
						alert("此题正在审核，请标注或审核其他题目。"); 
					 }else{
						//利用对话框返回的值 （true 或者 false）  
					        if (confirm("此题已经标注完成或已审核通过 ，确定要进行修改么？")) {  
					        	var currentPage = "${page.currentPage}";
								var showCount = "${page.showCount}";
								var selectIsLock = "${pd.selectIsLock}";
								var keywords = "${pd.keywords}";
								window.location.href='<%=basePath%>txtannotations/showContentById.do?getTxtContent=1&TXTANNOTATIONS_ID=' + id + '+&currentPage=' + currentPage + '&showCount=' + showCount+'&selectIsLock='+selectIsLock+'&keywords='+keywords; 
					        }else {  
					            //点击取消调用此步骤
					        }   
					 }
				}
			});
		 
		}
	</script>


</body>
</html>