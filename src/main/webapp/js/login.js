$("#loginButton").click(function(){
$.ajax({
		url : "global?name=c:\Users\sakaruna",
		type : "GET",
		success : function(data) {
			window.location.href="apitest.html";
		},
		error:function(data){
			alert('error');
		}
	});
	//alert('hello')
});