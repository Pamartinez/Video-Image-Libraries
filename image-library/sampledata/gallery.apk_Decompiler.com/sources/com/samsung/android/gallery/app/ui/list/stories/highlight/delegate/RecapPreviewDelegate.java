package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A9.a;
import Fa.C0558l;
import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.airbnb.lottie.LottieAnimationView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.StoryData;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapTemplateBinder;
import com.samsung.android.gallery.module.lottie.recap.data.DummyCreator;
import com.samsung.android.gallery.module.lottie.service.RecapWorker;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import n0.C0235b;
import org.json.JSONException;
import org.json.JSONObject;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapPreviewDelegate extends Delegate {
    int counter = 0;
    int frame = 0;
    private String jsonFromClipboard;
    String[] log = new String[9];
    RecapTemplateBinder mBinder;
    SubscriberListener subscriberListener = new SubscriberListener() {
        public void onNotify(Object obj, Bundle bundle) {
            Float f = (Float) obj;
            float floatValue = f.floatValue();
            Toast toast = RecapPreviewDelegate.this.toast;
            if (toast != null) {
                toast.cancel();
            }
            RecapPreviewDelegate recapPreviewDelegate = RecapPreviewDelegate.this;
            recapPreviewDelegate.toast = Toast.makeText(recapPreviewDelegate.mView.getContext(), String.format("Processing... %.1f %%", new Object[]{f}), 0);
            RecapPreviewDelegate.this.toast.show();
            if (floatValue == 100.0f) {
                BlackboardUtils.publishBackKeyEvent(RecapPreviewDelegate.this.mView.getBlackboard());
            }
        }
    };
    Toast toast;
    private int userSelectedType;

    public RecapPreviewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public void initiateRecording(Context context) {
        new AlertDialog.Builder(context).setTitle((CharSequence) "Recording video?").setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new C0558l(10, this)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$1(View view, w wVar, String str) {
        String str2 = this.TAG;
        Log.i(str2, "Lottie created : " + wVar);
        if (wVar != null) {
            ThreadUtil.postOnUiThread(new C0235b(this, view, wVar, 6));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$2(String str, View view) {
        JSONObject jSONObject;
        this.mBinder = new RecapTemplateBinder();
        try {
            String loadFromClipboard = DummyCreator.loadFromClipboard();
            this.jsonFromClipboard = loadFromClipboard;
            if (loadFromClipboard != null) {
                jSONObject = new JSONObject(this.jsonFromClipboard);
                Log.i(this.TAG, "recap with soda clipboard");
            } else {
                this.jsonFromClipboard = DummyCreator.create(this.mView.getMediaData().getAllData());
                JSONObject jSONObject2 = new JSONObject(this.jsonFromClipboard);
                String str2 = this.TAG;
                Log.i(str2, "recap with user selected\n" + this.jsonFromClipboard);
                jSONObject = jSONObject2;
            }
            int i2 = this.userSelectedType;
            if (i2 != -1) {
                jSONObject.put("userSelectedType", i2);
                this.jsonFromClipboard = jSONObject.toString();
            }
            if (this.userSelectedType == 90001) {
                MediaItem mediaItem = (MediaItem) this.mView.getBlackboard().read(ArgumentsUtil.getArgValue(str, "media_item"));
                jSONObject.put("story_title", mediaItem.getTitle());
                jSONObject.put("story_duration", StoryData.of(mediaItem).duration);
                jSONObject.put("story_bgm", StoryData.of(mediaItem).bgmInfo);
                this.jsonFromClipboard = jSONObject.toString();
            }
            jSONObject.put("preview", true);
            if (!this.mBinder.setData(jSONObject)) {
                String str3 = this.TAG;
                Log.e(str3, "fail : " + this.jsonFromClipboard);
                Utils.showToast(view.getContext(), "Fail load recap");
                return;
            }
            this.mBinder.createLottie(new a(22, this, view), new AtomicBoolean(false));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initiateRecording$4(DialogInterface dialogInterface, int i2) {
        Utils.showToast(this.mView.getContext(), "start recording.");
        startRecordWithService();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreview$3(w wVar, LottieAnimationView lottieAnimationView) {
        String str = this.TAG;
        Log.i(str, "start preview : " + wVar);
        lottieAnimationView.c();
    }

    /* access modifiers changed from: private */
    /* renamed from: startPreview */
    public void lambda$initView$0(View view, w wVar) {
        if (!this.mView.isDestroyed()) {
            final LottieAnimationView lottieAnimationView = (LottieAnimationView) view.findViewById(R.id.recap_animation);
            lottieAnimationView.setVisibility(0);
            lottieAnimationView.setComposition(wVar.d);
            lottieAnimationView.setRepeatCount(0);
            lottieAnimationView.setOnTouchListener(new View.OnTouchListener() {
                boolean mPause;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!this.mPause) {
                        LottieAnimationView lottieAnimationView = lottieAnimationView;
                        lottieAnimationView.l = false;
                        lottieAnimationView.f1099h.j();
                        this.mPause = true;
                        RecapPreviewDelegate.this.frame = lottieAnimationView.getFrame();
                        RecapPreviewDelegate.this.initiateRecording(lottieAnimationView.getContext());
                    } else {
                        if (motionEvent.getY() < 1000.0f) {
                            RecapPreviewDelegate.this.frame--;
                        } else {
                            RecapPreviewDelegate.this.frame++;
                        }
                        lottieAnimationView.setMaxFrame(RecapPreviewDelegate.this.frame);
                        lottieAnimationView.setFrame(RecapPreviewDelegate.this.frame);
                        Utils.showToast(RecapPreviewDelegate.this.mView.getContext(), "recap preview frame : " + (RecapPreviewDelegate.this.frame + 1));
                    }
                    return false;
                }
            });
            this.mBinder.bindView(lottieAnimationView);
            lottieAnimationView.f1099h.e.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    RecapPreviewDelegate.this.initiateRecording(lottieAnimationView.getContext());
                }

                public void onAnimationStart(Animator animator) {
                    Log.i(RecapPreviewDelegate.this.TAG, "onAnimationStart");
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            });
            lottieAnimationView.post(new C0235b(this, wVar, lottieAnimationView, 7));
        }
    }

    private void startRecordWithService() {
        Log.d(this.TAG, "startRecordWithService");
        RecapWorker.init(this.mView.getContext(), this.jsonFromClipboard, (Bundle) null, true);
        Blackboard.getApplicationInstance().subscribeOnUi("recapProgress", this.subscriberListener);
    }

    public void initView(View view) {
        int i2;
        String locationKey = this.mView.getLocationKey();
        if (Build.VERSION.SDK_INT >= 34) {
            String argValue = ArgumentsUtil.getArgValue(locationKey, "type");
            if (!TextUtils.isEmpty(argValue)) {
                i2 = DummyCreator.TYPE_VALUES[Arrays.stream(DummyCreator.TYPE_NAMES).toList().indexOf(argValue)];
                this.userSelectedType = i2;
                ThreadUtil.postOnBgThread(new C0235b(this, locationKey, view, 5));
            }
        }
        i2 = -1;
        this.userSelectedType = i2;
        ThreadUtil.postOnBgThread(new C0235b(this, locationKey, view, 5));
    }

    public void onDestroyView() {
        Blackboard.getApplicationInstance().unsubscribe("recapProgress", this.subscriberListener);
    }
}
