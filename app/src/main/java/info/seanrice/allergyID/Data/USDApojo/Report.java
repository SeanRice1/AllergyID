package info.seanrice.allergyID.Data.USDApojo;

public class Report
{
    private Food food;

    private String sr;

    private String type;

    private String[] footnotes;

    public Food getFood ()
    {
        return food;
    }

    public void setFood (Food food)
    {
        this.food = food;
    }

    public String getSr ()
    {
        return sr;
    }

    public void setSr (String sr)
    {
        this.sr = sr;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String[] getFootnotes ()
    {
        return footnotes;
    }

    public void setFootnotes (String[] footnotes)
    {
        this.footnotes = footnotes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [food = "+food+", sr = "+sr+", type = "+type+", footnotes = "+footnotes+"]";
    }
}