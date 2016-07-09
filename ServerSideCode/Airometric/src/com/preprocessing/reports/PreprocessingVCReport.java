package com.preprocessing.reports;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.model.DBUtil;
import com.report.to.VoiceDataTo;
import com.report.to.VoiceQualityTo;
import com.to.CallSetUpTo;
import com.to.DeviceInfoTO;
import com.to.VoiceConnectivityTO;

public class PreprocessingVCReport {

	static WritableFont cellFont1 = null;
	static WritableCellFormat cellFormat1 = null;
	static WritableCellFormat cellFormat = null;
	static WritableFont cellFont = null;
	static WritableSheet writableSheet1 = null;
	static WritableWorkbook writableWorkbook = null;
	static int rowIndex = 1;
	static int colIndex = 1;
	static int sheetNumber = 0;

	public static void instantiateFonts(File exlFile, String sheetTitle) {
		try {
			rowIndex = 1;
			colIndex = 1;
			writableSheet1 = writableWorkbook.createSheet(sheetTitle,
					sheetNumber);
			cellFont = new WritableFont(WritableFont.ARIAL, 12);
			cellFont.setBoldStyle(WritableFont.BOLD);
			cellFont.setColour(Colour.BLACK);

			cellFont1 = new WritableFont(WritableFont.ARIAL, 10);
			cellFont.setColour(Colour.BLACK);

			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setShrinkToFit(true);
			cellFormat.setBackground(Colour.GRAY_50);
			cellFormat
					.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			cellFormat1 = new WritableCellFormat(cellFont1);
			cellFormat1.setShrinkToFit(true);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN,
					Colour.BLACK);
			cellFormat1.setAlignment(Alignment.CENTRE);
			cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat1.setWrap(true);
			sheetNumber++;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void createHeaderLabels(List<String> labelList) {
		try {
			// System.out.println("labelList00000"+labelList);
			for (String labelName : labelList) {
				writableSheet1.setColumnView(1, 25);
				Label label = new Label(colIndex, rowIndex, labelName,
						cellFormat);
				writableSheet1.addCell(label);
				colIndex++;
			}
		} catch (RowsExceededException e) {
			// TODO: handle exception
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rowIndex++;
	}

	public static void writeToCell(String value, int rowInd, int colInd,
			WritableCellFormat cellFormat) {
		try {
			writableSheet1.setColumnView(1, 25);
			Label label = new Label(colInd, rowInd, value, cellFormat);
			writableSheet1.addCell(label);
		} catch (RowsExceededException e) {
			// TODO: handle exception
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<List<String>> getPreVoiceData(String testNames,
			String[] colNames) {
		List<VoiceConnectivityTO> voiceDataList = new ArrayList<VoiceConnectivityTO>();
		List<List<String>> allRowValuesList = new ArrayList<List<String>>();
		List<String> rowvalues = new ArrayList<String>();
		String query = "SELECT PC.*,M.MARKET_NAME FROM pre_calc_voiceconnectivity_1 PC,MARKET M WHERE PC.TEST_NAME IN ('"
				+ testNames + "') AND M.MARKET_ID = PC.MARKET_ID";
//		System.out.println(query);
		Connection conn = DBUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
//			System.out.println(query);
			while (rs.next()) {
				VoiceConnectivityTO voiceConnectivityTo = new VoiceConnectivityTO();
				rowvalues = new ArrayList<String>();
				for (String colName : colNames) {
					rowvalues.add(rs.getString(colName));
				}
				allRowValuesList.add(rowvalues);
				/*
				 * String marketName = rs.getString("MARKET_NAME"); String
				 * timeStamp = rs.getString("TIMESTAMP"); String chartType =
				 * rs.getString("CHART_TYPE"); String test_name =
				 * rs.getString("TEST_NAME"); String networkType =
				 * rs.getString("NETWORK_TYPE");
				 * 
				 * String imei = rs.getString("DEVICE_IMEI"); String phonenumber
				 * = rs.getString("DEVICE_PHONENUMBER"); String phonetype =
				 * rs.getString("DEVICE_PHONETYPE"); String devicemodel =
				 * rs.getString("DEVICE_MODEL"); String deviceversion =
				 * rs.getString("DEVICE_VERSION"); String cellcid =
				 * rs.getString("CELLLOCATION_CID"); String cellLac =
				 * rs.getString("CELLLOCATION_LAC"); String dataState =
				 * rs.getString("DATA_STATE"); String signalStrength =
				 * rs.getString("SIGNAL_STRENGTH"); int lattitude =
				 * rs.getInt("GEOLOCATION_LATITUDE"); int longitude =
				 * rs.getInt("GEOLOCATION_LANGITUDE");
				 * voiceConnectivityTo.setMarketName(marketName);
				 * voiceConnectivityTo.setTestName(test_name);
				 * voiceConnectivityTo.setChartType(chartType);
				 * voiceConnectivityTo.setTime_stamp(timeStamp);
				 * voiceConnectivityTo.setNetworkType(networkType);
				 * voiceConnectivityTo.setImei(imei);
				 * voiceConnectivityTo.setPhoneNumber(phonenumber);
				 * voiceConnectivityTo.setDevicePhoneType(phonetype);
				 * voiceConnectivityTo.setDevicemodel(devicemodel);
				 * voiceConnectivityTo.setDeviceversion(deviceversion);
				 * voiceConnectivityTo.setCellLocationCID(cellcid);
				 * voiceConnectivityTo.setCellLocationLAC(cellLac);
				 * voiceConnectivityTo.setDataState(dataState);
				 * voiceConnectivityTo.setSignalStrength(signalStrength);
				 * voiceConnectivityTo.setLattitude(lattitude);
				 * voiceConnectivityTo.setLongitude(longitude);
				 * voiceDataList.add(voiceConnectivityTo);
				 */
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allRowValuesList;
	}

	static void renderReport(List<VoiceConnectivityTO> voiceDataList) {
		for (VoiceConnectivityTO voiceConnectivityTO : voiceDataList) {
			int colIndex = 1;
			writeToCell(voiceConnectivityTO.getMarketName(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getTestName(), rowIndex, colIndex,
					cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getChartType(), rowIndex, colIndex,
					cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getTime_stamp().toString(),
					rowIndex, colIndex, cellFormat1);
			colIndex++;

			writeToCell(voiceConnectivityTO.getNetworkType(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getImei(), rowIndex, colIndex,
					cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getPhoneNumber(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getDevicePhoneType(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;

			writeToCell(voiceConnectivityTO.getDevicemodel(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getDeviceversion(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getCellLocationCID(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getCellLocationLAC(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getDataState(), rowIndex, colIndex,
					cellFormat1);
			colIndex++;
			writeToCell(voiceConnectivityTO.getSignalStrength(), rowIndex,
					colIndex, cellFormat1);
			colIndex++;
			writeToCell(new Double(voiceConnectivityTO.getLattitude())
					.toString(), rowIndex, colIndex, cellFormat1);
			colIndex++;
			writeToCell(new Double(voiceConnectivityTO.getLongitude())
					.toString(), rowIndex, colIndex, cellFormat1);
			colIndex++;
			/*
			 * writeToCell(voiceConnectivityTO.getNetworkType().toString(),rowIndex
			 * ,colIndex,cellFormat1); colIndex++;
			 */
			rowIndex++;
		}
	}



	public static void preProcessVCReport(String filePath, String testNames) {

		List<String> labelList = new ArrayList<String>();

		labelList.add("Market Name");
		labelList.add("Test Name");
		labelList.add("Parameter");
		labelList.add("TimeStamp");

		labelList.add("NETWORK_TYPE");
		labelList.add("DEVICE_IMEI");
		labelList.add("DEVICE_PHONENUMBER");
		labelList.add("DEVICE_PHONETYPE");

		labelList.add("DEVICE_MODEL");
		labelList.add("DEVICE_VERSION");
		labelList.add("CELLLOCATION_CID");
		labelList.add("CELLLOCATION_LAC");
		labelList.add("DATA_STATE");
		labelList.add("SIGNAL STRENGTH");
		labelList.add("LATTITUDE");
		labelList.add("LONGITUDE");
		/*
		 * labelList.add("Network Type"); labelList.add("Signal Strength");
		 */
		// System.out.println("helooooooo");
		try {
			File exlFile = new File(filePath);
			if (exlFile.exists()) {
				exlFile.delete();
			}
			exlFile = new File(filePath);
			writableWorkbook = Workbook.createWorkbook(exlFile);
			// System.out.println("hiiii");
			instantiateFonts(exlFile, "CallSetup");
			// System.out.println("nooooooo");
			createHeaderLabels(labelList);
			// List<VoiceConnectivityTO> vqList = getPreVoiceData(testNames);
			// renderReport(vqList);
			PreprocessingVCCallRetentionReport.renderRententionReport(filePath,
					testNames);
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		List<String> testList = new ArrayList<String>();
		testList.add("srikanth");
		testList.add("srikanth");
		testList.add("srikanth");
		testList.set(0, "kulkarni");
//		System.out.println(testList);
	}
}
