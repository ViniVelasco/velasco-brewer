var Brewer = Brewer || {};

Brewer.ComboState = (function() {
	
	function ComboState() {
		this.combo = $('#state');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboState.prototype.initialize = function() {
		this.combo.on('change', onStateUpdated.bind(this));
	}
	
	function onStateUpdated() {
		this.emitter.trigger('updated', this.combo.val());
	}
	
	return ComboState;
	
}());

Brewer.ComboCity = (function() {
	
	function ComboCity(comboState) {
		this.comboState = comboState;
		this.combo = $('#city');
		this.imgLoading = $('.js-img-loading');
	}
	
	ComboCity.prototype.initialize = function() {
		reset.call(this);
		this.comboState.on('updated', onStateUpdated.bind(this));
	}
	
	function onStateUpdated(event, idState) {
		if(idState) {
			var response = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: "application/json",
				data: { "state": idState },
				beforeSend: initializeRequisition.bind(this),
				complete: finalizeRequisition.bind(this)
			});
			
			response.done(onSearchCitiesFinalized.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onSearchCitiesFinalized(cities) {
		var options = [];
		cities.forEach(function(city) {
			options.push('<option value="' + city.id + '">' + city.name + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione uma cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function initializeRequisition() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizeRequisition() {
		this.imgLoading.hide();
	}
	
	return ComboCity;
	
}());

$(function() {
	
	var comboState = new Brewer.ComboState();
	comboState.initialize();
	
	var comboCity = new Brewer.ComboCity(comboState);
	comboCity.initialize();
});