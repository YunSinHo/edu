<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="resources/css/admin/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="resources/css/admin/sb-admin-2.min.css" rel="stylesheet">
    <link href="resources/air-datepicker/dist/css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">
    <!-- Air datepicker css -->
    <link href="resources/css/admin/tables.css" rel="stylesheet">
	<link href="resources/css/admin/dataTables.bootstrap4.min.css" rel="stylesheet">
   <style>
    	img{
			width:100%;
			vertical-align:middle;
		}
		.card-body{
			position:relative !important;
			padding:0 !important;
		}
		.dayarea{
			position: absolute;
		    top: 4%;
		    right: 6.5%;
		    width: 15%;
		    height: 2.3vw;
		}
		.daybox{
			width:100%;
			height:100%;
			font-size:1.6783vw;
		}
		.classarea{
		    position: absolute;
		    top: 13.6%;
		    right: 22%;
		    width: 20%;
		    height: 3.4vw;
		}
		.classbox{
		    width: 100%;
		    height: 100%;
		    font-size: 2.2378vw;
		    text-align:center;
		}
		.namearea{
		    position: absolute;
		    top: 30%;
		    left: 24.5%;
		    width: 15.5%;
		    height: 3vw;
		}
		.namebox{
		    width: 100%;
		    height: 100%;
		    font-size: 2.2378vw;
		    text-align:center;
		}
		.booksarea{
		    position: absolute;
		    top: 30%;
		    right: 14%;
		    width: 27%;
		    height: 3vw;
		}
		.booksbox{
		    width: 100%;
		    height: 100%;
		    font-size: 2.2378vw;
		    text-align:center;
		}
		input[type=radio]{
			width:1.3986vw;
			height:1.3986vw;
		}
		.memorization{
		    position: absolute;
		    top: 54.1%;
		    left: 32%;
		    width: 17.5%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.reportcheck{
		    position: absolute;
		    top: 54.1%;
		    left: 69.8%;
		    width: 17%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.readcheck{
		    position: absolute;
		    top: 59.9%;
		    left: 32%;
		    width: 17.5%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.transcheck{
		    position: absolute;
		    top: 59.9%;
		    left: 69.8%;
		    width: 17%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.solvingcheck{
		    position: absolute;
		    top: 66%;
		    left: 32%;
		    width: 17.5%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.listencheck{
		    position: absolute;
		    top: 65.7%;
		    left: 69.8%;
		    width: 17%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.gettingcheck{
		    position: absolute;
		    top: 71.4%;
		    left: 32%;
		    width: 17.4%;
		    height: 4vw;
		    text-align:center;
		}
		.gettingbox{
			width:100%;
    		height: 50%;
		}
		.showdowncheck{
		    position: absolute;
		    top: 71.7%;
		    left: 69.8%;
		    width: 17%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.speechcheck{
		    position: absolute;
		    top: 78.5%;
		    left: 32%;
		    width: 17.5%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.perfectioncheck{
		    position: absolute;
		    top: 78.4%;
		    left: 69.8%;
		    width: 17%;
		    height: 3vw;
		    text-align: center;
		    font-size: 1.9vw;
		}
		.perfectionbox{
			width:100%;
			height:100%;
			font-size:1.9vw;
    		text-align: center;
		}
		.resultarea{
		    position: absolute;
		    top: 84%;
		    left: 20%;
		    width: 66.7%;
		    height: 10.2vw;
		}
		.resultbox{
		    width: 100%;
		    height: 100%;
    		resize: none;
		    font-size: 1.3986vw;
		}
		.pagebox{
		    width: 70%;
		    height: 100%;
		    font-size: 2.2378vw;
		    text-align:center;
		}
    </style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.mdo">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
            </a>
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                메뉴
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>전체 교재</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">목록</h6>
                        <a class="collapse-item" href="maincontent.mdo">모두 보기</a>
                        <a class="collapse-item" href="textcontents.mdo">독해</a>
                        <a class="collapse-item" href="#">문법</a>
                        <a class="collapse-item" href="#">어휘</a>
                        <a class="collapse-item" href="#">듣기</a>
                        <a class="collapse-item" href="#">영작</a>
                        <a class="collapse-item" href="#">내신</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseone"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-table"></i>
                    <span>학습자 관리</span>
                </a>
                <div id="collapseone" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">목록</h6>
                        <a class="collapse-item" href="tables.mdo">학습자 목록</a>
                        <a class="collapse-item" href="#">반 배정</a>
                        <a class="collapse-item" href="#">숙제 배정</a>
                        <a class="collapse-item" href="sendmessage.mdo">문자 전송</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Components</span>
                </a>
                <div id="collapse" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Components:</h6>
                        <a class="collapse-item" href="buttons.mdo">Buttons</a>
                        <a class="collapse-item" href="cards.mdo">Cards</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span>Utilities</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Custom Utilities:</h6>
                        <a class="collapse-item" href="utilities-color.mdo">Colors</a>
                        <a class="collapse-item" href="utilities-border.mdo">Borders</a>
                        <a class="collapse-item" href="utilities-animation.mdo">Animations</a>
                        <a class="collapse-item" href="utilities-other.mdo">Other</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Addons
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Login Screens:</h6>
                        <a class="collapse-item" href="login.mdo">Login</a>
                        <a class="collapse-item" href="register.mdo">Register</a>
                        <a class="collapse-item" href="forgot-password.mdo">Forgot Password</a>
                        <div class="collapse-divider"></div>
                        <h6 class="collapse-header">Other Pages:</h6>
                        <a class="collapse-item" href="404.mdo">404 Page</a>
                        <a class="collapse-item" href="blank.mdo">Blank Page</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="charts.mdo">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Charts</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="tables.mdo">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Tables</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Heading -->
            <div class="sidebar-heading">
                최고 관리자 전용
            </div>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="workdivide.mdo">
                    <i class="fas fa-fw fa-table"></i>
                    <span>업무 리스트 작성</span></a>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
        </ul>
<!-- End of Sidebar -->
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                            alt="...">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                            alt="...">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="...">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">홍길동님 안녕하세요.</span>
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
              <div class="container-fluid">
                    <!-- Content Row -->
                    <div class="row">
                        <div class="col-xl-12 col-lg-7">
                            <!-- Basic Card Example -->
                            <form id="form1" name="CarrotVO" method="POST" action="updateCarrot.mdo">
                            <input type="hidden" name="carrot_number1" value="${carrot_view.carrot_number}">
                            <input type="hidden" name="carrot_seq" value="${carrot_view.carrot_seq}">
	                            <div class="card shadow mb-4">
	                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
	                                    <h4 class="m-0 font-weight-bold text-primary">CARROT library</h4>
	                                    <input type="submit" class="shadow-sm bg-body rounded" id="" value="수정하기">
	                                    <input type="button" value="뒤로가기" onclick="history.go(-1)">
	                                    수정인:<input type="text" name="updator_name" value="${updatorList.updator_name}" readonly>
	                                </div>
	                                <div class="card-body">
	<img class="mainheaderimg1" alt="img1" src="resources/img/admin/carrot3.png">
<div class="dayarea"><input type="text" class="daybox" id="datepicker" value="${carrot_view.carrot_date}"  name="carrot_date" readonly></div>
<div class="classarea"><input type="text" class="classbox" id="classbox" name="carrot_class"value="${carrot_view.carrot_class}"></div>
 <div class="namearea"><input type="text" class="namebox" id="namebox" name="carrot_name"value="${carrot_view.carrot_name}"></div>
<div class="booksarea"><input type="text" class="booksbox" id="booksbox" name="carrot_books"value="${carrot_view.carrot_books}"></div>
<div class="memorization">
<c:if test="${carrot_view.carrot_memorization eq 'pass' }">
<input type="radio" name="carrot_memorization" value="pass" checked> P / 
<input type="radio" name="carrot_memorization" value="non-pass"> N /
<input type="radio" name="carrot_memorization" value="holding"> H
</c:if>
<c:if test="${carrot_view.carrot_memorization eq 'non-pass' }">
<input type="radio" name="carrot_memorization" value="pass"> P / 
<input type="radio" name="carrot_memorization" value="non-pass"checked> N /
<input type="radio" name="carrot_memorization" value="holding"> H
</c:if>
<c:if test="${carrot_view.carrot_memorization eq 'holding' }">
<input type="radio" name="carrot_memorization" value="pass"> P / 
<input type="radio" name="carrot_memorization" value="non-pass"> N /
<input type="radio" name="carrot_memorization" value="holding"checked> H
</c:if>
</div>

<div class="reportcheck">
<c:if test="${carrot_view.carrot_report eq 'pass' }">
<input type="radio" name="carrot_report" value="pass" checked> P / 
<input type="radio" name="carrot_report" value="non-pass"> N /
<input type="radio" name="carrot_report" value="holding"> H
</c:if>
<c:if test="${carrot_view.carrot_report eq 'non-pass' }">
<input type="radio" name="carrot_report" value="pass"> P / 
<input type="radio" name="carrot_report" value="non-pass" checked> N /
<input type="radio" name="carrot_report" value="holding"> H
</c:if>
<c:if test="${carrot_view.carrot_report eq 'holding' }">
<input type="radio" name="carrot_report" value="pass"> P / 
<input type="radio" name="carrot_report" value="non-pass"> N /
<input type="radio" name="carrot_report" value="holding" checked> H
</c:if>
</div>
<div class="readcheck">
<c:if test="${carrot_view.carrot_read eq 'pass' }">
<input type="radio" name="carrot_read" value="pass" checked> P / 
<input type="radio" name="carrot_read" value="non-pass"> N /
<input type="radio" name="carrot_read" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_read eq 'non-pass' }">
<input type="radio" name="carrot_read" value="pass"> P / 
<input type="radio" name="carrot_read" value="non-pass" checked> N /
<input type="radio" name="carrot_read" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_read eq 'holding' }">
<input type="radio" name="carrot_read" value="pass"> P / 
<input type="radio" name="carrot_read" value="non-pass"> N /
<input type="radio" name="carrot_read" value="holding" checked> H
</c:if>
</div>
<div class="transcheck">
<c:if test="${carrot_view.carrot_trans eq 'pass' }">
<input type="radio" name="carrot_trans" value="pass" checked> P / 
<input type="radio" name="carrot_trans" value="non-pass"> N /
<input type="radio" name="carrot_trans" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_trans eq 'non-pass' }">
<input type="radio" name="carrot_trans" value="pass"> P / 
<input type="radio" name="carrot_trans" value="non-pass" checked> N /
<input type="radio" name="carrot_trans" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_trans eq 'holding' }">
<input type="radio" name="carrot_trans" value="pass"> P / 
<input type="radio" name="carrot_trans" value="non-pass"> N /
<input type="radio" name="carrot_trans" value="holding" checked> H
</c:if>
</div>
<div class="solvingcheck">
<c:if test="${carrot_view.carrot_solving eq 'pass' }">
<input type="radio" name="carrot_solving" value="pass"checked> P / 
<input type="radio" name="carrot_solving" value="non-pass"> N /
<input type="radio" name="carrot_solving" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_solving eq 'non-pass' }">
<input type="radio" name="carrot_solving" value="pass"> P / 
<input type="radio" name="carrot_solving" value="non-pass"checked> N /
<input type="radio" name="carrot_solving" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_solving eq 'holding' }">
<input type="radio" name="carrot_solving" value="pass"> P / 
<input type="radio" name="carrot_solving" value="non-pass"> N /
<input type="radio" name="carrot_solving" value="holding" checked> H
</c:if>
</div>
<div class="listencheck"><input type="text" class="pagebox" name="carrot_listen"  value="${carrot_view.carrot_listen}">P</div>
<div class="gettingcheck">
<input type="text" value="${carrot_view.carrot_getting}" name="getting">
<select class="gettingbox" name="gettingselect1" id="gettingselect1" onchange="gettingkindchange(this)">
<option value="" >대분류</option>
<option value="문장의 형식"  >문장의 형식</option>
<option value="시제">시제</option>
<option value="조동사">조동사</option>
<option value="수동태">수동태</option>
<option value="부정사">부정사</option>
<option value="동명사">동명사</option>
<option value="분사">분사</option>
<option value="분사구문">분사구문</option>
<option value="명사와 대명사">명사와 대명사</option>
<option value="형용사, 부사">형용사, 부사</option>
	                                    		<option value="비교급, 최상급">비교급, 최상급</option>
	                                    		<option value="전치사">전치사</option>
	                                    		<option value="접속사">접속사</option>
	                                    		<option value="관계사">관계사</option>
	                                    		<option value="가정법">가정법</option>
	                                    		<option value="일치와 화법">일치와 화법</option>
	                                    		<option value="특수구문">특수구문</option>
	                                    	</select>
	                                    	<br>
	                                    	<select class="gettingbox" id="gettingkind" name="carrot_getting">
	                                    		<option>대분류 우선 선택</option>
	                                    	</select>
	                                    </div>
<div class="showdowncheck"><input type="text" class="pagebox" name="carrot_showdown" value="${carrot_view.carrot_showdown}">P</div>
	                                    <div class="speechcheck">
<c:if test="${carrot_view.carrot_speech eq 'pass' }">
<input type="radio" name="carrot_speech" value="pass"checked> P / 
<input type="radio" name="carrot_speech" value="non-pass"> N /
<input type="radio" name="carrot_speech" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_speech eq 'non-pass' }">
<input type="radio" name="carrot_speech" value="pass"> P / 
<input type="radio" name="carrot_speech" value="non-pass"checked> N /
<input type="radio" name="carrot_speech" value="holding" > H
</c:if>
<c:if test="${carrot_view.carrot_speech eq 'holding' }">
<input type="radio" name="carrot_speech" value="pass"> P / 
<input type="radio" name="carrot_speech" value="non-pass"> N /
<input type="radio" name="carrot_speech" value="holding" checked> H
</c:if>
	                                    </div>
	                                    <div class="perfectioncheck">
	                                    <input type="text" value="${carrot_view.carrot_perfection}" readonly style=" width: 115px;">
	                                    	<select class="perfectionbox" name="carrot_perfection">
	                                    		<option value="A+">A+</option>
	                                    		<option value="A">A</option>
	                                    		<option value="B+">B+</option>
	                                    		<option value="B">B</option>
	                                    		<option value="C">C</option>
	                                    		<option value="F">F</option>
	                                    	</select>
	                                    </div>
<div class="resultarea"><textarea class="resultbox" name="carrot_result">${carrot_view.carrot_result}</textarea></div>
	                                </div>
	                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                     
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    
    <!-- Logout Modal-->
    <div class="modal fade" id="inforModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">학생 정보</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    학생 정보
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.mdo">Logout</a>
                </div>
            </div>
        </div>
    </div>

	<!-- Bootstrap core JavaScript-->
    <script src="resources/js/admin/jquery.min.js"></script>
    <script src="resources/js/admin/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/js/admin/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/js/admin/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="resources/js/admin/jquery.dataTables.min.js"></script>
    <script src="resources/js/admin/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="resources/js/admin/datatables-demo.js"></script>
    <script>
        $(document).ready(function() {
            function addRemoveClass(theRows){
                theRows.removeClass("odd even");
                theRows.filter(":odd").addClass("add");
                theRows.filter(":even").addClass("even");
            }
            var rows = $("table.table tr:not(:first)");
            addRemoveClass(rows);
            $(".selectclass").on("change",function(){
                var selected = $('.selectclass').val();
                if(selected != "클래스 전체"){
                    rows.filter(":has(td:nth-child(2):contains("+selected+"))").show();
                    rows.not(":has(td:nth-child(2):contains("+selected+"))").hide();
                }else{
                    rows.show();
                    addRemoveClass(rows);
                }  
            });
            $(".selectgrade").on("change",function(){
                var selected = $('.selectgrade').val();
                if(selected != "학년 전체"){
                    rows.filter(":has(td:nth-child(4):contains("+selected+"))").show();
                    rows.not(":has(td:nth-child(4):contains("+selected+"))").hide();
                }else{
                    rows.show();
                    addRemoveClass(rows);
                }
            });
            $(".selectschool").on("change",function(){
                var selected = $('.selectschool').val();
                if(selected != "학교 전체"){
                    rows.filter(":has(td:nth-child(5):contains("+selected+"))").show();
                    rows.not(":has(td:nth-child(5):contains("+selected+"))").hide();
                }else{
                    rows.show();
                    addRemoveClass(rows);
                }  
            });
            $(".selectstatus").on("change",function(){
                var selected = $('.selectstatus').val();
                if(selected != "현황 전체"){
                    rows.filter(":has(td:nth-child(6):contains("+selected+"))").show();
                    rows.not(":has(td:nth-child(6):contains("+selected+"))").hide();
                }else{
                    rows.show();
                    addRemoveClass(rows);
                }  
            });
        });
    </script>
     <script src="resources/js/admin/jquery.min.js"></script>
    <script src="resources/js/admin/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/js/admin/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="resources/js/admin/sb-admin-2.min.js"></script>

    <script src="resources/air-datepicker/dist/js/datepicker.js"></script> <!-- Air datepicker js -->
    <script src="resources/air-datepicker/dist/js/i18n/datepicker.ko.js"></script> <!-- 달력 한글 추가를 위해 커스텀 -->
    <script>
        $("#datepicker").datepicker({
            language: 'ko'
        });
        function gettingkindchange(r){
        	var a = ["1형식과 2형식","감각동사","3형식","4형식","5형식","명사,형용사,to부정사를 목적격 보어로 쓰는 동사","사역동사","지각동사"];
        	var b = ["현재시제","과거시제","미래시제","현재완료시제"];
        	var c = ["can, may, will","must, should","should의 생략","used to, would","조동사 + have + p.p.","조동사 관용 표현"];
        	var d = ["수동태 문장 만드는 법","수동태로 쓸 수 없는 동사","수동태의 시제","조동사가 있는 수동태","4형식 문장의 수동태","5형식 문장의 수동태","구동사의 수동태","목적어가 that절인 문장의 수동태","수동태 관용 표현"];
        	var e = ["to부정사의 형태와 용법","명사적 용법: 주어와 보어로 쓰이는 to부정사","명사적 용법: 목적어로 쓰이는 to부정사","명사적 용법: 의문사 + to부정사","형용사적 용법: 명사•대명사 수식","형용사적 용법: be동사 + to부정사","부사적 용법","too ~ to, enough to","to부정사의 의미상의 주어"];
        	var f = ["동명사의 형태와 쓰임","동명사의 의미상의 주어","동명사의 시제와 태","동명사를 목적어로 쓰는 동사","동명사와 to부정사를 모두 목적어로 쓰는 동사","동명사 관용 표현"];
        	var g = ["분사의 형태와 쓰임","현재분사 vs. 과거분사","감정을 나타내는 분사","현재분사 vs. 동명사"];
        	var h = ["분사구문 만드는 법","분사구문의 다양한 의미","분사구문의 시제와 태","with + 명사 + 분사","분사구문 관용 표현"];
        	var i = ["셀 수 있는 명사","셀 수 없는 명사","명사의 소유격","재귀대명사","it의 다양한 쓰임 I","it의 다양한 쓰임 II","부정대명사: one","부정대명사: some, any","부정대명사: another, other","부정대명사: all, each, every","부정대명사: both, either, neither"];
        	var j = ["형용사의 용법","수량형용사","부사의 역할","형용사와 형태가 같은 부사","-ly가 붙으면 의미가 달라지는 부사","빈도부사","타동사 + 부사"];
        	var k = ["원급 비교: as + 원급 + as","원급 관련 표현","비교급 비교: 비교급 + than","비교급 관련 표현","최상급 비교와 관련된 표현"];
        	var l = ["시간을 나타내는 전치사 I","시간을 나타내는 전치사 II","시간을 나타내는 전치사 III","장소를 나타내는 전치사 I","장소를 나타내는 전치사 II","위치를 나타내는 전치사","방향을 나타내는 전치사","기타 전치사","전치사 관용 표현: 형용사 + 전치사","전치사 관용 표현: 동사 + 전치사","전치사 관용 표현: 동사 + 명사 + 전치사"];
        	var m = ["등위접속사: and, but, or, so","상관접속사","명사절을 이끄는 접속사: that","명사절을 이끄는 접속사: if/whether","명사절을 이끄는 접속사: 의문사","부사절을 이끄는 접속사: 시간","부사절을 이끄는 접속사: 이유","부사절을 이끄는 접속사: 조건","부사절을 이끄는 접속사: 양보","부사절을 이끄는 접속사: so that ~","부사절을 이끄는 접속사: so ~ that …","다양한 의미의 부사절을 이끄는 접속사","접속부사"];
        	var n = ["관계대명사의 역할과 종류","주격 관계대명사","목적격 관계대명사","소유격 관계대명사","관계대명사 that","관계대명사 what","전치사 + 관계대명사","관계부사의 역할과 종류","관계사의 계속적 용법","복합관계사"];
        	var o = ["가정법 과거와 과거완료","혼합 가정법","가정법을 직설법으로 바꾸는 법","if를 생략한 가정법","Without 가정법","I wish 가정법","as if[though] 가정법"];
        	var p = ["수 일치 I","수 일치 II","시제 일치","화법 전환: 평서문","화법 전환: 의문사가 있는 의문문","화법 전환: 의문사가 없는 의문문","화법 전환: 명령문"];
        	var q = ["강조","강조: It is ~ that 강조 구문","부정","도치","생략","동격","병치"];
        	var target = document.getElementById("gettingkind");
        	
        	if(r.value == "문장의 형식") var change = a;
        	else if(r.value == "시제") var change = b;
        	else if(r.value == "조동사") var change = c;
        	else if(r.value == "수동태") var change = d;
        	else if(r.value == "부정사") var change = e;
        	else if(r.value == "동명사") var change = f;
        	else if(r.value == "분사") var change = g;
        	else if(r.value == "분사구문") var change = h;
        	else if(r.value == "명사와 대명사") var change = i;
        	else if(r.value == "형용사, 부사") var change = j;
        	else if(r.value == "비교급, 최상급") var change = k;
        	else if(r.value == "전치사") var change = l;
        	else if(r.value == "접속사") var change = m;
        	else if(r.value == "관계사") var change = n;
        	else if(r.value == "가정법") var change = o;
        	else if(r.value == "일치와 화법") var change = p;
        	else if(r.value == "특수구문") var change = q;
    		
        	target.options.length = 0;
        	
        	for(x in change){
        		var opt = document.createElement("option");
        		opt.value = change[x];
        		opt.innerHTML = change[x];
        		target.appendChild(opt);
        	}
        }
    </script>
</body>

</html>