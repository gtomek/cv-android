package uk.co.tomek.cvandroid.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import uk.co.tomek.cvandroid.R
import uk.co.tomek.cvandroid.domain.model.ExperienceModel
import uk.co.tomek.cvandroid.presentation.viewmodel.MainViewModel
import uk.co.tomek.cvandroid.presentation.viewstate.MainViewState

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var experienceListAdapter: ExperienceListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        experienceListAdapter = ExperienceListAdapter(Glide.with(this), ::openDetails)

        recycler_items_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = experienceListAdapter
        }

        button_error_layout_try_again.setOnClickListener {
            mainViewModel.retryButtonClicked()
        }

        mainViewModel.mainLiveData.observe(this, Observer { viewState ->
            viewState?.let { renderState(it) }
        })
    }

    private fun renderState(state: MainViewState) {
        Timber.v("Render state ${state::class.java}, thread: ${Thread.currentThread().name}")
        when (state) {
            is MainViewState.Loading -> {
                recycler_items_list.visibility = View.GONE
                layout_error_main.visibility = View.GONE
                progress_bar.visibility = View.VISIBLE
            }
            is MainViewState.Data -> {
                toolbar_layout.title = state.itemsResponse.name
                recycler_items_list.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
                layout_error_main.visibility = View.GONE
                state.itemsResponse.let { cvModel ->
                    experienceListAdapter.updateSummary(cvModel.summary, cvModel.knowledgeTopics)
                    experienceListAdapter.submitList(cvModel.experience)
                }
                if (state.itemsResponse.experience.isEmpty()) {
                    //TODO:DisplayEmptyState
                }
            }
            is MainViewState.Error -> {
                recycler_items_list.visibility = View.GONE
                progress_bar.visibility = View.GONE
                layout_error_main.visibility = View.VISIBLE
                Timber.e(state.throwable)
            }
        }
    }

    private fun openDetails(experienceModel: ExperienceModel) {
        Toast.makeText(this, R.string.experience_item_clicked, Toast.LENGTH_LONG).show()
    }

}
