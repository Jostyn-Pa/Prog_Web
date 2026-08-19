import {type ChangeEvent, type FormEvent, useState} from "react";
import {directorApi} from "../api/directorApi.ts";
import {useNavigate} from "react-router-dom";

function Director2 () {
    const navigate = useNavigate()
    const [nombre, setNombre] = useState('')
    const [pais, setPais] = useState('')

    const guardarDirector = async(e: FormEvent) => {
        e.preventDefault()
        const exito = await directorApi.create({
            nombre,
            pais
        })
        if(exito){
            alert("Guardado")
            navigate("/")
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
            <h1>CREAR DIRECTORES</h1>
            <form onSubmit={guardarDirector}>
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
                <button type='submit'>Guardar Director</button>
            </form>
        </>
    )
}

export default Director2