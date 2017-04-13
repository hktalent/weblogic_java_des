package com.mtx.xiatian.hacker.weblogic;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class RunCmd extends Thread
{
  public String command;
  public StringBuffer stdout;
/*  21 */   public StringBuffer stderr = new StringBuffer();
/*  22 */   public boolean result = false;
/*  23 */   public int exitValue = -1;
  Process proc;
  RunCmd readErr;

  RunCmd()
  {
  }

  public RunCmd(String paramString)
  {
/*  34 */     this.command = paramString;
  }

  public void run()
  {
/*  40 */     if (this.command == null) {
/*  41 */       if (this.proc == null) return;
/*  42 */       getStdErr();

/*  40 */       return;
    }

/*  43 */     this.result = runCommand(this.command);
  }
  public boolean runCommand(String paramString) {
/*  50 */     String str = null;
    Process localProcess;
    try {
/*  53 */       localProcess = Runtime.getRuntime().exec(paramString);
    } catch (Exception localException1) {
/*  55 */       this.stderr.append("Command did not execute: " + paramString + " \nException: " + localException1);
/*  56 */       return false;
    }

/*  59 */     this.readErr = new RunCmd();
/*  60 */     this.proc = localProcess;
/*  61 */     this.readErr.proc = localProcess;
/*  62 */     this.readErr.stderr = this.stderr;
/*  63 */     this.readErr.start();

/*  65 */     BufferedInputStream localBufferedInputStream = new BufferedInputStream(localProcess.getInputStream());
/*  66 */     DataInputStream localDataInputStream = new DataInputStream(localBufferedInputStream);
/*  67 */     this.stdout = new StringBuffer();
    try
    {
/*  70 */       str = localDataInputStream.readLine();
/*  71 */       while (str != null) {
/*  72 */         this.stdout.append(str + "\n");
        try { Thread.sleep(1L); } catch (Exception localException2) {
/*  74 */         }if (localBufferedInputStream.available() <= 0) try { localProcess.exitValue(); } catch (IllegalThreadStateException localIllegalThreadStateException) { continue; }
        else
/*  76 */           str = localDataInputStream.readLine();
      }
    } catch (IOException localIOException) {
/*  79 */       if (!System.getProperty("os.name").equals("Solaris"))
      {
/*  81 */         this.stderr.append("Error running command: " + paramString + " : " + localIOException);
/*  82 */         return false;
      }
    }
/*  85 */     return (this.exitValue = localProcess.exitValue()) == 0;
  }

  public StringBuffer getError()
  {
/*  91 */     return this.stderr;
  }

  public boolean getStdErr()
  {
/*  96 */     String str = null;

/*  98 */     DataInputStream localDataInputStream = new DataInputStream(
/*  99 */       new BufferedInputStream(this.proc.getErrorStream()));
    try
    {
/* 102 */       str = localDataInputStream.readLine();
/* 103 */       while (str != null) {
/* 104 */         this.stderr.append(str + "\n");
/* 105 */         str = localDataInputStream.readLine();
      }
    } catch (IOException localIOException) {
/* 108 */       if (!System.getProperty("os.name").equals("Solaris"))
      {
/* 110 */         this.stderr.append("Error running command: " + this.command + " : " + localIOException);
/* 111 */         return false;
      }
    }
/* 114 */     return true;
  }

  public void stopCommand()
  {
/* 119 */     stop();
/* 120 */     if (this.readErr != null) this.readErr.stop();
/* 121 */     if (this.proc != null) this.proc.destroy();
  }
}

/* Location:           \\psf\Home\\Documents\安全工作\远程执行漏洞工具包\WebLogic_EXP.jar
 * Qualified Name:     com.adventnet.utils.agent.RunCmd
 * JD-Core Version:    0.6.0
 */