package com.me.API_REST.Controller;

import com.me.API_REST.Entity.Producto;
import com.me.API_REST.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAll(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + id));
    }

    @PostMapping
    public Producto save(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto actulizarProducto){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + id));

        producto.setNombre(actulizarProducto.getNombre());
        producto.setPrecio(actulizarProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        var producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + id));

        productoRepository.delete(producto);

        return "El producto fue eliminado";
    }
}
