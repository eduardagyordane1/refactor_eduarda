package com.exemplo.notificacao.observer;

/**
 * Interface Subject para o padrão Observer.
 * Define o contrato para objetos que podem ser observados.
 */
public interface PedidoSubject {
    
    /**
     * Adiciona um observador à lista de observadores.
     */
    void adicionarObservador(PedidoObserver observer);
    
    /**
     * Remove um observador da lista de observadores.
     */
    void removerObservador(PedidoObserver observer);
    
    /**
     * Notifica todos os observadores sobre um evento.
     */
    void notificarObservadores();
}
