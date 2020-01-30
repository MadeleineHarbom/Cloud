<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        
    <!-- www.fontawesome.com (icons) -->    
    <link rel="stylesheet" type="text/css" href="../css/all.css">
    
    <!-- Bootstrap glyphicons (icons) -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">      
    
    <!-- our CSS -->
    <link rel="stylesheet" type="text/css" href="../css/ourStyles.css">
    
    <!-- our JavaScript -->
    <script src="../js/ourScripts.js" defer></script>
    
    <!-- www.fontawesome.com javascript (icons) -->    
    <script src="../js/all.js" defer></script>  
    
    <!-- Google MAP API -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDRfXcW_SSX-xnAuMlF2by63o8jtqUqDNk&libraries=places"></script>   
        
    <title>Jumper</title>
</head>
<body>

    <div class="container">

        <!-- navigations-menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a class="navbar-brand" href="#">Jumperr</a> <!-- ændrer udseendet på navigations-menuen på en mobil-device -->
        
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                <!--  Home -->
                <li class="nav-item"><a class="nav-link" href="/">Home</a></li>

                <!--  Trips -->
                <li class="nav-item"><a class="nav-link" href="/Trips">Trips</a>
                </li>

                <!--  Register user -->
                <li class="nav-item"><a class="nav-link" href="/CreateUser">Register</a>
                </li>

                <!--  Jumper - (skal finde et lift)  -->
                <li class="nav-item"><a class="nav-link active" href="/Jumper">Jumper</a>
                </li>

                <!--  Driver - (skal tilbyde et lift) -->
                <li class="nav-item"><a class="nav-link" href="/Driver">Driver</a>
                </li>

                <!--  About - den har en dropdown-menu -->
                <li class="nav-item dropdown"><a
                    class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                    data-toggle="dropdown"> About </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/AboutUs">About us</a> <a
                            class="dropdown-item" href="/FAQ">FAQ</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/ContactUs">Contact us</a>
                    </div></li>

                <!--  Profile -->
                <li class="nav-item dropdown"><a
                    class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
                    data-toggle="dropdown"> <span class="fa fa-user"></span>
                        Profile
                </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="/MyProfile">My profile</a> <a
                            class="dropdown-item" href="/ProfileSettings">Settings</a>

                        <div class="dropdown-divider"></div>

                        <!--  Logout -->
                        <a class="dropdown-item" href="/Logout">Logout</a>
                    </div></li>

            </ul>
        </div>
        </nav>
        
        <!-- bootstrap-spinner - den vises når der søges på trips -->
        <div class="modal fade spinner-modal-lg" data-backdrop="static" data-keyboard="false" tabindex="-1">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <span class="fa fa-spinner fa-spin fa-5x"></span>
                </div>
            </div>
        </div>

        <!-- Jumbotron - det er en form for header eller en udvidet header (hero-section) -->
        <div class="jumbotron">
            <!-- 
            <div class="alert alert-warning mb-5">
                <h4 class="alert-heading">Info!</h4>
                <p>Please be aware that a Driver has the option to accept and
                    reject a passenger. If you have any questions, please feel free to
                    contact us.</p>
                <hr />
                <p>You will receive an email when the driver accepts your trip.</p>
            </div>
            -->
            <img class="img-fluid rounded mx-auto d-block mb-5" width="700px" src="../images/jumper-billede.png" alt="Jumper image">            
            
            <!-- hvis der allerede er søgt på trips gemmes værdierne i disse variable -->
            <% String fromAddress = (String) request.getAttribute("fromAddress"); %>
            <% String toAddress = (String) request.getAttribute("toAddress"); %>      
            
            <!-- success meddelelse - vises når man giver rating til en driver -->
            <div id="error" class="alert alert-success alert-dismissible" role="alert">
               <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
               ${success}
            </div>                     

            <!-- søg på en trip -formular -->
            <form class="mb-5" autocomplete="off" action="Jumper" method="post">
                
                <h3 class="mb-3">Search for a trip:</h3>
                
                <div class="row">
                
                    <!-- col-xm-12 er i Bootstrap-4 lavet om til: col-12 -->
                    <div class="col col-xl-3 col-lg-6 col-md-6 col-12 mb-3 autocomplete">
                        <input type="text" id="fromAddress" <% if(fromAddress != null && toAddress != null) { %> value="<%= fromAddress %>" <% } %> class="form-control" placeholder="Departure Address" name="departureAddress" required="true">
                    </div>
                    <div id="map-canvas1"></div> <!-- her vises adresse-forslagene fra Google -->
                    
                    <div class="col col-xl-3 col-lg-6 col-md-6 col-12 mb-3 autocomplete">
                        <input type="text" id="toAddress" <% if(fromAddress != null && toAddress != null) { %> value="<%= toAddress %>" <% } %> class="form-control" placeholder="Arrival address" name="arrivalAddress" required="true">
                    </div>
                    <div id="map-canvas2"></div> <!-- her vises adresse-forslagene fra Google -->
                    
                    <div class="col col-xl-2 col-lg-3 col-md-6 col-12 mb-3">
                        <input type="date" id="datefield" class="form-control col-xs-2" name="date" required="true">
                    </div>
                    
                    <div class="col col-xl-2 col-lg-3 col-md-6 col-12 mb-3">
                        <input type="time" id="timefield1" class="form-control col-xs-2" name="timeOfDeparture" required="true">
                    </div>                  

                    <div class="col col-xl-2 col-lg-6 col-md-6 col-12">
                       <button class="btn btn-primary btn-block" onclick="searchForTrips_jumper()">Search</button>  
                    </div>              
                </div>
            </form>
            
            <!-- ingen søgeresultater fundet -->
            <div id="noResults" class="container text-center">
                 <h4 class="font-weight-bold">${noResults}</h4>
            </div>
                        
            <!-- søge-resultater -->    
            <%! @SuppressWarnings("unchecked") %>
            <% ArrayList<Trip> matches = (ArrayList<Trip>) request.getAttribute("matches"); %>
            
            <% if(matches != null) { %>
                <% for(Trip t : matches) { %>                   
                    <div class="row mb-5">
                         <div class="card-group col-sm-12">
                         
                                <!-- info om driver -->
                                <div class="card">
                                      <img class="card-img-top" src="/images/<%= t.getDriver().getProfilePicture() %>" alt="Card image">
                                      <div class="card-body">
                                        <h4 class="card-title"><%= t.getDriver().getName() %></h4>
                                        <p class="card-text" data-toggle="modal" data-target="#modalForDriver<%=t.getDriver().getId()%>">
                                        
                                        <%for (int i = 1; i <= 5; i++) {%>
                                             <span class="fa fa-star <% if(t.getDriver().getRating() >= i) { %> checked <% } %> " aria-hidden="true"></span> 
                                        <%}%>        
                                                                       
                                        </p>
                                        <a href="#" class="btn btn-primary">See Profile</a>
                                      </div>
                                </div>
                            
                                <!-- info om trip -->
                                <div class="card" id="trip-info-jumper-search-result">
                                    <div class="card-body text-left d-flex flex-column">
                                        <h3 class="card-title font-weight-bold">Departure: <%= t.getDate() %> <%= t.getTimeOfDeparture() %></h3>
                                        <p class="card-text">From: <%= t.getDepartureAddress() %></p>       
                                        <p class="card-text mb-5">To: <%= t.getArrivalAddress() %></p>
                                        <p class="card-text"> <span data-toggle="tooltip" title="<%= t.getAvailableSeats() %> seats available"><% for(int i = 0; i < t.getAvailableSeats(); i++) { %> <span class="fas fa-chair text-primary"></span><% } %></span></p>
                                        <p class="card-text font-weight-bold">Price per km: <%= t.getPricePerKm() %> kr.</p>         
                                        <a href="/Jumper?tripId=<%= t.getId() %>&departureAddress=<%= fromAddress %>&arrivalAddress=<%= toAddress %>" id="bookSeat" class="btn btn-primary btn-lg mt-auto">Book a seat</a>                  
                                    </div>
                                </div>
                        </div>      
                      </div> 
                    
                      <!-- Rating-Modal -->
                      <div class="modal fade" id="modalForDriver<%=t.getDriver().getId()%>" role="dialog">
                        <div class="modal-dialog">
                        
                          <!-- Modal content-->
                          <div class="modal-content">
                          
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                              
                              <% User user = (User) request.getSession().getAttribute("user"); %>
                              <% User driver = t.getDriver(); %> 
                                                               
                            </div>
                            
                            <div class="modal-body">
                            
                              <p>
                                 <b>Driver:</b> <br/>
                                 <%=t.getDriver().getName() %>
                              </p>
                              
                              <p>
                                 <b>Rating</b>
                                 <% int driverScore = t.getDriver().getRating(); %>                           
                                                          
                                 <form action="/Jumper" method="post" id="ratingForm<%= t.getId() %>">
                                      <input type="hidden" class="ratingGiven" name="driverScore" value="">
                                      <input type="hidden" name="driverId" value=<%= t.getDriver().getId() %>>
                                      
                                      <%for (int i = 1; i <=5; i++) {%>
                                           <span id="star-<%= i %>" class="fa fa-star <% if(t.getDriver().getRating() >= i) { %> checked <% } %> " aria-hidden="true" onclick="updateDriverRating(<%= i %>)"></span> 
                                      <%}%>  
                                      
                                     <br/> <br/>
                                     <button type="submit" class="btn btn-primary">Rate</button>
                                 </form>
                              </p>
                              
                            </div>
                            
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                            
                          </div>
                        </div>
                      </div> <!-- her slutter Rating Modal -->                                      
                                                  
                <% } %>
            <% } %> <!-- matches resultaterne slutter her -->
                       
            <!-- recent trip bliver vist her -->
            <% Trip trip = (Trip) request.getAttribute("trip"); %>
            <% String pickUpPointFrom = (String) request.getAttribute("pickUpPointFrom"); %>
            <% String pickUpPointTo = (String) request.getAttribute("pickUpPointTo"); %>
            
            <% if(trip != null) { %>
            
                     <div class="row mb-5">
                         <div class="card-group col-sm-12">
                         
                                <!-- info om driver -->
                                <div class="card">
                                      <img class="card-img-top" src="/images/<%= trip.getDriver().getProfilePicture() %>" alt="Card image">
                                      <div class="card-body">
                                        <h4 class="card-title"><%= trip.getDriver().getName() %></h4>
                                        <p class="card-text">
                                             <span class="fa fa-star checked" aria-hidden="true"></span>
                                             <span class="fa fa-star checked" aria-hidden="true"></span>
                                             <span class="fa fa-star checked" aria-hidden="true"></span>
                                             <span class="fa fa-star" aria-hidden="true"></span>
                                             <span class="fa fa-star" aria-hidden="true"></span>                                     
                                        </p>
                                        <a href="#" class="btn btn-primary">See Profile</a>
                                      </div>
                                </div>
                            
                                <!-- info om trip -->
                                <div class="card" id="trip-info-jumper-search-result">
                                    <div class="card-body text-left d-flex flex-column">
                                        <h3 class="card-title font-weight-bold">Departure: <%= trip.getDate() %> <%= trip.getTimeOfDeparture() %></h3>
                                        <p class="card-text">From: <%= trip.getDepartureAddress() %></p>       
                                        <p class="card-text mb-5">To: <%= trip.getArrivalAddress() %></p>
                                        <p class="card-text"> <span data-toggle="tooltip" title="<%= trip.getAvailableSeats() %> seats available"><% for(int i = 0; i < trip.getAvailableSeats(); i++) { %> <span class="fas fa-chair text-primary"></span><% } %></span></p>
                                        <p class="card-text font-weight-bold">Price per km: <%= trip.getPricePerKm() %> kr.</p>         
                                        <a href="/Jumper?tripId=<%= trip.getId() %>&departureAddress=<%= pickUpPointFrom %>&arrivalAddress=<%= pickUpPointTo %>" id="bookSeat" class="btn btn-primary btn-lg mt-auto">Book a seat</a>                  
                                    </div>
                                </div>
                        </div>      
                    </div> 
              <% } %> <!-- recent-trip resultatet slutter her -->

        </div> <!-- Jumbotron slutter her -->
        
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    

</body>
</html>