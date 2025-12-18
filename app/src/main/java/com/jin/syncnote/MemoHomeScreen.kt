package com.jin.syncnote

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jin.syncnote.ui.theme.SyncNoteTheme
import java.time.LocalDate

@Composable
fun MemoHomeScreen() {
    val uiList = buildUiList(dummyMemo)
    val gridState = rememberLazyGridState()
    val bottomThreshold = 6
    val isNearBottom by remember(uiList.size) {
        derivedStateOf {
            val layout = gridState.layoutInfo
            val lastVisibleIndex = layout.visibleItemsInfo.lastOrNull()?.index ?: 0
            val lastIndex = (layout.totalItemsCount - 1).coerceAtLeast(0)
            lastVisibleIndex >= lastIndex - bottomThreshold
        }
    }

    val bottomPadding by animateDpAsState(
        targetValue = if (isNearBottom) 80.dp else 0.dp,
        label = "gridBottomPadding"
    )

    LaunchedEffect(uiList.size) {
        if (uiList.isNotEmpty() && isNearBottom) {
            gridState.scrollToItem(uiList.lastIndex) // 최초는 scrollToItem이 더 안정적
        }
    }

    Scaffold(contentWindowInsets = WindowInsets.safeDrawing) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = bottomPadding
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = uiList.size,
                    span = { index ->
                        when (uiList[index]) {
                            is MemoUi.Header -> GridItemSpan(maxLineSpan)
                            is MemoUi.Item -> GridItemSpan(1)
                        }
                    },
                    key = { index ->
                        when (val ui = uiList[index]) {
                            is MemoUi.Header -> "h-${ui.date}"
                            is MemoUi.Item -> "m-${ui.memo.id}"
                        }
                    }
                ) {
                    when (val memo = uiList[it]) {
                        is MemoUi.Header -> {
                            Text(memo.date.toString()) // TODO ui
                        }

                        is MemoUi.Item -> MemoItem(memo.memo)
                    }
                }
            }
            var viewMode = Mode.ALL // temporary
            BottomBar(
                mode = Mode.ALL,
                onModeChange = { viewMode = it },
                onClick = { /* add */ },
            )
        }
    }
}

@Composable
fun MemoItem(memo: Memo) {
    Card(
        onClick = {
            //TODO navigate to detail
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp)) {
            Text(
                text = memo.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            // TODO click event
            Icon(if (memo.favorite) Icons.Sharp.Favorite else Icons.Sharp.FavoriteBorder, contentDescription = "")
        }
        Text(
            text = memo.content,
            maxLines = 3,
            minLines = 3,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SyncNoteTheme() {
        MemoHomeScreen()
    }
}

// TODO data 적용필요
fun buildUiList(memos: List<Memo>): List<MemoUi> {
    return memos
        .sortedBy { it.writeTime }
        .groupBy { it.writeTime }
        .toSortedMap()
        .flatMap { (date, items) ->
            listOf(MemoUi.Header(date)) + items.map { MemoUi.Item(it) }
        }
}

@Composable
fun BottomBar(
    mode: Mode,
    onModeChange: (Mode) -> Unit,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 10.dp),
    ) {
        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ModeSegment(
                selected = mode,
                onSelected = onModeChange
            )
        }
        Box(
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(48.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White,
                ),
            ) {
                Icon(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = "add memo",
                )
            }
        }
    }
}

@Composable
private fun ModeSegment(
    selected: Mode,
    onSelected: (Mode) -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.LightGray)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ModeChip(
            text = Mode.YEAR.label,
            isSelected = selected == Mode.YEAR,
            onClick = { onSelected(Mode.YEAR) }
        )
        Spacer(Modifier.width(4.dp))
        ModeChip(
            text = Mode.MONTH.label,
            isSelected = selected == Mode.MONTH,
            onClick = { onSelected(Mode.MONTH) }
        )
        Spacer(Modifier.width(4.dp))
        ModeChip(
            text = Mode.WEEK.label,
            isSelected = selected == Mode.WEEK,
            onClick = { onSelected(Mode.WEEK) }
        )
        Spacer(Modifier.width(4.dp))
        ModeChip(
            text = Mode.ALL.label,
            isSelected = selected == Mode.ALL,
            onClick = { onSelected(Mode.ALL) }
        )
    }
}

@Composable
private fun ModeChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val bgAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.22f else 0.0f,
        label = "chipBgAlpha"
    )
    val textAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1.0f else 0.65f,
        label = "chipTextAlpha"
    )
    val interactionSource = remember { MutableInteractionSource() }
    val indication = LocalIndication.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = bgAlpha))
            .clickable(
                interactionSource = interactionSource,
                indication = indication,   // ✅ 여기서만 지정
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White.copy(alpha = textAlpha),
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

// temp
enum class Mode(val label: String) {
    YEAR("연"), MONTH("월"), WEEK("주"), ALL("전체")
}

sealed interface MemoUi {
    data class Header(val date: LocalDate) : MemoUi
    data class Item(val memo: Memo) : MemoUi
}

// dummy
data class Memo(
    val id: Int,
    val title: String,
    val content: String,
    val writeTime: LocalDate,
    val updateTime: LocalDate,
    val favorite: Boolean
)

val dummyMemo: List<Memo> = listOf(
    // 오늘
    Memo(
        id = 1001,
        title = "회의 메모",
        content = "다음 스프린트 목표: 메모 리스트 그룹화(날짜 헤더), 즐겨찾기 필터, 검색 UX 정리.",
        writeTime = LocalDate.now(),
        updateTime = LocalDate.now(),
        favorite = true
    ),
    Memo(
        id = 1002,
        title = "해야 할 일",
        content = "1) AAPT2 이슈 재현 케이스 기록\n2) Compose Grid 섹션 헤더 적용\n3) 테스트 데이터 정리",
        writeTime = LocalDate.now(),
        updateTime = LocalDate.now(),
        favorite = false
    ),
    Memo(
        id = 1003,
        title = "아이디어",
        content = "메모 카드에 태그(Work/Personal) 추가하면 필터 UI 만들기 쉬울 듯.",
        writeTime = LocalDate.now(),
        updateTime = LocalDate.now().plusDays(1), // 업데이트된 케이스
        favorite = true
    ),

    // 어제
    Memo(
        id = 1004,
        title = "맛집 리스트",
        content = "공덕역 근처: 국물요리/매콤한 메뉴 위주로 다시 찾아보기.",
        writeTime = LocalDate.now().minusDays(1),
        updateTime = LocalDate.now().minusDays(1),
        favorite = false
    ),
    Memo(
        id = 1005,
        title = "영수증 정리",
        content = "카페: 아메리카노 4,800원 / 디저트 6,500원. 다음엔 멤버십 적립하기.",
        writeTime = LocalDate.now().minusDays(1),
        updateTime = LocalDate.now(), // 다음날 수정한 케이스
        favorite = false
    ),

    // 2일 전
    Memo(
        id = 1006,
        title = "운동 기록",
        content = "걷기 8,200보. 스트레칭 10분. 컨디션 괜찮았음.",
        writeTime = LocalDate.now().minusDays(2),
        updateTime = LocalDate.now().minusDays(2),
        favorite = true
    ),
    Memo(
        id = 1007,
        title = "독서 메모",
        content = "핵심은 ‘상태의 단일 소스(SSOT)’를 유지하는 것. UI는 상태를 렌더링한다.",
        writeTime = LocalDate.now().minusDays(2),
        updateTime = LocalDate.now().minusDays(2).plusDays(1),
        favorite = false
    ),

    // 5일 전
    Memo(
        id = 1008,
        title = "여행 준비",
        content = "사진 정리 + 일정표 확인. 이동 동선은 한 번 더 단순하게.",
        writeTime = LocalDate.now().minusDays(5),
        updateTime = LocalDate.now().minusDays(5),
        favorite = false
    ),
    Memo(
        id = 1009,
        title = "비밀번호 힌트",
        content = "테스트 계정/환경 변수 정리해두기. (실제 비밀번호는 저장하지 않기)",
        writeTime = LocalDate.now().minusDays(5),
        updateTime = LocalDate.now().minusDays(3),
        favorite = true
    ),

    // 8일 전
    Memo(
        id = 1010,
        title = "프로젝트 메모",
        content = "Local Memo Sync App: Repository → UseCase → ViewModel 흐름 정리하고, 저장소는 DataStore + Room 조합 고려.",
        writeTime = LocalDate.now().minusDays(8),
        updateTime = LocalDate.now().minusDays(8),
        favorite = true
    ),
    Memo(
        id = 1011,
        title = "디자인 체크",
        content = "카드 높이 고정: content는 minLines/maxLines로 3줄 고정하면 Grid 균일해짐.",
        writeTime = LocalDate.now().minusDays(8),
        updateTime = LocalDate.now().minusDays(7),
        favorite = false
    ),

    // 14일 전
    Memo(
        id = 1012,
        title = "정리",
        content = "주간 회고: 잘한 점(꾸준함), 개선점(작업 단위 더 쪼개기), 다음 목표(헤더/필터/검색 완성).",
        writeTime = LocalDate.now().minusDays(14),
        updateTime = LocalDate.now().minusDays(14),
        favorite = false
    )
)
