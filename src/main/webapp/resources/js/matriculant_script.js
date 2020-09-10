document.querySelector('.sidebar-sticky').addEventListener('click', (e) => {
	const matriculants = document.querySelector('.matriculants-choose');
	if (e.target.id === 'matriculantsTable') matriculants.classList.remove('none');
	if (e.target.tagName === 'BUTTON' && e.target.id !== 'matriculantsTable') matriculants.classList.add('none');
});