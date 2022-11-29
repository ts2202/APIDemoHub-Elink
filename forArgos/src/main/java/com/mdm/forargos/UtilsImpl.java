package com.mdm.forargos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UtilsImpl extends com.api.forelink.Utils
        implements com.mdm.forargos.Utils {

    protected Context mContext;

    public UtilsImpl(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 禁用锁屏
     */
    @Override
    public boolean setScreenLockNone() {
        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.none");
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置锁屏为滑屏
     */
    @Override
    public boolean setScreenLockSwipe() {
        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.swipe");
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置锁屏图案
     *
     * @param password 密码长度需超过4位
     */
    @Override
    public boolean setScreenLockPattern(String password) {
        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.parrten");
            intent.putExtra("password", password);
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置锁屏为PIN码
     *
     * @param password 密码长度需超过4位
     */
    @Override
    public boolean setScreenLockPin(String password) {
        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.pin");
            intent.putExtra("password", password);
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 设置锁屏为密码
     *
     * @param password 密码长度需超过4位
     */
    @Override
    public boolean setScreenLockPassword(String password) {
        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.password");
            intent.putExtra("password", password);
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean startPasswordInterface() {
        try {
            Intent intent = new Intent();
            intent.setAction("start.screen.password.interface");
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean turnStolenMode(boolean onOff, String lockScreenPackage, String lockScreenActivity) {
        boolean isStolen = Settings.Global.getInt(mContext.getContentResolver(), "pad.stolen.status", 0) == 1;
        if (isStolen == onOff) {
            return false;
        }
        if (onOff) {
            if (TextUtils.isEmpty(lockScreenPackage) || TextUtils.isEmpty(lockScreenActivity)) {
                return false;
            }
        }
        try {
            Intent intent = new Intent();
            intent.setAction("pad.stolen.screen");
            intent.putExtra("stolen_state", onOff);
            intent.putExtra("pkg_name", lockScreenPackage);
            intent.putExtra("activity_name", lockScreenActivity);
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean factoryReset() {
        try {
            Intent intent = new Intent("reset.factory");
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getWIFIMAC() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.wifi.mac");
    }

    @Override
    public String getBTMAC() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.bt.mac");
    }

    @Override
    public String getSN() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.sn");
    }

    @Override
    public String getIMEI() {
        return Settings.Global.getString(mContext.getContentResolver(), "device.imei");
    }

    @Override
    public boolean isBluetoothLocked() {
        return getBluetoothDisabled();
    }

    @Override
    public boolean lockBluetooth(boolean lock) {
        try {
            setBluetoothDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isBluetoothTetheringLocked() {
        return getBluetoothTetherDisabled();
    }

    @Override
    public boolean lockBluetoothTethering(boolean lock) {
        try {
            setBluetoothTetherDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isWifiLocked() {
        return getWifiDisabled();
    }

    @Override
    public boolean lockWifi(boolean lock) {
        try {
            setWifiDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isWifiDirectLocked() {
        return getWifiP2PDisabled();
    }

    @Override
    public boolean lockWifiDirect(boolean lock) {
        try {
            setWifiP2PDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isWifiTetheringLocked() {
        return getWifiTetherDisabled();
    }

    @Override
    public boolean lockWifiTethering(boolean lock) {
        try {
            setWifiTetherDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isUSBLocked() {
        return getUSBDataDisabled() && getUSBDebugDisabled();
    }

    @Override
    public boolean lockUSB(boolean lock) {
        try {
            setUSBDataDisabled(lock);
            setUSBDebugDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isUSBTetheringLocked() {
        return getUsbTetherDisabled();
    }

    @Override
    public boolean lockUSBTethering(boolean lock) {
        try {
            setUsbTetherDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isGPSLocked() {
        return getGPSDisabled();
    }

    @Override
    public boolean lockGPS(boolean lock) {
        try {
            setGPSDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isMockLocationLocked() {
        return getMockLocationDisabled();
    }

    @Override
    public boolean lockMockLocation(boolean lock) {
        try {
            setMockLocationDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isMicLocked() {
        return getMicDisabled();
    }

    @Override
    public boolean lockMic(boolean lock) {
        try {
            setMicDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isCameraLocked() {
        return getCameraDisabled();
    }

    @Override
    public boolean lockCamera(boolean lock) {
        try {
            setCameraDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isSDCardLocked() {
        return getSDCardDisabled();
    }

    @Override
    public boolean lockSDCard(boolean lock) {
        try {
            setSDCardDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isScreenCaptureLocked() {
        return getScreenshotDisabled();
    }

    @Override
    public boolean lockScreenCapture(boolean lock) {
        try {
            setScreenshotDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplicationLocked(String pkgName) {
        return getApplicationDisabled(pkgName);
    }

    @Override
    public boolean lockApplication(String pkgName, boolean lock) {
        try {
            setApplicationDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplicationInstallLocked(String pkgName) {
        return getInstallDisabled(pkgName);
    }

    @Override
    public boolean lockApplicationInstall(String pkgName, boolean lock) {
        try {
            setInstallDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplicationUninstallLocked(String pkgName) {
        return getUninstallDisabled(pkgName);
    }

    @Override
    public boolean lockApplicationUninstall(String pkgName, boolean lock) {
        try {
            setUninstallDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void installApplication(String apkPath) {
        silenceInstall(apkPath);
    }

    @Override
    public void uninstallApplication(String pkgName) {
        silenceUninstall(pkgName);
    }

    @Override
    public boolean isApplicationForceStopLocked(String pkgName) {
        return getForceStopDisabled(pkgName);
    }

    @Override
    public boolean lockApplicationForceStop(String pkgName, boolean lock) {
        try {
            setForceStopDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplicationDataRemoveLocked(String pkgName) {
        return getClearAppDataDisabled(pkgName);
    }

    @Override
    public boolean lockApplicationDataRemove(String pkgName, boolean lock) {
        try {
            setClearAppDataDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isApplicationCacheRemoveLocked(String pkgName) {
        return getClearAppCacheDisabled(pkgName);
    }

    @Override
    public boolean lockApplicationCacheRemove(String pkgName, boolean lock) {
        try {
            setClearAppCacheDisabled(pkgName, lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isAdminLocked() {
        return getDeviceManagerDisabled();
    }

    @Override
    public boolean setAdminLocked(boolean lock) {
        try {
            setDeviceManagerDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isFactoryResetLocked() {
        return getFactoryResetDisabled();
    }

    @Override
    public boolean lockFactoryReset(boolean lock) {
        try {
            setFactoryResetDisabled(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isSWUpdateLocked() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "system.update.disabled", 0) == 1;
    }

    @Override
    public boolean lockSWUpdate(boolean lock) {
        try {
            Intent intent = new Intent();
            if (lock) {
                intent.setAction("disable.system.update");
            } else {
                intent.setAction("enable.system.update");
            }
            intent.setPackage("com.android.inputdevices");
            mContext.sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String deduplication(String srcStr) {
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

    private String deduplication(List<String> srcList) {
        if (srcList.size() < 1)
            return "";
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(srcList);
        set.remove("null");
        for (String str : set) {
            tgtStr.append(str);
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        } else return "";
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

    public void setUSBDataDisabled(boolean disabled) {
        setUSBDataMode(disabled ? "none" : "mtp");
//        Intent intent = new Intent();
//        if (disabled) {
//            intent.setAction("disable.usb.data.transfer");
//        } else {
//            intent.setAction("enable.usb.data.transfer");
//        }
//        intent.setPackage("com.android.inputdevices");
//        mContext.sendBroadcast(intent);
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

    public void setMockLocationDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.mock.location");
        } else {
            intent.setAction("enable.mock.location");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getMockLocationDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "mock.location.enabled", 1) == 0;
    }

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
        if (packageNames != null && !packageNames.equals(""))
            for (String packageName : packageNames.split(",")) {
                setApplicationDisabled(packageName, disabled);
            }
    }

    public String getDisabledApplications() {
        String disabledApps = "";
        PackageManager packageManager = mContext.getPackageManager();
        @SuppressLint("QueryPermissionsNeeded")
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            if (!info.applicationInfo.enabled) {
                if (disabledApps.equals("")) disabledApps = info.packageName;
                else disabledApps += "," + info.packageName;
            }
        }
        return disabledApps;
    }

    public void setInstallDisabled(String packageName, boolean disabled) {
        if (disabled)
            insertInstallBlacklist(packageName);
        else
            removeInstallBlacklist(packageName);
    }

    public boolean getInstallDisabled(String packageName) {
        if (queryInstallBlacklist() == null)
            return false;
        return Arrays.asList(queryInstallBlacklist().split(",")).contains(packageName);
    }

    public void setUninstallDisabled(String packageName, boolean disabled) {
        if (disabled)
            insertUninstallBlacklist(packageName);
        else
            removeUninstallBlacklist(packageName);
    }

    public boolean getUninstallDisabled(String packageName) {
        if (queryUninstallBlacklist() == null)
            return false;
        return Arrays.asList(queryUninstallBlacklist().split(",")).contains(packageName);
    }

    public void silenceInstall(String apkPath) {
        Intent intent = new Intent("install.application");
        intent.putExtra("path_name", apkPath);
        intent.setPackage("com.android.shell");
        mContext.sendBroadcast(intent);
    }

    public void silenceUninstall(String packageName) {
        Intent intent = new Intent("uninstall.application");
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public void setClearAppDataDisabled(String packageName, boolean disabled) {
        if (disabled)
            insertClearDataBlacklist(packageName);
        else
            removeClearDataBlacklist(packageName);
    }

    public boolean getClearAppDataDisabled(String packageName) {
        if (queryClearDataBlacklist() == null)
            return false;
        return Arrays.asList(queryClearDataBlacklist().split(",")).contains(packageName);
    }

    public void setClearAppCacheDisabled(String packageName, boolean disabled) {
        if (disabled)
            insertClearCacheBlacklist(packageName);
        else
            removeClearCacheBlacklist(packageName);
    }

    public boolean getClearAppCacheDisabled(String packageName) {
        if (queryClearCacheBlacklist() == null)
            return false;
        return Arrays.asList(queryClearCacheBlacklist().split(",")).contains(packageName);
    }

    public void setForceStopDisabled(String packageName, boolean disabled) {
        if (disabled)
            insertForceStopBlacklist(packageName);
        else
            removeForceStopBlacklist(packageName);
    }

    public boolean getForceStopDisabled(String packageName) {
        if (queryForceStopBlacklist() == null)
            return false;
        return Arrays.asList(queryForceStopBlacklist().split(",")).contains(packageName);
    }

    public void setDeviceManagerDisabled(boolean disabled) {
        Intent intent = new Intent();
        if (disabled) {
            intent.setAction("disable.device.manager");
        } else {
            intent.setAction("enable.device.manager");
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public boolean getDeviceManagerDisabled() {
        return Settings.Global.getInt(
                mContext.getContentResolver(), "device.manager.enabled", 1) == 0;
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
        if (queryUninstallBlacklist() == null)
            return;
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
     * 应用安装黑名单 禁用/启用/查询/删除/插入 更新(private)
     */
    public void disableInstallBlacklist() {
        enableInstallBlacklist(null);
    }

    public void enableInstallBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.install.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryInstallBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.install.blacklist");
    }

    public void removeInstallBlacklist(String pkgs) {
        if (queryInstallBlacklist() == null)
            return;
        List<String> blacklist = Arrays.asList(queryInstallBlacklist().split(","));
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
        updateInstallBlacklist(pkgs, false);
    }

    public void insertInstallBlacklist(String pkgs) {
        updateInstallBlacklist(pkgs, true);
    }

    private void updateInstallBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.install.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    ///////////////

    public void disableClearDataBlacklist() {
        enableClearDataBlacklist(null);
    }

    public void enableClearDataBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.cleardata.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryClearDataBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.cleardata.blacklist");
    }

    public void removeClearDataBlacklist(String pkgs) {
        if (queryClearDataBlacklist() == null)
            return;
        List<String> blacklist = Arrays.asList(queryClearDataBlacklist().split(","));
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
        updateClearDataBlacklist(pkgs, false);
    }

    public void insertClearDataBlacklist(String pkgs) {
        updateClearDataBlacklist(pkgs, true);
    }

    private void updateClearDataBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.cleardata.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }
    ///////////////////

    public void disableClearCacheBlacklist() {
        enableClearCacheBlacklist(null);
    }

    public void enableClearCacheBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.clearcache.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryClearCacheBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.clearcache.blacklist");
    }

    public void removeClearCacheBlacklist(String pkgs) {
        if (queryClearCacheBlacklist() == null)
            return;
        List<String> blacklist = Arrays.asList(queryClearCacheBlacklist().split(","));
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
        updateClearCacheBlacklist(pkgs, false);
    }

    public void insertClearCacheBlacklist(String pkgs) {
        updateClearCacheBlacklist(pkgs, true);
    }

    private void updateClearCacheBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.clearcache.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }
    ////////////////////////

    public void disableForceStopBlacklist() {
        enableForceStopBlacklist(null);
    }

    public void enableForceStopBlacklist(String pkgs) {
        Intent intent = new Intent("update.app.forcestop.blacklist");
        if (null == pkgs /*|| "".equals(pkgs)*/) {
            intent.putExtra("list_on", false);
        } else {
            intent.putExtra("list_on", true);
            intent.putExtra("app_list", pkgs);
        }
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }

    public String queryForceStopBlacklist() {
        return Settings.Global.getString(mContext.getContentResolver(), "app.forcestop.blacklist");
    }

    public void removeForceStopBlacklist(String pkgs) {
        if (queryForceStopBlacklist() == null)
            return;
        List<String> blacklist = Arrays.asList(queryForceStopBlacklist().split(","));
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
        updateForceStopBlacklist(pkgs, false);
    }

    public void insertForceStopBlacklist(String pkgs) {
        updateForceStopBlacklist(pkgs, true);
    }

    private void updateForceStopBlacklist(String pkgs, boolean append) {
        Intent intent = new Intent("update.app.forcestop.blacklist");
        intent.putExtra("list_on", true);
        intent.putExtra("app_list", deduplication(pkgs));
        intent.putExtra("append", append);
        intent.setPackage("com.android.inputdevices");
        mContext.sendBroadcast(intent);
    }
}
