/*    */ package com.sie.weight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*    */ 
/*    */ public class ScaleConfigException extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = -8408111388504239133L;
/*    */ 
/*    */   public ScaleConfigException(String msg, Throwable e)
/*    */   {
/*  9 */     super(msg, e);
/*    */   }
/*    */ 
/*    */   public ScaleConfigException(String msg) {
/* 13 */     super(msg);
/*    */   }

public static void main(String[] args) {
	 String temp = "";
	 String s ="S S    10843.5 g";
	 String  weight="";
	           int x = s.indexOf("g",5);
	           
	           String str = "S S       12742 g";
	   		String pattern = "[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*";

	   		Pattern r = Pattern.compile(pattern);
	   		Matcher m = r.matcher(str);
	   		System.out.println(m.matches());
	           
	           System.out.println(x);
	       if (x < 8) {
	             weight = "0";
	 /*     */           }else{
	 	                   temp = s.substring(x - 8, x+1);
	 	                   System.out.println(temp);
	 	                  //temp = temp + s.substring(x, x + 4);
	 	                  s = temp;
	 	                  weight = s;
	 					}
	       System.out.println(weight);
}
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\weight.jar
 * Qualified Name:     cn.com.yaohua.ScaleConfigException
 * JD-Core Version:    0.6.2
 */