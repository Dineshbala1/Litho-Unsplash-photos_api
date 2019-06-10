package com.example.litho.picasso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.facebook.soloader.SoLoader;

public class Application extends android.app.Application implements android.app.Application.ActivityLifecycleCallbacks {

    private static Context AppContext;

    public static Context getContext() {
        return AppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
        AppContext = this.getApplicationContext();
        this.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        GlideProvider.get().resumeRequests();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        GlideProvider.get().pauseAllRequests();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
