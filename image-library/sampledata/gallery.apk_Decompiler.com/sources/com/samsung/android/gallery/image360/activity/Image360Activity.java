package com.samsung.android.gallery.image360.activity;

import A.a;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.image360.R$animator;
import com.samsung.android.gallery.image360.R$id;
import com.samsung.android.gallery.image360.R$layout;
import com.samsung.android.gallery.image360.R$string;
import com.samsung.android.gallery.image360.activity.abstraction.IActivityView;
import com.samsung.android.gallery.image360.activity.options.PlayBackOptionsFragment;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Image360Activity extends AppCompatActivity implements IActivityView {
    private static final String[] COLOR_MODE = {"DEF", "WCG", "HDR", "HDR10", "A8"};
    private int mColorMode;
    private String mFilePath;
    private boolean mIsDirectionChanged;
    private ImageFileObserver mObserver = null;
    private boolean mWideColorGamut;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ImageFileObserver extends FileObserver {
        public /* synthetic */ ImageFileObserver(Image360Activity image360Activity, String str, int i2) {
            this(str);
        }

        public void onEvent(int i2, String str) {
            if ((i2 & 512) != 0 || (i2 & 1024) != 0) {
                Image360Activity.this.finish();
            }
        }

        private ImageFileObserver(String str) {
            super(str, 1536);
        }
    }

    private void commit360Fragment() {
        getSupportFragmentManager().beginTransaction().replace(R$id.main_layout, new Image360Fragment(), Image360Fragment.class.getName()).commit();
    }

    private void finishActivity(boolean z) {
        setResult(z);
        finish();
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_360_PHOTO_VIEWER_MAIN.toString();
    }

    private boolean initFilePath() {
        if (getIntent().getExtras() == null) {
            return false;
        }
        String string = getIntent().getExtras().getString("mediaItemPath");
        this.mFilePath = string;
        if (string != null) {
            return true;
        }
        return false;
    }

    private void initImageFileObserver() {
        ImageFileObserver imageFileObserver = new ImageFileObserver(this, this.mFilePath, 0);
        this.mObserver = imageFileObserver;
        imageFileObserver.startWatching();
    }

    private void setDisplayCutOutStateFlag() {
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            window.setAttributes(attributes);
        }
    }

    private void setResult(boolean z) {
        if (z) {
            Intent intent = new Intent();
            intent.putExtra("is_direction_changed", this.mIsDirectionChanged);
            setResult(-1, intent);
            return;
        }
        setResult(0);
    }

    public void commitPlayBackFragment(int i2) {
        PlayBackOptionsFragment playBackOptionsFragment = new PlayBackOptionsFragment();
        playBackOptionsFragment.setInitValues(this.mFilePath, i2);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R$animator.playback_fade_in, R$animator.playback_fade_out).replace(R$id.main_layout, playBackOptionsFragment).addToBackStack((String) null).commit();
    }

    public void finishWithToast(int i2) {
        Toast.makeText(this, i2, 1).show();
        finishActivity(false);
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finishActivity(true);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetColorSpace(configuration);
    }

    public void onCreate(Bundle bundle) {
        setColorSpace(getResources().getConfiguration());
        super.onCreate(bundle);
        getWindow().requestFeature(9);
        if (!initFilePath()) {
            finishWithToast(R$string.gallery360viewer_error_file_corrupt);
            return;
        }
        setContentView(R$layout.gallery360viewer_main);
        initImageFileObserver();
        SeApiCompat.disableViewRoundedCorner(getWindow().getDecorView());
        setDisplayCutOutStateFlag();
        if (getSupportFragmentManager().findFragmentByTag(Image360Fragment.class.getName()) == null) {
            commit360Fragment();
        }
    }

    public void onDestroy() {
        ImageFileObserver imageFileObserver = this.mObserver;
        if (imageFileObserver != null) {
            imageFileObserver.stopWatching();
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            AnalyticsLogger.getInstance().postLog(getScreenId(), AnalyticsEventId.EVENT_UP_KEY.toString());
            finishActivity(true);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void resetColorSpace(Configuration configuration) {
        try {
            boolean isScreenWideColorGamut = configuration.isScreenWideColorGamut();
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                this.mColorMode = isScreenWideColorGamut;
                getWindow().setColorMode(this.mColorMode);
                Log.d("Image360Activity", "resetColorSpace:ColorMode", COLOR_MODE[this.mColorMode], Boolean.valueOf(this.mWideColorGamut));
            } else if (this.mWideColorGamut || !isScreenWideColorGamut) {
                this.mColorMode = isScreenWideColorGamut ? 1 : 0;
                getWindow().setColorMode(this.mColorMode);
                Log.d("Image360Activity", "resetColorSpace:ColorMode", COLOR_MODE[this.mColorMode], Boolean.valueOf(this.mWideColorGamut));
            } else {
                Log.d("Image360Activity", "resetColorSpace:ColorSpace not supported > restart");
                Utils.finishAndStartGalleryActivity(this);
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("resetColorSpace failed e="), "Image360Activity");
        }
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [boolean, int] */
    public void setColorSpace(Configuration configuration) {
        try {
            ? isScreenWideColorGamut = configuration.isScreenWideColorGamut();
            this.mWideColorGamut = isScreenWideColorGamut;
            this.mColorMode = isScreenWideColorGamut;
            if (isScreenWideColorGamut > 0) {
                getWindow().setColorMode(this.mColorMode);
            }
            Log.d("Image360Activity", "setColorSpace:ColorMode", COLOR_MODE[this.mColorMode], Boolean.valueOf(this.mWideColorGamut));
        } catch (Exception e) {
            a.s(e, new StringBuilder("setColorSpace failed e="), "Image360Activity");
        }
    }

    public void setPlaybackDirection(int i2) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(Image360Fragment.class.getName());
        if (findFragmentByTag != null) {
            ((Image360Fragment) findFragmentByTag).updatePlaybackDirection(i2);
        }
        this.mIsDirectionChanged = true;
    }
}
