package androidx.appfunctions.internal;

import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/appfunctions/internal/NullTranslatorSelector;", "Landroidx/appfunctions/internal/TranslatorSelector;", "<init>", "()V", "getTranslator", "Landroidx/appfunctions/internal/Translator;", "schemaMetadata", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NullTranslatorSelector implements TranslatorSelector {
    public Translator getTranslator(AppFunctionSchemaMetadata appFunctionSchemaMetadata) {
        j.e(appFunctionSchemaMetadata, "schemaMetadata");
        return null;
    }
}
