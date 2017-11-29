package info.seanrice.allergyID.Data.USDAndbnoPOJO;

public class List
{
    private String total;

    private String sort;

    private String start;

    private String sr;

    private String q;

    private Item[] item;

    private String group;

    private String end;

    private String ds;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getSort ()
    {
        return sort;
    }

    public void setSort (String sort)
    {
        this.sort = sort;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    public String getSr ()
    {
        return sr;
    }

    public void setSr (String sr)
    {
        this.sr = sr;
    }

    public String getQ ()
    {
        return q;
    }

    public void setQ (String q)
    {
        this.q = q;
    }

    public Item[] getItem ()
    {
        return item;
    }

    public void setItem (Item[] item)
    {
        this.item = item;
    }

    public String getGroup ()
    {
        return group;
    }

    public void setGroup (String group)
    {
        this.group = group;
    }

    public String getEnd ()
    {
        return end;
    }

    public void setEnd (String end)
    {
        this.end = end;
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
        return "ClassPojo [total = "+total+", sort = "+sort+", start = "+start+", sr = "+sr+", q = "+q+", item = "+item+", group = "+group+", end = "+end+", ds = "+ds+"]";
    }
}