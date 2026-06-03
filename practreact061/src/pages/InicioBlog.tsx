import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios"
import type {Posts} from "../models/Posts.ts";

function InicioBlog () {

    const navigate = useNavigate()
    const [posts, setPosts] = useState<Posts[]>([])
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true)
        axios.get(`https://jsonplaceholder.typicode.com/posts`)
            .then(response => {
                setPosts(response.data)
            })
            .catch(error => {
                console.log(error)
            })
            .finally(() => setLoading(false))
    }, [])

    // RESPUESTA 1: Tu función extraída que recibe un ID
    /*const handleNavigateToArticle = (id: number) => {
        navigate(`/posts/${id}`)
    }
    <button onClick={() => handleNavigateToArticle(post.id)}>
                                        Leer artículo*/

    return (
        <>
            <h1>Inicio Blog</h1>
            {loading && <p>Cargando...</p>}
            <div>
                <h2>Lista de Posts</h2>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>TITLE</th>
                        <th>Navegar al Artículo</th>
                    </tr>
                    </thead>

                    <tbody>
                    {posts.map(post => (
                        <tr key={post.id}>
                            <td>{post.id}</td>
                            <td>{post.title}</td>
                            <td>
                                <button onClick={() => navigate(`/posts/${post.id}`)}>Leer artículo</button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}

export default InicioBlog