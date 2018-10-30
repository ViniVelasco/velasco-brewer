$(function() {
	
	var modal = $('#modalFastRegister');
	var saveButton = modal.find('.js-modal-register-save-btn');
	var form = modal.find('form');
	form.on('submit', function(e) { e.preventDefault() });
	var url = form.attr('action');
	var inputNameStyle = $('#nameStyle');
	var errorMessageContainer = $('.js-message-fast-register-style');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose);
	saveButton.on('click', onSaveButtonClick);
	
	function onModalShow() {
		inputNameStyle.focus();
	}
	
	function onModalClose() {
		inputNameStyle.val('');
		errorMessageContainer.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onSaveButtonClick() {
		var nameStyle = inputNameStyle.val().trim();
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 'name': nameStyle }),
			error: onErrorSavingStyle,
			success: onStyleSaved
		});
	}
	
	function onErrorSavingStyle(obj) {
		var errorMessage = obj.responseText;
		errorMessageContainer.removeClass('hidden');
		errorMessageContainer.html('<span>' + errorMessage + '</span>');
		form.find('.form-group').addClass('has-error');
	} 
	
	function onStyleSaved(style) {
		var comboStyle = $('#styleCombo');
		comboStyle.append('<option value=' + style.id + '>' + style.name + '</option>');
		comboStyle.val(style.id);
		modal.modal('hide');
	}
	
});