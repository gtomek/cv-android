package uk.co.tomek.cvandroid.domain.model

/**
 * Potentially processed/mapped representation of [CvModelData] received from the
 * backend, here it has been left the same as there was no business logic
 * specified that would change somehow the data from gh
 */
data class CvModel(
    val experience: List<ExperienceModel>,
    val knowledgeTopics: List<String>,
    val name: String,
    val nationality: String,
    val phone: String,
    val summary: String
)