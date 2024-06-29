import java.io.File;

import edu.duke.*;

public class Perimeter {

    public static double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    @SuppressWarnings("unused")
    public static int getNumPoints(Shape s) {
        int count = 0;
        for (Point currPt : s.getPoints()) {
            count += 1;
        }
        return count;
    }

    public static double getAverageLength(Shape s) {
        double avgLength = 0.0;
        double perimeter = getPerimeter(s);
        double noPoints = getNumPoints(s);
        avgLength = perimeter / noPoints;
        return avgLength;
    }

    public static double getLargestSide(Shape s) {
        double largestSide = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if (currDist > largestSide) {
                largestSide = currDist;
            }
        }
        return largestSide;
    }

    public static double getLargestX(Shape s) {
        double largestX = 0.0;
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            // Update totalPerim by currDist
            if (currPt.getX() > largestX) {
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public static double getLargestPerimeterMultipleFiles() {
        double largestPeri = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > largestPeri) {
                largestPeri = currPerimeter;
            }
        }

        // System.out.println("Largest Perimeter among file is " + largestPeri);
        return largestPeri;
    }

    public static String getFileWithLargestPerimeter() {
        String temp = "";
        double largestPeri = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > largestPeri) {
                largestPeri = currPerimeter;
                temp = f.getName();
            }
        }
        // System.out.println("File with largest perimeter is " + temp);
        return temp;
    }
    
    public void testPerimeterMultipleFiles() {
        getLargestPerimeterMultipleFiles();
    }

    public void testFileWithLargestPerimeter() {
        getFileWithLargestPerimeter();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println(getNumPoints(s));
        System.out.println("Average length" +getAverageLength(s));
        System.out.println("Longest Side " + getLargestSide(s));
        System.out.println("Largest X is " + getLargestX(s));

        // System.out.println("Largest perameter among file is" + getLargestPerimeterMultipleFiles());

        // System.out.println("largest Perimeter file name is" + getFileWithLargestPerimeter());
    }

    

    public static void main(String[] args) {
        Perimeter pr = new Perimeter();
        pr.testPerimeter();
    }
}
