package androidx.appfunctions.internal;

import Uf.a;
import Uf.c;
import android.content.Context;
import android.util.Log;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadata;
import androidx.appfunctions.metadata.AppFunctionComponentsMetadataDocument;
import androidx.appfunctions.metadata.AppFunctionMetadataDocument;
import androidx.appfunctions.metadata.AppFunctionParameterMetadata;
import androidx.appfunctions.metadata.AppFunctionParameterMetadataDocument;
import androidx.appfunctions.metadata.AppFunctionResponseMetadata;
import androidx.appfunctions.metadata.AppFunctionResponseMetadataDocument;
import androidx.appfunctions.metadata.AppFunctionRuntimeMetadata;
import androidx.appfunctions.metadata.AppFunctionSchemaMetadata;
import androidx.appfunctions.metadata.CompileTimeAppFunctionMetadata;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.app.SearchResult;
import androidx.appsearch.app.SearchSpec;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import me.x;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 :2\u00020\u0001:\u0001:B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J4\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\t\u001a\u00020\b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH@¢\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b\"\u0010#J\u001f\u0010&\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u000bH\u0002¢\u0006\u0004\b&\u0010'J)\u0010,\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0006\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010!H\u0002¢\u0006\u0004\b,\u0010-J#\u0010/\u001a\u0004\u0018\u00010.2\u0006\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010!H\u0002¢\u0006\u0004\b/\u00100J?\u00101\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u00182\b\u0010)\u001a\u0004\u0018\u00010!2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b1\u00102J\u0017\u00103\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0018H\u0002¢\u0006\u0004\b3\u00104J\"\u00106\u001a\u0004\u0018\u0001052\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bH@¢\u0006\u0004\b6\u00107R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u00108R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u00109¨\u0006;"}, d2 = {"Landroidx/appfunctions/internal/AppSearchAppFunctionReader;", "", "Landroid/content/Context;", "context", "Landroidx/appfunctions/internal/SchemaAppFunctionInventory;", "schemaAppFunctionInventory", "<init>", "(Landroid/content/Context;Landroidx/appfunctions/internal/SchemaAppFunctionInventory;)V", "Landroidx/appsearch/app/GlobalSearchSession;", "session", "", "", "packageNames", "", "Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "searchTopLevelComponent", "(Landroidx/appsearch/app/GlobalSearchSession;Ljava/util/Set;Lqe/c;)Ljava/lang/Object;", "Landroidx/appsearch/app/SearchResult;", "searchResult", "", "sharedTopLevelComponentsByPackage", "Lme/x;", "extractAppFunctionComponentsMetadataFromSearchResult", "(Landroidx/appsearch/app/SearchResult;Ljava/util/Map;)V", "Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;", "staticMetadata", "Landroidx/appfunctions/metadata/AppFunctionRuntimeMetadata;", "runtimeMetadata", "", "computeEffectivelyEnabled", "(Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;Landroidx/appfunctions/metadata/AppFunctionRuntimeMetadata;)Z", "Landroidx/appsearch/app/GenericDocument;", "document", "Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "buildSchemaMetadataFromGdForLegacyIndexer", "(Landroidx/appsearch/app/GenericDocument;)Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;", "packageName", "functionId", "getAppFunctionId", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "appFunctionMetadataDocument", "schemaMetadata", "", "Landroidx/appfunctions/metadata/AppFunctionParameterMetadata;", "getAppFunctionParameterMetadata", "(Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;)Ljava/util/List;", "Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "getAppFunctionResponseMetadata", "(Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;)Landroidx/appfunctions/metadata/AppFunctionResponseMetadata;", "getAppFunctionComponentsMetadata", "(Ljava/lang/String;Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;Landroidx/appfunctions/metadata/AppFunctionSchemaMetadata;Ljava/util/Map;)Landroidx/appfunctions/metadata/AppFunctionComponentsMetadata;", "isAppFunctionMetadataDocumentFromDynamicIndexer", "(Landroidx/appfunctions/metadata/AppFunctionMetadataDocument;)Z", "Landroidx/appfunctions/metadata/AppFunctionMetadata;", "getAppFunctionMetadata", "(Ljava/lang/String;Ljava/lang/String;Lqe/c;)Ljava/lang/Object;", "Landroid/content/Context;", "Landroidx/appfunctions/internal/SchemaAppFunctionInventory;", "Companion", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppSearchAppFunctionReader {
    private static final Companion Companion = new Companion((e) null);
    private static final long OBSERVER_DEBOUNCE_MILLIS;
    private static final SearchSpec RUNTIME_SEARCH_SPEC;
    private final Context context;
    private final SchemaAppFunctionInventory schemaAppFunctionInventory;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\u0007\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00048\u0006XT¢\u0006\u0006\n\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Landroidx/appfunctions/internal/AppSearchAppFunctionReader$Companion;", "", "<init>", "()V", "", "SYSTEM_PACKAGE_NAME", "Ljava/lang/String;", "APP_FUNCTIONS_NAMESPACE", "APP_FUNCTIONS_RUNTIME_NAMESPACE", "APP_FUNCTIONS_STATIC_DATABASE_NAME", "APP_FUNCTIONS_RUNTIME_DATABASE_NAME", "appfunctions"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    static {
        long j2;
        int i2 = a.g;
        c cVar = c.SECONDS;
        j.e(cVar, "unit");
        if (cVar.compareTo(cVar) <= 0) {
            j2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.s(C0246a.Q((long) 1, cVar, c.NANOSECONDS));
        } else {
            j2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.Q((long) 1, cVar);
        }
        OBSERVER_DEBOUNCE_MILLIS = j2;
        SearchSpec build = new SearchSpec.Builder().addFilterNamespaces("app_functions_runtime").addFilterDocumentClasses((Class<?>[]) new Class[]{AppFunctionRuntimeMetadata.class}).addFilterPackageNames("android").setVerbatimSearchEnabled(true).build();
        j.d(build, "build(...)");
        RUNTIME_SEARCH_SPEC = build;
    }

    public AppSearchAppFunctionReader(Context context2, SchemaAppFunctionInventory schemaAppFunctionInventory2) {
        j.e(context2, "context");
        this.context = context2;
        this.schemaAppFunctionInventory = schemaAppFunctionInventory2;
    }

    private final AppFunctionSchemaMetadata buildSchemaMetadataFromGdForLegacyIndexer(GenericDocument genericDocument) {
        String propertyString = genericDocument.getPropertyString("schemaName");
        String propertyString2 = genericDocument.getPropertyString("schemaCategory");
        long propertyLong = genericDocument.getPropertyLong("schemaVersion");
        if (propertyString != null && propertyString2 != null && propertyLong != 0) {
            return new AppFunctionSchemaMetadata(propertyString2, propertyString, propertyLong);
        }
        if (propertyString == null && propertyString2 == null && propertyLong == 0) {
            return null;
        }
        StringBuilder q = C0086a.q("Unexpected state: schemaName=", propertyString, ", schemaCategory=", propertyString2, ", schemaVersion=");
        q.append(propertyLong);
        Log.e("AppFunctions", q.toString());
        return null;
    }

    private final boolean computeEffectivelyEnabled(AppFunctionMetadataDocument appFunctionMetadataDocument, AppFunctionRuntimeMetadata appFunctionRuntimeMetadata) {
        int enabled = (int) appFunctionRuntimeMetadata.getEnabled();
        if (enabled == 0) {
            return appFunctionMetadataDocument.isEnabledByDefault();
        }
        if (enabled == 1) {
            return true;
        }
        if (enabled == 2) {
            return false;
        }
        throw new IllegalStateException("Unknown AppFunction state: " + appFunctionRuntimeMetadata.getEnabled() + '.');
    }

    private final void extractAppFunctionComponentsMetadataFromSearchResult(SearchResult searchResult, Map<String, AppFunctionComponentsMetadata> map) {
        Object obj;
        AppFunctionComponentsMetadata appFunctionComponentsMetadata;
        Class cls = AppFunctionComponentsMetadataDocument.class;
        String propertyString = searchResult.getGenericDocument().getPropertyString(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME);
        if (propertyString != null) {
            GenericDocument genericDocument = searchResult.getGenericDocument();
            j.d(genericDocument, "getGenericDocument(...)");
            try {
                obj = genericDocument.toDocumentClass(cls);
            } catch (Exception e) {
                Log.e("AppFunctions", "Failed to convert search result " + genericDocument.getId() + " to " + v.f4727a.b(cls).o(), e);
                obj = null;
            }
            AppFunctionComponentsMetadataDocument appFunctionComponentsMetadataDocument = (AppFunctionComponentsMetadataDocument) obj;
            if (appFunctionComponentsMetadataDocument != null && (appFunctionComponentsMetadata = appFunctionComponentsMetadataDocument.toAppFunctionComponentsMetadata()) != null && !appFunctionComponentsMetadata.getDataTypes().isEmpty()) {
                map.put(propertyString, appFunctionComponentsMetadata);
                return;
            }
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    private final AppFunctionComponentsMetadata getAppFunctionComponentsMetadata(String str, AppFunctionMetadataDocument appFunctionMetadataDocument, AppFunctionSchemaMetadata appFunctionSchemaMetadata, Map<String, AppFunctionComponentsMetadata> map) {
        SchemaAppFunctionInventory schemaAppFunctionInventory2;
        if (isAppFunctionMetadataDocumentFromDynamicIndexer(appFunctionMetadataDocument)) {
            AppFunctionComponentsMetadata appFunctionComponentsMetadata = map.get(str);
            if (appFunctionComponentsMetadata == null) {
                return new AppFunctionComponentsMetadata((Map) null, 1, (e) null);
            }
            return appFunctionComponentsMetadata;
        } else if (appFunctionSchemaMetadata == null || (schemaAppFunctionInventory2 = this.schemaAppFunctionInventory) == null) {
            return null;
        } else {
            return schemaAppFunctionInventory2.getComponentsMetadata();
        }
    }

    private final String getAppFunctionId(String str, String str2) {
        return str + '/' + str2;
    }

    private final List<AppFunctionParameterMetadata> getAppFunctionParameterMetadata(AppFunctionMetadataDocument appFunctionMetadataDocument, AppFunctionSchemaMetadata appFunctionSchemaMetadata) {
        SchemaAppFunctionInventory schemaAppFunctionInventory2;
        Map<AppFunctionSchemaMetadata, CompileTimeAppFunctionMetadata> schemaFunctionsMap;
        CompileTimeAppFunctionMetadata compileTimeAppFunctionMetadata;
        if (isAppFunctionMetadataDocumentFromDynamicIndexer(appFunctionMetadataDocument)) {
            List<AppFunctionParameterMetadataDocument> parameters = appFunctionMetadataDocument.getParameters();
            if (parameters == null) {
                return C1202t.d;
            }
            Iterable<AppFunctionParameterMetadataDocument> iterable = parameters;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (AppFunctionParameterMetadataDocument appFunctionParameterMetadata : iterable) {
                arrayList.add(appFunctionParameterMetadata.toAppFunctionParameterMetadata());
            }
            return arrayList;
        } else if (appFunctionSchemaMetadata == null || (schemaAppFunctionInventory2 = this.schemaAppFunctionInventory) == null || (schemaFunctionsMap = schemaAppFunctionInventory2.getSchemaFunctionsMap()) == null || (compileTimeAppFunctionMetadata = schemaFunctionsMap.get(appFunctionSchemaMetadata)) == null) {
            return null;
        } else {
            return compileTimeAppFunctionMetadata.getParameters();
        }
    }

    private final AppFunctionResponseMetadata getAppFunctionResponseMetadata(AppFunctionMetadataDocument appFunctionMetadataDocument, AppFunctionSchemaMetadata appFunctionSchemaMetadata) {
        SchemaAppFunctionInventory schemaAppFunctionInventory2;
        Map<AppFunctionSchemaMetadata, CompileTimeAppFunctionMetadata> schemaFunctionsMap;
        CompileTimeAppFunctionMetadata compileTimeAppFunctionMetadata;
        if (isAppFunctionMetadataDocumentFromDynamicIndexer(appFunctionMetadataDocument)) {
            AppFunctionResponseMetadataDocument response = appFunctionMetadataDocument.getResponse();
            if (response != null) {
                return response.toAppFunctionResponseMetadata();
            }
            throw new IllegalStateException("Required value was null.");
        } else if (appFunctionSchemaMetadata == null || (schemaAppFunctionInventory2 = this.schemaAppFunctionInventory) == null || (schemaFunctionsMap = schemaAppFunctionInventory2.getSchemaFunctionsMap()) == null || (compileTimeAppFunctionMetadata = schemaFunctionsMap.get(appFunctionSchemaMetadata)) == null) {
            return null;
        } else {
            return compileTimeAppFunctionMetadata.getResponse();
        }
    }

    private final boolean isAppFunctionMetadataDocumentFromDynamicIndexer(AppFunctionMetadataDocument appFunctionMetadataDocument) {
        if (appFunctionMetadataDocument.getResponse() != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object searchTopLevelComponent(androidx.appsearch.app.GlobalSearchSession r8, java.util.Set<java.lang.String> r9, qe.C1227c r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.appfunctions.internal.AppSearchAppFunctionReader$searchTopLevelComponent$1
            if (r0 == 0) goto L_0x0013
            r0 = r10
            androidx.appfunctions.internal.AppSearchAppFunctionReader$searchTopLevelComponent$1 r0 = (androidx.appfunctions.internal.AppSearchAppFunctionReader$searchTopLevelComponent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            androidx.appfunctions.internal.AppSearchAppFunctionReader$searchTopLevelComponent$1 r0 = new androidx.appfunctions.internal.AppSearchAppFunctionReader$searchTopLevelComponent$1
            r0.<init>(r7, r10)
        L_0x0018:
            java.lang.Object r10 = r0.result
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            java.lang.Object r7 = r0.L$0
            java.util.Map r7 = (java.util.Map) r7
            L2.a.A(r10)
            return r7
        L_0x002b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0033:
            L2.a.A(r10)
            androidx.appsearch.app.SearchSpec$Builder r10 = new androidx.appsearch.app.SearchSpec$Builder
            r10.<init>()
            java.lang.String r2 = "app_functions"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            androidx.appsearch.app.SearchSpec$Builder r10 = r10.addFilterNamespaces((java.lang.String[]) r2)
            if (r9 != 0) goto L_0x0049
            ne.v r9 = ne.v.d
        L_0x0049:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r9 = r9.iterator()
        L_0x0052:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x0076
            java.lang.Object r4 = r9.next()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "AppFunctionComponentMetadataDocument-"
            r5.<init>(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.util.List r4 = o1.C0246a.e0(r4)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            ne.C1200r.A0(r4, r2)
            goto L_0x0052
        L_0x0076:
            androidx.appsearch.app.SearchSpec$Builder r9 = r10.addFilterSchemas(r2)
            java.lang.String r10 = "android"
            java.lang.String[] r10 = new java.lang.String[]{r10}
            androidx.appsearch.app.SearchSpec$Builder r9 = r9.addFilterPackageNames((java.lang.String[]) r10)
            androidx.appsearch.app.SearchSpec$Builder r9 = r9.setVerbatimSearchEnabled(r3)
            androidx.appsearch.app.SearchSpec$Builder r9 = r9.setNumericSearchEnabled(r3)
            androidx.appsearch.app.SearchSpec$Builder r9 = r9.setListFilterQueryLanguageEnabled(r3)
            androidx.appsearch.app.SearchSpec r9 = r9.build()
            java.lang.String r10 = "build(...)"
            kotlin.jvm.internal.j.d(r9, r10)
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            java.lang.String r2 = ""
            androidx.appsearch.app.SearchResults r8 = r8.search(r2, r9)
            java.lang.String r9 = "search(...)"
            kotlin.jvm.internal.j.d(r8, r9)
            Wf.c r9 = new Wf.c
            r2 = 10
            r9.<init>(r2, r7, r10)
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r7 = androidx.appfunctions.internal.AppSearchUtilsKt.readAll(r8, r9, r0)
            if (r7 != r1) goto L_0x00bb
            return r1
        L_0x00bb:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.internal.AppSearchAppFunctionReader.searchTopLevelComponent(androidx.appsearch.app.GlobalSearchSession, java.util.Set, qe.c):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final x searchTopLevelComponent$lambda$1(AppSearchAppFunctionReader appSearchAppFunctionReader, Map map, SearchResult searchResult) {
        j.e(searchResult, "searchResult");
        appSearchAppFunctionReader.extractAppFunctionComponentsMetadataFromSearchResult(searchResult, map);
        return x.f4917a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x01d4, code lost:
        r2 = r30;
        r0 = r2.buildSchemaMetadataFromGdForLegacyIndexer(r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getAppFunctionMetadata(java.lang.String r31, java.lang.String r32, qe.C1227c r33) {
        /*
            r30 = this;
            r1 = r30
            r0 = r33
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionRuntimeMetadata> r2 = androidx.appfunctions.metadata.AppFunctionRuntimeMetadata.class
            java.lang.String r3 = " to "
            java.lang.String r4 = "Failed to convert search result "
            java.lang.String r5 = "AppFunctions"
            java.lang.Class<androidx.appfunctions.metadata.AppFunctionMetadataDocument> r6 = androidx.appfunctions.metadata.AppFunctionMetadataDocument.class
            boolean r7 = r0 instanceof androidx.appfunctions.internal.AppSearchAppFunctionReader$getAppFunctionMetadata$1
            if (r7 == 0) goto L_0x0021
            r7 = r0
            androidx.appfunctions.internal.AppSearchAppFunctionReader$getAppFunctionMetadata$1 r7 = (androidx.appfunctions.internal.AppSearchAppFunctionReader$getAppFunctionMetadata$1) r7
            int r8 = r7.label
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = r8 & r9
            if (r10 == 0) goto L_0x0021
            int r8 = r8 - r9
            r7.label = r8
            goto L_0x0026
        L_0x0021:
            androidx.appfunctions.internal.AppSearchAppFunctionReader$getAppFunctionMetadata$1 r7 = new androidx.appfunctions.internal.AppSearchAppFunctionReader$getAppFunctionMetadata$1
            r7.<init>(r1, r0)
        L_0x0026:
            java.lang.Object r0 = r7.result
            re.a r8 = re.C1245a.COROUTINE_SUSPENDED
            int r9 = r7.label
            java.lang.String r10 = "getByDocumentIdAsync(...)"
            java.lang.String r11 = "android"
            r12 = 4
            r13 = 3
            r14 = 2
            r15 = 1
            r16 = 0
            if (r9 == 0) goto L_0x00c7
            if (r9 == r15) goto L_0x00bb
            if (r9 == r14) goto L_0x00a2
            if (r9 == r13) goto L_0x0088
            if (r9 != r12) goto L_0x0080
            java.lang.Object r2 = r7.L$10
            androidx.appfunctions.metadata.AppFunctionSchemaMetadata r2 = (androidx.appfunctions.metadata.AppFunctionSchemaMetadata) r2
            java.lang.Object r3 = r7.L$9
            androidx.appfunctions.metadata.AppFunctionMetadataDocument r3 = (androidx.appfunctions.metadata.AppFunctionMetadataDocument) r3
            java.lang.Object r4 = r7.L$8
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r7.L$7
            androidx.appfunctions.internal.AppSearchAppFunctionReader r5 = (androidx.appfunctions.internal.AppSearchAppFunctionReader) r5
            java.lang.Object r6 = r7.L$6
            androidx.appfunctions.metadata.AppFunctionResponseMetadata r6 = (androidx.appfunctions.metadata.AppFunctionResponseMetadata) r6
            java.lang.Object r8 = r7.L$5
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r7.L$4
            androidx.appfunctions.metadata.AppFunctionSchemaMetadata r9 = (androidx.appfunctions.metadata.AppFunctionSchemaMetadata) r9
            java.lang.Object r10 = r7.L$3
            androidx.appfunctions.metadata.AppFunctionRuntimeMetadata r10 = (androidx.appfunctions.metadata.AppFunctionRuntimeMetadata) r10
            java.lang.Object r11 = r7.L$2
            androidx.appfunctions.metadata.AppFunctionMetadataDocument r11 = (androidx.appfunctions.metadata.AppFunctionMetadataDocument) r11
            java.lang.Object r12 = r7.L$1
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r7 = r7.L$0
            java.lang.String r7 = (java.lang.String) r7
            L2.a.A(r0)
            r18 = r2
            r2 = r1
            r1 = r18
            r23 = r6
            r18 = r7
            r22 = r8
            r21 = r9
            r19 = r12
            goto L_0x021e
        L_0x0080:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0088:
            java.lang.Object r9 = r7.L$4
            androidx.appsearch.app.AppSearchBatchResult r9 = (androidx.appsearch.app.AppSearchBatchResult) r9
            java.lang.Object r10 = r7.L$3
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r7.L$2
            androidx.appsearch.app.GlobalSearchSession r11 = (androidx.appsearch.app.GlobalSearchSession) r11
            java.lang.Object r13 = r7.L$1
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r14 = r7.L$0
            java.lang.String r14 = (java.lang.String) r14
            L2.a.A(r0)
            r12 = r14
            goto L_0x0152
        L_0x00a2:
            java.lang.Object r9 = r7.L$3
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r14 = r7.L$2
            androidx.appsearch.app.GlobalSearchSession r14 = (androidx.appsearch.app.GlobalSearchSession) r14
            java.lang.Object r15 = r7.L$1
            java.lang.String r15 = (java.lang.String) r15
            java.lang.Object r12 = r7.L$0
            java.lang.String r12 = (java.lang.String) r12
            L2.a.A(r0)
            r29 = r15
            r15 = r9
            r9 = r29
            goto L_0x011a
        L_0x00bb:
            java.lang.Object r9 = r7.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r12 = r7.L$0
            java.lang.String r12 = (java.lang.String) r12
            L2.a.A(r0)
            goto L_0x00e3
        L_0x00c7:
            L2.a.A(r0)
            android.content.Context r0 = r1.context
            r9 = r31
            r7.L$0 = r9
            r12 = r32
            r7.L$1 = r12
            r7.label = r15
            java.lang.Object r0 = androidx.appfunctions.internal.AppSearchUtilsKt.createSearchSession(r0, r7)
            if (r0 != r8) goto L_0x00de
            goto L_0x020b
        L_0x00de:
            r29 = r12
            r12 = r9
            r9 = r29
        L_0x00e3:
            androidx.appsearch.app.GlobalSearchSession r0 = (androidx.appsearch.app.GlobalSearchSession) r0
            java.lang.String r15 = r1.getAppFunctionId(r9, r12)
            androidx.appsearch.app.GetByDocumentIdRequest$Builder r13 = new androidx.appsearch.app.GetByDocumentIdRequest$Builder
            java.lang.String r14 = "app_functions"
            r13.<init>(r14)
            java.lang.String[] r14 = new java.lang.String[]{r15}
            androidx.appsearch.app.GetByDocumentIdRequest$Builder r13 = r13.addIds((java.lang.String[]) r14)
            androidx.appsearch.app.GetByDocumentIdRequest r13 = r13.build()
            java.lang.String r14 = "apps-db"
            com.google.common.util.concurrent.ListenableFuture r13 = r0.getByDocumentIdAsync(r11, r14, r13)
            kotlin.jvm.internal.j.d(r13, r10)
            r7.L$0 = r12
            r7.L$1 = r9
            r7.L$2 = r0
            r7.L$3 = r15
            r14 = 2
            r7.label = r14
            java.lang.Object r13 = D1.f.l(r13, r7)
            if (r13 != r8) goto L_0x0118
            goto L_0x020b
        L_0x0118:
            r14 = r0
            r0 = r13
        L_0x011a:
            androidx.appsearch.app.AppSearchBatchResult r0 = (androidx.appsearch.app.AppSearchBatchResult) r0
            androidx.appsearch.app.GetByDocumentIdRequest$Builder r13 = new androidx.appsearch.app.GetByDocumentIdRequest$Builder
            java.lang.String r1 = "app_functions_runtime"
            r13.<init>(r1)
            java.lang.String[] r1 = new java.lang.String[]{r15}
            androidx.appsearch.app.GetByDocumentIdRequest$Builder r1 = r13.addIds((java.lang.String[]) r1)
            androidx.appsearch.app.GetByDocumentIdRequest r1 = r1.build()
            java.lang.String r13 = "appfunctions-db"
            com.google.common.util.concurrent.ListenableFuture r1 = r14.getByDocumentIdAsync(r11, r13, r1)
            kotlin.jvm.internal.j.d(r1, r10)
            r7.L$0 = r12
            r7.L$1 = r9
            r7.L$2 = r14
            r7.L$3 = r15
            r7.L$4 = r0
            r10 = 3
            r7.label = r10
            java.lang.Object r1 = D1.f.l(r1, r7)
            if (r1 != r8) goto L_0x014d
            goto L_0x020b
        L_0x014d:
            r13 = r9
            r11 = r14
            r10 = r15
            r9 = r0
            r0 = r1
        L_0x0152:
            androidx.appsearch.app.AppSearchBatchResult r0 = (androidx.appsearch.app.AppSearchBatchResult) r0
            java.util.Map r1 = r9.getSuccesses()
            java.lang.Object r1 = r1.get(r10)
            androidx.appsearch.app.GenericDocument r1 = (androidx.appsearch.app.GenericDocument) r1
            java.lang.String r9 = " is not available under "
            java.lang.String r14 = "Function "
            if (r1 == 0) goto L_0x0243
            java.util.Map r0 = r0.getSuccesses()
            java.lang.Object r0 = r0.get(r10)
            r10 = r0
            androidx.appsearch.app.GenericDocument r10 = (androidx.appsearch.app.GenericDocument) r10
            if (r10 == 0) goto L_0x0239
            java.lang.Object r0 = r1.toDocumentClass(r6)     // Catch:{ Exception -> 0x0176 }
            goto L_0x019c
        L_0x0176:
            r0 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r4)
            java.lang.String r14 = r1.getId()
            r9.append(r14)
            r9.append(r3)
            kotlin.jvm.internal.w r14 = kotlin.jvm.internal.v.f4727a
            He.d r6 = r14.b(r6)
            java.lang.String r6 = r6.o()
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            android.util.Log.e(r5, r6, r0)
            r0 = r16
        L_0x019c:
            r6 = r0
            androidx.appfunctions.metadata.AppFunctionMetadataDocument r6 = (androidx.appfunctions.metadata.AppFunctionMetadataDocument) r6
            if (r6 != 0) goto L_0x01a3
            goto L_0x0226
        L_0x01a3:
            java.lang.Object r0 = r10.toDocumentClass(r2)     // Catch:{ Exception -> 0x01a8 }
            goto L_0x01ce
        L_0x01a8:
            r0 = move-exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r4)
            java.lang.String r4 = r10.getId()
            r9.append(r4)
            r9.append(r3)
            kotlin.jvm.internal.w r3 = kotlin.jvm.internal.v.f4727a
            He.d r2 = r3.b(r2)
            java.lang.String r2 = r2.o()
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            android.util.Log.e(r5, r2, r0)
            r0 = r16
        L_0x01ce:
            r10 = r0
            androidx.appfunctions.metadata.AppFunctionRuntimeMetadata r10 = (androidx.appfunctions.metadata.AppFunctionRuntimeMetadata) r10
            if (r10 != 0) goto L_0x01d4
            goto L_0x0226
        L_0x01d4:
            r2 = r30
            androidx.appfunctions.metadata.AppFunctionSchemaMetadata r0 = r2.buildSchemaMetadataFromGdForLegacyIndexer(r1)
            java.util.List r1 = r2.getAppFunctionParameterMetadata(r6, r0)
            if (r1 != 0) goto L_0x01e1
            goto L_0x0226
        L_0x01e1:
            androidx.appfunctions.metadata.AppFunctionResponseMetadata r3 = r2.getAppFunctionResponseMetadata(r6, r0)
            if (r3 != 0) goto L_0x01e8
            goto L_0x0226
        L_0x01e8:
            java.util.Set r4 = B1.a.S(r13)
            r7.L$0 = r12
            r7.L$1 = r13
            r7.L$2 = r6
            r7.L$3 = r10
            r7.L$4 = r0
            r7.L$5 = r1
            r7.L$6 = r3
            r7.L$7 = r2
            r7.L$8 = r13
            r7.L$9 = r6
            r7.L$10 = r0
            r5 = 4
            r7.label = r5
            java.lang.Object r4 = r2.searchTopLevelComponent(r11, r4, r7)
            if (r4 != r8) goto L_0x020c
        L_0x020b:
            return r8
        L_0x020c:
            r21 = r0
            r22 = r1
            r5 = r2
            r23 = r3
            r3 = r6
            r11 = r3
            r18 = r12
            r19 = r13
            r1 = r21
            r0 = r4
            r4 = r19
        L_0x021e:
            java.util.Map r0 = (java.util.Map) r0
            androidx.appfunctions.metadata.AppFunctionComponentsMetadata r24 = r5.getAppFunctionComponentsMetadata(r4, r3, r1, r0)
            if (r24 != 0) goto L_0x0227
        L_0x0226:
            return r16
        L_0x0227:
            androidx.appfunctions.metadata.AppFunctionMetadata r17 = new androidx.appfunctions.metadata.AppFunctionMetadata
            boolean r20 = r2.computeEffectivelyEnabled(r11, r10)
            r27 = 384(0x180, float:5.38E-43)
            r28 = 0
            r25 = 0
            r26 = 0
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return r17
        L_0x0239:
            androidx.appfunctions.AppFunctionFunctionNotFoundException r0 = new androidx.appfunctions.AppFunctionFunctionNotFoundException
            java.lang.String r1 = i.C0212a.n(r14, r12, r9, r13)
            r0.<init>(r1)
            throw r0
        L_0x0243:
            androidx.appfunctions.AppFunctionFunctionNotFoundException r0 = new androidx.appfunctions.AppFunctionFunctionNotFoundException
            java.lang.String r1 = i.C0212a.n(r14, r12, r9, r13)
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appfunctions.internal.AppSearchAppFunctionReader.getAppFunctionMetadata(java.lang.String, java.lang.String, qe.c):java.lang.Object");
    }
}
