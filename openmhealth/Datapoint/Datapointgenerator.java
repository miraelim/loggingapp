import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.*;
import java.lang.*;
public class Datapointgenerator {
    public static void main(String[] args) {
	RaspberrypiData data = new RaspberrypiData();
	System.out.println("{\"header\":{\"id\":\"" + data.id+ "\",\"creation_date_time\":\""+data.creationtime +"\",\"acquisition_provenance\":{\"source_name\":\""+ data.sourcename+ "\",\"source_creation_date_time\":\""+ data.sourcecreationtime + "\",\"modality\":\""+data.modality+"\"},\"user_id\":\""+data.userid+"\",\"schema_id\":{\"namespace\":\""+data.namespace+"\",\"name\":\""+data.name+"\",\"version\":\""+data.version+"\"}},\"body\":{\"effective_time_frame\":{\"data_time\":\""+data.datatime+"\"},\"systolic_blood_pressure\":{\"unit\":\""+data.unit+"\",\"value\":"+data.cpu_value+"},\"diastolic_blood_pressure\":{\"unit\":\""+data.unit+"\",\"value\":"+data.gpu_value+"}}}");
    }
}
