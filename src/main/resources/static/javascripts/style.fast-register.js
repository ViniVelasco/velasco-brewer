var Brewer = Brewer || {};

Brewer.StyleFastRegister = function({
	
	function StyleFastRegister() {
		
		this.modal = $('#modalFastRegister');
		this.saveButton = this.modal.find('.js-modal-register-save-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNameStyle = $('#nameStyle');
		this.errorMessageContainer = $('.js-message-fast-register-style');
	}
	
	StyleFastRegister.prototype.initialize = function() {
		this.form.on('submit', function(e) { e.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.saveButton.on('click', onSaveButtonClick.bind(this));
		
	}
	
	function onModalShow() {
		this.inputNameStyle.focus();
	}
	
	function onModalClose() {
		this.inputNameStyle.val('');
		this.errorMessageContainer.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onSaveButtonClick() {
		var nameStyle = this.inputNameStyle.val().trim();
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ 'name': nameStyle }),
			error: onErrorSavingStyle.bind(this),
			success: onStyleSaved.bind(this.)
		});
	}
	
	function onErrorSavingStyle(obj) {
		var errorMessage = obj.responseText;
		this.errorMessageContainer.removeClass('hidden');
		this.errorMessageContainer.html('<span>' + errorMessage + '</span>');
		this.form.find('.form-group').addClass('has-error');
	} 
	
	function onStyleSaved(style) {
		var comboStyle = $('#styleCombo');
		comboStyle.append('<option value=' + style.id + '>' + style.name + '</option>');
		comboStyle.val(style.id);
		this.modal.modal('hide');
	}
	
	return StyleFastRegister;
	
}());


$(function() {
	var styleFastRegister = new Brewer.StyleFastRegister();
	styleFastRegister.initialize();
	
});