package com.samsung.android.gallery.app.ui.list.stories;

import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        Integer num = (Integer) obj;
        Integer num2 = (Integer) obj2;
        switch (this.d) {
            case 0:
                return num.compareTo(num2);
            default:
                return DeleteAnimDelegate.lambda$handleDeleteWithItemAnimator$1(num, num2);
        }
    }
}
