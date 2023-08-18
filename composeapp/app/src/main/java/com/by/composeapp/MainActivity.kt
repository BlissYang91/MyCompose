package com.by.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.by.composeapp.ui.theme.ComposeAppTheme
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
//                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) { //数据量很大的时候性能可能会非常低
//                        for (i in 0 ..20) {
//                            More(title = "Compose 测试联系第${i+1}部分，快来看吧~")
//                        }
//                    }

//                    LazyColumn(
//                        content = {
//                            item {
//                                for (i in 1..20) {
//                                    More(title = "Compose 测试联系第${i}部分，快来看吧~")
//                                }
//                            }
//                        },
////                        modifier = Modifier.clickable(onClick = {  }, indication = null, interactionSource = remember {
////                        MutableInteractionSource()
////                    })
//                    )


//                    LazyColumn() {
//                        items(getData()) { data ->
//                            More(title = "$data--")
//                        }
//                    }
//
//                    LazyRow() {
//                        items(getData()) { data ->
//                            More(title = "$data--")
//                        }
//                    }

                    Counter()

                }
            }

        }
    }

}

@Composable
fun Counter() {
    var number by remember {
        mutableStateOf(0)
    }
    Column() {
        Text(text = "当前值：$number")
        Button(onClick = { number ++ }) {
            Text(text = "ADD")
        }
    }
}

interface MutableState<T> : State<T> {
    override var value: T
}

fun getData(): List<String> {
    return List(20) { "Compose 测试联系第${it}部分，快来看吧~" }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun More(title: String) {
    Row(
        modifier = Modifier
            .background(Color.Green)
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.weight(1f))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "查看详情", color = Color.White)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAppTheme {
        Greeting("Android")
    }
}