package com.api.forelink;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by liaobz on 2020-9-11.
 */

public class Utils {

    protected final String ipPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    protected final String ipBlockPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)";
    protected final String TAG = "elinktek.demo";

    protected Context mContext;

    public Utils(Context context) {
        mContext = context;
    }

    public String getString(int id) {
        return mContext.getResources().getString(id);
    }

    protected String deduplication(String srcStr) {
        if (srcStr == null || "".equals(srcStr))
            return "";
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(Arrays.asList(srcStr.split(",")));
        set.remove("null");
        for (String str : set) {
            tgtStr.append(str);
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        } else return "";
    }

    protected String deduplication(List<String> srcList) {
        if (srcList.size() < 1)
            return "";
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        for (String str : srcList) {
            set.add(str);
        }
        set.remove("null");
        for (String str : set) {
            tgtStr.append(str);
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        } else return "";
    }

    public String getWIFIMAC() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.wifi.mac");
    }

    public String getBTMAC() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.bt.mac");
    }

    public String getSN() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.sn");
    }

    public String getIMEI() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.imei");
    }


    public void setColorTempNormal() {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", 3);
        mContext.sendBroadcast(intent);
    }

    public void setColorTempEnhance() {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", 2);
        mContext.sendBroadcast(intent);
    }

    public void setColorTempIntelligentNature() {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", 0xFF000000);
        mContext.sendBroadcast(intent);
    }

    public void setColorTempIntelligentWarm() {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", 0xFF000001);
        mContext.sendBroadcast(intent);
    }

    public void setColorTempIntelligentCold() {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", 0xFF000002);
        mContext.sendBroadcast(intent);
    }

    public void takeScreenshot(String path, String name) {
        ComponentName cn = new ComponentName("com.android.shell",
                "com.android.shell.BugreportProgressService");
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        Intent intent = new Intent();
        intent.setComponent(cn);
        intent.putExtra("path", path.endsWith("/") ? path + name : path + "/" + name);
        mContext.startService(intent);
    }

    public Bitmap catchScreenBitmap(long time) {
        String path = "/storage/emulated/0/Android/0.png";
        ComponentName cn = new ComponentName("com.android.shell",
                "com.android.shell.BugreportProgressService");
        File folder = new File("/storage/emulated/0/Android/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        Intent intent = new Intent();
        intent.setComponent(cn);
        intent.putExtra("path", path);
        mContext.startService(intent);
        try {
            Thread.sleep(time);
            File file = new File(path);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                file.delete();
                return bitmap;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap catchScreenBitmap() {
        return catchScreenBitmap(1800);
    }

    public void forceStopApp(String pkgName) {
        Intent intent = new Intent("kill.app");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("pkg_name", pkgName);
        mContext.sendBroadcast(intent);
    }

    public void setScreenOn() {
        Intent intent = new Intent("screen.turn.on");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setScreenOff() {
        Intent intent = new Intent("screen.turn.off");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setUrlWhiteList(String cmds) {
        setUrlWhiteList(Arrays.asList(cmds.split(",")));
    }

    public void setUrlWhiteList(List<String> cmds) {
        ArrayList<String> cmdList = new ArrayList<>(cmds);
        Intent intent = new Intent();
        intent.setPackage("com.android.inputdevices");
        if (cmdList.size() > 0) {
            intent.setAction("exec.ipset.cmd");
            intent.putStringArrayListExtra("cmd_list", cmdList);
            mContext.sendBroadcast(intent);
        }
    }

    public void setSystemTime(long millisecond) {
        Intent intent = new Intent("modify.system.time");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("millisecond", millisecond);
        mContext.sendBroadcast(intent);
    }

    public void setDeviceName(String deviceName) {
        Intent intent = new Intent("set.device.name");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("device_name", deviceName);
        mContext.sendBroadcast(intent);
    }

    /**
     * 护眼模式
     *
     * @param enabled 护眼模式启用与否
     */
    public void setEyesCareEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.blue.filter");
        } else {
            intent.setAction("disable.blue.filter");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getEyesCareEnabled() {
        return Settings.Secure.getInt(
                mContext.getContentResolver(), "night_display_activated", 0) == 1;
    }

    public void setReadModeEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.read.mode");
        } else {
            intent.setAction("disable.read.mode");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getReadModeEnabled() {
        return Settings.Secure.getInt(
                mContext.getContentResolver(), "accessibility_display_daltonizer_enabled", 0) == 1;
    }

    public void setAdaptiveBrightnessEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.adaptive.brightness");
        } else {
            intent.setAction("disable.adaptive.brightness");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getAdaptiveBrightnessEnabled() {
        return Settings.System.getInt(
                mContext.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) ==
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
    }

    public void setClearAppEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.clear.app");
        } else {
            intent.setAction("disable.clear.app");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getClearAppEnabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.clear.app", 1) == 1;
    }

    public void setClearAppDataEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.clear.app.data");
        } else {
            intent.setAction("disable.clear.app.data");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getClearAppDataEnabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.clear.app.data", 1) == 1;
    }

    public void setClearAppCacheEnabled(boolean enabled) {
        Intent intent = new Intent();
        if (enabled) {
            intent.setAction("enable.clear.app.cache");
        } else {
            intent.setAction("disable.clear.app.cache");
        }
        Log.d(TAG, "enable:" + enabled);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getClearAppCacheEnabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.clear.app.cache", 1) == 1;
    }

    public void setVolumeKeyDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.volume.press");
        } else {
            intent.setAction("enable.volume.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getVolumeKeyDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.volume.key", 1) == 0;
    }

    public void setPowerKeyShortDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.power.short.press");
        } else {
            intent.setAction("enable.power.short.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getPowerKeyShortDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.power.key.short", 1) == 0;
    }

    public void setPowerKeyLongDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.power.long.press");
        } else {
            intent.setAction("enable.power.long.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getPowerKeyLongDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.power.key.long", 1) == 0;
    }

    public void setPowerKeyDblclkDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.power.double.press");
        } else {
            intent.setAction("enable.power.double.press");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getPowerKeyDblclkDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.power.key.double", 1) == 0;
    }

    /**
     * 禁用、启用Mic
     *
     * @param disabled true关闭,false开启
     */
    public void setMicDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled)
            intent.setAction("disable.audio.mic");
        else {
            intent.setAction("enable.audio.mic");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getMicDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "mic.module.enable", 1) == 0;
    }

    public void setMainCameraDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.camera.main");
        } else {
            intent.setAction("enable.camera.main");
        }
        intent.setPackage("com.android.inputdevices");

        mContext.sendBroadcast(intent);
    }

//    public boolean getMainCameraDisabled() {
//        return Settings.Global.getInt(
//                mContext.getContentResolver(), "camera.enabled", 1) == 0;
//    }

    public void setSubCameraDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.camera.sub");
        } else {
            intent.setAction("enable.camera.sub");
        }
        intent.setPackage("com.android.inputdevices");

        mContext.sendBroadcast(intent);
    }

//    public boolean getSubCameraDisabled() {
//        return Settings.Global.getInt(
//                mContext.getContentResolver(), "camera.enabled", 1) == 0;
//    }

    public void setCameraDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.camera.module");
        } else {
            intent.setAction("enable.camera.module");
        }
        intent.setPackage("com.android.inputdevices");

        mContext.sendBroadcast(intent);
    }

    public boolean getCameraDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "camera.enabled", 1) == 0;
    }

    public void setScreenshotDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.screenshot");
        } else {
            intent.setAction("enable.screenshot");
        }
        intent.setPackage("com.android.inputdevices");

        mContext.sendBroadcast(intent);
    }

    public boolean getScreenshotDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "screenshot.enabled", 1) == 0;
    }

    public void setFactoryResetDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.factory.reset");
        } else {
            intent.setAction("enable.factory.reset");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getFactoryResetDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "enable.factory.reset", 1) == 0;
    }

    public void resetFactory() {
        Intent intent = new Intent("reset.factory");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setDevelopmentOptionsDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.development.options");
        } else {
            intent.setAction("enable.development.options");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getDevelopmentOptionsDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 1) == 0;
    }

    public void setGPSDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.gps");
        } else {
            intent.setAction("enable.gps");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getGPSDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "gps.module.enable", 1) == 0;
    }

    public void setUSBDisabled(boolean disabled) {
        setUSBDataDisabled(disabled);
        setUSBDebugDisabled(disabled);
    }

//    public void getUSBDisabled(){
//        return;
//    }

    public void setUSBDataDisabled(boolean disabled) {
        setUSBDataMode(disabled ? "none" : "mtp");
        /*Intent intent = new Intent();
        if (enabled) {
            intent.setAction("disable.usb.data.transfer");
        } else {
            intent.setAction("enable.usb.data.transfer");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);*/
    }

    public boolean getUSBDataDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "usb.data.transfer.enable", 1) == 0;
    }

    public void setUSBDataMode(int mode) {
        Intent intent = new Intent("set.usb.data.mode");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("mode_value", mode);
        mContext.sendBroadcast(intent);
    }

    public void setUSBDataMode(String mode) {
        switch (mode) {
            case "mtp":
                setUSBDataMode(1);
                break;
            case "rndis":
                setUSBDataMode(2);
                break;
            case "midi":
                setUSBDataMode(3);
                break;
            case "ptp":
                setUSBDataMode(4);
                break;
            default:
                setUSBDataMode(0);
                break;
        }
    }

    public void setFontSize(int size) {
        Intent intent = new Intent("set.font.size");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("font_scale", size);
        mContext.sendBroadcast(intent);
    }

    public int getFontSize() {
        float size = Settings.System.getFloat(mContext.getContentResolver(),
                "font_scale", 1);
        if (size >= 1.225) {
            return 3;
        } else if (size >= 1.075) {
            return 2;
        } else if (size >= 0.925) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setOTGDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.otg");
        } else {
            intent.setAction("enable.otg");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getOTGDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "otg.enabled", 1) == 0;
    }

    public void setUSBDebugDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.usb.debug");
        } else {
            intent.setAction("enable.usb.debug");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getUSBDebugDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), Settings.Global.ADB_ENABLED, 1) == 0;
    }

    public void setSDCardDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.tf.module");
        } else {
            intent.setAction("enable.tf.module");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getSDCardDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "sd.tf.visible", 1) == 0;
    }

    public void setWifiDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.wifi.module");
        } else {
            intent.setAction("enable.wifi.module");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getWifiDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "wifi.module.enable", 1) == 0;
    }

    public void setWifiP2PDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.wifi.p2p");
        } else {
            intent.setAction("enable.wifi.p2p");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getWifiP2PDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "wifi.p2p.enable", 1) == 0;
    }

    public void setWifiTetherDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.wifi.tether");
        } else {
            intent.setAction("enable.wifi.tether");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getWifiTetherDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "wifi.tether.enable", 1) == 0;
    }

    public void setUsbTetherDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.usb.tether");
        } else {
            intent.setAction("enable.usb.tether");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getUsbTetherDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "usb.tether.enable", 1) == 0;
    }

    public void setBluetoothDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.bluetooth.module");
        } else {
            intent.setAction("enable.bluetooth.module");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getBluetoothDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "bluetooth.module.enable", 1) == 0;
    }

    public void setBluetoothTetherDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.bluetooth.tether");
        } else {
            intent.setAction("enable.bluetooth.tether");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getBluetoothTetherDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "bluetooth.tether.enable", 1) == 0;
    }

    public void setBluetoothFileShareDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.bluetooth.opp");
        } else {
            intent.setAction("enable.bluetooth.opp");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getBluetoothFileShareDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "bluetooth.opp.enable", 1) == 0;
    }

    public void customSystemSettings(boolean custom) {
        Intent intent = new Intent(custom ? "enable.custom.settings" : "disable.custom.settings");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setSettingMenu(String items) {
        Intent intent = new Intent("set.settings.menu");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("items", items);
        mContext.sendBroadcast(intent);
    }

    public String getSettingMenu() {
        return Settings.Global.getString(
                mContext.getContentResolver(), "custom.settings.items");
    }

    public void clearAppStorageCache(String pkgName, boolean clearStorage, boolean clearCache) {
        Intent intent = new Intent("clear.storage.cache");
        intent.putExtra("pkg_name", pkgName);
        intent.putExtra("clear_storage", clearStorage);
        intent.putExtra("clear_cache", clearCache);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void clearLockPassword() {
        Intent intent = new Intent("clear.lock.password");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void rebootDevice(String mode) {
        if (!mode.equals("bootloader") && !mode.equals("recovery"))
            return;
        Intent intent = new Intent("reboot.device");
        intent.setPackage("com.android.shell");
        intent.putExtra("reboot_mode", mode);
        mContext.sendBroadcast(intent);
    }

    public void rebootDevice() {
        Intent intent = new Intent("reboot.device");
        intent.setPackage("com.android.shell");
        mContext.sendBroadcast(intent);
    }

    public void shutdownDevice() {
        Intent intent = new Intent("reboot.device");
        intent.putExtra("reboot_mode", "-p");
        intent.setPackage("com.android.shell");
        mContext.sendBroadcast(intent);
    }

    public void setApplicationDisabled(String packageName, boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.application");
        } else {
            intent.setAction("enable.application");
        }
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getApplicationDisabled(String packageName) {
        return Arrays.asList(getDisabledApplications().split(",")).contains(packageName);
    }

    public void setApplicationsDisabled(String packageNames, boolean disabled) {
        for (String packageName : packageNames.split(",")) {
            setApplicationDisabled(packageName, disabled);
        }
    }

    public String getDisabledApplications() {
        String disabledApps = "";
        PackageManager packageManager = mContext.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (!info.applicationInfo.enabled) {
                if ("com.sprd.powersavemodelauncher".equals(info.packageName)
                        || "com.android.nfc".equals(info.packageName))
                    continue;
                if (disabledApps.equals("")) disabledApps = info.packageName;
                else disabledApps += "," + info.packageName;
            }
        }
        return disabledApps;
    }

    public void setSMSDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "disable.sms" : "enable.sms");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getSMSDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "sms.enabled", 1) != 1;
    }

    public void setOutgoingCallDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "disable.outcall" : "enable.outcall");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getOutgoingCallDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "outcall.enabled", 1) != 1;
    }

    public void setIncomingCallDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "disable.incall" : "enable.incall");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getIncomingCallDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "incall.enabled", 1) != 1;
    }

    public void setCellDataDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "disable.sim.card.data" : "enable.sim.card.data");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getCellDataDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "sim.data.enabled", 1) != 1;
    }

    public void setAlwaysOpenDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "hide.always.button" : "show.always.button");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getAlwaysOpenDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "always.button.enabled", 1) != 1;
    }

    public void setBackDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("back", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setHomeDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("home", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setRecentDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("recent", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setStatusBarExpandDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("expand", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setStatusBarNotifyDisabled(boolean disabled) {
        Intent intent = new Intent("update.statusbar");
        intent.setPackage("com.android.systemui");
        intent.putExtra("notify", disabled ? 1 : 0);
        mContext.sendBroadcast(intent);
    }

    public void setStatusBarDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "hide.statusbar" : "show.statusbar");
        intent.setPackage("com.android.systemui");
        mContext.sendBroadcast(intent);
    }

    public void setNavigationBarDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "hide.navigationbar" : "show.navigationbar");
        intent.setPackage("com.android.systemui");
        mContext.sendBroadcast(intent);
    }

    public void grantAppAllPermission(String packageName) {
        Intent intent = new Intent("grant.all.permission");
        intent.putExtra("pkg_name", packageName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            intent.setPackage("com.android.permissioncontroller");
        else
            intent.setPackage("com.android.packageinstaller");
        mContext.sendBroadcast(intent);
    }

    public void setAllowAppInstallUnknowSource(String pkg, boolean allow) {
        Intent intent = new Intent("allow.install.unknow.source");
        intent.putExtra("pkg_name", pkg);
        intent.putExtra("allow", allow);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setAllowAppModifySystemSettings(String pkg, boolean allow) {
        Intent intent = new Intent("allow.modify.system.setting");
        intent.putExtra("pkg_name", pkg);
        intent.putExtra("allow", allow);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setAllowAppDisplayOverOtherApps(String pkg, boolean allow) {
        Intent intent = new Intent("allow.draw.overlay");
        intent.putExtra("pkg_name", pkg);
        intent.putExtra("allow", allow);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setAllowAppAccessUsageDetails(String pkg, boolean allow) {
        Intent intent = new Intent("allow.access.usage");
        intent.putExtra("pkg_name", pkg);
        intent.putExtra("allow", allow);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void silenceInstall(String apkPath) {
        Intent intent = new Intent("install.application");
        intent.putExtra("path_name", apkPath);
        intent.setPackage("com.android.packageinstaller");
        mContext.sendBroadcast(intent);
    }

    public void silenceUninstall(String packageName) {
        Intent intent = new Intent("uninstall.application");
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setDefaultBrowser(String packageNames) {
        Intent intent = new Intent("set.default.browser");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("pkg_name", packageNames);
        mContext.sendBroadcast(intent);
    }

    public void setDefaultLauncher(String packageName, String className) {
        Intent intent = new Intent("set.default.home");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("component_name", packageName + "/" + className);
        mContext.sendBroadcast(intent);
    }

    public void setDefaultInputMethod(String value) {
        Intent intent = new Intent("set.default.inputmethod");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("component_name", value);
        mContext.sendBroadcast(intent);
    }

    public void forgetWIFI(String ssid) {
        Intent intent = new Intent("forget.wifi");
        intent.putExtra("ssid", ssid);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setWIFIAdvancedDisabled(boolean disabled) {
        Intent intent = new Intent(disabled ? "hide.wifi.advanced" : "show.wifi.advanced");
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getWIFIAdvancedDisabled() {
        return Settings.Global.getInt(mContext.getContentResolver(),
                "show.wifi.advanced", 1) != 1;
    }

    public void disableWIFIBlacklist() {
        enableWIFIBlacklist(null);
    }

    public void enableWIFIBlacklist(String ssids) {
        Intent intent = new Intent("update.wifi.blacklist");
        if (null == ssids /*|| "".equals(ssids)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("wifi_list", ssids);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryWIFIBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "wifi.blacklist");
    }

    public void removeWIFIBlacklist(String ssids) {
        List<String> blacklist = Arrays.asList(queryWIFIBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] pkgList = ssids.split(",");
        for (String pkg : pkgList) {
            if (blacklist.contains(pkg))
                mBlackList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mBlackList.get(i)).append(",");
        }
        ssids = pkgsBuilder.toString();

        if (ssids.endsWith(",")) {
            ssids = ssids.substring(0, ssids.length() - 1);
        }
        updateWIFIBlacklist(ssids, false);
    }

    public void insertWIFIBlacklist(String ssids) {
        updateWIFIBlacklist(ssids, true);
    }

    private void updateWIFIBlacklist(String ssids, boolean append) {
        Intent intent = new Intent("update.wifi.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("wifi_list", deduplication(ssids));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用安装白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableInstallWhitelist() {
        enableInstallWhitelist(null);
    }

    public void enableInstallWhitelist(String pkgs) {
        Intent intent = new Intent("update.app.install.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryInstallWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.install.whitelist");
    }

    public void removeInstallWhitelist(String pkgs) {
        List<String> whitelist = Arrays.asList(queryInstallWhitelist().split(","));
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
        updateInstallWhitelist(pkgs, false);
    }

    public void insertInstallWhitelist(String pkgs) {
        updateInstallWhitelist(pkgs, true);
    }

    private void updateInstallWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.install.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用卸载白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUninstallWhitelist() {
        enableUninstallWhitelist(null);
    }

    public void enableUninstallWhitelist(String pkgs) {
        Intent intent = new Intent("update.app.uninstall.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUninstallWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.uninstall.whitelist");
    }

    public void removeUninstallWhitelist(String pkgs) {
        List<String> whitelist = Arrays.asList(queryUninstallWhitelist().split(","));
        List<String> Whitelist = new ArrayList<>(whitelist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (whitelist.contains(pkg))
                Whitelist.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(Whitelist);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(Whitelist.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateUninstallWhitelist(pkgs, false);
    }

    public void insertUninstallWhitelist(String pkgs) {
        updateUninstallWhitelist(pkgs, true);
    }

    private void updateUninstallWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.uninstall.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用卸载黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUninstallBlacklist() {
        enableUninstallBlacklist(null);
    }

    public void enableUninstallBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.uninstall.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUninstallBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.uninstall.blacklist");
    }

    public void removeUninstallBlacklist(String pkgs) {
        List<String> blacklist = Arrays.asList(queryUninstallBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (blacklist.contains(pkg))
                mBlackList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mBlackList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateUninstallBlacklist(pkgs, false);
    }

    public void insertUninstallBlacklist(String pkgs) {
        updateUninstallBlacklist(pkgs, true);
    }

    private void updateUninstallBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.uninstall.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用禁杀白名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUnkillableWhitelist() {
        enableUnkillableWhitelist(null);
    }

    public void enableUnkillableWhitelist(String pkgs) {
        Intent intent = new Intent("update.app.unkillable.whitelist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUnkillableWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.unkillable.whitelist");
    }

    public void removeUnkillableWhitelist(String pkgs) {
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
    }

    public void insertUnkillableWhitelist(String pkgs) {
        updateUnkillableWhitelist(pkgs, true);
    }

    private void updateUnkillableWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.unkillable.whitelist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用运行黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableRunningBlacklist() {
        enableRunningBlacklist(null);
    }

    public void enableRunningBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.running.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryRunningBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.running.blacklist");
    }

    public void removeRunningBlacklist(String pkgs) {
        if (queryRunningBlacklist() == null) return;
        List<String> whitelist = Arrays.asList(queryRunningBlacklist().split(","));
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
        updateRunningBlacklist(pkgs, false);
    }

    public void insertRunningBlacklist(String pkgs) {
        updateRunningBlacklist(pkgs, true);
    }

    private void updateRunningBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.running.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }


    /**
     * 停用防火墙
     */
    public void disableFirewall() {
        enableIpWhitelist(null);
    }

    /**
     * 网络黑名单 自动匹配 启用/查询/删除/插入
     *
     * @param links 0. 各个参数之间逗号分隔
     *              1. 通过正则表达式过滤IP,格式包括255.255.255.255 和 255.255.255.255/32
     *              2. 其余则认为是域名模糊匹配,如www.google.com/ditu.google.cn可直接用google
     */

    public void enableWebBlacklist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            enableIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            enableUrlBlacklist(urls.toString());
        }

    }

    public String queryWebBlacklist() {
        return deduplication(queryIpBlacklist() + "," + queryUrlBlacklist());
    }

    public void removeWebBlacklist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            removeIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            removeUrlBlacklist(urls.toString());
        }
    }

    public void insertWebBlacklist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            insertIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            insertUrlBlacklist(urls.toString());
        }
    }

    public void disableWebBlacklist() {
        removeUrlBlacklist(queryUrlBlacklist());
        removeIpBlacklist(queryIpBlacklist());
    }

    /**
     * 网络域名黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableUrlBlacklist() {
        removeUrlBlacklist(queryUrlBlacklist());
    }

    public void enableUrlBlacklist(String urls) {
        Intent intent;
        if (null == urls /*|| "".equals(pkgs)*/) {
            intent = new Intent("update.url.blacklist");
            intent.putExtra("url_list", "");
        } else {
            intent = new Intent("update.url.blacklist");
            intent.putExtra("url_list", urls);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUrlBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "url.blacklist");
    }

    public void removeUrlBlacklist(String urls) {
        if (queryUrlBlacklist() == null) return;
        List<String> blacklist = Arrays.asList(queryUrlBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] urlList = urls.split(",");
        for (String url : urlList) {
            if (blacklist.contains(url))
                mBlackList.remove(url);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder urlsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            urlsBuilder.append(mBlackList.get(i)).append(",");
        }
        urls = urlsBuilder.toString();

        if (urls.endsWith(",")) {
            urls = urls.substring(0, urls.length() - 1);
        }
        updateUrlBlacklist(urls, false);
    }

    public void insertUrlBlacklist(String urls) {
        updateUrlBlacklist(urls, true);
    }

    private void updateUrlBlacklist(String urls, boolean append) {
        Intent intent = new Intent("update.url.blacklist");
        intent.putExtra("url_list", deduplication(urls));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 网络IP黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableIpBlacklist() {
        removeIpBlacklist(queryIpBlacklist());
    }

    public void enableIpBlacklist(String ips) {
        Intent intent;
        if (null == ips /*|| "".equals(pkgs)*/) {
            intent = new Intent("update.ip.blacklist");
            intent.putExtra("ip_list", "");
        } else {
            intent = new Intent("update.ip.blacklist");
            intent.putExtra("ip_list", ips);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryIpBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "ip.blacklist");
    }

    public void removeIpBlacklist(String ips) {
        if (queryIpBlacklist() == null) return;
        List<String> blacklist = Arrays.asList(queryIpBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] ipList = ips.split(",");
        for (String ip : ipList) {
            if (blacklist.contains(ip))
                mBlackList.remove(ip);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder ipsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            ipsBuilder.append(mBlackList.get(i)).append(",");
        }
        ips = ipsBuilder.toString();

        if (ips.endsWith(",")) {
            ips = ips.substring(0, ips.length() - 1);
        }
        updateIpBlacklist(ips, false);
    }

    public void insertIpBlacklist(String ips) {
        updateIpBlacklist(ips, true);
    }

    private void updateIpBlacklist(String ips, boolean append) {
        Intent intent = new Intent("update.ip.blacklist");
        intent.putExtra("ip_list", deduplication(ips));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用上网黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableAppBlacklist() {
        removeAppBlacklist(queryAppBlacklist());
    }

    public void enableAppBlacklist(String pkgs) {
        Intent intent;
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent = new Intent("update.app.blacklist");
            intent.putExtra("app_list", "");
        } else {
            intent = new Intent("update.app.blacklist");
            intent.putExtra("app_list", pkgs);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryAppBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.blacklist");
    }

    public void removeAppBlacklist(String pkgs) {
        if (queryAppBlacklist() == null) return;
        List<String> blacklist = Arrays.asList(queryAppBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        String[] pkgList = pkgs.split(",");
        for (String pkg : pkgList) {
            if (blacklist.contains(pkg))
                mBlackList.remove(pkg);
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mBlackList.get(i)).append(",");
        }
        pkgs = pkgsBuilder.toString();

        if (pkgs.endsWith(",")) {
            pkgs = pkgs.substring(0, pkgs.length() - 1);
        }
        updateAppBlacklist(pkgs, false);
    }

    public void insertAppBlacklist(String pkgs) {
        updateAppBlacklist(pkgs, true);
    }

    private void updateAppBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.blacklist");
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 应用上网白名单 禁用/启用/查询/删除/插入 更新(private)
     */

    public void disableAppWhitelist() {
        enableAppWhitelist(null);
    }

    public void enableAppWhitelist(String pkgs) {
        Intent intent;
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent = new Intent("disable.app.whitelist");
        } else {
            intent = new Intent("update.app.whitelist");
            intent.putExtra("app_list", pkgs);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);

    }

    public String queryAppWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.whitelist");
    }

    public void removeAppWhitelist(String pkgs) {
        if (queryAppWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryAppWhitelist().split(","));
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
        updateAppWhitelist(pkgs, false);
    }

    public void insertAppWhitelist(String pkgs) {
        updateAppWhitelist(pkgs, true);

    }

    public void updateAppWhitelist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.whitelist");
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }


    /**
     * 网络IP白名单 启用/查询/删除/插入 更新(private)
     *
     * @param ips ip列表,逗号(,)分隔
     */
    public void enableIpWhitelist(String ips) {
        Intent intent;
        if (null == ips /*|| "".equals(ips)*/) {
            intent = new Intent("disable.firewall");
        } else {
            intent = new Intent("update.ip.whitelist");
            intent.putExtra("ip_list", ips);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryIpWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "ip.whitelist");
    }

    public void removeIpWhitelist(String ips) {
        if (queryIpWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryIpWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] ipList = ips.split(",");
        for (String ip : ipList) {
            if (whitelist.contains(ip))
                mWhiteList.remove(ip);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder ipsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            ipsBuilder.append(mWhiteList.get(i)).append(",");
        }
        ips = ipsBuilder.toString();

        if (ips.endsWith(",")) {
            ips = ips.substring(0, ips.length() - 1);
        }
        updateIpWhitelist(ips, false);
    }

    public void insertIpWhitelist(String ips) {
        updateIpWhitelist(ips, true);
    }

    private void updateIpWhitelist(String ips, boolean append) {
        Intent intent = new Intent("update.ip.whitelist");
        intent.putExtra("ip_list", deduplication(ips));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 网络域名白名单 启用/查询/删除/插入 更新(private)
     *
     * @param urls 域名列表,逗号(,)分隔
     */
    public void enableUrlWhitelist(String urls) {
        Intent intent;
        if (null == urls /*|| "".equals(urls)*/) {
            intent = new Intent("disable.firewall");
        } else {
            intent = new Intent("update.url.whitelist");
            intent.putExtra("url_list", urls);
        }

        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryUrlWhitelist() {
        return Settings.Global.getString(mContext.getContentResolver(), "url.whitelist");
    }

    public void removeUrlWhitelist(String urls) {
        if (queryUrlWhitelist() == null) return;
        List<String> whitelist = Arrays.asList(queryUrlWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        String[] urlList = urls.split(",");
        for (String url : urlList) {
            if (whitelist.contains(url))
                mWhiteList.remove(url);
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder urlsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            urlsBuilder.append(mWhiteList.get(i)).append(",");
        }
        urls = urlsBuilder.toString();

        if (urls.endsWith(",")) {
            urls = urls.substring(0, urls.length() - 1);
        }
        updateUrlWhitelist(urls, false);
    }

    public void insertUrlWhitelist(String urls) {
        updateUrlWhitelist(urls, true);
    }

    private void updateUrlWhitelist(String urls, boolean append) {
        Intent intent = new Intent("update.url.whitelist");
        intent.putExtra("url_list", deduplication(urls));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    /**
     * 网络白名单 自动匹配 启用/查询/删除/插入
     *
     * @param links 0. 各个参数之间逗号分隔
     *              1. 通过正则表达式过滤IP,格式包括255.255.255.255 和 255.255.255.255/32
     *              2. 其余则认为是域名模糊匹配,如www.google.com/ditu.google.cn可直接用google
     */
    public void enableWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            enableIpWhitelist(ips.toString());
        } else {
            enableIpWhitelist("");
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            enableUrlWhitelist(urls.toString());
        } else {
            enableUrlWhitelist("");
        }

    }

    public String queryWebWhitelist() {
        return deduplication(queryIpWhitelist() + "," + queryUrlWhitelist());
    }

    public void removeWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            removeIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            removeUrlWhitelist(urls.toString());
        }
    }

    public void insertWebWhitelist(String links) {
        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches(ipPattern))
                ipList.add(link);
            else
                urlList.add(link);
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }

            if (ips.toString().endsWith(","))
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            insertIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set = new HashSet<>(urlList);
            for (int i = 0; i < set.size(); i++) {
                urls.append(urlList.get(i)).append(",");
            }

            if (urls.toString().endsWith(","))
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            insertUrlWhitelist(urls.toString());
        }
    }
}
