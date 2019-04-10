package uk.co.tomek.cvandroid.domain.model

data class ExperienceModel(
    val description: String,
    val formattedEndDate: String,
    val jobTitle: String,
    val location: String,
    val logoUrl: String,
    val organization: String,
    val formattedStartDate: String
)