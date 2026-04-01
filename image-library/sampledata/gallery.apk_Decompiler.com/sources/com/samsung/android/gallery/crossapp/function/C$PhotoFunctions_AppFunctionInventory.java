package com.samsung.android.gallery.crossapp.function;

import androidx.appfunctions.internal.AppFunctionInventory;
import androidx.appfunctions.metadata.AppFunctionArrayTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionBooleanTypeMetadata;
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
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import me.i;
import ne.C1195m;
import ne.z;
import o1.C0246a;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0004TUVWB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010L\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010P\u001a\u00020QX\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010S¨\u0006X"}, d2 = {"com/samsung/android/gallery/crossapp/function/$PhotoFunctions_AppFunctionInventory", "Landroidx/appfunctions/internal/AppFunctionInventory;", "<init>", "()V", "functionIdToMetadataMap", "", "", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getFunctionIdToMetadataMap", "()Ljava/util/Map;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_YEAR", "Landroidx/appfunctions/metadata/AppFunctionIntTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MONTH", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_DAY", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP", "Landroidx/appfunctions/metadata/AppFunctionDataTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEZONE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEOFDAY", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARTTIME", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ENDTIME", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED", "Landroidx/appfunctions/metadata/AppFunctionBooleanTypeMetadata;", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MEDIAITEMID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE", "ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI", "ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP", "ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST", "ANDROID_NET_URI_OBJECT_DATA_TYPE", "componentsMetadataDataTypesMap", "componentsMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "getComponentsMetadata", "()Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject", "ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject", "ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject", "ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* renamed from: com.samsung.android.gallery.crossapp.function.$PhotoFunctions_AppFunctionInventory  reason: invalid class name */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C$PhotoFunctions_AppFunctionInventory implements AppFunctionInventory {
    private final AppFunctionObjectTypeMetadata ANDROID_NET_URI_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI;
    private final List<String> ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ENDTIME;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY;
    private final AppFunctionBooleanTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARTTIME;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION;
    private final AppFunctionBooleanTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TYPE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MEDIAITEMID;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED;
    private final AppFunctionReferenceTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
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
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionBooleanTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionStringTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionObjectTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE;
    private final Map<String, AppFunctionDataTypeMetadata> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS;
    private final AppFunctionIntTypeMetadata COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS;
    private final List<String> COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST;
    private final AppFunctionComponentsMetadata componentsMetadata;
    private final Map<String, AppFunctionDataTypeMetadata> componentsMetadataDataTypesMap;
    private final Map<String, CompileTimeAppFunctionMetadata> functionIdToMetadataMap = z.b0(new i("com.samsung.android.gallery.crossapp.function.PhotoFunctions#findMediaItems", ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()), new i("com.samsung.android.gallery.crossapp.function.PhotoFunctions#showMediaItem", ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()), new i("com.samsung.android.gallery.crossapp.function.PhotoFunctions#updateMediaItem", ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()), new i("com.samsung.android.gallery.crossapp.function.PhotoFunctions#getMediaItemContentUris", ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject.INSTANCE.getAPP_FUNCTION_METADATA()));

    @Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"com/samsung/android/gallery/crossapp/function/$PhotoFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_FINDMEDIAITEMSPARAMS_REFERENCE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "FINDMEDIAITEMSPARAMS_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE", "ARRAY_RESPONSE_VALUE_TYPE", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$PhotoFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionArrayTypeMetadata ARRAY_RESPONSE_VALUE_TYPE;
        private static final AppFunctionReferenceTypeMetadata ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        private static final AppFunctionParameterMetadata FINDMEDIAITEMSPARAMS_PARAMETER_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject();
        private static final AppFunctionReferenceTypeMetadata PARAMETER_METADATA_FINDMEDIAITEMSPARAMS_REFERENCE_DATA_TYPE;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "findMediaItems", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams", false, "");
            PARAMETER_METADATA_FINDMEDIAITEMSPARAMS_REFERENCE_DATA_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("findMediaItemsParams", true, appFunctionReferenceTypeMetadata, "");
            FINDMEDIAITEMSPARAMS_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.MediaItem", false, "");
            ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE = appFunctionReferenceTypeMetadata2;
            AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata = new AppFunctionArrayTypeMetadata(appFunctionReferenceTypeMetadata2, false, "");
            ARRAY_RESPONSE_VALUE_TYPE = appFunctionArrayTypeMetadata;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionArrayTypeMetadata, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.PhotoFunctions#findMediaItems", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsFindMediaItemsMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"com/samsung/android/gallery/crossapp/function/$PhotoFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE_PRIMITIVE_ITEM_TYPE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionArrayTypeMetadata;", "MEDIAITEMIDS_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "ARRAY_RESPONSE_VALUE_TYPE", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$PhotoFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionArrayTypeMetadata ARRAY_RESPONSE_VALUE_TYPE;
        private static final AppFunctionReferenceTypeMetadata ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject();
        private static final AppFunctionParameterMetadata MEDIAITEMIDS_PARAMETER_METADATA;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionArrayTypeMetadata PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE;
        private static final AppFunctionStringTypeMetadata PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE_PRIMITIVE_ITEM_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "getMediaItemContentUris", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
            PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE_PRIMITIVE_ITEM_TYPE = appFunctionStringTypeMetadata;
            AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata = new AppFunctionArrayTypeMetadata(appFunctionStringTypeMetadata, false, "");
            PARAMETER_METADATA_MEDIAITEMIDS_ARRAY_DATA_TYPE = appFunctionArrayTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("mediaItemIds", true, appFunctionArrayTypeMetadata, "");
            MEDIAITEMIDS_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("android.net.Uri", false, "");
            ARRAY_RESPONSE_VALUE_TYPE_REFERENCE_ITEM_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionArrayTypeMetadata appFunctionArrayTypeMetadata2 = new AppFunctionArrayTypeMetadata(appFunctionReferenceTypeMetadata, false, "");
            ARRAY_RESPONSE_VALUE_TYPE = appFunctionArrayTypeMetadata2;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionArrayTypeMetadata2, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.PhotoFunctions#getMediaItemContentUris", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsGetMediaItemContentUrisMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"com/samsung/android/gallery/crossapp/function/$PhotoFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_MEDIAITEMID_PRIMITIVE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionStringTypeMetadata;", "MEDIAITEMID_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "PRIMITIVE_RESPONSE_VALUE_TYPE", "Landroidx/appfunctions/metadata/AppFunctionPendingIntentTypeMetadata;", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$PhotoFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject();
        private static final AppFunctionParameterMetadata MEDIAITEMID_PARAMETER_METADATA;
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionStringTypeMetadata PARAMETER_METADATA_MEDIAITEMID_PRIMITIVE_DATA_TYPE;
        private static final AppFunctionPendingIntentTypeMetadata PRIMITIVE_RESPONSE_VALUE_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "showMediaItem", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
            PARAMETER_METADATA_MEDIAITEMID_PRIMITIVE_DATA_TYPE = appFunctionStringTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("mediaItemId", true, appFunctionStringTypeMetadata, "");
            MEDIAITEMID_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionPendingIntentTypeMetadata appFunctionPendingIntentTypeMetadata = new AppFunctionPendingIntentTypeMetadata(false, "");
            PRIMITIVE_RESPONSE_VALUE_TYPE = appFunctionPendingIntentTypeMetadata;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionPendingIntentTypeMetadata, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.PhotoFunctions#showMediaItem", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsShowMediaItemMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/samsung/android/gallery/crossapp/function/$PhotoFunctions_AppFunctionInventory.ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject", "", "<init>", "()V", "SCHEMA_METADATA", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "PARAMETER_METADATA_UPDATEMEDIAITEMPARAMS_REFERENCE_DATA_TYPE", "Landroidx/appfunctions/metadata/AppFunctionReferenceTypeMetadata;", "UPDATEMEDIAITEMPARAMS_PARAMETER_METADATA", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "PARAMETER_METADATA_LIST", "", "REFERENCE_RESPONSE_VALUE_TYPE", "RESPONSE_METADATA", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "DEPRECATION_METADATA", "Landroidx/appfunctions/metadata/AppFunctionDeprecationMetadata;", "APP_FUNCTION_METADATA", "Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "getAPP_FUNCTION_METADATA", "()Landroidx/appfunctions/metadata/CompileTimeAppFunctionMetadata;", "crossapp_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: com.samsung.android.gallery.crossapp.function.$PhotoFunctions_AppFunctionInventory$ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject {
        private static final CompileTimeAppFunctionMetadata APP_FUNCTION_METADATA;
        private static final AppFunctionDeprecationMetadata DEPRECATION_METADATA;
        public static final ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject INSTANCE = new ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject();
        private static final List<AppFunctionParameterMetadata> PARAMETER_METADATA_LIST;
        private static final AppFunctionReferenceTypeMetadata PARAMETER_METADATA_UPDATEMEDIAITEMPARAMS_REFERENCE_DATA_TYPE;
        private static final AppFunctionReferenceTypeMetadata REFERENCE_RESPONSE_VALUE_TYPE;
        private static final AppFunctionResponseMetadata RESPONSE_METADATA;
        private static final AppFunctionSchemaMetadata SCHEMA_METADATA;
        private static final AppFunctionParameterMetadata UPDATEMEDIAITEMPARAMS_PARAMETER_METADATA;

        static {
            AppFunctionSchemaMetadata appFunctionSchemaMetadata = new AppFunctionSchemaMetadata(Privacy.KEY_PHOTOS, "updateMediaItem", 1);
            SCHEMA_METADATA = appFunctionSchemaMetadata;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams", false, "");
            PARAMETER_METADATA_UPDATEMEDIAITEMPARAMS_REFERENCE_DATA_TYPE = appFunctionReferenceTypeMetadata;
            AppFunctionParameterMetadata appFunctionParameterMetadata = new AppFunctionParameterMetadata("updateMediaItemParams", true, appFunctionReferenceTypeMetadata, "");
            UPDATEMEDIAITEMPARAMS_PARAMETER_METADATA = appFunctionParameterMetadata;
            List<AppFunctionParameterMetadata> e02 = C0246a.e0(appFunctionParameterMetadata);
            PARAMETER_METADATA_LIST = e02;
            AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.photos.v1.MediaItem", false, "");
            REFERENCE_RESPONSE_VALUE_TYPE = appFunctionReferenceTypeMetadata2;
            AppFunctionResponseMetadata appFunctionResponseMetadata = new AppFunctionResponseMetadata(appFunctionReferenceTypeMetadata2, "");
            RESPONSE_METADATA = appFunctionResponseMetadata;
            AppFunctionDeprecationMetadata appFunctionDeprecationMetadata = new AppFunctionDeprecationMetadata("This function is deprecated and will be removed in future versions.");
            DEPRECATION_METADATA = appFunctionDeprecationMetadata;
            APP_FUNCTION_METADATA = new CompileTimeAppFunctionMetadata("com.samsung.android.gallery.crossapp.function.PhotoFunctions#updateMediaItem", true, appFunctionSchemaMetadata, e02, appFunctionResponseMetadata, (AppFunctionComponentsMetadata) null, (String) null, appFunctionDeprecationMetadata, 96, (e) null);
        }

        private ComSamsungAndroidGalleryCrossappFunctionPhotoFunctionsUpdateMediaItemMetadataObject() {
        }

        public final CompileTimeAppFunctionMetadata getAPP_FUNCTION_METADATA() {
            return APP_FUNCTION_METADATA;
        }
    }

    public C$PhotoFunctions_AppFunctionInventory() {
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_YEAR = appFunctionIntTypeMetadata;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata2 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_MONTH = appFunctionIntTypeMetadata2;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata3 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP_DAY = appFunctionIntTypeMetadata3;
        Map<String, AppFunctionDataTypeMetadata> b0 = z.b0(new i("year", appFunctionIntTypeMetadata), new i("month", appFunctionIntTypeMetadata2), new i("day", appFunctionIntTypeMetadata3));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_PROPERTIES_MAP = b0;
        List<String> q0 = C1195m.q0("year", "month", "day");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q0;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata = new AppFunctionObjectTypeMetadata(b0, q0, "com.google.android.appfunctions.schema.types.v1.Date", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATE_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata4 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_HOURS = appFunctionIntTypeMetadata4;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata5 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_MINUTES = appFunctionIntTypeMetadata5;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata6 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_SECONDS = appFunctionIntTypeMetadata6;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata7 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP_NANOS = appFunctionIntTypeMetadata7;
        Map<String, AppFunctionDataTypeMetadata> b02 = z.b0(new i("hours", appFunctionIntTypeMetadata4), new i("minutes", appFunctionIntTypeMetadata5), new i("seconds", appFunctionIntTypeMetadata6), new i("nanos", appFunctionIntTypeMetadata7));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_PROPERTIES_MAP = b02;
        List<String> q02 = C1195m.q0("hours", "minutes", "seconds", "nanos");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata2 = new AppFunctionObjectTypeMetadata(b02, q02, "com.google.android.appfunctions.schema.types.v1.TimeOfDay", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_TIMEOFDAY_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata2;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEZONE = appFunctionStringTypeMetadata;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.Date", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATE = appFunctionReferenceTypeMetadata;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata2 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.TimeOfDay", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP_TIMEOFDAY = appFunctionReferenceTypeMetadata2;
        Map<String, AppFunctionDataTypeMetadata> b03 = z.b0(new i("timeZone", appFunctionStringTypeMetadata), new i(BuddyContract.Event.DATE, appFunctionReferenceTypeMetadata), new i("timeOfDay", appFunctionReferenceTypeMetadata2));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_PROPERTIES_MAP = b03;
        List<String> q03 = C1195m.q0("timeZone", BuddyContract.Event.DATE, "timeOfDay");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata3 = new AppFunctionObjectTypeMetadata(b03, q03, "com.google.android.appfunctions.schema.types.v1.DateTime", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_DATETIME_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata3;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata3 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.DateTime", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARTTIME = appFunctionReferenceTypeMetadata3;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata4 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.DateTime", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ENDTIME = appFunctionReferenceTypeMetadata4;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata2 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_QUERY = appFunctionStringTypeMetadata2;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata3 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION = appFunctionStringTypeMetadata3;
        AppFunctionBooleanTypeMetadata appFunctionBooleanTypeMetadata = new AppFunctionBooleanTypeMetadata(true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED = appFunctionBooleanTypeMetadata;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata4 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID = appFunctionStringTypeMetadata4;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata4 = appFunctionObjectTypeMetadata3;
        AppFunctionIntTypeMetadata appFunctionIntTypeMetadata8 = new AppFunctionIntTypeMetadata(false, "", (Set<Integer>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MAXCOUNT = appFunctionIntTypeMetadata8;
        i iVar = new i("startTime", appFunctionReferenceTypeMetadata3);
        i iVar2 = new i("endTime", appFunctionReferenceTypeMetadata4);
        i iVar3 = new i(Contract.QUERY, appFunctionStringTypeMetadata2);
        i iVar4 = new i("location", appFunctionStringTypeMetadata3);
        i iVar5 = new i("starred", appFunctionBooleanTypeMetadata);
        i iVar6 = iVar;
        Map<String, AppFunctionDataTypeMetadata> b04 = z.b0(iVar6, iVar2, iVar3, iVar4, iVar5, new i("albumId", appFunctionStringTypeMetadata4), new i("maxCount", appFunctionIntTypeMetadata8));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP = b04;
        List<String> e02 = C0246a.e0("maxCount");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e02;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata5 = new AppFunctionObjectTypeMetadata(b04, e02, "com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_FINDMEDIAITEMSPARAMS_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata5;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata5 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ID = appFunctionStringTypeMetadata5;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata6 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TYPE = appFunctionStringTypeMetadata6;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata5 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.DateTime", false, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_DATECREATED = appFunctionReferenceTypeMetadata5;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata7 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE = appFunctionStringTypeMetadata7;
        AppFunctionBooleanTypeMetadata appFunctionBooleanTypeMetadata2 = new AppFunctionBooleanTypeMetadata(true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED = appFunctionBooleanTypeMetadata2;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata8 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_LOCATION = appFunctionStringTypeMetadata8;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata6 = appFunctionObjectTypeMetadata2;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata9 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID = appFunctionStringTypeMetadata9;
        i iVar7 = new i("id", appFunctionStringTypeMetadata5);
        i iVar8 = new i("type", appFunctionStringTypeMetadata6);
        i iVar9 = new i("dateCreated", appFunctionReferenceTypeMetadata5);
        i iVar10 = new i("title", appFunctionStringTypeMetadata7);
        i iVar11 = new i("starred", appFunctionBooleanTypeMetadata2);
        i iVar12 = iVar10;
        i iVar13 = iVar11;
        Map<String, AppFunctionDataTypeMetadata> b05 = z.b0(iVar7, iVar8, iVar9, iVar12, iVar13, new i("location", appFunctionStringTypeMetadata8), new i("albumId", appFunctionStringTypeMetadata9));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_PROPERTIES_MAP = b05;
        List<String> q04 = C1195m.q0("id", "type", "dateCreated");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = q04;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata7 = new AppFunctionObjectTypeMetadata(b05, q04, "com.google.android.appfunctions.schema.photos.v1.MediaItem", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_MEDIAITEM_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata7;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata10 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE = appFunctionStringTypeMetadata10;
        Map<String, AppFunctionDataTypeMetadata> a0 = z.a0(new i("value", appFunctionStringTypeMetadata10));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_PROPERTIES_MAP = a0;
        List<String> e03 = C0246a.e0("value");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e03;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata8 = new AppFunctionObjectTypeMetadata(a0, e03, "com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING__OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata8;
        AppFunctionBooleanTypeMetadata appFunctionBooleanTypeMetadata3 = new AppFunctionBooleanTypeMetadata(true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE = appFunctionBooleanTypeMetadata3;
        Map<String, AppFunctionDataTypeMetadata> a02 = z.a0(new i("value", appFunctionBooleanTypeMetadata3));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP = a02;
        List<String> e04 = C0246a.e0("value");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e04;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata9 = new AppFunctionObjectTypeMetadata(a02, e04, "com.google.android.appfunctions.schema.types.v1.SetField<kotlin.Boolean?>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_BOOLEAN_NULLABLE__OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata9;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata11 = new AppFunctionStringTypeMetadata(true, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP_VALUE = appFunctionStringTypeMetadata11;
        Map<String, AppFunctionDataTypeMetadata> a03 = z.a0(new i("value", appFunctionStringTypeMetadata11));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_PROPERTIES_MAP = a03;
        List<String> e05 = C0246a.e0("value");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e05;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata10 = new AppFunctionObjectTypeMetadata(a03, e05, "com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String?>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_TYPES_V1_SETFIELD_KOTLIN_STRING_NULLABLE__OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata10;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata12 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_MEDIAITEMID = appFunctionStringTypeMetadata12;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata6 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_TITLE = appFunctionReferenceTypeMetadata6;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata7 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.Boolean?>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_STARRED = appFunctionReferenceTypeMetadata7;
        AppFunctionReferenceTypeMetadata appFunctionReferenceTypeMetadata8 = new AppFunctionReferenceTypeMetadata("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String?>", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP_ALBUMID = appFunctionReferenceTypeMetadata8;
        Map<String, AppFunctionDataTypeMetadata> b06 = z.b0(new i("mediaItemId", appFunctionStringTypeMetadata12), new i("title", appFunctionReferenceTypeMetadata6), new i("starred", appFunctionReferenceTypeMetadata7), new i("albumId", appFunctionReferenceTypeMetadata8));
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_PROPERTIES_MAP = b06;
        List<String> e06 = C0246a.e0("mediaItemId");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e06;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata11 = new AppFunctionObjectTypeMetadata(b06, e06, "com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams", true, "");
        this.COM_GOOGLE_ANDROID_APPFUNCTIONS_SCHEMA_PHOTOS_V1_UPDATEMEDIAITEMPARAMS_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata11;
        AppFunctionStringTypeMetadata appFunctionStringTypeMetadata13 = new AppFunctionStringTypeMetadata(false, "", (Set<String>) null);
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP_URI = appFunctionStringTypeMetadata13;
        Map<String, AppFunctionDataTypeMetadata> a04 = z.a0(new i(OCRServiceConstant.KEY_PARAM_URI, appFunctionStringTypeMetadata13));
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_PROPERTIES_MAP = a04;
        List<String> e07 = C0246a.e0(OCRServiceConstant.KEY_PARAM_URI);
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE_REQUIRED_PROPERTIES_LIST = e07;
        AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata12 = new AppFunctionObjectTypeMetadata(a04, e07, "android.net.Uri", true, "");
        this.ANDROID_NET_URI_OBJECT_DATA_TYPE = appFunctionObjectTypeMetadata12;
        i iVar14 = new i("com.google.android.appfunctions.schema.types.v1.Date", appFunctionObjectTypeMetadata);
        i iVar15 = new i("com.google.android.appfunctions.schema.types.v1.TimeOfDay", appFunctionObjectTypeMetadata6);
        i iVar16 = new i("com.google.android.appfunctions.schema.types.v1.DateTime", appFunctionObjectTypeMetadata4);
        i iVar17 = new i("com.google.android.appfunctions.schema.photos.v1.FindMediaItemsParams", appFunctionObjectTypeMetadata5);
        i iVar18 = new i("com.google.android.appfunctions.schema.photos.v1.MediaItem", appFunctionObjectTypeMetadata7);
        i iVar19 = new i("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String>", appFunctionObjectTypeMetadata8);
        i iVar20 = new i("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.Boolean?>", appFunctionObjectTypeMetadata9);
        i iVar21 = new i("com.google.android.appfunctions.schema.types.v1.SetField<kotlin.String?>", appFunctionObjectTypeMetadata10);
        i iVar22 = iVar14;
        i iVar23 = iVar15;
        i iVar24 = iVar16;
        i iVar25 = iVar17;
        i iVar26 = iVar18;
        Map<String, AppFunctionDataTypeMetadata> b07 = z.b0(iVar22, iVar23, iVar24, iVar25, iVar26, iVar19, iVar20, iVar21, new i("com.google.android.appfunctions.schema.photos.v1.UpdateMediaItemParams", appFunctionObjectTypeMetadata11), new i("android.net.Uri", appFunctionObjectTypeMetadata12));
        this.componentsMetadataDataTypesMap = b07;
        this.componentsMetadata = new AppFunctionComponentsMetadata(b07);
    }

    public AppFunctionComponentsMetadata getComponentsMetadata() {
        return this.componentsMetadata;
    }

    public Map<String, CompileTimeAppFunctionMetadata> getFunctionIdToMetadataMap() {
        return this.functionIdToMetadataMap;
    }
}
