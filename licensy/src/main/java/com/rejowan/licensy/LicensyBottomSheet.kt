package com.rejowan.licensy

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rejowan.licensy.databinding.BottomsheetLicensyBinding
import com.rejowan.licensy.databinding.DialogLicensyBinding

class LicensyBottomSheet(context: Context) : BottomSheetDialog(context) {


    private val binding: BottomsheetLicensyBinding by lazy {
        BottomsheetLicensyBinding.inflate(layoutInflater)
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

    fun setCloseText(text: String) {
        binding.closeText.text = text
    }

    fun setAccentColor(color: Int) {
        binding.closeButton.setCardBackgroundColor(color)
    }

    fun setBackgroundColor(color: Int) {
        binding.dialogBody.setCardBackgroundColor(color)
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
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        listener?.onShow()

    }




}