package androidx.appfunctions.internal.serializableproxies;

import android.net.Uri;
import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"androidx/appfunctions/internal/serializableproxies/$UriFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Landroid/net/Uri;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* renamed from: androidx.appfunctions.internal.serializableproxies.$UriFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$UriFactory implements AppFunctionSerializableFactory<Uri> {
    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public Uri fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        String stringOrNull = super.getAppFunctionDataWithSpec(appFunctionData, "android.net.Uri").getStringOrNull(OCRServiceConstant.KEY_PARAM_URI);
        if (stringOrNull != null) {
            return new AppFunctionUri(stringOrNull).toUri();
        }
        throw new IllegalStateException("Required value was null.");
    }

    public AppFunctionData toAppFunctionData(Uri uri) {
        j.e(uri, "appFunctionSerializable");
        AppFunctionUri fromUri = AppFunctionUri.Companion.fromUri(uri);
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("android.net.Uri");
        appFunctionDataBuilder.setString(OCRServiceConstant.KEY_PARAM_URI, fromUri.getUri());
        return appFunctionDataBuilder.build();
    }
}
