package com.umc.approval.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.umc.approval.R
import com.umc.approval.databinding.FragmentPasswordBinding
import com.umc.approval.ui.activity.MainActivity

/**
 * basic login password view
 * */
class PasswordFragment : Fragment() {

    private var _binding : FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val view = binding.root

        //login button click
        login()

        //back to loginFragment
        backToLoginFragment()

        //password clear
        init_password()

        //move to passwordResetFragment
        moveToPasswordResetFragment()

        return view
    }

    /**
     * x버튼 누르면 입력중인 내용 모두 삭제
     * */
    private fun init_password() {
        binding.textRemove.setOnClickListener {
            binding.password.text.clear()
        }
    }

    /**
     * back to loginFragment
     * */
    private fun backToLoginFragment() {
        binding.backToEmailLogin.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_passwordFragment_to_loginFragment)
        }
    }

    /**
     * move to password_reset_fragmnet
     * */
    private fun moveToPasswordResetFragment() {
        binding.passwordForgot.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_passwordFragment_to_passwordResetFragment)
        }
    }

    /**
     * 로그인 버튼 클릭시 패스워드가 일치하면 MainActivity, 일치하지 않으면 오류 발생
     * */
    private fun login() {
        binding.loginButton.setOnClickListener {

            val password = binding.password.text

            Toast.makeText(requireContext(), password.toString(), Toast.LENGTH_SHORT).show()

            if (password.toString() == "12345678") {
                Toast.makeText(requireContext(), "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show()
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                binding.password.setBackgroundResource(R.drawable.login_activity_red_box)
                binding.validFail.isVisible = true
                binding.textRemove.isVisible = false
                binding.passwordValid.isVisible = true
                Toast.makeText(requireContext(), "비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * viewBinding이 더이상 필요 없을 경우 null 처리 필요
     */
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}