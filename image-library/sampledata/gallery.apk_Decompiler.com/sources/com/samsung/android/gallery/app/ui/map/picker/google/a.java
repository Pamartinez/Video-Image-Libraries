package com.samsung.android.gallery.app.ui.map.picker.google;

import android.location.Location;
import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMapPickerContainer.GoogleLocApiImpl2 f2568a;
    public final /* synthetic */ long b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Consumer f2569c;

    public /* synthetic */ a(GoogleMapPickerContainer.GoogleLocApiImpl2 googleLocApiImpl2, long j2, Consumer consumer) {
        this.f2568a = googleLocApiImpl2;
        this.b = j2;
        this.f2569c = consumer;
    }

    public final void a(Object obj) {
        this.f2568a.lambda$requestByLocationService$0(this.b, this.f2569c, (Location) obj);
    }
}
