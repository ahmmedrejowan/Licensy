package com.rejowan.licensysample

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rejowan.licensysample.databinding.SingleLicenseItemBinding

class LicensyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {



    init {

        initAttributes(context, attrs)

        val layoutManager = LinearLayoutManager(context)
        setLayoutManager(layoutManager)
        val demo = LicenseContent(
            "Licensy",
            "ahmmedrejowan",
            Licenses.APACHE_2_0,
            2024,
            "https://github.com/ahmmedrejowan/Licensy")
        adapter = LicensesAdapter(mutableListOf(demo))
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?) {


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


    fun generateAbbreviation(title: String): String {

        // list of all uppercase letters from the title
        val upperCaseLetters = title.filter { it.isUpperCase() }

        if (upperCaseLetters.length >= 2) {
            return upperCaseLetters.substring(0, 2)
        } else {

            if (upperCaseLetters.length == 1) {
                val position = title.indexOf(upperCaseLetters[0])
                return if (position == 0) {
                    upperCaseLetters + title[1]
                } else {
                    title[0] + upperCaseLetters
                }
            } else {
                return title.substring(0, 2)
            }

        }


    }


}