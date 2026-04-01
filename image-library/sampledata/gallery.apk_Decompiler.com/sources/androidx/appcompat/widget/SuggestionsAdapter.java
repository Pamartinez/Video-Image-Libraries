package androidx.appcompat.widget;

import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.ResourceCursorAdapter;
import c0.C0086a;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuggestionsAdapter extends ResourceCursorAdapter implements View.OnClickListener {
    private boolean mClosed = false;
    private final int mCommitIconResId;
    private int mFlagsCol = -1;
    private int mIconName1Col = -1;
    private int mIconName2Col = -1;
    private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement = 1;
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mSpannableTextColor = -16736050;
    private int mText1Col = -1;
    private int mText2Col = -1;
    private int mText2UrlCol = -1;
    private ColorStateList mUrlColor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ChildViewCache {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;

        public ChildViewCache(View view) {
            this.mText1 = (TextView) view.findViewById(16908308);
            this.mText2 = (TextView) view.findViewById(16908309);
            this.mIcon1 = (ImageView) view.findViewById(16908295);
            this.mIcon2 = (ImageView) view.findViewById(16908296);
            this.mIconRefine = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), (Cursor) null, true);
        this.mSearchView = searchView;
        this.mSearchable = searchableInfo;
        this.mCommitIconResId = searchView.getSuggestionCommitIconResId();
        this.mProviderContext = context;
        this.mOutsideDrawablesCache = weakHashMap;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().data, new int[]{16843828});
        this.mSpannableTextColor = obtainStyledAttributes.getColor(0, -16736050);
        obtainStyledAttributes.recycle();
    }

    private Drawable checkIconCache(String str) {
        Drawable.ConstantState constantState = this.mOutsideDrawablesCache.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private CharSequence formatUrl(CharSequence charSequence) {
        if (this.mUrlColor == null) {
            TypedValue typedValue = new TypedValue();
            this.mProviderContext.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.mUrlColor = this.mProviderContext.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan((String) null, 0, 0, this.mUrlColor, (ColorStateList) null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable getActivityIcon(ComponentName componentName) {
        PackageManager packageManager = this.mProviderContext.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            StringBuilder o2 = C0086a.o(iconResource, "Invalid icon resource ", " for ");
            o2.append(componentName.flattenToShortString());
            Log.w("SuggestionsAdapter", o2.toString());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    private Drawable getActivityIconWithCache(ComponentName componentName) {
        String flattenToShortString = componentName.flattenToShortString();
        Drawable.ConstantState constantState = null;
        if (this.mOutsideDrawablesCache.containsKey(flattenToShortString)) {
            Drawable.ConstantState constantState2 = this.mOutsideDrawablesCache.get(flattenToShortString);
            if (constantState2 == null) {
                return null;
            }
            return constantState2.newDrawable(this.mProviderContext.getResources());
        }
        Drawable activityIcon = getActivityIcon(componentName);
        if (activityIcon != null) {
            constantState = activityIcon.getConstantState();
        }
        this.mOutsideDrawablesCache.put(flattenToShortString, constantState);
        return activityIcon;
    }

    public static String getColumnString(Cursor cursor, String str) {
        return getStringOrNull(cursor, cursor.getColumnIndex(str));
    }

    private Drawable getDefaultIcon1() {
        Drawable activityIconWithCache = getActivityIconWithCache(this.mSearchable.getSearchActivity());
        if (activityIconWithCache != null) {
            return activityIconWithCache;
        }
        return this.mProviderContext.getPackageManager().getDefaultActivityIcon();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:8|9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002d, code lost:
        throw new java.io.FileNotFoundException("Resource does not exist: " + r8);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.drawable.Drawable getDrawable(android.net.Uri r8) {
        /*
            r7 = this;
            java.lang.String r0 = "SuggestionsAdapter"
            java.lang.String r1 = "Error closing icon stream for "
            java.lang.String r2 = "Failed to open "
            java.lang.String r3 = "Resource does not exist: "
            r4 = 0
            java.lang.String r5 = r8.getScheme()     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.String r6 = "android.resource"
            boolean r5 = r6.equals(r5)     // Catch:{ FileNotFoundException -> 0x001a }
            if (r5 == 0) goto L_0x002e
            android.graphics.drawable.Drawable r7 = r7.getDrawableFromResourceUri(r8)     // Catch:{ NotFoundException -> 0x001c }
            return r7
        L_0x001a:
            r7 = move-exception
            goto L_0x007b
        L_0x001c:
            java.io.FileNotFoundException r7 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x001a }
            r1.<init>(r3)     // Catch:{ FileNotFoundException -> 0x001a }
            r1.append(r8)     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.String r1 = r1.toString()     // Catch:{ FileNotFoundException -> 0x001a }
            r7.<init>(r1)     // Catch:{ FileNotFoundException -> 0x001a }
            throw r7     // Catch:{ FileNotFoundException -> 0x001a }
        L_0x002e:
            android.content.Context r7 = r7.mProviderContext     // Catch:{ FileNotFoundException -> 0x001a }
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ FileNotFoundException -> 0x001a }
            java.io.InputStream r7 = r7.openInputStream(r8)     // Catch:{ FileNotFoundException -> 0x001a }
            if (r7 == 0) goto L_0x0069
            android.graphics.drawable.Drawable r2 = android.graphics.drawable.Drawable.createFromStream(r7, r4)     // Catch:{ all -> 0x0053 }
            r7.close()     // Catch:{ IOException -> 0x0042 }
            return r2
        L_0x0042:
            r7 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x001a }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x001a }
            r3.append(r8)     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.String r1 = r3.toString()     // Catch:{ FileNotFoundException -> 0x001a }
            android.util.Log.e(r0, r1, r7)     // Catch:{ FileNotFoundException -> 0x001a }
            return r2
        L_0x0053:
            r2 = move-exception
            r7.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x0068
        L_0x0058:
            r7 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x001a }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x001a }
            r3.append(r8)     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.String r1 = r3.toString()     // Catch:{ FileNotFoundException -> 0x001a }
            android.util.Log.e(r0, r1, r7)     // Catch:{ FileNotFoundException -> 0x001a }
        L_0x0068:
            throw r2     // Catch:{ FileNotFoundException -> 0x001a }
        L_0x0069:
            java.io.FileNotFoundException r7 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x001a }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x001a }
            r1.append(r8)     // Catch:{ FileNotFoundException -> 0x001a }
            java.lang.String r1 = r1.toString()     // Catch:{ FileNotFoundException -> 0x001a }
            r7.<init>(r1)     // Catch:{ FileNotFoundException -> 0x001a }
            throw r7     // Catch:{ FileNotFoundException -> 0x001a }
        L_0x007b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Icon not found: "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = ", "
            r1.append(r8)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.util.Log.w(r0, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SuggestionsAdapter.getDrawable(android.net.Uri):android.graphics.drawable.Drawable");
    }

    private Drawable getDrawableFromResourceValue(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.mProviderContext.getPackageName() + "/" + parseInt;
            Drawable checkIconCache = checkIconCache(str2);
            if (checkIconCache != null) {
                return checkIconCache;
            }
            Drawable drawable = ContextCompat.getDrawable(this.mProviderContext, parseInt);
            storeInIconCache(str2, drawable);
            return drawable;
        } catch (NumberFormatException unused) {
            Drawable checkIconCache2 = checkIconCache(str);
            if (checkIconCache2 != null) {
                return checkIconCache2;
            }
            Drawable drawable2 = getDrawable(Uri.parse(str));
            storeInIconCache(str, drawable2);
            return drawable2;
        } catch (Resources.NotFoundException unused2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: ".concat(str));
            return null;
        }
    }

    private Drawable getIcon1(Cursor cursor) {
        int i2 = this.mIconName1Col;
        if (i2 == -1) {
            return null;
        }
        Drawable drawableFromResourceValue = getDrawableFromResourceValue(cursor.getString(i2));
        if (drawableFromResourceValue != null) {
            return drawableFromResourceValue;
        }
        return getDefaultIcon1();
    }

    private Drawable getIcon2(Cursor cursor) {
        int i2 = this.mIconName2Col;
        if (i2 == -1) {
            return null;
        }
        return getDrawableFromResourceValue(cursor.getString(i2));
    }

    private static String getStringOrNull(Cursor cursor, int i2) {
        if (i2 == -1) {
            return null;
        }
        try {
            return cursor.getString(i2);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    private void setViewDrawable(ImageView imageView, Drawable drawable, int i2) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i2);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void setViewText(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void storeInIconCache(String str, Drawable drawable) {
        if (drawable != null) {
            this.mOutsideDrawablesCache.put(str, drawable.getConstantState());
        }
    }

    private void updateSpinnerState(Cursor cursor) {
        Bundle bundle;
        if (cursor != null) {
            bundle = cursor.getExtras();
        } else {
            bundle = null;
        }
        if (bundle != null) {
            bundle.getBoolean("in_progress");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: android.text.SpannableStringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bindView(android.view.View r18, android.content.Context r19, android.database.Cursor r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r20
            java.lang.Object r2 = r18.getTag()
            androidx.appcompat.widget.SuggestionsAdapter$ChildViewCache r2 = (androidx.appcompat.widget.SuggestionsAdapter.ChildViewCache) r2
            androidx.appcompat.widget.SearchView r3 = r0.mSearchView
            java.lang.CharSequence r3 = r3.getQuery()
            java.lang.String r3 = r3.toString()
            int r4 = r3.length()
            int r5 = r0.mFlagsCol
            r6 = 0
            r7 = -1
            if (r5 == r7) goto L_0x0023
            int r5 = r1.getInt(r5)
            goto L_0x0024
        L_0x0023:
            r5 = r6
        L_0x0024:
            android.widget.TextView r8 = r2.mText1
            r9 = 33
            r10 = 0
            if (r8 == 0) goto L_0x00ba
            int r8 = r0.mText1Col
            java.lang.String r8 = getStringOrNull(r1, r8)
            if (r8 == 0) goto L_0x00b5
            if (r4 != 0) goto L_0x0037
            r11 = r7
            goto L_0x0069
        L_0x0037:
            android.widget.TextView r11 = r2.mText1
            android.text.TextPaint r11 = r11.getPaint()
            char[] r12 = r3.toCharArray()
            char[] r11 = androidx.reflect.text.SeslTextUtilsReflector.semGetPrefixCharForSpan(r11, r8, r12)
            if (r11 == 0) goto L_0x005d
            java.lang.String r4 = new java.lang.String
            r4.<init>(r11)
            java.lang.String r11 = r8.toLowerCase()
            java.lang.String r12 = r4.toLowerCase()
            int r11 = r11.indexOf(r12)
            int r4 = r4.length()
            goto L_0x0069
        L_0x005d:
            java.lang.String r11 = r8.toLowerCase()
            java.lang.String r12 = r3.toLowerCase()
            int r11 = r11.indexOf(r12)
        L_0x0069:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = " queryIndex = "
            r12.<init>(r13)
            r12.append(r11)
            java.lang.String r13 = "\nqueryLength = "
            r12.append(r13)
            r12.append(r4)
            java.lang.String r12 = r12.toString()
            java.lang.String r13 = "SuggestionsAdapter"
            android.util.Log.d(r13, r12)
            if (r11 == r7) goto L_0x009a
            if (r4 <= 0) goto L_0x009a
            android.text.SpannableStringBuilder r12 = new android.text.SpannableStringBuilder
            r12.<init>(r8)
            android.text.style.ForegroundColorSpan r14 = new android.text.style.ForegroundColorSpan
            int r15 = r0.mSpannableTextColor
            r14.<init>(r15)
            int r15 = r11 + r4
            r12.setSpan(r14, r11, r15, r9)
            goto L_0x009b
        L_0x009a:
            r12 = r10
        L_0x009b:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r14 = " matchText1 = "
            r11.<init>(r14)
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r13, r11)
            android.widget.TextView r11 = r2.mText1
            if (r12 == 0) goto L_0x00b1
            r8 = r12
        L_0x00b1:
            r0.setViewText(r11, r8)
            goto L_0x00ba
        L_0x00b5:
            android.widget.TextView r11 = r2.mText1
            r0.setViewText(r11, r8)
        L_0x00ba:
            android.widget.TextView r8 = r2.mText2
            r11 = 2
            r12 = 8
            r13 = 1
            if (r8 == 0) goto L_0x016f
            int r8 = r0.mText2UrlCol
            java.lang.String r8 = getStringOrNull(r1, r8)
            if (r8 == 0) goto L_0x00d0
            java.lang.CharSequence r8 = r0.formatUrl(r8)
            r14 = r13
            goto L_0x00d7
        L_0x00d0:
            int r8 = r0.mText2Col
            java.lang.String r8 = getStringOrNull(r1, r8)
            r14 = r6
        L_0x00d7:
            boolean r15 = android.text.TextUtils.isEmpty(r8)
            if (r15 == 0) goto L_0x00ea
            android.widget.TextView r15 = r2.mText1
            if (r15 == 0) goto L_0x00f6
            r15.setSingleLine(r6)
            android.widget.TextView r15 = r2.mText1
            r15.setMaxLines(r11)
            goto L_0x00f6
        L_0x00ea:
            android.widget.TextView r15 = r2.mText1
            if (r15 == 0) goto L_0x00f6
            r15.setSingleLine(r13)
            android.widget.TextView r15 = r2.mText1
            r15.setMaxLines(r13)
        L_0x00f6:
            if (r14 != 0) goto L_0x016a
            int r8 = r0.mText2Col
            java.lang.String r8 = getStringOrNull(r1, r8)
            if (r4 != 0) goto L_0x0103
        L_0x0100:
            r3 = r4
            r4 = r7
            goto L_0x013e
        L_0x0103:
            android.widget.TextView r14 = r2.mText1
            if (r14 == 0) goto L_0x0100
            if (r8 == 0) goto L_0x0100
            android.text.TextPaint r14 = r14.getPaint()
            char[] r15 = r3.toCharArray()
            char[] r14 = androidx.reflect.text.SeslTextUtilsReflector.semGetPrefixCharForSpan(r14, r8, r15)
            if (r14 == 0) goto L_0x012d
            java.lang.String r3 = new java.lang.String
            r3.<init>(r14)
            java.lang.String r4 = r8.toLowerCase()
            java.lang.String r14 = r3.toLowerCase()
            int r4 = r4.indexOf(r14)
            int r3 = r3.length()
            goto L_0x013e
        L_0x012d:
            java.lang.String r14 = r8.toLowerCase()
            java.lang.String r3 = r3.toLowerCase()
            int r3 = r14.indexOf(r3)
            r16 = r4
            r4 = r3
            r3 = r16
        L_0x013e:
            if (r4 == r7) goto L_0x0154
            if (r3 <= 0) goto L_0x0154
            if (r8 == 0) goto L_0x0154
            android.text.SpannableStringBuilder r10 = new android.text.SpannableStringBuilder
            r10.<init>(r8)
            android.text.style.ForegroundColorSpan r7 = new android.text.style.ForegroundColorSpan
            int r14 = r0.mSpannableTextColor
            r7.<init>(r14)
            int r3 = r3 + r4
            r10.setSpan(r7, r4, r3, r9)
        L_0x0154:
            if (r10 == 0) goto L_0x015c
            android.widget.TextView r3 = r2.mText2
            r0.setViewText(r3, r10)
            goto L_0x016f
        L_0x015c:
            if (r8 == 0) goto L_0x0164
            android.widget.TextView r3 = r2.mText2
            r0.setViewText(r3, r8)
            goto L_0x016f
        L_0x0164:
            android.widget.TextView r3 = r2.mText2
            r3.setVisibility(r12)
            goto L_0x016f
        L_0x016a:
            android.widget.TextView r3 = r2.mText2
            r0.setViewText(r3, r8)
        L_0x016f:
            android.widget.ImageView r3 = r2.mIcon1
            if (r3 == 0) goto L_0x017a
            android.graphics.drawable.Drawable r4 = r0.getIcon1(r1)
            r0.setViewDrawable(r3, r4, r12)
        L_0x017a:
            android.widget.ImageView r3 = r2.mIcon2
            if (r3 == 0) goto L_0x0185
            android.graphics.drawable.Drawable r1 = r0.getIcon2(r1)
            r0.setViewDrawable(r3, r1, r12)
        L_0x0185:
            int r1 = r0.mQueryRefinement
            if (r1 == r11) goto L_0x0196
            if (r1 != r13) goto L_0x0190
            r1 = r5 & 1
            if (r1 == 0) goto L_0x0190
            goto L_0x0196
        L_0x0190:
            android.widget.ImageView r0 = r2.mIconRefine
            r0.setVisibility(r12)
            return
        L_0x0196:
            android.widget.ImageView r1 = r2.mIconRefine
            r1.setVisibility(r6)
            android.widget.TextView r1 = r2.mText1
            if (r1 == 0) goto L_0x01a8
            android.widget.ImageView r3 = r2.mIconRefine
            java.lang.CharSequence r1 = r1.getText()
            r3.setTag(r1)
        L_0x01a8:
            android.widget.ImageView r1 = r2.mIconRefine
            r1.setOnClickListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SuggestionsAdapter.bindView(android.view.View, android.content.Context, android.database.Cursor):void");
    }

    public void changeCursor(Cursor cursor) {
        if (this.mClosed) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.changeCursor(cursor);
            if (cursor != null) {
                this.mText1Col = cursor.getColumnIndex("suggest_text_1");
                this.mText2Col = cursor.getColumnIndex("suggest_text_2");
                this.mText2UrlCol = cursor.getColumnIndex("suggest_text_2_url");
                this.mIconName1Col = cursor.getColumnIndex("suggest_icon_1");
                this.mIconName2Col = cursor.getColumnIndex("suggest_icon_2");
                this.mFlagsCol = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        String columnString;
        String columnString2;
        if (cursor == null) {
            return null;
        }
        String columnString3 = getColumnString(cursor, "suggest_intent_query");
        if (columnString3 != null) {
            return columnString3;
        }
        if (this.mSearchable.shouldRewriteQueryFromData() && (columnString2 = getColumnString(cursor, "suggest_intent_data")) != null) {
            return columnString2;
        }
        if (!this.mSearchable.shouldRewriteQueryFromText() || (columnString = getColumnString(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return columnString;
    }

    public Drawable getDrawableFromResourceUri(Uri uri) {
        int i2;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.mProviderContext.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            i2 = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        i2 = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (i2 != 0) {
                        return resourcesForApplication.getDrawable(i2);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        } else {
            throw new FileNotFoundException("No authority: " + uri);
        }
    }

    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i2, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View newDropDownView = newDropDownView(this.mProviderContext, getCursor(), viewGroup);
            if (newDropDownView != null) {
                ((ChildViewCache) newDropDownView.getTag()).mText1.setText(e.toString());
            }
            return newDropDownView;
        }
    }

    public Cursor getSearchManagerSuggestions(SearchableInfo searchableInfo, String str, int i2) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder fragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i2 > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i2));
        }
        return this.mProviderContext.getContentResolver().query(fragment.build(), (String[]) null, suggestSelection, strArr2, (String) null);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i2, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View newView = newView(this.mProviderContext, getCursor(), viewGroup);
            if (newView != null) {
                ((ChildViewCache) newView.getTag()).mText1.setText(e.toString());
            }
            return newView;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View newView = super.newView(context, cursor, viewGroup);
        newView.setTag(new ChildViewCache(newView));
        ((ImageView) newView.findViewById(R$id.edit_query)).setImageResource(this.mCommitIconResId);
        return newView;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateSpinnerState(getCursor());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        updateSpinnerState(getCursor());
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence) tag);
        }
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        String str;
        if (charSequence == null) {
            str = "";
        } else {
            str = charSequence.toString();
        }
        if (this.mSearchView.getVisibility() == 0 && this.mSearchView.getWindowVisibility() == 0) {
            try {
                Cursor searchManagerSuggestions = getSearchManagerSuggestions(this.mSearchable, str, 50);
                if (searchManagerSuggestions != null) {
                    searchManagerSuggestions.getCount();
                    return searchManagerSuggestions;
                }
            } catch (RuntimeException e) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
            }
        }
        return null;
    }

    public void setQueryRefinement(int i2) {
        this.mQueryRefinement = i2;
    }
}
