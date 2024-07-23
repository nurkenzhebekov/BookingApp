package com.example.bookingapp.ui.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class BookingDatePickerDialogFragment(private val onDataSet: (year: Int, month: Int, day: Int) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            onDataSet(selectedYear, selectedMonth, selectedDay)
        }, year, month, day)
    }
}