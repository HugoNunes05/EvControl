package com.evcontrol.evcontrol.controller;

import com.evcontrol.evcontrol.domain.reserva.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@Validated
public class ReservaController {

    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(@RequestBody @Valid ReservaRequestDTO dto){
        var r = new Reserva();
        r.setNomeCliente(dto.nomeCliente());
        r.setDataReserva(dto.dataReserva());
        r.setValorCobrado(dto.valorCobrado());
        r.setObservacoes(dto.observacoes());
        var salvo = service.criar(r);
        var body = ReservaMapper.toDTO(salvo);
        return ResponseEntity.created(URI.create("/reservas/" + salvo.getId())).body(body);
    }

    @GetMapping("/{nome}")
    public List<ReservaResponseDTO> buscarPorNome(@PathVariable String nome) {
        return service.buscarNome(nome)
                .stream()
                .map(ReservaMapper::toDTO)
                .toList();
    }

    @PutMapping("/{id}")
    public ReservaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ReservaResponseDTO dto){
        var r = new Reserva();
        r.setNomeCliente(dto.nomeCliente());
        r.setDataReserva(dto.dataReserva());
        r.setValorCobrado(dto.valorCobrado());
        r.setObservacoes(dto.observacoes());
        return ReservaMapper.toDTO(service.atualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ReservaResponseDTO> listar(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano
            ){
        if(mes != null && ano != null){
            return service.listarPorMes(ano, mes)
                    .stream()
                    .map(ReservaMapper::toDTO)
                    .toList();
        }else{
            return service.listar()
                    .stream()
                    .map(ReservaMapper::toDTO)
                    .toList();
        }
    }

}
