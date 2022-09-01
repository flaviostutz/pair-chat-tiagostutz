/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.protocol;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import chat.Util;


/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MessagePart {
	
	byte[] contents;

	public MessagePart(String contents) {
		if(contents!=null) this.contents = contents.getBytes(); 
		else this.contents = new byte[0];

	}
	public MessagePart(boolean bool) {
		this((bool+"").getBytes());
	}
	
	public MessagePart(byte[] contents) {
		this.contents = contents;
	}

	public byte[] encode() throws IOException {
		ByteArrayOutputStream size = new ByteArrayOutputStream(Message.SIZE_BYTES);
		ByteArrayOutputStream partReturn = new ByteArrayOutputStream(Message.SIZE_BYTES + contents.length);
		size.write((contents.length+"").getBytes());

		partReturn.write(Util.fillZeroes((size+"").getBytes(), Message.SIZE_BYTES));//tamanho do atributo
		partReturn.write(contents);//conteúdo do atributo
		return partReturn.toByteArray();
	}
	
	
	/**
	 * @return Returns the contents.
	 */
	public byte[] getContents() {
		return contents;
	}
	
	public String toString() {
		return new String(contents);
	}
}
