package ee.ut.math.tvt.salessystem.ui.panels;

public class ComboItem
{
    private Long key;
    private String value;

    public ComboItem(Long long1, String value)
    {
        this.key = long1;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
    
    public Long getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}