<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


    <!DOCTYPE html>
    <html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>AcroYoga</title>
        <link rel="shortcut icon" href="img/logo.png" />

        <!-- Bootstrap Core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href='css/style.css' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

        <!-- Plugin CSS -->
        <link href="vendor/magnific-popup/magnific-popup.css" rel="stylesheet">

        <!-- Theme CSS -->
        <link href="css/creative.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    </head>

    <body id="page-top forum-page">
<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
                    <a class="navbar-brand page-scroll " href="#page-top" style="background-image: url('img/logo.png'); background-size:contain; background-repeat: no-repeat; margin-top:10px; margin-left:0px"></a>
                    <a class="navbar-brand page-scroll" href="#page-top">
                    	AcroYoga</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="page-scroll" href="/AcroYoga/Index">Startseite</a></li>
                        <li><a class="page-scroll" href="/AcroYoga/Events">Veranstaltungen</a></li>
                        <c:choose>
                        	<c:when test="${user.admin}">
                        		<li><a class="page-scroll" href="/AcroYoga/Members">Teilnehmer</a></li>                        	
                        	</c:when>
                        </c:choose>     
                        <li><a class="page-scroll" href="/AcroYoga/Forums">Forum</a></li>
                        <li>
                            <form id="search-box" action="Search" method="post" accept-charset="UTF-8">
                                <div class="input-group">
                                    <div class="input-group-btn search-panel">
                                        FILTER
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
										<span id="search_concept">Filter</span> <span class="caret"></span>
									</button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#contains">Personen</a></li>
                                            <li><a href="#greather_than">Veranstaltungen</a></li>
                                        </ul>
                                    </div>
                                    <input type="hidden" name="search_param" value="all" id="search_param"> <input type="text" class="form-control" name="x" placeholder="Suchbegriff..">
                                    <span class="input-group-btn"> <input
									class="btn btn-primary btn-block" type="submit" id="sign-in"
									value="search"></span>
                                </div>
                            </form>
                        </li>
                        <li>
                                <c:choose>
                                    <c:when test="${loggedIn}">
                                        <li class="dropdown"><a class="dropdown-toggle" href="#" data-toggle="dropdown"> ${user.name} <strong class="caret"></strong>
											</a>
                                            <div class="dropdown-menu" style="padding: 10px; background:transparent; -webkit-box-shadow: 0 0px 0px rgba(0, 0, 0, .175);
          											box-shadow: 0 0px 0px rgba(0, 0, 0, .175); border: 0px solid #ccc; border: 0px solid rgba(0, 0, 0, .15);">
                                                <form method="post" action="Logout" accept-charset="UTF-8">
                                                    <input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Logout">
                                                </form>
                                            </div>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="dropdown">
                                        	<a class="dropdown-toggle" href="#" data-toggle="dropdown"> Anmelden <strong class="caret"></strong></a>
                                            <div class="dropdown-menu" style="padding: 0px; background:transparent; -webkit-box-shadow: 0 0px 0px rgba(0, 0, 0, .175);
          											box-shadow: 0 0px 0px rgba(0, 0, 0, .175); border: 0px solid #ccc; border: 0px solid rgba(0, 0, 0, .15);">
                                                <div class="container">
                                                    <div class="row" style="padding:0px; background:transparent">
                                                        <div class="col-xs-6 col-xs-offset-6" style="padding:0px; background:transparent">
                                                            <div class="panel panel-login" style="margin:0px">
                                                                <div class="panel-heading">
                                                                    <div class="row">
                                                                        <div class="col-xs-6">
                                                                            <a href="#" class="active" id="login-form-link">Anmelden</a>
                                                                        </div>
                                                                        <div class="col-xs-6">
                                                                            <a href="#" id="register-form-link">Registrieren</a>
                                                                        </div>
                                                                    </div>
                                                                    <hr>
                                                                </div>
                                                                <div class="panel-body">
                                                                    <div class="row">
                                                                        <div class="col-lg-12">
                                                                            <form id="login-form" action="Login" method="post" role="form" style="display: block;">
                                                                                <div class="form-group">
                                                                                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="E-Mail" value="">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Passwort">
                                                                                </div>
                                                                                <c:choose>
                                                                                    <c:when test="${loginError != null}">
                                                                                        <p style="color: red">${loginError}</p>
                                                                                    </c:when>
                                                                                </c:choose>
                                                                                <div class="form-group">
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 col-sm-offset-3">
                                                                                            <input type="submit" name="login-submit" id="sign-in" tabindex="4" class="form-control btn btn-login" value="Sign In">
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>
                                                                            <form id="register-form" action="Register" method="post" role="form" enctype="multipart/form-data" style="display: none;">
                                                                                <div class="form-group">
                                                                                    <input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="Vorname" value="">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <input type="text" name="lastname" id="lastname" tabindex="2" class="form-control" placeholder="Nachname" value="">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <input type="email" name="email" id="email" tabindex="3" class="form-control" placeholder="Email Address" value="">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <input type="password" name="password" id="password" tabindex="4" class="form-control" placeholder="Passwort">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <input type="password" name="confirm-password" id="confirm-password" tabindex="5" class="form-control" placeholder="Confirm Password">
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <div class="row">
                                                                                        <div class="col-sm-6 col-sm-offset-3">
                                                                                            <input type="submit" name="register-submit" id="register-submit" tabindex="6" class="form-control btn btn-register" value="Register Now">
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>

        <header>
            <div class="header-content">
                <div class="header-content-inner">
                    <h1 id="homeHeading">${name}</h1>
                </div>
            </div>
        </header>


        <!-- Threads -->
        <section id="foren">

            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2 class="section-heading">Foren</h2>
                        <hr class="primary">
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <c:forEach items="${fList}" var="forumList">
                        <div class="col-xs-5 col-md-offset-1">
                            <div class="card">
                                <div class="card-content">
                                    <a href="/AcroYoga/Forum?id=${forumList.id}" alt="Lights" style="width: 100%">${forumList.name}</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:choose>
                <c:when test="${user.admin}">
                    <div class="container">
                        <form method="post" action="views/AddForum.jsp" accept-charset="UTF-8">
                            <input type="hidden" id="id" name="id" value="<%=request.getParameter("id")%>">

                            <div>
                                <input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Forum erstellen">
                            </div>
                        </form>
                    </div>
                </c:when>
            </c:choose>
        </section>
        <!-- Threads ende -->

        <!-- Kontakt -->
        <section class="bg-primary" id="kontakt">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <h2 class="section-heading">Kontakt</h2>
                        <hr class="primary">
                        <p>Kontakt blabla</p>
                    </div>
                    <div class="col-lg-4 col-lg-offset-2 text-center">
                        <i class="fa fa-phone fa-3x sr-contact"></i>
                        <p>0421 123456789</p>
                    </div>
                    <div class="col-lg-4 text-center">
                        <i class="fa fa-envelope-o fa-3x sr-contact"></i>
                        <!-- <p><a href="mailto:acroyoga@acroyoga.de">acroyoga@acroyoga.de</a></p> -->
                        <p>acroyoga@acroyoga.de</p>
                    </div>
                </div>
            </div>
        </section>
        <!-- Kontakt ende -->

        <!-- jQuery -->
        <script src="vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Plugin JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
        <script src="vendor/scrollreveal/scrollreveal.min.js"></script>
        <script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

        <!-- Theme JavaScript -->
        <script src="js/creative.min.js"></script>

    </body>

    </html>
