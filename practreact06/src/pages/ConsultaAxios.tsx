import {useState} from "react";
import * as React from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

interface User {
    id: number
    name: string
    email: string
    phone: string
}

function ConsultaAxios() {

    const [id, setId] = useState(1)
    const [user, setUser] = useState<User | null>(null)

    const [loading, setLoading] = useState(false)

    const navigate = useNavigate()

    const handleChangePostId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setId(Number(e.target.value))
    }

    const consultarDatos = () => {
        setLoading(true)

        axios.get<User>(`https://jsonplaceholder.typicode.com/users/${id}`)
            .then(response => {
                setUser(response.data)
            })
            .catch(err => {
                console.log(err)
            })
            .finally(() => setLoading(false))
    }

    const handleRegresar = () => {
        navigate("/")
    }

    return (
        <>
            <div>
                <h2>Consulta Axios</h2>

                <input type='text' placeholder='id'
                value={id} onChange={handleChangePostId}
                />

                <button onClick={consultarDatos}>Consultar</button>
                {loading && <p>Cargando datos...</p>}
            </div>

            <br/>
            {
                user && (
                        <div>
                            <p>ID: {user.id}</p>
                            <p>NAME: {user.name}</p>
                            <p>EMAIL: {user.email}</p>
                            <p>PHONE: {user.phone}</p>
                        </div>

                )
            }
            <button onClick={handleRegresar}>Regresar</button>
        </>
    )
}

export default ConsultaAxios