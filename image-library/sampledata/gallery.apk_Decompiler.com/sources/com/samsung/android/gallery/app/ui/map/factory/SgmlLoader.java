package com.samsung.android.gallery.app.ui.map.factory;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.CancellationSignal;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.map.abstraction.IMapFactory;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileAuth;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import dalvik.system.DexClassLoader;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SgmlLoader {
    private static final String FACTORY_CLASS = "com.samsung.android.gallery.app.ui.map.factory.AMapMapFactory";
    static final String REPOSITORY = Logger.decrypt(new byte[]{92, -71, 82, 75, 18, -14, 64, 90, 16, -14, 84, 94, 31, -79, 86, 77, 10, 82});
    private static final String TAG = "SgmlLoader";
    private static volatile boolean sCanReload = true;
    private static volatile DexClassLoader sLoader = null;
    static final int versionCode = 1;

    private static boolean canReload(int i2) {
        if (!FileUtils.delete(getLibPath(i2))) {
            Log.w(TAG, "fail deleted last version");
        }
        if (!sCanReload) {
            return false;
        }
        sCanReload = false;
        Log.e(TAG, "try reload");
        return true;
    }

    public static IMapFactory createFactory() {
        try {
            return createFactory(1);
        } catch (Error | Exception unused) {
            return new EmptyMapFactory();
        }
    }

    private static DexClassLoader getDexClassLoader(Context context, int i2) {
        if (sLoader == null) {
            long currentTimeMillis = System.currentTimeMillis();
            String updateLibrary = updateLibrary(i2);
            try {
                sLoader = new DexClassLoader(updateLibrary, REPOSITORY, (String) null, context.getClass().getClassLoader());
            } catch (SecurityException unused) {
                if (!FileAuth.setOnlyReadable(updateLibrary)) {
                    FileUtils.delete(updateLibrary);
                    updateLibrary = updateLibrary(i2);
                }
                sLoader = new DexClassLoader(updateLibrary, REPOSITORY, (String) null, context.getClass().getClassLoader());
            }
            Log.i(TAG, "getDexClassLoader" + Logger.vt(Logger.getSimpleName((Object) sLoader), FileUtils.getNameFromPath(updateLibrary), Long.valueOf(currentTimeMillis)));
        }
        return sLoader;
    }

    private static String getLibName(int i2) {
        return C0212a.j(i2, "sgml_", ".jar");
    }

    private static String getLibPath(int i2) {
        return REPOSITORY + "/" + getLibName(i2);
    }

    private static void handleError(String str) {
        Log.e(TAG, str);
    }

    private static AssetFileDescriptor openFileDescriptor(Context context, int i2) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver.openAssetFile(Uri.parse("content://com.samsung.android.gallery.plugins.MapProvider/" + i2), "r", (CancellationSignal) null);
    }

    public static void updateLibrary() {
        updateLibrary(1);
    }

    private static synchronized String updateLibrary(int i2) {
        String libPath;
        AssetFileDescriptor openFileDescriptor;
        FileInputStream createInputStream;
        synchronized (SgmlLoader.class) {
            libPath = getLibPath(i2);
            SecureFile secureFile = new SecureFile(libPath);
            if (!secureFile.exists()) {
                long currentTimeMillis = System.currentTimeMillis();
                FileUtils.makeDirectoryIfAbsent(REPOSITORY);
                try {
                    openFileDescriptor = openFileDescriptor(AppResources.getAppContext(), i2);
                    createInputStream = openFileDescriptor.createInputStream();
                    FileUtils.writeFile((File) secureFile, (InputStream) createInputStream);
                    FileAuth.setOnlyReadable(libPath);
                    String str = "write" + Logger.vt(Integer.valueOf(i2), secureFile.getName(), Long.valueOf(secureFile.length()), Long.valueOf(currentTimeMillis));
                    Log.i(TAG, "updateLibrary " + str);
                    DebugLogger.getInstance().lambda$apply$0(TAG, str);
                    if (createInputStream != null) {
                        createInputStream.close();
                    }
                    openFileDescriptor.close();
                } catch (IOException | NullPointerException e) {
                    Log.e(TAG, "updateLibrary failed. e=" + e.getMessage());
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
        }
        return libPath;
        throw th;
        throw th;
    }

    private static IMapFactory createFactory(int i2) {
        Class<?> cls = Reflector.getClass(FACTORY_CLASS, getDexClassLoader(AppResources.getAppContext(), i2));
        if (cls != null) {
            try {
                IMapFactory iMapFactory = (IMapFactory) cls.newInstance();
                int versionCode2 = iMapFactory.getVersionCode();
                if (versionCode2 == i2) {
                    return iMapFactory;
                }
                Log.e(TAG, "Version mismatched. expected=1/loaded=" + versionCode2);
                if (canReload(i2)) {
                    return createFactory(i2);
                }
                handleError("AMapMapFactory version mismatched");
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else if (canReload(i2)) {
            return createFactory(i2);
        } else {
            handleError("AMapMapFactory not exist");
        }
        return new EmptyMapFactory();
    }
}
