package com.prova.unifacisa;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarroController {

    CarroRepository repository;

    @GetMapping("/carro")
    public List<Carro> getAllCars() {
        return repository.findAll();
    }

    @GetMapping("/carro/{id}")
    public Carro getCarById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com o ID: " + id));
    }
    @PostMapping("/carro")
    public Carro saveCar(@RequestBody Carro carro) {
        return repository.save(carro);
    }

    @DeleteMapping("carro/{id}")
    public void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PatchMapping("/carro/{id}")
    public Carro updateCar(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        return repository.findById(id)
                .map(carro -> {
                    if (carroAtualizado.getMarca() != null) {
                        carro.setMarca(carroAtualizado.getMarca());
                    }
                    if (carroAtualizado.getModelo() != null) {
                        carro.setModelo(carroAtualizado.getModelo());
                    }
                    if (carroAtualizado.getAno() != null) {
                        carro.setAno(carroAtualizado.getAno());
                    }
                    return repository.save(carro);
                })
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com o ID: " + id));
    }
}
