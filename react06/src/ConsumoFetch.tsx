import {useState} from "react";
import * as React from "react";

interface Post {
    id: number
    title: string
    body: string
}

function ConsumoFetch () {

    //const url = 'https://jsonplaceholder.typicode.com/posts/1'

    const [postId, setPostId] = useState(1)
    const [post, setPost] = useState<Post | null>(null)
    const [loading, setLoading] = useState(false)

    const handleChangePostId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setPostId(Number(e.target.value))
    }

    const handleClick = () => {
        setLoading(true)

        fetch(`https://jsonplaceholder.typicode.com/posts/${postId}`,
            {
                method: 'GET',
                headers:{
                    'Content-Type': 'application/json'
                }
            }
        ).then(response => response.json())
            .then(data => {
                setPost(data)
            })
            .finally(() => setLoading(false))
    }

    return (
        <>
            <h2>FETCH</h2>
            Id: <input type='text' placeholder='id'
                       value={postId}
                       onChange={handleChangePostId}
        />
            <button onClick={handleClick}>Consultar</button>
            {loading && <p>Cargando...</p>}
            <br/>
            {
                post && (
                    <div>
                        <p>ID: {post.id}</p>
                        <p>TITLE: {post.title}</p>
                        <p>BODY: {post.body}</p>
                    </div>
                )
            }
        </>
    )
}

export default ConsumoFetch