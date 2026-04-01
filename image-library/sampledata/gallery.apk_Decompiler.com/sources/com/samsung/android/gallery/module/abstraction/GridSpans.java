package com.samsung.android.gallery.module.abstraction;

import Qa.a;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridSpans {
    private final String TAG = ("GridSpans@" + Integer.toHexString(hashCode()));
    public boolean hasMonth;
    public boolean hasRealRatio;
    public boolean hasYear;
    public int index;
    String mDebugTag = "n/a";
    final ConcurrentHashMap<Integer, Integer> mHeightMap = new ConcurrentHashMap<>();
    int[] mIndexMonths = {-1, -1};
    int[] mNormalSpans;
    final int[] mSelectableIndexRange = {0, 0};
    int[] mSpanDays = {4};
    int[] mSpanIndex = new int[100];
    int[] mSpanInfo = {1};
    int mSpanMax = 4;
    int mSpanMin = 4;
    int[] mSpanMonths = {7};
    int mSpanYear;
    int[] mSplitSpans;
    public boolean normalOrder;
    public int[] spans = {4};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GridMarginHolder {
        static final int[] heights = {R$dimen.date_location_body_divider_height, R$dimen.date_location_body_divider_height_1depth, R$dimen.date_location_body_divider_height_2depth, R$dimen.date_location_body_divider_height_3depth, R$dimen.date_location_body_divider_height_4depth, R$dimen.date_location_body_divider_height_5depth};
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$computeHeight$2(Context context, Integer num) {
        return Integer.valueOf(calcHeight(context, num.intValue()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$init$0(int i2) {
        if ((i2 & 16) == 0) {
            return true;
        }
        return false;
    }

    public int calcHeight(Context context, int i2) {
        if (!this.normalOrder) {
            i2 = indexMax() - i2;
        }
        return context.getResources().getDimensionPixelOffset(GridMarginHolder.heights[i2]);
    }

    public boolean canPinch(boolean z) {
        return canPinch(z, false);
    }

    public void clear() {
        this.mHeightMap.clear();
    }

    public int clusterOf(int i2) {
        return GridValue.cluster(valueOf(i2));
    }

    public int computeHeight(Context context, int i2) {
        return this.mHeightMap.computeIfAbsent(Integer.valueOf(indexOf(i2)), new a(5, (Object) this, (Object) context)).intValue();
    }

    public int decrease() {
        int i2;
        if (this.normalOrder) {
            i2 = this.index - 1;
        } else {
            i2 = this.index + 1;
        }
        return set(i2);
    }

    public int[] getActiveSpans(boolean z) {
        if (z) {
            int[] iArr = this.mSelectableIndexRange;
            if (iArr[0] > 0 || iArr[1] < indexMax()) {
                int[] iArr2 = this.spans;
                int[] iArr3 = this.mSelectableIndexRange;
                return Arrays.copyOfRange(iArr2, iArr3[0], iArr3[1] + 1);
            }
        }
        return this.spans;
    }

    public boolean inDayByIndex(int i2) {
        return GridValue.isDay(this.mSpanInfo[i2]);
    }

    public int increase() {
        int i2;
        if (this.normalOrder) {
            i2 = this.index + 1;
        } else {
            i2 = this.index - 1;
        }
        return set(i2);
    }

    public int indexMax() {
        return this.mSpanInfo.length - 1;
    }

    public int indexOf(int i2) {
        String str;
        if (i2 > 1048575) {
            int depthOf = GridValue.depthOf(i2);
            if (depthOf >= 0 && depthOf < this.spans.length) {
                return depthOf;
            }
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("indexOf(0x");
            sb2.append(Integer.toHexString(i2));
            sb2.append(")=");
            sb2.append(depthOf);
            sb2.append(" (");
            sb2.append(this.index);
            sb2.append(") ");
            if ((10485760 & i2) > 0) {
                str = "in s=" + StringCompat.toString(this.mSplitSpans);
            } else if ((i2 & 5242880) > 0) {
                str = "in n=" + StringCompat.toString(this.mNormalSpans);
            } else {
                str = "unknown";
            }
            sb2.append(str);
            Log.w(str2, sb2.toString());
            return this.index;
        } else if (i2 > this.mSpanMax) {
            return this.index;
        } else {
            return this.mSpanIndex[i2];
        }
    }

    public int indexOfNearSelectable(int i2) {
        int[] iArr = this.mSelectableIndexRange;
        int i7 = iArr[0];
        if (i2 < i7) {
            return i7;
        }
        return Math.min(i2, iArr[1]);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, java.util.function.IntFunction] */
    /* JADX WARNING: type inference failed for: r8v10, types: [java.lang.Object, java.util.function.IntPredicate] */
    public GridSpans init(int[] iArr, String str, boolean z) {
        this.mSpanInfo = iArr;
        this.mDebugTag = str;
        if (!z) {
            int[] array = Arrays.stream(iArr).filter(new Object()).toArray();
            if (array.length != this.mSpanInfo.length) {
                this.mSpanInfo = array;
            }
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int[] iArr2 = this.mSpanInfo;
            if (i2 < iArr2.length) {
                int i7 = iArr2[i2];
                if ((i7 & 64) == 0) {
                    arrayList.add(Integer.valueOf(i2));
                }
                if ((i7 & 16) > 0) {
                    this.hasRealRatio = true;
                }
                int i8 = i7 & 15;
                if (i8 == 3) {
                    this.hasYear = true;
                } else if (i8 == 2) {
                    this.hasMonth = true;
                }
                i2++;
            } else {
                this.mSelectableIndexRange[0] = ((Integer) arrayList.get(0)).intValue();
                this.mSelectableIndexRange[1] = ((Integer) C0212a.i(arrayList, 1)).intValue();
                String str2 = this.TAG;
                StringBuilder sb2 = new StringBuilder("init");
                String str3 = this.mDebugTag;
                String p6 = C0212a.p(new StringBuilder("["), (String) Arrays.stream(this.mSpanInfo).mapToObj(new Object()).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]");
                StringBuilder sb3 = new StringBuilder("[");
                sb3.append(this.mSelectableIndexRange[0]);
                sb3.append("~");
                sb2.append(Logger.v(str3, p6, C0086a.l(sb3, this.mSelectableIndexRange[1], "]")));
                Log.i(str2, sb2.toString());
                return this;
            }
        }
    }

    public boolean isDecoHide(int i2) {
        int i7 = this.mIndexMonths[0];
        if (i7 < 0) {
            return false;
        }
        if (i2 > 1048575) {
            if ((10485760 & i2) > 0) {
                return (i2 & 4095) >= this.mSplitSpans[i7];
            }
            if ((5242880 & i2) > 0) {
                return (i2 & 4095) >= this.mNormalSpans[i7];
            }
            String str = this.TAG;
            Log.e(str, "isDecoHide wrong spanCount=0x" + Integer.toHexString(i2));
        } else if (i2 > this.mSpanMax) {
            return false;
        }
        return (i2 & 4095) >= this.spans[this.mIndexMonths[0]];
    }

    public void load(int[] iArr, int[] iArr2) {
        this.mNormalSpans = iArr;
        this.mSplitSpans = iArr2;
        String str = this.TAG;
        Log.d(str, "load n=" + StringCompat.toString(this.mNormalSpans) + ", s=" + StringCompat.toString(this.mSplitSpans));
    }

    public boolean selectable(int i2) {
        int[] iArr = this.mSelectableIndexRange;
        if (i2 < iArr[0] || i2 > iArr[1]) {
            return false;
        }
        return true;
    }

    public int[] selectableRange() {
        return this.mSelectableIndexRange;
    }

    public int set(int i2) {
        if (i2 < 0 || i2 > this.spans.length - 1) {
            Log.e((CharSequence) this.TAG, "set skip with wrong value", Integer.valueOf(i2));
            return 0;
        }
        A.a.w(C0086a.o(i2, "set[", "]="), this.spans[i2], this.TAG);
        this.index = i2;
        return this.spans[i2];
    }

    public int[] spanDays() {
        return this.mSpanDays;
    }

    public int spanMax() {
        return this.mSpanMax;
    }

    public int spanMonthMax() {
        int[] iArr = this.mSpanMonths;
        if (iArr.length > 0) {
            return iArr[iArr.length - 1];
        }
        return this.mSpanMax;
    }

    public int spanMonthMin() {
        return this.mSpanMonths[0];
    }

    public int[] spanMonths() {
        return this.mSpanMonths;
    }

    public int spanOf(int i2) {
        return this.spans[i2];
    }

    public int spanYear() {
        return this.mSpanYear;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        StringBuilder sb2 = new StringBuilder("GridSpans{");
        if (this.hasYear) {
            str = "Y";
        } else {
            str = "y";
        }
        sb2.append(str);
        if (this.hasMonth) {
            str2 = "M";
        } else {
            str2 = "m";
        }
        sb2.append(str2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDebugTag);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.normalOrder) {
            str3 = "asc";
        } else {
            str3 = "des";
        }
        sb2.append(str3);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.index);
        sb2.append(",[");
        sb2.append(this.mSpanMin);
        sb2.append("-");
        sb2.append(this.mSpanMax);
        sb2.append("],[");
        sb2.append(this.mSelectableIndexRange[0]);
        sb2.append("-");
        sb2.append(this.mSelectableIndexRange[1]);
        sb2.append("],");
        sb2.append(StringCompat.toString(this.spans));
        sb2.append("}");
        return sb2.toString();
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.util.function.ToIntFunction, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object, java.util.function.IntFunction] */
    public void update(int[] iArr) {
        boolean z;
        boolean z3;
        int[] iArr2;
        boolean z7;
        String str;
        int i2;
        int[] iArr3 = iArr;
        this.spans = iArr3;
        int i7 = 0;
        if (iArr3.length == 1 || (iArr3.length > 1 && iArr3[iArr3.length - 1] > iArr3[iArr3.length - 2])) {
            z = true;
        } else {
            z = false;
        }
        this.normalOrder = z;
        if (iArr3.length != this.mSpanInfo.length) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            int[] iArr4 = new int[iArr3.length];
            this.mSpanInfo = iArr4;
            Arrays.fill(iArr4, 1);
            int[] iArr5 = this.mSelectableIndexRange;
            iArr5[0] = 0;
            iArr5[1] = iArr3.length - 1;
            this.hasYear = false;
            this.hasMonth = false;
            this.hasRealRatio = false;
        }
        ArrayList arrayList = new ArrayList();
        int i8 = 100;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = -1;
        int i14 = -1;
        int i15 = 100;
        while (i10 < iArr3.length) {
            int i16 = iArr3[i10];
            int i17 = this.mSpanInfo[i10] & 15;
            if (i17 == 1) {
                arrayList.add(Integer.valueOf(i16));
                i2 = i7;
            } else if (i17 == 2) {
                if (i12 < i16) {
                    i14 = i10;
                    i12 = i16;
                }
                i2 = i7;
                if (i15 > i16) {
                    i13 = i10;
                    i15 = i16;
                }
            } else {
                i2 = i7;
                if (i17 == 3) {
                    this.mSpanYear = i16;
                }
            }
            if (z3 && iArr3[i10] == 1) {
                int[] iArr6 = this.mSpanInfo;
                iArr6[i10] = iArr6[i10] | 16;
                this.hasRealRatio = true;
            }
            if (i11 < i16) {
                i11 = i16;
            }
            if (i8 > i16) {
                i8 = i16;
            }
            this.mSpanIndex[i16] = i10;
            i10++;
            i7 = i2;
        }
        int i18 = i7;
        this.mSpanMax = i11;
        this.mSpanMin = i8;
        if (i12 > i15) {
            iArr2 = new int[2];
            iArr2[i18] = i15;
            iArr2[1] = i12;
        } else if (i12 == i15) {
            iArr2 = new int[1];
            iArr2[i18] = i15;
        } else {
            iArr2 = new int[i18];
        }
        this.mSpanMonths = iArr2;
        if (iArr2.length > 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.hasMonth = z7;
        this.mSpanDays = arrayList.stream().sorted().mapToInt(new Object()).toArray();
        int[] iArr7 = this.mIndexMonths;
        iArr7[0] = i13;
        iArr7[1] = i14;
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("update ");
        sb2.append(this);
        if (z3) {
            str = C0212a.p(new StringBuilder(" +info=["), (String) Arrays.stream(this.mSpanInfo).mapToObj(new Object()).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)), "]");
        } else {
            str = "";
        }
        sb2.append(str);
        Log.i(str2, sb2.toString());
    }

    public int valueOf() {
        return this.mSpanInfo[this.index];
    }

    public boolean canPinch(boolean z, boolean z3) {
        if (!z3) {
            return canPinch(z, 0, this.spans.length - 1);
        }
        int[] iArr = this.mSelectableIndexRange;
        return canPinch(z, iArr[0], iArr[1]);
    }

    public int spanMax(boolean z) {
        if (!this.normalOrder) {
            return z ? this.mSplitSpans[0] : this.mNormalSpans[0];
        }
        if (z) {
            int[] iArr = this.mSplitSpans;
            return iArr[iArr.length - 1];
        }
        int[] iArr2 = this.mNormalSpans;
        return iArr2[iArr2.length - 1];
    }

    public int spanOf() {
        return spanOf(this.index);
    }

    public int valueOf(int i2) {
        return this.mSpanInfo[indexOf(i2)];
    }

    public boolean canPinch(boolean z, int i2, int i7) {
        if (this.normalOrder) {
            int i8 = this.index;
            return z ? i8 < i7 : i8 > i2;
        }
        int i10 = this.index;
        return z ? i10 > i2 : i10 < i7;
    }

    public boolean isDecoHide(int i2, boolean z) {
        int i7;
        if (this.mSpanMonths.length != 0 && (i7 = this.mIndexMonths[0]) >= 0) {
            if (z) {
                return (i2 & 4095) >= this.mSplitSpans[i7];
            }
            if ((i2 & 4095) >= this.mNormalSpans[i7]) {
                return true;
            }
        }
        return false;
    }
}
