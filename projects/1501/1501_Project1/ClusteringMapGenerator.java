package cs1501_p5;
import java.util.*;
public class ClusteringMapGenerator implements ColorMapGenerator_Inter 
{
    private final DistanceMetric_Inter metric;
    public ClusteringMapGenerator(DistanceMetric_Inter metric)
    {
        // distance calculation (squared euclidean or circular)
        this.metric = metric;
    }
    //using k-- means clustering & finding furthest pixels to use as centroids at first
    public Pixel[] generateColorPalette(Pixel[][] pixelArray, int numColors)
    {
        // keeps all final centroid colors
       Pixel[] centroids = new Pixel[numColors];
       // tracks which centroids we have already seen
       Map<Pixel,Boolean> select = new HashMap<>();
       // makes the first pixel the original centroid and puts it in the map since we have already used it
       centroids[0] = pixelArray[0][0];
       select.put(centroids[0], true);
       // finds the furthest pixel from the centroid
       for(int i = 1; i<numColors; i++)
       {
        Pixel far = null;
        double max = -1;
        int rgbMax = -1;
        // goes through each pixel in each row
        for(Pixel[] r: pixelArray)
        {
            for(Pixel p: r)
            {
                if(!select.containsKey(p))
                {
                    double min = Double.MAX_VALUE;
                    // this loop tries to find distance between the nearest centroid and also to find the pixel with the biggest min distance
                    // to create the next centroid, you want to find the pixel that is furthest away from any existing centroid
                    for(int j =0; j < i; j++)
                    {
                        double distance = metric.colorDistance(p, centroids[j]);
                        // update the min distance if there is a closer one found
                        if(distance < min)
                        {
                            min = distance;
                        }
                    }
                    // higher RGB values are prioritized if two pixels have the same distance
                    int rgb = (p.getRed() << 16) | (p.getGreen() << 8) | p.getBlue();
                    
                    if(min > max || (min == max && rgb > rgbMax))
                    {
                        max = min;
                        far = p;
                        rgbMax = rgb;
                    }
                }
            }
        }
        // make the farthest pixel the new centroid
        centroids[i] = far;
        select.put(far, true);
       }
       return centroids;
    }
    public Map<Pixel, Pixel> generateColorMap(Pixel[][] pixelArray, Pixel[] initialColorPalette)
    {
        Pixel[] centroids = new Pixel[initialColorPalette.length];
        // copy all elements from the initial color palette into the centroids array
        for(int i =0; i < initialColorPalette.length; i++)
        {
            centroids[i] = initialColorPalette[i];
        }
        // maps original pixels to their quantized colors
        Map<Pixel, Pixel> colorMap = new HashMap<>();
       // use a boolean to track which centroids have been modified
        boolean change = true;
        // do this until no further changes need to be made to update the centroids
        while(change == true)
        {
            change = false;
            @SuppressWarnings("unchecked")
            // create an array of clusters per centroid
            Map<Pixel, Pixel>[] clusters = new Map[centroids.length];
            for(int i = 0; i < clusters.length; i++)
            {
                // for each cluster, create a hash map for the pixels
                clusters[i] = new HashMap<>();
            }
            // for each pixel in each row
            for(Pixel[] r: pixelArray)
            {
                for(Pixel p : r)
                {
                    int near = 0;
                    double min = Double.MAX_VALUE;
                    for(int i = 0; i < centroids.length; i++)
                    {
                        // calculate the distance from the pixel to every centroid
                        double distance = metric.colorDistance(p, centroids[i]);
                        if(distance < min)
                        {
                            // updates which one is the nearest when a closer one is found
                            min = distance;
                            near = i;
                        }
                    }
                    // assign this pixel to thecluster of the centroid that was the closest 
                    // cluster[i] will contain the pixels that were nearest to the centroids[i]
                    clusters[near].put(p, centroids[near]);
                }
            }
            // use this to update the centroids
            for(int i =0; i < centroids.length; i++)
            {
                int red = 0;
                int green =0;
                int blue = 0;
                int count =0;
                // sum up the value of all the RGB values on the cluster
                for(Pixel pix : clusters[i].keySet())
                {
                    red +=pix.getRed();
                    green+= pix.getGreen();
                    blue += pix.getBlue();
                    count++;
                }
                Pixel newPix;
                // if there is more than one pixel, calculate the mean RGB value for the new centroid from the pixels in the cluster
                if(count > 0)
                {
                    newPix = new Pixel(red/count, green/count, blue/count);

                }
                else
                {
                    // if there is only one pixel, make that the new centroid
                    newPix = centroids[i];
                }
                // if the centroid moved, mark it as changed since we have adjudsted the value
                if(!newPix.equals(centroids[i]))
                {
                    centroids[i] = newPix;
                    change = true;
                }
            }
            // clear the previous color map and update it with the new mappings from pixels to their centroids
            colorMap.clear();
            for(Map<Pixel, Pixel> cluster: clusters)
            {
                colorMap.putAll(cluster);
            }
        }
        // the loop will stop when none of the centroids move at all, which means our color map has mapped all pixels to its nearest centroid
        return colorMap;
    }
}
