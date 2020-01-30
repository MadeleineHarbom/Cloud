<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,model.*,controller.*"%>
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
    
  <title>My recent trips</title>
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
                    <a class="nav-link active" href="/">Home</a>
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
        <h1 class="mb-5">My recent trips</h1>   
        
        <% User jumper = (User) request.getSession().getAttribute("user"); %>
        <% ArrayList<PickUpPoint> pickUpPoints = Controller.getRecentPickuppointsByJumper(jumper); %> 
        
        <% if(pickUpPoints.size() == 0) { %>
            
            <!-- ingen resultater fundet -->        
            <h6 class="font-weight-bold text-center mt-5">You haven't booked any trips yet...</h6> 
            
        <% } else { %>
            <% for(PickUpPoint p : pickUpPoints) { %> 
            
                <% Trip t = Controller.getTripById(String.valueOf(p.getTripId())); %> 
                
                <!-- resultater -->
                <div class="container mb-5 bg-white rounded p-3">
                
                    <!-- Her skal vises de trips samt sine pickuppoint-objekter ud for hver trip -->
                    <div class="row">
                    
                        <div class="col-md-3">
                            <h4>Trip joined</h4>                            
                        </div>
                        
                        <div class="col">
                            Driver: <%= t.getDriver().getName() %> |    
                            Departure-time: <%= t.getTimeOfDeparture() %> | 
                            Arrival-time: <%= t.getTimeOfArrival() %> |
                            From: <%= t.getDepartureAddress() %> |  
                            To: <%= t.getArrivalAddress() %> | 
                            Price per km: <%= t.getPricePerKm() %>  
                        </div>  
                                            
                    </div>
                    
                    <div class="row mt-3">
                    
                        <div class="col-md-3">
                            <h4>My pickuppoint</h4>
                        </div>
                        
                        <div class="col">
                            Departure address: <%= p.getDepartureAddress() %> |
                            Arrival address: <%= p.getArrivalAddress() %> |
                            Price: <%= p.getPrice() %> kr. |
                            Km: <%= p.getKm() %>
                        </div>
                        
                    </div>  
                    
                    <div class="row mt-3">
                    
                      <!-- søger på samme trip med samme driver & tidspunkter, for dagens dato samt med samme tidspunkter for pickuppoint -->
                      <form class="mb-5" autocomplete="off" action="Jumper" method="post">
                          <input type="hidden" name="recentTrip" value="true">
                          <input type="date" id="datefield" class="form-control datefield ml-3" name="date" required="true">
                          <input type="time" id="timefield1" class="form-control timefield mt-2 ml-3" name="timeOfDeparture" required="true">
                          <input type="hidden" name="tripId" value="<%= t.getId() %>">  
                          <input type="hidden" name="pickUpPointFrom" value="<%= p.getDepartureAddress() %>">
                          <input type="hidden" name="pickUpPointTo" value="<%= p.getArrivalAddress() %>">     
                                          
                          <input type="submit" class="btn btn-primary btn-md ml-3 mt-3" value="Search for trip">
                      </form>
                    </div>          
                </div>  
                
            <% } %> 
        <% } %> 
          
   </div>

</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    

</body>
</html>
