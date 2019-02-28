<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${pd.SYSNAME}</title>
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
<link rel="stylesheet" href="static/ace/css/webline.css" />
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-header position-relative">
						<table style="width: 10%;">
						<input id="TXTANNOTATIONS_ID" type = "hidden" value="${txtPd.TXTANNOTATIONS_ID}"/>
						<input id="getTxtContentType" type = "hidden" value="${pd.getTxtContent}"/>
							<tr>  
								<td style="vertical-align: top;">
										<a style="cursor:pointer;" class="btn btn-default btn-success" onclick="goback('${parameter.begintime}','${parameter.endtime }','${parameter.keyword }','${txtPd.isLock }');">返回</a>
								</td>
                                 <td style="vertical-align: top;">
	                                 <input id ="status" type = "hidden" value="${txtPd.STATUS}"/>
	                                 <button id="butt1" class="btn btn-default btn-success">无效数据</button>
	                                 <button id="butt2" class="btn btn-default btn-success">有效数据</button>
							     </td>
							     <td style="vertical-align: top;">
	                                 <input id ="checkStatus" type = "hidden" value="${txtPd.checkStatus}"/>
	                                 <button id="butt3" class="btn btn-default btn-success">未审核</button>
	                                 <button id="butt4" class="btn btn-default btn-success">已审核</button>
							     </td>
							</tr>
						</table>
					</div>
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<h3 id="txtTitle" style="text-align: center;" onmouseup="SelectText('title')">
							<c:if test="${txtPd.resultTitle == null || txtPd.resultTitle == ''}">${txtPd.TXTTITLE}</c:if>
							<c:if test="${txtPd.resultTitle != null && txtPd.resultTitle != ''}">${txtPd.resultTitle}</c:if>
							</h3>
							<hr />
							<div id="data">
								<ul style="text-align:center">
									<span>日期：${txtPd.TXTDATE}</span>
									<span>&nbsp;&nbsp;相关性：${txtPd.relevance}</span>
								</ul>
							</div>
						</div>
						<div class="col-md-1 column"></div>
					</div>
				</div>
				<!-- 正文 -->
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<div id="txtContent" class="main-content-inner" style="font-size:18px;" onmouseup="SelectText('txtContent')">
							<c:if test="${txtPd.resultContent == null || txtPd.resultContent == ''}">${txtPd.txtContent}</c:if>
							<c:if test="${txtPd.resultContent != null && txtPd.resultContent != ''}">${txtPd.resultContent}</c:if>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-1 column"></div>
						<div class="col-md-10 column">
							<div class="main-content-inner">
							 <c:if test="${pd.sourcename != ''} || ${pd.sourcename == null }">
								<p>附件：
									<a href="${pd.sourceUrl }">${pd.sourcename } </a>
								</p>
							</c:if>
							</div>
						</div>
					</div>
				</div> --%>
			</div>
		</div>
		<!-- 页面底部js¨ -->
		<%@ include file="../../system/index/foot.jsp"%>
		<!-- 删除时确认窗口 -->
		<script src="static/ace/js/bootbox.js"></script>
		<!-- ace scripts -->
		<script src="static/ace/js/ace/ace.js"></script>
		<!-- 日期框 -->
		<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
		<!-- 下拉框 -->
		<script src="static/ace/js/chosen.jquery.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
</body>
<script type="text/javascript">
$(function(){
	var status = $("#status").val();
	   if(1 == status){
	        $('#butt1').hide();
	        $('#butt2').show();
	    }else{
	        $('#butt1').show();
	        $('#butt2').hide();
	    }
	var checkStatus = $("#checkStatus").val();
	   if(1 == checkStatus){
	        $('#butt3').hide();
	        $('#butt4').show();
	    }else{
	        $('#butt3').show();
	        $('#butt4').hide();
	    }
});
$('#butt1').click(function (){
    $('#butt1').hide();
    $('#butt2').show();
    updateTxtStatus('1');
});
$('#butt2').click(function (){
    $('#butt2').hide();
    $('#butt1').show();
    updateTxtStatus('0');
});
$('#butt3').click(function (){
    $('#butt3').hide();
    $('#butt4').show();
    updateTxtCheckStatus('1');
});
$('#butt4').click(function (){
    $('#butt4').hide();
    $('#butt3').show();
    updateTxtCheckStatus('0');
});
var quick_type = '${quickParameter.type}';
//修改数据是否有效的状态
function updateTxtStatus(status){
	$.ajax({
	    url :'txtannotations/updateTxtStatus.do',
	    type : 'POST',
	    data : {
	    	status :status,
	    	isLock : 1,
	    	id : $('#TXTANNOTATIONS_ID').val()
	    },
	    timeout : 500000,
	    dataType : 'html',
	    cache: false,
        success : function(msg) {
        }
	});
} 
//修改数据是否审核的状态
function updateTxtCheckStatus(checkStatus){
	$.ajax({
	    url :'txtannotations/updateTxtCheckStatus.do',
	    type : 'POST',
	    data : {
	    	checkStatus :checkStatus,
	    	id : $('#TXTANNOTATIONS_ID').val()
	    },
	    timeout : 500000,
	    dataType : 'html',
	    cache: false,
        success : function(msg) {
        }
	});
} 
</script>
<script type="text/javascript">
	$(top.hangge());
	//判断是否操作
	var isLock = 0;
	
	// 返回
	function goback(begintime,endtime,keyword,startIsLock){
		var currentPage = "${pd.currentPage}";
		var showCount = "${pd.showCount}";
		var selectIsLock = "${pd.selectIsLock}";
		var keywords = "${pd.keywords}";
		
		//当文章已经被修改或者正在修改，对没有做任何操作点击返回，则保持文章现有状态不变
		if((isLock == 0 && startIsLock == 2) || (isLock == 0 && startIsLock == 1) || (isLock == 0 && startIsLock == 3)){
			isLock = startIsLock;
		}else if (isLock == 1){
			isLock = 2;
		}
		
		//异步存储数据
		var txtContent = $("#txtContent").text().replace(" ","");
		var txtTitle =  $("#txtTitle").text().replace(" ","");
		//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
		var getTxtContentType1 = $('#getTxtContentType').val();
		//修改后退出文章详情页之后再次进行提交
		//同时对文章进行解锁
		$.ajax({
		    url :'txtannotations/updateResultContent.do',
		    type : 'POST',
		    data : {
		    	//goBack记录其是返回按钮进入，1是非返回按钮
		    	goBack : 1,
		    	isLock : isLock,
		    	resultContent : txtContent.toString(),
		    	resultTitle : txtTitle.toString(),
		    	getTxtContentType : getTxtContentType1,
		    	id : $('#TXTANNOTATIONS_ID').val()
		    },
		    timeout : 500000,
		    dataType : 'text',
		    cache: false,
	        success : function(data) {
	        },
	        error : function(XMLResponse){
	        }
		});
		
		window.location.href='<%=basePath%>txtannotations/list.do?keywords='+keywords+'&begintime='+begintime+'&endtime='+endtime+'&currentPage=' + currentPage + '&showCount=' + showCount +'&selectIsLock='+selectIsLock;
	}
	
	
	//选中文字
	function SelectText(titleOrContent){
		var selectTextContent = window.getSelection();
		var overSelectText ;//保存需要二次修改的选择词
		var backupsContentSelectText = selectTextContent;
		var start = window.getSelection().anchorOffset; //开始位置
		var end = window.getSelection().focusOffset;  //结束位置
		if(selectTextContent != null && selectTextContent != ""){
			
			var replaceContent;
			var txtContent = document.getElementById("txtContent");
			var txtTitle = document.getElementById("txtTitle");
			
			var regg = "\\(*(.+?)\\)/.*";
			//判断选择的部分是否存在()/结构的数据
			var reggFlag = selectTextContent.toString().match(regg) != null;
			if(reggFlag){
				//获取之前存储标签
				var oldOverSelectText = selectTextContent.toString().split(")/")[1];
				replaceContent = prompt(selectTextContent+"：替换成什么？",oldOverSelectText);
			}else{
				replaceContent = prompt(selectTextContent+"：替换成什么？");
			}
			
			//选择取消时不进行修改
			if(replaceContent != null){
				
				if(replaceContent == ""){
					//获取括号内文字
					var reg = "\\(*(.+?)\\)";
					overSelectText = selectTextContent.toString().match(reg)[1];
					//判断是不是标题
					if(titleOrContent == "txtContent"){
						txtContent.innerHTML = changeString(txtContent.innerHTML,start,end,selectTextContent,overSelectText);
					}else{
						txtTitle.innerHTML = changeString(txtTitle.innerHTML,start,end,selectTextContent,overSelectText);
					}
				}else{
					//判断选择内容是否是()/结构的数据，如果是直接进行替换
					if(reggFlag){
						//获取括号内文字
						var reg = "\\(*(.+?)\\)";
						overSelectText = selectTextContent.toString().match(reg)[1];
						//判断是不是标题
						if(titleOrContent == "txtContent"){
							var changeStr = "("+overSelectText+")/"+replaceContent;
							txtContent.innerHTML = changeString(txtContent.innerHTML,start,end,selectTextContent,changeStr);
							//txtContent.innerHTML = txtContent.innerHTML.replace(new RegExp(selectTextContent,"gm"),"("+backupsContentSelectText+")/"+replaceContent);
						}else{
							var changeStr = "("+overSelectText+")/"+replaceContent;
							txtTitle.innerHTML = changeString(txtTitle.innerHTML,start,end,selectTextContent,changeStr);
							//txtTitle.innerHTML = txtTitle.innerHTML.replace(new RegExp(selectTextContent,"gm"),"("+backupsContentSelectText+")/"+replaceContent);
						}
					}else{
						//判断是不是标题
						if(titleOrContent == "txtContent"){
							var changeStr = "("+backupsContentSelectText+")/"+replaceContent;
							txtContent.innerHTML = changeString(txtContent.innerHTML,start,end,selectTextContent,changeStr);
							//txtContent.innerHTML = txtContent.innerHTML.replace(new RegExp(selectTextContent,"gm"),"("+backupsContentSelectText+")/"+replaceContent);
						}else{
							var changeStr = "("+backupsContentSelectText+")/"+replaceContent;
							txtTitle.innerHTML = changeString(txtTitle.innerHTML,start,end,selectTextContent,changeStr);
							//txtTitle.innerHTML = txtTitle.innerHTML.replace(new RegExp(selectTextContent,"gm"),"("+backupsContentSelectText+")/"+replaceContent);
						}
					}
				}
				
				isLock = 1;
				var txtContentFinal = $("#txtContent").text();
				var txtTitleFinal =  $("#txtTitle").text();
				//修改锁状态的来源，0：标示从审核入口进入，1：标示从标注入口进入
				var getTxtContentType = $('#getTxtContentType').val();
				//异步存储数据
				$.ajax({
				    url :'txtannotations/updateResultContent.do',
				    type : 'POST',
				    data : {
				    	//goBack记录其是返回按钮进入，0是非返回按钮
				    	goBack : 0,
				    	isLock : isLock,
				    	resultContent : txtContentFinal,
				    	resultTitle : txtTitleFinal,
				    	getTxtContentType : getTxtContentType,
				    	id : $('#TXTANNOTATIONS_ID').val()
				    },
				    timeout : 500000,
				    dataType : 'json',
				    cache: false,
			        success : function(data) {
			        }
				});
			}
			
		}
	} 
	
	//allstr:原始字符串，start,开始位置,end：结束位  置,str：要改变的字，changeStr:改变后的字
	function changeString(allstr,start,end,str,changeStr){
		if(start <= end){
		 	if(allstr.substring(start,end) == str){
		      return allstr.substring(0,start)+changeStr+allstr.substring(end,allstr.length); 
		 	}else{
		 		return allstr.toString(); 
		    }
		}else{
			if(allstr.substring(end,start) == str){
		      return allstr.substring(0,end)+changeStr+allstr.substring(start,allstr.length); 
		 	}else{
		 		return allstr.toString(); 
		    }
		}
	}
</script>
</html>

