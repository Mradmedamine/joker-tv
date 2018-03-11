$(function() {

	initServersDataTables();
	initModal();
	
	function initServersDataTables() {
		var serversDataTable = $('#servers-datatables').DataTable(
				dataTablesConfig);
		return serversDataTable.rows().count();
	}

	function initModal() {

		var modal = $('#page-wrapper').find('#addServerModal');
		var actionForm = $(modal).find('form');
		var closeBtn = $(modal).find('.close');
		var backBtn = $(modal).find('.panel-footer .btn-back');
		var saveBtn = $(modal).find('.panel-footer .btn-save');
		var newBtn = $('#addServer');

		$(newBtn).on('click', function() {
			$(modal).show();
		});

		$(closeBtn).on('click', function() {
			$(actionForm).reset();
			$(modal).hide();
		});

		$(backBtn).on('click', function() {
			$(actionForm).reset();
			$(modal).hide();
		});

		$(saveBtn).on('click', function() {
			$(actionForm).submit();
		});

		$(actionForm).submit(function() {
			if ($(actionForm).valid()) {
				var formObject = $(this).serializeObject();
				var url = subscriptionType === IPTV ? '/iptv' : '/sharing';
				url += '/subscriptions';

				$.ajax({
					type : 'POST',
					url : url,
					data : formObject,
					success : function(result) {
						$(modal).hide();
						toastr['success'](result);
						$('#toast-container .toast-success').show();
						timer = setTimeout(function() {
							location.reload();
						}, 1000);
					},
					error : function(error) {
						toastr['error']("error occured ! ");
						$('#toast-container .toast-error').show();
					}
				});
			}
			return false;
		});

	}

	
});