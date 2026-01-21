package FileStorage;
import ConstructorInitialization.Event;
import ConstructorInitialization.EventType;
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
    private final Set<Integer> passedEventIds= new HashSet<>();

    public void loadEvents(EventQueue queue)
    {
        try(BufferedReader reader=new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line=reader.readLine())!=null)
            {
                Event event= parseEvent(line);
                if(passedEventIds.contains(event.getId()))
                    continue;
                queue.publish(event);
                passedEventIds.add(event.getId());
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
        int id=Integer.parseInt(part[0]);
        String msg= part[1];
        EventType type=EventType.INFO;

        return new Event(id,msg,type);
    }
}
