package cs1501_p5;

import java.util.*;
public class BucketingMapGenerator implements ColorMapGenerator_Inter
{
    public Pixel[] generateColorPalette(Pixel[][] pixelArray, int numColors)
    {
        // array to store pixels 
        Pixel[] palette = new Pixel[numColors];
        // total possible number of RGB colros (16777216 or 2^24)
        long totalColors = (long)(Math.pow(2,24));
         // the RGB color space is 24 bits, so we need to divide that space into numColor buckets and map each to the mid color of the bucket
        for(int i = 0; i < numColors; i++)
        {
            // calculate the center of the i'th bucket (number of buckets is the number of colors)
            // need to add 0.5 to the i to find the midpoint, but we cannot use integers because it will truncate the decimal and could place them into the wronfg bucket
            long center = (long) (((i+0.5) / numColors) *totalColors);
            // the red is bits 16-23, so we need to perform a right shift so that only the individual colors remain
            // 0xFF keeps only the last 8 bits
            int r = (int) ((center >> 16) & 0xFF);
            int g = (int) ((center >> 8) & 0xFF);
            int b = (int) (center & 0xFF);
            //stores the new center color in the palette
            palette[i] = new Pixel(r,g,b);
        }
        return palette;
    }
    public Map<Pixel,Pixel> generateColorMap(Pixel[][] pixelArray, Pixel[] initialColorPalette)
    {
        // stores og pixel to the quantized color 
        Map<Pixel,Pixel> colorMap = new HashMap<>();
        int numColors = initialColorPalette.length;
        long totalColors = (long)(Math.pow(2, 24));
        //nu,ber of buckets should equal the number of colors
        int bucketSize = (int)(totalColors / numColors);
        // iterate over every pixel in an image (two loops needed since it is a 2d array)
        for(int i = 0; i < pixelArray.length; i++)
        {
            for(int j = 0; j < pixelArray[i].length; j++)
            {
                Pixel pix = pixelArray[i][j];
                // check to make sure it doesnt already exist in the color map to avoid reprocessing
                if(!colorMap.containsKey(pix))
                {
                    // combine into a 24-bit value 
                    long val = (pix.getRed() << 16) | (pix.getGreen() << 8) | pix.getBlue();
                   // find the bucket it belongs in
                    int bucket = (int)(val / bucketSize);
                    //if the bucket is out of the specified indices, place it in the last bucket
                    if(bucket >= numColors)
                    {
                        bucket = numColors -1;
                    }
                    // put the quantized pixel in the color map and map it back to its original color
                    colorMap.put(pix, initialColorPalette[bucket]);
                }
            }
        }
        return colorMap;
    }

}