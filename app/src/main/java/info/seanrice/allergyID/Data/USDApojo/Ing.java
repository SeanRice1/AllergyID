package info.seanrice.allergyID.Data.USDApojo;

public class Ing
{
    private String desc;

    private String upd;

    public String getDesc ()
    {
        return desc;
    }

    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    public String getUpd ()
    {
        return upd;
    }

    public void setUpd (String upd)
    {
        this.upd = upd;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [desc = "+desc+", upd = "+upd+"]";
    }
}