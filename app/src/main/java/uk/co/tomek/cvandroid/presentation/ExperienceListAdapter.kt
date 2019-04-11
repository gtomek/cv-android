package uk.co.tomek.cvandroid.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uk.co.tomek.cvandroid.R
import kotlinx.android.synthetic.main.item_cv_list.*
import kotlinx.android.synthetic.main.item_cv_list.view.*
import uk.co.tomek.cvandroid.domain.model.ExperienceModel

class ExperienceListAdapter(
    private val requestManager: RequestManager,
    private val clickListener: (ExperienceModel) -> Unit
) : ListAdapter<ExperienceModel, ExperienceListAdapter.ExperienceItemViewHolder>(ExperienceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExperienceItemViewHolder(
            inflater.inflate(R.layout.item_cv_list, parent, false),
            requestManager
        )
    }

    override fun onBindViewHolder(holder: ExperienceItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class ExperienceItemViewHolder(
        view: View,
        private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(view) {

        fun bind(
            item: ExperienceModel,
            clickListener: (ExperienceModel) -> Unit
        ) {
            with(itemView) {
                itemView.setOnClickListener { clickListener.invoke(item) }
                val until = if (item.formattedEndDate.isNotBlank()) {
                    item.formattedEndDate
                } else {
                    resources.getString(R.string.now)
                }
                 textview_institution_name.text =
                    "${item.organization}, ${item.location}  ${item.formattedStartDate} - $until"
                textview_job_title.text = item.jobTitle
                textview_description.text = item.description
                requestManager.load(item.logoUrl).into(imageview_logo)
            }

        }

    }
}