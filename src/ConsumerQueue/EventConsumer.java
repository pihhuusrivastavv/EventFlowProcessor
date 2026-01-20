package ConsumerQueue;
import ConstructorInitialization.Event;
import QueueInitialization.EventQueue;
import java.util.logging.Logger;
import java.util.Random;

public class EventConsumer extends Thread
{

    private final EventQueue queue;
    private final Random random=new Random();
    private static final int max_retries=3;
    private static final Logger logger=Logger.getLogger(EventConsumer.class.getName());
    public EventConsumer(EventQueue queue)
    {
        this.queue=queue;
    }
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                Event event = queue.consume();//consumer asks queue for event
                if(event==null)
                    break;//exists is queue is shutdown
                int attempt=0;
                boolean success=false;
                while(attempt<max_retries && !success) {
                    try {
                        if (random.nextInt(5) == 0)//20% error
                            throw new RuntimeException("Random error...");

                        logger.info(Thread.currentThread().getName() + " processed " + event.getType() + ":" + event.getMessage());
                        success = true;
                    }
                catch(Exception e)
                        {
                            attempt++;
                            logger.warning(Thread.currentThread().getName() + " failed processing " + event.getType() + ":" + "| attempt " + attempt);

                            if (attempt == max_retries) {
                                logger.severe(Thread.currentThread().getName() + " is now being dropped" + event.getType() + ":" + event.getMessage());
                            }
                        }
                    }
                Thread.sleep(500);
            }
            logger.info(Thread.currentThread().getName()+ " exited gracefully.");
        }
        catch(InterruptedException e)
        {
            logger.info(Thread.currentThread().getName()+ " interrupted, exiting. ");
        }
    }

}