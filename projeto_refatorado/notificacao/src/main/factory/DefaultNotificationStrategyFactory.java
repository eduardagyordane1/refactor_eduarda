package com.exemplo.notificacao.factory;

import com.exemplo.notificacao.model.TipoNotificacao;
import com.exemplo.notificacao.strategy.NotificationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementação padrão da Factory para estratégias de notificação.
 */
@Component
public class DefaultNotificationStrategyFactory implements NotificationStrategyFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultNotificationStrategyFactory.class);
    
    private final Map<TipoNotificacao, NotificationStrategy> estrategias;
    private final Set<TipoNotificacao> estrategiasPadrao;
    
    @Autowired
    public DefaultNotificationStrategyFactory(
            List<NotificationStrategy> todasEstrategias,
            @Value("${notificacao.estrategias.padrao:EMAIL,SMS,PUSH}") String estrategiasPadraoConfig) {
        
        // Mapeia as estratégias por tipo
        this.estrategias = todasEstrategias.stream()
                .collect(Collectors.toMap(
                    NotificationStrategy::getTipo,
                    strategy -> strategy,
                    (existing, replacement) -> {
                        logger.warn("Estratégia duplicada encontrada para tipo {}", existing.getTipo());
                        return existing;
                    }
                ));
        
        // Configura estratégias padrão
        this.estrategiasPadrao = parseEstrategiasPadrao(estrategiasPadraoConfig);
        
        logger.info("Factory inicializada com {} estratégias: {}", 
                   estrategias.size(), estrategias.keySet());
    }
    
    @Override
    public NotificationStrategy criarEstrategia(TipoNotificacao tipo) {
        NotificationStrategy estrategia = estrategias.get(tipo);
        
        if (estrategia == null) {
            logger.warn("Estratégia não encontrada para tipo: {}", tipo);
            return null;
        }
        
        if (!estrategia.isDisponivel()) {
            logger.warn("Estratégia {} não está disponível", tipo);
            return null;
        }
        
        return estrategia;
    }
    
    @Override
    public List<NotificationStrategy> obterTodasEstrategias() {
        return estrategias.values().stream()
                .filter(NotificationStrategy::isDisponivel)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<NotificationStrategy> obterEstrategiasPadrao() {
        return estrategiasPadrao.stream()
                .map(this::criarEstrategia)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean isEstrategiaDisponivel(TipoNotificacao tipo) {
        NotificationStrategy estrategia = estrategias.get(tipo);
        return estrategia != null && estrategia.isDisponivel();
    }
    
    private Set<TipoNotificacao> parseEstrategiasPadrao(String config) {
        Set<TipoNotificacao> tipos = new HashSet<>();
        
        if (config != null && !config.trim().isEmpty()) {
            String[] tiposArray = config.split(",");
            
            for (String tipoStr : tiposArray) {
                try {
                    TipoNotificacao tipo = TipoNotificacao.valueOf(tipoStr.trim().toUpperCase());
                    tipos.add(tipo);
                } catch (IllegalArgumentException e) {
                    logger.warn("Tipo de notificação inválido: {}", tipoStr);
                }
            }
        }
        
        // Padrões se nenhuma configuração válida
        if (tipos.isEmpty()) {
            tipos.addAll(Arrays.asList(TipoNotificacao.EMAIL, TipoNotificacao.SMS, TipoNotificacao.PUSH));
        }
        
        return tipos;
    }
}
