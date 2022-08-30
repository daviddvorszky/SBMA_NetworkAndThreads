package com.example.sbma_networkandthreads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sbma_networkandthreads.ui.theme.SBMA_NetworkAndThreadsTheme
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBMA_NetworkAndThreadsTheme {
                var text by remember { mutableStateOf("Loading")}
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    thread(){
                        val myUrl = URL("https://users.metropolia.fi/~jarkkov/koe.txt")
                        val myConn = myUrl.openConnection()
                        val istream = myConn.getInputStream()
                        val allText = istream.bufferedReader().use{it.readText()}
                        val result = StringBuilder()
                        result.append(allText)
                        text = result.toString()
                    }
                    Text(text = text)
                }
            }
        }
    }
}