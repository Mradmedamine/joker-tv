$(function() {

	const IPTV = 'IPTV', SHARING = 'SHARING';

	var subscriptionsContainer = $('.subscriptions-container');
	var subscriptionType = $(subscriptionsContainer).data('subscription-type');

	initSubscriptionsDataTables();
	initModal();

	function initSubscriptionsDataTables() {
		var subscriptionsDataTable = $('#subscriptions-datatables').DataTable(
				dataTablesConfig);
		return subscriptionsDataTable.rows().count();
	}

	function initModal() {

		var modal = $('#page-wrapper').find('#subscriptionModal');
		var actionForm = $(modal).find('form');
		var closeBtn = $(modal).find('.close');
		var backBtn = $(modal).find('.panel-footer .btn-back');
		var saveBtn = $(modal).find('.panel-footer .btn-save');
		var newBtn = $('#newSubscription');

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
					success : function(data, textStatus, xhr) {
						var toastrLevel = 'success';
						if (xhr.status == 302) {
							toastrLevel = 'warning';
						}
						$(modal).hide();
						toastr[toastrLevel](data);
						$('#toast-container .toast-success').show();
						timer = setTimeout(function() {
							location.reload();
						}, 1000);
					},
					error : function(error) {
						var message = "Device already existing with different Serial Number/Mac Address";
						toastr['error'](message);
						$('#toast-container .toast-error').show();
					}
				});
			}
			return false;
		});

		$(window).on('click', function(event) {
			if (event.target == modal) {
				$(modal).hide();
			}
		});
	}

});