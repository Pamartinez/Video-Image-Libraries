package com.samsung.android.gallery.module.search.root;

import android.net.Uri;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchParser {
    private static final String[] SEARCH_KEYS = {ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE, "locationkey", "from_time", "to_time"};
    private static final HashMap<String, String> mHashMap = new HashMap<>();
    private boolean mIsAllTagNull = false;
    private boolean mIsSupportTagSearch = true;
    private String mKeyString;
    private int mLimit = -1;
    private String mSearchFilter;
    private final ArrayList<String> mUserDef = new ArrayList<>();

    public String getFromTime() {
        return mHashMap.getOrDefault("from_time", "0");
    }

    public String getKeyString() {
        return this.mKeyString;
    }

    public int getLimit() {
        return this.mLimit;
    }

    public String getLocationKey() {
        return mHashMap.get("locationkey");
    }

    public String getMediaType() {
        return mHashMap.get(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
    }

    public String getSearchFilter() {
        return this.mSearchFilter;
    }

    public String getToTime() {
        return mHashMap.getOrDefault("to_time", "0");
    }

    public boolean isEmptyKeyString() {
        if (this.mUserDef.isEmpty() || !this.mIsAllTagNull || getKeyString() != null) {
            return false;
        }
        return true;
    }

    public boolean isSupportTagSearch() {
        return this.mIsSupportTagSearch;
    }

    public SearchParser parse(Uri uri) {
        String[] regexParser = new QueryParser().regexParser(uri.getLastPathSegment());
        StringBuilder sb2 = new StringBuilder();
        if (regexParser != null && regexParser.length > 0) {
            for (int i2 = 0; i2 < regexParser.length; i2++) {
                if ((regexParser[i2].contains("AND") || regexParser[i2].equals("&")) && i2 % 2 != 0) {
                    sb2.append(" ");
                } else {
                    sb2.append(regexParser[i2]);
                }
            }
        }
        this.mKeyString = sb2.toString();
        String queryParameter = uri.getQueryParameter("limit");
        if (queryParameter != null) {
            this.mLimit = Integer.parseInt(queryParameter);
        }
        String queryParameter2 = uri.getQueryParameter("location");
        String queryParameter3 = uri.getQueryParameter("weather");
        String queryParameter4 = uri.getQueryParameter("people");
        this.mSearchFilter = uri.getQueryParameter("searchfilter");
        this.mUserDef.addAll(uri.getQueryParameters("userdef"));
        this.mIsSupportTagSearch = uri.getBooleanQueryParameter("tag_search", true);
        if (queryParameter3 == null && queryParameter4 == null && queryParameter2 == null && this.mUserDef.isEmpty()) {
            this.mIsAllTagNull = true;
        }
        return this;
    }

    public SearchParser parse(String str, String[] strArr) {
        String[] split = str.toLowerCase().replace("=", " ").replace("?", " ").split("and");
        if (split.length == strArr.length) {
            int i2 = 0;
            while (i2 < split.length) {
                String trim = split[i2].trim();
                if (Arrays.asList(SEARCH_KEYS).contains(trim)) {
                    mHashMap.put(trim, strArr[i2]);
                    i2++;
                } else {
                    throw new IllegalArgumentException("wrong value arguments");
                }
            }
            return this;
        }
        throw new IllegalArgumentException("wrong size arguments");
    }
}
