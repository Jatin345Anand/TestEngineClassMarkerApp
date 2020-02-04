app.controller('testController', function ($scope,$http, $interval, $filter) {
	
	
	
	console.log("entered in controller");
	$scope.testVisible=true;
	$scope.count=-1;
	$scope.questionList=[];
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
         data : {'action':'SELECT'}
         ,
         headers: {
             'Content-Type': 'application/json'
         }
         
 }).success(function(data, status, headers, config) {
	 
	 var dataSubmit=new submit(data.courseid,data.facultyID,data.testName,data.testDuration,data.MinMarks,data.totalMarks,data.testid);
	 
	 localStorage.testDetails=JSON.stringify(dataSubmit);
	 localStorage.questionList=JSON.stringify(data.questionList);
	 //localStorage.currentQuestion=-1;
	 //$scope.count=localStorage.getItem("currentQuestion");
		$scope.testDetails=JSON.parse(localStorage.getItem("testDetails"));
		
     $scope.questionList=JSON.parse(localStorage.getItem("questionList"));
     
     $scope.StartTimer();
     $scope.nextQ();
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
    };
    $scope.answered=function(val){
        $scope.questionList[$scope.count].answer=val;
        console.log($scope.questionList[$scope.count].answer);
        localStorage.currentQuestion=$scope.count-1;
        localStorage.questionList=JSON.stringify($scope.questionList);
    };
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

/*
app.controller('LoginCtrl', function($scope,loginFactory,$location){
    $scope.login = function() {
        console.log("Login Call...");
        //loginFactory.postJSON($scope.email,$scope.password)
        var promise = loginFactory.postJSON($scope.email, $scope.password);
        promise.then(function (object) {
            //console.log(object);

            $scope.val = object.msg;
            $location.path('homeStudent');
            console.log("Promise Call"),
                function (reason) {
                    //alert('Failed: ' + reason);
                    $scope.result = reason;
                }
        });
        $scope.result = "Dynamic Values from Controller";
        //$scope.nextQ();
    }

});

app.controller('RegisterCtrl', function($scope,registerFactory){
    $scope.register = function(){
        console.log('Register Call...');
        console.log($scope.email + " " + $scope.password);
        registerFactory.postJSON($scope.email, $scope.password);
    }
});

app.controller('uploadQuestionCtrl', function($scope,uploadQuestionFactory){



     function submit(testName,questions){
         this.testName=testName;
         this.questions=questions;
     }

    $scope.questionListUpload=[];
    $scope. submit= function(){
        console.log('UploadQuestionCtrl Submit Call...');
        var questionObj=new Question($scope.questionNumber,$scope.question,$scope.optionA,$scope.optionB,$scope.optionC,$scope.optionD,$scope.answer);
        //$scope.questionListUpload.push({questionNumber:$scope.questionNumber,question:$scope.question,optionA:$scope.optionA,optionB:$scope.optionB,optionC:$scope.optionC,optionD:$scope.optionD,answer:$scope.answer});
        $scope.questionListUpload.push(questionObj);
    }
    $scope. upload= function(){
        console.log('UploadQuestionCtrl Upload Call...');
        var dataSubmit=new submit($scope.testName,$scope.questionListUpload)
        var promise =uploadQuestionFactory.postJSON(dataSubmit);

        promise.then(function(object) {
            $scope.resultMsg =object;
            console.log("Result in controllers object"+$scope.resultMsg);

        });
    }
    function Question(questionNumber,question,optionA,optionB,optionC,optionD,answer){
        this.questionNumber=questionNumber;
        this.question=question;
        this.optionA=optionA;
        this.optionB=optionB;
        this.optionC=optionC;
        this.optionD=optionD;
        this.answer=answer;
    }
   
});*/

