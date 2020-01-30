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
  
  <!-- Google MAP API -->
  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDRfXcW_SSX-xnAuMlF2by63o8jtqUqDNk&libraries=places"></script>    
      
  <title>Driver</title>
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
                                                            
                    <!--  Jumper - (skal finde et lift)  -->
                    <li class="nav-item">
                        <a class="nav-link" href="/Jumper">Jumper</a>
                    </li>
                    
                    <!--  Driver - (skal tilbyde et lift) -->
                    <li class="nav-item">
                        <a class="nav-link active" href="/Driver">Driver</a>
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
        
            <!-- 
            <div class="alert alert-warning mb-5">
                <h4 class="alert-heading">Info!</h4>
                <p>Please note that it may take some time before your trip is public to other users. If you have any questions, please feel free to contact us.</p>
                <hr/>
                <p>You will receive an email as soon a user chooses to join your trip.</p>
            </div>           
            -->
 
            <!-- trip-formular -->               
            <main class="my-form">
                <div class="container col-md-8">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <!-- fejl meddelelse -->
                            <div id="error" class="alert alert-danger" role="alert">${error}</div>  
                                <div class="card">
                                    <div class="card-header">Trip</div>
                                    <div class="card-body">
                                        <form name="my-form" autocomplete="off" action="Driver" method="post">
                                        
                                            <div class="form-group row">
                                                <label for="full_name" class="col-md-4 col-form-label text-md-right">Date</label>
                                                <div class="col-md-6">
                                                    <input type="date" id="datefield" class="form-control" name="date" required="true">
                                                    <small id="errorDate" class="text-muted form-errors">The date should not be before today</small>
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Time of departure</label>
                                                <div class="col-md-6">
                                                    <input type="time" id="timefield1" class="form-control" name="timeOfDeparture" required="true">
                                                    <small id="errorTime1" class="text-muted form-errors">The time should not be before local time unless it is a day in the future</small>
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="address" class="col-md-4 col-form-label text-md-right">Time of arrival</label>
                                                <div class="col-md-6">
                                                    <input type="time" id="timefield2" class="form-control" name="timeOfArrival" required="true">
                                                    <small id="errorTime2" class="text-muted form-errors">The time should not be before time of departure unless it is a day in the future</small>
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="phoneNumber" class="col-md-4 col-form-label text-md-right">Departure address</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="fromAddress" class="form-control" name="departureAddress" required="true">
                                                    <div id="map-canvas1"></div> <!-- her vises adresse-forslagene fra Google -->
                                                </div>
                                            </div>
            
                                            <div class="form-group row">
                                                <label for="username" class="col-md-4 col-form-label text-md-right">Arrival address</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="toAddress" class="form-control" name="arrivalAddress" required="true">
                                                    <div id="map-canvas2"></div> <!-- her vises adresse-forslagene fra Google -->
                                                </div>
                                            </div>   
                                            
                                            <div class="form-group row">
                                                <label for="username" class="col-md-4 col-form-label text-md-right">Price per km</label>
                                                <div class="col-md-6">                                                            
                                                    <select class="custom-select custom-select-md" name="pricePerKm" required="true">
                                                      <option disabled selected hidden>Select price in dkk</option>
                                                      <option value="2">Two</option>
                                                      <option value="4">Four</option>
                                                      <option value="6">Six</option>
                                                    </select>
                                                </div>
                                            </div>                                                   
                                            
                                            <div class="form-group row">
                                                <label for="username" class="col-md-4 col-form-label text-md-right">Available seats</label>
                                                <div class="col-md-6">                                                                                                                  
                                                    <select class="custom-select custom-select-md" name="availableSeats" required="true">
                                                      <option disabled selected hidden>Select available seats</option>
                                                      <option value="1">One</option>
                                                      <option value="2">Two</option>
                                                      <option value="3">Three</option>
                                                      <option value="4">Four</option>
                                                      <option value="5">Five</option>
                                                      <option value="6">Six</option>
                                                      <option value="7">Seven</option>
                                                      <option value="8">Eight</option>
                                                      <option value="9">Nine</option>
                                                      <option value="10">Ten</option>
                                                    </select>                                                       
                                                </div>
                                            </div>                                                      
            
                                            <div class="col-md-6 offset-md-4">
                                                <button type="submit" class="btn btn-primary">
                                                    Create trip
                                                </button>
                                            </div>
                                      </form>
                                    </div> <!-- card-body -->
                                </div> <!-- card -->
                            </div> <!-- yderste column -->
                        </div> <!-- yderste row -->
                    </div>  <!-- container i login formularen -->         
               </main> <!-- trip formularen -->
           </div> <!-- jumbotron -->
    </div> <!-- yderste div container -->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    


</body>
</html>