<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bizvez - The Online Business Hub | Tour</title>
	<jsp:include page="htmlHead.jsp"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/tour.css'/>">
</head>
<body>

	<div id="wrapper">
	
		<jsp:include page="navBar.jsp"/>
		
		<div id="content">
			
			<img src="<c:url value='/images/home.jpg'/>" class="tourFigure left">
			<div id="home" class="tourContent right">
				<h1 class="tour">1. Why Bizvez?</h1>
				<p>
					Want to exhibit your business online and don't know where to start?<br/>
					You've come to the right place!
				</p>

				<p>
					Bizvez is the online business hub where you can create content to promote your business and
					let us worry about hosting and other technical details. You can login any time and update the content.</p>

				<p>So click on the 'Sign Up' link in the black bar on the top and advertise your business to the world in the most optimized way!</p>
			</div>
			
			<img src="<c:url value='/images/signup.jpg'/>" class="tourFigure right">
			<div id="signup" class="tourContent left">
				<h1 class="tour">2. Sign Up</h1>
				<p>Enter your details : first-name , last-name, name of the business (optional), etc. </p>
				<p>You will use the email address that you provide here to login. Create a password for Bizvez.</p>
			</div>
			
			<img src="<c:url value='/images/postLogin.jpg'/>" class="tourFigure left">
			<div id="postLogin" class="tourContent right">
				<h1 class="tour">3. Welcome Page</h1>
				<p>Go to the 'Home' page after you login/sign up.</p>
				<p>You'll see the welcome message with the 'Create My Site' link.</p>
				<p>Click on 'Create My Site' to get started with your site on Bizvez.</p>
			</div>
			
			<img src="<c:url value='/images/siteDetails.jpg'/>" class="tourFigure right">
			<div id="siteDetails" class="tourContent left">
				<h1 class="tour">4. Site Details</h1>
				<p>The most important field here is the 'Bizvez Site Address'. What you enter here determines how your site will be addressed.</p>
				<p>e.g. If you enter 'cloudnine'. Your site's address will be <span class="bold">www.bizvez.com/site/cloudnine</span></p>
				<p>Click on the green button to start editing.</p>
			</div>
			
			<img src="<c:url value='/images/editor.jpg'/>" class="tourFigure left">
			<div id="editor" class="tourContent right">
				<h1 class="tour">5. Create Site Content</h1>
				<p>Use the editor to create content for your site. This is what your site's visitors will see. You can embed images too.</p>
				<p> <span class="bold">Coming Soon : </span> Tips on using the editor for creating fancy content.</p>
			</div>
			
		</div>
	
	</div>
  
</body>
</html>