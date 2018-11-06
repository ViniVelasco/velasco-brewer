var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function() {
	
	function MaskCpfCnpj() {
		this.radioPeopleType = $('.js-radio-people-type');
		this.labelCpfCnpj = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj ');
	}
	
	MaskCpfCnpj.prototype.initialize = function() {
		this.radioPeopleType.on('change', onPeopleTypeUpdated.bind(this));
	}
	
	function onPeopleTypeUpdated(event) {
		var typePeopleSelected = $(event.currentTarget);
		this.labelCpfCnpj.text(typePeopleSelected.data('document'));
		this.inputCpfCnpj.mask(typePeopleSelected.data('maskara'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpj;
}());

$(function() {
	var maskCpfCnpj = new Brewer.MaskCpfCnpj();
	maskCpfCnpj.initialize();
});