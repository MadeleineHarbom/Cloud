<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,model.*,storage.*"
    import="java.time.*" import="java.time.temporal.ChronoUnit"
    import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">

<!-- Bootstrap glyphicons (icons) -->
<link
    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
    rel="stylesheet">

<!-- our CSS -->
<link rel="stylesheet" type="text/css" href="../css/ourStyles.css">

<!-- our JavaScript -->
<script src="../js/ourScripts.js" defer></script>

<title>Settings</title>
</head>
<body>

    <div class="container">

        <!-- navigations-menu -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
            class="navbar-brand" href="#">Jumperr</a> <!-- ændrer udseendet på navigations-menuen på en mobil-device -->
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                <!--  Home -->
                <li class="nav-item"><a class="nav-link" href="/">Home</a></li>

                <!--  Jumper - (skal finde et lift)  -->
                <li class="nav-item"><a class="nav-link" href="/Jumper">Jumper</a>
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
                    class="nav-link active dropdown-toggle" href="#"
                    id="navbarDropdown2" data-toggle="dropdown"> <span
                        class="fa fa-user"></span> Profile
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

        <!-- Nav tabs -->
        <ul class="nav nav-tabs mt-5">

            <li class="nav-item"><a class="nav-link active"
                data-toggle="tab" href="#menu1">Driver section</a></li>

            <li class="nav-item"><a class="nav-link" data-toggle="tab"
                href="#menu2">Jumper section</a></li>

            <li class="nav-item"><a class="nav-link" data-toggle="tab"
                href="#menu3">Subscription</a></li>

        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <!-- DRIVER| TRIPS OFFERED BY USER-->
            <div id="menu1" class="container tab-pane active">
                <br>
                <h3>Trips I have offered</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed
                    do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
            </div>
            <!-- JUMPERR| TRIPS JOINED BY USER-->
            <div id="menu2" class="container tab-pane fade">
                <br>
                <h3>Trips you have joined</h3>
                <br>
                <div class="row">
                    <%
                        int userId = (int) request.getAttribute("ID");
                    %>
                    <%
                        ArrayList<Trip> TRIPS = (ArrayList<Trip>) request.getAttribute("TRIPS");
                    %>
                    <%
                        PickUpPoint userPickUpPoint = null;
                    %>
                    <!-- LOOP THE TRIPS JOINED-->
                    <%
                        for (Trip trip : TRIPS) {
                    %>

                    <br>
                    <div class="col-sm-6">
                        <div class="card text-center">
                            <div class="card-header">
                                <%=trip.getDepartureAddress()%>
                                ➡
                                <%=trip.getArrivalAddress()%>
                            </div>
                            <div class="card-body">
                                <ul class="list-group">
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center">
                                        Driver: <%=trip.getDriver().getName()%> <span
                                        class="badge badge-primary badge-pill"><%=trip.getDriver().getRating()%></span>
                                    </li>
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center">
                                        Remaining seats<span class="badge badge-primary badge-pill"><%=trip.getAvailableSeats()%></span>
                                    </li>
                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center">


                                        <!-- GET THE PICK_UP_POINT FOR THE TRIP JOINED ABOVE--> <%
                                            ArrayList<PickUpPoint> pickUpPoints = (ArrayList<PickUpPoint>) trip.getPickUpPoints();
                                         %> <!-- LOOP THE PICUPS --> <%
                                            for (int i = 0; i < pickUpPoints.size(); i++) {
                                         %> <%
                                            if (pickUpPoints.get(i).getJumper().getId() == userId) {
                                         %> 
                                         <% userPickUpPoint = pickUpPoints.get(i); %>
                                         
                                         Your pick up point: <%=pickUpPoints.get(i).getDepartureAddress()%> 
                                         ➡
                                         <%=pickUpPoints.get(i).getArrivalAddress()%>
                                         <%
                                            break; }
                                         %> 
                                         <%
                                            }
                                          %>
                                        <button type="button" class="btn btn-success"
                                            data-toggle="modal" data-target="#exampleModal">
                                            Edit <span class="fa fa-edit"></span>
                                        </button>
                                    </li>



                                    <!-- ================= MODAL=========================== -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1"
                                        role="dialog" aria-labelledby="exampleModalLabel"
                                        aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Current
                                                        pick up point</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">

                                                    <div class="input-group flex-nowrap"
                                                        style="width: 85%; margin: auto;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="addon-wrapping">Picked
                                                                up at</span>
                                                        </div>
                                                        <input type="text" class="form-control"
                                                            
                                                            placeholder="<%=userPickUpPoint.getDepartureAddress()%>"
                                                            aria-label="Username" aria-describedby="addon-wrapping">

                                                    </div>
                                                    <br>
                                                    <div id="googleMap"
                                                        style="width: 85%; margin: auto; height: 275px;"></div>

                                                    <script>
                                                        function myMap() {
                                                            var mapProp = {
                                                                center : new google.maps.LatLng(
                                                                        56.1629,
                                                                        10.2039),
                                                                zoom : 10,
                                                            //mapTypeId: 'satellite'

                                                            };
                                                            var map = new google.maps.Map(
                                                                    document
                                                                            .getElementById("googleMap"),
                                                                    mapProp);
                                                        }
                                                    </script>
                                                    <br>
                                                    <div class="input-group flex-nowrap"
                                                        style="width: 85%; margin: auto;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="addon-wrapping">Your
                                                                destination</span>
                                                        </div>
                                                        <input type="text" class="form-control"
                                                            placeholder="<%=userPickUpPoint.getArrivalAddress()%>"
                                                            aria-label="Username" aria-describedby="addon-wrapping">
                                                        <div class="input-group-prepend"></div>
                                                    </div>
                                                    <br>
                                                    <div class="input-group flex-nowrap"
                                                        style="width: 85%; margin: auto;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="addon-wrapping">Drivers
                                                                destination</span>
                                                        </div>
                                                        <input type="text" class="form-control"
                                                            placeholder="<%=trip.getArrivalAddress()%>"
                                                            aria-label="Username" aria-describedby="addon-wrapping">
                                                        <div class="input-group-prepend"></div>
                                                    </div>
                                                    <br>
                                                    
                                                    <div class="input-group flex-nowrap"
                                                        style="width: 85%; margin: auto;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text" id="addon-wrapping">Passengers</span>
                                                        </div>
                                                         <% String passagerNames = ""; %>
                                                         <%
                                                            for (int i = 0; i < pickUpPoints.size(); i++) {
                                                         %> 
                                                            <%passagerNames += pickUpPoints.get(i).getJumper().getName() + " | "; %>
                                                         <% 
                                                            }
                                                         %>
                                                        
                                                        <input type="text" class="form-control"
                                                            placeholder="<%=passagerNames%>"
                                                            aria-label="Username" aria-describedby="addon-wrapping">
                                                        <div class="input-group-prepend"></div>
                                                    </div>




                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                        data-dismiss="modal">Close</button>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- END MODAL-->

                                </ul>
                                <a href="/Delete?pickuppointIdTest=<%=userPickUpPoint.getId()%>"
                                    style="margin-top: 25px;" class="btn btn-danger">Cancel
                                    reservation</a>
                                    
                                    
                                    
                                <img style="width:60px; height: 60px; float: right;" src="data:image/png;base64,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"> 
                                    
                            </div>
                            <div class="card-footer text-muted">2 days from today</div>
                        </div>
                        <br> <br>
                        <!--  END CARD -->
                    </div>
                    <br> <br>
                    <!--  END COL2 -->
                    
                    <!--  END LOOP TRIPS -->
                    <%
                        } 
                    %>

                </div>
                <!--  END ROW -->
            </div>


            <div id="menu3" class="container tab-pane fade">
                <br>
                <h3>Subscription</h3>
                <p>Sed ut perspiciatis unde omnis iste natus error sit
                    voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
            </div>

        </div>

    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>


    <!-- Google MAP API -->
    <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDRfXcW_SSX-xnAuMlF2by63o8jtqUqDNk&callback=myMap"></script>


</body>
</html>