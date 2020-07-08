$(document).ready(
	function($) {

		$(document).on('click', '#formFaculty', function() {
			var facultyName = $(this).val();
			var typeName = $('#formStudy').val();

			$.get('/jwd-web-project/AjaxController', {
				command : 'get_specialties_by_faculty',
				faculty: facultyName,
				type: typeName
				
			}, getSpecialties);
		});

		function getSpecialties(data) {

			$('#formSpecialty option').remove();

			let specialties = '';

			$.each(data, function(specialty, listSpec1) {
				
				console.log(listSpec1.getSpecialtyName);
				specialties += '<option>' + listSpec1.specialtyName + '</option>';

			});

			$('#formSpecialty').append(specialties);

		}
	}
);