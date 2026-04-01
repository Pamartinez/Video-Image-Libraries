package androidx.appfunctions;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import com.samsung.scsp.media.file.FileApiContract;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"androidx/appfunctions/$AppFunctionTextResourceFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Landroidx/appfunctions/AppFunctionTextResource;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* renamed from: androidx.appfunctions.$AppFunctionTextResourceFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AppFunctionTextResourceFactory implements AppFunctionSerializableFactory<AppFunctionTextResource> {
    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public AppFunctionTextResource fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "androidx.appfunctions.AppFunctionTextResource");
        String string = appFunctionDataWithSpec.getString(FileApiContract.Parameter.MIME_TYPE);
        if (string != null) {
            String string2 = appFunctionDataWithSpec.getString("content");
            if (string2 != null) {
                return new AppFunctionTextResource(string, string2);
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public AppFunctionData toAppFunctionData(AppFunctionTextResource appFunctionTextResource) {
        j.e(appFunctionTextResource, "appFunctionSerializable");
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("androidx.appfunctions.AppFunctionTextResource");
        appFunctionDataBuilder.setString(FileApiContract.Parameter.MIME_TYPE, appFunctionTextResource.getMimeType());
        appFunctionDataBuilder.setString("content", appFunctionTextResource.getContent());
        return appFunctionDataBuilder.build();
    }
}
