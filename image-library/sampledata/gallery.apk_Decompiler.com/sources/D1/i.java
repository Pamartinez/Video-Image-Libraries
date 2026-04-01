package D1;

import De.a;
import H0.F;
import He.t;
import I0.b;
import J0.f;
import Nf.c;
import R2.e;
import R2.h;
import R2.k;
import R2.l;
import R2.q;
import R2.r;
import android.content.Context;
import android.graphics.Color;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements b, F, a, e {
    public int d;

    public /* synthetic */ i(int i2) {
        this.d = i2;
    }

    public int a(Context context, String str, boolean z) {
        return 0;
    }

    public int b(Context context, String str) {
        return this.d;
    }

    public void c(h hVar) {
        h(hVar);
    }

    public void d(q qVar) {
        h(qVar);
    }

    public Object f(Object obj, t tVar) {
        c cVar = (c) obj;
        j.e(cVar, "thisRef");
        j.e(tVar, "property");
        return cVar.d.get(this.d);
    }

    public Object g(I0.c cVar, float f) {
        boolean z;
        int i2;
        int i7;
        int i8;
        float f5;
        float e;
        float f8;
        ArrayList arrayList = new ArrayList();
        int i10 = 1;
        int i11 = 0;
        if (cVar.n() == b.BEGIN_ARRAY) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            cVar.a();
        }
        while (cVar.h()) {
            arrayList.add(Float.valueOf((float) cVar.j()));
        }
        int i12 = 2;
        if (arrayList.size() == 4 && ((Float) arrayList.get(0)).floatValue() == 1.0f) {
            arrayList.set(0, Float.valueOf(0.0f));
            arrayList.add(Float.valueOf(1.0f));
            arrayList.add((Float) arrayList.get(1));
            arrayList.add((Float) arrayList.get(2));
            arrayList.add((Float) arrayList.get(3));
            this.d = 2;
        }
        if (z) {
            cVar.f();
        }
        if (this.d == -1) {
            this.d = arrayList.size() / 4;
        }
        int i13 = this.d;
        float[] fArr = new float[i13];
        int[] iArr = new int[i13];
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (true) {
            i2 = this.d * 4;
            if (i14 >= i2) {
                break;
            }
            int i17 = i14 / 4;
            double floatValue = (double) ((Float) arrayList.get(i14)).floatValue();
            int i18 = i11;
            int i19 = i14 % 4;
            if (i19 == 0) {
                if (i17 > 0) {
                    float f10 = (float) floatValue;
                    if (fArr[i17 - 1] >= f10) {
                        fArr[i17] = f10 + 0.01f;
                    }
                }
                fArr[i17] = (float) floatValue;
            } else if (i19 == i10) {
                i15 = (int) (floatValue * 255.0d);
            } else if (i19 == 2) {
                i16 = (int) (floatValue * 255.0d);
            } else if (i19 == 3) {
                iArr[i17] = Color.argb(ScoverState.TYPE_NFC_SMART_COVER, i15, i16, (int) (floatValue * 255.0d));
            }
            i14++;
            i11 = i18;
            i10 = 1;
        }
        int i20 = i11;
        E0.c cVar2 = new E0.c(iArr, fArr);
        if (arrayList.size() <= i2) {
            return cVar2;
        }
        int size = (arrayList.size() - i2) / 2;
        float[] fArr2 = new float[size];
        float[] fArr3 = new float[size];
        int i21 = i20;
        while (i2 < arrayList.size()) {
            if (i2 % 2 == 0) {
                fArr2[i21] = ((Float) arrayList.get(i2)).floatValue();
            } else {
                fArr3[i21] = ((Float) arrayList.get(i2)).floatValue();
                i21++;
            }
            i2++;
        }
        float[] fArr4 = cVar2.f126a;
        if (fArr4.length == 0) {
            fArr4 = fArr2;
        } else if (size != 0) {
            int length = fArr4.length + size;
            float[] fArr5 = new float[length];
            int i22 = i20;
            int i23 = i22;
            int i24 = i23;
            int i25 = i24;
            while (i22 < length) {
                float f11 = Float.NaN;
                if (i24 < fArr4.length) {
                    f8 = fArr4[i24];
                } else {
                    f8 = Float.NaN;
                }
                if (i25 < size) {
                    f11 = fArr2[i25];
                }
                if (Float.isNaN(f11) || f8 < f11) {
                    fArr5[i22] = f8;
                    i24++;
                } else if (Float.isNaN(f8) || f11 < f8) {
                    fArr5[i22] = f11;
                    i25++;
                } else {
                    fArr5[i22] = f8;
                    i24++;
                    i25++;
                    i23++;
                }
                i22++;
            }
            if (i23 == 0) {
                fArr4 = fArr5;
            } else {
                fArr4 = Arrays.copyOf(fArr5, length - i23);
            }
        }
        int length2 = fArr4.length;
        int[] iArr2 = new int[length2];
        int i26 = i20;
        while (i26 < length2) {
            float f12 = fArr4[i26];
            int binarySearch = Arrays.binarySearch(fArr, f12);
            int binarySearch2 = Arrays.binarySearch(fArr2, f12);
            if (binarySearch < 0 || binarySearch2 > 0) {
                if (binarySearch2 < 0) {
                    binarySearch2 = -(binarySearch2 + 1);
                }
                float f13 = fArr3[binarySearch2];
                if (i13 < i12 || f12 == fArr[i20]) {
                    i7 = iArr[i20];
                } else {
                    int i27 = 1;
                    while (i27 < i13) {
                        float f14 = fArr[i27];
                        if (f14 < f12 && i27 != i13 - 1) {
                            i27++;
                        } else if (i27 != i13 - 1 || f12 < f14) {
                            int i28 = i27 - 1;
                            float f15 = fArr[i28];
                            int u = com.samsung.context.sdk.samsunganalytics.internal.sender.c.u((f12 - f15) / (f14 - f15), iArr[i28], iArr[i27]);
                            i7 = Color.argb((int) (f13 * 255.0f), Color.red(u), Color.green(u), Color.blue(u));
                        } else {
                            i7 = Color.argb((int) (f13 * 255.0f), Color.red(iArr[i27]), Color.green(iArr[i27]), Color.blue(iArr[i27]));
                        }
                    }
                    throw new IllegalArgumentException("Unreachable code.");
                }
                iArr2[i26] = i7;
            } else {
                int i29 = iArr[binarySearch];
                if (size < i12 || f12 <= fArr2[i20]) {
                    i8 = Color.argb((int) (fArr3[i20] * 255.0f), Color.red(i29), Color.green(i29), Color.blue(i29));
                } else {
                    int i30 = 1;
                    while (i30 < size) {
                        float f16 = fArr2[i30];
                        int i31 = (f16 > f12 ? 1 : (f16 == f12 ? 0 : -1));
                        if (i31 < 0) {
                            f5 = 255.0f;
                            if (i30 != size - 1) {
                                i30++;
                            }
                        } else {
                            f5 = 255.0f;
                        }
                        if (i31 <= 0) {
                            e = fArr3[i30];
                        } else {
                            int i32 = i30 - 1;
                            float f17 = fArr2[i32];
                            e = f.e(fArr3[i32], fArr3[i30], (f12 - f17) / (f16 - f17));
                        }
                        i8 = Color.argb((int) (e * f5), Color.red(i29), Color.green(i29), Color.blue(i29));
                    }
                    throw new IllegalArgumentException("Unreachable code.");
                }
                iArr2[i26] = i8;
            }
            i26++;
            i12 = 2;
        }
        return new E0.c(iArr2, fArr4);
    }

    public void h(R2.f fVar) {
        int c5;
        k kVar = fVar.f;
        if (kVar != null && (c5 = kVar.c() + kVar.d) > this.d) {
            this.d = c5;
        }
        l lVar = fVar.g;
        int length = lVar.e.length;
        for (int i2 = 0; i2 < length; i2++) {
            k kVar2 = (k) lVar.d(i2);
            int c6 = kVar2.c() + kVar2.d;
            if (c6 > this.d) {
                this.d = c6;
            }
        }
    }

    public void i(r rVar) {
        h(rVar);
    }

    public void l(R2.i iVar) {
        h(iVar);
    }
}
