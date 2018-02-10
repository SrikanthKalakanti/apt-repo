package com.apt.msa.business;

import java.util.ArrayList;
import java.util.List;

public class Conversions {
	
	public static double [][] transposeATwoDArray(double [][] anArray){
		double [][] transposedArray = new double [anArray[0].length][anArray.length];
		for (int j = 0; j < anArray[0].length; j++){
			for(int i = 0; i< anArray.length; i++){
				transposedArray [j][i] = anArray[i][j]; 
			}
			
		}
	
		return transposedArray;
	}
	
	public static ArrayList<String> convertDoubleArrayToStringArray(double [] aDoubleArray, String rowHeader){
		ArrayList<String> newStringArrayList = new ArrayList<String>();
			newStringArrayList.add(rowHeader);
			for(int i = 0; i< aDoubleArray.length;i++){
				newStringArrayList.add(Double.toString(aDoubleArray[i]));
			}			
		return newStringArrayList;
	}
	
	public static List<List<String>> convertDoubleMatrixToStringMatrix(double[][] aDoubleMatrix, 
			String[] rowHeaders){
		int numberOfRowsInTheMatrix = rowHeaders.length;
		List<List<String>> newStringMatrix = new ArrayList<List<String>>();
		ArrayList<String> newStringArrayList = new ArrayList<String>();
		for (int j = 0; j< numberOfRowsInTheMatrix;j++){
			newStringArrayList = new ArrayList<String>(); 
			newStringArrayList.add(rowHeaders [j]);
			for(int i = 0; i< aDoubleMatrix.length;i++){
				newStringArrayList.add(Double.toString(aDoubleMatrix[i][j]));
			}			
			newStringMatrix.add(newStringArrayList);
		}
		return newStringMatrix;
	}	
	
	public static ArrayList<ArrayList<String>> convertDoubleMatrixToStringMatrixWithoutRowHeaders(double[][] aDoubleMatrix){
		ArrayList<ArrayList<String>> newStringMatrix = new ArrayList<ArrayList<String>>();
		ArrayList<String> newStringArrayList = new ArrayList<String>();
		for (int j = 0; j< aDoubleMatrix[0].length;j++){
			newStringArrayList = new ArrayList<String>();
			for(int i = 0; i< aDoubleMatrix.length;i++){
				newStringArrayList.add(Double.toString(aDoubleMatrix[i][j]));
			}			
			newStringMatrix.add(newStringArrayList);
		}
		return newStringMatrix;
	}

	public static double [] extractAColumn(double [][] aDoubleMatrix, int columnNumber){
		double [] extractedRow = new double [aDoubleMatrix[columnNumber-1].length];
		for (int i = 0; i<aDoubleMatrix[columnNumber-1].length; i++){
			extractedRow[i] = aDoubleMatrix[columnNumber-1][i];
		}
		return extractedRow;
	}

	public static double [] extractARow(double [][] aDoubleMatrix, int rowNumber){
		double [] extractedRow = new double [aDoubleMatrix.length];
		for (int i = 0; i<aDoubleMatrix.length; i++){
			extractedRow[i] = aDoubleMatrix[i][rowNumber];
		}
		return extractedRow;
	}

	public static double [] extractColumnAndSumArray(double [][] aDoubleMatrix, int columnNumber){
		double [] extractedArray = extractAColumn(aDoubleMatrix, columnNumber);
		double [] extractedAndSummedArray = Conversions.setASumArray(extractedArray);
		return extractedAndSummedArray;
	}

	public static double [] extractRowAndSumArray(double [][] aDoubleMatrix, int rowNumber){
		double [] extractedArray = extractARow(aDoubleMatrix, rowNumber);
		double [] extractedAndSummedArray = Conversions.setASumArray(extractedArray);
		return extractedAndSummedArray;
	}

	//Compute totals of a double array and store it in the last cell
	public static double [] setArraySumInTheLastCell(double [] aDoubleArray){
		double sum = 0.00;
		for (int i = 0; i <aDoubleArray.length-1; i++){
			sum = sum+aDoubleArray[i];			
		}
		aDoubleArray [aDoubleArray.length-1] = sum;
		return aDoubleArray;
	}

	public static double [] setASumArray(double [] aDoubleArray){
		double [] aSumArray = new double [aDoubleArray.length+1];
		for (int i = 0; i< aDoubleArray.length; i++){
			aSumArray [i]= aDoubleArray[i];
		}
		setArraySumInTheLastCell(aSumArray);
		return aSumArray;
	}

	public static double abs(double aDouble){
		
		if(aDouble <0){
			aDouble = -aDouble;
		}
		return aDouble;
	}	

}
