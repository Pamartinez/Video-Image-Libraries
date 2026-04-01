package androidx.appfunctions;

import Tf.n;
import android.app.PendingIntent;
import android.app.appsearch.GenericDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.ext.SdkExtensions;
import android.util.Log;
import androidx.appfunctions.internal.AppFunctionSerializableFactory;
import androidx.appfunctions.metadata.AppFunctionAllOfTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionObjectTypeMetadata;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import c0.C0086a;
import com.samsung.android.gallery.plugins.portrait.t;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1192j;
import ne.C1193k;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;

@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0007\u0018\u0000 }2\u00020\u0001:\u0002~}B#\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\nJ\u0015\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0012J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0018J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u001eJ\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\"\u0010#J\u001d\u0010\"\u001a\u00020!2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020!¢\u0006\u0004\b\"\u0010$J\u0019\u0010%\u001a\u0004\u0018\u00010!2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b%\u0010&J\u0015\u0010(\u001a\u00020'2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b(\u0010)J\u001d\u0010(\u001a\u00020'2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020'¢\u0006\u0004\b(\u0010*J\u0019\u0010+\u001a\u0004\u0018\u00010'2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b-\u0010.J\u0019\u0010/\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b/\u0010.J\u0017\u00100\u001a\u0004\u0018\u00010\u00002\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b0\u00101J\u0017\u00103\u001a\u0004\u0018\u0001022\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u0004\u0018\u0001022\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b5\u00104J\u0017\u00107\u001a\u0004\u0018\u0001062\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b7\u00108J\u0017\u0010:\u001a\u0004\u0018\u0001092\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b:\u0010;J\u0017\u0010=\u001a\u0004\u0018\u00010<2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b=\u0010>J\u0017\u0010@\u001a\u0004\u0018\u00010?2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b@\u0010AJ\u0017\u0010C\u001a\u0004\u0018\u00010B2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\bC\u0010DJ\u0017\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\bF\u0010GJ\u0019\u0010H\u001a\u0004\u0018\u00010E2\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\bH\u0010GJ\u001d\u0010J\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010I2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\bJ\u0010KJ\u001d\u0010L\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010I2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\bL\u0010KJ\u001d\u0010M\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010I2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\bM\u0010KJ\u000f\u0010N\u001a\u00020\u000bH\u0016¢\u0006\u0004\bN\u0010OJ%\u0010S\u001a\u00028\u0000\"\b\b\u0000\u0010P*\u00020\u00012\f\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000Q¢\u0006\u0004\bS\u0010TJ!\u0010S\u001a\u00028\u0000\"\b\b\u0000\u0010P*\u00020\u00012\u0006\u0010U\u001a\u00020\u000bH\u0007¢\u0006\u0004\bS\u0010VJ\u001f\u0010]\u001a\u00020\u00002\u0006\u0010X\u001a\u00020W2\u0006\u0010Z\u001a\u00020YH\u0000¢\u0006\u0004\b[\u0010\\J%\u0010]\u001a\u00020\u00002\f\u0010_\u001a\b\u0012\u0004\u0012\u00020^0I2\u0006\u0010Z\u001a\u00020YH\u0000¢\u0006\u0004\b[\u0010`J#\u0010e\u001a\u00020c2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020b\u0012\u0004\u0012\u00020c0aH\u0007¢\u0006\u0004\be\u0010fJ\u0017\u0010h\u001a\u00020\r2\u0006\u0010g\u001a\u00020\u001bH\u0002¢\u0006\u0004\bh\u0010iJ\u0017\u0010k\u001a\u00020\r2\u0006\u0010j\u001a\u00020'H\u0002¢\u0006\u0004\bk\u0010lJ1\u0010n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010P*\u00020\u00012\u0006\u0010\f\u001a\u00020\u000b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00028\u00000QH\u0002¢\u0006\u0004\bn\u0010oR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000X\u0004¢\u0006\f\n\u0004\b\u0003\u0010p\u001a\u0004\bq\u0010rR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0004¢\u0006\f\n\u0004\b\u0005\u0010s\u001a\u0004\bt\u0010uR\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0004¢\u0006\f\n\u0004\b\u0007\u0010v\u001a\u0004\bw\u0010xR\u0017\u0010y\u001a\u00020\u000b8\u0007¢\u0006\f\n\u0004\by\u0010z\u001a\u0004\b{\u0010OR\u0011\u0010U\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b|\u0010O¨\u0006"}, d2 = {"Landroidx/appfunctions/AppFunctionData;", "", "Landroidx/appfunctions/AppFunctionDataSpec;", "spec", "Landroid/app/appsearch/GenericDocument;", "genericDocument", "Landroid/os/Bundle;", "extras", "<init>", "(Landroidx/appfunctions/AppFunctionDataSpec;Landroid/app/appsearch/GenericDocument;Landroid/os/Bundle;)V", "(Landroid/app/appsearch/GenericDocument;Landroid/os/Bundle;)V", "", "key", "", "containsKey", "(Ljava/lang/String;)Z", "getBoolean", "defaultValue", "(Ljava/lang/String;Z)Z", "getBooleanOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "", "getFloat", "(Ljava/lang/String;)F", "(Ljava/lang/String;F)F", "getFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "", "getDouble", "(Ljava/lang/String;)D", "(Ljava/lang/String;D)D", "getDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "", "getInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "getIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "", "getLong", "(Ljava/lang/String;)J", "(Ljava/lang/String;J)J", "getLongOrNull", "(Ljava/lang/String;)Ljava/lang/Long;", "getString", "(Ljava/lang/String;)Ljava/lang/String;", "getStringOrNull", "getAppFunctionData", "(Ljava/lang/String;)Landroidx/appfunctions/AppFunctionData;", "Landroid/app/PendingIntent;", "getPendingIntent", "(Ljava/lang/String;)Landroid/app/PendingIntent;", "getPendingIntentOrNull", "", "getBooleanArray", "(Ljava/lang/String;)[Z", "", "getFloatArray", "(Ljava/lang/String;)[F", "", "getDoubleArray", "(Ljava/lang/String;)[D", "", "getIntArray", "(Ljava/lang/String;)[I", "", "getLongArray", "(Ljava/lang/String;)[J", "", "getByteArray", "(Ljava/lang/String;)[B", "getByteArrayOrNull", "", "getStringList", "(Ljava/lang/String;)Ljava/util/List;", "getAppFunctionDataList", "getPendingIntentList", "toString", "()Ljava/lang/String;", "T", "Ljava/lang/Class;", "serializableClass", "deserialize", "(Ljava/lang/Class;)Ljava/lang/Object;", "qualifiedName", "(Ljava/lang/String;)Ljava/lang/Object;", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "objectTypeMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentMetadata", "replaceSpecWith$appfunctions", "(Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)Landroidx/appfunctions/AppFunctionData;", "replaceSpecWith", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "parameterMetadata", "(Ljava/util/List;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)Landroidx/appfunctions/AppFunctionData;", "Lkotlin/Function1;", "Landroidx/appfunctions/AppFunctionUriGrant;", "Lme/x;", "visitor", "visitAppFunctionUriGrants", "(LAe/b;)V", "doubleValue", "isDoubleWithinFloatRange", "(D)Z", "longValue", "isLongWithinLongRange", "(J)Z", "arrayClass", "unsafeGetProperty", "(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "Landroidx/appfunctions/AppFunctionDataSpec;", "getSpec$appfunctions", "()Landroidx/appfunctions/AppFunctionDataSpec;", "Landroid/app/appsearch/GenericDocument;", "getGenericDocument$appfunctions", "()Landroid/app/appsearch/GenericDocument;", "Landroid/os/Bundle;", "getExtras$appfunctions", "()Landroid/os/Bundle;", "id", "Ljava/lang/String;", "getId", "getQualifiedName", "Companion", "Builder", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppFunctionData {
    public static final Companion Companion = new Companion((e) null);
    public static final AppFunctionData EMPTY;
    private final Bundle extras;
    private final GenericDocument genericDocument;
    private final String id;
    private final AppFunctionDataSpec spec;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J \u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014\"\b\b\u0000\u0010\u0015*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J&\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0018\"\b\b\u0000\u0010\u0015*\u00020\u00012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014H\u0002J\u0014\u0010\u001a\u001a\u00020\u000f2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J-\u0010\u001b\u001a\u00020\u001c\"\b\b\u0000\u0010\u0015*\u00020\u00012\u0006\u0010\u001d\u001a\u0002H\u00152\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00150\u0014H\u0007¢\u0006\u0002\u0010\u001eJ'\u0010\u001b\u001a\u00020\u001c\"\b\b\u0000\u0010\u0015*\u00020\u00012\u0006\u0010\u001d\u001a\u0002H\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u00020\u001c8\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/appfunctions/AppFunctionData$Companion;", "", "<init>", "()V", "DEFAULT_BOOLEAN", "", "DEFAULT_FLOAT", "", "DEFAULT_DOUBLE", "", "DEFAULT_INT", "", "DEFAULT_LONG", "", "LEGACY_ID_FIELD_KEY", "", "extrasKey", "key", "index", "getSerializableClass", "Ljava/lang/Class;", "T", "qualifiedName", "getSerializableFactory", "Landroidx/appfunctions/internal/AppFunctionSerializableFactory;", "serializableClass", "getPackageName", "serialize", "Landroidx/appfunctions/AppFunctionData;", "serializable", "(Ljava/lang/Object;Ljava/lang/Class;)Landroidx/appfunctions/AppFunctionData;", "(Ljava/lang/Object;Ljava/lang/String;)Landroidx/appfunctions/AppFunctionData;", "EMPTY", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String extrasKey(String str) {
            return C0212a.l("property/", str);
        }

        private final String getPackageName(Class<?> cls) {
            w wVar = v.f4727a;
            if (C1192j.z0(new String[]{wVar.b(LocalDateTime.class).o(), wVar.b(Uri.class).o()}).contains(cls.getSimpleName())) {
                return "androidx.appfunctions.internal.serializableproxies";
            }
            String l = cls.getPackageName();
            j.d(l, "getPackageName(...)");
            return l;
        }

        /* access modifiers changed from: private */
        public final <T> Class<T> getSerializableClass(String str) {
            try {
                return Class.forName(str);
            } catch (Exception e) {
                Log.d("AppFunctions", "Unable to find serializable class " + str, e);
                throw new IllegalArgumentException(C0212a.l("Unable to find serializable class ", str));
            }
        }

        /* access modifiers changed from: private */
        public final <T> AppFunctionSerializableFactory<T> getSerializableFactory(Class<T> cls) {
            String packageName = getPackageName(cls);
            String m = C0212a.m("$", n.O0(cls.getName(), '.'), "Factory");
            try {
                Object newInstance = Class.forName(packageName + '.' + m).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                j.c(newInstance, "null cannot be cast to non-null type androidx.appfunctions.internal.AppFunctionSerializableFactory<T of androidx.appfunctions.AppFunctionData.Companion.getSerializableFactory>");
                return (AppFunctionSerializableFactory) newInstance;
            } catch (Exception e) {
                Log.d("AppFunctions", "Unable to create AppFunctionSerializableFactory for " + cls, e);
                throw new IllegalArgumentException("Unable to create AppFunctionSerializableFactory for " + cls);
            }
        }

        public final <T> AppFunctionData serialize(T t, Class<T> cls) {
            j.e(t, "serializable");
            j.e(cls, "serializableClass");
            try {
                return getSerializableFactory(cls).toAppFunctionData(t);
            } catch (Exception e) {
                Log.d("AppFunctions", "Something went wrong while serialize " + t + " of class " + cls, e);
                throw new IllegalArgumentException("Unable to serialize " + cls + ". Is the class annotated with @AppFunctionSerializable?");
            }
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String extrasKey(String str, int i2) {
            return "property/" + str + '[' + i2 + ']';
        }

        public final <T> AppFunctionData serialize(T t, String str) {
            j.e(t, "serializable");
            j.e(str, "qualifiedName");
            return serialize(t, getSerializableClass(str));
        }
    }

    static {
        GenericDocument e = t.b().build();
        j.d(e, "build(...)");
        Bundle bundle = Bundle.EMPTY;
        j.d(bundle, "EMPTY");
        EMPTY = new AppFunctionData(e, bundle);
    }

    public AppFunctionData(AppFunctionDataSpec appFunctionDataSpec, GenericDocument genericDocument2, Bundle bundle) {
        j.e(genericDocument2, "genericDocument");
        j.e(bundle, "extras");
        this.spec = appFunctionDataSpec;
        this.genericDocument = genericDocument2;
        this.extras = bundle;
        bundle.setClassLoader(AppFunctionData.class.getClassLoader());
        String k = genericDocument2.getId();
        j.d(k, "getId(...)");
        this.id = k;
    }

    private final boolean isDoubleWithinFloatRange(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return true;
        }
        if (d > 3.4028234663852886E38d || d < -3.4028234663852886E38d) {
            return false;
        }
        return true;
    }

    private final boolean isLongWithinLongRange(long j2) {
        if (j2 < -2147483648L || j2 > 2147483647L) {
            return false;
        }
        return true;
    }

    private final <T> T unsafeGetProperty(String str, Class<T> cls) {
        try {
            Object j2 = this.genericDocument.getProperty(str);
            if (j2 != null) {
                return cls.cast(j2);
            }
            return null;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(C0212a.m("Found the property under [", str, "] but data type does not match with the request."), e);
        }
    }

    public final boolean containsKey(String str) {
        j.e(str, "key");
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null && !appFunctionDataSpec.containsMetadata(str)) {
            throw new IllegalArgumentException("There is no metadata associated with ".concat(str));
        } else if (str.equals("id") || this.genericDocument.getProperty(str) != null || this.extras.containsKey(str) || this.extras.containsKey(Companion.extrasKey(str))) {
            return true;
        } else {
            return false;
        }
    }

    public final <T> T deserialize(Class<T> cls) {
        j.e(cls, "serializableClass");
        try {
            return Companion.getSerializableFactory(cls).fromAppFunctionData(this);
        } catch (Exception e) {
            Log.d("AppFunctions", "Something went wrong while deserialize " + this + " to " + cls, e);
            throw new IllegalArgumentException("Unable to deserialize " + cls + ". Is the class annotated with @AppFunctionSerializable?");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: androidx.appfunctions.AppFunctionData} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [androidx.appfunctions.AppFunctionDataSpec] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.appfunctions.AppFunctionData getAppFunctionData(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.Class<android.app.appsearch.GenericDocument[]> r0 = android.app.appsearch.GenericDocument[].class
            java.lang.Object r0 = r6.unsafeGetProperty(r7, r0)
            android.app.appsearch.GenericDocument[] r0 = (android.app.appsearch.GenericDocument[]) r0
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x0043
            int r3 = r0.length
            if (r3 != 0) goto L_0x0015
            goto L_0x0043
        L_0x0015:
            androidx.appfunctions.AppFunctionData r3 = new androidx.appfunctions.AppFunctionData
            androidx.appfunctions.AppFunctionDataSpec r4 = r6.spec
            if (r4 == 0) goto L_0x002a
            r1 = r0[r2]
            java.lang.String r1 = r1.getSchemaType()
            java.lang.String r5 = "getSchemaType(...)"
            kotlin.jvm.internal.j.d(r1, r5)
            androidx.appfunctions.AppFunctionDataSpec r1 = r4.getPropertyObjectSpec((java.lang.String) r7, (java.lang.String) r1)
        L_0x002a:
            r0 = r0[r2]
            android.os.Bundle r4 = r6.extras
            androidx.appfunctions.AppFunctionData$Companion r5 = Companion
            java.lang.String r5 = r5.extrasKey(r7)
            android.os.Bundle r4 = r4.getBundle(r5)
            if (r4 != 0) goto L_0x003c
            android.os.Bundle r4 = android.os.Bundle.EMPTY
        L_0x003c:
            kotlin.jvm.internal.j.b(r4)
            r3.<init>(r1, r0, r4)
            r1 = r3
        L_0x0043:
            androidx.appfunctions.AppFunctionDataSpec r6 = r6.spec
            if (r6 == 0) goto L_0x004c
            java.lang.Class<androidx.appfunctions.AppFunctionData> r0 = androidx.appfunctions.AppFunctionData.class
            r6.validateReadRequest(r7, r0, r2, r1)
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.AppFunctionData.getAppFunctionData(java.lang.String):androidx.appfunctions.AppFunctionData");
    }

    public final List<AppFunctionData> getAppFunctionDataList(String str) {
        AppFunctionDataSpec appFunctionDataSpec;
        j.e(str, "key");
        GenericDocument[] genericDocumentArr = (GenericDocument[]) unsafeGetProperty(str, GenericDocument[].class);
        ArrayList arrayList = null;
        if (genericDocumentArr != null) {
            ArrayList arrayList2 = new ArrayList(genericDocumentArr.length);
            int length = genericDocumentArr.length;
            int i2 = 0;
            int i7 = 0;
            while (i2 < length) {
                GenericDocument genericDocument2 = genericDocumentArr[i2];
                int i8 = i7 + 1;
                AppFunctionDataSpec appFunctionDataSpec2 = this.spec;
                if (appFunctionDataSpec2 != null) {
                    String schemaType = genericDocument2.getSchemaType();
                    j.d(schemaType, "getSchemaType(...)");
                    appFunctionDataSpec = appFunctionDataSpec2.getPropertyObjectSpec(str, schemaType);
                } else {
                    appFunctionDataSpec = null;
                }
                Bundle bundle = this.extras.getBundle(Companion.extrasKey(str, i7));
                if (bundle == null) {
                    bundle = Bundle.EMPTY;
                }
                j.b(bundle);
                arrayList2.add(new AppFunctionData(appFunctionDataSpec, genericDocument2, bundle));
                i2++;
                i7 = i8;
            }
            arrayList = arrayList2;
        }
        AppFunctionDataSpec appFunctionDataSpec3 = this.spec;
        if (appFunctionDataSpec3 != null) {
            appFunctionDataSpec3.validateReadRequest(str, AppFunctionData.class, true, arrayList);
        }
        return arrayList;
    }

    public final boolean getBoolean(String str) {
        j.e(str, "key");
        return getBoolean(str, false);
    }

    public final boolean[] getBooleanArray(String str) {
        j.e(str, "key");
        boolean[] zArr = (boolean[]) unsafeGetProperty(str, boolean[].class);
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Boolean.TYPE, true, zArr);
        }
        return zArr;
    }

    public final Boolean getBooleanOrNull(String str) {
        Boolean bool;
        j.e(str, "key");
        boolean[] zArr = (boolean[]) unsafeGetProperty(str, boolean[].class);
        if (zArr == null || zArr.length == 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(zArr[0]);
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Boolean.TYPE, false, bool);
        }
        return bool;
    }

    public final byte[] getByteArray(String str) {
        j.e(str, "key");
        return getByteArrayOrNull(str);
    }

    public final byte[] getByteArrayOrNull(String str) {
        byte[] bArr;
        j.e(str, "key");
        byte[][] bArr2 = (byte[][]) unsafeGetProperty(str, byte[][].class);
        if (bArr2 == null || bArr2.length == 0) {
            bArr = null;
        } else {
            bArr = bArr2[0];
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Byte.TYPE, true, bArr);
        }
        return bArr;
    }

    public final double getDouble(String str) {
        j.e(str, "key");
        return getDouble(str, MapUtil.INVALID_LOCATION);
    }

    public final double[] getDoubleArray(String str) {
        j.e(str, "key");
        double[] dArr = (double[]) unsafeGetProperty(str, double[].class);
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Double.TYPE, true, dArr);
        }
        return dArr;
    }

    public final Double getDoubleOrNull(String str) {
        Double d;
        j.e(str, "key");
        double[] dArr = (double[]) unsafeGetProperty(str, double[].class);
        if (dArr == null || dArr.length == 0) {
            d = null;
        } else {
            d = Double.valueOf(dArr[0]);
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Double.TYPE, false, d);
        }
        return d;
    }

    public final Bundle getExtras$appfunctions() {
        return this.extras;
    }

    public final float getFloat(String str) {
        j.e(str, "key");
        return getFloat(str, 0.0f);
    }

    public final float[] getFloatArray(String str) {
        float[] fArr;
        j.e(str, "key");
        double[] dArr = (double[]) unsafeGetProperty(str, double[].class);
        if (dArr != null) {
            ArrayList<Number> arrayList = new ArrayList<>(dArr.length);
            int length = dArr.length;
            int i2 = 0;
            int i7 = 0;
            while (i7 < length) {
                double d = dArr[i7];
                if (isDoubleWithinFloatRange(d)) {
                    arrayList.add(Float.valueOf((float) d));
                    i7++;
                } else {
                    throw new IllegalStateException(C0212a.m("One of the value associated with ", str, " is not within the range of Float"));
                }
            }
            fArr = new float[arrayList.size()];
            for (Number floatValue : arrayList) {
                fArr[i2] = floatValue.floatValue();
                i2++;
            }
        } else {
            fArr = null;
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Float.TYPE, true, fArr);
        }
        return fArr;
    }

    public final Float getFloatOrNull(String str) {
        Double d;
        j.e(str, "key");
        double[] dArr = (double[]) unsafeGetProperty(str, double[].class);
        if (dArr == null || dArr.length == 0) {
            d = null;
        } else {
            d = Double.valueOf(dArr[0]);
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Float.TYPE, false, d);
        }
        if (d != null && !isDoubleWithinFloatRange(d.doubleValue())) {
            throw new IllegalStateException(C0212a.m("The value associated with ", str, " is not within the range of Float"));
        } else if (d != null) {
            return Float.valueOf((float) d.doubleValue());
        } else {
            return null;
        }
    }

    public final GenericDocument getGenericDocument$appfunctions() {
        return this.genericDocument;
    }

    public final int getInt(String str) {
        j.e(str, "key");
        return getInt(str, 0);
    }

    public final int[] getIntArray(String str) {
        int[] iArr;
        j.e(str, "key");
        long[] jArr = (long[]) unsafeGetProperty(str, long[].class);
        if (jArr != null) {
            ArrayList arrayList = new ArrayList(jArr.length);
            int length = jArr.length;
            int i2 = 0;
            while (i2 < length) {
                long j2 = jArr[i2];
                if (isLongWithinLongRange(j2)) {
                    arrayList.add(Integer.valueOf((int) j2));
                    i2++;
                } else {
                    throw new IllegalStateException(C0212a.m("One of the value associated with ", str, " is not within the range of Int"));
                }
            }
            iArr = C1194l.j1(arrayList);
        } else {
            iArr = null;
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Integer.TYPE, true, iArr);
        }
        return iArr;
    }

    public final Integer getIntOrNull(String str) {
        Long l;
        j.e(str, "key");
        long[] jArr = (long[]) unsafeGetProperty(str, long[].class);
        Integer num = null;
        if (jArr == null || jArr.length == 0) {
            l = null;
        } else {
            l = Long.valueOf(jArr[0]);
        }
        if (l == null || isLongWithinLongRange(l.longValue())) {
            if (l != null) {
                num = Integer.valueOf((int) l.longValue());
            }
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateReadRequest(str, Integer.TYPE, false, num);
            }
            return num;
        }
        throw new IllegalStateException(C0212a.m("The value associated with ", str, " is not within the range of Int"));
    }

    public final long getLong(String str) {
        j.e(str, "key");
        return getLong(str, 0);
    }

    public final long[] getLongArray(String str) {
        j.e(str, "key");
        long[] jArr = (long[]) unsafeGetProperty(str, long[].class);
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Long.TYPE, true, jArr);
        }
        return jArr;
    }

    public final Long getLongOrNull(String str) {
        Long l;
        j.e(str, "key");
        long[] jArr = (long[]) unsafeGetProperty(str, long[].class);
        if (jArr == null || jArr.length == 0) {
            l = null;
        } else {
            l = Long.valueOf(jArr[0]);
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, Long.TYPE, false, l);
        }
        return l;
    }

    public final PendingIntent getPendingIntent(String str) {
        j.e(str, "key");
        return getPendingIntentOrNull(str);
    }

    public final List<PendingIntent> getPendingIntentList(String str) {
        j.e(str, "key");
        ArrayList n = this.extras.getParcelableArrayList(Companion.extrasKey(str), PendingIntent.class);
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, PendingIntent.class, true, n);
        }
        return n;
    }

    public final PendingIntent getPendingIntentOrNull(String str) {
        j.e(str, "key");
        PendingIntent pendingIntent = (PendingIntent) this.extras.getParcelable(Companion.extrasKey(str), PendingIntent.class);
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, PendingIntent.class, false, pendingIntent);
        }
        return pendingIntent;
    }

    public final String getQualifiedName() {
        String B = this.genericDocument.getSchemaType();
        j.d(B, "getSchemaType(...)");
        return B;
    }

    public final AppFunctionDataSpec getSpec$appfunctions() {
        return this.spec;
    }

    public final String getString(String str) {
        j.e(str, "key");
        return getStringOrNull(str);
    }

    public final List<String> getStringList(String str) {
        List<String> list;
        j.e(str, "key");
        String[] strArr = (String[]) unsafeGetProperty(str, String[].class);
        if (strArr != null) {
            list = C1192j.a0(strArr);
        } else {
            list = null;
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, String.class, true, list);
        }
        return list;
    }

    public final String getStringOrNull(String str) {
        j.e(str, "key");
        String[] strArr = (String[]) unsafeGetProperty(str, String[].class);
        String str2 = null;
        if (str.equals("id")) {
            String str3 = this.id;
            if (str3.length() != 0) {
                str2 = str3;
            } else if (strArr != null) {
                if (strArr.length == 0) {
                    strArr = null;
                }
                if (strArr != null) {
                    str2 = strArr[0];
                }
            }
        } else if (!(strArr == null || strArr.length == 0)) {
            str2 = strArr[0];
        }
        AppFunctionDataSpec appFunctionDataSpec = this.spec;
        if (appFunctionDataSpec != null) {
            appFunctionDataSpec.validateReadRequest(str, String.class, false, str2);
        }
        return str2;
    }

    public final AppFunctionData replaceSpecWith$appfunctions(AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
        j.e(appFunctionObjectTypeMetadata, "objectTypeMetadata");
        j.e(appFunctionComponentsMetadata, "componentMetadata");
        return new AppFunctionData(AppFunctionDataSpec.Companion.create(appFunctionObjectTypeMetadata, appFunctionComponentsMetadata), this.genericDocument, this.extras);
    }

    public String toString() {
        return "AppFunctionData(genericDocument=" + this.genericDocument + ", extras=" + this.extras + ')';
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004d */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0056 A[Catch:{ Exception -> 0x0035 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062 A[Catch:{ Exception -> 0x0035 }, LOOP:1: B:22:0x005c->B:24:0x0062, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void visitAppFunctionUriGrants(Ae.b r4) {
        /*
            r3 = this;
            java.lang.String r0 = "visitor"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = r3.getQualifiedName()
            java.lang.Class<androidx.appfunctions.AppFunctionUriGrant> r1 = androidx.appfunctions.AppFunctionUriGrant.class
            java.lang.String r2 = r1.getCanonicalName()
            boolean r0 = kotlin.jvm.internal.j.a(r0, r2)
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r3.deserialize(r1)     // Catch:{ Exception -> 0x001d }
            androidx.appfunctions.AppFunctionUriGrant r0 = (androidx.appfunctions.AppFunctionUriGrant) r0     // Catch:{ Exception -> 0x001d }
            goto L_0x0026
        L_0x001d:
            r0 = move-exception
            java.lang.String r1 = "AppFunctions"
            java.lang.String r2 = "Unexpected error while visiting AppFunctionUriGrant"
            android.util.Log.d(r1, r2, r0)
            r0 = 0
        L_0x0026:
            if (r0 == 0) goto L_0x002b
            r4.invoke(r0)
        L_0x002b:
            android.app.appsearch.GenericDocument r0 = r3.genericDocument
            java.util.Set r0 = r0.getPropertyNames()
            java.util.Iterator r0 = r0.iterator()
        L_0x0035:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x006c
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            kotlin.jvm.internal.j.b(r1)     // Catch:{ Exception -> 0x004d }
            androidx.appfunctions.AppFunctionData r2 = r3.getAppFunctionData(r1)     // Catch:{ Exception -> 0x004d }
            if (r2 == 0) goto L_0x004d
            r2.visitAppFunctionUriGrants(r4)     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            kotlin.jvm.internal.j.b(r1)     // Catch:{ Exception -> 0x0035 }
            java.util.List r1 = r3.getAppFunctionDataList(r1)     // Catch:{ Exception -> 0x0035 }
            if (r1 != 0) goto L_0x0058
            ne.t r1 = ne.C1202t.d     // Catch:{ Exception -> 0x0035 }
        L_0x0058:
            java.util.Iterator r1 = r1.iterator()     // Catch:{ Exception -> 0x0035 }
        L_0x005c:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x0035 }
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x0035 }
            androidx.appfunctions.AppFunctionData r2 = (androidx.appfunctions.AppFunctionData) r2     // Catch:{ Exception -> 0x0035 }
            r2.visitAppFunctionUriGrants(r4)     // Catch:{ Exception -> 0x0035 }
            goto L_0x005c
        L_0x006c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.AppFunctionData.visitAppFunctionUriGrants(Ae.b):void");
    }

    public final boolean getBoolean(String str, boolean z) {
        j.e(str, "key");
        Boolean booleanOrNull = getBooleanOrNull(str);
        return booleanOrNull != null ? booleanOrNull.booleanValue() : z;
    }

    public final double getDouble(String str, double d) {
        j.e(str, "key");
        Double doubleOrNull = getDoubleOrNull(str);
        return doubleOrNull != null ? doubleOrNull.doubleValue() : d;
    }

    public final float getFloat(String str, float f) {
        j.e(str, "key");
        Float floatOrNull = getFloatOrNull(str);
        return floatOrNull != null ? floatOrNull.floatValue() : f;
    }

    public final int getInt(String str, int i2) {
        j.e(str, "key");
        Integer intOrNull = getIntOrNull(str);
        return intOrNull != null ? intOrNull.intValue() : i2;
    }

    public final long getLong(String str, long j2) {
        j.e(str, "key");
        Long longOrNull = getLongOrNull(str);
        return longOrNull != null ? longOrNull.longValue() : j2;
    }

    @Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0005\u0010\u000bB\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0005\u0010\u000eB\u0019\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u0005\u0010\u0011B\u0011\b\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0005\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001dH\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010!\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020 H\u0007¢\u0006\u0004\b!\u0010\"J\u001f\u0010$\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020#H\u0007¢\u0006\u0004\b$\u0010%J\u001f\u0010'\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020&H\u0007¢\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0002H\u0007¢\u0006\u0004\b)\u0010*J\u001f\u0010,\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020+H\u0007¢\u0006\u0004\b,\u0010-J\u001f\u0010/\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020.H\u0007¢\u0006\u0004\b/\u00100J\u001f\u00102\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u000201H\u0007¢\u0006\u0004\b2\u00103J\u001f\u00105\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u000204H\u0007¢\u0006\u0004\b5\u00106J\u001f\u00108\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u000207H\u0007¢\u0006\u0004\b8\u00109J\u001f\u0010;\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020:H\u0007¢\u0006\u0004\b;\u0010<J\u001f\u0010>\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020=H\u0007¢\u0006\u0004\b>\u0010?J\u001f\u0010A\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020@H\u0007¢\u0006\u0004\bA\u0010BJ%\u0010D\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020CH\u0007¢\u0006\u0004\bD\u0010EJ%\u0010F\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020+0CH\u0007¢\u0006\u0004\bF\u0010EJ%\u0010G\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020.0CH\u0007¢\u0006\u0004\bG\u0010EJ\r\u0010H\u001a\u00020+¢\u0006\u0004\bH\u0010IR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010JR\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010KR\u001a\u0010M\u001a\u0006\u0012\u0002\b\u00030L8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0014\u0010P\u001a\u00020O8\u0002X\u0004¢\u0006\u0006\n\u0004\bP\u0010Q¨\u0006R"}, d2 = {"Landroidx/appfunctions/AppFunctionData$Builder;", "", "", "qualifiedName", "id", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;", "objectTypeMetadata", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "componentMetadata", "(Landroidx/appfunctions/metadata/AppFunctionObjectTypeMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)V", "Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;", "allOfTypeMetadata", "(Landroidx/appfunctions/metadata/AppFunctionAllOfTypeMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)V", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "responseMetadata", "(Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;)V", "Landroidx/appfunctions/AppFunctionDataSpec;", "spec", "(Landroidx/appfunctions/AppFunctionDataSpec;)V", "Lme/x;", "setLegacyId", "(Ljava/lang/String;)V", "key", "", "value", "setBoolean", "(Ljava/lang/String;Z)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setFloat", "(Ljava/lang/String;F)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setDouble", "(Ljava/lang/String;D)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setInt", "(Ljava/lang/String;I)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setLong", "(Ljava/lang/String;J)Landroidx/appfunctions/AppFunctionData$Builder;", "setString", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/appfunctions/AppFunctionData$Builder;", "Landroidx/appfunctions/AppFunctionData;", "setAppFunctionData", "(Ljava/lang/String;Landroidx/appfunctions/AppFunctionData;)Landroidx/appfunctions/AppFunctionData$Builder;", "Landroid/app/PendingIntent;", "setPendingIntent", "(Ljava/lang/String;Landroid/app/PendingIntent;)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setBooleanArray", "(Ljava/lang/String;[Z)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setFloatArray", "(Ljava/lang/String;[F)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setDoubleArray", "(Ljava/lang/String;[D)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setIntArray", "(Ljava/lang/String;[I)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setLongArray", "(Ljava/lang/String;[J)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setByteArray", "(Ljava/lang/String;[B)Landroidx/appfunctions/AppFunctionData$Builder;", "", "setStringList", "(Ljava/lang/String;Ljava/util/List;)Landroidx/appfunctions/AppFunctionData$Builder;", "setAppFunctionDataList", "setPendingIntentList", "build", "()Landroidx/appfunctions/AppFunctionData;", "Ljava/lang/String;", "Landroidx/appfunctions/AppFunctionDataSpec;", "Landroid/app/appsearch/GenericDocument$Builder;", "genericDocumentBuilder", "Landroid/app/appsearch/GenericDocument$Builder;", "Landroid/os/Bundle;", "extrasBuilder", "Landroid/os/Bundle;", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private final Bundle extrasBuilder;
        private GenericDocument.Builder<?> genericDocumentBuilder;
        private final String qualifiedName;
        private final AppFunctionDataSpec spec;

        public Builder(String str, String str2) {
            j.e(str, "qualifiedName");
            j.e(str2, "id");
            this.extrasBuilder = new Bundle();
            this.qualifiedName = str;
            this.spec = null;
            this.genericDocumentBuilder = t.d(str2, str);
        }

        private final void setLegacyId(String str) {
            if (SdkExtensions.getExtensionVersion(33) >= 13) {
                this.genericDocumentBuilder.setId(str);
            } else {
                Log.wtf("AppFunctions", "setId method in GenericDocument isn't supported on the current device.");
            }
        }

        public final AppFunctionData build() {
            Set<String> allPropertyKeys$appfunctions;
            GenericDocument e = this.genericDocumentBuilder.build();
            j.d(e, "build(...)");
            AppFunctionData appFunctionData = new AppFunctionData(this.spec, e, this.extrasBuilder);
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (!(appFunctionDataSpec == null || (allPropertyKeys$appfunctions = appFunctionDataSpec.getAllPropertyKeys$appfunctions()) == null)) {
                for (String str : allPropertyKeys$appfunctions) {
                    if (this.spec.isRequired$appfunctions(str) && !appFunctionData.containsKey(str)) {
                        throw new IllegalArgumentException(C0086a.m(N2.j.k("Missing required property: '", str, "' for object '"), this.qualifiedName, '\''));
                    }
                }
            }
            return appFunctionData;
        }

        public final Builder setAppFunctionData(String str, AppFunctionData appFunctionData) {
            AppFunctionDataSpec propertyObjectSpec;
            j.e(str, "key");
            j.e(appFunctionData, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, AppFunctionData.class, false, appFunctionData);
            }
            AppFunctionDataSpec appFunctionDataSpec2 = this.spec;
            if (!(appFunctionDataSpec2 == null || (propertyObjectSpec = appFunctionDataSpec2.getPropertyObjectSpec(str, appFunctionData.getQualifiedName())) == null)) {
                propertyObjectSpec.validateDataSpecMatches(appFunctionData);
            }
            this.genericDocumentBuilder.setPropertyDocument(str, new GenericDocument[]{appFunctionData.getGenericDocument$appfunctions()});
            if (!appFunctionData.getExtras$appfunctions().isEmpty()) {
                this.extrasBuilder.putBundle(AppFunctionData.Companion.extrasKey(str), appFunctionData.getExtras$appfunctions());
            }
            return this;
        }

        public final Builder setAppFunctionDataList(String str, List<AppFunctionData> list) {
            AppFunctionDataSpec propertyObjectSpec;
            j.e(str, "key");
            j.e(list, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, AppFunctionData.class, true, list);
            }
            GenericDocument.Builder<?> builder = this.genericDocumentBuilder;
            Iterable<AppFunctionData> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionData genericDocument$appfunctions : iterable) {
                arrayList.add(genericDocument$appfunctions.getGenericDocument$appfunctions());
            }
            int i2 = 0;
            GenericDocument[] genericDocumentArr = (GenericDocument[]) arrayList.toArray(new GenericDocument[0]);
            builder.setPropertyDocument(str, (GenericDocument[]) Arrays.copyOf(genericDocumentArr, genericDocumentArr.length));
            for (Object next : iterable) {
                int i7 = i2 + 1;
                if (i2 >= 0) {
                    AppFunctionData appFunctionData = (AppFunctionData) next;
                    AppFunctionDataSpec appFunctionDataSpec2 = this.spec;
                    if (!(appFunctionDataSpec2 == null || (propertyObjectSpec = appFunctionDataSpec2.getPropertyObjectSpec(str, appFunctionData.getQualifiedName())) == null)) {
                        propertyObjectSpec.validateDataSpecMatches(appFunctionData);
                    }
                    if (!appFunctionData.getExtras$appfunctions().isEmpty()) {
                        this.extrasBuilder.putBundle(AppFunctionData.Companion.extrasKey(str, i2), appFunctionData.getExtras$appfunctions());
                    }
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
            return this;
        }

        public final Builder setBoolean(String str, boolean z) {
            j.e(str, "key");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Boolean.TYPE, false, Boolean.valueOf(z));
            }
            this.genericDocumentBuilder.setPropertyBoolean(str, new boolean[]{z});
            return this;
        }

        public final Builder setBooleanArray(String str, boolean[] zArr) {
            j.e(str, "key");
            j.e(zArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Boolean.TYPE, true, zArr);
            }
            this.genericDocumentBuilder.setPropertyBoolean(str, Arrays.copyOf(zArr, zArr.length));
            return this;
        }

        public final Builder setByteArray(String str, byte[] bArr) {
            j.e(str, "key");
            j.e(bArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Byte.TYPE, true, bArr);
            }
            this.genericDocumentBuilder.setPropertyBytes(str, new byte[][]{bArr});
            return this;
        }

        public final Builder setDouble(String str, double d) {
            j.e(str, "key");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Double.TYPE, false, Double.valueOf(d));
            }
            this.genericDocumentBuilder.setPropertyDouble(str, new double[]{d});
            return this;
        }

        public final Builder setDoubleArray(String str, double[] dArr) {
            j.e(str, "key");
            j.e(dArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Double.TYPE, true, dArr);
            }
            this.genericDocumentBuilder.setPropertyDouble(str, Arrays.copyOf(dArr, dArr.length));
            return this;
        }

        public final Builder setFloat(String str, float f) {
            j.e(str, "key");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Float.TYPE, false, Float.valueOf(f));
            }
            this.genericDocumentBuilder.setPropertyDouble(str, new double[]{(double) f});
            return this;
        }

        public final Builder setFloatArray(String str, float[] fArr) {
            j.e(str, "key");
            j.e(fArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Float.TYPE, true, fArr);
            }
            GenericDocument.Builder<?> builder = this.genericDocumentBuilder;
            C1193k<Number> kVar = new C1193k(1, fArr);
            ArrayList arrayList = new ArrayList(C1196n.w0(kVar, 10));
            for (Number floatValue : kVar) {
                arrayList.add(Double.valueOf((double) floatValue.floatValue()));
            }
            int size = arrayList.size();
            double[] dArr = new double[size];
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                dArr[i2] = ((Number) it.next()).doubleValue();
                i2++;
            }
            builder.setPropertyDouble(str, Arrays.copyOf(dArr, size));
            return this;
        }

        public final Builder setInt(String str, int i2) {
            j.e(str, "key");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Integer.TYPE, false, Integer.valueOf(i2));
            }
            this.genericDocumentBuilder.setPropertyLong(str, new long[]{(long) i2});
            return this;
        }

        public final Builder setIntArray(String str, int[] iArr) {
            j.e(str, "key");
            j.e(iArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Integer.TYPE, true, iArr);
            }
            GenericDocument.Builder<?> builder = this.genericDocumentBuilder;
            C1193k<Number> kVar = new C1193k(0, iArr);
            ArrayList arrayList = new ArrayList(C1196n.w0(kVar, 10));
            for (Number intValue : kVar) {
                arrayList.add(Long.valueOf((long) intValue.intValue()));
            }
            long[] l12 = C1194l.l1(arrayList);
            builder.setPropertyLong(str, Arrays.copyOf(l12, l12.length));
            return this;
        }

        public final Builder setLong(String str, long j2) {
            j.e(str, "key");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Long.TYPE, false, Long.valueOf(j2));
            }
            this.genericDocumentBuilder.setPropertyLong(str, new long[]{j2});
            return this;
        }

        public final Builder setLongArray(String str, long[] jArr) {
            j.e(str, "key");
            j.e(jArr, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, Long.TYPE, true, jArr);
            }
            this.genericDocumentBuilder.setPropertyLong(str, Arrays.copyOf(jArr, jArr.length));
            return this;
        }

        public final Builder setPendingIntent(String str, PendingIntent pendingIntent) {
            j.e(str, "key");
            j.e(pendingIntent, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, PendingIntent.class, false, pendingIntent);
            }
            this.extrasBuilder.putParcelable(AppFunctionData.Companion.extrasKey(str), pendingIntent);
            return this;
        }

        public final Builder setPendingIntentList(String str, List<PendingIntent> list) {
            j.e(str, "key");
            j.e(list, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, PendingIntent.class, true, list);
            }
            this.extrasBuilder.putParcelableArrayList(AppFunctionData.Companion.extrasKey(str), new ArrayList(list));
            return this;
        }

        public final Builder setString(String str, String str2) {
            j.e(str, "key");
            j.e(str2, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, String.class, false, str2);
            }
            this.genericDocumentBuilder.setPropertyString(str, new String[]{str2});
            if (str.equals("id")) {
                setLegacyId(str2);
            }
            return this;
        }

        public final Builder setStringList(String str, List<String> list) {
            j.e(str, "key");
            j.e(list, "value");
            AppFunctionDataSpec appFunctionDataSpec = this.spec;
            if (appFunctionDataSpec != null) {
                appFunctionDataSpec.validateWriteRequest(str, new String().getClass(), true, list);
            }
            GenericDocument.Builder<?> builder = this.genericDocumentBuilder;
            String[] strArr = (String[]) list.toArray(new String[0]);
            builder.setPropertyString(str, (String[]) Arrays.copyOf(strArr, strArr.length));
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(String str, String str2, int i2, e eVar) {
            this(str, (i2 & 2) != 0 ? "" : str2);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(AppFunctionObjectTypeMetadata appFunctionObjectTypeMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            this(AppFunctionDataSpec.Companion.create(appFunctionObjectTypeMetadata, appFunctionComponentsMetadata));
            j.e(appFunctionObjectTypeMetadata, "objectTypeMetadata");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(AppFunctionAllOfTypeMetadata appFunctionAllOfTypeMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            this(AppFunctionDataSpec.Companion.create(appFunctionAllOfTypeMetadata.getPseudoObjectTypeMetadata$appfunctions(appFunctionComponentsMetadata), appFunctionComponentsMetadata));
            j.e(appFunctionAllOfTypeMetadata, "allOfTypeMetadata");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(AppFunctionResponseMetadata appFunctionResponseMetadata, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
            this(AppFunctionDataSpec.Companion.create(appFunctionResponseMetadata, appFunctionComponentsMetadata));
            j.e(appFunctionResponseMetadata, "responseMetadata");
            j.e(appFunctionComponentsMetadata, "componentMetadata");
        }

        private Builder(AppFunctionDataSpec appFunctionDataSpec) {
            this.extrasBuilder = new Bundle();
            this.spec = appFunctionDataSpec;
            this.qualifiedName = appFunctionDataSpec.getObjectQualifiedName();
            t.n();
            this.genericDocumentBuilder = t.c(appFunctionDataSpec.getObjectQualifiedName());
        }
    }

    public final AppFunctionData replaceSpecWith$appfunctions(List<AppFunctionParameterMetadata> list, AppFunctionComponentsMetadata appFunctionComponentsMetadata) {
        j.e(list, "parameterMetadata");
        j.e(appFunctionComponentsMetadata, "componentMetadata");
        return new AppFunctionData(AppFunctionDataSpec.Companion.create(list, appFunctionComponentsMetadata), this.genericDocument, this.extras);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppFunctionData(GenericDocument genericDocument2, Bundle bundle) {
        this((AppFunctionDataSpec) null, genericDocument2, bundle);
        j.e(genericDocument2, "genericDocument");
        j.e(bundle, "extras");
    }

    public final <T> T deserialize(String str) {
        j.e(str, "qualifiedName");
        return deserialize(Companion.getSerializableClass(str));
    }
}
