package com.example.lab_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lab_2.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var model : Model = Model(0, AllQuestions(), 0)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable("saved", model)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if(savedInstanceState != null) {
            model = savedInstanceState.getParcelable("saved")!!
        }

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.quizText.setText(model.questions.listOfQuestions[model.questionID].questionID)
        binding.score.text = resources.getString(R.string.Score) + ": " + model.score
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.doneButton.setOnClickListener {
            var bundle : Bundle = Bundle()
            bundle.putParcelable("model", model)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.trueButton.setOnClickListener(
            View.OnClickListener {
                if (model.questionID < model.questions.listOfQuestions.size) {
                    if (model.questions.listOfQuestions[model.questionID].isTrue) {
                        Toast.makeText(activity?.applicationContext, "Correct", Toast.LENGTH_SHORT)
                            .show()
                        model.score++
                        binding.score.text = resources.getString(R.string.Score) + ": " + model.score
                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            "Incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if(model.questionID != model.questions.listOfQuestions.size-1) {
                        binding.quizText.setText(model.questions.listOfQuestions[++model.questionID].questionID)
                    } else {
                        binding.quizText.text = resources.getString(R.string.outOfQuestions)
                        model.questionID++
                    }

                } else {
                    Toast.makeText(activity?.applicationContext, "No more questions\n   Press Done", Toast.LENGTH_LONG).show()
                }
            }
        )

        binding.falseButton.setOnClickListener(
            View.OnClickListener {
                if (model.questionID < model.questions.listOfQuestions.size) {
                    if (!model.questions.listOfQuestions[model.questionID].isTrue) {
                        Toast.makeText(activity?.applicationContext, "Correct", Toast.LENGTH_SHORT)
                            .show()
                        model.score++
                        binding.score.text = resources.getString(R.string.Score) + ": " + model.score
                    } else {
                        Toast.makeText(
                            activity?.applicationContext,
                            "Incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if(model.questionID != model.questions.listOfQuestions.size-1) {
                        binding.quizText.setText(model.questions.listOfQuestions[++model.questionID].questionID)
                    } else {
                        binding.quizText.text = resources.getString(R.string.outOfQuestions)
                        model.questionID++
                    }
                    binding.quizText.setText(model.questions.listOfQuestions[++model.questionID].questionID)
                } else {
                    Toast.makeText(activity?.applicationContext, "No more questions\nPress Done", Toast.LENGTH_LONG).show()
                }
            }
        )

        binding.nextButton.setOnClickListener(
            View.OnClickListener {
                model.questionID++
                if(model.questionID < model.questions.listOfQuestions.size) {
                    binding.quizText.setText(model.questions.listOfQuestions[model.questionID].questionID)
                } else {
                    binding.quizText.text = resources.getString(R.string.outOfQuestions)
                    Toast.makeText(activity?.applicationContext, "No more questions\nPress Done", Toast.LENGTH_LONG).show()
                }
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}