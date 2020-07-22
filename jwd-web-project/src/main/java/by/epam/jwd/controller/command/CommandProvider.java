package by.epam.jwd.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.jwd.controller.command.impl.ajax.AjaxCommand;
import by.epam.jwd.controller.command.impl.ajax.AjaxCommandName;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllFaculties;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllMatriculants;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllPlans;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllSpecialties;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllTypes;
import by.epam.jwd.controller.command.impl.ajax.impl.GetAllUsers;
import by.epam.jwd.controller.command.impl.ajax.impl.ShowSpecialtiesByFaculty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.command.impl.front.CommandName;
import by.epam.jwd.controller.command.impl.front.impl.AddFaculty;
import by.epam.jwd.controller.command.impl.front.impl.AddMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.AddPlan;
import by.epam.jwd.controller.command.impl.front.impl.AddTypeStudy;
import by.epam.jwd.controller.command.impl.front.impl.BlockUser;
import by.epam.jwd.controller.command.impl.front.impl.ChangeLocale;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUser;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUserDetail;
import by.epam.jwd.controller.command.impl.front.impl.ChangeUserPassword;
import by.epam.jwd.controller.command.impl.front.impl.Registr;
import by.epam.jwd.controller.command.impl.front.impl.RemoveFaculty;
import by.epam.jwd.controller.command.impl.front.impl.RemoveMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.RemovePlan;
import by.epam.jwd.controller.command.impl.front.impl.RemoveType;
import by.epam.jwd.controller.command.impl.front.impl.ShowAdminPanel;
import by.epam.jwd.controller.command.impl.front.impl.ShowAllSpecialties;
import by.epam.jwd.controller.command.impl.front.impl.ShowMatriculant;
import by.epam.jwd.controller.command.impl.front.impl.ShowSpecialty;
import by.epam.jwd.controller.command.impl.front.impl.ShowTableSpecialties;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateFaculty;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdatePlan;
import by.epam.jwd.controller.command.impl.front.impl.ShowUpdateType;
import by.epam.jwd.controller.command.impl.front.impl.ShowUserData;
import by.epam.jwd.controller.command.impl.front.impl.ShowUserDetail;
import by.epam.jwd.controller.command.impl.front.impl.SignIn;
import by.epam.jwd.controller.command.impl.front.impl.SignOut;
import by.epam.jwd.controller.command.impl.front.impl.UpdateFaculty;
import by.epam.jwd.controller.command.impl.front.impl.UpdatePlan;
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
        repository.put(CommandName.SHOW_TABLE_SPECIALTIES, new ShowTableSpecialties());
        repository.put(CommandName.SHOW_SPECIALTY, new ShowSpecialty());
        repository.put(CommandName.SHOW_MATRICULANT, new ShowMatriculant()); 
        repository.put(CommandName.ADD_MATRICULANT, new AddMatriculant()); 
        repository.put(CommandName.ADD_FACULTY, new AddFaculty()); 
        repository.put(CommandName.ADD_TYPE_STUDY, new AddTypeStudy()); 
        repository.put(CommandName.ADD_PLAN, new AddPlan()); 
        repository.put(CommandName.REMOVE_MATRICULANT, new RemoveMatriculant()); 
        repository.put(CommandName.BLOCK_USER, new BlockUser());
        repository.put(CommandName.CHANGE_USER, new ChangeUser());
        repository.put(CommandName.SHOW_UPDATE_FACULTY, new ShowUpdateFaculty());
        repository.put(CommandName.SHOW_UPDATE_TYPE, new ShowUpdateType());
        repository.put(CommandName.SHOW_UPDATE_PLAN, new ShowUpdatePlan());
        repository.put(CommandName.UPDATE_FACULTY, new UpdateFaculty());
        repository.put(CommandName.UPDATE_TYPE, new UpdateType());
        repository.put(CommandName.UPDATE_PLAN, new UpdatePlan());
        repository.put(CommandName.DELETE_FACULTY, new RemoveFaculty());
        repository.put(CommandName.DELETE_TYPE, new RemoveType());
        repository.put(CommandName.DELETE_PLAN, new RemovePlan());
        repository.put(CommandName.WRONG_METHOD, new WrongMethod());
        
        
        ajaxRepository.put(AjaxCommandName.GET_SPECIALTIES_BY_FACULTY, new ShowSpecialtiesByFaculty());
        ajaxRepository.put(AjaxCommandName.GET_ALL_FACULTIES, new GetAllFaculties());
        ajaxRepository.put(AjaxCommandName.GET_ALL_SPECIALTIES, new GetAllSpecialties());
        ajaxRepository.put(AjaxCommandName.GET_ALL_USERS, new GetAllUsers());
        ajaxRepository.put(AjaxCommandName.GET_ALL_PLANS, new GetAllPlans());
        ajaxRepository.put(AjaxCommandName.GET_ALL_TYPES, new GetAllTypes());
        ajaxRepository.put(AjaxCommandName.GET_ALL_MATRICULANTS, new GetAllMatriculants());
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
