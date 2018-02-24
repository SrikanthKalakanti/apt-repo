package com.apt.msa.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.apt.msa.business.CostOfProjectRow;
import com.apt.msa.business.CostOfProjectTable;

public class ExcelUtil {

	public static void createAptReport(CostOfProjectTable costOfProjectTable){

		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		/* CreationHelper helps us create instances for various things like DataFormat, 
       Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
		CreationHelper createHelper = workbook.getCreationHelper();

		/** Start of Assets Work sheet **/
		// 1 Create a Work Sheet for Assets
		Sheet sheet = workbook.createSheet("Assets");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeight((short) 16);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Creating header cells
		for(int i = 0; i < costOfProjectTable.columnHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(costOfProjectTable.columnHeaders[i]);
			//cell.setCellStyle(headerCellStyle);
		}

		// Create Cell Style for formatting Date
		//CellStyle dateCellStyle = workbook.createCellStyle();
		//dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		//"assetId", "assetName", "assetValue", "termLoanAmount", "promoterMargin", "clientId"

		//Create all rows and corresponding cells for each of the CostOfProjectRow data 
		int rowNum = 1;
		for(CostOfProjectRow coptRow: costOfProjectTable.listOfCostOfProjectRows) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(coptRow.getAssetId());

			row.createCell(1).setCellValue(coptRow.getName());

			row.createCell(2).setCellValue(coptRow.getValue());

			row.createCell(3).setCellValue(coptRow.getTermLoanAmount());

			row.createCell(4).setCellValue(coptRow.getPromoterMargin());
		}
		
		//Print TOtal cost row
		
		Row totalRow = sheet.createRow(rowNum++);
		totalRow.createCell(0).setCellValue((String)costOfProjectTable.totalsRow.get(0));

		totalRow.createCell(1).setCellValue((String)costOfProjectTable.totalsRow.get(1));

		totalRow.createCell(2).setCellValue((String)costOfProjectTable.totalsRow.get(2));

		totalRow.createCell(3).setCellValue((Double)costOfProjectTable.totalsRow.get(3));

		totalRow.createCell(4).setCellValue((Double)costOfProjectTable.totalsRow.get(4));
			
		
		// Resize all columns to fit the content size
		for(int i = 0; i < costOfProjectTable.columnHeaders.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		/** End of Assets Work sheet **/
		
		
		/** Start of Basic Input Work sheet **/
		/** End of Basic Input Work sheet **/
		
		/** Start of Basic Input Work sheet **/
		/** End of Basic Input Work sheet **/

		

		// Write the output to a file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("APT_Report.xlsx");
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

