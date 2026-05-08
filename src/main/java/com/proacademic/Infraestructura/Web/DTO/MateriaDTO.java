package com.proacademic.Infraestructura.Web.DTO;

import com.proacademic.Dominio.Modelo.Materia;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para crear o actualizar una materia")
public class MateriaDTO {

    @Schema(description = "Nombre de la materia", example = "Matemáticas")
    private String nombre;

    @Schema(description = "Descripción de la materia", example = "Álgebra y cálculo")
    private String descripcion;

    public MateriaDTO() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Materia toDomain() {
        Materia m = new Materia();
        m.setNombre(this.nombre);
        m.setDescripcion(this.descripcion);
        return m;
    }

}
