package com.quanghoa.hoavotwitter.util;

import com.quanghoa.hoavotwitter.config.AppConstant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by voqua on 3/8/2016.
 */

/***
 * Contains some useful methods for working with IO
 */
public class IOUtil {
    /***
     * Read fully the input and return the result as a string
     * @param inputStream The input stream to read
     * @return The result as a string
     * @throws IOException When we could not read the stream
     */
    public static String readFullyInput(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[AppConstant.DEFAULT_BUFFER_SIZE];
        int bufferLength;

        //Read to end
        while ((bufferLength = inputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, bufferLength);
        }
        //Close the stream
        inputStream.close();
        byteArrayOutputStream.close();

        return new String(byteArrayOutputStream.toByteArray());
    }
}
