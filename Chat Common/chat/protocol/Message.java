/*
 * Created on 24/02/2004
 *
 */
package chat.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import chat.Util;

/**
 * @author Tiago
 *
 */
public class Message {
	
	/** FUNCTIONS */
					/** NAME */ 				/**	PARAMETER ORDER */
	public static int FUNCTION_PING = 1; 		/** NO PARAM */	
	public static int FUNCTION_SEND_SERVER = 2; /** message, destinationNick, privateMsg */
	public static int FUNCTION_SEND_CLIENT = 3; /** message, senderNick, receiverNick, private */
	public static int FUNCTION_GET_USERS = 4; 	/** NO PARAM */
	public static int FUNCTION_CONNECT = 5; 	/** nick */
	
	/** RESPONSES */
					/** NAME */ 				/**	ATTRIBUTE ORDER */
	public static int RESPONSE_GET_USERS = 6; 	/** nick1, nick2, nick3, nick4... */
	public static int RESPONSE_PING = 7; 		/** NO ATTRIBUTE */
	
	
	public static int SIZE_BYTES = 6;
	public static int SIZE_FUNCTION = 1;
	
	int function;
	Collection parts;
	byte[] contents;
	
	public Message(int function) {
		parts = new ArrayList();
		this.function = function;
	}

	public Message(byte[] contents) throws NumberFormatException, IOException {
		parts = new ArrayList();
		decode(contents);
	}

	public void decode(byte[] contents) throws NumberFormatException, IOException {
		ByteArrayInputStream is = new ByteArrayInputStream(contents);

		//get the function value
		function = is.read();
		
		byte[] bodySizeB = new byte[SIZE_BYTES];
		
		if(is.available()>0)
		if(is.available()>=SIZE_BYTES) {
			
			while(is.available()>0) {
				//read the part size
				is.read(bodySizeB);
				int partSize = Integer.parseInt(new String(bodySizeB));
				
				//part contents
				byte[] partContents = new byte[partSize];
				is.read(partContents);
	
				//add part to this message
				MessagePart mp = new MessagePart(partContents);
				addPart(mp);
			}
			
		} else {
			throw new IOException("The stream size is less than " + SIZE_BYTES + " bytes long (this is the part size)");
		}
		
	}
	

	public byte[] encode() throws IOException {

		//carregar o conteúdo total das partes
		ByteArrayOutputStream partsStream = new ByteArrayOutputStream();
		Iterator i = parts.iterator();
		while(i.hasNext()) { MessagePart part = (MessagePart)i.next();
			partsStream.write(part.encode());
		}

		//definir o tamanho total da mensagem
		int size = SIZE_FUNCTION+partsStream.size();

		ByteArrayOutputStream messageStream = new ByteArrayOutputStream();
		messageStream.write(Util.fillZeroes((size+"").getBytes(), SIZE_BYTES));//tamanho total
		messageStream.write(function);			//function
		messageStream.write(partsStream.toByteArray());//conteúdo das partes

		return messageStream.toByteArray();
	}


	
	public void addPart(MessagePart part) {
		parts.add(part);
	}
	
	public void addPart(String part) {
		parts.add(new MessagePart(part));
	}
	
	public void addPart(byte[] part) {
		parts.add(new MessagePart(part));
	}
	
	public void addPart(boolean part) {
		parts.add(new MessagePart(part));
	}
	
	/**
	 * @return Returns the function.
	 */
	public int getFunction() {
		return function;
	}
	/**
	 * @return Returns the parts.
	 */
	public Collection getParts() {
		return parts;
	}

	public MessagePart[] getArrayParts() {
		return (MessagePart[])parts.toArray(new MessagePart[0]);
	}
	
	public String toString() {
		return "function==" + function + ", parts==" + parts; 
	}
}
