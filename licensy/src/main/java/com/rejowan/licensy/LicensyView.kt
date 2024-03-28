package com.rejowan.licensy

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rejowan.licensy.databinding.SingleLicenseItemBinding

class LicensyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {


    var lvPrimaryColor = Color.parseColor("#121211")
    var lvSecondaryColor = Color.parseColor("#444444")
    var lvLinkColor = Color.parseColor("#1976D2")

    var lvTitleTextSize = 0f

    var lvBackgroundColor = Color.WHITE
    var lvBackgroundColorExpand = Color.parseColor("#f8f8f8")

    var lvOpenImage = R.drawable.ic_licensy_open
    var imageTint = Color.parseColor("#444444")

    var lvDividerColor = Color.parseColor("#e0e0e0")


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
            holder.binding.copyright.text = "© ${licenseContent.copyrightYear}"

            holder.binding.ivOpen.visibility = if (licenseContent.url != null) VISIBLE else GONE
            holder.binding.llRepoLink.visibility = GONE
            if (licenseContent.url != null) {
                holder.binding.tvRepoUrl.text = licenseContent.url
                holder.binding.ivOpen.setOnClickListener {
                    holder.binding.llRepoLink.visibility =
                        if (holder.binding.llRepoLink.visibility == VISIBLE) GONE else VISIBLE
                    holder.binding.llLicenseDetails.visibility = GONE
                }
                holder.binding.tvRepoUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, licenseContent.url.toUri())
                    context.startActivity(intent)
                }
            }

            val license = licenseContent.licenses
            holder.binding.tvLicenseName.text = license.shortName
            holder.binding.tvLicenseFullName.text = license.fullName
            holder.binding.tvLicenseDescription.text = license.description
            holder.binding.tvLicenseUrl.text = license.url

            holder.binding.llLicenseDetails.visibility = GONE
            holder.binding.llRepoDetails.setOnClickListener {
                holder.binding.llLicenseDetails.visibility =
                    if (holder.binding.llLicenseDetails.visibility == VISIBLE) GONE else VISIBLE
                holder.binding.llRepoLink.visibility = GONE
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

        fun updateList(listOfLicenses: List<LicenseContent>) {
            this.listOfLicenses.clear()
            this.listOfLicenses.addAll(listOfLicenses)
            notifyDataSetChanged()
        }

        inner class LicenseViewHolder(val binding: SingleLicenseItemBinding) :
            ViewHolder(binding.root)

    }

}