import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoCredential;

public class MongoC{
    String user; // the user name
    String database; // the name of the database in which the user is defined
    char[] password; // the password as a character array
    // ...


    MongoCredential credential = MongoCredential.createCredential(user, database, password);

    ClusterSettings clusterSettings = ClusterSettings.builder()
	                                  .hosts(asList(new ServerAddress("localhost"))).build();
    MongoClientSettings settings = MongoClientSettings.builder()
	                                  .clusterSettings(clusterSettings)
					                                    .credentialList(Arrays.asList(credential))
									                                      .build();
    MongoClient mongoClient = MongoClients.create(settings);
}
