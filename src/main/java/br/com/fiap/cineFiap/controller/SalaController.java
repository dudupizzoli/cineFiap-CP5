package br.com.fiap.cineFiap.controller;

import br.com.fiap.cineFiap.dao.SalaDAO;
import br.com.fiap.cineFiap.models.Sala;
import br.com.fiap.cineFiap.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final SalaService salaService;

    public SalaController() {
        this.salaService = new SalaService();
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Sala sala){
        try{
            salaService.cadastrar(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body("A sala foi criada com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Não foi possível realizar o cadastro pois uma ou mais informações eram inválidas: " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable Long id){
        try{
            Sala salaBuscada = salaService.buscarPorId(id);

            if (salaBuscada == null || salaBuscada.getId() == null) {
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(salaBuscada);
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sala>> salasEmCartaz(){
        try{

            return ResponseEntity.ok(salaService.listar());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


//    public void excluir ( Long id){
//
//            dao.excluir(id);
//
//    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@PathVariable Long id, @RequestBody Sala sala){
        try{
            salaService.alterar(sala, id);
            return ResponseEntity.ok().body("A sala foi atualizada com sucesso!");
        }catch(IllegalArgumentException e){
            return  ResponseEntity.badRequest().body("Ocorreu um erro: " + e);
        }
    }

//    public void deletar(@PathVariable Long id){
//
//            dao.deletar(id);
//
//
//    }
}
