package x2;

import D1.b;
import D1.c;
import Gf.m;
import Q1.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import androidx.core.util.Preconditions;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import q2.r;
import rf.C1268s;
import s2.C0275e;

/* renamed from: x2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0338e implements C1268s, C0275e {
    public C0338e(m mVar) {
        String str = m.d;
        new ConcurrentHashMap(3, 1.0f, 2);
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [x2.e, java.lang.Object] */
    public static C0338e h(Context context, int i2) {
        boolean z;
        if (i2 != 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, a.y);
        Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(0, 0), obtainStyledAttributes.getDimensionPixelOffset(2, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0), obtainStyledAttributes.getDimensionPixelOffset(3, 0));
        B1.a.u(context, obtainStyledAttributes, 4);
        B1.a.u(context, obtainStyledAttributes, 9);
        B1.a.u(context, obtainStyledAttributes, 7);
        obtainStyledAttributes.getDimensionPixelSize(8, 0);
        C0344k.a(context, obtainStyledAttributes.getResourceId(5, 0), obtainStyledAttributes.getResourceId(6, 0), new C0334a((float) 0)).a();
        obtainStyledAttributes.recycle();
        ? obj = new Object();
        Preconditions.checkArgumentNonnegative(rect.left);
        Preconditions.checkArgumentNonnegative(rect.top);
        Preconditions.checkArgumentNonnegative(rect.right);
        Preconditions.checkArgumentNonnegative(rect.bottom);
        return obj;
    }

    public void e(r rVar) {
        j.e(rVar, "listener");
    }

    public void f(r rVar) {
        j.e(rVar, "listener");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, D1.c] */
    public c i(Context context, String str, b bVar) {
        ? obj = new Object();
        obj.f112a = 0;
        obj.b = 0;
        obj.f113c = 0;
        int a7 = bVar.a(context, str, true);
        obj.b = a7;
        if (a7 != 0) {
            obj.f113c = 1;
            return obj;
        }
        int b = bVar.b(context, str);
        obj.f112a = b;
        if (b != 0) {
            obj.f113c = -1;
        }
        return obj;
    }

    public void a() {
    }
}
