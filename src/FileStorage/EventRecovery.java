package FileStorage;
import ConstructorInitialization.Event;
import ConstructorInitialization.EventType;
import QueueInitialization.ConfirmedEventStore;
import QueueInitialization.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class EventRecovery
{
    private final String file="events_info";
    private final Logger logger =Logger.getLogger(EventRecovery.class.getName());
    public void loadEvents(EventQueue queue, ConfirmedEventStore confirmStore)
    {
        try(BufferedReader reader=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=reader.readLine())!=null)
            {
                Event event= parseEvent(line);
                if(confirmStore.isConfirmed(event.getId()))
                {
                    logger.info("Skipping Confirmed Event "+event.getId());
                    continue;
                }
                queue.publish(event);
                logger.info("Recovered event: "+event.getId());
            }
        }
        catch(IOException  | InterruptedException e)
        {
            logger.severe("Recovery event: "+e.getMessage());

        }
    }
    private Event parseEvent(String line)
    {
        String[] part=line.split("\\|");
        EventType type=EventType.valueOf(part[1].trim());
        int id=Integer.parseInt(part[2].trim());
        String msg= part[3].trim();

        return new Event(id,msg,type);
    }
}
