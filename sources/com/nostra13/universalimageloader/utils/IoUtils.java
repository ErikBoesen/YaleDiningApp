package com.nostra13.universalimageloader.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils {
    private static final int BUFFER_SIZE = 8192;

    private IoUtils() {
    }

    public static void copyStream(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[8192];
        while (true) {
            int count = is.read(bytes, 0, 8192);
            if (count != -1) {
                os.write(bytes, 0, count);
            } else {
                return;
            }
        }
    }

    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }
}
