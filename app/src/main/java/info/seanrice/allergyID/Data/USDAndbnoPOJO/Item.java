package info.seanrice.allergyID.Data.USDAndbnoPOJO;

public class Item
{
    private String name;

    private String ndbno;

    private String group;

    private String offset;

    private String ds;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getNdbno ()
    {
        return ndbno;
    }

    public void setNdbno (String ndbno)
    {
        this.ndbno = ndbno;
    }

    public String getGroup ()
    {
        return group;
    }

    public void setGroup (String group)
    {
        this.group = group;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
    }

    public String getDs ()
    {
        return ds;
    }

    public void setDs (String ds)
    {
        this.ds = ds;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", ndbno = "+ndbno+", group = "+group+", offset = "+offset+", ds = "+ds+"]";
    }
}