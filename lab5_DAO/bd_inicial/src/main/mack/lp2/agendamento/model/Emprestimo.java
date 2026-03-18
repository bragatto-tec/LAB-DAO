package mack.lp2.agendamento.model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int livroId;
    private LocalDate dataRetirada;

    public Emprestimo(int id, int livroId, LocalDate dataRetirada) {
        this.id = id;
        this.livroId = livroId;
        this.dataRetirada = dataRetirada;
    }

    public Emprestimo(int livroId, LocalDate dataRetirada) {
        this(0, livroId, dataRetirada);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", livroId=" + livroId +
                ", dataRetirada=" + dataRetirada +
                '}';
    }
}
