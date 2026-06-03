import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import type {Empleado} from "../models/Empleado.ts";

function ListaGeneral () {
    // TIP: Mejor en plural para no confundirte
    const [empleados, setEmpleados] = useState<Empleado[]>([])
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        setLoading(true)
        axios.get(`https://jsonplaceholder.typicode.com/users`)
            .then(res => setEmpleados(res.data))
            .catch(err => console.log(err))
            .finally(() => setLoading(false))
    }, [])

    return (
        <>
            <h1 style={{color:'red'}}>Lista General de Empleados</h1>
            {loading && <p>Cargando...</p>}

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>ACCION</th>
                </tr>
                </thead>
                <tbody>
                {
                    empleados.map(emp => (
                        <tr key={emp.id}>
                            <td>{emp.id}</td>
                            <td>{emp.name}</td>
                            <td>
                                {/* CORRECCIÓN 1: Mandamos 'emp' (el individual), no el arreglo completo */}
                                <button onClick={() => navigate(`/perfil/${emp.id}`, { state: { datosEmpleado: emp } })}>
                                    Ver Perfil
                                </button>
                            </td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </>
    )
}

export default ListaGeneral