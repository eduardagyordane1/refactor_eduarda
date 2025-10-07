package com.exemplo.notificacao.model;

/**
 * Enum que representa os possíveis status de um pedido.
 */
public enum StatusPedido {
    CRIADO("Pedido criado"),
    CONFIRMADO("Pedido confirmado"),
    PROCESSANDO("Pedido em processamento"),
    ENVIADO("Pedido enviado"),
    ENTREGUE("Pedido entregue"),
    CANCELADO("Pedido cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
