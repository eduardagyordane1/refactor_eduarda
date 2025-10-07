package com.exemplo.notificacao.observer;

import com.exemplo.notificacao.model.Pedido;

/**
 * Interface Observer para o padrão Observer.
 * Define o contrato para objetos que desejam ser notificados sobre eventos de pedido.
 */
public interface PedidoObserver {
    
    /**
     * Método chamado quando um novo pedido é criado.
     */
    void onPedidoCriado(Pedido pedido);
    
    /**
     * Método chamado quando um pedido é atualizado.
     */
    void onPedidoAtualizado(Pedido pedido);
    
    /**
     * Método chamado quando um pedido é cancelado.
     */
    void onPedidoCancelado(Pedido pedido);
}
