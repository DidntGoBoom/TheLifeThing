import java.io.*;

/**
 * Write a description of class LifeDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeDriver
{
    /**
     * Constructor for objects of class LifeDriver
     */
    public int threshhold=50;
    
    public LifeDriver() throws InterruptedException
    {
        ldInit();
    }

    public LifeDriver(int thresh) throws InterruptedException
    {
        threshhold = thresh;
        ldInit();
    }
    
    public LifeDriver(int dim, String filename) throws FileNotFoundException, IOException, InterruptedException
    {
        // initialise instance variables
        File inFile = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(inFile));
        
        String line;
        boolean x[][] = new boolean[dim][dim];
//        System.out.println("");
        char inChars[];

//        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

        for( int ii=0; ii<dim; ii++ )
        {
            line=buffer.readLine();
//            System.out.println("**"+line+"**");
            inChars = new char[line.length()];
            line.getChars(0, line.length(), inChars, 0);
            for( int jj=0; jj<line.length() && jj < dim; jj++ )
            {
                if( inChars[jj] == '1' )
                    x[ii][jj] = true;
                else
                    x[ii][jj] = false;
//                System.out.print(x[ii][jj]);
            }
//            System.out.println("");
        }
        buffer.close();
        LifeGrid lg = new LifeGrid(dim);
        lg.populate(x);
        lg.run();
    }
    
    
    public LifeDriver(int dim, boolean readme) throws IOException, InterruptedException
    {
        // initialise instance variables
        
        boolean x[][] = new boolean[dim][dim];
        System.out.println("");
        char inChars[];
        String line;
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

        for( int ii=0; ii<dim; ii++ )
        {
            line=buffer.readLine();
//            System.out.println("**"+line+"**");
            inChars = new char[line.length()];
            line.getChars(0, line.length(), inChars, 0);
            for( int jj=0; jj<line.length() && jj < dim; jj++ )
            {
                if( inChars[jj] == '1' )
                    x[ii][jj] = true;
                else
                    x[ii][jj] = false;
//                System.out.print(x[ii][jj]);
            }
//            System.out.println("");
        }
        LifeGrid lg = new LifeGrid(dim);
        lg.populate(x);
        lg.run();
    }
    
    
    private void ldInit()throws InterruptedException
    {
        // initialise instance variables
        LifeGrid lg = new LifeGrid(50);
        lg.populate(50);
        lg.run();
    }

    static public void main(String[] argv)
    {
    	try
    	{
    		new LifeDriver(17, "pulsar.txt");
    	}
    	catch( Exception e)
    	{
    		
    	}
        
    }

}
