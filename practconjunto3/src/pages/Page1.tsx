import {type ChangeEvent, useEffect, useState} from "react";
import type {Medico} from "../models/Medico.ts";
import {medicoApi} from "../api/medicoApi.ts";

function Page1 () {

    //const navigate = useNavigate()

    //findAll
    const [medico, setMedico] = useState<Medico[]>([])

    //findById
    const[id, setId] = useState<number>(0)
    const[medicoEncontrado, setMedicoEncontrado] = useState<Medico|null>(null)

    const obtenerTodos = async () => {
        const datos = await medicoApi.findAll()
        setMedico(datos)
    }

    useEffect(() => {
        obtenerTodos()
    }, []);

    const obtenerPorId = async () => {
        const datos = await medicoApi.findById(id)
        if(datos != null) {
            setMedicoEncontrado(datos)
        } else {
            alert("No se pudo obtener es ID")
            setMedicoEncontrado(null)
        }
    }

    const handleChangeId = (e: ChangeEvent<HTMLInputElement>) => {
        setId(Number(e.target.value))
    }

    return (
        <>
            <h2>LISTA DE TODOS LOS MÉDICOS</h2>
            <div>
                {
                    medico.map(med => (
                        <li key={med.id}>
                            {med.nombre} -- {med.especialidad}
                        </li>
                    ))
                }
            </div>

            <h2>BUSCAR UN MÉDICO POR ID</h2>
            <input
            type='number'
            value={id === 0 ? '': id}
            onChange={handleChangeId}
            placeholder={"Ingrese id"}
            />
            <button onClick={obtenerPorId}>BUSCAR</button>
            <div>
                {
                    medicoEncontrado != null && (
                        <p>{medicoEncontrado.nombre} --- {medicoEncontrado.especialidad}</p>
                    )
                }
            </div>
        </>
    )
}

export default Page1