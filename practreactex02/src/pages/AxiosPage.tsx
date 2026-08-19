import {type ChangeEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";

interface User {
    id: number
    name: string
    email: string
    phone: string
}

function AxiosPage () {

    const [id, setId] = useState(1);
    const [user, setUser] = useState<User | null>(null)
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

    const handleChangePostId = (e: ChangeEvent<HTMLInputElement>) => {
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

    const regresar = () => {
        navigate("/")
    }

    return (
        <>
            <div>
                <h2>Consulta AXIOS</h2>
                <input
                type='text'
                placeholder={"id"}
                value={id}
                onChange={handleChangePostId}
                />
                <button onClick={consultarDatos}>Consultar</button>
                {loading && <p>Cargando</p>}
            </div>

            <br/>
            {
                user && (
                    <div>
                        <p>ID: {user.id}</p>
                        <p>Name:{user.name}</p>
                        <p>Email: {user.email}</p>
                        <p>Phone: {user.phone}</p>
                    </div>
                )
            }
            <button onClick={regresar}>Regresar</button>
        </>
    )
}

export default AxiosPage