package ConstructorInitialization;

public class Event
{
    private final int id;
    private final String msg;

    public Event(int id, String msg)
    {
        this.id=id;
        this.msg=msg;
    }
    public int getId()
    {
        return id;
    }
    public String getMessage()
    {
        return msg;
    }
}