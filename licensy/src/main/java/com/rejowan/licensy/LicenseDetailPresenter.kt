package com.rejowan.licensy

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.net.toUri
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rejowan.licensy.databinding.BottomsheetLicenseDetailBinding
import com.rejowan.licensy.databinding.DialogLicenseDetailBinding

/**
 * Object responsible for presenting license details in a dialog or bottom sheet.
 */
object LicenseDetailPresenter {

    /**
     * Shows a centered dialog with full license details.
     *
     * @param context The context to use for creating the dialog
     * @param license The license content to display
     * @param customization Optional customization for colors and styling
     */
    fun showDialog(
        context: Context,
        license: LicenseContent,
        customization: LicensyCustomization = LicensyCustomization()
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val binding = DialogLicenseDetailBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)

        bindLicenseToDialogView(binding, license, customization, context)

        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        dialog.show()
    }

    /**
     * Shows a bottom sheet with full license details.
     *
     * @param context The context to use for creating the bottom sheet
     * @param license The license content to display
     * @param customization Optional customization for colors and styling
     */
    fun showBottomSheet(
        context: Context,
        license: LicenseContent,
        customization: LicensyCustomization = LicensyCustomization()
    ) {
        val bottomSheet = BottomSheetDialog(context)

        val binding = BottomsheetLicenseDetailBinding.inflate(LayoutInflater.from(context))
        bottomSheet.setContentView(binding.root)

        bindLicenseToBottomSheetView(binding, license, customization, context)

        bottomSheet.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        bottomSheet.show()
    }

    private fun bindLicenseToDialogView(
        binding: DialogLicenseDetailBinding,
        license: LicenseContent,
        customization: LicensyCustomization,
        context: Context
    ) {
        val licenseType = license.license

        // Title and author
        binding.tvTitle.text = license.title
        binding.tvAuthor.text = license.author
        binding.tvCopyright.visibility = if (license.copyrightYear != null) View.VISIBLE else View.GONE
        binding.tvCopyright.text = "\u00A9 ${license.copyrightYear}"

        // License details
        binding.tvLicenseFullName.text = licenseType.fullName
        binding.tvLicenseDescription.text = licenseType.description
        binding.tvLicenseUrl.text = licenseType.url

        binding.tvLicenseUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, licenseType.url.toUri())
            context.startActivity(intent)
        }

        // Repository link
        if (license.url != null) {
            binding.llRepoLink.visibility = View.VISIBLE
            binding.tvRepoUrl.text = license.url
            binding.tvRepoUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, license.url.toUri())
                context.startActivity(intent)
            }
        } else {
            binding.llRepoLink.visibility = View.GONE
        }

        // Apply customization
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
        binding.cardDialog.setCardBackgroundColor(customization.lvBackgroundColor)
    }

    private fun bindLicenseToBottomSheetView(
        binding: BottomsheetLicenseDetailBinding,
        license: LicenseContent,
        customization: LicensyCustomization,
        context: Context
    ) {
        val licenseType = license.license

        // Title and author
        binding.tvTitle.text = license.title
        binding.tvAuthor.text = license.author
        binding.tvCopyright.visibility = if (license.copyrightYear != null) View.VISIBLE else View.GONE
        binding.tvCopyright.text = "\u00A9 ${license.copyrightYear}"

        // License details
        binding.tvLicenseFullName.text = licenseType.fullName
        binding.tvLicenseDescription.text = licenseType.description
        binding.tvLicenseUrl.text = licenseType.url

        binding.tvLicenseUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, licenseType.url.toUri())
            context.startActivity(intent)
        }

        // Repository link
        if (license.url != null) {
            binding.llRepoLink.visibility = View.VISIBLE
            binding.tvRepoUrl.text = license.url
            binding.tvRepoUrl.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, license.url.toUri())
                context.startActivity(intent)
            }
        } else {
            binding.llRepoLink.visibility = View.GONE
        }

        // Apply customization
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
        binding.root.setBackgroundColor(customization.lvBackgroundColor)
        binding.dragHandle.setBackgroundColor(customization.lvDividerColor)
    }
}
