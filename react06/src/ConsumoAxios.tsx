import {useState} from "react";
import axios from "axios";

interface Post {
    id: number
    title: string
    body: string
}

function ConsumoAxios () {

    const [loading, setLoading] = useState(false)
    const [posts, setPosts] = useState<Post[]>([])

    const handleClick = () => {
        setLoading(true)
        axios.get<Post[]>('https://jsonplaceholder.typicode.com/posts')
            .then(response => {
                setPosts(response.data)
            })
            .catch(error => alert(error))
            .finally(() => setLoading(false))
    }

    return (
        <>
            <h2>AXIOS</h2>
            <button onClick={handleClick}>Consultar</button>
            {loading && <p>Cargando...</p>}

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>TITLE</th>
                    <th>BODY</th>
                </tr>
                </thead>
                <tbody>
                {
                    posts.map(post => (
                        <tr key = {post.id}>
                            <td>{post.id}</td>
                            <td>{post.title}</td>
                            <td>{post.body}</td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </>
    )
}

export default ConsumoAxios