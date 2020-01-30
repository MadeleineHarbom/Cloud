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
  
  <title>Contact us</title>
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
                    
                    <!--  Register user -->
                    <li class="nav-item">
                        <a class="nav-link" href="/CreateUser">Register</a>
                    </li>                      
                    
                    <!--  About - den har en dropdown-menu -->
                    <li class="nav-item dropdown">
                        <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                            About
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/AboutUs">About us</a>
                            <a class="dropdown-item" href="/FAQ">FAQ</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/ContactUs">Contact us</a>
                        </div>
                    </li>
                    
                </ul>
            </div>
        </nav> 
        
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
                                    <div class="card-header">
                                      Contact Us
                                    </div>
                                    <div class="card-body">
                                    
                                      <!-- det er følgende E-mail der bliver en en mail til: contact.jumperr@gmail.com -->
                                      <form class="gform pure-form pure-form-stacked" method="POST" data-email="contact.jumperr@gmail.com"
                                      action="https://script.google.com/macros/s/AKfycbzmZh_0MAhHx0J7nD7bKGO3hzLazApwI-pKHG6w9-JkCK3trtM/exec">
                                       <!-- URL i action="" attributten er URL'en for en mail-script som vi har lavet i https://script.google.com/home -->
                                    
                                       <!-- denne div indeholder alle input-felterne der bliver skjult ved submit - det bliver gjort for at vise en besked til brugeren om at en mail er sendt afsted -->
                                       <div class="form-elements">
                                        
                                            <div class="form-group row">
                                                <label for="name" class="col-md-4 col-form-label text-md-right">Name</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="name" name="name" class="form-control" required="true"/>
                                                </div>
                                            </div>
                                              
                                            <div class="form-group row">
                                                <label for="email" class="col-md-4 col-form-label text-md-right">Email</label>
                                                <div class="col-md-6">
                                                    <input type="email" id="email" name="email" class="form-control" required="true"/>
                                                </div>
                                            </div>
                                        
                                            <div class="form-group row">
                                                <label for="message" class="col-md-4 col-form-label text-md-right">Message</label>
                                                <div class="col-md-6">
                                                    <textarea id="message" name="message" class="form-control" rows="10" required="true"></textarea>
                                                </div>
                                            </div>
                                        
                                            <!-- den er skjult da det er et felt der IKKE burde udfyldes af brugeren -->
                                            <!-- en robot vil til gengæld med stor sandsynlighed udfylde feltet -->
                                            <div class="form-group row">
                                                <input id="honeypot" type="hidden" name="honeypot" />
                                            </div>
                                              
                                            <div class="col-md-6 offset-md-4">
                                                <button type="submit" class="btn btn-primary">
                                                   Send
                                                </button>
                                            </div>
                                        
                                        </div> <!-- input-felterne i formularen slutter her -->
                                        
                                        <!-- det er denne besked brugeren ser når formularen bliver submitted korrekt -->
                                        <div class="thankyou_message hiddenElement">
                                          <h2><em>Thanks</em> for contacting us! <br/>
                                            We will get back to you soon!</h2>
                                        </div>                                      
                                                                                                
                                      </form> <!-- formularen slutter her -->                                                                   
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
    
    <!-- gør sådan så at når formularen bliver submitted, forhindrer man at en default JSON-side bliver vist efterfølgende -->
    <!-- der vil i stedet blive vist en besked på siden, der fortæller at en mail er blevet sendt med de udfyldte oplysninger -->
    <script data-cfasync="false" src="../js/form-submission-handler.js"></script>  
</body>
</html>