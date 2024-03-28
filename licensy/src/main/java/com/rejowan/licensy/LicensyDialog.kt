package com.rejowan.licensy

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.rejowan.licensy.databinding.DialogLicensyBinding

class LicensyDialog(context: Context) : Dialog(context) {


    private val binding: DialogLicensyBinding by lazy {
        DialogLicensyBinding.inflate(layoutInflater)
    }
    private var listener: OnDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {

        binding.closeButton.setOnClickListener {
            dismiss()
        }

        setOnDismissListener {
            listener?.onDismiss()
        }


    }

    fun setOnDialogListener(listener: OnDialogListener) {
        this.listener = listener
    }

    fun setLicenses(listOfLicenses: List<LicenseContent>) {
        binding.licensyView.setLicenses(listOfLicenses)
    }

    fun setTitle(title: String) {
        binding.dialogTitle.text = title
    }

    fun setTitle(title: String, @ColorInt color: Int) {
        binding.dialogTitle.text = title
        binding.dialogTitle.setTextColor(color)
    }

    fun setTitle(title: String, @ColorInt color: Int, size: Float) {
        binding.dialogTitle.text = title
        binding.dialogTitle.setTextColor(color)
        binding.dialogTitle.textSize = size
    }

    fun setTitle(title: String, @ColorInt color: Int, size: Float, typeFace: Int) {
        binding.dialogTitle.text = title
        binding.dialogTitle.setTextColor(color)
        binding.dialogTitle.textSize = size
        binding.dialogTitle.typeface = Typeface.defaultFromStyle(typeFace)
    }

    fun setCloseText(text: String) {
        binding.closeText.text = text
    }

    fun setCloseText(text: String, @ColorInt color: Int) {
        binding.closeText.text = text
        binding.closeText.setTextColor(color)
    }

    fun setCloseText(text: String, @ColorInt color: Int, size: Float) {
        binding.closeText.text = text
        binding.closeText.setTextColor(color)
        binding.closeText.textSize = size
    }

    fun setCloseText(text: String, @ColorInt color: Int, size: Float, typeFace: Int) {
        binding.closeText.text = text
        binding.closeText.setTextColor(color)
        binding.closeText.textSize = size
        binding.closeText.typeface = Typeface.defaultFromStyle(typeFace)
    }

    fun setBackgroundColor(@ColorInt color: Int) {
        binding.dialogBody.setCardBackgroundColor(color)
    }

    fun setAccentColor(@ColorInt color: Int) {
        binding.dialogHeader.setCardBackgroundColor(color)
        binding.dialogFooter.setCardBackgroundColor(color)
        binding.closeButton.setCardBackgroundColor(color)
    }

    fun setCustomization(customization: LicensyCustomization) {
        binding.licensyView.lvPrimaryColor = customization.lvPrimaryColor
        binding.licensyView.lvSecondaryColor = customization.lvSecondaryColor
        binding.licensyView.lvLinkColor = customization.lvLinkColor
        binding.licensyView.lvTitleTextSize = customization.lvTitleTextSize
        binding.licensyView.lvBackgroundColor = customization.lvBackgroundColor
        binding.licensyView.lvBackgroundColorExpand = customization.lvBackgroundColorExpand
        binding.licensyView.lvOpenImage = customization.lvOpenImage
        binding.licensyView.imageTint = customization.imageTint
        binding.licensyView.lvDividerColor = customization.lvDividerColor

    }

    override fun onStart() {
        super.onStart()
        setCancelable(true)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )
        listener?.onShow()

    }


}