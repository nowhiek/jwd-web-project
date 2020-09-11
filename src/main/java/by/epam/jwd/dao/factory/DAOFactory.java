package by.epam.jwd.dao.factory;

import by.epam.jwd.dao.ExamDAO;
import by.epam.jwd.dao.FacultyDAO;
import by.epam.jwd.dao.MatriculantDAO;
import by.epam.jwd.dao.PlanDAO;
import by.epam.jwd.dao.ResultDAO;
import by.epam.jwd.dao.SpecialtyDAO;
import by.epam.jwd.dao.SpecialtyHasSubjectDAO;
import by.epam.jwd.dao.SubjectDAO;
import by.epam.jwd.dao.TypeStudyDAO;
import by.epam.jwd.dao.UserDAO;
import by.epam.jwd.dao.UserDetailDAO;

public interface DAOFactory {
	UserDAO getUserDAO();
	UserDetailDAO getUserDetailDAO();
	FacultyDAO getFacultyDAO();
	PlanDAO getPlanDAO();
	SpecialtyDAO getSpecialtyDAO();
	TypeStudyDAO getTypeStudyDAO();
	MatriculantDAO getMatriculantDAO();
	ResultDAO getResultDAO();
	SubjectDAO getSubjectDAO();
	ExamDAO getExamDAO();
	SpecialtyHasSubjectDAO getSpecialtyHasSubjectDAO();
}
