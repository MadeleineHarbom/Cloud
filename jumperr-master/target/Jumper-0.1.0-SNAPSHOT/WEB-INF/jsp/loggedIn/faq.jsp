<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <!-- Bootstrap glyphicons (icons) -->
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    
  <!-- our CSS -->
  <link rel="stylesheet" type="text/css" href="../css/ourStyles.css"> 
  
  <!-- our JavaScript -->
  <script src="../js/ourScripts.js" defer></script>  
  
  <title>FAQ</title>
</head>
<body>

    <!-- container/grid der indeholder alle elementerne i body-tagget -->           
    <div class="container">
    
        <!-- navigations-menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Jumperr</a>
            
            <!-- ændrer udseendet på navigations-menuen på en mobil-device -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
                <span class="navbar-toggler-icon"></span>
            </button>           
            
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                
                    <!--  Home -->
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                                                            
                    <!--  Jumper - (skal finde et lift)  -->
                    <li class="nav-item">
                        <a class="nav-link" href="/Jumper">Jumper</a>
                    </li>
                    
                    <!--  Driver - (skal tilbyde et lift) -->
                    <li class="nav-item">
                        <a class="nav-link" href="/Driver">Driver</a>
                    </li>                 
                    
                    <!--  About - den har en dropdown-menu -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" data-toggle="dropdown">
                            About
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/AboutUs">About us</a>
                            <a class="dropdown-item" href="/FAQ">FAQ</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/ContactUs">Contact us</a>
                        </div>
                    </li>
                    
                    <!--  Profile -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" data-toggle="dropdown">                            
                            <span class="fa fa-user"></span> Profile
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/MyProfile">My profile</a>
                            <a class="dropdown-item" href="/ProfileSettings">Settings</a>
                            
                            <div class="dropdown-divider"></div>
                            
                            <!--  Logout -->
                            <a class="dropdown-item" href="/Logout">Logout</a>
                        </div>
                    </li>
                                      
                </ul>
            </div>
        </nav>

        <!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->
        <div class="jumbotron">  
                <div class="my-form">
                    <div class="container col-md-8">
                        <div class="row justify-content-center">
                            <div class="col-md-10">
                                <!-- fejl meddelelse -->
                                <div id="error" class="alert alert-danger alert-dismissible" role="alert">
                                   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                   ${error}
                                </div> 
                                    <div class="card">
                                        <div class="card-header">Frequently Asked Questions</div>
                                        <div class="card-body">
                                        
                                            <!-- spørgsmål og svar -->
                                            
                                            <h4>What is Jumperr?</h4>
                                            <p>Jumperr is a revolutionary new way of traveling that puts the environment in the front seat.</p>
                                            
                                            <br/>
                                            
                                            <h4>How is my personal information processed?</h4>
                                            <p>The personal information you provide on Jumperr will only be shared with other Jumperr users if you have an agreement with them. Your personal contact information will not be shared with others.</p>
                                            
                                            <br/>
                                            
                                            <h4>How the trip paid as well as the transfer of the money to the driver's bank account?</h4>
                                            <p>Lift agreements are paid online via the payment module on our platform.
                                            Earnings are automatically transferred to the driver's or jumper's Jumperr balance 24 hours after the lift is over.</p>
                                            
                                            <br/>
                                            
                                            <h4>How can I report a driver?</h4>
                                            <p>You can report any user at any time if you feel you are unfairly treated or feel that you have not received the trip you have paid for. This can be done on the <a href="../ContactUs">Contact us</a> page</p>

                                            <br/>

                                            <p><b>Do you have any other questions?</b></p>
                                            <p>Please feel free to <a href="../ContactUs">contact</a> us, and we will reply as fast a possible within 48 hours.</p>
                                        </div> <!-- card-body -->
                                </div> <!-- card -->
                            </div> <!-- yderste column -->
                        </div> <!-- yderste row -->
                    </div>  <!-- container i login formularen -->         
               </div> <!-- my-form -->
        </div> <!-- jumbotron -->
     </div> <!-- yderste container -->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>