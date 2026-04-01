package com.samsung.android.app.sdk.deepsky.textextraction.entity;

import L2.a;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.util.EntityUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u00020\u0006*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\n\u001a\u00020\u0006*\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityScoreGenerator;", "", "<init>", "()V", "Landroid/graphics/Point;", "to", "", "distance", "(Landroid/graphics/Point;Landroid/graphics/Point;)D", "Landroid/graphics/Rect;", "maxDistance", "(Landroid/graphics/Rect;)D", "value", "maxValue", "", "normalize", "(DD)F", "rect", "Lme/x;", "checkRect", "(Landroid/graphics/Rect;)V", "", "text", "Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;", "entityType", "imageRect", "measureEntityScore", "(Ljava/lang/String;Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityType;Landroid/graphics/Rect;Landroid/graphics/Rect;)F", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EntityScoreGenerator {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/entity/EntityScoreGenerator$Companion;", "", "<init>", "()V", "ENTITY_SCORE_CENTER_OF_IMAGE", "", "ENTITY_SCORE_NORMAL", "ENTITY_SCORE_LOW", "DISTANCE_SCORE_IGNORED", "RATIO_SCORE_ENTITY", "RATIO_SCORE_DISTANCE", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.PHONE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.EMAIL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType r1 = com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityType.URL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.entity.EntityScoreGenerator.WhenMappings.<clinit>():void");
        }
    }

    private final void checkRect(Rect rect) {
        if (rect.width() < 1) {
            throw new IllegalArgumentException("width < 1");
        } else if (rect.height() < 1) {
            throw new IllegalArgumentException("height < 1");
        } else if (rect.left > rect.right) {
            throw new IllegalArgumentException("left > right");
        } else if (rect.top > rect.bottom) {
            throw new IllegalArgumentException("top > bottom");
        }
    }

    private final double distance(Point point, Point point2) {
        return Math.sqrt(Math.pow(((double) point.y) - ((double) point2.y), 2.0d) + Math.pow(((double) point.x) - ((double) point2.x), 2.0d));
    }

    private final double maxDistance(Rect rect) {
        return Math.max((double) rect.width(), (double) rect.height());
    }

    private final float normalize(double d, double d2) {
        return (float) (Math.abs(d2 - d) / d2);
    }

    public final float measureEntityScore(String str, EntityType entityType, Rect rect, Rect rect2) {
        Object obj;
        j.e(str, "text");
        j.e(entityType, "entityType");
        j.e(rect, "rect");
        float f = 0.0f;
        if (EntityUtils.INSTANCE.isDateTimeEntity(entityType)) {
            return 0.0f;
        }
        if (rect2 != null) {
            try {
                checkRect(rect2);
                obj = x.f4917a;
            } catch (Throwable th) {
                obj = a.l(th);
            }
            if (obj instanceof me.j) {
                obj = null;
            }
            if (((x) obj) != null) {
                if (rect.contains(rect2.centerX(), rect2.centerY())) {
                    return 1.0f;
                }
                f = normalize(distance(new Point(rect2.centerX(), rect2.centerY()), new Point(rect.centerX(), rect.centerY())), maxDistance(rect2));
            }
        }
        float f5 = 0.8f;
        if (WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()] == 1 && str.length() <= 4) {
            f5 = 0.7f;
        }
        return (f * 0.1f) + (f5 * 0.9f);
    }
}
