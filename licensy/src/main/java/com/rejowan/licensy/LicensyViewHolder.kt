package com.rejowan.licensy

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.rejowan.licensy.databinding.LicenseItemCardBinding
import com.rejowan.licensy.databinding.LicenseItemCompactBinding
import com.rejowan.licensy.databinding.LicenseItemDetailedBinding
import com.rejowan.licensy.databinding.LicenseItemStandardBinding

/**
 * Sealed class representing ViewHolders for different [LicensyStyle] variants.
 */
sealed class LicensyViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    protected val context get() = itemView.context

    abstract fun bind(
        license: LicenseContent,
        customization: LicensyCustomization,
        interactionMode: LicensyInteractionMode,
        onItemClick: ((LicenseContent) -> Unit)?,
        animationDuration: Long
    )

    /**
     * Sets text with underline style for links.
     */
    protected fun TextView.setUnderlinedText(text: String) {
        val spannable = SpannableString(text)
        spannable.setSpan(UnderlineSpan(), 0, text.length, 0)
        this.text = spannable
    }

    /**
     * Standard expandable list style ViewHolder.
     */
    class Standard(private val binding: LicenseItemStandardBinding) : LicensyViewHolder(binding) {

        private var isLicenseDetailsVisible = false
        private var isRepoLinkVisible = false

        override fun bind(
            license: LicenseContent,
            customization: LicensyCustomization,
            interactionMode: LicensyInteractionMode,
            onItemClick: ((LicenseContent) -> Unit)?,
            animationDuration: Long
        ) {
            // Reset expanded states
            isLicenseDetailsVisible = false
            isRepoLinkVisible = false
            binding.llLicenseDetails.visibility = View.GONE
            binding.llRepoLink.visibility = View.GONE

            // Basic info
            binding.tvRepoName.text = license.title
            binding.tvAuthorName.text = license.author
            binding.copyright.visibility = if (license.copyrightYear != null) View.VISIBLE else View.GONE
            binding.copyright.text = context.getString(R.string.licensy_copyright, license.copyrightYear)

            // URL handling
            binding.ivOpen.visibility = if (license.url != null) View.VISIBLE else View.GONE
            if (license.url != null) {
                binding.tvRepoUrl.setUnderlinedText(license.url)
                binding.ivOpen.setOnClickListener {
                    if (interactionMode == LicensyInteractionMode.EXPAND_INLINE) {
                        isRepoLinkVisible = !isRepoLinkVisible
                        toggleViewWithAnimation(binding.llRepoLink, isRepoLinkVisible, animationDuration)
                        if (isLicenseDetailsVisible) {
                            isLicenseDetailsVisible = false
                            toggleViewWithAnimation(binding.llLicenseDetails, false, animationDuration)
                        }
                    }
                }
                binding.tvRepoUrl.setOnClickListener {
                    context.openUrl(license.url)
                }
            }

            // License info
            val licenseType = license.license
            binding.tvLicenseName.text = licenseType.shortName
            binding.tvLicenseFullName.text = licenseType.fullName
            binding.tvLicenseDescription.text = licenseType.description
            binding.tvLicenseUrl.setUnderlinedText(licenseType.url)

            // Click handling based on interaction mode
            binding.llRepoDetails.setOnClickListener {
                onItemClick?.invoke(license)
                if (interactionMode == LicensyInteractionMode.EXPAND_INLINE) {
                    isLicenseDetailsVisible = !isLicenseDetailsVisible
                    toggleViewWithAnimation(binding.llLicenseDetails, isLicenseDetailsVisible, animationDuration)
                    if (isRepoLinkVisible) {
                        isRepoLinkVisible = false
                        toggleViewWithAnimation(binding.llRepoLink, false, animationDuration)
                    }
                }
            }

            binding.tvLicenseUrl.setOnClickListener {
                context.openUrl(licenseType.url)
            }

            // Apply customization
            applyCustomization(customization)
        }

        private fun applyCustomization(customization: LicensyCustomization) {
            binding.tvRepoName.setTextColor(customization.lvPrimaryColor)
            binding.tvAuthorName.setTextColor(customization.lvPrimaryColor)
            binding.tvLicenseName.setTextColor(customization.lvPrimaryColor)
            binding.tvLicenseFullName.setTextColor(customization.lvPrimaryColor)

            binding.tvCreatedBy.setTextColor(customization.lvSecondaryColor)
            binding.tvLicensedUnder.setTextColor(customization.lvSecondaryColor)
            binding.copyright.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseFull.setTextColor(customization.lvSecondaryColor)
            binding.tvRepositoryLink.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseDescription.setTextColor(customization.lvSecondaryColor)

            binding.tvLicenseUrl.setTextColor(customization.lvLinkColor)
            binding.tvRepoUrl.setTextColor(customization.lvLinkColor)

            binding.llRepoDetails.setBackgroundColor(customization.lvBackgroundColor)
            binding.llLicenseDetails.setBackgroundColor(customization.lvBackgroundColorExpand)
            binding.llRepoLink.setBackgroundColor(customization.lvBackgroundColorExpand)

            binding.divider1.setBackgroundColor(customization.lvDividerColor)
            binding.divider2.setBackgroundColor(customization.lvDividerColor)
            binding.divider3.setBackgroundColor(customization.lvDividerColor)

            if (customization.lvTitleTextSize != 0f) {
                binding.tvRepoName.textSize = customization.lvTitleTextSize / context.resources.displayMetrics.density
            }

            binding.ivOpen.setImageResource(customization.lvOpenImage)
            binding.ivOpen.setColorFilter(customization.imageTint)
        }

        private fun toggleViewWithAnimation(view: View, show: Boolean, duration: Long) {
            if (show) {
                view.visibility = View.VISIBLE
                view.alpha = 0f
                view.animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .start()
            } else {
                view.animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .withEndAction {
                        view.visibility = View.GONE
                    }
                    .start()
            }
        }
    }

    /**
     * Compact single-row style ViewHolder.
     */
    class Compact(private val binding: LicenseItemCompactBinding) : LicensyViewHolder(binding) {

        override fun bind(
            license: LicenseContent,
            customization: LicensyCustomization,
            interactionMode: LicensyInteractionMode,
            onItemClick: ((LicenseContent) -> Unit)?,
            animationDuration: Long
        ) {
            binding.tvTitle.text = license.title
            binding.tvAuthor.text = license.author
            binding.tvLicenseBadge.text = license.license.shortName

            // URL icon
            binding.ivOpen.visibility = if (license.url != null) View.VISIBLE else View.GONE
            binding.ivOpen.setOnClickListener {
                license.url?.let { url -> context.openUrl(url) }
            }

            binding.llItemRoot.setOnClickListener {
                onItemClick?.invoke(license)
            }

            // Apply customization
            applyCustomization(customization)
        }

        private fun applyCustomization(customization: LicensyCustomization) {
            binding.tvTitle.setTextColor(customization.lvPrimaryColor)
            binding.tvAuthor.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseBadge.setTextColor(customization.lvSecondaryColor)
            binding.llItemRoot.setBackgroundColor(customization.lvBackgroundColor)
            binding.divider.setBackgroundColor(customization.lvDividerColor)
            binding.ivOpen.setImageResource(customization.lvOpenImage)
            binding.ivOpen.setColorFilter(customization.imageTint)
        }
    }

    /**
     * Material CardView style ViewHolder.
     */
    class Card(private val binding: LicenseItemCardBinding) : LicensyViewHolder(binding) {

        override fun bind(
            license: LicenseContent,
            customization: LicensyCustomization,
            interactionMode: LicensyInteractionMode,
            onItemClick: ((LicenseContent) -> Unit)?,
            animationDuration: Long
        ) {
            binding.tvTitle.text = license.title
            binding.tvAuthor.text = license.author
            binding.tvCopyright.visibility = if (license.copyrightYear != null) View.VISIBLE else View.GONE
            binding.tvCopyright.text = context.getString(R.string.licensy_copyright, license.copyrightYear)
            binding.tvLicenseBadge.text = license.license.shortName
            binding.tvLicenseName.text = license.license.fullName

            // URL icon
            binding.ivOpen.visibility = if (license.url != null) View.VISIBLE else View.GONE
            binding.ivOpen.setOnClickListener {
                license.url?.let { url -> context.openUrl(url) }
            }

            binding.cardRoot.setOnClickListener {
                onItemClick?.invoke(license)
            }

            // Apply customization
            applyCustomization(customization)
        }

        private fun applyCustomization(customization: LicensyCustomization) {
            binding.tvTitle.setTextColor(customization.lvPrimaryColor)
            binding.tvAuthor.setTextColor(customization.lvSecondaryColor)
            binding.tvCopyright.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseBadge.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseName.setTextColor(customization.lvPrimaryColor)
            binding.cardRoot.setCardBackgroundColor(customization.lvBackgroundColor)
            binding.divider.setBackgroundColor(customization.lvDividerColor)
            binding.ivOpen.setImageResource(customization.lvOpenImage)
            binding.ivOpen.setColorFilter(customization.imageTint)
        }

        fun setCardProperties(cornerRadius: Float, elevation: Float) {
            binding.cardRoot.radius = cornerRadius
            binding.cardRoot.cardElevation = elevation
        }
    }

    /**
     * Detailed style ViewHolder with all info visible.
     */
    class Detailed(private val binding: LicenseItemDetailedBinding) : LicensyViewHolder(binding) {

        override fun bind(
            license: LicenseContent,
            customization: LicensyCustomization,
            interactionMode: LicensyInteractionMode,
            onItemClick: ((LicenseContent) -> Unit)?,
            animationDuration: Long
        ) {
            val licenseType = license.license

            binding.tvTitle.text = license.title
            binding.tvAuthor.text = license.author
            binding.tvCopyright.visibility = if (license.copyrightYear != null) View.VISIBLE else View.GONE
            binding.tvCopyright.text = context.getString(R.string.licensy_copyright, license.copyrightYear)

            binding.tvLicenseFullName.text = licenseType.fullName
            binding.tvLicenseDescription.text = licenseType.description
            binding.tvLicenseUrl.setUnderlinedText(licenseType.url)

            // Repository link
            if (license.url != null) {
                binding.llRepoLink.visibility = View.VISIBLE
                binding.tvRepoUrl.setUnderlinedText(license.url)
                binding.tvRepoUrl.setOnClickListener {
                    context.openUrl(license.url)
                }
                binding.ivOpen.visibility = View.VISIBLE
                binding.ivOpen.setOnClickListener {
                    context.openUrl(license.url)
                }
            } else {
                binding.llRepoLink.visibility = View.GONE
                binding.ivOpen.visibility = View.GONE
            }

            binding.tvLicenseUrl.setOnClickListener {
                context.openUrl(licenseType.url)
            }

            binding.llItemRoot.setOnClickListener {
                onItemClick?.invoke(license)
            }

            // Apply customization
            applyCustomization(customization)
        }

        private fun applyCustomization(customization: LicensyCustomization) {
            binding.tvTitle.setTextColor(customization.lvPrimaryColor)
            binding.tvCreatedBy.setTextColor(customization.lvSecondaryColor)
            binding.tvAuthor.setTextColor(customization.lvPrimaryColor)
            binding.tvCopyright.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseFullName.setTextColor(customization.lvPrimaryColor)
            binding.tvLicenseDescription.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseFull.setTextColor(customization.lvSecondaryColor)
            binding.tvLicenseUrl.setTextColor(customization.lvLinkColor)
            binding.tvRepositoryLabel.setTextColor(customization.lvSecondaryColor)
            binding.tvRepoUrl.setTextColor(customization.lvLinkColor)
            binding.llItemRoot.setBackgroundColor(customization.lvBackgroundColor)
            binding.dividerBottom.setBackgroundColor(customization.lvDividerColor)
            binding.ivOpen.setImageResource(customization.lvOpenImage)
            binding.ivOpen.setColorFilter(customization.imageTint)

            if (customization.lvTitleTextSize != 0f) {
                binding.tvTitle.textSize = customization.lvTitleTextSize / context.resources.displayMetrics.density
            }
        }
    }
}
