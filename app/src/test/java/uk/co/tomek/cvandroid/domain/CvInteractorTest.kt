package uk.co.tomek.cvandroid.domain

import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import uk.co.tomek.cvandroid.data.model.CvModelData
import uk.co.tomek.cvandroid.data.model.Experience
import uk.co.tomek.cvandroid.data.repository.Repository
import uk.co.tomek.cvandroid.domain.model.CvModel
import uk.co.tomek.cvandroid.domain.model.ExperienceModel
import uk.co.tomek.cvandroid.presentation.viewstate.MainViewState

@RunWith(MockitoJUnitRunner::class)
class CvInteractorTest {

    private lateinit var interactor: CvInteractor

    @Mock
    private lateinit var repository: Repository<CvModelData>

    @Before
    fun setUp() {
        interactor = CvInteractor(repository, CvDataMapper())
    }

    @Test
    fun verifyThatCvDataStateIsReturnedInCaseOfNormallDataFetch() {
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
        val expected = MainViewState.Data(expectedResult)

        // when
        runBlocking { whenever(repository.fetchData()) }.thenReturn(testModelData)
        val result = runBlocking { interactor.fetchData() }

        // then
        Assert.assertEquals(expected, result)
    }

    @Test
    fun verifyAnErrorIsPropagatedWhenConnectionFails() {
        // given
        val expection = RuntimeException("an expection")
        val expected = MainViewState.Error(expection)

        // when
        runBlocking { whenever(repository.fetchData()) }.thenThrow(expection)
        val result = runBlocking { interactor.fetchData() }

        // then
        assertEquals(expected, result)
    }
}