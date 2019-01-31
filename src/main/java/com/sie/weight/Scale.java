/*     */ package com.sie.weight;
/*     */ 
/*     */ import gnu.io.CommPortIdentifier;
/*     */ import gnu.io.NoSuchPortException;
/*     */ import gnu.io.PortInUseException;
/*     */ import gnu.io.SerialPort;
/*     */ import gnu.io.UnsupportedCommOperationException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class Scale
/*     */ {
/*     */   public static final char GET_WEIGHT_CMD = 'R';
/*     */   public static final char CLEAR_WEIGHT_CMD = 'Z';
/*     */   public static final char CLEAR_TARE_CMD = 'T';
/*  21 */   private Pattern pattern = Pattern.compile("(ww|wn|wt)(-?\\d+\\.\\d+)(kg|lb)");
/*     */   private ScaleConfig cfg;
/*     */   private SerialPort port;
/*     */   private OutputStream os;
/*     */   private InputStream is;
/*     */   private boolean open;
/*     */ 
/*     */   public boolean isOpen()
/*     */   {
/*  30 */     return this.open;
/*     */   }
/*     */ 
/*     */   public Scale(ScaleConfig cfg) {
/*  34 */     this.cfg = cfg;
/*     */   }
/*     */ 
/*     */   public void openPort()
/*     */   {
/*     */     try
/*     */     {
/*  41 */       CommPortIdentifier portIdRs = CommPortIdentifier.getPortIdentifier(this.cfg.getPortName());
/*  42 */       this.port = ((SerialPort)portIdRs.open(this.cfg.getAppName(), this.cfg.getOpenTimeout()));
/*  43 */       this.os = this.port.getOutputStream();
/*  44 */       this.is = this.port.getInputStream();
/*  45 */       this.port.setSerialPortParams(this.cfg.getBaudRate(), this.cfg.getDataBits(), this.cfg.getStopBits(), this.cfg.getParity());
/*  46 */       this.open = true;
/*     */     } catch (NoSuchPortException e) {
/*  48 */       e.printStackTrace();
/*  49 */       throw new NoSuchScalePortException("端口号" + this.cfg.getPortName() + "不存在", e);
/*     */     } catch (PortInUseException e) {
/*  51 */       e.printStackTrace();
/*  52 */       throw new ScalePortInUseException("设备未开启或端口号" + this.cfg.getPortName() + "被占用", e);
/*     */     } catch (UnsupportedCommOperationException e) {
/*  54 */       e.printStackTrace();
/*  55 */       throw new UnsupportedScaleCommOperationException("端口参数设置错误", e);
/*     */     }
/*     */     catch (IOException e) {
/*  58 */       e.printStackTrace();
/*  59 */       throw new ScaleIOException("建立输入/输出流时发生错误", e);
/*     */     } catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       throw new ScaleIOException("unknow error");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void closePort()
/*     */   {
/*     */     try
/*     */     {
/*  70 */      
				if (this.os!=null) {
					 this.os.close();
				}
				
				if (this.is!=null) {
					 this.is.close();
				}
				
				if (this.port!=null) {
					 this.port.close();
				}


/*  73 */       this.os = null;
/*  74 */       this.is = null;
/*  75 */       this.port = null;
/*     */     } catch (IOException e) {
/*  77 */       throw new ScaleIOException("关闭端口时发生错误", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getWeight()
/*     */   {
/*  83 */     String weight ="";
/*     */     try {
/*  85 */       String s = "";
/*  86 */       byte[] buffer = new byte[100];
/*  87 */       char[] chars = (char[])null;
/*  88 */       Matcher matcher = null;
/*  89 */       this.os.write(82);
/*  90 */       this.os.flush();
/*  91 */       Long currenttime = Long.valueOf(new Date().getTime());
/*  92 */       while (this.is.available() < this.cfg.getDatalength()) {
/*     */         try {
/*  94 */           Thread.sleep(50L);
/*     */         } catch (InterruptedException e) {
/*  96 */           e.printStackTrace();
/*  97 */           throw new ScaleIOException("thread sleep error");
/*     */         }
/*  99 */         Long nowtime = Long.valueOf(new Date().getTime());
/* 100 */         System.out.println(nowtime.longValue() - currenttime.longValue());
/* 101 */         if (nowtime.longValue() - currenttime.longValue() > this.cfg.getReadoutTimeout()) {
/* 102 */           throw new ScaleIOException("从电子称读取数据超时");
/*     */         }
/*     */       }
/* 105 */       if (this.is.available() >= this.cfg.getDatalength()) {
/* 106 */         int bytes = this.is.read(buffer);
/*     */ 
/* 108 */         if (bytes > 0) {
/* 109 */           chars = new char[bytes];
/*     */ 
/* 111 */           for (int i = 0; i < bytes; i++) {
/* 112 */             chars[i] = ((char)buffer[i]);
/*     */           }
/*     */ 
/* 115 */           s = s + new String(chars).trim();
/*     */ 
/* 117 */           String temp = "";
					System.out.println("读取数据data："+s);
/* 118 */           int x = s.indexOf("g",5);
/* 119 */           if (x < 8) {
/* 120 */             weight = "0";
/*     */           }else{
	                   temp = s.substring(x - 8, x+1);
	                  //temp = temp + s.substring(x, x + 4);
	                   System.out.println("截取后data1："+ temp);
	                  s = temp.trim();
	                  weight = s;
					}
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 129 */       
/*     */     }
/*     */     catch (IOException e) {
/* 132 */       throw new ScaleIOException("从电子称读取数据时发生错误", e);
/*     */     }
/* 134 */     return weight;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/*     */     try
/*     */     {
/* 141 */       this.os.write(90);
/*     */     } catch (IOException e) {
/* 143 */       throw new ScaleIOException("向电子称发送指令时发生错误", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearTare()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.os.write(84);
/*     */     } catch (IOException e) {
/* 153 */       throw new ScaleIOException("向电子称发送指令时发生错误", e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\weight.jar
 * Qualified Name:     cn.com.yaohua.Scale
 * JD-Core Version:    0.6.2
 */