import {useState} from "react";
import {Button, Container, Table, TableBody, TableCell, TableHead, TableRow, Typography} from "@mui/material";
import axios from "axios";
import type {Posts} from "../models/Posts.ts";
import {Link} from "react-router-dom";

interface Post {
    id: number,
    title: string,
    body: string
}

function Posts() {

    const [posts, setPosts]= useState<Post[]>([])
    const [loading, setLoading] = useState(false)

    const fetchPosts =  () => {
        setLoading(true)

        axios.get('https://jsonplaceholder.typicode.com/posts')
            .then(response => {
                setPosts(response.data)
            }).catch(error=>alert(error))
            .finally(()=>setLoading(false) );
    }


    return (
        <>
            <Container sx={{mt : 4}}>
                <Typography variant="h4" gutterBottom>
                    Posts
                </Typography>

                <Button
                    variant="contained"
                    color="primary"
                    onClick={fetchPosts}
                    disabled={loading}
                    >
                    {loading ? 'Cargando...' : 'Cargar Posts'}

                </Button>

                <Table sx={{mt : 2}}>
                    <TableHead>
                        <TableRow>
                            <TableCell>Id</TableCell>
                            <TableCell>Title</TableCell>
                            <TableCell>Body</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {posts.map(post => (
                            <TableRow key={post.id}>
                                <TableCell>{post.id}</TableCell>
                                <TableCell>{post.title}</TableCell>
                                <TableCell>{post.body}</TableCell>
                                <TableCell>
                                    <Button color={"secondary"} variant="outlined" size="small"
                                    component={Link} to={`/posts/${post.id}`}>
                                        Ver Detalle
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>

            </Container>
        </>
    )
}
export default Posts;