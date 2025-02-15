package com.app.pmc.core.ui.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pmc.core.ui.theme.NormalButtonContentColor
import com.app.pmc.core.ui.theme.NormalDiaryCardDescriptionColor
import com.app.pmc.core.ui.theme.NormalDiaryCardTitleColor
import com.app.pmc.core.ui.theme.White

@Composable
fun EchogDefaultCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    subDescription: @Composable() () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = modifier.padding(20.dp)) {
            Text(
                text = title,
                color = NormalDiaryCardTitleColor,
                fontWeight = FontWeight.W600,
                fontSize = 15.sp
            )
            Text(
                text = description,
                color = NormalButtonContentColor,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            subDescription()
        }
    }
}

@Composable
@Preview
fun CardPreview() {
    EchogDefaultCard(
        title = "Title",
        description = "Description",
        subDescription = {
            Text(
                text = "Sub Description",
                color = NormalDiaryCardDescriptionColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}