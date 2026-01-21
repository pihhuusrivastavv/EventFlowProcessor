package QueueInitialization;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import ConstructorInitialization.Event;
import java.util.logging.Logger;
public class EventQueue
{
    private final Queue<Event>queue =new LinkedList<>();
    private final int capacity=5;
    private boolean shutdown=false;//shutdown
    private static final Logger logger=Logger.getLogger(EventQueue.class.getName());

    public synchronized void publish(Event event) throws InterruptedException //producer adds event to queue
    {
        while(queue.size()==capacity)
        {
            wait();//producer waits
        }
        queue.add(event);
        logger.info("Published event: "+ event.getMessage());
        notifyAll();
    }
    public synchronized  Event consume() throws InterruptedException //consumer removes event from queue
    {
        while(queue.isEmpty() && !shutdown)
        {
            wait();//Consumer waits if queue is empty and shutdown
        }
        if(queue.isEmpty() && shutdown)
        {
            return null;//signals consumer thread to exit
        }
        Event event=queue.poll();//removes the head of the queue
        notifyAll();
        return event;
    }
    public synchronized void shutDownQueue()
    {
        shutdown=true;
        notifyAll();//consumers wakes up
    }
}