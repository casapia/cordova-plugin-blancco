package cl.entel.plugins;

import static by.chemerisuk.cordova.support.ExecutionThread.MAIN;
import static by.chemerisuk.cordova.support.ExecutionThread.WORKER;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import com.inhancetechnology.framework.hub.Hub;
import com.inhancetechnology.healthchecker.ui.plays.TestSequencePlay;
import com.inhancetechnology.healthchecker.ui.plays.enums.TestType;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

@SuppressLint("LogNotTimber")
public class BlanccoSDKPlugin extends ReflectiveCordovaPlugin {
    private static final String TAG = "BlanccoSDKPlugin";

    @Override
    protected void pluginInitialize() {
        Log.d(TAG,"Starting Blancco SDK plugin");
    }

    @CordovaMethod(MAIN)
    public void activate(final CordovaArgs args, final CallbackContext callbackContext) {
        Log.d(TAG, "Activating Blancco SDK");
        try {
            String key = args.getString(0).trim();
            if (key.isEmpty()) {
                throw new IllegalArgumentException("API key is required");
            }
            Hub.getInstance(cordova.getContext()).activateApiKey(key);
            callbackContext.success();
        } catch (Exception e) {
            Log.e(TAG, "Error activating Blancco SDK", e);
            callbackContext.error(e.getMessage());
        }
    }

    @CordovaMethod(WORKER)
    public void runSequence(final CordovaArgs args, final CallbackContext callbackContext) {
        Log.d(TAG,"Running Blancco SDK sequence");
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    JSONArray jsonArray = args.getJSONArray(0);
                    List<TestType> tests = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        tests.add(TestType.values()[jsonArray.getInt(i)]);
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        TestSequencePlay.runSequence(cordova.getContext(), tests);
                    }
                    callbackContext.success();
                } catch (Exception e) {
                    Log.e(TAG, "Error activating Blancco SDK", e);
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }
}