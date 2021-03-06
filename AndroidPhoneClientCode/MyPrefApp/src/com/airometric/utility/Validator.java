package com.airometric.utility;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

import com.airometric.classes.BrowserTestConfig;
import com.airometric.classes.FTPTestConfig;
import com.airometric.classes.MOTestConfig;
import com.airometric.classes.MTTestConfig;
import com.airometric.classes.PingTestConfig;
import com.airometric.classes.UDPTestConfig;
import com.airometric.classes.VOIPTestConfig;
import com.airometric.config.Constants;
import com.airometric.config.StringUtils;

/**
 * 
 * Custom utility class for validations
 * 
 */
public class Validator {

	public static boolean isValidEmail(String strEmail) {
		String NotAllowedChars = " &!#$%^*'?//\\<>||,`~";
		String NumericChars = "1234567890";

		if ((strEmail.indexOf("@") == -1) || (strEmail.indexOf(".") == -1)
				|| (strEmail.indexOf("@") > strEmail.lastIndexOf("."))
				|| (strEmail.lastIndexOf(".") == strEmail.length() - 1)
				|| (strEmail.indexOf("@") == strEmail.lastIndexOf(".") - 1))
			return false;

		if (!strEmail.equals("")) {
			char[] CharOfNumericChars = NumericChars.toCharArray();
			for (int i = 0; i < CharOfNumericChars.length; i++) {
				if (strEmail.charAt(0) == CharOfNumericChars[i])
					return false;
			}

			char[] CharOfNotAllowedChars = NotAllowedChars.toCharArray();
			char[] CharOfstrEmail = strEmail.toCharArray();

			for (int i = 0; i < CharOfNotAllowedChars.length; i++) {
				for (int j = 0; j < CharOfstrEmail.length; j++) {
					if (CharOfNotAllowedChars[i] == CharOfstrEmail[j])
						return false;
				}
			}
		}
		return true;
	}

	public static boolean isEmpty(String str) {
		return TextUtils.isEmpty(str);
	}

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValid2Byte(String str) {
		if (TextUtils.isEmpty(str))
			return false;
		else {
			double val = Double.parseDouble(str);
			if (val <= 655.99)
				return true;
			else
				return false;
		}
	}

	public static boolean isValid4Byte(String str) {
		if (TextUtils.isEmpty(str))
			return false;
		else {
			double val = Double.parseDouble(str);
			if (val <= 42949671.99)
				return true;
			else
				return false;
		}
	}

	public static boolean isValidUsername1(String username) {
		String USERNAME_PATTERN = "^[A-Za-z0-9]$";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(username);

		String SPACE_PATTERN = "^\b$";
		Pattern sp_pattern = Pattern.compile(SPACE_PATTERN);
		Matcher sp_matcher = sp_pattern.matcher(username);
		if (!matcher.matches())
			return false;
		else if (sp_matcher.matches()) {
			return false;
		} else {
			return true;
		}

	}

	public static boolean isValidUsername(String username) {
		if (username.indexOf(" ") != -1)
			return false;
		// else if(Character.isDigit(username.charAt(0)))
		// return false;
		else
			return true;
	}

	// To validate address field in Transaction Details screen
	public static boolean isAddressValid(String address) {
		boolean isAddressFlag = true;

		// if (address.equals(""))
		// return true;
		//
		// String allowedChars =
		// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#,. ";
		// char[] addressChar = address.toCharArray();
		//
		// for (int i = 0; i < addressChar.length; i++) {
		// if (allowedChars.indexOf(String.valueOf(addressChar[i])) == -1) {
		// isAddressFlag = false;
		// return isAddressFlag;
		// }
		// }
		return isAddressFlag;
	}

	// To validate Name field in Transaction Details screen. Onluy
	// alphabets,space and dot is allowed
	public static boolean isNameValid(String name) {
		boolean isNameFlag = true;

		if (name.equals(""))
			isNameFlag = false;

		// String allowedChars =
		// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .,-#";
		// char[] nameChar = name.toCharArray();
		// for (int i = 0; i < nameChar.length; i++) {
		// if (allowedChars.indexOf(String.valueOf(nameChar[i])) == -1) {
		// isNameFlag = false;
		// return isNameFlag;
		// }
		// }
		return isNameFlag;
	}

	// To validate Notes field in Transaction Details screen. Only alphabets,
	// numbers,space and dot is allowed
	public static boolean isNotesValid(String notes) {
		boolean isNotesFlag = true;

		// if (notes.equals(""))
		// return true;
		//
		// String allowedChars =
		// "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 .,-#";
		// char[] nameChar = notes.toCharArray();
		// for (int i = 0; i < nameChar.length; i++) {
		// if (allowedChars.indexOf(String.valueOf(nameChar[i])) == -1) {
		// isNotesFlag = false;
		// return isNotesFlag;
		// }
		// }
		return isNotesFlag;
	}

	// To validate Transaction ID field in Transaction Details screen. Onluy
	// alphabets and numbers are allowed.
	public static boolean isTransactionIDValid(String transactionID) {
		boolean isTransactionIDFlag = true;

		if (transactionID.equals(""))
			return true;

		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char[] transactionIDChar = transactionID.toCharArray();
		for (int i = 0; i < transactionIDChar.length; i++) {
			if (allowedChars.indexOf(String.valueOf(transactionIDChar[i])) == -1) {
				isTransactionIDFlag = false;
				return isTransactionIDFlag;
			}
		}
		return isTransactionIDFlag;
	}

	// To validate that the string does not contain the xml Mark up tags
	public static boolean isValidXMLString(String inputString) {
		boolean flag = true;

		if (inputString.equals(""))
			flag = false;

		// String notAllowedChars = "&</>\\";
		// char[] inputStringCharArray = inputString.toCharArray();
		//
		// for (int i = 0; i < inputStringCharArray.length; i++) {
		// if (notAllowedChars
		// .indexOf(String.valueOf(inputStringCharArray[i])) > -1) {
		// flag = false;
		// return flag;
		// }
		// }
		return flag;
	}

	/**
	 * Only alphabets and numbers are allowed for New Password.
	 */
	public static boolean isEnoughPasswordChars(String strText) {
		// TODO Auto-generated method stub
		boolean isNewPasswordFlag = true;
		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		char[] transactionIDChar = strText.toCharArray();
		int numberOfAlphaChars = 0;
		int numberOfNumericChars = 0;

		// new password Should be min of 7 characters
		if (strText.length() < 7) {
			isNewPasswordFlag = false;
			return isNewPasswordFlag;
		}

		// new password should have only alphabets and numeric characters.
		for (int i = 0; i < transactionIDChar.length; i++) {
			if (allowedChars.indexOf(String.valueOf(transactionIDChar[i])) == -1) {
				isNewPasswordFlag = false;
				return isNewPasswordFlag;
			} else// new password should be mix of alphabets and numerics.
			{
				if (Character.isDigit(transactionIDChar[i])) {
					numberOfNumericChars++;
				} else {
					numberOfAlphaChars++;
				}
			}

		}

		// atleast 1 alphabate and 1 number should be there.
		if (numberOfAlphaChars == 0 || numberOfNumericChars == 0) {
			isNewPasswordFlag = false;
			return isNewPasswordFlag;
		}

		return isNewPasswordFlag;
	}

	public boolean containsIgnoreCase(String str, ArrayList<String> list) {
		for (String i : list) {
			if (i.equalsIgnoreCase(str))
				return true;
		}
		return false;
	}

	public static int indexOf(String str, ArrayList<String> list) {
		int ind = -1, found = -1;
		for (String i : list) {
			ind++;
			if (i.equalsIgnoreCase(str)) {
				found = ind;
				break;
			}
		}
		if (found != -1)
			return found;
		else
			return -1;
	}

	public static boolean isValidDeviceID(String sDeviceID) {

		Pattern pattern = Pattern.compile("[A-Fa-f0-9]+");
		Matcher matcher = pattern.matcher(sDeviceID);

		if (matcher.matches())
			return true;
		else
			return false;

	}

	public static boolean isValidAlphaNumeric(String username) {
		String USERNAME_PATTERN = "^[A-Za-z0-9]$";
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = pattern.matcher(username);

		return matcher.matches();
	}

	public static boolean isValidURL(String pUrl) {

		URL u = null;
		try {
			u = new URL(pUrl);
		} catch (MalformedURLException e) {
			return false;
		}
		try {
			u.toURI();
		} catch (URISyntaxException e) {
			return false;
		}
		return true;
	}

	public static boolean isValidMOConfig(MOTestConfig objMOConfig) {
		boolean isValidConfig = true;
		if (objMOConfig == null)
			return false;
		if (Validator.isEmpty(objMOConfig.sPhoneNumber)
				|| Validator.isEmpty(objMOConfig.sCallDuration)
				|| Validator.isEmpty(objMOConfig.sPauseTime)
				|| Validator.isEmpty(objMOConfig.sTestDuration)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static boolean isValidMTConfig(MTTestConfig objMTTonfig) {
		boolean isValidConfig = true;
		if (objMTTonfig == null)
			return false;
		if (Validator.isEmpty(objMTTonfig.sCallDuration)
				|| Validator.isEmpty(objMTTonfig.sTestDuration)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static boolean isValidFTPConfig(FTPTestConfig objFTPConfig) {
		boolean isValidMOConfig = true;
		if (objFTPConfig == null)
			return false;
		if (Validator.isEmpty(objFTPConfig.sServerURL)
				|| Validator.isEmpty(objFTPConfig.sUsername)
				|| Validator.isEmpty(objFTPConfig.sPassword)
				|| Validator.isEmpty(objFTPConfig.sTestCyles)
				|| Validator.isEmpty(objFTPConfig.sUploadPath)
				|| Validator.isEmpty(objFTPConfig.sFileToDownload)) {
			isValidMOConfig = false;
		}
		return isValidMOConfig;
	}

	public static boolean isValidUDPConfig(UDPTestConfig UDPTestConfigObj) {
		boolean isValidConfig = true;
		if (UDPTestConfigObj == null)
			return false;
		if (Validator.isEmpty(UDPTestConfigObj.sServerIP)
				|| Validator.isEmpty(UDPTestConfigObj.sServerPort)
				|| Validator.isEmpty(UDPTestConfigObj.sTestCycles)
				|| Validator.isEmpty(UDPTestConfigObj.sFileToUpload)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static boolean isValidPingConfig(PingTestConfig objPingTonfig) {
		boolean isValidConfig = true;
		if (objPingTonfig == null)
			return false;
		if (Validator.isEmpty(objPingTonfig.sServerIP)
				|| Validator.isEmpty(objPingTonfig.sCycles)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static boolean isValidBrowserConfig(
			BrowserTestConfig objBrowserTonfig) {
		boolean isValidConfig = true;
		if (objBrowserTonfig == null)
			return false;
		if (Validator.isEmpty(objBrowserTonfig.sPageURL)
				|| Validator.isEmpty(objBrowserTonfig.sCycles)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static boolean isValidVOIPConfig(VOIPTestConfig objVOIPTonfig) {
		boolean isValidConfig = true;
		if (objVOIPTonfig == null)
			return false;
		if (Validator.isEmpty(objVOIPTonfig.sTestDuration)) {
			isValidConfig = false;
		}
		return isValidConfig;
	}

	public static String getValidCyclesText(String input, String sType) {
		int maxCount = 0;
		if (sType.equals(StringUtils.TEST_TYPE_CODE_FTP)
				|| sType.equals(StringUtils.TEST_TYPE_CODE_UDP)
				|| sType.equals(StringUtils.TEST_TYPE_CODE_PING)
				|| sType.equals(StringUtils.TEST_TYPE_CODE_BROWSER))
			maxCount = Constants.TEST_CYCLES_MAX_COUNT_FTP_UDP;
		else
			maxCount = Constants.TEST_CYCLES_MAX_COUNT;
		if (isEmpty(input))
			return "Please enter no of cycles";
		try {
			int cycles = Integer.parseInt(input);
			if (cycles < 1 || cycles > maxCount)
				return "Cycles values should be between 1 and " + maxCount;
			else
				return null;
		} catch (Exception e) {
			return "Please enter valid numer of cycles";
		}
	}
}
