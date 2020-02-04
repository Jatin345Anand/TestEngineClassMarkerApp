<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<%
response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
response.setDateHeader("Expires", 0);
response.setHeader("Pragma", "no-cache");
String user = null;
if(session.getAttribute("username") == null || session.getAttribute("role") == null){
	response.sendRedirect("index.jsp");
}
else{ 
	int role=Integer.parseInt(session.getAttribute("role").toString());
	if(role!=2){
		response.sendRedirect("index.jsp");
	}
	else{
	user = (String) session.getAttribute("username");
	}
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Welcome: <%=session.getAttribute("username")%></title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<!--<link href="css/freelancer.min.css" rel="stylesheet" type="text/css">-->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Ubuntu:500|Vollkorn" rel="stylesheet">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/adminpage.css">
<link rel="stylesheet" href="css/addtest.css">

<script src="controller/angular.min.js"></script>
    <script src="controller/mainController.js"></script>
    <script src="controller/addTestController.js"></script>
</head>
<body id="page-top" class="index" ng-app="myApp" ng-controller="uploadQuestionCtrl" ng-init="init()">
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom colornav">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="index.jsp">Online Test Engine</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
			<li class="page-scroll">
                    <a href="facultypage.jsp">Home</a>
                </li>
                <li class="page-scroll">
                    <a href="#" onclick="logout()">Logout</a>
      			</li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div class="group col-xs-12">
<aside class="left col-xs-3 col-md-6 col-sm-12 col-lg-3">
   <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
   <div class="panel-heading" role="tab" id="headingtwo">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#" aria-expanded="true" aria-controls="collapsetwo">
        	Course Details	
        </a>
      </h4>
    </div>
    <div id="collapsetwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingtwo">
      <div class="panel-body">
       <ul class="nav-sidebar nav">
        <li><a href="facultyviewcourse.jsp"> View Course</a></li>
        	<li><a href="addcontent.jsp"> Add Course Content</a></li>
        	<li><a href="addcourse.jsp"> Add Course</a></li>
          </ul>
      </div>
  </div>
  <div class="panel-heading" role="tab" id="headingthree">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="" aria-expanded="true" aria-controls="collapsethree">
         Test Details
        </a>
      </h4>
    </div>
    <div id="collapsethree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingthree">
      <div class="panel-body">
       <ul class="nav-sidebar nav">
        <li><a href="addtestnew.jsp" style="background-color: gray;">Add Test</a></li>
        <li><a href="facultyviewtest.jsp">Test View</a></li>
          </ul>
      </div>
  </div>
   <div class="panel-heading" role="tab" id="headingfour">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#" aria-expanded="true" aria-controls="collapsefour">
         Student Details
        </a>
      </h4>
    </div>
    <div id="collapsefour" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingfour">
      <div class="panel-body">
       <ul class="nav-sidebar nav">
        <li><a href="facultyviewstudent.jsp">View Student</a></li>
          </ul>
        </div>
  	</div>
  	<div class="panel-heading" role="tab" id="headingthree">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#" aria-expanded="true" aria-controls="collapsethree">
         Faculty Details
        </a>
      </h4>
    </div>
    <div id="collapsethree" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingthree">
      <div class="panel-body">
       <ul class="nav-sidebar nav">
			<li><a href="facultyresetpassword.jsp"> Change Password</a></li>
			<li><a href="facultyeditprofile.jsp"> Edit Profile</a></li>
          </ul>
      </div>
  </div>
       </div>
    </div>
</aside>
<!--<section class="right">-->
<div class="col-xs-9">
		<div class="row">
			<div class="col-xs-12 col-md-6 col-lg-4">
				<div class="panel panel-blue panel-widget ">
					<div class="row no-padding">
						<div class="col-sm-3 col-lg-5 widget-left">
							<svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>
						</div>
						<div class="col-sm-9 col-lg-7 widget-right">
							<div class="large">{{pageData.admin}}</div>
							<div class="text-muted">Total Admin</div>
						</div> 
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-4">
				<div class="panel panel-teal panel-widget">
					<div class="row no-padding">
						<div class="col-sm-3 col-lg-5 widget-left">
							<svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>
						</div>
						<div class="col-sm-9 col-lg-7 widget-right">
							<div class="large">{{pageData.student}}</div>
							<div class="text-muted">Total Students</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-4">
				<div class="panel panel-widget">
					<div class="row no-padding">
						<div class="col-sm-3 col-lg-5 widget-left panel-red">
							<svg class="glyph stroked email"><use xlink:href="#stroked-email"/></svg>
						</div>
						<div class="col-sm-9 col-lg-7 widget-right">
							<div class="large">{{pageData.test}}</div>
							<div class="text-muted">Total test</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
         	   <div id="mainContent" class="col-lg-12">
            <form method="post" name="addtest" onsubmit="return validate();">
            <table class="sidestyle">
            <tr>
                <td class="tablestyle"><label for="facultyid">Faculty ID</label>
                  <span><h4><%=session.getAttribute("loginid") %></h4></span>
                    <label for="course_id">Course ID</label>
                    <select name="course_id" ng-model="courseId" ng-change="changeevtcourse()" placeholder="Select Course ID">
                            <option  ng-repeat="course in courseIdList" value="{{course.courseID}}">{{course.title}}</option>
                </select>
                    </td>
            </tr>
               <tr>
                <td class="tablestyle"><label for="testname">Test Name</label>
                    <input type="text" name="testname" ng-model="testName" placeholder="Test Name">
                    <label for="testtime">Test Timing</label>
                    <input type="number" name="testtime" ng-model="testTime" placeholder="Test Duration"></td>
            </tr>
            <tr>
                <td  class="tablestyle">
                    <label for="minmarks">Minimum Marks</label>
                    <input type="number" name="minmarks" ng-model="minMarks" placeholder="MinMarks">
                    <label for="testtime">Total Marks</label>
                    <input type="number" name="totalmarks" ng-model="totalMarks" placeholder="Total Marks">
                </td>
            </tr>
            <tr>
            <td>
                <label>Total Question :</label>
                    <p>{{totalQuestions}}</p>
                </td>
            </tr>
            <tr>
                <td>
                    <hr/>
                    <label for="textareaname" style="vertical-align: top;">Question </label>
                    <textarea  class="textareadetails" name="testquestion" ng-model="question" id="textareaname" placeholder="Write Question here...." cols="50px" rows="4"/></textarea>
                </td></tr>
             <tr><td>
            <label for="option_A">Option A</label>
            <input type="text" placeholder="Option A" name="a" ng-model="optionA"/>
                </td></tr>
            <tr><td>
                <label for="option_B">Option B</label>
                <input type="text" placeholder="Option B" name="b" ng-model="optionB"/>
            </td></tr>
            <tr><td>
                <label for="option_C">Option C</label>
                <input type="text" placeholder="Option C" name="c" ng-model="optionC"/>
            </td></tr>
            <tr><td>
                <label for="option_D">Option D</label>
                <input type="text" placeholder="Option D" name="d" ng-model="optionD"/>
            </td></tr>
            <tr><td>
                <label for="answer">Answer</label>
                <input type="text" placeholder="Answer" name="d" ng-model="answer"/>
            </td></tr>
            <tr>
                <td>
                    <input type="button" ng-click="submit()" class="button" value="Add Question">
                    <button ng-click="upload()" name="publish test">Publish Test</button>
                    <p>{{resultMsg.msg}}</p>
                </td>
            </tr>
            </table>
        <hr/>
        <table class="table sidestyle2">
            <thead>
            <tr>
                <th>Q No.</th>
                <th>Question:</th>
                <th>Option A</th>
                <th>Option B</th>
                <th>Option C</th>
                <th>Option D</th>
                <th>Answer</th>
            </tr>
            </thead>
            <tbody>
           
            <tr ng-repeat="q in questionListUpload">
        <th scope="row">{{q.questionNumber}}</td>
        <td>{{q.question}}</td>
        <td>{{q.optionA}}</td>
        <td>{{q.optionB}}</td>
        <td>{{q.optionC}}</td>
        <td>{{q.optionD}}</td>
        <td>{{q.answer}}</td>
    	</tr>
        
            </tbody>
        
        </table>
         <br>
		</div>
	</div>
</div>
		</div>
<footer class="footer">
   <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    Copyright &copy; Your Website 2016
                </div>
            </div>
        </div>
</footer>

</form>
<script type="application/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="application/javascript" src="js/bootstrap.min.js"></script>
<script src="js/ajax.js"></script>
<script src="js/lumino.glyphs.js"></script>
<script type="application/javascript" src="js/addtest.js"></script>

</body>
</html>
