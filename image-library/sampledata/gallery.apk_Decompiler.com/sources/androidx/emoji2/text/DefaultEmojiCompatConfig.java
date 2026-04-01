package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.util.Log;
import androidx.core.provider.FontRequest;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DefaultEmojiCompatConfig {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultEmojiCompatConfigFactory {
        private final DefaultEmojiCompatConfigHelper mHelper;

        public DefaultEmojiCompatConfigFactory(DefaultEmojiCompatConfigHelper defaultEmojiCompatConfigHelper) {
            this.mHelper = defaultEmojiCompatConfigHelper == null ? getHelperForApi() : defaultEmojiCompatConfigHelper;
        }

        private EmojiCompat.Config configOrNull(Context context, FontRequest fontRequest) {
            if (fontRequest == null) {
                return null;
            }
            return new FontRequestEmojiCompatConfig(context, fontRequest);
        }

        private List<List<byte[]>> convertToByteArray(Signature[] signatureArr) {
            ArrayList arrayList = new ArrayList();
            for (Signature byteArray : signatureArr) {
                arrayList.add(byteArray.toByteArray());
            }
            return Collections.singletonList(arrayList);
        }

        private FontRequest generateFontRequestFrom(ProviderInfo providerInfo, PackageManager packageManager) {
            String str = providerInfo.authority;
            String str2 = providerInfo.packageName;
            return new FontRequest(str, str2, "emojicompat-emoji-font", convertToByteArray(this.mHelper.getSigningSignatures(packageManager, str2)));
        }

        private static DefaultEmojiCompatConfigHelper getHelperForApi() {
            return new DefaultEmojiCompatConfigHelper_API28();
        }

        private boolean hasFlagSystem(ProviderInfo providerInfo) {
            ApplicationInfo applicationInfo;
            if (providerInfo == null || (applicationInfo = providerInfo.applicationInfo) == null || (applicationInfo.flags & 1) != 1) {
                return false;
            }
            return true;
        }

        private ProviderInfo queryDefaultInstalledContentProvider(PackageManager packageManager) {
            for (ResolveInfo providerInfo : this.mHelper.queryIntentContentProviders(packageManager, new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0)) {
                ProviderInfo providerInfo2 = this.mHelper.getProviderInfo(providerInfo);
                if (hasFlagSystem(providerInfo2)) {
                    return providerInfo2;
                }
            }
            return null;
        }

        public EmojiCompat.Config create(Context context) {
            return configOrNull(context, queryForDefaultFontRequest(context));
        }

        public FontRequest queryForDefaultFontRequest(Context context) {
            PackageManager packageManager = context.getPackageManager();
            Preconditions.checkNotNull(packageManager, "Package manager required to locate emoji font provider");
            ProviderInfo queryDefaultInstalledContentProvider = queryDefaultInstalledContentProvider(packageManager);
            if (queryDefaultInstalledContentProvider == null) {
                return null;
            }
            try {
                return generateFontRequestFrom(queryDefaultInstalledContentProvider, packageManager);
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e);
                return null;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultEmojiCompatConfigHelper {
        public abstract ProviderInfo getProviderInfo(ResolveInfo resolveInfo);

        public abstract Signature[] getSigningSignatures(PackageManager packageManager, String str);

        public abstract List<ResolveInfo> queryIntentContentProviders(PackageManager packageManager, Intent intent, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultEmojiCompatConfigHelper_API19 extends DefaultEmojiCompatConfigHelper {
        public ProviderInfo getProviderInfo(ResolveInfo resolveInfo) {
            return resolveInfo.providerInfo;
        }

        public List<ResolveInfo> queryIntentContentProviders(PackageManager packageManager, Intent intent, int i2) {
            return packageManager.queryIntentContentProviders(intent, i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultEmojiCompatConfigHelper_API28 extends DefaultEmojiCompatConfigHelper_API19 {
        public Signature[] getSigningSignatures(PackageManager packageManager, String str) {
            return packageManager.getPackageInfo(str, 64).signatures;
        }
    }

    public static FontRequestEmojiCompatConfig create(Context context) {
        return (FontRequestEmojiCompatConfig) new DefaultEmojiCompatConfigFactory((DefaultEmojiCompatConfigHelper) null).create(context);
    }
}
