import {useLocation, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import type {Empleado} from "../models/Empleado.ts";
import axios from "axios";

function PerfilOptimizado () {
    const {id} = useParams<{id: string}>()
    const location = useLocation()
    const [loading, setLoading] = useState(false)

    // 1. Abrimos la maleta
    const datosEnMaleta = location.state?.datosEmpleado

    // 2. Si hay maleta, arranca con esos datos. Si no, arranca nulo.
    const [empleado, setEmpleado] = useState<Empleado | null>(datosEnMaleta || null)

    useEffect(() => {
        // CORRECCIÓN 2: El escudo protector.
        // Si ya tenemos al empleado (porque venía en la maleta), nos salimos del useEffect y NO hacemos Axios.
        if (empleado) {
            return;
        }

        // --- SOLO LLEGA AQUÍ SI LA MALETA ESTABA VACÍA (Plan B) ---
        setLoading(true)
        axios.get(`https://jsonplaceholder.typicode.com/users/${id}`)
            .then(res => {
                setEmpleado(res.data)
            })
            .catch(err => {
                console.log(err)
            })
            .finally(() => setLoading(false))

    }, [id])

    return (
        <>
            <h2>Perfil del Empleado</h2>
            {loading && <p>Cargando desde internet (Plan B)...</p>}

            <div>
                {
                    empleado ? (
                        <div style={{border: '1px solid black', padding: '10px'}}>
                            <h3>Nombre: {empleado.name}</h3>
                            <h3>Email: {empleado.email}</h3>
                            <p>Sitio web: {empleado.website}</p>
                        </div>
                    ) : (
                        !loading && <p>No se encontró el empleado</p>
                    )
                }
            </div>
        </>
    )
}

export default PerfilOptimizado