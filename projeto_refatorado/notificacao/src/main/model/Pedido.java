package com.exemplo.notificacao.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Representa um pedido no sistema.
 * Implementa o padrão Builder para facilitar a construção.
 */
public class Pedido {
    private final String id;
    private final String cliente;
    private final double valor;
    private final LocalDateTime dataHora;
    private final String email;
    private final String telefone;
    private final StatusPedido status;

    private Pedido(Builder builder) {
        this.id = builder.id;
        this.cliente = builder.cliente;
        this.valor = builder.valor;
        this.dataHora = builder.dataHora;
        this.email = builder.email;
        this.telefone = builder.telefone;
        this.status = builder.status;
    }

    // Getters
    public String getId() { return id; }
    public String getCliente() { return cliente; }
    public double getValor() { return valor; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public StatusPedido getStatus() { return status; }

    /**
     * Builder para construção de objetos Pedido.
     */
    public static class Builder {
        private String id;
        private String cliente;
        private double valor;
        private LocalDateTime dataHora = LocalDateTime.now();
        private String email;
        private String telefone;
        private StatusPedido status = StatusPedido.CRIADO;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder valor(double valor) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor deve ser positivo");
            }
            this.valor = valor;
            return this;
        }

        public Builder dataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Builder status(StatusPedido status) {
            this.status = status;
            return this;
        }

        public Pedido build() {
            validar();
            return new Pedido(this);
        }

        private void validar() {
            if (cliente == null || cliente.trim().isEmpty()) {
                throw new IllegalArgumentException("Cliente é obrigatório");
            }
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor deve ser positivo");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", cliente='" + cliente + '\'' +
                ", valor=" + valor +
                ", status=" + status +
                '}';
    }
}
