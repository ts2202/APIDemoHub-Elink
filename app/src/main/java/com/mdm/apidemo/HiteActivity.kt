package com.mdm.apidemo

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.hite.mdm.MdmApi
//import com.hite.mdm.MdmApiImpl
import com.mdm.apidemo.ui.theme.APIDemoHubTheme
import java.util.*

class HiteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val utils = MdmApiImpl(baseContext)
        super.onCreate(savedInstanceState)/*
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val scrollState = rememberScrollState()
                    Column(Modifier.verticalScroll(scrollState)) {
                        Text(
                                text = "forHite ver:2021-7-16",
                                Modifier.padding(8.dp, 4.dp),
                                fontSize = 12.sp
                        )


                        Row {
                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiAppWhitelistHt(utils)
                                        ApiWebWhitelistHt(utils)
                                        ApiAppBlacklistHt(utils)
                                        ApiWebBlacklistHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableAppHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiStatusBarHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiExternalStorageHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiKillAppHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiBootHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiForgetWIFIHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDefaultLauncherHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                            }
////////////////////////////////////////////////////////////////////////////////////////////////////
                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiUsbDataHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiInstallWhitelistHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiBlacklistHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiSilentInstallHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiCameraHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiPowerKeyShortHt(utils)
                                        ApiVolumnKeyHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiBluetoothHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiTimeHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiUnkillableWhitelistHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiSNHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiClearAppHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiColorTempHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                            }
                        }
                    }
                }
            }
        }*/
    }
}

// app manage
/*
@Composable
fun ApiDisableAppHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用/停用应用✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.android.phone,com.android.email")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Row {
            Button(
                    onClick = {
                        txtPackage.text
                        utils.mdmApplication(txtPackage.text.split(","), true)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "启用应用") }
            Button(
                    onClick = {
                        utils.mdmApplication(txtPackage.text.split(","), false)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "停用应用") }
        }
        Button(
                onClick = {
                    utils.removeAllAppBlackList()
                },
                Modifier
                        .padding(8.dp, 4.dp)
        ) { Text(text = "应用禁用名单全部删除") }

    }
}

@Composable
fun ApiBlacklistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }

        TextButton(onClick = {
//            utils.getAllappWhiteList()
            txtList = TextFieldValue((utils as MdmApiImpl).disabledApplications ?: "已停用")
        }) { Text(text = "应用黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        val packageNames = ArrayList<String>()
                        packageNames.addAll(txtPackage.text.split(","))
                        utils.setAppBlackListWrite(packageNames)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "设置黑名单") }
            Button(
                    onClick = {
//                    (utils as MdmApiImpl).disableInstallWhitelist()
                        utils.setAppBlackListWrite(null)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "停用黑名单") }
        }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("黑名单列表(包名 英文逗号分隔)") })

//        Row {
//            Button(
//                onClick = {
//                    utils.insertInstallWhitelist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeInstallWhitelist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "移除白名单") }
//        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiSilentInstallHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "静默安装/卸载", Modifier.padding(0.dp, 4.dp)) }

        var textPath by remember { mutableStateOf(TextFieldValue("/storage/emulated/0/uc.apk")) }
        TextField(value = textPath, onValueChange = {
            textPath = it
        }, label = { Text("路径") })

        Button(onClick = {
            utils.silentInstall(textPath.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "安装") }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.silentUnInstall(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "卸载") }
    }
}

@Composable
fun ApiInstallWhitelistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }

        TextButton(onClick = {
//            utils.getAllappWhiteList()
            txtList = TextFieldValue((utils as MdmApiImpl).queryInstallWhitelist() ?: "已停用")
        }) { Text(text = "应用安装白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        val packageNames = ArrayList<String>()
                        packageNames.addAll(txtPackage.text.split(","))
                        utils.setAppWhiteListWrite(packageNames)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "设置白名单") }
            Button(
                    onClick = {
//                    (utils as MdmApiImpl).disableInstallWhitelist()
                        utils.setAppWhiteListWrite(null)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "停用白名单") }
        }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

//        Row {
//            Button(
//                onClick = {
//                    utils.insertInstallWhitelist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeInstallWhitelist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "移除白名单") }
//        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiUnkillableWhitelistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextButton(onClick = {
            txtList = TextFieldValue((utils as MdmApiImpl).queryUnkillableWhitelist() ?: "已停用")
        }) { Text(text = "应用禁杀白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
//            Button(
//                onClick = {
//                    utils.enableUnkillableWhitelist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用白名单") }
            Button(
                    onClick = {
                        utils.addBackgroundProtectionWhiteList(null)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "停用白名单") }
        }

        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                    onClick = {
                        utils.addBackgroundProtectionWhiteList(txtPackage.text)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeUnkillableWhitelist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "移除白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiKillAppHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "杀进程✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.mdm.apidemo")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.killApplication(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "杀进程") }
    }
}

// firewall manage

@Composable
fun ApiWebWhitelistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue((utils as MdmApiImpl).queryWebWhitelist() ?: "已停用")
        }) { Text(text = "网络白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        utils.setFirewallType(true)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                    onClick = {
                        utils.setFirewallType(false)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true),
                    colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color.White
                    )
            ) {
                Text(text = "停用防火墙")
            }
        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("google.cn,192.168.2.0/24")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("白名单列表(域名/IP 英文逗号分隔)") })

        Row {
            Button(
                    onClick = {
                        val whiteLists = ArrayList<String>()
                        whiteLists.addAll(txtPackage.text.split(","))
                        utils.addNetworkAccessWhitelist(whiteLists)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "添加网络访问白名单") }
            Button(
                    onClick = {
                        utils.removeAllNetWhiteList()
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "移除所有网络白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiAppWhitelistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue((utils as MdmApiImpl).queryAppWhitelist() ?: "已停用")
        }) { Text(text = "APP白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        utils.setFirewallType(true)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                    onClick = {
                        utils.setFirewallType(false)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true),
                    colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red,
                            contentColor = Color.White
                    )
            ) {
                Text(text = "停用防火墙")
            }
        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("org.lineageos.jelly")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                    onClick = {
                        utils.addNetworkAccessAppWhitelist(txtPackage.text)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeAllNetWhiteList()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "移除所有白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiWebBlacklistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue((utils as MdmApiImpl).queryWebBlacklist() ?: "已停用")
        }) { Text(text = "网络黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
////                    utils.enableWebBlacklist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用黑名单") }
//            Button(
//                onClick = {
////                    utils.disableWebBlacklist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) {
//                Text(text = "停用黑名单")
//            }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("google.com,192.168.3.0/24")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("黑名单列表(域名/IP 英文逗号分隔)") })

        Row {
            Button(
                    onClick = {
                        val blackLists = ArrayList<String>()
                        blackLists.addAll(txtPackage.text.split(","))
                        utils.addNetworkAccessBlacklist(blackLists)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "添加网络访问黑名单") }
            Button(
                    onClick = {
                        utils.removeAllNetBlackList()
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "移除所有网络黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiAppBlacklistHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue((utils as MdmApiImpl).queryAppBlacklist() ?: "已停用")
        }) { Text(text = "APP黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
////                    utils.enableAppBlacklist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用黑名单") }
//            Button(
//                onClick = {
////                    utils.disableAppBlacklist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用黑名单") }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("org.lineageos.jelly")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
                Modifier
                        .padding(8.dp, 4.dp)
                        .fillMaxWidth(), label = { Text("黑名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                    onClick = {
                        val blackLists = ArrayList<String>()
                        blackLists.addAll(txtPackage.text.split(","))
                        utils.addNetworkAccessAppBlacklist(blackLists)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
            Button(
                    onClick = {
//                    utils.removeAppBlacklist(txtPackage.text)
                        (utils as MdmApiImpl).removeAppBlacklist(utils.queryAppBlacklist())
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "移除所有APP黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

// factory reset

@Composable
fun ApiClearAppHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { (utils as MdmApiImpl).customSystemSettings(false) }) {
            Text(
                    text = "清应用缓存",
                    Modifier.padding(0.dp, 4.dp)
            )
        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.microsoft.launcher")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.clearPackageCache(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "清除应用缓存") }
    }
}

// key manage

@Composable
fun ApiVolumnKeyHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用音量键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmVolumeKey(checked)
                    }
            )
        }
    }
}

@Composable
fun ApiPowerKeyShortHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用短按电源键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmShortPressPower(checked)
                    }
            )
        }
    }
}

// wire manage

@Composable
fun ApiUsbDataHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用USB✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmUsb(checked)
                    }
            )
        }
    }
}

// wireless manage



@Composable
fun ApiForgetWIFIHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "清除WIFI✅", Modifier.padding(0.dp, 4.dp)) }

        var txtSSID by remember { mutableStateOf(TextFieldValue("liaobz")) }
        TextField(value = txtSSID, onValueChange = {
            txtSSID = it
        }, label = { Text("ssid") })

        Button(onClick = {
            utils.removeWifiConfiguration(txtSSID.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "清除WIFI") }
    }
}

@Composable
fun ApiBluetoothHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) {
            Text(text = "启用蓝牙", Modifier.padding(0.dp, 4.dp))
        }

        Row {
            val checkedState = remember { mutableStateOf(true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmBluetooth(checked)
                    }
            )
        }
    }
}

// hardware manage

@Composable
fun ApiExternalStorageHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用外部存储", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(/*utils.sdCardDisabled*/false) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmExternalStorageDisabled(checked)
                    },
                    colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.Red
                    )
            )
        }
    }
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅显示内部存储", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(/*utils.sdCardDisabled*/true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmSdCard(checked)
                    }
            )
        }
    }
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅启用OTG", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(/*utils.sdCardDisabled*/true) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmOTG(checked)
                    }
            )
        }
    }
}

@Composable
fun ApiCameraHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅启用相机", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(!utils.isCameraDisable) }
            Switch(
                    checked = checkedState.value,
                    onCheckedChange = { checked ->
                        checkedState.value = checked
                        utils.mdmCamera(checked)
                    }
            )

            /*val checkedStateMain = remember { mutableStateOf(utils.mainCameraDisabled) }
            TextButton(onClick = {}) { Text(text = "后摄", Modifier.padding(0.dp, 4.dp)) }
            Switch(
                checked = checkedStateMain.value,
                onCheckedChange = { checked ->
                    checkedStateMain.value = checked
                    utils.mainCameraDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )

            val checkedStateSub = remember { mutableStateOf(utils.subCameraDisabled) }
            TextButton(onClick = {}) { Text(text = "前摄", Modifier.padding(0.dp, 4.dp)) }
            Switch(
                checked = checkedStateSub.value,
                onCheckedChange = { checked ->
                    checkedStateSub.value = checked
                    utils.subCameraDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )*/
        }
    }
}

// system setting manage

@Composable
fun ApiColorTempHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        val stepMode = remember { mutableStateOf("") }
        TextButton(onClick = {}) { Text(text = "色温", Modifier.padding(0.dp, 4.dp)) }
        Text(text = "" + stepMode.value, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)

        val step = remember { mutableStateOf(0f) }
        Slider(value = step.value, onValueChange = {
            step.value = it
        }, steps = 3, onValueChangeFinished = {
            when ((step.value * 4).toInt()) {
                0 -> {
                    (utils as MdmApiImpl).setColorTempNormal()
                    stepMode.value = "标准模式"
                }
                1 -> {
                    (utils as MdmApiImpl).setColorTempEnhance()
                    stepMode.value = "屏幕增强"
                }
                2 -> {
                    (utils as MdmApiImpl).setColorTempIntelligentNature()
                    stepMode.value = "智能环境适应 自然"
                }
                3 -> {
                    (utils as MdmApiImpl).setColorTempIntelligentWarm()
                    stepMode.value = "智能环境适应 暖色"
                }
                4 -> {
                    (utils as MdmApiImpl).setColorTempIntelligentCold()
                    stepMode.value = "智能环境适应 冷色"
                }
            }
        })


        val stepValue = remember { mutableStateOf("") }
        TextButton(onClick = {}) { Text(text = "护眼模式强度", Modifier.padding(0.dp, 4.dp)) }
        Text(text = "" + stepValue.value, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)

        val progress = remember { mutableStateOf(0f) }
        Slider(value = progress.value, onValueChange = {
            progress.value = it
        }, steps = 189, onValueChangeFinished = {
            utils.setColorTemperatureAdjustmentEnabled(true, ((progress.value) * 190).toInt() + 1)
            stepValue.value = "" + (((progress.value) * 190).toInt())

            Log.e("a", "i:" + (((progress.value) * 190).toInt()))
        })

        Button(onClick = {
            utils.setColorTemperatureAdjustmentEnabled(false, 8)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "关闭护眼模式") }

//        var i = 0;
//        while (i<191){
//            Log.e("a", "i:"+i+"->"+ ((i*64/191)+1) )
//            i++;
//        }
    }
}

@Composable
fun ApiTimeHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "系统时间✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        utils.modifySystemTime(System.currentTimeMillis() - 60 * 1000)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "系统时间减一分钟") }
            Button(
                    onClick = {
                        utils.modifySystemTime(System.currentTimeMillis() + 60 * 1000)
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "系统时间加一分钟") }
        }
    }
}

@Composable
fun ApiDefaultLauncherHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "默认桌面✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.microsoft.launcher/com.microsoft.launcher.Launcher")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名/类名") })

        Button(onClick = {
            utils.setDefaultLauncher(
                    txtPackage.text.split("/")[0],
                    txtPackage.text.split("/")[1]
            )
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置默认桌面") }
        Button(onClick = {
            utils.clearDefaultLauncher()
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "重置默认桌面") }
    }
}

// Shell


@Composable
fun ApiBootHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "关机/重启✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                    onClick = {
                        utils.shutDown()
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "关机") }
            Button(
                    onClick = {
                        utils.reboot()
                    },
                    Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
            ) { Text(text = "重启") }
        }

//        Row {
//            Button(
//                onClick = {
//                    utils.rebootDevice("recovery")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "重启到Recovery") }
//            Button(
//                onClick = {
//                    utils.rebootDevice("bootloader")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "重启到BootLoader") }
//        }
    }
}

// SystemUI


@Composable
fun ApiStatusBarHt(utils: MdmApi) {

    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "状态栏和导航栏✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedStateStatusbar = remember { mutableStateOf(false) }
            val checkedStateNavbar = remember { mutableStateOf(true) }
            Checkbox(
                    checked = checkedStateStatusbar.value,
                    onCheckedChange = { checked ->
                        checkedStateStatusbar.value = checked
                        utils.mdmTaskBar(checked)
                    },
                    Modifier.weight(1.0f, true),
//                enabled = false
            )
            Checkbox(
                    checked = checkedStateNavbar.value, onCheckedChange = { checked ->
                checkedStateNavbar.value = checked
                utils.mdmNavigationBar(checked)
            }, Modifier.weight(1.0f, true)
            )
        }
        Row {
            Text(
                    text = "显示通知栏",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                    text = "显示导航栏",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
        }

        Divider()

        Row {
            val checkedStateBack = remember { mutableStateOf(true) }
            val checkedStateHome = remember { mutableStateOf(true) }
            val checkedStateRecent = remember { mutableStateOf(true) }
            val checkedStateExpand = remember { mutableStateOf(true) }
            /*val checkedStateNotify = remember { mutableStateOf(false) }*/
            Checkbox(
                    checked = checkedStateBack.value, onCheckedChange = { checked ->
                checkedStateBack.value = checked
                utils.mdmBackKey(checked)
            }, Modifier.weight(1.0f, true)
            )
            Checkbox(
                    checked = checkedStateHome.value, onCheckedChange = { checked ->
                checkedStateHome.value = checked
                utils.mdmHomeKey(checked)
            }, Modifier.weight(1.0f, true)
            )
            Checkbox(
                    checked = checkedStateRecent.value, onCheckedChange = { checked ->
                checkedStateRecent.value = checked
                utils.mdmRecentKey(checked)
            }, Modifier.weight(1.0f, true)
            )
            Checkbox(
                    checked = checkedStateExpand.value, onCheckedChange = { checked ->
                checkedStateExpand.value = checked
                utils.mdmStatusbar(checked)
            }, Modifier.weight(1.0f, true)
            )
            /*Checkbox(
                checked = checkedStateNotify.value, onCheckedChange = { checked ->
                    checkedStateNotify.value = checked
                    (utils as MdmApiImpl).setStatusBarExpandDisabled(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )*/
        }
        Row {
            Text(
                    text = "显示返回键",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                    text = "显示主页键",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                    text = "显示任务键",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                    text = "启用通知栏下拉",
                    Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            /*Text(
                text = "禁用通知图标",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )*/
        }

    }

}

@Composable
fun ApiSNHt(utils: MdmApi) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = { (utils as MdmApiImpl).customSystemSettings(true) }) {
            Text(
                    text = "版本号",
                    Modifier.padding(0.dp, 4.dp)
            )
        }

        Text(text = "ROM Version:\n" + utils.romVersion, Modifier.padding(8.dp, 4.dp))
    }
}
*/