package domain.models.event

data class PerformanceType(val id: Int, val description: String){
    companion object {
        private val concert = PerformanceType(id = 1, description = "Concerto")
        private val presentation = PerformanceType(id = 2, description = "Apresentação")

        val performanceTypes = listOf(concert, presentation)
    }
}