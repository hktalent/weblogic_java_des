package com.mtx.xiatian.hacker.weblogic;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RyClient
{
  public static final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
  int port;
  String host;

  public static InitialContext getInitialContext(String url)
    throws NamingException
  {
/* 24 */     Hashtable env = new Hashtable();
/* 25 */     env.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
/* 26 */     env.put("java.naming.provider.url", url);
/* 27 */     return new InitialContext(env);
  }
}
