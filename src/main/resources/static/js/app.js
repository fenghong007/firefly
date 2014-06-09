$(function(){
	$("#shutDownApp").on('click',function(){
		$.ajax({
			type : "POST",
			url : "shutdown",
			success : function(result) {
				console.log(result);
			}
		});
	});
});