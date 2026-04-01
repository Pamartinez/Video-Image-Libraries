package com.samsung.scsp.framework.core.network.base;

import O3.r;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.error.Logger;
import com.samsung.scsp.framework.core.listeners.ProgressListener;
import com.samsung.scsp.framework.core.network.ByteBufferWriter;
import com.samsung.scsp.framework.core.network.visitor.FileInputStreamPayloadWriter;
import com.samsung.scsp.framework.core.network.visitor.FilePayloadWriter;
import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import com.samsung.scsp.framework.core.network.visitor.StringPayloadWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PayloadWriterVisitorImpl implements PayloadWriterVisitor<OutputStream> {
    private final Logger logger = Logger.get("PayloadWriterVisitorImpl");

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$visit$0(PayloadWriterVisitor.Payload payload) {
        return (String) payload.content;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$visit$1(PayloadWriterVisitor.Payload payload, ProgressListener progressListener, long j2, long j3) {
        long j8 = payload.transferred + j3;
        payload.transferred = j8;
        progressListener.onProgress(j8, j2);
    }

    public void visit(PayloadWriterVisitor.Payload<OutputStream> payload, StringPayloadWriter stringPayloadWriter) {
        this.logger.d(new h(payload));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((OutputStream) payload.output, StandardCharsets.UTF_8));
        bufferedWriter.write((String) payload.content);
        bufferedWriter.flush();
    }

    public void visit(PayloadWriterVisitor.Payload<OutputStream> payload, FilePayloadWriter filePayloadWriter) {
        FileInputStream fileInputStream = new FileInputStream((File) payload.content);
        try {
            visit(payload, fileInputStream, true);
            fileInputStream.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void visit(PayloadWriterVisitor.Payload<OutputStream> payload, FileInputStreamPayloadWriter fileInputStreamPayloadWriter) {
        visit(payload, (FileInputStream) payload.content, false);
    }

    private void visit(PayloadWriterVisitor.Payload<OutputStream> payload, FileInputStream fileInputStream, boolean z) {
        PayloadWriterVisitor.Payload<OutputStream> payload2;
        r rVar;
        long available = (long) fileInputStream.available();
        fileInputStream.skip(payload.transferred);
        try {
            ProgressListener progressListener = payload.httpRequest.getProgressListener();
            if (progressListener != null) {
                payload2 = payload;
                rVar = new r(payload2, progressListener, available, 2);
            } else {
                payload2 = payload;
                rVar = null;
            }
            ByteBufferWriter.write((OutputStream) payload2.output, fileInputStream, rVar, payload2.length);
            if (z) {
                FaultBarrier.run(new g(2, fileInputStream), true);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (z) {
                FaultBarrier.run(new g(2, fileInputStream), true);
            }
            throw th2;
        }
    }
}
