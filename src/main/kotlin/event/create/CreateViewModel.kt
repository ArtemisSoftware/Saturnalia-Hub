package event.create

import data.repository.CooltureRepositoryImpl
import data.repository.EventRepositoryImpl
import domain.models.EventType
import domain.models.event.PerformanceType
import domain.models.event.Schedule
import domain.repository.CooltureRepository
import domain.repository.EventRepository
import event.create.mapper.toDropDownItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.utils.extensions.updateDate
import presentation.utils.extensions.updateHour

class CreateViewModel(
    private val cooltureRepository: CooltureRepository = CooltureRepositoryImpl(),
    private val repository: EventRepository = EventRepositoryImpl()
) {

    private var coroutineScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow(
        CreateState(
            performanceTypes = PerformanceType.performanceTypes.map { it.toDropDownItem() },
            eventTypes = EventType.eventTypes.map { it.toDropDownItem() },
        )
    )
    val state: StateFlow<CreateState> = _state.asStateFlow()


    fun onTriggerEvent(event: CreateEvent) {
        when (event) {
            CreateEvent.CooltureEvent.LoadFromUrl -> loadFromUrl()
            is CreateEvent.CooltureEvent.UpdateUrl -> updateUrl(event.url)
            is CreateEvent.SummaryEvent.UpdateType -> updateType(event.id)
            is CreateEvent.SummaryEvent.UpdateTitle -> updateTitle(event.title)
            is CreateEvent.SummaryEvent.UpdateAddress -> updateAddress(event.address)
            is CreateEvent.SummaryEvent.UpdateAvenue -> updateAvenue(event.avenue)
            is CreateEvent.SummaryEvent.UpdateLocation -> updateLocation(event.location)
            is CreateEvent.SummaryEvent.UpdateLatitude -> updateLatitude(event.latitude)
            is CreateEvent.SummaryEvent.UpdateLongitude -> updateLongitude(event.longitude)
            is CreateEvent.PerformaceEvent.Delete -> deletePerformance(event.index)
            is CreateEvent.PerformaceEvent.UpdateLocal -> updateLocal(event.index, event.local)
            is CreateEvent.PerformaceEvent.UpdateArtist -> updateArtist(event.index, event.artist)
            is CreateEvent.PerformaceEvent.UpdateDate -> updatePerformanceDate(event.index, event.date)
            is CreateEvent.PerformaceEvent.UpdateHour -> updatePerformanceHour(event.index, event.hour)
            is CreateEvent.PerformaceEvent.UpdateName -> updateName(event.index, event.name)
            is CreateEvent.PerformaceEvent.UpdateOrganizer -> updateOrganizer(event.index, event.organizer)
            is CreateEvent.PerformaceEvent.UpdateType -> updatePerformanceType(event.index, event.id)
            is CreateEvent.SummaryEvent.RemoveSchedule -> removeSchedule(event.index)
            is CreateEvent.SummaryEvent.UpdateScheduleDate -> updateScheduleDate(event.index, event.date)
            is CreateEvent.SummaryEvent.UpdateScheduleHour -> updateScheduleHour(event.index, event.hour)
            is CreateEvent.Json.Load -> loadJson()
            is CreateEvent.Json.Update -> updateJson(event.json)
            is CreateEvent.SummaryEvent.UpdateImageUrl -> updateBannerUrl(url = event.url)
            is CreateEvent.PerformaceEvent.UpdateImageUrl -> updateBannerUrl(position = event.index, url = event.url)
        }
    }


    private fun updateBannerUrl(position: Int? = null, url: String) = with(_state) {

        position?.let { index ->

            val result = value.performances.toMutableList()
            val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(imageUrl = url) else value }
            update {
                it.copy(performances = edited)
            }

        } ?: run {
            update {
                it.copy(summary = it.summary.copy(imageUrl = url))
            }
        }
    }

    private fun updateScheduleHour(index: Int, hour: String) = with(_state) {
        val formatHour = hour//DateUtil.formatHour(hour)
        val result = value.summary.schedules.toMutableList()

        if(formatHour.length == 5) {
            val currentDate = result[index]
            val newHour = formatHour.split(":")
            val updatedDateTime = currentDate.time.updateHour(hour = newHour[1].toInt(), minute = newHour[0].toInt())
            val edited = result.mapIndexed { position, value -> if (position == index) Schedule(time = updatedDateTime, currentHour = hour) else value }
            update {
                it.copy(summary = it.summary.copy(schedules = edited))
            }
        } else if(formatHour.length < 5) {
            val edited =
                result.mapIndexed { position, value -> if (position == index) value.copy(currentHour = hour) else value }
            update {
                it.copy(summary = it.summary.copy(schedules = edited))
            }
        }
    }

    private fun updateScheduleDate(index: Int, date: String) = with(_state) {
        val formatDate = date//DateUtil.formatDate(date)
        val result = value.summary.schedules.toMutableList()

        if(formatDate.length == 10) {
            val currentDate = result[index]
            val newDate = formatDate.split("-")
            val updatedDateTime = currentDate.time.updateDate(year = newDate[2].toInt(), month = newDate[1].toInt(), day = newDate[0].toInt())
            val edited = result.mapIndexed { position, value -> if (position == index) Schedule(updatedDateTime, currentDate = date) else value }
            update {
                it.copy(summary = it.summary.copy(schedules = edited))
            }
        } else if(formatDate.length < 10) {
            val edited = result.mapIndexed { position, value -> if (position == index) value.copy(currentDate = formatDate) else value }
            update {
                it.copy(summary = it.summary.copy(schedules = edited))
            }
        }
    }

    private fun removeSchedule(index: Int) = with(_state) {
        val result = value.summary.schedules.toMutableList()
        result.removeAt(index)

        update {
            it.copy(summary = it.summary.copy(schedules = result))
        }
    }

    private fun updatePerformanceDate(index: Int, date: String) = with(_state) {
        val formatDate = date//DateUtil.formatDate(date)
        val result = value.performances.toMutableList()

        if(formatDate.length == 10) {
            val currentDate = result[index]
            val newDate = formatDate.split("-")
            val updatedDateTime = currentDate.date?.updateDate(year = newDate[2].toInt(), month = newDate[1].toInt(), day = newDate[0].toInt())
            val edited = result.mapIndexed { position, value -> if (position == index) value.copy(date = updatedDateTime, currentDate = date) else value }
            update {
                it.copy(performances = edited)
            }
        } else if(formatDate.length < 10) {
            val edited =
                result.mapIndexed { position, value -> if (position == index) value.copy(currentDate = date) else value }
            update {
                it.copy(performances = edited)
            }
        }
    }

    private fun updatePerformanceHour(index: Int, hour: String) = with(_state) {
        val formatHour = hour//DateUtil.formatHour(hour)
        val result = value.performances.toMutableList()

        if(hour.length == 5) {
            val currentDate = result[index]
            val newHour = formatHour.split(":")
            val updatedDateTime = currentDate.date?.updateHour(hour = newHour[1].toInt(), minute = newHour[0].toInt())

            val edited = result.mapIndexed { position, value -> if (position == index) value.copy(date = updatedDateTime, currentHour = hour) else value }
            update {
                it.copy(performances = edited)
            }
        }
        else {
            val edited =
                result.mapIndexed { position, value -> if (position == index) value.copy(currentHour = hour) else value }
            update {
                it.copy(performances = edited)
            }
        }
    }

    private fun updatePerformanceType(index: Int, typeId: Int) = with(_state) {
        val result = value.performances.toMutableList()
        val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(typeId = typeId) else value }
        update {
            it.copy(performances = edited)
        }
    }

    private fun updateName(index: Int, name: String) = with(_state) {
        val result = value.performances.toMutableList()
        val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(name = name) else value }
        update {
            it.copy(performances = edited)
        }
    }

    private fun updateOrganizer(index: Int, organizer: String) = with(_state) {
        val result = value.performances.toMutableList()
        val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(organizer = organizer) else value }
        update {
            it.copy(performances = edited)
        }
    }

    private fun updateArtist(index: Int, artist: String) = with(_state) {
        val result = value.performances.toMutableList()
        val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(artist = artist) else value }
        update {
            it.copy(performances = edited)
        }
    }

    private fun updateLocal(index: Int, local: String) = with(_state) {
        val result = value.performances.toMutableList()
        val edited = result.mapIndexed { position, value ->  if(position == index) value.copy(location = local) else value }
        update {
            it.copy(performances = edited)
        }
    }


    private fun deletePerformance(index: Int) = with(_state) {
        val result = value.performances.toMutableList()
        result.removeAt(index)
        update {
            it.copy(performances = result)
        }
    }

    private fun updateLongitude(latitude: String) = with(_state) {
        update {
            it.copy(coordinates = it.coordinates.copy(latitude = latitude.toDouble()))
        }
    }

    private fun updateLatitude(latitude: String) = with(_state) {
        update {
            it.copy(coordinates = it.coordinates.copy(latitude = latitude.toDouble()))
        }
    }

    private fun updateLocation(location: String) = with(_state) {
        update {
            it.copy(summary = it.summary.copy(location = location))
        }
    }

    private fun updateAvenue(avenue: String) = with(_state) {
        update {
            it.copy(summary = it.summary.copy(place = avenue))
        }
    }

    private fun updateAddress(address: String) = with(_state) {
        update {
            it.copy(summary = it.summary.copy(address = address))
        }
    }

    private fun updateTitle(title: String) = with(_state) {
        update {
            it.copy(summary = it.summary.copy(title = title))
        }
    }

    private fun updateType(id: Int) = with(_state) {
        update {
            it.copy(summary = it.summary.copy(typeId = id))
        }
    }

    private fun loadFromUrl() {
        coroutineScope.launch {
            val result = cooltureRepository.getEvent(_state.value.url)

            result?.let { event ->
                _state.update {
                    it.copy(
                        summary = event.summary,
                        performances = event.performances,
                        coordinates = event.coordinates
                    )
                }
            }
        }
    }

    private fun updateUrl(url: String) = with(_state) {
        update {
            it.copy(url = url)
        }
    }

    private fun loadJson() {
        coroutineScope.launch {
            val result = repository.getEventFromJson(_state.value.json)

            result?.let { event ->
                _state.update {
                    it.copy(
                        summary = event.summary,
                        performances = event.performances,
                        coordinates = event.coordinates
                    )
                }
            }
        }
    }

    private fun updateJson(json: String) = with(_state) {
        update {
            it.copy(json = json)
        }
    }
}