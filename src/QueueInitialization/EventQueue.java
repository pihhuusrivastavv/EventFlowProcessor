package QueueInitialization;
import java.util.LinkedList;
import java.util.Queue;
import ConstructorInitialization.Event;
public class EventQueue
{
    private final Queue<Event>queue =new LinkedList<>();
    private final int capacity=5;

    public synchronized void publish(Event event) throws InterruptedException
    {
        while(queue.size()==capacity)
        {
            wait();
        }
        queue.add(event);
        System.out.println("Produced Event: "+ event.getMessage());
        notifyAll();
    }
    public synchronized  Event consume() throws InterruptedException
    {
        while(queue.isEmpty())
        {
            wait();
        }
        Event event=queue.poll();
        notifyAll();
        return event;
    }
}