package StorageEvents;
import ConstructorInitialization.Event;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.logging.Logger;
public class EventStore
{
    private static final String file="event-store.log";
    private final Logger logger=Logger.getLogger(EventStore.class.getName());
    public synchronized void store(Event event)
    {
        try(FileWriter writer=new FileWriter(file))
        {
            String record= LocalDateTime.now()+" | "+event.getId()+" | "+event.getType()+" | "+event.getMessage()+"\n";
            writer.write(record);
        }
        catch(IOException e)
        {
            logger.severe("Failed to submit a confirmed event");
        }
    }
}
