package com.tiufanov.denis.pixformancetask.fragment;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static String readFromResources(@NonNull final Context context, final String fileName) {
        BufferedReader reader = null;
        try {
            final InputStream resourceAsStream = context.getClassLoader().getResourceAsStream(fileName);
            if (resourceAsStream == null) {
                throw new IllegalStateException("Resource not found! " + fileName);
            }
            reader = new BufferedReader(new InputStreamReader(resourceAsStream));
            final StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (final IOException ignored) {
            }
        }
    }

}
