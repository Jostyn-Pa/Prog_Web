import {type ChangeEvent, useEffect, useState} from "react";
import type {Pelicula} from "../models/Pelicula.ts";
import {peliculaApi} from "../api/peliculaApi.ts";

function Peliculas1 () {

    //findALL
    const [peliculas, setPeliculas] = useState<Pelicula[]>([])

    //findById
    const [id, setId] = useState<number>(0)
    const [peliculaBuscada, setPeliculaBuscada] = useState<Pelicula|null>(null)

    const obtenerTodas = async () => {
        const datos = await peliculaApi.findAll()
        setPeliculas(datos)
    }

    const obtenerUna = async () => {
        const datos = await peliculaApi.findById(id)
        if (datos != null) {
            setPeliculaBuscada(datos)
        } else {
            alert("No se encontró esa película")
            setPeliculaBuscada(null)
        }
    }

    const eliminarPelicula = async (id: number) => {

        const confirmar = window.confirm("Seguro?")
        if(!confirmar) return

        const datos = await peliculaApi.delete(id)
        if(datos) {
            obtenerTodas()
        } else {
            alert("No se puede eliminar")
        }
    }

    const handleChangeId = (e: ChangeEvent<HTMLInputElement>) => {
        setId(Number(e.target.value))
    }

    useEffect(() => {
        obtenerTodas()
    }, []);


    return (
        <>
            <h1>PELICULAS</h1>
            <div style={{margin:'15px', color:'red'}}>
                <h2>LISTADO DE TODAS LAS PELÍCULAS</h2>
                {
                    peliculas.map(peli => (
                        <li style={{color:'black'}} key={peli.id}>
                            {peli.titulo} - {peli.genero} - {peli.precio} - {peli.anio}
                            <button onClick={() => eliminarPelicula(peli.id!)}>
                                ELIMINAR
                            </button>
                        </li>
                    ))
                }
            </div>
            <br/>
            <h2>BUSCAR PELICULA POR ID</h2>
            <div>
                <input
                type='number'
                value={id}
                onChange={handleChangeId}
                placeholder={"PONER ID"}
                />
                <button onClick={obtenerUna}>Buscar</button>
            </div>
            {
                peliculaBuscada != null && (
                    <div>
                        {peliculaBuscada.titulo} - {peliculaBuscada.genero} - {peliculaBuscada.anio}
                    </div>
                )
            }
        </>
    )
}
export default Peliculas1