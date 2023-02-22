package com.mdm.apidemo

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.api.forelink.Utils
import com.mdm.apidemo.ui.theme.APIDemoHubTheme
import com.mdm.forargos.UtilsImpl


class ArgosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val utils = UtilsImpl(baseContext)
        val uu = Utils(baseContext)
        var packageManager : PackageManager = getPackageManager()
        super.onCreate(savedInstanceState)
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val scrollState = rememberScrollState()
                    val cc=this;
                    Column(Modifier.verticalScroll(scrollState)) {
                        Row {
                            Column(Modifier.weight(1.0f, true)) {
/*                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiAppBlacklist(utils)
                                        ApiAppWhitelist(utils)
                                        ApiWebWhitelist(utils)
                                        ApiUrlWhitelist(utils)
                                        ApiIpWhitelist(utils)
                                    }
                                }*/
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        Row {
                                            ApiBluetooth(utils)
                                            ApiBluetoothTether(utils)
                                        }
                                        Row {
                                            ApiWifi(utils)
                                            ApiWifiP2P(utils)
                                            ApiWifiTether(utils)
                                        }
                                        Row {
                                            ApiAdb(utils)
                                            ApiUsbData(utils)
                                            ApiUsbTether(utils)
                                        }
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiExternalStorage(utils)
//                                        ApiScreenshot(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDeviceManagerForArgos(utils)
                                        ApiFactoryReset(utils)
                                        ApiOtaForArgos(utils)
                                        ApiSN(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiClearPassword(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiInsertDnslist(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiSeturlBlacklist(utils)
                                        ApiSetUrlWhitelist(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableSafeModeBoot(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableStatusBar(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableNavigationBar(utils)
                                    }
                                }
                            }

                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        Row {
                                            ApiGPS(utils)
                                            ApiGPSMockForArgos(utils)
                                        }
                                        ApiMic(utils)
                                        ApiCamera(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableApp(utils,uu,packageManager,cc)
                                        Row {
                                            ApiInstallWhitelistForArgos(utils)
                                            ApiUninstallWhitelistForArgos(utils)
                                        }
                                        ApiSilentInstall(utils)
                                        ApiAppDataForArgos(utils)
                                        ApiAppCacheForArgos(utils)
                                        ApiAppForceStopForArgos(utils)
                                    }

                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableMultiUser(utils)

                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiGPSForceOn(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiNetworkLocationForceOn(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiScreenShot(utils)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
//add by elink_ts GPS强制启用
fun ApiGPSForceOn(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) { Text(text = "GPS强制启用", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(utils.getGPSOn()) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setGPSOn(checked)
                })
        }
    }
}

@Composable
//add by elink_ts 网络位置提供程序强制打开  未找到调用方法
fun ApiNetworkLocationForceOn(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) { Text(text = "网络位置强制打开", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(utils.getGPSOn()) }
            Log.d("rrr", "sssss" + checkedState)
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setGPSOn(checked)
                    Log.d("rrr", "tttt" + checked)
                })
        }
    }
}

@Composable
//add by elink_ts 屏幕截图强制禁用
fun ApiScreenShot(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) { Text(text = "禁用屏幕截图", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(utils.isScreenCaptureLocked()) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.lockScreenCapture(checked)
                })
        }
    }
}

@Composable
//add by elink_ts 强制清除密码  无效果
fun ApiClearPassword(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        val flag =false
        TextButton(onClick = { }) { Text(text = "✅强制清除密码", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(flag) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setScreenLockNone()
                })
        }
    }
}
@Composable
//add by elink_ts 更新防火墙DNS列表
fun ApiInsertDnslist(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "更新防火墙DNS列表✅", Modifier.padding(0.dp, 4.dp)) }
        var dnslist by remember { mutableStateOf(TextFieldValue(utils.queryDnslist()))}
        TextField(value = dnslist, onValueChange = {
            dnslist = it
        }, label = { Text("dns") })
        Spacer(Modifier.height(4.dp))
        Row {
            Button(
                onClick = {
                    utils.insertDnslist(dnslist.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .width(200.dp)
            ) { Text(text = "更新dns") }
        }
    }
}

@Composable
//add by elink_ts 设置url黑名单
fun ApiSeturlBlacklist(utils: UtilsImpl) {
    val urlList: MutableList<String> = ArrayList()
    val appList: MutableList<String> = ArrayList()
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.displayUrlBlacklist())
        }) { Text(text = "Url黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("125.133.65.187")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(域名url 英文逗号分隔)") })
        var txtAPP by remember { mutableStateOf(TextFieldValue("org.lineageos.jelly")) }
        TextField(value = txtAPP, onValueChange = {
            txtAPP = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(APP名 英文逗号分隔)") })

        val linkList: Array<String> = txtPackage.text.split(",").toTypedArray()
        val linkappList: Array<String> = txtAPP.text.split(",").toTypedArray()
        for (link in linkList) {
            urlList.add(link)
        }
        for (app in linkappList) {
            appList.add(app)
        }

        Row {
            Button(
                onClick = {
                    utils.setUrlBlackList(urlList, appList)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .width(200.dp)
            ) { Text(text = "设置黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}
@Composable
//add by elink_ts 设置url白名单
fun ApiSetUrlWhitelist(utils: UtilsImpl) {
    val urlList: MutableList<String> = ArrayList()
    val appList: MutableList<String> = ArrayList()
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.displayUrlWhitelist())
        }) { Text(text = "Url白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("125.133.65.187")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(域名url 英文逗号分隔)") })
        var txtAPP by remember { mutableStateOf(TextFieldValue("org.lineageos.jelly")) }
        TextField(value = txtAPP, onValueChange = {
            txtAPP = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(APP名 英文逗号分隔)") })

        val linkList: Array<String> = txtPackage.text.split(",").toTypedArray()
        val linkappList: Array<String> = txtAPP.text.split(",").toTypedArray()
        for (link in linkList) {
            urlList.add(link)
        }
        for (app in linkappList) {
            appList.add(app)
        }

        Row {
            Button(
                onClick = {
                    utils.setUrlWhiteList(urlList, appList)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .width(200.dp)
            ) { Text(text = "设置白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}
@Composable
//add by elink_ts 安全引导模式禁用 无效果
fun ApiDisableSafeModeBoot(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) { Text(text = "✅禁用安全引导模式", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(utils.getSafeModeBootDisabled()) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setSafeModeBootDisabled(checked)
                })
        }
    }
}
@Composable
//add by elink_ts 状态栏禁用 禁用有效但无get方法无法保存状态
fun ApiDisableStatusBar(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        val flag = utils.statusBarDisabled
        TextButton(onClick = { }) { Text(text = "✅禁用状态栏", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(flag) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.statusBarDisabled = checked
                })
        }
    }
}
@Composable
//add by elink_ts 导航栏禁用  禁用有效但无get方法无法保存状态
fun ApiDisableNavigationBar(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        val flag = utils.navigationBarDisabled
        TextButton(onClick = { }) { Text(text = "✅禁用导航栏", Modifier.padding(0.dp, 4.dp)) }
        Row {
            val checkedState = remember { mutableStateOf(flag)}
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.navigationBarDisabled = checked
                })
        }
    }
}
@Composable
fun ApiDisableMultiUser(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) { Text(text = "禁用多用户", Modifier.padding(0.dp, 4.dp)) }

        Row {


            val checkedState = remember { mutableStateOf(utils.getMultiUserDisabled()) }
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setMultiUserDisabled(checked)
                })

        }
    }
}


@Composable
fun ApiInstallWhitelistForArgos(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        Text(text = "禁用安装✅", Modifier.padding(0.dp, 4.dp))

        Row {
            val checkedState = remember { mutableStateOf(utils.getInstallDisabled("com.UCMobile")) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setInstallDisabled("com.UCMobile", checked)
                })
        }
    }
}

@Composable
fun ApiUninstallWhitelistForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        Text(text = "禁止卸载✅", Modifier.padding(0.dp, 4.dp))

        Row {
            val checkedState =
                remember { mutableStateOf(utils.getUninstallDisabled("com.UCMobile")) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setUninstallDisabled("com.UCMobile", checked)
                })
        }
    }
}

@Composable
fun ApiAppForceStopForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用强制停止", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState =
                remember { mutableStateOf(utils.getForceStopDisabled("com.UCMobile")) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setForceStopDisabled("com.UCMobile", checked)
                })
        }
    }
}

@Composable
fun ApiGPSMockForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用GPS模拟定位", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.mockLocationDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.mockLocationDisabled = checked
                })
        }
    }
}

@Composable
fun ApiAppDataForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) {
            Text(
                text = "✅disabled clear storage(com.UCMobile)",
                Modifier.padding(0.dp, 4.dp)
            )
        }

        Row {
            val checkedState =
                remember { mutableStateOf(utils.getClearAppDataDisabled("com.UCMobile")) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setClearAppDataDisabled("com.UCMobile", checked)
                })
        }
    }
}

@Composable
fun ApiAppCacheForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) {
            Text(
                text = "✅disabled clear cache(com.UCMobile)",
                Modifier.padding(0.dp, 4.dp)
            )
        }

        Row {
            val checkedState =
                remember { mutableStateOf(utils.getClearAppCacheDisabled("com.UCMobile")) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setClearAppCacheDisabled("com.UCMobile", checked)
                })
        }
    }
}

@Composable
fun ApiDeviceManagerForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用设备管理", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.deviceManagerDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.deviceManagerDisabled = checked
                })
        }
    }
}

@Composable
fun ApiOtaForArgos(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用OTA", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isSWUpdateLocked) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.lockSWUpdate(checked)
                })
        }
    }
}
