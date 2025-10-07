package com.exemplo.notificacao.strategy;

import com.exemplo.notificacao.model.Pedido;
import com.exemplo.notificacao.model.TipoNotificacao;

/**
 * Interface que define o contrato para estratégias de notificação.
 * Implementa o padrão Strategy do GoF.
 */
public interface NotificationStrategy {
    
    /**
     * Envia uma notificação para o pedido especificado.
     */
    boolean enviar(Pedido pedido);
    
    /**
     * Retorna o tipo de notificação que esta estratégia implementa.
     */
    TipoNotificacao getTipo();
    
    /**
     * Verifica se esta estratégia está disponível para uso.
     */
    boolean isDisponivel();
}
