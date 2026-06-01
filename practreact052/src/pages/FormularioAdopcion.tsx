import {useState} from "react";
import {useNavigate} from "react-router-dom";
import * as React from "react";

function FormularioAdopcion() {

    const [name, setName] = useState("")
    const [animal, setAnimal] = useState("")

    // 2. Llama al Hook que te permite navegar (el taxista) y guárdalo en una variable
    const navigate = useNavigate()

    const handleSubmit = (e:React.FormEvent<HTMLFormElement>) => {
        // 3. Evita que la página se recargue por defecto
        e.preventDefault()

        // 4. Usa tu variable de navegación para viajar a "/resumen"
        // y envíale en el 'state' un objeto con el adoptante y la mascota
        navigate("/resumen", {state: {name: name, animal: animal}})
    }

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value)
    }

    const handleAnimalChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setAnimal(e.target.value)
    }

    return (
        <>
            <form onSubmit={handleSubmit} style={{padding: '20px'}}>
                <h2>Centro de Adopción</h2>

                <label>Tu nombre: </label>
                <input
                type='text'
                value={name}
                onChange={handleNameChange}
                />
                <br/>
                <label>Qué Animal Quieres Adoptar?</label>
                <input
                type='text'
                value={animal}
                onChange={handleAnimalChange}
                />
                <br/>
                <input type='submit' value="Adoptar Mascota"/>
            </form>
        </>
    )
}

export default FormularioAdopcion