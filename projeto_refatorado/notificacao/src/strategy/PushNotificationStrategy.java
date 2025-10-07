package com.exemplo.notificacao.strategy;

import com.exemplo.notificacao.model.Pedido;
import com.exemplo.notificacao.model.TipoNotificacao;
import org.springframework.stereotype.Component;

/**
 * Implementação da estratégia de notificação push.
 */
@Component
public class PushNotificationStrategy extends AbstractNotificationStrategy {
    
    @Override
    public TipoNotificacao getTipo() {
        return TipoNotificacao.PUSH;
    }
    
    @Override
    protected String formatarMensagem(Pedido pedido) {
        return String.format("Pedido #%s confirmado! Valor: R$ %.2f", 
                           pedido.getId(), pedido.getValor());
    }
    
    @Override
    protected boolean enviarMensagem(String mensagem, Pedido pedido) {
        try {
            logger.info("Enviando push notification para cliente: {}", pedido.getCliente());
            
            Thread.sleep(30);
            
            // Simula sucesso (85% dos casos)
            boolean sucesso = Math.random() > 0.15;
            
            if (sucesso) {
                System.out.println("✓ Push notification enviada para " + pedido.getCliente());
            } else {
                System.out.println("✗ Falha ao enviar push notification para " + pedido.getCliente());
            }
            
            return sucesso;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
