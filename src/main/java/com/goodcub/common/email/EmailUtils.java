package com.goodcub.common.email;

import java.net.HttpURLConnection;
import java.net.URL;

public class EmailUtils {
	/**
     * 验证一个url是否有效
     * @param strLink
     * @return
     */
	public static boolean isValid(String strLink) {
		URL url;
		try {
			url = new URL(strLink);
			HttpURLConnection connt = (HttpURLConnection) url.openConnection();
			connt.setRequestMethod("HEAD");
			String strMessage = connt.getResponseMessage();
			if (strMessage.compareTo("Not Found") == 0) {
				return false;
			}
			connt.disconnect();
		} catch (Exception e) {
            return false;
		}

		return true;
	}
	
	public static String getServiceUrl(String email){
		
		String mailUrl = "http://mail.";
		
		mailUrl += email.substring(email.lastIndexOf("@")+1);
		
		return mailUrl;
	}
	
	
    public static void main(String[] args) {
    	
    	System.out.println(isValid(EmailUtils.getServiceUrl("520@qq.com")));
    	
	}
}
