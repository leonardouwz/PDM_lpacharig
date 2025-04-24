/**
 * Fragment para seleccionar extras del pedido
 * Autor: Leonardo Pachari
 * Fecha creación: 24/04/2025
 * Última modificación: 24/04/2025
 */

package com.example.configuradorpedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.configuradorpedidos.databinding.FragmentSeleccionExtrasBinding
import com.example.configuradorpedidos.viewmodel.PedidoViewModel

class SeleccionExtrasFragment : Fragment() {

    private var _binding: FragmentSeleccionExtrasBinding? = null
    private val binding get() = _binding!!
    private val pedidoViewModel: PedidoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeleccionExtrasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Cargar extras existentes si es una edición
        pedidoViewModel.pedido.observe(viewLifecycleOwner) { pedido ->
            binding.cbBebida.isChecked = pedido.extras.contains(getString(R.string.bebida))
            binding.cbPapas.isChecked = pedido.extras.contains(getString(R.string.papas_fritas))
            binding.cbPostre.isChecked = pedido.extras.contains(getString(R.string.postre))
        }

        // Configurar checkbox listeners
        binding.cbBebida.setOnCheckedChangeListener { _, isChecked ->
            manejarCheckboxExtra(getString(R.string.bebida), isChecked)
        }

        binding.cbPapas.setOnCheckedChangeListener { _, isChecked ->
            manejarCheckboxExtra(getString(R.string.papas_fritas), isChecked)
        }

        binding.cbPostre.setOnCheckedChangeListener { _, isChecked ->
            manejarCheckboxExtra(getString(R.string.postre), isChecked)
        }

        binding.btnSiguiente.setOnClickListener {
            findNavController().navigate(R.id.action_seleccionExtrasFragment_to_resumenPedidoFragment)
        }
    }

    private fun manejarCheckboxExtra(extra: String, isChecked: Boolean) {
        if (isChecked) {
            pedidoViewModel.agregarExtra(extra)
        } else {
            pedidoViewModel.quitarExtra(extra)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}