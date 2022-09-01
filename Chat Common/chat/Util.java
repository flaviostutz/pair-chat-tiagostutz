/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import java.io.IOException;
import java.util.Arrays;


/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Util {

	public static int DEFAULT_SERVER_PORT = 2048;
	
	public static int PING_TIMEOUT =  30000;
	public static int PING_INTERVAL = 15000;
	public static int THREAD_SLEEP = 100; //agents thread
	
	public static byte[] fillZeroes(byte[] value, int length) throws IOException {
		byte[] r = new byte[length];
		Arrays.fill(r, Byte.parseByte("48"));
		if(length<value.length) throw new IOException("fillZeroes: value.length > length");
		
		for(int i=0; i<value.length; i++) {
			r[(length-value.length)+i] = value[i];
		}
				
		return r;
	}
	
}
