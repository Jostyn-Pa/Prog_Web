import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

interface User {
    id: number
    name: string
    email: string
    phone: string
}

function ListaGeneralFetch () {

    const [user, setUser] = useState<User[]>([])
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

    const obtenerDatos = () => {
        setLoading(true)

        fetch("https://jsonplaceholder.typicode.com/users",
            {
                method: "GET",
                headers: {
                    "Content-Type": "application/json"
                }
            })
            .then(response => response.json())
            .then(data => setUser(data))
            .catch(error => console.log("Hubo un error" + error))
            .finally(() => setLoading(false))
    }

    const siguientePagina = () => {
        navigate("/consultaAxios")
    }

    useEffect(() => {
        obtenerDatos()
    }, [])

    return (
        <>
            <div>
                <h2>LISTA GENERAL DE USUARIOS USANDO FETCH</h2>
            </div>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>EMAIL</th>
                    <th>PHONE</th>
                </tr>
                </thead>

                <tbody>
                {
                    user.map(usuario => (
                        <tr key={usuario.id}>
                            <td>{usuario.id}</td>
                            <td>{usuario.name}</td>
                            <td>{usuario.email}</td>
                            <td>{usuario.phone}</td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
            <br/>
            <button onClick={obtenerDatos}>Obtener DATOS</button>
            {loading && <p>Cargando...</p>}
            <button onClick={siguientePagina}>Siguiente</button>
        </>
    )
}

export default ListaGeneralFetch