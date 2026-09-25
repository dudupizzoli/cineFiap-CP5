package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;

public class SalaService {
    private final SalaDAO salaDAO;

    public SalaService() {
        this.salaDAO = new SalaDAO();
    }

    public List<Sala> listar(){
        try{
            return salaDAO.listar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Sala buscarPorId(Long id){
        if(id == null){
            throw new IllegalArgumentException("Nenhum ID foi fornecido.");
        }
        Sala salaBuscada = salaDAO.buscarPorId(id);
        if(salaBuscada == null) {
            throw new IllegalArgumentException("Sala não encontrada para o ID fornecido.");
        } else{
            return salaBuscada;
        }
    }



}
