package com.example.shohel.khaler_kontho.Utils;


public class BaseURL {
	
//	public static String HTTP = "http://lovelane.aapbd.xyz/api/v1/";
	public static String HTTP = "http://luna.aapbd.xyz/api/v1/contractor/";
	public static String ADD_API_TOKEN="?api_token=";

	/**
	 * @return the hTTP
	 */
	public static String makeHTTPURL(String param) {
		return BaseURL.HTTP + UrlUtils.encode(param);
	}

	/**
	 * @param hTTP
	 *            the hTTP to set
	 */
	public static void setHTTP(final String hTTP) {
		BaseURL.HTTP = hTTP;
	}

}
