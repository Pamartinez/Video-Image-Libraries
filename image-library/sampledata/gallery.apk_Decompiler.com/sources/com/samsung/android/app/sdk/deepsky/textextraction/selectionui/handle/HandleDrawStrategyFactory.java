package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle;

import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.EndHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.HandleDrawStrategy;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.MovingEndHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.MovingRtlEndHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.MovingRtlStartHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.MovingStartHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.RtlEndHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.RtlStartHandle;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.draw.StartHandle;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\r"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/HandleDrawStrategyFactory;", "", "<init>", "()V", "createDrawStrategy", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/draw/HandleDrawStrategy;", "handleType", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$HandleType;", "movingState", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/handle/Handle$MovingState;", "isMovingHandleEnabled", "", "isLeftToRight", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class HandleDrawStrategyFactory {
    public static final HandleDrawStrategyFactory INSTANCE = new HandleDrawStrategyFactory();

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle$HandleType[] r0 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle.HandleType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle$HandleType r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle.HandleType.START     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle$HandleType r1 = com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.Handle.HandleType.END     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.selectionui.handle.HandleDrawStrategyFactory.WhenMappings.<clinit>():void");
        }
    }

    private HandleDrawStrategyFactory() {
    }

    public final HandleDrawStrategy createDrawStrategy(Handle.HandleType handleType, Handle.MovingState movingState, boolean z, boolean z3) {
        j.e(handleType, "handleType");
        j.e(movingState, "movingState");
        if (!z || movingState != Handle.MovingState.MOVING) {
            if (z3) {
                int i2 = WhenMappings.$EnumSwitchMapping$0[handleType.ordinal()];
                if (i2 == 1) {
                    return new StartHandle();
                }
                if (i2 == 2) {
                    return new EndHandle();
                }
                throw new RuntimeException();
            }
            int i7 = WhenMappings.$EnumSwitchMapping$0[handleType.ordinal()];
            if (i7 == 1) {
                return new RtlStartHandle();
            }
            if (i7 == 2) {
                return new RtlEndHandle();
            }
            throw new RuntimeException();
        } else if (z3) {
            int i8 = WhenMappings.$EnumSwitchMapping$0[handleType.ordinal()];
            if (i8 == 1) {
                return new MovingStartHandle();
            }
            if (i8 == 2) {
                return new MovingEndHandle();
            }
            throw new RuntimeException();
        } else {
            int i10 = WhenMappings.$EnumSwitchMapping$0[handleType.ordinal()];
            if (i10 == 1) {
                return new MovingRtlStartHandle();
            }
            if (i10 == 2) {
                return new MovingRtlEndHandle();
            }
            throw new RuntimeException();
        }
    }
}
