package com.samsung.android.gallery.support.blackboard.key;

import android.os.Bundle;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EventMessage {
    public int arg1;
    public int arg2;
    private Bundle mBundle;
    public Object obj;
    public int what;

    private EventMessage(int i2, int i7, int i8, Object obj2) {
        this.what = i2;
        this.arg1 = i7;
        this.arg2 = i8;
        this.obj = obj2;
    }

    public static EventMessage obtain(int i2, int i7, int i8, Object obj2) {
        return new EventMessage(i2, i7, i8, obj2);
    }

    public Bundle getData() {
        return this.mBundle;
    }

    public void setData(Bundle bundle) {
        this.mBundle = bundle;
    }

    public String toString() {
        return "EventMessage{" + EventKey.getName(this.what) + ArcCommonLog.TAG_COMMA + this.arg1 + ArcCommonLog.TAG_COMMA + this.arg2 + ArcCommonLog.TAG_COMMA + this.obj + "}";
    }

    public static EventMessage obtain(int i2, int i7, Object obj2) {
        return new EventMessage(i2, i7, 0, obj2);
    }

    public static EventMessage obtain(int i2, Object obj2) {
        return new EventMessage(i2, 0, 0, obj2);
    }

    public static EventMessage obtain(int i2) {
        return new EventMessage(i2, 0, 0, (Object) null);
    }
}
