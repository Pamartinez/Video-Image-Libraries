package com.adobe.internal.xmp.utils;

import com.adobe.internal.xmp.options.SerializeOptions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMLStreamWriterFactory {
    public static XMLStreamWriterImpl create(Writer writer, SerializeOptions serializeOptions) {
        return new XMLStreamWriterImpl(writer, serializeOptions);
    }

    public static XMLStreamWriterImpl create(OutputStream outputStream, SerializeOptions serializeOptions) {
        try {
            return create((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, serializeOptions.getEncoding()), 4096), serializeOptions);
        } catch (UnsupportedEncodingException unused) {
            throw new IOException("Unsupported encoding " + serializeOptions.getEncoding());
        }
    }
}
