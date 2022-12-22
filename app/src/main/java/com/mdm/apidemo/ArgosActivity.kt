package com.mdm.apidemo

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.solver.LinearSystem.DEBUG
import com.mdm.apidemo.ui.theme.APIDemoHubTheme
import com.mdm.forargos.UtilsImpl


class ArgosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val utils = UtilsImpl(baseContext)

        super.onCreate(savedInstanceState)
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val scrollState = rememberScrollState()
                    Column(Modifier.verticalScroll(scrollState)) {
                        Row {
                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiAppBlacklist(utils)
                                        ApiAppWhitelist(utils)
                                        ApiWebWhitelist(utils)
                                        ApiUrlWhitelist(utils)
                                        ApiIpWhitelist(utils)
                                    }
                                }
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
                                        ApiDisableApp(utils)
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
        TextButton(onClick = { }) {Text(text = "GPS强制启用", Modifier.padding(0.dp, 4.dp))}
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
        TextButton(onClick = { }) {Text(text = "网络位置强制打开", Modifier.padding(0.dp, 4.dp))}
        Row {
            val checkedState = remember { mutableStateOf(utils.getGPSOn()) }
            Log.d("rrr","sssss"+checkedState)
            Switch(checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setGPSOn(checked)
                    Log.d("rrr","tttt"+checked)
                })
        }
    }
}
@Composable
//add by elink_ts 屏幕截图强制禁用  无效
fun ApiScreenShot(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) {Text(text = "禁用屏幕截图", Modifier.padding(0.dp, 4.dp))}
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
fun ApiDisableMultiUser(utils: UtilsImpl) {
//    jetpack compose
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { }) {Text(text = "禁用多用户", Modifier.padding(0.dp, 4.dp))}

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
