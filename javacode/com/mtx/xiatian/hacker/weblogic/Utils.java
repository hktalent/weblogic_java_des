package com.mtx.xiatian.hacker.weblogic;
/*    */ import java.io.FileInputStream;
/*    */ 
/*    */ public class Utils
/*    */ {
/*    */   public static byte[] hexStringToBytes(String hexString)
/*    */   {
/*  7 */     if ((hexString == null) || (hexString.equals(""))) {
/*  8 */       return null;
/*    */     }
/* 10 */     hexString = hexString.toUpperCase();
/* 11 */     int length = hexString.length() / 2;
/* 12 */     char[] hexChars = hexString.toCharArray();
/* 13 */     byte[] d = new byte[length];
/* 14 */     for (int i = 0; i < length; i++) {
/* 15 */       int pos = i * 2;
/* 16 */       d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)]));
/*    */     }
/* 18 */     return d;
/*    */   }
/*    */   private static byte charToByte(char c) {
/* 21 */     return (byte)"0123456789ABCDEF".indexOf(c);
/*    */   }
/*    */   public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
/* 24 */     byte[] byte_3 = new byte[byte_1.length + byte_2.length];
/* 25 */     System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
/* 26 */     System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
/* 27 */     return byte_3;
/*    */   }
/*    */ 
/*    */   public static byte[] GetByteByFile(String FilePath) throws Exception {
/* 31 */     FileInputStream fi = new FileInputStream(FilePath);
/* 32 */     byte[] temp = new byte[50000000];
/* 33 */     int length = -1;
/* 34 */     length = fi.read(temp);
/* 35 */     byte[] file = new byte[length];
/* 36 */     for (int i = 0; i < length; i++)
/*    */     {
/* 38 */       file[i] = temp[i];
/*    */     }
/* 40 */     fi.close();
/* 41 */     temp = null;
/* 42 */     return file;
/*    */   }
/*    */ }
