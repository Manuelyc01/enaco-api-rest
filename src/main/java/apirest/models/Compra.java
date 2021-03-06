package apirest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer id_compra;
    @Column(name = "num_liquidacion")
    private String num_liquidacion;

    @OneToOne
    @JoinColumn(name = "cedula_productor",referencedColumnName = "cedula")
    private Productor cedula_productor;

    @Column(name = "dni_repre")
    private String dni_repre;

    @OneToOne
    @JoinColumn(name = "cod_uniope",referencedColumnName = "cod_uniope")
    private UnidadOperativa cod_uniOpe;

    @OneToOne
    @JoinColumn(name = "cod_tipohoja", referencedColumnName = "cod_tipohoja")
    private TipoHojaCoca cod_tipoHoja;

    @Column(name = "pesobruto")
    private Double pesoBruto;

    @Column(name = "tara")
    private Double tara;

    @Column(name = "humedad")
    private Double humedad;

    @Column(name = "pesoneto")
    private Double pesoNeto;

    @Column(name = "valorcompra")
    private Double valorCompra;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "totalcompra")
    private Double totalCompra;

    @Column(name = "son")
    private String son;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario id_usuario;

    @Basic(optional = false)
    @Column(name = "fecha",insertable = false, updatable = false)
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "id_repre",referencedColumnName = "id_representante")
    private Representante id_repre;

}
