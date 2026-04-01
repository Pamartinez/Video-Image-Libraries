package androidx.media3.exoplayer.source;

import E2.r;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements r {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ DataSource.Factory f;

    public /* synthetic */ a(Object obj, DataSource.Factory factory, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = factory;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return DefaultMediaSourceFactory.newInstance((Class) this.e, this.f);
            case 1:
                return DefaultMediaSourceFactory.newInstance((Class) this.e, this.f);
            case 2:
                return DefaultMediaSourceFactory.newInstance((Class) this.e, this.f);
            default:
                return ((DefaultMediaSourceFactory.DelegateFactoryLoader) this.e).lambda$loadSupplier$4(this.f);
        }
    }
}
