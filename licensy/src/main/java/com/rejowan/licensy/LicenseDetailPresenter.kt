package com.rejowan.licensy

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.graphics.drawable.toDrawable
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

        bindLicenseToDialogView(binding, license, customization, context, dialog)

        dialog.window?.apply {
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
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
            setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        }

        bottomSheet.show()
    }

    private fun bindLicenseToDialogView(
        binding: DialogLicenseDetailBinding,
        license: LicenseContent,
        customization: LicensyCustomization,
        context: Context,
        dialog: Dialog
    ) {
        val licenseType = license.license

        // Title
        binding.tvTitle.text = license.title

        // Author line
        binding.tvAuthorLine.text = context.getString(R.string.licensy_by, license.author)

        // Copyright
        if (license.copyrightYear != null) {
            binding.tvCopyright.visibility = View.VISIBLE
            binding.tvCopyright.text = context.getString(R.string.licensy_copyright, license.copyrightYear)
        } else {
            binding.tvCopyright.visibility = View.GONE
        }

        // License badge and details
        binding.tvLicenseBadge.text = licenseType.shortName
        binding.tvLicenseFullName.text = licenseType.fullName
        binding.tvLicenseDescription.text = licenseType.description

        // Close button
        binding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // License button
        binding.btnLicense.setOnClickListener {
            context.openUrl(licenseType.url)
        }

        // Repository button
        if (license.url != null) {
            binding.btnRepository.visibility = View.VISIBLE
            binding.btnRepository.setOnClickListener {
                context.openUrl(license.url)
            }
        } else {
            binding.btnRepository.visibility = View.GONE
        }

        // Apply customization
        applyDialogCustomization(binding, customization)
    }

    private fun applyDialogCustomization(
        binding: DialogLicenseDetailBinding,
        customization: LicensyCustomization
    ) {
        // Text colors
        binding.tvTitle.setTextColor(customization.lvPrimaryColor)
        binding.tvAuthorLine.setTextColor(customization.lvSecondaryColor)
        binding.tvCopyright.setTextColor(customization.lvSecondaryColor)
        binding.tvLicenseBadge.setTextColor(customization.lvLinkColor)
        binding.tvLicenseFullName.setTextColor(customization.lvPrimaryColor)
        binding.tvLicenseDescription.setTextColor(customization.lvSecondaryColor)

        // Background colors
        binding.cardDialog.setCardBackgroundColor(customization.lvBackgroundColor)
        binding.headerLayout.setBackgroundColor(customization.lvBackgroundColorExpand)
        binding.cardLicenseInfo.setCardBackgroundColor(customization.lvBackgroundColorExpand)

        // Close button tint
        binding.btnClose.imageTintList = ColorStateList.valueOf(customization.lvSecondaryColor)

        // Badge background with alpha
        val badgeBgColor = ColorStateList.valueOf(
            Color.argb(
                38, // ~15% alpha
                Color.red(customization.lvLinkColor),
                Color.green(customization.lvLinkColor),
                Color.blue(customization.lvLinkColor)
            )
        )
        binding.tvLicenseBadge.backgroundTintList = badgeBgColor

        // Button colors
        binding.btnLicense.setBackgroundColor(customization.lvLinkColor)
        binding.btnRepository.setBackgroundColor(customization.lvBackgroundColorExpand)
        binding.btnRepository.setTextColor(customization.lvPrimaryColor)
        binding.btnRepository.iconTint = ColorStateList.valueOf(customization.lvPrimaryColor)
    }

    private fun bindLicenseToBottomSheetView(
        binding: BottomsheetLicenseDetailBinding,
        license: LicenseContent,
        customization: LicensyCustomization,
        context: Context
    ) {
        val licenseType = license.license

        // Title
        binding.tvTitle.text = license.title

        // Author line
        binding.tvAuthorLine.text = context.getString(R.string.licensy_by, license.author)

        // Copyright
        if (license.copyrightYear != null) {
            binding.tvCopyright.visibility = View.VISIBLE
            binding.tvCopyright.text = context.getString(R.string.licensy_copyright, license.copyrightYear)
        } else {
            binding.tvCopyright.visibility = View.GONE
        }

        // License badge and details
        binding.tvLicenseBadge.text = licenseType.shortName
        binding.tvLicenseFullName.text = licenseType.fullName
        binding.tvLicenseDescription.text = licenseType.description

        // License button
        binding.btnLicense.setOnClickListener {
            context.openUrl(licenseType.url)
        }

        // Repository button
        if (license.url != null) {
            binding.btnRepository.visibility = View.VISIBLE
            binding.btnRepository.setOnClickListener {
                context.openUrl(license.url)
            }
        } else {
            binding.btnRepository.visibility = View.GONE
        }

        // Apply customization
        applyBottomSheetCustomization(binding, customization)
    }

    private fun applyBottomSheetCustomization(
        binding: BottomsheetLicenseDetailBinding,
        customization: LicensyCustomization
    ) {
        // Text colors
        binding.tvTitle.setTextColor(customization.lvPrimaryColor)
        binding.tvAuthorLine.setTextColor(customization.lvSecondaryColor)
        binding.tvCopyright.setTextColor(customization.lvSecondaryColor)
        binding.tvLicenseBadge.setTextColor(customization.lvLinkColor)
        binding.tvLicenseFullName.setTextColor(customization.lvPrimaryColor)
        binding.tvLicenseDescription.setTextColor(customization.lvSecondaryColor)

        // Background colors
        binding.root.setBackgroundColor(customization.lvBackgroundColor)
        binding.cardLicenseInfo.setCardBackgroundColor(customization.lvBackgroundColorExpand)

        // Drag handle
        binding.dragHandle.setBackgroundColor(customization.lvDividerColor)

        // Badge background with alpha
        val badgeBgColor = ColorStateList.valueOf(
            Color.argb(
                38, // ~15% alpha
                Color.red(customization.lvLinkColor),
                Color.green(customization.lvLinkColor),
                Color.blue(customization.lvLinkColor)
            )
        )
        binding.tvLicenseBadge.backgroundTintList = badgeBgColor

        // Button colors
        binding.btnLicense.setBackgroundColor(customization.lvLinkColor)
        binding.btnRepository.setBackgroundColor(customization.lvBackgroundColorExpand)
        binding.btnRepository.setTextColor(customization.lvPrimaryColor)
        binding.btnRepository.iconTint = ColorStateList.valueOf(customization.lvPrimaryColor)
    }
}
