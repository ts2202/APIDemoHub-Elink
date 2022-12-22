package com.mdm.apidemo

import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.SyncStateContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
import androidx.core.app.ActivityCompat
import com.api.forelink.Utils
import com.mdm.forargos.UtilsImpl
import com.mdm.apidemo.ui.theme.*
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun CheckPermission(activity: ComponentActivity) {

    if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(
            activity.baseContext, "android.permission.WRITE_EXTERNAL_STORAGE"
        )
    )
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
            ), 1
        )
}


// app manage

@Composable
fun ApiDisableApp(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "启用/禁用应用✅", Modifier.padding(0.dp, 4.dp)) }

        val disableBtnColor = ButtonDefaults.buttonColors(
            backgroundColor = backgrdRed,
            contentColor = contentRed
        )
        val enableBtnColor = ButtonDefaults.buttonColors(
            backgroundColor = backgrdGreen,
            contentColor = contentGreen
        )

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.android.browser")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Row {
            Button(
                onClick = {
                    utils.setApplicationDisabled(txtPackage.text, false)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用应用") }
            Button(
                onClick = {
                    utils.setApplicationDisabled(txtPackage.text, true)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "禁用应用") }
        }
        Spacer(Modifier.height(2.dp))
        TextButton(onClick = {}) { Text(text = "禁用应用包名", Modifier.padding(0.dp, 4.dp)) }
        Row {
            //add by elink_ts 查询禁用包名
            var disablePackage by remember { mutableStateOf(TextFieldValue(txtPackage.text)) }
            TextField(value = disablePackage, onValueChange = {
                disablePackage = it
            }, label = { Text("包名") })
        }
        Spacer(Modifier.height(30.dp))
        TextButton(onClick = {}) { Text(text = "强制启动/关闭应用包名", Modifier.padding(0.dp, 4.dp)) }
        Row {
            //add by elink_ts 强制启动应用包名
            var disablePackage by remember { mutableStateOf(TextFieldValue(txtPackage.text)) }
            TextField(value = disablePackage, onValueChange = {
                disablePackage = it
            }, label = { Text("包名") })
        }
        Row {
            Button(
                onClick = {
                    utils.setApplicationDisabled(txtPackage.text, false)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用应用") }
            Button(
                onClick = {
                    utils.setApplicationDisabled(txtPackage.text, true)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "关闭应用") }
        }
        Spacer(Modifier.height(30.dp))
        Row(Modifier.fillMaxWidth(0.99f)) {
/*            //var tt=Text(text = "状态：已禁用");
            val marketState = remember {
                mutableStateOf( if (utils.getApplicationDisabled("com.android.vending")) "应用商城状态：已禁用" else "应用商城状态：已启用" )
            }
            Column() {
                Row() {
                    TextButton(onClick = {}) {Text(text = marketState.value , Modifier.padding(0.dp, 4.dp))}
                }

                Row {
                    // 声明式UI
                    Button(
                        onClick = {
                            utils.setApplicationDisabled("com.android.vending", false)
                            marketState.value = "应用商城状态：已启用"
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "启用应用商城") }
                    Button(
                        onClick = {
                            utils.setApplicationDisabled("com.android.vending", true)
                            marketState.value = "应用商城状态：已禁用"
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "禁用应用商城") }
                }
            }*/
            //var tt=Text(text = "状态：已禁用");
/*            var marketState = remember {
               mutableStateOf( if (utils.getApplicationDisabled("com.android.vending")) "应用商城状态：已禁用" else "应用商城状态：已启用" )
            }*/
            val checkedState = remember {
                mutableStateOf(utils.getApplicationDisabled("com.android.vending"))
            }


            Column() {
                Row {
                    if (checkedState.value) {
                        TextButton(onClick = {}, Modifier, colors = disableBtnColor) {
                            Text(text = "应用商城状态：已禁用", Modifier.padding(0.dp, 4.dp))
                        }
                    } else {
                        TextButton(onClick = {}, Modifier, colors = enableBtnColor) {
                            Text(text = "应用商城状态：已启用", Modifier.padding(0.dp, 4.dp))
                        }
                    }
                }
                Row {
                    // 声明式UI
                    Button(
                        onClick = {
                            utils.setApplicationDisabled("com.android.vending", false)
                            //marketState = "应用商城状态：已启用"
                            checkedState.value = false;
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "启用应用商城") }
                    Button(
                        onClick = {
                            utils.setApplicationDisabled("com.android.vending", true)
                            //marketState = "应用商城状态：已禁用"
                            checkedState.value = true;
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "禁用应用商城") }
                }
            }
        }
        Spacer(Modifier.height(30.dp))
        Row(Modifier.fillMaxWidth(0.99f)) {
            val checkedState = remember {
                mutableStateOf(utils.getApplicationDisabled("com.android.chrome"))
            }
            Column {
                Row {
                    if (checkedState.value) {
                        TextButton(onClick = {}, Modifier, colors = disableBtnColor) {
                            Text(text = "浏览器状态：已禁用", Modifier.padding(0.dp, 4.dp))
                        }
                    } else {
                        TextButton(onClick = {}, Modifier, colors = enableBtnColor) {
                            Text(text = "浏览器状态：已启用", Modifier.padding(0.dp, 4.dp))
                        }
                    }
                }
                Row {
                    Button(
                        onClick = {
                            utils.setApplicationDisabled(
                                "com.android.chrome",
                                false
                            )
                            checkedState.value = false;
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "启用浏览器") }
                    Button(
                        onClick = {
                            utils.setApplicationDisabled(
                                "com.android.chrome",
                                true
                            )
                            checkedState.value = true;
                        },
                        Modifier
                            .padding(8.dp, 4.dp)
                            .weight(1.0f, true)
                    ) { Text(text = "禁用浏览器") }
                }
            }
        }
    }
}

@Composable
fun ApiSilentInstall(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "静默安装✅/卸载✅", Modifier.padding(0.dp, 4.dp)) }

        var textPath by remember { mutableStateOf(TextFieldValue("/storage/emulated/0/uc.apk")) }
        TextField(value = textPath, onValueChange = {
            textPath = it
        }, label = { Text("路径") })

        Button(onClick = {
            utils.silenceInstall(textPath.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "安装") }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.silenceUninstall(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "卸载") }
    }
}


@Composable
fun ApiInstallWhitelist(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryInstallWhitelist() ?: "已停用")
        }) { Text(text = "应用安装白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableInstallWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableInstallWhitelist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用白名单") }
        }

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
                    utils.insertInstallWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeInstallWhitelist(txtPackage.text)
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
fun ApiUninstallWhitelist(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryUninstallWhitelist() ?: "已停用")
        }) { Text(text = "应用卸载白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableUninstallWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableUninstallWhitelist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用白名单") }
        }

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
            Button(
                onClick = {
                    utils.removeUninstallWhitelist(txtPackage.text)
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
fun ApiUninstallBlacklist(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryUninstallBlacklist() ?: "已停用")
        }) { Text(text = "应用卸载黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableUninstallBlacklist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用黑名单") }
            Button(
                onClick = {
                    utils.disableUninstallBlacklist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用黑名单") }
        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.insertUninstallBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
            Button(
                onClick = {
                    utils.removeUninstallBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiUnkillableWhitelist(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryUnkillableWhitelist() ?: "已停用")
        }) { Text(text = "应用禁杀白名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableUnkillableWhitelist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用白名单") }
            Button(
                onClick = {
                    utils.disableUnkillableWhitelist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用白名单") }
        }

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
                    utils.insertUnkillableWhitelist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加白名单") }
            Button(
                onClick = {
                    utils.removeUnkillableWhitelist(txtPackage.text)
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
fun ApiRunningBlacklist(utils: Utils) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryRunningBlacklist() ?: "已停用")
        }) { Text(text = "应用运行黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableRunningBlacklist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用黑名单") }
            Button(
                onClick = {
                    utils.disableRunningBlacklist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用黑名单") }
        }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.UCMobile")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        },
            Modifier
                .padding(8.dp, 4.dp)
                .fillMaxWidth(), label = { Text("黑名单列表(包名 英文逗号分隔)") })

        Row {
            Button(
                onClick = {
                    utils.insertRunningBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
            Button(
                onClick = {
                    utils.removeRunningBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}


@Composable
fun ApiKillApp(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "杀进程✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.example.apidemo")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })

        Button(onClick = {
            utils.forceStopApp(txtPackage.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "杀进程") }
    }
}

// firewall manage

@Composable
fun ApiWebWhitelist(utils: UtilsImpl) {
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
fun ApiUrlWhitelist(utils: UtilsImpl) {
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
fun ApiIpWhitelist(utils: UtilsImpl) {
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
fun ApiAppWhitelist(utils: UtilsImpl) {
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
fun ApiAppBlacklist(utils: UtilsImpl) {
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
fun ApiFactoryReset(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用恢复出厂设置", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.factoryResetDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.factoryResetDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiClearApp(utils: UtilsImpl) {
    // Column(Modifier.padding(16.dp)) {
    //     TextButton(onClick = { (utils as MdmApiImpl).customSystemSettings(false) }) {
    //         Text(
    //                 text = "清应用缓存",
    //                 Modifier.padding(0.dp, 4.dp)
    //         )
    //     }

    //     var txtPackage by remember { mutableStateOf(TextFieldValue("com.microsoft.launcher")) }
    //     TextField(value = txtPackage, onValueChange = {
    //         txtPackage = it
    //     }, label = { Text("包名") })

    //     Button(onClick = {
    //         utils.clearPackageCache(txtPackage.text)
    //     }, Modifier.padding(0.dp, 4.dp)) { Text(text = "清除应用缓存") }
    // }
}

@Composable
fun ApiAppData(utils: UtilsImpl) {
    // Column(Modifier.padding(16.dp)) {
    //     TextButton(onClick = {}) {
    //         Text(
    //             text = "✅disabled clear storage(com.UCMobile)",
    //             Modifier.padding(0.dp, 4.dp)
    //         )
    //     }

    //     Row {
    //         val checkedState =
    //             remember { mutableStateOf(utils.getClearAppDataDisabled("com.UCMobile")) }
    //         Switch(
    //             checked = checkedState.value,
    //             onCheckedChange = { checked ->
    //                 checkedState.value = checked
    //                 utils.setClearAppDataDisabled("com.UCMobile", checked)
    //             })
    //     }
    // }
}

@Composable
fun ApiAppCache(utils: UtilsImpl) {
    // Column(Modifier.padding(16.dp)) {
    //     TextButton(onClick = {}) {
    //         Text(
    //             text = "✅disabled clear cache(com.UCMobile)",
    //             Modifier.padding(0.dp, 4.dp)
    //         )
    //     }

    //     Row {
    //         val checkedState =
    //             remember { mutableStateOf(utils.getClearAppCacheDisabled("com.UCMobile")) }
    //         Switch(
    //             checked = checkedState.value,
    //             onCheckedChange = { checked ->
    //                 checkedState.value = checked
    //                 utils.setClearAppCacheDisabled("com.UCMobile", checked)
    //             })
    //     }
    // }
}

@Composable
fun ApiDeviceName(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "设备名称✅", Modifier.padding(0.dp, 4.dp)) }

        var txtDeviceName by remember { mutableStateOf(TextFieldValue("ElinkDevice")) }
        TextField(value = txtDeviceName, onValueChange = {
            txtDeviceName = it
        }, label = { Text("设备名称") })

        Button(onClick = {
            utils.setDeviceName(txtDeviceName.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "设置设备名称") }
    }
}

// key manage

@Composable
fun ApiVolumnKey(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用音量键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.volumeKeyDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.volumeKeyDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiPowerKeyShort(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用短按电源键✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.powerKeyShortDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.powerKeyShortDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiPowerKeyLong(utils: Utils) {
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
fun ApiPowerKeyDblclk(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用双击电源键⛔", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.powerKeyDblclkDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.powerKeyDblclkDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiScreen(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "亮屏/灭屏✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.setScreenOff()
                    Thread.sleep(3000)
                    utils.setScreenOn()
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
fun ApiDevOption(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用开发者选项✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.developmentOptionsDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.developmentOptionsDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiAdb(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用USB调试✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.usbDebugDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.usbDebugDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiUsbData(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用USB数据✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.usbDataDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.usbDataDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiUsbDataMode(utils: UtilsImpl) {

    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "USB偏好设置✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val options = listOf("仅充电", "文件传输", "USB网络共享", "MIDI", "PTP")
            val (selectedOption, onOptionSelected) = remember {
                mutableStateOf(
                    "none"
//                    when (utils.usbDataMode) {
//                        1 -> "文件传输"
//                        2 -> "USB网络共享"
//                        3 -> "MIDI"
//                        4 -> "PTP"
//                        else -> "仅充电"
//                    }
                )
            }
            options.forEach { text ->
                Column(modifier = Modifier.weight(1.0f, true)) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            when (text) {
                                "文件传输" -> utils.setUSBDataMode(1)
                                "USB网络共享" -> utils.setUSBDataMode(2)
                                "MIDI" -> utils.setUSBDataMode(3)
                                "PTP" -> utils.setUSBDataMode(4)
                                "仅充电" -> utils.setUSBDataMode(0)
                            }
                            onOptionSelected(text)
                        })
                    Text(text = text, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }

        }

    }
}

// wireless manage

@Composable
fun ApiWifi(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用Wifi", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.wifiDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.wifiDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiWIFIBlacklist(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {

        var txtList by remember { mutableStateOf(TextFieldValue("")) }
        TextButton(onClick = {
            txtList = TextFieldValue(utils.queryWIFIBlacklist() ?: "已停用")
        }) { Text(text = "WIFI黑名单(点击查询)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.enableWIFIBlacklist("")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "启用黑名单") }
            Button(
                onClick = {
                    utils.disableWIFIBlacklist()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "停用黑名单") }
        }

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
                    utils.insertWIFIBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "添加黑名单") }
            Button(
                onClick = {
                    utils.removeWIFIBlacklist(txtPackage.text)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "移除黑名单") }
        }
        Text(text = txtList.text, Modifier.padding(8.dp, 4.dp), fontSize = 12.sp)
    }
}

@Composable
fun ApiForgetWIFI(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "清除WIFI✅", Modifier.padding(0.dp, 4.dp)) }

        var txtSSID by remember { mutableStateOf(TextFieldValue("liaobz")) }
        TextField(value = txtSSID, onValueChange = {
            txtSSID = it
        }, label = { Text("ssid") })

        Button(onClick = {
            utils.forgetWIFI(txtSSID.text)
        }, Modifier.padding(0.dp, 4.dp)) { Text(text = "清除WIFI") }
    }
}

@Composable
fun ApiWifiAdvancedBtn(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用WIFI高级选项", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.wifiAdvancedDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.wifiAdvancedDisabled = (checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiWifiP2P(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用Wifi直连", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.wifiP2PDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.wifiP2PDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiWifiTether(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用Wifi热点", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.wifiTetherDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.wifiTetherDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiUsbTether(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用USB热点", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.usbTetherDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.usbTetherDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiBluetooth(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) {
            Text(
                text = "disable_bt", // utils.getString(R.string.disable_bt),
                Modifier.padding(0.dp, 4.dp)
            )
        }

        Row {
            val checkedState = remember { mutableStateOf(utils.bluetoothDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.bluetoothDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiBluetoothFileShare(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用蓝牙文件传输✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.bluetoothFileShareDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.bluetoothFileShareDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiBluetoothTether(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用蓝牙热点", Modifier.padding(0.dp, 4.dp)) }


        Row {
            val checkedState = remember { mutableStateOf(utils.bluetoothTetherDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.bluetoothTetherDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiGPS(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用GPS定位✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.gpsDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.gpsDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiSMS(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用短信✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.smsDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.smsDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiIncall(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用来电✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.incomingCallDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.incomingCallDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiOutcall(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用去电✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.outgoingCallDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.outgoingCallDisabled = checked
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
fun ApiExternalStorage(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用TF/SD/OTG", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.sdCardDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.sdCardDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiCamera(utils: Utils) {
    // 测试重载 ApiCamera(utils: UtilsImpl)
}

@Composable
fun ApiCamera(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用相机", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.cameraDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.cameraDisabled = checked
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

@Composable
fun ApiOTG(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用OTG功能✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.otgDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.otgDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiMic(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用麦克风", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.micDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.micDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

// system setting manage

@Composable
fun ApiAlwaysBtn(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "禁用Always(始终)✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.alwaysOpenDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.alwaysOpenDisabled = (checked)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiEyeCare(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "护眼模式✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.eyesCareEnabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.eyesCareEnabled = (checked)
                }
            )
        }
    }
}

@Composable
fun ApiTime(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "系统时间✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.setSystemTime(System.currentTimeMillis() - 60 * 1000)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "系统时间减一分钟") }
            Button(
                onClick = {
                    utils.setSystemTime(System.currentTimeMillis() + 60 * 1000)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "系统时间加一分钟") }
        }
    }
}

@Composable
fun ApiFontSize(utils: Utils) {

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
fun ApiDefaultInputMethod(utils: Utils) {
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
fun ApiDefaultLauncher(utils: Utils) {
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
fun ApiDefaultBrowser(utils: Utils) {
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
fun ApiPermissions(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "特殊应用权限✅", Modifier.padding(0.dp, 4.dp)) }

        var txtPackage by remember { mutableStateOf(TextFieldValue("com.google.android.inputmethod.pinyin")) }
        TextField(value = txtPackage, onValueChange = {
            txtPackage = it
        }, label = { Text("包名") })
        Button(onClick = {
            utils.grantAppAllPermission(txtPackage.text)
        }, Modifier.padding(8.dp, 4.dp)) { Text(text = "授予所有运行时权限") }

        Row {
            Button(
                onClick = {
                    utils.setAllowAppDisplayOverOtherApps(txtPackage.text, true)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许显示在其他应用上层") }
            Button(
                onClick = {
                    utils.setAllowAppModifySystemSettings(txtPackage.text, true)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许修改系统设置") }
        }

        Row {
            Button(
                onClick = {
                    utils.setAllowAppInstallUnknowSource(txtPackage.text, true)
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "允许安装未知应用") }
            Button(
                onClick = {
                    utils.setAllowAppAccessUsageDetails(txtPackage.text, true)
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
fun ApiScreenshot(utils: Utils, activity: ComponentActivity) {
    CheckPermission(activity)
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "截屏✅", Modifier.padding(0.dp, 4.dp)) }

        val initAsset = painterResource(id = R.drawable.nipaste20200908143757)
        val (asset, setAsset) = remember { mutableStateOf(initAsset) }
        Button(onClick = {
            setAsset(
                BitmapPainter(utils.catchScreenBitmap().asImageBitmap())
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
fun ApiBoot(utils: Utils) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "关机/重启✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            Button(
                onClick = {
                    utils.shutdownDevice()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "关机") }
            Button(
                onClick = {
                    utils.rebootDevice()
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "重启") }
        }

        Row {
            Button(
                onClick = {
                    utils.rebootDevice("recovery")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "重启到Recovery") }
            Button(
                onClick = {
                    utils.rebootDevice("bootloader")
                },
                Modifier
                    .padding(8.dp, 4.dp)
                    .weight(1.0f, true)
            ) { Text(text = "重启到BootLoader") }
        }
    }
}

// SystemUI
@Composable
fun ApiStatusBar(utils: Utils) {

    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "状态栏和导航栏✅", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedStateBack = remember { mutableStateOf(false) }
            val checkedStateHome = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedStateBack.value, onCheckedChange = { checked ->
                    checkedStateBack.value = checked
                    utils.setStatusBarDisabled(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateHome.value, onCheckedChange = { checked ->
                    checkedStateHome.value = checked
                    utils.setNavigationBarDisabled(checked)
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
        }
        Row {
            Text(
                text = "隐藏通知栏",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
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
            val checkedStateNotify = remember { mutableStateOf(false) }
            val checkedStateExpand = remember { mutableStateOf(false) }
            Checkbox(
                checked = checkedStateBack.value, onCheckedChange = { checked ->
                    checkedStateBack.value = checked
                    utils.setBackDisabled(checked)
//                utils.setColorTempIntelligentCold()
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateHome.value, onCheckedChange = { checked ->
                    checkedStateHome.value = checked
                    utils.setHomeDisabled(checked)
//                utils.setColorTempIntelligentNature()
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateRecent.value, onCheckedChange = { checked ->
                    checkedStateRecent.value = checked
                    utils.setRecentDisabled(checked)
//                utils.setColorTempIntelligentWarm()
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateNotify.value, onCheckedChange = { checked ->
                    checkedStateNotify.value = checked
                    utils.setStatusBarExpandDisabled(checked)
//                utils.setColorTempEnhance()
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Checkbox(
                checked = checkedStateExpand.value, onCheckedChange = { checked ->
                    checkedStateExpand.value = checked
                    utils.setStatusBarNotifyDisabled(checked)
//                utils.setColorTempNormal()
                }, Modifier.weight(1.0f, true), colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
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
            Text(
                text = "禁用通知栏下拉",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "禁用通知图标",
                Modifier.weight(1.0f, true), fontSize = 12.sp, textAlign = TextAlign.Center
            )
        }

    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////


@Composable
fun ApiScreenshotDisable(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅禁用截图", Modifier.padding(0.dp, 4.dp)) }

        Row {
            val checkedState = remember { mutableStateOf(utils.screenshotDisabled) }
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                    utils.screenshotDisabled = checked
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Red
                )
            )
        }
    }
}

@Composable
fun ApiSN(utils: UtilsImpl) {
    Column(Modifier.padding(16.dp)) {
        TextButton(onClick = {}) { Text(text = "✅读取各种SN", Modifier.padding(0.dp, 4.dp)) }

        Text(text = "SN:\n" + utils.sn, Modifier.padding(8.dp, 4.dp))
        Text(text = "WIFI MAC:\n" + utils.wifimac, Modifier.padding(8.dp, 4.dp))
        Text(text = "BT MAC:\n" + utils.btmac, Modifier.padding(8.dp, 4.dp))
        Text(text = "IMEI:\n" + utils.imei, Modifier.padding(8.dp, 4.dp))
    }
}