package uk.co.tomek.cvandroid.domain

import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.model.Experience
import uk.co.tomek.cvandroid.domain.model.CvModel
import uk.co.tomek.cvandroid.domain.model.ExperienceModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Basic mapper that could include some business logic if it were required.
 * Here it just maps one date format into another as there was no
 * specific business logic required.
 */
class CvDataMapper {

    private var inputDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
    private var outputDateFormat = SimpleDateFormat("MMM yyyy", Locale.UK)

    fun mapCvDataToPresentation(receivedCvModelData: CvModelData): CvModel {
        return CvModel(
            mapExperienceData(receivedCvModelData.experience),
            receivedCvModelData.knowledgeTopics,
            receivedCvModelData.name,
            receivedCvModelData.nationality,
            receivedCvModelData.phone,
            receivedCvModelData.summary
        )
    }

    private fun mapExperienceData(experience: List<Experience>): List<ExperienceModel> {
        return experience.map { item ->
            ExperienceModel(
                description = item.description,
                formattedEndDate = if (item.endDate.isNotBlank()) {
                    outputDateFormat.format(inputDateFormat.parse(item.endDate))
                } else {
                    ""
                },
                jobTitle = item.jobTitle,
                location = item.location,
                logoUrl = item.logoUrl,
                organization = item.organization,
                formattedStartDate = outputDateFormat.format(inputDateFormat.parse(item.startDate))
            )
        }

    }
}