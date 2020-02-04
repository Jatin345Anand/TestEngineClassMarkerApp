
app.controller('uploadQuestionCtrl', function($scope,$http){

	 $scope.facultyId="sunil";
		  
	 $scope.totalQuestions=0;
	 /*
	 $scope.courseIdList = [{
		    value: '17',
		    label: 'JAVA2'
		  }, {
		    value: '18',
		    label: 'JAVA3'
		  }]; 
	*/
	 $scope.getCommon=function(){
	 $http({
         method : 'POST',
         //url : "courseServlet?action=SELECT"
         url : "addTestServlet",
         data : {'action':'COMMONVALUES','id':$scope.facultyId}
         ,
         headers: {
             'Content-Type': 'application/json'
         }
         
 }).success(function(data, status, headers, config) {
	 $scope.courseIdList = data;
 }).error(function(data, status, headers, config) {
         // called asynchronously if an error occurs
         // or server returns response with an error status.
 });
}
	 
	 $scope.pageValue=function(){
		 $http({
	         method : 'POST',
	         //url : "courseServlet?action=SELECT"
	         url : "getPagesCommonDataServlet",
	         data : {'action':'COMMONVALUES'}
	         ,
	         headers: {
	             'Content-Type': 'application/json'
	         }
	         
	 }).success(function(data, status, headers, config) {
		 $scope.pageData = data;
	 }).error(function(data, status, headers, config) {
	         // called asynchronously if an error occurs
	         // or server returns response with an error status.
	 });
	}

$scope.init=function(){
		$scope.getCommon();
		$scope.pageValue();
    }
	 
	 
     function submit(courseId,facultyId,testName,testTime,minMarks,totalMarks,questions){
         this.testName=testName;
         this.testTime=testTime;
         this.minMarks=minMarks;
         this.totalMarks=totalMarks;
         this.questions=questions;
     }

    $scope.questionListUpload=[];
    if(localStorage.questionList){
    	$scope.questionListUpload=JSON.parse(localStorage.questionList);
    	$scope.totalQuestions=localStorage.total;
    }
    $scope. submit= function(){
        console.log('UploadQuestionCtrl Submit Call...');
        $scope.totalQuestions++;
        var questionObj=new Question($scope.totalQuestions,$scope.question,$scope.optionA,$scope.optionB,$scope.optionC,$scope.optionD,$scope.answer);
        //$scope.questionListUpload.push({questionNumber:$scope.questionNumber,question:$scope.question,optionA:$scope.optionA,optionB:$scope.optionB,optionC:$scope.optionC,optionD:$scope.optionD,answer:$scope.answer});
        $scope.questionListUpload.push(questionObj);
        
        localStorage.questionList=JSON.stringify($scope.questionListUpload);
        
        localStorage.total=$scope.totalQuestions;
        
    }
    $scope.upload= function(){
        console.log('UploadQuestionCtrl Upload Call...');
        var dataSubmit=new submit($scope.courseId.value,$scope.facultyId.value,$scope.testName,$scope.testTime,$scope.minMarks,$scope.totalMarks,$scope.questionListUpload);
        //var dataSubmit=new submit($scope.testName,$scope.questionListUpload)
        console.log("data in dataSubmit :"+JSON.stringify(dataSubmit));
        
        $http({
            method : 'POST',
            //url : "courseServlet?action=SELECT"
            url : "addTestServlet",
            //data : {'action':'ADD',course:obj}
            data : {'action':'INSERT','test':dataSubmit}
            ,
            headers: {
                'Content-Type': 'application/json'
            }
            
    }).success(function(data, status, headers, config) {
            //$scope.courseData = data;
    	$scope.result=data.data;
    	
    	localStorage.clear();
    	localStorage.total=0;
    	$scope.questionListUpload=[];
    	
    }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
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
});

