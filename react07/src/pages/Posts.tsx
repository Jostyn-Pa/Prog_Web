import {useEffect, useState} from "react";
import {Button, Container, Table, TableBody, TableCell, TableHead, TableRow, Typography} from "@mui/material";
import axios from "axios";
import type {Post} from "../models/Post.ts";
import { Link } from "react-router-dom";


function Posts() {

    const [posts, setPosts] = useState<Post[]>([]);
    const [, setLoading] = useState(false);

    const fetchPosts = () => {
        setLoading(true);
        axios.get<Post[]>("https://jsonplaceholder.typicode.com/posts")
            .then(response => {
                setPosts(response.data);
            })
            .catch(error => alert(error))
            .finally(() => setLoading(false));
    };

    useEffect(() => {
        
    fetchPosts();  
    
    },[]);

    return (
        <>
            <Container sx={{mt: 5}}>
                <Typography variant="h4" gutterBottom>
                    Posts
                </Typography>

                <Table sx={{mt: 2}}>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Title</TableCell>
                            <TableCell>Body</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {
                            posts.map((post) => (
                                <TableRow key={post.id}>
                                    <TableCell>{post.id}</TableCell>
                                    <TableCell>{post.title}</TableCell>
                                    <TableCell>{post.body}</TableCell>
                                    <TableCell>
                                        <Button color={"secondary"} variant="outlined" component={Link} to={`/posts/${post.id}`}>
                                            Ver detalles
                                        </Button>
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

export default Posts;