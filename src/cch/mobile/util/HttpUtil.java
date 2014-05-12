package cch.mobile.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

public class HttpUtil {

	public static String response2String(HttpResponse response) throws IllegalStateException, IOException
	{
		InputStream inputstream = response.getEntity().getContent();
		byte[] b = new byte[1024];
		int i = 0,j = 0;
		int tmp = inputstream.read();
		while (tmp!=-1&&j!=1024) {
			b[j++] = (byte)tmp;
			tmp = inputstream.read();
		}
		byte[] b1 = new byte[j];
		while(i<j){
			b1[i] = b[i];
			i++;
		}
		String result = new String(b1,"UTF-8");
		inputstream.close();
		return result;
	}
}
