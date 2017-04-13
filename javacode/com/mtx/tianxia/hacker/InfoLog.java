package com.mtx.tianxia.hacker;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class InfoLog
{
	
	/**
     * 获取s中符合szPattern正则表达式的内容
     *
     * @param szPattern
     * @param s
     *
     * @return
     */
    public static String[] getPatternStr(String szPattern, String s) {
        if (null == s || null == szPattern) return null;
        String[] a = null;
        Pattern p = Pattern.compile(szPattern, Pattern.DOTALL);
        Matcher m = p.matcher(s);
        if (m.find()) {
            int j = m.groupCount(), i = 0;
            a = new String[j];
            for (; i < j; i++)
                a[i] = m.group(i);
        }
        return a;
    }

	public static String getTime()
	{
		return getTime("yyyy-MM-dd HH:mm:ss");
	}
	public static String getTime(String szFormat)
	{
		return new SimpleDateFormat(szFormat).format(new Date());
	}
	/**
	 * 获取文件内容
	 * @param f
	 * @return
	 */
	public static String getFile(File f )
	{
		try
		{
			if(0L == f.length())
			{
				info("0字节文件: " , f.getAbsolutePath());
				return null;
			}
			FileInputStream in = new FileInputStream(f);
		    ByteOutputStream out = new ByteOutputStream();
		    byte []a = new byte[1024];
		    int i = 0;
		    while(-1 < (i = in.read(a, 0, a.length)))
		    {
		    		out.write(a,0, i);
		    }
		    in.close();
		    a = out.getBytes();
		    out.close();out = null;
		    return new String(a, "UTF-8");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 输出日志
	 * 
	 * @param out
	 * @param a
	 */
	public static void log(OutputStream out, Object... a)
	{
		StringBuffer sb = new StringBuffer();
		for (Object s : a)
		{
			if(s instanceof OutputStream)continue;
			if(s instanceof Throwable)((Throwable)s).printStackTrace();
			else sb.append(String.valueOf(s));
		}
		sb.append("\n");
		try
        {
	        out.write(sb.toString().getBytes());
        } catch (IOException e)
        {
	        e.printStackTrace();
        }
	}

	/**
	 * 添加文件
	 * @param szFileName
	 * @param b
	 */
	public static void writeFile(String szFileName, String b)
	{
		try
        {
	        writeFile(szFileName, b.getBytes("UTF-8"), true);
        } catch (Exception e)
        {
	        info(e);
        }
	}
	
	public static OutputStream writeFileForBuf(String szFileName, String b, OutputStream o)
	{
		try
		{
			return writeFileForBuf(szFileName, b.getBytes("UTF-8"), true, o);
		}catch(Exception e)
		{
			info(e);
		}
		return null;
	}
	
	/**
	 * 解压文件
	 * @param szFileName
	 */
	public static void doUnZipFile(String szFileName)
	{
		InputStream in = null;
		OutputStream out = null;
		try
		{
			in = new GZIPInputStream(new FileInputStream(new File(szFileName)));
			out = new BufferedOutputStream(new FileOutputStream(new File(szFileName.substring(0, szFileName.lastIndexOf(".")))), 64 * 1024 * 1024);
			byte []b = new byte[10 * 1024];
			int i = 0;
			while(-1 < (i = in.read(b, 0, b.length)))
			{
				out.write(b, 0, i);
			}
			out.flush();
		}catch(Exception e)
		{
			info(e);
		}
		finally
		{
			if(null != in)
			{
				try
				{
					in.close();
				}catch(Exception e)
				{
					info(e);
				}
			}
			if(null != out)
			{
				try
				{
					out.flush();
					out.close();
				}catch(Exception e)
				{
					info(e);
				}
			}
		}
		if(new File(szFileName).exists())
			info("Ok: ", szFileName);
	}
	
	public static void main(String []args)
	{
		doUnZipFile("/Volumes/data 1/sg/MTX私有/GroupData11_sa.sql.zip");
	}
	
	public  static OutputStream writeFileForBuf(String szFileName, byte []b, boolean bApp, OutputStream o)
	{
		OutputStream out = o;
		try
		{
			int lnSize = 1024 * 1024 * 2; // 100M
			if(null == out)
			{
				/**
				 * JDK GZIP ——这是一个压缩比高的慢速算法，压缩后的数据适合长期使用。JDK中的java.util.zip.GZIPInputStream / GZIPOutputStream便是这个算法的实现。
JDK deflate ——这是JDK中的又一个算法（zip文件用的就是这一算法）。它与gzip的不同之处在于，你可以指定算法的压缩级别，这样你可以在压缩时间和输出文件大小上进行平衡。可选的级别有0（不压缩），以及1(快速压缩)到9（慢速压缩）。它的实现是java.util.zip.DeflaterOutputStream / InflaterInputStream。
LZ4压缩算法的Java实现——这是本文介绍的算法中压缩速度最快的一个，与最快速的deflate相比，它的压缩的结果要略微差一点。如果想搞清楚它的工作原理，我建议你读一下这篇文章。它是基于友好的Apache 2.0许可证发布的。
Snappy——这是Google开发的一个非常流行的压缩算法，它旨在提供速度与压缩比都相对较优的压缩算法。我用来测试的是这个实现。它也是遵循Apache 2.0许可证发布的。
				 */
				// out = new DeflaterOutputStream(new BufferedOutputStream(new FileOutputStream(new File(szFileName), bApp), lnSize), new Deflater(9,true));
				// new GZIPOutputStream
				out = (new BufferedOutputStream(new FileOutputStream(new File(szFileName), bApp), lnSize));
			}
			out.write(b);
			out.flush();
		}catch(Exception e)
		{
			info(e);
		}
//		finally
//		{
//			if(null != out && null == o)
//			{
//				try
//				{
//					out.flush();
//					out.close();
//				}catch(Exception e)
//				{
//					info(e);
//				}
//			}
//		}
		return out;
	}
	/**
	 * 写文件
	 * @param szFileName
	 * @param b
	 */
	public  static void writeFile(String szFileName, byte []b, boolean bApp)
	{
		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(new File(szFileName), bApp);
			out.write(b);
		}catch(Exception e)
		{
			info(e);
		}
		finally
		{
			if(null != out)
			{
				try
				{
					out.flush();
					out.close();
				}catch(Exception e)
				{
					info(e);
				}
			}
		}
	}
	/**
	 * 输出调试信息
	 * 
	 * @param a
	 */
	public static void info(Object... a)
	{
		OutputStream out = (OutputStream) ((a[0] instanceof OutputStream) ? a[0] :System.out);
		log(out, a);
	}

	/**
	 * 输出错误信息
	 * 
	 * @param a
	 */
	public  void err(Object... a)
	{
		log(System.err, a);
	}
}
