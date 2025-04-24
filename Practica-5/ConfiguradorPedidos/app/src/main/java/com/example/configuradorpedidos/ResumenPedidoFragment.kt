/**
 * Fragment para mostrar el resumen del pedido
 * Autor: Leonardo Pachari
 * Fecha creación: 24/04/2025
 * Última modificación: 24/04/2025
 */

package com.example.configuradorpedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.configuradorpedidos.databinding.FragmentResumenPedidoBinding
import com.example.configuradorpedidos.viewmodel.PedidoViewModel

class ResumenPedidoFragment : Fragment() {

    private var _binding: FragmentResumenPedidoBinding? = null
    private val binding get() = _binding!!
    private val pedidoViewModel: PedidoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResumenPedidoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observar cambios en el pedido
        pedidoViewModel.pedido.observe(viewLifecycleOwner) { pedido ->
            binding.tvComidaSeleccionada.text = "Comida: ${pedido.comida}"

            val extrasText = if (pedido.extras.isNotEmpty()) {
                "Extras:\n" + pedido.extras.joinToString("\n") { "- $it" }
            } else {
                "Extras: Ninguno"
            }

            binding.tvExtrasSeleccionados.text = extrasText
        }

        binding.btnConfirmar.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "¡Pedido confirmado! Gracias por tu compra",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_resumenPedidoFragment_to_inicioFragment)
        }

        binding.btnEditar.setOnClickListener {
            // Pasar los datos actuales como resultado para edición
            val pedido = pedidoViewModel.pedido.value
            if (pedido != null) {
                setFragmentResult("editar_pedido", bundleOf("comida" to pedido.comida))
            }
            // Volver a la pantalla de selección de comida
            findNavController().popBackStack(R.id.seleccionComidaFragment, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}