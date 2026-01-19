package ProducerQueue;
import  ConstructorInitialization.Event;
import ConstructorInitialization.EventType;
import QueueInitialization.EventQueue;
import java.util.Random;
public class EventProducer extends Thread
{
    private final EventQueue queue;
    private final Random random= new Random();

    public EventProducer(EventQueue queue)
    {
        this.queue=queue;
    }
    public void run()
    {
        try
        {
            for(int i=1;i<=10;i++)
            {
                EventType type = EventType.values()[random.nextInt(EventType.values().length)];
                Event event =new Event(i," Event- "+i,type);//waits if full and then add event with id, message,and random type
                queue.publish(event);
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}