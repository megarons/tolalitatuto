package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

/**
 * Created by wyiss on 2016/12/28.
 */
public class TesseractUtil {

    public static ITesseract instance = new Tesseract();

    //public static File tessDataFolder = LoadLibs.extractTessResources("tessdata");

    public static ITesseract getInstance() {
        return instance == null ? new Tesseract() : instance;
    }

    public static String doOCR(File file) {
        Tesseract instance = new Tesseract();  // JNA Interface Mapping
         //instance.setDatapath("/usr/local/Cellar/tesseract/4.0.0_1/share/tessdata");
        instance.setLanguage("por");
        String result = "";
        try {
            result = instance.doOCR(file);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String doOCR(BufferedImage bufferedImage) {
        ITesseract instance = getInstance();  // JNA Interface Mapping
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
       // instance.setDatapath(tessDataFolder.getParent());

        try {
            String result = instance.doOCR(bufferedImage);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }

}
