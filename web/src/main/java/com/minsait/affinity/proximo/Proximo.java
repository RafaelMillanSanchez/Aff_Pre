package com.minsait.affinity.proximo;

import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

public class Proximo {
	
	public static void setup() {
	    String proximoUrl = "http://proxy:42a8294251b34918a21336915804757d@proxy-79-125-110-199.proximo.io";
	    if (proximoUrl != null) {
	      URL proximo = null;
		try {
			proximo = new URL(proximoUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      String userInfo = proximo.getUserInfo();
	      String user = userInfo.substring(0, userInfo.indexOf(':'));
	      String password = userInfo.substring(userInfo.indexOf(':') + 1);
	
	      System.setProperty("socksProxyHost", proximo.getHost());
	      Authenticator.setDefault(new ProxyAuth(user, password));
	  }
  }

	private static class ProxyAuth extends Authenticator {
	    private final PasswordAuthentication passwordAuthentication;
	
	    private ProxyAuth(String user, String password) {
	      passwordAuthentication = new PasswordAuthentication(user, password.toCharArray());
	    }
	
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	      return passwordAuthentication;
	    }
  }
}
