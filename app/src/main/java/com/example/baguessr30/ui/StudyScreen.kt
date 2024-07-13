package com.example.baguessr30.ui

import android.provider.ContactsContract.Data
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baguessr30.data.DataSource
import com.example.baguessr30.data.DataSource.studentList
import kotlin.math.exp

@Composable
fun StudentInfo(
    studentName: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = studentName,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun StudentPhoto(
    studentPhoto: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = studentPhoto),
            contentDescription = null,
            modifier = modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun StudentItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (expanded) {
                Icons.Filled.KeyboardArrowUp
            } else
                Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }

}

@Composable
fun StudentCard(
    student: DataSource.Student
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    val colour by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer, label = ""
    )




    Card(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = colour),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StudentInfo(studentName = student.name)
                StudentItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }

            if (expanded) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    StudentPhoto(studentPhoto = student.imageResourceId)
                }
            }
        }


    }
}

@Composable
fun StudyApp(
    goBackButton:() -> Unit,
    playGame:() -> Unit

) {

    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            Button(
                onClick = goBackButton,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Go Back")
            }
            Spacer(modifier = Modifier.weight(0.5f))

            Button(
                onClick = playGame,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = "Play Game")
            }
        }
        LazyColumn {
            items(DataSource.studentList) {
                StudentCard(student = it)
            }

        }
    }


}

@Preview
@Composable
fun StudyScreenPreview() {
    StudyApp({},{})
}