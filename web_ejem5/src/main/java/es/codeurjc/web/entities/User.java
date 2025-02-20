package es.codeurjc.web.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    public enum UserType {
        ANONYMOUS, REGISTERED, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String nombre;
    private String contraseña;
    private String gmail;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] foto;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private String masDatos;

    @ElementCollection
    private List<Long> peliculasCalificadas; // Lista de IDs de películas calificadas

    @ElementCollection
    private List<Long> reseñas; // Lista de IDs de reseñas hechas

    // Constructor vacío requerido por JPA
    protected User() {}

    // Constructor para usuario anónimo
    public User(UserType userType) {
        this.userType = userType;
    }

    // Constructor para usuario registrado
    public User(String nombre, String contraseña, String gmail, byte[] foto, Date fechaCreacion, String masDatos, List<Long> peliculasCalificadas, List<Long> reseñas) {
        this.userType = UserType.REGISTERED;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.gmail = gmail;
        this.foto = foto;
        this.fechaCreacion = fechaCreacion;
        this.masDatos = masDatos;
        this.peliculasCalificadas = peliculasCalificadas;
        this.reseñas = reseñas;
    }

    // Constructor para administrador
    public User(String nombre, String contraseña) {
        this.userType = UserType.ADMIN;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (this.userType != UserType.ANONYMOUS) {
            this.nombre = nombre;
        }
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        if (this.userType != UserType.ANONYMOUS) {
            this.contraseña = contraseña;
        }
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        if (this.userType == UserType.REGISTERED) {
            this.gmail = gmail;
        }
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        if (this.userType == UserType.REGISTERED) {
            this.foto = foto;
        }
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        if (this.userType == UserType.REGISTERED) {
            this.fechaCreacion = fechaCreacion;
        }
    }

    public String getMasDatos() {
        return masDatos;
    }

    public void setMasDatos(String masDatos) {
        if (this.userType == UserType.REGISTERED) {
            this.masDatos = masDatos;
        }
    }

    public List<Long> getPeliculasCalificadas() {
        return peliculasCalificadas;
    }

    public void setPeliculasCalificadas(List<Long> peliculasCalificadas) {
        if (this.userType == UserType.REGISTERED) {
            this.peliculasCalificadas = peliculasCalificadas;
        }
    }

    public List<Long> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Long> reseñas) {
        if (this.userType == UserType.REGISTERED) {
            this.reseñas = reseñas;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userType=" + userType +
                ", nombre='" + nombre + '\'' +
                ", gmail='" + gmail + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", peliculasCalificadas=" + peliculasCalificadas +
                ", reseñas=" + reseñas +
                '}';
    }
}
