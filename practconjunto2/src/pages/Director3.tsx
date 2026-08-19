import {useNavigate, useParams} from "react-router-dom";
import {type ChangeEvent, type FormEvent, useEffect, useState} from "react";
import {directorApi} from "../api/directorApi.ts";

function Director3 () {

    const navigate = useNavigate()

    //capturar el id
    const {id} = useParams<{id:string}>()
    const idNumero = Number(id)

    //las constantes necesarias
    const [nombre, setNombre] = useState('')
    const [pais, setPais] = useState('')

    const cargarDatos = async () => {
        if(!idNumero) return
        const directorActual = await directorApi.findById(idNumero)
        if(directorActual) {
            setNombre(directorActual.nombre)
            setPais(directorActual.pais)
        } else {
            alert("No se encontro ese id")
            navigate("/")
        }
    }

    useEffect(() => {
        cargarDatos()
    }, [idNumero]);

    const editarDatos = async (e:FormEvent) => {
        e.preventDefault()
        const exito = await directorApi.actualizar(idNumero, {
            nombre,
            pais
        })
        if(exito) {
            alert("Actualizado exitosamente")
            navigate("/")
        } else {
            alert("No se pudo actualizar")
        }
    }

    const handleChangeNombre = (e: ChangeEvent<HTMLInputElement>) => {
        setNombre(e.target.value)
    }

    const handleChangePais = (e: ChangeEvent<HTMLInputElement>) => {
        setPais(e.target.value)
    }

    return (
        <>
            <h1>EDITAR DIRECTORES</h1>
            <form onSubmit={editarDatos}>
                <input
                    type='text'
                    value={nombre}
                    onChange={handleChangeNombre}
                    placeholder={"Ingrese el nombre"}
                    required/>
                <br/>
                <input
                    type='text'
                    value={pais}
                    onChange={handleChangePais}
                    placeholder={"Ingrese el pais"}
                    required
                />
                <br/>
                <button type='submit'>EDITAR</button>
            </form>
        </>
    )
}

export default Director3