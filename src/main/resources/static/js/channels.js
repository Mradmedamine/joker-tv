$(function() {

    var channelContainer = $('#channels-container');

    initChannelsForm();
    initChannelsDataTables();

    function initChannelsDataTables() {
	var channelsDataTable = $('#channels-datatables').DataTable(dataTablesConfig);
    }

    function initChannelsForm() {
	var form = $('#channels-form');
	var activateBtn = $('.btn-save');
	$(activateBtn).on('click', function(e) {
	    if ($(form).valid()) {
		var data = $(form).serializeObject();
		$.ajax({
		    type : 'POST',
		    url : '/channels',
		    data : data,
		    success : function(data) {
			$('#result-container').html(data);
			initChannelsDataTables();
			toastr['success']('success !');
			$('#toast-container .toast-' + messageType).show();
		    }
		});
	    }
	});
    }

});