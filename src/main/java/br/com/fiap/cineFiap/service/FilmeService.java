package br.com.fiap.cineFiap.service;

import br.com.fiap.cineFiap.dao.FilmeDAO;
import br.com.fiap.cineFiap.enums.SimNaoEnum;
import br.com.fiap.cineFiap.models.Filme;
import br.com.fiap.cineFiap.models.Sala;

import java.util.List;

public class FilmeService {
    private final FilmeDAO filmeDAO;


    public FilmeService() {
        this.filmeDAO = new FilmeDAO();
    }

    public List<Filme> listarEmCartaz(SimNaoEnum emCartaz){
        try{
            return filmeDAO.buscarEmCartaz();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Filme buscarPorId(Integer id){
        if(id == null){
            throw new IllegalArgumentException("Nenhum ID foi fornecido.");
        }
        Filme filmeBuscado = filmeDAO.buscarPorId(id);
        if(filmeBuscado == null) {
            throw new IllegalArgumentException("Filme não encontrado para o ID fornecido.");
        } else{
            return filmeBuscado;
        }
    }

    public void cadastrar(Filme filme){
        if (filme.getId() == null){
            throw new IllegalArgumentException("O ID do filme é obrigatório.");
        }
        if(filme.getNome() == null || filme.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do filme é obrigatório.");
        }
        if(filme.getCapa() == null || filme.getCapa().isEmpty()){
            throw new IllegalArgumentException("A capa do filme é obrigatória.");
        }
        else{
            filmeDAO.cadastrar(filme);
        }
    }

    public void alterar(Filme filme, Integer id){
        if(id == null){
            throw new IllegalArgumentException("Nenhum ID foi fornecido.");
        }
        Filme filmeBuscado = filmeDAO.buscarPorId(id);
        if(filmeBuscado.getNome() == null || filme.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do filme é obrigatório.");
        }
        if(filmeBuscado.getCapa() == null || filme.getCapa().isEmpty()){
            throw new IllegalArgumentException("A capa do filme é obrigatória.");
        }
        else{
            filmeDAO.alterar(filme);
        }
    }

    public void excluir(Integer id){
        if(id == null){
            throw new IllegalArgumentException("Nenhum ID foi fornecido.");
        }
        Filme filmeBuscado = filmeDAO.buscarPorId(id);
        filmeDAO.excluir(id);
    }

}
