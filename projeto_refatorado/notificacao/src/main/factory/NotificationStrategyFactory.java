package com.exemplo.notificacao.factory;

import com.exemplo.notificacao.model.TipoNotificacao;
import com.exemplo.notificacao.strategy.NotificationStrategy;
import java.util.List;

/**
 * Interface Factory para criação de estratégias de notificação.
 * Implementa o padrão Factory Method do GoF.
 */
public interface NotificationStrategyFactory {
    
    /**
     * Cria uma estratégia de notificação específica.
     */
    NotificationStrategy criarEstrategia(TipoNotificacao tipo);
    
    /**
     * Retorna todas as estratégias disponíveis.
     */
    List<NotificationStrategy> obterTodasEstrategias();
    
    /**
     * Retorna as estratégias padrão configuradas.
     */
    List<NotificationStrategy> obterEstrategiasPadrao();
    
    /**
     * Verifica se uma estratégia está disponível.
     */
    boolean isEstrategiaDisponivel(TipoNotificacao tipo);
}
