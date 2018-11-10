var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function() {
	
	function MaskCpfCnpj() {
		this.radioPeopleType = $('.js-radio-people-type');
		this.labelCpfCnpj = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj ');
	}
	
	MaskCpfCnpj.prototype.initialize = function() {
		this.radioPeopleType.on('change', onPeopleTypeUpdated.bind(this));
		
		var typePeopleSelected = this.radioPeopleType.filter(':checked')[0];
		if(typePeopleSelected) {
			applyMask.call(this, $(typePeopleSelected));
		}
	}
	
	function onPeopleTypeUpdated(event) {
		var typePeopleSelected = $(event.currentTarget);
		applyMask.call(this, typePeopleSelected);
		this.inputCpfCnpj.val('');
	}
	
	function applyMask(typePeopleSelected) {
		this.labelCpfCnpj.text(typePeopleSelected.data('document'));
		this.inputCpfCnpj.mask(typePeopleSelected.data('maskara'));
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpj;
}());

$(function() {
	var maskCpfCnpj = new Brewer.MaskCpfCnpj();
	maskCpfCnpj.initialize();
});