package com.rejowan.licensy

import android.annotation.SuppressLint
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
internal class LicensyAdapter(
    private var style: LicensyStyle = LicensyStyle.STANDARD,
    private var interactionMode: LicensyInteractionMode = LicensyInteractionMode.EXPAND_INLINE,
    private var customization: LicensyCustomization = LicensyCustomization(),
    private var cardCornerRadius: Float = 12f,
    private var cardElevation: Float = 4f,
    private var animationDuration: Long = 200L
) : RecyclerView.Adapter<LicensyViewHolder>() {

    private val licenses = mutableListOf<LicenseContent>()
    var onItemClick: ((LicenseContent) -> Unit)? = null

    companion object {
        private const val PAYLOAD_CUSTOMIZATION = "customization"
        private const val PAYLOAD_CARD_PROPERTIES = "card_properties"
    }

    override fun getItemViewType(position: Int): Int = style.ordinal

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

        if (holder is LicensyViewHolder.Card) {
            holder.setCardProperties(cardCornerRadius, cardElevation)
        }
    }

    override fun onBindViewHolder(
        holder: LicensyViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        val license = licenses[position]
        for (payload in payloads) {
            when (payload) {
                PAYLOAD_CUSTOMIZATION -> {
                    holder.bind(license, customization, interactionMode, onItemClick, animationDuration)
                }
                PAYLOAD_CARD_PROPERTIES -> {
                    if (holder is LicensyViewHolder.Card) {
                        holder.setCardProperties(cardCornerRadius, cardElevation)
                    }
                }
            }
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
     * Uses notifyDataSetChanged because ViewHolder types change.
     */
    @SuppressLint("NotifyDataSetChanged")
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
            if (licenses.isNotEmpty()) {
                notifyItemRangeChanged(0, licenses.size, PAYLOAD_CUSTOMIZATION)
            }
        }
    }

    /**
     * Updates the customization and refreshes all items.
     */
    fun updateCustomization(newCustomization: LicensyCustomization) {
        customization = newCustomization
        if (licenses.isNotEmpty()) {
            notifyItemRangeChanged(0, licenses.size, PAYLOAD_CUSTOMIZATION)
        }
    }

    /**
     * Updates card-specific properties.
     */
    fun updateCardProperties(cornerRadius: Float, elevation: Float) {
        cardCornerRadius = cornerRadius
        cardElevation = elevation
        if (style == LicensyStyle.CARD && licenses.isNotEmpty()) {
            notifyItemRangeChanged(0, licenses.size, PAYLOAD_CARD_PROPERTIES)
        }
    }

    /**
     * Updates animation duration.
     */
    fun updateAnimationDuration(duration: Long) {
        animationDuration = duration
    }

    /**
     * Returns a copy of the current license list.
     */
    fun getLicenses(): List<LicenseContent> {
        return licenses.toList()
    }

    private class LicenseDiffCallback(
        private val oldList: List<LicenseContent>,
        private val newList: List<LicenseContent>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title &&
                    oldList[oldItemPosition].author == newList[newItemPosition].author
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
