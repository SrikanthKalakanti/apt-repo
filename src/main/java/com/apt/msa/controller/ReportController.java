package com.apt.msa.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.business.CostOfProjectTable;
import com.apt.msa.business.DepreciationTable;
import com.apt.msa.business.Expenses;
import com.apt.msa.business.Expenses.ExpensesNomenclature;
import com.apt.msa.business.ExpensesOutput;
import com.apt.msa.business.ExpensesOutputTwo;
import com.apt.msa.business.GrowthAndInflation;
import com.apt.msa.business.OperatingStatement;
import com.apt.msa.business.OtherConstants;
import com.apt.msa.business.RepaymentSchedule;
import com.apt.msa.entity.AssetInput;
import com.apt.msa.entity.BasicInput;
import com.apt.msa.entity.Client;
import com.apt.msa.entity.ClientGlobalInput;
import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.service.IBasicInputService;
import com.apt.msa.service.IClientService;
import com.apt.msa.service.IExpensesInputService;
import com.apt.msa.service.IGrowthInflationInputService;
import com.apt.msa.util.ExcelUtil;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/report")
@CrossOrigin(origins = { "http://localhost:9000" })
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private IClientService clientService;

	@Autowired
	private IBasicInputService basicInputService;

	@Autowired
	private IAssetInputService assetService;

	@Autowired
	private IExpensesInputService expensesService;

	@Autowired
	private IGrowthInflationInputService growthService;

	/**
	 * 1 Generate Report API
	 * 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response generateReport(final RequestEntity<ClientRequest> requestEntity) {

		logger.info("---- Start of generateReport API-------");
		try {

			ClientGlobalInput clientGlobalInput = new ClientGlobalInput();

			/**
			 * get client details
			 */

			Client clientDetails = clientService.findOne(requestEntity.getBody().getClientId());

			if (clientDetails != null) {
				clientGlobalInput.setClient(clientDetails);
			}

			System.out.println(requestEntity.getBody().getClientId());

			BasicInput basicInput = basicInputService.fetchByClientId(requestEntity.getBody().getClientId());

			if (basicInput != null) {

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				basicInput.setTermLoanDisbursement(df.format(basicInput.getTermLoanFirstDisbursementDate()));
				basicInput.setBusinessCommencement(df.format(basicInput.getBusinessCommencementDate()));

				logger.info("---- retrieved basic input successfully-------");
				clientGlobalInput.setBasic(basicInput);
				/**
				 * *Need to write business logic for basic input
				 * 
				 * 
				 */

				/**
				 * Get assets for client
				 */

				List<AssetInput> assetInputList = assetService.fetchByClientId(requestEntity.getBody().getClientId());

				if (null != assetInputList && assetInputList.size() > 0) {

					logger.info("---- getassetinputbyclient API success-------");
					clientGlobalInput.setAssetList(assetInputList);
					for (AssetInput asset : assetInputList) {
						logger.info("asset: " + asset);
					}
				}

				/**
				 * get expenses for client
				 */

				List<ExpensesInput> expensesInputList = expensesService
						.fetchByClientId(requestEntity.getBody().getClientId());

				if (null != expensesInputList && expensesInputList.size() > 0) {

					logger.info("---- getexpensesinputbyclient API success-------");
					clientGlobalInput.setExpensesList(expensesInputList);
					for (ExpensesInput expense : expensesInputList) {
						logger.info("expense: " + expense);
					}
				}

				/**
				 * get growth and inflation for client
				 */

				GrowthInflationInput growthInput = growthService.fetchByClientId(requestEntity.getBody().getClientId());

				if (growthInput != null) {

					logger.info("---- getgrowthinputbyclient API success-------");
					clientGlobalInput.setGrowthInflation(growthInput);
				}

				logger.info("......client global input returned successfully...." + clientGlobalInput
						+ "......asset list size...." + assetInputList.size());

				/**
				 * create a method in my logic
				 * 
				 */

				logger.info("......asset list size...." + assetInputList.size());

				/*
				 * Table 1 of the report The following is the first table to be
				 * printed in an Excel sheet and later converted into pdf
				 */

				CostOfProjectTable costOfProjectTable = new CostOfProjectTable(clientGlobalInput);
				// Print above CostOfProjectTable related to Asset in Excel

				/*
				 * The following line is to check the above object
				 */
				costOfProjectTable.printCostOfProjectTable(costOfProjectTable);

				/*
				 * Table 2 of the report The following is the first table to be
				 * printed in an Excel sheet and later converted into pdf
				 */
				DepreciationTable depreciationTable = new DepreciationTable();
				/*
				 * The object created below is a table in the final report
				 */

				depreciationTable.printDepreciationTables(clientGlobalInput);

				RepaymentSchedule repay = new RepaymentSchedule(clientGlobalInput);
				repay.printMonthWiseTable(clientGlobalInput);
				repay.printYearWiseTable(clientGlobalInput);

				/*
				 * Table I : Monthly Repayment Schedule to be printed in a sheet
				 * named "Monthly Repayment Schedule" Each of the following
				 * print statements is a
				 */
				// repay.repayment is to be printed in the sheet named "Monthly
				// Repayment Schedule";
				// repay.repaymentYearly is to be printed in the sheet named
				// "Yearwise Repayment Schedule";

				GrowthAndInflation growthAndInflation = new GrowthAndInflation(clientGlobalInput);
				System.out.println(clientGlobalInput.getClient().getLandphone() + " phone");
				System.out.println(clientGlobalInput.getBasic().getNumberOfDaysInAMonth() + " phone");
				for (int i = 0; i < clientGlobalInput.getExpensesList().size(); i++) {
					System.out
							.println(clientGlobalInput.getExpensesList().get(i).getAmountInINR() + " Amount Expenses");
					System.out.println(
							clientGlobalInput.getExpensesList().get(i).getNomenclature() + " Nomenclature Expenses");
				}
				OperatingStatement operatingStatement = new OperatingStatement(clientGlobalInput);
				operatingStatement.printProjections();

				// Expenses
				List<ExpensesInput> expensesList = clientGlobalInput.getExpensesList();
				
				
				List<Double> production = new ArrayList<Double>();
				System.out.println(production.size()+" size production array");
				// Double [] productionArray = {10000.00, 12000.00, 14000.00,
				// 16000.00, 18000.00, 20000.000}
				ExpensesOutput expensesOutput = new ExpensesOutput();
				expensesOutput.setAnnualProdutionInUnits(20000.00, clientGlobalInput);
				System.out.println(expensesOutput.getAnnualProdutionInUnits().size()+" size production array at line 225");
				production = expensesOutput.getAnnualProdutionInUnits();
				
				System.out.println(production.size()+" size production array at line 228");
				expensesOutput.setNomenclatureCostsForAllYears(expensesList,
				 production,operatingStatement.getFirstYearMonths(),operatingStatement.getYearsProjection(),growthAndInflation.getGrowthRate());
				// The following is another method for the above task not
				// working. should be debugged
				// expensesOutput.setNomenclatureCostsForAllYearsModified(clientGlobalInput,100000.00);
				List<List<Double>> nomenclatureCosts =
				 expensesOutput.getNomenclatureCostsForAllYears();
				System.out.println(nomenclatureCosts.size()+" size nomenclatureCosts array");
				// List<List<Double>> nomenclatureCosts =
				// expensesOutput.getNomenclatureCostsForAllYears();
				for (int i = 0; i < 12; i++) {
					System.out.print(expensesOutput.RowHeader.get(i) + ", ");
					System.out.println(nomenclatureCosts.get(i * 2).toString() + ", ");
					System.out.print(expensesOutput.RowHeader2.get(i) + ", ");
					System.out.println(nomenclatureCosts.get(i * 2 + 1).toString() + ", ");
				}
				System.out.print(expensesOutput.RowHeader2.get(12) + ", ");
				System.out.println(nomenclatureCosts.get(24).toString() + ", ");
				System.out.println("TNM");
				
				ExpensesOutputTwo expensesOutputTwo = new ExpensesOutputTwo();
				List<List<Double>> expensesClassifiedAndAnnualised = 
						expensesOutputTwo.getExpensesListClassified(expensesList, 
						OtherConstants.NUMBER_OF_DAYS_IN_A_MONTH, 
						operatingStatement.getFirstYearMonths(),
						operatingStatement.getYearsProjection(), 
						growthAndInflation.getInflationRate(), production);
				
				
				List<Double> variableCostArray = expensesOutputTwo.getTotalVariableCost(expensesClassifiedAndAnnualised);
				List<Double> fixedCostArray = expensesOutputTwo.getTotalFixedCost(expensesClassifiedAndAnnualised);
				System.out.println("F and V");
				List<List<Double>> fixed = expensesOutputTwo.classifyNomenclatureIntoVariableAndFixed(expensesClassifiedAndAnnualised);
				ExcelUtil.createAptReport(clientGlobalInput, repay, costOfProjectTable);
				
				
				return new Response(ResultStatusConstants.STATUS_OK, ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_BASIC_DETAILS, null, clientGlobalInput);
			} else {
				logger.info("---- generateReport API no records-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_BASICINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOBASICINPUT_DETAILS, null);
			}
		} catch (APTException aptException) {

			logger.debug("---- generateReport API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		} catch (Exception e) {
			logger.debug("---- generateReport API failed Exception -------" + e.getMessage());
			logger.info("---- generateReport API failed Exception -------" + e.getMessage());
			return new Response(ResultStatusConstants.STATUS_FAIL, ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR, null);
		}

	}

}
