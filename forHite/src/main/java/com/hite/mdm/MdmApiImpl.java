package com.hite.mdm;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.api.forelink.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MdmApiImpl extends Utils
        implements MdmApi {
    public MdmApiImpl(Context context) {
        super(context);
    }

    @Override
    public void setFirewallType(boolean state) {
        if (!state)
            disableFirewall();
        else
            enableIpWhitelist("");
    }

    @Override
    public void enableIpWhitelist(String ips) {
        Intent intent;
        if (null == ips) {
            intent = new Intent("disable.firewall");
        } else if ("".equals(ips)) {
            intent = new Intent("enable.firewall");
        } else {
            intent = new Intent("update.ip.whitelist");
            intent.putExtra("ip_list", ips);
        }

        intent.setPackage("com.android.inputdevices");
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void addNetworkAccessAppWhitelist(String packageName) {
        insertAppWhitelist(packageName);
    }

    @Override
    public void addNetworkAccessAppBlacklist(ArrayList<String> blackLists) {
        insertAppBlacklist(String.join(",", blackLists));
    }

    @Override
    public void mdmUsb(boolean state) {
        setUSBDataDisabled(!state);
        setUSBDebugDisabled(!state);
    }

    @Override
    public boolean setAppWhiteListWrite(List<String> packageNames) {
        try {
            if (packageNames == null) enableInstallWhitelist(null);
            else enableInstallWhitelist(String.join(",", packageNames));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setAppBlackListWrite(List<String> blackLists) {
        if (null == blackLists)
            setApplicationsDisabled(getDisabledApplications(), false);
        else
            setApplicationsDisabled(String.join(",", blackLists), true);
    }

    @Override
    public List<String> getAllappWhiteList() {
        return Arrays.asList(queryInstallWhitelist().split(","));
    }

    @Override
    public List<String> getAllappBlackList() {
        return Arrays.asList(getDisabledApplications().split(","));
    }

    @Override
    public void silentInstall(String path) {
        silenceInstall(path);
    }

    @Override
    public void silentUnInstall(String packageName) {
        silenceUninstall(packageName);
    }

    @Override
    public void mdmApplication(List<String> packageName, boolean state) {
        setApplicationsDisabled(String.join(",", packageName), !state);
    }

    @Override
    public void removeAllAppBlackList() {
        setApplicationsDisabled(getDisabledApplications(), false);
    }

    @Override
    public void mdmCamera(boolean state) {
        setCameraDisabled(!state);
    }

    @Override
    public boolean isCameraDisable() {
        return getCameraDisabled();
    }

    @Override
    public void mdmStatusbar(boolean state) {
        setStatusBarExpandDisabled(!state);
    }

    @Override
    public void mdmHomeKey(boolean state) {
        setHomeDisabled(!state);
    }

    @Override
    public void mdmBackKey(boolean state) {
        setBackDisabled(!state);
    }

    @Override
    public void mdmRecentKey(boolean state) {
        setRecentDisabled(!state);
    }

    @Override
    public void mdmTaskBar(boolean state) {
        setStatusBarDisabled(!state);
    }

    @Override
    public void mdmShortPressPower(boolean state) {
        setPowerKeyShortDisabled(!state);
    }

    @Override
    public void mdmVolumeKey(boolean state) {
        setVolumeKeyDisabled(!state);
    }

    @Override
    public void mdmBluetooth(boolean state) {
        setBluetoothDisabled(!state);
    }

    @Override
    public void modifySystemTime(long milliSecond) {
        setSystemTime(milliSecond);
    }


    @Override
    @Deprecated
    public boolean runADBComm(List<String> cmdList) {
        // TODO
        return false;
    }

    @Override
    public void mdmOTG(boolean state) {
        setOTGDisabled(!state);
    }

    @Override
    public void mdmSdCard(boolean state) {
        Intent intent = new Intent();
        if (!state) {
            intent.setAction("disable.storage");
        } else {
            intent.setAction("enable.storage");
        }

        intent.setPackage("com.android.inputdevices");
        this.mContext.sendBroadcast(intent);
    }

    @Override
    public void killApplication(String packageName) {
        forceStopApp(packageName);
    }

    @Override
    @Deprecated
    public void installWhiteList(List<String> whiteLists) {
        // TODO
    }

    @Override
    public void mdmExternalStorageDisabled(boolean state) {
        setSDCardDisabled(state);
    }

    @Override
    public void shutDown() {
        shutdownDevice();
    }

    @Override
    @Deprecated
    public boolean isRoot() {
        // TODO
        return false;
    }

    @Override
    public String getRomVersion() {
        return Settings.Global.getString(this.mContext.getContentResolver(), "device.rom.version");
    }

    @Override
    public void reboot() {
        rebootDevice();
    }

    @Override
    public void removeWifiConfiguration(String ssid) {
        forgetWIFI(ssid);
    }

//    @Override
//    public void setDefaultLauncher(String packageName, String className) {
//        super.setDefaultLauncher(packageName, className);
//    }

    @Override
    public void clearDefaultLauncher() {
//        setDefaultLauncher("com.hht.rc", "com.hht.guide.GuideActivity");
        setDefaultLauncher("com.android.launcher3", "com.android.launcher3.Launcher");
    }

    @Override
    public void removeAllNetBlackList() {
        removeWebBlacklist(queryWebBlacklist());
    }

    @Override
    public void removeAllNetWhiteList() {
        removeWebWhitelist(queryWebWhitelist());
    }

    @Override
    public void addNetworkAccessWhitelist(ArrayList<String> whiteLists) {
        insertWebWhitelist(String.join(",", whiteLists));
    }

    @Override
    public void addNetworkAccessBlacklist(ArrayList<String> blackLists) {
        insertWebBlacklist(String.join(",", blackLists));
    }

    @Override
    public boolean clearPackageCache(String packageName) {
        try {
            clearAppStorageCache(packageName, false, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Deprecated
    public void setSystemTrayEnable(boolean enable) {
        // TODO
    }

    @Override
    public void mdmNavigationBar(boolean enable) {
        setNavigationBarDisabled(!enable);
    }

    @Override
    public void setColorTemperatureAdjustmentEnabled(boolean enable, int level) {
        Intent intent = new Intent("set.color.temp.mode");
        intent.setPackage("com.android.inputdevices");
//        intent.putExtra("level", 65 - level);
        intent.putExtra("level", 65 - ((level * 64 / 191) + 1));
        intent.putExtra("activate", enable);
        mContext.sendBroadcast(intent);
    }

    @Override
    public void addBackgroundProtectionWhiteList(String packageName) {
        enableUnkillableWhitelist(packageName);
    }
}
