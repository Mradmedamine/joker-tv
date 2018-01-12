$(function() {

	initmoviesForm();
	initmoviesDataTables();

	function initmoviesDataTables() {
		var moviesDataTable = $('#movies-datatables').DataTable(dataTablesConfig);
	}

	function initmoviesForm() {
		var form = $('#movies-form');
		var submitBtn = $('.btn-save');
		$(submitBtn).on('click', function(e) {
			if ($(form).valid()) {
				var data = $(form).serializeObject();
				$.ajax({
				type : 'POST',
				url : '/movies',
				data : data,
				success : function(data) {
					$('#result-container').html(data);
					initmoviesDataTables();
					toastr['success']('success !');
					$('#toast-container .toast-' + messageType).show();
				}
				});
			}
		});
	}

});