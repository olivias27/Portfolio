package cs1501_p5;

import java.util.Map;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ColorQuantizer implements ColorQuantizer_Inter
{
    // original imasage pixels
    private final Pixel[][] pixelArray;
    // bucket or cluster
    private final ColorMapGenerator_Inter gen;
    public ColorQuantizer(Pixel[][] pixelArray, ColorMapGenerator_Inter gen)
    {
        this.pixelArray = pixelArray;
        this.gen = gen;
    }
    public ColorQuantizer(String bmpFilename, ColorMapGenerator_Inter gen) throws Exception
    {
        this.gen = gen;
        // reads in the image
        BufferedImage image = ImageIO.read(new File(bmpFilename));
        // converts the image into pixels to be used
        this.pixelArray = Util.convertBitmapToPixelMatrix(image);
    }
    public Pixel[][] quantizeTo2DArray(int numColors)
    {
        // generates and maps pixels to a palette
        Pixel[] initialPalette = gen.generateColorPalette(pixelArray, numColors);
        Map<Pixel, Pixel> colorMap = gen.generateColorMap(pixelArray, initialPalette);
        Pixel[][] quant = new Pixel[pixelArray.length][pixelArray[0].length];
       // replaces each pixel in the image with the quantized pixle
        for(int i =0; i < pixelArray.length; i++)
        {
            for(int j = 0; j < pixelArray[i].length; j++)
            {
                quant[i][j]= colorMap.get(pixelArray[i][j]);
            }
        }
        return quant;
    }
    public void quantizeToBMP(String filename, int numColors)
    {
        Pixel[][] quant = quantizeTo2DArray(numColors);
        // saves the quantized image
        Util.savePixelMatrixToBitmap(filename, quant);
    }
}
