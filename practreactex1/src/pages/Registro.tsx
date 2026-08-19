import {type ChangeEvent, type FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";

function Registro() {

    const navigate = useNavigate()

    const [name, setName] = useState('')
    const [age, setAge] = useState('')
    const [carrera, setCarrera] = useState('')

    const onChangeName = (e: ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value)
    }

    const onChangeAge = (e: ChangeEvent<HTMLInputElement>) => {
        setAge(e.target.value)
    }

    const onChangeCarrera = (e: ChangeEvent<HTMLInputElement>) => {
        setCarrera(e.target.value)
    }

    const onSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        navigate("/resumen", {state: { name, age, carrera}})
    }

    return (
        <>
            <form onSubmit={onSubmit}>
                <h2 style={{color:'red'}}>Bienvenido a la Pantalla de Registro</h2>
                Nombre
                <input
                type='text'
                value={name}
                onChange={onChangeName}
                placeholder={"Nombre:"}
                />
                <br/>
                Edad
                <input
                type='text'
                value={age}
                onChange={onChangeAge}
                placeholder={"Edad:"}
                />
                <br/>
                Carrera
                <input
                type='text'
                value={carrera}
                onChange={onChangeCarrera}
                placeholder={"Carrera: "}
                />
                <input type='submit' name={"Registro"}/>
            </form>
        </>
    )
}
export default Registro