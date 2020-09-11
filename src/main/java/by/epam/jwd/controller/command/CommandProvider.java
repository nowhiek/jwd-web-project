package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommandName;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllFaculties;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllMatriculants;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllMatriculantsByProperty;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllMatriculantsBySpecialty;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllPlans;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllSpecialties;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllSpecialtyHasSubject;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllSubjects;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllTypes;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllUsers;
import by.epam.jwd.controller.command.impl.ajax.impl.ShowSpecialtiesByFaculty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.command.impl.front.CommandName;
import by.epam.jwd.controller.command.impl.front.impl.ActivateMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.AddFaculty;
import by.epam.jwd.controller.command.impl.front.impl.AddMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.AddPlan;
import by.epam.jwd.controller.command.impl.front.impl.AddSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.AddSpecialtyHasSubject;
import by.epam.jwd.controller.command.impl.front.impl.AddSubject;
import by.epam.jwd.controller.command.impl.front.impl.AddTypeStudy;
import by.epam.jwd.controller.command.impl.front.impl.BlockUser;
import by.epam.jwd.controller.command.impl.front.impl.ChangeLocale;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUser;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUserDetail;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUserPassword;
import by.epam.jwd.controller.command.impl.front.impl.CreateExcelTable;
import by.epam.jwd.controller.command.impl.front.impl.CreateListBySpecialty;
import by.epam.jwd.controller.command.impl.front.impl.DeactivateMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.Registr;
import by.epam.jwd.controller.command.impl.front.impl.RemoveApplication;
import by.epam.jwd.controller.command.impl.front.impl.RemoveFaculty;
import by.epam.jwd.controller.command.impl.front.impl.RemoveMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.RemovePlan;
import by.epam.jwd.controller.command.impl.front.impl.RemoveSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.RemoveSpecialtyHasSubject;
import by.epam.jwd.controller.command.impl.front.impl.RemoveSubject;
import by.epam.jwd.controller.command.impl.front.impl.RemoveType;
import by.epam.jwd.controller.command.impl.front.impl.ShowAdminPanel;
import by.epam.jwd.controller.command.impl.front.impl.ShowAllSpecialties;
import by.epam.jwd.controller.command.impl.front.impl.ShowMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.ShowSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateFaculty;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdatePlan;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateSpecialtyHasSubject;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateSubject;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateType;
import by.epam.jwd.controller.command.impl.front.impl.ShowUserData;
import by.epam.jwd.controller.command.impl.front.impl.ShowUserDetail;
import by.epam.jwd.controller.command.impl.front.impl.SignIn;
import by.epam.jwd.controller.command.impl.front.impl.SignOut;
import by.epam.jwd.controller.command.impl.front.impl.UpdateFaculty;
import by.epam.jwd.controller.command.impl.front.impl.UpdateMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.UpdatePlan;
import by.epam.jwd.controller.command.impl.front.impl.UpdateSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.UpdateSpecialtyHasSubject;
import by.epam.jwd.controller.command.impl.front.impl.UpdateSubject;
import by.epam.jwd.controller.command.impl.front.impl.UpdateType;
import by.epam.jwd.controller.command.impl.front.impl.WrongMethod;

public final class CommandProvider {
	
	private static final CommandProvider instance =  new CommandProvider();
	
	private final Map<CommandName, Command> repository = new HashMap<>();
	private final Map<AjaxCommandName, AjaxCommand> ajaxRepository = new HashMap<AjaxCommandName, AjaxCommand>();

    private CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.REGISTR, new Registr());
        repository.put(CommandName.SHOW_USER_DETAIL, new ShowUserDetail());
        repository.put(CommandName.SHOW_ADMIN_PANEL, new ShowAdminPanel()); 
        repository.put(CommandName.SHOW_USER_DATA, new ShowUserData()); 
        repository.put(CommandName.CHANGE_USER_PASSWORD, new ChangeUserPassword());
        repository.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
        repository.put(CommandName.CHANGE_USER_DETAIL, new ChangeUserDetail());
        repository.put(CommandName.SHOW_ALL_SPECIALTIES, new ShowAllSpecialties());
        repository.put(CommandName.SHOW_SPECIALTY, new ShowSpecialty());
        repository.put(CommandName.SHOW_MATRICULANT, new ShowMatriculant()); 
        repository.put(CommandName.ADD_MATRICULANT, new AddMatriculant()); 
        repository.put(CommandName.ADD_FACULTY, new AddFaculty()); 
        repository.put(CommandName.ADD_TYPE_STUDY, new AddTypeStudy()); 
        repository.put(CommandName.ADD_PLAN, new AddPlan()); 
        repository.put(CommandName.ADD_SUBJECT, new AddSubject()); 
        repository.put(CommandName.ADD_SPECIALTY, new AddSpecialty()); 
        repository.put(CommandName.ADD_SPECIALTY_HAS_SUBJECT, new AddSpecialtyHasSubject()); 
        repository.put(CommandName.REMOVE_MATRICULANT, new RemoveMatriculant()); 
        repository.put(CommandName.BLOCK_USER, new BlockUser());
        repository.put(CommandName.CHANGE_USER, new ChangeUser());
        repository.put(CommandName.SHOW_UPDATE_FACULTY, new ShowUpdateFaculty());
        repository.put(CommandName.SHOW_UPDATE_TYPE, new ShowUpdateType());
        repository.put(CommandName.SHOW_UPDATE_PLAN, new ShowUpdatePlan());
        repository.put(CommandName.SHOW_UPDATE_SUBJECT, new ShowUpdateSubject());
        repository.put(CommandName.SHOW_UPDATE_SPECIALTY, new ShowUpdateSpecialty());
        repository.put(CommandName.SHOW_UPDATE_MATRICULANT, new ShowUpdateMatriculant());
        repository.put(CommandName.SHOW_UPDATE_SPECIALTY_HAS_SUBJECT, new ShowUpdateSpecialtyHasSubject());
        repository.put(CommandName.UPDATE_FACULTY, new UpdateFaculty());
        repository.put(CommandName.UPDATE_TYPE, new UpdateType());
        repository.put(CommandName.UPDATE_PLAN, new UpdatePlan());
        repository.put(CommandName.UPDATE_SUBJECT, new UpdateSubject());
        repository.put(CommandName.UPDATE_SPECIALTY, new UpdateSpecialty());
        repository.put(CommandName.UPDATE_MATRICULANT, new UpdateMatriculant());
        repository.put(CommandName.UPDATE_SPECIALTY_HAS_SUBJECT, new UpdateSpecialtyHasSubject());
        repository.put(CommandName.REMOVE_FACULTY, new RemoveFaculty());
        repository.put(CommandName.REMOVE_TYPE, new RemoveType());
        repository.put(CommandName.REMOVE_PLAN, new RemovePlan());
        repository.put(CommandName.REMOVE_SUBJECT, new RemoveSubject());
        repository.put(CommandName.REMOVE_SPECIALTY, new RemoveSpecialty());
        repository.put(CommandName.REMOVE_SPECIALTY_HAS_SUBJECT, new RemoveSpecialtyHasSubject());
        repository.put(CommandName.REMOVE_APPLICATION, new RemoveApplication());
        repository.put(CommandName.ACTIVATE_MATRICULANT, new ActivateMatriculant());
        repository.put(CommandName.DEACTIVATE_MATRICULANT, new DeactivateMatriculant());
        repository.put(CommandName.CREATE_LIST_BY_SPECIALTY, new CreateListBySpecialty());
        repository.put(CommandName.CREATE_EXCEL_TABLE, new CreateExcelTable());
        repository.put(CommandName.WRONG_METHOD, new WrongMethod());
        
        ajaxRepository.put(AjaxCommandName.GET_SPECIALTIES_BY_FACULTY, new ShowSpecialtiesByFaculty());
        ajaxRepository.put(AjaxCommandName.GET_ALL_FACULTIES, new GetAllFaculties());
        ajaxRepository.put(AjaxCommandName.GET_ALL_SPECIALTIES, new GetAllSpecialties());
        ajaxRepository.put(AjaxCommandName.GET_ALL_USERS, new GetAllUsers());
        ajaxRepository.put(AjaxCommandName.GET_ALL_PLANS, new GetAllPlans());
        ajaxRepository.put(AjaxCommandName.GET_ALL_TYPES, new GetAllTypes());
        ajaxRepository.put(AjaxCommandName.GET_ALL_MATRICULANTS, new GetAllMatriculants());
        ajaxRepository.put(AjaxCommandName.GET_ALL_SUBJECTS, new GetAllSubjects());
        ajaxRepository.put(AjaxCommandName.GET_ALL_MATRICULANTS_BY_SPECIALTY, new GetAllMatriculantsBySpecialty());
        ajaxRepository.put(AjaxCommandName.GET_ALL_MATRICULANTS_BY_PROPERTY, new GetAllMatriculantsByProperty());
        ajaxRepository.put(AjaxCommandName.GET_ALL_SPECIALTY_HAS_SUBJECT, new GetAllSpecialtyHasSubject());
        ajaxRepository.put(AjaxCommandName.WRONG_METHOD, new by.epam.jwd.controller.command.impl.ajax.impl.WrongMethod());
    }

    public Command getCommand(String commandName) {
        CommandName commandEnum = null;
        Command command = null;

        try{
            commandEnum = CommandName.valueOf(commandName.toUpperCase());
            command = repository.get(commandEnum);
        }catch (IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.WRONG_METHOD);
        }

        return command;
    }
    
    public AjaxCommand getAjaxCommand(String name) {
        AjaxCommandName ajaxCommandName = null;
        AjaxCommand ajaxCommand = null;

        if (name != null) {
            ajaxCommandName = AjaxCommandName.valueOf(name.toUpperCase());
            ajaxCommand = ajaxRepository.get(ajaxCommandName);

            if (ajaxCommand == null) {
                ajaxCommand = ajaxRepository.get(AjaxCommandName.WRONG_METHOD);
            }
        } else {
            ajaxCommand = ajaxRepository.get(AjaxCommandName.WRONG_METHOD);
        }
        
        return ajaxCommand;
    }
    
    public static CommandProvider getInstance(){
        return instance;
    }
}
