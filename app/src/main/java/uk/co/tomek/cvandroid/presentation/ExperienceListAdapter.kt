package uk.co.tomek.cvandroid.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uk.co.tomek.cvandroid.R
import kotlinx.android.synthetic.main.item_cv_list.view.*
import kotlinx.android.synthetic.main.item_cv_list_header.view.*
import timber.log.Timber
import uk.co.tomek.cvandroid.domain.model.ExperienceModel

class ExperienceListAdapter(
    private val requestManager: RequestManager,
    private val clickListener: (ExperienceModel) -> Unit
) : ListAdapter<ExperienceModel, RecyclerView.ViewHolder>(ExperienceDiffUtil()) {

    private var summary : String? = null
    private var knowledgeTopics : List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflatedView = inflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_cv_list_header -> HeaderViewHolder(inflatedView)
            else -> ExperienceItemViewHolder(inflatedView, requestManager)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as HeaderViewHolder).bind(summary, knowledgeTopics)
        } else {
            (holder as ExperienceItemViewHolder).bind(getItem(position - 1), clickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.item_cv_list_header
        } else {
            R.layout.item_cv_list
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1 // +1 for the header
    }

    fun updateSummary(summary: String, knowledgeTopics: List<String>) {
        this.summary = summary
        this.knowledgeTopics = knowledgeTopics
        notifyItemChanged(0)
    }

    class ExperienceItemViewHolder(
        view: View,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(view) {

        fun bind(
            item: ExperienceModel,
            clickListener: (ExperienceModel) -> Unit
        ) {
            Timber.v("Bind ExperienceItemViewHolder")
            with(itemView) {
                itemView.setOnClickListener { clickListener.invoke(item) }
                val until = if (item.formattedEndDate.isNotBlank()) {
                    item.formattedEndDate
                } else {
                    resources.getString(R.string.now)
                }
                val institutionFormatted = "${item.organization}, ${item.location}  ${item.formattedStartDate} - $until"
                textview_institution_name.text = institutionFormatted
                textview_job_title.text = item.jobTitle
                textview_description.text = item.description
                requestManager.load(item.logoUrl).into(imageview_logo)
            }
        }
    }

    class HeaderViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(summary: String?, knowledgeTopics: List<String>?) {
            Timber.v("Bind HeaderViewHolder")
            with(itemView) {
                textview_summary.text = summary
                val knowledge = "${resources.getString(R.string.knowledge)} : ${knowledgeTopics?.joinToString()}"
                textview_knowledge.text = knowledge
            }
        }

    }
}