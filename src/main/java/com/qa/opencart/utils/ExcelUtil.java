package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static final String TEST_DATA_SHEET_PATH="./src/test/resources/testData/OpenCartTestData.xlsx";

	private static Workbook workBook;
	private static Sheet sheet;
	public static Object[][] getTestData(String sheetName)
	{
		System.out.println("reading data from excel sheet : "+ sheetName);
		
		Object data[][]=null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			workBook=WorkbookFactory.create(ip);
			sheet=workBook.getSheet(sheetName);
			
			// data = new Object[3][6]; Here 3 Row and 6 Column
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++)
			{
				for (int j=0;j<sheet.getRow(0).getLastCellNum();j++)
				{
					data[i][j]= sheet.getRow(i+1).getCell(j).toString();
					// getRow(i+1) because in first row headers will be there and 
					// test data will there from second row 
				}
				
			}
					
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}
}
