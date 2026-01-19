package ConstructorInitialization;

public class Event
{
    private final int id;
    private final String msg;
    private final EventType type;

    public Event(int id, String msg,EventType type)
    {
        this.id=id;
        this.msg=msg;
        this.type=type;
    }
    public int getId()
    {
        return id;
    }
    public String getMessage()
    {
        return msg;
    }
    public EventType getType()
    {
        return type;
    }
}