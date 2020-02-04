<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Form</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/freelancer.min.css" rel="stylesheet" type="text/css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" href="css/custom.css">
   <script type="application/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="application/javascript" src="js/bootstrap.min.js"></script>

<script src="controller/angular.min.js"></script>
   
<script>
var app=angular.module("myApp",[]);

app.factory("loginfactory",function($http,$q){
    var factoryObject = {};
    factoryObject.doLogin=function(){
       var defer = $q.defer(); $http.post("loginCommonValues",{'action':'COMMONVALUES'}).then(function(data){
            defer.resolve(data)
        },function(error){
            defer.reject(error);
        });
    return defer.promise;
    }
    return factoryObject;
});



app.controller("roleCtrl",function($scope,loginfactory){
	//$scope.rolesList=[];
   $scope.fetchRole=function(){
	var promise = loginfactory.doLogin();  
        promise.then(function(data){
            $scope.rolesList = data.data;
        },function(error){
            $scope.error = error;
        })
   }
})

/*
app.controller('roleCtrl', function($scope,$http){
	$scope.rolesList=[];
	$scope.fetchRole=function(){
	 $http({
         method : 'POST',
         //url : "courseServlet?action=SELECT"
         url : "loginCommonValues",
         data : {'action':'COMMONVALUES'}
         ,
         headers: {
             'Content-Type': 'application/json'
         }
         
 }).success(function(data, status, headers, config) {
	 $scope.rolesList = data;
	console.log(JSON.stringify(rolesList));
	 
 }).error(function(data, status, headers, config) {
         // called asynchronously if an error occurs
         // or server returns response with an error status.
 });
	};
	
});
*/

</script>

  </head>
  
  <body class="loginpage" ng-app="myApp" ng-controller="roleCtrl" ng-init="fetchRole()">
   <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            
            <a class="navbar-brand" href="#page-top">Learning Management System</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="page-scroll">
                    <a href="HomePage.html">Home</a>
                </li>
                <li class="page-scroll">
                    <a href="#page-top">Explore</a>
                </li>
                <li class="page-scroll">
                    <a href="register.jsp">Registration</a>
                </li>
                <li class="page-scroll">
                    <a href="#page-top">Login</a>
                </li>
                <li class="page-scroll">
                    <a href="#contact">Contact us</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="login">
	<h1>Login</h1>
    <form method="post" action="loginServlet">
    
    
        <select name="roles" class="Options" ng-model="courseId"  >
            <option ng-repeat="role in rolesList" value="{{role.identity}}">{{role.roleName}}</option>
           
        </select>
    	<input type="text" name="username" placeholder="Username" required="required"/>
        <input type="password" name="pwd" placeholder="Password" required="required"/>
        
        <% String error="";
        		if(request.getParameter("error")!=null){
        			error=request.getParameter("error");
        		}
        %>
        <p style="color: white"><%=error %></p>
        <input type="submit" class="btn btn-primary btn-block btn-large" value="Submit"/>
    </form>
</div>
   <footer class="text-center" id="logincontactus">
    <div class="footer-above">
        <div class="container">
            <div class="row">
                <div class="footer-col col-md-4">
                    <h3>Location</h3>
                    <p>A-1, Dheeraj Vihar, Karala,
                        <br>New Delhi 110081, INDIA</p>
                </div>
                <div class="footer-col col-md-4">
                    <h3>Around the Web</h3>
                    <ul class="list-inline">
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-google-plus"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-linkedin"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-dribbble"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="footer-col col-md-4">
                    <h3>About Online  Test Engine</h3>
                    <p>Learning Management System Provide online learning and examination facility to college students.</p>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-below">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    Copyright &copy; Your Website 2016
                </div>
            </div>
        </div>
    </div>
</footer>
  </body>
</html>
