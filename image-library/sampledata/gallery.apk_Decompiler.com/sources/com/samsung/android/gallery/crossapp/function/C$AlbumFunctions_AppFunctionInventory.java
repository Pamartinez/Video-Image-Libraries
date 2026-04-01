package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.internal.AppFunctionInventory;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionDataTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionDeprecationMetadata;
import androidx.appfunctions.metadata.AppFunctionIntTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionPendingIntentTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionReferenceTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import androidx.appfunctions.metadata.AppFunctionStringTypeMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.mobileservice.profile.Privacy;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.i;
import ne.C1195m;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0003789B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\u000204X\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u0006:"}, d2 = {"com/samsung/android/gallery/crossapp/function/$AlbumFunctions_AppFunctionInventory", "Landroidx/appfunctions/internal/AppFunctionInventory;", "<init>", "()V", "functionIdToMetadataMap", "", "", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getFunctionIdToMetadataMap", "()Ljava/util/Map;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT", "Landroidx/appfunctions/metadata/AppFunctionIntTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_YEAR", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MONTH", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_DAY", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEZONE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEOFDAY", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE", "componentsMetadataDataTypesMap", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject", "ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject", "ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInventory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$AlbumFunctions_AppFunctionInventory implements AppFunctionInventory {
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATE;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEOFDAY;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEZONE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_DAY;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MONTH;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_YEAR;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionComponentsMetadata componentsMetadata;
    private final Map<String, AppFunctionDataTypeMetadata> componentsMetadataDataTypesMap;
    private final Map<String, CompileTimeAppFunctionMetadata> functionIdToMetadataMap = z.b0(new i("com.samsung.android.gallery.crossapp.function.AlbumFunctions#showAlbum", ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()), new i("com.samsung.android.gallery.crossapp.function.AlbumFunctions#findAlbums", ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()), new i("com.samsung.android.gallery.crossapp.function.AlbumFunctions#createAlbum", ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()));

    @Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/samsung/android/gallery/crossapp/function/$AlbumFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_CREATEALBUMPARAMS_REFERENCE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "CREATEALBUMPARAMS_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "REFERENCE_RESPONSE_VALUE_TYPE", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionParameterMetadata CREATEALBUMPARAMS_PARAMETER_METADATA;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject();
        private static final AppFunctionReferenceTypeMetadata PARAMETER_METADATA_CREATEALBUMPARAMS_REFERENCE_DATA_TYPE;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionReferenceTypeMetadata REFERENCE_RESPONSE_VALUE_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "createAlbum", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams", false, "");
            PARAMETER_METADATA_CREATEALBUMPARAMS_REFERENCE_DATA_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("createAlbumParams", true, appFunctionReferenceTypeMetadata, "");
            CREATEALBUMPARAMS_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.Album", false, "");
            REFERENCE_RESPONSE_VALUE_TYPE = appFunctionReferenceTypeMetadata2;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionReferenceTypeMetadata2, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.AlbumFunctions#createAlbum", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsCreateAlbumMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"com/samsung/android/gallery/crossapp/function/$AlbumFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_FINDALBUMSPARAMS_REFERENCE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "FINDALBUMSPARAMS_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE", "ARRAY_RESPONSE_VALUE_TYPE", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionArrayTypeMetadata ARRAY_RESPONSE_VALUE_TYPE;
        private static final AppFunctionReferenceTypeMetadata ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        private static final AppFunctionParameterMetadata FINDALBUMSPARAMS_PARAMETER_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject();
        private static final AppFunctionReferenceTypeMetadata PARAMETER_METADATA_FINDALBUMSPARAMS_REFERENCE_DATA_TYPE;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "findAlbums", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams", false, "");
            PARAMETER_METADATA_FINDALBUMSPARAMS_REFERENCE_DATA_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("findAlbumsParams", true, appFunctionReferenceTypeMetadata, "");
            FINDALBUMSPARAMS_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.Album", false, "");
            ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE = appFunctionReferenceTypeMetadata2;
            AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata = new AppFunctionArrayTypeMetadata(appFunctionReferenceTypeMetadata2, false, "");
            ARRAY_RESPONSE_VALUE_TYPE = appFunctionArrayTypeMetadata;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionArrayTypeMetadata, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.AlbumFunctions#findAlbums", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsFindAlbumsMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"com/samsung/android/gallery/crossapp/function/$AlbumFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_ALBUMID_PRIMITIVE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "ALBUMID_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "PRIMITIVE_RESPONSE_VALUE_TYPE", "Landroidx/appfunctions/metadata/AppFunctionPendingIntentTypeMetadata;", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$AlbumFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject {
        private static final AppFunctionParameterMetadata ALBUMID_PARAMETER_METADATA;
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject();
        private static final AppFunctionStringTypeMetadata PARAMETER_METADATA_ALBUMID_PRIMITIVE_DATA_TYPE;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionPendingIntentTypeMetadata PRIMITIVE_RESPONSE_VALUE_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "showAlbum", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
            PARAMETER_METADATA_ALBUMID_PRIMITIVE_DATA_TYPE = appFunctionStringTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("albumId", true, appFunctionStringTypeMetadata, "");
            ALBUMID_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionPendingIntentTypeMetadata appFunctionPendingIntentTypeMetadata = new AppFunctionPendingIntentTypeMetadata(false, "");
            PRIMITIVE_RESPONSE_VALUE_TYPE = appFunctionPendingIntentTypeMetadata;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionPendingIntentTypeMetadata, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.AlbumFunctions#showAlbum", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionAlbumFunctionsShowAlbumMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    public C$AlbumFunctions_AppFunctionInventory() {
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY = appFunctionStringTypeMetadata;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT = appFunctionIntTypeMetadata;
        Map<String, AppFunctionDataTypeMetadata> b0 = z.b0(new i(Contract.QUERY, appFunctionStringTypeMetadata), new i("maxCount", appFunctionIntTypeMetadata));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP = b0;
        List<String> e02 = C0246a.e0("maxCount");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata = new AppFunctionObjectTypeMetadata(b0, e02, "com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDALBUMSPARAMS_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata2 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_YEAR = appFunctionIntTypeMetadata2;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata3 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MONTH = appFunctionIntTypeMetadata3;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata4 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_DAY = appFunctionIntTypeMetadata4;
        Map<String, AppFunctionDataTypeMetadata> b02 = z.b0(new i("year", appFunctionIntTypeMetadata2), new i("month", appFunctionIntTypeMetadata3), new i("day", appFunctionIntTypeMetadata4));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP = b02;
        List<String> q0 = C1195m.q0("year", "month", "day");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q0;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata2 = new AppFunctionObjectTypeMetadata(b02, q0, "com.google.android.appfunctions.schema.types.v1.Date", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata2;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata5 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS = appFunctionIntTypeMetadata5;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata6 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES = appFunctionIntTypeMetadata6;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata7 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS = appFunctionIntTypeMetadata7;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata8 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS = appFunctionIntTypeMetadata8;
        Map<String, AppFunctionDataTypeMetadata> b03 = z.b0(new i("hours", appFunctionIntTypeMetadata5), new i("minutes", appFunctionIntTypeMetadata6), new i("seconds", appFunctionIntTypeMetadata7), new i("nanos", appFunctionIntTypeMetadata8));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP = b03;
        List<String> q02 = C1195m.q0("hours", "minutes", "seconds", "nanos");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata3 = new AppFunctionObjectTypeMetadata(b03, q02, "com.google.android.appfunctions.schema.types.v1.TimeOfDay", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata3;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata2 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEZONE = appFunctionStringTypeMetadata2;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.Date", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATE = appFunctionReferenceTypeMetadata;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.TimeOfDay", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEOFDAY = appFunctionReferenceTypeMetadata2;
        Map<String, AppFunctionDataTypeMetadata> b04 = z.b0(new i("timeZone", appFunctionStringTypeMetadata2), new i(BuddyContract.Event.DATE, appFunctionReferenceTypeMetadata), new i("timeOfDay", appFunctionReferenceTypeMetadata2));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP = b04;
        List<String> q03 = C1195m.q0("timeZone", BuddyContract.Event.DATE, "timeOfDay");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata4 = new AppFunctionObjectTypeMetadata(b04, q03, "com.google.android.appfunctions.schema.types.v1.DateTime", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata4;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata3 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID = appFunctionStringTypeMetadata3;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata4 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE = appFunctionStringTypeMetadata4;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata3 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.DateTime", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED = appFunctionReferenceTypeMetadata3;
        Map<String, AppFunctionDataTypeMetadata> b05 = z.b0(new i("id", appFunctionStringTypeMetadata3), new i("title", appFunctionStringTypeMetadata4), new i("dateCreated", appFunctionReferenceTypeMetadata3));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_PROPERTIES_MAP = b05;
        List<String> q04 = C1195m.q0("id", "title", "dateCreated");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q04;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata5 = new AppFunctionObjectTypeMetadata(b05, q04, "com.google.android.appfunctions.schema.photos.v1.Album", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_ALBUM_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata5;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata5 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE = appFunctionStringTypeMetadata5;
        Map<String, AppFunctionDataTypeMetadata> a0 = z.a0(new i("title", appFunctionStringTypeMetadata5));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP = a0;
        List<String> e03 = C0246a.e0("title");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata6 = new AppFunctionObjectTypeMetadata(a0, e03, "com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_CREATEALBUMPARAMS_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata6;
        i iVar = new i("com.google.android.appfunctions.schema.photos.v1.FindAlbumsParams", appFunctionObjectTypeMetadata);
        i iVar2 = new i("com.google.android.appfunctions.schema.types.v1.Date", appFunctionObjectTypeMetadata2);
        i iVar3 = new i("com.google.android.appfunctions.schema.types.v1.TimeOfDay", appFunctionObjectTypeMetadata3);
        i iVar4 = new i("com.google.android.appfunctions.schema.types.v1.DateTime", appFunctionObjectTypeMetadata4);
        i iVar5 = iVar;
        i iVar6 = iVar2;
        i iVar7 = iVar3;
        i iVar8 = iVar4;
        Map<String, AppFunctionDataTypeMetadata> b06 = z.b0(iVar5, iVar6, iVar7, iVar8, new i("com.google.android.appfunctions.schema.photos.v1.Album", appFunctionObjectTypeMetadata5), new i("com.google.android.appfunctions.schema.photos.v1.CreateAlbumParams", appFunctionObjectTypeMetadata6));
        this.componentsMetadataDataTypesMap = b06;
        this.componentsMetadata = new AppFunctionComponentsMetadata(b06);
    }

    public AppFunctionComponentsMetadata getComponentsMetadata() {
        return this.componentsMetadata;
    }

    public Map<String, CompileTimeAppFunctionMetadata> getFunctionIdToMetadataMap() {
        return this.functionIdToMetadataMap;
    }
}
