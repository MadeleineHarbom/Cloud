<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.*,storage.LocalStorage"%>
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
    
  <title>Trips</title>
</head>
<body>

<div id="trips_admin" class="container">

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
                    <a class="nav-link active" href="/Trips">Trips</a>
                </li>                   
                
                <!--  Register user -->
                <li class="nav-item">
                    <a class="nav-link" href="/CreateUser">Register</a>
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

            <!-- add trip - åbner i et Modal -->
            <button class="btn btn-primary" data-toggle="modal" data-target="#createTrip">
                <span class="fa fa-plus"></span> Add trip
            </button>
            
            <!-- Modal -->
            <div class="modal fade" id="createTrip" role="dialog">
              
              <div class="modal-dialog modal-lg">
              
                <!-- Modal indhold -->
                <div class="modal-content">
                
                  <!-- Modal header -->
                  <div class="modal-header">
                    <h4 class="modal-title">Add trip</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>                   
                  </div>
                  
                  <!-- Modal body -->
                  <div class="modal-body">            
            
                    <!-- trip-formular -->               
                    <main class="my-form">
                        <div class="container col-md-10">
                            <div class="row justify-content-center">
                                <div class="col-md-12">
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
                    
                                                    <!-- Modal footer -->
                                                    <div class="modal-footer">                                                  
                                                        <div class="col-md-6 offset-md-4">                                                  
                                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                            Close
                                                            </button>
                                                            
                                                            <button type="submit" class="btn btn-primary">
                                                            Add trip
                                                            </button>
                                                        </div>           
                                                   </div>   
                                              </form>
                                            </div> <!-- card-body -->
                                        </div> <!-- card -->
                                    </div> <!-- yderste column -->
                                </div> <!-- yderste row -->
                            </div>  <!-- container i login formularen -->         
                       </main> <!-- trip formularen -->      
                  </div> <!-- modal body slutter her -->
                  
                </div> <!-- modal indhold slutter her -->
                
              </div> <!-- modal dialog slutter her -->
            </div> <!-- modal vinduet slutter her -->                            
            
            <!-- Search form -->
            <form class="form-inline mt-3 mb-5 inner-addon left-addon">
              <input class="form-control mr-3 w-20" type="text" placeholder="Search by driver"
                aria-label="Search" id="searchTrip">
              <span class="fa fa-search"></span>
            </form>   
            
            <!-- fejl meddelelse -->
            <div id="error" class="alert alert-danger alert-dismissible" role="alert">
               <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
               ${error}
            </div>                      
            
            <!-- tabel med trips i systemet -->
            <table id="tripsTable" class="table table-striped table-responsive">
                <thead>
                  <tr class="crud-table text-white">
                        <th>ID</th>
                        <th>Day</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Driver</th>
                        <th>Price per km</th>
                        <th>Available seats</th>
                        <th>PickUpPoints</th> 
                        <th>Actions</th>                            
                  </tr>
                </thead>
                <tbody>
                  <%
                      for(Trip t : LocalStorage.getTrips()) {
                  %>
                        
                        <tr>
                           <td><%= t.getId()   %></td>
                           <td><%= t.getDate()    %></td>
                           <td><%= t.getTimeOfDeparture()   %></td>
                           <td><%= t.getTimeOfArrival()   %></td>
                           <td><%= t.getDepartureAddress()   %></td>
                           <td><%= t.getArrivalAddress()   %></td>
                           <td><%= t.getDriver().getName()   %></td>
                           <td><%= t.getPricePerKm()  %></td>
                           <td><%= t.getAvailableSeats()  %></td>
                           <td> <!-- view pick-up-points button samt Modal-vindue -->
                                <button class="btn btn-default" data-toggle="modal" data-target="#modalForTrip<%=t.getId()%>">View</button>
                                            
                                        <!-- Modal -->
                                        <div class="modal fade" id="modalForTrip<%=t.getId()%>" role="dialog">
                                          
                                          <div class="modal-dialog modal-lg">
                                          
                                            <!-- Modal indhold -->
                                            <div class="modal-content">
                                            
                                              <!-- Modal header -->
                                              <div class="modal-header">
                                                <h4 class="modal-title">Pick Up Points</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>                   
                                              </div>  
                                              
                                              <!-- Modal body -->
                                              <div class="modal-body">  
                                 <% if(t.getPickUpPoints().size() != 0) { %>  
                                        <div class="row">   
                                            <div class="col">
                                                    <ul class="nav flex-column nav-pills">                                                                          
                                                                                
                                                <% for(PickUpPoint p : t.getPickUpPoints()) { %>        
                                                                                    
                                                    <li class="nav-item">
                                                        <a class="nav-link showPickUpPointLinks" href="javascript:void(0)" onclick="showPickUpPointInfo(<%=p.getId()%>, this)">Pick-up-point<%=p.getId()%></a>
                                                    </li> 
                                                    
                                                    <!-- indeholder info om pick-up-point - denne span er altid usynlig. Teksten i den bliver brugt til at fylde på textarea alt efter hvilken pick-up-point link der klikkes på -->
                                                    <span id="pickUpPoint<%=p.getId()%>_info" class="d-none">Jumper: <%= p.getJumper().getName() %>&#010;Departure address: <%= p.getDepartureAddress() %>&#010;Arrival address: <%= p.getArrivalAddress() %>&#010;Price: <%= p.getPrice() %> kr.&#010;Km: <%= p.getKm() %></span>                                                              
                                                                                                                                            
                                                <% } %>  
                                                
                                                    </ul>
                                            </div>
                                            <div class="col">
                                                <!-- her fyldes pick-up-point oplysningerne når der vælges en pick-up-point -->
                                                <textarea readonly class="pickUpPoint_textarea form-control mb-3" rows="7"></textarea>  
                                            </div>
                                        </div>
                                    
                                <% } else { %> 
                                    <textarea readonly class="form-control mb-3" rows="5">No pick-up-points added to this trip yet!</textarea>
                                <% } %> 
                                        
                                                 <button type="button" class="btn btn-default" data-dismiss="modal">
                                                     Close
                                                 </button>                                                 
                                                 
                                                 <a href="javascript:void(0)" id="removePickUpPoint" class="btn btn-danger" disabled="true">
                                                     Remove
                                                 </a>   
  
                                                <!-- add-formular -->               
                                                <main class="my-form mt-4 mb-3">
                                                    <div class="container col-md-12">
                                                        <div class="row justify-content-center">
                                                            <div class="col-md-10">
                                                                    <div class="card">
                                                                        <div class="card-header">Add pick-up-point</div>
                                                                        <div class="card-body">
                                                                            <form name="my-form" action="Trips" method="post">
                                                
                                                                                <div class="form-group row">
                                                                                    <label for="phoneNumber" class="col-md-4 col-form-label text-md-right">Departure address</label>
                                                                                    <div class="col-md-6">
                                                                                        <input type="text" class="form-control pip_fromAddress" name="departureAddress" required="true">
                                                                                        <div class="map-canvas3"></div> <!-- her vises adresse-forslagene fra Google -->
                                                                                    </div>
                                                                                </div>
                                                
                                                                                <div class="form-group row">
                                                                                    <label for="username" class="col-md-4 col-form-label text-md-right">Arrival address</label>
                                                                                    <div class="col-md-6">
                                                                                        <input type="text" class="form-control pip_toAddress" name="arrivalAddress" required="true">
                                                                                        <div class="map-canvas4"></div> <!-- her vises adresse-forslagene fra Google -->
                                                                                    </div>
                                                                                </div>   
                                                                                
                                                                                <div class="form-group row">
                                                                                    <label class="col-md-4 col-form-label text-md-right">Price</label>
                                                                                    <div class="col-md-6">
                                                                                        <input type="text" class="form-control" name="price" required="true">
                                                                                    </div>
                                                                                </div>  
                                                                                
                                                                                <div class="form-group row">
                                                                                    <label class="col-md-4 col-form-label text-md-right">KM</label>
                                                                                    <div class="col-md-6">
                                                                                        <input type="text" class="form-control" name="km" required="true">
                                                                                    </div>
                                                                                </div>   
                                                                             
                                                                                <!-- skjult input-felt der indeholder tripId -->  
                                                                                <input type="hidden" class="form-control" name="tripId" value="<%= t.getId() %>">                                                                                                                                                                                        
                                                
                                                                                <div class="col-md-6 offset-md-4">
                                                                                    <button type="submit" class="btn btn-primary">
                                                                                    Add
                                                                                    </button>
                                                                                </div>                                          
                                                                           </form>
                                                                        </div> <!-- card-body -->
                                                                    </div> <!-- card -->
                                                                </div> <!-- yderste column -->
                                                            </div> <!-- yderste row -->
                                                        </div>  <!-- container i login formularen -->         
                                                   </main> <!-- add formularen -->                                                 
                                                                                                                                                                                                  
                                              </div> <!-- modal body slutter her -->
                                              
                                            </div> <!-- modal indhold slutter her -->
                                            
                                          </div> <!-- modal dialog slutter her -->
                                        </div> <!-- modal vinduet slutter her -->                                                                                                                                                                                               
                           </td>
                           <td id="actions_td">
                               <a id="edit_link" href="/Update?tripId=<%= t.getId() %>" data-toggle="tooltip" data-placement="top" title="Edit" class="btn btn-success">
                                   <span class="fa fa-edit"></span> 
                               </a>
                               <a href="/Delete?tripId=<%= t.getId() %>" data-toggle="tooltip" data-placement="top" title="Delete" class="btn btn-danger">
                                   <span class="fa fa-trash"></span> 
                               </a>
                           </td>                                                     
                        </tr>        
                     <% } %>
                </tbody>
            </table>              
        </div>           

    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    

</body>
</html>