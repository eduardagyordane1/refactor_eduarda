package com.exemplo.notificacao.strategy;

import com.exemplo.notificacao.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe abstrata que implementa o Template Method Pattern.
 * Define o fluxo comum para envio de notificações.
 */
public abstract class AbstractNotificationStrategy implements NotificationStrategy {
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * Template Method que define o fluxo de envio de notificação.
     */
    @Override
    public final boolean enviar(Pedido pedido) {
        try {
            logger.info("Iniciando envio de notificação {} para pedido {}", getTipo(), pedido.getId());
            
            if (!isDisponivel()) {
                logger.warn("Estratégia {} não está disponível", getTipo());
                return false;
            }
            
            if (!validarPedido(pedido)) {
                logger.warn("Pedido {} não é válido para notificação {}", pedido.getId(), getTipo());
                return false;
            }
            
            String mensagem = formatarMensagem(pedido);
            boolean sucesso = enviarMensagem(mensagem, pedido);
            
            if (sucesso) {
                registrarEnvio(pedido);
                logger.info("Notificação {} enviada com sucesso para pedido {}", getTipo(), pedido.getId());
            } else {
                logger.error("Falha ao enviar notificação {} para pedido {}", getTipo(), pedido.getId());
            }
            
            return sucesso;
            
        } catch (Exception e) {
            logger.error("Erro ao enviar notificação {} para pedido {}: {}", 
                        getTipo(), pedido.getId(), e.getMessage(), e);
            return false;
        }
    }
    
    /**
     * Valida se o pedido contém as informações necessárias.
     */
    protected boolean validarPedido(Pedido pedido) {
        return pedido != null && 
               pedido.getCliente() != null && 
               !pedido.getCliente().trim().isEmpty();
    }
    
    /**
     * Formata a mensagem que será enviada.
     * Deve ser implementado pelas subclasses.
     */
    protected abstract String formatarMensagem(Pedido pedido);
    
    /**
     * Envia a mensagem formatada.
     * Deve ser implementado pelas subclasses.
     */
    protected abstract boolean enviarMensagem(String mensagem, Pedido pedido);
    
    /**
     * Registra o envio da notificação.
     */
    protected void registrarEnvio(Pedido pedido) {
        logger.debug("Registro de envio para pedido {} via {}", pedido.getId(), getTipo());
    }
    
    /**
     * Implementação padrão que considera a estratégia sempre disponível.
     */
    @Override
    public boolean isDisponivel() {
        return true;
    }
}
