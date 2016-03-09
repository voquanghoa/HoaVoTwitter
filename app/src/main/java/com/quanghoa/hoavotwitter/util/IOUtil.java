package com.quanghoa.hoavotwitter.util;

import com.quanghoa.hoavotwitter.config.AppConstant;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by voqua on 3/8/2016.
 */
public class IOUtil {
    public static String readFullyInput(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[AppConstant.DEFAULT_BUFFER_SIZE];
        int bufferLength;

        while ((bufferLength = inputStream.read(buffer)) > 0) {
            byteArrayOutputStream.write(buffer, 0, bufferLength);
        }

        inputStream.close();
        byteArrayOutputStream.close();

        return new String(byteArrayOutputStream.toByteArray());
    }
}
