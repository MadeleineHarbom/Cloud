

import java.io.IOException;
import java.io.FileInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.List;

/**
 * Servlet implementation class HelloAppEngineServlet
 */
@WebServlet(name = "HelloAppEngine", urlPatterns = {"/hello"})
public class HelloAppEngineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        response.getWriter().print("Hello App Engine!\r\n");

        try {
            sendMessage();
            response.getWriter().print("Message sent.\r\n");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            response.getWriter().print(e.getMessage());
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            //7e.printStackTrace();
            response.getWriter().print(e.getMessage());
        }
        
    }
  
    public void sendMessage() throws IOException, InterruptedException, ExecutionException {
        String topicId = "MJ_DEMO_TOPIC";
          
        ProjectTopicName topicName = ProjectTopicName.of("ultimate-life-237105", topicId);
        
        Publisher publisher = null;
        List<ApiFuture<String>> futures = new ArrayList<>();

        try {
            // publisher = Publisher.newBuilder(topicName).build();

            // FileInputStream stream = new FileInputStream("credentials/mjtest-ultimate-life-237105-72ccd2d04951.json");
            FileInputStream stream = new FileInputStream("credentials/mjowner-ultimate-life-237105-8c7a42e1875f.json");
            
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
            publisher = Publisher
                .newBuilder(topicName)
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();
          
            // convert message to bytes
            ByteString data = ByteString.copyFromUtf8("mj-message-1");
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(data)
                .build();

            // Schedule a message to be published. Messages are automatically batched.
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            futures.add(future);
        } finally {
            // Wait on any pending requests
            List<String> messageIds = ApiFutures.allAsList(futures).get();
                  
            for (String messageId : messageIds) {
                System.out.println(messageId);
            }
            
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
            }
        }
    }

}