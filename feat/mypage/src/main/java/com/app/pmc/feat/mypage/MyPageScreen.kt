package com.app.pmc.feat.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.R.drawable
import com.app.pmc.core.ui.R.string
import com.app.pmc.ui.theme.Gray_100
import com.app.pmc.ui.theme.Red_500
import com.app.pmc.ui.theme.Slate_800

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 80.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(id = string.my_page_title), fontWeight = FontWeight.W600, fontSize = 24.sp)
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = drawable.ic_x_line),
                contentDescription = null,
            )
        }
        Divider(color = Gray_100)
        MenuTitle(title = stringResource(id = string.my_vote_list))
        MenuTitle(title = stringResource(id = string.my_report_list))
        Divider(color = Gray_100)
        MenuTitle(title = stringResource(id = string.logout))
        MenuTitle(title = stringResource(id = string.withdraw), color = Red_500 )
    }
}

@Composable
private fun MenuTitle(
    modifier: Modifier = Modifier,
    color: Color = Slate_800,
    title : String
) {
    Text(text = title, fontWeight = FontWeight.W500, color = color, fontSize = 15.sp, modifier = modifier.padding(start = 20.dp))
}

@Composable
@Preview(showBackground = true)
fun MyPageScreenPreview() {
    MyPageScreen()
}