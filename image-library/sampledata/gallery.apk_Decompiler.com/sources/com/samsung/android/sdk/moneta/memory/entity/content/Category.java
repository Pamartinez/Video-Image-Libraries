package com.samsung.android.sdk.moneta.memory.entity.content;

import com.samsung.android.sdk.scs.ai.visual.c2pa.C2paManifestList;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/Category;", "", "className", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "GAME", "ENTERTAINMENT", "SPORTS", "HEALTH_AND_WELLNESS", "PETS", "STYLE_AND_FASHION", "FOOD", "TRAVEL", "UNKNOWN", "DEFAULT", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Category {
    GAME("Game"),
    ENTERTAINMENT("Entertainment"),
    SPORTS("Sports"),
    HEALTH_AND_WELLNESS("HealthAndWellness"),
    PETS("Pets"),
    STYLE_AND_FASHION("StyleAndFashion"),
    FOOD("Food"),
    TRAVEL("Travel"),
    UNKNOWN(C2paManifestList.UNKNOWN_VALUE),
    DEFAULT("");
    
    public static final Companion Companion = null;
    private final String className;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/Category$Companion;", "", "<init>", "()V", "create", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Category;", "value", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Category create(String str) {
            Object obj;
            j.e(str, "value");
            Iterator it = Category.getEntries().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (j.a(((Category) obj).getClassName(), str)) {
                    break;
                }
            }
            Category category = (Category) obj;
            if (category != null) {
                return category;
            }
            throw new IllegalArgumentException("Unsupported category: ".concat(str));
        }

        private Companion() {
        }
    }

    static {
        Category[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private Category(String str) {
        this.className = str;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final String getClassName() {
        return this.className;
    }
}
