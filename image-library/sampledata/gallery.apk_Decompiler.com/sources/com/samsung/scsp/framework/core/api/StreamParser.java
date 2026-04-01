package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.framework.core.ScspException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class StreamParser {
    public static String parseString(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb2.toString();
                }
                sb2.append(readLine.trim());
            }
        } catch (IOException e) {
            throw new ScspException(60000000, "IOException occurred during data conversion received from the server.", e);
        }
    }
}
