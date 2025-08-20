package com.evcontrol.evcontrol.domain.reserva;

import com.evcontrol.evcontrol.infra.exception.RecursoNaoEncontradoException;
import com.evcontrol.evcontrol.infra.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    //CRIAR RESERVA
    @Transactional
    public Reserva criar(Reserva r){
        if(repository.existsByDataReserva(r.getDataReserva())){
            throw new RegraNegocioException("Data já reservada");
        }
        return repository.save(r);
    }

    //ATUALIZAR RESERVA --> VERIFICA SE A NOVA DATA ESTÁ DISPONIVEL ANTES DE ATUALIZAR
    public Reserva atualizar(Long id, Reserva dados){
        Reserva atual = buscarPorId(id);
        if(dados.getDataReserva() != null && repository.existsByDataReservaAndIdNot(dados.getDataReserva(), id)){
                throw new RegraNegocioException("Nova data indisponível");
        }
        if(dados.getNomeCliente() != null){
            atual.setNomeCliente(dados.getNomeCliente());
        }

        if(dados.getDataReserva() != null){
            atual.setDataReserva(dados.getDataReserva());
        }

        if(dados.getValorCobrado() != null){
            atual.setValorCobrado(dados.getValorCobrado());
        }

        if(dados.getObservacoes() != null){
            atual.setObservacoes(dados.getObservacoes());
        }
        return repository.save(atual);
    }

    //BUSCAR RESERVA PELO ID PARA ATUALIZAR
    @Transactional(readOnly = true)
    public Reserva buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Reserva" + id + " Não encontrada"));
    }

    //BUSCAR RESERVA PELO NOME
    @Transactional(readOnly = true)
    public List<Reserva> buscarNome(String nome){
        List<Reserva> reservas = repository.findAllByNomeClienteContainingIgnoreCase(nome);
        if(reservas.isEmpty()){
            throw new RecursoNaoEncontradoException("Nenhuma reserva encontrada para o nome " + nome);
        }
        return reservas;
    }

    @Transactional
    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new RecursoNaoEncontradoException("Reserva " + id + " não encontrado");
        }
        repository.deleteById(id);
    }

    public List<Reserva> listar(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Reserva> listarPorMes(int ano, int mes){
        YearMonth ym = YearMonth.of(ano, mes);
        var inicio = ym.atDay(1);
        var fim = ym.atEndOfMonth();
        return repository.findAllByDataReservaBetween(inicio, fim);
    }

    public boolean dataDisponivel(LocalDate data){
        return !repository.existsByDataReserva(data);
    }


}
