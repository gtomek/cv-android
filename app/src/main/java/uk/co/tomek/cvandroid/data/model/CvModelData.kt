package uk.co.tomek.cvandroid.data.model

data class CvModelData(
    val experience: List<Experience>,
    val knowledgeTopics: List<String>,
    val name: String,
    val nationality: String,
    val phone: String,
    val email: String,
    val summary: String
)