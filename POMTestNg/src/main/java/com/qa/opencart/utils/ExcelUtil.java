package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.opencart.constants.AppConstants;

public class ExcelUtil {

	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getTestData(String sheetname) {
		Object data[][] = null;
		try {
			FileInputStream ip = new FileInputStream(AppConstants.TEST_DATA_FILE_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetname.trim());

			int LastRowNumber = sheet.getLastRowNum();
			int LastColNumber = sheet.getRow(0).getLastCellNum();
			System.out.println("Rows are"+LastRowNumber+"cols are "+LastColNumber);
			data = new Object[LastRowNumber][LastColNumber];

			for (int i = 0; i <LastRowNumber; i++) {
				for (int j = 0; j <LastColNumber; j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (InvalidFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return data;
	}

}
