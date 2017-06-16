<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>AcroYoga</title>

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>

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
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">AcroYoga</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="/AcroYoga/Index">Home</a></li>
					<li><a class="page-scroll" href="#veranstaltungen">Veranstaltungen</a></li>
					<li><a class="page-scroll" href="#kontakt">Kontakt</a></li>
					<li><a class="page-scroll" href="/AcroYoga/Foren">Forum</a></li>
					<li><a class="page-scroll" href="#suche">Suche</a></li>
					<li><a class="page-scroll" href="#"> <c:choose>
								<c:when test="${loggedIn}">
									<li class="dropdown">
										<a class="dropdown-toggle" href="#"
											data-toggle="dropdown"> ${user} <strong class="caret"></strong>
										</a>
										<div class="dropdown-menu" style="padding: 15px;">
											<form method="post" action="Logout" accept-charset="UTF-8">
												 <input class="btn btn-primary btn-block"
													type="submit" id="sign-in" value="Logout">
											</form>
										</div>
									</li>
									<!-- <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">${user}
										<b class="caret"></b>
									</a></li>
									<ul class="dropdown-menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
										<li class="divider"></li>
										<li><form action="Logout" method="post">
												<input type="submit" value="Logout">
											</form></li>
									</ul> -->
								</c:when>
								<c:otherwise>
									<li class="dropdown"><a class="dropdown-toggle" href="#"
										data-toggle="dropdown"> Log In <strong class="caret"></strong>
									</a>
										<div class="dropdown-menu" style="padding: 15px;">
											<form method="post" action="Login" accept-charset="UTF-8">
												<input style="margin-bottom: 15px;" type="text"
													placeholder="E-Mail" id="username" name="username">
												<input style="margin-bottom: 15px;" type="password"
													placeholder="Password" id="password" name="password">
												<p>E-Mail Adresse oder Passwort falsch</p>
												<input style="float: left; margin-right: 10px;"
													checkbox" name="remember-me" id="remember-me" value="">
												<label class="string optional" for="user_remember_me">
													Remember me</label> <input class="btn btn-primary btn-block"
													type="submit" id="sign-in" value="Sign In">
											</form>
										</div></li>
								</c:otherwise>
							</c:choose>
					</a></li>


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
				<hr>
				<p>Noch mehr blablabla</p>
				<a href="#about" class="btn btn-primary btn-xl page-scroll">Find
					Out More</a>
			</div>
		</div>
	</header>


	<!-- Threads -->
	<section id="foren">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Threads</h2>
					<hr class="primary">
				</div>
			</div>
		</div>
		<div class="row">
			${threadListe}
		</div>
		
		

	</section>
	<!-- Threads ende -->


	<!-- jQuery -->
	<script src="vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="vendor/scrollreveal/scrollreveal.min.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/creative.min.js"></script>

</body>

</html>
