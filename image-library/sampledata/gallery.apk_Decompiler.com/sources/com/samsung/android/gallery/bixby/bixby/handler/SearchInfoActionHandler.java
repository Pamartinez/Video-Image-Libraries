package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import c4.C0438h;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.bixby.bixby.search.SearchInfo;
import com.samsung.android.gallery.bixby.bixby.util.ActionHandlerUtil;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.bixby.BixbyAppStateUtil;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.bixby2.action.ResponseCallback;
import java.util.Arrays;
import java.util.stream.Stream;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchInfoActionHandler extends GalleryActionHandler {
    static boolean SUPPORT_INTELLIGENT_SEARCH = Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH);
    private boolean running = false;

    private int getBixbySearchCount(Context context, ActionHandlerUtil actionHandlerUtil, SearchInfo searchInfo) {
        Cursor query;
        C0438h hVar;
        Stream stream;
        Cursor cursor;
        int i2 = 0;
        if (!Features.isEnabled(Features.SUPPORT_BIXBY_UTT_KEYWORD_SEARCH) || TextUtils.isEmpty(searchInfo.getUtterance())) {
            try {
                query = IntelligentSearch.getInstance().query(getBuilder(actionHandlerUtil, searchInfo).build(context));
                if (query != null) {
                    if (!query.isClosed()) {
                        i2 = query.getCount();
                    }
                }
                if (query != null) {
                    query.close();
                }
                return i2;
            } catch (Exception e) {
                String str = this.TAG;
                Log.bxe(str, "unable to get count, e=" + e.getMessage());
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            Object[] objArr = null;
            try {
                Cursor[] searchForTimeline = SearchEngineFactory.create(context).searchForTimeline(new SearchFilter.Builder(searchInfo.getUtterance()).build(context));
                if (searchForTimeline != null && searchForTimeline.length > 0 && (cursor = searchForTimeline[0]) != null && !cursor.isClosed()) {
                    i2 = searchForTimeline[0].getCount();
                }
                if (searchForTimeline != null && searchForTimeline.length > 0) {
                    stream = Arrays.stream(searchForTimeline);
                    hVar = new C0438h(17);
                    stream.forEach(hVar);
                }
            } catch (Exception e7) {
                String str2 = this.TAG;
                Log.bxe(str2, "unable to get count, e=" + e7.getMessage());
                if (objArr != null && objArr.length > 0) {
                    stream = Arrays.stream(objArr);
                    hVar = new C0438h(17);
                }
            } catch (Throwable th2) {
                if (objArr != null && objArr.length > 0) {
                    Arrays.stream(objArr).forEach(new C0438h(17));
                }
                throw th2;
            }
            return i2;
        }
        throw th;
    }

    private MediaItem getBixbySearchItem(Context context, ActionHandlerUtil actionHandlerUtil, SearchInfo searchInfo) {
        Cursor search;
        BaseSearchEngine create = SearchEngineFactory.create(context);
        create.clearCache();
        SearchFilter build = getBuilder(actionHandlerUtil, searchInfo).build(context);
        MediaItem mediaItem = null;
        try {
            search = create.search(build);
            if (search != null) {
                if (!search.isClosed() && search.getCount() > 0) {
                    mediaItem = MediaItemLoader.loadMediaItem(search, 0);
                }
            }
            if (search != null) {
                search.close();
            }
            return mediaItem;
        } catch (Exception e) {
            String str = this.TAG;
            Log.bxe(str, "unable to get item, e=" + e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private SearchFilter.Builder getBuilder(ActionHandlerUtil actionHandlerUtil, SearchInfo searchInfo) {
        return new SearchFilter.Builder(actionHandlerUtil.getKeywordString(searchInfo)).countryInfo(new String[]{searchInfo.getCountry(), searchInfo.getCountryCode()}).locationKey(searchInfo.getLocation()).order(searchInfo.getOrderType()).period(new long[]{searchInfo.getFrom(), searchInfo.getTo()}).setFilterEnable(false);
    }

    private JSONObject getInformation(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", i2 > 0 ? "success" : "fail");
            jSONObject.put("KEY_SEARCH_COUNT", i2);
            jSONObject.put("KEY_SUPPORT_INTELLIGENT_SEARCH", SUPPORT_INTELLIGENT_SEARCH);
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private SearchInfo getSearchInfoFromParam(Context context, ActionHandlerUtil actionHandlerUtil) {
        SearchInfo searchInfo = new SearchInfo();
        long j2 = UnsafeCast.toLong(getValue("KEY_TIME_FROM"), 0);
        long j3 = UnsafeCast.toLong(getValue("KEY_TIME_TO"), 0);
        long[] jArr = {j2, j3};
        if (!(j2 == 0 || j3 == 0)) {
            searchInfo.setTimeInfo(jArr);
        }
        String value = getValue("KEY_CONTENT_TYPE");
        if (value != null) {
            searchInfo.setContentType(actionHandlerUtil.getTranslatedName(context, value));
        }
        searchInfo.setCountry(getValue("KEY_COUNTRY"), getValue("KEY_COUNTRY_CODE"));
        searchInfo.setLocation(getValue("KEY_LOCATION"));
        searchInfo.setOrderType(getValue("KEY_ORDER_TYPE"));
        searchInfo.setPoi(getValue("KEY_POI"));
        searchInfo.setTag(getValue("KEY_TAG"));
        searchInfo.setTitle(getValue("KEY_TITLE"));
        searchInfo.setTime(getValue("KEY_TIME"));
        searchInfo.setUtterance(getValue("KEY_UTT"));
        return searchInfo;
    }

    public void executeAction(Context context, String str, Bundle bundle, ResponseCallback responseCallback) {
        this.running = false;
        super.executeAction(context, str, bundle, responseCallback);
    }

    public boolean isSupported() {
        return "SEARCH_BY_CATEGORY_INFO".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        MediaItem mediaItem;
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_SEARCH_BY_CATEGORY_INFO [" + blackboard.getName() + "]");
        if (this.running) {
            Log.bxe(this.TAG, "duplicate request skipped.");
            return;
        }
        this.running = true;
        ActionHandlerUtil actionHandlerUtil = new ActionHandlerUtil();
        int i2 = 0;
        boolean z = UnsafeCast.toBoolean(getValue("KEY_IS_SHARE"), false);
        boolean z3 = UnsafeCast.toBoolean(getValue("KEY_IS_DELETE"), false);
        if (z || z3) {
            if (SUPPORT_INTELLIGENT_SEARCH) {
                mediaItem = getBixbySearchItem(context, actionHandlerUtil, getSearchInfoFromParam(context, actionHandlerUtil));
            } else {
                mediaItem = null;
            }
            sendCallback(getResultString(getInformation(mediaItem)));
            return;
        }
        if (SUPPORT_INTELLIGENT_SEARCH) {
            i2 = getBixbySearchCount(context, actionHandlerUtil, getSearchInfoFromParam(context, actionHandlerUtil));
        }
        sendCallback(getResultString(getInformation(i2)));
    }

    private JSONObject getInformation(MediaItem mediaItem) {
        Integer num = null;
        Uri uri = mediaItem != null ? ContentUri.getUri(mediaItem) : null;
        Long valueOf = mediaItem != null ? Long.valueOf(mediaItem.getFileId()) : null;
        if (mediaItem != null) {
            num = Integer.valueOf(mediaItem.getMediaType().toInt());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            jSONObject.put("KEY_STATUS", uri != null ? "success" : "fail");
            jSONObject.put("KEY_URI", BixbyAppStateUtil.getNonNullValue(uri));
            jSONObject.put("KEY_CONTENT_ID", BixbyAppStateUtil.getNonNullValue(valueOf));
            jSONObject.put("KEY_MEDIA_TYPE", BixbyAppStateUtil.getNonNullValue(num));
            jSONObject.put("KEY_SUPPORT_INTELLIGENT_SEARCH", SUPPORT_INTELLIGENT_SEARCH);
            jSONObject.put("KEY_TRASH_ON", PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash));
            String str = this.TAG;
            Log.bx(str, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }
}
