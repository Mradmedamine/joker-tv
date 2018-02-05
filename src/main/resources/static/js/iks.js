$(function() {

    initActivationForm();

    function initActivationForm() {
	var form = $('#iks-form');
	var activateBtn = $('.btn-save');
	$(activateBtn).on('click', function(e) {
	    if ($(form).valid()) {
		var data = $(form).serializeObject();
		$.ajax({
		    type : 'POST',
		    url : '/iks',
		    data : data,
		    dataType : 'json',
		    success : function(data) {
			var status = data['status'];
			var messageType;
			switch (status) {
			case '000':
			    messageType = 'error';
			    break;
			case '550':
			    messageType = 'warning';
			    break;
			case '100':
			    messageType = 'success';
			    break;
			}
			toastr[messageType](data['message']);
			$('#toast-container .toast-' + messageType).show();
		    }
		});
	    }
	});
    }
});