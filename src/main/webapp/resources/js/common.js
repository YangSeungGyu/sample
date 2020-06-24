common = {
	test1 : function()  {
		alert('test1')
	},
	test2 : function() {
		alert('test1')
	},
	pttrnTest : function(msg) {
		var result = false;
		//첫번째가 대문자N으로 시작하고 그뒤에 10자리 숫자
		var rgEx = /(N)(\d{10})$/g
		result =  rgEx.test(msg);
		return result;
	},
	pttrnMobl : function(num) {
		var result = false;
		//첫번째가 대문자N으로 시작하고 그뒤에 10자리 숫자
		var rgEx = /(01[016789])(\d{4}|\d{3})\d{4}$/g;
		result =  rgEx.test(num);
		return result;
	},
	pttrnNum : function(num) {
		var result = false;
		//첫번째가 대문자N으로 시작하고 그뒤에 10자리 숫자
		var rgEx = /[^0-9]/g;
		result =  rgEx.test(num);
		return result;
	}
	
};