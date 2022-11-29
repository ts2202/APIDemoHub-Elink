package com.mdm.apidemo

import android.content.Context
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.hite.mdm.MdmApiImpl
import com.mdm.apidemo.ui.theme.APIDemoHubTheme

class UDActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val utils = MdmApiImpl(baseContext)
//
//        var bLevel = (baseContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager)
//                .getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        super.onCreate(savedInstanceState)/*
        setContent {
            APIDemoHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val scrollState = rememberScrollState()
                    Column(Modifier.verticalScroll(scrollState)) {
                        Text(
                            text = "forUD ver:2021-11-18 Battery Level:" + bLevel,
                            Modifier.padding(8.dp, 4.dp),
                            fontSize = 12.sp
                        )


                        Row {
                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiAppWhitelist(utils)
                                        ApiWebWhitelist(utils)
                                        ApiAppBlacklist(utils)
//                                        ApiWebBlacklistHt(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiDisableApp(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiStatusBar(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiBoot(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                            }
////////////////////////////////////////////////////////////////////////////////////////////////////
                            Column(Modifier.weight(1.0f, true)) {
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiInstallWhitelist(utils)
                                        ApiUninstallBlacklist(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiSilentInstall(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiPowerKeyShort(utils)
                                        ApiVolumnKey(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiTime(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiFactoryReset(utils)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Card(Modifier.fillMaxWidth(0.99f)) {
                                    Column {
                                        ApiGPS(utils)
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
