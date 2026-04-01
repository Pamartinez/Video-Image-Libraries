package com.samsung.android.sum.core.graph;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.cache.DiskCache;
import com.samsung.android.sum.core.message.MessagePublisher;
import com.samsung.android.sum.core.message.MessageSubscriber;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.types.OptionBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface Graph<T> {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Option extends OptionBase {
        public static final int CACHEABLE = 1;
        public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
            public Option createFromParcel(Parcel parcel) {
                return new Option(parcel);
            }

            public Option[] newArray(int i2) {
                return new Option[i2];
            }
        };
        public static final int GRAPH_NAME = 11;
        public static final int IGNORE_FILTER_EXCEPTION = 6;
        public static final int MAX_DURATION = 0;
        public static final int OUTPUT_ON_EVENT_CALLBACK = 9;
        public static final int PACKED_IO_BUFFERS = 2;
        public static final int RECEIVE_ALL_EXCEPTION = 7;
        public static final int RESTORE_META_DATA = 4;
        public static final int RUN_ONE_BY_ONE = 3;
        public static final int RUN_ONE_WAY = 10;
        public static final int RUN_TIMEOUT = 12;
        public static final int SUPPORT_ALPHA_CHANNEL = 5;
        private static final String TAG = Def.tagOf((Class<?>) Option.class);
        public static final int TRACE_MEDIAFILTER = 8;

        public Option() {
        }

        public Option cacheable() {
            set(1);
            return this;
        }

        public void clear() {
            if (get(1) != null) {
                ((DiskCache) get(1)).close();
            }
            super.clear();
        }

        public String getGraphName() {
            return (String) get(11, "n/a");
        }

        public <V> V getIgnoreFilterException() {
            return get(6);
        }

        public long getMaxDuration(TimeUnit timeUnit) {
            return timeUnit.convert(((Long) get(0, 0L)).longValue(), TimeUnit.MILLISECONDS);
        }

        public long getRunTimeout() {
            return ((Long) get(12)).longValue();
        }

        public Option ignoreFilterException(Object... objArr) {
            set(6, Arrays.asList(objArr));
            return this;
        }

        public boolean isCacheable() {
            return contains(1);
        }

        public boolean isIgnoreFilterException() {
            return contains(6);
        }

        public boolean isOutputOnEventCallback() {
            return contains(9);
        }

        public boolean isPackedIOBuffers() {
            return contains(2);
        }

        public boolean isRestoreMetadata() {
            return contains(4);
        }

        public boolean isRunOneByOne() {
            return contains(3);
        }

        public boolean isRunOneWay() {
            return contains(10);
        }

        public boolean isSupportAlphaChannel() {
            return contains(5);
        }

        public boolean isTraceMediaFilter() {
            return contains(8);
        }

        public Option outputOnEventCallback() {
            set(9);
            return this;
        }

        public Option packedIOBuffers() {
            set(2);
            return this;
        }

        public Option receiveAllException() {
            set(7);
            return this;
        }

        public Option restoreMetadata() {
            set(4);
            return this;
        }

        public Option runOneByOne() {
            set(3);
            return this;
        }

        public Option runOneWay() {
            set(10);
            return this;
        }

        public OptionBase set(int i2) {
            return super.set(i2);
        }

        public Option setGraphName(String str) {
            set(11, str);
            return this;
        }

        public OptionBase setIfExists(int i2, Object obj) {
            return super.setIfExists(i2, obj);
        }

        public Option setMaxDuration(long j2, TimeUnit timeUnit) {
            set(0, Long.valueOf(timeUnit.toMillis(j2)));
            return this;
        }

        public Option setRunTimeout(long j2) {
            set(12, Long.valueOf(j2));
            return this;
        }

        public Option supportAlphaChannel() {
            set(5);
            return this;
        }

        public Option traceMediaFilter() {
            set(8);
            return this;
        }

        public Option(Parcel parcel) {
            super(parcel);
        }

        public long getRunTimeout(long j2) {
            return ((Long) get(12, Long.valueOf(j2))).longValue();
        }

        public OptionBase set(int i2, Object obj) {
            return super.set(i2, obj);
        }
    }

    MessagePublisher getMessagePublisher();

    <V> V getOption(int i2);

    <V> V getOption(int i2, V v);

    void pause();

    void quitNow();

    void quitSafely();

    @Deprecated
    void release();

    void resume();

    MediaBuffer run(MediaBuffer mediaBuffer) {
        ArrayList arrayList = new ArrayList();
        Response run = run((List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer) {
            final /* synthetic */ MediaBuffer val$buffer;

            {
                this.val$buffer = r2;
                add(r2);
            }
        }, (List<MediaBuffer>) arrayList);
        if (!run.isError()) {
            return (MediaBuffer) arrayList.get(0);
        }
        throw ((RuntimeException) Optional.ofNullable((RuntimeException) run.getException()).orElseGet(new u(4)));
    }

    Response run(List<MediaBuffer> list, List<MediaBuffer> list2);

    void setMessageSubscriber(MessageSubscriber messageSubscriber);

    void run(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        Response run = run((List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer) {
            final /* synthetic */ MediaBuffer val$inputBuffer;

            {
                this.val$inputBuffer = r2;
                add(r2);
            }
        }, (List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer2) {
            final /* synthetic */ MediaBuffer val$outputBuffer;

            {
                this.val$outputBuffer = r2;
                add(r2);
            }
        });
        if (run.isError()) {
            throw ((RuntimeException) Optional.ofNullable((RuntimeException) run.getException()).orElseGet(new u(4)));
        }
    }

    List<MediaBuffer> run(List<MediaBuffer> list) {
        ArrayList arrayList = new ArrayList();
        Response run = run(list, (List<MediaBuffer>) arrayList);
        if (!run.isError()) {
            return arrayList;
        }
        throw ((RuntimeException) Optional.ofNullable((RuntimeException) run.getException()).orElseGet(new u(4)));
    }
}
