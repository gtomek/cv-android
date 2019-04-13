package uk.co.tomek.cvandroid.domain

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.model.Experience
import uk.co.tomek.cvandroid.domain.model.CvModel
import uk.co.tomek.cvandroid.domain.model.ExperienceModel


class CvDataMapperTest {

    private lateinit var mapper: CvDataMapper

    @Before
    fun setUp() {
        mapper = CvDataMapper()
    }

    @Test
    fun verifyExpectedDateMappingWorks() {
        // given
        val description = "description"
        val startDate = "01-02-2010"
        val startDateProcessed = "Feb 2010"
        val endDate = "30-11-2011"
        val endDateProcessed = "Nov 2011"
        val jobTitle = "Junior"
        val location = "City,Country"
        val logoUrl = "http://"
        val organisation = "org"
        val phone = "+4445454546"
        val email = "test@email.com"
        val experience1 = Experience(
            description,
            endDate,
            jobTitle,
            location,
            logoUrl,
            organisation,
            startDate
        )
        val knowledgeTopic = "Rust"
        val name = "MyName"
        val nationality = "Monaco"
        val summary = "Just a simple summary"
        val knowledgeTopics = listOf(knowledgeTopic)
        val testModelData = CvModelData(
            listOf(experience1),
            knowledgeTopics,
            name,
            nationality,
            phone,
            email,
            summary
        )
        val experience1Processed = ExperienceModel(
            description,
            endDateProcessed,
            jobTitle,
            location,
            logoUrl,
            organisation,
            startDateProcessed
        )
        val expectedResult = CvModel(
            listOf(experience1Processed),
            knowledgeTopics,
            name, nationality, phone, summary
        )

        // when
        val result = mapper.mapCvDataToPresentation(testModelData)

        // then
        Assert.assertEquals(expectedResult, result)

    }
}