package uk.co.tomek.cvandroid.presentation

import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import uk.co.tomek.cvandroid.R
import uk.co.tomek.cvandroid.domain.model.ExperienceModel

class ExperienceListAdapter(
    private val requestManager: RequestManager,
    private val clickListener: (ExperienceModel) -> Unit
) : ListAdapter<ExperienceModel, ExperienceListAdapter.ExperienceItemViewHolder>(ExperienceDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExperienceItemViewHolder(inflater.inflate(R.layout.item_cv_list, parent, false),
            requestManager)
    }

    override fun onBindViewHolder(holder: ExperienceItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class ExperienceItemViewHolder(view: View,
                                   private val requestManager: RequestManager
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: ExperienceModel,
                 clickListener: (ExperienceModel) -> Unit) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}