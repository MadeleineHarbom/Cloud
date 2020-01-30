import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

public class SubscriberRunTimw {
    static class MessageReceiverExample implements MessageReceiver {
        private Subscriber subscriber;
        public void setSubscriber(Subscriber subscriber) {
            this.subscriber = subscriber;
        }
        public void receiveMessage(PubsubMessage message, AckReplyConsumer consumer) {
            System.out.println(
                               "Message Idx: " + message.getMessageId() + " Data: " + message.getData().toStringUtf8());
            // Ack only after all work for the message is complete.
              
            // System.out.println(message);
            
            consumer.ack();
              
            if(this.subscriber != null)
                subscriber.stopAsync().awaitTerminated();         
              
        }
    }

        
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        System.out.println("Hello world");
        System.out.println("Working Directory = " +
                           System.getProperty("user.dir"));
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(
                                                                              "myjanuaryproject", "mades_sub");
        Subscriber subscriber = null;
        try {
            // create a subscriber bound to the asynchronous message receiver
            //subscriber =
            //    Subscriber.newBuilder(subscriptionName, new MessageReceiverExample()).build();
              
            FileInputStream stream = new FileInputStream("/Users/made/Yule2019/lektion22_2/myjanuaryproject-0aba45e9f641.json");
          
            GoogleCredentials credentials = GoogleCredentials.fromStream(stream);
            MessageReceiverExample receiver = new MessageReceiverExample();
            subscriber = Subscriber.newBuilder(subscriptionName, receiver)
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();
          
            receiver.setSubscriber(subscriber);
          
            
            
            // subscriber.startAsync().awaitRunning();
            subscriber.startAsync().awaitRunning();
              
            // Thread.sleep(10000);
          
            // Allow the subscriber to run indefinitely unless an unrecoverable error occurs.
            // subscriber.awaitTerminated();
          
            try {
                subscriber.awaitTerminated(10000, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("EXCEPTION: " + e);
            }
              
        } catch (IllegalStateException e) {
            System.out.println("Subscriber unexpectedly stopped: " + e);
        }

        System.out.println("Program ended");
    }

}
