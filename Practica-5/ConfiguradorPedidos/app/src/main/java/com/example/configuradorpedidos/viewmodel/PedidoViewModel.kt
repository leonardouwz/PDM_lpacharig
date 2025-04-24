/**
 * ViewModel para compartir datos del pedido entre fragments
 * Autor: Leonardo Pachari
 * Fecha creación: 24/04/2025
 * Última modificación: 24/04/2025
 */

package com.example.configuradorpedidos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.configuradorpedidos.model.Pedido

class PedidoViewModel : ViewModel() {

    private val _pedido = MutableLiveData<Pedido>(Pedido())
    val pedido: LiveData<Pedido> = _pedido

    fun seleccionarComida(comida: String) {
        val pedidoActual = _pedido.value ?: Pedido()
        pedidoActual.comida = comida
        _pedido.value = pedidoActual
    }

    fun agregarExtra(extra: String) {
        val pedidoActual = _pedido.value ?: Pedido()
        if (!pedidoActual.extras.contains(extra)) {
            pedidoActual.extras.add(extra)
            _pedido.value = pedidoActual
        }
    }

    fun quitarExtra(extra: String) {
        val pedidoActual = _pedido.value ?: Pedido()
        pedidoActual.extras.remove(extra)
        _pedido.value = pedidoActual
    }

    fun reiniciarPedido() {
        _pedido.value = Pedido()
    }
}