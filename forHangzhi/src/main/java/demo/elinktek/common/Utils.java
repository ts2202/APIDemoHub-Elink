package demo.elinktek.common;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liaobz on 2017/11/24.
 */

public class Utils extends com.api.forelink.Utils {
//    private final String ipPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
//    private final String urlPattern = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";

    //    private final String TAG = "elinktek.demo";
//    private final Context mContext;
    private long mImageTime;
    private File mScreenshotDir;
    private String mImageFileName;
    private String mImageFilePath;

    private final String SCREENSHOTS_DIR_NAME = "Screenshots";
    private final String SCREENSHOT_FILE_NAME_TEMPLATE = "Screenshot_%s.png";
    private final Object mScreenshotLock = new Object();
    private final Handler mHandler = new Handler();
    private ServiceConnection mScreenshotConnection = null;
    private final Runnable mScreenshotTimeout = new Runnable() {
        @Override
        public void run() {
            synchronized (mScreenshotLock) {
                try {
                    if (mScreenshotConnection != null) {
                        mContext.unbindService(mScreenshotConnection);
                        mScreenshotConnection = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public Utils(Context context) {
        super(context);
    }

    public String takeScreenshot() {
        // Prepare all the output metadata
        mImageTime = System.currentTimeMillis();
        String imageDate = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date(mImageTime));
        mImageFileName = String.format(SCREENSHOT_FILE_NAME_TEMPLATE, imageDate);
        mScreenshotDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), SCREENSHOTS_DIR_NAME);
        mImageFilePath = new File(mScreenshotDir, mImageFileName).getAbsolutePath();
        takeScreenshot(mImageFilePath);
        return mImageFilePath;
    }

    public void takeScreenshot(final String path) {
        synchronized (mScreenshotLock) {
            if (mScreenshotConnection != null) {
                return;
            }
            ComponentName cn = new ComponentName("com.android.systemui",
                    "com.android.systemui.screenshot.TakeScreenshotService");
            Intent intent = new Intent();
            intent.setComponent(cn);
            ServiceConnection conn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.e(TAG, "onServiceConnected");
                    synchronized (mScreenshotLock) {
                        if (mScreenshotConnection != this) {
                            return;
                        }
                        Messenger messenger = new Messenger(service);
                        Message msg = Message.obtain(null, 1);
                        final ServiceConnection myConn = this;
                        Handler h = new Handler(mHandler.getLooper()) {
                            @Override
                            public void handleMessage(Message msg) {
                                synchronized (mScreenshotLock) {
                                    try {
                                        if (mScreenshotConnection == myConn) {
                                            mContext.unbindService(mScreenshotConnection);
                                            mScreenshotConnection = null;
                                            mHandler.removeCallbacks(mScreenshotTimeout);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        };
                        msg.replyTo = new Messenger(h);
                        msg.arg1 = msg.arg2 = 1;

                        Bundle data = new Bundle();
                        data.putString("img_path", path);
                        msg.setData(data);

                        try {
                            messenger.send(msg);
                        } catch (RemoteException e) {
                        }
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Log.e(TAG, "onServiceDisconnected");
                }
            };
            if (mContext.bindService(intent, conn, Context.BIND_AUTO_CREATE)) {
                mScreenshotConnection = conn;
                mHandler.postDelayed(mScreenshotTimeout, 1000);
            }
        }
    }

    /**
     * 护眼模式
     *
     * @param enable 护眼模式启用与否
     */
    public void setEyesCareEnable(boolean enable) {
        Intent intent = new Intent();
        if (enable) {
            intent.setAction("enable.blue.filter");
        } else {
            intent.setAction("disable.blue.filter");
        }
        Log.d(TAG, "enable:" + enable);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getEyesCareEnable() {
        return Settings.Secure.getInt(
                mContext.getContentResolver(), "night_display_activated", 0) == 1;
    }

    public void setClearAppEnable(boolean enable) {
        Intent intent = new Intent();
        if (enable) {
            intent.setAction("enable.clear.app");
        } else {
            intent.setAction("disable.clear.app");
        }
        Log.d(TAG, "enable:" + enable);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableVolumeKey() {
        Intent intent = new Intent("disable.volume.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableVolumeKey() {
        Intent intent = new Intent("enable.volume.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disablePowerKeyShort() {
        Intent intent = new Intent("disable.power.short.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enablePowerKeyShort() {
        Intent intent = new Intent("enable.power.short.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disablePowerKeyLong() {
        Intent intent = new Intent("disable.power.long.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enablePowerKeyLong() {
        Intent intent = new Intent("enable.power.long.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disablePowerKeyDblclk() {
        Intent intent = new Intent("disable.power.double.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enablePowerKeyDblclk() {
        Intent intent = new Intent("enable.power.double.press");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableCamera() {
        Intent intent = new Intent("disable.camera.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableCamera() {
        Intent intent = new Intent("enable.camera.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableFactoryReset() {
        Intent intent = new Intent("disable.factory.reset");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableFactoryReset() {
        Intent intent = new Intent("enable.factory.reset");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableDevelopmentOptions() {
        Intent intent = new Intent("disable.development.options");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableDevelopmentOptions() {
        Intent intent = new Intent("enable.development.options");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableGPS() {
        Intent intent = new Intent("disable.gps");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableGPS() {
        Intent intent = new Intent("enable.gps");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableUSBData() {
        Intent intent = new Intent("disable.usb.data.transfer");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableUSBData() {
        Intent intent = new Intent("enable.usb.data.transfer");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableSDCard() {
        Intent intent = new Intent("disable.tf.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableSDCard() {
        Intent intent = new Intent("enable.tf.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableUSBDebug() {
        Intent intent = new Intent("disable.usb.debug");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableUSBDebug() {
        Intent intent = new Intent("enable.usb.debug");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void disableBluetooth() {
        Intent intent = new Intent("disable.bluetooth.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void enableBluetooth() {
        Intent intent = new Intent("enable.bluetooth.module");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void factoryReset() {
        super.resetFactory();
    }

//    public void clearAppStorageCache(String pkgName, boolean clearStorage, boolean clearCache) {
//        Intent intent = new Intent("clear.storage.cache");
//        intent.putExtra("pkg_name", pkgName);
//        intent.putExtra("clear_storage", clearStorage);
//        intent.putExtra("clear_cache", clearCache);
//        intent.setPackage("com.android.inputdevices");
//        mContext.sendBroadcast(intent);
//    }

    /**
     * 启用应用
     *
     * @param packageName 需要启用应用的包名
     */
    public void enableApplication(String packageName) {
        super.setApplicationDisabled(packageName, false);
    }

    public void enableApplications(String packageNames) {
        super.setApplicationsDisabled(packageNames, false);
    }

    /**
     * 禁用应用
     *
     * @param packageName 需要禁用应用的包名
     */
    public void disableApplication(String packageName) {
        super.setApplicationDisabled(packageName, true);
    }

    public void disableApplications(String packageNames) {
        super.setApplicationsDisabled(packageNames, true);
    }

    public String getDisabledApplications() {
        String disabledApps = "";
        PackageManager packageManager = mContext.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (!info.applicationInfo.enabled) {
                if (disabledApps == "") disabledApps = info.packageName;
                else disabledApps += "," + info.packageName;
            }
        }
        return disabledApps;
    }

    public void grantAppAllPermission(String packageName) {
        super.grantAppAllPermission(packageName);
        setAllowAppAccessUsageDetails(packageName, true);
        setAllowAppModifySystemSettings(packageName, true);
        setAllowAppInstallUnknowSource(packageName, true);
        setAllowAppDisplayOverOtherApps(packageName, true);
    }

    public void showSettingsOptions() {
        Intent intent = new Intent("showSettings");
        intent.setComponent(new ComponentName(
                "com.android.settings",
                "com.android.settings.ShowSettingBroadcas"));
        mContext.sendBroadcast(intent);
    }

    public void hideSettingsOptions() {
        Intent intent = new Intent("hideSettings");
        intent.setComponent(new ComponentName(
                "com.android.settings",
                "com.android.settings.ShowSettingBroadcas"));
        mContext.sendBroadcast(intent);
    }

    public void setWifiBlacklist(String ssids) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(
                "com.android.settings",
                "com.android.settings.ShowSettingBroadcas"));
        intent.setAction("setwifiblacklist");
        intent.putExtra("wifiblacklist", ssids);
        mContext.sendBroadcast(intent);
    }

    public String getWifiBlacklist() {
        return Settings.System.getString(mContext.getContentResolver(), "wifiblacklist");
    }

    public void setDefaultLauncher(String componentName) {
        Intent intent = new Intent("action_set_default_home");
        intent.setComponent(new ComponentName(
                "com.android.settings",
                "com.android.settings.CustomHomeReceiver"));
        intent.putExtra("key", componentName);
        mContext.sendBroadcast(intent);
    }

    public void forgetWIFI(String ssid) {
        Intent intent = new Intent("wifi_forget");
        intent.setComponent(new ComponentName(
                "com.android.settings",
                "com.android.settings.wifi.WifiBroadcast"));
        intent.putExtra("forget_ssid", ssid);
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用禁杀白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    /*public void disableUnkillableWhitelist() {
        enableUnkillableWhitelist(null);
    }*/
    public void enableUnkillableWhitelist(String pkgs) {
        Intent intent = new Intent("update.unkillable.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("white_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUnkillableWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "unkillable.whitelist");
    }

    /*public void removeUnkillableWhitelist(String pkgs) {
        if (queryUnkillableWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryUnkillableWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (whitelist.contains(pkg))
                mWhiteList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mWhiteList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateUnkillableWhitelist(pkgs, false);
    }*/

    public void insertUnkillableWhitelist(String pkgs) {
        updateUnkillableWhitelist(pkgs, true);
    }

    private void updateUnkillableWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.unkillable.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }
}
