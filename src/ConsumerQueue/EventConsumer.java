package ConsumerQueue;
import ConstructorInitialization.Event;
import QueueInitialization.EventQueue;
public class EventConsumer extends Thread
{
    private final EventQueue queue;
    public EventConsumer(EventQueue queue)
    {
        this.queue=queue;
    }
    public void run()
    {
        try
        {
            while(true)
            {
                Event event = queue.consume();//consumer asks queue for event
                if(event==null)
                    break;//exists is queue is shutdown
                System.out.println(Thread.currentThread().getName()+" processed "+event.getType()+":"+event.getMessage());
                Thread.sleep(500);
            }
            System.out.println(Thread.currentThread().getName()+ " exited gracefully.");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}