package info.seanrice.allergyID.Data.USDApojo;

public class Food
{
    private Nutrients[] nutrients;

    private String manu;

    private String name;

    private String ndbno;

    private Ing ing;

    private String ru;

    private String ds;

    public Nutrients[] getNutrients ()
    {
        return nutrients;
    }

    public void setNutrients (Nutrients[] nutrients)
    {
        this.nutrients = nutrients;
    }

    public String getManu ()
    {
        return manu;
    }

    public void setManu (String manu)
    {
        this.manu = manu;
    }

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

    public Ing getIng ()
    {
        return ing;
    }

    public void setIng (Ing ing)
    {
        this.ing = ing;
    }

    public String getRu ()
    {
        return ru;
    }

    public void setRu (String ru)
    {
        this.ru = ru;
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
        return "ClassPojo [nutrients = "+nutrients+", manu = "+manu+", name = "+name+", ndbno = "+ndbno+", ing = "+ing+", ru = "+ru+", ds = "+ds+"]";
    }
}

