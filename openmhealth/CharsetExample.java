import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.*;
import java.lang.*;
public class CharsetExample {
    public static void main(String[] args) {
	Charset charset=Charset.forName("US-ASCII");
	System.out.println(charset.displayName());
	System.out.println(charset.canEncode());
	String s= "Hello, This is Charset Example.\n";

	//convert byte buffer in given charset to char buffer in unicode
	ByteBuffer bb = ByteBuffer.wrap(s.getBytes());
	CharBuffer cb = charset.decode(bb);



	System.out.print("Byte:");
	ByteOrder od=ByteOrder.LITTLE_ENDIAN;
	bb.order(od);
	bb.flip();
	System.out.println(bb.getInt());

	//convert char buffer in unicode to byte buffer in given charset
	ByteBuffer newbb = charset.encode(cb);
	while(newbb.hasRemaining()){
	    char ch = (char) newbb.get();
	    System.out.print(ch);
	}
	System.out.print("Result Byte:");
	newbb.order(od);
	newbb.flip();
	System.out.println(newbb.getInt());

	newbb.clear();
    }
}
