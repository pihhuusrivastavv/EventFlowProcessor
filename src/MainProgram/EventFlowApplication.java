package MainProgram;
import QueueInitialization.EventQueue;
import ProducerQueue.EventProducer;
import ConsumerQueue.EventConsumer;

public class EventFlowApplication
{
    public static void main(String[]args) throws InterruptedException
    {
        EventQueue queue=new EventQueue();

        EventProducer producer=new EventProducer(queue);
        EventConsumer consumer1=new EventConsumer(queue);
        EventConsumer consumer2=new EventConsumer(queue);

        producer.start();
        consumer1.start();
        consumer2.start();

        producer.join();
        queue.shutDownQueue();
        consumer1.join();
        consumer2.join();

        System.out.println("All events done. Application is now exiting.");

    }
}