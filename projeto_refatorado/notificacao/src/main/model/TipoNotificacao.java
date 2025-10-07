package com.exemplo.notificacao.model;

/**
 * Enum que representa os tipos de notificação disponíveis.
 */
public enum TipoNotificacao {
    EMAIL("E-mail"),
    SMS("SMS"),
    PUSH("Push Notification"),
    WHATSAPP("WhatsApp");

    private final String descricao;

    TipoNotificacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
