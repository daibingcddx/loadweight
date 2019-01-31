/*     */ package com.sie.weight;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class ScaleConfig
/*     */ {
/*     */   public static final String PORT_NAME = "port_name";
/*     */   public static final String BAUD_RATE = "baud_rate";
/*     */   public static final String DATA_BITS = "data_bits";
/*     */   public static final String STOP_BITS = "stop_bits";
/*     */   public static final String PARITY = "parity";
/*     */   public static final String APP_NAME = "app_name";
/*     */   public static final String OPEN_TIMEOUT = "open_timeout";
/*     */   public static final String READ_TIMEOUT = "read_timeout";
/*     */   private String portName;
/*     */   private int baudRate;
/*     */   private int dataBits;
/*     */   private int stopBits;
/*     */   private int parity;
/*  21 */   private String appName = "YAOHUA-SCALE";
/*     */ 
/*  23 */   private int openTimeout = 100;
/*  24 */   private int readoutTimeout = 2000;
/*  25 */   private int datalength = 13;
/*     */ 
/*     */   public ScaleConfig(InputStream inputStream)
/*     */   {
/*     */     try
/*     */     {
/*  31 */       Properties props = new Properties();
/*  32 */       props.load(inputStream);
/*  33 */       if (props.containsKey("port_name")) {
/*  34 */         this.portName = props.getProperty("port_name");
/*     */       }
/*  36 */       if (props.containsKey("baud_rate")) {
/*  37 */         this.baudRate = Integer.parseInt(props.getProperty("baud_rate").trim());
/*     */       }
/*  39 */       if (props.containsKey("data_bits")) {
/*  40 */         this.dataBits = Integer.parseInt(props.getProperty("data_bits").trim());
/*     */       }
/*  42 */       if (props.containsKey("stop_bits")) {
/*  43 */         this.stopBits = Integer.parseInt(props.getProperty("stop_bits").trim());
/*     */       }
/*  45 */       if (props.containsKey("parity")) {
/*  46 */         this.parity = Integer.parseInt(props.getProperty("parity").trim());
/*     */       }
/*  48 */       if (props.contains("app_name")) {
/*  49 */         this.appName = props.getProperty("app_name");
/*     */       }
/*  51 */       if (props.containsKey("open_timeout")) {
/*  52 */         this.openTimeout = Integer.parseInt(props.getProperty("open_timeout").trim());
/*     */       }
/*  54 */       if (props.containsKey("read_timeout")) {
/*  55 */         this.readoutTimeout = Integer.parseInt(props.getProperty("read_timeout").trim());
/*     */       }
/*  57 */       if (props.containsKey("data_length")) {
/*  58 */         this.datalength = Integer.parseInt(props.getProperty("data_length").trim());
/*     */       }
/*  60 */       inputStream.close();
/*     */     } catch (Exception e) {
/*  62 */       throw new ScaleConfigException("加载配置信息时发生错误", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public ScaleConfig() {
/*     */   }
/*     */ 
/*     */   public String getPortName() {
/*  70 */     return this.portName;
/*     */   }
/*     */ 
/*     */   public void setPortName(String portName) {
/*  74 */     this.portName = portName;
/*     */   }
/*     */ 
/*     */   public int getBaudRate() {
/*  78 */     return this.baudRate;
/*     */   }
/*     */ 
/*     */   public void setBaudRate(int baudRate) {
/*  82 */     this.baudRate = baudRate;
/*     */   }
/*     */ 
/*     */   public int getDataBits() {
/*  86 */     return this.dataBits;
/*     */   }
/*     */ 
/*     */   public void setDataBits(int dataBits) {
/*  90 */     this.dataBits = dataBits;
/*     */   }
/*     */ 
/*     */   public int getStopBits() {
/*  94 */     return this.stopBits;
/*     */   }
/*     */ 
/*     */   public void setStopBits(int stopBits) {
/*  98 */     this.stopBits = stopBits;
/*     */   }
/*     */ 
/*     */   public int getParity() {
/* 102 */     return this.parity;
/*     */   }
/*     */ 
/*     */   public void setParity(int parity) {
/* 106 */     this.parity = parity;
/*     */   }
/*     */ 
/*     */   public String getAppName() {
/* 110 */     return this.appName;
/*     */   }
/*     */ 
/*     */   public void setAppName(String appName) {
/* 114 */     this.appName = appName;
/*     */   }
/*     */ 
/*     */   public int getOpenTimeout() {
/* 118 */     return this.openTimeout;
/*     */   }
/*     */ 
/*     */   public void setOpenTimeout(int openTimeout) {
/* 122 */     this.openTimeout = openTimeout;
/*     */   }
/*     */   public int getReadoutTimeout() {
/* 125 */     return this.readoutTimeout;
/*     */   }
/*     */   public void setReadoutTimeout(int readoutTimeout) {
/* 128 */     this.readoutTimeout = readoutTimeout;
/*     */   }
/*     */   public int getDatalength() {
/* 131 */     return this.datalength;
/*     */   }
/*     */   public void setDatalength(int datalength) {
/* 134 */     this.datalength = datalength;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\weight.jar
 * Qualified Name:     cn.com.yaohua.ScaleConfig
 * JD-Core Version:    0.6.2
 */