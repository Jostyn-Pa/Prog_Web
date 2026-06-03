import {useState} from "react";
import {useNavigate} from "react-router-dom";
import * as React from "react";

function FormularioSoporte () {
    // 1. Crea dos estados: 'usuario' y 'problema'
    const [usuario, setUsuario] = useState("")
    const [problema, setProblema] = useState("")

    // 2. Llama al taxista
    const navigate = useNavigate()

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        navigate("/ticket-enviado", {state: {usuario:usuario, problema:problema}})
    }

    //
    const handleChangeUsuario = (e: React.ChangeEvent<HTMLInputElement>) => {
        setUsuario(e.target.value)
    }

    const handleChangeProblema = (e: React.ChangeEvent<HTMLInputElement>) => {
        setProblema(e.target.value)
    }

    return (
        <>
            <form onSubmit={handleSubmit} style={{padding: '20px'}}>
                <h2>Contactar Soporte</h2>

                <label>Tu nombre: </label>
                <input
                type='text'
                value={usuario}
                onChange={handleChangeUsuario}
                />
                <br/>
                <label>Ingresa tu Problema</label>
                <input
                type='text'
                value={problema}
                onChange={handleChangeProblema}
                />

                <button type='submit'>Enviar Ticket</button>
            </form>
        </>
    )
}

export default FormularioSoporte