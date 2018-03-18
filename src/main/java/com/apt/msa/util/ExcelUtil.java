package com.apt.msa.util;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.CreationHelper;

import org.apache.poi.ss.usermodel.Font;

import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.apt.msa.business.CostOfProjectRow;

import com.apt.msa.business.CostOfProjectTable;
import com.apt.msa.business.DepreciationTable;
import com.apt.msa.business.ExpensesOutput;
import com.apt.msa.business.ExpensesOutputTwo;
import com.apt.msa.business.GrowthAndInflation;
import com.apt.msa.business.OperatingStatement;
import com.apt.msa.business.OtherConstants;
import com.apt.msa.business.RepaymentSchedule;
import com.apt.msa.entity.ClientGlobalInput;

public class ExcelUtil {

	public static void createAptReport(ClientGlobalInput clientGlobalInput, RepaymentSchedule repaymentSchedule,
			CostOfProjectTable costOfProjectTable) {

		// Create a Workbook

		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for
												// generating `.xls` file

		/*
		 * CreationHelper helps us create instances for various things like
		 * DataFormat,
		 * 
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent
		 * way
		 */

		CreationHelper createHelper = workbook.getCreationHelper();

		/** Start of Assets Work sheet **/

		// 1 Create a Work Sheet for Assets

		Sheet assets = workbook.createSheet("CostOfProject");
		
		// Create a Font for styling header cells

		Font headerFont = workbook.createFont();

		headerFont.setBold(true);

		headerFont.setFontHeight((short) 16);

		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font

		CellStyle headerCellStyle = workbook.createCellStyle();
		CellStyle colorCell = workbook.createCellStyle();

		headerCellStyle.setFont(headerFont);

		// Create a Row

		Row title = assets.createRow(0);
		Cell titleCell = title.createCell(0);
		titleCell.setCellValue(clientGlobalInput.getBasic().getNameOfTheBusiness());
		assets.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		Row headerRow = assets.createRow(1);
		
		// Creating header cells

		for (int i = 0; i < costOfProjectTable.columnHeaders.length - 1; i++) {

			Cell cell = headerRow.createCell(i);

			cell.setCellValue(costOfProjectTable.columnHeaders[i]);

			// cell.setCellStyle(headerCellStyle);

		}

		// Create Cell Style for formatting Date

		// CellStyle dateCellStyle = workbook.createCellStyle();

		// dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// "assetId", "assetName", "assetValue", "termLoanAmount",
		// "promoterMargin", "clientId"

		// Create all rows and corresponding cells for each of the
		// CostOfProjectRow data

		int rowNum = 2;

		for (CostOfProjectRow coptRow : costOfProjectTable.listOfCostOfProjectRows) {

			Row row = assets.createRow(rowNum++);

			row.createCell(0).setCellValue(costOfProjectTable.listOfCostOfProjectRows.indexOf(coptRow));

			row.createCell(1).setCellValue(coptRow.getName());

			row.createCell(2).setCellValue(coptRow.getValue());

			row.createCell(3).setCellValue(coptRow.getTermLoanAmount());

			row.createCell(4).setCellValue(coptRow.getMarginAmount());

			row.createCell(5).setCellValue(coptRow.getAssetId());

		}

		// Print TOtal cost row

		Row totalRow = assets.createRow(rowNum++);

		totalRow.createCell(0).setCellValue((String) costOfProjectTable.totalsRow.get(0));

		totalRow.createCell(1).setCellValue((String) costOfProjectTable.totalsRow.get(1));

		totalRow.createCell(2).setCellValue((Double) costOfProjectTable.totalsRow.get(2));

		totalRow.createCell(3).setCellValue((Double) costOfProjectTable.totalsRow.get(3));

		totalRow.createCell(4).setCellValue((Double) costOfProjectTable.totalsRow.get(4));

		// Resize all columns to fit the content size

		for (int i = 0; i < costOfProjectTable.columnHeaders.length; i++) {

			assets.autoSizeColumn(i);

		}

		Sheet repay = workbook.createSheet("Repay");
		title = repay.createRow(0);
		titleCell = title.createCell(0);
		titleCell.setCellValue(clientGlobalInput.getBasic().getNameOfTheBusiness());
		repay.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
		// Create a Font for styling header cells

		// Create a Row
		rowNum = 3;
		headerRow = repay.createRow(1);
		repay.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
		Cell cell = headerRow.createCell(0);
		cell.setCellValue("Term Loan Repayment Schedule");

		Row headerRow2 = repay.createRow(2);
		cell = headerRow2.createCell(0);
		cell.setCellValue("SL No");
		cell = headerRow2.createCell(1);
		cell.setCellValue("Year");
		cell = headerRow2.createCell(2);
		cell.setCellValue("Month");
		cell = headerRow2.createCell(3);
		cell.setCellValue("Op Bal");
		cell = headerRow2.createCell(4);
		cell.setCellValue("Principal Repayment");
		cell = headerRow2.createCell(5);
		cell.setCellValue("Interest");
		cell = headerRow2.createCell(6);
		cell.setCellValue("Cl Bal");
		Row repayRow = repay.createRow(rowNum);
		// Creating header cells

		for (int i = 0; i < repaymentSchedule.getSerialNumber().size(); i++) {
			repayRow = repay.createRow(rowNum);
			cell = repayRow.createCell(0);
			cell.setCellValue(repaymentSchedule.getSerialNumber().get(i));
			cell = repayRow.createCell(1);
			cell.setCellValue(repaymentSchedule.getYear().get(i));
			cell = repayRow.createCell(2);
			cell.setCellValue(repaymentSchedule.getMonth().get(i));
			cell = repayRow.createCell(3);
			cell.setCellValue(repaymentSchedule.getOpeningBalance().get(i));
			cell = repayRow.createCell(4);
			cell.setCellValue(repaymentSchedule.getPrincipalRepayment().get(i));
			cell = repayRow.createCell(5);
			cell.setCellValue(repaymentSchedule.getInterest().get(i));
			cell = repayRow.createCell(6);
			cell.setCellValue(repaymentSchedule.getClosingBalance().get(i));
			rowNum++;
			// cell.setCellStyle(headerCellStyle);
		}
		for (int i = 0; i < 10; i++) {

			repay.autoSizeColumn(i);

		}

		Sheet repayYearly = workbook.createSheet("RepayYearly");
		title = repayYearly.createRow(0);
		titleCell = title.createCell(0);
		titleCell.setCellValue(clientGlobalInput.getBasic().getNameOfTheBusiness());
		repayYearly.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

		
		repayYearly.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
		cell = headerRow.createCell(1);
		cell.setCellValue("Term Loan Repayment Schedule Consolidation");

		headerRow2 = repayYearly.createRow(2);
		cell = headerRow2.createCell(0);
		cell.setCellValue("SL No");
		cell = headerRow2.createCell(1);
		cell.setCellValue("Year");
		cell = headerRow2.createCell(2);
		cell.setCellValue("Op Bal");
		cell = headerRow2.createCell(3);
		cell.setCellValue("Principal Repayment");
		cell = headerRow2.createCell(4);
		cell.setCellValue("Interest");
		cell = headerRow2.createCell(5);
		cell.setCellValue("Cl Bal");

		// Create a Row
		rowNum = 2;
		headerRow = repayYearly.createRow(0);
		Row repayYearlyRow = repayYearly.createRow(rowNum);
		// Creating header cells
		cell = headerRow.createCell(0);

		cell.setCellValue("Term Loan Repayment Schedule - Yearwise");
		for (int i = 0; i < repaymentSchedule.getSerialNumberYearly().size(); i++) {
			repayYearlyRow = repayYearly.createRow(rowNum);
			cell = repayYearlyRow.createCell(0);
			cell.setCellValue(repaymentSchedule.getSerialNumberYearly().get(i));
			cell = repayYearlyRow.createCell(1);
			cell.setCellValue(repaymentSchedule.getYearYearly().get(i));
			cell = repayYearlyRow.createCell(2);
			cell.setCellValue(repaymentSchedule.getOpeningBalanceYearly().get(i));
			cell = repayYearlyRow.createCell(3);
			cell.setCellValue(repaymentSchedule.getPrincipalRepaymentYearly().get(i));
			cell = repayYearlyRow.createCell(4);
			cell.setCellValue(repaymentSchedule.getInterestYearly().get(i));
			cell = repayYearlyRow.createCell(5);
			cell.setCellValue(repaymentSchedule.getClosingBalanceYearly().get(i));
			rowNum++;
			// cell.setCellStyle(headerCellStyle);
		}

		for (int i = 0; i < 10; i++) {

			repayYearly.autoSizeColumn(i);

		}

		List<DepreciationTable> depreciationTables = DepreciationTable.setAllTables(clientGlobalInput);
	

		Sheet depreciation = workbook.createSheet("DepreciationTable");

		title = depreciation.createRow(0);
		titleCell = title.createCell(0);
		titleCell.setCellValue(clientGlobalInput.getBasic().getNameOfTheBusiness());
		depreciation.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

		// Create a Row
		rowNum = 2;
		headerRow = depreciation.createRow(1);
		Row tableHeader = depreciation.createRow(rowNum);
		Row years = depreciation.createRow(rowNum + 1);
		Row grossBlock = depreciation.createRow(rowNum + 2);
		Row depre = depreciation.createRow(rowNum + 3);
		Row accummulateDepreciation = depreciation.createRow(rowNum + 4);
		Row netBlock = depreciation.createRow(rowNum + 5);
		Row endOfATable = depreciation.createRow(rowNum + 6);
		// Creating header cells
		depreciation.addMergedRegion(new CellRangeAddress(1, 1, 0, 6));
		cell = headerRow.createCell(0);

		cell.setCellValue("Depreciation Tables");
		int yearsProjection = depreciationTables.get(0).getYears().size();
		for (DepreciationTable depreciationTable : depreciationTables) {
			tableHeader = depreciation.createRow(rowNum);
			years = depreciation.createRow(rowNum + 1);
			grossBlock = depreciation.createRow(rowNum + 2);
			depre = depreciation.createRow(rowNum + 3);
			accummulateDepreciation = depreciation.createRow(rowNum + 4);
			netBlock = depreciation.createRow(rowNum + 5);
			endOfATable = depreciation.createRow(rowNum + 6);

			cell = tableHeader.createCell(0);
			cell.setCellValue(depreciationTable.getAssetInput().getName() + " (Name)");
			cell = tableHeader.createCell(1);
			cell.setCellValue(depreciationTable.getAssetInput().getValue() + " (Value)");
			cell = tableHeader.createCell(2);
			cell.setCellValue(depreciationTable.getAssetInput().getDepreciationRate() + " (Rate of Depreciation)");

			cell = years.createCell(0);
			cell.setCellValue("Year");
			cell = grossBlock.createCell(0);
			cell.setCellValue("Gross Block");
			cell = depre.createCell(0);
			cell.setCellValue("Depreciation");
			cell = accummulateDepreciation.createCell(0);
			cell.setCellValue("Accummulated Depreciation");
			cell = netBlock.createCell(0);
			cell.setCellValue("Written Down Value");

			for (int i = 1; i < yearsProjection; i++) {
				cell = years.createCell(i);
				cell.setCellValue(depreciationTable.getYears().get(i));
				cell = grossBlock.createCell(i);
				cell.setCellValue(depreciationTable.getGrossBlock().get(i));
				cell = depre.createCell(i);
				cell.setCellValue(depreciationTable.getDepreciation().get(i));
				cell = accummulateDepreciation.createCell(i);
				cell.setCellValue(depreciationTable.getAccummulatedDepreciation().get(i));
				cell = netBlock.createCell(i);
				cell.setCellValue(depreciationTable.getWrittenDownValue().get(i));
			}
			depreciation.addMergedRegion(new CellRangeAddress(rowNum + 6, rowNum + 6, 0, 5));
			cell = endOfATable.createCell(0);
			cell.setCellValue(depreciationTable.getAssetInput().getName()
					+ "----End of the table----------------------------------------");
			rowNum = rowNum + 7;

		}

		for (int i = 0; i < yearsProjection + 5; i++) {

			depreciation.autoSizeColumn(i);

		}

		DepreciationTable depreciationTable = new DepreciationTable();
		depreciationTable = depreciationTable.consolidateAllTables(clientGlobalInput);

		Sheet depreciationConsolidtion = workbook.createSheet("DepConsolidation");
		// Create a Row
		rowNum = 1;
		headerRow = depreciationConsolidtion.createRow(0);
		tableHeader = depreciationConsolidtion.createRow(1);
		years = depreciationConsolidtion.createRow(2);
		grossBlock = depreciationConsolidtion.createRow(3);
		depre = depreciationConsolidtion.createRow(4);
		accummulateDepreciation = depreciationConsolidtion.createRow(5);
		netBlock = depreciationConsolidtion.createRow(6);
		endOfATable = depreciationConsolidtion.createRow(7);
		// Creating header cells
		depreciationConsolidtion.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		depreciationConsolidtion.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		cell = headerRow.createCell(0);

		cell.setCellValue("Depreciation Tables Consolidation");

		cell = tableHeader.createCell(0);
		cell.setCellValue("Consolidation Table");

		cell = years.createCell(0);
		cell.setCellValue("Year");
		cell = grossBlock.createCell(0);
		cell.setCellValue("Gross Block");
		cell = depre.createCell(0);
		cell.setCellValue("Depreciation");
		cell = accummulateDepreciation.createCell(0);
		cell.setCellValue("Accummulated Depreciation");
		cell = netBlock.createCell(0);
		cell.setCellValue("Written Down Value");

		for (int i = 1; i < yearsProjection; i++) {
			cell = years.createCell(i);
			cell.setCellValue(depreciationTable.getYears().get(i));
			cell = grossBlock.createCell(i);
			cell.setCellValue(depreciationTable.getGrossBlock().get(i));
			cell = depre.createCell(i);
			cell.setCellValue(depreciationTable.getDepreciation().get(i));
			cell = accummulateDepreciation.createCell(i);
			cell.setCellValue(depreciationTable.getAccummulatedDepreciation().get(i));
			cell = netBlock.createCell(i);
			cell.setCellValue(depreciationTable.getWrittenDownValue().get(i));
		}
		depreciationConsolidtion.addMergedRegion(new CellRangeAddress(rowNum + 6, rowNum + 6, 0, 5));
		cell = endOfATable.createCell(0);
		cell.setCellValue(depreciationTable.getAssetInput().getName()
				+ "----End of the table----------------------------------------");
		for (int i = 0; i < yearsProjection + 5; i++) {

			depreciationConsolidtion.autoSizeColumn(i);

		}

		OperatingStatement opStatement = new OperatingStatement();
		opStatement = opStatement.recurseProjections(clientGlobalInput);
		Sheet operatingStatement = workbook.createSheet("OperatingStatement");
		// Create a Row
		rowNum = 1;
		headerRow = operatingStatement.createRow(0);
		tableHeader = operatingStatement.createRow(1);
		Row contribution = operatingStatement.createRow(2);
		Row fixedExpenses = operatingStatement.createRow(3);
		Row depreciationInOpStmnt = operatingStatement.createRow(4);
		Row interestOnCapital = operatingStatement.createRow(5);
		Row interestOnCC = operatingStatement.createRow(6);
		Row interestOnTL = operatingStatement.createRow(7);
		Row remuneration = operatingStatement.createRow(8);
		Row pbt = operatingStatement.createRow(9);
		Row taxes = operatingStatement.createRow(10);
		Row pat = operatingStatement.createRow(11);
		Row cashGeneration = operatingStatement.createRow(12);
		Row debtService = operatingStatement.createRow(13);
		Row dscr = operatingStatement.createRow(14);
		Row GrossDscr = operatingStatement.createRow(15);
		Row GrossDscrDeviation = operatingStatement.createRow(16);
		cell = contribution.createCell(0);
		cell.setCellValue("Contribution");
		cell = fixedExpenses.createCell(0);
		cell.setCellValue("Fixed Expenses");
		cell = depreciationInOpStmnt.createCell(0);
		cell.setCellValue("Depreciation");
		cell = interestOnCapital.createCell(0);
		cell.setCellValue("Interest On Capital");
		cell = interestOnCC.createCell(0);
		cell.setCellValue("Interest On CC");
		cell = interestOnTL.createCell(0);
		cell.setCellValue("Interest On TL");
		cell = remuneration.createCell(0);
		cell.setCellValue("Remuneration");
		cell = pbt.createCell(0);
		cell.setCellValue("Profit Before Taxes");
		cell = taxes.createCell(0);
		cell.setCellValue("Taxes");
		cell = pat.createCell(0);
		cell.setCellValue("Profit After Taxes");
		cell = cashGeneration.createCell(0);
		cell.setCellValue("Cash Generation");
		cell = debtService.createCell(0);
		cell.setCellValue("Debt Service");
		cell = dscr.createCell(0);
		cell.setCellValue("Debt Service Coverage Ratio");
		cell = GrossDscr.createCell(0);
		cell.setCellValue("Gross Average DSCR");
		cell = GrossDscrDeviation.createCell(0);
		cell.setCellValue("Gross DSCR Deviation");

		// Creating header cells
		operatingStatement.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		cell = headerRow.createCell(0);

		cell.setCellValue("Operating Statement");

		cell = tableHeader.createCell(0);
		cell.setCellValue("Particulars");

		printAnIntegerListInARow(repaymentSchedule.getYearYearly(), tableHeader, 1);
		printAListInARow(opStatement.getContribution(), contribution, 1);
		printAListInARow(opStatement.getFixedExpenses(), fixedExpenses, 1);
		printAListInARow(opStatement.getDepreciation(), depreciationInOpStmnt, 1);
		printAListInARow(opStatement.getInterestOnPartnersCapital(), interestOnCapital, 1);
		printAListInARow(opStatement.getInterestOnCashCredit(), interestOnCC, 1);
		printAListInARow(opStatement.getInterestOnTermLoan(), interestOnTL, 1);
		printAListInARow(opStatement.getPartnersRemuneration(), remuneration, 1);
		printAListInARow(opStatement.getProfitBeforeTaxes(), pbt, 1);
		printAListInARow(opStatement.getTaxes(), taxes, 1);
		printAListInARow(opStatement.getProfitAfterTaxes(), pat, 1);
		printAListInARow(opStatement.getCashGeneration(), cashGeneration, 1);
		printAListInARow(opStatement.getDebtService(), debtService, 1);
		printAListInARow(opStatement.getDebtServiceCoverageRatio(), dscr, 1);
		cell = GrossDscr.createCell(1);
		opStatement.setGrossAverageDSCR(opStatement);
		cell.setCellValue(opStatement.getGrossAverageDSCR());

		for (int i = 0; i < yearsProjection + 5; i++) {

			operatingStatement.autoSizeColumn(i);

		}

		// ExpensesOutput expensesOutput = new ExpensesOutput ();
		// expensesOutput.setNomenclatureCostsForAllYearsModified(clientGlobalInput,
		// 50000.00);

		ExpensesOutput expensesOutput = new ExpensesOutput();
		expensesOutput.setAnnualProdutionInUnits(20000.00, clientGlobalInput);
		List<Double> production = new ArrayList<Double>();
		production = expensesOutput.getAnnualProdutionInUnits();
		GrowthAndInflation growthAndInflation = new GrowthAndInflation(clientGlobalInput);
		expensesOutput.setNomenclatureCostsForAllYears(clientGlobalInput.getExpensesList(), production,
				opStatement.getFirstYearMonths(), opStatement.getYearsProjection(),
				growthAndInflation.getInflationRate());
		// The following is another method for the above task not
		// working. should be debugged
		// expensesOutput.setNomenclatureCostsForAllYearsModified(clientGlobalInput,100000.00);
		List<List<Double>> nomenclatureCosts = expensesOutput.getNomenclatureCostsForAllYears();

		Sheet expenses = workbook.createSheet("Expenses");
		headerRow = expenses.createRow(0);
		tableHeader = expenses.createRow(1);
		cell = headerRow.createCell(0);
		cell.setCellValue("Table showing details of expenses");
		cell = tableHeader.createCell(0);
		cell.setCellValue("Particulars");
		for (int i = 1; i < yearsProjection + 1; i++) {
			cell = tableHeader.createCell(i);
			cell.setCellValue("Year: " + repaymentSchedule.getYearYearly().get(i - 1));
		}

		String[] rowHeader1 = { "DirectLabour", "ExpensesAmortised", "FactoryWages", "ImportedRawMaterials",
				"ImportedStoresAndSpares", "IndigenousRawMaterials", "IndigenousStoresAndSpares", "NonOpExpense",
				"NonOpIncome", "OtherManufacturingExpenses", "PowerAndFuel", "AdminAndSellingExpenses" };
		String[] rowHeader2 = { "UserInput", "Annualised", "Consolidation" };
		String[] rowHeader3 = { "Unit", "Day", "Month", "Year" };

		ExpensesOutputTwo expensesOutputTwo = new ExpensesOutputTwo();
		List<List<Double>> expensesList = expensesOutputTwo.getExpensesListClassified(
				clientGlobalInput.getExpensesList(), OtherConstants.NUMBER_OF_DAYS_IN_A_MONTH,
				opStatement.getFirstYearMonths(), opStatement.getYearsProjection(),
				growthAndInflation.getInflationRate(), production);

		rowNum = 2;
		Row row = expenses.createRow(rowNum);
		for (int header1 = 0; header1 < rowHeader1.length; header1++) {
			for (int header2 = 0; header2 < rowHeader2.length - 1; header2++) {
				for (int header3 = 0; header3 < rowHeader3.length; header3++) {
					row = expenses.createRow(rowNum);
					cell = row.createCell(0);
					cell.setCellValue(rowHeader1[header1] + " " + rowHeader2[header2] + " " + rowHeader3[header3]);
					for (int i = 1; i < yearsProjection + 1; i++) {
						cell = row.createCell(i);
						cell.setCellValue(expensesList.get(rowNum - 2).get(i - 1));
					}
					rowNum++;
				}
			}
			// rowNum++;
			row = expenses.createRow(rowNum);
			cell = row.createCell(0);
			cell.setCellValue(rowHeader1[header1] + " " + rowHeader2[2]);
			for (int i = 1; i < yearsProjection + 1; i++) {
				cell = row.createCell(i);
				cell.setCellValue(expensesList.get(rowNum - 2).get(i - 1));
			}
			rowNum++;
		}

		for (int i = 0; i < yearsProjection + 5; i++) {

			expenses.autoSizeColumn(i);

		}

		Sheet expensesSummary = workbook.createSheet("ExpensesSummary");
		headerRow = expensesSummary.createRow(0);
		tableHeader = expensesSummary.createRow(1);
		cell = headerRow.createCell(0);
		cell.setCellValue("Table showing summary of expenses");
		cell = tableHeader.createCell(0);
		cell.setCellValue("Particulars");
		for (int i = 1; i < yearsProjection + 1; i++) {
			cell = tableHeader.createCell(i);
			cell.setCellValue("Year: " + repaymentSchedule.getYearYearly().get(i - 1));
		}

		String[] rowHeader4 = { "Variable", "Fixed", "Total" };
		System.out.print("Excell Util");
		List<List<Double>> fixed = expensesOutputTwo.classifyNomenclatureIntoVariableAndFixed(expensesList);
		System.out.println(fixed.get(0).size() + " fixed");
		rowNum = 2;

		row = expensesSummary.createRow(rowNum);
		for (int header1 = 0; header1 < rowHeader1.length; header1++) {
			for (int header4 = 0; header4 < rowHeader4.length; header4++) {
				row = expensesSummary.createRow(rowNum);
				cell = row.createCell(0);
				cell.setCellValue(rowHeader1[header1] + " " + rowHeader4[header4]);
				// Line 819 to 822 need to be debugged; index error
				for (int i = 1; i < yearsProjection + 1; i++) {
					cell = row.createCell(i);
					cell.setCellValue(fixed.get(rowNum - 2).get(i - 1));
				}
				rowNum++;
			}
		}
		List<List<Double>> summary = expensesOutputTwo.summariseFixedAndVariable(fixed);
		for (int j = 0; j < 3; j++) {
			row = expensesSummary.createRow(rowNum);
			cell = row.createCell(0);
			cell.setCellValue(rowHeader4[j] + " Total");
			for (int i = 1; i < yearsProjection + 1; i++) {
				cell = row.createCell(i);
				cell.setCellValue(summary.get(j).get(i - 1));
			}
			rowNum++;
		}
		
		for (int i = 0; i < yearsProjection + 5; i++) {

			expensesSummary.autoSizeColumn(i);

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

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public static void printAListInARow(List<Double> list, Row row, int cellValue) {
		Cell cell = row.createCell(cellValue);
		for (Double aDouble : list) {
			cell = row.createCell(cellValue);
			cell.setCellValue(aDouble);
			cellValue++;
		}
	}

	public static void printEveryFifthElementFromAnArray(List<Double> list, Row row, int cellValue) {
		Cell cell = row.createCell(cellValue);
		for (Double aDouble : list) {
			int index = list.indexOf(aDouble);
			index++;
			if (index % 5 == 0) {
				cell.setCellValue(aDouble);
				cellValue++;
				cell = row.createCell(cellValue);
			}
		}
	}

	public static void printAnIntegerListInARow(List<Integer> list, Row row, int cellValue) {
		Cell cell = row.createCell(cellValue);
		for (Integer aDouble : list) {
			cell = row.createCell(cellValue);
			cell.setCellValue(aDouble);
			cellValue++;
		}
	}
}