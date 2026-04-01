package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.internal.AppFunctionInventory;
import androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDeprecationMetadata;
import androidx.appfunctions.metadata.AppFunctionIntTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import androidx.appfunctions.metadata.AppFunctionStringTypeMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.i;
import ne.C1195m;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020%X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0004¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u000203X\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u00105¨\u00067"}, d2 = {"com/samsung/android/gallery/crossapp/function/$SearchFunctions_AppFunctionInventory", "Landroidx/appfunctions/internal/AppFunctionInventory;", "<init>", "()V", "functionIdToMetadataMap", "", "", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getFunctionIdToMetadataMap", "()Ljava/util/Map;", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MIMETYPE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_CONTENT", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI", "ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP", "ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "ANDROID_NET_URI_OBJECT_DATA_TYPE", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_MODEFLAGS", "Landroidx/appfunctions/metadata/AppFunctionIntTypeMetadata;", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DESCRIPTION", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES_REFERENCE_ITEM_TYPE", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_REQUIRED_PROPERTIES_LIST", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS_REFERENCE_ITEM_TYPE", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_REQUIRED_PROPERTIES_LIST", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST", "COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;", "componentsMetadataDataTypesMap", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInventory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$SearchFunctions_AppFunctionInventory implements AppFunctionInventory {
    private final AppFunctionObjectTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_CONTENT;
    private final AppFunctionStringTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MIMETYPE;
    private final List<String> ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionIntTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_MODEFLAGS;
    private final AppFunctionReferenceTypeMetadata ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI;
    private final List<String> ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata ANDROID_NET_URI_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI;
    private final List<String> ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionAllOfTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE;
    private final List<AppFunctionDataTypeMetadata> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST;
    private final AppFunctionObjectTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0;
    private final Map<String, AppFunctionDataTypeMetadata> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP;
    private final AppFunctionArrayTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES;
    private final AppFunctionReferenceTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES_REFERENCE_ITEM_TYPE;
    private final List<String> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1;
    private final Map<String, AppFunctionDataTypeMetadata> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP;
    private final AppFunctionArrayTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS;
    private final AppFunctionReferenceTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS_REFERENCE_ITEM_TYPE;
    private final List<String> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DESCRIPTION;
    private final AppFunctionReferenceTypeMetadata COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI;
    private final List<String> COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionComponentsMetadata componentsMetadata;
    private final Map<String, AppFunctionDataTypeMetadata> componentsMetadataDataTypesMap;
    private final Map<String, CompileTimeAppFunctionMetadata> functionIdToMetadataMap = z.a0(new i("com.samsung.android.gallery.crossapp.function.SearchFunctions#findPhotos", ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()));

    @Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"com/samsung/android/gallery/crossapp/function/$SearchFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_QUERYSTRING_PRIMITIVE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "QUERYSTRING_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_MAXCOUNT_PRIMITIVE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionIntTypeMetadata;", "MAXCOUNT_PARAMETER_METADATA", "PARAMETER_METADATA_LIST", "", "REFERENCE_RESPONSE_VALUE_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$SearchFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject();
        private static final AppFunctionParameterMetadata MAXCOUNT_PARAMETER_METADATA;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionIntTypeMetadata PARAMETER_METADATA_MAXCOUNT_PRIMITIVE_DATA_TYPE;
        private static final AppFunctionStringTypeMetadata PARAMETER_METADATA_QUERYSTRING_PRIMITIVE_DATA_TYPE;
        private static final AppFunctionParameterMetadata QUERYSTRING_PARAMETER_METADATA;
        private static final AppFunctionReferenceTypeMetadata REFERENCE_RESPONSE_VALUE_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA = null;

        static {
            AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
            PARAMETER_METADATA_QUERYSTRING_PRIMITIVE_DATA_TYPE = appFunctionStringTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("queryString", true, appFunctionStringTypeMetadata, "");
            QUERYSTRING_PARAMETER_METADATA = appFunctionParameterMetadata;
            AppFunctionIntTypeMetadata appFunctionIntTypeMetadata = new AppFunctionIntTypeMetadata(true, "", (Set<Integer>) null);
            PARAMETER_METADATA_MAXCOUNT_PRIMITIVE_DATA_TYPE = appFunctionIntTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata2 = new AppFunctionParameterMetadata("maxCount", false, appFunctionIntTypeMetadata, "");
            MAXCOUNT_PARAMETER_METADATA = appFunctionParameterMetadata2;
            List<AppFunctionParameterMetadata> q0 = C1195m.q0(appFunctionParameterMetadata, appFunctionParameterMetadata2);
            PARAMETER_METADATA_LIST = q0;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.samsung.android.gallery.crossapp.function.FindPhotosResponse", false, "");
            REFERENCE_RESPONSE_VALUE_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionReferenceTypeMetadata, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.SearchFunctions#findPhotos", true, SCHEMA_METADATA, q0, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, (AppFunctionDeprecationMetadata) null, 224, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionSearchFunctionsFindPhotosMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    public C$SearchFunctions_AppFunctionInventory() {
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MIMETYPE = appFunctionStringTypeMetadata;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata2 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP_CONTENT = appFunctionStringTypeMetadata2;
        Map<String, AppFunctionDataTypeMetadata> b0 = z.b0(new i(FileApiContract.Parameter.MIME_TYPE, appFunctionStringTypeMetadata), new i("content", appFunctionStringTypeMetadata2));
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_PROPERTIES_MAP = b0;
        List<String> q0 = C1195m.q0(FileApiContract.Parameter.MIME_TYPE, "content");
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q0;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata = new AppFunctionObjectTypeMetadata(b0, q0, "androidx.appfunctions.AppFunctionTextResource", true, "");
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONTEXTRESOURCE_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata3 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI = appFunctionStringTypeMetadata3;
        Map<String, AppFunctionDataTypeMetadata> a0 = z.a0(new i(OCRServiceConstant.KEY_PARAM_URI, appFunctionStringTypeMetadata3));
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP = a0;
        List<String> e02 = C0246a.e0(OCRServiceConstant.KEY_PARAM_URI);
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata2 = new AppFunctionObjectTypeMetadata(a0, e02, "android.net.Uri", true, "");
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata2;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("android.net.Uri", false, "");
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI = appFunctionReferenceTypeMetadata;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP_MODEFLAGS = appFunctionIntTypeMetadata;
        Map<String, AppFunctionDataTypeMetadata> b02 = z.b0(new i(OCRServiceConstant.KEY_PARAM_URI, appFunctionReferenceTypeMetadata), new i("modeFlags", appFunctionIntTypeMetadata));
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_PROPERTIES_MAP = b02;
        List<String> q02 = C1195m.q0(OCRServiceConstant.KEY_PARAM_URI, "modeFlags");
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata3 = new AppFunctionObjectTypeMetadata(b02, q02, "androidx.appfunctions.AppFunctionUriGrant", true, "");
        this.ANDROIDX_APPFUNCTIONS_APPFUNCTIONURIGRANT_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata3;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata4 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DESCRIPTION = appFunctionStringTypeMetadata4;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("androidx.appfunctions.AppFunctionUriGrant", false, "");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI = appFunctionReferenceTypeMetadata2;
        Map<String, AppFunctionDataTypeMetadata> b03 = z.b0(new i("description", appFunctionStringTypeMetadata4), new i(OCRServiceConstant.KEY_PARAM_URI, appFunctionReferenceTypeMetadata2));
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP = b03;
        List<String> q03 = C1195m.q0("description", OCRServiceConstant.KEY_PARAM_URI);
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata4 = new AppFunctionObjectTypeMetadata(b03, q03, "com.samsung.android.gallery.crossapp.function.MediaItem", true, "\n Represents a single media item (photo or video) in the search results.\n");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_MEDIAITEM_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata4;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata3 = new AppFunctionReferenceTypeMetadata("androidx.appfunctions.AppFunctionTextResource", false, "");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES_REFERENCE_ITEM_TYPE = appFunctionReferenceTypeMetadata3;
        AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata = new AppFunctionArrayTypeMetadata(appFunctionReferenceTypeMetadata3, false, "");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP_RESOURCES = appFunctionArrayTypeMetadata;
        Map<String, AppFunctionDataTypeMetadata> a02 = z.a0(new i("resources", appFunctionArrayTypeMetadata));
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_PROPERTIES_MAP = a02;
        List<String> e03 = C0246a.e0("resources");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0_REQUIRED_PROPERTIES_LIST = e03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata5 = new AppFunctionObjectTypeMetadata(a02, e03, "androidx.appfunctions.AppFunctionResourceContainer", true, "\n The response containing found photos and videos.\n");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_0 = appFunctionObjectTypeMetadata5;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata4 = new AppFunctionReferenceTypeMetadata("com.samsung.android.gallery.crossapp.function.MediaItem", false, "");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS_REFERENCE_ITEM_TYPE = appFunctionReferenceTypeMetadata4;
        AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata2 = new AppFunctionArrayTypeMetadata(appFunctionReferenceTypeMetadata4, false, "");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP_MEDIAITEMS = appFunctionArrayTypeMetadata2;
        Map<String, AppFunctionDataTypeMetadata> a03 = z.a0(new i("mediaItems", appFunctionArrayTypeMetadata2));
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_PROPERTIES_MAP = a03;
        List<String> e04 = C0246a.e0("mediaItems");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1_REQUIRED_PROPERTIES_LIST = e04;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata6 = new AppFunctionObjectTypeMetadata(a03, e04, "com.samsung.android.gallery.crossapp.function.FindPhotosResponse", true, "\n The response containing found photos and videos.\n");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST_ITEM_1 = appFunctionObjectTypeMetadata6;
        List<AppFunctionDataTypeMetadata> q04 = C1195m.q0(appFunctionObjectTypeMetadata5, appFunctionObjectTypeMetadata6);
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE_MATCH_ALL_LIST = q04;
        AppFunctionAllOfTypeMetadata appFunctionAllOfTypeMetadata = new AppFunctionAllOfTypeMetadata(q04, "com.samsung.android.gallery.crossapp.function.FindPhotosResponse", true, "\n The response containing found photos and videos.\n");
        this.COM_SAMSUNG_ANDROID_GALLERY_CROSSAPP_FUNCTION_FINDPHOTOSRESPONSE_ALL_OF_DATA_TYPE = appFunctionAllOfTypeMetadata;
        Map<String, AppFunctionDataTypeMetadata> b04 = z.b0(new i("androidx.appfunctions.AppFunctionTextResource", appFunctionObjectTypeMetadata), new i("android.net.Uri", appFunctionObjectTypeMetadata2), new i("androidx.appfunctions.AppFunctionUriGrant", appFunctionObjectTypeMetadata3), new i("com.samsung.android.gallery.crossapp.function.MediaItem", appFunctionObjectTypeMetadata4), new i("com.samsung.android.gallery.crossapp.function.FindPhotosResponse", appFunctionAllOfTypeMetadata));
        this.componentsMetadataDataTypesMap = b04;
        this.componentsMetadata = new AppFunctionComponentsMetadata(b04);
    }

    public AppFunctionComponentsMetadata getComponentsMetadata() {
        return this.componentsMetadata;
    }

    public Map<String, CompileTimeAppFunctionMetadata> getFunctionIdToMetadataMap() {
        return this.functionIdToMetadataMap;
    }
}
