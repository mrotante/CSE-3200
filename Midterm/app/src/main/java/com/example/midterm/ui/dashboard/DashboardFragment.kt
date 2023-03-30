package com.example.midterm.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.Timer
import com.example.midterm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var timer = Timer()

        val textView: TextView = binding.textt1
        val timerText : TextView = binding.timer1

        val startButton : Button = binding.t1start
        val stopButton : Button = binding.t1stop
        val resetButton : Button = binding.t1reset

        var time : ArrayList<Long>
        var hours : String;
        var min : String;
        var sec : String

        var displayTime : Long;




        startButton.setOnClickListener(View.OnClickListener {
            displayTime = timer.start()
            if(displayTime > 0) {
                time = timer.getTimeFormatted(displayTime)
                hours = time[2].toString()
                min = time[1].toString()
                sec = time[0].toString()

                timerText.setText(hours + ":" + min + ":" + sec)
            }
        })

        stopButton.setOnClickListener(View.OnClickListener {
            displayTime = timer.stop();
            if(displayTime > 0) {
                time = timer.getTimeFormatted(displayTime)
                hours = time[2].toString()
                min = time[1].toString()
                sec = time[0].toString()

                timerText.setText(hours + ":" + min + ":" + sec)
            }
        })

        resetButton.setOnClickListener(View.OnClickListener {
            displayTime = timer.reset()
            if(displayTime > 0) {
                time = timer.getTimeFormatted(displayTime)
                hours = time[2].toString()
                min = time[1].toString()
                sec = time[0].toString()

                timerText.setText(hours + ":" + min + ":" + sec)
            }
        })

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}