package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.InflateException;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PreferenceInflater {
    private static final HashMap<String, Constructor<?>> CONSTRUCTOR_MAP = new HashMap<>();
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private final Object[] mConstructorArgs = new Object[2];
    private final Context mContext;
    private String[] mDefaultPackages;
    private PreferenceManager mPreferenceManager;

    public PreferenceInflater(Context context, PreferenceManager preferenceManager) {
        this.mContext = context;
        init(preferenceManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        r12 = new android.view.InflateException(r13.getPositionDescription() + ": Error inflating class " + r11);
        r12.initCause(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0098, code lost:
        throw r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009a, code lost:
        throw r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036 A[ExcHandler: Exception (r10v5 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:2:0x000d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.preference.Preference createItem(java.lang.String r11, java.lang.String[] r12, android.util.AttributeSet r13) {
        /*
            r10 = this;
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r0 = CONSTRUCTOR_MAP
            java.lang.Object r0 = r0.get(r11)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            java.lang.String r1 = ": Error inflating class "
            r2 = 1
            if (r0 != 0) goto L_0x006f
            android.content.Context r0 = r10.mContext     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r3 = 0
            if (r12 == 0) goto L_0x005d
            int r4 = r12.length     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            if (r4 != 0) goto L_0x001a
            goto L_0x005d
        L_0x001a:
            int r4 = r12.length     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r5 = 0
            r6 = r3
            r7 = r5
        L_0x001e:
            if (r6 >= r4) goto L_0x003c
            r8 = r12[r6]     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            r9.<init>()     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            r9.append(r8)     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            r9.append(r11)     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            java.lang.String r8 = r9.toString()     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            java.lang.Class r5 = java.lang.Class.forName(r8, r3, r0)     // Catch:{ ClassNotFoundException -> 0x0038, Exception -> 0x0036 }
            goto L_0x003c
        L_0x0036:
            r10 = move-exception
            goto L_0x007a
        L_0x0038:
            r7 = move-exception
            int r6 = r6 + 1
            goto L_0x001e
        L_0x003c:
            if (r5 != 0) goto L_0x0061
            if (r7 != 0) goto L_0x005c
            android.view.InflateException r10 = new android.view.InflateException     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r12.<init>()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.String r0 = r13.getPositionDescription()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r12.append(r0)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r12.append(r1)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r12.append(r11)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.String r12 = r12.toString()     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r10.<init>(r12)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            throw r10     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
        L_0x005c:
            throw r7     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
        L_0x005d:
            java.lang.Class r5 = java.lang.Class.forName(r11, r3, r0)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
        L_0x0061:
            java.lang.Class<?>[] r12 = CONSTRUCTOR_SIGNATURE     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.reflect.Constructor r0 = r5.getConstructor(r12)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r0.setAccessible(r2)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<?>> r12 = CONSTRUCTOR_MAP     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r12.put(r11, r0)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
        L_0x006f:
            java.lang.Object[] r10 = r10.mConstructorArgs     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            r10[r2] = r13     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            java.lang.Object r10 = r0.newInstance(r10)     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            androidx.preference.Preference r10 = (androidx.preference.Preference) r10     // Catch:{ ClassNotFoundException -> 0x0099, Exception -> 0x0036 }
            return r10
        L_0x007a:
            android.view.InflateException r12 = new android.view.InflateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r13 = r13.getPositionDescription()
            r0.append(r13)
            r0.append(r1)
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            r12.<init>(r11)
            r12.initCause(r10)
            throw r12
        L_0x0099:
            r10 = move-exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.PreferenceInflater.createItem(java.lang.String, java.lang.String[], android.util.AttributeSet):androidx.preference.Preference");
    }

    private Preference createItemFromTag(String str, AttributeSet attributeSet) {
        try {
            if (-1 == str.indexOf(46)) {
                return onCreateItem(str, attributeSet);
            }
            return createItem(str, (String[]) null, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (ClassNotFoundException e7) {
            InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class (not found)" + str);
            inflateException.initCause(e7);
            throw inflateException;
        } catch (Exception e8) {
            InflateException inflateException2 = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException2.initCause(e8);
            throw inflateException2;
        }
    }

    private void init(PreferenceManager preferenceManager) {
        this.mPreferenceManager = preferenceManager;
        setDefaultPackages(new String[]{Preference.class.getPackage().getName() + ".", SwitchPreference.class.getPackage().getName() + "."});
    }

    private PreferenceGroup onMergeRoots(PreferenceGroup preferenceGroup, PreferenceGroup preferenceGroup2) {
        if (preferenceGroup != null) {
            return preferenceGroup;
        }
        preferenceGroup2.onAttachedToHierarchy(this.mPreferenceManager);
        return preferenceGroup2;
    }

    private void rInflate(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if ("intent".equals(name)) {
                    try {
                        preference.setIntent(Intent.parseIntent(getContext().getResources(), xmlPullParser, attributeSet));
                    } catch (IOException e) {
                        XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException.initCause(e);
                        throw xmlPullParserException;
                    }
                } else if (KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA.equals(name)) {
                    getContext().getResources().parseBundleExtra(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, attributeSet, preference.getExtras());
                    try {
                        skipCurrentTag(xmlPullParser);
                    } catch (IOException e7) {
                        XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException2.initCause(e7);
                        throw xmlPullParserException2;
                    }
                } else {
                    Preference createItemFromTag = createItemFromTag(name, attributeSet);
                    ((PreferenceGroup) preference).addItemFromInflater(createItemFromTag);
                    rInflate(xmlPullParser, createItemFromTag, attributeSet);
                }
            }
        }
    }

    private static void skipCurrentTag(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Preference inflate(int i2, PreferenceGroup preferenceGroup) {
        XmlResourceParser xml = getContext().getResources().getXml(i2);
        try {
            return inflate((XmlPullParser) xml, preferenceGroup);
        } finally {
            xml.close();
        }
    }

    public Preference onCreateItem(String str, AttributeSet attributeSet) {
        return createItem(str, this.mDefaultPackages, attributeSet);
    }

    public void setDefaultPackages(String[] strArr) {
        this.mDefaultPackages = strArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a A[Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035 A[SYNTHETIC, Splitter:B:18:0x0035] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.preference.Preference inflate(org.xmlpull.v1.XmlPullParser r6, androidx.preference.PreferenceGroup r7) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.mConstructorArgs
            monitor-enter(r0)
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r6)     // Catch:{ all -> 0x002d }
            java.lang.Object[] r2 = r5.mConstructorArgs     // Catch:{ all -> 0x002d }
            android.content.Context r3 = r5.mContext     // Catch:{ all -> 0x002d }
            r4 = 0
            r2[r4] = r3     // Catch:{ all -> 0x002d }
        L_0x000e:
            int r2 = r6.next()     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r3 = 2
            if (r2 == r3) goto L_0x0018
            r4 = 1
            if (r2 != r4) goto L_0x000e
        L_0x0018:
            if (r2 != r3) goto L_0x0035
            java.lang.String r2 = r6.getName()     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            androidx.preference.Preference r2 = r5.createItemFromTag(r2, r1)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            androidx.preference.PreferenceGroup r2 = (androidx.preference.PreferenceGroup) r2     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            androidx.preference.PreferenceGroup r7 = r5.onMergeRoots(r7, r2)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r5.rInflate(r6, r7, r1)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r7
        L_0x002d:
            r5 = move-exception
            goto L_0x0083
        L_0x002f:
            r5 = move-exception
            goto L_0x0050
        L_0x0031:
            r5 = move-exception
            goto L_0x0075
        L_0x0033:
            r5 = move-exception
            goto L_0x0082
        L_0x0035:
            android.view.InflateException r5 = new android.view.InflateException     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r7.<init>()     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            java.lang.String r1 = r6.getPositionDescription()     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r7.append(r1)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            java.lang.String r1 = ": No start tag found!"
            r7.append(r1)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            java.lang.String r7 = r7.toString()     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            r5.<init>(r7)     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
            throw r5     // Catch:{ InflateException -> 0x0033, XmlPullParserException -> 0x0031, IOException -> 0x002f }
        L_0x0050:
            android.view.InflateException r7 = new android.view.InflateException     // Catch:{ all -> 0x002d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x002d }
            r1.<init>()     // Catch:{ all -> 0x002d }
            java.lang.String r6 = r6.getPositionDescription()     // Catch:{ all -> 0x002d }
            r1.append(r6)     // Catch:{ all -> 0x002d }
            java.lang.String r6 = ": "
            r1.append(r6)     // Catch:{ all -> 0x002d }
            java.lang.String r6 = r5.getMessage()     // Catch:{ all -> 0x002d }
            r1.append(r6)     // Catch:{ all -> 0x002d }
            java.lang.String r6 = r1.toString()     // Catch:{ all -> 0x002d }
            r7.<init>(r6)     // Catch:{ all -> 0x002d }
            r7.initCause(r5)     // Catch:{ all -> 0x002d }
            throw r7     // Catch:{ all -> 0x002d }
        L_0x0075:
            android.view.InflateException r6 = new android.view.InflateException     // Catch:{ all -> 0x002d }
            java.lang.String r7 = r5.getMessage()     // Catch:{ all -> 0x002d }
            r6.<init>(r7)     // Catch:{ all -> 0x002d }
            r6.initCause(r5)     // Catch:{ all -> 0x002d }
            throw r6     // Catch:{ all -> 0x002d }
        L_0x0082:
            throw r5     // Catch:{ all -> 0x002d }
        L_0x0083:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.PreferenceInflater.inflate(org.xmlpull.v1.XmlPullParser, androidx.preference.PreferenceGroup):androidx.preference.Preference");
    }
}
