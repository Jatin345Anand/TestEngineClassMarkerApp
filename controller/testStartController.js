app.controller('testController', function ($scope,$http, $interval, $filter) {
	
	
	
	console.log("entered in controller");
	$scope.testVisible=true;
	$scope.count=-1;
	$scope.questionList=[];
	$scope.left=function(){
        count=0;
        $scope.questionList.filter(function(item){
            if (item.answer==""){
                count++;
            }
        });
        return count;
    }	
	
	$scope.loadTest=function(){
		console.log("controller called");
		
		
		//timerCode
		
		//$scope.callAtInterval();
		
		
    if(localStorage.questionList){
    	console.log("inside if value of count"+$scope.count);
    	$scope.questionList=JSON.parse(localStorage.getItem("questionList"));
    	$scope.count=parseInt(localStorage.getItem("currentQuestion"));
    	console.log("inside if value of count after local"+$scope.count);
    	$scope.testDetails=JSON.parse(localStorage.getItem("testDetails"));
    	
    	
    	$scope.StartTimer();
    	
    	
    	$scope.nextQ();
    }
    else{
	 $http({
         method : 'POST',
         //url : "courseServlet?action=SELECT"
         url : "getTestServlet",
         data : {'action':'SELECT','testid':getParameterByName('id')}
         ,
         headers: {
             'Content-Type': 'application/json'
         }
         
 }).success(function(data, status, headers, config) {
	 
	 var dataSubmit=new submit(data.courseid,data.facultyID,data.testName,data.testDuration,data.MinMarks,data.totalMarks,data.testid);
	 
	 if(data.questionList){
	 localStorage.testDetails=JSON.stringify(dataSubmit);
	 localStorage.questionList=JSON.stringify(data.questionList);
	 
	 
	 //localStorage.currentQuestion=-1;
	 //$scope.count=localStorage.getItem("currentQuestion");
	$scope.testDetails=JSON.parse(localStorage.getItem("testDetails"));
		
     $scope.questionList=JSON.parse(localStorage.getItem("questionList"));
     
     
     
     $scope.StartTimer();
     $scope.nextQ();
	 }
	 //$scope.testOperation.questionList = data;
 }).error(function(data, status, headers, config) {
         // called asynchronously if an error occurs
         // or server returns response with an error status.
 			
		});
		    
    }
	};
    
	
	$scope.Timer = null;
	
	
	
	//$scope.finishTime=Date().setMinutes(new Date().getMinutes()+$scope.testDetails.testTime);
	$scope.finishTime=new Date().setMinutes(new Date().getMinutes());
	//Timer start function.
	$scope.StartTimer = function () {

		$scope.total=$scope.questionList.length;
		console.log("total questions :"+$scope.total);
		
	    //Set the Timer start message.
	    //$scope.Message = "Timer started. ";

	    //Initialize the Timer to run every 1000 milliseconds i.e. one second.
	    $scope.Timer = $interval(function () {
	    	$scope.startTime=new Date();
	    	
	        //Display the current time.
	    	finishMinutes=$scope.finishTime-$scope.startTime;
	        //var time = $filter('date')(new Date(), 'mm:ss');
	    	var time = $filter('date')(finishMinutes, 'mm:ss');
	        $scope.Message = time;
	    }, 1000);
	};
	
	 $scope.callAtInterval = function() {
	        //console.log("$scope.callAtInterval - Interval occurred");
		 $scope.submit();
	    }

	    //$interval( function(){ $scope.callAtInterval(); }, 1000*$scope.testDetails.testTime);
	 $interval( function(){ $scope.callAtInterval(); }, 1000*60*30);
	
	/*
	//Timer stop function.
	$scope.StopTimer = function () {

	    //Set the Timer stop message.
	    $scope.Message = "Timer stopped.";

	    //Cancel the Timer.
	    if (angular.isDefined($scope.Timer)) {
	        $interval.cancel($scope.Timer);
	    }
	};
	*/
    
	
	
    $scope.nextQ=function(){
        console.log("next called");
        $scope.count++;
        $scope.questionNo=$scope.questionList[$scope.count].questionNo;
        $scope.question=$scope.questionList[$scope.count].question;
        $scope.optionA=$scope.questionList[$scope.count].optionA;
        $scope.optionB=$scope.questionList[$scope.count].optionB;
        $scope.optionC=$scope.questionList[$scope.count].optionC;
        $scope.optionD=$scope.questionList[$scope.count].optionD;
        localStorage.currentQuestion=$scope.count-1;
        $scope.change($scope.count);
    };
    $scope.previousQ=function(){
        console.log("previous called");
        $scope.count--;
        $scope.questionNo=$scope.questionList[$scope.count].questionNo;
        $scope.question=$scope.questionList[$scope.count].question;
        $scope.optionA=$scope.questionList[$scope.count].optionA;
        $scope.optionB=$scope.questionList[$scope.count].optionB;
        $scope.optionC=$scope.questionList[$scope.count].optionC;
        $scope.optionD=$scope.questionList[$scope.count].optionD;
        localStorage.currentQuestion=$scope.count-1;
        $scope.change($scope.count);
    };
    $scope.answered=function(val){
        $scope.questionList[$scope.count].answer=val;
        console.log($scope.questionList[$scope.count].answer);
        localStorage.currentQuestion=$scope.count-1;
        localStorage.questionList=JSON.stringify($scope.questionList);
        
    };
    
    $scope.change=function(count){
    	document.getElementsByName("question")[0].checked=false;
    	document.getElementsByName("question")[1].checked=false;
    	document.getElementsByName("question")[2].checked=false;
    	document.getElementsByName("question")[3].checked=false;
    	
    	//document.getElementById("questionopt").checked=false;
    	/*$scope.questionopt[0]=false;
    	$scope.questionopt[1]=false;
    	$scope.questionopt[2]=false;
    	$scope.questionopt[3]=false;*/
    	/*$scope.optionA.Selected=false;
        $scope.optionB=false;
        $scope.optionC=false;
        $scope.optionD=false;*/
    }
    $scope.submit=function(){
        console.log("submit called");

        $http({
            method : 'POST',
            //url : "courseServlet?action=SELECT"
            url : "getTestServlet",
            //data : {'action':'ADD',course:obj}
            data : {'action':'SUBMIT','userid':'sunil', 'testDetails':$scope.testDetails,'questionList':JSON.parse(localStorage.getItem("questionList"))}
            ,
            headers: {
                'Content-Type': 'application/json'
            }
            
    }).success(function(data, status, headers, config) {
            //$scope.courseData = data;
    	//localStorage.clear();
    	localStorage.removeItem("questionList");
    	$scope.result=data;
    	$scope.testVisible=false;
    	localStorage.result=JSON.stringify($scope.result);
    	
    }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
    });
        
        
      
    };
    });
function submit(courseId,facultyId,testName,testTime,minMarks,totalMarks,testid){
	this.courseId=courseId;
	this.facultyId=facultyId;
    this.testName=testName;
    this.testTime=testTime;
    this.minMarks=minMarks;
    this.totalMarks=totalMarks;
    this.testid=testid;
    
}



