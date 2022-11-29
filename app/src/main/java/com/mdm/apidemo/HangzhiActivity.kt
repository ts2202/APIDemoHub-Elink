package com.mdm.apidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdm.apidemo.ui.theme.APIDemoHubTheme
//import demo.elinktek.common.
//import cloudcontrol.systeminterface.Utils


class HangzhiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val utils = Utils(baseContext)
        super.onCreate(savedInstanceState)/*
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val scrollState = rememberScrollState()
                    Column(Modifier.verticalScroll(scrollState)) {
//                        Row {
//                            Column(Modifier.weight(1.0f, true)) {
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiCameraHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiExternalStorageHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Row {
//                                        ApiAdbHZQ(utils)
//                                        ApiUsbDataHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiInstallWhitelistHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    ApiUnkillableWhitelistHZQ(utils)
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiPermissionsHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiDefaultLauncherHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiStatusBarHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiScreenHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiBootHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiDefaultBrowserHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiPowerKeyHZQ(utils)
////                                        ApiPowerKeyLongHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiEyeCareHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiSNHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
////                                        ApiAppBlacklistHZQ(utils)
////                                        ApiAppWhitelistHZQ(utils)
////                                        ApiWebWhitelistHZQ(utils)
////                                        ApiUrlWhitelistHZQ(utils)
////                                        ApiIpWhitelistHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                            }
//////////////////////////////////////////////////////////////////////////////////////////////////////
//                            Column(Modifier.weight(1.0f, true)) {
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiBluetoothHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiGPSHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Row {
//                                        ApiSMSHZQ(utils)
//                                        ApiIncallHZQ(utils)
//                                        ApiOutcallHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiDisableAppHZQ(utils)
//                                        ApiKillAppHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiSilentInstallHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiWIFIBlacklistHZQ(utils)
//                                        ApiForgetWIFIHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiScreenshotHZQ(utils, this@HangzhiActivity)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiDefaultInputMethodHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiTimeHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiVolumnKeyHZQ(utils)
//                                    }
//                                }
//                                Spacer(Modifier.height(4.dp))
//                                Spacer(Modifier.height(4.dp))
//                                Card(Modifier.fillMaxWidth(0.99f)) {
//                                    Column {
//                                        ApiFontSizeHZQ(utils)
//                                    }
//                                }
//                            }
//                        }
                    }
                }
            }
        }*/
    }
}
/*
// app manage

@Composable
fun ApiDisableAppHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用/停用应用✅", Modifier.padding(0.dp, 4.dp)) }

//        var txtPackage by remember { mutableStateOf(TextFieldValue("com.android.browser")) }
//        TextField(value = txtPackage, onValueChange = {
//            txtPackage = it
//        }, label = { Text("包名") })
//
//        Row {
//            Button(
//                onClick = {
//                    utils.setApplicationDisabled(txtPackage.text, false)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用应用") }
//            Button(
//                onClick = {
//                    utils.setApplicationDisabled(txtPackage.text, true)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用应用") }
//        }

    }
}

@Composable
fun ApiSilentInstallHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "静默安装✅/卸载✅", Modifier.padding(0.dp, 4.dp)) }

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
            utils.silentUninstall(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "卸载") }
    }
}
@Composable
fun ApiInstallWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(
                utils.installPackageWhiteList.joinToString(separator = ",")
            )
        }) { Text(text = "应用安装白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
//                    utils.enableInstallWhitelist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用白名单") }
//            Button(
//                onClick = {
//                    utils.disableInstallWhitelist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用白名单") }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.installPackageWhiteList = txtPackage.text.split(",")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeInstallWhitelist(txtPackage.text)
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
fun ApiUninstallWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryUninstallWhitelist() ?: "已停用")
        }) { Text(text = "应用卸载白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
//                    utils.enableUninstallWhitelist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用白名单") }
//            Button(
//                onClick = {
//                    utils.disableUninstallWhitelist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用白名单") }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.insertUninstallWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
//            Button(
//                onClick = {
//                    utils.removeUninstallWhitelist(txtPackage.text)
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
fun ApiUnkillableWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList =
                TextFieldValue(utils.keepAliveList.joinToString(separator = ","))
        }) { Text(text = "应用禁杀白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
//                    utils.enableUnkillableWhitelist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用白名单") }
//            Button(
//                onClick = {
//                    utils.disableUnkillableWhitelist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用白名单") }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.keepAliveList = txtPackage.text.split(",")
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
fun ApiKillAppHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "杀进程✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.example.apidemo")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.killApplicationProcess(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "杀进程") }
    }
}

// firewall manage

@Composable
fun ApiWebWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryWebWhitelist() ?: "已停用")
        }) { Text(text = "网络白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableWebWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableFirewall()
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
                    utils.insertWebWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeWebWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiUrlWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryUrlWhitelist() ?: "已停用")
        }) { Text(text = "域名白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableUrlWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableFirewall()
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

        var txtPackage by remember { mutableStateOf(TextFieldValue("google.cn,baidu")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(域名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.insertUrlWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeUrlWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiIpWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryIpWhitelist() ?: "已停用")
        }) { Text(text = "IP白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableIpWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableFirewall()
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

        var txtPackage by remember { mutableStateOf(TextFieldValue("192.168.2.0/24,192.168.1.1")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("白名单列表(IP 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.insertIpWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeIpWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiAppWhitelistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryAppWhitelist() ?: "已停用")
        }) { Text(text = "APP白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableAppWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableFirewall()
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
                    utils.insertAppWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeAppWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除白名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiAppBlacklistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryAppBlacklist() ?: "已停用")
        }) { Text(text = "APP黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableAppBlacklist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用黑名单") }
            Button(
                onClick = {
                    utils.disableAppBlacklist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用黑名单") }
        }

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
                    utils.insertAppBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
            Button(
                onClick = {
                    utils.removeAppBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

// factory reset

@Composable
fun ApiDeviceNameHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
//        TextButton(onClick = {}) { Text(text = "设备名称✅", Modifier.padding(0.dp, 4.dp)) }
//
//        var txtDeviceName by remember { mutableStateOf(TextFieldValue("ElinkDevice")) }
//        TextField(value = txtDeviceName, onValueChange = {
//            txtDeviceName = it
//        }, label = { Text("设备名称") })
//
//        Button(onClick = {
//            utils.setDeviceName(txtDeviceName.text)
//        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置设备名称") }
    }
}

// key manage

@Composable
fun ApiVolumnKeyHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用音量键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isVolumeButtonDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isVolumeButtonDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}


@Composable
fun ApiPowerKeyHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用电源键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isPowerButtonDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isPowerButtonDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiPowerKeyLongHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用长按电源键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.powerKeyLongDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.powerKeyLongDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiScreenHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "亮屏/灭屏✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.turnOffScreen()
                    Thread.sleep(3000)
                    utils.turnOnScreen()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "灭屏，3秒后亮屏") }
        }

    }
}

// wire manage

@Composable
fun ApiAdbHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用USB调试✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isAdbTurnOn) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.turnOnAdb(checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiUsbDataHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用USB数据✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isUSBDataDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isUSBDataDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

// wireless manage

@Composable
fun ApiWIFIBlacklistHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList =
                TextFieldValue(utils.wiFiBlackList.joinToString(separator = ","))
        }) { Text(text = "WIFI黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

//        Row {
//            Button(
//                onClick = {
//                    utils.enableWIFIBlacklist("")
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "启用黑名单") }
//            Button(
//                onClick = {
//                    utils.disableWIFIBlacklist()
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "停用黑名单") }
//        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("liaobz,11Bwifitest2.4G")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(ssid 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.wiFiBlackList = txtPackage.text.split(",")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
//            Button(
//                onClick = {
//                    utils.removeWIFIBlacklist(txtPackage.text)
//                },
//                Modifier
//                    .padding(8.dp, 4.dp)
//                    .weight(1.0f, true)
//            ) { Text(text = "移除黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiForgetWIFIHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "清除WIFI✅", Modifier.padding(0.dp, 4.dp)) }

        var txtSSID by remember { mutableStateOf(TextFieldValue("liaobz")) }
        TextField(value = txtSSID, onValueChange = {
            txtSSID = it
        }, label = { Text("ssid") })

        Button(onClick = {
            utils.removeWiFiConfiguration(txtSSID.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "清除WIFI") }
    }
}

@Composable
fun ApiBluetoothHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) {
            Text(
                text = "禁用蓝牙",
                Modifier.padding(0.dp, 4.dp)
            )
        }

        Row {
            val checkedState = remember { mutableStateOf(utils.isBluetoothDataTransferDisable) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isBluetoothDataTransferDisable = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiGPSHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用GPS定位✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isGpsOn) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.turnOnGPS(checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiSMSHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用短信✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isSMSDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isSMSDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiIncallHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用来电✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isVoiceIncomingDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isVoiceIncomingDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiOutcallHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用去电✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isVoiceOutgoingDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setVoiceOutgoingDisable(checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

// hardware manage

@Composable
fun ApiExternalStorageHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用TF/SD/OTG", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isExternalStorageDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isExternalStorageDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiCameraHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用相机", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isCameraDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.isCameraDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
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
fun ApiAlwaysBtnHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用Always(始终)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(false) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.setSelectDialogWhenOpeningFileDisabled(checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiEyeCareHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "护眼模式✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.isEyeComfortTurnedOn) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.turnOnEyeComfort(checked)
                }
            )
        }
    }
}

@Composable
fun ApiTimeHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "系统时间✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.setSysTime(System.currentTimeMillis() - 60 * 1000)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "系统时间减一分钟") }
            Button(
                onClick = {
                    utils.setSysTime(System.currentTimeMillis() + 60 * 1000)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "系统时间加一分钟") }
        }
    }
}

@Composable
fun ApiFontSizeHZQ(utils: Utils) {

    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "字体大小✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val options = listOf("小", "默认", "大", "最大")
            val (selectedOption, onOptionSelected) = remember {
                mutableStateOf(
                    when (utils.fontSize) {
                        0 -> "小"
                        1 -> "默认"
                        2 -> "大"
                        3 -> "最大"
                        else -> "默认"
                    }
                )
            }
            options.forEach { text ->
                Column(modifier = Modifier.weight(1.0f, true)) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            when (text) {
                                "小" -> utils.fontSize = 0
                                "默认" -> utils.fontSize = 1
                                "大" -> utils.fontSize = 2
                                "最大" -> utils.fontSize = 3
                            }
                            onOptionSelected(text)
                        })
                    Text(text = text, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }

        }

    }
}

@Composable
fun ApiDefaultInputMethodHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "默认输入法✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.google.android.inputmethod.pinyin/.PinyinIME")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名/类名") })

        Button(onClick = {
//            utils.setDefaultLauncher(txtPackage.text.split("/")[0], txtPackage.text.split("/")[1])
            utils.setDefaultInputMethod(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置输入法") }
    }
}

@Composable
fun ApiDefaultLauncherHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "默认桌面✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.microsoft.launcher/com.microsoft.launcher.Launcher")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名/类名") })

        Button(onClick = {
            utils.setDefaultBrowser(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置默认桌面") }
    }
}

@Composable
fun ApiDefaultBrowserHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "默认浏览器✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.setDefaultBrowser(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置默认浏览器") }
    }
}


@Composable
fun ApiPermissionsHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "特殊应用权限✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.google.android.inputmethod.pinyin")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })
        Button(onClick = {
            utils.allowAppPermission(txtPackage.text)
        }, Modifier.padding(8.dp, 4.dp)) { Text(text = "授予所有运行时权限") }

        Row {
            Button(
                onClick = {
                    utils.allowAppDisplayOverOtherAppsPermission(txtPackage.text/*, true*/)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许显示在其他应用上层") }
            Button(
                onClick = {
                    utils.allowAppModifySystemSettingsPermission(txtPackage.text/*, true*/)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许修改系统设置") }
        }

        Row {
            Button(
                onClick = {
                    utils.allowAppInstallPermission(txtPackage.text/*, true*/)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许安装未知应用") }
            Button(
                onClick = {
                    utils.allowAccessUsageDetails(txtPackage.text/*, true*/)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许使用情况访问权限") }
        }
    }
}

// Shell

@Composable
fun ApiScreenshotHZQ(utils: Utils, activity: ComponentActivity) {
    CheckPermission(activity)
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "截屏✅", Modifier.padding(0.dp, 4.dp)) }

        val initAsset = painterResource(id = R.drawable.nipaste20200908143757)
        val (asset, setAsset) = remember { mutableStateOf(initAsset) }
        Button(onClick = {
            setAsset(
                BitmapPainter(utils.captureScreen().asImageBitmap())
            )
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "截取当前屏幕") }

        Image(
            asset,
            contentDescription = "",
            Modifier
                .clip(RoundedCornerShape(2))
                .border(1.dp, Color.LightGray, RoundedCornerShape(2))
        )
    }
}

@Composable
fun ApiBootHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "关机/重启✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.shutdown()
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
fun ApiStatusBarHZQ(utils: Utils) {

    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "状态栏和导航栏✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
//            val checkedStateStatusBar = remember { mutableStateOf(false) }
            val checkedStateNavBar = remember { mutableStateOf(false) }
//            Checkbox(
//                checked = checkedStateStatusBar.value, onCheckedChange = { checked ->
//                    checkedStateStatusBar.value = checked
//                    utils.setStatusBarDisabled(checked)
//                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
//                    checkedColor = Color.Red
//                )
//            )
            Checkbox(
                checked = checkedStateNavBar.value, onCheckedChange = { checked ->
                    checkedStateNavBar.value = checked
                    utils.hideBottomNavigationBar(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
        }
        Row {
//            Text(
//                text = "隐藏通知栏",
//                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
//            )
            Text(
                text = "隐藏导航栏⛔",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
        }

        Divider()

        Row {
            val checkedStateBack = remember { mutableStateOf(false) }
            val checkedStateHome = remember { mutableStateOf(false) }
            val checkedStateRecent = remember { mutableStateOf(false) }
//            val checkedStateNotify = remember { mutableStateOf(false) }
//            val checkedStateExpand = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedStateBack.value, onCheckedChange = { checked ->
                    checkedStateBack.value = checked
                    utils.hideBackButton(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateHome.value, onCheckedChange = { checked ->
                    checkedStateHome.value = checked
                    utils.hideHomeButton(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateRecent.value, onCheckedChange = { checked ->
                    checkedStateRecent.value = checked
                    utils.hideTaskButton(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
//            Checkbox(
//                checked = checkedStateNotify.value, onCheckedChange = { checked ->
//                    checkedStateNotify.value = checked
//                    utils.setStatusBarExpandDisabled(checked)
//                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
//                    checkedColor = Color.Red
//                )
//            )
//            Checkbox(
//                checked = checkedStateExpand.value, onCheckedChange = { checked ->
//                    checkedStateExpand.value = checked
//                    utils.setStatusBarNotifyDisabled(checked)
//                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
//                    checkedColor = Color.Red
//                )
//            )
        }
        Row {
            Text(
                text = "禁用返回键",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "禁用主页键",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "禁用任务键",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
//            Text(
//                text = "禁用通知栏下拉",
//                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
//            )
//            Text(
//                text = "禁用通知图标",
//                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
//            )
        }
    }
}

@Composable
fun ApiSNHZQ(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅读取各种SN", Modifier.padding(0.dp, 4.dp)) }

//        Text(text = "SN:\n" + utils.sn, Modifier.padding(8.dp, 4.dp))
//        Text(text = "WIFI MAC:\n" + utils.wifimac, Modifier.padding(8.dp, 4.dp))
//        Text(text = "BT MAC:\n" + utils.btmac, Modifier.padding(8.dp, 4.dp))
//        Text(text = "IMEI:\n" + utils.imei, Modifier.padding(8.dp, 4.dp))
    }
}
*/