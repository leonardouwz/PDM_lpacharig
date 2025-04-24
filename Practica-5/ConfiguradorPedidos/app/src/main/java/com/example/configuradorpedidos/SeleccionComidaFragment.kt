/**
 * Fragment para seleccionar el tipo de comida
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.configuradorpedidos.databinding.FragmentSeleccionComidaBinding
import com.example.configuradorpedidos.viewmodel.PedidoViewModel

class SeleccionComidaFragment : Fragment() {

    private var _binding: FragmentSeleccionComidaBinding? = null
    private val binding get() = _binding!!
    private val pedidoViewModel: PedidoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeleccionComidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Escuchar resultado del fragment ResumenPedido para edición
        setFragmentResultListener("editar_pedido") { _, bundle ->
            val comida = bundle.getString("comida", "")
            seleccionarComidaDesdeEdicion(comida)
        }

        // Cargar datos del pedido actual si existen
        pedidoViewModel.pedido.observe(viewLifecycleOwner) { pedido ->
            seleccionarComidaDesdeEdicion(pedido.comida)
        }

        binding.btnSiguiente.setOnClickListener {
            val comidaSeleccionada = when {
                binding.rbPizza.isChecked -> getString(R.string.pizza)
                binding.rbHamburguesa.isChecked -> getString(R.string.hamburguesa)
                binding.rbEnsalada.isChecked -> getString(R.string.ensalada)
                else -> ""
            }

            if (comidaSeleccionada.isNotEmpty()) {
                pedidoViewModel.seleccionarComida(comidaSeleccionada)
                findNavController().navigate(R.id.action_seleccionComidaFragment_to_seleccionExtrasFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Por favor selecciona una comida",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun seleccionarComidaDesdeEdicion(comida: String) {
        when (comida) {
            getString(R.string.pizza) -> binding.rbPizza.isChecked = true
            getString(R.string.hamburguesa) -> binding.rbHamburguesa.isChecked = true
            getString(R.string.ensalada) -> binding.rbEnsalada.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}