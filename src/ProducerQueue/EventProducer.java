package ProducerQueue;
import  ConstructorInitialization.Event;
import ConstructorInitialization.EventType;
import QueueInitialization.EventQueue;
import java.util.Random;
public class EventProducer extends Thread
{
    private final EventQueue queue;
    private final Random random= new Random();
    private int eventCounter=1;

    public EventProducer(EventQueue queue)
    {
        this.queue=queue;
    }
    @Override
    public void run()
    {
        try
        {
            while(eventCounter<=10)
            {
                EventType type = EventType.values()[random.nextInt(EventType.values().length)];
                Event event =new Event(eventCounter," Event- "+eventCounter,type);//waits if full and then add event with id, message,and random type
                queue.publish(event);
                eventCounter++;
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