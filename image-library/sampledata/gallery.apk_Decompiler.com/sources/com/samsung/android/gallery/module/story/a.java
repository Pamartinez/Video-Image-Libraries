package com.samsung.android.gallery.module.story;

import android.graphics.Typeface;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3089a;

    public /* synthetic */ a(int i2) {
        this.f3089a = i2;
    }

    public final Object apply(Object obj) {
        String str = (String) obj;
        switch (this.f3089a) {
            case 0:
                return Typeface.createFromFile("/system/fonts/NotoSerif-Regular.ttf");
            case 1:
                return Typeface.create(Typeface.create("sec", 1), 700, false);
            case 2:
                return Typeface.create("sec-roboto-light", 1);
            case 3:
                return Typeface.create(Typeface.create("sec", 1), 600, false);
            case 4:
                return Typeface.create("sec-roboto-light", 1);
            case 5:
                return Typeface.create(Typeface.create("sec", 0), 400, false);
            case 6:
                return Typeface.create("sec-roboto-light", 0);
            case 7:
                return Typeface.createFromFile("/system/fonts/ComingSoon.ttf");
            case 8:
                return Typeface.createFromFile("/system/fonts/SamsungKorean-Light.ttf");
            case 9:
                return Typeface.createFromFile("/system/fonts/SamsungKorean-Regular.ttf");
            default:
                return Typeface.createFromFile("/system/fonts/SamsungKorean-Bold.ttf");
        }
    }
}
