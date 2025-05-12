/*
 * Juego de Colores - Fragmento de bienvenida
 * Autor: Leonardo Pachari
 * Fecha creación: 11/05/2025
 * Última modificación: 11/05/2025
 */

package com.example.juegodecolores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegodecolores.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animamos el título y la imagen
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.welcomeTitle.startAnimation(fadeIn)
        binding.welcomeImage.startAnimation(fadeIn)

        // Configuramos el botón de inicio
        binding.startGameButton.setOnClickListener {
            // Aplicamos animación al botón
            val pulseAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse_animation)
            it.startAnimation(pulseAnimation)

            // Navegamos al fragmento de juego
            findNavController().navigate(R.id.action_welcomeFragment_to_gameFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}