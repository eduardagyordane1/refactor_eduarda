package com.exemplo.notificacao.strategy;

import com.exemplo.notificacao.model.Pedido;
import com.exemplo.notificacao.model.TipoNotificacao;
import org.springframework.stereotype.Component;

/**
 * Implementação da estratégia de notificação por SMS.
 */
@Component
public class SmsNotificationStrategy extends AbstractNotificationStrategy {
    
    private static final int MAX_SMS_LENGTH = 160;
    
    @Override
    public TipoNotificacao getTipo() {
        return TipoNotificacao.SMS;
    }
    
    @Override
    protected boolean validarPedido(Pedido pedido) {
        return super.validarPedido(pedido) && 
               pedido.getTelefone() != null && 
               !pedido.getTelefone().trim().isEmpty() &&
               isTelefoneValido(pedido.getTelefone());
    }
    
    @Override
    protected String formatarMensagem(Pedido pedido) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Olá ").append(pedido.getCliente()).append("! ");
        mensagem.append("Seu pedido #").append(pedido.getId()).append(" ");
        mensagem.append("no valor de R$ ").append(String.format("%.2f", pedido.getValor()));
        mensagem.append(" foi recebido. Obrigado!");
        
        String mensagemCompleta = mensagem.toString();
        
        // Trunca se exceder o limite
        if (mensagemCompleta.length() > MAX_SMS_LENGTH) {
            mensagemCompleta = mensagemCompleta.substring(0, MAX_SMS_LENGTH - 3) + "...";
        }
        
        return mensagemCompleta;
    }
    
    @Override
    protected boolean enviarMensagem(String mensagem, Pedido pedido) {
        try {
            logger.info("Enviando SMS para: {}", pedido.getTelefone());
            
            Thread.sleep(50);
            
            // Simula sucesso (90% dos casos)
            boolean sucesso = Math.random() > 0.10;
            
            if (sucesso) {
                System.out.println("✓ SMS enviado para " + pedido.getCliente() + " (" + pedido.getTelefone() + ")");
            } else {
                System.out.println("✗ Falha ao enviar SMS para " + pedido.getCliente());
            }
            
            return sucesso;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    private boolean isTelefoneValido(String telefone) {
        String numeroLimpo = telefone.replaceAll("[^0-9]", "");
        return numeroLimpo.length() >= 10 && numeroLimpo.length() <= 11;
    }
}
