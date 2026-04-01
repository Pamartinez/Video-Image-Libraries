package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.core.util.ObjectsCompat;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.scsp.common.ContentType;
import com.samsung.scsp.media.file.FileApiContract;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileProvider extends ContentProvider {
    private static final String[] COLUMNS = {"_display_name", IParameterKey.SIZE};
    private static final File DEVICE_ROOT = new File("/");
    private static final HashMap<String, PathStrategy> sCache = new HashMap<>();
    private String mAuthority;
    private PathStrategy mLocalPathStrategy;
    private final Object mLock;
    private final int mResourceId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static File[] getExternalMediaDirs(Context context) {
            return context.getExternalMediaDirs();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimplePathStrategy implements PathStrategy {
        private final String mAuthority;
        private final HashMap<String, File> mRoots = new HashMap<>();

        public SimplePathStrategy(String str) {
            this.mAuthority = str;
        }

        private boolean belongsToRoot(String str, String str2) {
            String access$000 = FileProvider.removeTrailingSlash(str);
            String access$0002 = FileProvider.removeTrailingSlash(str2);
            if (access$000.equals(access$0002)) {
                return true;
            }
            if (access$000.startsWith(access$0002 + '/')) {
                return true;
            }
            return false;
        }

        public void addRoot(String str, File file) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.mRoots.put(str, file.getCanonicalFile());
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty");
            }
        }

        public File getFileForUri(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.mRoots.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    if (belongsToRoot(canonicalFile.getPath(), file.getPath())) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
                }
            } else {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
        }

        public Uri getUriForFile(File file) {
            String str;
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry entry = null;
                for (Map.Entry next : this.mRoots.entrySet()) {
                    String path = ((File) next.getValue()).getPath();
                    if (belongsToRoot(canonicalPath, path) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                        entry = next;
                    }
                }
                if (entry != null) {
                    String path2 = ((File) entry.getValue()).getPath();
                    if (path2.endsWith("/")) {
                        str = canonicalPath.substring(path2.length());
                    } else {
                        str = canonicalPath.substring(path2.length() + 1);
                    }
                    return new Uri.Builder().scheme("content").authority(this.mAuthority).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(str, "/")).build();
                }
                throw new IllegalArgumentException(C0212a.l("Failed to find configured root that contains ", canonicalPath));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }
    }

    public FileProvider() {
        this(0);
    }

    private static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    private static String[] copyOf(String[] strArr, int i2) {
        String[] strArr2 = new String[i2];
        System.arraycopy(strArr, 0, strArr2, 0, i2);
        return strArr2;
    }

    public static XmlResourceParser getFileProviderPathsMetaData(Context context, String str, ProviderInfo providerInfo, int i2) {
        if (providerInfo != null) {
            if (providerInfo.metaData == null && i2 != 0) {
                Bundle bundle = new Bundle(1);
                providerInfo.metaData = bundle;
                bundle.putInt("android.support.FILE_PROVIDER_PATHS", i2);
            }
            XmlResourceParser loadXmlMetaData = providerInfo.loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
            if (loadXmlMetaData != null) {
                return loadXmlMetaData;
            }
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        throw new IllegalArgumentException(C0212a.l("Couldn't find meta-data for provider with authority ", str));
    }

    private PathStrategy getLocalPathStrategy() {
        PathStrategy pathStrategy;
        synchronized (this.mLock) {
            try {
                ObjectsCompat.requireNonNull(this.mAuthority, "mAuthority is null. Did you override attachInfo and did not call super.attachInfo()?");
                if (this.mLocalPathStrategy == null) {
                    this.mLocalPathStrategy = getPathStrategy(getContext(), this.mAuthority, this.mResourceId);
                }
                pathStrategy = this.mLocalPathStrategy;
            } catch (Throwable th) {
                throw th;
            }
        }
        return pathStrategy;
    }

    private static PathStrategy getPathStrategy(Context context, String str, int i2) {
        PathStrategy pathStrategy;
        HashMap<String, PathStrategy> hashMap = sCache;
        synchronized (hashMap) {
            try {
                pathStrategy = hashMap.get(str);
                if (pathStrategy == null) {
                    pathStrategy = parsePathStrategy(context, str, i2);
                    hashMap.put(str, pathStrategy);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
            } catch (XmlPullParserException e7) {
                throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e7);
            } catch (Throwable th) {
                throw th;
            }
        }
        return pathStrategy;
    }

    public static Uri getUriForFile(Context context, String str, File file) {
        return getPathStrategy(context, str, 0).getUriForFile(file);
    }

    private static int modeToMode(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException(C0212a.l("Invalid mode: ", str));
    }

    private static PathStrategy parsePathStrategy(Context context, String str, int i2) {
        SimplePathStrategy simplePathStrategy = new SimplePathStrategy(str);
        XmlResourceParser fileProviderPathsMetaData = getFileProviderPathsMetaData(context, str, context.getPackageManager().resolveContentProvider(str, 128), i2);
        while (true) {
            int next = fileProviderPathsMetaData.next();
            if (next == 1) {
                return simplePathStrategy;
            }
            if (next == 2) {
                String name = fileProviderPathsMetaData.getName();
                File file = null;
                String attributeValue = fileProviderPathsMetaData.getAttributeValue((String) null, "name");
                String attributeValue2 = fileProviderPathsMetaData.getAttributeValue((String) null, FileApiContract.Parameter.PATH);
                if ("root-path".equals(name)) {
                    file = DEVICE_ROOT;
                } else if ("files-path".equals(name)) {
                    file = context.getFilesDir();
                } else if ("cache-path".equals(name)) {
                    file = context.getCacheDir();
                } else if ("external-path".equals(name)) {
                    file = Environment.getExternalStorageDirectory();
                } else if ("external-files-path".equals(name)) {
                    File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context, (String) null);
                    if (externalFilesDirs.length > 0) {
                        file = externalFilesDirs[0];
                    }
                } else if ("external-cache-path".equals(name)) {
                    File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context);
                    if (externalCacheDirs.length > 0) {
                        file = externalCacheDirs[0];
                    }
                } else if ("external-media-path".equals(name)) {
                    File[] externalMediaDirs = Api21Impl.getExternalMediaDirs(context);
                    if (externalMediaDirs.length > 0) {
                        file = externalMediaDirs[0];
                    }
                }
                if (file != null) {
                    simplePathStrategy.addRoot(attributeValue, buildPath(file, attributeValue2));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static String removeTrailingSlash(String str) {
        if (str.length() <= 0 || str.charAt(str.length() - 1) != '/') {
            return str;
        }
        return C0280e.d(1, 0, str);
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            String str = providerInfo.authority.split(";")[0];
            synchronized (this.mLock) {
                this.mAuthority = str;
            }
            HashMap<String, PathStrategy> hashMap = sCache;
            synchronized (hashMap) {
                hashMap.remove(str);
            }
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return getLocalPathStrategy().getFileForUri(uri).delete() ? 1 : 0;
    }

    public String getType(Uri uri) {
        String mimeTypeFromExtension;
        File fileForUri = getLocalPathStrategy().getFileForUri(uri);
        int lastIndexOf = fileForUri.getName().lastIndexOf(46);
        if (lastIndexOf < 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileForUri.getName().substring(lastIndexOf + 1))) == null) {
            return ContentType.OCTET_STREAM;
        }
        return mimeTypeFromExtension;
    }

    public String getTypeAnonymous(Uri uri) {
        return ContentType.OCTET_STREAM;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public boolean onCreate() {
        return true;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(getLocalPathStrategy().getFileForUri(uri), modeToMode(str));
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i2;
        String str3;
        File fileForUri = getLocalPathStrategy().getFileForUri(uri);
        String queryParameter = uri.getQueryParameter("displayName");
        if (strArr == null) {
            strArr = COLUMNS;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i7 = 0;
        for (String str4 : strArr) {
            if ("_display_name".equals(str4)) {
                strArr3[i7] = "_display_name";
                i2 = i7 + 1;
                if (queryParameter == null) {
                    str3 = fileForUri.getName();
                } else {
                    str3 = queryParameter;
                }
                objArr[i7] = str3;
            } else if (IParameterKey.SIZE.equals(str4)) {
                strArr3[i7] = IParameterKey.SIZE;
                i2 = i7 + 1;
                objArr[i7] = Long.valueOf(fileForUri.length());
            }
            i7 = i2;
        }
        String[] copyOf = copyOf(strArr3, i7);
        Object[] copyOf2 = copyOf(objArr, i7);
        MatrixCursor matrixCursor = new MatrixCursor(copyOf, 1);
        matrixCursor.addRow(copyOf2);
        return matrixCursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public FileProvider(int i2) {
        this.mLock = new Object();
        this.mResourceId = i2;
    }

    private static Object[] copyOf(Object[] objArr, int i2) {
        Object[] objArr2 = new Object[i2];
        System.arraycopy(objArr, 0, objArr2, 0, i2);
        return objArr2;
    }
}
