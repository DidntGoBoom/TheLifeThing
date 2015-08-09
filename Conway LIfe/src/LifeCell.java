/**
 * Write a description of class LifeCell here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeCell
{
    // instance variables - replace the example below with your own
    private boolean isOn;
    static int counter=0;

    /**
     * Constructor for objects of class LifeCell
     */
    public LifeCell()
    {
    }
    
    public void setContents(int thresh)
    {
        isOn = Math.random()*100.0 > thresh;
    }
    
    public void setContents(boolean on)
    {
        isOn = on;
    }
    
    public void clearContents()
    {
        isOn = false;
    }
    
    public void setOn() 
    {
        isOn = true;
    }
    
    public void set(boolean on) 
    {
        isOn = on;
    }
    
    public void setOff() 
    {
        isOn = false;
    }
    
    public boolean isOn()
    {
        return isOn;
    }
}
