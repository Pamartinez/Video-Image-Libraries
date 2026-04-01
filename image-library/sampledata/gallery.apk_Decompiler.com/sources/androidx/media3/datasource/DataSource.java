package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.DataReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface DataSource extends DataReader {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Factory {
        DataSource createDataSource();
    }

    void addTransferListener(TransferListener transferListener);

    void close();

    Map<String, List<String>> getResponseHeaders() {
        return Collections.EMPTY_MAP;
    }

    Uri getUri();

    long open(DataSpec dataSpec);
}
