package ProducerQueue;
import  ConstructorInitialization.Event;
import ConstructorInitialization.EventType;
import QueueInitialization.EventQueue;
import java.util.Random;
import FileStorage.EventFileInformationStore;
public class EventProducer extends Thread
{
    private final EventQueue queue;
    private final Random random= new Random();
    private int eventNumber=1;
    private final EventFileInformationStore file_info;

    public EventProducer(EventQueue queue)
    {
        this.queue=queue;
        this.file_info=new EventFileInformationStore();
    }
    @Override
    public void run()
    {
        try
        {
            while(eventNumber<=10)
            {
                EventType type = EventType.values()[random.nextInt(EventType.values().length)];
                Event event =new Event(eventNumber," Event- "+eventNumber,type);//waits if full and then add event with id, message,and random type
                if(file_info.persist(event))//persist first for durability
                {
                    queue.publish(event);
                }
                else
                {
                    System.out.println("Event Dropped due to persistence: "+event);
                }
                eventNumber++;
                Thread.sleep(500);
            }
            queue.shutDownQueue();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}