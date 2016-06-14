angular.module('companyContext', ['ngDialog'])
  .controller('CompanyController', function($scope, $http,ngDialog) {
	  
	  // 定义对象
	  $scope.company = {
	        id: '',
	        companyName: '',
	        companyCode: '',
	        status:''
	  };

	  // 列表初始化
	  $http.get('http://localhost:8080/auth_center/company/list.json').
	  	success(function(data) {
	  		$scope.companys = data;
	  });
	  
	  // 删除
	  $scope.deleteCompany = function(companyId){
		  if(confirm("确定要删除数据吗？"))
		  {
			  $http.get('http://localhost:8080/auth_center/company/delete?companyId='+companyId).
			  	success(function(data) {
			  		$scope.companys = data;
			  });
		  }
	  };
	  
	  // 添加
	  $scope.addCompany = function(){
		  var company = angular.copy($scope.company);
		  $http.post('http://localhost:8080/auth_center/company/add',
				      {'companyName':company.companyName,'companyCode':company.companyCode}
		  ).success(function(data) {
		  		alert("数据添加成功");
		  });
	  }
	  
	  $scope.editCompany = function(companyId){
		  //alert(companyId);
		  //ngDialog.open({ template: '/template/companyEdit.html', plain: true});
	  }
  });