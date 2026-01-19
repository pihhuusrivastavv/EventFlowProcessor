package ProducerQueue;
import  ConstructorInitialization.Event;
import QueueInitialization.EventQueue;
public class EventProducer extends Thread
{
    private final EventQueue queue;

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
                queue.publish(new Event(i,"Event- "+i));//waits if full, and then adds the event
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}