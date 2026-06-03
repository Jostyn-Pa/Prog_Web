import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import type {Posts} from "../models/Posts.ts";

function DetallePost () {

    const {id} = useParams<{id:string}>()
    //Porque se busca un solo artículo
    const [post, setPost] = useState<Posts | null>(null)
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        setLoading(true)

        axios.get(`https://jsonplaceholder.typicode.com/posts/${id}`)
            .then(response => {
                setPost(response.data)
            })
            .catch(error => {
                console.log(error)
            })
            .finally(() => setLoading(false))
    }, [id])

    const handleRegresar = () => {
        navigate(`/`)
    }

    return (
        <>
            <h2>Detalle del Post con ID: {id}</h2>
            {loading && <p>Cargando...</p>}

            <div>
                {
                    post && (
                        <div>
                            <h3>{post.title}</h3>
                            <p>{post.body}</p>
                        </div>
                    )
                }
            </div>
            <br/>
            <button onClick={handleRegresar}>Regresar</button>
        </>
    )
}

export default DetallePost