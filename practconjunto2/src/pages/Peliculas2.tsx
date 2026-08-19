import {type ChangeEvent, type FormEvent, useState} from "react";
import {peliculaApi} from "../api/peliculaApi.ts";
import {useNavigate} from "react-router-dom";

function Peliculas2() {

    const navigate = useNavigate()
    const [titulo, setTitulo] = useState('')
    const [genero, setGenero] = useState('')
    const [anio, setAnio] = useState(0)
    const [precio, setPrecio] = useState(0)

    const crearPelicula = async (e: FormEvent) => {
        e.preventDefault()
        const exito = await peliculaApi.create({
            titulo,
            genero,
            anio,
            precio
            }
        )
        if(exito){
            alert("Guardado")
            navigate("/")
        }
    }

    const handleChangeTitulo = (e: ChangeEvent<HTMLInputElement>) => {
        setTitulo(e.target.value)
    }

    const handleChangeGenero = (e:ChangeEvent<HTMLInputElement>) => {
        setGenero(e.target.value)
    }

    const handleChangeAnio = (e: ChangeEvent<HTMLInputElement>) => {
        setAnio(Number(e.target.value))
    }

    const handleChangePrecio = (e: ChangeEvent<HTMLInputElement>) => {
        setPrecio(Number(e.target.value))
    }

    return (
        <>
            <h1>CREAR PELICULA</h1>
            <form onSubmit={crearPelicula}>
                <input
                type='text'
                value={titulo}
                onChange={handleChangeTitulo}
                placeholder={"Ingrese el titulo"}
                required
                />
                <br/>
                <input
                type='text'
                value={genero}
                onChange={handleChangeGenero}
                placeholder={"Ingrese genero"}
                required/>
                <br/>
                <input
                type='number'
                value={precio === 0 ? '' :precio}
                onChange={handleChangePrecio}
                placeholder={"Ingrese precio"}
                required/>
                <br/>
                <input
                type='number'
                value={anio === 0 ? '':anio}
                onChange={handleChangeAnio}
                placeholder={"Ingrese anio"}
                required/>
                <button type='submit'>Guardar Producto</button>
            </form>
        </>
    )
}

export default Peliculas2