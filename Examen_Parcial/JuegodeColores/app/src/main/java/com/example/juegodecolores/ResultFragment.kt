/*
 * Juego de Colores - Fragmento de resultados
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
import androidx.navigation.fragment.navArgs
import com.example.juegodecolores.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    // Argumentos recibidos (puntuación)
    private val args: ResultFragmentArgs by navArgs()

    // Gestor de puntuaciones
    private lateinit var scoreManager: ScoreManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializamos el gestor de puntuaciones
        scoreManager = ScoreManager(requireContext())

        // Mostramos la puntuación final
        val finalScore = args.score
        binding.finalScoreTextView.text = getString(R.string.score_final, finalScore)

        // Comprobamos si es un nuevo récord
        if (scoreManager.saveHighScore(finalScore)) {
            // Mostramos el mensaje de nuevo récord
            binding.highScoreTextView.text = getString(R.string.high_score, finalScore)
            binding.highScoreTextView.visibility = View.VISIBLE
        } else {
            // Mostramos el récord actual si no es nuevo
            val highScore = scoreManager.getHighScore()
            if (highScore > 0) {
                binding.highScoreTextView.text = getString(R.string.high_score, highScore)
                binding.highScoreTextView.visibility = View.VISIBLE
            }
        }

        // Mostramos un mensaje según la puntuación
        val message = when {
            finalScore < 10 -> getString(R.string.message_bad)
            finalScore < 20 -> getString(R.string.message_good)
            else -> getString(R.string.message_great)
        }
        binding.messageTextView.text = message

        // Animamos los elementos
        val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.resultTitle.startAnimation(fadeIn)
        binding.finalScoreTextView.startAnimation(fadeIn)
        binding.messageTextView.startAnimation(fadeIn)

        // Configuramos el botón para jugar de nuevo
        binding.playAgainButton.setOnClickListener {
            // Aplicamos animación al botón
            val pulseAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse_animation)
            it.startAnimation(pulseAnimation)

            // Navegamos al fragmento de juego
            findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        // Configuramos el botón para volver al menú principal
        binding.backToMenuButton.setOnClickListener {
            // Aplicamos animación al botón
            val pulseAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse_animation)
            it.startAnimation(pulseAnimation)

            // Navegamos al fragmento de bienvenida
            findNavController().navigate(R.id.action_resultFragment_to_welcomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}