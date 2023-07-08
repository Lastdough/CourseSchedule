package com.dicoding.courseschedule.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.END_PICKER
import com.dicoding.courseschedule.util.START_PICKER
import com.dicoding.courseschedule.util.TimePickerFragment
import com.dicoding.courseschedule.util.showToast
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {
    private lateinit var addCourseViewModel: AddCourseViewModel
    private lateinit var startTime: String
    private lateinit var endTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val factory = AddCourseViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        findViewById<ImageButton>(R.id.add_btn_start_time).setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, START_PICKER)
        }

        findViewById<ImageButton>(R.id.add_btn_end_time).setOnClickListener {
            TimePickerFragment().show(supportFragmentManager, END_PICKER)
        }

        addCourseViewModel.saved.observe(this) {
            if (it.getContentIfNotHandled() == true) {
                onBackPressedDispatcher.onBackPressed()
            } else {
                this.showToast(getString(R.string.input_empty_message))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_insert -> {
                val courseName = findViewById<TextInputEditText>(R.id.add_ed_course_name).text.toString()
                val day = findViewById<Spinner>(R.id.sp_day).selectedItemPosition
                val lecturer = findViewById<TextInputEditText>(R.id.add_ed_lecturer).text.toString()
                val note = findViewById<TextInputEditText>(R.id.add_ed_note).text.toString()

                if (::startTime.isInitialized && ::endTime.isInitialized){
                    addCourseViewModel.insertCourse(
                        courseName = courseName,
                        startTime = startTime,
                        endTime = endTime,
                        lecturer = lecturer,
                        day = day,
                        note = note,
                    )
                    this.showToast(getString(R.string.success_message))
                } else {
                    this.showToast(getString(R.string.input_empty_message))
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance().also {
            it.set(Calendar.HOUR_OF_DAY, hour)
            it.set(Calendar.MINUTE, minute)
        }

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            START_PICKER -> {
                val startTime = timeFormat.format(calendar.time)
                findViewById<TextView>(R.id.add_tv_start_time).text = startTime
                this.startTime = startTime
            }

            END_PICKER -> {
                val endTime = timeFormat.format(calendar.time)
                findViewById<TextView>(R.id.add_tv_end_time).text = endTime
                this.endTime = endTime
            }
        }
    }


}