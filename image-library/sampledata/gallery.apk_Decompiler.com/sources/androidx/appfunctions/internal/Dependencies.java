package androidx.appfunctions.internal;

import Ad.C0721b;
import L1.d;
import android.util.Log;
import kotlin.Metadata;
import me.f;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048FX\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\u000e\u001a\u0004\u0018\u00010\n8@X\u0002¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u000f8FX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00148@X\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/appfunctions/internal/Dependencies;", "", "<init>", "()V", "Landroidx/appfunctions/internal/TranslatorSelector;", "translatorSelector$delegate", "Lme/f;", "getTranslatorSelector", "()Landroidx/appfunctions/internal/TranslatorSelector;", "translatorSelector", "Landroidx/appfunctions/internal/SchemaAppFunctionInventory;", "schemaAppFunctionInventory$delegate", "getSchemaAppFunctionInventory$appfunctions", "()Landroidx/appfunctions/internal/SchemaAppFunctionInventory;", "schemaAppFunctionInventory", "Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "aggregatedAppFunctionInventory$delegate", "getAggregatedAppFunctionInventory", "()Landroidx/appfunctions/internal/AggregatedAppFunctionInventory;", "aggregatedAppFunctionInventory", "Landroidx/appfunctions/internal/AppFunctionInventory;", "appFunctionInventory$delegate", "getAppFunctionInventory$appfunctions", "()Landroidx/appfunctions/internal/AppFunctionInventory;", "appFunctionInventory", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Dependencies {
    public static final Dependencies INSTANCE = new Dependencies();
    private static final f aggregatedAppFunctionInventory$delegate = d.q(new C0721b(16));
    private static final f appFunctionInventory$delegate = d.q(new C0721b(17));
    private static final f schemaAppFunctionInventory$delegate = d.q(new C0721b(15));
    private static final f translatorSelector$delegate = d.q(new C0721b(14));

    private Dependencies() {
    }

    /* access modifiers changed from: private */
    public static final AggregatedAppFunctionInventory aggregatedAppFunctionInventory_delegate$lambda$0() {
        try {
            return (AggregatedAppFunctionInventory) ClassUtilsKt.findImpl(AggregatedAppFunctionInventory.class, "$", "_Impl");
        } catch (Exception e) {
            Log.d("AppFunctions", "Cannot find AggregatedAppFunctionInventory implementation", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final AppFunctionInventory appFunctionInventory_delegate$lambda$0() {
        Dependencies dependencies = INSTANCE;
        if (dependencies.getAggregatedAppFunctionInventory() != null) {
            return dependencies.getAggregatedAppFunctionInventory();
        }
        return dependencies.getSchemaAppFunctionInventory$appfunctions();
    }

    /* access modifiers changed from: private */
    public static final SchemaAppFunctionInventory schemaAppFunctionInventory_delegate$lambda$0() {
        try {
            return (SchemaAppFunctionInventory) ClassUtilsKt.findImpl(SchemaAppFunctionInventory.class, "$", "_Impl");
        } catch (Exception unused) {
            Log.d("AppFunctions", "Cannot find SchemaAppFunctionInventory implementation");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final TranslatorSelector translatorSelector_delegate$lambda$0() {
        try {
            return (TranslatorSelector) ClassUtilsKt.findImpl(TranslatorSelector.class, "", "Impl");
        } catch (Exception unused) {
            Log.d("AppFunctions", "Cannot find TranslatorSelectorImpl");
            return new NullTranslatorSelector();
        }
    }

    public final AggregatedAppFunctionInventory getAggregatedAppFunctionInventory() {
        return (AggregatedAppFunctionInventory) aggregatedAppFunctionInventory$delegate.getValue();
    }

    public final AppFunctionInventory getAppFunctionInventory$appfunctions() {
        return (AppFunctionInventory) appFunctionInventory$delegate.getValue();
    }

    public final SchemaAppFunctionInventory getSchemaAppFunctionInventory$appfunctions() {
        return (SchemaAppFunctionInventory) schemaAppFunctionInventory$delegate.getValue();
    }

    public final TranslatorSelector getTranslatorSelector() {
        return (TranslatorSelector) translatorSelector$delegate.getValue();
    }
}
