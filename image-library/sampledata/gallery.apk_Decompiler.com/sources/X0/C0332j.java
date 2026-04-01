package x0;

import C0.i;
import J0.b;
import J0.g;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: x0.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0332j {

    /* renamed from: a  reason: collision with root package name */
    public final E f2056a = new E();
    public final HashSet b = new HashSet();

    /* renamed from: c  reason: collision with root package name */
    public HashMap f2057c;
    public HashMap d;
    public float e;
    public HashMap f;
    public ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public SparseArrayCompat f2058h;

    /* renamed from: i  reason: collision with root package name */
    public LongSparseArray f2059i;

    /* renamed from: j  reason: collision with root package name */
    public ArrayList f2060j;
    public Rect k;
    public float l;
    public float m;
    public float n;

    /* renamed from: o  reason: collision with root package name */
    public int f2061o = 0;

    public final void a(String str) {
        b.b(str);
        this.b.add(str);
    }

    public final float b() {
        return (float) ((long) ((c() / this.n) * 1000.0f));
    }

    public final float c() {
        return this.m - this.l;
    }

    public final Map d() {
        float c5 = g.c();
        if (c5 != this.e) {
            for (Map.Entry entry : this.d.entrySet()) {
                HashMap hashMap = this.d;
                String str = (String) entry.getKey();
                y yVar = (y) entry.getValue();
                float f5 = this.e / c5;
                int i2 = (int) (((float) yVar.f2099a) * f5);
                int i7 = (int) (((float) yVar.b) * f5);
                y yVar2 = new y(i2, i7, yVar.f2100c, yVar.d, yVar.e);
                Bitmap bitmap = yVar.f;
                if (bitmap != null) {
                    yVar2.f = Bitmap.createScaledBitmap(bitmap, i2, i7, true);
                }
                hashMap.put(str, yVar2);
            }
        }
        this.e = c5;
        return this.d;
    }

    public final i e(String str) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            i iVar = (i) this.g.get(i2);
            String str2 = iVar.f99a;
            if (str2.equalsIgnoreCase(str) || (str2.endsWith("\r") && str2.substring(0, str2.length() - 1).equalsIgnoreCase(str))) {
                return iVar;
            }
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("LottieComposition:\n");
        Iterator it = this.f2060j.iterator();
        while (it.hasNext()) {
            sb2.append(((F0.i) it.next()).a("\t"));
        }
        return sb2.toString();
    }
}
