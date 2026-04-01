package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.AppFunctionData;
import androidx.appfunctions.AppFunctionTextResource;
import androidx.appfunctions.C$AppFunctionTextResourceFactory;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"com/samsung/android/gallery/crossapp/function/$FindPhotosResponseFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "Lcom/samsung/android/gallery/crossapp/function/FindPhotosResponse;", "<init>", "()V", "fromAppFunctionData", "appFunctionData", "Landroidx/appfunctions/AppFunctionData;", "toAppFunctionData", "appFunctionSerializable", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$FindPhotosResponseFactory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$FindPhotosResponseFactory implements AppFunctionSerializableFactory<FindPhotosResponse> {
    public final /* bridge */ /* synthetic */ AppFunctionData.Builder getAppFunctionDataBuilder(String str) {
        return super.getAppFunctionDataBuilder(str);
    }

    public final /* bridge */ /* synthetic */ AppFunctionData getAppFunctionDataWithSpec(AppFunctionData appFunctionData, String str) {
        return super.getAppFunctionDataWithSpec(appFunctionData, str);
    }

    public FindPhotosResponse fromAppFunctionData(AppFunctionData appFunctionData) {
        j.e(appFunctionData, "appFunctionData");
        AppFunctionData appFunctionDataWithSpec = super.getAppFunctionDataWithSpec(appFunctionData, "com.samsung.android.gallery.crossapp.function.FindPhotosResponse");
        C$MediaItemFactory _mediaitemfactory = new C$MediaItemFactory();
        C$AppFunctionTextResourceFactory _appfunctiontextresourcefactory = new C$AppFunctionTextResourceFactory();
        List<AppFunctionData> appFunctionDataList = appFunctionDataWithSpec.getAppFunctionDataList("mediaItems");
        if (appFunctionDataList != null) {
            Iterable<AppFunctionData> iterable = appFunctionDataList;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionData fromAppFunctionData : iterable) {
                arrayList.add(_mediaitemfactory.fromAppFunctionData(fromAppFunctionData));
            }
            List<AppFunctionData> appFunctionDataList2 = appFunctionDataWithSpec.getAppFunctionDataList("resources");
            if (appFunctionDataList2 != null) {
                Iterable<AppFunctionData> iterable2 = appFunctionDataList2;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
                for (AppFunctionData fromAppFunctionData2 : iterable2) {
                    arrayList2.add(_appfunctiontextresourcefactory.fromAppFunctionData(fromAppFunctionData2));
                }
                return new FindPhotosResponse(arrayList, arrayList2);
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public AppFunctionData toAppFunctionData(FindPhotosResponse findPhotosResponse) {
        j.e(findPhotosResponse, "appFunctionSerializable");
        C$MediaItemFactory _mediaitemfactory = new C$MediaItemFactory();
        C$AppFunctionTextResourceFactory _appfunctiontextresourcefactory = new C$AppFunctionTextResourceFactory();
        AppFunctionData.Builder appFunctionDataBuilder = super.getAppFunctionDataBuilder("com.samsung.android.gallery.crossapp.function.FindPhotosResponse");
        Iterable<MediaItem> mediaItems = findPhotosResponse.getMediaItems();
        ArrayList arrayList = new ArrayList(C1196n.w0(mediaItems, 10));
        for (MediaItem appFunctionData : mediaItems) {
            arrayList.add(_mediaitemfactory.toAppFunctionData(appFunctionData));
        }
        appFunctionDataBuilder.setAppFunctionDataList("mediaItems", arrayList);
        Iterable<AppFunctionTextResource> resources = findPhotosResponse.getResources();
        ArrayList arrayList2 = new ArrayList(C1196n.w0(resources, 10));
        for (AppFunctionTextResource appFunctionData2 : resources) {
            arrayList2.add(_appfunctiontextresourcefactory.toAppFunctionData(appFunctionData2));
        }
        appFunctionDataBuilder.setAppFunctionDataList("resources", arrayList2);
        return appFunctionDataBuilder.build();
    }
}
