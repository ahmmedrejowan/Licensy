package com.rejowan.licensy

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rejowan.licensy.databinding.SingleLicenseItemBinding

/**
 * Callback interface for license item clicks.
 */
fun interface OnLicenseClickListener {
    fun onLicenseClick(license: LicenseContent)
}

class LicensyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    var lvPrimaryColor = ContextCompat.getColor(context, R.color.licensy_primary)
    var lvSecondaryColor = ContextCompat.getColor(context, R.color.licensy_secondary)
    var lvLinkColor = ContextCompat.getColor(context, R.color.licensy_link)

    var lvTitleTextSize = 0f

    var lvBackgroundColor = ContextCompat.getColor(context, R.color.licensy_background)
    var lvBackgroundColorExpand = ContextCompat.getColor(context, R.color.licensy_background_expand)

    var lvOpenImage = R.drawable.ic_licensy_open
    var imageTint = ContextCompat.getColor(context, R.color.licensy_secondary)

    var lvDividerColor = ContextCompat.getColor(context, R.color.licensy_divider)

    var onLicenseClickListener: OnLicenseClickListener? = null

    private val animationDuration = 200L

    init {
        initAttributes(context, attrs)

        val layoutManager = LinearLayoutManager(context)
        setLayoutManager(layoutManager)
        val demo = LicenseContent(
            "Licensy",
            "ahmmedrejowan",
            Licenses.APACHE_2_0,
            "2024",
            "https://github.com/ahmmedrejowan/Licensy"
        )
        adapter = LicensesAdapter(mutableListOf(demo))
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LicensyView)

        try {
            lvPrimaryColor =
                typedArray.getColor(R.styleable.LicensyView_lv_text_color_primary, lvPrimaryColor)
            lvSecondaryColor = typedArray.getColor(
                R.styleable.LicensyView_lv_text_color_secondary,
                lvSecondaryColor
            )
            lvLinkColor =
                typedArray.getColor(R.styleable.LicensyView_lv_text_color_link, lvLinkColor)
            lvTitleTextSize =
                typedArray.getDimension(R.styleable.LicensyView_lv_text_size_title, lvTitleTextSize)
            lvBackgroundColor = typedArray.getColor(
                R.styleable.LicensyView_lv_background_color, lvBackgroundColor
            )
            lvBackgroundColorExpand = typedArray.getColor(
                R.styleable.LicensyView_lv_background_color_expand, lvBackgroundColorExpand
            )
            lvOpenImage =
                typedArray.getResourceId(R.styleable.LicensyView_lv_open_image, lvOpenImage)
            imageTint = typedArray.getColor(R.styleable.LicensyView_lv_image_tint, imageTint)

            lvDividerColor =
                typedArray.getColor(R.styleable.LicensyView_lv_divider_color, lvDividerColor)


        } finally {
            typedArray.recycle()
        }


    }

    fun setLicenses(listOfLicenses: List<LicenseContent>) {
        (adapter as LicensesAdapter).updateList(listOfLicenses)
    }

    private fun toggleViewWithAnimation(view: View, show: Boolean) {
        if (show) {
            view.visibility = View.VISIBLE
            view.alpha = 0f
            view.animate()
                .alpha(1f)
                .setDuration(animationDuration)
                .start()
        } else {
            view.animate()
                .alpha(0f)
                .setDuration(animationDuration)
                .withEndAction {
                    view.visibility = View.GONE
                }
                .start()
        }
    }

    private inner class LicenseDiffCallback(
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

    private inner class LicensesAdapter(val listOfLicenses: MutableList<LicenseContent>) :
        Adapter<LicensesAdapter.LicenseViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LicenseViewHolder {
            val binding =
                SingleLicenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LicenseViewHolder(binding)

        }

        override fun onBindViewHolder(holder: LicenseViewHolder, position: Int) {
            val licenseContent = listOfLicenses[position]
            holder.binding.tvRepoName.text = licenseContent.title
            holder.binding.tvAuthorName.text = licenseContent.author
            holder.binding.copyright.visibility =
                if (licenseContent.copyrightYear != null) VISIBLE else GONE
            holder.binding.copyright.text = "\u00A9 ${licenseContent.copyrightYear}"

            holder.binding.ivOpen.visibility = if (licenseContent.url != null) VISIBLE else GONE
            holder.binding.llRepoLink.visibility = GONE
            if (licenseContent.url != null) {
                holder.binding.tvRepoUrl.text = licenseContent.url
                holder.binding.ivOpen.setOnClickListener {
                    val isCurrentlyVisible = holder.binding.llRepoLink.visibility == VISIBLE
                    toggleViewWithAnimation(holder.binding.llRepoLink, !isCurrentlyVisible)
                    if (holder.binding.llLicenseDetails.visibility == VISIBLE) {
                        toggleViewWithAnimation(holder.binding.llLicenseDetails, false)
                    }
                }
                holder.binding.tvRepoUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, licenseContent.url.toUri())
                    context.startActivity(intent)
                }
            }

            val license = licenseContent.license
            holder.binding.tvLicenseName.text = license.shortName
            holder.binding.tvLicenseFullName.text = license.fullName
            holder.binding.tvLicenseDescription.text = license.description
            holder.binding.tvLicenseUrl.text = license.url

            holder.binding.llLicenseDetails.visibility = GONE
            holder.binding.llRepoDetails.setOnClickListener {
                onLicenseClickListener?.onLicenseClick(licenseContent)
                val isCurrentlyVisible = holder.binding.llLicenseDetails.visibility == VISIBLE
                toggleViewWithAnimation(holder.binding.llLicenseDetails, !isCurrentlyVisible)
                if (holder.binding.llRepoLink.visibility == VISIBLE) {
                    toggleViewWithAnimation(holder.binding.llRepoLink, false)
                }
            }

            holder.binding.tvLicenseUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, license.url.toUri())
                context.startActivity(intent)
            }


            holder.binding.tvRepoName.setTextColor(lvPrimaryColor)
            holder.binding.tvAuthorName.setTextColor(lvPrimaryColor)
            holder.binding.tvLicenseName.setTextColor(lvPrimaryColor)
            holder.binding.tvLicenseFullName.setTextColor(lvPrimaryColor)

            holder.binding.tvCreatedBy.setTextColor(lvSecondaryColor)
            holder.binding.tvLicensedUnder.setTextColor(lvSecondaryColor)
            holder.binding.copyright.setTextColor(lvSecondaryColor)
            holder.binding.tvLicenseFull.setTextColor(lvSecondaryColor)
            holder.binding.tvRepositoryLink.setTextColor(lvSecondaryColor)
            holder.binding.tvLicenseDescription.setTextColor(lvSecondaryColor)

            holder.binding.tvLicenseUrl.setTextColor(lvLinkColor)
            holder.binding.tvRepoUrl.setTextColor(lvLinkColor)

            holder.binding.llRepoDetails.setBackgroundColor(lvBackgroundColor)
            holder.binding.llLicenseDetails.setBackgroundColor(lvBackgroundColorExpand)
            holder.binding.llRepoLink.setBackgroundColor(lvBackgroundColorExpand)

            holder.binding.divider1.setBackgroundColor(lvDividerColor)
            holder.binding.divider2.setBackgroundColor(lvDividerColor)
            holder.binding.divider3.setBackgroundColor(lvDividerColor)

            if (lvTitleTextSize != 0f) {
                holder.binding.tvRepoName.textSize =
                    lvTitleTextSize / resources.displayMetrics.density
            }

            holder.binding.ivOpen.setImageResource(lvOpenImage)
            holder.binding.ivOpen.setColorFilter(imageTint)


        }

        override fun getItemCount(): Int {
            return listOfLicenses.size
        }

        fun updateList(newList: List<LicenseContent>) {
            val diffCallback = LicenseDiffCallback(listOfLicenses.toList(), newList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            listOfLicenses.clear()
            listOfLicenses.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }

        inner class LicenseViewHolder(val binding: SingleLicenseItemBinding) :
            ViewHolder(binding.root)

    }

}
