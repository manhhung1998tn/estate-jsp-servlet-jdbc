<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Blank Page - Ace Admin</title>

<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/font-awesome/4.5.0/css/font-awesome.min.css'/>" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/fonts.googleapis.com.css'/>" />

<!-- ace styles -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/ace.min.css'/>"
	class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace-part2.min.css'/>" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/ace-skins.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/assets/css/ace-rtl.min.css'/>" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace-ie.min.css'/>" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script
	src="<c:url value='/template/admin/assets/js/ace-extra.min.js'/>"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="<c:url value='/template/admin/assets/js/html5shiv.min.js'/>"></script>
		<script src="<c:url value='/template/admin/assets/js/respond.min.js'/>"></script>
		<![endif]-->
</head>
<body>

	<%@ include file="/common/admin/header.jsp"%>
	<div class="main-container ace-save-state" id="main-container">

		<%@ include file="/common/admin/menu.jsp"%>

		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
						</li>
						<li class="active">Dashboard</li>
					</ul>
					<!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text"
								placeholder="Search ..." class="nav-search-input"
								id="nav-search-input" autocomplete="off"> <i
								class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- /.nav-search -->
				</div>
				<div class="page-content">
					<decorator:body />
				</div>



				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->


	</div>
	
	
	
	
	<%@ include file="/common/admin/footer.jsp"%>
	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script
		src="<c:url value='/template/admin/assets/js/jquery-2.1.4.min.js'/>"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="<c:url value='/template/admin/assets/js/jquery-1.11.3.min.js'/>"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='<c:url value='/template/admin/assets/js/jquery.mobile.custom.min.js'/>'>"
							+ "<"+"/script>");
	</script>
	<script
		src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

	<!-- page specific plugin scripts -->

	<!-- ace scripts /template-->
	<script
		src="<c:url value='/template/admin/assets/js/ace-elements.min.js'/>"></script>
	<script src="<c:url value='/template/admin/assets/js/ace.min.js'/>"></script>

	<!-- inline scripts related to this page -->
</body>
</html>