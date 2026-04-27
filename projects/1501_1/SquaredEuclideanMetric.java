package cs1501_p5;

public class SquaredEuclideanMetric implements DistanceMetric_Inter
{
    public double colorDistance(Pixel p1, Pixel p2)
    {
        // calculates sq euc distance between two pixels using the distance formula(sq sum)
        int red = p1.getRed() - p2.getRed();
        int green = p1.getGreen() - p2.getGreen();
        int blue = p1.getBlue() - p2.getBlue();
        return red*red + blue*blue + green*green;
    }

}
