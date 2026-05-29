import * as React from "react";
import type {FormEvent} from "react";

function MyForm () {

    const [name, setName] = React.useState("")

    const handleSubmit = (event:FormEvent<HTMLFormElement>) => {
        alert(`Nombre Ingresado: ${name}`)
        event?.preventDefault()
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>)=> {
        setName(event.target.value)
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <div>
                    Nombre: <input type='text' value={name} onChange={handleChange}/>
                </div>

                <input type='submit' value='Enviar' />
                <input type='submit' value="Enviar2"/>
                <br/>
                <div>{name}</div>
            </form>
        </>
    )
}

export default MyForm