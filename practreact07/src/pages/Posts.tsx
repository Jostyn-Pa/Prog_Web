import {useEffect, useState} from "react";
import type {Posts} from "../models/Posts.ts";
import {Button, Container, Table, TableBody, TableCell, TableHead, TableRow, Typography} from "@mui/material";
import {Link} from "react-router-dom";

function Posts () {

    const [posts, setPosts] = useState<Posts[]>([])
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        setLoading(true)

        fetch("https://jsonplaceholder.typicode.com/posts", {
            method: "GET",
            headers:{
                "Content-Type": "application/json"
            }
        })
            .then(response => response.json())
            .then(data => {
                setPosts(data)
            })
            .catch(error => {
                console.log(error)
            })
            .finally(() => setLoading(false))
    }, []);

    return (
        <>
            <Container sx={{mt: 5}}>
                <Typography variant="h4" gutterBottom>
                    Posts
                </Typography>

                <Table sx={{mt: 2}}>
                    <TableHead>
                        <TableRow>
                            <TableCell>USER_ID</TableCell>
                            <TableCell>ID</TableCell>
                            <TableCell>TITLE</TableCell>
                            <TableCell>BODY</TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {loading && <p>CARGANDO...</p>}
                        {
                            posts.map(post => (
                                <TableRow key={post.id}>
                                    <TableCell>{post.userId}</TableCell>
                                    <TableCell>{post.id}</TableCell>
                                    <TableCell>{post.title}</TableCell>
                                    <TableCell>{post.body}</TableCell>
                                    <TableCell>
                                        <Button color='secondary' variant='outlined' component={Link} to={`/posts/${post.id}`}>VER DETALLES</Button>
                                    </TableCell>
                                </TableRow>
                            ))
                        }
                    </TableBody>
                </Table>
            </Container>
        </>
    )
}

export default Posts