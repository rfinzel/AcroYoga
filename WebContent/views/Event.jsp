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
        <link href="css/style.css" rel="stylesheet">

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

    <body id="page-top">
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

        <header style="background-image: url('img/events/${id}/image.jpg');">
            <div class="header-content">
                <div class="header-content-inner">
                    <h1 id="homeHeading">${name}</h1>
                </div>
            </div>
        </header>

        <!-- Infobar -->
        <section id="veranstaltungen">
            <div class="container">
                <div class="row">
                    <div class="col-xs-4 text-center">
                    	<div class="btn btn-default">
                        	<p>${timing} bis ${endDate}</p>
                        </div>
                    </div>
                    <div class="col-xs-4 text-center" style= "font-weight: 800">
                        <p>${name}</p>
                    </div>
                    <div class="col-xs-4 text-center">
                    	<c:choose>
	                    	<c:when test="${loggedIn}">
	                    		<c:choose>
	                    		<c:when test="${participate}">
		                    		<form id="participate" action="DeleteParticipation" method="post" accept-charset="UTF-8">
			                    	<input type="hidden" name="nextEvent" value="${nextEvent}" id="nextEvent">
			                    	<input type="hidden" name="eventId" value="${id}" id="eventId"> 
									<input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Nicht mehr teilnehmen">
			                    	</form>
	                    		</c:when>
	                    		<c:when test="${not participate}">
		                    		<form id="participate" action="AddParticipation" method="post" accept-charset="UTF-8">
			                    	<input type="hidden" name="nextEvent" value="${nextEvent}" id="nextEvent">
			                    	<input type="hidden" name="eventId" value="${id}" id="eventId"> 
									<input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Teilnehmen">
			                    	</form>
	                    		</c:when>
	                    		</c:choose>
	                   		</c:when>
	                   		<c:otherwise>
	                   			<form accept-charset="UTF-8">
									<input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Anmelden zum Teilnehmen" style="background-color: #fdebe7">
			                    </form>
	                   		</c:otherwise>
                    	</c:choose>
                    </div>
                </div>
            </div>
        </section>
        <!-- About Infobar -->
        <hr style="width: 100%">

        <!-- Details -->
        <section id="veranstaltungen">
            <div class="container">
                <div class="row">
                    <div class="col-xs-3" style="color:gray; ">
                        <p>Wann:</p>
                        
                        <p>N�chster Termin:</p>

                        <p>Wo:</p>

                        <p>Eintritt:</p>

                        <p>Wer kommt noch:</p>
					
                    </div>
                    <div class="col-xs-3">
                        <p>${weekday} ${time} Uhr</p>
                        
                        <p>${nextEvent}</p>

                        <p>${place}</p>

                        <p>${fee} Euro</p>
	
						<c:choose>
							<c:when test="${loggedIn}">
		                        <c:forEach items="${participants}" var="participants">
		                        	<img src="img/members/${participants.id}/picture.png" alt="${participants.name }" width="50" height="50" title="${participants.name }"/>
		                        </c:forEach>						
							</c:when>
							<c:otherwise>
								<p>Das wird dir angezeigt, wenn du eingeloggt bist.</p>
							</c:otherwise>
						</c:choose>
                    </div>
                    <div class="col-xs-5 col-xs-offset-1">
                        <p>${content}</p>
                    </div>
                </div>

            </div>
        </section>
        <!-- About Details -->

        <div class="container">
            <c:choose>
                <c:when test="${loggedIn}">
                    <div class="row">
                        <form method="post" action="UploadImages" enctype="multipart/form-data">
                            <div id="images" class="col-xs-9">
                                <input type="hidden" id="id" name="id" value="${id}" /> 
                                <input type="hidden" id="amount" name="amount" value="1" /> 
                                <div class="col-xs-8">
                                	<div class="col-xs-10">
                                		<input type="file" name="file1" />
						            </div>
						            <div class="col-xs-2">
						            	<a id="addImage"><button type="button" class="btn btn-default"
											aria-label="Left Align">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
											</button> </a>
									</div>		
                            	</div>
								<div class="col-xs-4">
									<input type="date" name="date" id="date" class="form-control" placeholder="Datum" required>
                                </div>
                            </div>
                           	
                            <div class="col-xs-3">
                                <input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Bilder hochladen">
                            </div>
                        </form>
                    </div>
	            <h2>Bilder</h2>
	            <div class="panel-group">
	                <div class="panel panel-default">
	                	<c:forEach items="${fileList}" var="fileList">
		                    <div class="panel-heading">
		                        <h4 class="panel-title">
		                            <a data-toggle="collapse" href="#${fileList.name}">${fileList.name}</a>
		                        </h4>
		                    </div>
		                    <div id="${fileList.name}" class="panel-collapse collapse" style="height: 100%">
		                        <div class='list-group gallery'>
		                        <c:forEach items="${fileList.files}" var="files">
	                                <div class='col-sm-4 col-xs-6 col-md-3 col-lg-3'>
	                                    <a class="card thumbnail" rel="ligthbox" href="img/events/${id}/${fileList.name}/${files}"> <img alt="" src="img/events/${id}/${fileList.name}/thumbnails/${files}" />
	                                    </a>
	                                </div>
		                        </c:forEach>
		                        </div>
		                        <!-- list-group / end -->
	                        	<div class="panel-footer"></div>
		                    </div>
	                    </c:forEach>
	                </div>
	            </div>
            </c:when>
            </c:choose>
        </div>
        <!--infobar-->

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
        <script src="js/creative.js"></script>

    </body>

    </html>
