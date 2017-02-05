package application.utilities;

import java.util.Base64;

/**
 * Created by pkonwar on 2/5/2017.
 */
public class ImageUtils {

    public static byte[] fromBase64decodedImageToByte(String base64encoded) {

        if(base64encoded == null || base64encoded.trim().length() == 0){
            return null;
        }
        String[] split = base64encoded.split(",");

        if(split.length < 2)
            return null;

        String base64Image = split[1];
        return Base64.getDecoder().decode(base64Image);
    }

    public static String fromByteToBase64encodedImage(byte[] bytes) {

        if(bytes == null){
            return null;
        }
        String base64Image = Base64.getEncoder().encodeToString(bytes);
        return "data:image/png;base64," + base64Image;
    }
}
