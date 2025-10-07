package com.exemplo.notificacao.strategy;

import com.exemplo.notificacao.model.Pedido;
import com.exemplo.notificacao.model.TipoNotificacao;
import org.springframework.stereotype.Component;

/**
 * Implementação da estratégia de notificação por e-mail.
 */
@Component
public class EmailNotificationStrategy extends AbstractNotificationStrategy {
    
    @Override
    public TipoNotificacao getTipo() {
        return TipoNotificacao.EMAIL;
    }
    
    @Override
    protected boolean validarPedido(Pedido pedido) {
        return super.validarPedido(pedido) && 
               pedido.getEmail() != null && 
               !pedido.getEmail().trim().isEmpty() &&
               isEmailValido(pedido.getEmail());
    }
    
    @Override
    protected String formatarMensagem(Pedido pedido) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá ").append(pedido.getCliente()).append(",\n\n");
        mensagem.append("Seu pedido foi recebido com sucesso!\n\n");
        mensagem.append("Detalhes do pedido:\n");
        mensagem.append("- ID: ").append(pedido.getId()).append("\n");
        mensagem.append("- Valor: R$ ").append(String.format("%.2f", pedido.getValor())).append("\n");
        mensagem.append("- Status: ").append(pedido.getStatus().getDescricao()).append("\n\n");
        mensagem.append("Obrigado por escolher nossos serviços!");
        
        return mensagem.toString();
    }
    
    @Override
    protected boolean enviarMensagem(String mensagem, Pedido pedido) {
        try {
            logger.info("Enviando e-mail para: {}", pedido.getEmail());
            
            // Simulação do envio de e-mail
            Thread.sleep(100);
            
            // Simula sucesso (95% dos casos)
            boolean sucesso = Math.random() > 0.05;
            
            if (sucesso) {
                System.out.println("✓ E-mail enviado para " + pedido.getCliente() + " (" + pedido.getEmail() + ")");
            } else {
                System.out.println("✗ Falha ao enviar e-mail para " + pedido.getCliente());
            }
            
            return sucesso;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    private boolean isEmailValido(String email) {
        return email.contains("@") && email.contains(".");
    }
}
