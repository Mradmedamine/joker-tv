$(function() {
	
    $('#accountType a').on('click', function() {
		var value = $(this).data('value');
		var hiddenInput = $(this).data('toggle');
		$('#' + hiddenInput).prop('value', value);
		$('a[data-toggle="' + hiddenInput + '"]').not('[data-value="' + value + '"]').removeClass('active');
		$('a[data-toggle="' + hiddenInput + '"][data-value="' + value + '"]').addClass('active');
    });
    
});