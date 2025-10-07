package com.exemplo.notificacao.observer;

import com.exemplo.notificacao.model.Pedido;
import com.exemplo.notificacao.service.NotificacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Observer responsável por enviar notificações quando eventos de pedido ocorrem.
 */
@Component
public class NotificationObserver implements PedidoObserver {
    
    private static final Logger logger = LoggerFactory.getLogger(NotificationObserver.class);
    
    private final NotificacaoService notificacaoService;
    
    @Autowired
    public NotificationObserver(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }
    
    @Override
    public void onPedidoCriado(Pedido pedido) {
        logger.info("Pedido criado detectado: {}", pedido.getId());
        notificacaoService.enviarNotificacoesPedidoCriado(pedido);
    }
    
    @Override
    public void onPedidoAtualizado(Pedido pedido) {
        logger.info("Pedido atualizado detectado: {}", pedido.getId());
        notificacaoService.enviarNotificacoesPedidoAtualizado(pedido);
    }
    
    @Override
    public void onPedidoCancelado(Pedido pedido) {
        logger.info("Pedido cancelado detectado: {}", pedido.getId());
        notificacaoService.enviarNotificacoesPedidoCancelado(pedido);
    }
}
