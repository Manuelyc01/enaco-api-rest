package apirest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "demasia")
public class Demasia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demasia")
    private Integer id_demasia;

    @Basic(optional = false)
    @Column(name = "fecha",insertable = false, updatable = false)
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario id_usuario;

    @OneToOne
    @JoinColumn(name = "cod_uniope",referencedColumnName = "cod_uniope")
    private UnidadOperativa cod_uniOpe;

    @Column(name = "documento")
    private String documento;

    @Column(name = "comentario")
    private String comentario;

    @OneToOne
    @JoinColumn(name = "cod_tipohoja", referencedColumnName = "cod_tipohoja")
    private TipoHojaCoca cod_tipoHoja;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "cantidadneta")
    private Double cantidadNeta;



}
