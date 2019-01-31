/*    */ package com.sie.controller;
import com.sie.weight.Scale;
import com.sie.weight.ScaleConfig;
/*    */ 
/*    */ public class SatWeightApplet 
/*    */ {
/*    */   private static final long serialVersionUID = 642811731978079592L;
/*    */   private Scale scale;
/*    */ 
/*    */   public void init_weightconfig()
/*    */   {
/* 19 */     ScaleConfig cfg = new ScaleConfig(getClass().getResourceAsStream("/yaohua-scale.properties"));
			try {
				 this.scale = new Scale(cfg);
				 this.scale.openPort();
				 System.out.println("ok");
			} catch (Throwable e) {
				e.printStackTrace();
			}
/* 21 */    
/*    */   }
/*    */ 
/*    */ 
/*    */   public String loadWeight() throws Exception {
/* 32 */     String errorMsg = "";
/*    */     try {
/* 34 */       init_weightconfig();
/* 35 */       String weight = this.scale.getWeight();
/* 37 */       return weight;
/*    */     } catch (Exception e)
/*    */     {
/* 42 */       e.printStackTrace();
/* 44 */       throw new Exception("異常信息"+e.getMessage()) ;
/*    */     } finally {
/*    */       try {
/* 47 */         if (this.scale == null) {
/* 48 */           //throw new Exception("異常信息1：端口異常");
/*    */         }
/* 50 */         this.scale.closePort();
/*    */       } catch (Exception e2) {
/* 52 */         e2.printStackTrace();
/* 53 */         throw new Exception("異常信息2"+e2.getMessage());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\weight.jar
 * Qualified Name:     cn.com.synnex.weight.SatWeightApplet
 * JD-Core Version:    0.6.2
 */