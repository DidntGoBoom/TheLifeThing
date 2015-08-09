import javax.swing.*;

import java.awt.*;

/**
 * Write a description of class LifeGrid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeGrid
{
    // instance variables - replace the example below with your own
    private int gridDim;
    private int cellSize=8;
    private boolean debug=false;
    LifeCell[][] cellsBefore;
    LifeCell[][] cellsAfter;
    JFrame frame = new JFrame(); // (500,500,0);
    JPanel paper = new JPanel();

    Graphics pen;
    
    private void debStr(Object o)
    {
    	if( debug )
    	{
    		System.out.print(o);
    	}
    }

    /**
     * Constructor for objects of class LifeGrid
     */
    public LifeGrid()
    {
        // initialise instance variables
        this(100);
    }
    
    public LifeGrid(int dim)
    {
        gridDim = dim+2;
        cellsBefore = new LifeCell[gridDim][gridDim];
        cellsAfter = new LifeCell[gridDim][gridDim];
        
        paper.setPreferredSize(new Dimension(500, 500));
        frame.add(paper);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setupGrid();
    }
    
    public void switchGrid()
    {
        LifeCell[][] cellsHolder;
        cellsHolder = cellsBefore;
        cellsBefore = cellsAfter;
        cellsAfter = cellsHolder;
        resetCells();
    }
    
    private void setupGrid()
    {
        for( int ii=0; ii<gridDim; ii++ )
        {
            for( int jj=0; jj<gridDim; jj++ )
            {
                cellsBefore[ii][jj] = new LifeCell();
                cellsAfter[ii][jj] = new LifeCell();
            }
        }
    }
    
    public void populate()
    {
        populate(50);
    }
    
    public void populate(int threshhold)
    {
        for( int ii=0; ii<gridDim; ii++ )
        {
            for( int jj=0; jj<gridDim; jj++ )
            {
                cellsBefore[ii][jj].setContents(threshhold);
            }
        }
    }
    
    public void populate(boolean[][] cellArray)
    {
        for( int ii=1; ii<gridDim-1; ii++ )
        {
            for( int jj=1; jj<gridDim-1; jj++ )
            {
                cellsBefore[ii][jj].setContents(cellArray[ii-1][jj-1]);
            }
        }
    }
    
    public void resetCells()
    {
        for( int ii=0; ii<gridDim; ii++ )
        {
            for( int jj=0; jj<gridDim; jj++ )
            {
                cellsAfter[ii][jj].clearContents();
            }
        }
    }

    public boolean drawCells()
    {
        boolean alive=false;
        
        pen = paper.getGraphics();
        debStr("\nDrawing Cells\n");
        for( int ii=1; ii<gridDim-1; ii++ )
        {
            for( int jj=1; jj<gridDim-1; jj++ )
            {
                if( cellsBefore[ii][jj].isOn() )
                {
                    alive = true;
                    pen.setColor(Color.BLACK);
                    debStr("1");
                }
                else
                {
                    pen.setColor(Color.YELLOW);
                    debStr("0");
                }
//              pen.move(ii*cellSize+cellSize/2, jj*cellSize+cellSize/2);
                pen.drawOval(ii*cellSize+cellSize/2, jj*cellSize+cellSize/2, cellSize/2, cellSize/2);
                //pen.up();
                //pen.move(location(ii), location(jj));
                //pen.down();
                //pen.drawCircle(1.5);
            }
            debStr("");
        }
        return alive;
    }
    
    public void run() throws InterruptedException
    {
        int nCount=0; //neighbor count
        boolean isAlive=true;

        while( isAlive )
        {
            isAlive = drawCells();
            Thread.currentThread();
			Thread.sleep(1000);
            for( int ii=1; ii<gridDim-1; ii++ )
            {
                for( int jj=1; jj<gridDim-1; jj++ )
                {
                    nCount = 0;
                    for(int kk=-1; kk<=1; kk++ )
                    {
                        for(int ll=-1; ll<=1; ll++ )
                        {
                            nCount += cellsBefore[ii+kk][jj+ll].isOn() ? 1 : 0;
                        }
                    }
                    nCount -= cellsBefore[ii][jj].isOn() ? 1 : 0;
                    switch(nCount)
                    {
                        case 2:
                            if( cellsBefore[ii][jj].isOn() )
                            {
                                cellsAfter[ii][jj].setOn();
                            }
                            break;
                        case 3:
                            cellsAfter[ii][jj].setOn();
                            break;
                        default:
                            cellsAfter[ii][jj].setOff();
                    }
                }
            }
            switchGrid();
        }
    }
}
