import {useNavigate, useParams} from "react-router-dom";
import {type ChangeEvent, type FormEvent, useEffect, useState} from "react";
import {peliculaApi} from "../api/peliculaApi.ts";

function Peliculas3 () {

    const navigate = useNavigate()

    //Capturar el id que viene de la URL
    const {id} = useParams<{id:string}>()
    const idNumero = Number(id)
    //titulo,genero,anio,precio
    const[titulo, setTitulo] = useState("")
    const [genero, setGenero] = useState('')
    const[anio, setAnio] = useState(0)
    const [precio, setPrecio] = useState(0)

    const cargarDatos = async() => {
        if(!idNumero) return

        const peliculaActual = await peliculaApi.findById(idNumero)
        if(peliculaActual) {
            setTitulo(peliculaActual.titulo)
            setGenero(peliculaActual.genero)
            setAnio(peliculaActual.anio)
            setPrecio(peliculaActual.precio)
        } else {
            alert("El producto no existe")
            navigate("/")
        }
    }

    useEffect(() => {
        cargarDatos()
    }, [idNumero]);

    const editarPelicula = async (e: FormEvent) => {
        e.preventDefault()
        const peliculaEditada = await peliculaApi.update(idNumero, {
            titulo,
            genero,
            anio,
            precio
        })

        if(peliculaEditada){
            alert("El producto editada")
            navigate("/")
        } else {
            alert("No se pudo actualizar")
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
            <h1>EDITAR PELICULA</h1>
            <form onSubmit={editarPelicula}>
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

                <button type='submit'>Actualizar</button>
            </form>
        </>
    )
}

export default Peliculas3