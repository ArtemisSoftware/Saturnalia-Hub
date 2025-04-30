package event.create.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import domain.models.event.Schedule
import event.PreviewData
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import presentation.utils.DateUtil
import presentation.utils.extensions.format
import presentation.utils.extensions.formatToDate
import presentation.utils.extensions.formatToTime
import presentation.utils.transformation.DateVisualTransformation
import presentation.utils.transformation.TimeVisualTransformation

@Composable
internal fun ScheduleItem(
    schedule: Schedule,
    updateStartDate: (String) -> Unit,
    updateStartHour: (String) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = schedule.currentDate,
            label = { Text(schedule.time.dayOfWeek.name) },
            onValueChange = {
                updateStartDate(it)
            },
            //visualTransformation = DateVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        OutlinedTextField(
            value = schedule.currentHour,
            label = { Text("Hora de inicio") },
            onValueChange = {
                updateStartHour(it)
            },
            //visualTransformation = TimeVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedButton(onClick = onRemove) {
            Text("Remover")
        }
    }
}

@Preview
@Composable
private fun ScheduleItemPreview() {
    ScheduleItem(
        schedule = Schedule(time = Clock.System.now().toLocalDateTime(TimeZone.UTC)),
        updateStartDate = {},
        updateStartHour = {},
        onRemove = {},
        modifier = Modifier
    )
}