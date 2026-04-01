package androidx.media3.datasource;

import E2.r;
import E2.s;
import E2.t;
import E2.u;
import G.a;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import com.google.common.util.concurrent.C;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.y;
import com.google.common.util.concurrent.z;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final r DEFAULT_EXECUTOR_SERVICE;
    private final DataSource.Factory dataSourceFactory;
    private final y listeningExecutorService;
    private final int maximumOutputDimension;
    private final BitmapFactory.Options options;

    static {
        r rVar;
        t tVar = new t(1);
        if (tVar instanceof Serializable) {
            rVar = new s(tVar);
        } else {
            rVar = new u(tVar);
        }
        DEFAULT_EXECUTOR_SERVICE = rVar;
    }

    public DataSourceBitmapLoader(y yVar, DataSource.Factory factory, BitmapFactory.Options options2, int i2) {
        this.listeningExecutorService = yVar;
        this.dataSourceFactory = factory;
        this.options = options2;
        this.maximumOutputDimension = i2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Bitmap lambda$loadBitmap$2(Uri uri) {
        return load(this.dataSourceFactory.createDataSource(), uri, this.options, this.maximumOutputDimension);
    }

    /* access modifiers changed from: private */
    public static y lambda$static$0() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        if (newSingleThreadExecutor instanceof y) {
            return (y) newSingleThreadExecutor;
        }
        if (newSingleThreadExecutor instanceof ScheduledExecutorService) {
            return new C((ScheduledExecutorService) newSingleThreadExecutor);
        }
        return new z(newSingleThreadExecutor);
    }

    private static Bitmap load(DataSource dataSource, Uri uri, BitmapFactory.Options options2, int i2) {
        try {
            dataSource.open(new DataSpec(uri));
            byte[] readToEnd = DataSourceUtil.readToEnd(dataSource);
            return BitmapUtil.decode(readToEnd, readToEnd.length, options2, i2);
        } finally {
            dataSource.close();
        }
    }

    public ListenableFuture loadBitmap(Uri uri) {
        return ((z) this.listeningExecutorService).a(new a(0, this, uri));
    }

    public boolean supportsMimeType(String str) {
        return Util.isBitmapFactorySupportedMimeType(str);
    }
}
