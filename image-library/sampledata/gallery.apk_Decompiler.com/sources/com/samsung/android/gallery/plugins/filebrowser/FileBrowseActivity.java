package com.samsung.android.gallery.plugins.filebrowser;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import c0.C0086a;
import com.samsung.android.gallery.plugins.R$bool;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;
import java.util.List;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FileBrowseActivity extends AppCompatActivity {
    private final Blackboard mBlackboard = Blackboard.getInstance(toString());
    OnBackPressCompat mOnBackPressCompat;

    public void onBackPressed() {
        Fragment fragment;
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments.isEmpty()) {
            fragment = null;
        } else {
            fragment = (Fragment) C0086a.f(1, fragments);
        }
        if (!(fragment instanceof FileBaseFragment) || !((FileBaseFragment) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.d("FileBrowseActivity", "onConfigurationChanged newConfig=" + Logger.toString(configuration));
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        String str;
        FileListFragment fileListFragment;
        super.onCreate(bundle);
        setContentView(R$layout.activity_main_layout);
        if (getResources().getBoolean(R$bool.enableOnBackInvokedCallback)) {
            OnBackPressCompat onBackPressHandler = new OnBackPressCompat(this).setOnBackPressHandler(new l(15, this));
            this.mOnBackPressCompat = onBackPressHandler;
            this.mBlackboard.publish("service://OnBackPressCompat", onBackPressHandler);
        }
        Intent intent = getIntent();
        Log.d("FileBrowseActivity", "onCreate", intent);
        if (intent != null) {
            str = intent.getStringExtra("zip-file");
        } else {
            str = null;
        }
        String str2 = StorageInfo.getDefault().getMediaPath("") + File.separator;
        StorageInfo.getDefault().getMediaPath("log");
        if (FileUtils.exists(str)) {
            fileListFragment = new FilePreviewFragment();
        } else {
            String str3 = str2;
            fileListFragment = new FileListFragment();
            str = str3;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(FileApiContract.Parameter.PATH, str);
        fileListFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().replace(R$id.fragment_container, fileListFragment, Logger.getSimpleName((Object) fileListFragment)).commit();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBlackboard.reset(toString());
        OnBackPressCompat onBackPressCompat = this.mOnBackPressCompat;
        if (onBackPressCompat != null) {
            onBackPressCompat.release();
            this.mOnBackPressCompat = null;
        }
        BitmapLoader.of((Context) this).release();
    }
}
