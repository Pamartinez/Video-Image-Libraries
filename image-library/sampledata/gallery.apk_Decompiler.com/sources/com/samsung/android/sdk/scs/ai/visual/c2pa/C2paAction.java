package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b \b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"¨\u0006#"}, d2 = {"Lcom/samsung/android/sdk/scs/ai/visual/c2pa/C2paAction;", "", "str", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getStr", "()Ljava/lang/String;", "C2PA_COLOR_ADJUSTMENTS", "C2PA_CONVERTED", "C2PA_COPIED", "C2PA_CREATED", "C2PA_CROPPED", "C2PA_DELETED", "C2PA_DRAWING", "C2PA_EDITED", "C2PA_EDITED_METADATA", "C2PA_FILTERED", "C2PA_OPENED", "C2PA_ORIENTATION", "C2PA_FORMATTED", "C2PA_VERSION_UPDATED", "C2PA_PRINTED", "C2PA_PUBLISHED", "C2PA_MANAGED", "C2PA_PRODUCED", "C2PA_RESIZED", "C2PA_SAVED", "C2PA_TRANSCODED", "C2PA_REPACKAGED", "C2PA_PLACED", "C2PA_REDACTED", "C2PA_REMOVED", "C2PA_UNKNOWN", "C2PA_WATERMARKED", "scs-ai-4.0.53_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C2paAction {
    C2PA_COLOR_ADJUSTMENTS("c2pa.color_adjustments"),
    C2PA_CONVERTED("c2pa.converted"),
    C2PA_COPIED("c2pa.copied"),
    C2PA_CREATED("c2pa.created"),
    C2PA_CROPPED("c2pa.cropped"),
    C2PA_DELETED("c2pa.deleted"),
    C2PA_DRAWING("c2pa.drawing"),
    C2PA_EDITED("c2pa.edited"),
    C2PA_EDITED_METADATA("c2pa.edited.metadata"),
    C2PA_FILTERED("c2pa.filtered"),
    C2PA_OPENED("c2pa.opened"),
    C2PA_ORIENTATION("c2pa.orientation"),
    C2PA_FORMATTED("c2pa.formatted"),
    C2PA_VERSION_UPDATED("c2pa.version_updated"),
    C2PA_PRINTED("c2pa.printed"),
    C2PA_PUBLISHED("c2pa.published"),
    C2PA_MANAGED("c2pa.managed"),
    C2PA_PRODUCED("c2pa.produced"),
    C2PA_RESIZED("c2pa.resized"),
    C2PA_SAVED("c2pa.saved"),
    C2PA_TRANSCODED("c2pa.transcoded"),
    C2PA_REPACKAGED("c2pa.repackaged"),
    C2PA_PLACED("c2pa.placed"),
    C2PA_REDACTED("c2pa.redacted"),
    C2PA_REMOVED("c2pa.removed"),
    C2PA_UNKNOWN("c2pa.unknown"),
    C2PA_WATERMARKED("c2pa.watermarked");
    
    private final String str;

    static {
        C2paAction[] $values;
        $ENTRIES = c.t($values);
    }

    private C2paAction(String str2) {
        this.str = str2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getStr() {
        return this.str;
    }
}
