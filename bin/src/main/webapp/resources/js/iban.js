$(document).ready(function(){

	$('input#validateIbanBtn').click(function(){
		iban.validateViaService();
		return false;
	});

});

var iban = {
		
	validateViaService : function() {
		var loader = $.ajax({
			url: '/iban/validate.json?iban=' + $('input#ibanRequest')
		}); 
		loader.done(function(data){
			console.log('Success! ' + data);
			iban.showIbanValidationResult(data);
		});
		loader.fail(function(jqXHR, textStatus){
			console.log('Failed! ' + textStatus);
			iban.showIbanValidationError();
		});
	},

	showIbanValidationResult : function(result) {
		alert(result.displayForm);
		$('div#ibanValidationResults').html('');
		$('The IBAN ' + result.displayForm 
				+ ' is' 
				+ (result.isValid ? '' : ' not')
				+ ' valid').appendTo('div#ibanValidationResults');
	},
	
	showIbanValidationError : function(result) {
		$('div#ibanValidationResults').html('');
		$('An error occurred whilst validating.').appendTo('div#ibanValidationResults');
	}

};

