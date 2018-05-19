import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.*;
import java.lang.*;
import java.io.*;



public class Datapointgenerator1 {
    public static void main(String[] args) {
	RaspberrypiData data = new RaspberrypiData();

	try{
	    //파일 읽기
	    File file = new File("temperature.txt");
	    FileReader filereader = new FileReader(file);
	    BufferedReader in = new BufferedReader(filereader);

	    String s;
	    while((s = in.readLine()) != null){
		String[] split = s.split("=|,");
		data.id = split[5];
		data.creationtime = split[7];
		data.sourcecreationtime= split[7];
		data.datatime=split[7];
		split[1]=split[1].replace("\'C","");
		data.cpu_value = Double.parseDouble(split[1]);
		split[3]=split[3].replace("\'C","");
		data.gpu_value = Double.parseDouble(split[3]);

		System.out.println("{\"header\":{\"id\":\"" + data.id+ "\",\"creation_date_time\":\""+data.creationtime +"\",\"acquisition_provenance\":{\"source_name\":\""+ data.sourcename+ "\",\"source_creation_date_time\":\""+ data.sourcecreationtime + "\",\"modality\":\""+data.modality+"\"},\"user_id\":\""+data.userid+"\",\"schema_id\":{\"namespace\":\""+data.namespace+"\",\"name\":\""+data.name+"\",\"version\":\""+data.version+"\"}},\"body\":{\"effective_time_frame\":{\"data_time\":\""+data.datatime+"\"},\"Raspberrypi_CPU_VALUE\":{\"unit\":\""+data.unit+"\",\"value\":"+data.cpu_value+"},\"Raspberrypi_GPU_VALUE\":{\"unit\":\""+data.unit+"\",\"value\":"+data.gpu_value+"}}}");

	    }
	    filereader.close();
	}catch (FileNotFoundException e){
	    System.out.println(e);
	}
	catch(IOException e){
	    System.out.println(e);
	}


	//	System.out.println("{\"header\":{\"id\":\"" + data.id+ "\",\"creation_date_time\":\""+data.creationtime +"\",\"acquisition_provenance\":{\"source_name\":\""+ data.sourcename+ "\",\"source_creation_date_time\":\""+ data.sourcecreationtime + "\",\"modality\":\""+data.modality+"\"},\"user_id\":\""+data.userid+"\",\"schema_id\":{\"namespace\":\""+data.namespace+"\",\"name\":\""+data.name+"\",\"version\":\""+data.version+"\"}},\"body\":{\"effective_time_frame\":{\"data_time\":\""+data.datatime+"\"},\"Raspberrypi_CPU_VALUE\":{\"unit\":\""+data.unit+"\",\"value\":"+data.cpu_value+"},\"Raspberrypi_GPU_VALUE\":{\"unit\":\""+data.unit+"\",\"value\":"+data.gpu_value+"}}}");
    }
}
