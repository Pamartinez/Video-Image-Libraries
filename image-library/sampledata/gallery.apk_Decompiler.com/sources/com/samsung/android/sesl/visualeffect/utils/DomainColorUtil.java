package com.samsung.android.sesl.visualeffect.utils;

import a.C0068a;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.animation.PathInterpolator;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1202t;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/DomainColorUtil;", "", "<init>", "()V", "Landroid/graphics/Bitmap;", "bitmap", "", "threshold", "downscaledBitmap", "(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;", "color", "Lcom/samsung/android/sesl/visualeffect/utils/DomainColorUtil$ColorPalette;", "palette", "convertColorPalette", "(ILcom/samsung/android/sesl/visualeffect/utils/DomainColorUtil$ColorPalette;)I", "Landroid/view/animation/PathInterpolator;", "interpolator", "calibrateByLightness", "(ILandroid/view/animation/PathInterpolator;)I", "extract", "(Landroid/graphics/Bitmap;Lcom/samsung/android/sesl/visualeffect/utils/DomainColorUtil$ColorPalette;)I", "ColorPalette", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class DomainColorUtil {
    public static final DomainColorUtil INSTANCE = new DomainColorUtil();

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0003`\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/utils/DomainColorUtil$ColorPalette;", "", "divider", "", "<init>", "(Ljava/lang/String;II)V", "getDivider", "()I", "Palette64", "Palette512", "createCountMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ColorPalette {
        Palette64((int) Math.ceil(63.75d)),
        Palette512((int) Math.ceil(31.875d));
        
        private final int divider;

        static {
            ColorPalette[] $values;
            $ENTRIES = c.t($values);
        }

        private ColorPalette(int i2) {
            this.divider = i2;
        }

        public final HashMap<String, Integer> createCountMap() {
            return new HashMap<>();
        }

        public final int getDivider() {
            return this.divider;
        }
    }

    private DomainColorUtil() {
    }

    public static /* synthetic */ int calibrateByLightness$default(DomainColorUtil domainColorUtil, int i2, PathInterpolator pathInterpolator, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            pathInterpolator = new PathInterpolator(0.8f, 0.0f, 0.45f, 0.45f);
        }
        return domainColorUtil.calibrateByLightness(i2, pathInterpolator);
    }

    private final int convertColorPalette(int i2, ColorPalette colorPalette) {
        Color valueOf = Color.valueOf(i2);
        int divider = colorPalette.getDivider();
        float f = (float) divider;
        int W6 = C0068a.W((valueOf.alpha() * 255.0f) / f) * divider;
        int i7 = ScoverState.TYPE_NFC_SMART_COVER;
        if (W6 > 255) {
            W6 = 255;
        }
        int W10 = C0068a.W((valueOf.red() * 255.0f) / f) * divider;
        if (W10 > 255) {
            W10 = 255;
        }
        int W11 = C0068a.W((valueOf.green() * 255.0f) / f) * divider;
        if (W11 > 255) {
            W11 = 255;
        }
        int W12 = C0068a.W((valueOf.blue() * 255.0f) / f) * divider;
        if (W12 <= 255) {
            i7 = W12;
        }
        return Color.argb(W6, W10, W11, i7);
    }

    private final Bitmap downscaledBitmap(Bitmap bitmap, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        while (width * height > i2) {
            width /= 2;
            height /= 2;
        }
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        j.d(createScaledBitmap, "createScaledBitmap(...)");
        return createScaledBitmap;
    }

    public static /* synthetic */ Bitmap downscaledBitmap$default(DomainColorUtil domainColorUtil, Bitmap bitmap, int i2, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            i2 = 40000;
        }
        return domainColorUtil.downscaledBitmap(bitmap, i2);
    }

    public final int calibrateByLightness(int i2, PathInterpolator pathInterpolator) {
        j.e(pathInterpolator, "interpolator");
        Color valueOf = Color.valueOf(i2);
        float alpha = valueOf.alpha();
        float red = valueOf.red() * alpha;
        float green = valueOf.green() * alpha;
        float blue = valueOf.blue() * alpha;
        float f = blue * blue;
        float sqrt = ((float) Math.sqrt((double) (f + ((green * green) + (red * red))))) / ((float) Math.sqrt(3.0d));
        float interpolation = pathInterpolator.getInterpolation(sqrt) / sqrt;
        return Color.valueOf(valueOf.red() * interpolation, valueOf.green() * interpolation, valueOf.blue() * interpolation, alpha).toArgb();
    }

    public final int extract(Bitmap bitmap, ColorPalette colorPalette) {
        j.e(bitmap, "bitmap");
        j.e(colorPalette, "palette");
        Bitmap downscaledBitmap$default = downscaledBitmap$default(this, bitmap, 0, 2, (Object) null);
        HashMap<String, Integer> createCountMap = colorPalette.createCountMap();
        int width = downscaledBitmap$default.getWidth();
        for (int i2 = 0; i2 < width; i2++) {
            int height = downscaledBitmap$default.getHeight();
            for (int i7 = 0; i7 < height; i7++) {
                String hexString = ColorUtil.INSTANCE.toHexString(convertColorPalette(downscaledBitmap$default.getPixel(i2, i7), colorPalette));
                createCountMap.put(hexString, Integer.valueOf(createCountMap.getOrDefault(hexString, 0).intValue() + 1));
            }
        }
        j.e(createCountMap, "<this>");
        int size = createCountMap.size();
        Iterable iterable = C1202t.d;
        if (size != 0) {
            Iterator<Map.Entry<String, Integer>> it = createCountMap.entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry next = it.next();
                if (!it.hasNext()) {
                    iterable = C0246a.e0(new i(next.getKey(), next.getValue()));
                } else {
                    ArrayList arrayList = new ArrayList(createCountMap.size());
                    arrayList.add(new i(next.getKey(), next.getValue()));
                    do {
                        Map.Entry next2 = it.next();
                        arrayList.add(new i(next2.getKey(), next2.getValue()));
                    } while (it.hasNext());
                    iterable = arrayList;
                }
            }
        }
        return Color.parseColor((String) ((Map.Entry) z.e0(C1194l.g1(iterable, new DomainColorUtil$extract$$inlined$sortedByDescending$1())).entrySet().iterator().next()).getKey());
    }
}
