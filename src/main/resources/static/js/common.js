$(function() {
	$.ajax({
		type : "POST",
		url : "user",
		success : function(users) {
			console.log(users);
		}
	});
	
	$.ajax({
		type : "POST",
		url : "user/save",
		data : "name=萤火虫",
		success : function(user) {
			console.log(user);
		}
	});
	
});