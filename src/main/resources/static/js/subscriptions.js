$(function() {

    var subscriptionsContainer = $('.subscriptions-container');

    initSubscriptionsDataTables();
//    initModal();

    function initSubscriptionsDataTables() {
	var subscriptionsDataTable = $('#subscriptions-datatables').DataTable(dataTablesConfig);
	return subscriptionsDataTable.rows().count();
    }

    function initModal() {

	var modal = $('#page-wrapper').find('#documentModal');
	var actionForm = $(modal).find('form');
	var closeBtn = $(modal).find('.close');
	var backBtn = $(modal).find('.panel-footer .btn-back');
	var saveBtn = $(modal).find('.panel-footer .btn-save');
	var newBtn = $('#importSubscriptions');

	var fileInput = $(actionForm).find(':file');
	var fileName = $(fileInput).attr('data-placeholder');
	$(fileInput).filestyle({
	    placeholder : fileName
	});

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
		var file = $(actionForm).find('#physicalFile').prop('files')[0];
		if (file && file.size > 2048576) {
		    toastr["error"](message.common.fileSizeError);
		    $('#toast-container .toast-error').show();
		    return false;
		}
		if (!file) {
		    file = new File([ "" ], "");
		}
		var formObject = $(this).serializeObject();
		var formData = new FormData();
		formData.append("physicalFile", file);

		$.ajax({
		    type : 'POST',
		    url : "/subscriptions/upload",
		    data : formData,
		    async : false,
		    cache : false,
		    contentType : false,
		    processData : false,
		    success : function(result) {
			$(modal).hide();
			toastr['success'](message.common.savingSuccessMessage);
			$('#toast-container .toast-success').show();
			timer = setTimeout(function() {
			    location.reload();
			}, 1000);
		    },
		    error : function(error) {
			toastr['error']("error bad format ");
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