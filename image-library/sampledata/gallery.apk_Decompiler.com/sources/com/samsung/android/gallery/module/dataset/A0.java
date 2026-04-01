package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import java.io.Closeable;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A0 implements Runnable {
    public final /* synthetic */ MediaDataTimeline d;
    public final /* synthetic */ long e;
    public final /* synthetic */ DataTable f;
    public final /* synthetic */ ClusterTable[] g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ DefaultTable[] f2936h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Closeable[] f2937i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ ArrayList f2938j;
    public final /* synthetic */ MediaDataEntire.Candidates k;

    public /* synthetic */ A0(MediaDataTimeline mediaDataTimeline, long j2, DataTable dataTable, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, Closeable[] closeableArr, ArrayList arrayList, MediaDataEntire.Candidates candidates) {
        this.d = mediaDataTimeline;
        this.e = j2;
        this.f = dataTable;
        this.g = clusterTableArr;
        this.f2936h = defaultTableArr;
        this.f2937i = closeableArr;
        this.f2938j = arrayList;
        this.k = candidates;
    }

    public final void run() {
        this.d.lambda$swap$7(this.e, this.f, this.g, this.f2936h, this.f2937i, this.f2938j, this.k);
    }
}
