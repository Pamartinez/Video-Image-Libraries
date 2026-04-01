package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ObjectConstructor {
    public final /* synthetic */ int d;
    public final /* synthetic */ InstanceCreator e;
    public final /* synthetic */ Type f;

    public /* synthetic */ a(InstanceCreator instanceCreator, Type type, int i2) {
        this.d = i2;
        this.e = instanceCreator;
        this.f = type;
    }

    public final Object construct() {
        switch (this.d) {
            case 0:
                return this.e.createInstance(this.f);
            default:
                return this.e.createInstance(this.f);
        }
    }
}
