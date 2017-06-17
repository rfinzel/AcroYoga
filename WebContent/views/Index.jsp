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

<!-- Bootstrap Core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href='css/style.css' rel='stylesheet' type='text/css'>
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
					<li><a class="page-scroll" href="/AcroYoga/Events">Veranstaltungen</a></li>
					<li><a class="page-scroll" href="#kontakt">Kontakt</a></li>
					<li><a class="page-scroll" href="/AcroYoga/Forums">Forum</a></li>
					<li>
						<form id="login-form" action="Search" method="post"
							accept-charset="UTF-8">
							<div class="input-group">
								<div class="input-group-btn search-panel">
									<!-- FILTER <button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<span id="search_concept">Filter</span> <span class="caret"></span>
								</button>
								 <ul class="dropdown-menu" role="menu">
									<li><a href="#contains">Personen</a></li>
									<li><a href="#greather_than">Veranstaltungen</a></li>
								</ul> FILTER -->
								</div>
								<input type="hidden" name="search_param" value="all"
									id="search_param"> <input type="text"
									class="form-control" name="x" placeholder="Search term...">
								<span class="input-group-btn"> <input
									class="btn btn-primary btn-block" type="submit" id="sign-in"
									value="search"></span>
							</div>
						</form>
					</li>
					<li><a class="page-scroll" href="#"> <c:choose>
								<c:when test="${loggedIn}">
									<li class="dropdown"><a class="dropdown-toggle" href="#"
										data-toggle="dropdown"> ${user.name} <strong class="caret"></strong>
									</a>
										<div class="dropdown-menu" style="padding: 0x;">
											<form method="post" action="Logout" accept-charset="UTF-8">
												<input class="btn btn-primary btn-block" type="submit"
													id="sign-in" value="Logout">
											</form>
										</div></li>
								</c:when>
								<c:otherwise>
									<li class="dropdown"><a class="dropdown-toggle" href="#"
										data-toggle="dropdown"> Log In <strong class="caret"></strong>
									</a>
										<div class="dropdown-menu" style="padding: 15px;">
											<div class="container">
												<div class="row">
													<div class="col-md-6 col-md-offset-3">
														<div class="panel panel-login">
															<div class="panel-heading">
																<div class="row">
																	<div class="col-xs-6">
																		<a href="#" class="active" id="login-form-link">Login</a>
																	</div>
																	<div class="col-xs-6">
																		<a href="#" id="register-form-link">Register</a>
																	</div>
																</div>
																<hr>
															</div>
															<div class="panel-body">
																<div class="row">
																	<div class="col-lg-12">
																		<form id="login-form" action="Login" method="post"
																			role="form" style="display: block;">
																			<div class="form-group">
																				<input type="text" name="username" id="username"
																					tabindex="1" class="form-control"
																					placeholder="E-Mail" value="">
																			</div>
																			<div class="form-group">
																				<input type="password" name="password" id="password"
																					tabindex="2" class="form-control"
																					placeholder="Passwort">
																			</div>
																			<div class="form-group text-center">
																				<input type="checkbox" tabindex="3" class=""
																					name="remember" id="remember"> <label
																					for="remember"> Remember Me</label>
																			</div>
																			<div class="form-group">
																				<div class="row">
																					<div class="col-sm-6 col-sm-offset-3">
																						<input type="submit" name="login-submit"
																							id="sign-in" tabindex="4"
																							class="form-control btn btn-login"
																							value="Sign In">
																					</div>
																				</div>
																			</div>
																			<div class="form-group">
																				<div class="row">
																					<div class="col-lg-12">
																						<div class="text-center">
																							<a href="https://phpoll.com/recover" tabindex="5"
																								class="forgot-password">Forgot Password?</a>
																						</div>
																					</div>
																				</div>
																			</div>
																		</form>
																		<form id="register-form" action="Register"
																			method="post" role="form" style="display: none;">
																			<div class="form-group">
																				<input type="text" name="name" id="name"
																					tabindex="1" class="form-control"
																					placeholder="Vorname" value="">
																			</div>
																			<div class="form-group">
																				<input type="text" name="lastname" id="lastname"
																					tabindex="1" class="form-control"
																					placeholder="Nachname" value="">
																			</div>
																			<div class="form-group">
																				<input type="email" name="email" id="email"
																					tabindex="1" class="form-control"
																					placeholder="Email Address" value="">
																			</div>
																			<div class="form-group">
																				<input type="password" name="password" id="password"
																					tabindex="2" class="form-control"
																					placeholder="Passwort">
																			</div>
																			<div class="form-group">
																				<input type="password" name="confirm-password"
																					id="confirm-password" tabindex="2"
																					class="form-control" placeholder="Confirm Password">
																			</div>
																			<div class="form-group">
																				<div class="row">
																					<div class="col-sm-6 col-sm-offset-3">
																						<input type="submit" name="register-submit"
																							id="register-submit" tabindex="4"
																							class="form-control btn btn-register"
																							value="Register Now">
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
				<c:choose>
					<c:when test="${loggedIn}">
						<h1 id="homeHeading">Hallo ${user.name}!</h1>
					</c:when>
					<c:otherwise>
						<h1 id="homeHeading">Hallo, hier bei AcroYoga Bremen!</h1>
					</c:otherwise>
				</c:choose>
				<hr>
				<p>Noch mehr blablabla</p>
				<a href="#about" class="btn btn-primary btn-xl page-scroll">Find
					Out More</a>
			</div>
		</div>
	</header>




	<c:choose>
		<c:when test="${loggedIn}">
			<section class="bg-primary" id="about">
				<div class="col-md-6">
					<div class="panel with-nav-tabs panel-default">
						<div class="panel-heading">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab1default" data-toggle="tab">Deine
										Veranstaltungen </a></li>
								<li><a href="#tab2default" data-toggle="tab">Deine
										Forenbeitr�ge</a></li>
								<li><a href="#tab3default" data-toggle="tab">Deine
										Einstellungen</a></li>
							</ul>
						</div>
						<div class="panel-body">
							<div class="tab-content">
								<div class="tab-pane fade in active" id="tab1default">
									<div class="container">
										<div class="row">
											<c:forEach items="${eLin}" var="eventList">
												<div class="col-lg-3 col-md-6 text-center">
													<div class="service-box">
														<div class="thumbnail">
															<a href="/AcroYoga/Event?id=${eventList.id}" img
																src="img/event/${id}/titel.jpg" alt="Lights"
																style="width: 100%">
																<div class="caption">
																	<h3>${eventList.name}</h3>
																	<hr class="divider">
																	<p>${eventList.shortContent}</p>
																</div>
															</a>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="tab2default">
									<div class="container">
										<div class="row">
											<c:forEach items="${pLin}" var="postList">
												<div class="col-lg-3 col-md-6 text-center">
													<div class="service-box">
														<div class="thumbnail">
															<a href="/AcroYoga/Thread?id=${p.thread_id}" alt="Lights"
																style="width: 100%">
																<div class="caption">
																	<h3>${postList.content}</h3>
																	<hr class="divider">
																	<p>${postList.content}</p>
																</div>
															</a>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="tab3default">
									<div class="container">
									<div class="row">
										<div class="col-xs-2">
										<p>Name</p>
										<p>Email</p>
										<p>Password</p>
										<p>Geburtstag</p>
										</div>
										<div class="col-xs-4">
										<p id="acName">${user.name} ${user.lastname}</p>
										<p id="acEmail">${user.email}</p>
										<p id ="acPassword">${user.password}</p>
										<p id="acBirthday">${user.birthday}</p>
										</div>
										<div class="col-xs-3"></div>
										<div class="col-xs-3">
										<p id="updateAccount">�ndern</p>
										<p id="deleteAccount">account l�schen</p>
										
										</div>
									</div>
									</div>
									<div></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</c:when>
		<c:otherwise>
			<!-- About -->
			<section class="bg-primary" id="about">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 col-lg-offset-2 text-center">
							<h2 class="section-heading">About</h2>
							<hr class="light">
							<p class="text-faded">blablabla</p>
							<a href="#veranstaltungen"
								class="page-scroll btn btn-default btn-xl sr-button">Get
								Started!</a>
						</div>
					</div>
				</div>
			</section>
			<!-- About ende -->
			<!-- Veranstaltungen -->
			<section id="veranstaltungen">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2 class="section-heading">Veranstaltungen</h2>
							<hr class="primary">
						</div>
					</div>
				</div>
				<div class="container">
					<a href="/AcroYoga/views/AddEvent.jsp"><button type="button"
							class="btn btn-default" aria-label="Left Align">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						</button></a>
				</div>
				<div class="container">
					<div class="row">
						<c:forEach items="${eList}" var="eventList">
							<div class="col-lg-3 col-md-6 text-center">
								<div class="service-box">
									<div class="thumbnail">
										<a href="/AcroYoga/Veranstaltung?id=${eventList.id}" img
											src="img/event/${id}/titel.jpg" alt="Lights"
											style="width: 100%">
											<div class="caption">
												<h3>${eventList.name}</h3>
												<hr class="divider">
												<p>${eventList.shortContent}</p>
											</div>
										</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</section>
			<!-- Veranstaltungen ende -->


			<!-- Forum -->
			<section class="bg-primary" id="veranstaltungen">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 text-center">
							<h2 class="section-heading">neueste Threads</h2>
							<hr class="primary">
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-3 col-md-6 text-center">
							<div class="service-box">
								<div class="thumbnail">
									<a href="/AcroYoga/Forum?id=1"> <img src="img/header.jpg"
										alt="Lights" style="width: 100%">
										<div class="caption">
											<h3>Forum 1</h3>
											<hr class="divider">
											<p>Lorem ipsum dolor sit amet, consetetur sadipscing
												elitr, sed diam nonumy eirmod tempor invidunt ut labore et
												dolore magna aliquyam erat, sed diam voluptua.</p>
										</div>
									</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 text-center">
							<div class="service-box">
								<div class="thumbnail">
									<a href="/w3images/lights.jpg"> <img src="img/header.jpg"
										alt="Lights" style="width: 100%">
										<div class="caption">
											<h3>Veranstaltung D</h3>
											<hr class="divider">
											<p>Lorem ipsum dolor sit amet, consetetur sadipscing
												elitr, sed diam nonumy eirmod tempor invidunt ut labore et
												dolore magna aliquyam erat, sed diam voluptua.</p>
										</div>
									</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 text-center">
							<div class="service-box">
								<div class="thumbnail">
									<a href="/w3images/lights.jpg"> <img src="img/header.jpg"
										alt="Lights" style="width: 100%">
										<div class="caption">
											<h3>Veranstaltung D</h3>
											<hr class="divider">
											<p>Lorem ipsum dolor sit amet, consetetur sadipscing
												elitr, sed diam nonumy eirmod tempor invidunt ut labore et
												dolore magna aliquyam erat, sed diam voluptua.</p>
										</div>
									</a>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-6 text-center">
							<div class="service-box">
								<div class="thumbnail">
									<a href="/w3images/lights.jpg"> <img src="img/header.jpg"
										alt="Lights" style="width: 100%">
									</a>
									<div class="caption">
										<h3>Veranstaltung D</h3>
										<hr class="divider">
										<p>Lorem ipsum dolor sit amet, consetetur sadipscing
											elitr, sed diam nonumy eirmod tempor invidunt ut labore et
											dolore magna aliquyam erat, sed diam voluptua.</p>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Forum ende -->
		</c:otherwise>
	</c:choose>


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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="vendor/scrollreveal/scrollreveal.min.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/creative.js"></script>
	<script src="js/gallery.js"></script>


</body>

</html>
