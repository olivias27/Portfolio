package cs1501_p5;

public class CircularHueMetric implements DistanceMetric_Inter
{
    // we want to compute the shortest distance on the color wheel (360 degrees)
    public double colorDistance(Pixel p1, Pixel p2)
    {
        // calculate the absolute distance between the two hues
        double hue = Math.abs(p1.getHue()- p2.getHue());
        // since we are computing on a color wheel, two colors could be very close to eachother, but have very far distances if they are at the beginning/end of the color wheel
        // therefore, we want to subtract the distance from 360 if this is the case, meaning that two hues cannot be further than 180 degrees apart. this will account for the circular aspect of the color wheel
        return Math.min(hue, 360-hue);
    }
}
