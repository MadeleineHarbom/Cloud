<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,storage.LocalStorage" %>
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
  
  <title>Registration</title>
</head>
<body>

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
                
                <!--  Trips -->
                <li class="nav-item">
                    <a class="nav-link" href="/Trips">Trips</a>
                </li>                   
                
                <!--  Register user -->
                <li class="nav-item">
                    <a class="nav-link active" href="/CreateUser">Register</a>
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
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
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
 
            <!-- formular til oprettelse -->               
            <main class="my-form">
                <div class="container col-md-8">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                        
                                <!-- fejl meddelelse -->
                                <div id="error" class="alert alert-danger alert-dismissible" role="alert">
                                   <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                   ${error}
                                </div> 
                                
                                <div class="card">
                                    <div class="card-header">Register</div>
                                    <div class="card-body">
                                        <form name="my-form" action="CreateUser" method="post">
                                            
                                            <div class="form-group row">
                                                <label for="full_name" class="col-md-4 col-form-label text-md-right">Full Name</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="full_name" class="form-control" name="name" required="true">
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-mail Address</label>
                                                <div class="col-md-6">
                                                    <input type="email" id="email_address" class="form-control" name="email" required="true">
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="address" class="col-md-4 col-form-label text-md-right">Address</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="address" class="form-control" name="address" required="true">
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="phoneNumber" class="col-md-4 col-form-label text-md-right">Phone Number</label>
                                                <div class="col-md-6">
                                                    <input type="tel" id="phone" class="form-control" name="telephoneNumber" required="true">
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="username" class="col-md-4 col-form-label text-md-right">Username</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="username" class="form-control" name="username" required="true">
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="password" class="form-control" name="password" required="true">
                                                </div>
                                            </div>
                                            
                                            <div class="form-group row">
                                                <label for="password" class="col-md-4 col-form-label text-md-right">Admin</label>
                                                
                                                <!-- Admin yes - radiobutton -->
                                                <div class="custom-control custom-radio custom-control-inline mt-2 ml-3">
                                                  <input type="radio" class="custom-control-input" id="defaultInline1" name="admin" value="yes" required="true">
                                                  <label class="custom-control-label" for="defaultInline1">Yes</label>
                                                </div>
                                                
                                                <!-- Admin no - radiobutton -->
                                                <div class="custom-control custom-radio custom-control-inline mt-2 ml-3">
                                                  <input type="radio" class="custom-control-input" id="defaultInline2" name="admin" value="no" required="true">
                                                  <label class="custom-control-label" for="defaultInline2">No</label>
                                                </div>                                             
                                            </div>                                            
            
                                            <div class="col-md-6 offset-md-4">
                                                <button type="submit" class="btn btn-primary">
                                                Register
                                                </button>
                                            </div>
                                      </form>
                                    </div> <!-- card-body -->
                                </div> <!-- card -->
                            </div> <!-- yderste column -->
                        </div> <!-- yderste row -->
                    </div>  <!-- container i login formularen -->         
               </main> <!-- formularen til oprettelse -->
           </div> <!-- jumbotron -->
     </div> <!-- yderste container --> 
    
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    

</body>
</html>