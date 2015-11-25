function fileListSection(){
	 $("#subApiList").css("display", "block");
	 $( "#headerData" ).empty();
	 $("#testit").css("display", "none");
     document.getElementById('subApiList').innerHTML = "Dirctory Path: <input id='theDir' type='text' onchange='directoryListingApi()'>"
  }
  
  function directoryListingApi(){
    var theDirValue = $('#theDir').val()
	$.ajax({
		url : "global?directory="+theDirValue,
		type : "GET",
		success : function(data) {
		       //bootbox.alert("hello");
		        document.getElementById('headerData').innerHTML = 'The files under the directory: <b><u>'+theDirValue+'</u></b>';
				var ulTag = "<ul class=\"nav nav-pills nav-stacked\">";
				var ulEndTag = '</ul>';
				var finalUlTag = [];
				for(i in data){    
						finalUlTag[i] = "<li class=\"active\">"+data[i]+"</li>";
				}
				document.getElementById('subApiList').innerHTML = ulTag + finalUlTag.join('') + ulEndTag;				
		},
		error:function(data){
			alert('error');
		}
	});
  }  
  
  function healthCheckApi(){
	 var ulTag1 = "<ul class=\"nav nav-pills nav-stacked\">";
	 var ulEndTag1 = "</ul>";
	 var finalUlTag1 = [];
	 var i = 0;
 	 $.ajax({
			url : "health",
			type : "GET",
			success : function(data) {
				$("#subApiList").css("display", "block");
				$("#testit").css("display", "none");
				document.getElementById('headerData').innerHTML = 'The Statuses of involved componets are:';
				$.each(data, function(index, value) {
				    if(value['status'] != undefined){
				    finalUlTag1[i] = "<li class=\"active\">"+index.charAt(0).toUpperCase()+index.slice(1)+":"+value['status']+"</li>";			    
					}
	                ++i;
			});
			document.getElementById('subApiList').innerHTML = ulTag1 + finalUlTag1.join('') + ulEndTag1;
			},
			error:function(data){
				alert('error');
			}	
		});
   }
   
   function userSearch(){
         $("#testit").css("display", "block");
         document.getElementById('headerData').innerHTML = 'Filter the user records based on the below fields';
         $("#subApiList").css("display", "none");
   }
   
   function userFormFiler(){
		$('#dynamictable').empty();
		$("#dynamictable").css("display", "block");
		var apiCall = buildUrl();
		$.ajax({
			url : apiCall,
			type : "GET",
			success : function(data) {
					$('#dynamictable').append('<table></table>');
					var table = $('#dynamictable').children();
	                $.each(data,function(key,value){
						table.append("<tr><td>"+nullCheck(value.firstName)+"</td><td>"+nullCheck(value.lastName)+"</td><td>"+nullCheck(value.profession)+"</td><td>"+nullCheck(value.age)+"</td><td>"+nullCheck(value.sex)+"</td><td>"+nullCheck(value.city)+"</td></tr>");
					});	
					},
			error:function(data){
				alert('error');
			}
		});
	  };
	  
	  function buildUrl(){
			var apiCall = "user";
			if($("#fname").val()){
				apiCall = apiCall +"?firstname="+$("#fname").val()};
			if($("#lname").val()){
				apiCall = apiCall +"&lastname="+$("#lname").val()};
			if($("#prof").val()){
				apiCall = apiCall +"&profession="+$("#prof").val()};
		    if($("#age").val()){
				apiCall = apiCall +"&age="+$("#age").val()};
			if($("#sex").val()){
				apiCall = apiCall +"&esx="+$("#sex").val()};
			if($("#city").val()){
				apiCall = apiCall +"&city="+$("#city").val()};	
				
			if(apiCall.indexOf('?') === -1){
			   	apiCall = apiCall.replace('&', '?');
		    }
			return apiCall;
		}

		function nullCheck(property){ 
			return (property == null) ?"":property;
		}	  