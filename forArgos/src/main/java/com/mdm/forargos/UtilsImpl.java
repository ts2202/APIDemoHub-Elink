package com.mdm.forargos;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class UtilsImpl {
    private static final String TAG = UtilsImpl.class.getSimpleName();

    protected final String ipBlockPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)";
    protected final String ipPattern = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    protected Context mContext;
    private IMDM mdmService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mdmService = IMDM.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mdmService = null;
        }
    };

    public UtilsImpl(Context context) {
        this.mContext = context;
        String pkg = "com.android.systemui";
        String classname = "com.android.systemui.SystemUIService";
        Intent srvIntent = new Intent();
        srvIntent.setClassName(pkg, classname);
        mContext.bindService(srvIntent, connection, Context.BIND_AUTO_CREATE);
    }

    public boolean setScreenLockNone() {
        Log.d(TAG, "Log.getMethod()");

        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.none");
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreenLockSwipe() {
        Log.d(TAG, "Log.getMethod()");

        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.swipe");
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreenLockPattern(String password) {
        Log.d(TAG, "Log.getMethod()" + " " + password);

        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.parrten");
            // intent.putExtra(HintConstants.AUTOFILL_HINT_PASSWORD, password);
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreenLockPin(String password) {
        Log.d(TAG, "Log.getMethod()" + " " + password);

        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.pin");
            // intent.putExtra(HintConstants.AUTOFILL_HINT_PASSWORD, password);
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreenLockPassword(String password) {
        Log.d(TAG, "Log.getMethod()" + " " + password);

        try {
            Intent intent = new Intent();
            intent.setAction("screen.password.password");
            // intent.putExtra(HintConstants.AUTOFILL_HINT_PASSWORD, password);
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean startPasswordInterface() {
        Log.d(TAG, "Log.getMethod()");

        try {
            Intent intent = new Intent();
            intent.setAction("start.screen.password.interface");
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean turnStolenMode(boolean onOff, String lockScreenPackage, String lockScreenActivity) {
        Log.d(TAG, "Log.getMethod()" + " " + onOff + " " + lockScreenPackage + " " + lockScreenActivity);

        if ((Settings.Global.getInt(this.mContext.getContentResolver(), "pad.stolen.status", 0) == 1) == onOff) {
            return false;
        }
        if (onOff && (TextUtils.isEmpty(lockScreenPackage) || TextUtils.isEmpty(lockScreenActivity))) {
            return false;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("pad.stolen.screen");
            intent.putExtra("stolen_state", onOff);
            intent.putExtra("pkg_name", lockScreenPackage);
            intent.putExtra("activity_name", lockScreenActivity);
            intent.setPackage("com.android.inputdevices");
            this.mContext.sendBroadcast(intent, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean factoryReset() {
        Log.d(TAG, "Log.getMethod()");
        if (mdmService == null) return false;

        try {
            return mdmService.factoryReset();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getWIFIMAC() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "device.wifi.mac");
    }

    public String getBTMAC() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "device.bt.mac");
    }

    public String getSN() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "device.sn");
    }

    public String getIMEI() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "device.imei");
    }

    public boolean isBluetoothLocked() {
        boolean isbluetoothLocked = getBluetoothDisabled();
        boolean isblueoothfileshareLocked = getBluetoothFileShareDisabled();

        Log.d(TAG, "Log.getMethod()" + " " + isbluetoothLocked + " " + isblueoothfileshareLocked);

        return isbluetoothLocked && isblueoothfileshareLocked;
    }

    public boolean lockBluetooth(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setBluetoothDisabled(lock);
            setBluetoothFileShareDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isBluetoothTetheringLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getBluetoothTetherDisabled();
    }

    public boolean lockBluetoothTethering(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setBluetoothTetherDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWifiLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getWifiDisabled();
    }

    public boolean lockWifi(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setWifiDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWifiDirectLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getWifiP2PDisabled();
    }

    public boolean lockWifiDirect(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setWifiP2PDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isWifiTetheringLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getWifiTetherDisabled();
    }

    public boolean lockWifiTethering(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setWifiTetherDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUSBLocked() {
        boolean isUsbDisabled = getUSBDataDisabled();
        boolean isUSBDebugDisabled = getUSBDebugDisabled();
        boolean isUSBOTGDisabled = getOTGDisabled();

        boolean ret = isUsbDisabled && isUSBDebugDisabled/* && isUSBOTGDisabled*/;

        Log.d(TAG, "Log.getMethod()" + " " + ret);

        return ret;
    }

    public boolean lockUSB(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            boolean isUsbDisabled = getUSBDataDisabled();
            boolean isUSBDebugDisabled = getUSBDebugDisabled();
            boolean isUSBOTGDisabled = getOTGDisabled();

            if (lock) {
                if (!isUsbDisabled) {
                    setUSBDataDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
                if (!isUSBDebugDisabled) {
                    setUSBDebugDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
                if (!isUSBOTGDisabled) {
                    setOTGDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
            } else {
                if (isUsbDisabled) {
                    setUSBDataDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
                if (isUSBDebugDisabled) {
                    setUSBDebugDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
                if (isUSBOTGDisabled) {
                    setOTGDisabled(lock);
                    // if (MdmManager.USE_THREAD_SLEEP_FOR_LOCK) {
                    //     try {
                    //         Thread.sleep(MdmManager.THREAD_SLEEP_TIME);
                    //     } catch (Exception e) {
                    //         Log.printStackTrace(e);
                    //     }
                    // }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUSBTetheringLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getUsbTetherDisabled();
    }

    public boolean lockUSBTethering(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setUsbTetherDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isGPSLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getGPSDisabled();
    }

    public boolean lockGPS(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setGPSDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMockLocationLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getMockLocationDisabled();
    }

    public boolean lockMockLocation(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setMockLocationDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isMicLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getMicDisabled();
    }

    public boolean lockMic(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setMicDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isCameraLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getCameraDisabled();
    }

    public boolean lockCamera(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setCameraDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSDCardLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getSDCardDisabled();
    }

    public boolean lockSDCard(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setSDCardDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isScreenCaptureLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getScreenshotDisabled();
    }

    public boolean lockScreenCapture(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setScreenshotDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isApplicationLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getApplicationDisabled(pkgName);
    }

    public boolean lockApplication(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setApplicationDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isApplicationInstallLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getInstallDisabled(pkgName);
    }

    public boolean lockApplicationInstall(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setInstallDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isApplicationUninstallLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getUninstallDisabled(pkgName);
    }

    public boolean lockApplicationUninstall(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setUninstallDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void installApplication(String apkPath) {
        Log.d(TAG, "Log.getMethod()" + " " + apkPath);

        silenceInstall(apkPath);
    }

    public void uninstallApplication(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        silenceUninstall(pkgName);
    }

    public boolean isApplicationForceStopLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getForceStopDisabled(pkgName);
    }

    public boolean lockApplicationForceStop(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setForceStopDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isApplicationDataRemoveLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getClearAppDataDisabled(pkgName);
    }

    public boolean lockApplicationDataRemove(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setClearAppDataDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isApplicationCacheRemoveLocked(String pkgName) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName);

        return getClearAppCacheDisabled(pkgName);
    }

    public boolean lockApplicationCacheRemove(String pkgName, boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgName + " " + lock);

        try {
            setClearAppCacheDisabled(pkgName, lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAdminLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getDeviceManagerDisabled();
    }

    public boolean setAdminLocked(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setDeviceManagerDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFactoryResetLocked() {
        Log.d(TAG, "Log.getMethod()");

        return getFactoryResetDisabled();
    }

    public boolean lockFactoryReset(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setFactoryResetDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSWUpdateLocked() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "system.update.disabled", 0) == 1;
    }

    public boolean lockSWUpdate(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);
        if (mdmService == null) return false;

        try {
            return mdmService.lockSWUpdate(lock);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean isCellData() {
        Log.d(TAG, "Log.getMethod()");

        return getCellDataDisabled();
    }

    public boolean lockCellData(boolean lock) {
        Log.d(TAG, "Log.getMethod()" + " " + lock);

        try {
            setCellDataDisabled(lock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setStatusBarDisabled(boolean disabled) {
        try {
            mdmService.setStatusBarDisabled(disabled);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getStatusBarDisabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "show.statusbar.enabled", 1) == 0;
    }

    public boolean setNavigationBarDisabled(boolean disabled) {
        try {
            mdmService.setNavigationBarDisabled(disabled);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean getNavigationBarDisabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "show.navigationbar.enabled", 1) == 0;
    }

    public boolean lockAirplaneMode(boolean lock) {
        try {
            mdmService.lockAirplaneMode(lock);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lockShareList(boolean lock) {
        try {
            mdmService.lockShareList(lock);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isShareListLocked() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "share.list.enable", 1) == 0;
    }


    public boolean setSingleAppMode(boolean bSet) {
        return setSingleAppMode(bSet, null);
    }


    public boolean setSingleAppMode(boolean bSet, String activity) {
        try {
            mdmService.setSingleAppMode(bSet, activity);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean preventNewAdminActivation(boolean prevent) {
        try {
            mdmService.preventNewAdminActivation(prevent);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNewAdminActivationPrevented(){
        return Settings.Global.getInt(this.mContext.getContentResolver(), "device.manager.active.prevented", 0) == 1;
    }

    public boolean preventNewAdminInstall(boolean prevent) {
        try {
            mdmService.preventNewAdminInstall(prevent);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isNewAdminActivationInstall(){
        return Settings.Global.getInt(this.mContext.getContentResolver(), "device.manager.install.prevented", 0) == 1;
    }


    private String deduplication(String srcStr) {
        Log.d(TAG, "Log.getMethod()" + " " + srcStr);

        if (srcStr == null || "".equals(srcStr)) {
            return "";
        }
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(Arrays.asList(srcStr.split(",")));
        set.remove("null");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            tgtStr.append(it.next());
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        }
        return "";
    }

    private String deduplication(List<String> srcList) {
        Log.d(TAG, "Log.getMethod()");

        if (srcList.size() < 1) {
            return "";
        }
        StringBuilder tgtStr = new StringBuilder();
        HashSet<String> set = new HashSet<>(srcList);
        set.remove("null");
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            tgtStr.append(it.next());
            tgtStr.append(",");
        }
        if (tgtStr.length() > 0) {
            return tgtStr.substring(0, tgtStr.length() - 1);
        }
        return "";
    }

    public void setBluetoothDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setBluetoothDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getBluetoothDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "bluetooth.module.enable", 1) == 0;
    }

    public void setBluetoothTetherDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setBluetoothTetherDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getBluetoothTetherDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "bluetooth.tether.enable", 1) == 0;
    }

    public void setBluetoothFileShareDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setBluetoothFileShareDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getBluetoothFileShareDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "bluetooth.opp.enable", 1) == 0;
    }

    public void setWifiDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setWifiDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getWifiDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "wifi.module.enable", 1) == 0;
    }

    public void setWifiP2PDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setWifiP2PDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getWifiP2PDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "wifi.p2p.enable", 1) == 0;
    }

    public void setWifiTetherDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setWifiTetherDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getWifiTetherDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "wifi.tether.enable", 1) == 0;
    }

    public void setOTGDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setOTGDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getOTGDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "otg.enabled", 1) == 0;
    }

    public void setUSBDebugDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setUSBDebugDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getUSBDebugDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "adb_enabled", 1) == 0;
    }

    public void setUSBDataDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);

        setUSBDataMode(disabled ? "none" : "mtp");
    }

    public boolean getUSBDataDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "usb.data.transfer.enable", 1) == 0;
    }

    public void setUSBDataMode(int mode) {
        Log.d(TAG, "Log.getMethod()" + " " + mode);
        if (mdmService == null) return;

        try {
            mdmService.setUSBDataMode(mode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setUSBDataMode(String mode) {
        Log.d(TAG, "Log.getMethod()" + " " + mode);

        byte b;

        switch (mode) {
            case "rndis":
                b = 1;
                break;
            case "midi":
                b = 2;
                break;
            case "ptp":
                b = 3;
                break;
            case "mtp":
                b = 0;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 3:
                setUSBDataMode(4);
                return;
            case 2:
                setUSBDataMode(3);
                return;
            case 1:
                setUSBDataMode(2);
                return;
            case 0:
                break;
            default:
                setUSBDataMode(0);
                return;
        }
        setUSBDataMode(1);
    }

    public void setUsbTetherDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setUsbTetherDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getUsbTetherDisabled() {
        Log.d(TAG, "Log.getMethod()");
        return Settings.Global.getInt(this.mContext.getContentResolver(), "usb.tether.enable", 1) == 0;
    }

    public void setGPSDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setGPSDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getGPSDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "gps.module.enable", 1) == 0;
    }

    public void setGPSOn(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setGPSDisabled(!disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getGPSOn() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "gps.module.enable", 1) == 1;
    }

    public void setMockLocationDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setMockLocationDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getMockLocationDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "mock.location.enabled", 1) == 0;
    }

    public void setCameraDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setCameraDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getCameraDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "camera.enabled", 1) == 0;
    }

    public void setMicDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setMicDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getMicDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "mic.module.enable", 1) == 0;
    }

    public void setSDCardDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);

        try {
            mdmService.setSDCardDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getSDCardDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "sd.tf.visible", 1) == 0;
    }

    public void setScreenshotDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setScreenshotDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getScreenshotDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "screenshot.enabled", 1) == 0;
    }

    public void setApplicationDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setApplicationDisabled(packageName, disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getApplicationDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + packageName);
        Log.d(TAG, "Log.getMethod()" + getDisabledApplications());

        return Arrays.asList(getDisabledApplications().split(",")).contains(packageName);
    }

    public void setApplicationsDisabled(String packageNames, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageNames + " " + disabled);

        if (packageNames != null && !packageNames.equals("")) {
            for (String packageName : packageNames.split(",")) {
                setApplicationDisabled(packageName, disabled);
            }
        }
    }

    public String getDisabledApplications() {
        Log.d(TAG, "Log.getMethod()");

        String disabledApps = "";
        for (PackageInfo info : this.mContext.getPackageManager().getInstalledPackages(0)) {
            if (info.applicationInfo.packageName.contains(("youtube")))
                Log.d(TAG, "applicationInfo.enabled:" + info.applicationInfo.enabled);
            if (!info.applicationInfo.enabled) {
                if (disabledApps.equals("")) {
                    disabledApps = info.packageName;
                } else {
                    disabledApps = disabledApps + "," + info.packageName;
                }
            }
        }
        return disabledApps;
    }

    public void setInstallDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);

        if (disabled) {
            insertInstallBlacklist(packageName);
        } else {
            removeInstallBlacklist(packageName);
        }
    }

    public boolean getInstallDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        if (queryInstallBlacklist() == null) {
            return false;
        }
        return Arrays.asList(queryInstallBlacklist().split(",")).contains(packageName);
    }

    public void setUninstallDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);

        if (disabled) {
            insertUninstallBlacklist(packageName);
        } else {
            removeUninstallBlacklist(packageName);
        }
    }

    public boolean getUninstallDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        if (queryUninstallBlacklist() == null) {
            return false;
        }
        return Arrays.asList(queryUninstallBlacklist().split(",")).contains(packageName);
    }

    public void silenceInstall(String apkPath) {
        Log.d(TAG, "Log.getMethod()" + apkPath);

        Intent intent = new Intent("install.application");
        intent.putExtra("path_name", apkPath);
        intent.setPackage("com.android.shell");
        this.mContext.sendBroadcast(intent, null);
    }

    public void silenceUninstall(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        Intent intent = new Intent("uninstall.application");
        intent.putExtra("pkg_name", packageName);
        intent.setPackage("com.android.inputdevices");
        this.mContext.sendBroadcast(intent, null);
    }

    public void setClearAppDataDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);

        if (disabled) {
            insertClearDataBlacklist(packageName);
        } else {
            removeClearDataBlacklist(packageName);
        }
    }

    public boolean getClearAppDataDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        if (queryClearDataBlacklist() == null) {
            return false;
        }
        return Arrays.asList(queryClearDataBlacklist().split(",")).contains(packageName);
    }

    public void setClearAppCacheDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);

        if (disabled) {
            insertClearCacheBlacklist(packageName);
        } else {
            removeClearCacheBlacklist(packageName);
        }
    }

    public boolean getClearAppCacheDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        if (queryClearCacheBlacklist() == null) {
            return false;
        }
        return Arrays.asList(queryClearCacheBlacklist().split(",")).contains(packageName);
    }

    public void setForceStopDisabled(String packageName, boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName + " " + disabled);

        if (disabled) {
            insertForceStopBlacklist(packageName);
        } else {
            removeForceStopBlacklist(packageName);
        }
    }

    public boolean getForceStopDisabled(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        if (queryForceStopBlacklist() == null) {
            return false;
        }
        return Arrays.asList(queryForceStopBlacklist().split(",")).contains(packageName);
    }

    public void setDeviceManagerDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setDeviceManagerDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getDeviceManagerDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "device.manager.enabled", 1) == 0;
    }

    public void setFactoryResetDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setFactoryResetDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getFactoryResetDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "enable.factory.reset", 1) == 0;
    }

    public void setSafeModeBootDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setSafeModeBootDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getSafeModeBootDisabled() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "enable.safemode.boot", 1) == 0;
    }

    public void disableUninstallBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableUninstallBlacklist((String) null);
    }

    public void enableUninstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableUninstallBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryUninstallBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.uninstall.blacklist");
    }

    public void removeUninstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryUninstallBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryUninstallBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateUninstallBlacklist(pkgs2, false);
        }
    }

    public void insertUninstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateUninstallBlacklist(pkgs, true);
    }

    private void updateUninstallBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateUninstallBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableInstallBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableInstallBlacklist((String) null);
    }

    public void enableInstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableInstallBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryInstallBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.install.blacklist");
    }

    public void removeInstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryInstallBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryInstallBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateInstallBlacklist(pkgs2, false);
        }
    }

    public void insertInstallBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateInstallBlacklist(pkgs, true);
    }

    private void updateInstallBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateInstallBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableClearDataBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableClearDataBlacklist((String) null);
    }

    public void enableClearDataBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableClearDataBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryClearDataBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.cleardata.blacklist");
    }

    public void removeClearDataBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryClearDataBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryClearDataBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateClearDataBlacklist(pkgs2, false);
        }
    }

    public void insertClearDataBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateClearDataBlacklist(pkgs, true);
    }

    private void updateClearDataBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateClearDataBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableClearCacheBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableClearCacheBlacklist((String) null);
    }

    public void enableClearCacheBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableClearCacheBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryClearCacheBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.clearcache.blacklist");
    }

    public void removeClearCacheBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryClearCacheBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryClearCacheBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateClearCacheBlacklist(pkgs2, false);
        }
    }

    public void insertClearCacheBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateClearCacheBlacklist(pkgs, true);
    }

    private void updateClearCacheBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateClearCacheBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableForceStopBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableForceStopBlacklist((String) null);
    }

    public void enableForceStopBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableForceStopBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryForceStopBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.forcestop.blacklist");
    }

    public void removeForceStopBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryForceStopBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryForceStopBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateForceStopBlacklist(pkgs2, false);
        }
    }

    public void insertForceStopBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateForceStopBlacklist(pkgs, true);
    }

    private void updateForceStopBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateForceStopBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMultiUserDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setMultiUserDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getMultiUserDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "multi.user.module.enable", 1) == 0;
    }

    public boolean deleteNewUser() {
        Log.d(TAG, "Log.getMethod()");
        if (mdmService == null) return false;

        try {
            return mdmService.deleteNewUser();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clearLockPassword() {
        Log.d(TAG, "Log.getMethod()");
        if (mdmService == null) return;

        try {
            mdmService.clearLockPassword();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setSMSDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setSMSDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getSMSDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "sms.enabled", 1) != 1;
    }

    public void setOutgoingCallDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setOutgoingCallDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getOutgoingCallDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "outcall.enabled", 1) != 1;
    }

    public void setIncomingCallDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setIncomingCallDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getIncomingCallDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "incall.enabled", 1) != 1;
    }

    public void setCellDataDisabled(boolean disabled) {
        Log.d(TAG, "Log.getMethod()" + " " + disabled);
        if (mdmService == null) return;

        try {
            mdmService.setCellDataDisabled(disabled);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean getCellDataDisabled() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getInt(this.mContext.getContentResolver(), "sim.data.enabled", 1) != 1;
    }

    public void grantAppAllPermission(String packageName) {
        Log.d(TAG, "Log.getMethod()" + " " + packageName);

        Intent intent = new Intent("grant.all.permission");
        intent.putExtra("pkg_name", packageName);
        if (Build.VERSION.SDK_INT >= 29) {
            intent.setPackage("com.android.permissioncontroller");
        } else {
            intent.setPackage("com.android.packageinstaller");
        }
        this.mContext.sendBroadcast(intent, null);
    }

    public void setAllowAppInstallUnknowSource(String pkg, boolean allow) {
        Log.d(TAG, "Log.getMethod()" + " " + pkg + " " + allow);
        if (mdmService == null) return;

        try {
            mdmService.setAllowAppInstallUnknowSource(pkg, allow);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAllowAppDisplayOverOtherApps(String pkg, boolean allow) {
        Log.d(TAG, "Log.getMethod()" + " " + pkg + " " + allow);
        if (mdmService == null) return;

        try {
            mdmService.setAllowAppDisplayOverOtherApps(pkg, allow);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableWIFIBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        enableWIFIBlacklist((String) null);
    }

    public void enableWIFIBlacklist(String ssids) {
        Log.d(TAG, "Log.getMethod()" + " " + ssids);
        if (mdmService == null) return;

        try {
            mdmService.enableWIFIBlacklist(ssids);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryWIFIBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "wifi.blacklist");
    }

    public void removeWIFIBlacklist(String ssids) {
        Log.d(TAG, "Log.getMethod()" + " " + ssids);

        List<String> blacklist = Arrays.asList(queryWIFIBlacklist().split(","));
        List<String> mBlackList = new ArrayList<>(blacklist);
        for (String pkg : ssids.split(",")) {
            if (blacklist.contains(pkg)) {
                mBlackList.remove(pkg);
            }
        }
        HashSet<String> set = new HashSet<>(mBlackList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mBlackList.get(i)).append(",");
        }
        String ssids2 = pkgsBuilder.toString();
        if (ssids2.endsWith(",")) {
            ssids2 = ssids2.substring(0, ssids2.length() - 1);
        }
        updateWIFIBlacklist(ssids2, false);
    }

    public void insertWIFIBlacklist(String ssids) {
        Log.d(TAG, "Log.getMethod()" + " " + ssids);

        updateWIFIBlacklist(ssids, true);
    }

    private void updateWIFIBlacklist(String ssids, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " ssids:" + ssids + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateWIFIBlacklist(ssids, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableInstallWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        enableInstallWhitelist((String) null);
    }

    public void enableInstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableInstallWhitelist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryInstallWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.install.whitelist");
    }

    public void removeInstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        List<String> whitelist = Arrays.asList(queryInstallWhitelist().split(","));
        List<String> mWhiteList = new ArrayList<>(whitelist);
        for (String pkg : pkgs.split(",")) {
            if (whitelist.contains(pkg)) {
                mWhiteList.remove(pkg);
            }
        }
        HashSet<String> set = new HashSet<>(mWhiteList);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(mWhiteList.get(i)).append(",");
        }
        String pkgs2 = pkgsBuilder.toString();
        if (pkgs2.endsWith(",")) {
            pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
        }
        updateInstallWhitelist(pkgs2, false);
    }

    public void insertInstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateInstallWhitelist(pkgs, true);
    }

    private void updateInstallWhitelist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateInstallWhitelist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableUninstallWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        enableUninstallWhitelist((String) null);
    }

    public void enableUninstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableUninstallWhitelist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryUninstallWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        return Settings.Global.getString(this.mContext.getContentResolver(), "app.uninstall.whitelist");
    }

    public void removeUninstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        List<String> whitelist = Arrays.asList(queryUninstallWhitelist().split(","));
        List<String> Whitelist = new ArrayList<>(whitelist);
        for (String pkg : pkgs.split(",")) {
            if (whitelist.contains(pkg)) {
                Whitelist.remove(pkg);
            }
        }
        HashSet<String> set = new HashSet<>(Whitelist);
        StringBuilder pkgsBuilder = new StringBuilder();
        for (int i = 0; i < set.size(); i++) {
            pkgsBuilder.append(Whitelist.get(i)).append(",");
        }
        String pkgs2 = pkgsBuilder.toString();
        if (pkgs2.endsWith(",")) {
            pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
        }
        updateUninstallWhitelist(pkgs2, false);
    }

    public void insertUninstallWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateUninstallWhitelist(pkgs, true);
    }

    private void updateUninstallWhitelist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateUninstallWhitelist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void enableWebBlacklist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            enableIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            enableUrlBlacklist(urls.toString());
        }
    }

    public String queryWebBlacklist() {
        String list = deduplication(queryIpBlacklist() + "," + queryUrlBlacklist());

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeWebBlacklist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            removeIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            removeUrlBlacklist(urls.toString());
        }
    }

    public void insertWebBlacklist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            insertIpBlacklist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            insertUrlBlacklist(urls.toString());
        }
    }

    public void disableWebBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        removeUrlBlacklist(queryUrlBlacklist());
        removeIpBlacklist(queryIpBlacklist());
    }

    public void disableUrlBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        removeUrlBlacklist(queryUrlBlacklist());
    }

    public void enableUrlBlacklist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);
        if (mdmService == null) return;

        try {
            mdmService.enableUrlBlacklist(urls);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryUrlBlacklist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "url.blacklist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeUrlBlacklist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);

        if (queryUrlBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryUrlBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String url : urls.split(",")) {
                if (blacklist.contains(url)) {
                    mBlackList.remove(url);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder urlsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                urlsBuilder.append(mBlackList.get(i)).append(",");
            }
            String urls2 = urlsBuilder.toString();
            if (urls2.endsWith(",")) {
                urls2 = urls2.substring(0, urls2.length() - 1);
            }
            updateUrlBlacklist(urls2, false);
        }
    }

    public void insertUrlBlacklist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);

        updateUrlBlacklist(urls, true);
    }

    private void updateUrlBlacklist(String urls, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " urls:" + urls + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateUrlBlacklist(urls, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableIpBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        removeIpBlacklist(queryIpBlacklist());
    }

    public void enableIpBlacklist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);
        if (mdmService == null) return;

        try {
            mdmService.enableIpBlacklist(ips);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryIpBlacklist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "ip.blacklist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeIpBlacklist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        if (queryIpBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryIpBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String ip : ips.split(",")) {
                if (blacklist.contains(ip)) {
                    mBlackList.remove(ip);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder ipsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                ipsBuilder.append(mBlackList.get(i)).append(",");
            }
            String ips2 = ipsBuilder.toString();
            if (ips2.endsWith(",")) {
                ips2 = ips2.substring(0, ips2.length() - 1);
            }
            updateIpBlacklist(ips2, false);
        }
    }

    public void insertIpBlacklist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        updateIpBlacklist(ips, true);
    }

    private void updateIpBlacklist(String ips, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " ips:" + ips + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateIpBlacklist(ips, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableAppBlacklist() {
        Log.d(TAG, "Log.getMethod()");

        removeAppBlacklist(queryAppBlacklist());
    }

    public void enableAppBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableAppBlacklist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryAppBlacklist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "app.blacklist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeAppBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryAppBlacklist() != null) {
            List<String> blacklist = Arrays.asList(queryAppBlacklist().split(","));
            List<String> mBlackList = new ArrayList<>(blacklist);
            for (String pkg : pkgs.split(",")) {
                if (blacklist.contains(pkg)) {
                    mBlackList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mBlackList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mBlackList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateAppBlacklist(pkgs2, false);
        }
    }

    public void insertAppBlacklist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateAppBlacklist(pkgs, true);
    }

    private void updateAppBlacklist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateAppBlacklist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void disableWebWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        removeUrlWhitelist(queryUrlWhitelist());
        removeIpWhitelist(queryIpWhitelist());
    }

    public void disableAppWhitelist() {
        Log.d(TAG, "Log.getMethod()");

        enableAppWhitelist((String) null);
    }

    public void enableAppWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);
        if (mdmService == null) return;

        try {
            mdmService.enableAppWhitelist(pkgs);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryAppWhitelist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "app.whitelist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeAppWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        if (queryAppWhitelist() != null) {
            List<String> whitelist = Arrays.asList(queryAppWhitelist().split(","));
            List<String> mWhiteList = new ArrayList<>(whitelist);
            for (String pkg : pkgs.split(",")) {
                if (whitelist.contains(pkg)) {
                    mWhiteList.remove(pkg);
                }
            }
            HashSet<String> set = new HashSet<>(mWhiteList);
            StringBuilder pkgsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                pkgsBuilder.append(mWhiteList.get(i)).append(",");
            }
            String pkgs2 = pkgsBuilder.toString();
            if (pkgs2.endsWith(",")) {
                pkgs2 = pkgs2.substring(0, pkgs2.length() - 1);
            }
            updateAppWhitelist(pkgs2, false);
        }
    }

    public void insertAppWhitelist(String pkgs) {
        Log.d(TAG, "Log.getMethod()" + " " + pkgs);

        updateAppWhitelist(pkgs, true);
    }

    public void updateAppWhitelist(String pkgs, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " pkgs:" + pkgs + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateAppWhitelist(pkgs, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void enableIpWhitelist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);
        if (mdmService == null) return;

        try {
            mdmService.enableIpWhitelist(ips);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryIpWhitelist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "ip.whitelist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeIpWhitelist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        if (queryIpWhitelist() != null) {
            List<String> whitelist = Arrays.asList(queryIpWhitelist().split(","));
            List<String> mWhiteList = new ArrayList<>(whitelist);
            for (String ip : ips.split(",")) {
                if (whitelist.contains(ip)) {
                    mWhiteList.remove(ip);
                }
            }
            HashSet<String> set = new HashSet<>(mWhiteList);
            StringBuilder ipsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                ipsBuilder.append(mWhiteList.get(i)).append(",");
            }
            String ips2 = ipsBuilder.toString();
            if (ips2.endsWith(",")) {
                ips2 = ips2.substring(0, ips2.length() - 1);
            }
            updateIpWhitelist(ips2, false);
        }
    }

    public void insertIpWhitelist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        updateIpWhitelist(ips, true);
    }

    private void updateIpWhitelist(String ips, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " ips:" + ips + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateIpWhitelist(ips, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void enableUrlWhitelist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);
        if (mdmService == null) return;

        try {
            mdmService.enableUrlWhitelist(urls);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String queryUrlWhitelist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "url.whitelist");

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeUrlWhitelist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);

        if (queryUrlWhitelist() != null) {
            List<String> whitelist = Arrays.asList(queryUrlWhitelist().split(","));
            List<String> mWhiteList = new ArrayList<>(whitelist);
            for (String url : urls.split(",")) {
                if (whitelist.contains(url)) {
                    mWhiteList.remove(url);
                }
            }
            HashSet<String> set = new HashSet<>(mWhiteList);
            StringBuilder urlsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                urlsBuilder.append(mWhiteList.get(i)).append(",");
            }
            String urls2 = urlsBuilder.toString();
            if (urls2.endsWith(",")) {
                urls2 = urls2.substring(0, urls2.length() - 1);
            }
            updateUrlWhitelist(urls2, false);
        }
    }

    public void insertUrlWhitelist(String urls) {
        Log.d(TAG, "Log.getMethod()" + " " + urls);

        updateUrlWhitelist(urls, true);
    }

    private void updateUrlWhitelist(String urls, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " urls:" + urls + " append:" + append);
        if (mdmService == null) return;

        try {
            mdmService.updateUrlWhitelist(urls, append);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void enableWebWhitelist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            enableIpWhitelist(ips.toString());
        } else {
            enableIpWhitelist("");
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            enableUrlWhitelist(urls.toString());
            return;
        }
        enableUrlWhitelist("");
    }

    public String queryWebWhitelist() {
        String list = deduplication(queryIpWhitelist() + "," + queryUrlWhitelist());

        Log.d(TAG, "Log.getMethod()" + " " + list);

        return list;
    }

    public void removeWebWhitelist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            removeIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            removeUrlWhitelist(urls.toString());
        }
    }

    public void insertWebWhitelist(String links) {
        Log.d(TAG, "Log.getMethod()" + " " + links);

        String[] linkList = links.split(",");
        List<String> ipList = new ArrayList<>();
        List<String> urlList = new ArrayList<>();
        for (String link : linkList) {
            if (link.matches("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)/(3[0-2]|[1-2]\\d{1}|[1-9]?\\d)|(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)")) {
                ipList.add(link);
            } else {
                urlList.add(link);
            }
        }
        StringBuilder ips = new StringBuilder();
        StringBuilder urls = new StringBuilder();
        if (ipList.size() > 0) {
            HashSet<String> set = new HashSet<>(ipList);
            for (int i = 0; i < set.size(); i++) {
                ips.append(ipList.get(i)).append(",");
            }
            if (ips.toString().endsWith(",")) {
                ips = new StringBuilder(ips.substring(0, ips.length() - 1));
            }
            insertIpWhitelist(ips.toString());
        }
        if (urlList.size() > 0) {
            HashSet<String> set2 = new HashSet<>(urlList);
            for (int i2 = 0; i2 < set2.size(); i2++) {
                urls.append(urlList.get(i2)).append(",");
            }
            if (urls.toString().endsWith(",")) {
                urls = new StringBuilder(urls.substring(0, urls.length() - 1));
            }
            insertUrlWhitelist(urls.toString());
        }
    }

    public String queryDnslist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "dns.list");

        Log.d(TAG, "Log.getMethod()" + " " + list);
        return list;
    }

    public void removeDnslist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        if (queryDnslist() != null) {
            List<String> dnslist = Arrays.asList(queryDnslist().split(","));
            List<String> mDnsList = new ArrayList<>(dnslist);
            for (String ip : ips.split(",")) {
                if (dnslist.contains(ip)) {
                    mDnsList.remove(ip);
                }
            }
            HashSet<String> set = new HashSet<>(mDnsList);
            StringBuilder ipsBuilder = new StringBuilder();
            for (int i = 0; i < set.size(); i++) {
                ipsBuilder.append(mDnsList.get(i)).append(",");
            }
            String ips2 = ipsBuilder.toString();
            if (ips2.endsWith(",")) {
                ips2 = ips2.substring(0, ips2.length() - 1);
            }
            updateDnslist(ips2, false);
        }
    }

    public void insertDnslist(String ips) {
        Log.d(TAG, "Log.getMethod()" + " " + ips);

        updateDnslist(ips, true);
    }

    private void updateDnslist(String ips, boolean append) {
        Log.d(TAG, "Log.getMethod()" + " ips:" + ips + " append:" + append);

        Intent intent = new Intent("update.dns.list");
        String dns_list = deduplication(ips);
        Log.d(TAG, "Log.getMethod()" + " dns_list:" + dns_list);
        if (dns_list.length() < 1) {
            dns_list = null;
        }

        intent.putExtra("dns_list", dns_list);
        intent.setPackage("com.android.inputdevices");
        this.mContext.sendBroadcast(intent, null);
    }

    public void disableFirewall() {
        Intent intent = new Intent();
        intent.setAction("disable.firewall");
        intent.setPackage("com.android.inputdevices");
        this.mContext.sendBroadcast(intent, null);
    }

    public void setUrlBlackList(List<String> urls, List<String> apps) {
        Log.d(TAG, "Log.getMethod()");

        String urlStr = null;
        String appStr = null;

        if (urls != null && urls.size() > 0) {
            StringBuilder urlBuilder = new StringBuilder();
            for (String url : urls) {
                url = url.replaceAll("\\*.", "*");
                url = url.replaceAll(".\\*", "*");
                url = url.replaceAll("\\*", "");

                urlBuilder.append(url).append(",");
            }
            String url2 = urlBuilder.toString();
            if (url2.endsWith(",")) {
                url2 = url2.substring(0, url2.length() - 1);
            }

            urlStr = url2;
        }

        Log.d(TAG, "Log.getMethod()" + " urlStr:" + urlStr);

        if (apps != null && apps.size() > 0) {
            StringBuilder appBuilder = new StringBuilder();
            for (String app : apps) {
                appBuilder.append(app).append(",");
            }
            String app2 = appBuilder.toString();
            if (app2.endsWith(",")) {
                app2 = app2.substring(0, app2.length() - 1);
            }

            appStr = app2;
        }

        Log.d(TAG, "Log.getMethod()" + " appStr:" + appStr);

        Intent intent = new Intent();
        intent.setAction("update.url.blacklist.forapp");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("url_list", urlStr);
        intent.putExtra("app_list", appStr);
        this.mContext.sendBroadcast(intent, null);
    }

    public String displayUrlBlacklist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "url.blacklist.forapp");

        Log.d(TAG, "Log.getMethod()" + " " + list);
        return list;
    }

    public void setUrlWhiteList(List<String> urls, List<String> apps) {
        Log.d(TAG, "Log.getMethod()");

        String urlStr = null;
        String appStr = null;

        if (urls != null && urls.size() > 0) {
            StringBuilder urlBuilder = new StringBuilder();
            for (String url : urls) {
                url = url.replaceAll("\\*.", "*");
                url = url.replaceAll(".\\*", "*");
                url = url.replaceAll("\\*", "");

                urlBuilder.append(url).append(",");
            }
            String url2 = urlBuilder.toString();
            if (url2.endsWith(",")) {
                url2 = url2.substring(0, url2.length() - 1);
            }

            urlStr = url2;
        }

        Log.d(TAG, "Log.getMethod()" + " urlStr:" + urlStr);

        if (apps != null && apps.size() > 0) {
            StringBuilder appBuilder = new StringBuilder();
            for (String app : apps) {
                appBuilder.append(app).append(",");
            }
            String app2 = appBuilder.toString();
            if (app2.endsWith(",")) {
                app2 = app2.substring(0, app2.length() - 1);
            }

            appStr = app2;
        }

        Log.d(TAG, "Log.getMethod()" + " appStr:" + appStr);

        Intent intent = new Intent();
        intent.setAction("update.url.whitelist.forapp");
        intent.setPackage("com.android.inputdevices");
        intent.putExtra("url_list", urlStr);
        intent.putExtra("app_list", appStr);
        this.mContext.sendBroadcast(intent, null);
    }

    public String displayUrlWhitelist() {
        String list = Settings.Global.getString(this.mContext.getContentResolver(), "url.whitelist.forapp");

        Log.d(TAG, "Log.getMethod()" + " " + list);
        return list;
    }

}
