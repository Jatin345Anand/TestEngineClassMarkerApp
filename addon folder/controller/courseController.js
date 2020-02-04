

app.controller('courseController', function($scope, $http){
	
	$scope.courseOperation={
		    courseList:[],
		    searchOn:false,

		    deleteCourse:function(a){
		    	  $http({
	                    method : 'POST',
	                    //url : "courseServlet?action=SELECT"
	                    url : "courseModifyServlet",
	                    //data : {'action':'ADD',course:obj}
	                    data : {'action':'DELETE','id':a.courseID,'title': a.title,'detail':a.Details,'file': a.imagePath}
	                    ,
	                    headers: {
	                        'Content-Type': 'application/json'
	                    }
	                    
	            }).success(function(data, status, headers, config) {
	                    //$scope.courseData = data;
	            	$scope.result=data[0].data;
	            }).error(function(data, status, headers, config) {
	                    // called asynchronously if an error occurs
	                    // or server returns response with an error status.
	            });
		    	  
		    	  $scope.courseOperation.fetchCourse();
		    	  
		    },
		    editCourse:function(a){
		    	$scope.courseId=a.courseID;
		    	$scope.courseTitle=a.title;
		    	 $scope.courseDetail=a.Details;
		    	 $scope.courseFile=a.imagePath;
		    	 //$scope.update.hide=false;
		    },
		    updateCourse:function(){
		    	 $http({
	                    method : 'POST',
	                    //url : "courseServlet?action=SELECT"
	                    url : "courseModifyServlet",
	                    //data : {'action':'ADD',course:obj}
	                    data : {'action':'UPDATE','id':$scope.courseId,'title': $scope.courseTitle,'detail': $scope.courseDetail,'file': $scope.courseFile}
	                    ,
	                    headers: {
	                        'Content-Type': 'application/json'
	                    }
	                    
	            }).success(function(data, status, headers, config) {
	                    //$scope.courseData = data;
	            	$scope.result=data[0].data;
	            	
	            }).error(function(data, status, headers, config) {
	                    // called asynchronously if an error occurs
	                    // or server returns response with an error status.
	            });
		    	 
		    	 $scope.courseOperation.fetchCourse();
		    },
		    saveTasks:function(){
		        console.log(this.taskList);
		     //$window.localStorage.savedTaskList=JSON.stringify(this.taskList);
		        $window.localStorage.savedTaskList=angular.toJson(this.taskList);
		    },
		    fetchCourse:function(){
		    	 $http({
                     method : 'POST',
                     //url : "courseServlet?action=SELECT"
                     url : "courseModifyServlet",
                     data : {'action':'SELECT','id':'0','title': $scope.courseTitle,'detail': $scope.courseDetail,'file': $scope.courseFile}
                     ,
                     headers: {
                         'Content-Type': 'application/json'
                     }
                     
             }).success(function(data, status, headers, config) {
            	 $scope.courseOperation.courseList = data;
             }).error(function(data, status, headers, config) {
                     // called asynchronously if an error occurs
                     // or server returns response with an error status.
             });
		        
		    }
		}
    
});

