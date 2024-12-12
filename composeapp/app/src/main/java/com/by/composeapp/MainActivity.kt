@file:Suppress("UNREACHABLE_CODE")

package com.by.composeapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.by.composeapp.ui.theme.ComposeAppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Timer
import java.util.TimerTask

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

                    // TextField
                    ShowTextField()
                    time()
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

//                    LazyRow() {
//                        items(getData()) { data ->
//                            More(title = "$data--")
//                        }
//                    }

//                    Counter()

                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTextField() {
    Column() {

//        TextField(
//            value = "",
//            onValueChange = {},
//            placeholder = {
//                Text(text = "hint text")
//            },
//            colors = TextFieldDefaults.textFieldColors(
//                placeholderColor = Color.Blue,
//                cursorColor = Color.Red,
//                textColor = Color.Yellow
//            )
//        )

//        Image(
//            // image 设置尺寸和圆形加载
//            modifier = Modifier
//                .width(50.dp)
//                .height(50.dp)
//                .clip(CircleShape),
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = "This is a example image"
//        )

        val imageBitmap = ImageBitmap.imageResource(id = R.drawable.biying)
        Image(bitmap = imageBitmap, contentDescription = null)

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://c-ssl.duitang.com/uploads/blog/202105/04/20210504125140_849c0.thumb.1000_0.jpg",
            contentDescription = "this is a coil image",
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )


    }

}


@Composable
fun Counter() {
    var number by remember {
        mutableStateOf(0)
    }
    Column() {
        Text(text = "当前值：$number")
        Button(onClick = { number++ }) {
            Text(text = "ADD")
        }
    }
}

/**
 * 定时器
 */
@Composable
fun time() {
    var timer : Timer? =null
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    timer = Timer()
    var number by remember {
        mutableStateOf(sdf.format(Date()))
    }
    LaunchedEffect(key1 = number , block = {
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                // 时间
                number = sdf.format(Date())
            }
        }, 0, 1000)
    })
    Text(text = number, color = Color.Green)
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
    val context = LocalContext.current
    // rememberSaveable 代替remember，避免横竖屏切换UI重绘
    var expanded by rememberSaveable() {
        mutableStateOf(false)
    }
    // 动画状态
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp
    )

    Column(modifier = Modifier.padding(bottom = extraPadding)) {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .padding(10.dp)
        ) {
            Text(text = title, fontSize = 16.sp, color = Color.Blue, modifier = Modifier.weight(1f))
            Button(onClick = {
                expanded = !expanded
                Toast.makeText(context, if (!expanded) "查看详情" else "收起", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text(text = if (!expanded) "查看详情" else "收起", color = Color.White)
            }

        }
        if (expanded) {
            Text(
                text = "详情页展示。。。。。", modifier = Modifier
                    .fillMaxSize()
                    .height(80.dp)
                    .background(Color.Yellow)
            )
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