package com.mtx.xiatian.hacker.weblogic;
/*    */ import com.adventnet.snmp.snmp2.SnmpSession;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class SetValues
/*    */ {
/* 15 */   boolean usage_error = false;
/*    */   private static final int COMMUNITY = 1;
/*    */   private static final int WRITE_COMMUNITY = 2;
/*    */   private static final int PORT = 3;
/*    */   private static final int RETRIES = 4;
/*    */   private static final int TIMEOUT = 5;
/*    */   private static final int VERSION = 7;
/*    */   private static final int USER_NAME = 8;
/*    */   private static final int AUTH_PROTOCOL = 9;
/*    */   private static final int AUTH_PASSWORD = 10;
/*    */   private static final int PRIV_PASSWORD = 11;
/*    */   private static final int CONTEXT_NAME = 12;
/*    */   private static final int CONTEXT_ID = 13;
/* 31 */   String userName = new String("");
/* 32 */   int authProtocol = 20;
/* 33 */   String authPassword = new String("");
/* 34 */   String privPassword = new String("");
/* 35 */   String contextName = new String("");
/* 36 */   String contextID = new String("");
/*    */ 
/*    */   public SetValues(SnmpSession paramSnmpSession, String[] paramArrayOfString)
/*    */   {
/* 41 */     if (paramArrayOfString[1] != null)
/* 42 */       paramSnmpSession.setCommunity(paramArrayOfString[1]);
/* 43 */     if (paramArrayOfString[2] != null) {
/* 44 */       paramSnmpSession.setWriteCommunity(paramArrayOfString[2]);
/*    */     }
/*    */     try
/*    */     {
/* 48 */       if (paramArrayOfString[3] != null)
/* 49 */         paramSnmpSession.setRemotePort(Integer.parseInt(paramArrayOfString[3]));
/* 50 */       if (paramArrayOfString[4] != null)
/* 51 */         paramSnmpSession.setRetries(Integer.parseInt(paramArrayOfString[4]));
/* 52 */       if (paramArrayOfString[5] != null)
/* 53 */         paramSnmpSession.setTimeout(Integer.parseInt(paramArrayOfString[5]));
/*    */     }
/*    */     catch (NumberFormatException localNumberFormatException) {
/* 56 */       System.err.println("Invalid Integer Arg");
/*    */     }
/*    */ 
/* 59 */     if (paramArrayOfString[7] != null) {
/* 60 */       if (paramArrayOfString[7].equals("v2")) {
/* 61 */         paramSnmpSession.setVersion(1);
/* 62 */       } else if (paramArrayOfString[7].equals("v1")) {
/* 63 */         paramSnmpSession.setVersion(0);
/* 64 */       } else if (paramArrayOfString[7].equals("v3")) {
/* 65 */         paramSnmpSession.setVersion(3);
/*    */       }
/*    */       else {
/* 68 */         System.out.println("Invalid Version Number");
/* 69 */         this.usage_error = true;
/*    */       }
/*    */     }
/*    */ 
/* 73 */     if (paramSnmpSession.getVersion() == 3)
/* 74 */       if (paramArrayOfString[8] != null) {
/* 75 */         this.userName = paramArrayOfString[8];
/*    */ 
/* 77 */         if ((paramArrayOfString[9] != null) && (paramArrayOfString[10] != null)) {
/* 78 */           if (paramArrayOfString[9].equals("SHA"))
/* 79 */             this.authProtocol = 22;
/*    */           else
/* 81 */             this.authProtocol = 21;
/* 82 */           if (this.authProtocol == 20) {
/* 83 */             System.err.println("Enter authentication protocol");
/* 84 */             this.usage_error = true;
/*    */           }
/*    */ 
/* 87 */           this.authPassword = paramArrayOfString[10];
/* 88 */           if (paramArrayOfString[11] != null)
/* 89 */             this.privPassword = paramArrayOfString[11];
/*    */         }
/* 91 */         else if ((paramArrayOfString[9] != null) || (paramArrayOfString[10] != null) || (paramArrayOfString[11] != null)) {
/* 92 */           this.usage_error = true;
/*    */         }
/* 94 */         if (paramArrayOfString[12] != null) {
/* 95 */           this.contextName = paramArrayOfString[12];
/*    */         }
/* 97 */         if (paramArrayOfString[13] != null) {
/* 98 */           this.contextID = paramArrayOfString[13];
/*    */ 
/* 74 */           return;
/*    */         }
/*    */ 
/*    */       }
/*    */       else
/*    */       {
/* 102 */         System.err.println("UserName Missing");
/* 103 */         this.usage_error = true;
/*    */       }
/*    */   }
/*    */ }

/* Location:           \\psf\Home\\Documents\安全工作\远程执行漏洞工具包\WebLogic_EXP.jar
 * Qualified Name:     SetValues
 * JD-Core Version:    0.6.0
 */