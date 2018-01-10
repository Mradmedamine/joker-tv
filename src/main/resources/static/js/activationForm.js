$(function() {

    initActivationForm();

    function initActivationForm() {
	// Fields
	var form = $('#activation-form');
	var formFields = $(form).find('input, select, textArea');
	var saveBtn = $('.btn-save');

	var url = 'http://7star.jokeriptv.com/7star/activation.php';

	$(saveBtn).on('click', function(e) {
	    if ($(form).valid()) {
		var data = $(form).serializeObject();
		$.ajax({
		    type : 'GET',
		    url : url,
		    data : data,
		    success : function(data) {
			toastr["success"](data);
			$('#toast-container .toast-success').show();
		    }
		});
	    }
	});

    }
});