/*
 * Juego de Colores - Fragmento principal del juego
 * Autor: Lonardo Pachari
 * Fecha creación: 11/05/2025
 * Última modificación: 11/05/2025
 */

package com.example.juegodecolores

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.juegodecolores.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    
    // Gestión de colores
    private lateinit var colorManager: ColorManager
    
    // Variables del juego
    private var score = 0
    private var gameTime = 30000L // 30 segundos en milisegundos
    private var timeRemaining = gameTime
    private lateinit var countDownTimer: CountDownTimer
    private var gameRunning = false
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializamos el gestor de colores
        colorManager = ColorManager(requireContext())
        
        // Configuramos los listeners de los botones
        setupButtonListeners()
        
        // Iniciamos el juego
        startGame()
    }
    
    private fun setupButtonListeners() {
        // Para cada botón de color, configuramos su listener
        for ((buttonId, colorId) in colorManager.buttonColorMap) {
            view?.findViewById<Button>(buttonId)?.setOnClickListener {
                // Verificamos si el botón pulsado coincide con el color mostrado
                checkAnswer(colorId)
                
                // Aplicamos animación al botón
                val pulseAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse_animation)
                it.startAnimation(pulseAnimation)
            }
        }
    }
    
    private fun startGame() {
        // Reiniciamos las variables del juego
        score = 0
        timeRemaining = gameTime
        updateScoreDisplay()
        
        // Mostramos el primer color
        setNewRandomColor()
        
        // Iniciamos el temporizador
        startTimer()
        
        // Indicamos que el juego está en marcha
        gameRunning = true
    }
    
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000
                binding.timerTextView.text = getString(R.string.time_remaining, secondsRemaining)
            }
            
            override fun onFinish() {
                endGame()
            }
        }.start()
    }
    
    private fun checkAnswer(selectedColorId: Int) {
        if (!gameRunning) return
        
        if (selectedColorId == colorManager.currentColor) {
            // Respuesta correcta
            score++
            updateScoreDisplay()
            
            // Cambiamos el color
            setNewRandomColor()
        } else {
            // Respuesta incorrecta - No sumamos puntos
            // Opcional: Podríamos restar puntos o añadir algún feedback visual/auditivo
        }
    }
    
    private fun setNewRandomColor() {
        // Obtenemos un nuevo color aleatorio
        colorManager.currentColor = colorManager.getRandomColor()
        
        // Aplicamos el color al view
        binding.colorDisplay.setBackgroundColor(colorManager.getColorValue(colorManager.currentColor))
        
        // Animamos el cambio de color
        val fadeAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
        binding.colorDisplay.startAnimation(fadeAnimation)
    }
    
    private fun updateScoreDisplay() {
        binding.scoreTextView.text = getString(R.string.score, score)
    }
    
    private fun endGame() {
        // Detenemos el temporizador
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        
        // Indicamos que el juego ha terminado
        gameRunning = false
        
        // Navegamos al fragmento de resultados pasando la puntuación
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(score)
        findNavController().navigate(action)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        
        // Detenemos el temporizador si abandonamos el fragmento
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        
        _binding = null
    }
}