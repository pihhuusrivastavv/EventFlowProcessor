package FileStorage;
import ConstructorInitialization.Event;
import java.io.IOException;
import java.io.FileWriter;

public class EventFileInformationStore {
    private final String File_Name = "events_info";
    private final int max_retries = 3;

    public synchronized boolean persist(Event event) {
        int attempt = 0;
        while (attempt < max_retries)
        {
            try (FileWriter writer = new FileWriter(File_Name, true))
            {
                writer.write(event.toString());
                writer.write(System.lineSeparator());
                return true;
            }
            catch (IOException e)
            {
                attempt++;
                System.out.println("Failed to persist the event "+event+"| for attempt-"+attempt);
            }
        }
        return false;
    }
}
