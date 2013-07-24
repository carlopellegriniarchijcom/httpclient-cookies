package com.archij.sample;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class ClientCoookies {

	public static void run() {
		try {
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setParameter("http.useragent", "My Browser");
			GetMethod method = new GetMethod("http://localhost:9090/");
			httpclient.executeMethod(method);
			org.apache.commons.httpclient.Cookie cookie = new org.apache.commons.httpclient.Cookie();
			cookie.setPath("/");
			cookie.setName("Tim");
			cookie.setValue("Tim");
			cookie.setDomain("localhost");
			httpclient.getState().addCookie(cookie);
			httpclient.executeMethod(method);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
