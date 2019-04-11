package uk.co.tomek.cvandroid.presentation

import androidx.recyclerview.widget.DiffUtil
import uk.co.tomek.cvandroid.domain.model.ExperienceModel

class ExperienceDiffUtil: DiffUtil.ItemCallback<ExperienceModel>() {
    override fun areContentsTheSame(oldItem: ExperienceModel, newItem: ExperienceModel): Boolean =
        oldItem.description == newItem.description


    override fun areItemsTheSame(oldItem: ExperienceModel, newItem: ExperienceModel): Boolean =
        oldItem.description == newItem.description
}
