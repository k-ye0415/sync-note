package com.jin.syncnote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.syncnote.ui.theme.SyncNoteTheme
import java.time.LocalTime

@Composable
fun MemoHomeScreen() {
    Scaffold(contentWindowInsets = WindowInsets.safeDrawing) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize()
            ) {
                items(dummyMemo.size) {
                    val memo = dummyMemo[it]
                    Card(onClick = {}) {
                        Row {
                            Text(memo.title)
                            Icon(Icons.Sharp.Favorite, contentDescription = "")
                        }
                        Text(memo.content)
                    }
                }
            }
            val interactionSource = remember { MutableInteractionSource() }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(vertical = 10.dp)
                    .padding(end = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.LightGray)
                        .clickable(interactionSource = interactionSource, indication = null, onClick = {  })
                        .indication(interactionSource, rememberRipple(color = Color.Blue)),
                ) {
                    Icon(Icons.Sharp.Add, contentDescription = "", modifier = Modifier.size(40.dp))
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.LightGray),
                ) {
                    Row {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 14.dp)
                                .background(Color.Transparent)
                        ) {
                            Text("연")
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 14.dp)
                                .background(Color.Transparent)
                        ) {
                            Text("월")
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 14.dp)
                                .background(Color.Transparent)
                        ) {
                            Text("주")
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 14.dp)
                                .background(Color.Transparent)
                        ) {
                            Text("전체")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SyncNoteTheme() {
        MemoHomeScreen()
    }
}

// temp
enum class Mode {
    YEAR, MONTH, WEEK, ALL
}

// dummy
data class Memo(
    val id: Int,
    val title: String,
    val content: String,
    val writeTime: LocalTime,
    val updateTime: LocalTime,
    val favorite: Boolean
)

val dummyMemo = listOf<Memo>(
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 8652,
        title = "cras",
        content = "metus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 1596,
        title = "lacus",
        content = "interdum",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
    Memo(
        id = 3052,
        title = "scripserit",
        content = "faucibus",
        writeTime = LocalTime.now(),
        updateTime = LocalTime.now(),
        favorite = false
    ),
)
