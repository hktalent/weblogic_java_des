package com.mtx.xiatian.hacker.weblogic;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class ParseOptions
/*    */ {
/*    */   public String[] remArgs;
/*    */   public String usage_string;
/* 28 */   String none = "None";
/*    */   String option;
/*    */   int i;
/*    */   int j;
/*    */ 
/*    */   public ParseOptions(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString)
/*    */   {
/* 43 */     Vector localVector = new Vector();
/*    */ 
/* 45 */     this.usage_string = paramString;
/*    */ 
/* 47 */     for (this.i = 0; this.i < paramArrayOfString1.length; this.i += 1)
/*    */     {
/* 49 */       for (this.j = 0; this.j < paramArrayOfString2.length; this.j += 1)
/*    */       {
/* 51 */         if (checkOption(paramArrayOfString1, paramArrayOfString2[this.j], paramArrayOfString3[this.j])) {
/* 52 */           paramArrayOfString3[this.j] = this.option;
/* 53 */           break;
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 58 */       if (this.j < paramArrayOfString2.length)
/*    */         continue;
/* 60 */       if (paramArrayOfString1[this.i].charAt(0) == '-') usage_error(); else {
/* 61 */         localVector.addElement(paramArrayOfString1[this.i]);
/*    */       }
/*    */     }
/*    */ 
/* 65 */     this.remArgs = new String[localVector.size()];
/* 66 */     for (this.i = 0; this.i < localVector.size(); this.i += 1)
/* 67 */       this.remArgs[this.i] = ((String)localVector.elementAt(this.i));
/*    */   }
/*    */ 
/*    */   private boolean checkOption(String[] paramArrayOfString, String paramString1, String paramString2)
/*    */   {
/* 73 */     if (paramArrayOfString[this.i].equals(paramString1)) {
/* 74 */       if (!this.none.equals(paramString2)) {
/* 75 */         if (++this.i < paramArrayOfString.length) this.option = paramArrayOfString[this.i]; else
/* 76 */           usage_error();
/*    */       } else this.option = "Set";
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   public void usage_error()
/*    */   {
/* 85 */     System.out.println("Usage: " + this.usage_string);
/* 86 */     System.exit(1);
/*    */   }
/*    */ }

/* Location:           \\psf\Home\\Documents\安全工作\远程执行漏洞工具包\WebLogic_EXP.jar
 * Qualified Name:     ParseOptions
 * JD-Core Version:    0.6.0
 */