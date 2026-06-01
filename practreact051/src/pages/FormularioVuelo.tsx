import {useState} from "react";
import {useNavigate} from "react-router-dom";
import * as React from "react";

function FormularioVuelo() {
    const [pasajero, setPasajero] = useState('')
    const [destino, setDestino] = useState('')

    const navigate = useNavigate()

    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        navigate("/boleto", {state: {pasajero: pasajero, destino: destino}})
    }

    const handleChangePasajero = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPasajero(e.target.value)
    }

    const handleChangeDestino = (e:React.ChangeEvent<HTMLInputElement>) => {
        setDestino(e.target.value)
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <h2>Reserva tu Vuelo</h2>
                Nombre:
                <input
                type='text'
                value={pasajero}
                onChange={handleChangePasajero}
                />
                <br/>
                Destino:
                <input
                type='text'
                value={destino}
                onChange={handleChangeDestino}
                />
                <br/>
                <input
                type='submit'
                value="Comprar Boleto"
                />
            </form>
        </>
    )
}

export default FormularioVuelo