package com.qa.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	
	public static String SHEET_PATH = ".\\src\\main\\java\\com\\qa\\gorest\\testdata\\CreateUser_API.xlsx";
	public static Workbook workbook;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		Object [][] data = null;
		
		try {
			FileInputStream ip = new FileInputStream(SHEET_PATH);
			workbook = WorkbookFactory.create(ip);
			sheet = workbook.getSheet(sheetName);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum();i++) {
			
			for(int j=0; j<sheet.getRow(0).getLastCellNum();j++) {
				
				data [i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		
		return data;
		
	}
}
