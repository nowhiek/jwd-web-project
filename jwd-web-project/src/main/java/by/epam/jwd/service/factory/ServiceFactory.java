package by.epam.jwd.service.factory;

import by.epam.jwd.service.FacultyService;
import by.epam.jwd.service.MatriculantService;
import by.epam.jwd.service.PlanService;
import by.epam.jwd.service.ResultService;
import by.epam.jwd.service.SpecialtyService;
import by.epam.jwd.service.TypeStudyService;
import by.epam.jwd.service.UserDetailService;
import by.epam.jwd.service.UserService;
import by.epam.jwd.service.impl.FacultyServiceImpl;
import by.epam.jwd.service.impl.MatriculantServiceImpl;
import by.epam.jwd.service.impl.PlanServiceImpl;
import by.epam.jwd.service.impl.ResultServiceImpl;
import by.epam.jwd.service.impl.SpecialtyServiceImpl;
import by.epam.jwd.service.impl.TypeStudyServiceImpl;
import by.epam.jwd.service.impl.UserDetailServiceImpl;
import by.epam.jwd.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	
    private final UserService userServiceImpl = new UserServiceImpl();
    private final UserDetailService userDetailServiceImpl = new UserDetailServiceImpl();
    private final TypeStudyService typeStypeServiceImpl = new TypeStudyServiceImpl();
    private final SpecialtyService specialtyServiceImpl = new SpecialtyServiceImpl();
    private final ResultService resultServiceImpl = new ResultServiceImpl();
    private final PlanService planServiceImpl = new PlanServiceImpl();
    private final MatriculantService matriculantServiceImpl = new MatriculantServiceImpl();
    private final FacultyService facultyServiceImpl = new FacultyServiceImpl();

    private ServiceFactory(){}
    
    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userServiceImpl;
    }
    
    public UserDetailService getUserDetailService(){
        return userDetailServiceImpl;
    }
    
    public TypeStudyService getTypeStudyService(){
        return typeStypeServiceImpl;
    }
    
    public SpecialtyService getSpecialtyService(){
        return specialtyServiceImpl;
    }
    
    public ResultService getResultService(){
        return resultServiceImpl;
    }
    
    public PlanService getPlanService(){
        return planServiceImpl;
    }
    
    public MatriculantService getMatriculantService(){
        return matriculantServiceImpl;
    }
    
    public FacultyService getFacultyService(){
        return facultyServiceImpl;
    }
}
