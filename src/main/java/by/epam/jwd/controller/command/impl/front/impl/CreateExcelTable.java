package by.epam.jwd.controller.command.impl.front.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import by.epam.jwd.bean.Specialty;
import by.epam.jwd.controller.command.impl.front.Command;
import by.epam.jwd.controller.parameter.FormParameter;
import by.epam.jwd.controller.parameter.JSPPageName;
import by.epam.jwd.controller.parameter.ValidateParameter;
import by.epam.jwd.service.SpecialtyService;
import by.epam.jwd.service.exception.ServiceException;
import by.epam.jwd.service.factory.ServiceFactory;

public class CreateExcelTable implements Command {

	private static Logger logger = LogManager.getLogger(CreateExcelTable.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		SpecialtyService serviceImpl = serviceFactory.getSpecialtyService();
		
		String[] matriculants = request.getParameterValues(FormParameter.MATRICULANT_NAME);
		int idSpecialty = Integer.parseInt(request.getParameter(FormParameter.ID_SPECIALTY));
		
		Specialty specialty = null;
		
		try {
			specialty = serviceImpl.findSpecialtyById(idSpecialty);
			
			if (specialty != null) {
				String fileName = specialty.getSpecialtyName().concat(".xls");
				
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	            HSSFWorkbook workbook = createExcel(matriculants);
	            workbook.write(response.getOutputStream());
			} else {
				request.setAttribute(ValidateParameter.EMPTY_SPECIALTY, ValidateParameter.EMPTY_SPECIALTY);
			}
        } catch (ServiceException e) {        
        	logger.log(Level.ERROR, e);
            String page = JSPPageName.ERROR_PAGE;
            forwardToPage(request, response, page);
        } 
	}
	
	private HSSFWorkbook createExcel(String[] matriculants) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("Matriculants sheet");
          
        for (int i = 0; i < matriculants.length; i++) {
        	 HSSFRow row1 = worksheet.createRow(i);
        	 
             HSSFCell cellA1 = row1.createCell(0);
             cellA1.setCellValue(matriculants[i]);
             HSSFCellStyle cellStyle = workbook.createCellStyle();
             cellA1.setCellStyle(cellStyle);
		}
        
        return workbook;
    }
}
