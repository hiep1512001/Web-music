<%-- 
    Document   : login
    Created on : Nov 6, 2022, 2:07:07 PM
    Author     : hoang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Music</title>
	<link rel="stylesheet" href="../assets/css/style-login-reg.css">
    <link rel="stylesheet" href="./assets/css/style-login-reg.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500&family=Roboto:wght@300;400;500;700&display=swap">
</head>

<body>
    <div class="login">
        <script src="https://accounts.google.com/gsi/client" async defer></script>
        <div class="logo">
            <p>
                <i class="fa-solid fa-music"></i>
                Music
            </p>
        </div>

        <!-- login with -->
        <div class="container">
            <p class="container__header">To continue, log in to Music.</p>
            <div class="container__login-with" id="btns">
                    <!-- button -->
                <div class="fb-login-button" 
	            data-width="" data-size="large" 
	            data-button-type="continue_with" 
	            data-layout="rounded" 
	            data-auto-logout-link="false" 
	            data-use-continue-as="false"
	            data-onlogin="checkLoginState();">    
	        </div>
                <button class="btn btn--gg" >
                    <span class="icon-gg" style="float: left; margin: 0 0 5px 10px;"></span>
                    <a style="text-decoration: none; color: black" href="https://accounts.google.com/o/oauth2/auth?scope=openid%20email%20profile&redirect_uri=http://localhost:8080/music_app/LoginWithApiServlet&response_type=code
		   &client_id=400727485680-va07ln04qr2fbg5ng3db8gsdveh4v806.apps.googleusercontent.com&approval_prompt=force">
                        Continue with Google
                    </a>
                </button>
            </div>
            <div class="line" id="sperator">
                <hr>
                <p>OR</p>
                <hr>
            </div>

            <!-- login with account -->
            <form action="<%=request.getContextPath()%>/LoginServlet" method="post" class="form-login">
                <!-- form login -->
                <div class="user-address">
                    <p class="form__user">Email address </p>
                    <input type="text" placeholder="Email address " name="email" class="form__input-user" required>
                </div>

                <div class="user-password">
                    <p class="form__password">Password</p>
                    <input type="password" placeholder="Password" name="password" class="form__input-password" required>
                </div>
                <!-- forgot password-->
                <a href="" class="forgot-password">Forgot your password?</a>
                
                 <div class="zxc">
                <!-- remember password-->
                <div class="remember">
                    <input type="checkbox" class="remember__checkbox">
                    <p>Remember me</p>
                </div>
                <button class="btn btn--login">Login</button>
            </div>
            </form>
            
            <hr class="last-line">

            <p class="asd">Don't have an account?</p>
            <div class="signpFor">
                <button class="btn btn--signUpFor">
                    <a href="./ConfirmUser.jsp">Sign up for Spotify</a> 
                </button>
            </div>
        </div>
    </div>
<script>
function checkLoginState() {
	 FB.getLoginStatus(function(response) {
	 statusChangeCallback(response);
	 });
	 }
function statusChangeCallback(response) {
	 console.log('statusChangeCallback');
	 console.log(response);
	 console.log(response.authResponse.accessToken);
	 FB.login((response)=>{
             if (response.status === 'connected') {
	 window.location.href='../LoginWithApiServlet?access_token='+response.authResponse.accessToken; 
	 } else {
	 // The person is not logged into your app or we are unable to tell.
	 document.getElementById('status').innerHTML = 'Please log ' +
	 'into this app.';
	 }
         },{scope: 'public_profile,email'});
}
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1264144231018304',
      cookie     : true,
      xfbml      : true,
      version    : 'v15.0'
    });
    FB.getLoginStatus(function(response) {
  	  statusChangeCallback(response);
  	  });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
</body>

</html>
