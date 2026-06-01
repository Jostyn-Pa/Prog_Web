import * as React from "react";
import {useNavigate} from "react-router-dom";

function Form1 () {

    const [name, setName] = React.useState('')

    const navigate = useNavigate()

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value)
    }

    const handleSubmit = (event:React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()

        navigate("/form2", {state: {message: name}})
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <h2>Bienvenido Al Formulario 1</h2>
                Nombre:
                <input
                type="text"
                value={name}
                onChange={handleChange}
                placeholder={"Ingresa tu Nombre"}
                />

                <input type='submit' name="Enviar"/>
                <div>{name}</div>
            </form>
        </>
    )
}

export default Form1