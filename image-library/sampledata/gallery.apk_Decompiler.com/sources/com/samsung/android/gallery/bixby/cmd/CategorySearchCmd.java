package com.samsung.android.gallery.bixby.cmd;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.samsung.android.gallery.bixby.bixby.search.SearchInfo;
import com.samsung.android.gallery.bixby.bixby.util.ActionHandlerUtil;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelper;
import com.samsung.android.gallery.bixby.cmd.abstraction.BaseCmd;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdResultBuilder;
import com.samsung.android.gallery.bixby.cmd.support.RequestResult$CmdState;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategorySearchCmd extends BaseCmd {
    private static final boolean UTT_SEARCH = Features.isEnabled(Features.SUPPORT_BIXBY_UTT_KEYWORD_SEARCH);

    private boolean isOrderTypeAvailable(String str) {
        if ("latest".equals(str)) {
            return true;
        }
        if (!"oldest".equals(str) || Features.isEnabled(Features.SUPPORT_BIXBY_UTT_KEYWORD_SEARCH)) {
            return false;
        }
        return true;
    }

    private void putBixbyActionToIntent(Intent intent, String str) {
        intent.putExtra("bixby_locationKey", str);
    }

    private void putSearchCountryToIntent(Intent intent, SearchInfo searchInfo) {
        String[] strArr = {searchInfo.getCountry(), searchInfo.getCountryCode()};
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("requested searchCountry [");
        sb2.append(Logger.getEncodedString(strArr[0] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + strArr[1]));
        sb2.append("]");
        Log.bx(str, sb2.toString());
        intent.putExtra("bixby_search_keyword_country", strArr);
        RequestResult$CmdResultBuilder requestResult$CmdResultBuilder = this.mBuilder;
        requestResult$CmdResultBuilder.setExtra("bixby_search_keyword_country", strArr[0] + strArr[1]);
    }

    private void putSearchKeywordToIntent(Intent intent, SearchInfo searchInfo) {
        putSearchKeywordToIntent(intent, new ActionHandlerUtil().getKeywordString(searchInfo));
    }

    private void putSearchOrderToIntent(Intent intent, SearchInfo searchInfo) {
        String orderType = searchInfo.getOrderType();
        String str = this.TAG;
        Log.bx(str, "requested searchOrder [" + orderType + "]");
        if (isOrderTypeAvailable(orderType)) {
            intent.putExtra("bixby_search_keyword_order", orderType);
            intent.putExtra("bixby_order_set", true);
            this.mBuilder.setExtra("bixby_search_keyword_order", orderType);
        }
    }

    private void putSearchPeriodToIntent(Intent intent, SearchInfo searchInfo) {
        boolean z;
        long j2;
        if (!UTT_SEARCH || TextUtils.isEmpty(searchInfo.getUtterance())) {
            z = false;
        } else {
            z = true;
        }
        long j3 = 0;
        if (z) {
            j2 = 0;
        } else {
            j2 = searchInfo.getFrom();
        }
        if (!z) {
            j3 = searchInfo.getTo();
        }
        long[] jArr = {j2, j3};
        Log.bx(this.TAG, "requested searchPeriod [" + jArr[0] + "][" + jArr[1] + "]");
        intent.putExtra("bixby_search_keyword_period", jArr);
        this.mBuilder.setExtra("bixby_search_keyword_period", String.valueOf(jArr[0] + jArr[1]));
    }

    public void execute(Context context) {
        Intent defaultIntent = getDefaultIntent();
        SearchInfo searchInfoFromUri = new ActionHelper().getSearchInfoFromUri(context, BaseCmd.mUri);
        if (searchInfoFromUri.isEmpty()) {
            Log.bx(this.TAG, "delivered parameters are empty, show search suggestion view");
            putBixbyActionToIntent(defaultIntent, "SHOW_SEARCH_SUGGESTION_VIEW");
            this.mBuilder.setState(RequestResult$CmdState.NO_PARAMETER);
        } else {
            String utterance = searchInfoFromUri.getUtterance();
            if (!UTT_SEARCH || TextUtils.isEmpty(utterance)) {
                putSearchKeywordToIntent(defaultIntent, searchInfoFromUri);
                putSearchPeriodToIntent(defaultIntent, searchInfoFromUri);
                putSearchCountryToIntent(defaultIntent, searchInfoFromUri);
            } else {
                putSearchKeywordToIntent(defaultIntent, utterance);
            }
            putSearchOrderToIntent(defaultIntent, searchInfoFromUri);
            putBixbyActionToIntent(defaultIntent, "SEARCH_BY_CATEGORY");
            this.mBuilder.setState(RequestResult$CmdState.EXECUTED);
        }
        startGalleryExternalActivity(context, defaultIntent, true);
    }

    public boolean support() {
        return "SEARCH_BY_CATEGORY".equals(BaseCmd.mAction);
    }

    private void putSearchKeywordToIntent(Intent intent, String str) {
        String str2 = this.TAG;
        Log.bx(str2, "requested searchKeyword [" + Logger.getEncodedString(str) + "]");
        intent.putExtra("bixby_search_keyword", str);
        this.mBuilder.setExtra("bixby_search_keyword", str);
    }
}
