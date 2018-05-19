import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author falbb
 *
 */
public class IntByteConvert {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub

	int testInt = 1025;

	System.out.println("===== ByteOrder.LITTLE_ENDIAN  ===");
	byte[] bytes = intTobyte(testInt, ByteOrder.LITTLE_ENDIAN);

	for (int i = 0; i < bytes.length; i++) {
	    System.out.printf("[%02X]", bytes[i]);
	}
	System.out.println();
	System.out.println(byteToInt(bytes, ByteOrder.LITTLE_ENDIAN));

	System.out.println();
	System.out.println("===== ByteOrder.BIG_ENDIAN  ===");
	bytes = intTobyte(testInt, ByteOrder.BIG_ENDIAN);

	for (int i = 0; i < bytes.length; i++) {
	    System.out.printf("[%02X]", bytes[i]);
	}
	System.out.println();
	System.out.println(byteToInt(bytes, ByteOrder.BIG_ENDIAN));
    }


    public static byte[] intTobyte(int integer, ByteOrder order) {

	ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
	buff.order(order);

	// 인수로 넘어온 integer을 putInt로설정
	buff.putInt(integer);

	System.out.println("intTobyte : " + buff);
	return buff.array();
    }
    public static int byteToInt(byte[] bytes, ByteOrder order) {

	ByteBuffer buff = ByteBuffer.allocate(Integer.SIZE/8);
	buff.order(order);

	// buff사이즈는 4인 상태임
	// bytes를 put하면 position과 limit는 같은 위치가 됨.
	buff.put(bytes);
	// flip()가 실행 되면 position은 0에 위치 하게 됨.
	buff.flip();

	System.out.println("byteToInt : " + buff);

	return buff.getInt(); // position위치(0)에서 부터 4바이트를 int로 변경하여 반환
    }
}
