package androidx.core.graphics;

import android.content.Context;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TypefaceCompatUtil {
    public static ByteBuffer mmap(Context context, CancellationSignal cancellationSignal, Uri uri) {
        ParcelFileDescriptor openFileDescriptor;
        Throwable th;
        FileInputStream fileInputStream;
        Throwable th2;
        try {
            openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", cancellationSignal);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
            FileChannel channel = fileInputStream.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            fileInputStream.close();
            openFileDescriptor.close();
            return map;
            throw th2;
            throw th;
        } catch (IOException unused) {
            return null;
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
    }
}
