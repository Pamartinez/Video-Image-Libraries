package androidx.appsearch.app;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SearchResults extends Closeable {
    ListenableFuture getNextPageAsync();
}
