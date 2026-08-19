import {type ChangeEvent, useEffect, useState} from "react";
import type {Director} from "../models/Director.ts";
import {directorApi} from "../api/directorApi.ts";
import {useNavigate} from "react-router-dom";

function Director1 () {

    const navigate = useNavigate()

    const [directores, setDirectores] = useState<Director[]>([])

    //Para findById
    const [id, setId] = useState<number>(0)
    const [directorBuscado, setDirectorBuscado] = useState<Director|null>(null)

    const findAll = async () => {
        const datos = await directorApi.findAll()
        setDirectores(datos)
    }

    const findById = async() => {
        const datos = await directorApi.findById(id)
        if(datos != null){
            setDirectorBuscado(datos)
        } else {
            // 👇 1. Cambiamos el console.log por un alert
            alert("No se encontró el director Buscado")
            setDirectorBuscado(null)
        }
    }

    const eliminarDirector = async (id:number) => {
        const confirmar = window.confirm("Estas seguro de eliminar?")
        if(!confirmar) return

        const datos = await directorApi.delete(id)
        if(datos){
            findAll()
        } else{
            alert("Hubo un error al eliminar el director")
        }
    }

    const cambiarId = (e: ChangeEvent<HTMLInputElement>) => {
        setId(Number(e.target.value))
    }

    const handleCambiarPag = () => {
        navigate("/peliculas1")
    }

    useEffect(() => {
        findAll()
    }, []);

    return (
        <>
            <h1>BIENVENIDOS A LA PAGINA DE INICIO</h1>
            <br/>
            <h2>DIRECTORES</h2>
            <ul>
                {
                    directores.map(dir => (
                        <li key={dir.id}>
                            {dir.nombre} - {dir.pais}

                            {/* Detalle visual: usualmente los botones de eliminar son rojos (red), no verdes (green) 😉 */}
                            <button onClick={() => eliminarDirector(dir.id!)}
                                    style={{marginLeft:'10px', color:'red'}}
                            >
                                Eliminar
                            </button>
                        </li>
                    ))
                }
            </ul>

            <br/>
            <h2>BUSCAR DIRECTOR POR ID</h2>
            <input
                type='number'
                value={id === 0 ? '' : id} // 👇 2. Ocultamos el 0 inicial para que se vea más limpio
                onChange={cambiarId}
                placeholder={'Ingresar ID'}
            />
            <button onClick={findById}>Buscar</button>

            {
                directorBuscado != null && (
                    <div style={{ marginTop: '15px' }}>
                        <strong>Resultado: </strong>
                        {directorBuscado.nombre} - {directorBuscado.pais}
                    </div>
                )
            }

            <button onClick={handleCambiarPag}>Pasar a Peliculas</button>
        </>
    )
}

export default Director1;