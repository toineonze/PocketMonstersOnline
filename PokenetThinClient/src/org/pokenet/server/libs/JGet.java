package org.pokenet.server.libs;

/*
 * simple class to provide functionality similar to Wget.
 *
 * Note: Could also strip out all of the html w/ jtidy.
 */

import java.io.*;
import java.net.*;

public class JGet
{

	public static void getFile(String input, String output) throws MalformedURLException, IOException
	{
		URL u = new URL(input);
		InputStream is = u.openStream();
		new File("download/").mkdir();
		File f = new File("download/"+output);

		OutputStream out = new FileOutputStream(f);
		byte buf[] = new byte[1024];
		int len;
		while((len = is.read(buf)) >0)
			out.write(buf,0,len);
		out.close();
		is.close();

	}

}
