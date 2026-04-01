package com.samsung.android.gallery.app.ui.map.picker.google;

import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ GoogleMapPickerContainer.GoogleLocApiImpl2 f2570a;
    public final /* synthetic */ Consumer b;

    public /* synthetic */ b(GoogleMapPickerContainer.GoogleLocApiImpl2 googleLocApiImpl2, Consumer consumer) {
        this.f2570a = googleLocApiImpl2;
        this.b = consumer;
    }

    public final void a(Exception exc) {
        this.f2570a.lambda$requestByLocationService$1(this.b, exc);
    }
}
