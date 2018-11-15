package com.taikang.common.httpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.taikang.common.safeUtil.DESencrypt;

public class RestAPITestUtil {
	
	
	public static String testPostEncrypt(String desKey,String url,String value){
		String message = DESencrypt.encryptECB(desKey, value);
		try {
			String result = testPostPlainText(url,message);
			result = DESencrypt.decryptECB(desKey, result);
			System.out.println(result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String testPostPlainText(String url, String json) throws IOException {
		URL restServiceURL;
		try {
			restServiceURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "text/plain");
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("" + connection.getResponseCode());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String outPut="";
			StringBuffer buffer = new StringBuffer();
			System.out.println("------------Post-method-output-----------");
			while ((outPut = reader.readLine()) != null) {
				buffer.append(outPut);
			}
			connection.disconnect();
			return buffer.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return "N/A";
	}
	
	public static String testPost(String url, String json) throws IOException {
		URL restServiceURL;
		try {
			System.out.println("------------Post-method-input-----------");
			System.out.println(json);
			restServiceURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) restServiceURL.openConnection();
			System.out.println("========链接地址正常打开！！！！！！！！！！！！！！！！！！！！========");
			
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes());
			os.flush();
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("" + connection.getResponseCode());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer output = new StringBuffer();
			String outPut;
			System.out.println("------------Post-method-output-----------");
			while ((outPut = reader.readLine()) != null) {
				System.out.println(outPut);
				output.append(outPut);
			}
			connection.disconnect();
			return output.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void testGet(String url) throws IOException {
		try {
			URL restServiceURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) restServiceURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			System.out.println("--connection.getResponseCode()---"
					+ connection.getResponseCode());
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("" + connection.getResponseCode());
			}
			String outPut;
			BufferedReader response = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			System.out.println("------------Get-method-output-----------");
			while ((outPut = response.readLine()) != null) {
				System.out.println(outPut);
			}
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public static String testGetEncrypt(String url) throws IOException {
		try {
			URL restServiceURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) restServiceURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "text/plain");
			System.out.println("--connection.getResponseCode()---"
					+ connection.getResponseCode());
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("" + connection.getResponseCode());
			}
			String outPut;
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			System.out.println("------------Get-method-output-----------");
			while ((outPut = reader.readLine()) != null) {
				buffer.append(outPut);
			}
			connection.disconnect();
			return buffer.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
