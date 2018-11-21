var Brewer = Brewer || {};

Brewer.UploadPhoto = (function() {
	
	function UploadPhoto() {
		this.inputNamePhoto = $('input[name=photo]');
		this.inputContentType = $('input[name=contentType]');
		
		this.source = $('#beer-photo').html();
		this.template = Handlebars.compile(this.source);
		
		this.containerBeerPhoto = $('.js-container-beer-photo');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadPhoto.prototype.initialize  = function () {

		
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png|gif)',
				action: this.containerBeerPhoto.data('url-photos'),
				complete: onUploadComplete.bind(this),
				beforeSend: addCsrfToken
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if (this.inputNamePhoto.val()) {
			onUploadComplete.call(this, ({name: this.inputNamePhoto.val(), contentType: this.inputContentType.val()}));
		}
	}
	
	function onUploadComplete(response) {
		this.inputNamePhoto.val(response.name);
		this.inputContentType.val(response.contentType);
		
		this.uploadDrop.addClass('hidden');
		var html = this.template({photoName: response.name});
		this.containerBeerPhoto.append(html);
		
		$('.js-remove-photo').on('click', onRemovePhoto.bind(this));
	}
	
	function onRemovePhoto() {
		$('.js-beer-photo').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNamePhoto.val("");
		this.inputContentType.val("");
	}
	
	function addCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}

	
	return UploadPhoto;
	
})();

$(function() {
	
	var uploadPhoto = new Brewer.UploadPhoto();
	uploadPhoto.initialize();
	
});