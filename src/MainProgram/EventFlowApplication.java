package MainProgram;
import QueueInitialization.EventQueue;
import ProducerQueue.EventProducer;
import ConsumerQueue.EventConsumer;
import java.util.logging.Logger;

public class EventFlowApplication
{
    private final static Logger logger= Logger.getLogger(EventFlowApplication.class.getName());
    public static void main(String[]args) throws InterruptedException
    {
        EventQueue queue=new EventQueue();

        EventProducer producer=new EventProducer(queue);
        EventConsumer consumer1=new EventConsumer(queue);
        EventConsumer consumer2=new EventConsumer(queue);

        producer.setName("Producer");
        consumer1.setName("Consumer-1");
        consumer2.setName("Consumer-2");

        consumer1.setDaemon(true);

        Runtime.getRuntime().addShutdownHook((new Thread(()->
        {
            System.out.println("Shutdown initiated..");
            producer.interrupt();
            queue.shutDownQueue();
        })));

        producer.start();
        consumer1.start();
        consumer2.start();

        producer.join();
        queue.shutDownQueue();
        consumer1.join();

        logger.info("All events done. Application is now exiting.");

    }
}