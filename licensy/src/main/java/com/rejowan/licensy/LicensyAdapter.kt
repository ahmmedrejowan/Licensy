package com.rejowan.licensy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rejowan.licensy.databinding.LicenseItemCardBinding
import com.rejowan.licensy.databinding.LicenseItemCompactBinding
import com.rejowan.licensy.databinding.LicenseItemDetailedBinding
import com.rejowan.licensy.databinding.LicenseItemStandardBinding

/**
 * RecyclerView adapter for displaying license items with different styles.
 */
class LicensyAdapter(
    private var style: LicensyStyle = LicensyStyle.STANDARD,
    private var interactionMode: LicensyInteractionMode = LicensyInteractionMode.EXPAND_INLINE,
    private var customization: LicensyCustomization = LicensyCustomization(),
    private var cardCornerRadius: Float = 12f,
    private var cardElevation: Float = 4f,
    private var animationDuration: Long = 200L
) : RecyclerView.Adapter<LicensyViewHolder>() {

    private val licenses = mutableListOf<LicenseContent>()
    var onItemClick: ((LicenseContent) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return style.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicensyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (LicensyStyle.entries[viewType]) {
            LicensyStyle.STANDARD -> {
                val binding = LicenseItemStandardBinding.inflate(inflater, parent, false)
                LicensyViewHolder.Standard(binding)
            }
            LicensyStyle.COMPACT -> {
                val binding = LicenseItemCompactBinding.inflate(inflater, parent, false)
                LicensyViewHolder.Compact(binding)
            }
            LicensyStyle.CARD -> {
                val binding = LicenseItemCardBinding.inflate(inflater, parent, false)
                LicensyViewHolder.Card(binding)
            }
            LicensyStyle.DETAILED -> {
                val binding = LicenseItemDetailedBinding.inflate(inflater, parent, false)
                LicensyViewHolder.Detailed(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: LicensyViewHolder, position: Int) {
        val license = licenses[position]
        holder.bind(license, customization, interactionMode, onItemClick, animationDuration)

        // Apply card-specific properties
        if (holder is LicensyViewHolder.Card) {
            holder.setCardProperties(cardCornerRadius, cardElevation)
        }
    }

    override fun getItemCount(): Int = licenses.size

    /**
     * Updates the license list with DiffUtil for efficient updates.
     */
    fun updateList(newList: List<LicenseContent>) {
        val diffCallback = LicenseDiffCallback(licenses.toList(), newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        licenses.clear()
        licenses.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * Updates the style and refreshes all items.
     */
    fun updateStyle(newStyle: LicensyStyle) {
        if (style != newStyle) {
            style = newStyle
            notifyDataSetChanged()
        }
    }

    /**
     * Updates the interaction mode.
     */
    fun updateInteractionMode(newMode: LicensyInteractionMode) {
        if (interactionMode != newMode) {
            interactionMode = newMode
            notifyDataSetChanged()
        }
    }

    /**
     * Updates the customization and refreshes all items.
     */
    fun updateCustomization(newCustomization: LicensyCustomization) {
        customization = newCustomization
        notifyDataSetChanged()
    }

    /**
     * Updates card-specific properties.
     */
    fun updateCardProperties(cornerRadius: Float, elevation: Float) {
        cardCornerRadius = cornerRadius
        cardElevation = elevation
        if (style == LicensyStyle.CARD) {
            notifyDataSetChanged()
        }
    }

    /**
     * Updates animation duration.
     */
    fun updateAnimationDuration(duration: Long) {
        animationDuration = duration
    }

    private class LicenseDiffCallback(
        private val oldList: List<LicenseContent>,
        private val newList: List<LicenseContent>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
