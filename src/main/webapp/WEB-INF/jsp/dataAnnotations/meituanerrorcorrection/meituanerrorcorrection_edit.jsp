<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
					
					<form action="meituanerrorcorrection/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="MEITUANERRORCORRECTION_ID" id="MEITUANERRORCORRECTION_ID" value="${pd.MEITUANERRORCORRECTION_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">ID:</td>
								<td><input type="text" name="ID" id="ID" value="${pd.ID}" maxlength="255" placeholder="这里输入ID" title="ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">城市ID:</td>
								<td><input type="text" name="CITYID" id="CITYID" value="${pd.CITYID}" maxlength="255" placeholder="这里输入城市ID" title="城市ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">城市名称:</td>
								<td><input type="text" name="CITYNAME" id="CITYNAME" value="${pd.CITYNAME}" maxlength="255" placeholder="这里输入城市名称" title="城市名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">搜索词:</td>
								<td><input type="text" name="KEYWORD" id="KEYWORD" value="${pd.KEYWORD}" maxlength="255" placeholder="这里输入搜索词" title="搜索词" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">改写词:</td>
								<td><input type="text" name="REWRITEKEYWORD" id="REWRITEKEYWORD" value="${pd.REWRITEKEYWORD}" maxlength="255" placeholder="这里输入改写词" title="改写词" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">结果:</td>
								<td><input type="text" name="RESULT" id="RESULT" value="${pd.RESULT}" maxlength="255" placeholder="这里输入结果" title="结果" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">修改人id:</td>
								<td><input type="text" name="USERID" id="USERID" value="${pd.USERID}" maxlength="255" placeholder="这里输入修改人id" title="修改人id" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">修改人姓名:</td>
								<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="255" placeholder="这里输入修改人姓名" title="修改人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）:</td>
								<td><input type="number" name="ISLOCK" id="ISLOCK" value="${pd.ISLOCK}" maxlength="32" placeholder="这里输入题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）" title="题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态（0:未回收，1：已回收）:</td>
								<td><input type="number" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="32" placeholder="这里输入状态（0:未回收，1：已回收）" title="状态（0:未回收，1：已回收）" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核人ID:</td>
								<td><input type="text" name="CHECKUSERID" id="CHECKUSERID" value="${pd.CHECKUSERID}" maxlength="255" placeholder="这里输入审核人ID" title="审核人ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核人姓名:</td>
								<td><input type="text" name="CHECKUSERNAME" id="CHECKUSERNAME" value="${pd.CHECKUSERNAME}" maxlength="255" placeholder="这里输入审核人姓名" title="审核人姓名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核状态（0：未通过，1.已通过）:</td>
								<td><input type="number" name="CHECKSTATUS" id="CHECKSTATUS" value="${pd.CHECKSTATUS}" maxlength="32" placeholder="这里输入审核状态（0：未通过，1.已通过）" title="审核状态（0：未通过，1.已通过）" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">修改时间:</td>
								<td><input class="span10 date-picker" name="UPDATETIME" id="UPDATETIME" value="${pd.UPDATETIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="修改时间" title="修改时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">写入时间:</td>
								<td><input type="text" name="CREATETIME" id="CREATETIME" value="${pd.CREATETIME}" maxlength="255" placeholder="这里输入写入时间" title="写入时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
						
					</form>
	
					<div id="zhongxin2" class="center" style="display:none"><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//保存
		function save(){
			if($("#ID").val()==""){
				$("#ID").tips({
					side:3,
		            msg:'请输入ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ID").focus();
			return false;
			}
			if($("#CITYID").val()==""){
				$("#CITYID").tips({
					side:3,
		            msg:'请输入城市ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CITYID").focus();
			return false;
			}
			if($("#CITYNAME").val()==""){
				$("#CITYNAME").tips({
					side:3,
		            msg:'请输入城市名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CITYNAME").focus();
			return false;
			}
			if($("#KEYWORD").val()==""){
				$("#KEYWORD").tips({
					side:3,
		            msg:'请输入搜索词',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#KEYWORD").focus();
			return false;
			}
			if($("#REWRITEKEYWORD").val()==""){
				$("#REWRITEKEYWORD").tips({
					side:3,
		            msg:'请输入改写词',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REWRITEKEYWORD").focus();
			return false;
			}
			if($("#RESULT").val()==""){
				$("#RESULT").tips({
					side:3,
		            msg:'请输入结果',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RESULT").focus();
			return false;
			}
			if($("#USERID").val()==""){
				$("#USERID").tips({
					side:3,
		            msg:'请输入修改人id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERID").focus();
			return false;
			}
			if($("#USERNAME").val()==""){
				$("#USERNAME").tips({
					side:3,
		            msg:'请输入修改人姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERNAME").focus();
			return false;
			}
			if($("#ISLOCK").val()==""){
				$("#ISLOCK").tips({
					side:3,
		            msg:'请输入题锁（0：未标注，1：正在标注，2.标注完成，3.正在审核，4.审核完成）',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ISLOCK").focus();
			return false;
			}
			if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入状态（0:未回收，1：已回收）',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
			return false;
			}
			if($("#CHECKUSERID").val()==""){
				$("#CHECKUSERID").tips({
					side:3,
		            msg:'请输入审核人ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKUSERID").focus();
			return false;
			}
			if($("#CHECKUSERNAME").val()==""){
				$("#CHECKUSERNAME").tips({
					side:3,
		            msg:'请输入审核人姓名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKUSERNAME").focus();
			return false;
			}
			if($("#CHECKSTATUS").val()==""){
				$("#CHECKSTATUS").tips({
					side:3,
		            msg:'请输入审核状态（0：未通过，1.已通过）',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKSTATUS").focus();
			return false;
			}
			if($("#UPDATETIME").val()==""){
				$("#UPDATETIME").tips({
					side:3,
		            msg:'请输入修改时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#UPDATETIME").focus();
			return false;
			}
			if($("#CREATETIME").val()==""){
				$("#CREATETIME").tips({
					side:3,
		            msg:'请输入写入时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATETIME").focus();
			return false;
			}
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>