package cl.entel.plugins;

import android.util.Log;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

public class BlanccoSDKPlugin extends ReflectiveCordovaPlugin {
    private static final String TAG = "BlanccoSDKPlugin";

    @Override
    protected void pluginInitialize() {
        Log.d(TAG, "Starting Blancco SDK plugin");
    }
}