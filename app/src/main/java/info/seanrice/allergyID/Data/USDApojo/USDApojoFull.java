package info.seanrice.allergyID.Data.USDApojo;

public class USDApojoFull
{
    private Report report;

    public Report getReport ()
    {
        return report;
    }

    public void setReport (Report report)
    {
        this.report = report;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [report = "+report+"]";
    }
}