package com.taikang.common;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taikang.common.safeUtil.Md5;


public class TkmCookie
{

	private  Logger log = LoggerFactory.getLogger(TkmCookie.class);
	
	public static final String key = "123456qwertyuipasghj"; //密钥
	private String tkmssid;
	private String tkmtoken;
	public boolean isCheck = false;
	
	public TkmCookie(HttpServletRequest request,String flag)
	{
		
		if("create".equals(flag))
		{
			tkmssid = ""+UUID.randomUUID();
			tkmtoken = Md5.getMD5Mac(tkmssid+key);
		}
		
		else if("check".equals(flag))
		{
			log.info("tttttttt");
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null)
			{
				for(Cookie c :cookies )
				{
					if("tkmssid".equals(c.getName()))
					{
						tkmssid = c.getValue();
					}
					else if("tkmtoken".equals(c.getName()))
					{
						tkmtoken = c.getValue();
					}
	            }
				
				
				if(tkmssid != null && tkmtoken != null && tkmtoken.equals(Md5.getMD5Mac(tkmssid+key)))
				{
					
					isCheck = true;
				}
			}
		}
		
	}

	public String getTkmssid()
	{
		return tkmssid;
	}

	public void setTkmssid(String tkmssid)
	{
		this.tkmssid = tkmssid;
	}

	public String getTkmtoken()
	{
		return tkmtoken;
	}

	public void setTkmtoken(String tkmtoken)
	{
		this.tkmtoken = tkmtoken;
	}
	
}
