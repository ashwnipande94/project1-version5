<%@ include file="header.jsp" %>










<div ng-app="app" ng-controller="myCtrl">
<table>

 <tbody>
   
        
        
        <tr ng-repeat="wlist in jsonData">  
            <td>{{slist.productName}}</td>  
            <td>{{slist.productDescription}}</td>  
            <td>{{slist.productQuantity}}</td>  
         
            <td><a href="deleteSubcategory-{{slist.subcategoryId}}">Delete</a></td> 
</tr>  
</tbody>


 <script type="text/javascript" src="resources/js/angular.min.js"></script>
    <script type="text/javascript">
    var app=angular.module('app',[]);
    app.controller("myCtrl",function ($scope){
  
   $scope.jsonData=${wishList};
    });

  </script>
  </table>
  </div>
  







<%@ include file="footer.jsp" %>