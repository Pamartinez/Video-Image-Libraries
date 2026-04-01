package com.samsung.android.gallery.app.service;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.samsung.android.gallery.module.abstraction.IntentExtra$InternalKey;
import com.samsung.android.gallery.module.mde.executor.ServiceExecutor;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeSharingService extends BaseService {
    /* access modifiers changed from: private */
    public Blackboard mBlackboard = null;
    /* access modifiers changed from: private */
    public boolean mIsServiceRunning = false;
    private MdeSharingHandlerThread mThreadHandler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MdeSharingHandlerThread extends HandlerThread {
        /* access modifiers changed from: private */
        public Handler mHandler;

        public MdeSharingHandlerThread() {
            super(MdeSharingService.this.TAG, 10);
        }

        /* access modifiers changed from: private */
        public void requestCreateFamilyGroup(String str, ArrayList<Uri> arrayList) {
            Blackboard.postEventGlobal(EventMessage.obtain(6013));
            Bundle bundle = new Bundle();
            bundle.putString("spaceTitle", str);
            bundle.putParcelableArrayList("contentsData", arrayList);
            Message message = new Message();
            message.what = 2;
            message.setData(bundle);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendMessage(message);
            }
        }

        /* access modifiers changed from: private */
        public void requestCreateGroup(String str, HashMap<Integer, Object> hashMap, ArrayList<Uri> arrayList) {
            Bundle bundle = new Bundle();
            bundle.putString("spaceTitle", str);
            bundle.putSerializable("invitationRequestData", hashMap);
            bundle.putParcelableArrayList("contentsData", arrayList);
            Message message = new Message();
            message.what = 0;
            message.setData(bundle);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendMessage(message);
            }
        }

        /* access modifiers changed from: private */
        public void requestCreateGroupForShare(String str, ArrayList<Uri> arrayList) {
            Bundle bundle = new Bundle();
            bundle.putString("spaceTitle", str);
            bundle.putParcelableArrayList("contentsData", arrayList);
            Message message = new Message();
            message.what = 1;
            message.setData(bundle);
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.sendMessage(message);
            }
        }

        public void onLooperPrepared() {
            super.onLooperPrepared();
            this.mHandler = new Handler(getLooper()) {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    MdeSharingHandlerThread.this.mHandler.removeMessages(6);
                    int i2 = message.what;
                    if (i2 == 6) {
                        MdeSharingService.this.stopSelf();
                        return;
                    }
                    try {
                        ServiceExecutor.create(i2, MdeSharingHandlerThread.this.mHandler, MdeSharingService.this.getApplicationContext(), MdeSharingService.this.mBlackboard).execute(message.getData());
                    } catch (IllegalStateException e) {
                        String access$200 = MdeSharingService.this.TAG;
                        Log.e(access$200, "failed to execute service -> " + e.getMessage());
                    }
                }
            };
        }
    }

    /* access modifiers changed from: private */
    public void createGroup(String str, boolean z, boolean z3, ArrayList<Uri> arrayList, HashMap<Integer, Object> hashMap) {
        if (z) {
            MdeSharingHandlerThread mdeSharingHandlerThread = this.mThreadHandler;
            if (!z3) {
                arrayList = null;
            }
            mdeSharingHandlerThread.requestCreateFamilyGroup(str, arrayList);
        } else if (z3) {
            this.mThreadHandler.requestCreateGroupForShare(str, arrayList);
        } else {
            this.mThreadHandler.requestCreateGroup(str, hashMap, arrayList);
        }
    }

    private void startService(boolean z, boolean z3, String str, HashMap<Integer, Object> hashMap, ArrayList<Uri> arrayList) {
        final boolean z7 = z;
        final boolean z9 = z3;
        final String str2 = str;
        final HashMap<Integer, Object> hashMap2 = hashMap;
        final ArrayList<Uri> arrayList2 = arrayList;
        AnonymousClass1 r0 = new MdeSharingHandlerThread() {
            public void onLooperPrepared() {
                super.onLooperPrepared();
                Log.d(MdeSharingService.this.TAG, "service is started");
                MdeSharingService.this.mIsServiceRunning = true;
                MdeSharingService.this.createGroup(str2, z9, z7, arrayList2, hashMap2);
            }
        };
        this.mThreadHandler = r0;
        r0.start();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mIsServiceRunning = false;
        MdeSharingHandlerThread mdeSharingHandlerThread = this.mThreadHandler;
        if (mdeSharingHandlerThread != null) {
            mdeSharingHandlerThread.quitSafely();
            this.mThreadHandler = null;
        }
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent == null) {
            return 2;
        }
        String action = intent.getAction();
        String str = this.TAG;
        Log.d(str, "startCommand [" + action + "]");
        Blackboard instance = Blackboard.getInstance(intent.getStringExtra("blackboard_name"));
        this.mBlackboard = instance;
        if (instance == null) {
            Log.e(this.TAG, "blackboard is null. prepare failed.");
        }
        if (!"com.samsung.android.gallery.app.service.MdeSharingService.REQUEST_CREATE_GROUP".equals(action)) {
            return 2;
        }
        String stringExtra = intent.getStringExtra("sharedAlbumTitle");
        boolean equals = Group.GROUP_TYPE_SA_FAMILY_GROUP.equals(intent.getStringExtra(IntentExtra$InternalKey.KEY_SHARED_GROUP_TYPE));
        boolean booleanExtra = intent.getBooleanExtra(IntentExtra$InternalKey.KEY_CREATE_GROUP_FOR_SHARE, false);
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        HashMap hashMap = (HashMap) intent.getSerializableExtra("invitationRequestData");
        if (this.mIsServiceRunning) {
            createGroup(stringExtra, equals, booleanExtra, parcelableArrayListExtra, hashMap);
            return 2;
        }
        boolean z = booleanExtra;
        String str2 = stringExtra;
        boolean z3 = z;
        HashMap hashMap2 = hashMap;
        ArrayList arrayList = parcelableArrayListExtra;
        startService(z3, equals, str2, hashMap2, arrayList);
        return 2;
    }
}
