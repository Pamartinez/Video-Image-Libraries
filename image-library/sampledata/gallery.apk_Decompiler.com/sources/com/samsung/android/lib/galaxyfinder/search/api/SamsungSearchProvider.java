package com.samsung.android.lib.galaxyfinder.search.api;

import E2.i;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sivs.ai.sdkcommon.asr.SpeechRecognitionConst;
import i.C0212a;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import lc.a;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b'\u0018\u0000 H2\u00020\u0001:\u0001IB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJM\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b\u0014\u0010\u0015JW\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016¢\u0006\u0004\b\u0014\u0010\u0018J\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ-\u0010\u001f\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0004\b\u001f\u0010 J#\u0010#\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016¢\u0006\u0004\b#\u0010$J1\u0010&\u001a\u00020%2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0016¢\u0006\u0004\b&\u0010'J;\u0010(\u001a\u00020%2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0016¢\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u00020\u000eH\u0016¢\u0006\u0004\b*\u0010+J\u0011\u0010-\u001a\u0004\u0018\u00010,H\u0016¢\u0006\u0004\b-\u0010.J\u0011\u0010/\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0004\b/\u0010+J\u0011\u00101\u001a\u0004\u0018\u000100H\u0016¢\u0006\u0004\b1\u00102J\u0011\u00104\u001a\u0004\u0018\u000103H&¢\u0006\u0004\b4\u00105J\u0011\u00106\u001a\u0004\u0018\u000103H&¢\u0006\u0004\b6\u00105J-\u00109\u001a\u0006\u0012\u0002\b\u0003082\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u00107\u001a\u00020%2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H&¢\u0006\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u000e8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b;\u0010<R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X.¢\u0006\u0006\n\u0004\b\u0007\u0010=R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X.¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020,8BX\u0004¢\u0006\u0006\u001a\u0004\bA\u0010.R\u0016\u0010D\u001a\u0004\u0018\u00010\u000e8BX\u0004¢\u0006\u0006\u001a\u0004\bC\u0010+R\u0014\u0010F\u001a\u00020E8BX\u0004¢\u0006\u0006\u001a\u0004\bF\u0010G¨\u0006J"}, d2 = {"Lcom/samsung/android/lib/galaxyfinder/search/api/SamsungSearchProvider;", "Landroid/content/ContentProvider;", "<init>", "()V", "Landroid/content/Context;", "context", "Landroid/content/pm/ProviderInfo;", "info", "Lme/x;", "attachInfo", "(Landroid/content/Context;Landroid/content/pm/ProviderInfo;)V", "Landroid/net/Uri;", "uri", "", "", "projection", "selection", "selectionArgs", "sortOrder", "Landroid/database/Cursor;", "query", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "Landroid/os/CancellationSignal;", "cancellationSignal", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;", "getType", "(Landroid/net/Uri;)Ljava/lang/String;", "method", "arg", "Landroid/os/Bundle;", "extras", "call", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Landroid/os/Bundle;", "Landroid/content/ContentValues;", "values", "insert", "(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;", "", "delete", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "getModuleKey", "()Ljava/lang/String;", "Landroid/graphics/drawable/Icon;", "getModuleIcon", "()Landroid/graphics/drawable/Icon;", "getModuleLabel", "Landroid/content/ComponentName;", "getLegacySearchActivity", "()Landroid/content/ComponentName;", "Landroid/content/Intent;", "makeAppLaunchIntent", "()Landroid/content/Intent;", "makeInAppSearchIntent", "limit", "Lnc/a;", "getSearchResult", "(Ljava/lang/String;ILandroid/os/CancellationSignal;)Lnc/a;", "providerKey", "Ljava/lang/String;", "Landroid/content/pm/ProviderInfo;", "Landroid/content/UriMatcher;", "matcher", "Landroid/content/UriMatcher;", "getModuleIconInternal", "moduleIconInternal", "getModuleLabelInternal", "moduleLabelInternal", "", "isSupportSearch", "()Z", "Companion", "lc/a", "DeviceSearchLibrary_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungSearchProvider extends ContentProvider {
    public static final a Companion = new Object();
    private static final int MATCH_SEARCH_CODE = 1;
    private static final String TAG = "SamsungSearchProvider";
    private static final String apiVersion = "2.1.4";
    private ProviderInfo info;
    private UriMatcher matcher;
    private String providerKey;

    private final Icon getModuleIconInternal() {
        ResolveInfo resolveInfo;
        PackageManager packageManager;
        Icon moduleIcon = getModuleIcon();
        if (moduleIcon != null) {
            return moduleIcon;
        }
        ProviderInfo providerInfo = this.info;
        if (providerInfo == null) {
            j.k("info");
            throw null;
        } else if (providerInfo.icon != 0) {
            Context context = getContext();
            ProviderInfo providerInfo2 = this.info;
            if (providerInfo2 != null) {
                Icon createWithResource = Icon.createWithResource(context, providerInfo2.icon);
                j.d(createWithResource, "createWithResource(context, info.icon)");
                return createWithResource;
            }
            j.k("info");
            throw null;
        } else {
            Intent makeAppLaunchIntent = makeAppLaunchIntent();
            if (makeAppLaunchIntent != null) {
                try {
                    Context context2 = getContext();
                    if (context2 == null || (packageManager = context2.getPackageManager()) == null) {
                        resolveInfo = null;
                    } else {
                        resolveInfo = packageManager.resolveActivity(makeAppLaunchIntent, 0);
                    }
                    if (resolveInfo != null) {
                        Icon createWithResource2 = Icon.createWithResource(getContext(), resolveInfo.getIconResource());
                        j.d(createWithResource2, "createWithResource(conte…resolveInfo.iconResource)");
                        return createWithResource2;
                    }
                } catch (Exception e) {
                    pc.a.b(e, "Fail to get Icon from AppLaunchIntent");
                }
            }
            ProviderInfo providerInfo3 = this.info;
            if (providerInfo3 == null) {
                j.k("info");
                throw null;
            } else if (providerInfo3.getIconResource() != 0) {
                Context context3 = getContext();
                ProviderInfo providerInfo4 = this.info;
                if (providerInfo4 != null) {
                    Icon createWithResource3 = Icon.createWithResource(context3, providerInfo4.getIconResource());
                    j.d(createWithResource3, "{\n                Icon.c…onResource)\n            }");
                    return createWithResource3;
                }
                j.k("info");
                throw null;
            } else {
                Icon createWithResource4 = Icon.createWithResource("android", 17301651);
                j.d(createWithResource4, "createWithResource(\"andr…rawable.sym_def_app_icon)");
                return createWithResource4;
            }
        }
    }

    private final String getModuleLabelInternal() {
        String moduleLabel = getModuleLabel();
        if (moduleLabel != null && moduleLabel.length() != 0) {
            return getModuleLabel();
        }
        ProviderInfo providerInfo = this.info;
        if (providerInfo == null) {
            j.k("info");
            throw null;
        } else if (providerInfo.labelRes != 0) {
            Context context = getContext();
            j.b(context);
            CharSequence loadLabel = providerInfo.loadLabel(context.getPackageManager());
            j.c(loadLabel, "null cannot be cast to non-null type kotlin.String");
            return (String) loadLabel;
        } else {
            Intent makeAppLaunchIntent = makeAppLaunchIntent();
            if (makeAppLaunchIntent != null) {
                try {
                    Context context2 = getContext();
                    j.b(context2);
                    ResolveInfo resolveActivity = context2.getPackageManager().resolveActivity(makeAppLaunchIntent, 0);
                    if (resolveActivity != null) {
                        Context context3 = getContext();
                        j.b(context3);
                        CharSequence loadLabel2 = resolveActivity.loadLabel(context3.getPackageManager());
                        j.c(loadLabel2, "null cannot be cast to non-null type kotlin.String");
                        return (String) loadLabel2;
                    }
                } catch (Exception e) {
                    pc.a.b(e, "Fail to get Label from AppLaunchIntent");
                }
            }
            ProviderInfo providerInfo2 = this.info;
            if (providerInfo2 != null) {
                Context context4 = getContext();
                j.b(context4);
                CharSequence loadLabel3 = providerInfo2.loadLabel(context4.getPackageManager());
                j.c(loadLabel3, "null cannot be cast to non-null type kotlin.String");
                return (String) loadLabel3;
            }
            j.k("info");
            throw null;
        }
    }

    private final boolean isSupportSearch() {
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        j.e(context, "context");
        j.e(providerInfo, "info");
        this.info = providerInfo;
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.matcher = uriMatcher;
        ProviderInfo providerInfo2 = this.info;
        if (providerInfo2 != null) {
            uriMatcher.addURI(providerInfo2.authority, "v1/search", 1);
            UriMatcher uriMatcher2 = this.matcher;
            if (uriMatcher2 != null) {
                ProviderInfo providerInfo3 = this.info;
                if (providerInfo3 != null) {
                    uriMatcher2.addURI(providerInfo3.authority, (String) null, 1);
                    if (!providerInfo.exported) {
                        throw new SecurityException("Provider must be exported");
                    } else if (!providerInfo.grantUriPermissions) {
                        throw new SecurityException("Provider must grantUriPermissions");
                    } else if (providerInfo.readPermission == null) {
                        throw new SecurityException("Provider must be protected by permission");
                    } else if (!TextUtils.isEmpty(getModuleKey())) {
                        this.providerKey = getModuleKey();
                        if (isSupportSearch()) {
                            super.attachInfo(context, providerInfo);
                            return;
                        }
                        throw new IllegalStateException("One or more features must be supported.");
                    } else {
                        throw new IllegalArgumentException("This key should be set.");
                    }
                } else {
                    j.k("info");
                    throw null;
                }
            } else {
                j.k("matcher");
                throw null;
            }
        } else {
            j.k("info");
            throw null;
        }
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        j.e(str, "method");
        pc.a.a(TAG, "call method: ".concat(str));
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!str.equals("getInfo")) {
            return null;
        }
        String str3 = this.providerKey;
        if (str3 != null) {
            bundle.putString("module_key", str3);
            bundle.putString(SpeechRecognitionConst.Key.INFO_API_LEVEL, apiVersion);
            bundle.putString("label", getModuleLabelInternal());
            bundle.putParcelable("icon", getModuleIconInternal());
            bundle.putParcelable("launchIntent", makeAppLaunchIntent());
            bundle.putParcelable("legacySearchActivity", getLegacySearchActivity());
            bundle.putParcelable("inAppSearchActivity", makeInAppSearchIntent());
            return bundle;
        }
        j.k("providerKey");
        throw null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        throw new UnsupportedOperationException("Delete not supported");
    }

    public ComponentName getLegacySearchActivity() {
        return null;
    }

    public Icon getModuleIcon() {
        return null;
    }

    public String getModuleKey() {
        ProviderInfo providerInfo = this.info;
        if (providerInfo != null) {
            String str = providerInfo.packageName;
            j.d(str, "info.packageName");
            return str;
        }
        j.k("info");
        throw null;
    }

    public String getModuleLabel() {
        return null;
    }

    public abstract nc.a getSearchResult(String str, int i2, CancellationSignal cancellationSignal);

    public String getType(Uri uri) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        UriMatcher uriMatcher = this.matcher;
        if (uriMatcher == null) {
            j.k("matcher");
            throw null;
        } else if (uriMatcher.match(uri) == 1) {
            return "vnd.android.cursor.dir/vnd.search";
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        throw new UnsupportedOperationException("Insert not supported");
    }

    public abstract Intent makeAppLaunchIntent();

    public abstract Intent makeInAppSearchIntent();

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        return query(uri, strArr, str, strArr2, str2, (CancellationSignal) null);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        j.e(uri, OCRServiceConstant.KEY_PARAM_URI);
        throw new UnsupportedOperationException("Update not supported");
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
        String str3;
        Integer num;
        Uri uri2 = uri;
        j.e(uri2, OCRServiceConstant.KEY_PARAM_URI);
        UriMatcher uriMatcher = this.matcher;
        if (uriMatcher == null) {
            j.k("matcher");
            throw null;
        } else if (uriMatcher.match(uri2) != 1) {
            throw new UnsupportedOperationException("Unknown Uri " + uri2);
        } else if (isSupportSearch()) {
            try {
                str3 = Uri.decode(uri2.getQueryParameter(Contract.QUERY));
            } catch (Exception unused) {
                str3 = null;
            }
            String queryParameter = uri2.getQueryParameter("requestId");
            try {
                String queryParameter2 = uri2.getQueryParameter("limit");
                num = queryParameter2 != null ? Integer.valueOf(Integer.parseInt(queryParameter2)) : null;
            } catch (Exception unused2) {
                num = -1;
            }
            if (!(str3 == null || str3.length() == 0 || queryParameter == null || queryParameter.length() == 0 || num == null)) {
                try {
                    nc.a searchResult = getSearchResult(str3, num.intValue(), cancellationSignal);
                    searchResult.getClass();
                    Object[] copyOf = Arrays.copyOf(new String[]{"key", "icon", "text", "text2", "group", "view_payload", "action1_label", "action1_payload", "action2_label", "action2_payload", "action3_label", "action3_payload", "extras"}, 15);
                    System.arraycopy(new String[]{"runningTime", "contentDescription"}, 0, copyOf, 13, 2);
                    j.d(copyOf, "merge");
                    String[] strArr3 = (String[]) copyOf;
                    Object[] copyOf2 = Arrays.copyOf(new String[]{apiVersion, searchResult.f3280a, "Thumbnail1", "1", String.valueOf(-1)}, strArr3.length);
                    j.d(copyOf2, "copyOf(this, newSize)");
                    MatrixCursor matrixCursor = new MatrixCursor(strArr3);
                    matrixCursor.addRow((String[]) copyOf2);
                    Iterator it = searchResult.b.iterator();
                    while (it.hasNext()) {
                        oc.a aVar = (oc.a) it.next();
                        String str4 = aVar.f3283a;
                        Uri uri3 = aVar.b;
                        i iVar = aVar.f3284c;
                        String l = iVar != null ? C0212a.l("intent://", iVar.f167a) : null;
                        aVar.a(0);
                        aVar.b(0);
                        aVar.a(1);
                        aVar.b(1);
                        aVar.a(2);
                        aVar.b(2);
                        Object[] copyOf3 = Arrays.copyOf(new Object[]{str4, uri3, null, null, null, l, null, null, null, null, null, null, null}, 15);
                        System.arraycopy(new Object[]{aVar.e, aVar.f}, 0, copyOf3, 13, 2);
                        j.d(copyOf3, "merge");
                        matrixCursor.addRow(copyOf3);
                    }
                    return matrixCursor;
                } catch (Exception e) {
                    try {
                        Log.d("SamSearch_".concat(TAG), "SearchResult is fail", e);
                    } catch (Exception unused3) {
                    }
                }
            }
            return null;
        } else {
            throw new UnsupportedOperationException("Unsupported Uri " + uri2);
        }
    }
}
