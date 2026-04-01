package com.samsung.android.gallery.app.controller.story;

import A.a;
import U3.b;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LaunchOnDemandStoryCmd extends BaseCommand {
    private void executeOnDemandFloatingView(String str, int i2) {
        if (getHandler().getActivity() != null && getHandler().getActivity().getWindow() != null) {
            View decorView = getHandler().getActivity().getWindow().getDecorView();
            Bitmap createBitmap = Bitmap.createBitmap(decorView.getWidth(), decorView.getHeight(), Bitmap.Config.RGB_565);
            Rect rect = new Rect();
            decorView.getGlobalVisibleRect(rect);
            try {
                PixelCopy.request(getActivity().getWindow(), rect, createBitmap, new b(this, createBitmap, str, i2), new Handler(Looper.getMainLooper()));
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail PixelCopy e="), this.TAG);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeOnDemandFloatingView$0(Bitmap bitmap, String str, int i2, int i7) {
        if (i7 == 0) {
            getBlackboard().publish("data:///CapturedBitmap", bitmap);
            getBlackboard().post("command://MoveURL", new UriBuilder("location://stories/onDemandFloating").appendArg("my_query", str).appendArg("type", i2).build());
            return;
        }
        Log.e(this.TAG, "PixelCopy failed.");
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        executeOnDemandFloatingView(objArr[0], objArr[1].intValue());
    }
}
