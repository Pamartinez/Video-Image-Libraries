package androidx.media3.common.util;

import android.net.Uri;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BitmapLoader {
    ListenableFuture loadBitmap(Uri uri);

    boolean supportsMimeType(String str);
}
