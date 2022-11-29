package com.hite.mdm;

import java.util.ArrayList;
import java.util.List;

public interface MdmApi {

    /**
     *  是否开启网络防火墙
     *
     * @param state
     */
     void setFirewallType(boolean state);

    /**
     * 添加不受网络管控的app
     *
     * @param packageName
     */
    void addNetworkAccessAppWhitelist(String packageName);

    /**
     * 添加网络访问黑名单
     * @param blackLists
     */
    void addNetworkAccessAppBlacklist(ArrayList<String> blackLists);

    /**
     * 启用USB,能充电,能连接电脑,也能打开调试模式
     *
     * @param state true：启用，false 禁用
     */
     void mdmUsb(boolean state);

    /**
     * 将应用白名单写入系统，写入之后，白名单列表之外的应用系统需要实现不允许安装
     *
     * @param packageNames 包名list
     * @return true:写入成功;false:写入失败
     */
    boolean setAppWhiteListWrite(List<String> packageNames);

    /**
     *  设置应用黑名单
     * @param blackLists
     */
    void setAppBlackListWrite(List<String> blackLists);

    /**
     * 从系统获取已写入的应用白名单
     *
     * @return 应用白名单的包名list
     */
    List<String> getAllappWhiteList();

    /**
     *  获取所有应用黑名单列表
     */
    List<String> getAllappBlackList();

    /**
     * 静默安装
     *
     * @param path apk本地路径
     */
    void silentInstall(String path);

    /**
     * 静默卸载
     *
     * @param packageName 指定要卸载的应用包名
     */
    void silentUnInstall(String packageName);

    /**
     * 启用/禁用某个应用
     *
     * @param packageName 需要被解冻的应用包名
     * @param state       true：启用，false 禁用
     */
    void mdmApplication(List<String> packageName, boolean state);

    /**
     *  应用禁用名单全部删除
     */
    void removeAllAppBlackList();

    /**
     * 启用相机
     */
    void mdmCamera(boolean state);

    /**
     * 获取当前Camera是否被禁用
     * @return
     */
    boolean isCameraDisable();

    /**
     * 启用/禁用下拉状态栏
     *
     * @param state
     */
    void mdmStatusbar(boolean state);

    /**
     * 启用Home键
     *
     * @param state true：启用，false 禁用
     */
    void mdmHomeKey(boolean state);

    /**
     * 启用Back键
     *
     * @param state true：启用，false 禁用
     */
    void mdmBackKey(boolean state);

    /**
     * 启用最近任务键
     *
     * @param state true：启用，false 禁用
     */
    void mdmRecentKey(boolean state);

    /**
     * 启用任务栏
     *
     * @param state true：启用，false 禁用
     */
    void mdmTaskBar(boolean state);

    /**
     * 启用短按电源键
     *
     * @param state true：启用，false 禁用
     */
    void mdmShortPressPower(boolean state);

    /**
     * 启用音量键，可加减调节音量
     *
     * @param state true：启用，false 禁用
     */
    void mdmVolumeKey(boolean state);

    /**
     * 启用蓝牙
     *
     * @param state true：启用，false 禁用
     */
    void mdmBluetooth(boolean state);

    /**
     * 修改系统时间
     *
     * @param milliSecond 当前毫秒
     */
     void modifySystemTime(long milliSecond);

    /**
     * 执行adb shell后的命令
     *
     * @param cmdList
     * @return
     */
    boolean runADBComm(List<String> cmdList);

    /**
     * 启用OTG
     *
     * @param state true：启用，false 禁用
     */
    void mdmOTG(boolean state);

    /**
     * 启用SdCard
     *
     * @param state true：启用，false 禁用
     */
    void mdmSdCard(boolean state);

    /**
     * 关闭指定报名的应用
     *
     * @param packageName
     */
    void killApplication(String packageName);

    /**
     * 本接口对应开发指导书中的 开启 关闭仅以指定某应用商店安装应用功能， 开启时屏蔽其
     * 他应用商店安装，同时屏蔽其他安装方式，如屏蔽 adb 和 SD 卡安装方式 功能
     * 本接口会屏蔽安装 方式 仅包名添加到 whitelist 列表的应用 ，有安装其他应用的功能 ，如
     * 某些应用市场。 包名不在 此 list 中 的 应用市场 ，不能安装应用 。 同时， 调用此方法后，
     * sd卡、 adb 等安装方式都会不生效 。
     *
     * @param whiteLists
     */
    void installWhiteList(List<String> whiteLists);

    /**
     * 禁止/允许 使用tf卡和U盘
     *
     * @param state
     */
    void mdmExternalStorageDisabled(boolean state);

    /**
     * 关机
     */
    void shutDown();

    /**
     * 该系统是否已经Root
     *
     * @return
     */
    boolean isRoot();

    /**
     * 获取Rom的版本好
     *
     * @return
     */
    String getRomVersion();

    /**
     * 重启
     */
    void reboot();

    /**
     * 删除指定SSID的wifi
     * @param ssid
     */
    void removeWifiConfiguration(String ssid);

    /**
     * 设置第三方桌面
     *
     * @param packageName
     * @param className
     */
    void setDefaultLauncher(String packageName, String className);

    /**
     *  清除第三方桌面
     */
    void clearDefaultLauncher();

    /**
     * 删除所有的网络黑名单
     */
    void removeAllNetBlackList();

    /**
     * 删除所有的网络白名单
     */
    void removeAllNetWhiteList();
    /**
     * 添加网络访问白名单
     * @param whiteLists
     */
    void addNetworkAccessWhitelist(ArrayList<String> whiteLists);

    /**
     * 添加网络访问黑名单
     * @param whiteLists
     */
    void addNetworkAccessBlacklist(ArrayList<String> whiteLists);

    /**
     * 清除指定应用的缓存
     * @param packageName
     */
    boolean clearPackageCache(String packageName);

    /**
     *  技德隐藏底部网路设置部分功能
     * @param enable
     */
    void setSystemTrayEnable(boolean enable);

    /**
     *  隐藏底部的NavigationBar
     * @param enable
     */
    void mdmNavigationBar(boolean enable);

    /**
     * 开启/关闭 色温调节功能
     * @param enable    true 开启   false 关闭
     * @param level    取值范围【0-1486】
     */
    void setColorTemperatureAdjustmentEnabled(boolean  enable,int level);

    /**
     * 添加应用到保活白名单
     * @param packageName
     */
    void addBackgroundProtectionWhiteList(String packageName);

}
