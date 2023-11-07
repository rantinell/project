/**
 * 
 */
console.log("Reply Module.....");

var replyService = (function(){
	
	function add(reply, callback, errorfn) {
		console.log("reply........");
		console.log(reply);
		$.ajax({
			type : 'post',
			url : '/movie/reply/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			error : function(xhr, status, err) {
				if(errorfn) {
					errorfn(err);
				}
			}
		})
	}
	
	function getList(param, callback, errorfn) {
	
		var mi_num = param.mi_num;
		var page = param.page || 1;
		
		$.getJSON("/movie/reply/pages/" + mi_num + ".json",
			function(data){
				if(callback) {
					callback(data.replyCnt, data.list);
				}
			})
			.fail(function(xhr, status, err) {
				if(errorfn){
					errorfn();
				}
			});
	}
	
	
	function remove(c_num, replyer, callback, errorfn) {
		$.ajax({
			type : 'delete',
			url : '/movie/reply/' + c_num,
			data: JSON.stringify({c_num:c_num, replyer:replyer}),
			contentType: "application/json; charset=utf-8",
			success : function(deleteResult, status, xhr) {
				if(callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, err) {
				if(errorfn) {
					errorfn(err);
				}
			}
		});	
	}
	
	
	function update(reply, callback, errorfn) {
	
		console.log("c_num: " + reply.c_num);
		
		$.ajax({
			type : 'put',
			url : '/movie/reply/' + reply.c_num,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			error : function(xhr, status, err) {
				if(errorfn) {
					errorfn(err);
				}
			}
		});
	}
	
	function get(c_num, callback, errorfn) {
		$.get("/movie/reply/" + c_num + ".json", function(result) {
			
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if(errorfn) {
				errorfn();
			}
		});
	}

	function displayTime(timeValue){
		var today = new Date();
		var gap = today.getTime()-timeValue;
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000*60*60*24)){
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();	
			return [(hh>9?'':'0')+hh,':',(mi>9?'':'0')+mi,':',(ss>9?'':'0')+ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;
			var dd = dateObj.getDate();	
			return [yy,'/',(mm>9?'':'0')+mm,'/',(dd>9?'':'0')+dd].join('');
		}
	}
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
	};
	
})();