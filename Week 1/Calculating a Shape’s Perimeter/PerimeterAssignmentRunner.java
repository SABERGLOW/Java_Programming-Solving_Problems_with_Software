import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner 
{
    
    public double getPerimeter (Shape s)
    {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) 
        {
            double currDist = prevPt.distance(currPt);// Find distance from prevPt point to currPt 
            totalPerim = totalPerim + currDist;// Update totalPerim by currDist
            prevPt = currPt;// Update prevPt to be currPt
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) 
    {
        // Put code here
        int NumPoints = 0;
        for (Point currPt : s.getPoints()) 
        {
            NumPoints++;
        }
        
        return NumPoints;
    }

    public double getAverageLength(Shape s)
    {
        // Put code here
        return (getPerimeter(s) / getNumPoints(s));
    }

    public double getLargestSide(Shape s)
    {
        // Put code here
        double LargestSide = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) 
        {
            double currDist = prevPt.distance(currPt);// Find distance from prevPt point to currPt 
            if(currDist > LargestSide)
            {
                LargestSide = currDist;
            }
            prevPt = currPt;// Update prevPt to be currPt
        }
        return LargestSide;
    }

    public double getLargestX(Shape s)
    {
        // Put code here
        double LargestX = 0.0;
        for (Point currPt : s.getPoints()) 
        {
            double currX = currPt.getX();// Find X of current point
            if(currX > LargestX)
            {
                LargestX = currX;
            }
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles()
    {
        // Put code here
        double LargestPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > LargestPerimeter)
            {
                LargestPerimeter = getPerimeter(s);
            }
        }
        return LargestPerimeter;
    }

    public String getFileWithLargestPerimeter() 
    {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double LargestPerimeter = 0.0;
        File FileWithLargestPerimeter = null;    // replace this code
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(getPerimeter(s) > LargestPerimeter)
            {
                LargestPerimeter = getPerimeter(s);
                FileWithLargestPerimeter = f;
            }
        }
        
        return FileWithLargestPerimeter.getName();
    }


    
    public void testPerimeterMultipleFiles() 
    {
        // Put code here
        double LargestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("LargestPerimeter = " + LargestPerimeter);
        
    }

    public void testFileWithLargestPerimeter() 
    {
        // Put code here
        String FileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("FileWithLargestPerimeter = " + FileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle()
    {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints())
        {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

        public void testPerimeter () 
    {
        
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int NumPoints = getNumPoints(s);
        double AverageLength = getAverageLength(s);
        double LargestSide = getLargestSide(s);
        double LargestX = getLargestX(s);
        
        System.out.println("perimeter = " + length);
        System.out.println("NumPoints = " + NumPoints);
        System.out.println("AverageLength = " + AverageLength);
        System.out.println("LargestSide = " + LargestSide);
        System.out.println("LargestX = " + LargestX);
        
        testFileWithLargestPerimeter();
        testPerimeterMultipleFiles() ;
    }
    
    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames()
    {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            System.out.println(f);
        }
    }

    public static void main (String[] args) 
    {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
