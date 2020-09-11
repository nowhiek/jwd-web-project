$(document).ready(
	function($) {

		$(document).on('change', '#formFaculty', function() {
			var facultyName = $(this).val();
			var typeName = $('#formStudy').val();
			
			console.log(facultyName);
			console.log(typeName);

			$.get('/jwd-web-project/AjaxController', {
				command : 'get_specialties_by_faculty',
				faculty_name: facultyName,
				type_study: typeName
				
			}, getSpecialties);
		});
		
		$(document).on('change', '#formStudy', function() {
			var typeName = $(this).val();
			var facultyName = $('#formFaculty').val();

			$.get('/jwd-web-project/AjaxController', {
				command : 'get_specialties_by_faculty',
				faculty_name: facultyName,
				type_study: typeName
				
			}, getSpecialties);
		});

		function getSpecialties(data) {
			$('#formSpecialty option').remove();

			let specialties = '';

			$.each(data, function(specialty, listSpec1) {
				
				specialties += '<option>' + listSpec1.specialtyName + '</option>';

			});
			
			console.log(specialties);

			$('#formSpecialty').append(specialties);
		}
		
		
		$(document).on('click', '#facultiesTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_faculties'
				
			}, createTableFaculties);
		});
		
		
		function createTableFaculties(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let faculties = '<thead><tr><th>#</th><th>Faculty name</th><th>Edit</th></tr></thead><tbody>';
								
			$.each(data, function(faculty, f) {
				
				faculties += '<tr>'
								+ '<th scope="row">' + f.id + '</th>'
								+ '<td>' + f.facultyName + '</td>'
								+ '<td><a href="Controller?command=show_update_faculty&id_faculty=' + f.id + '">Edit</a>' +
							 '</tr>';
			});
			
			faculties += '</tbody>';

			$('#table_list').append(faculties);
		}
		
		$(document).on('click', '#usersTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_users'
				
			}, createTableUsers);
		});
		
		function createTableUsers(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let users = '<thead><tr><th>#</th><th>Login</th><th>Password</th><th>Email</th><th>Role</th><th>Ban</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(user, u) {

				users += '<tr>'
							+ '<th scope="row">' + u.id + '</th>'
							+ '<td>' + u.userName + '</td>'
							+ '<td>' + u.userPassword + '</td>'
							+ '<td>' + u.userEmail + '</td>'
							+ '<td><c:choose><c:when test="${user.getUserRole() == USER}">User</c:when></c:choose></td>'
							+ '<td>' + u.isBanned +'</td>'
							+ '<td><a href="Controller?command=show_user_data&id_user=' + u.id + '">Edit</a>' +
						'</tr>';
			});
			
			users += '</tbody>';

			$('#table_list').append(users);
		}
		
		$(document).on('click', '#countPlacesTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_plans'
				
			}, createTablePlans);
		});
		
		function createTablePlans(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let plans = '<thead><tr><th>#</th><th>Count places</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(plan, p) {
				
				plans += '<tr>'
							+ '<th scope="row">' + p.id + '</th>'
							+ '<td>' + p.countPlaces + '</td>'
							+ '<td><a href="Controller?command=show_update_plan&id_plan=' + p.id + '">Edit</a>' +
						'</tr>';							
			});
			
			plans += '</tbody>';

			$('#table_list').append(plans);
		}
		
		$(document).on('click', '#typesTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_types'
				
			}, createTableTypes);
		});
		
		function createTableTypes(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let types = '<thead><tr><th>#</th><th>Type study name</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(type, t) {
				
				types += '<tr>'
							+ '<th scope="row">' + t.id + '</th>'
							+ '<td>' + t.studyName + '</td>'
							+ '<td><a href="Controller?command=show_update_type&id_type=' + t.id + '">Edit</a>' +
						'</tr>';							
			});
			
			types += '</tbody>';

			$('#table_list').append(types);
		}
		
		$(document).on('click', '#specialtiesTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_specialties'
				
			}, createTableSpecialties);
		});
		
		function createTableSpecialties(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let specialties = '<thead><tr><th>#</th><th>Type study name</th><th>Faculty name</th><th>Specialty name</th><th>Count places</th><th>Count year study</th><th>Edit</th><th>List</th><th>Matriculants</th></tr></thead><tbody>';

			$.each(data, function(specialty, s) {
				
				specialties += '<tr>'
								+ '<th scope="row">' + s.id + '</th>'
								+ '<td>' + s.nameTypeStudy + '</td>'
								+ '<td>' + s.nameFaculty + '</td>'
								+ '<td>' + s.specialtyName + '</td>'
								+ '<td>' + s.countPlan + '</td>'
								+ '<td>' + s.countYearStudy + '</td>'
								+ '<td><a href="Controller?command=show_update_specialty&id_specialty=' + s.id + '">Edit</a>' 
								+ '<td><a href="Controller?command=create_list_by_specialty&id_specialty=' + s.id + '">Create</a>'
								+ '<td><button class="btn btn-link btn-sm" type="submit" value="' + s.id + '" id="tableMatriculantsBySpecialty">Show</button>' +
							  '</tr>';							
			});
			
			specialties += '</tbody>';

			$('#table_list').append(specialties);
		}
				
		
		$(document).on('click', '#tableMatriculantsBySpecialty', function() {
			var idSpecialty = $(this).val();

			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_matriculants_by_specialty',
				id_specialty : idSpecialty
				
			}, createTableMatriculantsBySpecialty);
		});
		
		function createTableMatriculantsBySpecialty(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let matriculants = '<thead><tr><th>#</th><th>Id user</th><th>Id specialty</th><th>Certificate</th><th>Activated</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(matriculant, m) {
				
				matriculants += '<tr>'
								+ '<th scope="row">' + m.id + '</th>'
								+ '<td><a href="Controller?command=show_user_data&id_user=' + m.idUser + '">' + m.idUser + '</a>'
								+ '<td><a href="Controller?command=show_update_specialty&id_specialty=' + m.idSpecialty + '">' + m.idSpecialty + '</td>'
								+ '<td>' + m.certificate + '</td>'
								+ '<td>' + m.isActivated + '</td>'
								+ '<td><a href="Controller?command=show_update_matriculant&id_matriculant=' + m.id + '">Edit</a>' +
							  '</tr>';							
			});
			
			matriculants += '</tbody>';

			$('#table_list').append(matriculants);
		}
		
		$(document).on('click', '#matriculantsTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_matriculants'
				
			}, createTableMatriculants);
		});
		
		$(document).on('click', '#matriculantsTableAll', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_matriculants'
				
			}, createTableMatriculants);
		});
		
		$(document).on('click', '#matriculantsTableActive', function() {
			var activeName = $(this).val();
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_matriculants_by_property',
				app : activeName
				
			}, createTableMatriculants);
		});
		
		$(document).on('click', '#matriculantsTableNotActive', function() {
			var activeName = $(this).val();
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_matriculants_by_property',
				app : activeName
				
			}, createTableMatriculants);
		});
		
		function createTableMatriculants(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let matriculants = '<thead><tr><th>#</th><th>Id user</th><th>Specialty name</th><th>Certificate</th><th>Activated</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(matriculant, m) {
				
				matriculants += '<tr>'
								+ '<th scope="row">' + m.id + '</th>'
								+ '<td><a href="Controller?command=show_user_data&id_user=' + m.idUser + '">' + m.idUser + '</a>'
								+ '<td>' + m.nameSpecialty + '</td>'
								+ '<td>' + m.certificate + '</td>'
								+ '<td>' + m.isActivated + '</td>'
								+ '<td><a href="Controller?command=show_update_matriculant&id_matriculant=' + m.id + '">Edit</a>' +
							  '</tr>';							
			});
			
			matriculants += '</tbody>';

			$('#table_list').append(matriculants);
		}
		
		$(document).on('click', '#subjetsTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_subjects'
				
			}, createTableSubjects);
		});
		
		function createTableSubjects(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let subjects = '<thead><tr><th>#</th><th>Subject name</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(subject, s) {
				
				subjects += '<tr>'
								+ '<th scope="row">' + s.id + '</th>'
								+ '<td>' + s.subjectName + '</td>'
								+ '<td><a href="Controller?command=show_update_subject&id_subject=' + s.id + '">Edit</a>' +
							  '</tr>';							
			});
			
			subjects += '</tbody>';

			$('#table_list').append(subjects);
		}
		
		$(document).on('click', '#specialtyHasSubjectTable', function() {
			
			$.get('/jwd-web-project/AjaxController', {
				command : 'get_all_specialty_has_subject'
				
			}, createSpecialtyHasSubject);
		});
		
		function createSpecialtyHasSubject(data) {
			$('#table_list tr').remove();
			$('#table_list th').remove();
			
			let specialtyHasSubject = '<thead><tr><th>#</th><th>Specialty id</th><th>Subject id</th><th>Edit</th></tr></thead><tbody>';

			$.each(data, function(has, h) {
				
				specialtyHasSubject += '<tr>'
								+ '<th scope="row">' + h.id + '</th>'
								+ '<td><a href="Controller?command=show_update_specialty&id_specialty=' + h.idSpecialty + '">' + h.idSpecialty + '</td>'
								+ '<td><a href="Controller?command=show_update_subject&id_subject=' + h.idSubject + '">' + h.idSubject + '</td>'
								+ '<td><a href="Controller?command=show_update_specialty_has_subject&id_specialty_has_subject=' + h.id + '">Edit</a>' +
							  '</tr>';							
			});
			
			specialtyHasSubject += '</tbody>';

			$('#table_list').append(specialtyHasSubject);
		}
	}
);